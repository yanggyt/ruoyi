package com.sinosoft.activity.controller;

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
import com.sinosoft.activity.domain.DrawTaskNotify;
import com.sinosoft.activity.service.IDrawTaskNotifyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动次数记录信息Controller
 * 
 * @author dy
 * @date 2021-03-26
 */
@Controller
@RequestMapping("/activity/DrawTaskNotify")
public class DrawTaskNotifyController extends BaseController
{
    private String prefix = "activity/DrawTaskNotify";

    @Autowired
    private IDrawTaskNotifyService drawTaskNotifyService;

    @RequiresPermissions("activity:DrawTaskNotify:view")
    @GetMapping()
    public String DrawTaskNotify()
    {
        return prefix + "/DrawTaskNotify";
    }

    /**
     * 查询活动次数记录信息列表
     */
    @RequiresPermissions("activity:DrawTaskNotify:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(DrawTaskNotify drawTaskNotify)
    {
        startPage();
        List<DrawTaskNotify> list = drawTaskNotifyService.selectDrawTaskNotifyList(drawTaskNotify);
        return getDataTable(list);
    }

    /**
     * 导出活动次数记录信息列表
     */
    @RequiresPermissions("activity:DrawTaskNotify:export")
    @Log(title = "活动次数记录信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(DrawTaskNotify drawTaskNotify)
    {
        List<DrawTaskNotify> list = drawTaskNotifyService.selectDrawTaskNotifyList(drawTaskNotify);
        ExcelUtil<DrawTaskNotify> util = new ExcelUtil<DrawTaskNotify>(DrawTaskNotify.class);
        return util.exportExcel(list, "DrawTaskNotify");
    }

    /**
     * 新增活动次数记录信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存活动次数记录信息
     */
    @RequiresPermissions("activity:DrawTaskNotify:add")
    @Log(title = "活动次数记录信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(DrawTaskNotify drawTaskNotify)
    {
        return toAjax(drawTaskNotifyService.insertDrawTaskNotify(drawTaskNotify));
    }

    /**
     * 修改活动次数记录信息
     */
    @GetMapping("/edit/{USERID}")
    public String edit(@PathVariable("USERID") String USERID, ModelMap mmap)
    {
        DrawTaskNotify drawTaskNotify = drawTaskNotifyService.selectDrawTaskNotifyById(USERID);
        mmap.put("drawTaskNotify", drawTaskNotify);
        return prefix + "/edit";
    }

    /**
     * 修改保存活动次数记录信息
     */
    @RequiresPermissions("activity:DrawTaskNotify:edit")
    @Log(title = "活动次数记录信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(DrawTaskNotify drawTaskNotify)
    {
        return toAjax(drawTaskNotifyService.updateDrawTaskNotify(drawTaskNotify));
    }

    /**
     * 删除活动次数记录信息
     */
    @RequiresPermissions("activity:DrawTaskNotify:remove")
    @Log(title = "活动次数记录信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(drawTaskNotifyService.deleteDrawTaskNotifyByIds(ids));
    }
}
