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
import com.ruoyi.store.domain.VGood;
import com.ruoyi.store.service.IVGoodService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 商品管理 信息操作处理
 * 
 * @author Enzo
 * @date 2019-03-25
 */
@Controller
@RequestMapping("/store/vGood")
public class VGoodController extends BaseController
{
    private String prefix = "store/vGood";
	
	@Autowired
	private IVGoodService vGoodService;
	
	@RequiresPermissions("store:vGood:view")
	@GetMapping()
	public String vGood()
	{
	    return prefix + "/vGood";
	}
	
	/**
	 * 查询商品管理列表
	 */
	@RequiresPermissions("store:vGood:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(VGood vGood)
	{
		startPage();
        List<VGood> list = vGoodService.selectVGoodList(vGood);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出商品管理列表
	 */
	@RequiresPermissions("store:vGood:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(VGood vGood)
    {
    	List<VGood> list = vGoodService.selectVGoodList(vGood);
        ExcelUtil<VGood> util = new ExcelUtil<VGood>(VGood.class);
        return util.exportExcel(list, "vGood");
    }
	
	/**
	 * 新增商品管理
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存商品管理
	 */
	@RequiresPermissions("store:vGood:add")
	@Log(title = "商品管理", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(VGood vGood)
	{		
		return toAjax(vGoodService.insertVGood(vGood));
	}

	/**
	 * 修改商品管理
	 */
	@GetMapping("/edit/{goodId}")
	public String edit(@PathVariable("goodId") Long goodId, ModelMap mmap)
	{
		VGood vGood = vGoodService.selectVGoodById(goodId);
		mmap.put("vGood", vGood);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存商品管理
	 */
	@RequiresPermissions("store:vGood:edit")
	@Log(title = "商品管理", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(VGood vGood)
	{		
		return toAjax(vGoodService.updateVGood(vGood));
	}
	
	/**
	 * 删除商品管理
	 */
	@RequiresPermissions("store:vGood:remove")
	@Log(title = "商品管理", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(vGoodService.deleteVGoodByIds(ids));
	}
	
}
