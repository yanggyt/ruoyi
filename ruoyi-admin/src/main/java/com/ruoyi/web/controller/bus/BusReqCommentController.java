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
import com.ruoyi.system.domain.BusReqComment;
import com.ruoyi.system.service.IBusReqCommentService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 资源需求评论 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busReqComment")
public class BusReqCommentController extends BaseController
{
    private String prefix = "system/busReqComment";
	
	@Autowired
	private IBusReqCommentService busReqCommentService;
	
	@RequiresPermissions("system:busReqComment:view")
	@GetMapping()
	public String busReqComment()
	{
	    return prefix + "/busReqComment";
	}
	
	/**
	 * 查询资源需求评论列表
	 */
	@RequiresPermissions("system:busReqComment:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusReqComment busReqComment)
	{
		startPage();
        List<BusReqComment> list = busReqCommentService.selectBusReqCommentList(busReqComment);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出资源需求评论列表
	 */
	@RequiresPermissions("system:busReqComment:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusReqComment busReqComment)
    {
    	List<BusReqComment> list = busReqCommentService.selectBusReqCommentList(busReqComment);
        ExcelUtil<BusReqComment> util = new ExcelUtil<BusReqComment>(BusReqComment.class);
        return util.exportExcel(list, "busReqComment");
    }
	
	/**
	 * 新增资源需求评论
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存资源需求评论
	 */
	@RequiresPermissions("system:busReqComment:add")
	@Log(title = "资源需求评论", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusReqComment busReqComment)
	{		
		return toAjax(busReqCommentService.insertBusReqComment(busReqComment));
	}

	/**
	 * 修改资源需求评论
	 */
	@GetMapping("/edit/{commentId}")
	public String edit(@PathVariable("commentId") Long commentId, ModelMap mmap)
	{
		BusReqComment busReqComment = busReqCommentService.selectBusReqCommentById(commentId);
		mmap.put("busReqComment", busReqComment);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存资源需求评论
	 */
	@RequiresPermissions("system:busReqComment:edit")
	@Log(title = "资源需求评论", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusReqComment busReqComment)
	{		
		return toAjax(busReqCommentService.updateBusReqComment(busReqComment));
	}
	
	/**
	 * 删除资源需求评论
	 */
	@RequiresPermissions("system:busReqComment:remove")
	@Log(title = "资源需求评论", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busReqCommentService.deleteBusReqCommentByIds(ids));
	}
	
}
