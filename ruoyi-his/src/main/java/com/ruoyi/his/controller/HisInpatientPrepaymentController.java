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
import com.ruoyi.his.domain.HisInpatientPrepayment;
import com.ruoyi.his.service.IHisInpatientPrepaymentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 住院预交Controller
 * 
 * @author bend
 * @date 2020-07-14
 */
@Controller
@RequestMapping("/his/inpatientPrepayment")
public class HisInpatientPrepaymentController extends BaseController
{
    private String prefix = "his/inpatientPrepayment";

    @Autowired
    private IHisInpatientPrepaymentService hisInpatientPrepaymentService;

    @RequiresPermissions("his:inpatientPrepayment:view")
    @GetMapping()
    public String inpatientPrepayment()
    {
        return prefix + "/inpatientPrepayment";
    }

    /**
     * 查询住院预交列表
     */
    @RequiresPermissions("his:inpatientPrepayment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisInpatientPrepayment hisInpatientPrepayment)
    {
        startPage();
        List<HisInpatientPrepayment> list = hisInpatientPrepaymentService.selectHisInpatientPrepaymentList(hisInpatientPrepayment);
        return getDataTable(list);
    }

    /**
     * 导出住院预交列表
     */
    @RequiresPermissions("his:inpatientPrepayment:export")
    @Log(title = "住院预交", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisInpatientPrepayment hisInpatientPrepayment)
    {
        List<HisInpatientPrepayment> list = hisInpatientPrepaymentService.selectHisInpatientPrepaymentList(hisInpatientPrepayment);
        ExcelUtil<HisInpatientPrepayment> util = new ExcelUtil<HisInpatientPrepayment>(HisInpatientPrepayment.class);
        return util.exportExcel(list, "inpatientPrepayment");
    }

    /**
     * 新增住院预交
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存住院预交
     */
    @RequiresPermissions("his:inpatientPrepayment:add")
    @Log(title = "住院预交", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisInpatientPrepayment hisInpatientPrepayment)
    {
        return toAjax(hisInpatientPrepaymentService.insertHisInpatientPrepayment(hisInpatientPrepayment));
    }

    /**
     * 修改住院预交
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisInpatientPrepayment hisInpatientPrepayment = hisInpatientPrepaymentService.selectHisInpatientPrepaymentById(id);
        mmap.put("hisInpatientPrepayment", hisInpatientPrepayment);
        return prefix + "/edit";
    }

    /**
     * 修改保存住院预交
     */
    @RequiresPermissions("his:inpatientPrepayment:edit")
    @Log(title = "住院预交", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisInpatientPrepayment hisInpatientPrepayment)
    {
        return toAjax(hisInpatientPrepaymentService.updateHisInpatientPrepayment(hisInpatientPrepayment));
    }

    /**
     * 删除住院预交
     */
    @RequiresPermissions("his:inpatientPrepayment:remove")
    @Log(title = "住院预交", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisInpatientPrepaymentService.deleteHisInpatientPrepaymentByIds(ids));
    }
}
