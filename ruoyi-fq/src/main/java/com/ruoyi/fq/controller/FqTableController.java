package com.ruoyi.fq.controller;

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
import com.ruoyi.fq.domain.FqTable;
import com.ruoyi.fq.service.IFqTableService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.framework.util.ShiroUtils;
import org.springframework.web.multipart.MultipartFile;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 封铅登记Controller
 *
 * @author mario
 * @date 2020-07-09
 */
@Controller
@RequestMapping("/fq/fqTable")
public class FqTableController extends BaseController
{
    private String prefix = "fq/fqTable";

    @Autowired
    private IFqTableService fqTableService;

    @RequiresPermissions("fq:fqTable:view")
    @GetMapping()
    public String fqTable()
    {
        return prefix + "/fqTable";
    }

            /**
         * 查询封铅登记列表
         */
        @RequiresPermissions("fq:fqTable:list")
        @PostMapping("/list")
        @ResponseBody
        public TableDataInfo list(FqTable fqTable)
        {
            startPage();
            List<FqTable> list = fqTableService.selectFqTableList(fqTable);
            return getDataTable(list);
        }
    
    /**
     * 导出封铅登记列表
     */
    @RequiresPermissions("fq:fqTable:export")
    @Log(title = "封铅登记", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FqTable fqTable)
    {
        List<FqTable> list = fqTableService.selectFqTableList(fqTable);
        ExcelUtil<FqTable> util = new ExcelUtil<FqTable>(FqTable.class);
        return util.exportExcel(list, "fqTable");
    }

    /**
     * 导入封铅登记列表
     */
    @Log(title = "封铅登记", businessType = BusinessType.IMPORT)
    @RequiresPermissions("fq:fqTable:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<FqTable> util = new ExcelUtil<FqTable>(FqTable.class);
        List<FqTable> list = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = fqTableService.importData(list, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 下载模板
     */
    @RequiresPermissions("fq:fqTable:list")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<FqTable> util = new ExcelUtil<>(FqTable.class);
        return util.importTemplateExcel("封铅登记数据");
    }

    /**
    * 查看详细
    */
    @RequiresPermissions("fq:fqTable:detail")
    @GetMapping("/detail/{id}")
    public String detail(@PathVariable("id") Long id, ModelMap mmap)
    {
        FqTable fqTable = fqTableService.selectFqTableById(id);
        mmap.put("fqTable", fqTable);
        return prefix + "/detail";
    }

            /**
         * 新增封铅登记
         */
        @GetMapping("/add")
        public String add()
        {
            return prefix + "/add";
        }
    
    /**
     * 新增保存封铅登记
     */
    @RequiresPermissions("fq:fqTable:add")
    @Log(title = "封铅登记", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FqTable fqTable)
    {
        fqTable.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(fqTableService.insertFqTable(fqTable));
    }

    /**
     * 修改封铅登记
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FqTable fqTable = fqTableService.selectFqTableById(id);
        mmap.put("fqTable", fqTable);
        return prefix + "/edit";
    }

    /**
     * 修改保存封铅登记
     */
    @RequiresPermissions("fq:fqTable:edit")
    @Log(title = "封铅登记", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FqTable fqTable)
    {
        fqTable.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(fqTableService.updateFqTable(fqTable));
    }

            /**
         * 删除封铅登记
         */
        @RequiresPermissions("fq:fqTable:remove")
        @Log(title = "封铅登记", businessType = BusinessType.DELETE)
        @PostMapping( "/remove")
        @ResponseBody
        public AjaxResult remove(String ids)
        {
            return toAjax(fqTableService.deleteFqTableByIds(ids));
        }
        }
