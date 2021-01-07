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
import com.ruoyi.system.domain.BusResourceType;
import com.ruoyi.system.service.IBusResourceTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 资源分类 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busResourceType")
public class BusResourceTypeController extends BaseController
{
    private String prefix = "system/busResourceType";
	
	@Autowired
	private IBusResourceTypeService busResourceTypeService;
	
	@RequiresPermissions("system:busResourceType:view")
	@GetMapping()
	public String busResourceType()
	{
	    return prefix + "/busResourceType";
	}
	
	/**
	 * 查询资源分类列表
	 */
	@RequiresPermissions("system:busResourceType:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusResourceType busResourceType)
	{
		startPage();
        List<BusResourceType> list = busResourceTypeService.selectBusResourceTypeList(busResourceType);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资源分类列表
	 */
	@RequiresPermissions("system:busResourceType:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusResourceType busResourceType)
    {
    	List<BusResourceType> list = busResourceTypeService.selectBusResourceTypeList(busResourceType);
        ExcelUtil<BusResourceType> util = new ExcelUtil<BusResourceType>(BusResourceType.class);
        return util.exportExcel(list, "busResourceType");
    }
	
	/**
	 * 新增资源分类
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资源分类
	 */
	@RequiresPermissions("system:busResourceType:add")
	@Log(title = "资源分类", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusResourceType busResourceType)
	{		
		return toAjax(busResourceTypeService.insertBusResourceType(busResourceType));
	}

	/**
	 * 修改资源分类
	 */
	@GetMapping("/edit/{typeId}")
	public String edit(@PathVariable("typeId") Long typeId, ModelMap mmap)
	{
		BusResourceType busResourceType = busResourceTypeService.selectBusResourceTypeById(typeId);
		mmap.put("busResourceType", busResourceType);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资源分类
	 */
	@RequiresPermissions("system:busResourceType:edit")
	@Log(title = "资源分类", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusResourceType busResourceType)
	{		
		return toAjax(busResourceTypeService.updateBusResourceType(busResourceType));
	}
	
	/**
	 * 删除资源分类
	 */
	@RequiresPermissions("system:busResourceType:remove")
	@Log(title = "资源分类", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busResourceTypeService.deleteBusResourceTypeByIds(ids));
	}
	
}
