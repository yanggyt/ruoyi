package com.ruoyi.kettle.controller;

import java.util.List;

import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.kettle.repo.RepoTree;
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
import com.ruoyi.kettle.domain.XRepository;
import com.ruoyi.kettle.service.IXRepositoryService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 资源库Controller
 * 
 * @author kone
 * @date 2021-07-12
 */
@Controller
@RequestMapping("/kettle/repository")
public class XRepositoryController extends BaseController
{
    private String prefix = "kettle/repository";

    @Autowired
    private IXRepositoryService xRepositoryService;

    @RequiresPermissions("kettle:repository:view")
    @GetMapping()
    public String repository()
    {
        return prefix + "/repository";
    }

    /**
     * 查询资源库列表
     */
    @RequiresPermissions("kettle:repository:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(XRepository xRepository)
    {
        startPage();
        List<XRepository> list = xRepositoryService.selectXRepositoryList(xRepository);
        return getDataTable(list);
    }
    @GetMapping(value = { "/selectRepositoryTree", "/selectRepositoryTree/{excludeId}" })
    public String selectRepositoryTree( @PathVariable(value = "excludeId", required = false) String excludeId, ModelMap mmap)
    {
        XRepository r=new XRepository();
        List<XRepository> repoTree = xRepositoryService.selectXRepositoryList(r);
        XRepository repository=xRepositoryService.selectXRepositoryById(2L);
        mmap.put("repository", repository);
        mmap.put("repoTree", repoTree);
        mmap.put("excludeId", excludeId);
        return "kettle/common/repository_tree";
    }
    @GetMapping("/repositoryRoot")
    @ResponseBody
    public List<RepoTree> repositoryRoot()
    {
        List<RepoTree> ztrees = xRepositoryService.selectRepoRoot(new XRepository());
        return ztrees;
    }
    @PostMapping("/qryRepoSubTree/{id}")
    @ResponseBody
    public List<RepoTree> qryRepoSubTree(@PathVariable("id") Long id, ModelMap mmapy) {
        List<RepoTree> ztrees = xRepositoryService.selectRepoTree(id);
        return ztrees;
    }
    /**
     * 导出资源库列表
     */
    @RequiresPermissions("kettle:repository:export")
    @Log(title = "资源库", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(XRepository xRepository)
    {
        List<XRepository> list = xRepositoryService.selectXRepositoryList(xRepository);
        ExcelUtil<XRepository> util = new ExcelUtil<XRepository>(XRepository.class);
        return util.exportExcel(list, "资源库数据");
    }

    /**
     * 新增资源库
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存资源库
     */
    @RequiresPermissions("kettle:repository:add")
    @Log(title = "资源库", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(XRepository xRepository)
    {
        return toAjax(xRepositoryService.insertXRepository(xRepository));
    }

    /**
     * 修改资源库
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        XRepository xRepository = xRepositoryService.selectXRepositoryById(id);
        mmap.put("xRepository", xRepository);
        return prefix + "/edit";
    }

    /**
     * 修改保存资源库
     */
    @RequiresPermissions("kettle:repository:edit")
    @Log(title = "资源库", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(XRepository xRepository)
    {
        return toAjax(xRepositoryService.updateXRepository(xRepository));
    }

    /**
     * 删除资源库
     */
    @RequiresPermissions("kettle:repository:remove")
    @Log(title = "资源库", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(xRepositoryService.deleteXRepositoryByIds(ids));
    }
}
