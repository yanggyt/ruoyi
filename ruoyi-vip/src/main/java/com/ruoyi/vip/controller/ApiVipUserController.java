package com.ruoyi.vip.controller;

import com.auth0.jwt.JWTVerifier;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.json.JSONObject;
import com.ruoyi.common.utils.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.jwt.JwtUtil;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.util.ServletUtils;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.vip.domain.VipUser;
import com.ruoyi.vip.service.IVipUserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Api("用户信息管理")
@RestController
@RequestMapping("/api/v1")
public class ApiVipUserController extends BaseController {


    @Autowired
    private ISysUserService sysUserService;
    @Autowired
    private SysPasswordService passwordService;

    @ApiOperation("用户登陆")
    @Log(title = "用户登陆", businessType = BusinessType.EXPORT)
    @RequestMapping(value = "/user/login", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    public AjaxResult login(@RequestBody SysUser user) {
        AjaxResult success = success( "登陆成功" );
        boolean rememberMe = false;
        UsernamePasswordToken token = new UsernamePasswordToken( user.getUserName(), user.getPassword(), rememberMe );
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login( token );
            String tokenSign = JwtUtil.sign( user.getUserName(), user.getPassword() );
            JSONObject json = new JSONObject();

            json.put( "token", tokenSign );
            SysUser sysUser = sysUserService.selectUserByLoginName( user.getUserName());
            json.put( "user", sysUser );
            success.put( "data", json );
            return success;
        } catch (AuthenticationException e) {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty( e.getMessage() )) {
                msg = e.getMessage();
            }
            return error( msg );
        }
    }

    @GetMapping("/member/user/info")
    public AjaxResult get() {
        AjaxResult success = success( "获取用户信息成功" );
        SysUser sysUser = sysUserService.selectUserByLoginName( JwtUtil.getLoginName() );
        success.put( "data", sysUser );
        return success;
    }


    /**
     * 新增保存用户
     */
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/user/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult addSave(@RequestBody SysUser user) {
        user.setSalt( ShiroUtils.randomSalt() );
        user.setPassword( passwordService.encryptPassword( user.getLoginName(), user.getPassword(), user.getSalt() ) );
        return toAjax( sysUserService.insertUser( user ) );
    }


    /**
     * 修改保存用户
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/member/user/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult editSave(SysUser user) {

        user.setUpdateBy( ShiroUtils.getLoginName() );
        return toAjax( sysUserService.updateUser( user ) );
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/member/user/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(SysUser user) {
        user.setSalt( ShiroUtils.randomSalt() );
        user.setPassword( passwordService.encryptPassword( user.getLoginName(), user.getPassword(), user.getSalt() ) );
        return toAjax( sysUserService.resetUserPwd( user ) );
    }

    /**
     * 校验用户名
     */
    @PostMapping("/member/user//checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(SysUser user) {
        return sysUserService.checkLoginNameUnique( user.getLoginName() );
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/member/user//checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(SysUser user) {
        return sysUserService.checkPhoneUnique( user );
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/member/user//checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(SysUser user) {
        return sysUserService.checkEmailUnique( user );
    }
}