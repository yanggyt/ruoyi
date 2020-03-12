package cn.com.infosouth.arj21.controller.actypemanage;

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
import cn.com.infosouth.arj21.domain.InfoAcType;
import cn.com.infosouth.arj21.service.IInfoAcTypeService;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.common.core.domain.AjaxResult;
import cn.com.infosouth.common.utils.poi.ExcelUtil;
import cn.com.infosouth.common.core.page.TableDataInfo;

/**
 * 机型Controller
 * 
 * @author kxnf
 * @date 2020-03-05
 */
@Controller
@RequestMapping("/arj21/actype_manage")
public class InfoAcTypeController extends BaseController
{
    private String prefix = "arj21/actype_manage";

    @Autowired
    private IInfoAcTypeService infoAcTypeService;

    @RequiresPermissions("arj21:actype_manage:view")
    @GetMapping()
    public String actype_manage()
    {
        return prefix + "/actype_manage";
    }

    /**
     * 查询机型列表
     */
    @RequiresPermissions("arj21:actype_manage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(InfoAcType infoAcType)
    {
        startPage();
        List<InfoAcType> list = infoAcTypeService.selectInfoAcTypeList(infoAcType);
        return getDataTable(list);
    }

    /**
     * 导出机型列表
     */
    @RequiresPermissions("arj21:actype_manage:export")
    @Log(title = "机型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InfoAcType infoAcType)
    {
        List<InfoAcType> list = infoAcTypeService.selectInfoAcTypeList(infoAcType);
        ExcelUtil<InfoAcType> util = new ExcelUtil<InfoAcType>(InfoAcType.class);
        return util.exportExcel(list, "actype_manage");
    }

    /**
     * 新增机型
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存机型
     */
    @RequiresPermissions("arj21:actype_manage:add")
    @Log(title = "机型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InfoAcType infoAcType)
    {
        return toAjax(infoAcTypeService.insertInfoAcType(infoAcType));
    }

    /**
     * 修改机型
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        InfoAcType infoAcType = infoAcTypeService.selectInfoAcTypeById(id);
        mmap.put("infoAcType", infoAcType);
        return prefix + "/edit";
    }

    /**
     * 修改保存机型
     */
    @RequiresPermissions("arj21:actype_manage:edit")
    @Log(title = "机型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InfoAcType infoAcType)
    {
        return toAjax(infoAcTypeService.updateInfoAcType(infoAcType));
    }

    /**
     * 删除机型
     */
    @RequiresPermissions("arj21:actype_manage:remove")
    @Log(title = "机型", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(infoAcTypeService.deleteInfoAcTypeByIds(ids));
    }
}
