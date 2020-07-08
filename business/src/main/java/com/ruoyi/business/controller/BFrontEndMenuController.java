package com.ruoyi.business.controller;

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
import com.ruoyi.business.domain.BFrontEndMenu;
import com.ruoyi.business.service.IBFrontEndMenuService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.core.domain.Ztree;

/**
 * 前端菜单Controller
 *
 * @author anjie
 * @date 2020-06-24
 */
@Controller
@RequestMapping("/business/menu")
public class BFrontEndMenuController extends BaseController
{
    private String prefix = "business/menu";

    @Autowired
    private IBFrontEndMenuService bFrontEndMenuService;

    @RequiresPermissions("business:menu:view")
    @GetMapping()
    public String menu()
    {
        return prefix + "/menu";
    }

    /**
     * 查询前端菜单树列表
     */
    @RequiresPermissions("business:menu:list")
    @PostMapping("/list")
    @ResponseBody
    public List<BFrontEndMenu> list(BFrontEndMenu bFrontEndMenu)
    {
        List<BFrontEndMenu> list = bFrontEndMenuService.selectBFrontEndMenuList(bFrontEndMenu);
        return list;
    }

    /**
     * 导出前端菜单列表
     */
    @RequiresPermissions("business:menu:export")
    @Log(title = "前端菜单", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BFrontEndMenu bFrontEndMenu)
    {
        List<BFrontEndMenu> list = bFrontEndMenuService.selectBFrontEndMenuList(bFrontEndMenu);
        ExcelUtil<BFrontEndMenu> util = new ExcelUtil<BFrontEndMenu>(BFrontEndMenu.class);
        return util.exportExcel(list, "menu");
    }

    /**
     * 新增前端菜单
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("bFrontEndMenu", bFrontEndMenuService.selectBFrontEndMenuById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存前端菜单
     */
    @RequiresPermissions("business:menu:add")
    @Log(title = "前端菜单", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BFrontEndMenu bFrontEndMenu)
    {
        return toAjax(bFrontEndMenuService.insertBFrontEndMenu(bFrontEndMenu));
    }

    /**
     * 修改前端菜单
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        BFrontEndMenu bFrontEndMenu = bFrontEndMenuService.selectBFrontEndMenuById(id);
        mmap.put("bFrontEndMenu", bFrontEndMenu);
        return prefix + "/edit";
    }

    /**
     * 修改保存前端菜单
     */
    @RequiresPermissions("business:menu:edit")
    @Log(title = "前端菜单", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BFrontEndMenu bFrontEndMenu)
    {
        return toAjax(bFrontEndMenuService.updateBFrontEndMenu(bFrontEndMenu));
    }

    /**
     * 删除
     */
    @RequiresPermissions("business:menu:remove")
    @Log(title = "前端菜单", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") Long id)
    {
        return toAjax(bFrontEndMenuService.deleteBFrontEndMenuById(id));
    }

    /**
     * 选择前端菜单树
     */
    @GetMapping(value = { "/selectMenuTree/{id}", "/selectMenuTree/" })
    public String selectMenuTree(@PathVariable(value = "id", required = false) Long id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("bFrontEndMenu", bFrontEndMenuService.selectBFrontEndMenuById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载前端菜单树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = bFrontEndMenuService.selectBFrontEndMenuTree();
        return ztrees;
    }
}
