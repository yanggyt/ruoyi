package com.ruoyi.web.controller.platform;

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
import com.ruoyi.province.platform.domain.Regional;
import com.ruoyi.province.platform.service.IRegionalService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;

/**
 * 地区资料Controller
 * 
 * @author dalin
 * @date 2021-01-11
 */
@Controller
@RequestMapping("/platform/regional")
public class RegionalController extends BaseController
{
    private String prefix = "platform/regional";

    @Autowired
    private IRegionalService regionalService;

    @RequiresPermissions("platform:regional:view")
    @GetMapping()
    public String regional()
    {
        return prefix + "/regional";
    }

    /**
     * 查询地区资料树列表
     */
    @RequiresPermissions("platform:regional:list")
    @PostMapping("/list")
    @ResponseBody
    public List<Regional> list(Regional regional)
    {
        List<Regional> list = regionalService.selectRegionalList(regional);
        return list;
    }

    /**
     * 导出地区资料列表
     */
    @RequiresPermissions("platform:regional:export")
    @Log(title = "地区资料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Regional regional)
    {
        List<Regional> list = regionalService.selectRegionalList(regional);
        ExcelUtil<Regional> util = new ExcelUtil<Regional>(Regional.class);
        return util.exportExcel(list, "regional");
    }

    /**
     * regional
      * 校验地区资料名称 是否重复
      */
    @PostMapping("/checkRegionalUnique")
    @ResponseBody
    public String checkRegionalUnique(Regional regional)
    {
        return regionalService.checkRegionalUnique(regional);
    }

    /**
     * 新增地区资料
     */
    @GetMapping(value = { "/add/{regionalId}", "/add/" })
    public String add(@PathVariable(value = "regionalId", required = false) Long regionalId, ModelMap mmap)
    {
    // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        if (StringUtils.isNotNull(regionalId))
        {
            mmap.put("regional", regionalService.selectRegionalById(regionalId));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存地区资料
     */
    @RequiresPermissions("platform:regional:add")
    @Log(title = "地区资料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Regional regional)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(regionalService.checkRegionalUnique(regional)))
        {
            return error("修改单据'" + regional.getRegionalName() + "'失败，名称已存在");
        }

    // 取身份信息
        regional.setCreateBy( ShiroUtils.getLoginName() );
        regional.setCreateTime(DateUtils.getNowDate() );

        return toAjax(regionalService.insertRegional(regional));
    }

    /**
     * 修改地区资料
     */
    @GetMapping("/edit/{regionalId}")
    public String edit(@PathVariable("regionalId") Long regionalId, ModelMap mmap)
    {
        Regional regional = regionalService.selectRegionalById(regionalId);
        mmap.put("regional", regional);

        // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        return prefix + "/edit";
    }

    /**
     * 修改保存地区资料
     */
    @RequiresPermissions("platform:regional:edit")
    @Log(title = "地区资料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Regional regional)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(regionalService.checkRegionalUnique(regional)))
        {
            return error("修改单据'" + regional.getRegionalName() + "'失败，名称已存在");
        }

        regional.setUpdateBy( ShiroUtils.getLoginName() );
        regional.setUpdateTime( DateUtils.getNowDate() );
        return toAjax(regionalService.updateRegional(regional));
    }

    /**
     * 删除
     */
    @RequiresPermissions("platform:regional:remove")
    @Log(title = "地区资料", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{regionalId}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("regionalId") Long regionalId)
    {
        return toAjax(regionalService.deleteRegionalById(regionalId));
    }

    /**
     * 选择地区资料树
     */
    @GetMapping(value = { "/selectRegionalTree/{regionalId}", "/selectRegionalTree/" })
    public String selectRegionalTree(@PathVariable(value = "regionalId", required = false) Long regionalId, ModelMap mmap)
    {
        if (StringUtils.isNotNull(regionalId))
        {
            mmap.put("regional", regionalService.selectRegionalById(regionalId));
        }
        return prefix + "/tree";
    }

    /**
     * 加载地区资料树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = regionalService.selectRegionalTree();
        return ztrees;
    }
}
