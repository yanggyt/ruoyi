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
import com.ruoyi.system.domain.BusinessFirm;
import com.ruoyi.system.service.IBusinessFirmService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 商家信息Controller
 * 
 * @author ruoyi
 * @date 2020-05-19
 */
@Controller
@RequestMapping("/system/firm")
public class BusinessFirmController extends BaseController
{
    private String prefix = "system/firm";

    @Autowired
    private IBusinessFirmService businessFirmService;

    @RequiresPermissions("system:firm:view")
    @GetMapping()
    public String firm()
    {
        return prefix + "/firm";
    }

    /**
     * 查询商家信息列表
     */
    @RequiresPermissions("system:firm:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusinessFirm businessFirm)
    {
        startPage();
        List<BusinessFirm> list = businessFirmService.selectBusinessFirmList(businessFirm);
        return getDataTable(list);
    }

    /**
     * 导出商家信息列表
     */
    @RequiresPermissions("system:firm:export")
    @Log(title = "商家信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusinessFirm businessFirm)
    {
        List<BusinessFirm> list = businessFirmService.selectBusinessFirmList(businessFirm);
        ExcelUtil<BusinessFirm> util = new ExcelUtil<BusinessFirm>(BusinessFirm.class);
        return util.exportExcel(list, "firm");
    }

    /**
     * 新增商家信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存商家信息
     */
    @RequiresPermissions("system:firm:add")
    @Log(title = "商家信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusinessFirm businessFirm)
    {
        return toAjax(businessFirmService.insertBusinessFirm(businessFirm));
    }

    /**
     * 修改商家信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BusinessFirm businessFirm = businessFirmService.selectBusinessFirmById(id);
        mmap.put("businessFirm", businessFirm);
        return prefix + "/edit";
    }

    /**
     * 修改保存商家信息
     */
    @RequiresPermissions("system:firm:edit")
    @Log(title = "商家信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusinessFirm businessFirm)
    {
        return toAjax(businessFirmService.updateBusinessFirm(businessFirm));
    }

    /**
     * 删除商家信息
     */
    @RequiresPermissions("system:firm:remove")
    @Log(title = "商家信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(businessFirmService.deleteBusinessFirmByIds(ids));
    }
}
