package com.ruoyi.content.service.impl;

import com.ruoyi.content.domain.CmsSysAuthority;
import com.ruoyi.content.domain.CmsSysAuthorityExample;
import com.ruoyi.content.domain.CmsSysRoleAuthorDto;
import com.ruoyi.content.domain.CmsSysUser;
import com.ruoyi.content.exception.ParameterException;
import com.ruoyi.content.mapper.CmsSysAuthorityExMapper;
import com.ruoyi.content.mapper.CmsSysAuthorityMapper;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.CmsRoleAuthorityService;
import com.ruoyi.content.service.CmsSysAuthorityService;
import com.ruoyi.content.utils.DateUtil;
import com.ruoyi.content.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 说明：菜单管理实现
 */
@Service
public class CmsSysAuthorityServiceImpl implements CmsSysAuthorityService {
    private static Logger logger = LoggerFactory.getLogger(CmsSysAuthorityServiceImpl.class);
    @Autowired
    private CmsSysAuthorityMapper cmsSysAuthorityMapper;
    @Autowired
    private CmsRoleAuthorityService authorityService;
    @Autowired
    private CmsSysAuthorityExMapper cmsSysAuthorityExMapper;

    /**
     * 新增菜单
     */
    @Override
    public Message addMenu(CmsSysAuthority authority) {
        logger.info("添加新菜单的业务层方法开始！");
        logger.info("添加新菜单的业务层方法拿到参数【{}】", JsonUtil.objectToJackson(authority));
        Message msg = new Message();
        if (authority == null || StringUtils.isBlank(authority.getId()) || StringUtils.isBlank(authority.getType())) {
            logger.info("新建菜单失败，参数缺少参数");
            msg.setInfo("创建菜单失败，缺少参数！");
            msg.setResult(false);
            return msg;
        }
        try {
            CmsSysAuthorityExample example = new CmsSysAuthorityExample();
            CmsSysAuthorityExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(authority.getId());
            List<CmsSysAuthority> authorList = cmsSysAuthorityMapper.selectByExample(example);
            if (authorList != null && authorList.size() > 0) {
                msg.setInfo("菜单已经存在，不能重复创建！");
                msg.setObject(authorList);
                msg.setResult(false);
                logger.info("菜单已经存在，不能重复创建！和菜单【{}】重复", authorList.get(0).getOperatorRoleName());
            } else {
                authority.setCreateUser("");
                if (cmsSysAuthorityMapper.insert(authority) > 0) {
                    msg.setInfo("创建菜单成功！");
                    msg.setResult(true);
                    logger.info("菜单创建成功！");
                } else {
                    msg.setInfo("创建菜单失败，请稍后再试！");
                    msg.setResult(false);
                    logger.info("菜单创建失败！");
                }
            }
        } catch (Exception e) {
            msg.setInfo("创建菜单失败，请稍后再试！");
            msg.setResult(false);
            e.printStackTrace();
            logger.info("创建菜单异常【{}】,菜单信息【{}】", e.getMessage(), JsonUtil.objectToJackson(authority));
        }
        logger.info("添加新菜单的业务层方法结束！");
        return msg;
    }

    /**
     * 更新菜单
     */
    @Override
    public Message upMenu(CmsSysAuthority authority) {
        logger.info("修改菜单的业务层方法开始！");
        logger.info("修改菜单的业务层方法拿到参数【{}】", JsonUtil.objectToJackson(authority));
        Message msg = new Message();
        if (authority == null || StringUtils.isBlank(authority.getId()) || StringUtils.isBlank(authority.getType())) {
            logger.info("更新菜单失败，参数缺少参数");
            msg.setInfo("更新菜单失败，缺少参数！");
            msg.setResult(false);
            return msg;
        }
        CmsSysUser roleInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        if (roleInfoDTO == null || StringUtils.isBlank(roleInfoDTO.getEmail())) {
            logger.info("您已离线，请重新登录！");
            msg.setInfo("您已离线，请重新登录！");
            msg.setResult(false);
            return msg;
        }
        try {
            //先查询出菜单，判断菜单是否存在
            CmsSysAuthorityExample example = new CmsSysAuthorityExample();
            CmsSysAuthorityExample.Criteria criteria = example.createCriteria();
            criteria.andIdEqualTo(authority.getId());
            List<CmsSysAuthority> list = cmsSysAuthorityMapper.selectByExample(example);
            if (list.size() > 0 && list != null) {
                CmsSysAuthority authorityNew = list.get(0);
                authorityNew.setId(authority.getId());
                authorityNew.setType(authority.getType());
                authorityNew.setLevel(authority.getLevel());
                authorityNew.setOperatorRoleName(authority.getOperatorRoleName());
                authorityNew.setOperatorRolePath(authority.getOperatorRolePath());
                authorityNew.setUpdateDate(DateUtil.currentDate());
                authorityNew.setUpdateTime(DateUtil.currentTime());
                authorityNew.setUpdateUser(roleInfoDTO.getEmail());
                if (cmsSysAuthorityMapper.updateByPrimaryKey(authorityNew) > 0) {
                    msg.setInfo("更新菜单成功！");
                    msg.setResult(true);
                    logger.info("修改菜单信息成功！");
                } else {
                    msg.setInfo("更新菜单失败，请稍后再试！");
                    msg.setResult(false);
                    logger.info("修改菜单信息失败！");
                }
            } else {
                logger.info("更新菜单失败，没有查询到对应菜单");
                msg.setInfo("更新菜单失败，没有查询到对应菜单！");
                msg.setResult(false);
                return msg;
            }
        } catch (Exception e) {
            msg.setInfo("修改菜单失败，请稍后再试！");
            msg.setResult(false);
            logger.info("更新菜单异常【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("修改菜单的业务层方法结束！");
        return msg;
    }

    /**
     * 删除菜单
     */
    @Override
    public Message delMenu(String nums) {
        logger.info("删除菜单的业务层方法开始！");
        logger.info("删除菜单的业务层方法拿到参数nums【{}】", nums);
        Message msg = new Message();
        if (StringUtils.isBlank(nums)) {
            logger.info("删除菜单失败，参数缺少参数");
            msg.setInfo("删除菜单失败，缺少参数！");
            msg.setResult(false);
            return msg;
        }
        try {
            String[] arrId = nums.split(",");
            for (String aId : arrId) {
                CmsSysAuthority authority = cmsSysAuthorityMapper.selectByPrimaryKey(Integer.parseInt(aId));
                if (authority != null) {
                    Message dataMsg = queryAuthoryById(authority.getId());
                    if (dataMsg.getResult()) {// 存在下级
                        logger.info("删除菜单失败，存在下级菜单");
                        msg.setInfo("删除菜单失败，请先删除下级菜单！");
                        msg.setResult(false);
                    } else {//不存在下级菜单， 删除自己
                        if (dataMsg.getObject().equals(0)) {
                            //删除菜单之前先删除对应关系
                            List<?> list = authorityService.queryRoleAuthority("", authority.getId());
                            if (list != null && list.size() > 0) {
                                logger.info("删除菜单和角色的对应关系！");
                                authorityService.delRoleAuthority("", authority.getId());
                            }
                            if (cmsSysAuthorityMapper.deleteByPrimaryKey(Integer.parseInt(aId)) > 0) {
                                logger.info("删除菜单成功！");
                                msg.setInfo("删除成功！");
                                msg.setResult(true);
                            } else {
                                logger.info("删除菜单失败！");
                                msg.setInfo("删除失败！");
                                msg.setResult(false);
                            }
                        } else {
                            logger.info("删除菜单失败，查询下级菜单参数不足。");
                            msg.setInfo("删除菜单失败，查询下级菜单参数不足！");
                            msg.setResult(false);
                        }
                    }
                } else {
                    msg.setInfo("删除菜单失败，该菜单不存在！");
                    msg.setResult(false);
                }
            }
        } catch (Exception e) {
            logger.info("删除菜单出错【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("删除菜单的业务层方法结束！");
        return msg;
    }

    /**
     * 查询下级菜单
     *
     * @return
     */
    @Override
    public Message queryAuthoryById(String id) {
        logger.info("查询下级菜单的业务层方法开始！");
        Message msg = new Message();
        if (StringUtils.isBlank(id)) {
            logger.info("根据id查询下级菜单失败，参数为空！");
            msg.setInfo("查询下级菜单失败，参数不足！");
            msg.setResult(false);
            return msg;
        }
        logger.info("查询下级菜单的业务层方法拿到参数【{}】", id);
        try {
            CmsSysAuthorityExample example = new CmsSysAuthorityExample();
            CmsSysAuthorityExample.Criteria criteria = example.createCriteria();
            criteria.andTypeEqualTo(id);
            List<CmsSysAuthority> authorList = cmsSysAuthorityMapper.selectByExample(example);
            if (authorList != null && authorList.size() > 0) {
                if (authorList.size() > 1) {
                    msg.setInfo("成功！");
                    msg.setObject(authorList);
                    msg.setResult(true);
                } else {
                    //判断id和type是否相等，也就是判断是不是一级菜单
                    if (authorList.get(0).getId().equals(authorList.get(0).getType())) {
                        msg.setInfo("未查询到下级菜单！");
                        msg.setObject(0);
                        msg.setResult(false);
                        logger.info("未查询到下级菜单！");
                    } else {
                        msg.setInfo("成功！");
                        msg.setObject(authorList);
                        msg.setResult(true);
                    }
                }
            } else {
                msg.setInfo("未查询到下级菜单！");
                msg.setObject(0);
                msg.setResult(false);
                logger.info("未查询到下级菜单！");
            }
        } catch (Exception e) {
            logger.info("根据id查询下级菜单异常【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("未查询到下级菜单！");
            msg.setResult(false);
        }
        logger.info("查询下级菜单的业务层方法结束！");
        return msg;
    }

    /**
     * 分页查询菜单
     */
    @Override
    public Message queryMenuList(int startRow, int rows, String id, String level, String type, String operatorRoleName) {
        logger.info("查询菜单列表信息的业务层方法开始！");
        logger.info("查询菜单列表信息的业务层方法拿到参数startRow【{}】,rows【{}】,id【{}】,level【{}】,type【{}】,operatorRoleName【{}】", startRow, rows, id, level, type, operatorRoleName);
        Message msg = new Message();
        if (StringUtils.isBlank(((Integer) startRow).toString()) || StringUtils.isBlank(((Integer) rows).toString())) {
            logger.info("查询菜单信息失败，缺少分页数据");
            throw new ParameterException("查询菜单信息失败，缺少分页数据！");
        }
        if (StringUtils.isBlank(id)) {
            id = "";
        }
        if (StringUtils.isBlank(level)) {
            level = "";
        }
        if (StringUtils.isBlank(type)) {
            type = "";
        }
        if (StringUtils.isBlank(operatorRoleName)) {
            operatorRoleName = "";
        }
        try {
            CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
            List<CmsSysAuthority> list = cmsSysAuthorityExMapper.selectAllMenuWithLimit(startRow, rows, userInfoDTO.getEmail(), id, level, type, operatorRoleName);
            if (list != null && list.size() > 0) {
                msg.setObject(list);
                msg.setInfo("查询成功！");
                msg.setResult(true);
            } else {
                logger.info("还没有任何菜单信息【{}】", JsonUtil.objectToJackson(list));
                msg.setInfo("还没有创建过菜单！");
                msg.setResult(false);
            }
        } catch (Exception e) {
            logger.info("查询菜单异常【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("系统异常，请稍候再试！");
            msg.setResult(false);
        }
        logger.info("查询菜单列表信息的业务层方法结束！");
        return msg;
    }

    /**
     * 查总数
     */
    @Override
    public List<CmsSysAuthority> authorityList(String id, String level, String type, String operatorRoleName) {
        logger.info("查询菜单总数的业务方法开始！");
        logger.info("查询菜单总数的业务层方法拿到参数,id【{}】,level【{}】,type【{}】,operatorRoleName【{}】", id, level, type, operatorRoleName);
        CmsSysAuthorityExample example = new CmsSysAuthorityExample();
        CmsSysAuthorityExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(id)) {
            criteria.andIdLike(id);
        }
        if (StringUtils.isNotBlank(level)) {
            criteria.andLevelLike(level);
        }
        if (StringUtils.isNotBlank(type)) {
            criteria.andTypeLike(type);
        }
        if (StringUtils.isNotBlank(operatorRoleName)) {
            criteria.andOperatorRoleNameLike(operatorRoleName);
        }
        List<CmsSysAuthority> authorList = null;
        try {
            authorList = new ArrayList<CmsSysAuthority>();
            authorList = cmsSysAuthorityMapper.selectByExample(example);
        } catch (Exception e) {
            logger.info("查询菜单数量出错【{}】", e.getMessage());
            e.printStackTrace();
        }
        return authorList;
    }

    @Override
    public Message queryRoleMenuList(int startRow, int rows, String id, String level, String type,
                                     String operatorRoleName, String role) {
        logger.info("查询菜单列表信息的业务层方法开始！");
        logger.info("查询菜单列表信息的业务层方法拿到参数startRow【{}】,rows【{}】,id【{}】,level【{}】,type【{}】,operatorRoleName【{}】", startRow,
                rows, id, level, type, operatorRoleName);
        Message msg = new Message();
        if (StringUtils.isBlank(((Integer) startRow).toString()) || StringUtils.isBlank(((Integer) rows).toString())) {
            logger.info("查询菜单信息失败，缺少分页数据");
            throw new ParameterException("查询菜单信息失败，缺少分页数据！");
        }
        if (StringUtils.isBlank(id)) {
            id = "";
        }
        if (StringUtils.isBlank(level)) {
            level = "";
        }
        if (StringUtils.isBlank(type)) {
            type = "";
        }
        if (StringUtils.isBlank(operatorRoleName)) {
            operatorRoleName = "";
        }
        try {
            CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
            List<CmsSysRoleAuthorDto> list = cmsSysAuthorityExMapper.selectAllRoleMenuWithLimit(startRow, rows,
                    userInfoDTO.getEmail(), id, level, type, operatorRoleName, role);
            if (list != null && list.size() > 0) {
                msg.setObject(list);
                msg.setInfo("查询成功！");
                msg.setResult(true);
            } else {
                logger.info("还没有任何菜单信息【{}】", JsonUtil.objectToJackson(list));
                msg.setInfo("还没有创建过菜单！");
                msg.setResult(false);
            }
        } catch (Exception e) {
            logger.info("查询菜单异常【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("系统异常，请稍候再试！");
            msg.setResult(false);
        }
        logger.info("查询菜单列表信息的业务层方法结束！");
        return msg;
    }
}
