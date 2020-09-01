package com.ruoyi.web.controller.his;

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
import com.ruoyi.his.domain.HisInpatient;
import com.ruoyi.his.service.IHisInpatientService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 住院病人Controller
 * 
 * @author bend
 * @date 2020-07-08
 */
@Controller
@RequestMapping("/his/inpatient")
public class HisInpatientController extends BaseController
{
    private String prefix = "his/inpatient";

    @Autowired
    private IHisInpatientService hisInpatientService;

    @RequiresPermissions("his:inpatient:view")
    @GetMapping()
    public String inpatient()
    {
        return prefix + "/inpatient";
    }

    /**
     * 查询住院病人列表
     */
    @RequiresPermissions("his:inpatient:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisInpatient hisInpatient)
    {
        startPage();
        List<HisInpatient> list = hisInpatientService.selectHisInpatientList(hisInpatient);
        return getDataTable(list);
    }

    /**
     * 导出住院病人列表
     */
    @RequiresPermissions("his:inpatient:export")
    @Log(title = "住院病人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisInpatient hisInpatient)
    {
        List<HisInpatient> list = hisInpatientService.selectHisInpatientList(hisInpatient);
        ExcelUtil<HisInpatient> util = new ExcelUtil<HisInpatient>(HisInpatient.class);
        return util.exportExcel(list, "inpatient");
    }

    /**
     * 新增住院病人
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存住院病人
     */
    @RequiresPermissions("his:inpatient:add")
    @Log(title = "住院病人", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisInpatient hisInpatient)
    {
        return toAjax(hisInpatientService.insertHisInpatient(hisInpatient));
    }

    /**
     * 修改住院病人
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisInpatient hisInpatient = hisInpatientService.selectHisInpatientById(id);
        mmap.put("hisInpatient", hisInpatient);
        return prefix + "/edit";
    }

    /**
     * 修改保存住院病人
     */
    @RequiresPermissions("his:inpatient:edit")
    @Log(title = "住院病人", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisInpatient hisInpatient)
    {
        return toAjax(hisInpatientService.updateHisInpatient(hisInpatient));
    }

    /**
     * 删除住院病人
     */
    @RequiresPermissions("his:inpatient:remove")
    @Log(title = "住院病人", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisInpatientService.deleteHisInpatientByIds(ids));
    }
}
