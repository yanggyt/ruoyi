package com.ruoyi.bps.service;

import java.util.List;
import com.ruoyi.bps.domain.ExpImportQuery;
import com.ruoyi.bps.domain.ExpressInfo;
import com.ruoyi.common.core.domain.AjaxResult;

/**
 * Excel批量快递查询Service接口
 * 
 * @author Bo
 * @date 2021-07-21
 */
public interface IExpImportQueryService 
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
     * 批量删除Excel批量快递查询
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpImportQueryByIds(String ids);

    /**
     * 删除Excel批量快递查询信息
     * 
     * @param sid Excel批量快递查询ID
     * @return 结果
     */
    public int deleteExpImportQueryById(Long sid);

    /**
     * 删除Excel批量快递查询信息
     *
     * @param expressInfoList Excel导入的快递列表
     * @return 结果
     */
    public AjaxResult importData(List<ExpressInfo> expressInfoList) throws Exception;

}
