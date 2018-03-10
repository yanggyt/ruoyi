package com.ruoyi.project.system.menu.controller;

import java.util.List;
import java.util.Map;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.project.system.menu.service.IMenuService;
import com.ruoyi.project.system.role.domain.Role;

/**
 * 角色信息
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/system/menu")
public class MenuController extends BaseController
{

    private String prefix = "system/menu";

    @Autowired
    private IMenuService menuService;

    @RequiresPermissions("system:menu:view")
    @GetMapping()
    public String user()
    {
        return prefix + "/menu";
    }

    /**
     * 加载菜单列表树
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Map<String, Object>> treeData(Role role)
    {
        List<Map<String, Object>> tree = menuService.selectMenuTree(role);
        return tree;
    }
}