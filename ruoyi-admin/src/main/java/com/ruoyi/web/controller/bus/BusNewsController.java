package com.ruoyi.web.controller.bus;

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
import com.ruoyi.system.domain.BusNews;
import com.ruoyi.system.service.IBusNewsService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 新闻 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busNews")
public class BusNewsController extends BaseController
{
    private String prefix = "system/busNews";
	
	@Autowired
	private IBusNewsService busNewsService;
	
	@RequiresPermissions("system:busNews:view")
	@GetMapping()
	public String busNews()
	{
	    return prefix + "/busNews";
	}
	
	/**
	 * 查询新闻列表
	 */
	@RequiresPermissions("system:busNews:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusNews busNews)
	{
		startPage();
        List<BusNews> list = busNewsService.selectBusNewsList(busNews);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出新闻列表
	 */
	@RequiresPermissions("system:busNews:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusNews busNews)
    {
    	List<BusNews> list = busNewsService.selectBusNewsList(busNews);
        ExcelUtil<BusNews> util = new ExcelUtil<BusNews>(BusNews.class);
        return util.exportExcel(list, "busNews");
    }
	
	/**
	 * 新增新闻
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存新闻
	 */
	@RequiresPermissions("system:busNews:add")
	@Log(title = "新闻", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusNews busNews)
	{		
		return toAjax(busNewsService.insertBusNews(busNews));
	}

	/**
	 * 修改新闻
	 */
	@GetMapping("/edit/{newsId}")
	public String edit(@PathVariable("newsId") Long newsId, ModelMap mmap)
	{
		BusNews busNews = busNewsService.selectBusNewsById(newsId);
		mmap.put("busNews", busNews);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存新闻
	 */
	@RequiresPermissions("system:busNews:edit")
	@Log(title = "新闻", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusNews busNews)
	{		
		return toAjax(busNewsService.updateBusNews(busNews));
	}
	
	/**
	 * 删除新闻
	 */
	@RequiresPermissions("system:busNews:remove")
	@Log(title = "新闻", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busNewsService.deleteBusNewsByIds(ids));
	}
	
}
