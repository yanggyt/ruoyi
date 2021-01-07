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
import com.ruoyi.system.domain.BusResourceComment;
import com.ruoyi.system.service.IBusResourceCommentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 资源评论 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busResourceComment")
public class BusResourceCommentController extends BaseController
{
    private String prefix = "system/busResourceComment";
	
	@Autowired
	private IBusResourceCommentService busResourceCommentService;
	
	@RequiresPermissions("system:busResourceComment:view")
	@GetMapping()
	public String busResourceComment()
	{
	    return prefix + "/busResourceComment";
	}
	
	/**
	 * 查询资源评论列表
	 */
	@RequiresPermissions("system:busResourceComment:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusResourceComment busResourceComment)
	{
		startPage();
        List<BusResourceComment> list = busResourceCommentService.selectBusResourceCommentList(busResourceComment);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资源评论列表
	 */
	@RequiresPermissions("system:busResourceComment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusResourceComment busResourceComment)
    {
    	List<BusResourceComment> list = busResourceCommentService.selectBusResourceCommentList(busResourceComment);
        ExcelUtil<BusResourceComment> util = new ExcelUtil<BusResourceComment>(BusResourceComment.class);
        return util.exportExcel(list, "busResourceComment");
    }
	
	/**
	 * 新增资源评论
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资源评论
	 */
	@RequiresPermissions("system:busResourceComment:add")
	@Log(title = "资源评论", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusResourceComment busResourceComment)
	{		
		return toAjax(busResourceCommentService.insertBusResourceComment(busResourceComment));
	}

	/**
	 * 修改资源评论
	 */
	@GetMapping("/edit/{commentId}")
	public String edit(@PathVariable("commentId") Long commentId, ModelMap mmap)
	{
		BusResourceComment busResourceComment = busResourceCommentService.selectBusResourceCommentById(commentId);
		mmap.put("busResourceComment", busResourceComment);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资源评论
	 */
	@RequiresPermissions("system:busResourceComment:edit")
	@Log(title = "资源评论", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusResourceComment busResourceComment)
	{		
		return toAjax(busResourceCommentService.updateBusResourceComment(busResourceComment));
	}
	
	/**
	 * 删除资源评论
	 */
	@RequiresPermissions("system:busResourceComment:remove")
	@Log(title = "资源评论", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busResourceCommentService.deleteBusResourceCommentByIds(ids));
	}
	
}
