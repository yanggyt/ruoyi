package com.ruoyi.web.controller.template;

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
import com.ruoyi.template.domain.TmplSwitch;
import com.ruoyi.template.service.ITmplSwitchService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 交换机模板 信息操作处理
 * 
 * @author TP
 * @date 2019-06-12
 */
@Controller
@RequestMapping("/template/tmplSwitch")
public class TmplSwitchController extends BaseController
{
    private String prefix = "template/tmplSwitch";
	
	@Autowired
	private ITmplSwitchService tmplSwitchService;
	
	@RequiresPermissions("template:tmplSwitch:view")
	@GetMapping()
	public String tmplSwitch()
	{
	    return prefix + "/tmplSwitch";
	}
	
	/**
	 * 查询交换机模板列表
	 */
	@RequiresPermissions("template:tmplSwitch:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(TmplSwitch tmplSwitch)
	{
		startPage();
        List<TmplSwitch> list = tmplSwitchService.selectTmplSwitchList(tmplSwitch);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出交换机模板列表
	 */
	@RequiresPermissions("template:tmplSwitch:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmplSwitch tmplSwitch)
    {
    	List<TmplSwitch> list = tmplSwitchService.selectTmplSwitchList(tmplSwitch);
        ExcelUtil<TmplSwitch> util = new ExcelUtil<TmplSwitch>(TmplSwitch.class);
        return util.exportExcel(list, "tmplSwitch");
    }
	
	/**
	 * 新增交换机模板
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存交换机模板
	 */
	@RequiresPermissions("template:tmplSwitch:add")
	@Log(title = "交换机模板", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(TmplSwitch tmplSwitch)
	{		
		return toAjax(tmplSwitchService.insertTmplSwitch(tmplSwitch));
	}

	/**
	 * 修改交换机模板
	 */
	@GetMapping("/edit/{switchId}")
	public String edit(@PathVariable("switchId") Integer switchId, ModelMap mmap)
	{
		TmplSwitch tmplSwitch = tmplSwitchService.selectTmplSwitchById(switchId);
		mmap.put("tmplSwitch", tmplSwitch);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存交换机模板
	 */
	@RequiresPermissions("template:tmplSwitch:edit")
	@Log(title = "交换机模板", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(TmplSwitch tmplSwitch)
	{		
		return toAjax(tmplSwitchService.updateTmplSwitch(tmplSwitch));
	}
	
	/**
	 * 删除交换机模板
	 */
	@RequiresPermissions("template:tmplSwitch:remove")
	@Log(title = "交换机模板", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(tmplSwitchService.deleteTmplSwitchByIds(ids));
	}
	
}
