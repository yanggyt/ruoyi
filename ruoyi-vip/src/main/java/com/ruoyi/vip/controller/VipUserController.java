package com.ruoyi.vip.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.shiro.service.SysPasswordService;
import com.ruoyi.framework.web.base.BaseController;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.framework.web.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.vip.domain.VipUser;
import com.ruoyi.vip.service.IVipUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用户信息
 *
 * @author ruoyi
 */
@Controller
@RequestMapping("/vip/user")
public class VipUserController extends BaseController {
    private String prefix = "vip/user";

    @Autowired
    private IVipUserService userService;

    @Autowired
    private SysPasswordService passwordService;

    @RequiresPermissions("vip:user:view")
    @GetMapping()
    public String user() {
        return prefix + "/user";
    }

    @RequiresPermissions("vip:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(VipUser user) {

        List<VipUser> list = userService.selectUserList( user );
        return getDataTable( list );
    }

    @Log(title = "用户管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("vip:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(VipUser user) {
        List<VipUser> list = userService.selectUserList( user );
        ExcelUtil<VipUser> util = new ExcelUtil<VipUser>( VipUser.class );
        return util.exportExcel( list, "user" );
    }

    /**
     * 新增用户
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        return prefix + "/add";
    }

    /**
     * 新增保存用户
     */
    @RequiresPermissions("vip:user:add")
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
     * 修改用户
     */
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put( "user", userService.selectUserById( userId ) );
        return prefix + "/edit";
    }

    /**
     * 修改保存用户
     */
    @RequiresPermissions("vip:user:edit")
    @Log(title = "用户管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @Transactional(rollbackFor = Exception.class)
    @ResponseBody
    public AjaxResult editSave(VipUser user) {

        user.setUpdateBy( ShiroUtils.getLoginName() );
        return toAjax( userService.updateUser( user ) );
    }

    @RequiresPermissions("vip:user:resetPwd")
    @Log(title = "重置密码", businessType = BusinessType.UPDATE)
    @GetMapping("/resetPwd/{userId}")
    public String resetPwd(@PathVariable("userId") Long userId, ModelMap mmap) {
        mmap.put( "user", userService.selectUserById( userId ) );
        return prefix + "/resetPwd";
    }

    @RequiresPermissions("vip:user:resetPwd")
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