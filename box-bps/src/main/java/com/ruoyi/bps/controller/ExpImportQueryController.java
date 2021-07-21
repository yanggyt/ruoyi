package com.ruoyi.bps.controller;

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
import com.ruoyi.bps.domain.ExpImportQuery;
import com.ruoyi.bps.service.IExpImportQueryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * Excel批量快递查询Controller
 * 
 * @author Bo
 * @date 2021-07-21
 */
@Controller
@RequestMapping("/bps/expImportQuery")
public class ExpImportQueryController extends BaseController
{
    private String prefix = "bps/expImportQuery";

    @Autowired
    private IExpImportQueryService expImportQueryService;

    @RequiresPermissions("bps:expImportQuery:view")
    @GetMapping()
    public String expImportQuery()
    {
        return prefix + "/expImportQuery";
    }

    /**
     * 查询Excel批量快递查询列表
     */
    @RequiresPermissions("bps:expImportQuery:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ExpImportQuery expImportQuery)
    {
        startPage();
        List<ExpImportQuery> list = expImportQueryService.selectExpImportQueryList(expImportQuery);
        return getDataTable(list);
    }

    /**
     * 导出Excel批量快递查询列表
     */
    @RequiresPermissions("bps:expImportQuery:export")
    @Log(title = "Excel批量快递查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExpImportQuery expImportQuery)
    {
        List<ExpImportQuery> list = expImportQueryService.selectExpImportQueryList(expImportQuery);
        ExcelUtil<ExpImportQuery> util = new ExcelUtil<ExpImportQuery>(ExpImportQuery.class);
        return util.exportExcel(list, "Excel批量快递查询数据");
    }

    /**
     * 新增Excel批量快递查询
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存Excel批量快递查询
     */
    @RequiresPermissions("bps:expImportQuery:add")
    @Log(title = "Excel批量快递查询", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ExpImportQuery expImportQuery)
    {
        return toAjax(expImportQueryService.insertExpImportQuery(expImportQuery));
    }

    /**
     * 修改Excel批量快递查询
     */
    @GetMapping("/edit/{sid}")
    public String edit(@PathVariable("sid") Long sid, ModelMap mmap)
    {
        ExpImportQuery expImportQuery = expImportQueryService.selectExpImportQueryById(sid);
        mmap.put("expImportQuery", expImportQuery);
        return prefix + "/edit";
    }

    /**
     * 修改保存Excel批量快递查询
     */
    @RequiresPermissions("bps:expImportQuery:edit")
    @Log(title = "Excel批量快递查询", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ExpImportQuery expImportQuery)
    {
        return toAjax(expImportQueryService.updateExpImportQuery(expImportQuery));
    }

    /**
     * 删除Excel批量快递查询
     */
    @RequiresPermissions("bps:expImportQuery:remove")
    @Log(title = "Excel批量快递查询", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(expImportQueryService.deleteExpImportQueryByIds(ids));
    }
}
