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
import com.ruoyi.system.domain.BusUserBrowse;
import com.ruoyi.system.service.IBusUserBrowseService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 用户浏览足迹 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busUserBrowse")
public class BusUserBrowseController extends BaseController
{
    private String prefix = "system/busUserBrowse";
	
	@Autowired
	private IBusUserBrowseService busUserBrowseService;
	
	@RequiresPermissions("system:busUserBrowse:view")
	@GetMapping()
	public String busUserBrowse()
	{
	    return prefix + "/busUserBrowse";
	}
	
	/**
	 * 查询用户浏览足迹列表
	 */
	@RequiresPermissions("system:busUserBrowse:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusUserBrowse busUserBrowse)
	{
		startPage();
        List<BusUserBrowse> list = busUserBrowseService.selectBusUserBrowseList(busUserBrowse);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户浏览足迹列表
	 */
	@RequiresPermissions("system:busUserBrowse:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusUserBrowse busUserBrowse)
    {
    	List<BusUserBrowse> list = busUserBrowseService.selectBusUserBrowseList(busUserBrowse);
        ExcelUtil<BusUserBrowse> util = new ExcelUtil<BusUserBrowse>(BusUserBrowse.class);
        return util.exportExcel(list, "busUserBrowse");
    }
	
	/**
	 * 新增用户浏览足迹
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户浏览足迹
	 */
	@RequiresPermissions("system:busUserBrowse:add")
	@Log(title = "用户浏览足迹", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusUserBrowse busUserBrowse)
	{		
		return toAjax(busUserBrowseService.insertBusUserBrowse(busUserBrowse));
	}

	/**
	 * 修改用户浏览足迹
	 */
	@GetMapping("/edit/{browseId}")
	public String edit(@PathVariable("browseId") Long browseId, ModelMap mmap)
	{
		BusUserBrowse busUserBrowse = busUserBrowseService.selectBusUserBrowseById(browseId);
		mmap.put("busUserBrowse", busUserBrowse);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户浏览足迹
	 */
	@RequiresPermissions("system:busUserBrowse:edit")
	@Log(title = "用户浏览足迹", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusUserBrowse busUserBrowse)
	{		
		return toAjax(busUserBrowseService.updateBusUserBrowse(busUserBrowse));
	}
	
	/**
	 * 删除用户浏览足迹
	 */
	@RequiresPermissions("system:busUserBrowse:remove")
	@Log(title = "用户浏览足迹", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busUserBrowseService.deleteBusUserBrowseByIds(ids));
	}
	
}
