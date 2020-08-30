package com.ruoyi.dfm.controller;

import com.ruoyi.dfm.constant.UserConstants;
import com.ruoyi.dfm.pojo.MenuInfo;
import com.ruoyi.dfm.pojo.UserInfo;
import com.ruoyi.dfm.service.LoginService;
import com.ruoyi.dfm.service.MenuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * 登录控制器
 * @author Kfighter
 *
 */
@Controller
@RequestMapping("/login.do")
public class LoginController extends BaseController {

	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	@Autowired
	private LoginService loginService;
	@Autowired
	private MenuService menuService;


	/**
	 * 获取登录界面
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/")
	public ModelAndView defaultHandle(HttpServletRequest req,
                                      HttpServletResponse res) throws Exception {
		logger.debug("请求登录");
		return new ModelAndView("login");
	}

	@RequestMapping("/login")
	public ModelAndView login(HttpServletRequest req,
                              HttpServletResponse res) throws Exception {
		logger.debug("有用户登录……");
		String username = req.getParameter("username");
		String pwd = req.getParameter("password");
		if(username == null || pwd == null || "".equals(username.trim()) || "".equals(pwd.trim()))
		{
			outputMsg(res, "<script>alert('对不起，用户名或密码不能为空，请重新登录！');document.location.href='login.do';</script>");
			return null;
		}
		UserInfo user = loginService.checkUser(username, pwd);
		if(null != user)
		{
			if(UserConstants.USER_STATUS_ZANTING == user.getStatus())
			{
				outputMsg(res, "<script>alert('对不起，您的帐户已被暂停，请联系管理员！');document.location.href='login.do';</script>");
				return null;
			}
			
			logger.debug("用户：" + user.getUsername() + " 登录成功！");
			req.getSession().setAttribute("user", user);
			List<MenuInfo> menus = menuService.getMenuByUser(user);
			req.getSession().setAttribute("menus", menus);
			return new ModelAndView("index");
		}
		else
		{
			outputMsg(res, "<script>alert('对不起，您的用户名或密码有误，请重新登录！');document.location.href='login.do';</script>");
			return null;
		}
		
	}
	
}
