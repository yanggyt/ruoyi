package com.ruoyi.web.controller.store;

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
import com.ruoyi.store.domain.VStore;
import com.ruoyi.store.service.IVStoreService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 门店管理 信息操作处理
 * 
 * @author Enzo
 * @date 2019-03-25
 */
@Controller
@RequestMapping("/store/vStore")
public class VStoreController extends BaseController
{
    private String prefix = "store/vStore";
	
	@Autowired
	private IVStoreService vStoreService;
	
	@RequiresPermissions("store:vStore:view")
	@GetMapping()
	public String vStore()
	{
	    return prefix + "/vStore";
	}
	
	/**
	 * 查询门店管理列表
	 */
	@RequiresPermissions("store:vStore:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VStore vStore)
	{
		startPage();
        List<VStore> list = vStoreService.selectVStoreList(vStore);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出门店管理列表
	 */
	@RequiresPermissions("store:vStore:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(VStore vStore)
    {
    	List<VStore> list = vStoreService.selectVStoreList(vStore);
        ExcelUtil<VStore> util = new ExcelUtil<VStore>(VStore.class);
        return util.exportExcel(list, "vStore");
    }
	
	/**
	 * 新增门店管理
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存门店管理
	 */
	@RequiresPermissions("store:vStore:add")
	@Log(title = "门店管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VStore vStore)
	{		
		return toAjax(vStoreService.insertVStore(vStore));
	}

	/**
	 * 修改门店管理
	 */
	@GetMapping("/edit/{storeId}")
	public String edit(@PathVariable("storeId") Long storeId, ModelMap mmap)
	{
		VStore vStore = vStoreService.selectVStoreById(storeId);
		mmap.put("vStore", vStore);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存门店管理
	 */
	@RequiresPermissions("store:vStore:edit")
	@Log(title = "门店管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VStore vStore)
	{		
		return toAjax(vStoreService.updateVStore(vStore));
	}
	
	/**
	 * 删除门店管理
	 */
	@RequiresPermissions("store:vStore:remove")
	@Log(title = "门店管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(vStoreService.deleteVStoreByIds(ids));
	}
	
}
