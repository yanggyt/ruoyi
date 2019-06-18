package com.ruoyi.web.controller.template;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.template.domain.TmplServer;
import com.ruoyi.template.service.ITmplServerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 服务器模板 信息操作处理
 *
 * @author TP
 * @date 2019-06-12
 */
@Controller
@RequestMapping("/template/tmplServer")
public class TmplServerController extends BaseController {
    private String prefix = "template/tmplServer";

    @Autowired
    private ITmplServerService tmplServerService;

    @RequiresPermissions("template:tmplServer:view")
    @GetMapping()
    public String tmplServer() {
        return prefix + "/tmplServer";
    }

    /**
     * 查询服务器模板列表
     */
    @RequiresPermissions("template:tmplServer:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(TmplServer tmplServer) {
        startPage();
        List<TmplServer> list = tmplServerService.selectTmplServerList(tmplServer);
        return getDataTable(list);
    }

    /**
     * 导出服务器模板列表
     */
    @RequiresPermissions("template:tmplServer:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(TmplServer tmplServer) {
        List<TmplServer> list = tmplServerService.selectTmplServerList(tmplServer);
        ExcelUtil<TmplServer> util = new ExcelUtil<TmplServer>(TmplServer.class);
        return util.exportExcel(list, "tmplServer");
    }

    /**
     * 新增服务器模板
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存服务器模板
     */
    @RequiresPermissions("template:tmplServer:add")
    @Log(title = "服务器模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(TmplServer tmplServer) {
        return toAjax(tmplServerService.insertTmplServer(tmplServer));
    }

    /**
     * 修改服务器模板
     */
    @GetMapping("/edit/{serverId}")
    public String edit(@PathVariable("serverId") Integer serverId, ModelMap mmap) {
        TmplServer tmplServer = tmplServerService.selectTmplServerById(serverId);
        mmap.put("tmplServer", tmplServer);
        return prefix + "/edit";
    }

    /**
     * 修改保存服务器模板
     */
    @RequiresPermissions("template:tmplServer:edit")
    @Log(title = "服务器模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(TmplServer tmplServer) {
        return toAjax(tmplServerService.updateTmplServer(tmplServer));
    }

    /**
     * 删除服务器模板
     */
    @RequiresPermissions("template:tmplServer:remove")
    @Log(title = "服务器模板", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(tmplServerService.deleteTmplServerByIds(ids));
    }

}
