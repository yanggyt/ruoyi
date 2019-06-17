package com.ruoyi.web.controller.assets;

import com.ruoyi.assets.domain.AssetsMachineRoom;
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
 * 机房 信息操作处理
 * 
 * @author TP
 * @date 2019-06-17
 */
@Controller
@RequestMapping("/assets/assetsMachineRoom")
public class AssetsMachineRoomController extends BaseController
{
    private String prefix = "assets/assetsMachineRoom";
	
	@Autowired
	private IAssetsMachineRoomService assetsMachineRoomService;
	
	@RequiresPermissions("assets:assetsMachineRoom:view")
	@GetMapping()
	public String assetsMachineRoom()
	{
	    return prefix + "/assetsMachineRoom";
	}
	
	/**
	 * 查询机房列表
	 */
	@RequiresPermissions("assets:assetsMachineRoom:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(AssetsMachineRoom assetsMachineRoom)
	{
		startPage();
        List<AssetsMachineRoom> list = assetsMachineRoomService.selectAssetsMachineRoomList(assetsMachineRoom);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出机房列表
	 */
	@RequiresPermissions("assets:assetsMachineRoom:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AssetsMachineRoom assetsMachineRoom)
    {
    	List<AssetsMachineRoom> list = assetsMachineRoomService.selectAssetsMachineRoomList(assetsMachineRoom);
        ExcelUtil<AssetsMachineRoom> util = new ExcelUtil<AssetsMachineRoom>(AssetsMachineRoom.class);
        return util.exportExcel(list, "assetsMachineRoom");
    }
	
	/**
	 * 新增机房
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存机房
	 */
	@RequiresPermissions("assets:assetsMachineRoom:add")
	@Log(title = "机房", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(AssetsMachineRoom assetsMachineRoom)
	{		
		return toAjax(assetsMachineRoomService.insertAssetsMachineRoom(assetsMachineRoom));
	}

	/**
	 * 修改机房
	 */
	@GetMapping("/edit/{machineRoomId}")
	public String edit(@PathVariable("machineRoomId") Integer machineRoomId, ModelMap mmap)
	{
		AssetsMachineRoom assetsMachineRoom = assetsMachineRoomService.selectAssetsMachineRoomById(machineRoomId);
		mmap.put("assetsMachineRoom", assetsMachineRoom);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存机房
	 */
	@RequiresPermissions("assets:assetsMachineRoom:edit")
	@Log(title = "机房", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(AssetsMachineRoom assetsMachineRoom)
	{		
		return toAjax(assetsMachineRoomService.updateAssetsMachineRoom(assetsMachineRoom));
	}
	
	/**
	 * 删除机房
	 */
	@RequiresPermissions("assets:assetsMachineRoom:remove")
	@Log(title = "机房", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(assetsMachineRoomService.deleteAssetsMachineRoomByIds(ids));
	}
	
}
