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
import com.ruoyi.province.platform.domain.PurchUserType;
import com.ruoyi.province.platform.service.IPurchUserTypeService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;

/**
 * 购货用户类型Controller
 * 
 * @author dalin
 * @date 2021-01-14
 */
@Controller
@RequestMapping("/platform/purchusertype")
public class PurchUserTypeController extends BaseController
{
    private String prefix = "platform/purchusertype";

    @Autowired
    private IPurchUserTypeService purchUserTypeService;

    @RequiresPermissions("platform:purchusertype:view")
    @GetMapping()
    public String purchusertype()
    {
        return prefix + "/purchusertype";
    }

    /**
     * 查询购货用户类型树列表
     */
    @RequiresPermissions("platform:purchusertype:list")
    @PostMapping("/list")
    @ResponseBody
    public List<PurchUserType> list(PurchUserType purchUserType)
    {
        List<PurchUserType> list = purchUserTypeService.selectPurchUserTypeList(purchUserType);
        return list;
    }

    /**
     * 导出购货用户类型列表
     */
    @RequiresPermissions("platform:purchusertype:export")
    @Log(title = "购货用户类型", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(PurchUserType purchUserType)
    {
        List<PurchUserType> list = purchUserTypeService.selectPurchUserTypeList(purchUserType);
        ExcelUtil<PurchUserType> util = new ExcelUtil<PurchUserType>(PurchUserType.class);
        return util.exportExcel(list, "purchusertype");
    }

    /**
     * purchUserType
      * 校验购货用户类型名称 是否重复
      */
    @PostMapping("/checkPurchUserTypeUnique")
    @ResponseBody
    public String checkPurchUserTypeUnique(PurchUserType purchUserType)
    {
        return purchUserTypeService.checkPurchUserTypeUnique(purchUserType);
    }

    /**
     * 新增购货用户类型
     */
    @GetMapping(value = { "/add/{purchTypeId}", "/add/" })
    public String add(@PathVariable(value = "purchTypeId", required = false) Long purchTypeId, ModelMap mmap)
    {
    // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        if (StringUtils.isNotNull(purchTypeId))
        {
            mmap.put("purchUserType", purchUserTypeService.selectPurchUserTypeById(purchTypeId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存购货用户类型
     */
    @RequiresPermissions("platform:purchusertype:add")
    @Log(title = "购货用户类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(PurchUserType purchUserType)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(purchUserTypeService.checkPurchUserTypeUnique(purchUserType)))
        {
            return error("修改单据'" + purchUserType.getPurchTypeName() + "'失败，名称已存在");
        }

    // 取身份信息
        purchUserType.setCreateBy( ShiroUtils.getLoginName() );
        purchUserType.setCreateTime(DateUtils.getNowDate() );

        return toAjax(purchUserTypeService.insertPurchUserType(purchUserType));
    }

    /**
     * 修改购货用户类型
     */
    @GetMapping("/edit/{purchTypeId}")
    public String edit(@PathVariable("purchTypeId") Long purchTypeId, ModelMap mmap)
    {
        PurchUserType purchUserType = purchUserTypeService.selectPurchUserTypeById(purchTypeId);
        mmap.put("purchUserType", purchUserType);

        // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        return prefix + "/edit";
    }

    /**
     * 修改保存购货用户类型
     */
    @RequiresPermissions("platform:purchusertype:edit")
    @Log(title = "购货用户类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(PurchUserType purchUserType)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(purchUserTypeService.checkPurchUserTypeUnique(purchUserType)))
        {
            return error("修改单据'" + purchUserType.getPurchTypeName() + "'失败，名称已存在");
        }

        if (purchUserType.getParentId().equals(purchUserType.getPurchTypeId()))
        {
            return error("修改购货用户类型 '" + purchUserType.getPurchTypeName() + "'失败，上级purchUserType.getPurchTypeName()不能是自己");
        }
        else if (StringUtils.equals(UserConstants.DEPT_DISABLE, purchUserType.getStatus())
                && purchUserTypeService.selectNormalChildrenPurchUserTypeById(purchUserType.getPurchTypeId()) > 0)
        {
            return AjaxResult.error("该购货用户类型包含未停用的子购货用户类型！");
        }

        purchUserType.setUpdateBy( ShiroUtils.getLoginName() );
        purchUserType.setUpdateTime( DateUtils.getNowDate() );
        return toAjax(purchUserTypeService.updatePurchUserType(purchUserType));
    }

    /**
     * 加载购货用户类型列表树（排除下级）
     */
    @GetMapping("/treeData/{excludeId}")
    @ResponseBody
    public List<Ztree> treeDataExcludeChild(@PathVariable(value = "excludeId", required = false) Long excludeId)
    {
        PurchUserType purchUserType = new PurchUserType();
        purchUserType.setPurchTypeId(excludeId);
        List<Ztree> ztrees = purchUserTypeService.selectPurchUserTypeTreeExcludeChild(purchUserType);
        return ztrees;
    }


    /**
     * 删除
     */
    @RequiresPermissions("platform:purchusertype:remove")
    @Log(title = "购货用户类型", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{purchTypeId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("purchTypeId") Long purchTypeId)
    {
        return toAjax(purchUserTypeService.deletePurchUserTypeById(purchTypeId));
    }
    /**
     * 选择购货用户类型树
     */
    @GetMapping(value = { "/selectPurchusertypeTree/{purchTypeId}", "/selectPurchusertypeTree/" })
    public String selectPurchusertypeTree(@PathVariable(value = "purchTypeId", required = false) Long purchTypeId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(purchTypeId))
        {
            mmap.put("purchUserType", purchUserTypeService.selectPurchUserTypeById(purchTypeId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载购货用户类型树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = purchUserTypeService.selectPurchUserTypeTree();
        return ztrees;
    }
}
