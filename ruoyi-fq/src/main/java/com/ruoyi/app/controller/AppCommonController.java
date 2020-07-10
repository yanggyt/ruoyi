package com.ruoyi.app.controller;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.exception.file.InvalidExtensionException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.framework.shiro.jwt.JWTUtil;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.shiro.service.SysRegisterService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * app通用类
 * 
 * @author mario
 * @date 2020-07-02
 */
@Controller
@RequestMapping("/app")
public class AppCommonController extends BaseController
{
    @Autowired
    private ISysUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @Autowired
    private ISysConfigService configService;

    /**
     * app登录
     */
    @PostMapping("/appLogin")
    @ResponseBody
    @CrossOrigin
    public AjaxResult appLogin(@RequestBody  SysUser sysUser)
    {
        SysUser realUser = userService.selectUserByLoginName(sysUser.getLoginName());

        if (realUser == null) {
            return AjaxResult.error("用户名错误");
        } else if (!passwordService.matches(realUser,sysUser.getPassword())) {
            return error("密码错误");
        }
        String tokenStr = JWTUtil.createToken(sysUser.getUserName());
        //JWTToken jwtToken = new JWTToken(token);
        //提交给realm进行登入，如果错误就会抛出异常并被捕获
        //Subject subject = SecurityUtils.getSubject();
        //subject.login(jwtToken);

        UsernamePasswordToken token = new UsernamePasswordToken(sysUser.getLoginName(), sysUser.getPassword(), false);
        Subject subject = SecurityUtils.getSubject();
        try
        {
            subject.login(token);
            return AjaxResult.success("登录成功",tokenStr);
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

    /**
     * 当前登录用户
     */
    @GetMapping("/appUser")
    @ResponseBody
    public AjaxResult appUser()
    {
        SysUser realUser = ShiroUtils.getSysUser();
        if(realUser == null)
            return AjaxResult.error("未登录");
        return AjaxResult.success(realUser);
    }

    /**
     * 当前登录用户
     */
    @GetMapping("/out")
    @ResponseBody
    public AjaxResult out()
    {
        ShiroUtils.logout();
        return AjaxResult.success("已登出");
    }

    /**
     * app 注册
     * @param user
     * @return
     */
    @PostMapping("/appRegister")
    @ResponseBody
    public AjaxResult ajaxRegister(@RequestBody SysUser user)
    {

        if (!("true".equals(configService.selectConfigByKey("sys.account.registerUser"))))
        {
            return error("当前系统没有开启注册功能！");
        }
        if (UserConstants.USER_NAME_NOT_UNIQUE.equals(userService.checkLoginNameUnique(user.getLoginName())))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，登录账号已存在");
        }
        else if (UserConstants.USER_PHONE_NOT_UNIQUE.equals(userService.checkPhoneUnique(user)))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，手机号码已存在");
        }
        else if (UserConstants.USER_EMAIL_NOT_UNIQUE.equals(userService.checkEmailUnique(user)))
        {
            return error("新增用户'" + user.getLoginName() + "'失败，邮箱账号已存在");
        }
        user.setSalt(ShiroUtils.randomSalt());
        user.setPassword(passwordService.encryptPassword(user.getLoginName(), user.getPassword(), user.getSalt()));
        user.setCreateBy("admin");
        return toAjax(userService.insertUser(user));
    }



    /**
     * 上传文件
     */
    @PostMapping("/upload")
    @ResponseBody
    public AjaxResult upload(@RequestParam("file") MultipartFile file)  throws IOException
    {
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(file);
        return AjaxResult.success("上传成功",fileName);
    }

    /**
     * 上传图片
     */
    @PostMapping("/imgUpload")
    @ResponseBody
    public AjaxResult imgUpload(@RequestParam("file") MultipartFile img) throws IOException, InvalidExtensionException {
        String[] allowedExtension = MimeTypeUtils.IMAGE_EXTENSION;
        // 上传并返回新文件名称
        try{
            String fileName = FileUploadUtils.upload(Global.getUploadPath(),img,allowedExtension);

            return AjaxResult.success("上传成功",fileName);
        }catch (InvalidExtensionException e){
            return AjaxResult.error("非图像文件");
        }

    }
}
