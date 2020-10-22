package com.ruoyi.web.controller.front;

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
import com.ruoyi.front.domain.CommonProblem;
import com.ruoyi.front.service.ICommonProblemService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 常见问题Controller
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Controller
@RequestMapping("/front/problem")
public class CommonProblemController extends BaseController
{
    private String prefix = "front/problem";

    @Autowired
    private ICommonProblemService commonProblemService;

    @RequiresPermissions("front:problem:view")
    @GetMapping()
    public String problem()
    {
        return prefix + "/problem";
    }

    /**
     * 查询常见问题列表
     */
    @RequiresPermissions("front:problem:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CommonProblem commonProblem)
    {
        startPage();
        List<CommonProblem> list = commonProblemService.selectCommonProblemList(commonProblem);
        return getDataTable(list);
    }

    /**
     * 导出常见问题列表
     */
    @RequiresPermissions("front:problem:export")
    @Log(title = "常见问题", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CommonProblem commonProblem)
    {
        List<CommonProblem> list = commonProblemService.selectCommonProblemList(commonProblem);
        ExcelUtil<CommonProblem> util = new ExcelUtil<CommonProblem>(CommonProblem.class);
        return util.exportExcel(list, "problem");
    }

    /**
     * 新增常见问题
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存常见问题
     */
    @RequiresPermissions("front:problem:add")
    @Log(title = "常见问题", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CommonProblem commonProblem)
    {
        return toAjax(commonProblemService.insertCommonProblem(commonProblem));
    }

    /**
     * 修改常见问题
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        CommonProblem commonProblem = commonProblemService.selectCommonProblemById(id);
        mmap.put("commonProblem", commonProblem);
        return prefix + "/edit";
    }

    /**
     * 修改保存常见问题
     */
    @RequiresPermissions("front:problem:edit")
    @Log(title = "常见问题", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CommonProblem commonProblem)
    {
        return toAjax(commonProblemService.updateCommonProblem(commonProblem));
    }

    /**
     * 删除常见问题
     */
    @RequiresPermissions("front:problem:remove")
    @Log(title = "常见问题", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(commonProblemService.deleteCommonProblemByIds(ids));
    }
}
