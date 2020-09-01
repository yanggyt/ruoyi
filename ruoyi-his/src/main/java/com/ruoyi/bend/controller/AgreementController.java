package com.ruoyi.bend.controller;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
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
import com.ruoyi.bend.domain.Agreement;
import com.ruoyi.bend.service.IAgreementService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 协议管理Controller
 * 
 * @author bend
 * @date 2020-08-30
 */
@Controller
@RequestMapping("/bend/agreement")
public class AgreementController extends BaseController
{
    private String prefix = "bend/agreement";

    @Autowired
    private IAgreementService agreementService;

    @RequiresPermissions("bend:agreement:view")
    @GetMapping()
    public String agreement()
    {
        return prefix + "/agreement";
    }

    /**
     * 查询协议管理列表
     */
    @RequiresPermissions("bend:agreement:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Agreement agreement)
    {
        startPage();
        List<Agreement> list = agreementService.selectAgreementList(agreement);
        return getDataTable(list);
    }

    /**
     * 导出协议管理列表
     */
    @RequiresPermissions("bend:agreement:export")
    @Log(title = "协议管理", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Agreement agreement)
    {
        List<Agreement> list = agreementService.selectAgreementList(agreement);
        ExcelUtil<Agreement> util = new ExcelUtil<Agreement>(Agreement.class);
        return util.exportExcel(list, "agreement");
    }

    /**
     * 新增协议管理
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存协议管理
     */
    @RequiresPermissions("bend:agreement:add")
    @Log(title = "协议管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Agreement agreement)
    {
        String operName = ShiroUtils.getSysUser().getLoginName();
        agreement.setCreateBy(operName);
        return toAjax(agreementService.insertAgreement(agreement));
    }

    /**
     * 修改协议管理
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Agreement agreement = agreementService.selectAgreementById(id);
        mmap.put("agreement", agreement);
        return prefix + "/edit";
    }

    /**
     * 修改保存协议管理
     */
    @RequiresPermissions("bend:agreement:edit")
    @Log(title = "协议管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Agreement agreement)
    {
        String operName = ShiroUtils.getSysUser().getLoginName();
        agreement.setCreateBy(operName);
        return toAjax(agreementService.updateAgreement(agreement));
    }

    /**
     * 删除协议管理
     */
    @RequiresPermissions("bend:agreement:remove")
    @Log(title = "协议管理", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(agreementService.deleteAgreementByIds(ids));
    }
}
