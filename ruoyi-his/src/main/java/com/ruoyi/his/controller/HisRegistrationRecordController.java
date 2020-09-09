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
import com.ruoyi.his.domain.HisRegistrationRecord;
import com.ruoyi.his.service.IHisRegistrationRecordService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 挂号记录Controller
 * 
 * @author bend
 * @date 2020-06-28
 */
@Controller
@RequestMapping("/his/registration")
public class HisRegistrationRecordController extends BaseController
{
    private String prefix = "his/registration";

    @Autowired
    private IHisRegistrationRecordService hisRegistrationRecordService;

    @RequiresPermissions("his:registration:view")
    @GetMapping()
    public String registration()
    {
        return prefix + "/registration";
    }

    /**
     * 查询挂号记录列表
     */
    @RequiresPermissions("his:registration:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisRegistrationRecord hisRegistrationRecord)
    {
        startPage();
        List<HisRegistrationRecord> list = hisRegistrationRecordService.selectHisRegistrationRecordList(hisRegistrationRecord);
        return getDataTable(list);
    }

    /**
     * 导出挂号记录列表
     */
    @RequiresPermissions("his:registration:export")
    @Log(title = "挂号记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisRegistrationRecord hisRegistrationRecord)
    {
        List<HisRegistrationRecord> list = hisRegistrationRecordService.selectHisRegistrationRecordList(hisRegistrationRecord);
        ExcelUtil<HisRegistrationRecord> util = new ExcelUtil<HisRegistrationRecord>(HisRegistrationRecord.class);
        return util.exportExcel(list, "registration");
    }

    /**
     * 新增挂号记录
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存挂号记录
     */
    @RequiresPermissions("his:registration:add")
    @Log(title = "挂号记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisRegistrationRecord hisRegistrationRecord)
    {
        return toAjax(hisRegistrationRecordService.insertHisRegistrationRecord(hisRegistrationRecord));
    }

    /**
     * 修改挂号记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisRegistrationRecord hisRegistrationRecord = hisRegistrationRecordService.selectHisRegistrationRecordById(id);
        mmap.put("hisRegistrationRecord", hisRegistrationRecord);
        return prefix + "/edit";
    }

    /**
     * 修改保存挂号记录
     */
    @RequiresPermissions("his:registration:edit")
    @Log(title = "挂号记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisRegistrationRecord hisRegistrationRecord)
    {
        return toAjax(hisRegistrationRecordService.updateHisRegistrationRecord(hisRegistrationRecord));
    }

    /**
     * 删除挂号记录
     */
    @RequiresPermissions("his:registration:remove")
    @Log(title = "挂号记录", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisRegistrationRecordService.deleteHisRegistrationRecordByIds(ids));
    }
}
