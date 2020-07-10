package com.ruoyi.fq.controller;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.security.PermissionUtils;
import com.ruoyi.fq.domain.FqPackage;
import com.ruoyi.fq.service.IFqPackageService;
import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 封铅袋Controller
 *
 * @author mario
 * @date 2020-07-02
 */
@Controller
@RequestMapping("/fq/pack")
public class FqPackageController extends BaseController
{
    private String prefix = "fq/pack";

    @Autowired
    private IFqPackageService fqPackageService;

    @RequiresPermissions("fq:pack:view")
    @GetMapping()
    public String pack()
    {
        return prefix + "/pack";
    }

    /**
     * 查询封铅袋列表
     */
    @DataScope(deptAlias = "p", userAlias = "p")
    @RequiresPermissions("fq:pack:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(FqPackage fqPackage)
    {
        startPage();
        List<FqPackage> list = fqPackageService.selectFqPackageList(fqPackage);
        return getDataTable(list);
    }

    /**
     * 导出封铅袋列表
     */
    @RequiresPermissions("fq:pack:export")
    @Log(title = "封铅袋", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(FqPackage fqPackage)
    {
        List<FqPackage> list = fqPackageService.selectFqPackageList(fqPackage);
        ExcelUtil<FqPackage> util = new ExcelUtil<FqPackage>(FqPackage.class);
        return util.exportExcel(list, "pack");
    }

    @Log(title = "封铅管理", businessType = BusinessType.IMPORT)
    @RequiresPermissions("fq:pack:import")
    @PostMapping("/importData")
    @ResponseBody
    public AjaxResult importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        ExcelUtil<FqPackage> util = new ExcelUtil<FqPackage>(FqPackage.class);
        List<FqPackage> list = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = fqPackageService.importData(list, updateSupport, operName);
        return AjaxResult.success(message);
    }

    /**
     * 下载模板
     */
    @RequiresPermissions("fq:pack:list")
    @GetMapping("/importTemplate")
    @ResponseBody
    public AjaxResult importTemplate()
    {
        ExcelUtil<FqPackage> util = new ExcelUtil<>(FqPackage.class);
        return util.importTemplateExcel("封签袋数据");
    }

    /**
     * 新增封铅袋
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存封铅袋
     */
    @RequiresPermissions("fq:pack:add")
    @Log(title = "封铅袋", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(FqPackage fqPackage)
    {
        fqPackage.setCreateBy(ShiroUtils.getLoginName());
        return toAjax(fqPackageService.insertFqPackage(fqPackage));
    }

    /**
     * 修改封铅袋
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        FqPackage fqPackage = fqPackageService.selectFqPackageById(id);
        mmap.put("fqPackage", fqPackage);
        return prefix + "/edit";
    }

    /**
     * 修改保存封铅袋
     */
    @RequiresPermissions("fq:pack:edit")
    @Log(title = "封铅袋", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(FqPackage fqPackage)
    {
        fqPackage.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(fqPackageService.updateFqPackage(fqPackage));
    }

    /**
     * 删除封铅袋
     */
    @RequiresPermissions("fq:pack:remove")
    @Log(title = "封铅袋", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(fqPackageService.deleteFqPackageByIds(ids));
    }

    /**
     * 出库，修改状态
     * 修改user_id
     */
    @Log(title = "封铅袋", businessType = BusinessType.UPDATE)
    @PostMapping("/ckSave")
    @ResponseBody
    public AjaxResult ckSave(FqPackage fqPackage,String ids)
    {
        fqPackage.setStatus("1");
        Long user_id = fqPackage.getkUserid() == 0 ? fqPackage.getaUserid() : fqPackage.getkUserid();
        fqPackage.setUserId(user_id);
        return toAjax(fqPackageService.updateCkFqPackageByIds(fqPackage,ids));
    }

    /**
     * 归还，修改状态
     * 修改user_id
     */
    @Log(title = "封铅袋", businessType = BusinessType.UPDATE)
    @PostMapping("/ghSave")
    @ResponseBody
    public AjaxResult ghSave(FqPackage fqPackage,String ids)
    {
        return toAjax(fqPackageService.updateGhFqPackageByIds(fqPackage,ids));
    }

    /**
     * 修改保存封铅袋
     */
    @Log(title = "封铅袋", businessType = BusinessType.UPDATE)
    @PostMapping("/editSaveAll")
    @ResponseBody
    public AjaxResult editSaveAll(FqPackage fqPackage,String ids)
    {
        return toAjax(fqPackageService.updateFqPackageByIds(fqPackage,ids));
    }


    /**
     * 出库
     */
    @GetMapping("/ck/{ids}")
    @RequiresPermissions("fq:pack:ck")
    public String ck(@PathVariable("ids") String ids, ModelMap mmap)
    {
        //FqPackage fqPackage = fqPackageService.selectFqPackageById(id);
        mmap.put("ids", ids);
        return prefix + "/ck";
    }

    /**
     * 归还
     */
    @GetMapping("/gh/{ids}")
    public String gh(@PathVariable("ids") String ids, ModelMap mmap)
    {
        //FqPackage fqPackage = fqPackageService.selectFqPackageById(id);
        mmap.put("ids", ids);
        return prefix + "/gh";
    }
}
