package com.ruoyi.web.controller;

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
import com.ruoyi.system.domain.WkCrmRecruitment;
import com.ruoyi.system.service.IWkCrmRecruitmentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 招聘职位Controller
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Controller
@RequestMapping("/system/recruitment")
public class WkCrmRecruitmentController extends BaseController
{
    private String prefix = "system/recruitment";

    @Autowired
    private IWkCrmRecruitmentService wkCrmRecruitmentService;

    @RequiresPermissions("system:recruitment:view")
    @GetMapping()
    public String recruitment()
    {
        return prefix + "/recruitment";
    }

    /**
     * 查询招聘职位列表
     */
    @RequiresPermissions("system:recruitment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WkCrmRecruitment wkCrmRecruitment)
    {
        startPage();
        List<WkCrmRecruitment> list = wkCrmRecruitmentService.selectWkCrmRecruitmentList(wkCrmRecruitment);
        return getDataTable(list);
    }

    /**
     * 导出招聘职位列表
     */
    @RequiresPermissions("system:recruitment:export")
    @Log(title = "招聘职位", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WkCrmRecruitment wkCrmRecruitment)
    {
        List<WkCrmRecruitment> list = wkCrmRecruitmentService.selectWkCrmRecruitmentList(wkCrmRecruitment);
        ExcelUtil<WkCrmRecruitment> util = new ExcelUtil<WkCrmRecruitment>(WkCrmRecruitment.class);
        return util.exportExcel(list, "recruitment");
    }

    /**
     * 新增招聘职位
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存招聘职位
     */
    @RequiresPermissions("system:recruitment:add")
    @Log(title = "招聘职位", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WkCrmRecruitment wkCrmRecruitment)
    {
        return toAjax(wkCrmRecruitmentService.insertWkCrmRecruitment(wkCrmRecruitment));
    }

    /**
     * 修改招聘职位
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        WkCrmRecruitment wkCrmRecruitment = wkCrmRecruitmentService.selectWkCrmRecruitmentById(id);
        mmap.put("wkCrmRecruitment", wkCrmRecruitment);
        return prefix + "/edit";
    }

    /**
     * 修改保存招聘职位
     */
    @RequiresPermissions("system:recruitment:edit")
    @Log(title = "招聘职位", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WkCrmRecruitment wkCrmRecruitment)
    {
        return toAjax(wkCrmRecruitmentService.updateWkCrmRecruitment(wkCrmRecruitment));
    }

    /**
     * 删除招聘职位
     */
    @RequiresPermissions("system:recruitment:remove")
    @Log(title = "招聘职位", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wkCrmRecruitmentService.deleteWkCrmRecruitmentByIds(ids));
    }
}
