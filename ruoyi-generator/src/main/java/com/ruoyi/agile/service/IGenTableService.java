package com.ruoyi.agile.service;

import com.ruoyi.agile.domain.GenTable;
import java.util.List;

/**
 * 代码生成 服务层
 * 
 * @author zhujj
 * @date 2018-11-29
 */
public interface IGenTableService 
{
	/**
     * 查询代码生成信息
     * 
     * @param tableName 代码生成ID
     * @return 代码生成信息
     */
	public GenTable selectGenTableById(String tableName);
	
	/**
     * 查询代码生成列表
     * 
     * @param genTable 代码生成信息
     * @return 代码生成集合
     */
	public List<GenTable> selectGenTableList(GenTable genTable);
	
	/**
     * 新增代码生成
     * 
     * @param genTable 代码生成信息
     * @return 结果
     */
	public int insertGenTable(GenTable genTable);
	
	/**
     * 修改代码生成
     * 
     * @param genTable 代码生成信息
     * @return 结果
     */
	public int updateGenTable(GenTable genTable);
		
	/**
     * 删除代码生成信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	public int deleteGenTableByIds(String ids);
	
}
