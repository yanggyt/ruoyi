package com.ruoyi.bps.mapper;

import java.util.List;
import com.ruoyi.bps.domain.ExpImportQuery;

/**
 * Excel批量快递查询Mapper接口
 * 
 * @author Bo
 * @date 2021-07-21
 */
public interface ExpImportQueryMapper 
{
    /**
     * 查询Excel批量快递查询
     * 
     * @param sid Excel批量快递查询ID
     * @return Excel批量快递查询
     */
    public ExpImportQuery selectExpImportQueryById(Long sid);

    /**
     * 查询Excel批量快递查询列表
     * 
     * @param expImportQuery Excel批量快递查询
     * @return Excel批量快递查询集合
     */
    public List<ExpImportQuery> selectExpImportQueryList(ExpImportQuery expImportQuery);

    /**
     * 新增Excel批量快递查询
     * 
     * @param expImportQuery Excel批量快递查询
     * @return 结果
     */
    public int insertExpImportQuery(ExpImportQuery expImportQuery);

    /**
     * 修改Excel批量快递查询
     * 
     * @param expImportQuery Excel批量快递查询
     * @return 结果
     */
    public int updateExpImportQuery(ExpImportQuery expImportQuery);

    /**
     * 删除Excel批量快递查询
     * 
     * @param sid Excel批量快递查询ID
     * @return 结果
     */
    public int deleteExpImportQueryById(Long sid);

    /**
     * 批量删除Excel批量快递查询
     * 
     * @param sids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpImportQueryByIds(String[] sids);
}
