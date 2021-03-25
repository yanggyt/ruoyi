package com.ruoyi.content.service.impl;

import com.ruoyi.content.domain.AuthorityExDto;
import com.ruoyi.content.domain.CmsRoleAuthority;
import com.ruoyi.content.domain.CmsRoleAuthorityExample;
import com.ruoyi.content.domain.CmsSysUser;
import com.ruoyi.content.exception.ParameterException;
import com.ruoyi.content.mapper.AuthorityFindfExMapper;
import com.ruoyi.content.mapper.CmsRoleAuthorityMapper;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.CmsRoleAuthorityService;
import com.ruoyi.content.utils.GsonUtil;
import com.ruoyi.content.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CmsRoleAuthorityServiceImpl implements CmsRoleAuthorityService {
    private static Logger logger = LoggerFactory.getLogger(CmsRoleAuthorityService.class);
    @Autowired
    private AuthorityFindfExMapper authorityFindfExMapper;

    @Autowired
    private CmsRoleAuthorityMapper cmsRoleAuthorityMapper;

    @Override
    public List<?> queryUserRole() {
        logger.info("进入查詢当前用户所有权限集合的业务方法。");
        CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        String jsonData = "";
        try {
            String userLog = userInfoDTO.getEmail();
            List<AuthorityExDto> list = authorityFindfExMapper.findAuthorityEx(userLog);
            logger.info("查詢当前用户所有权限集合【{}】", JsonUtil.objectToJackson(list));
            StringBuffer jsons = new StringBuffer();
            if (list.size() > 0) {
                for (int i = 0; i < list.size(); i++) {
                    if (list.get(i).getLevel().equals("1") && i == 0) {
                        jsons.append("{id:\"" + list.get(i).getId() + "\", text: \"" + list.get(i).getOperatorRoleName()
                                + "\",pid:\"0\", url:\"\"}");

                    } else if (list.get(i).getLevel().equals("1") && i != 0) {
                        jsons.append(",{id:\"" + list.get(i).getId() + "\", text: \""
                                + list.get(i).getOperatorRoleName() + "\",pid:\"0\", url:\"\"}");

                    } else if (!list.get(i).getLevel().equals("1") && i != 0) {
                        jsons.append(",{id: \"" + list.get(i).getId() + "\",text:\" "
                                + list.get(i).getOperatorRoleName() + "\",pid:\"" + list.get(i).getType() + "\", url:\""
                                + list.get(i).getOperatorRolePath() + "\"}");
                    }
                }
            }
            String str = jsons.toString();
            logger.info("组装后的菜单集合【{}】", str);
            String indexStr = "";
            if (StringUtils.isNotBlank(jsons)) {
                indexStr = (String) jsons.subSequence(0, 1);
            }
            if (indexStr.equals(",")) {
                str = (String) jsons.subSequence(1, jsons.length());
            }
            jsonData = "[" + str + "]";
            return GsonUtil.jsonToList(jsonData);
        } catch (Exception e) {
            logger.info("获取菜单失败，请重新登录【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("查詢当前用户所有权限集合的业务方法结束。");
        return null;
    }

    /**
     * 添加对应关系
     */
    @Override
    public Message insertRoleAuthority(String selfChild, String parentChild) {
        logger.info("添加对应关系的业务层方法开始！");
        logger.info("添加对应关系的业务层方法拿到的参数selfChild【{}】，parentChild【{}】", selfChild, parentChild);
        Message msg = new Message();
        // 获取登录人信息
        CmsSysUser roleInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        String roleLog = roleInfoDTO.getEmail();
        if (StringUtils.isBlank(selfChild) || StringUtils.isBlank(parentChild)) {
            msg.setInfo("添加对应关系失败！");
            msg.setResult(false);
            logger.info("添加对应关系失败，缺少参数");
            return msg;
        }
        if (roleInfoDTO == null || StringUtils.isBlank(roleLog)) {
            logger.info("用户已离线！");
            msg.setInfo("您已离线，请重新登录！");
            msg.setResult(false);
            return msg;
        }
        try {
            // 添加对应关系之前先判断对应关系是否已经存在，不存在插入，存在则全部删除后再添加
            List<?> list = queryRoleAuthority(selfChild, "");
            if (list.size() > 0) {// 关系存在，删除
                CmsRoleAuthorityExample example = new CmsRoleAuthorityExample();
                CmsRoleAuthorityExample.Criteria criteria = example.createCriteria();
                criteria.andSelfChildEqualTo(selfChild);
                cmsRoleAuthorityMapper.deleteByExample(example);
            }

            // 循环插入对应关系
            String[] arrId = parentChild.split(",");
            for (String aId : arrId) {
                if (StringUtils.isNotBlank(aId)) {
                    CmsRoleAuthority record = new CmsRoleAuthority();
                    SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
                    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");// 设置时间格式
                    record.setSelfChild(selfChild);
                    record.setParentChild(aId);
                    record.setCreateDate(data.format(new Date()));
                    record.setCreateTime(time.format(new Date()));
                    record.setCreateUser(roleLog);
                    cmsRoleAuthorityMapper.insert(record);
                    // if (list.size() > 0) {// 关系存在，删除
                    // record = (CmsRoleAuthority) list.get(0);
                    // record.setUpdateDate(data.format(new Date()));
                    // record.setUpdateTime(time.format(new Date()));
                    // record.setUpdateUser(roleLog);
                    // CmsRoleAuthorityExample example = new
                    // CmsRoleAuthorityExample();
                    // Criteria criteria = example.createCriteria();
                    // criteria.andParentChildEqualTo(aId);
                    // criteria.andSelfChildEqualTo(selfChild);
                    // cmsRoleAuthorityMapper.deleteByExample(example);
                    // } else {// 不存在，创建关系
                    // record.setSelfChild(selfChild);
                    // record.setParentChild(aId);
                    // record.setCreateDate(data.format(new Date()));
                    // record.setCrateTime(time.format(new Date()));
                    // record.setCreateUser(roleLog);
                    // cmsRoleAuthorityMapper.insert(record);
                    // }
                    msg.setInfo("添加成功！");
                    msg.setResult(true);
                    logger.info("添加成功！");
                }
            }
        } catch (Exception e) {
            logger.info("对应关系创建失败【{}】", e.getMessage());
            msg.setInfo("对应关系创建失败！");
            msg.setResult(false);
            e.printStackTrace();
        }
        logger.info("添加对应关系的业务层方法结束！");
        return msg;
    }

    /**
     * 查询对应关系，添加或者删除对应关系之前先确定此关系是否存在
     */
    @Override
    public List<?> queryRoleAuthority(String selfChild, String parentChild) {
        logger.info("查询对应关系的业务层方法开始！");
        logger.info("查询对应关系的业务层方法拿到的参数selfChild【{}】，parentChild【{}】", selfChild, parentChild);
        if (StringUtils.isBlank(selfChild) && StringUtils.isBlank(parentChild)) {
            logger.info("查询对应关系失败，缺少参数");
            throw new ParameterException("查询对应关系失败，缺少参数");
        }
        List<CmsRoleAuthority> list = null;
        try {
            CmsRoleAuthorityExample example = new CmsRoleAuthorityExample();
            CmsRoleAuthorityExample.Criteria criteria = example.createCriteria();
            if (selfChild != "") {
                criteria.andSelfChildEqualTo(selfChild);
            }
            if (parentChild != "") {
                criteria.andParentChildEqualTo(parentChild);
            }
            list = cmsRoleAuthorityMapper.selectByExample(example);
        } catch (Exception e) {
            logger.info("查询对应关系失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("查询对应关系的业务层方法结束！");
        return list;
    }

    /**
     * 根据主键删除对应关系
     */
    @Override
    public Message delRoleAuthority(String selfChild, String parentChild) {
        logger.info("删除对应关系的业务层方法开始！");
        logger.info("删除对应关系的业务层方法拿到的参数selfChild【{}】，parentChild【{}】", selfChild, parentChild);
        Message msg = new Message();
        if (StringUtils.isBlank(selfChild) && StringUtils.isBlank(parentChild)) {
            msg.setInfo("删除失败！");
            msg.setResult(false);
            logger.info("删除对应关系失败，缺少参数");
            throw new ParameterException("删除对应关系失败，缺少参数");
        }
        try {
            CmsRoleAuthorityExample example = new CmsRoleAuthorityExample();
            CmsRoleAuthorityExample.Criteria criteria = example.createCriteria();
            if (selfChild != "") {
                criteria.andSelfChildEqualTo(selfChild);
            }
            if (parentChild != "") {
                criteria.andParentChildEqualTo(parentChild);
            }
            // 删除对应关系之前判断对应关系是否存在
            cmsRoleAuthorityMapper.deleteByExample(example);
            msg.setInfo("删除成功！");
            msg.setResult(true);
            logger.info("删除对应关系成功！");
        } catch (Exception e) {
            logger.info("删除对应关系失败【{}】", e.getMessage());
            msg.setInfo("删除对应关系失败！");
            msg.setResult(false);
            e.printStackTrace();
        }
        logger.info("删除对应关系的业务层方法结束！");
        return msg;
    }

}
