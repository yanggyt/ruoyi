package com.ruoyi.web.controller.front;

import java.util.List;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.utils.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.front.domain.ServiceOrganization;
import com.ruoyi.front.service.IServiceOrganizationService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 服务组织Controller
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Controller
@RequestMapping("/front/organization")
public class ServiceOrganizationController extends BaseController
{
    private String prefix = "front/organization";

    @Autowired
    private IServiceOrganizationService serviceOrganizationService;

    @RequiresPermissions("front:organization:view")
    @GetMapping()
    public String organization()
    {
        return prefix + "/organization";
    }

    /**
     * 查询服务组织列表
     */
    @RequiresPermissions("front:organization:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ServiceOrganization serviceOrganization)
    {
        startPage();
        List<ServiceOrganization> list = serviceOrganizationService.selectServiceOrganizationList(serviceOrganization);
        return getDataTable(list);
    }

    /**
     * 导出服务组织列表
     */
    @RequiresPermissions("front:organization:export")
    @Log(title = "服务组织", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ServiceOrganization serviceOrganization)
    {
        List<ServiceOrganization> list = serviceOrganizationService.selectServiceOrganizationList(serviceOrganization);
        ExcelUtil<ServiceOrganization> util = new ExcelUtil<ServiceOrganization>(ServiceOrganization.class);
        return util.exportExcel(list, "organization");
    }

    /**
     * 新增服务组织
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存服务组织
     */
    @RequiresPermissions("front:organization:add")
    @Log(title = "服务组织", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ServiceOrganization serviceOrganization)
    {
        return toAjax(serviceOrganizationService.insertServiceOrganization(serviceOrganization));
    }

    /**
     * 修改服务组织
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ServiceOrganization serviceOrganization = serviceOrganizationService.selectServiceOrganizationById(id);
        mmap.put("serviceOrganization", serviceOrganization);
        return prefix + "/edit";
    }

    /**
     * 修改保存服务组织
     */
    @RequiresPermissions("front:organization:edit")
    @Log(title = "服务组织", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ServiceOrganization serviceOrganization)
    {
        return toAjax(serviceOrganizationService.updateServiceOrganization(serviceOrganization));
    }

    /**
     * 删除服务组织
     */
    @RequiresPermissions("front:organization:remove")
    @Log(title = "服务组织", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(serviceOrganizationService.deleteServiceOrganizationByIds(ids));
    }

    /**
     * 新增服务组织
     */
    @GetMapping("/audit")
    public String audit(@RequestParam String ids, ModelMap mmap)
    {
        mmap.put("ids", ids);
        return prefix + "/audit";
    }

    /**
     * 审核服务组织
     */
    @PostMapping("/audit")
    @ResponseBody
    public AjaxResult audit(@RequestParam() String ids, @RequestParam String auditStatus, String remark)
    {
        // 未审核通过，则备注不能为空
        if (StringUtils.isEmpty(remark) && auditStatus.equals(Constants.NO_PASS_AUDIT))  {
            return error("备注不能为空");
        }

        return toAjax(serviceOrganizationService.audit(ids, auditStatus, remark));
    }
}
