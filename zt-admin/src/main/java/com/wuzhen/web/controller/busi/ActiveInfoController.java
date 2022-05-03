package com.wuzhen.web.controller.busi;

import com.alibaba.fastjson.JSONObject;
import com.wuzhen.common.annotation.Log;
import com.wuzhen.common.config.RuoYiConfig;
import com.wuzhen.common.core.controller.BaseController;
import com.wuzhen.common.core.domain.AjaxResult;
import com.wuzhen.common.core.page.TableDataInfo;
import com.wuzhen.common.enums.BusinessType;
import com.wuzhen.common.utils.poi.ExcelUtil;
import com.wuzhen.framework.shiro.util.AuthorizationUtils;
import com.wuzhen.system.domain.ActiveInfo;
import com.wuzhen.system.service.IActiveInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 角色信息
 *
 * @author zhengzheng
 */
@Controller
@RequestMapping("/active/info")
public class ActiveInfoController extends BaseController
{

    private static final Logger logger = LoggerFactory.getLogger(ActiveInfoController.class);

    private String prefix = "active/info";

    @Autowired
    private IActiveInfoService activeInfoService;

    @Value("${wuzhen.fp}")
    private String fp ;
    @Value("${wuzhen.lp}")
    private String lp ;




    @RequiresPermissions("active:info:view")
    @GetMapping()
    public String role()
    {
        return prefix + "/index";
    }

    @RequiresPermissions("active:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ActiveInfo activeInfo)
    {
        startPage();
        List<ActiveInfo> list = activeInfoService.selectActiveList(activeInfo);
        list.forEach(item->{
            if (item!=null&&!"".equals(item.getActiveDesc())&&item.getActiveDesc().length()>6){
                item.setActiveDesc(item.getActiveDesc().substring(0,6)+"...");
            }
        });
        return getDataTable(list);
    }

    @Log(title = "活动信息导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("active:info:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ActiveInfo activeInfo)
    {
        List<ActiveInfo> list = activeInfoService.selectActiveList(activeInfo);
        ExcelUtil<ActiveInfo> util = new ExcelUtil<ActiveInfo>(ActiveInfo.class);
        return util.exportExcel(list, "活动信息");
    }

    /**
     * 新增活动
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存活动
     */
    @RequiresPermissions("active:info:add")
    @Log(title = "活动管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated ActiveInfo activeInfo)
    {
        logger.info(activeInfo.toString());
        activeInfo.setCreateBy(getLoginName());
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(activeInfoService.insertActive(activeInfo));
    }

    /**
     * 修改活动
     */
    @RequiresPermissions("active:info:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
//        roleService.checkRoleDataScope(roleId);
        mmap.put("active", activeInfoService.selectActiveById(id));
        return prefix + "/edit";
    }

    /**
     * 修改活动
     */
    @RequiresPermissions("active:info:edit")
    @GetMapping("/first/{id}")
    public String first(@PathVariable("id") Long id, ModelMap mmap)
    {
        mmap.put("active", activeInfoService.selectActiveById(id));
        return prefix + "/first";
    }


    /**
     *保存首页
     */
    @RequiresPermissions("active:info:edit")
    @Log(title = "保存首页", businessType = BusinessType.UPDATE)
    @PostMapping("/firstSave")
    @ResponseBody
    public AjaxResult firstSave(@Validated ActiveInfo activeInfo)
    {
        MultipartFile file = activeInfo.getActiveFirstPic();
        // 获取上传文件名
        String filename = file.getOriginalFilename();
        if (!"".equals(filename)){

            // 定义上传文件保存路径
            String path = RuoYiConfig.getFPUploadPath();
            // 新建文件
            File filepath = new File(path, filename);
            // 判断路径是否存在，如果不存在就创建一个
            if (!filepath.getParentFile().exists()) {
                filepath.getParentFile().mkdirs();
            }
            String picUrl = path + File.separator + filename;
            try {
                // 写入文件
                file.transferTo(new File(picUrl));
            } catch (IOException e) {
                e.printStackTrace();
            }
            activeInfo.setActiveFirstPicUrl(fp+filename);
            activeInfo.setIsFristPage("1");
        }
        activeInfo.setUpdateBy(getLoginName());
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(activeInfoService.saveFistPage(activeInfo));
    }



    /**
     * 修改保存活动
     */
    @RequiresPermissions("active:info:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated ActiveInfo activeInfo)
    {

        activeInfo.setUpdateBy(getLoginName());
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(activeInfoService.updateActive(activeInfo));
    }



    @RequiresPermissions("active:info:remove")
    @Log(title = "活动管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(activeInfoService.deleteActiveByIds(ids));
    }



    /**
     * 状态修改
     */
    @Log(title = "活动上下线状态管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("active:info:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(@Validated ActiveInfo activeInfo)
    {
        return toAjax(activeInfoService.updateActive(activeInfo));
    }




}