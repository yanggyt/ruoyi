package com.ruoyi.web.controller.platform;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.province.platform.Constants.BussiConstants;
import com.ruoyi.province.platform.domain.BusinessScope;
import com.ruoyi.province.platform.service.IBusinessScopeService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 业务规模Controller
 * 
 * @author dalin
 * @date 2020-12-24
 */
@Controller
@RequestMapping("/platform/businessscope")
public class BusinessScopeController extends BaseController
{
    private String prefix = "platform/businessscope";

    @Autowired
    private IBusinessScopeService businessScopeService;

    @RequiresPermissions("platform:businessscope:view")
    @GetMapping()
    public String businessscope()
    {
        return prefix + "/businessscope";
    }

    /**
     * 查询业务规模列表
     */
    @RequiresPermissions("platform:businessscope:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusinessScope businessScope)
    {
        startPage();
        List<BusinessScope> list = businessScopeService.selectBusinessScopeList(businessScope);
        return getDataTable(list);
    }

    /**
     * 导出业务规模列表
     */
    @RequiresPermissions("platform:businessscope:export")
    @Log(title = "业务规模", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusinessScope businessScope)
    {
        List<BusinessScope> list = businessScopeService.selectBusinessScopeList(businessScope);
        ExcelUtil<BusinessScope> util = new ExcelUtil<BusinessScope>(BusinessScope.class);
        return util.exportExcel(list, "businessscope");
    }

    /**
     * businessScope
      * 校验业务规模名称 是否重复
      */
    @PostMapping("/checkBusinessScopeUnique")
    @ResponseBody
    public String checkBusinessScopeUnique(BusinessScope businessScope)
    {
        return businessScopeService.checkBusinessScopeUnique(businessScope);
    }

    /**
     * 新增业务规模
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
      // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        return prefix + "/add";
    }

    /**
     * 新增保存业务规模
     */
    @RequiresPermissions("platform:businessscope:add")
    @Log(title = "业务规模", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusinessScope businessScope)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(businessScopeService.checkBusinessScopeUnique(businessScope)))
        {
            return error("修改单据'" + businessScope.getBusinessScaleName() + "'失败，名称已存在");
        }

    // 取身份信息
        businessScope.setCreateBy( ShiroUtils.getLoginName() );
        businessScope.setCreateTime(DateUtils.getNowDate() );

        return toAjax(businessScopeService.insertBusinessScope(businessScope));
    }

    /**
     * 修改业务规模
     */
    @GetMapping("/edit/{businessScaleId}")
    public String edit(@PathVariable("businessScaleId") Long businessScaleId, ModelMap mmap)
    {
        BusinessScope businessScope = businessScopeService.selectBusinessScopeById(businessScaleId);
        mmap.put("businessScope", businessScope);

        // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        return prefix + "/edit";
    }

    /**
     * 修改保存业务规模
     */
    @RequiresPermissions("platform:businessscope:edit")
    @Log(title = "业务规模", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusinessScope businessScope)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(businessScopeService.checkBusinessScopeUnique(businessScope)))
        {
            return error("修改单据'" + businessScope.getBusinessScaleName() + "'失败，名称已存在");
        }

        businessScope.setUpdateBy( ShiroUtils.getLoginName() );
        businessScope.setUpdateTime( DateUtils.getNowDate() );
        return toAjax(businessScopeService.updateBusinessScope(businessScope));
    }

    /**
     * 删除业务规模
     */
    @RequiresPermissions("platform:businessscope:remove")
    @Log(title = "业务规模", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(businessScopeService.deleteBusinessScopeByIds(ids));
    }
}
