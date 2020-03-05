package cn.com.infosouth.arj21.controller.csvmanager;

 

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

import cn.com.infosouth.arj21.domain.InfoImexportLog;
import cn.com.infosouth.arj21.service.IInfoImexportLogService;
import cn.com.infosouth.common.annotation.Log;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.common.core.domain.AjaxResult;
import cn.com.infosouth.common.core.page.TableDataInfo;
import cn.com.infosouth.common.enums.BusinessType;
import cn.com.infosouth.common.utils.poi.ExcelUtil;

/**
 * 导入导出日志Controller
 * 
 * @author kxnf
 * @date 2020-03-05
 */
@Controller
@RequestMapping("/arj21/csvexport")
public class InfoImexportLogController extends BaseController
{
    private String prefix = "arj21/csvmanager/log";

    @Autowired
    private IInfoImexportLogService infoImexportLogService;

    @RequiresPermissions("arj21:csvexport:view")
    @GetMapping()
    public String csvexport()
    {
        return prefix + "/csvexport";
    }

    /**
     * 查询导入导出日志列表
     */
    @RequiresPermissions("arj21:csvexport:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(InfoImexportLog infoImexportLog)
    {
        startPage();
        List<InfoImexportLog> list = infoImexportLogService.selectInfoImexportLogList(infoImexportLog);
        return getDataTable(list);
    }

    /**
     * 导出导入导出日志列表
     */
    @RequiresPermissions("arj21:csvexport:export")
    @Log(title = "导入导出日志", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InfoImexportLog infoImexportLog)
    {
        List<InfoImexportLog> list = infoImexportLogService.selectInfoImexportLogList(infoImexportLog);
        ExcelUtil<InfoImexportLog> util = new ExcelUtil<InfoImexportLog>(InfoImexportLog.class);
        return util.exportExcel(list, "csvexport");
    }

    /**
     * 新增导入导出日志
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存导入导出日志
     */
    @RequiresPermissions("arj21:csvexport:add")
    @Log(title = "导入导出日志", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InfoImexportLog infoImexportLog)
    {
        return toAjax(infoImexportLogService.insertInfoImexportLog(infoImexportLog));
    }

    /**
     * 修改导入导出日志
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        InfoImexportLog infoImexportLog = infoImexportLogService.selectInfoImexportLogById(id);
        mmap.put("infoImexportLog", infoImexportLog);
        return prefix + "/edit";
    }

    /**
     * 修改保存导入导出日志
     */
    @RequiresPermissions("arj21:csvexport:edit")
    @Log(title = "导入导出日志", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InfoImexportLog infoImexportLog)
    {
        return toAjax(infoImexportLogService.updateInfoImexportLog(infoImexportLog));
    }

    /**
     * 删除导入导出日志
     */
    @RequiresPermissions("arj21:csvexport:remove")
    @Log(title = "导入导出日志", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(infoImexportLogService.deleteInfoImexportLogByIds(ids));
    }
}
