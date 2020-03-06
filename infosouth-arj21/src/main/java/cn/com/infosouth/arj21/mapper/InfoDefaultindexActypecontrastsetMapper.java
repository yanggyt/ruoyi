/**
 * Copyright &copy; 2013-2017 <a href="https://www.infosouth.com.cn/">Infosouth</a> All rights reserved.
 */
package cn.com.infosouth.arj21.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.com.infosouth.arj21.domain.InfoDefaultindexActypecontrastset;


/**
 * 默认首页-机型数据对比设置DAO接口
 * @author zy
 * @version 2018-03-20
 */
@Mapper
public interface InfoDefaultindexActypecontrastsetMapper{
	public List<Map<String,String>> findActypecontrastSetData();

	public List<InfoDefaultindexActypecontrastset> findList();
}