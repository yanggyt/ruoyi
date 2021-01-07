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
import com.ruoyi.system.domain.BusReqProgress;
import com.ruoyi.system.service.IBusReqProgressService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 资源需求进度 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busReqProgress")
public class BusReqProgressController extends BaseController
{
    private String prefix = "system/busReqProgress";
	
	@Autowired
	private IBusReqProgressService busReqProgressService;
	
	@RequiresPermissions("system:busReqProgress:view")
	@GetMapping()
	public String busReqProgress()
	{
	    return prefix + "/busReqProgress";
	}
	
	/**
	 * 查询资源需求进度列表
	 */
	@RequiresPermissions("system:busReqProgress:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusReqProgress busReqProgress)
	{
		startPage();
        List<BusReqProgress> list = busReqProgressService.selectBusReqProgressList(busReqProgress);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资源需求进度列表
	 */
	@RequiresPermissions("system:busReqProgress:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusReqProgress busReqProgress)
    {
    	List<BusReqProgress> list = busReqProgressService.selectBusReqProgressList(busReqProgress);
        ExcelUtil<BusReqProgress> util = new ExcelUtil<BusReqProgress>(BusReqProgress.class);
        return util.exportExcel(list, "busReqProgress");
    }
	
	/**
	 * 新增资源需求进度
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资源需求进度
	 */
	@RequiresPermissions("system:busReqProgress:add")
	@Log(title = "资源需求进度", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusReqProgress busReqProgress)
	{		
		return toAjax(busReqProgressService.insertBusReqProgress(busReqProgress));
	}

	/**
	 * 修改资源需求进度
	 */
	@GetMapping("/edit/{progressId}")
	public String edit(@PathVariable("progressId") Long progressId, ModelMap mmap)
	{
		BusReqProgress busReqProgress = busReqProgressService.selectBusReqProgressById(progressId);
		mmap.put("busReqProgress", busReqProgress);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资源需求进度
	 */
	@RequiresPermissions("system:busReqProgress:edit")
	@Log(title = "资源需求进度", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusReqProgress busReqProgress)
	{		
		return toAjax(busReqProgressService.updateBusReqProgress(busReqProgress));
	}
	
	/**
	 * 删除资源需求进度
	 */
	@RequiresPermissions("system:busReqProgress:remove")
	@Log(title = "资源需求进度", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busReqProgressService.deleteBusReqProgressByIds(ids));
	}
	
}
