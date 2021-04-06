package com.ruoyi.web.controller.system;

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
import com.ruoyi.system.domain.WkCrmContacts;
import com.ruoyi.system.service.IWkCrmContactsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 联系人Controller
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Controller
@RequestMapping("/system/contacts")
public class WkCrmContactsController extends BaseController
{
    private String prefix = "system/contacts";

    @Autowired
    private IWkCrmContactsService wkCrmContactsService;

    @RequiresPermissions("system:contacts:view")
    @GetMapping()
    public String contacts()
    {
        return prefix + "/contacts";
    }

    /**
     * 查询联系人列表
     */
    @RequiresPermissions("system:contacts:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WkCrmContacts wkCrmContacts)
    {
        startPage();
        List<WkCrmContacts> list = wkCrmContactsService.selectWkCrmContactsList(wkCrmContacts);
        return getDataTable(list);
    }

    /**
     * 导出联系人列表
     */
    @RequiresPermissions("system:contacts:export")
    @Log(title = "联系人", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WkCrmContacts wkCrmContacts)
    {
        List<WkCrmContacts> list = wkCrmContactsService.selectWkCrmContactsList(wkCrmContacts);
        ExcelUtil<WkCrmContacts> util = new ExcelUtil<WkCrmContacts>(WkCrmContacts.class);
        return util.exportExcel(list, "contacts");
    }

    /**
     * 新增联系人
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存联系人
     */
    @RequiresPermissions("system:contacts:add")
    @Log(title = "联系人", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WkCrmContacts wkCrmContacts)
    {
        return toAjax(wkCrmContactsService.insertWkCrmContacts(wkCrmContacts));
    }

    /**
     * 修改联系人
     */
    @GetMapping("/edit/{contactsId}")
    public String edit(@PathVariable("contactsId") Long contactsId, ModelMap mmap)
    {
        WkCrmContacts wkCrmContacts = wkCrmContactsService.selectWkCrmContactsById(contactsId);
        mmap.put("wkCrmContacts", wkCrmContacts);
        return prefix + "/edit";
    }

    /**
     * 修改保存联系人
     */
    @RequiresPermissions("system:contacts:edit")
    @Log(title = "联系人", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WkCrmContacts wkCrmContacts)
    {
        return toAjax(wkCrmContactsService.updateWkCrmContacts(wkCrmContacts));
    }

    /**
     * 删除联系人
     */
    @RequiresPermissions("system:contacts:remove")
    @Log(title = "联系人", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wkCrmContactsService.deleteWkCrmContactsByIds(ids));
    }
}
