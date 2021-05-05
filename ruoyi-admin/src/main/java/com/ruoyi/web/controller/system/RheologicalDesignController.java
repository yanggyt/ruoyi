package com.ruoyi.system.controller;

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
import com.ruoyi.system.domain.RheologicalDesign;
import com.ruoyi.system.service.IRheologicalDesignService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 流变学设计数据Controller
 * 
 * @author ruoyi
 * @date 2021-05-05
 */
@Controller
@RequestMapping("/system/design")
public class RheologicalDesignController extends BaseController
{
    private String prefix = "system/design";

    @Autowired
    private IRheologicalDesignService rheologicalDesignService;

    @RequiresPermissions("system:design:view")
    @GetMapping()
    public String design()
    {
        return prefix + "/design";
    }

    /**
     * 查询流变学设计数据列表
     */
    @RequiresPermissions("system:design:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(RheologicalDesign rheologicalDesign)
    {
        startPage();
        List<RheologicalDesign> list = rheologicalDesignService.selectRheologicalDesignList(rheologicalDesign);
        return getDataTable(list);
    }

    /**
     * 导出流变学设计数据列表
     */
    @RequiresPermissions("system:design:export")
    @Log(title = "流变学设计数据", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(RheologicalDesign rheologicalDesign)
    {
        List<RheologicalDesign> list = rheologicalDesignService.selectRheologicalDesignList(rheologicalDesign);
        ExcelUtil<RheologicalDesign> util = new ExcelUtil<RheologicalDesign>(RheologicalDesign.class);
        return util.exportExcel(list, "流变学设计数据数据");
    }

    /**
     * 新增流变学设计数据
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存流变学设计数据
     */
    @RequiresPermissions("system:design:add")
    @Log(title = "流变学设计数据", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(RheologicalDesign rheologicalDesign)
    {
        return toAjax(rheologicalDesignService.insertRheologicalDesign(rheologicalDesign));
    }

    /**
     * 修改流变学设计数据
     */
    @GetMapping("/edit/{rheologicalDesignNumber}")
    public String edit(@PathVariable("rheologicalDesignNumber") Long rheologicalDesignNumber, ModelMap mmap)
    {
        RheologicalDesign rheologicalDesign = rheologicalDesignService.selectRheologicalDesignById(rheologicalDesignNumber);
        mmap.put("rheologicalDesign", rheologicalDesign);
        return prefix + "/edit";
    }

    /**
     * 修改保存流变学设计数据
     */
    @RequiresPermissions("system:design:edit")
    @Log(title = "流变学设计数据", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(RheologicalDesign rheologicalDesign)
    {
        return toAjax(rheologicalDesignService.updateRheologicalDesign(rheologicalDesign));
    }

    /**
     * 删除流变学设计数据
     */
    @RequiresPermissions("system:design:remove")
    @Log(title = "流变学设计数据", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(rheologicalDesignService.deleteRheologicalDesignByIds(ids));
    }
}
