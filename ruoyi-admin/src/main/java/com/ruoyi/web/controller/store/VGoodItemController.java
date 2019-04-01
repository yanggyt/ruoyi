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
import com.ruoyi.store.domain.VGoodItem;
import com.ruoyi.store.service.IVGoodItemService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 商品分类 信息操作处理
 * 
 * @author Enzo
 * @date 2019-03-25
 */
@Controller
@RequestMapping("/store/vGoodItem")
public class VGoodItemController extends BaseController
{
    private String prefix = "store/vGoodItem";
	
	@Autowired
	private IVGoodItemService vGoodItemService;
	
	@RequiresPermissions("store:vGoodItem:view")
	@GetMapping()
	public String vGoodItem()
	{
	    return prefix + "/vGoodItem";
	}
	
	/**
	 * 查询商品分类列表
	 */
	@RequiresPermissions("store:vGoodItem:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VGoodItem vGoodItem)
	{
		startPage();
        List<VGoodItem> list = vGoodItemService.selectVGoodItemList(vGoodItem);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品分类列表
	 */
	@RequiresPermissions("store:vGoodItem:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(VGoodItem vGoodItem)
    {
    	List<VGoodItem> list = vGoodItemService.selectVGoodItemList(vGoodItem);
        ExcelUtil<VGoodItem> util = new ExcelUtil<VGoodItem>(VGoodItem.class);
        return util.exportExcel(list, "vGoodItem");
    }
	
	/**
	 * 新增商品分类
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品分类
	 */
	@RequiresPermissions("store:vGoodItem:add")
	@Log(title = "商品分类", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VGoodItem vGoodItem)
	{		
		return toAjax(vGoodItemService.insertVGoodItem(vGoodItem));
	}

	/**
	 * 修改商品分类
	 */
	@GetMapping("/edit/{id}")
	public String edit(@PathVariable("id") Long id, ModelMap mmap)
	{
		VGoodItem vGoodItem = vGoodItemService.selectVGoodItemById(id);
		mmap.put("vGoodItem", vGoodItem);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品分类
	 */
	@RequiresPermissions("store:vGoodItem:edit")
	@Log(title = "商品分类", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VGoodItem vGoodItem)
	{		
		return toAjax(vGoodItemService.updateVGoodItem(vGoodItem));
	}
	
	/**
	 * 删除商品分类
	 */
	@RequiresPermissions("store:vGoodItem:remove")
	@Log(title = "商品分类", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(vGoodItemService.deleteVGoodItemByIds(ids));
	}
	
}
