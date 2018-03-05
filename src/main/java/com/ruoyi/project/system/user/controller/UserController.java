package com.ruoyi.project.system.user.controller;

import java.util.List;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.framework.aspectj.lang.annotation.Log;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.domain.JSON;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.system.role.domain.Role;
import com.ruoyi.project.system.role.service.IRoleService;
import com.ruoyi.project.system.user.domain.User;
import com.ruoyi.project.system.user.service.IUserService;

/**
 * 用户信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/user")
public class UserController extends BaseController
{

    private String prefix = "system/user";

    @Autowired
    private IUserService userService;

    @Autowired
    private IRoleService roleService;

    @RequiresPermissions("system:user:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/user";
    }

    @RequiresPermissions("system:user:view")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = userService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }

    /**
     * 修改
     */
    @Log(title = "系统管理", action = "用户管理-修改用户")
    @GetMapping("/edit/{userId}")
    public String edit(@PathVariable("userId") Long userId, Model model)
    {
        User user = userService.selectUserById(userId);
        List<Role> roles = roleService.selectRolesByUserId(userId);
        model.addAttribute("roles", roles);
        model.addAttribute("user", user);
        return prefix + "/edit";
    }

    @Log(title = "系统管理", action = "用户管理-删除用户")
    @RequestMapping("/remove/{userId}")
    @ResponseBody
    public JSON remove(@PathVariable("userId") Long userId)
    {
        User user = userService.selectUserById(userId);
        if (user == null)
        {
            return JSON.error("用户不存在");
        }
        if (userService.deleteUserById(userId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @Log(title = "系统管理", action = "用户管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = userService.batchDeleteUser(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }
    
    /**
     * 保存
     */
    @Log(title = "系统管理", action = "部门管理-保存部门")
    @RequiresPermissions("system:dept:save")
    @PostMapping("/save")
    @ResponseBody
    public JSON save(User user)
    {
        if (userService.saveUser(user) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

}