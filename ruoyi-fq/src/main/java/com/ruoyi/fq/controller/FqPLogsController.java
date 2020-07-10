package com.ruoyi.fq.controller;

import java.util.List;

import com.ruoyi.framework.util.ShiroUtils;
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
import com.ruoyi.fq.domain.FqPLogs;
import com.ruoyi.fq.service.IFqPLogsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 封铅袋出入库记录Controller
 *
 * @author mario
 * @date 2020-07-09
 */
@Controller
@RequestMapping("/fq/logs")
public class FqPLogsController extends BaseController
{
    private String prefix = "fq/logs";

    @Autowired
    private IFqPLogsService fqPLogsService;

    @RequiresPermissions("fq:logs:view")
    @GetMapping()
    public String logs()
    {
        return prefix + "/logs";
    }

            /**
         * 查询封铅袋出入库记录列表
         */
        @RequiresPermissions("fq:logs:list")
        @PostMapping("/list")
        @ResponseBody
        public TableDataInfo list(FqPLogs fqPLogs)
        {
            startPage();
            List<FqPLogs> list = fqPLogsService.selectFqPLogsList(fqPLogs);
            return getDataTable(list);
        }
    
    /**
     * 导出封铅袋出入库记录列表
     */
    @RequiresPermissions("fq:logs:export")
    @Log(title = "封铅袋出入库记录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FqPLogs fqPLogs)
    {
        List<FqPLogs> list = fqPLogsService.selectFqPLogsList(fqPLogs);
        ExcelUtil<FqPLogs> util = new ExcelUtil<FqPLogs>(FqPLogs.class);
        return util.exportExcel(list, "logs");
    }

    /**
     * 导入封铅袋出入库记录列表
     */
    @Log(title = "封铅袋出入库记录", businessType = BusinessType.IMPORT)
    @RequiresPermissions("fq:logs:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<FqPLogs> util = new ExcelUtil<FqPLogs>(FqPLogs.class);
        List<FqPLogs> list = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = fqPLogsService.importData(list, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 下载模板
     */
    @RequiresPermissions("fq:logs:list")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<FqPLogs> util = new ExcelUtil<>(FqPLogs.class);
        return util.importTemplateExcel("封铅袋出入库记录数据");
    }

    /**
    * 查看详细
    */
    @RequiresPermissions("fq:logs:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") String id, ModelMap mmap)
    {
        FqPLogs fqPLogs = fqPLogsService.selectFqPLogsById(id);
        mmap.put("fqPLogs", fqPLogs);
        return prefix + "/detail";
    }

            /**
         * 新增封铅袋出入库记录
         */
        @GetMapping("/add")
        public String add()
        {
            return prefix + "/add";
        }
    
    /**
     * 新增保存封铅袋出入库记录
     */
    @RequiresPermissions("fq:logs:add")
    @Log(title = "封铅袋出入库记录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FqPLogs fqPLogs)
    {
        return toAjax(fqPLogsService.insertFqPLogs(fqPLogs));
    }

    /**
     * 修改封铅袋出入库记录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        FqPLogs fqPLogs = fqPLogsService.selectFqPLogsById(id);
        mmap.put("fqPLogs", fqPLogs);
        return prefix + "/edit";
    }

    /**
     * 修改保存封铅袋出入库记录
     */
    @RequiresPermissions("fq:logs:edit")
    @Log(title = "封铅袋出入库记录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FqPLogs fqPLogs)
    {
        return toAjax(fqPLogsService.updateFqPLogs(fqPLogs));
    }

            /**
         * 删除封铅袋出入库记录
         */
        @RequiresPermissions("fq:logs:remove")
        @Log(title = "封铅袋出入库记录", businessType = BusinessType.DELETE)
        @PostMapping( "/remove")
        @ResponseBody
        public AjaxResult remove(String ids)
        {
            return toAjax(fqPLogsService.deleteFqPLogsByIds(ids));
        }
        }
