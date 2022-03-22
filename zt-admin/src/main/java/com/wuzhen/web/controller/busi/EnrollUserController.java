package com.wuzhen.web.controller.busi;

import com.wuzhen.common.annotation.Log;
import com.wuzhen.common.core.controller.BaseController;
import com.wuzhen.common.core.domain.AjaxResult;
import com.wuzhen.common.core.page.TableDataInfo;
import com.wuzhen.common.enums.BusinessType;
import com.wuzhen.common.utils.poi.ExcelUtil;
import com.wuzhen.system.domain.EnrollUser;
import com.wuzhen.system.service.IEnrollUserService;
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
@RequestMapping("/enroll/user")
public class EnrollUserController extends BaseController
{
    private String prefix = "enroll/user";

    @Autowired
    private IEnrollUserService iEnrollUserService;

    @RequiresPermissions("enroll:user:view")
    @GetMapping()
    public String operlog()
    {
        return prefix + "/index";
    }

    @RequiresPermissions("enroll:user:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EnrollUser enrollUser)
    {
        startPage();
        List<EnrollUser> list = iEnrollUserService.selectEnrollUserList(enrollUser);
        return getDataTable(list);
    }

    @Log(title = "用户导出管理", businessType = BusinessType.EXPORT)
    @RequiresPermissions("enroll:user:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EnrollUser enrollUser)
    {
        List<EnrollUser> list = iEnrollUserService.selectEnrollUserList(enrollUser);
        ExcelUtil<EnrollUser> util = new ExcelUtil<EnrollUser>(EnrollUser.class);
        return util.exportExcel(list, "报名用户数据");
    }


}
