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
import com.ruoyi.system.domain.BusAccessory;
import com.ruoyi.system.service.IBusAccessoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 附件 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busAccessory")
public class BusAccessoryController extends BaseController
{
    private String prefix = "system/busAccessory";
	
	@Autowired
	private IBusAccessoryService busAccessoryService;
	
	@RequiresPermissions("system:busAccessory:view")
	@GetMapping()
	public String busAccessory()
	{
	    return prefix + "/busAccessory";
	}
	
	/**
	 * 查询附件列表
	 */
	@RequiresPermissions("system:busAccessory:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusAccessory busAccessory)
	{
		startPage();
        List<BusAccessory> list = busAccessoryService.selectBusAccessoryList(busAccessory);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出附件列表
	 */
	@RequiresPermissions("system:busAccessory:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusAccessory busAccessory)
    {
    	List<BusAccessory> list = busAccessoryService.selectBusAccessoryList(busAccessory);
        ExcelUtil<BusAccessory> util = new ExcelUtil<BusAccessory>(BusAccessory.class);
        return util.exportExcel(list, "busAccessory");
    }
	
	/**
	 * 新增附件
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存附件
	 */
	@RequiresPermissions("system:busAccessory:add")
	@Log(title = "附件", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusAccessory busAccessory)
	{		
		return toAjax(busAccessoryService.insertBusAccessory(busAccessory));
	}

	/**
	 * 修改附件
	 */
	@GetMapping("/edit/{accessoryId}")
	public String edit(@PathVariable("accessoryId") Long accessoryId, ModelMap mmap)
	{
		BusAccessory busAccessory = busAccessoryService.selectBusAccessoryById(accessoryId);
		mmap.put("busAccessory", busAccessory);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存附件
	 */
	@RequiresPermissions("system:busAccessory:edit")
	@Log(title = "附件", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusAccessory busAccessory)
	{		
		return toAjax(busAccessoryService.updateBusAccessory(busAccessory));
	}
	
	/**
	 * 删除附件
	 */
	@RequiresPermissions("system:busAccessory:remove")
	@Log(title = "附件", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busAccessoryService.deleteBusAccessoryByIds(ids));
	}
	
}
