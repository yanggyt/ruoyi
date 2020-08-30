package com.ruoyi.dfm.service;

import com.ruoyi.dfm.dao.UserDAO;
import com.ruoyi.dfm.pojo.UserInfo;
import com.ruoyi.dfm.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LoginService {

	private static final Logger logger = LoggerFactory.getLogger(LoginService.class);
	@Autowired
	private UserDAO userDAO;

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public UserInfo checkUser(String username, String pwd) {
		//加密查询
		return userDAO.checkUser(username, Md5Util.md5(pwd));
	}

}
