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
import com.ruoyi.system.domain.BusResourceApply;
import com.ruoyi.system.service.IBusResourceApplyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 资源使用申请 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busResourceApply")
public class BusResourceApplyController extends BaseController
{
    private String prefix = "system/busResourceApply";
	
	@Autowired
	private IBusResourceApplyService busResourceApplyService;
	
	@RequiresPermissions("system:busResourceApply:view")
	@GetMapping()
	public String busResourceApply()
	{
	    return prefix + "/busResourceApply";
	}
	
	/**
	 * 查询资源使用申请列表
	 */
	@RequiresPermissions("system:busResourceApply:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusResourceApply busResourceApply)
	{
		startPage();
        List<BusResourceApply> list = busResourceApplyService.selectBusResourceApplyList(busResourceApply);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资源使用申请列表
	 */
	@RequiresPermissions("system:busResourceApply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusResourceApply busResourceApply)
    {
    	List<BusResourceApply> list = busResourceApplyService.selectBusResourceApplyList(busResourceApply);
        ExcelUtil<BusResourceApply> util = new ExcelUtil<BusResourceApply>(BusResourceApply.class);
        return util.exportExcel(list, "busResourceApply");
    }
	
	/**
	 * 新增资源使用申请
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资源使用申请
	 */
	@RequiresPermissions("system:busResourceApply:add")
	@Log(title = "资源使用申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusResourceApply busResourceApply)
	{		
		return toAjax(busResourceApplyService.insertBusResourceApply(busResourceApply));
	}

	/**
	 * 修改资源使用申请
	 */
	@GetMapping("/edit/{applyId}")
	public String edit(@PathVariable("applyId") Long applyId, ModelMap mmap)
	{
		BusResourceApply busResourceApply = busResourceApplyService.selectBusResourceApplyById(applyId);
		mmap.put("busResourceApply", busResourceApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资源使用申请
	 */
	@RequiresPermissions("system:busResourceApply:edit")
	@Log(title = "资源使用申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusResourceApply busResourceApply)
	{		
		return toAjax(busResourceApplyService.updateBusResourceApply(busResourceApply));
	}
	
	/**
	 * 删除资源使用申请
	 */
	@RequiresPermissions("system:busResourceApply:remove")
	@Log(title = "资源使用申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busResourceApplyService.deleteBusResourceApplyByIds(ids));
	}
	
}
