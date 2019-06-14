package com.ruoyi.web.controller.network;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.network.domain.NetIpAddress;
import com.ruoyi.network.service.INetIpAddressService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 网络IP 信息操作处理
 * 
 * @author TP
 * @date 2019-06-15
 */
@Controller
@RequestMapping("/network/netIpAddress")
public class NetIpAddressController extends BaseController
{
    private String prefix = "network/netIpAddress";
	
	@Autowired
	private INetIpAddressService netIpAddressService;
	
	@RequiresPermissions("network:netIpAddress:view")
	@GetMapping()
	public String netIpAddress()
	{
	    return prefix + "/netIpAddress";
	}
	
	/**
	 * 查询网络IP列表
	 */
	@RequiresPermissions("network:netIpAddress:list")
	@PostMapping("/list")
	@ResponseBody
	public TableDataInfo list(NetIpAddress netIpAddress)
	{
		startPage();
        List<NetIpAddress> list = netIpAddressService.selectNetIpAddressList(netIpAddress);
		return getDataTable(list);
	}
	
	
	/**
	 * 导出网络IP列表
	 */
	@RequiresPermissions("network:netIpAddress:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(NetIpAddress netIpAddress)
    {
    	List<NetIpAddress> list = netIpAddressService.selectNetIpAddressList(netIpAddress);
        ExcelUtil<NetIpAddress> util = new ExcelUtil<NetIpAddress>(NetIpAddress.class);
        return util.exportExcel(list, "netIpAddress");
    }
	
	/**
	 * 新增网络IP
	 */
	@GetMapping("/add")
	public String add()
	{
	    return prefix + "/add";
	}
	
	/**
	 * 新增保存网络IP
	 */
	@RequiresPermissions("network:netIpAddress:add")
	@Log(title = "网络IP", businessType = BusinessType.INSERT)
	@PostMapping("/add")
	@ResponseBody
	public AjaxResult addSave(NetIpAddress netIpAddress)
	{		
		return toAjax(netIpAddressService.insertNetIpAddress(netIpAddress));
	}

	/**
	 * 修改网络IP
	 */
	@GetMapping("/edit/{ipAddressId}")
	public String edit(@PathVariable("ipAddressId") Integer ipAddressId, ModelMap mmap)
	{
		NetIpAddress netIpAddress = netIpAddressService.selectNetIpAddressById(ipAddressId);
		mmap.put("netIpAddress", netIpAddress);
	    return prefix + "/edit";
	}
	
	/**
	 * 修改保存网络IP
	 */
	@RequiresPermissions("network:netIpAddress:edit")
	@Log(title = "网络IP", businessType = BusinessType.UPDATE)
	@PostMapping("/edit")
	@ResponseBody
	public AjaxResult editSave(NetIpAddress netIpAddress)
	{		
		return toAjax(netIpAddressService.updateNetIpAddress(netIpAddress));
	}
	
	/**
	 * 删除网络IP
	 */
	@RequiresPermissions("network:netIpAddress:remove")
	@Log(title = "网络IP", businessType = BusinessType.DELETE)
	@PostMapping( "/remove")
	@ResponseBody
	public AjaxResult remove(String ids)
	{		
		return toAjax(netIpAddressService.deleteNetIpAddressByIds(ids));
	}
	
}
