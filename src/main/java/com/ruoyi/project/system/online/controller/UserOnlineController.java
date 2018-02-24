package com.ruoyi.project.system.online.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.common.tools.StringTools;
import com.ruoyi.common.utils.TableDataInfo;
import com.ruoyi.framework.core.controller.BaseController;
import com.ruoyi.framework.core.domain.R;
import com.ruoyi.project.system.online.domain.UserOnline;
import com.ruoyi.project.system.online.service.IUserOnlineService;

@Controller
@RequestMapping("/monitor/userOnline")
public class UserOnlineController extends BaseController
{

    private String prefix = "monitor/online";

    @Autowired
    private IUserOnlineService userOnlineService;

    @GetMapping()
    public String userOnline()
    {
        return prefix + "/online";
    }

    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list(Model model)
    {
        List<UserOnline> list = userOnlineService.selectUserOnlines();
        TableDataInfo tableDataInfo = new TableDataInfo(list, 12);
        return tableDataInfo;
    }

    @GetMapping("/forceLogout")
    @ResponseBody
    public R forceLogout(@RequestParam(value = "ids") String[] ids)
    {
        try
        {
            for (String sessionId : ids)
            {
                UserOnline online = userOnlineService.selectByOnlineId(sessionId);
                if (online == null)
                {
                    continue;
                }
                userOnlineService.forceLogout(sessionId);
            }
            return R.ok();
        }
        catch (Exception e)
        {
            String msg = "未知错误";
            if (StringTools.isNotEmpty(e.getMessage()))
            {
                msg = e.getMessage();
            }
            return R.error(msg);
        }
    }

}
