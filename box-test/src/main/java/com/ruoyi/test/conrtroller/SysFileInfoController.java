package com.ruoyi.test.conrtroller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.test.domain.SysFileInfo;
import com.ruoyi.test.service.ISysFileInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 文件信息Controller
 * 
 * @author box
 * @date 2021-05-06
 */
@Controller
@RequestMapping("/test/fileinfo")
public class SysFileInfoController extends BaseController
{
    private String prefix = "test/fileinfo";

    @Autowired
    private ISysFileInfoService sysFileInfoService;

    @RequiresPermissions("test:fileinfo:view")
    @GetMapping()
    public String fileinfo()
    {
        return prefix + "/fileinfo";
    }

    /**
     * 查询文件信息列表
     */
    @RequiresPermissions("test:fileinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SysFileInfo sysFileInfo)
    {
        startPage();
        List<SysFileInfo> list = sysFileInfoService.selectSysFileInfoList(sysFileInfo);
        return getDataTable(list);
    }

    /**
     * 导出文件信息列表
     */
    @RequiresPermissions("test:fileinfo:export")
    @Log(title = "文件信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SysFileInfo sysFileInfo)
    {
        List<SysFileInfo> list = sysFileInfoService.selectSysFileInfoList(sysFileInfo);
        ExcelUtil<SysFileInfo> util = new ExcelUtil<SysFileInfo>(SysFileInfo.class);
        return util.exportExcel(list, "文件信息数据");
    }

    /**
     * 新增文件信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存文件信息
     */
    @RequiresPermissions("test:fileinfo:add")
    @Log(title = "文件信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SysFileInfo sysFileInfo)
    {
        return toAjax(sysFileInfoService.insertSysFileInfo(sysFileInfo));
    }

    /**
     * 修改文件信息
     */
    @GetMapping("/edit/{fileId}")
    public String edit(@PathVariable("fileId") Long fileId, ModelMap mmap)
    {
        SysFileInfo sysFileInfo = sysFileInfoService.selectSysFileInfoById(fileId);
        mmap.put("sysFileInfo", sysFileInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存文件信息
     */
    @RequiresPermissions("test:fileinfo:edit")
    @Log(title = "文件信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SysFileInfo sysFileInfo)
    {
        return toAjax(sysFileInfoService.updateSysFileInfo(sysFileInfo));
    }

    /**
     * 删除文件信息
     */
    @RequiresPermissions("test:fileinfo:remove")
    @Log(title = "文件信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(sysFileInfoService.deleteSysFileInfoByIds(ids));
    }
}
