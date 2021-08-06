package com.ruoyi.kettle.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.entity.SysRole;
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
import com.ruoyi.kettle.domain.KettleTrans;
import com.ruoyi.kettle.service.IKettleTransService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 转换Controller
 * 
 * @author kone
 * @date 2021-07-14
 */
@Controller
@RequestMapping("/kettle/trans")
public class KettleTransController extends BaseController
{
    private String prefix = "kettle/trans";

    @Autowired
    private IKettleTransService kettleTransService;
    @Autowired
    private ISysRoleService roleService;
    @Autowired
    private ISysJobService jobService;
    @RequiresPermissions("kettle:trans:view")
    @GetMapping()
    public String trans()
    {
        return prefix + "/trans";
    }

    /**
     * 查询转换列表
     */
    @RequiresPermissions("kettle:trans:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(KettleTrans kettleTrans)
    {
        startPage();
        List<KettleTrans> list = kettleTransService.selectKettleTransList(kettleTrans);
        return getDataTable(list);
    }

    /**
     * 导出转换列表
     */
    @RequiresPermissions("kettle:trans:export")
    @Log(title = "转换", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(KettleTrans kettleTrans)
    {
        List<KettleTrans> list = kettleTransService.selectKettleTransList(kettleTrans);
        ExcelUtil<KettleTrans> util = new ExcelUtil<KettleTrans>(KettleTrans.class);
        return util.exportExcel(list, "转换数据");
    }

    /**
     * 新增转换
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        List<String> roleKeys=roleService.selectRoleAll().stream().map(SysRole::getRoleKey).collect(Collectors.toList());
        mmap.put("allRoles",roleKeys);
        return prefix + "/add";
    }

    /**
     * 新增保存转换
     */
    @RequiresPermissions("kettle:trans:add")
    @Log(title = "转换", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(KettleTrans kettleTrans)
    {
        return  kettleTransService.insertKettleTrans(kettleTrans) ;
    }

    /**
     * 修改转换
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        KettleTrans kettleTrans = kettleTransService.selectKettleTransById(id);
        List<String> roleKeys=roleService.selectRoleAll().stream().map(SysRole::getRoleKey).collect(Collectors.toList());
        String rks="";
        if(kettleTrans!=null && kettleTrans.getRoleKey()!=null){
            rks=kettleTrans.getRoleKey();
        }
        String[] rkArray=rks.split(",");
        mmap.put("allRoles",roleKeys);
        mmap.put("kettleTrans", kettleTrans);
        mmap.put("rkArray",rkArray);
        return prefix + "/edit";
    }

    /**
     * 修改保存转换
     */
    @RequiresPermissions("kettle:trans:edit")
    @Log(title = "转换", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(KettleTrans kettleTrans)
    {
        return toAjax(kettleTransService.updateKettleTrans(kettleTrans));
    }

    /**
     * 删除转换
     */
    @RequiresPermissions("kettle:trans:remove")
    @Log(title = "转换", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(kettleTransService.deleteKettleTransByIds(ids));
    }

    /**
     * 转换执行得日志
     */
    @RequiresPermissions("kettle:trans:log")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        KettleTrans kettleTrans = kettleTransService.selectKettleTransById(id);
        List<String> transLog= kettleTransService.queryTransLog(kettleTrans);
        mmap.put("kettleTrans", kettleTrans);
        mmap.put("transLog",transLog);
        return prefix + "/detail";
    }
    /**
     * 转换立即执行一次
     */
    @Log(title = "立即执行转换", businessType = BusinessType.UPDATE)
    @RequiresPermissions("kettle:trans:run")
    @PostMapping("/run")
    @ResponseBody
    public AjaxResult runToQueue(KettleTrans trans)
    {
        AjaxResult result = kettleTransService.runToQueue(trans);
        return result;
    }


    /**
     * 跳转到新增调度定时任务页面
     */
    @RequiresPermissions("kettle:trans:setquartz")
    @GetMapping("/transQuartz/{id}")
    public String transQuartz(@PathVariable("id") Long id,ModelMap mmap)
    {
        KettleTrans trans = kettleTransService.selectKettleTransById(id);
        //kettleTransServiceImpl.runTransQuartz('12','text')
        String checkStr="kettleTransServiceImpl.runTransQuartz('"+trans.getId()+"','"+trans.getTransName()+"')";
        Long jobId = kettleTransService.checkQuartzExist(checkStr);
        if(jobId != null){
            mmap.put("job", jobService.selectJobById(Long.valueOf(jobId)));
            return "kettle/quartz/editquartz";
        }else{

            mmap.put("invokeTarget", checkStr);
            //mmap.put("trans", trans);
            return "kettle/quartz/addquartz";
        }
    }



}
