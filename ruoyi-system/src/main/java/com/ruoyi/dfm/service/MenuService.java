package com.ruoyi.dfm.service;

import com.ruoyi.dfm.constant.UserConstants;
import com.ruoyi.dfm.dao.MenuDAO;
import com.ruoyi.dfm.pojo.MenuInfo;
import com.ruoyi.dfm.pojo.UserInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
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


	/**
	 * 根据用户ID获取该用户拥有的菜单列表
	 * @return
	 */
	public List<MenuInfo> getMenuByGroup(int groupId)
	{
		List<MenuInfo> menus = menuDAO.getByGroup(groupId);
		List<MenuInfo> result = new ArrayList<>();
		for (MenuInfo menuInfo : menus) {
			if(UserConstants.MENU_LEVEL_0 == menuInfo.getMenuLevel()) {
				//第一级菜单
				result.add(menuInfo);
			} else {
				MenuInfo parentMenu = getMenuById(menus, menuInfo.getParentId());
				if(null == parentMenu){
					result.add(menuInfo);
				}
				parentMenu.getChildren().add(menuInfo);
			}
		}
		return result;
	}


	private MenuInfo getMenuById(List<MenuInfo> menus, int id) {
		for (MenuInfo menuInfo : menus) {
			if(menuInfo.getId() == id) {
				return menuInfo;
			}
		}
		return null;
	}

}
