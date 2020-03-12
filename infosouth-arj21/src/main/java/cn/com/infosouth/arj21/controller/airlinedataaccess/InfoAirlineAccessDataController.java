package cn.com.infosouth.arj21.controller.airlinedataaccess;

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
import cn.com.infosouth.arj21.domain.InfoAirlineAccessData;
import cn.com.infosouth.arj21.service.IInfoAirlineAccessDataService;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.common.core.domain.AjaxResult;
import cn.com.infosouth.common.utils.poi.ExcelUtil;
import cn.com.infosouth.common.core.page.TableDataInfo;

/**
 * 航空公司数据接入Controller
 * 
 * @author kxnf
 * @date 2020-03-06
 */
@Controller
@RequestMapping("/arj21/airline_data_access")
public class InfoAirlineAccessDataController extends BaseController
{
    private String prefix = "arj21/airline_data_access";

    @Autowired
    private IInfoAirlineAccessDataService infoAirlineAccessDataService;

    @RequiresPermissions("arj21:airline_data_access:view")
    @GetMapping()
    public String airline_data_access()
    {
        return prefix + "/airline_data_access";
    }

    /**
     * 查询航空公司数据接入列表
     */
    @RequiresPermissions("arj21:airline_data_access:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(InfoAirlineAccessData infoAirlineAccessData)
    {
        startPage();
        List<InfoAirlineAccessData> list = infoAirlineAccessDataService.selectInfoAirlineAccessDataList(infoAirlineAccessData);
        return getDataTable(list);
    }

    /**
     * 导出航空公司数据接入列表
     */
    @RequiresPermissions("arj21:airline_data_access:export")
    @Log(title = "航空公司数据接入", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(InfoAirlineAccessData infoAirlineAccessData)
    {
        List<InfoAirlineAccessData> list = infoAirlineAccessDataService.selectInfoAirlineAccessDataList(infoAirlineAccessData);
        ExcelUtil<InfoAirlineAccessData> util = new ExcelUtil<InfoAirlineAccessData>(InfoAirlineAccessData.class);
        return util.exportExcel(list, "airline_data_access");
    }

    /**
     * 新增航空公司数据接入
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存航空公司数据接入
     */
    @RequiresPermissions("arj21:airline_data_access:add")
    @Log(title = "航空公司数据接入", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(InfoAirlineAccessData infoAirlineAccessData)
    {
        return toAjax(infoAirlineAccessDataService.insertInfoAirlineAccessData(infoAirlineAccessData));
    }

    /**
     * 修改航空公司数据接入
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        InfoAirlineAccessData infoAirlineAccessData = infoAirlineAccessDataService.selectInfoAirlineAccessDataById(id);
        mmap.put("infoAirlineAccessData", infoAirlineAccessData);
        return prefix + "/edit";
    }

    /**
     * 修改保存航空公司数据接入
     */
    @RequiresPermissions("arj21:airline_data_access:edit")
    @Log(title = "航空公司数据接入", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(InfoAirlineAccessData infoAirlineAccessData)
    {
        return toAjax(infoAirlineAccessDataService.updateInfoAirlineAccessData(infoAirlineAccessData));
    }

    /**
     * 删除航空公司数据接入
     */
    @RequiresPermissions("arj21:airline_data_access:remove")
    @Log(title = "航空公司数据接入", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(infoAirlineAccessDataService.deleteInfoAirlineAccessDataByIds(ids));
    }
}
