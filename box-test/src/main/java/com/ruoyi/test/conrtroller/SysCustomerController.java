package com.ruoyi.test.conrtroller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.test.domain.SysCustomer;
import com.ruoyi.test.service.ISysCustomerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户Controller
 * 
 * @author box
 * @date 2021-02-13
 */
@Controller
@RequestMapping("/test/customer")
public class SysCustomerController extends BaseController
{
    private String prefix = "test/customer";

    @Autowired
    private ISysCustomerService sysCustomerService;

    @RequiresPermissions("test:customer:view")
    @GetMapping()
    public String customer()
    {
        return prefix + "/customer";
    }

    /**
     * 查询客户列表
     */
    @RequiresPermissions("test:customer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysCustomer sysCustomer)
    {
        startPage();
        List<SysCustomer> list = sysCustomerService.selectSysCustomerList(sysCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户列表
     */
    @RequiresPermissions("test:customer:export")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysCustomer sysCustomer)
    {
        List<SysCustomer> list = sysCustomerService.selectSysCustomerList(sysCustomer);
        ExcelUtil<SysCustomer> util = new ExcelUtil<SysCustomer>(SysCustomer.class);
        return util.exportExcel(list, "customer");
    }

    /**
     * 新增客户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存客户
     */
    @RequiresPermissions("test:customer:add")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysCustomer sysCustomer)
    {
        return toAjax(sysCustomerService.insertSysCustomer(sysCustomer));
    }

    /**
     * 修改客户
     */
    @GetMapping("/edit/{customerId}")
    public String edit(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
        SysCustomer sysCustomer = sysCustomerService.selectSysCustomerById(customerId);
        mmap.put("sysCustomer", sysCustomer);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户
     */
    @RequiresPermissions("test:customer:edit")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysCustomer sysCustomer)
    {
        return toAjax(sysCustomerService.updateSysCustomer(sysCustomer));
    }

    /**
     * 删除客户
     */
    @RequiresPermissions("test:customer:remove")
    @Log(title = "客户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysCustomerService.deleteSysCustomerByIds(ids));
    }
}
