package com.ruoyi.web.controller.product;

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
import com.ruoyi.product.domain.AduitMessage;
import com.ruoyi.product.service.IAduitMessageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * productController
 * 
 * @author ruoyi
 * @date 2021-04-29
 */
@Controller
@RequestMapping("/productMessage/product")
public class AduitMessageController extends BaseController
{
    private String prefix = "product/product";

    @Autowired
    private IAduitMessageService aduitMessageService;

    @RequiresPermissions("product:product:view")
    @GetMapping()
    public String product()
    {
        return prefix + "/product";
    }

    /**
     * 查询product列表
     */
    @RequiresPermissions("product:product:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AduitMessage aduitMessage)
    {
        startPage();
        List<AduitMessage> list = aduitMessageService.selectAduitMessageList(aduitMessage);
        return getDataTable(list);
    }

    /**
     * 导出product列表
     */
    @RequiresPermissions("product:product:export")
    @Log(title = "product", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AduitMessage aduitMessage)
    {
        List<AduitMessage> list = aduitMessageService.selectAduitMessageList(aduitMessage);
        ExcelUtil<AduitMessage> util = new ExcelUtil<AduitMessage>(AduitMessage.class);
        return util.exportExcel(list, "product");
    }

    /**
     * 新增product
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存product
     */
    @RequiresPermissions("product:product:add")
    @Log(title = "product", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AduitMessage aduitMessage)
    {
        return toAjax(aduitMessageService.insertAduitMessage(aduitMessage));
    }

    /**
     * 修改product
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        AduitMessage aduitMessage = aduitMessageService.selectAduitMessageById(id);
        mmap.put("aduitMessage", aduitMessage);
        return prefix + "/edit";
    }

    /**
     * 修改保存product
     */
    @RequiresPermissions("product:product:edit")
    @Log(title = "product", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AduitMessage aduitMessage)
    {
        return toAjax(aduitMessageService.updateAduitMessage(aduitMessage));
    }

    /**
     * 删除product
     */
    @RequiresPermissions("product:product:remove")
    @Log(title = "product", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(aduitMessageService.deleteAduitMessageByIds(ids));
    }
}
