package com.ruoyi.project.system.online.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ruoyi.common.tools.StringTools;
import com.ruoyi.framework.core.controller.BaseController;
import com.ruoyi.framework.core.domain.R;
import com.ruoyi.project.shiro.session.OnlineSessionDAO;
import com.ruoyi.project.system.online.domain.OnlineSession;
import com.ruoyi.project.system.online.domain.UserOnline;
import com.ruoyi.project.system.online.service.IUserOnlineService;

@Controller
@RequestMapping("/monitor/online")
public class UserOnlineController extends BaseController
{

    private String prefix = "monitor/online";

    @Autowired
    private IUserOnlineService userOnlineService;

    @Autowired
    private OnlineSessionDAO onlineSessionDAO;

    @GetMapping()
    public String online()
    {
        return prefix + "/online";
    }

    @GetMapping("/list")
    @ResponseBody
    public List<UserOnline> list(Model model)
    {
        List<UserOnline> list = userOnlineService.selectUserOnlines();
        return list;
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

    @ResponseBody
    @RequestMapping("/forceLogout/{sessionId}")
    public R forceLogout(@PathVariable("sessionId") String sessionId)
    {
        try
        {
            UserOnline online = userOnlineService.selectByOnlineId(sessionId);
            if (online == null)
            {
                return R.error("用户已下线。数据不存在");
            }
            OnlineSession onlineSession = (OnlineSession) onlineSessionDAO.readSession(online.getSessionId());
            if (onlineSession == null)
            {
                return R.error("用户已下线。会话不存在");
            }
            onlineSession.setStatus(OnlineSession.OnlineStatus.off_line);
            online.setStatus(OnlineSession.OnlineStatus.off_line);
            userOnlineService.saveByOnline(online);
            return R.ok();
        }
        catch (Exception e)
        {
            return R.error(e.getMessage());
        }

    }

}
