package com.wuzhen.web.controller.busi;

import com.wuzhen.common.annotation.Log;
import com.wuzhen.common.core.controller.BaseController;
import com.wuzhen.common.core.domain.AjaxResult;
import com.wuzhen.common.core.page.TableDataInfo;
import com.wuzhen.common.enums.BusinessType;
import com.wuzhen.common.utils.poi.ExcelUtil;
import com.wuzhen.system.domain.EnrollActiveUser;
import com.wuzhen.system.service.IActiveUserService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * 报名用户查询
 *
 * @author zhengzheng
 */
@Controller
@RequestMapping("/active/user")
public class ActiveUserController extends BaseController {
    private String prefix = "active/user";

    @Autowired
    private IActiveUserService iActiveUserService;

    @RequiresPermissions("active:user:view")
    @GetMapping()
    public String operlog() {
        return prefix + "/index";
    }

    @RequiresPermissions("active:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EnrollActiveUser enrollActiveUser) {
        startPage();
        List<EnrollActiveUser> list = iActiveUserService.selectActiveUserList(enrollActiveUser);
        return getDataTable(list);
    }

    @Log(title = "用户活动导出管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("active:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EnrollActiveUser enrollActiveUser) {
        List<EnrollActiveUser> list = iActiveUserService.selectActiveUserList(enrollActiveUser);
        ExcelUtil<EnrollActiveUser> util = new ExcelUtil<EnrollActiveUser>(EnrollActiveUser.class);
        return util.exportExcel(list, "报名用户活动数据");
    }

}
