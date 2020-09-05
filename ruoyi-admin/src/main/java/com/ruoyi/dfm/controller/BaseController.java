package com.ruoyi.dfm.controller;

import com.ruoyi.dfm.pojo.UserInfo;
import com.ruoyi.framework.util.ShiroUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;


public class BaseController {
	
	protected void outputMsg(HttpServletResponse res , String msg) throws Exception
	{
		PrintWriter out = res.getWriter();
		res.setContentType("text/html");
		res.setHeader("Content-Type","text/json;charset=utf8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/json;charset=utf8");
		out.write(msg);
		out.flush();
		out.close();
	}
	
	protected void outputJson(HttpServletResponse res , String msg) throws Exception
	{
		PrintWriter out = res.getWriter();
		res.setContentType("text/json");
		res.setHeader("Content-Type","text/json;charset=utf8");
		res.setCharacterEncoding("utf-8");
		res.setContentType("text/json;charset=utf8");
		out.write(msg);
		out.flush();
		out.close();
	}
	
	
	protected UserInfo getUserInfo(HttpServletRequest req)
	{
		return ShiroUtils.getLoginUser();
	}
}
