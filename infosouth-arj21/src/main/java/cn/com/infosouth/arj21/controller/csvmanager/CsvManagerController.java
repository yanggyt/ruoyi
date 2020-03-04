package cn.com.infosouth.arj21.controller.csvmanager;

import cn.com.infosouth.common.config.Global;
import cn.com.infosouth.common.core.controller.BaseController;
import cn.com.infosouth.common.core.domain.AjaxResult;
import cn.com.infosouth.common.utils.ServletUtils;
import cn.com.infosouth.common.utils.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/arj21/csvmanager")
public class CsvManagerController extends BaseController {
    private String prefix = "arj21/csvmanager";

    @GetMapping("/infoFlight")
    public String list(ModelMap mmap, HttpServletRequest request, HttpServletResponse response)
    {
        // 如果是Ajax请求，返回Json字符串。
        if (ServletUtils.isAjaxRequest(request))
        {
            return ServletUtils.renderString(response, "{\"code\":\"1\",\"msg\":\"未登录或登录超时。请重新登录\"}");

        }
        mmap.put("sysName", Global.getName());
        mmap.put("sysWelcome", Global.getWelcome());
        mmap.put("sysBaba", Global.getBaba());
        mmap.put("copyrightYear", Global.getCopyrightYear());
        mmap.put("sysVersion", Global.getVersion());
        return prefix + "/list";
    }
}
