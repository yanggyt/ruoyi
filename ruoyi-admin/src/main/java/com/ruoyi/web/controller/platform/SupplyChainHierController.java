package com.ruoyi.web.controller.platform;

import java.util.List;

import com.ruoyi.common.constant.UserConstants;
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
import com.ruoyi.province.platform.domain.SupplyChainHier;
import com.ruoyi.province.platform.service.ISupplyChainHierService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;

/**
 * 供应链层级Controller
 * 
 * @author dalin
 * @date 2021-01-14
 */
@Controller
@RequestMapping("/platform/supplychainhier")
public class SupplyChainHierController extends BaseController
{
    private String prefix = "platform/supplychainhier";

    @Autowired
    private ISupplyChainHierService supplyChainHierService;

    @RequiresPermissions("platform:supplychainhier:view")
    @GetMapping()
    public String supplychainhier()
    {
        return prefix + "/supplychainhier";
    }

    /**
     * 查询供应链层级树列表
     */
    @RequiresPermissions("platform:supplychainhier:list")
    @PostMapping("/list")
    @ResponseBody
    public List<SupplyChainHier> list(SupplyChainHier supplyChainHier)
    {
        List<SupplyChainHier> list = supplyChainHierService.selectSupplyChainHierList(supplyChainHier);
        return list;
    }

    /**
     * 导出供应链层级列表
     */
    @RequiresPermissions("platform:supplychainhier:export")
    @Log(title = "供应链层级", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(SupplyChainHier supplyChainHier)
    {
        List<SupplyChainHier> list = supplyChainHierService.selectSupplyChainHierList(supplyChainHier);
        ExcelUtil<SupplyChainHier> util = new ExcelUtil<SupplyChainHier>(SupplyChainHier.class);
        return util.exportExcel(list, "supplychainhier");
    }

    /**
     * supplyChainHier
      * 校验供应链层级名称 是否重复
      */
    @PostMapping("/checkSupplyChainHierUnique")
    @ResponseBody
    public String checkSupplyChainHierUnique(SupplyChainHier supplyChainHier)
    {
        return supplyChainHierService.checkSupplyChainHierUnique(supplyChainHier);
    }

    /**
     * 新增供应链层级
     */
    @GetMapping(value = { "/add/{supplyChainId}", "/add/" })
    public String add(@PathVariable(value = "supplyChainId", required = false) Long supplyChainId, ModelMap mmap)
    {
    // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        if (StringUtils.isNotNull(supplyChainId))
        {
            mmap.put("supplyChainHier", supplyChainHierService.selectSupplyChainHierById(supplyChainId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存供应链层级
     */
    @RequiresPermissions("platform:supplychainhier:add")
    @Log(title = "供应链层级", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(SupplyChainHier supplyChainHier)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(supplyChainHierService.checkSupplyChainHierUnique(supplyChainHier)))
        {
            return error("修改单据'" + supplyChainHier.getSupplyChainName() + "'失败，名称已存在");
        }

    // 取身份信息
        supplyChainHier.setCreateBy( ShiroUtils.getLoginName() );
        supplyChainHier.setCreateTime(DateUtils.getNowDate() );

        return toAjax(supplyChainHierService.insertSupplyChainHier(supplyChainHier));
    }

    /**
     * 修改供应链层级
     */
    @GetMapping("/edit/{supplyChainId}")
    public String edit(@PathVariable("supplyChainId") Long supplyChainId, ModelMap mmap)
    {
        SupplyChainHier supplyChainHier = supplyChainHierService.selectSupplyChainHierById(supplyChainId);
        mmap.put("supplyChainHier", supplyChainHier);

        // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        return prefix + "/edit";
    }

    /**
     * 修改保存供应链层级
     */
    @RequiresPermissions("platform:supplychainhier:edit")
    @Log(title = "供应链层级", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(SupplyChainHier supplyChainHier)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(supplyChainHierService.checkSupplyChainHierUnique(supplyChainHier)))
        {
            return error("修改单据'" + supplyChainHier.getSupplyChainName() + "'失败，名称已存在");
        }

        if (supplyChainHier.getParentId().equals(supplyChainHier.getSupplyChainId()))
        {
            return error("修改供应链层级 '" + supplyChainHier.getSupplyChainName() + "'失败，上级supplyChainHier.getSupplyChainName()不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, supplyChainHier.getStatus())
                && supplyChainHierService.selectNormalChildrenSupplyChainHierById(supplyChainHier.getSupplyChainId()) > 0)
        {
            return AjaxResult.error("该供应链层级包含未停用的子供应链层级！");
        }

        supplyChainHier.setUpdateBy( ShiroUtils.getLoginName() );
        supplyChainHier.setUpdateTime( DateUtils.getNowDate() );
        return toAjax(supplyChainHierService.updateSupplyChainHier(supplyChainHier));
    }

    /**
     * 加载供应链层级列表树（排除下级）
     */
    @GetMapping("/treeData/{excludeId}")
    @ResponseBody
    public List<Ztree> treeDataExcludeChild(@PathVariable(value = "excludeId", required = false) Long excludeId)
    {
        SupplyChainHier supplyChainHier = new SupplyChainHier();
        supplyChainHier.setSupplyChainId(excludeId);
        List<Ztree> ztrees = supplyChainHierService.selectSupplyChainHierTreeExcludeChild(supplyChainHier);
        return ztrees;
    }


    /**
     * 删除
     */
    @RequiresPermissions("platform:supplychainhier:remove")
    @Log(title = "供应链层级", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{supplyChainId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("supplyChainId") Long supplyChainId)
    {
        return toAjax(supplyChainHierService.deleteSupplyChainHierById(supplyChainId));
    }
    /**
     * 选择供应链层级树
     */
    @GetMapping(value = { "/selectSupplychainhierTree/{supplyChainId}", "/selectSupplychainhierTree/" })
    public String selectSupplychainhierTree(@PathVariable(value = "supplyChainId", required = false) Long supplyChainId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(supplyChainId))
        {
            mmap.put("supplyChainHier", supplyChainHierService.selectSupplyChainHierById(supplyChainId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载供应链层级树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = supplyChainHierService.selectSupplyChainHierTree();
        return ztrees;
    }
}
