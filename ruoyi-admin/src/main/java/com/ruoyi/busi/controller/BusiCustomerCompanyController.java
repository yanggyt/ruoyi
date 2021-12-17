package com.ruoyi.busi.controller;

import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.utils.StringUtils;
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
import com.ruoyi.busi.domain.BusiCustomerCompany;
import com.ruoyi.busi.service.IBusiCustomerCompanyService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 客户公司Controller
 *
 * @author WangCL
 * @date 2021-12-16
 */
@Controller
@RequestMapping("/busi/company")
public class BusiCustomerCompanyController extends BaseController {
    private String prefix = "busi/company";

    @Autowired
    private IBusiCustomerCompanyService busiCustomerCompanyService;

    @RequiresPermissions("busi:company:view")
    @GetMapping()
    public String company() {
        return prefix + "/company";
    }

    /**
     * 查询客户公司列表
     */
    @RequiresPermissions("busi:company:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiCustomerCompany busiCustomerCompany) {
        startPage();
        List<BusiCustomerCompany> list = busiCustomerCompanyService.selectBusiCustomerCompanyList(busiCustomerCompany);
        return getDataTable(list);
    }

    /**
     * 导出客户公司列表
     */
    @RequiresPermissions("busi:company:export")
    @Log(title = "客户公司", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiCustomerCompany busiCustomerCompany) {
        List<BusiCustomerCompany> list = busiCustomerCompanyService.selectBusiCustomerCompanyList(busiCustomerCompany);
        ExcelUtil<BusiCustomerCompany> util = new ExcelUtil<BusiCustomerCompany>(BusiCustomerCompany.class);
        return util.exportExcel(list, "客户公司数据");
    }

    /**
     * 新增客户公司
     */
    @GetMapping("/add")
    public String add() {
        return prefix + "/add";
    }

    /**
     * 新增保存客户公司
     */
    @RequiresPermissions("busi:company:add")
    @Log(title = "客户公司", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiCustomerCompany busiCustomerCompany) {
        busiCustomerCompany.setCreateBy(getLoginName());
        busiCustomerCompany.getBusiCustomerPersonList().stream().forEach(person -> person.setCreateBy(getLoginName()));
        return toAjax(busiCustomerCompanyService.insertBusiCustomerCompany(busiCustomerCompany));
    }

    /**
     * 修改客户公司
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        BusiCustomerCompany busiCustomerCompany = busiCustomerCompanyService.selectBusiCustomerCompanyById(id);
        mmap.put("busiCustomerCompany", busiCustomerCompany);
        return prefix + "/edit";
    }

    /**
     * 修改保存客户公司
     */
    @RequiresPermissions("busi:company:edit")
    @Log(title = "客户公司", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiCustomerCompany busiCustomerCompany) {
        busiCustomerCompany.setUpdateBy(getLoginName());
        busiCustomerCompany.getBusiCustomerPersonList().stream().forEach(person -> person.setCreateBy(getLoginName()));
        return toAjax(busiCustomerCompanyService.updateBusiCustomerCompany(busiCustomerCompany));
    }

    /**
     * 删除客户公司
     */
    @RequiresPermissions("busi:company:remove")
    @Log(title = "客户公司", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(busiCustomerCompanyService.deleteBusiCustomerCompanyByIds(ids));
    }

    /**
     * @param mmap
     * @return
     */
    @GetMapping(value = {"/selectCompany"})
    public String selectCompany(ModelMap mmap) {
        BusiCustomerCompany com = new BusiCustomerCompany();
        com.setId("0");
        com.setCoName("根节点");
        mmap.put("company", com);
        return prefix + "/tree";
    }

    /**
     * 加载客户公司树列表
     */
    @GetMapping("/treeData")
    @ResponseBody
    public List<Ztree> treeData() {
        List<BusiCustomerCompany> list = busiCustomerCompanyService.selectBusiCustomerCompanyList(new BusiCustomerCompany());
        return initZtree(list);
    }

    /**
     * 初始化树列表
     * @param list
     * @return
     */
    public List<Ztree> initZtree(List<BusiCustomerCompany> list) {
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (BusiCustomerCompany comp : list) {
            Ztree ztree = new Ztree();
            ztree.setId(Long.valueOf(comp.getId()));
            ztree.setpId(0l);
            ztree.setName(comp.getCoName());
            ztree.setTitle(comp.getCoName());
            ztrees.add(ztree);
        }
        return ztrees;
    }
}
