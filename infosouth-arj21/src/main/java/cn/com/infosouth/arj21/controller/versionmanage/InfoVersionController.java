package cn.com.infosouth.arj21.controller.versionmanage;

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
import cn.com.infosouth.arj21.domain.InfoVersion;
import cn.com.infosouth.arj21.service.IInfoVersionService;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.common.core.domain.AjaxResult;
import cn.com.infosouth.common.utils.poi.ExcelUtil;
import cn.com.infosouth.common.core.page.TableDataInfo;

/**
 * 参数版本Controller
 * 
 * @author kxnf
 * @date 2020-03-05
 */
@Controller
@RequestMapping("/arj21/version_manage")
public class InfoVersionController extends BaseController
{
    private String prefix = "arj21/version_manage";

    @Autowired
    private IInfoVersionService infoVersionService;

    @RequiresPermissions("arj21:version_manage:view")
    @GetMapping()
    public String version_manage()
    {
        return prefix + "/version_manage";
    }

    /**
     * 查询参数版本列表
     */
    @RequiresPermissions("arj21:version_manage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(InfoVersion infoVersion)
    {
        startPage();
        List<InfoVersion> list = infoVersionService.selectInfoVersionList(infoVersion);
        return getDataTable(list);
    }

    /**
     * 导出参数版本列表
     */
    @RequiresPermissions("arj21:version_manage:export")
    @Log(title = "参数版本", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InfoVersion infoVersion)
    {
        List<InfoVersion> list = infoVersionService.selectInfoVersionList(infoVersion);
        ExcelUtil<InfoVersion> util = new ExcelUtil<InfoVersion>(InfoVersion.class);
        return util.exportExcel(list, "version_manage");
    }

    /**
     * 新增参数版本
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存参数版本
     */
    @RequiresPermissions("arj21:version_manage:add")
    @Log(title = "参数版本", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InfoVersion infoVersion)
    {
        return toAjax(infoVersionService.insertInfoVersion(infoVersion));
    }

    /**
     * 修改参数版本
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        InfoVersion infoVersion = infoVersionService.selectInfoVersionById(id);
        mmap.put("infoVersion", infoVersion);
        return prefix + "/edit";
    }

    /**
     * 修改保存参数版本
     */
    @RequiresPermissions("arj21:version_manage:edit")
    @Log(title = "参数版本", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InfoVersion infoVersion)
    {
        return toAjax(infoVersionService.updateInfoVersion(infoVersion));
    }

    /**
     * 删除参数版本
     */
    @RequiresPermissions("arj21:version_manage:remove")
    @Log(title = "参数版本", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(infoVersionService.deleteInfoVersionByIds(ids));
    }
}
