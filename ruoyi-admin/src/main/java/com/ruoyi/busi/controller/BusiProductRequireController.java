package com.ruoyi.busi.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.busi.domain.BusiProductRequire;
import com.ruoyi.busi.service.IBusiProductRequireService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 产品需求Controller
 * 
 * @author WangCL
 * @date 2021-12-22
 */
@Controller
@RequestMapping("/busi/productRequire")
public class BusiProductRequireController extends BaseController
{
    private String prefix = "busi/productRequire";

    @Autowired
    private IBusiProductRequireService busiProductRequireService;

    @RequiresPermissions("busi:productRequire:view")
    @GetMapping()
    public String productRequire()
    {
        return prefix + "/productRequire";
    }

    /**
     * 查询产品需求列表
     */
    @RequiresPermissions("busi:productRequire:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiProductRequire busiProductRequire)
    {
        startPage();
        List<BusiProductRequire> list = busiProductRequireService.selectBusiProductRequireList(busiProductRequire);
        return getDataTable(list);
    }

    /**
     * 导出产品需求列表
     */
    @RequiresPermissions("busi:productRequire:export")
    @Log(title = "产品需求", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiProductRequire busiProductRequire)
    {
        List<BusiProductRequire> list = busiProductRequireService.selectBusiProductRequireList(busiProductRequire);
        ExcelUtil<BusiProductRequire> util = new ExcelUtil<BusiProductRequire>(BusiProductRequire.class);
        return util.exportExcel(list, "产品需求数据");
    }

    /**
     * 新增产品需求
     */
    @GetMapping("/add")
    public String add(@RequestParam(name = "orderId", required = false)String orderId,
                      @RequestParam(name = "orderName", required = false)String orderName, ModelMap mmap)
    {
        mmap.put("orderId",orderId);
        mmap.put("orderName",orderName);
        return prefix + "/add";
    }

    /**
     * 新增保存产品需求
     */
    @RequiresPermissions("busi:productRequire:add")
    @Log(title = "产品需求", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiProductRequire busiProductRequire)
    {
        BusiProductRequire query = new BusiProductRequire();
        query.setOrderId(busiProductRequire.getOrderId());
        query.setColor(busiProductRequire.getColor());
        query.setSize(busiProductRequire.getSize());

        List<BusiProductRequire> list = busiProductRequireService.selectBusiProductRequireList(query);
        if(!list.isEmpty()){
            return error("该订单已存在相同尺码颜色的产品");
        }
        return toAjax(busiProductRequireService.insertBusiProductRequire(busiProductRequire));
    }

    /**
     * 修改产品需求
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap)
    {
        BusiProductRequire busiProductRequire = busiProductRequireService.selectBusiProductRequireById(id);
        mmap.put("busiProductRequire", busiProductRequire);
        return prefix + "/edit";
    }

    /**
     * 修改保存产品需求
     */
    @RequiresPermissions("busi:productRequire:edit")
    @Log(title = "产品需求", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiProductRequire busiProductRequire)
    {
        return toAjax(busiProductRequireService.updateBusiProductRequire(busiProductRequire));
    }

    /**
     * 删除产品需求
     */
    @RequiresPermissions("busi:productRequire:remove")
    @Log(title = "产品需求", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(busiProductRequireService.deleteBusiProductRequireByIds(ids));
    }

    ////////////////

    /**
     * 通过订单ID查询 物料类型列表
     */
    @PostMapping("/selMaterialTypeListByOrderId")
    @ResponseBody
    public AjaxResult selMaterialTypeListByOrderId(@RequestParam(name = "orderId",required = false) String orderId)
    {
        List<Map<String,String>> list = busiProductRequireService.selMaterialTypeListByOrderId(orderId);
        return success(list);
    }

    /**
     * 通过订单ID和物料类型查询 物料颜色列表
     */
    @PostMapping("/selMaterialColorListByOrderIdAndType")
    @ResponseBody
    public AjaxResult selMaterialColorListByOrderIdAndType(@RequestParam Map<String,String> queryMap)
    {
        List<Map<String,String>> list = busiProductRequireService.selMaterialColorListByOrderIdAndType(queryMap);
        return success(list);
    }

    /**
     * 通过订单ID查询产品需求列表
     */
    @PostMapping("/listByOrderId")
    @ResponseBody
    public AjaxResult listByOrderId(@RequestParam(name = "orderId", required = false) String orderId) {
        BusiProductRequire query = new BusiProductRequire();
        query.setOrderId(orderId);
        List<BusiProductRequire> list = busiProductRequireService.selectBusiProductRequireList(query);
        return success(list);
    }

    /**
     * 通过产品ID查询 物料类型列表
     */
    @PostMapping("/selMaterialTypeListByProductId")
    @ResponseBody
    public AjaxResult selMaterialTypeListByProductId(@RequestParam(name = "productId",required = false) String productId)
    {
        List<Map<String,String>> list = busiProductRequireService.selMaterialTypeListByProductId(productId);
        return success(list);
    }

    /**
     * 通过产品ID和查询物料颜色查询物料类型需求列表
     */
    @PostMapping("/materialColorListByProductIdAndType")
    @ResponseBody
    public AjaxResult selMaterialColorListByProductIdAndType(@RequestParam Map<String,String> queryMap)
    {
        List<Map<String,String>> list = busiProductRequireService.selMaterialColorListByProductIdAndType(queryMap);
        return success(list);
    }
}
