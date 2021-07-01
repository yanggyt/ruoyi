package com.ruoyi.test.conrtroller;

import com.ruoyi.common.core.domain.AjaxResult;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 *
 * 测试权限登录访问请求
 *
 *         登录访问（返回token） POST / http://localhost:80/jwt/login?username=ry&password=admin123
 *
 *         测试任意权限（header携带token） GET / http://localhost:80/api/list
 *
 *         测试菜单权限（header携带token） GET / http://localhost:80/api/user/list
 *
 *         测试角色权限（header携带token） GET / http://localhost:80/api/role/list
 *
 */



@RestController
@RequestMapping("/api")
public class ApiController
{
    /**
     * 无权限访问
     *
     * @return
     */
    @GetMapping("/list")
    public AjaxResult list()
    {
        return AjaxResult.success("list success");
    }

    /**
     * 菜单权限 system:user:list
     */
    @GetMapping("/user/list")
    @RequiresPermissions("system:user:list")
    public AjaxResult userlist()
    {
        return AjaxResult.success("user list success");
    }

    /**
     * 角色权限 admin
     */
    @GetMapping("/role/list")
    @RequiresRoles("admin")
    public AjaxResult rolelist()
    {
        return AjaxResult.success("role list success");
    }
}