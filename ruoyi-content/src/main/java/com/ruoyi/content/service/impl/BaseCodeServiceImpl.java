package com.ruoyi.content.service.impl;

import com.ruoyi.content.domain.BaseCode;
import com.ruoyi.content.domain.BaseCodeExample;
import com.ruoyi.content.domain.BaseCodeTree;
import com.ruoyi.content.exception.ParameterException;
import com.ruoyi.content.mapper.BaseCodeExMapper;
import com.ruoyi.content.mapper.BaseCodeMapper;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.redis.RedisManager;
import com.ruoyi.content.service.BaseCodeService;
import com.ruoyi.content.utils.DateUtil;
import com.ruoyi.content.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * 说明：基础数据查询实现
 *
 * @author wang.q
 * @date 2017年8月25日
 */
@Service
public class BaseCodeServiceImpl implements BaseCodeService {
    private static final Logger logger = LoggerFactory.getLogger(BaseCodeServiceImpl.class);
    @Autowired
    private BaseCodeMapper baseCodeMapper;
    @Autowired
    private BaseCodeExMapper baseCodeExMapper;
    @Autowired
    private RedisManager redisManager;

    /**
     * 根据type类型查询基础数据
     */
    @Override
    public List<BaseCode> queryBaseCodeByType(String codeType) {
        logger.info("进入查询基础数据的方法");
        String companyId = "1";//公司id
        String branchId = "86";
        // 首先判断代码类型是否为空
        if (StringUtils.isBlank(codeType)) {
            logger.info("根据代码类型数据字典--参数为空codeType【{}】", codeType);
            return new ArrayList<BaseCode>();
        }
        logger.info("根据代码类型数据字典--参数codeType【{}】", codeType);
        List<BaseCode> list = null;
        BaseCodeExample example = new BaseCodeExample();
        BaseCodeExample.Criteria criteria = example.createCriteria();
        criteria.andCodeTypeEqualTo(codeType);
        criteria.andStateEqualTo("0");
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andBranchIdLike("%" + branchId + "%");
        example.setOrderByClause("ORDER_NO");
        list = baseCodeMapper.selectByExample(example);
        logger.info("查询基础数据结束，查询到的结果为【{}】" + JsonUtil.objectToJackson(list));
        logger.info("查询基础数据的方法结束！");
        return list;
    }

    /**
     * 保存栏目信息
     */
    @Override
    public Message saveBaseCode(BaseCode baseCode) {
        logger.info("进入保存栏目信息的业务方法。");
        if (baseCode == null || StringUtils.isBlank(baseCode.getCodeType())
                || StringUtils.isBlank(baseCode.getCodeCname())) {
            logger.info("创建基础数据失败，参数不足【{}】", JsonUtil.objectToJackson(baseCode));
            throw new ParameterException("创建失败，参数不足！");
        }
        logger.info("创建基础数据的参数【{}】", JsonUtil.objectToJackson(baseCode));
        String companyId = "1";
        Message msg = new Message();
        try {
            // 获取当前code最大值
            BaseCodeExample example = new BaseCodeExample();
            //Criteria criteria = example.createCriteria();
            //criteria.andCodeTypeEqualTo(baseCode.getCodeType());
            //criteria.andCompanyIdEqualTo(companyId);
            logger.info("查询codecode");
            example.setOrderByClause(" ID*1 DESC ");
            List<BaseCode> list = baseCodeMapper.selectByExample(example);
            logger.info("list:" + JsonUtil.objectToJackson(list));
            if (list != null && list.size() > 0) {
                String code = list.get(0).getId().toString();
                baseCode.setCodeCode((Integer.parseInt(code) + 1) + "");
            } else {
                baseCode.setCodeCode("1");
            }
            //更新刚创建的栏目的序号
            BaseCodeExample exampleOrder = new BaseCodeExample();
            exampleOrder.createCriteria().andCodeTypeEqualTo(baseCode.getCodeType())
                    .andCompanyIdEqualTo(companyId);
            Integer orderNo = baseCodeMapper.countByExample(exampleOrder) + 1;
            logger.info("查询出来的总数：" + orderNo);
            baseCode.setOrderNo(orderNo.toString());
            baseCode.setState("0");
            baseCode.setCompanyId(companyId);
            baseCode.setCreateTime(DateUtil.getDate());
            baseCode.setCreateUser("");
            int result = baseCodeMapper.insert(baseCode);
            if (result > 0) {
                if ("86".equals(baseCode.getBranchId())) {
                    Set<byte[]> baseCodeKeys = redisManager.queryByVague("baseCode_companyId" + companyId + "_branchId" + "*");
                    for (byte[] baseCodeKey : baseCodeKeys) {
                        redisManager.delete(baseCodeKey);
                    }
                } else {
                    String delCodeType = null;
                    logger.info("查询到的codeType：" + baseCode.getCodeType());
                    try {
                        Integer.parseInt(baseCode.getCodeType());//如果codeType能转成数字说明是三级栏目
                        BaseCodeExample example1 = new BaseCodeExample();
                        BaseCodeExample.Criteria createCriteria = example1.createCriteria();
                        createCriteria.andCodeCodeEqualTo(baseCode.getCodeType());
                        List<BaseCode> basecodeEx = baseCodeMapper.selectByExample(example1);
                        delCodeType = basecodeEx.get(0).getCodeType();
                    } catch (Exception e) {
                        delCodeType = baseCode.getCodeType();
                    }
                    logger.info("删除的redis的key是：" + "baseCode_companyId" + companyId + "_branchId" + baseCode.getBranchId() + "_" + delCodeType);
                    redisManager.delete("baseCode_companyId" + companyId + "_branchId" + baseCode.getBranchId() + "_" + delCodeType);


                    //redisManager.delete("baseCode_companyId"+companyId+"_branchId"+baseCode.getBranchId()+"_"+baseCode.getCodeType());
                }
                msg.setInfo("创建成功！");
                msg.setResult(true);
                HashMap<String, String> returnCode = new HashMap<String, String>();
                returnCode.put("codeCode", baseCode.getCodeCode());
                msg.setObject(returnCode);
                // 触发更新redis
            } else {
                logger.info("栏目插入数据库失败");
                msg.setInfo("创建失败！");
                msg.setResult(false);
            }
        } catch (Exception e) {
            logger.info("添加栏目异常【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("系统异常，请稍后再试！");
            msg.setResult(false);
        }
        logger.info("保存栏目信息的业务方法结束。");
        return msg;
    }

    /**
     * 更新栏目信息
     */
    @Override
    public Message updateBaseCode(BaseCode baseCode) {
        logger.info("进入更新栏目的业务方法。");

        if (baseCode == null || StringUtils.isBlank(String.valueOf(baseCode.getId())) || StringUtils.isBlank(baseCode.getCodeCname())) {
            logger.info("更新栏目失败，数据不完整【{}】", JsonUtil.objectToJackson(baseCode));
            throw new ParameterException("栏目更新失败，参数不足！");
        }
        String companyId = "1";//公司id
        Message msg = new Message();
        try {
            // 查询是否存在相同类型的code值
            BaseCodeExample example = new BaseCodeExample();
            BaseCodeExample.Criteria criteria = example.createCriteria();
            criteria.andCodeTypeEqualTo(baseCode.getCodeType());
            criteria.andCodeCodeEqualTo(baseCode.getCodeCode());
            criteria.andCompanyIdEqualTo(companyId);
            criteria.andBranchIdEqualTo(baseCode.getBranchId());
            criteria.andStateEqualTo("0");
            List<BaseCode> list = baseCodeMapper.selectByExample(example);
            if (list != null && list.size() > 0 && !list.get(0).getId().equals(baseCode.getId())) {
                logger.info("更新栏目失败相同类型相同code已经存在有效的数据！【{}】", JsonUtil.objectToJackson(baseCode));
                msg.setInfo("更新失败，和现有栏目存在冲突，无法更换类型！");
                msg.setResult(false);
                return msg;
            }
            baseCode.setUpdateTime(DateUtil.getDate());
            baseCode.setUpdateUser("");
            int result = baseCodeMapper.updateByPrimaryKeySelective(baseCode);
            if (result > 0) {
                if ("86".equals(baseCode.getBranchId())) {
                    Set<byte[]> baseCodeKeys = redisManager.queryByVague("baseCode_companyId" + companyId + "_branchId" + "*");
                    for (byte[] baseCodeKey : baseCodeKeys) {
                        redisManager.delete(baseCodeKey);
                    }
                } else {
                    String delCodeType = null;
                    logger.info("查询到的codeType：" + baseCode.getCodeType());
                    try {
                        Integer.parseInt(baseCode.getCodeType());//如果codeType能转成数字说明是三级栏目
                        BaseCodeExample example1 = new BaseCodeExample();
                        BaseCodeExample.Criteria createCriteria = example1.createCriteria();
                        createCriteria.andCodeCodeEqualTo(baseCode.getCodeType());
                        List<BaseCode> basecodeEx = baseCodeMapper.selectByExample(example1);
                        delCodeType = basecodeEx.get(0).getCodeType();
                    } catch (Exception e) {
                        delCodeType = baseCode.getCodeType();
                    }
                    logger.info("删除的redis的key是：" + "baseCode_companyId" + companyId + "_branchId" + baseCode.getBranchId() + "_" + delCodeType);
                    redisManager.delete("baseCode_companyId" + companyId + "_branchId" + baseCode.getBranchId() + "_" + delCodeType);
                    //redisManager.delete("baseCode_companyId"+companyId+"_branchId"+baseCode.getBranchId()+"_"+baseCode.getCodeType());
                }
                msg.setInfo("更新栏目成功！");
                msg.setResult(true);
                // 触发更新redis

            } else {
                logger.info("栏目更新数据库失败");
                msg.setInfo("更新失败！");
                msg.setResult(false);
            }
        } catch (Exception e) {
            logger.info("更新栏目异常【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("更新栏目异常，请稍候再试！");
            msg.setResult(false);
        }
        logger.info("更新栏目的业务方法结束。");
        return msg;
    }

    /**
     * 修改栏目状态
     */
    @Override
    public Message queryBaseCode(String ids) {
        String companyId = "1";
        logger.info("进入修改栏目信息的业务方法。");
        if (StringUtils.isBlank(ids)) {
            logger.info("更新栏目失败，参数不足 ids【{}】", ids);
            throw new ParameterException("栏目更新失败，参数不足！");
        }
        Message msg = new Message();
        try {
            String[] arr = ids.split(",");
            for (String id : arr) {
                if (StringUtils.isNotBlank(id)) {
                    BaseCode baseCode = baseCodeMapper.selectByPrimaryKey(Integer.valueOf(id));
                    String state = baseCode.getState();
                    if ("0".equals(state)) {
                        baseCode.setState("1");
                    } else {
                        baseCode.setState("0");
                    }
                    //标志位0:有效1:无效
                    baseCode.setUpdateTime(DateUtil.getDate());
                    baseCode.setUpdateUser("");
                    baseCodeMapper.updateByPrimaryKey(baseCode);
                    if (baseCodeMapper.updateByPrimaryKey(baseCode) > 0) {
                        if ("86".equals(baseCode.getBranchId())) {
                            Set<byte[]> baseCodeKeys = redisManager.queryByVague("baseCode_companyId" + companyId + "_branchId" + "*");
                            for (byte[] baseCodeKey : baseCodeKeys) {
                                redisManager.delete(baseCodeKey);
                            }
                        } else {

                            String delCodeType = null;
                            logger.info("查询到的codeType：" + baseCode.getCodeType());
                            try {
                                Integer.parseInt(baseCode.getCodeType());//如果codeType能转成数字说明是三级栏目
                                BaseCodeExample example = new BaseCodeExample();
                                BaseCodeExample.Criteria createCriteria = example.createCriteria();
                                createCriteria.andCodeCodeEqualTo(baseCode.getCodeType());
                                List<BaseCode> basecodeEx = baseCodeMapper.selectByExample(example);
                                delCodeType = basecodeEx.get(0).getCodeType();
                            } catch (Exception e) {
                                delCodeType = baseCode.getCodeType();
                            }

                            logger.info("删除的redis的key是：" + "baseCode_companyId" + companyId + "_branchId" + baseCode.getBranchId() + "_" + delCodeType);
                            redisManager.delete("baseCode_companyId" + companyId + "_branchId" + baseCode.getBranchId() + "_" + delCodeType);
                            //redisManager.delete("baseCode_companyId"+companyId+"_branchId"+baseCode.getBranchId()+"_"+baseCode.getCodeType());
                        }
                        msg.setInfo("成功修改栏目状态。");
                        msg.setResult(true);
                        logger.info("成功修改栏目状态！");
                        //"baseCode_companyId"+companyId+"_branchId"+branch+"_"+codeType
                    } else {
                        msg.setInfo("修改栏目状态失败。");
                        msg.setResult(false);
                        logger.info("修改栏目状态失败！");
                    }

                } else {
                    msg.setInfo("修改栏目状态失败。");
                    msg.setResult(false);
                    logger.info("修改栏目状态失败！");
                }
            }

        } catch (Exception e) {
            logger.info("修改栏目状态异常【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("修改栏目状态失败！");
            msg.setResult(false);
        }
        logger.info("修改栏目状态的业务方法结束。");
        return msg;
    }

    /**
     * 删除栏目信息
     */
    @Override
    public Message delBaseCode(String ids) {
        String companyId = "1";
        logger.info("进入删除栏目信息的业务方法。");
        if (StringUtils.isBlank(ids)) {
            logger.info("删除栏目失败，参数不足 ids【{}】", ids);
            throw new ParameterException("栏目删除失败，参数不足！");
        }
        Message msg = new Message();
        try {
            String[] arr = ids.split(",");
            for (String id : arr) {
                if (StringUtils.isNotBlank(id)) {
                    BaseCode baseCode = baseCodeMapper.selectByPrimaryKey(Integer.valueOf(id));
//					//标志位0:有效1:无效;2删除
                    baseCode.setState("2");
                    baseCode.setUpdateTime(DateUtil.getDate());
                    baseCode.setUpdateUser("");
                    baseCodeMapper.updateByPrimaryKey(baseCode);
                    if (baseCodeMapper.updateByPrimaryKey(baseCode) > 0) {
                        if ("86".equals(baseCode.getBranchId())) {
                            Set<byte[]> baseCodeKeys = redisManager.queryByVague("baseCode_companyId" + companyId + "_branchId" + "*");
                            for (byte[] baseCodeKey : baseCodeKeys) {
                                redisManager.delete(baseCodeKey);
                            }
                        } else {
                            String delCodeType = null;
                            logger.info("查询到的codeType：" + baseCode.getCodeType());
                            try {
                                Integer.parseInt(baseCode.getCodeType());//如果codeType能转成数字说明是三级栏目
                                BaseCodeExample example = new BaseCodeExample();
                                BaseCodeExample.Criteria createCriteria = example.createCriteria();
                                createCriteria.andCodeCodeEqualTo(baseCode.getCodeType());
                                List<BaseCode> basecodeEx = baseCodeMapper.selectByExample(example);
                                delCodeType = basecodeEx.get(0).getCodeType();
                            } catch (Exception e) {
                                delCodeType = baseCode.getCodeType();
                            }
                            logger.info("删除的redis的key是：" + "baseCode_companyId" + companyId + "_branchId" + baseCode.getBranchId() + "_" + delCodeType);
                            redisManager.delete("baseCode_companyId" + companyId + "_branchId" + baseCode.getBranchId() + "_" + delCodeType);
                            //redisManager.delete("baseCode_companyId"+companyId+"_branchId"+baseCode.getBranchId()+"_"+baseCode.getCodeType());
                        }
                        msg.setInfo("成功删除栏目状态。");
                        msg.setResult(true);
                        logger.info("成功删除栏目状态！");
                    } else {
                        msg.setInfo("删除栏目状态失败。");
                        msg.setResult(false);
                        logger.info("删除栏目状态失败！");
                    }

                } else {
                    msg.setInfo("删除栏目状态失败。");
                    msg.setResult(false);
                    logger.info("删除栏目状态失败！");
                }
            }

        } catch (Exception e) {
            logger.info("删除栏目状态异常【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("删除栏目状态失败！");
            msg.setResult(false);
        }
        logger.info("删除栏目状态的业务方法结束。");
        return msg;
    }

    /**
     * 分页查询栏目信息
     */
    @Override
    public List<BaseCode> queryBaseCode(int startRow, int rows, String codeType, String codeCname, String orderNo) {
        logger.info("进入查询栏目信息的业务方法。");
        logger.info("查询栏目信息的参数startRow【{}】,rows【{}】,codeType【{}】,codeCname【{}】,orderNo【{}】", startRow, rows, codeType, codeCname, orderNo);
        List<BaseCode> codeList = null;
        String companyId = "1";//渠道id
        String branchId = "86";//分支公司
        try {
            codeList = baseCodeExMapper.selectAllWithLimit(companyId, branchId, codeType, codeCname, orderNo, startRow, rows);
            if (codeList == null || codeList.size() < 1) {
                logger.info("未查询到基础数据信息【{}】", JsonUtil.objectToJackson(codeList));
                return codeList;
            }
        } catch (Exception e) {
            logger.info("查询栏目信息的业务方法异常【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("查询栏目信息的业务方法结束。");
        return codeList;
    }

    /**
     * 查询当前条件下有多少数据
     */
    @Override
    public int countBaseCode(String codeType, String codeCname, String orderNo) {
        logger.info("进入查询栏目数量的业务方法。");
        logger.info("查询栏目数量的业务方法拿到参数codeType【{}】，codeCname【{}】，orderNo【{}】", codeType, codeCname, orderNo);
        String companyId = "1";
        BaseCodeExample example = new BaseCodeExample();
        BaseCodeExample.Criteria criteria = example.createCriteria();
//		criteria.andStateEqualTo("0");
        List<String> statelist = new ArrayList<String>();
        statelist.add("0");
        statelist.add("1");
        criteria.andStateIn(statelist);

        criteria.andCompanyIdEqualTo(companyId);
        if (StringUtils.isNotBlank(codeType)) {
            criteria.andCodeTypeEqualTo(codeType);
        }
        if (StringUtils.isNotBlank(codeCname)) {
            criteria.andCodeTypeLike(codeCname);
        }
        if (StringUtils.isNotBlank(orderNo)) {
            criteria.andOrderNoEqualTo(orderNo);
        }
        //example.setOrderByClause(" CODE_TYPE desc,CODE_CODE desc ");
        List<BaseCode> list = null;
        try {
            list = baseCodeMapper.selectByExample(example);
            if (list == null || list.size() < 1) {
                logger.info("未查询到栏目数量【{}】", JsonUtil.objectToJackson(list));
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        logger.info("查询栏目数量的业务方法结束。");
        return list.size();
    }

    @Override
    public List<BaseCode> queryColumn() {
        logger.info("进入查询栏目信息的方法");
        List<BaseCode> list = null;
        String companyId = "1";
        String branchId = "86";
        BaseCodeExample example = new BaseCodeExample();
        BaseCodeExample.Criteria criteria = example.createCriteria();
        criteria.andStateEqualTo("0");
        criteria.andCompanyIdEqualTo(companyId);
        if (!"86".equals(branchId)) {
            criteria.andBranchIdLike(branchId);
        }
        list = baseCodeMapper.selectByExample(example);
        logger.info("查询栏目信息结束，查询到的结果为【{}】" + JsonUtil.objectToJackson(list));
        logger.info("查询栏目信息的方法结束！");
        return list;
    }

    @Override
    public List<BaseCodeTree> columnTree(String codeCode) {
        logger.info("进入查询栏目树的方法");
        if (StringUtils.isBlank(codeCode)) {
            logger.info("查询栏目树请求参数不正确codeCode【{}】", codeCode);
            throw new ParameterException("创建失败，参数不足！");
        }
        List<BaseCodeTree> list = null;
        String companyId = "1";
        String branchId = "86";
        String state = "0";
        HashMap<String, String> parMap = new HashMap<String, String>();
        parMap.put("codeCode", codeCode);
        parMap.put("companyId", companyId);
        parMap.put("state", state);
        if (!"86".equals(branchId)) {
            parMap.put("branchId", branchId);
        }
        list = baseCodeExMapper.columnTree(parMap);
        logger.info("查询栏目树结束，查询到的结果为【{}】" + JsonUtil.objectToJackson(list));
        logger.info("查询栏目信息的方法结束！");
        return list;
    }

    @Override
    public Message operateColumn(String columnId, String operateType) {
        logger.info("进入操作栏目排序业务层方法，入参columnId:[{}],operateType:[{}]", columnId, operateType);
        Message msg = new Message();
        if (StringUtils.isEmpty(operateType) || StringUtils.isEmpty(columnId)) {
            msg.setInfo("缺少请求参数");
            msg.setResult(false);
            return msg;
        }
        BaseCode operateBaseCode = baseCodeMapper.selectByPrimaryKey(Integer.parseInt(columnId));
        BaseCodeExample example = new BaseCodeExample();
        example.createCriteria().andCodeTypeEqualTo(operateBaseCode.getCodeType());
        example.setOrderByClause("ORDER_NO*1 ASC,CREATE_TIME ASC");
        List<BaseCode> baseCodeList = baseCodeMapper.selectByExample(example);
        BaseCode preBaseCode = null;
        BaseCode nextBaseCode = null;
        BaseCode current = null;
        logger.info("需要重排序的栏目：" + JsonUtil.objectToJackson(baseCodeList));
        try {
            for (int i = 0; i < baseCodeList.size(); i++) {
                Integer orderNo = i + 1;
                baseCodeList.get(i).setOrderNo(orderNo.toString());
                baseCodeMapper.updateByPrimaryKey(baseCodeList.get(i));
                if (baseCodeList.get(i).getId().equals(Integer.parseInt(columnId))) {
                    preBaseCode = i - 1 == -1 ? baseCodeList.get(i) : baseCodeList.get(i - 1);
                    nextBaseCode = i + 1 == baseCodeList.size() ? baseCodeList.get(i) : baseCodeList.get(i + 1);
                    current = baseCodeList.get(i);
                    logger.info("查询到当前操作栏目的前一个栏目：【{}】，和后一个栏目信息：【{}】，当前操作栏目信息：【{}】",
                            preBaseCode.getId() + "--" + preBaseCode.getCodeCname(),
                            nextBaseCode.getId() + "--" + nextBaseCode.getCodeCname(),
                            current.getId() + "--" + current.getCodeCname());
                }
            }
            if ("86".equals(current.getBranchId())) {
                Set<byte[]> baseCodeKeys = redisManager.queryByVague("baseCode_companyId" + current.getCompanyId() + "_branchId" + "*");
                for (byte[] baseCodeKey : baseCodeKeys) {
                    redisManager.delete(baseCodeKey);
                }
            } else {
                String delCodeType = null;
                logger.info("查询到的codeType：" + current.getCodeType());
                try {
                    Integer.parseInt(current.getCodeType());//如果codeType能转成数字说明是三级栏目
                    BaseCodeExample example1 = new BaseCodeExample();
                    BaseCodeExample.Criteria createCriteria = example1.createCriteria();
                    createCriteria.andCodeCodeEqualTo(current.getCodeType());
                    List<BaseCode> basecodeEx = baseCodeMapper.selectByExample(example1);
                    delCodeType = basecodeEx.get(0).getCodeType();
                } catch (Exception e) {
                    delCodeType = current.getCodeType();
                }
                logger.info("删除的redis的key是：" + "baseCode_companyId" + current.getCompanyId() + "_branchId" + current.getBranchId() + "_" + delCodeType);
                redisManager.delete("baseCode_companyId" + current.getCompanyId() + "_branchId" + current.getBranchId() + "_" + delCodeType);
            }
            String modelOrder = null;
            current.setUpdateTime(DateUtil.getDate());
            current.setUpdateUser("");
            if ("1".equals(operateType)) {
                preBaseCode.setUpdateTime(DateUtil.getDate());
                preBaseCode.setUpdateUser("");
                modelOrder = preBaseCode.getOrderNo();
                preBaseCode.setOrderNo(current.getOrderNo());
                current.setOrderNo(modelOrder);
                baseCodeMapper.updateByPrimaryKey(preBaseCode);
                baseCodeMapper.updateByPrimaryKey(current);
                logger.info("上移操作成功");
                msg.setResult(true);
                msg.setInfo("上移成功！");
            } else {
                nextBaseCode.setUpdateTime(DateUtil.getDate());
                nextBaseCode.setUpdateUser("");
                modelOrder = nextBaseCode.getOrderNo();
                nextBaseCode.setOrderNo(current.getOrderNo());
                current.setOrderNo(modelOrder);
                baseCodeMapper.updateByPrimaryKey(nextBaseCode);
                baseCodeMapper.updateByPrimaryKey(current);
                logger.info("下移操作成功");
                msg.setResult(true);
                msg.setInfo("下移成功！");
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("操作栏目排序业务层方法报错");
            msg.setInfo("操作栏目排序业务层方法报错");
            msg.setResult(false);
        }
        logger.info("操作栏目排序业务层方法结束");
        return msg;
    }
}
