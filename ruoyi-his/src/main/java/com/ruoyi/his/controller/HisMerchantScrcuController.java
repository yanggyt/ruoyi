package com.ruoyi.his.controller;

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
import com.ruoyi.his.domain.HisMerchantScrcu;
import com.ruoyi.his.service.IHisMerchantScrcuService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 农信商户Controller
 *
 * @author bend
 * @date 2020-07-27
 */
@Controller
@RequestMapping("/his/merchantScrcu")
public class HisMerchantScrcuController extends BaseController
{
    private String prefix = "his/merchantScrcu";

    @Autowired
    private IHisMerchantScrcuService hisMerchantScrcuService;

    @RequiresPermissions("his:merchantScrcu:view")
    @GetMapping()
    public String merchantScrcu()
    {
        return prefix + "/merchantScrcu";
    }

    /**
     * 查询农信商户列表
     */
    @RequiresPermissions("his:merchantScrcu:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisMerchantScrcu hisMerchantScrcu)
    {
        startPage();
        List<HisMerchantScrcu> list = hisMerchantScrcuService.selectHisMerchantScrcuList(hisMerchantScrcu);
        return getDataTable(list);
    }

    /**
     * 导出农信商户列表
     */
    @RequiresPermissions("his:merchantScrcu:export")
    @Log(title = "农信商户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisMerchantScrcu hisMerchantScrcu)
    {
        List<HisMerchantScrcu> list = hisMerchantScrcuService.selectHisMerchantScrcuList(hisMerchantScrcu);
        ExcelUtil<HisMerchantScrcu> util = new ExcelUtil<HisMerchantScrcu>(HisMerchantScrcu.class);
        return util.exportExcel(list, "merchantScrcu");
    }

    /**
     * 新增农信商户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存农信商户
     */
    @RequiresPermissions("his:merchantScrcu:add")
    @Log(title = "农信商户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisMerchantScrcu hisMerchantScrcu)
    {
        return toAjax(hisMerchantScrcuService.insertHisMerchantScrcu(hisMerchantScrcu));
    }

    /**
     * 修改农信商户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisMerchantScrcu hisMerchantScrcu = hisMerchantScrcuService.selectHisMerchantScrcuById(id);
        mmap.put("hisMerchantScrcu", hisMerchantScrcu);
        return prefix + "/edit";
    }

    /**
     * 修改保存农信商户
     */
    @RequiresPermissions("his:merchantScrcu:edit")
    @Log(title = "农信商户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisMerchantScrcu hisMerchantScrcu)
    {
        return toAjax(hisMerchantScrcuService.updateHisMerchantScrcu(hisMerchantScrcu));
    }

    /**
     * 删除农信商户
     */
    @RequiresPermissions("his:merchantScrcu:remove")
    @Log(title = "农信商户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisMerchantScrcuService.deleteHisMerchantScrcuByIds(ids));
    }
}
