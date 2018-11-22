package com.ruoyi.web.controller.tool;

import java.io.IOException;
import java.util.List;
import javax.servlet.http.HttpServletResponse;

import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.framework.datasource.DynamicDataSourceContextHolder;
import org.apache.commons.io.IOUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.generator.domain.TableInfo;
import com.ruoyi.generator.service.IGenService;
import com.ruoyi.framework.web.base.BaseController;

/**
 * 代码生成 操作处理
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/tool/gen")
public class GenController extends BaseController
{
    private String prefix = "tool/gen";

    @Autowired
    private IGenService genService;

    @RequiresPermissions("tool:gen:view")
    @GetMapping()
    public String gen(ModelMap mmap)
    {
        mmap.put("dataSource", DataSourceType.MASTER.name());
        return prefix + "/gen";
    }

    @RequiresPermissions("tool:gen:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TableInfo tableInfo)
    {
        startPage();
        DynamicDataSourceContextHolder.setDateSoureType((String) tableInfo.getParams().get("dataSource"));
        List<TableInfo> list = genService.selectTableList(tableInfo);
        DynamicDataSourceContextHolder.clearDateSoureType();
        return getDataTable(list);
    }

    /**
     * 生成代码
     */
    @RequiresPermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/genCode/{tableName}/{dataSource}")
    public void genCode(HttpServletResponse response, @PathVariable("tableName") String tableName, @PathVariable("dataSource") String dataSource) throws IOException
    {
        DynamicDataSourceContextHolder.setDateSoureType(dataSource);
        byte[] data = genService.generatorCode(tableName);
        DynamicDataSourceContextHolder.clearDateSoureType();
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }

    /**
     * 批量生成代码
     */
    @RequiresPermissions("tool:gen:code")
    @Log(title = "代码生成", businessType = BusinessType.GENCODE)
    @GetMapping("/batchGenCode")
    @ResponseBody
    public void batchGenCode(HttpServletResponse response, String tables, String dataSource) throws IOException
    {
        String[] tableNames = Convert.toStrArray(tables);
        DynamicDataSourceContextHolder.setDateSoureType(dataSource);
        byte[] data = genService.generatorCode(tableNames);
        DynamicDataSourceContextHolder.clearDateSoureType();
        response.reset();
        response.setHeader("Content-Disposition", "attachment; filename=\"ruoyi.zip\"");
        response.addHeader("Content-Length", "" + data.length);
        response.setContentType("application/octet-stream; charset=UTF-8");

        IOUtils.write(data, response.getOutputStream());
    }
}
