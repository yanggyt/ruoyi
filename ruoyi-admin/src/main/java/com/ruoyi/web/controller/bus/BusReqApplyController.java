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
import com.ruoyi.system.domain.BusReqApply;
import com.ruoyi.system.service.IBusReqApplyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 资源需求接包申请 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busReqApply")
public class BusReqApplyController extends BaseController
{
    private String prefix = "system/busReqApply";
	
	@Autowired
	private IBusReqApplyService busReqApplyService;
	
	@RequiresPermissions("system:busReqApply:view")
	@GetMapping()
	public String busReqApply()
	{
	    return prefix + "/busReqApply";
	}
	
	/**
	 * 查询资源需求接包申请列表
	 */
	@RequiresPermissions("system:busReqApply:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusReqApply busReqApply)
	{
		startPage();
        List<BusReqApply> list = busReqApplyService.selectBusReqApplyList(busReqApply);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资源需求接包申请列表
	 */
	@RequiresPermissions("system:busReqApply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusReqApply busReqApply)
    {
    	List<BusReqApply> list = busReqApplyService.selectBusReqApplyList(busReqApply);
        ExcelUtil<BusReqApply> util = new ExcelUtil<BusReqApply>(BusReqApply.class);
        return util.exportExcel(list, "busReqApply");
    }
	
	/**
	 * 新增资源需求接包申请
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资源需求接包申请
	 */
	@RequiresPermissions("system:busReqApply:add")
	@Log(title = "资源需求接包申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusReqApply busReqApply)
	{		
		return toAjax(busReqApplyService.insertBusReqApply(busReqApply));
	}

	/**
	 * 修改资源需求接包申请
	 */
	@GetMapping("/edit/{applyId}")
	public String edit(@PathVariable("applyId") Long applyId, ModelMap mmap)
	{
		BusReqApply busReqApply = busReqApplyService.selectBusReqApplyById(applyId);
		mmap.put("busReqApply", busReqApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资源需求接包申请
	 */
	@RequiresPermissions("system:busReqApply:edit")
	@Log(title = "资源需求接包申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusReqApply busReqApply)
	{		
		return toAjax(busReqApplyService.updateBusReqApply(busReqApply));
	}
	
	/**
	 * 删除资源需求接包申请
	 */
	@RequiresPermissions("system:busReqApply:remove")
	@Log(title = "资源需求接包申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busReqApplyService.deleteBusReqApplyByIds(ids));
	}
	
}
