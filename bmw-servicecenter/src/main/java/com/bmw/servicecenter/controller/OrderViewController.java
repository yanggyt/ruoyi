package com.bmw.servicecenter.controller;
import com.bmw.common.core.controller.BaseController;
import com.bmw.common.core.page.TableDataInfo;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * @author mwu
 * @date 2019/7/26
 */

@Controller
@RequestMapping("/order")
public class OrderViewController extends BaseController {

    @RequiresPermissions("order:list:view")
    @GetMapping ("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        System.out.println("订单列表");
        return null;
    }
}
