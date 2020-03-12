package cn.com.infosouth.arj21.controller.aircraftmanage;

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
import cn.com.infosouth.common.annotation.Log;
import cn.com.infosouth.common.enums.BusinessType;
import cn.com.infosouth.arj21.domain.InfoAircraft;
import cn.com.infosouth.arj21.service.IInfoAircraftService;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.common.core.domain.AjaxResult;
import cn.com.infosouth.common.utils.poi.ExcelUtil;
import cn.com.infosouth.common.core.page.TableDataInfo;

/**
 * 飞机Controller
 * 
 * @author kxnf
 * @date 2020-03-05
 */
@Controller
@RequestMapping("/arj21/aircraft_manage")
public class InfoAircraftController extends BaseController
{
    private String prefix = "arj21/aircraft_manage";

    @Autowired
    private IInfoAircraftService infoAircraftService;

    @RequiresPermissions("arj21:aircraft_manage:view")
    @GetMapping()
    public String aircraft_manage()
    {
        return prefix + "/aircraft_manage";
    }

    /**
     * 查询飞机列表
     */
    @RequiresPermissions("arj21:aircraft_manage:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(InfoAircraft infoAircraft)
    {
        startPage();
        List<InfoAircraft> list = infoAircraftService.selectInfoAircraftList(infoAircraft);
        return getDataTable(list);
    }

    /**
     * 导出飞机列表
     */
    @RequiresPermissions("arj21:aircraft_manage:export")
    @Log(title = "飞机", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InfoAircraft infoAircraft)
    {
        List<InfoAircraft> list = infoAircraftService.selectInfoAircraftList(infoAircraft);
        ExcelUtil<InfoAircraft> util = new ExcelUtil<InfoAircraft>(InfoAircraft.class);
        return util.exportExcel(list, "aircraft_manage");
    }

    /**
     * 新增飞机
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存飞机
     */
    @RequiresPermissions("arj21:aircraft_manage:add")
    @Log(title = "飞机", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InfoAircraft infoAircraft)
    {
        return toAjax(infoAircraftService.insertInfoAircraft(infoAircraft));
    }

    /**
     * 修改飞机
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        InfoAircraft infoAircraft = infoAircraftService.selectInfoAircraftById(id);
        mmap.put("infoAircraft", infoAircraft);
        return prefix + "/edit";
    }

    /**
     * 修改保存飞机
     */
    @RequiresPermissions("arj21:aircraft_manage:edit")
    @Log(title = "飞机", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InfoAircraft infoAircraft)
    {
        return toAjax(infoAircraftService.updateInfoAircraft(infoAircraft));
    }

    /**
     * 删除飞机
     */
    @RequiresPermissions("arj21:aircraft_manage:remove")
    @Log(title = "飞机", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(infoAircraftService.deleteInfoAircraftByIds(ids));
    }
}
