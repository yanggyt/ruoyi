package cn.com.infosouth.arj21.controller.schedule;

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
import cn.com.infosouth.common.annotation.Log;
import cn.com.infosouth.common.enums.BusinessType;
import cn.com.infosouth.arj21.domain.InfoDutySchedule;
import cn.com.infosouth.arj21.service.IInfoDutyScheduleService;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.common.core.domain.AjaxResult;
import cn.com.infosouth.common.utils.poi.ExcelUtil;
import cn.com.infosouth.common.core.page.TableDataInfo;

/**
 * 任务调度Controller
 * 
 * @author kxnf
 * @date 2020-03-05
 */
@Controller
@RequestMapping("/arj21/duty_schedule")
public class InfoDutyScheduleController extends BaseController
{
    private String prefix = "arj21/duty_schedule";

    @Autowired
    private IInfoDutyScheduleService infoDutyScheduleService;

    @RequiresPermissions("arj21:duty_schedule:view")
    @GetMapping()
    public String duty_schedule()
    {
        return prefix + "/duty_schedule";
    }

    /**
     * 查询任务调度列表
     */
    @RequiresPermissions("arj21:duty_schedule:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(InfoDutySchedule infoDutySchedule)
    {
        startPage();
        List<InfoDutySchedule> list = infoDutyScheduleService.selectInfoDutyScheduleList(infoDutySchedule);
        return getDataTable(list);
    }

    /**
     * 导出任务调度列表
     */
    @RequiresPermissions("arj21:duty_schedule:export")
    @Log(title = "任务调度", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InfoDutySchedule infoDutySchedule)
    {
        List<InfoDutySchedule> list = infoDutyScheduleService.selectInfoDutyScheduleList(infoDutySchedule);
        ExcelUtil<InfoDutySchedule> util = new ExcelUtil<InfoDutySchedule>(InfoDutySchedule.class);
        return util.exportExcel(list, "duty_schedule");
    }

    /**
     * 新增任务调度
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存任务调度
     */
    @RequiresPermissions("arj21:duty_schedule:add")
    @Log(title = "任务调度", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InfoDutySchedule infoDutySchedule)
    {
        return toAjax(infoDutyScheduleService.insertInfoDutySchedule(infoDutySchedule));
    }

    /**
     * 修改任务调度
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        InfoDutySchedule infoDutySchedule = infoDutyScheduleService.selectInfoDutyScheduleById(id);
        mmap.put("infoDutySchedule", infoDutySchedule);
        return prefix + "/edit";
    }

    /**
     * 修改保存任务调度
     */
    @RequiresPermissions("arj21:duty_schedule:edit")
    @Log(title = "任务调度", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InfoDutySchedule infoDutySchedule)
    {
        return toAjax(infoDutyScheduleService.updateInfoDutySchedule(infoDutySchedule));
    }

    /**
     * 删除任务调度
     */
    @RequiresPermissions("arj21:duty_schedule:remove")
    @Log(title = "任务调度", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(infoDutyScheduleService.deleteInfoDutyScheduleByIds(ids));
    }
}
