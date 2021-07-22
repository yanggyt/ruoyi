package com.ruoyi.bps.controller;

import com.ruoyi.bps.domain.ExpressInfo;
import com.ruoyi.bps.service.IExpressInfoService;
import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.enums.BusinessType;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.controller.BaseController;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

/**
 * 快递信息Controller
 * 
 * @author box
 * @date 2021-05-06
 */
@Controller
@RequestMapping("/bps/expressInfo")
public class ExpressInfoController extends BaseController
{
    private String prefix = "bps/expressInfo";

    @Autowired
    private IExpressInfoService expressInfoService;

    @RequiresPermissions("bps:expressInfo:view")
    @GetMapping()
    public String expressInfo()
    {
        return prefix + "/expressInfo";
    }

    /**
     * 查询快递信息列表
     */
    @RequiresPermissions("bps:expressInfo:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ExpressInfo expressInfo)
    {
        startPage();
        List<ExpressInfo> list = expressInfoService.selectExpressInfoList(expressInfo);
        return getDataTable(list);
    }

    /**
     * 导出快递信息列表
     */
    @RequiresPermissions("bps:expressInfo:export")
    @Log(title = "快递信息", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ExpressInfo expressInfo)
    {
        List<ExpressInfo> list = expressInfoService.selectExpressInfoList(expressInfo);
        ExcelUtil<ExpressInfo> util = new ExcelUtil<ExpressInfo>(ExpressInfo.class);
        return util.exportExcel(list, "快递信息数据");
    }

    /**
     * 新增快递信息
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存快递信息
     */
    @RequiresPermissions("bps:expressInfo:add")
    @Log(title = "快递信息", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(ExpressInfo expressInfo)
    {
        return toAjax(expressInfoService.insertExpressInfo(expressInfo));
    }

    /**
     * 修改快递信息
     */
    @GetMapping("/edit/{message}")
    public String edit(@PathVariable("message") String message, ModelMap mmap)
    {
        ExpressInfo expressInfo = expressInfoService.selectExpressInfoById(message);
        mmap.put("expressInfo", expressInfo);
        return prefix + "/edit";
    }

    /**
     * 修改保存快递信息
     */
    @RequiresPermissions("bps:expressInfo:edit")
    @Log(title = "快递信息", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(ExpressInfo expressInfo)
    {
        return toAjax(expressInfoService.updateExpressInfo(expressInfo));
    }

    /**
     * 删除快递信息
     */
    @RequiresPermissions("bps:expressInfo:remove")
    @Log(title = "快递信息", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(expressInfoService.deleteExpressInfoByIds(ids));
    }



    @GetMapping ( "/importTemplate" )
    @ResponseBody
    public AjaxResult importTemplate ( ) {
        ExcelUtil < ExpressInfo > util = new ExcelUtil<>(ExpressInfo.class);
        return util.importTemplateExcel ( "快递查询导入模板" );
    }

    @PostMapping("/importData")
    @ResponseBody
    public TableDataInfo importData(MultipartFile file, boolean updateSupport) throws Exception
    {
        /*ExcelUtil<SysUser> util = new ExcelUtil<SysUser>(SysUser.class);
        List<SysUser> userList = util.importExcel(file.getInputStream());
        String operName = ShiroUtils.getSysUser().getLoginName();
        String message = userService.importUser(userList, updateSupport, operName);
        return AjaxResult.success(message);*/

        ExcelUtil<ExpressInfo> util= new ExcelUtil<ExpressInfo>(ExpressInfo.class);
        List<ExpressInfo> expressInfoList=util.importExcel(file.getInputStream());
        List<ExpressInfo> list = new ArrayList<>();
        for( ExpressInfo expressInfo:expressInfoList)
        {
            list.add(expressInfo);

        }

        //String message = expressInfoList.importUser(userList, updateSupport, operName);

        return getDataTable(list);
    }
}
