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
import com.ruoyi.system.domain.WkCrmCandidate;
import com.ruoyi.system.service.IWkCrmCandidateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 候选人Controller
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Controller
@RequestMapping("/system/candidate")
public class WkCrmCandidateController extends BaseController
{
    private String prefix = "system/candidate";

    @Autowired
    private IWkCrmCandidateService wkCrmCandidateService;

    @RequiresPermissions("system:candidate:view")
    @GetMapping()
    public String candidate()
    {
        return prefix + "/candidate";
    }

    /**
     * 查询候选人列表
     */
    @RequiresPermissions("system:candidate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WkCrmCandidate wkCrmCandidate)
    {
        startPage();
        List<WkCrmCandidate> list = wkCrmCandidateService.selectWkCrmCandidateList(wkCrmCandidate);
        return getDataTable(list);
    }

    /**
     * 导出候选人列表
     */
    @RequiresPermissions("system:candidate:export")
    @Log(title = "候选人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WkCrmCandidate wkCrmCandidate)
    {
        List<WkCrmCandidate> list = wkCrmCandidateService.selectWkCrmCandidateList(wkCrmCandidate);
        ExcelUtil<WkCrmCandidate> util = new ExcelUtil<WkCrmCandidate>(WkCrmCandidate.class);
        return util.exportExcel(list, "candidate");
    }

    /**
     * 新增候选人
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存候选人
     */
    @RequiresPermissions("system:candidate:add")
    @Log(title = "候选人", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WkCrmCandidate wkCrmCandidate)
    {
        return toAjax(wkCrmCandidateService.insertWkCrmCandidate(wkCrmCandidate));
    }

    /**
     * 修改候选人
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Integer id, ModelMap mmap)
    {
        WkCrmCandidate wkCrmCandidate = wkCrmCandidateService.selectWkCrmCandidateById(id);
        mmap.put("wkCrmCandidate", wkCrmCandidate);
        return prefix + "/edit";
    }

    /**
     * 修改保存候选人
     */
    @RequiresPermissions("system:candidate:edit")
    @Log(title = "候选人", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WkCrmCandidate wkCrmCandidate)
    {
        return toAjax(wkCrmCandidateService.updateWkCrmCandidate(wkCrmCandidate));
    }

    /**
     * 删除候选人
     */
    @RequiresPermissions("system:candidate:remove")
    @Log(title = "候选人", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wkCrmCandidateService.deleteWkCrmCandidateByIds(ids));
    }
}
