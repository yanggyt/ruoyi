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
import com.ruoyi.system.domain.WkCrmCustomerPool;
import com.ruoyi.system.service.IWkCrmCustomerPoolService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 公海Controller
 * 
 * @author ruoyi
 * @date 2021-04-06
 */
@Controller
@RequestMapping("/system/pool")
public class WkCrmCustomerPoolController extends BaseController
{
    private String prefix = "system/pool";

    @Autowired
    private IWkCrmCustomerPoolService wkCrmCustomerPoolService;

    @RequiresPermissions("system:pool:view")
    @GetMapping()
    public String pool()
    {
        return prefix + "/pool";
    }

    /**
     * 查询公海列表
     */
    @RequiresPermissions("system:pool:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(WkCrmCustomerPool wkCrmCustomerPool)
    {
        startPage();
        List<WkCrmCustomerPool> list = wkCrmCustomerPoolService.selectWkCrmCustomerPoolList(wkCrmCustomerPool);
        return getDataTable(list);
    }

    /**
     * 导出公海列表
     */
    @RequiresPermissions("system:pool:export")
    @Log(title = "公海", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WkCrmCustomerPool wkCrmCustomerPool)
    {
        List<WkCrmCustomerPool> list = wkCrmCustomerPoolService.selectWkCrmCustomerPoolList(wkCrmCustomerPool);
        ExcelUtil<WkCrmCustomerPool> util = new ExcelUtil<WkCrmCustomerPool>(WkCrmCustomerPool.class);
        return util.exportExcel(list, "pool");
    }

    /**
     * 新增公海
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存公海
     */
    @RequiresPermissions("system:pool:add")
    @Log(title = "公海", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(WkCrmCustomerPool wkCrmCustomerPool)
    {
        return toAjax(wkCrmCustomerPoolService.insertWkCrmCustomerPool(wkCrmCustomerPool));
    }

    /**
     * 修改公海
     */
    @GetMapping("/edit/{poolId}")
    public String edit(@PathVariable("poolId") Long poolId, ModelMap mmap)
    {
        WkCrmCustomerPool wkCrmCustomerPool = wkCrmCustomerPoolService.selectWkCrmCustomerPoolById(poolId);
        mmap.put("wkCrmCustomerPool", wkCrmCustomerPool);
        return prefix + "/edit";
    }

    /**
     * 修改保存公海
     */
    @RequiresPermissions("system:pool:edit")
    @Log(title = "公海", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(WkCrmCustomerPool wkCrmCustomerPool)
    {
        return toAjax(wkCrmCustomerPoolService.updateWkCrmCustomerPool(wkCrmCustomerPool));
    }

    /**
     * 删除公海
     */
    @RequiresPermissions("system:pool:remove")
    @Log(title = "公海", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(wkCrmCustomerPoolService.deleteWkCrmCustomerPoolByIds(ids));
    }
}
