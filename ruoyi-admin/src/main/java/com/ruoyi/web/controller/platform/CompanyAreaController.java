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
import com.ruoyi.province.platform.domain.CompanyArea;
import com.ruoyi.province.platform.service.ICompanyAreaService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 营业面积Controller
 * 
 * @author dalin
 * @date 2020-12-23
 */
@Controller
@RequestMapping("/platform/companyarea")
public class CompanyAreaController extends BaseController
{
    private String prefix = "platform/companyarea";

    @Autowired
    private ICompanyAreaService companyAreaService;

    @RequiresPermissions("platform:companyarea:view")
    @GetMapping()
    public String companyarea()
    {
        return prefix + "/companyarea";
    }

    /**
     * 查询营业面积列表
     */
    @RequiresPermissions("platform:companyarea:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(CompanyArea companyArea)
    {
        startPage();
        List<CompanyArea> list = companyAreaService.selectCompanyAreaList(companyArea);
        return getDataTable(list);
    }

    /**
     * 导出营业面积列表
     */
    @RequiresPermissions("platform:companyarea:export")
    @Log(title = "营业面积", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(CompanyArea companyArea)
    {
        List<CompanyArea> list = companyAreaService.selectCompanyAreaList(companyArea);
        ExcelUtil<CompanyArea> util = new ExcelUtil<CompanyArea>(CompanyArea.class);
        return util.exportExcel(list, "companyarea");
    }

    /**
      * 校验营业面积名称 是否重复
      */
    @PostMapping("/checkcompanyAreaUnique")
    @ResponseBody
    public String checkcompanyAreaUnique(CompanyArea companyArea)
    {
        return companyAreaService.checkCompanyAreaUnique(companyArea);
    }

    /**
     * 新增营业面积
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
      // 取身份信息
        mmap.put("user", ShiroUtils.getSysUser() );

        return prefix + "/add";
    }

    /**
     * 新增保存营业面积
     */
    @RequiresPermissions("platform:companyarea:add")
    @Log(title = "营业面积", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(CompanyArea companyArea)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(companyAreaService.checkCompanyAreaUnique(companyArea)))
        {
            return error("修改单据'" + companyArea.getCompanyAreaName() + "'失败，名称已存在");
        }

    // 取身份信息
        companyArea.setCreateBy( ShiroUtils.getLoginName() );
        companyArea.setCreateTime(DateUtils.getNowDate() );

        return toAjax(companyAreaService.insertCompanyArea(companyArea));
    }

    /**
     * 修改营业面积 th:value="*{createTime}"
     */
    @GetMapping("/edit/{companyAreaId}")
    public String edit(@PathVariable("companyAreaId") Long companyAreaId, ModelMap mmap)
    {
        CompanyArea companyArea = companyAreaService.selectCompanyAreaById(companyAreaId);
        mmap.put("companyArea", companyArea);

                    // 取身份信息
                            mmap.put("user", ShiroUtils.getSysUser() );
                    
        return prefix + "/edit";
    }

    /**
     * 修改保存营业面积
     */
    @RequiresPermissions("platform:companyarea:edit")
    @Log(title = "营业面积", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(CompanyArea companyArea)
    {
        // 控制名称重复!
        if (BussiConstants.DOC_NAME_NOT_UNIQUE.equals(companyAreaService.checkCompanyAreaUnique(companyArea)))
        {
            return error("修改单据'" + companyArea.getCompanyAreaName() + "'失败，名称已存在");
        }

        companyArea.setUpdateBy( ShiroUtils.getLoginName() );
        companyArea.setUpdateTime( DateUtils.getNowDate() );
        return toAjax(companyAreaService.updateCompanyArea(companyArea));
    }

    /**
     * 删除营业面积
     */
    @RequiresPermissions("platform:companyarea:remove")
    @Log(title = "营业面积", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(companyAreaService.deleteCompanyAreaByIds(ids));
    }
}
