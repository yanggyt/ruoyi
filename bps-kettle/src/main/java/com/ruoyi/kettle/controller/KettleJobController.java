package com.ruoyi.kettle.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.kettle.domain.KettleTrans;
import com.ruoyi.quartz.service.ISysJobService;
import com.ruoyi.system.service.ISysRoleService;
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
import com.ruoyi.kettle.domain.KettleJob;
import com.ruoyi.kettle.service.IKettleJobService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 作业调度Controller
 * 
 * @author kone
 * @date 2021-07-22
 */
@Controller
@RequestMapping("/kettle/job")
public class KettleJobController extends BaseController
{
    private String prefix = "kettle/job";

    @Autowired
    private IKettleJobService kettleJobService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysJobService jobService;
    @RequiresPermissions("kettle:job:view")
    @GetMapping()
    public String job()
    {
        return prefix + "/job";
    }

    /**
     * 查询作业调度列表
     */
    @RequiresPermissions("kettle:job:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KettleJob kettleJob)
    {
        startPage();
        List<KettleJob> list = kettleJobService.selectKettleJobList(kettleJob);
        return getDataTable(list);
    }

    /**
     * 导出作业调度列表
     */
    @RequiresPermissions("kettle:job:export")
    @Log(title = "作业调度", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KettleJob kettleJob)
    {
        List<KettleJob> list = kettleJobService.selectKettleJobList(kettleJob);
        ExcelUtil<KettleJob> util = new ExcelUtil<KettleJob>(KettleJob.class);
        return util.exportExcel(list, "作业调度数据");
    }

    /**
     * 新增作业调度
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        List<String> roleKeys=roleService.selectRoleAll().stream().map(SysRole::getRoleKey).collect(Collectors.toList());
        mmap.put("allRoles",roleKeys);
        return prefix + "/add";
    }

    /**
     * 新增保存作业调度
     */
    @RequiresPermissions("kettle:job:add")
    @Log(title = "作业调度", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KettleJob kettleJob)
    {
        return kettleJobService.insertKettleJob(kettleJob);
    }

    /**
     * 修改作业调度
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KettleJob kettleJob = kettleJobService.selectKettleJobById(id);
        List<String> roleKeys=roleService.selectRoleAll().stream().map(SysRole::getRoleKey).collect(Collectors.toList());
        String rks="";
        if(kettleJob!=null && kettleJob.getRoleKey()!=null){
            rks=kettleJob.getRoleKey();
        }
        String[] rkArray=rks.split(",");
        mmap.put("allRoles",roleKeys);
        mmap.put("rkArray",rkArray);
        mmap.put("kettleJob", kettleJob);
        return prefix + "/edit";
    }

    /**
     * 修改保存作业调度
     */
    @RequiresPermissions("kettle:job:edit")
    @Log(title = "作业调度", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KettleJob kettleJob)
    {
        return toAjax(kettleJobService.updateKettleJob(kettleJob));
    }

    /**
     * 删除作业调度
     */
    @RequiresPermissions("kettle:job:remove")
    @Log(title = "作业调度", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kettleJobService.deleteKettleJobByIds(ids));
    }


    @Log(title = "立即执行作业", businessType = BusinessType.UPDATE)
    @RequiresPermissions("kettle:job:run")
    @PostMapping("/run")
    @ResponseBody
    public AjaxResult run(KettleJob job)
    {
        AjaxResult result = kettleJobService.run(job);
        return result;
    }
    @RequiresPermissions("kettle:job:log")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        KettleJob kettleJob = kettleJobService.selectKettleJobById(id);
        List<String> jobLog= kettleJobService.queryJobLog(kettleJob);
        mmap.put("kettleJob", kettleJob);
        mmap.put("jobLog",jobLog);
        return prefix + "/detail";
    }
    /**
     * 跳转到新增调度定时任务页面
     */
    @RequiresPermissions("kettle:job:setquartz")
    @GetMapping("/jobQuartz/{id}")
    public String jobQuartz(@PathVariable("id") Long id,ModelMap mmap)
    {
        KettleJob kettleJob = kettleJobService.selectKettleJobById(id);
        //kettleTransServiceImpl.runTransQuartz('12','text')
        String checkStr="kettleJobServiceImpl.runJobQuartz('"+kettleJob.getId()+"','"+kettleJob.getJobName()+"')";
        Long jobId = kettleJobService.checkQuartzExist(checkStr);
        if(jobId != null){
            mmap.put("job", jobService.selectJobById(Long.valueOf(jobId)));
            return "kettle/quartz/editquartz";
        }else{
            mmap.put("invokeTarget", checkStr);
            //mmap.put("job", kettleJob);
            return "kettle/quartz/addquartz";
        }
    }
}
