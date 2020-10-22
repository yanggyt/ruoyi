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
import com.ruoyi.front.domain.EventRecruitment;
import com.ruoyi.front.service.IEventRecruitmentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 活动招募Controller
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Controller
@RequestMapping("/front/recruitment")
public class EventRecruitmentController extends BaseController
{
    private String prefix = "front/recruitment";

    @Autowired
    private IEventRecruitmentService eventRecruitmentService;

    @RequiresPermissions("front:recruitment:view")
    @GetMapping()
    public String recruitment()
    {
        return prefix + "/recruitment";
    }

    /**
     * 查询活动招募列表
     */
    @RequiresPermissions("front:recruitment:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(EventRecruitment eventRecruitment)
    {
        startPage();
        List<EventRecruitment> list = eventRecruitmentService.selectEventRecruitmentList(eventRecruitment);
        return getDataTable(list);
    }

    /**
     * 导出活动招募列表
     */
    @RequiresPermissions("front:recruitment:export")
    @Log(title = "活动招募", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(EventRecruitment eventRecruitment)
    {
        List<EventRecruitment> list = eventRecruitmentService.selectEventRecruitmentList(eventRecruitment);
        ExcelUtil<EventRecruitment> util = new ExcelUtil<EventRecruitment>(EventRecruitment.class);
        return util.exportExcel(list, "recruitment");
    }

    /**
     * 新增活动招募
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存活动招募
     */
    @RequiresPermissions("front:recruitment:add")
    @Log(title = "活动招募", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(EventRecruitment eventRecruitment)
    {
        return toAjax(eventRecruitmentService.insertEventRecruitment(eventRecruitment));
    }

    /**
     * 修改活动招募
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        EventRecruitment eventRecruitment = eventRecruitmentService.selectEventRecruitmentById(id);
        mmap.put("eventRecruitment", eventRecruitment);
        return prefix + "/edit";
    }

    /**
     * 修改保存活动招募
     */
    @RequiresPermissions("front:recruitment:edit")
    @Log(title = "活动招募", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(EventRecruitment eventRecruitment)
    {
        return toAjax(eventRecruitmentService.updateEventRecruitment(eventRecruitment));
    }

    /**
     * 删除活动招募
     */
    @RequiresPermissions("front:recruitment:remove")
    @Log(title = "活动招募", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(eventRecruitmentService.deleteEventRecruitmentByIds(ids));
    }
}
