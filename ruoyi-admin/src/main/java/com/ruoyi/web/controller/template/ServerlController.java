package com.ruoyi.web.controller.template;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.template.domain.Server;
import com.ruoyi.template.service.IServerService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 服务器模板 信息操作处理
 * 
 * @author TP
 * @date 2019-06-11
 */
@Controller
@RequestMapping("/template/server")
public class ServerlController extends BaseController
{
    private String prefix = "template/server";
	
	@Autowired
	private IServerService serverService;
	
	@RequiresPermissions("template:server:view")
	@GetMapping()
	public String server()
	{
	    return prefix + "/server";
	}
	
	/**
	 * 查询服务器模板列表
	 */
	@RequiresPermissions("template:server:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(Server server)
	{
		startPage();
        List<Server> list = serverService.selectServerList(server);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出服务器模板列表
	 */
	@RequiresPermissions("template:server:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Server server)
    {
    	List<Server> list = serverService.selectServerList(server);
        ExcelUtil<Server> util = new ExcelUtil<Server>(Server.class);
        return util.exportExcel(list, "server");
    }
	
	/**
	 * 新增服务器模板
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存服务器模板
	 */
	@RequiresPermissions("template:server:add")
	@Log(title = "服务器模板", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(Server server)
	{		
		return toAjax(serverService.insertServer(server));
	}

	/**
	 * 修改服务器模板
	 */
	@GetMapping("/edit/{serverId}")
	public String edit(@PathVariable("serverId") Integer serverId, ModelMap mmap)
	{
		Server server = serverService.selectServerById(serverId);
		mmap.put("server", server);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存服务器模板
	 */
	@RequiresPermissions("template:server:edit")
	@Log(title = "服务器模板", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(Server server)
	{		
		return toAjax(serverService.updateServer(server));
	}
	
	/**
	 * 删除服务器模板
	 */
	@RequiresPermissions("template:server:remove")
	@Log(title = "服务器模板", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(serverService.deleteServerByIds(ids));
	}
	
}
