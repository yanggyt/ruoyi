package com.ruoyi.web.controller.front;

import java.util.List;
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
import com.ruoyi.front.domain.OnlineMessage;
import com.ruoyi.front.service.IOnlineMessageService;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.common.core.page.TableDataInfo;

/**
 * 在线留言Controller
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Controller
@RequestMapping("/front/message")
public class OnlineMessageController extends BaseController
{
    private String prefix = "front/message";

    @Autowired
    private IOnlineMessageService onlineMessageService;

    @RequiresPermissions("front:message:view")
    @GetMapping()
    public String message()
    {
        return prefix + "/message";
    }

    /**
     * 查询在线留言列表
     */
    @RequiresPermissions("front:message:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(OnlineMessage onlineMessage)
    {
        startPage();
        List<OnlineMessage> list = onlineMessageService.selectOnlineMessageList(onlineMessage);
        return getDataTable(list);
    }

    /**
     * 导出在线留言列表
     */
    @RequiresPermissions("front:message:export")
    @Log(title = "在线留言", businessType = BusinessType.EXPORT)
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(OnlineMessage onlineMessage)
    {
        List<OnlineMessage> list = onlineMessageService.selectOnlineMessageList(onlineMessage);
        ExcelUtil<OnlineMessage> util = new ExcelUtil<OnlineMessage>(OnlineMessage.class);
        return util.exportExcel(list, "message");
    }

    /**
     * 新增在线留言
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存在线留言
     */
    @RequiresPermissions("front:message:add")
    @Log(title = "在线留言", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(OnlineMessage onlineMessage)
    {
        return toAjax(onlineMessageService.insertOnlineMessage(onlineMessage));
    }

    /**
     * 修改在线留言
     */
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
        OnlineMessage onlineMessage = onlineMessageService.selectOnlineMessageById(id);
        mmap.put("onlineMessage", onlineMessage);
        return prefix + "/edit";
    }

    /**
     * 修改保存在线留言
     */
    @RequiresPermissions("front:message:edit")
    @Log(title = "在线留言", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(OnlineMessage onlineMessage)
    {
        return toAjax(onlineMessageService.updateOnlineMessage(onlineMessage));
    }

    /**
     * 删除在线留言
     */
    @RequiresPermissions("front:message:remove")
    @Log(title = "在线留言", businessType = BusinessType.DELETE)
    @PostMapping( "/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(onlineMessageService.deleteOnlineMessageByIds(ids));
    }
}
