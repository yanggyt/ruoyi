package com.ruoyi.busi.controller;

import java.util.List;

import com.ruoyi.busi.domain.BusiCustomerCompany;
import com.ruoyi.busi.service.IBusiCustomerCompanyService;
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
import com.ruoyi.busi.domain.BusiCustomerPerson;
import com.ruoyi.busi.service.IBusiCustomerPersonService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户公司人员Controller
 *
 * @author WangCL
 * @date 2021-12-16
 */
@Controller
@RequestMapping("/busi/person")
public class BusiCustomerPersonController extends BaseController {
    private String prefix = "busi/person";

    @Autowired
    private IBusiCustomerPersonService busiCustomerPersonService;

    @Autowired
    private IBusiCustomerCompanyService busiCustomerCompanyService;

    @RequiresPermissions("busi:person:view")
    @GetMapping("/{companyId}")
    public String person(@PathVariable("companyId") String companyId, ModelMap mmap) {
        BusiCustomerCompany busiCustomerCompany = busiCustomerCompanyService.selectBusiCustomerCompanyById(companyId);
        mmap.put("company", busiCustomerCompany == null ? new BusiCustomerCompany() : busiCustomerCompany);
        return prefix + "/person";
    }

    /**
     * 查询客户公司人员列表
     */
    @RequiresPermissions("busi:person:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiCustomerPerson busiCustomerPerson) {
        startPage();
        List<BusiCustomerPerson> list = busiCustomerPersonService.selectBusiCustomerPersonList(busiCustomerPerson);
        return getDataTable(list);
    }

    /**
     * 导出客户公司人员列表
     */
    @RequiresPermissions("busi:person:export")
    @Log(title = "客户公司人员", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiCustomerPerson busiCustomerPerson) {
        List<BusiCustomerPerson> list = busiCustomerPersonService.selectBusiCustomerPersonList(busiCustomerPerson);
        ExcelUtil<BusiCustomerPerson> util = new ExcelUtil<BusiCustomerPerson>(BusiCustomerPerson.class);
        return util.exportExcel(list, "客户公司人员数据");
    }

    /**
     * 新增客户公司人员
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存客户公司人员
     */
    @RequiresPermissions("busi:person:add")
    @Log(title = "客户公司人员", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiCustomerPerson busiCustomerPerson) {
        busiCustomerPerson.setCreateBy(getLoginName());
        return toAjax(busiCustomerPersonService.insertBusiCustomerPerson(busiCustomerPerson));
    }

    /**
     * 修改客户公司人员
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        BusiCustomerPerson busiCustomerPerson = busiCustomerPersonService.selectBusiCustomerPersonById(id);
        mmap.put("busiCustomerPerson", busiCustomerPerson);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户公司人员
     */
    @RequiresPermissions("busi:person:edit")
    @Log(title = "客户公司人员", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiCustomerPerson busiCustomerPerson) {
        busiCustomerPerson.setUpdateBy(getLoginName());
        return toAjax(busiCustomerPersonService.updateBusiCustomerPerson(busiCustomerPerson));
    }

    /**
     * 删除客户公司人员
     */
    @RequiresPermissions("busi:person:remove")
    @Log(title = "客户公司人员", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(busiCustomerPersonService.deleteBusiCustomerPersonByIds(ids));
    }
}
