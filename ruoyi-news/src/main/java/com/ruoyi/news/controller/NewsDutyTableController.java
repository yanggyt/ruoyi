package com.ruoyi.news.controller;

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
import com.ruoyi.news.domain.NewsDutyTable;
import com.ruoyi.news.service.INewsDutyTableService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 【请填写功能名称】Controller
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
@Controller
@RequestMapping("/news/table")
public class NewsDutyTableController extends BaseController
{
    private String prefix = "news/table";

    @Autowired
    private INewsDutyTableService newsDutyTableService;

    @RequiresPermissions("news:table:view")
    @GetMapping()
    public String table()
    {
        return prefix + "/table";
    }

    /**
     * 查询【请填写功能名称】列表
     */
    @RequiresPermissions("news:table:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(NewsDutyTable newsDutyTable)
    {
        startPage();
        List<NewsDutyTable> list = newsDutyTableService.selectNewsDutyTableList(newsDutyTable);
        return getDataTable(list);
    }

    /**
     * 导出【请填写功能名称】列表
     */
    @RequiresPermissions("news:table:export")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NewsDutyTable newsDutyTable)
    {
        List<NewsDutyTable> list = newsDutyTableService.selectNewsDutyTableList(newsDutyTable);
        ExcelUtil<NewsDutyTable> util = new ExcelUtil<NewsDutyTable>(NewsDutyTable.class);
        return util.exportExcel(list, "【请填写功能名称】数据");
    }

    /**
     * 新增【请填写功能名称】
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存【请填写功能名称】
     */
    @RequiresPermissions("news:table:add")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(NewsDutyTable newsDutyTable)
    {
        return toAjax(newsDutyTableService.insertNewsDutyTable(newsDutyTable));
    }

    /**
     * 修改【请填写功能名称】
     */
    @RequiresPermissions("news:table:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        NewsDutyTable newsDutyTable = newsDutyTableService.selectNewsDutyTableById(id);
        mmap.put("newsDutyTable", newsDutyTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存【请填写功能名称】
     */
    @RequiresPermissions("news:table:edit")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(NewsDutyTable newsDutyTable)
    {
        return toAjax(newsDutyTableService.updateNewsDutyTable(newsDutyTable));
    }

    /**
     * 删除【请填写功能名称】
     */
    @RequiresPermissions("news:table:remove")
    @Log(title = "【请填写功能名称】", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(newsDutyTableService.deleteNewsDutyTableByIds(ids));
    }
}
