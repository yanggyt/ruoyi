package com.ruoyi.content.service;

import com.ruoyi.content.domain.WxUserInfoDto;

/**
 * 用户业务层
*
* @author liu.hx  
* @date 2018年4月3日  
*
 */
public interface UserService {

	/**
	 * 获取用户id
	*
	*	liu.hx
	*	2018年4月3日
	* @throws
	 */
	String getUserId(WxUserInfoDto wxUserInfoDto);
}
