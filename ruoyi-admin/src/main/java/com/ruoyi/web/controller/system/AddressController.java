package com.ruoyi.web.controller.system;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
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
import com.ruoyi.system.domain.Address;
import com.ruoyi.system.service.IAddressService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 地区信息Controller
 * 
 * @author ruoyi
 * @date 2020-05-23
 */
@Controller
@RequestMapping("/system/address")
public class AddressController extends BaseController
{
    private final static String PREFIX = "system/address";
    private final static String ROOT = "0";

    @Autowired
    private IAddressService addressService;

    @RequiresPermissions("system:address:view")
    @GetMapping()
    public String address()
    {
        return PREFIX + "/address";
    }

    @RequiresPermissions("system:address:list")
    @GetMapping("/childrenList")
    @ResponseBody
    public List<Address> selectAddressByParentCode(String code) {
        return addressService.selectAddressByParentCode(code);
    }

    /**
     * 查询地区信息列表
     */
    @RequiresPermissions("system:address:list")
    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Address address)
    {
        startPage();
        if (StringUtils.isBlank(address.getAreaCode())
                || StringUtils.isBlank(address.getAreaName())
                || StringUtils.isBlank(address.getParentCode())
                || address.getId() == null) {
            address.setParentCode(ROOT);
        }
        List<Address> list = addressService.selectAddressList(address);
        return getDataTable(list);
    }

    /**
     * 导出地区信息列表
     */
    @RequiresPermissions("system:address:export")
    @Log(title = "地区信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(Address address)
    {
        List<Address> list = addressService.selectAddressList(address);
        ExcelUtil<Address> util = new ExcelUtil<Address>(Address.class);
        return util.exportExcel(list, "address");
    }

    /**
     * 新增地区信息
     */
    @GetMapping("/add")
    public String add()
    {
        return PREFIX + "/add";
    }

    /**
     * 新增保存地区信息
     */
    @RequiresPermissions("system:address:add")
    @Log(title = "地区信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(Address address)
    {
        return toAjax(addressService.insertAddress(address));
    }

    /**
     * 修改地区信息
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        Address address = addressService.selectAddressById(id);
        mmap.put("address", address);
        return PREFIX + "/edit";
    }

    /**
     * 修改保存地区信息
     */
    @RequiresPermissions("system:address:edit")
    @Log(title = "地区信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(Address address)
    {
        return toAjax(addressService.updateAddress(address));
    }

    /**
     * 删除地区信息
     */
    @RequiresPermissions("system:address:remove")
    @Log(title = "地区信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(addressService.deleteAddressByIds(ids));
    }
}
