package com.ruoyi.web.controller.assets;

import com.ruoyi.assets.domain.AssetsCabinet;
import com.ruoyi.assets.domain.AssetsMachineRoom;
import com.ruoyi.assets.service.IAssetsCabinetService;
import com.ruoyi.assets.service.IAssetsMachineRoomService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 机柜类型 信息操作处理
 *
 * @author TP
 * @date 2019-06-17
 */
@Controller
@RequestMapping("/assets/assetsCabinet")
public class AssetsCabinetController extends BaseController {
    private String prefix = "assets/assetsCabinet";

    @Autowired
    private IAssetsCabinetService assetsCabinetService;
    @Autowired
    private IAssetsMachineRoomService assetsMachineRoomService;

    @RequiresPermissions("assets:assetsCabinet:view")
    @GetMapping()
    public String assetsCabinet() {
        return prefix + "/assetsCabinet";
    }

    /**
     * 查询机柜类型列表
     */
    @RequiresPermissions("assets:assetsCabinet:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(AssetsCabinet assetsCabinet) {
        startPage();
        List<AssetsCabinet> list = assetsCabinetService.selectAssetsCabinetList(assetsCabinet);
        return getDataTable(list);
    }


    /**
     * 导出机柜类型列表
     */
    @RequiresPermissions("assets:assetsCabinet:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AssetsCabinet assetsCabinet) {
        List<AssetsCabinet> list = assetsCabinetService.selectAssetsCabinetList(assetsCabinet);
        ExcelUtil<AssetsCabinet> util = new ExcelUtil<AssetsCabinet>(AssetsCabinet.class);
        return util.exportExcel(list, "assetsCabinet");
    }

    /**
     * 新增机柜类型
     */
    @GetMapping("/add")
    public String add(ModelMap mmap) {
        List<AssetsMachineRoom> list = assetsMachineRoomService.selectAssetsMachineRoomList(new AssetsMachineRoom());
        mmap.put("machineRooms",list);
        return prefix + "/add";
    }

    /**
     * 新增保存机柜类型
     */
    @RequiresPermissions("assets:assetsCabinet:add")
    @Log(title = "机柜类型", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(AssetsCabinet assetsCabinet) {
        return toAjax(assetsCabinetService.insertAssetsCabinet(assetsCabinet));
    }

    /**
     * 修改机柜类型
     */
    @GetMapping("/edit/{cabinetId}")
    public String edit(@PathVariable("cabinetId") Integer cabinetId, ModelMap mmap) {
        AssetsCabinet assetsCabinet = assetsCabinetService.selectAssetsCabinetById(cabinetId);
        List<AssetsMachineRoom> list = assetsMachineRoomService.selectAssetsMachineRoomList(new AssetsMachineRoom());
        mmap.put("machineRooms",list);
        mmap.put("assetsCabinet", assetsCabinet);
        return prefix + "/edit";
    }

    /**
     * 修改保存机柜类型
     */
    @RequiresPermissions("assets:assetsCabinet:edit")
    @Log(title = "机柜类型", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(AssetsCabinet assetsCabinet) {
        return toAjax(assetsCabinetService.updateAssetsCabinet(assetsCabinet));
    }

    /**
     * 删除机柜类型
     */
    @RequiresPermissions("assets:assetsCabinet:remove")
    @Log(title = "机柜类型", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(assetsCabinetService.deleteAssetsCabinetByIds(ids));
    }

}
