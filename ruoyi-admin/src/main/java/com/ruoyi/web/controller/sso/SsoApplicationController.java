package com.ruoyi.web.controller.sso;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import com.ruoyi.framework.util.ShiroUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.sso.domain.SsoApplication;
import com.ruoyi.sso.service.ISsoApplicationService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 单点登录应用Controller
 * 
 * @author shixueshu
 * @date 2020-03-23
 */
@Controller
@RequestMapping("/sso/ssoApplication")
public class SsoApplicationController extends BaseController
{
    private String prefix = "sso/ssoApplication";

    @Autowired
    private ISsoApplicationService ssoApplicationService;

    @RequiresPermissions("sso:ssoApplication:view")
    @GetMapping()
    public String ssoApplication()
    {
        return prefix + "/ssoApplication";
    }

    /**
     * 查询单点登录应用列表
     */
    @RequiresPermissions("sso:ssoApplication:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(SsoApplication ssoApplication)
    {
        startPage();
        List<SsoApplication> list = ssoApplicationService.selectSsoApplicationList(ssoApplication);
        return getDataTable(list);
    }

    /**
     * 导出单点登录应用列表
     */
    @RequiresPermissions("sso:ssoApplication:export")
    @Log(title = "单点登录应用", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SsoApplication ssoApplication)
    {
        List<SsoApplication> list = ssoApplicationService.selectSsoApplicationList(ssoApplication);
        ExcelUtil<SsoApplication> util = new ExcelUtil<SsoApplication>(SsoApplication.class);
        return util.exportExcel(list, "templates/sso/ssoApplication");
    }

    /**
     * 新增单点登录应用
     */
    @GetMapping("/add")
    public String add(Model model)
    {
        String appSecret = Base64Utils.encodeToString(UUID.randomUUID().toString().getBytes());
        model.addAttribute("appSecret", appSecret);
        return prefix + "/add";
    }

    /**
     * 新增保存单点登录应用
     */
    @RequiresPermissions("sso:ssoApplication:add")
    @Log(title = "单点登录应用", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SsoApplication ssoApplication)
    {
        ssoApplication.setCreateBy(ShiroUtils.getLoginName());
        ssoApplication.setStatus("1");
        return toAjax(ssoApplicationService.insertSsoApplication(ssoApplication));
    }

    /**
     * 修改单点登录应用
     */
    @GetMapping("/edit/{appId}")
    public String edit(@PathVariable("appId") Long appId, ModelMap mmap)
    {
        SsoApplication ssoApplication = ssoApplicationService.selectSsoApplicationById(appId);
        mmap.put("ssoApplication", ssoApplication);
        return prefix + "/edit";
    }

    /**
     * 修改保存单点登录应用
     */
    @RequiresPermissions("sso:ssoApplication:edit")
    @Log(title = "单点登录应用", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SsoApplication ssoApplication)
    {
        ssoApplication.setUpdateBy(ShiroUtils.getLoginName());
        return toAjax(ssoApplicationService.updateSsoApplication(ssoApplication));
    }

    /**
     * 删除单点登录应用
     */
    @RequiresPermissions("sso:ssoApplication:remove")
    @Log(title = "单点登录应用", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(ssoApplicationService.deleteSsoApplicationByIds(ids));
    }
}
