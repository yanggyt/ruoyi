package com.ruoyi.busi.controller;

import java.util.List;
import java.util.Map;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.busi.domain.BusiProductOperate;
import com.ruoyi.busi.service.IBusiProductOperateService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 成品操作流水Controller
 *
 * @author WangCL
 * @date 2022-01-08
 */
@Controller
@RequestMapping("/busi/productOperate")
public class BusiProductOperateController extends BaseController {
    private String prefix = "busi/productOperate";

    @Autowired
    private IBusiProductOperateService busiProductOperateService;

    @RequiresPermissions("busi:productOperate:view")
    @GetMapping()
    public String productOperate() {
        return prefix + "/productOperate";
    }

    /**
     * 查询成品操作流水列表
     */
    @RequiresPermissions("busi:productOperate:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(BusiProductOperate busiProductOperate) {
        startPage();
        List<BusiProductOperate> list = busiProductOperateService.selectBusiProductOperateList(busiProductOperate);
        return getDataTable(list);
    }

    /**
     * 导出成品操作流水列表
     */
    @RequiresPermissions("busi:productOperate:export")
    @Log(title = "成品操作流水", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(BusiProductOperate busiProductOperate) {
        List<BusiProductOperate> list = busiProductOperateService.selectBusiProductOperateList(busiProductOperate);
        ExcelUtil<BusiProductOperate> util = new ExcelUtil<BusiProductOperate>(BusiProductOperate.class);
        return util.exportExcel(list, "成品操作流水数据");
    }

    /**
     * 入库或退回
     */
    @GetMapping("/addIn")
    public String addIn(@RequestParam(name = "operType", required = false) String operType, @RequestParam(name = "inTab", required = false) String inTab, ModelMap mmap) {
        mmap.put("operType",operType);
        mmap.put("inTab",inTab);
        return prefix + "/addIn";
    }

    /**
     * 出库
     */
    @GetMapping("/addOut")
    public String addOut(@RequestParam(name = "operType", required = false) String operType, @RequestParam(name = "inTab", required = false) String inTab, ModelMap mmap) {
        mmap.put("operType",operType);
        mmap.put("inTab",inTab);
        return prefix + "/addOut";
    }


    /**
     * 新增保存成品操作流水
     */
    @RequiresPermissions("busi:productOperate:add")
    @Log(title = "成品操作流水", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(BusiProductOperate busiProductOperate) {
        return toAjax(busiProductOperateService.insertBusiProductOperate(busiProductOperate));
    }

    /**
     * 修改成品操作流水
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") String id, ModelMap mmap) {
        BusiProductOperate busiProductOperate = busiProductOperateService.selectBusiProductOperateById(id);
        mmap.put("busiProductOperate", busiProductOperate);
        return prefix + "/edit";
    }

    /**
     * 修改保存成品操作流水
     */
    @RequiresPermissions("busi:productOperate:edit")
    @Log(title = "成品操作流水", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(BusiProductOperate busiProductOperate) {
        return toAjax(busiProductOperateService.updateBusiProductOperate(busiProductOperate));
    }

    /**
     * 删除成品操作流水
     */
    @RequiresPermissions("busi:productOperate:remove")
    @Log(title = "成品操作流水", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids) {
        return toAjax(busiProductOperateService.deleteBusiProductOperateByIds(ids));
    }

    /**
     * 通过产线ID查询产品任务尺码列表
     */
    @PostMapping("/selProductSizeByLineId")
    @ResponseBody
    public AjaxResult selProductSizeByLineId(@RequestParam(name = "lineId", required = false) String lineId) {
        List<Map<String, String>> list = busiProductOperateService.selProductSizeByLineId(lineId);
        return success(list);
    }

    /**
     * 通过产线ID和产品尺码查询产品任务颜色列表
     */
    @PostMapping("/selProductColorByLineIdAndSize")
    @ResponseBody
    public AjaxResult selProductColorByLineIdAndSize(@RequestParam Map<String, String> queryMap) {
        List<Map<String, String>> list = busiProductOperateService.selProductColorByLineIdAndSize(queryMap);
        return success(list);
    }

    /**
     * 通过订单ID查询库存尺码列表
     */
    @PostMapping("/selProductSizeByOrderId")
    @ResponseBody
    public AjaxResult selProductSizeByOrderId(@RequestParam(name = "orderId", required = false) String orderId) {
        List<Map<String, String>> list = busiProductOperateService.selProductSizeByOrderId(orderId);
        return success(list);
    }

    /**
     * 通过订单ID和产品尺码查询库存颜色列表
     */
    @PostMapping("/selProductColorByOrderIdAndSize")
    @ResponseBody
    public AjaxResult selProductColorByOrderIdAndSize(@RequestParam Map<String, String> queryMap) {
        List<Map<String, String>> list = busiProductOperateService.selProductColorByOrderIdAndSize(queryMap);
        return success(list);
    }
}
