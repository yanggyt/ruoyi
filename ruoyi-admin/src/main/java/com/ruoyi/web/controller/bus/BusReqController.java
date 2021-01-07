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
import com.ruoyi.system.domain.BusReq;
import com.ruoyi.system.service.IBusReqService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 资源需求 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busReq")
public class BusReqController extends BaseController
{
    private String prefix = "system/busReq";
	
	@Autowired
	private IBusReqService busReqService;
	
	@RequiresPermissions("system:busReq:view")
	@GetMapping()
	public String busReq()
	{
	    return prefix + "/busReq";
	}
	
	/**
	 * 查询资源需求列表
	 */
	@RequiresPermissions("system:busReq:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusReq busReq)
	{
		startPage();
        List<BusReq> list = busReqService.selectBusReqList(busReq);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资源需求列表
	 */
	@RequiresPermissions("system:busReq:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusReq busReq)
    {
    	List<BusReq> list = busReqService.selectBusReqList(busReq);
        ExcelUtil<BusReq> util = new ExcelUtil<BusReq>(BusReq.class);
        return util.exportExcel(list, "busReq");
    }
	
	/**
	 * 新增资源需求
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资源需求
	 */
	@RequiresPermissions("system:busReq:add")
	@Log(title = "资源需求", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusReq busReq)
	{		
		return toAjax(busReqService.insertBusReq(busReq));
	}

	/**
	 * 修改资源需求
	 */
	@GetMapping("/edit/{reqId}")
	public String edit(@PathVariable("reqId") Long reqId, ModelMap mmap)
	{
		BusReq busReq = busReqService.selectBusReqById(reqId);
		mmap.put("busReq", busReq);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资源需求
	 */
	@RequiresPermissions("system:busReq:edit")
	@Log(title = "资源需求", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusReq busReq)
	{		
		return toAjax(busReqService.updateBusReq(busReq));
	}
	
	/**
	 * 删除资源需求
	 */
	@RequiresPermissions("system:busReq:remove")
	@Log(title = "资源需求", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busReqService.deleteBusReqByIds(ids));
	}
	
}
