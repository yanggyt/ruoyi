package cn.com.infosouth.arj21.controller.parameter;

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
import cn.com.infosouth.common.annotation.Log;
import cn.com.infosouth.common.enums.BusinessType;
import cn.com.infosouth.arj21.domain.InfoParameterComputed;
import cn.com.infosouth.arj21.service.IInfoParameterComputedService;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.common.core.domain.AjaxResult;
import cn.com.infosouth.common.utils.poi.ExcelUtil;
import cn.com.infosouth.common.core.page.TableDataInfo;

/**
 * 计算参数Controller
 * 
 * @author kxnf
 * @date 2020-03-05
 */
@Controller
@RequestMapping("/arj21/parameter_computed")
public class InfoParameterComputedController extends BaseController
{
    private String prefix = "arj21/parameter_computed";

    @Autowired
    private IInfoParameterComputedService infoParameterComputedService;

    @RequiresPermissions("arj21:parameter_computed:view")
    @GetMapping()
    public String parameter_computed()
    {
        return prefix + "/parameter_computed";
    }

    /**
     * 查询计算参数列表
     */
    @RequiresPermissions("arj21:parameter_computed:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(InfoParameterComputed infoParameterComputed)
    {
        startPage();
        List<InfoParameterComputed> list = infoParameterComputedService.selectInfoParameterComputedList(infoParameterComputed);
        return getDataTable(list);
    }

    /**
     * 导出计算参数列表
     */
    @RequiresPermissions("arj21:parameter_computed:export")
    @Log(title = "计算参数", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InfoParameterComputed infoParameterComputed)
    {
        List<InfoParameterComputed> list = infoParameterComputedService.selectInfoParameterComputedList(infoParameterComputed);
        ExcelUtil<InfoParameterComputed> util = new ExcelUtil<InfoParameterComputed>(InfoParameterComputed.class);
        return util.exportExcel(list, "parameter_computed");
    }

    /**
     * 新增计算参数
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存计算参数
     */
    @RequiresPermissions("arj21:parameter_computed:add")
    @Log(title = "计算参数", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InfoParameterComputed infoParameterComputed)
    {
        return toAjax(infoParameterComputedService.insertInfoParameterComputed(infoParameterComputed));
    }

    /**
     * 修改计算参数
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        InfoParameterComputed infoParameterComputed = infoParameterComputedService.selectInfoParameterComputedById(id);
        mmap.put("infoParameterComputed", infoParameterComputed);
        return prefix + "/edit";
    }

    /**
     * 修改保存计算参数
     */
    @RequiresPermissions("arj21:parameter_computed:edit")
    @Log(title = "计算参数", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InfoParameterComputed infoParameterComputed)
    {
        return toAjax(infoParameterComputedService.updateInfoParameterComputed(infoParameterComputed));
    }

    /**
     * 删除计算参数
     */
    @RequiresPermissions("arj21:parameter_computed:remove")
    @Log(title = "计算参数", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(infoParameterComputedService.deleteInfoParameterComputedByIds(ids));
    }
}
