package com.ruoyi.agile.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.agile.mapper.GenTableMapper;
import com.ruoyi.agile.domain.GenTable;
import com.ruoyi.agile.service.IGenTableService;
import com.ruoyi.common.support.Convert;

/**
 * 代码生成 服务层实现
 * 
 * @author zhujj
 * @date 2018-11-29
 */
@Service
public class GenTableServiceImpl implements IGenTableService 
{
	@Autowired
	private GenTableMapper genTableMapper;

	/**
     * 查询代码生成信息
     * 
     * @param tableName 代码生成ID
     * @return 代码生成信息
     */
    @Override
	public GenTable selectGenTableById(String tableName)
	{
	    return genTableMapper.selectGenTableById(tableName);
	}
	
	/**
     * 查询代码生成列表
     * 
     * @param genTable 代码生成信息
     * @return 代码生成集合
     */
	@Override
	public List<GenTable> selectGenTableList(GenTable genTable)
	{
	    return genTableMapper.selectGenTableList(genTable);
	}
	
    /**
     * 新增代码生成
     * 
     * @param genTable 代码生成信息
     * @return 结果
     */
	@Override
	public int insertGenTable(GenTable genTable)
	{
	    return genTableMapper.insertGenTable(genTable);
	}
	
	/**
     * 修改代码生成
     * 
     * @param genTable 代码生成信息
     * @return 结果
     */
	@Override
	public int updateGenTable(GenTable genTable)
	{
	    return genTableMapper.updateGenTable(genTable);
	}

	/**
     * 删除代码生成对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteGenTableByIds(String ids)
	{
		return genTableMapper.deleteGenTableByIds(Convert.toStrArray(ids));
	}
	
}
