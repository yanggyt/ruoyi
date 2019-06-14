package com.ruoyi.wish.controller;

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
import com.ruoyi.wish.domain.WishInfo;
import com.ruoyi.wish.service.IWishInfoService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 记录微心愿的 信息操作处理
 * 
 * @author jyking
 * @date 2019-06-14
 */
@Controller
@RequestMapping("/wish/wishInfo")
public class WishInfoController extends BaseController
{
    private String prefix = "wish/wishInfo";
	
	@Autowired
	private IWishInfoService wishInfoService;
	
	@RequiresPermissions("wish:wishInfo:view")
	@GetMapping()
	public String wishInfo()
	{
	    return prefix + "/wishInfo";
	}
	
	/**
	 * 查询记录微心愿的列表
	 */
	@RequiresPermissions("wish:wishInfo:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(WishInfo wishInfo)
	{
		startPage();
        List<WishInfo> list = wishInfoService.selectWishInfoList(wishInfo);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出记录微心愿的列表
	 */
	@RequiresPermissions("wish:wishInfo:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(WishInfo wishInfo)
    {
    	List<WishInfo> list = wishInfoService.selectWishInfoList(wishInfo);
        ExcelUtil<WishInfo> util = new ExcelUtil<WishInfo>(WishInfo.class);
        return util.exportExcel(list, "wishInfo");
    }
	
	/**
	 * 新增记录微心愿的
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存记录微心愿的
	 */
	@RequiresPermissions("wish:wishInfo:add")
	@Log(title = "记录微心愿的", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(WishInfo wishInfo)
	{		
		return toAjax(wishInfoService.insertWishInfo(wishInfo));
	}

	/**
	 * 修改记录微心愿的
	 */
	@GetMapping("/edit/{wishId}")
	public String edit(@PathVariable("wishId") Integer wishId, ModelMap mmap)
	{
		WishInfo wishInfo = wishInfoService.selectWishInfoById(wishId);
		mmap.put("wishInfo", wishInfo);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存记录微心愿的
	 */
	@RequiresPermissions("wish:wishInfo:edit")
	@Log(title = "记录微心愿的", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(WishInfo wishInfo)
	{		
		return toAjax(wishInfoService.updateWishInfo(wishInfo));
	}
	
	/**
	 * 删除记录微心愿的
	 */
	@RequiresPermissions("wish:wishInfo:remove")
	@Log(title = "记录微心愿的", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(wishInfoService.deleteWishInfoByIds(ids));
	}
	
}
