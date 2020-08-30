package com.ruoyi.dfm.service;

import com.ruoyi.dfm.dao.MenuDAO;
import com.ruoyi.dfm.pojo.MenuInfo;
import com.ruoyi.dfm.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MenuService {
	private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
	@Autowired
	private MenuDAO menuDAO;
	

	/**
	 * 根据用户ID获取该用户拥有的菜单列表
	 * @return
	 */
	public List<MenuInfo> getMenuByUser(UserInfo user)
	{
		return menuDAO.getByGroup(user.getGroupId());
	}
}
