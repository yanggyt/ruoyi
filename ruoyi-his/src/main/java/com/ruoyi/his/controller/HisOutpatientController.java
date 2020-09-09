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
import com.ruoyi.his.domain.HisOutpatient;
import com.ruoyi.his.service.IHisOutpatientService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 门诊病人Controller
 * 
 * @author bend
 * @date 2020-07-07
 */
@Controller
@RequestMapping("/his/outpatient")
public class HisOutpatientController extends BaseController
{
    private String prefix = "his/outpatient";

    @Autowired
    private IHisOutpatientService hisOutpatientService;

    @RequiresPermissions("his:outpatient:view")
    @GetMapping()
    public String outpatient()
    {
        return prefix + "/outpatient";
    }

    /**
     * 查询门诊病人列表
     */
    @RequiresPermissions("his:outpatient:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisOutpatient hisOutpatient)
    {
        startPage();
        List<HisOutpatient> list = hisOutpatientService.selectHisOutpatientList(hisOutpatient);
        return getDataTable(list);
    }

    /**
     * 导出门诊病人列表
     */
    @RequiresPermissions("his:outpatient:export")
    @Log(title = "门诊病人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisOutpatient hisOutpatient)
    {
        List<HisOutpatient> list = hisOutpatientService.selectHisOutpatientList(hisOutpatient);
        ExcelUtil<HisOutpatient> util = new ExcelUtil<HisOutpatient>(HisOutpatient.class);
        return util.exportExcel(list, "outpatient");
    }

    /**
     * 新增门诊病人
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存门诊病人
     */
    @RequiresPermissions("his:outpatient:add")
    @Log(title = "门诊病人", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisOutpatient hisOutpatient)
    {
        return toAjax(hisOutpatientService.insertHisOutpatient(hisOutpatient));
    }

    /**
     * 修改门诊病人
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisOutpatient hisOutpatient = hisOutpatientService.selectHisOutpatientById(id);
        mmap.put("hisOutpatient", hisOutpatient);
        return prefix + "/edit";
    }

    /**
     * 修改保存门诊病人
     */
    @RequiresPermissions("his:outpatient:edit")
    @Log(title = "门诊病人", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisOutpatient hisOutpatient)
    {
        return toAjax(hisOutpatientService.updateHisOutpatient(hisOutpatient));
    }

    /**
     * 删除门诊病人
     */
    @RequiresPermissions("his:outpatient:remove")
    @Log(title = "门诊病人", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisOutpatientService.deleteHisOutpatientByIds(ids));
    }
}
