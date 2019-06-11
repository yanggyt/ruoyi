package com.ruoyi.web.controller.template;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.template.domain.Switch;
import com.ruoyi.template.service.ISwitchService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 交换机模板 信息操作处理
 *
 * @author TP
 * @date 2019-06-11
 */
@Controller
@RequestMapping("/template/switch")
public class SwitchController extends BaseController {
    private String prefix = "template/switch" ;

    @Autowired
    private ISwitchService switchService;

    @RequiresPermissions("template:switch:view")
    @GetMapping()
    public String switchTemplate() {
        return prefix + "/switch" ;
    }

    /**
     * 查询交换机模板列表
     */
    @RequiresPermissions("template:switch:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(Switch switchTemplate) {
        startPage();
        List<Switch> list = switchService.selectSwitchList(switchTemplate);
        return getDataTable(list);
    }


    /**
     * 导出交换机模板列表
     */
    @RequiresPermissions("template:switch:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Switch switchTemplate) {
        List<Switch> list = switchService.selectSwitchList(switchTemplate);
        ExcelUtil<Switch> util = new ExcelUtil<Switch>(Switch.class);
        return util.exportExcel(list, "switch");
    }

    /**
     * 新增交换机模板
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add" ;
    }

    /**
     * 新增保存交换机模板
     */
    @RequiresPermissions("template:switch:add")
    @Log(title = "交换机模板", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Switch switchTemplate) {
        return toAjax(switchService.insertSwitch(switchTemplate));
    }

    /**
     * 修改交换机模板
     */
    @GetMapping("/edit/{serverId}")
    public String edit(@PathVariable("serverId") Integer serverId, ModelMap mmap) {
        Switch switchTemplate = switchService.selectSwitchById(serverId);
        mmap.put("switch", switchTemplate);
        return prefix + "/edit" ;
    }

    /**
     * 修改保存交换机模板
     */
    @RequiresPermissions("template:switch:edit")
    @Log(title = "交换机模板", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Switch switchTemplate) {
        return toAjax(switchService.updateSwitch(switchTemplate));
    }

    /**
     * 删除交换机模板
     */
    @RequiresPermissions("template:switch:remove")
    @Log(title = "交换机模板", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(switchService.deleteSwitchByIds(ids));
    }

}
