package com.ruoyi.web.controller.system;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.system.domain.WkCrmCustomer;
import com.ruoyi.system.service.IWkCrmCustomerService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户Controller
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Controller
@RequestMapping("/system/customer")
public class WkCrmCustomerController extends BaseController
{
    private String prefix = "system/customer";

    @Autowired
    private IWkCrmCustomerService wkCrmCustomerService;

    @RequiresPermissions("system:customer:view")
    @GetMapping()
    public String customer()
    {
        return prefix + "/customer";
    }

    /**
     * 查询客户列表
     */
    @RequiresPermissions("system:customer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WkCrmCustomer wkCrmCustomer)
    {
        startPage();
        List<WkCrmCustomer> list = wkCrmCustomerService.selectWkCrmCustomerList(wkCrmCustomer);
        return getDataTable(list);
    }

    /**
     * 导出客户列表
     */
    @RequiresPermissions("system:customer:export")
    @Log(title = "客户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WkCrmCustomer wkCrmCustomer)
    {
        List<WkCrmCustomer> list = wkCrmCustomerService.selectWkCrmCustomerList(wkCrmCustomer);
        ExcelUtil<WkCrmCustomer> util = new ExcelUtil<WkCrmCustomer>(WkCrmCustomer.class);
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
    @RequiresPermissions("system:customer:add")
    @Log(title = "客户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WkCrmCustomer wkCrmCustomer)
    {
        return toAjax(wkCrmCustomerService.insertWkCrmCustomer(wkCrmCustomer));
    }

    /**
     * 修改客户
     */
    @GetMapping("/edit/{customerId}")
    public String edit(@PathVariable("customerId") Long customerId, ModelMap mmap)
    {
        WkCrmCustomer wkCrmCustomer = wkCrmCustomerService.selectWkCrmCustomerById(customerId);
        mmap.put("wkCrmCustomer", wkCrmCustomer);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户
     */
    @RequiresPermissions("system:customer:edit")
    @Log(title = "客户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WkCrmCustomer wkCrmCustomer)
    {
        return toAjax(wkCrmCustomerService.updateWkCrmCustomer(wkCrmCustomer));
    }

    /**
     * 删除客户
     */
    @RequiresPermissions("system:customer:remove")
    @Log(title = "客户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wkCrmCustomerService.deleteWkCrmCustomerByIds(ids));
    }
}
