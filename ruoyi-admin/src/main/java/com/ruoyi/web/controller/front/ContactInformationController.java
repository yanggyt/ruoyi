package com.ruoyi.web.controller.front;

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
import com.ruoyi.front.domain.ContactInformation;
import com.ruoyi.front.service.IContactInformationService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 联系方式Controller
 * 
 * @author ruoyi
 * @date 2020-10-22
 */
@Controller
@RequestMapping("/front/contactInformation")
public class ContactInformationController extends BaseController
{
    private String prefix = "front/contactInformation";

    @Autowired
    private IContactInformationService contactInformationService;

    @RequiresPermissions("front:contactInformation:view")
    @GetMapping()
    public String contactInformation()
    {
        return prefix + "/contactInformation";
    }

    /**
     * 查询联系方式列表
     */
    @RequiresPermissions("front:contactInformation:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ContactInformation contactInformation)
    {
        startPage();
        List<ContactInformation> list = contactInformationService.selectContactInformationList(contactInformation);
        return getDataTable(list);
    }

    /**
     * 导出联系方式列表
     */
    @RequiresPermissions("front:contactInformation:export")
    @Log(title = "联系方式", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ContactInformation contactInformation)
    {
        List<ContactInformation> list = contactInformationService.selectContactInformationList(contactInformation);
        ExcelUtil<ContactInformation> util = new ExcelUtil<ContactInformation>(ContactInformation.class);
        return util.exportExcel(list, "contactInformation");
    }

    /**
     * 新增联系方式
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存联系方式
     */
    @RequiresPermissions("front:contactInformation:add")
    @Log(title = "联系方式", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ContactInformation contactInformation)
    {
        return toAjax(contactInformationService.insertContactInformation(contactInformation));
    }

    /**
     * 修改联系方式
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        ContactInformation contactInformation = contactInformationService.selectContactInformationById(id);
        mmap.put("contactInformation", contactInformation);
        return prefix + "/edit";
    }

    /**
     * 修改保存联系方式
     */
    @RequiresPermissions("front:contactInformation:edit")
    @Log(title = "联系方式", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ContactInformation contactInformation)
    {
        return toAjax(contactInformationService.updateContactInformation(contactInformation));
    }

    /**
     * 删除联系方式
     */
    @RequiresPermissions("front:contactInformation:remove")
    @Log(title = "联系方式", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(contactInformationService.deleteContactInformationByIds(ids));
    }
}
