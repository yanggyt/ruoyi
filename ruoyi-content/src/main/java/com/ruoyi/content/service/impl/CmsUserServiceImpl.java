package com.ruoyi.content.service.impl;

import com.ruoyi.content.domain.CmsSysUser;
import com.ruoyi.content.domain.CmsSysUserExample;
import com.ruoyi.content.domain.CmsUserRole;
import com.ruoyi.content.exception.BusinessException;
import com.ruoyi.content.exception.ParameterException;
import com.ruoyi.content.mapper.CmsSysUserExMapper;
import com.ruoyi.content.mapper.CmsSysUserMapper;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.CmsUserRoleService;
import com.ruoyi.content.service.CmsUserService;
import com.ruoyi.content.utils.JsonUtil;
import com.ruoyi.content.utils.PasswordHelper;
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
public class CmsUserServiceImpl implements CmsUserService {
    private static Logger logger = LoggerFactory.getLogger(CmsUserService.class);

    @Autowired
    private CmsSysUserMapper cmsSysUserMapper;

    @Autowired
    private CmsUserRoleService cmsUserRoleService;

    @Autowired
    private CmsSysUserExMapper cmsSysUserExMapper;
    @Autowired
    private PasswordHelper passwordHelper;

    /**
     * 分页查询所有用户信息
     */
    @Override
    public List<?> queryUser(int startRow, int rows, String email, String phone, String name, String companyId) {
        logger.info("查询用户列表信息的业务层方法开始！");
        logger.info("查询用户列表信息的业务层方法拿到参数startRow【{}】，rows【{}】，email【{}】，phone【{}】，name【{}】，companyId【{}】", startRow, rows, email, phone, name, companyId);
        if (StringUtils.isBlank(((Integer) startRow).toString()) || StringUtils.isBlank(((Integer) rows).toString())) {
            logger.info("查询用户信息失败，缺少分页数据");
            throw new ParameterException("查询用户信息失败，缺少分页数据！");
        }
        if (StringUtils.isBlank(email)) {
            email = "";
        }
        if (StringUtils.isBlank(phone)) {
            phone = "";
        }
        if (StringUtils.isBlank(name)) {
            name = "";
        }
        if (StringUtils.isBlank(companyId)) {
            companyId = "";
        }
        List<CmsSysUser> list = null;
        try {
//			list = cmsSysUserExMapper.selectByPage(startRow, rows,userInfoDTO.getEmail(),emial,phone,name,companyId);
            list = cmsSysUserExMapper.selectByPage(startRow, rows, email, phone, name, companyId);
            if (list == null || list.size() < 1) {
                logger.info("还没有任何用户信息【{}】", JsonUtil.objectToJackson(list));
            }
        } catch (Exception e) {
            logger.info("查询用户信息失败，系统异常【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("查询用户列表信息的业务层方法结束！list[{}]", JsonUtil.objectToJackson(list));
        return list;
    }

    /**
     * 添加用户
     */
    @Override
    public Message insertUser(CmsSysUser user) {
        logger.info("添加新用户的业务层方法开始！");
        logger.info("添加新用户的业务层方法拿到参数user【{}】", JsonUtil.objectToJackson(user));
        Message msg = new Message();
        //获取登录人信息
        CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        String userLog = userInfoDTO.getEmail();
        if (user == null) {
            logger.info("添加用户【{}】信息失败。", JsonUtil.objectToJackson(user));
            msg.setInfo("用户创建失败,用户信息为空！");
            msg.setResult(false);
            return msg;
        }
        if (userInfoDTO == null || StringUtils.isBlank(userLog)) {
            logger.info("用户已离线！");
            msg.setInfo("您已离线，请重新登录！");
            msg.setResult(false);
            return msg;
        }
        //必输项校验
        if (StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getBranchId()) || StringUtils.isBlank(user.getPhone()) || StringUtils.isBlank(user.getOpenId())) {
            logger.info("添加用户信息失败，参数不足！");
            msg.setInfo("用户创建失败,，参数不足！");
            msg.setResult(false);
            return msg;
        }

        try {
            //新建之前判断用户是否已经存在，已经存在则不能添加
            CmsSysUser sysUser = cmsSysUserMapper.selectByPrimaryKey(user.getEmail());
            if (sysUser != null) {
                logger.info("用户【{}】已经存在，不能重复创建！！", JsonUtil.objectToJackson(sysUser));
                msg.setInfo("用户已经存在，不能重复创建！");
                msg.setResult(false);
                return msg;
            }
            SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");//设置时间格式
            user.setCreateDate(data.format(new Date()));
            user.setCreateTime(time.format(new Date()));
            user.setCreateUser(userLog);
            passwordHelper.encryptPassword(user);
            if (cmsSysUserMapper.insert(user) > 0) {
                msg.setInfo("用户创建成功！");
                msg.setResult(true);
                logger.info("用户创建成功！");
            } else {
                msg.setInfo("用户创建失败！");
                msg.setResult(false);
                logger.info("用户创建失败！");
            }
        } catch (Exception e) {
            logger.info("用户创建失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("用户创建失败！");
            msg.setResult(false);
        }
        logger.info("添加新用户的业务层方法结束！");
        return msg;
    }

    /**
     * 删除用户
     */
    @Override
    public Message delUser(String email) {
        logger.info("删除用户的业务层方法开始！");
        logger.info("删除用户的业务层方法拿到参数email【{}】", email);
        Message msg = new Message();
        if (StringUtils.isBlank(email)) {
            msg.setInfo("删除失败，缺少参数！");
            msg.setResult(false);
            logger.info("删除用户信息失败，缺少参数");
            return msg;
        }
        try {
            String[] arrId = email.split(",");
            for (String aId : arrId) {
                if (StringUtils.isNotBlank(aId)) {
                    //删除用户之前，如果用户已经分配了角色，则将对应的用户与角色的对应关系删除
                    CmsSysUser sysUser = cmsSysUserMapper.selectByPrimaryKey(aId);
                    if (sysUser != null) {
                        List<?> list = cmsUserRoleService.queryUserRole("", aId);
                        if (list.size() > 0) {
                            for (int i = 0; i < list.size(); i++) {
                                CmsUserRole cmsUserRole = (CmsUserRole) list.get(i);
                                cmsUserRoleService.delUserRole(Integer.toString(cmsUserRole.getId()));
                            }
                        }
                        if (cmsSysUserMapper.deleteByPrimaryKey(aId) > 0) {
                            msg.setInfo("删除成功！");
                            msg.setResult(true);
                            logger.info("删除信息成功！");
                        }
                    } else {
                        msg.setInfo("删除失败，用户不存在！");
                        msg.setResult(false);
                        logger.info("删除失败，用户【{}】不存在！", JsonUtil.objectToJackson(sysUser));
                    }
                }
            }
        } catch (Exception e) {
            logger.info("删除信息失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("删除失败！");
            msg.setResult(false);
        }
        logger.info("删除用户的业务层方法结束！");
        return msg;
    }

    /**
     * 修改用户信息
     */
    @Override
    public Message updateUser(CmsSysUser user) {
        logger.info("修改用户的业务层方法开始！");
        logger.info("修改用户的业务层方法拿到参数user【{}】", JsonUtil.objectToJackson(user));
        Message msg = new Message();
        //获取登录人信息
        CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        String userLog = userInfoDTO.getEmail();
        if (user == null) {
            logger.info("修改用户信息失败，用户【{}】为空！", JsonUtil.objectToJackson(user));
            msg.setInfo("修改用户信息失败，用户为空！");
            msg.setResult(false);
            return msg;
        }
        if (userInfoDTO == null || StringUtils.isBlank(userLog)) {
            logger.info("用户已离线！");
            msg.setInfo("您已离线，请重新登录！");
            msg.setResult(false);
            return msg;
        }
        //必输项校验
        if (StringUtils.isBlank(user.getEmail()) || StringUtils.isBlank(user.getState())) {
            logger.info("修改用户信息失败，参数不足！");
            msg.setInfo("修改用户信息失败，参数不足！");
            msg.setResult(false);
            return msg;
        }
        try {
            SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");//设置时间格式
            user.setUpdateDate(data.format(new Date()));
            user.setUpdateTime(time.format(new Date()));
            user.setUpdateUser(userLog);
            //passwordHelper.encryptPassword(user);
            if (cmsSysUserMapper.updateByPrimaryKey(user) > 0) {
                msg.setInfo("用户信息修改成功！");
                msg.setResult(true);
                logger.info("修改用户信息成功！");
            }
        } catch (Exception e) {
            logger.info("修改用户信息失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("用户信息修改失败！");
            msg.setResult(false);
        }
        logger.info("修改用户的业务层方法结束！");
        return msg;
    }

    /**
     * 根据主键查询用户，修改用户信息使用
     */
    @Override
    public CmsSysUser queryUserByEmail(String email) {
        logger.info("根据用户邮箱查询用户信息的业务层方法开始！");
        logger.info("根据用户邮箱查询用户信息的业务层方法拿到参数email【{}】", email);
        if (StringUtils.isBlank(email)) {
            logger.info("查询用户信息失败，缺少参数。");
            throw new ParameterException("未查询到用户信息");
        }
        CmsSysUser user = null;
        try {
            user = cmsSysUserMapper.selectByPrimaryKey(email);
            if (user == null) {
                logger.info("根据emaill【{}】未查询到相关用户【{}】", email, JsonUtil.objectToJackson(user));
                throw new BusinessException("未查询到相关用户！");
            }
            logger.info("根据用户邮箱【{}】查询用户信息成功【{}】", email, JsonUtil.objectToJackson(user));
        } catch (Exception e) {
            logger.info("根据用户邮箱查询用户信息的业务层方法异常【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("根据用户邮箱查询用户信息的业务层方法结束！");
        return user;
    }

    /**
     * 统计用户数量，分页使用
     */
    @Override
    public int countUser(String email, String phone, String name, String companyId) {
        logger.info("查询用户列表信息数量的业务层方法开始！");
        logger.info("查询用户列表信息数量的业务层方法拿到参数email【{}】,phone【{}】,name【{}】,companyId【{}】", email, phone, name, companyId);
        CmsSysUserExample example = new CmsSysUserExample();
        CmsSysUserExample.Criteria criteria = example.createCriteria();
        if (StringUtils.isNotBlank(email)) {
            criteria.andEmailLike(email);
        }
        if (StringUtils.isNotBlank(phone)) {
            criteria.andPhoneLike(phone);
        }
        if (StringUtils.isNotBlank(name)) {
            criteria.andNameLike(name);
        }
        if (StringUtils.isNotBlank(companyId)) {
            criteria.andCompanyIdEqualTo(companyId);
        }
        int count = 0;
        try {
            count = cmsSysUserMapper.countByExample(example);
            if (count == 0) {
                logger.info("未查询到用户【{}】", count);
            }
            logger.info("查询到的用户数量【{}】", count);
        } catch (Exception e) {
            logger.info("查询用户数量出错【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("查询用户列表信息数量的业务层方法结束！");
        return count;
    }


    /**
     * 修改当前登录人的密码
     */
    @Override
    public Message updataPassword(String newPass) {
        logger.info("修改用户密码的业务层方法开始！");
        logger.info("修改用户密码的业务层方法拿到参数【{}】", newPass);
        Message msg = new Message();
        //获取登录人信息
        CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        String userLog = userInfoDTO.getEmail();
        if (userInfoDTO == null || StringUtils.isBlank(userLog)) {
            logger.info("用户已离线！");
            msg.setInfo("您已离线，请重新登录！");
            msg.setResult(false);
            return msg;
        }
        //必输项校验
        if (StringUtils.isBlank(newPass)) {
            logger.info("修改用户密码失败，参数【{}】不足！", newPass);
            msg.setInfo("修改用户密码失败，参数不足！");
            msg.setResult(false);
            return msg;
        }
        try {
            userInfoDTO.setPwd(newPass);
            passwordHelper.encryptPassword(userInfoDTO);
            SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");//设置日期格式
            SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");//设置时间格式
            userInfoDTO.setUpdateDate(data.format(new Date()));
            userInfoDTO.setUpdateTime(time.format(new Date()));
            userInfoDTO.setUpdateUser(userLog);
            if (cmsSysUserMapper.updateByPrimaryKey(userInfoDTO) > 0) {
                msg.setInfo("用户密码修改成功！");
                msg.setResult(true);
                logger.info("修改用户密码成功！");
            }
        } catch (Exception e) {
            logger.info("修改用户密码失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("用户密码修改失败！");
            msg.setResult(false);
        }
        logger.info("修改用户密码的业务层方法结束！");
        return msg;
    }

    /**
     * 判断原始密码是否正确
     *
     * @param oldPass
     * @return
     */
    @Override
    public Message checkOldPass(String oldPass) {
        logger.info("判断原始密码是否正确的业务层方法开始！");
        logger.info("判断原始密码是否正确的业务层方法拿到的原始密码【{}】", oldPass);
        Message msg = new Message();
        //获取登录人信息
        CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        String userLog = userInfoDTO.getEmail();
        if (userInfoDTO == null || StringUtils.isBlank(userLog)) {
            logger.info("用户已离线！");
            msg.setInfo("您已离线，请重新登录！");
            msg.setResult(false);
            return msg;
        }
        //必输项校验
        if (StringUtils.isBlank(oldPass)) {
            logger.info("判断失败，原始密码为【{}】。", oldPass);
            msg.setInfo("判断失败，缺少原始密码。");
            msg.setResult(false);
            return msg;
        }
        CmsSysUser user = new CmsSysUser();
        user.setPwd(oldPass);
        user.setEmail(userLog);
        user.setSalt(userInfoDTO.getSalt());
        passwordHelper.encryptCkeckPassword(user);
        if (userInfoDTO.getPwd().equals(user.getPwd())) {
            msg.setInfo("原始密码正确！");
            msg.setResult(true);
        } else {
            msg.setInfo("输入的原始密码不正确！");
            msg.setResult(false);
        }
        logger.info("判断原始密码是否正确的业务层方法结束！");
        return msg;
    }

}
