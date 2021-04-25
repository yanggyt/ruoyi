package com.ruoyi.content.service.impl;

import com.ruoyi.content.domain.*;
import com.ruoyi.content.exception.BusinessException;
import com.ruoyi.content.exception.ParameterException;
import com.ruoyi.content.mapper.CmsSysRoleExMapper;
import com.ruoyi.content.mapper.CmsSysRoleMapper;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.CmsRoleAuthorityService;
import com.ruoyi.content.service.CmsRoleService;
import com.ruoyi.content.service.CmsUserRoleService;
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
public class CmsRoleServiceImpl implements CmsRoleService {
    private static Logger logger = LoggerFactory.getLogger(CmsRoleService.class);

    @Autowired
    private CmsSysRoleMapper cmsSysRoleMapper;

    @Autowired
    private CmsUserRoleService cmsUserRoleService;

    @Autowired
    private CmsRoleAuthorityService cmsRoleAuthorityService;

    @Autowired
    private CmsSysRoleExMapper cmsSysRoleExMapper;

    /**
     * 分页查询所有角色信息
     */
    @Override
    public List<?> queryRole(int startRow, int rows, String name, String email) {
        logger.info("查询角色列表信息的业务层方法开始！");
        logger.info("查询角色列表信息的业务层拿到的参数startRow【{}】，rows【{}】，name【{}】", startRow, rows, name);
        if (StringUtils.isBlank(((Integer) startRow).toString()) || StringUtils.isBlank(((Integer) rows).toString())) {
            logger.info("查询角色信息失败，缺少分页数据");
            throw new ParameterException("查询角色信息失败，缺少分页数据！");
        }
        if (StringUtils.isBlank(name)) {
            name = "";
        }
        List<CmsUSerSysRoleDto> list = null;
        try {
            CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
            list = cmsSysRoleExMapper.selectuserRoleByPage(startRow, rows, userInfoDTO.getEmail(), name, email);
            if (list == null || list.size() < 1) {
                logger.info("还没有任何角色信息【{}】");
            }
        } catch (Exception e) {
            logger.info("查询角色信息失败，系统异常【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("查询角色列表信息的业务层方法结束！");
        return list;
    }

    /**
     * 添加角色
     */
    @Override
    public Message insertRole(CmsSysRole role) {
        logger.info("添加新角色的业务层方法开始！");
        logger.info("添加新角色的业务层方法拿到的参数role【{}】", JsonUtil.objectToJackson(role));
        Message msg = new Message();
        //获取登录人信息
        String roleLog = "o43yZt9cXHtRwvNF8ZdpGw9r0RGI";
        if (role == null) {
            msg.setInfo("添加角色信息失败，角色为空！");
            logger.info("添加角色信息失败，角色为空！");
            msg.setResult(false);
            return msg;
        }
        //必输项校验
        if (StringUtils.isBlank(role.getName())) {
            msg.setInfo("添加角色信息失败，参数不足！");
            logger.info("添加角色信息失败，参数不足！");
            msg.setResult(false);
            return msg;
        }
        try {
            //添加角色之前判断角色是否已经存在，不存在添加，存在不能添加
            CmsSysRoleExample example = new CmsSysRoleExample();
            CmsSysRoleExample.Criteria criteria = example.createCriteria();
            criteria.andNameEqualTo(role.getName());
            List<CmsSysRole> list = cmsSysRoleMapper.selectByExample(example);
            if (list.size() > 0) {
                msg.setInfo("角色已经存在，不能重复创建！");
                msg.setResult(false);
                logger.info("角色【{}】已经存在，不能重复创建！", JsonUtil.objectToJackson(list.get(0)));
                return msg;
            }
            SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");//设置时间格式
            role.setCreateDate(data.format(new Date()));
            role.setCreateTime(time.format(new Date()));
            role.setCreateUser(roleLog);
            if (cmsSysRoleMapper.insert(role) > 0) {
                msg.setInfo("角色创建成功！");
                msg.setResult(true);
                logger.info("角色创建成功！");
            }
        } catch (Exception e) {
            logger.info("角色创建失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("角色创建失败！");
            msg.setResult(false);
        }
        logger.info("添加新角色的业务层方法结束！");
        return msg;
    }

    /**
     * 删除角色
     */
    @Override
    public Message delRole(String id) {
        logger.info("删除角色的业务层方法开始！");
        logger.info("删除角色的业务层方拿到的参数id【{}】", id);
        Message msg = new Message();
        if (StringUtils.isBlank(id)) {
            msg.setInfo("删除角色信息失败，缺少参数！");
            msg.setResult(false);
            logger.info("删除角色信息失败，缺少参数");
            throw new ParameterException("删除角色信息失败，缺少参数");
        }
        try {
            String[] arrId = id.split(",");
            for (String aId : arrId) {
                if (StringUtils.isNotBlank(aId)) {
                    //删除角色之前判断角色是否存在
                    CmsSysRole sysRole = cmsSysRoleMapper.selectByPrimaryKey(Integer.parseInt(aId));
                    if (sysRole != null) {
                        //删除角色之前，如果角色已经分配了用户，则将对应的角色与用户的对应关系删除
                        List<?> list = cmsUserRoleService.queryUserRole(sysRole.getName(), "");
                        if (list.size() > 0) {
                            for (int i = 0; i < list.size(); i++) {
                                CmsUserRole cmsUserRole = (CmsUserRole) list.get(i);
                                cmsUserRoleService.delUserRole(Integer.toString(cmsUserRole.getId()));
                            }
                        }
                        //删除角色之前，如果角色已经分配了权限，则将对应的角色与权限的对应关系删除
                        List<?> list2 = cmsRoleAuthorityService.queryRoleAuthority(sysRole.getName(), "");
                        if (list2.size() > 0) {
                            cmsRoleAuthorityService.delRoleAuthority(sysRole.getName(), "");
                        }
                        cmsSysRoleMapper.deleteByPrimaryKey(Integer.parseInt(aId));
                        msg.setInfo("删除成功！");
                        msg.setResult(true);
                        logger.info("删除信息成功！");
                    } else {
                        msg.setInfo("删除失败，角色不存在！");
                        msg.setResult(false);
                        logger.info("删除失败，角色不存在！");
                        return msg;
                    }
                }
            }
        } catch (Exception e) {
            logger.info("删除信息失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("删除失败！");
            msg.setResult(false);
        }
        logger.info("删除角色的业务层方法结束！");
        return msg;
    }

    /**
     * 修改角色信息
     */
    @Override
    public Message updateRole(CmsSysRole role) {
        logger.info("修改角色的业务层方法开始！");
        logger.info("修改角色的业务层方法拿到参数role【{}】", JsonUtil.objectToJackson(role));
        Message msg = new Message();
        //获取登录人信息
        CmsSysUser roleInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        String roleLog = roleInfoDTO.getEmail();
        if (role == null) {
            logger.info("修改角色信息失败，角色为空！");
            msg.setInfo("修改角色信息失败，角色为空！");
            msg.setResult(false);
            return msg;
        }
        if (roleInfoDTO == null || StringUtils.isBlank(roleLog)) {
            logger.info("用户已离线！");
            msg.setInfo("您已离线，请重新登录！");
            msg.setResult(false);
            return msg;
        }
        //必输项校验
        if (StringUtils.isBlank(role.getName())) {
            logger.info("修改角色信息失败，参数不足！");
            msg.setInfo("修改角色信息失败，参数不足！");
            msg.setResult(false);
            return msg;
        }
        try {
            SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");//设置时间格式
            role.setUpdateDate(data.format(new Date()));
            role.setUpdateTime(time.format(new Date()));
            role.setUpdateUser(roleLog);
            if (cmsSysRoleMapper.updateByPrimaryKey(role) > 0) {
                msg.setInfo("角色信息修改成功！");
                msg.setResult(true);
                logger.info("修改角色信息成功！");
            }
        } catch (Exception e) {
            logger.info("修改角色信息失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("角色信息修改失败！");
            msg.setResult(false);
        }
        logger.info("修改角色的业务层方法结束！");
        return msg;
    }

    /**
     * 根据主键查询角色，修改角色信息使用
     */
    @Override
    public CmsSysRole queryRoleById(String id) {
        logger.info("根据角色id查询角色信息的业务层方法开始！");
        logger.info("查询角色信息的业务层方法拿到参数id【{}】", id);
        if (StringUtils.isBlank(id)) {
            logger.info("查询角色信息失败，缺少参数");
            throw new ParameterException("未查询到角色信息");
        }
        CmsSysRole role = null;
        try {
            role = cmsSysRoleMapper.selectByPrimaryKey(Integer.parseInt(id));
            if (role == null) {
                logger.info("未查询到相关角色【{}】", JsonUtil.objectToJackson(role));
                throw new BusinessException("未查询到相关角色！");
            }
            logger.info("根据角id查询角色信息成功【{}】", JsonUtil.objectToJackson(role));
        } catch (Exception e) {
            logger.info("根据角色id查询角色信息失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("根据角色id查询角色信息的业务层方法结束！");
        return role;
    }

    /**
     * 统计角色数量，分页使用
     */
    @Override
    public int countRole(String name) {
        logger.info("查询角色列表信息数量的业务层方法开始！");
        logger.info("查询角色列表信息数量的业务层方法拿到参数name【{}】", name);
        CmsSysRoleExample example = new CmsSysRoleExample();
        CmsSysRoleExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(name);
        }
        int count = 0;
        try {
            count = cmsSysRoleMapper.countByExample(example);
            if (count == 0) {
                logger.info("还没有创建任何角色【{}】");
                throw new BusinessException("还没创建角色！");
            }
            logger.info("查询到的角色数量count【{}】", count);
        } catch (Exception e) {
            logger.info("查询角色数量出错【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("查询角色列表信息数量的业务层方法结束！");
        return count;
    }


}
