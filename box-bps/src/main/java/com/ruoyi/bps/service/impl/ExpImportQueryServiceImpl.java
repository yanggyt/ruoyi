package com.ruoyi.bps.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bps.mapper.ExpImportQueryMapper;
import com.ruoyi.bps.domain.ExpImportQuery;
import com.ruoyi.bps.service.IExpImportQueryService;
import com.ruoyi.common.core.text.Convert;

/**
 * Excel批量快递查询Service业务层处理
 * 
 * @author Bo
 * @date 2021-07-21
 */
@Service
public class ExpImportQueryServiceImpl implements IExpImportQueryService 
{
    @Autowired
    private ExpImportQueryMapper expImportQueryMapper;

    /**
     * 查询Excel批量快递查询
     * 
     * @param sid Excel批量快递查询ID
     * @return Excel批量快递查询
     */
    @Override
    public ExpImportQuery selectExpImportQueryById(Long sid)
    {
        return expImportQueryMapper.selectExpImportQueryById(sid);
    }

    /**
     * 查询Excel批量快递查询列表
     * 
     * @param expImportQuery Excel批量快递查询
     * @return Excel批量快递查询
     */
    @Override
    public List<ExpImportQuery> selectExpImportQueryList(ExpImportQuery expImportQuery)
    {
        return expImportQueryMapper.selectExpImportQueryList(expImportQuery);
    }

    /**
     * 新增Excel批量快递查询
     * 
     * @param expImportQuery Excel批量快递查询
     * @return 结果
     */
    @Override
    public int insertExpImportQuery(ExpImportQuery expImportQuery)
    {
        return expImportQueryMapper.insertExpImportQuery(expImportQuery);
    }

    /**
     * 修改Excel批量快递查询
     * 
     * @param expImportQuery Excel批量快递查询
     * @return 结果
     */
    @Override
    public int updateExpImportQuery(ExpImportQuery expImportQuery)
    {
        return expImportQueryMapper.updateExpImportQuery(expImportQuery);
    }

    /**
     * 删除Excel批量快递查询对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteExpImportQueryByIds(String ids)
    {
        return expImportQueryMapper.deleteExpImportQueryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除Excel批量快递查询信息
     * 
     * @param sid Excel批量快递查询ID
     * @return 结果
     */
    @Override
    public int deleteExpImportQueryById(Long sid)
    {
        return expImportQueryMapper.deleteExpImportQueryById(sid);
    }
}
