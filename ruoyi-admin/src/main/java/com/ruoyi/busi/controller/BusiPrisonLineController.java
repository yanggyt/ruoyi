package com.ruoyi.busi.controller;

import java.util.List;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.busi.domain.BusiPrisonLine;
import com.ruoyi.busi.service.IBusiPrisonLineService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 监区产线Controller
 * 
 * @author WangCL
 * @date 2021-12-17
 */
@Controller
@RequestMapping("/busi/prisonLine")
public class BusiPrisonLineController extends BaseController
{
    private String prefix = "busi/prisonLine";

    @Autowired
    private IBusiPrisonLineService busiPrisonLineService;

    @RequiresPermissions("busi:prisonLine:view")
    @GetMapping()
    public String prisonLine()
    {
        return prefix + "/prisonLine";
    }

    /**
     * 查询监区产线树列表
     */
    @RequiresPermissions("busi:prisonLine:list")
    @PostMapping("/list")
    @ResponseBody
    public List<BusiPrisonLine> list(BusiPrisonLine busiPrisonLine)
    {
        List<BusiPrisonLine> list = busiPrisonLineService.selectBusiPrisonLineList(busiPrisonLine);
        return list;
    }

    /**
     * 导出监区产线列表
     */
    @RequiresPermissions("busi:prisonLine:export")
    @Log(title = "监区产线", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiPrisonLine busiPrisonLine)
    {
        List<BusiPrisonLine> list = busiPrisonLineService.selectBusiPrisonLineList(busiPrisonLine);
        ExcelUtil<BusiPrisonLine> util = new ExcelUtil<BusiPrisonLine>(BusiPrisonLine.class);
        return util.exportExcel(list, "监区产线数据");
    }

    /**
     * 新增监区产线
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("busiPrisonLine", busiPrisonLineService.selectBusiPrisonLineById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存监区产线
     */
    @RequiresPermissions("busi:prisonLine:add")
    @Log(title = "监区产线", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiPrisonLine busiPrisonLine)
    {
        return toAjax(busiPrisonLineService.insertBusiPrisonLine(busiPrisonLine));
    }

    /**
     * 修改监区产线
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BusiPrisonLine busiPrisonLine = busiPrisonLineService.selectBusiPrisonLineById(id);
        mmap.put("busiPrisonLine", busiPrisonLine);
        return prefix + "/edit";
    }

    /**
     * 修改保存监区产线
     */
    @RequiresPermissions("busi:prisonLine:edit")
    @Log(title = "监区产线", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiPrisonLine busiPrisonLine)
    {
        return toAjax(busiPrisonLineService.updateBusiPrisonLine(busiPrisonLine));
    }

    /**
     * 删除
     */
    @RequiresPermissions("busi:prisonLine:remove")
    @Log(title = "监区产线", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(busiPrisonLineService.deleteBusiPrisonLineById(id));
    }

    /**
     * 选择监区产线树
     */
    @GetMapping(value = { "/selectPrisonLineTree/{id}", "/selectPrisonLineTree/" })
    public String selectPrisonLineTree(@PathVariable(value = "id", required = false) Long id, @RequestParam(name = "JCOnly",required = false) String JCOnly, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("busiPrisonLine", busiPrisonLineService.selectBusiPrisonLineById(id));
        }
        mmap.put("JCOnly", JCOnly);
        return prefix + "/tree";
    }

    /**
     * 加载监区产线树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData(@RequestParam(name = "JCOnly",required = false) String JCOnly)
    {
        System.out.println(JCOnly);
        List<Ztree> ztrees = busiPrisonLineService.selectBusiPrisonLineTree(JCOnly);
        return ztrees;
    }
}
