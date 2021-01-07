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
import com.ruoyi.system.domain.BusUserCollect;
import com.ruoyi.system.service.IBusUserCollectService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 用户收藏 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busUserCollect")
public class BusUserCollectController extends BaseController
{
    private String prefix = "system/busUserCollect";
	
	@Autowired
	private IBusUserCollectService busUserCollectService;
	
	@RequiresPermissions("system:busUserCollect:view")
	@GetMapping()
	public String busUserCollect()
	{
	    return prefix + "/busUserCollect";
	}
	
	/**
	 * 查询用户收藏列表
	 */
	@RequiresPermissions("system:busUserCollect:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusUserCollect busUserCollect)
	{
		startPage();
        List<BusUserCollect> list = busUserCollectService.selectBusUserCollectList(busUserCollect);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户收藏列表
	 */
	@RequiresPermissions("system:busUserCollect:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusUserCollect busUserCollect)
    {
    	List<BusUserCollect> list = busUserCollectService.selectBusUserCollectList(busUserCollect);
        ExcelUtil<BusUserCollect> util = new ExcelUtil<BusUserCollect>(BusUserCollect.class);
        return util.exportExcel(list, "busUserCollect");
    }
	
	/**
	 * 新增用户收藏
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户收藏
	 */
	@RequiresPermissions("system:busUserCollect:add")
	@Log(title = "用户收藏", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusUserCollect busUserCollect)
	{		
		return toAjax(busUserCollectService.insertBusUserCollect(busUserCollect));
	}

	/**
	 * 修改用户收藏
	 */
	@GetMapping("/edit/{collectId}")
	public String edit(@PathVariable("collectId") Long collectId, ModelMap mmap)
	{
		BusUserCollect busUserCollect = busUserCollectService.selectBusUserCollectById(collectId);
		mmap.put("busUserCollect", busUserCollect);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户收藏
	 */
	@RequiresPermissions("system:busUserCollect:edit")
	@Log(title = "用户收藏", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusUserCollect busUserCollect)
	{		
		return toAjax(busUserCollectService.updateBusUserCollect(busUserCollect));
	}
	
	/**
	 * 删除用户收藏
	 */
	@RequiresPermissions("system:busUserCollect:remove")
	@Log(title = "用户收藏", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busUserCollectService.deleteBusUserCollectByIds(ids));
	}
	
}
