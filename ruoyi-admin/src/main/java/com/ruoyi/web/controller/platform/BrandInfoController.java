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
import com.ruoyi.province.platform.domain.BrandInfo;
import com.ruoyi.province.platform.service.IBrandInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 品牌资料Controller
 * 
 * @author dalin
 * @date 2020-12-23
 */
@Controller
@RequestMapping("/platform/brandinfo")
public class BrandInfoController extends BaseController
{
    private String prefix = "platform/brandinfo";

    @Autowired
    private IBrandInfoService brandInfoService;

    @RequiresPermissions("platform:brandinfo:view")
    @GetMapping()
    public String brandinfo()
    {
        return prefix + "/brandinfo";
    }

    /**
     * 查询品牌资料列表
     */
    @RequiresPermissions("platform:brandinfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BrandInfo brandInfo)
    {
        startPage();
        List<BrandInfo> list = brandInfoService.selectBrandInfoList(brandInfo);
        return getDataTable(list);
    }

    /**
     * 导出品牌资料列表
     */
    @RequiresPermissions("platform:brandinfo:export")
    @Log(title = "品牌资料", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BrandInfo brandInfo)
    {
        List<BrandInfo> list = brandInfoService.selectBrandInfoList(brandInfo);
        ExcelUtil<BrandInfo> util = new ExcelUtil<BrandInfo>(BrandInfo.class);
        return util.exportExcel(list, "brandinfo");
    }

    /**
      * 校验品牌资料名称 是否重复
      */
    @PostMapping("/checkBrandInfoUnique")
    @ResponseBody
    public String checkBrandInfoUnique(BrandInfo brandInfo)
    {
        return brandInfoService.checkBrandInfoUnique(brandInfo);
    }

    /**
     * 新增品牌资料
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
      // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        return prefix + "/add";
    }

    /**
     * 新增保存品牌资料
     */
    @RequiresPermissions("platform:brandinfo:add")
    @Log(title = "品牌资料", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BrandInfo brandInfo)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(brandInfoService.checkBrandInfoUnique(brandInfo)))
        {
            return error("修改单据'" + brandInfo.getBrandName() + "'失败，名称已存在");
        }

    // 取身份信息
        brandInfo.setCreateBy( ShiroUtils.getLoginName() );
        brandInfo.setCreateTime(DateUtils.getNowDate() );

        return toAjax(brandInfoService.insertBrandInfo(brandInfo));
    }

    /**
     * 修改品牌资料
     */
    @GetMapping("/edit/{brandId}")
    public String edit(@PathVariable("brandId") Long brandId, ModelMap mmap)
    {
        BrandInfo brandInfo = brandInfoService.selectBrandInfoById(brandId);
        mmap.put("brandInfo", brandInfo);

                    // 取身份信息
                            mmap.put("user", ShiroUtils.getSysUser() );
                    
        return prefix + "/edit";
    }

    /**
     * 修改保存品牌资料
     */
    @RequiresPermissions("platform:brandinfo:edit")
    @Log(title = "品牌资料", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BrandInfo brandInfo)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(brandInfoService.checkBrandInfoUnique(brandInfo)))
        {
            return error("修改单据'" + brandInfo.getBrandName() + "'失败，名称已存在");
        }

        brandInfo.setUpdateBy( ShiroUtils.getLoginName() );
        brandInfo.setUpdateTime( DateUtils.getNowDate() );
        return toAjax(brandInfoService.updateBrandInfo(brandInfo));
    }

    /**
     * 删除品牌资料
     */
    @RequiresPermissions("platform:brandinfo:remove")
    @Log(title = "品牌资料", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(brandInfoService.deleteBrandInfoByIds(ids));
    }
}
