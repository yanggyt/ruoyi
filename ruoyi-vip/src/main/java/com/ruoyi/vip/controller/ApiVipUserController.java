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
@RequestMapping("/api/v1/vip/user")
public class ApiVipUserController extends BaseController {

    @Autowired
    private IVipUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @ApiOperation("用户登陆")
    @Log(title = "用户登陆", businessType = BusinessType.EXPORT)
    @RequestMapping(value = "/login", method = RequestMethod.POST,produces= "application/json;charset=UTF-8")
    public AjaxResult login(@RequestBody SysUser user) {
        AjaxResult success = success("登陆成功");
        boolean rememberMe=false;
        UsernamePasswordToken token = new UsernamePasswordToken(user.getUserName(), user.getPassword(), rememberMe);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            String tokenSign=JwtUtil.sign(user.getUserName(),user.getUserName());
            JSONObject json = new JSONObject();

            json.put("token",tokenSign);
            success.put("data",json);
            return success;
        }
        catch (AuthenticationException e)
        {
            String msg = "用户或密码错误";
            if (StringUtils.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return error(msg);
        }
    }
    @GetMapping("/info")
    public AjaxResult get() {
        AjaxResult success = success("登陆成功");
        VipUser vipUser = userService.selectUserByLoginName(JwtUtil.getLoginName());
        success.put("user", vipUser);
        SysUser vipUser2 =ShiroUtils.getSysUser();
        return success;
    }
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(VipUser user) {

        List<VipUser> list = userService.selectUserList( user );
        return getDataTable( list );
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(VipUser user) {
        List<VipUser> list = userService.selectUserList( user );
        ExcelUtil<VipUser> util = new ExcelUtil<VipUser>( VipUser.class );
        return util.exportExcel( list, "user" );
    }


    /**
     * 新增保存用户
     */
    @Log(title = "用户管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult addSave(VipUser user) {
        user.setSalt( ShiroUtils.randomSalt() );
        user.setPassword( passwordService.encryptPassword( user.getLoginName(), user.getPassword(), user.getSalt() ) );
        user.setCreateBy( ShiroUtils.getLoginName() );
        return toAjax( userService.insertUser( user ) );
    }



    /**
     * 修改保存用户
     */
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult editSave(VipUser user) {

        user.setUpdateBy( ShiroUtils.getLoginName() );
        return toAjax( userService.updateUser( user ) );
    }

    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @PostMapping("/resetPwd")
    @ResponseBody
    public AjaxResult resetPwdSave(VipUser user) {
        user.setSalt( ShiroUtils.randomSalt() );
        user.setPassword( passwordService.encryptPassword( user.getLoginName(), user.getPassword(), user.getSalt() ) );
        return toAjax( userService.resetUserPwd( user ) );
    }

    @RequiresPermissions("vip:user:remove")
    @Log(title = "用户管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        try {
            return toAjax( userService.deleteUserByIds( ids ) );
        } catch (Exception e) {
            return error( e.getMessage() );
        }
    }

    /**
     * 校验用户名
     */
    @PostMapping("/checkLoginNameUnique")
    @ResponseBody
    public String checkLoginNameUnique(VipUser user) {
        return userService.checkLoginNameUnique( user.getLoginName() );
    }

    /**
     * 校验手机号码
     */
    @PostMapping("/checkPhoneUnique")
    @ResponseBody
    public String checkPhoneUnique(VipUser user) {
        return userService.checkPhoneUnique( user );
    }

    /**
     * 校验email邮箱
     */
    @PostMapping("/checkEmailUnique")
    @ResponseBody
    public String checkEmailUnique(VipUser user) {
        return userService.checkEmailUnique( user );
    }
}