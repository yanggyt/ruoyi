package com.ruoyi.his.controller;

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
import com.ruoyi.his.domain.HisEhealthClient;
import com.ruoyi.his.service.IHisEhealthClientService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 健康卡Controller
 * 
 * @author bend
 * @date 2020-07-27
 */
@Controller
@RequestMapping("/his/ehealthClient")
public class HisEhealthClientController extends BaseController
{
    private String prefix = "his/ehealthClient";

    @Autowired
    private IHisEhealthClientService hisEhealthClientService;

    @RequiresPermissions("his:ehealthClient:view")
    @GetMapping()
    public String ehealthClient()
    {
        return prefix + "/ehealthClient";
    }

    /**
     * 查询健康卡列表
     */
    @RequiresPermissions("his:ehealthClient:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisEhealthClient hisEhealthClient)
    {
        startPage();
        List<HisEhealthClient> list = hisEhealthClientService.selectHisEhealthClientList(hisEhealthClient);
        return getDataTable(list);
    }

    /**
     * 导出健康卡列表
     */
    @RequiresPermissions("his:ehealthClient:export")
    @Log(title = "健康卡", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisEhealthClient hisEhealthClient)
    {
        List<HisEhealthClient> list = hisEhealthClientService.selectHisEhealthClientList(hisEhealthClient);
        ExcelUtil<HisEhealthClient> util = new ExcelUtil<HisEhealthClient>(HisEhealthClient.class);
        return util.exportExcel(list, "ehealthClient");
    }

    /**
     * 新增健康卡
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存健康卡
     */
    @RequiresPermissions("his:ehealthClient:add")
    @Log(title = "健康卡", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisEhealthClient hisEhealthClient)
    {
        return toAjax(hisEhealthClientService.insertHisEhealthClient(hisEhealthClient));
    }

    /**
     * 修改健康卡
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisEhealthClient hisEhealthClient = hisEhealthClientService.selectHisEhealthClientById(id);
        mmap.put("hisEhealthClient", hisEhealthClient);
        return prefix + "/edit";
    }

    /**
     * 修改保存健康卡
     */
    @RequiresPermissions("his:ehealthClient:edit")
    @Log(title = "健康卡", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisEhealthClient hisEhealthClient)
    {
        return toAjax(hisEhealthClientService.updateHisEhealthClient(hisEhealthClient));
    }

    /**
     * 删除健康卡
     */
    @RequiresPermissions("his:ehealthClient:remove")
    @Log(title = "健康卡", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisEhealthClientService.deleteHisEhealthClientByIds(ids));
    }
}
