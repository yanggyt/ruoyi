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
import com.ruoyi.his.domain.HisOutpatientPayment;
import com.ruoyi.his.service.IHisOutpatientPaymentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 门诊预交Controller
 * 
 * @author bend
 * @date 2020-07-14
 */
@Controller
@RequestMapping("/his/outpatientPayment")
public class HisOutpatientPaymentController extends BaseController
{
    private String prefix = "his/outpatientPayment";

    @Autowired
    private IHisOutpatientPaymentService hisOutpatientPaymentService;

    @RequiresPermissions("his:outpatientPayment:view")
    @GetMapping()
    public String outpatientPayment()
    {
        return prefix + "/outpatientPayment";
    }

    /**
     * 查询门诊预交列表
     */
    @RequiresPermissions("his:outpatientPayment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisOutpatientPayment hisOutpatientPayment)
    {
        startPage();
        List<HisOutpatientPayment> list = hisOutpatientPaymentService.selectHisOutpatientPaymentList(hisOutpatientPayment);
        return getDataTable(list);
    }

    /**
     * 导出门诊预交列表
     */
    @RequiresPermissions("his:outpatientPayment:export")
    @Log(title = "门诊预交", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisOutpatientPayment hisOutpatientPayment)
    {
        List<HisOutpatientPayment> list = hisOutpatientPaymentService.selectHisOutpatientPaymentList(hisOutpatientPayment);
        ExcelUtil<HisOutpatientPayment> util = new ExcelUtil<HisOutpatientPayment>(HisOutpatientPayment.class);
        return util.exportExcel(list, "outpatientPayment");
    }

    /**
     * 新增门诊预交
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存门诊预交
     */
    @RequiresPermissions("his:outpatientPayment:add")
    @Log(title = "门诊预交", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisOutpatientPayment hisOutpatientPayment)
    {
        return toAjax(hisOutpatientPaymentService.insertHisOutpatientPayment(hisOutpatientPayment));
    }

    /**
     * 修改门诊预交
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisOutpatientPayment hisOutpatientPayment = hisOutpatientPaymentService.selectHisOutpatientPaymentById(id);
        mmap.put("hisOutpatientPayment", hisOutpatientPayment);
        return prefix + "/edit";
    }

    /**
     * 修改保存门诊预交
     */
    @RequiresPermissions("his:outpatientPayment:edit")
    @Log(title = "门诊预交", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisOutpatientPayment hisOutpatientPayment)
    {
        return toAjax(hisOutpatientPaymentService.updateHisOutpatientPayment(hisOutpatientPayment));
    }

    /**
     * 删除门诊预交
     */
    @RequiresPermissions("his:outpatientPayment:remove")
    @Log(title = "门诊预交", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisOutpatientPaymentService.deleteHisOutpatientPaymentByIds(ids));
    }
}
