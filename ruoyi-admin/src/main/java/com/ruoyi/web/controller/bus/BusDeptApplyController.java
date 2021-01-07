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
import com.ruoyi.system.domain.BusDeptApply;
import com.ruoyi.system.service.IBusDeptApplyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 企业入驻申请 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busDeptApply")
public class BusDeptApplyController extends BaseController
{
    private String prefix = "system/busDeptApply";
	
	@Autowired
	private IBusDeptApplyService busDeptApplyService;
	
	@RequiresPermissions("system:busDeptApply:view")
	@GetMapping()
	public String busDeptApply()
	{
	    return prefix + "/busDeptApply";
	}
	
	/**
	 * 查询企业入驻申请列表
	 */
	@RequiresPermissions("system:busDeptApply:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusDeptApply busDeptApply)
	{
		startPage();
        List<BusDeptApply> list = busDeptApplyService.selectBusDeptApplyList(busDeptApply);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出企业入驻申请列表
	 */
	@RequiresPermissions("system:busDeptApply:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusDeptApply busDeptApply)
    {
    	List<BusDeptApply> list = busDeptApplyService.selectBusDeptApplyList(busDeptApply);
        ExcelUtil<BusDeptApply> util = new ExcelUtil<BusDeptApply>(BusDeptApply.class);
        return util.exportExcel(list, "busDeptApply");
    }
	
	/**
	 * 新增企业入驻申请
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存企业入驻申请
	 */
	@RequiresPermissions("system:busDeptApply:add")
	@Log(title = "企业入驻申请", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusDeptApply busDeptApply)
	{		
		return toAjax(busDeptApplyService.insertBusDeptApply(busDeptApply));
	}

	/**
	 * 修改企业入驻申请
	 */
	@GetMapping("/edit/{applyId}")
	public String edit(@PathVariable("applyId") Long applyId, ModelMap mmap)
	{
		BusDeptApply busDeptApply = busDeptApplyService.selectBusDeptApplyById(applyId);
		mmap.put("busDeptApply", busDeptApply);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存企业入驻申请
	 */
	@RequiresPermissions("system:busDeptApply:edit")
	@Log(title = "企业入驻申请", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusDeptApply busDeptApply)
	{		
		return toAjax(busDeptApplyService.updateBusDeptApply(busDeptApply));
	}
	
	/**
	 * 删除企业入驻申请
	 */
	@RequiresPermissions("system:busDeptApply:remove")
	@Log(title = "企业入驻申请", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busDeptApplyService.deleteBusDeptApplyByIds(ids));
	}
	
}
