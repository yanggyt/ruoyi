package com.wuzhen.web.controller.busi;

import com.alibaba.fastjson.JSONObject;
import com.wuzhen.common.annotation.Log;
import com.wuzhen.common.core.controller.BaseController;
import com.wuzhen.common.core.domain.AjaxResult;
import com.wuzhen.common.core.page.TableDataInfo;
import com.wuzhen.common.enums.BusinessType;
import com.wuzhen.common.utils.poi.ExcelUtil;
import com.wuzhen.system.domain.AdvertInfo;
import com.wuzhen.system.service.AdvertDataService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 数据广告信息
 * 
 * @author zhengzheng
 */
@Controller
@RequestMapping("/advert/info")
public class AdvertDataController extends BaseController
{
    private String prefix = "advert/info";

    @Value("${spring.profiles.active}")
    private String active ;


    @Autowired
    private AdvertDataService advertDataService;

    @RequiresPermissions("advert:info:view")
    @GetMapping()
    public String dictData()
    {
        return prefix + "/index";
    }

    @PostMapping("/list")
    @RequiresPermissions("advert:info:list")
    @ResponseBody
    public TableDataInfo list(AdvertInfo advertInfo)
    {
        startPage();
        List<AdvertInfo> list = advertDataService.selectAdvertDataList(advertInfo);
        return getDataTable(list);
    }

    @Log(title = "广告数据", businessType = BusinessType.EXPORT)
    @RequiresPermissions("advert:info:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(AdvertInfo advertInfo)
    {
        List<AdvertInfo> list = advertDataService.selectAdvertDataList(advertInfo);
        ExcelUtil<AdvertInfo> util = new ExcelUtil<AdvertInfo>(AdvertInfo.class);
        return util.exportExcel(list, "广告数据");
    }

    /**
     * 新增广告类型
     */
    @GetMapping("/add")
    public String add(ModelMap mmap)
    {
        return prefix + "/add";
    }

    /**
     * 新增保存广告类型
     */
    @Log(title = "广告数据", businessType = BusinessType.INSERT)
    @RequiresPermissions("advert:info:add")
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated AdvertInfo advertInfo)
    {
        advertInfo.setCreateBy(getLoginName());
        return toAjax(advertDataService.insertAdvertData(advertInfo));
    }

    /**
     * 修改广告类型
     */
    @RequiresPermissions("advert:info:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {

        AdvertInfo advertInfo =  advertDataService.selectAdvertDataById(id);
        String fileNames = advertInfo.getBannerPicUrl();
        if(fileNames!=null && !"".equals(fileNames)){
            advertInfo.setBannerBaNames("http://"+this.getAdress()+":18000/profile/upload/ba/"+fileNames);
        }
        mmap.put("advert", advertInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存广告类型
     */
    @Log(title = "广告数据", businessType = BusinessType.UPDATE)
    @RequiresPermissions("advert:info:edit")
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated AdvertInfo advertInfo)
    {
        advertInfo.setUpdateBy(getLoginName());
        return toAjax(advertDataService.updateAdvertData(advertInfo));
    }

    @Log(title = "广告数据", businessType = BusinessType.DELETE)
    @RequiresPermissions("advert:info:remove")
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        advertDataService.deleteAdvertDataByIds(ids);
        return success();
    }


    private String getAdress(){
        String ipaddr ="";
        if (active.equals("druid")){
            ipaddr = "localhost";
        }else if(active.equals("prd")){
            ipaddr="47.94.96.229";
        }
        return ipaddr;
    }
}
