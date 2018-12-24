<<<<<<< HEAD
package com.ruoyi.web.controller.monitor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruoyi.framework.web.base.BaseController;

/**
 * druid 监控
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController extends BaseController
{
    private String prefix = "/monitor/druid";

    @RequiresPermissions("monitor:data:view")
    @GetMapping()
    public String index()
    {
        return redirect(prefix + "/index");
    }
}
=======
package com.ruoyi.web.controller.monitor;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import com.ruoyi.framework.web.base.BaseController;

/**
 * druid 监控
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/data")
public class DruidController extends BaseController
{
    private String prefix = "/monitor/druid";

    @RequiresPermissions("monitor:data:view")
    @GetMapping()
    public String index()
    {
        return redirect(prefix + "/index");
    }
}
>>>>>>> c404de177361c58256c3fe7ac8124ea9ac7f890d
