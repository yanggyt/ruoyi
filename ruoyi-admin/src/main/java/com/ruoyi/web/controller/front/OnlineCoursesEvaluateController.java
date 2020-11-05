package com.ruoyi.front.controller;

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
import com.ruoyi.front.domain.OnlineCoursesEvaluate;
import com.ruoyi.front.service.IOnlineCoursesEvaluateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 线上课程评价Controller
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
@Controller
@RequestMapping("/front/evaluate")
public class OnlineCoursesEvaluateController extends BaseController
{
    private String prefix = "front/evaluate";

    @Autowired
    private IOnlineCoursesEvaluateService onlineCoursesEvaluateService;

    @RequiresPermissions("front:evaluate:view")
    @GetMapping()
    public String evaluate()
    {
        return prefix + "/evaluate";
    }

    /**
     * 查询线上课程评价列表
     */
    @RequiresPermissions("front:evaluate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OnlineCoursesEvaluate onlineCoursesEvaluate)
    {
        startPage();
        List<OnlineCoursesEvaluate> list = onlineCoursesEvaluateService.selectOnlineCoursesEvaluateList(onlineCoursesEvaluate);
        return getDataTable(list);
    }

    /**
     * 导出线上课程评价列表
     */
    @RequiresPermissions("front:evaluate:export")
    @Log(title = "线上课程评价", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OnlineCoursesEvaluate onlineCoursesEvaluate)
    {
        List<OnlineCoursesEvaluate> list = onlineCoursesEvaluateService.selectOnlineCoursesEvaluateList(onlineCoursesEvaluate);
        ExcelUtil<OnlineCoursesEvaluate> util = new ExcelUtil<OnlineCoursesEvaluate>(OnlineCoursesEvaluate.class);
        return util.exportExcel(list, "evaluate");
    }

    /**
     * 新增线上课程评价
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存线上课程评价
     */
    @RequiresPermissions("front:evaluate:add")
    @Log(title = "线上课程评价", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OnlineCoursesEvaluate onlineCoursesEvaluate)
    {
        return toAjax(onlineCoursesEvaluateService.insertOnlineCoursesEvaluate(onlineCoursesEvaluate));
    }

    /**
     * 修改线上课程评价
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OnlineCoursesEvaluate onlineCoursesEvaluate = onlineCoursesEvaluateService.selectOnlineCoursesEvaluateById(id);
        mmap.put("onlineCoursesEvaluate", onlineCoursesEvaluate);
        return prefix + "/edit";
    }

    /**
     * 修改保存线上课程评价
     */
    @RequiresPermissions("front:evaluate:edit")
    @Log(title = "线上课程评价", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OnlineCoursesEvaluate onlineCoursesEvaluate)
    {
        return toAjax(onlineCoursesEvaluateService.updateOnlineCoursesEvaluate(onlineCoursesEvaluate));
    }

    /**
     * 删除线上课程评价
     */
    @RequiresPermissions("front:evaluate:remove")
    @Log(title = "线上课程评价", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(onlineCoursesEvaluateService.deleteOnlineCoursesEvaluateByIds(ids));
    }
}
