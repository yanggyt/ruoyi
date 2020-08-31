package com.ruoyi.dfm.controller;

import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.dfm.constant.UserConstants;
import com.ruoyi.dfm.pojo.Page;
import com.ruoyi.dfm.pojo.UserInfo;
import com.ruoyi.dfm.pojo.UserQueryBean;
import com.ruoyi.dfm.service.FileService;
import com.ruoyi.dfm.service.UserService;
import com.ruoyi.dfm.util.Md5Util;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysRole;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Controller
@RequestMapping("/user.do")
public class UserController extends com.ruoyi.common.core.controller.BaseController {
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@Autowired
	private FileService fileService;

	/**
	 * 用户管理控制器，默认打开个人资料方法
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	
	/**
	 * 添加用户
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/addSave")
	public ModelAndView addSave(HttpServletRequest req,
                            HttpServletResponse res) throws Exception {
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String pwd = req.getParameter("password");
		String pwd1 = req.getParameter("password1");
		String department = req.getParameter("department");
		String projectGroup = req.getParameter("projectGroup");
		String email = req.getParameter("email");
		String ccEmail = req.getParameter("ccEmail");
		String isDepAdmin = req.getParameter("isDepAdmin");
		String isAdmin = req.getParameter("isAdmin");
		try {
			UserInfo user = new UserInfo();
			user.setDepartment(department);
			user.setEmail(email);
			user.setCcEmail(ccEmail);
			user.setPassword(Md5Util.md5(pwd));
			int groupId = UserConstants.USER_LEVEL_NORMAL;
			if(StringUtils.isNotBlank(isAdmin) && "1".equals(isAdmin))
			{
				groupId = UserConstants.USER_LEVEL_ADMIN;
			} else if(StringUtils.isNotBlank(isDepAdmin) && "1".equals(isDepAdmin))
			{
				groupId = UserConstants.USER_LEVEL_DEP_ADMIN;
			}
			user.setGroupId(groupId);
			user.setName(name);
			user.setProjectGroup(projectGroup);
			user.setUsername(username);
			userService.addUser(user);
			//创建用户目录
			String dir = fileService.getRootPath() + user.getUsername();
			fileService.createDir(dir);
			outputMsg(res, "<script>alert('添加成功，点击确定跳转到用户列表！');document.location.href='user.do?method=getUserList';</script>");
			return null;
		} catch (Exception e) {
			logger.error("添加用户失败", e);
			outputMsg(res, "<script>alert('添加用户失败，请检查数据正确性，重新添加！');window.history.go(-1)';</script>");
			return null;
		}
		
	}

	/**
	 * 添加用户页面
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/add")
	public ModelAndView add(HttpServletRequest req,
							 HttpServletResponse res) throws Exception {
		return new ModelAndView("dfm/addUser");
	}


	/**
	 * 用户管理控制器，默认打开个人资料方法
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/list")
	public ModelAndView list(HttpServletRequest req,
									  HttpServletResponse res) throws Exception {
		return new ModelAndView("dfm/userList");
	}

	/**
	 * 获取用户列表
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
//	@RequestMapping("/getUserList")
//	@ResponseBody
//	public TableDataInfo getUserList(HttpServletRequest req, HttpServletResponse res) throws Exception {
//		startPage();
//		Page page = new Page();
//		PageDomain pageDomain = TableSupport.getPageDomain();
//		page.setCurrentPage(pageDomain.getPageNum());
//		page.setPageSize(pageDomain.getPageSize());
//
//		UserInfo currentUser = ShiroUtils.getLoginUser();
//		List<UserInfo> rs = null;
//		if(UserConstants.USER_LEVEL_ADMIN == currentUser.getGroupId()) {
//			rs = userService.getAllUser(page);
//		} else if(UserConstants.USER_LEVEL_DEP_ADMIN == currentUser.getGroupId()) {
//			//部门管理员，只能获取自己部门的用户
//			UserQueryBean userQueryBean = new UserQueryBean();
//			userQueryBean.setDepartment(currentUser.getDepartment());
//			rs = userService.getByQueryBean(userQueryBean, page);
//		}
//		return getDataTable(rs);
//	}
//
	
	
	/**
	 * 删除用户
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/deleteUser")
	public ModelAndView deleteUser(HttpServletRequest req,
                                   HttpServletResponse res) throws Exception {
		try {
			String delProject = req.getParameter("delProject");
			int uid = Integer.parseInt(req.getParameter("uid"));
			userService.deleteUser(uid,delProject);
			outputMsg(res, "<script>alert('删除用户成功！');document.location.href='user.do?method=getUserList';</script>");
		} catch (Exception e) {
			logger.error("删除用户失败", e);
			outputMsg(res, "<script>alert('删除用户失败，请重试！');window.history.go(-1);</script>");
		}
		return null;
	}
	
	
	/**
	 * 暂停用户
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/changeUserState")
	public ModelAndView changeUserState(HttpServletRequest req,
                                        HttpServletResponse res) throws Exception {
		try {
			int uid = Integer.parseInt(req.getParameter("uid"));
			int state = Integer.parseInt(req.getParameter("state"));
			//构造分页参数
			String currentPage = req.getParameter("currentPage");
			userService.changeUserState(uid , state);
			outputMsg(res, "<script>alert('操作用户成功！');document.location.href='user.do?method=getUserList&currentPage="+currentPage+"';</script>");
		} catch (Exception e) {
			logger.error("暂停用户失败", e);
			outputMsg(res, "<script>alert('操作用户失败，请重试！');window.history.go(-1);</script>");
		}
		return null;
	}
	
	/**
	 * 获取修改用户界面
	 * @param req
	 * @param res
	 * @return
	 * @throws Exception
	 */
	@RequestMapping("/getModifyUser")
	public ModelAndView getModifyUser(HttpServletRequest req,
                                      HttpServletResponse res) throws Exception {
		int uid = 0 ;
		String ustr = req.getParameter("uid");
		if(ustr == null || "".equals(ustr))
		{
			uid = ShiroUtils.getLoginUser().getId();
		}else
		{
			uid = Integer.parseInt(ustr);
		}
		 
		UserInfo user = userService.getUserById(uid);
		req.setAttribute("user", user);
		return new ModelAndView("modifyUser");
	}

	@RequestMapping("/modifyUser")
	public ModelAndView modifyUser(HttpServletRequest req,
                                   HttpServletResponse res) throws Exception {
		String id = req.getParameter("id");
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String pwd = req.getParameter("password");
		String pwd1 = req.getParameter("password1");
		String department = req.getParameter("department");
		String projectGroup = req.getParameter("projectGroup");
		String email = req.getParameter("email");
		String ccEmail = req.getParameter("ccEmail");
		String gid = req.getParameter("gid");
		int groupId = null == gid ? UserConstants.USER_LEVEL_NORMAL : Integer.parseInt(gid);

		String isDepAdmin = req.getParameter("isDepAdmin");
		String isAdmin = req.getParameter("isAdmin");
//		int groupId = UserConstants.USER_LEVEL_NORMAL;
		if("1".equals(isAdmin))
		{
			groupId = UserConstants.USER_LEVEL_ADMIN;
		} else if("1".equals(isDepAdmin))
		{
			groupId = UserConstants.USER_LEVEL_DEP_ADMIN;
		}

		UserInfo user1 = ShiroUtils.getLoginUser();
		try {
			UserInfo user = new UserInfo();
			user.setId(Integer.parseInt(id));
			user.setDepartment(department);
			user.setEmail(email);
			user.setCcEmail(ccEmail);
			//user.setPassword(Md5Util.md5(pwd));
			user.setPassword(pwd);
			user.setName(name);
			user.setProjectGroup(projectGroup);
			user.setUsername(username);
			user.setGroupId(groupId);
			userService.updateUser(user);
			if(UserConstants.USER_LEVEL_ADMIN == user1.getGroupId())
			{
				outputMsg(res, "<script>alert('修改成功，点击确定跳转到用户列表！');document.location.href='user.do?method=getUserList';</script>");
			}
			else
			{
				outputMsg(res, "<script>alert('修改成功，点击确定跳转项目提交页面！');document.location.href='project.do?method=getAddPage';</script>");
			}
			
			return null;
		} catch (Exception e) {
			logger.error("修改用户失败", e);
			outputMsg(res, "<script>alert('修改用户失败，请检查数据正确性，重新添加！');window.history.go(-1)';</script>");
			return null;
		}
	}

	@RequestMapping("/getUserList")
	@ResponseBody
	public TableDataInfo queryUser(HttpServletRequest req,
                                  HttpServletResponse res) throws Exception {
		startPage();
		String name = req.getParameter("name");
		String username = req.getParameter("username");
		String department = req.getParameter("department");
		String projectGroup = req.getParameter("projectGroup");
		String state = req.getParameter("state");
		
		UserQueryBean queryBean = new UserQueryBean();
		queryBean.setDepartment(department);
		queryBean.setName(name);
		queryBean.setProjectGroup(projectGroup);
		queryBean.setState(state);
		queryBean.setUsername(username);

		Page page = new Page();
		PageDomain pageDomain = TableSupport.getPageDomain();
		page.setCurrentPage(pageDomain.getPageNum());
		page.setPageSize(pageDomain.getPageSize());

		UserInfo currentUser = ShiroUtils.getLoginUser();

		List<UserInfo> rs = null;
		if(UserConstants.USER_LEVEL_ADMIN == currentUser.getGroupId()) {
			rs = userService.getByQueryBean(queryBean , page);
		} else if(UserConstants.USER_LEVEL_DEP_ADMIN == currentUser.getGroupId()) {
			//部门管理员，只能获取自己部门的用户
			queryBean.setDepartment(currentUser.getDepartment());
			rs = userService.getByQueryBean(queryBean, page);
		}
		return getDataTable(rs, page.getTotalCount());
	}
	
	
}
