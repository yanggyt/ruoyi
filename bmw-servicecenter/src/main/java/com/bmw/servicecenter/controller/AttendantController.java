package com.bmw.servicecenter.controller;

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
import com.bmw.common.annotation.Log;
import com.bmw.common.enums.BusinessType;
import com.bmw.servicecenter.domain.Attendant;
import com.bmw.servicecenter.service.IAttendantService;
import com.bmw.common.core.controller.BaseController;
import com.bmw.common.core.page.TableDataInfo;
import com.bmw.common.core.domain.AjaxResult;
import com.bmw.common.utils.poi.ExcelUtil;

/**
 * 服务员 信息操作处理
 * 
 * @author bmw
 * @date 2019-07-26
 */
@Controller
@RequestMapping("/servicecenter/attendant")
public class AttendantController extends BaseController
{
    private String prefix = "servicecenter/attendant";
	
	@Autowired
	private IAttendantService attendantService;
	
	@RequiresPermissions("servicecenter:attendant:view")
	@GetMapping()
	public String attendant()
	{
	    return prefix + "/attendant";
	}
	
	/**
	 * 查询服务员列表
	 */
	@RequiresPermissions("servicecenter:attendant:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Attendant attendant)
	{
		startPage();
        List<Attendant> list = attendantService.selectAttendantList(attendant);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出服务员列表
	 */
	@RequiresPermissions("servicecenter:attendant:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Attendant attendant)
    {
    	List<Attendant> list = attendantService.selectAttendantList(attendant);
        ExcelUtil<Attendant> util = new ExcelUtil<Attendant>(Attendant.class);
        return util.exportExcel(list, "attendant");
    }
	
	/**
	 * 新增服务员
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存服务员
	 */
	@RequiresPermissions("servicecenter:attendant:add")
	@Log(title = "服务员", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Attendant attendant)
	{		
		return toAjax(attendantService.insertAttendant(attendant));
	}

	/**
	 * 修改服务员
	 */
	@GetMapping("/edit/{attendantId}")
	public String edit(@PathVariable("attendantId") Long attendantId, ModelMap mmap)
	{
		Attendant attendant = attendantService.selectAttendantById(attendantId);
		mmap.put("attendant", attendant);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存服务员
	 */
	@RequiresPermissions("servicecenter:attendant:edit")
	@Log(title = "服务员", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Attendant attendant)
	{		
		return toAjax(attendantService.updateAttendant(attendant));
	}
	
	/**
	 * 删除服务员
	 */
	@RequiresPermissions("servicecenter:attendant:remove")
	@Log(title = "服务员", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(attendantService.deleteAttendantByIds(ids));
	}
	
}
