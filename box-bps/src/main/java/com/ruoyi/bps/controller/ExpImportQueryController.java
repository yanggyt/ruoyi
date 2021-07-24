package com.ruoyi.bps.controller;

import com.ruoyi.bps.domain.ExpImportQuery;
import com.ruoyi.bps.domain.ExpressInfo;
import com.ruoyi.bps.mapper.ExpressInfoMapper;
import com.ruoyi.bps.service.IExpImportQueryService;
import com.ruoyi.bps.service.IExpressInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Excel批量快递查询Controller
 * 
 * @author Bo
 * @date 2021-07-21
 */
@Controller
@RequestMapping("/bps/expImportQuery")
public class ExpImportQueryController extends BaseController
{
    private String prefix = "bps/expImportQuery";

    @Autowired
    private IExpImportQueryService expImportQueryService;

    @Autowired
    private IExpressInfoService expressInfoService;

    @Autowired
    private ExpressInfoMapper expressInfoMapper;

    @RequiresPermissions("bps:expImportQuery:view")
    @GetMapping()
    public String expImportQuery()
    {
        return prefix + "/expImportQuery";
    }

    /**
     * 查询Excel批量快递查询列表
     */
    @RequiresPermissions("bps:expImportQuery:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ExpImportQuery expImportQuery)
    {
        startPage();
        List<ExpImportQuery> list = expImportQueryService.selectExpImportQueryList(expImportQuery);
        return getDataTable(list);
    }

    /**
     * 导出Excel批量快递查询列表
     */
    @RequiresPermissions("bps:expImportQuery:export")
    @Log(title = "Excel批量快递查询", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExpImportQuery expImportQuery)
    {
        List<ExpImportQuery> list = expImportQueryService.selectExpImportQueryList(expImportQuery);
        ExcelUtil<ExpImportQuery> util = new ExcelUtil<ExpImportQuery>(ExpImportQuery.class);
        return util.exportExcel(list, "Excel批量快递查询数据");
    }


    /**
     * 导出Excel批量快递查询列表
     */
    @RequiresPermissions("bps:expressInfo:export")
    @Log(title = "详细快递信息导出", businessType = BusinessType.EXPORT)
    @PostMapping("/exportDetail")
    @ResponseBody
    public AjaxResult exportDetail(ExpressInfo expressInfo)
    {
        List<ExpressInfo> list = expressInfoService.selectLocalExpressInfoList(expressInfo);
        ExcelUtil<ExpressInfo> util = new ExcelUtil<ExpressInfo>(ExpressInfo.class);
        return util.exportExcel(list, "Excel批量快递查询数据");
    }

    /**
     * 新增Excel批量快递查询
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存Excel批量快递查询
     */
    @RequiresPermissions("bps:expImportQuery:add")
    @Log(title = "Excel批量快递查询", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ExpImportQuery expImportQuery)
    {
        return toAjax(expImportQueryService.insertExpImportQuery(expImportQuery));
    }

    /**
     * 修改Excel批量快递查询
     */
    @GetMapping("/edit/{sid}")
    public String edit(@PathVariable("sid") Long sid, ModelMap mmap)
    {
        ExpImportQuery expImportQuery = expImportQueryService.selectExpImportQueryById(sid);
        mmap.put("expImportQuery", expImportQuery);
        return prefix + "/edit";
    }

    /**
     * 修改保存Excel批量快递查询
     */
    @RequiresPermissions("bps:expImportQuery:edit")
    @Log(title = "Excel批量快递查询", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ExpImportQuery expImportQuery)
    {
        return toAjax(expImportQueryService.updateExpImportQuery(expImportQuery));
    }

    /**
     * 删除Excel批量快递查询
     */
    @RequiresPermissions("bps:expImportQuery:remove")
    @Log(title = "Excel批量快递查询", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(expImportQueryService.deleteExpImportQueryByIds(ids));
    }


    /**
     * 快递查询明细信息
     */
    @RequiresPermissions("bps:expImportQuery:detail")
    @GetMapping("/detail/{sid}")
    public String detail(@PathVariable("sid") Long sid, ModelMap mmap)
    {
       String queryId = expImportQueryService.selectExpImportQueryById(sid).getQueryId();
        ExpressInfo expressInfo= new ExpressInfo();
        expressInfo.setQueryId(queryId);
        mmap.put("expressInfo",expressInfo);
        return prefix + "/detail";
    }

    /**
     * Excel导入查模板下载
     */
    @GetMapping ( "/importTemplate" )
    @ResponseBody
    public AjaxResult importTemplate ( ) {
        ExcelUtil <ExpressInfo> util = new ExcelUtil<>(ExpressInfo.class);
        return util.importTemplateExcel ( "快递查询导入模板" );
    }

    /**
     * Excel导入查询
     */
    @PostMapping("/importData")
    @Log(title = "Excel批量导入快递查询", businessType = BusinessType.IMPORT)
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<ExpressInfo> util= new ExcelUtil<ExpressInfo>(ExpressInfo.class);
        List<ExpressInfo> expressInfoList=util.importExcel(file.getInputStream());
        return expImportQueryService.importData(expressInfoList);
    }
}
