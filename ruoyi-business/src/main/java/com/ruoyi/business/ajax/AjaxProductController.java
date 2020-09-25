package com.ruoyi.business.ajax;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelReader;
import com.github.pagehelper.PageHelper;
import com.ruoyi.business.domain.BizProduct;
import com.ruoyi.business.domain.BizProductType;
import com.ruoyi.business.mapper.BizAccountMapper;
import com.ruoyi.business.mapper.BizMemberMapper;
import com.ruoyi.business.service.IBizProductService;
import com.ruoyi.business.service.IBizProductTypeService;
import com.ruoyi.business.sync.UserData;
import com.ruoyi.business.sync.UserDataListener;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/ajax/product")
public class AjaxProductController extends AuthController {

    @Resource
    private IBizProductService bizProductService;

    @Resource
    private IBizProductTypeService bizProductTypeService;

    //分类和推荐商品
    @PostMapping("/center")
    public AjaxResult center()
    {
        PageHelper.startPage(1, 10);
        //首页分类
        Map<String, Object> resultMap = new HashMap<String, Object>();
        BizProductType productType = new BizProductType();
        productType.setIsEnable(1);
        resultMap.put("typeList", bizProductTypeService.selectBizProductTypeList(productType));

        //首页商品
        BizProduct product = new BizProduct();
        product.setOnlineStatus(1);
        resultMap.put("productList", getSimpleProductList(bizProductService.selectBizProductList(product)));
        return AjaxResult.success(resultMap);
    }

    //产品列表
    @PostMapping("/list")
    public AjaxResult list(Long typeID)
    {
        PageHelper.startPage(1, 20);

        Map<String, Object> resultMap = new HashMap<String, Object>();
        BizProductType productType = new BizProductType();
        productType.setIsEnable(1);
        resultMap.put("typeList", bizProductTypeService.selectBizProductTypeList(productType));

        BizProduct product = new BizProduct();
        product.setOnlineStatus(1);
        product.setProductTypeId(typeID);
        resultMap.put("productList", getSimpleProductList(bizProductService.selectBizProductList(product)));
        resultMap.put("typeID", typeID);
        return AjaxResult.success(resultMap);
    }

    //读取产品
    @PostMapping("/load")
    public AjaxResult load(Long productId)
    {
        BizProduct bizProduct = bizProductService.selectBizProductById(productId);
        return AjaxResult.success(bizProduct);
    }

    //简易商品列表
    private List<Map> getSimpleProductList(List<BizProduct> productList)
    {
        List<Map> resultList = new ArrayList<Map>();
        for (BizProduct bizProduct : productList) {
            Map map = new HashMap();
            map.put("name", bizProduct.getProductName());
            map.put("mainImage", bizProduct.getMainImage());
            map.put("price", bizProduct.getAmount());
            resultList.add(map);
        }
        return resultList;
    }
}
