package com.ruoyi.businessfile.controller;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.ruoyi.common.config.Global;
import com.ruoyi.common.utils.file.FileUploadUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.businessfile.domain.BusinessFile;
import com.ruoyi.businessfile.service.IBusinessFileService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文件资源Controller
 *
 * @author anjie
 * @date 2020-06-01
 */
@Controller
@RequestMapping("/businessfile/file")
public class BusinessFileController extends BaseController
{
    private String prefix = "businessfile/file";

    @Autowired
    private IBusinessFileService businessFileService;

    @RequiresPermissions("businessfile:file:view")
    @GetMapping()
    public String file()
    {
        return prefix + "/file";
    }

    /**
     * 查询文件资源列表
     */
    @RequiresPermissions("businessfile:file:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusinessFile businessFile)
    {
        startPage();
        List<BusinessFile> list = businessFileService.selectBusinessFileList(businessFile);
        return getDataTable(list);
    }

    /**
     * 导出文件资源列表
     */
    @RequiresPermissions("businessfile:file:export")
    @Log(title = "文件资源", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusinessFile businessFile)
    {
        List<BusinessFile> list = businessFileService.selectBusinessFileList(businessFile);
        ExcelUtil<BusinessFile> util = new ExcelUtil<BusinessFile>(BusinessFile.class);
        return util.exportExcel(list, "file");
    }

    /**
     * 新增文件资源
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存文件资源
     */
    @RequiresPermissions("businessfile:file:add")
    @Log(title = "文件资源", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusinessFile businessFile)
    {
        return toAjax(businessFileService.insertBusinessFile(businessFile));
    }

    @PostMapping("/addfile")
    @ResponseBody
    public Object addSave(@RequestParam("upload") MultipartFile file) throws IOException {
        // 上传文件路径
        String filePath = Global.getUploadPath();
        System.out.println("filePath:" + filePath);

        //文件名称
        String name = file.getOriginalFilename();
        // 上传并返回新文件名称
        String fileName = FileUploadUtils.upload(filePath, file);

        BusinessFile businessFile = new BusinessFile();
        businessFile.setFileName(name);
        businessFile.setFilePath(fileName);
        int i = businessFileService.insertBusinessFile(businessFile);
        HashMap<String, Object> okmap = new HashMap<>();
        okmap.put("uploaded", 1);
        okmap.put("url", fileName);
        if(i>0)
            return okmap;
        else
            return AjaxResult.error("上传失败");
    }

    /**
     * 修改文件资源
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BusinessFile businessFile = businessFileService.selectBusinessFileById(id);
        mmap.put("businessFile", businessFile);
        return prefix + "/edit";
    }

    /**
     * 修改保存文件资源
     */
    @RequiresPermissions("businessfile:file:edit")
    @Log(title = "文件资源", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusinessFile businessFile) {
        businessFile.setUpdateTime(new Date());
        return toAjax(businessFileService.updateBusinessFile(businessFile));
    }

    /**
     * 删除文件资源
     */
    @RequiresPermissions("businessfile:file:remove")
    @Log(title = "文件资源", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(businessFileService.deleteBusinessFileByIds(ids));
    }
}
