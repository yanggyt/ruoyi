package com.ruoyi.web.controller.bus;

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
import com.ruoyi.system.domain.BusResource;
import com.ruoyi.system.service.IBusResourceService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 资源 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busResource")
public class BusResourceController extends BaseController
{
    private String prefix = "system/busResource";
	
	@Autowired
	private IBusResourceService busResourceService;
	
	@RequiresPermissions("system:busResource:view")
	@GetMapping()
	public String busResource()
	{
	    return prefix + "/busResource";
	}
	
	/**
	 * 查询资源列表
	 */
	@RequiresPermissions("system:busResource:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusResource busResource)
	{
		startPage();
        List<BusResource> list = busResourceService.selectBusResourceList(busResource);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资源列表
	 */
	@RequiresPermissions("system:busResource:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusResource busResource)
    {
    	List<BusResource> list = busResourceService.selectBusResourceList(busResource);
        ExcelUtil<BusResource> util = new ExcelUtil<BusResource>(BusResource.class);
        return util.exportExcel(list, "busResource");
    }
	
	/**
	 * 新增资源
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资源
	 */
	@RequiresPermissions("system:busResource:add")
	@Log(title = "资源", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusResource busResource)
	{		
		return toAjax(busResourceService.insertBusResource(busResource));
	}

	/**
	 * 修改资源
	 */
	@GetMapping("/edit/{resourceId}")
	public String edit(@PathVariable("resourceId") Long resourceId, ModelMap mmap)
	{
		BusResource busResource = busResourceService.selectBusResourceById(resourceId);
		mmap.put("busResource", busResource);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资源
	 */
	@RequiresPermissions("system:busResource:edit")
	@Log(title = "资源", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusResource busResource)
	{		
		return toAjax(busResourceService.updateBusResource(busResource));
	}
	
	/**
	 * 删除资源
	 */
	@RequiresPermissions("system:busResource:remove")
	@Log(title = "资源", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busResourceService.deleteBusResourceByIds(ids));
	}
	
}
