package com.ruoyi.busi.controller;

import java.util.List;

import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.busi.domain.BusiMaterialOperate;
import com.ruoyi.busi.service.IBusiMaterialOperateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 物料操作流水Controller
 * 
 * @author WangCL
 * @date 2021-12-24
 */
@Controller
@RequestMapping("/busi/materialperate")
public class BusiMaterialOperateController extends BaseController
{
    private String prefix = "busi/materialperate";

    @Autowired
    private IBusiMaterialOperateService busiMaterialOperateService;

    @RequiresPermissions("busi:materialperate:view")
    @GetMapping()
    public String materialperate()
    {
        return prefix + "/materialperate";
    }

    /**
     * 查询物料操作流水列表
     */
    @RequiresPermissions("busi:materialperate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiMaterialOperate busiMaterialOperate)
    {
        startPage();
        List<BusiMaterialOperate> list = busiMaterialOperateService.selectBusiMaterialOperateList(busiMaterialOperate);
        return getDataTable(list);
    }

    /**
     * 导出物料操作流水列表
     */
    @RequiresPermissions("busi:materialperate:export")
    @Log(title = "物料操作流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiMaterialOperate busiMaterialOperate)
    {
        List<BusiMaterialOperate> list = busiMaterialOperateService.selectBusiMaterialOperateList(busiMaterialOperate);
        ExcelUtil<BusiMaterialOperate> util = new ExcelUtil<BusiMaterialOperate>(BusiMaterialOperate.class);
        return util.exportExcel(list, "物料操作流水数据");
    }

    /**
     * 新增物料操作流水
     */
    @GetMapping("/addIn")
    public String addIn()
    {
        return prefix + "/addIn";
    }

    /**
     * 新增物料操作流水
     */
    @GetMapping("/addOut")
    public String addOut()
    {
        return prefix + "/addOut";
    }

    /**
     * 新增保存物料操作流水
     */
    @RequiresPermissions("busi:materialperate:add")
    @Log(title = "物料操作流水", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiMaterialOperate busiMaterialOperate) throws ServiceException {
        busiMaterialOperate.setCreateBy(getLoginName());
        busiMaterialOperate.setCreateTime(DateUtils.getNowDate());

        return toAjax(busiMaterialOperateService.insertBusiMaterialOperate(busiMaterialOperate));
    }

    /**
     * 修改物料操作流水
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BusiMaterialOperate busiMaterialOperate = busiMaterialOperateService.selectBusiMaterialOperateById(id);
        mmap.put("busiMaterialOperate", busiMaterialOperate);
        return prefix + "/edit";
    }

    /**
     * 修改保存物料操作流水
     */
    @RequiresPermissions("busi:materialperate:edit")
    @Log(title = "物料操作流水", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiMaterialOperate busiMaterialOperate)
    {
        return toAjax(busiMaterialOperateService.updateBusiMaterialOperate(busiMaterialOperate));
    }

    /**
     * 删除物料操作流水
     */
    @RequiresPermissions("busi:materialperate:remove")
    @Log(title = "物料操作流水", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(busiMaterialOperateService.deleteBusiMaterialOperateByIds(ids));
    }
}
