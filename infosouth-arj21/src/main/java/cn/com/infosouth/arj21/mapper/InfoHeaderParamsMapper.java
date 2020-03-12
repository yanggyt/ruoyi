/**
 * Copyright &copy; 2013-2017 <a href="https://www.infosouth.com.cn/">Infosouth</a> All rights reserved.
 */
package cn.com.infosouth.arj21.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import cn.com.infosouth.arj21.domain.InfoHeaderParams;


/**
 * 导入csv时不同机型对应的header参数DAO接口
 * @author cyh
 * @version 2018-03-06
 */
@Mapper
public interface InfoHeaderParamsMapper{
	public InfoHeaderParams getByVersionId(int versionId);
	
	public int addRefParams(@Param("versionId")String versionId,@Param("refParams")String refParams);
	
	public String checkRefParam(@Param("versionId")String versionId,@Param("refParam")String refParams);

	public void insert();
	
	
	
	
}