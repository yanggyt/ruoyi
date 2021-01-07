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
import com.ruoyi.system.domain.BusUserMessage;
import com.ruoyi.system.service.IBusUserMessageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;

/**
 * 用户消息 信息操作处理
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Controller
@RequestMapping("/system/busUserMessage")
public class BusUserMessageController extends BaseController
{
    private String prefix = "system/busUserMessage";
	
	@Autowired
	private IBusUserMessageService busUserMessageService;
	
	@RequiresPermissions("system:busUserMessage:view")
	@GetMapping()
	public String busUserMessage()
	{
	    return prefix + "/busUserMessage";
	}
	
	/**
	 * 查询用户消息列表
	 */
	@RequiresPermissions("system:busUserMessage:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(BusUserMessage busUserMessage)
	{
		startPage();
        List<BusUserMessage> list = busUserMessageService.selectBusUserMessageList(busUserMessage);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出用户消息列表
	 */
	@RequiresPermissions("system:busUserMessage:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusUserMessage busUserMessage)
    {
    	List<BusUserMessage> list = busUserMessageService.selectBusUserMessageList(busUserMessage);
        ExcelUtil<BusUserMessage> util = new ExcelUtil<BusUserMessage>(BusUserMessage.class);
        return util.exportExcel(list, "busUserMessage");
    }
	
	/**
	 * 新增用户消息
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存用户消息
	 */
	@RequiresPermissions("system:busUserMessage:add")
	@Log(title = "用户消息", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(BusUserMessage busUserMessage)
	{		
		return toAjax(busUserMessageService.insertBusUserMessage(busUserMessage));
	}

	/**
	 * 修改用户消息
	 */
	@GetMapping("/edit/{messageId}")
	public String edit(@PathVariable("messageId") Long messageId, ModelMap mmap)
	{
		BusUserMessage busUserMessage = busUserMessageService.selectBusUserMessageById(messageId);
		mmap.put("busUserMessage", busUserMessage);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存用户消息
	 */
	@RequiresPermissions("system:busUserMessage:edit")
	@Log(title = "用户消息", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(BusUserMessage busUserMessage)
	{		
		return toAjax(busUserMessageService.updateBusUserMessage(busUserMessage));
	}
	
	/**
	 * 删除用户消息
	 */
	@RequiresPermissions("system:busUserMessage:remove")
	@Log(title = "用户消息", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(busUserMessageService.deleteBusUserMessageByIds(ids));
	}
	
}
