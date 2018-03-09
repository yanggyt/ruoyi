package com.ruoyi.project.system.role.controller;

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

/**
 * 角色信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/role")
public class RoleController extends BaseController
{

    private String prefix = "system/role";

    @Autowired
    private IRoleService roleService;
    
    @RequiresPermissions("system:role:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/role";
    }

    @RequiresPermissions("system:role:view")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        TableDataInfo rows = roleService.pageInfoQuery(getPageUtilEntity());
        return rows;
    }

    /**
     * 修改角色
     */
    @Log(title = "系统管理", action = "角色管理-修改角色")
    @GetMapping("/edit/{roleId}")
    public String edit(@PathVariable("roleId") Long roleId, Model model)
    {
        Role role = roleService.selectRoleById(roleId);
        model.addAttribute("role", role);
        return prefix + "/edit";
    }

    @Log(title = "系统管理", action = "角色管理-删除角色")
    @RequestMapping("/remove/{roleId}")
    @ResponseBody
    public JSON remove(@PathVariable("roleId") Long roleId)
    {
        Role role = roleService.selectRoleById(roleId);
        if (role == null)
        {
            return JSON.error("角色不存在");
        }
        if (roleService.deleteRoleById(roleId) > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    @Log(title = "系统管理", action = "角色管理-批量删除")
    @PostMapping("/batchRemove")
    @ResponseBody
    public JSON batchRemove(@RequestParam("ids[]") Long[] ids)
    {
        int rows = roleService.batchDeleteRole(ids);
        if (rows > 0)
        {
            return JSON.ok();
        }
        return JSON.error();
    }

    /**
     * 选择菜单树
     */
    @GetMapping("/selectMenuTree")
    public String selectMenuTree()
    {
        return prefix + "/tree";
    }

}