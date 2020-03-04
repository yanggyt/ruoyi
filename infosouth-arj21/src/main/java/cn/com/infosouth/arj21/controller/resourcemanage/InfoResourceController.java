package cn.com.infosouth.arj21.controller.resourcemanage;

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
import cn.com.infosouth.arj21.domain.InfoResource;
import cn.com.infosouth.arj21.service.IInfoResourceService;
import cn.com.infosouth.common.annotation.Log;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.common.core.domain.AjaxResult;
import cn.com.infosouth.common.core.domain.Ztree;
import cn.com.infosouth.common.enums.BusinessType;
import cn.com.infosouth.common.utils.StringUtils;
import cn.com.infosouth.common.utils.poi.ExcelUtil;

/**
 * 资源目录Controller
 * 
 * @author kxnf
 * @date 2020-03-04
 */
@Controller
@RequestMapping("/arj21/resource-manage")
public class InfoResourceController extends BaseController
{
    private String prefix = "arj21/resource-manage";

    @Autowired
    private IInfoResourceService infoResourceService;

    @RequiresPermissions("arj21:resource:view")
    @GetMapping()
    public String resource()
    {
        return prefix + "/resource";
    }

    /**
     * 查询资源目录树列表
     */
    @RequiresPermissions("arj21:resource:list")
    @PostMapping("/list")
    @ResponseBody
    public List<InfoResource> list(InfoResource infoResource)
    {
        List<InfoResource> list = infoResourceService.selectInfoResourceList(infoResource);
        return list;
    }

    /**
     * 导出资源目录列表
     */
    @RequiresPermissions("arj21:resource:export")
    @Log(title = "资源目录", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InfoResource infoResource)
    {
        List<InfoResource> list = infoResourceService.selectInfoResourceList(infoResource);
        ExcelUtil<InfoResource> util = new ExcelUtil<InfoResource>(InfoResource.class);
        return util.exportExcel(list, "resource");
    }

    /**
     * 新增资源目录
     */
    @GetMapping(value = { "/add/{id}", "/add/" })
    public String add(@PathVariable(value = "id", required = false) String id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("infoResource", infoResourceService.selectInfoResourceById(id));
        }
        return prefix + "/add";
    }

    /**
     * 新增保存资源目录
     */
    @RequiresPermissions("arj21:resource:add")
    @Log(title = "资源目录", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InfoResource infoResource)
    {
        return toAjax(infoResourceService.insertInfoResource(infoResource));
    }

    /**
     * 修改资源目录
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        InfoResource infoResource = infoResourceService.selectInfoResourceById(id);
        mmap.put("infoResource", infoResource);
        return prefix + "/edit";
    }

    /**
     * 修改保存资源目录
     */
    @RequiresPermissions("arj21:resource:edit")
    @Log(title = "资源目录", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InfoResource infoResource)
    {
        return toAjax(infoResourceService.updateInfoResource(infoResource));
    }

    /**
     * 删除
     */
    @RequiresPermissions("arj21:resource:remove")
    @Log(title = "资源目录", businessType = BusinessType.DELETE)
    @GetMapping("/remove/{id}")
    @ResponseBody
    public AjaxResult remove(@PathVariable("id") String id)
    {
        return toAjax(infoResourceService.deleteInfoResourceById(id));
    }

    /**
     * 选择资源目录树
     */
    @GetMapping(value = { "/selectResourceTree/{id}", "/selectResourceTree/" })
    public String selectResourceTree(@PathVariable(value = "id", required = false) String id, ModelMap mmap)
    {
        if (StringUtils.isNotNull(id))
        {
            mmap.put("infoResource", infoResourceService.selectInfoResourceById(id));
        }
        return prefix + "/tree";
    }

    /**
     * 加载资源目录树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData()
    {
        List<Ztree> ztrees = infoResourceService.selectInfoResourceTree();
        return ztrees;
    }
}
