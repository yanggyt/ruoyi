package com.ruoyi.web.controller.cms.customer;

import com.ruoyi.cms.domain.Customer;
import com.ruoyi.cms.service.ICustomerService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 客户 信息操作处理
 * 
 * @author pengc
 * @date 2019-05-26
 */
@Controller
@RequestMapping("/cms/customer")
public class CustomerController extends BaseController
{
    private String prefix = "cms/customer";
	
	@Autowired
	private ICustomerService customerService;
	
	@RequiresPermissions("cms:customer:view")
	@GetMapping()
	public String customer()
	{
	    return prefix + "/customer";
	}
	
	/**
	 * 查询客户列表
	 */
	@RequiresPermissions("cms:customer:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Customer customer)
	{
		startPage();
        List<Customer> list = customerService.selectCustomerList(customer);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出客户列表
	 */
	@RequiresPermissions("cms:customer:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Customer customer)
    {
    	List<Customer> list = customerService.selectCustomerList(customer);
        ExcelUtil<Customer> util = new ExcelUtil<Customer>(Customer.class);
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
	@RequiresPermissions("cms:customer:add")
	@Log(title = "客户", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Customer customer)
	{		
		return toAjax(customerService.insertCustomer(customer));
	}

	/**
	 * 修改客户
	 */
	@GetMapping("/edit/{customerid}")
	public String edit(@PathVariable("customerid") Integer customerid, ModelMap mmap)
	{
		Customer customer = customerService.selectCustomerById(customerid);
		mmap.put("customer", customer);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存客户
	 */
	@RequiresPermissions("cms:customer:edit")
	@Log(title = "客户", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Customer customer)
	{		
		return toAjax(customerService.updateCustomer(customer));
	}
	
	/**
	 * 删除客户
	 */
	@RequiresPermissions("cms:customer:remove")
	@Log(title = "客户", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(customerService.deleteCustomerByIds(ids));
	}
	
}
