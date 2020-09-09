package com.ruoyi.his.controller;

import java.util.List;

import com.ruoyi.his.domain.HisRegistrationTemplate;
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
import com.ruoyi.his.domain.HisPayAccount;
import com.ruoyi.his.service.IHisPayAccountService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 支付账户Controller
 * 
 * @author bend
 * @date 2020-07-14
 */
@Controller
@RequestMapping("/his/payAccount")
public class HisPayAccountController extends BaseController
{
    private String prefix = "his/payAccount";

    @Autowired
    private IHisPayAccountService hisPayAccountService;

    @RequiresPermissions("his:payAccount:view")
    @GetMapping()
    public String payAccount()
    {
        return prefix + "/payAccount";
    }

    /**
     * 查询支付账户列表
     */
    @RequiresPermissions("his:payAccount:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(HisPayAccount hisPayAccount)
    {
        startPage();
        List<HisPayAccount> list = hisPayAccountService.selectHisPayAccountList(hisPayAccount);
        return getDataTable(list);
    }

    /**
     * 导出支付账户列表
     */
    @RequiresPermissions("his:payAccount:export")
    @Log(title = "支付账户", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(HisPayAccount hisPayAccount)
    {
        List<HisPayAccount> list = hisPayAccountService.selectHisPayAccountList(hisPayAccount);
        ExcelUtil<HisPayAccount> util = new ExcelUtil<HisPayAccount>(HisPayAccount.class);
        return util.exportExcel(list, "payAccount");
    }

    /**
     * 新增支付账户
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存支付账户
     */
    @RequiresPermissions("his:payAccount:add")
    @Log(title = "支付账户", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(HisPayAccount hisPayAccount)
    {
        return toAjax(hisPayAccountService.insertHisPayAccount(hisPayAccount));
    }

    /**
     * 修改支付账户
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        HisPayAccount hisPayAccount = hisPayAccountService.selectHisPayAccountById(id);
        mmap.put("hisPayAccount", hisPayAccount);
        return prefix + "/edit";
    }

    /**
     * 修改保存支付账户
     */
    @RequiresPermissions("his:payAccount:edit")
    @Log(title = "支付账户", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(HisPayAccount hisPayAccount)
    {
        return toAjax(hisPayAccountService.updateHisPayAccount(hisPayAccount));
    }

    /**
     * 删除支付账户
     */
    @RequiresPermissions("his:payAccount:remove")
    @Log(title = "支付账户", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(hisPayAccountService.deleteHisPayAccountByIds(ids));
    }

    /**
     * 账户状态修改
     */
    @Log(title = "支付账户管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("his:payAccount:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(HisPayAccount hisPayAccount)
    {
        return toAjax(hisPayAccountService.changeStatus(hisPayAccount));
    }
}
