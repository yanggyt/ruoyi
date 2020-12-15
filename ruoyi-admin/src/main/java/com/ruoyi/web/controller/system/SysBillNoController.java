package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.SysBillNo;
import com.ruoyi.system.service.ISysBillNoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 单据号迭代信息Controller
 * 
 * @author dalin
 * @date 2020-12-15
 */
@Controller
@RequestMapping("/SysBillNo/bill")
public class SysBillNoController extends BaseController
{
    private String prefix = "SysBillNo/bill";

    @Autowired
    private ISysBillNoService sysBillNoService;

    @RequiresPermissions("SysBillNo:bill:view")
    @GetMapping()
    public String bill()
    {
        return prefix + "/bill";
    }

    /**
     * 查询单据号迭代信息列表
     */
    @RequiresPermissions("SysBillNo:bill:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysBillNo sysBillNo)
    {
        startPage();
        List<SysBillNo> list = sysBillNoService.selectSysBillNoList(sysBillNo);
        return getDataTable(list);
    }

    /**
     * 导出单据号迭代信息列表
     */
    @RequiresPermissions("SysBillNo:bill:export")
    @Log(title = "单据号迭代信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysBillNo sysBillNo)
    {
        List<SysBillNo> list = sysBillNoService.selectSysBillNoList(sysBillNo);
        ExcelUtil<SysBillNo> util = new ExcelUtil<SysBillNo>(SysBillNo.class);
        return util.exportExcel(list, "bill");
    }

    /**
     * 新增单据号迭代信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存单据号迭代信息
     */
    @RequiresPermissions("SysBillNo:bill:add")
    @Log(title = "单据号迭代信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysBillNo sysBillNo)
    {
        return toAjax(sysBillNoService.insertSysBillNo(sysBillNo));
    }

    /**
     * 修改单据号迭代信息
     */
    @GetMapping("/edit/{fperiod}")
    public String edit(@PathVariable("fperiod") String fperiod, ModelMap mmap)
    {
        SysBillNo sysBillNo = sysBillNoService.selectSysBillNoById(fperiod);
        mmap.put("sysBillNo", sysBillNo);
        return prefix + "/edit";
    }

    /**
     * 修改保存单据号迭代信息
     */
    @RequiresPermissions("SysBillNo:bill:edit")
    @Log(title = "单据号迭代信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysBillNo sysBillNo)
    {
        return toAjax(sysBillNoService.updateSysBillNo(sysBillNo));
    }

    /**
     * 删除单据号迭代信息
     */
    @RequiresPermissions("SysBillNo:bill:remove")
    @Log(title = "单据号迭代信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysBillNoService.deleteSysBillNoByIds(ids));
    }
}
