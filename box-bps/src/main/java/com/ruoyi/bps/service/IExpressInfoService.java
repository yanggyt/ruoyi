package com.ruoyi.bps.service;

import com.ruoyi.bps.domain.ExpressInfo;

import java.util.List;

/**
 * 快递信息Service接口
 * 
 * @author box
 * @date 2021-05-06
 */
public interface IExpressInfoService 
{
    /**
     * 查询快递信息
     * 
     * @param message 快递信息ID
     * @return 快递信息
     */
    public ExpressInfo selectExpressInfoById(String message);

    /**
     * 查询快递信息列表
     * 
     * @param expressInfo 快递信息
     * @return 快递信息集合
     */
    public List<ExpressInfo> selectExpressInfoList(ExpressInfo expressInfo);

    /**
     * 新增快递信息
     * 
     * @param expressInfo 快递信息
     * @return 结果
     */
    public int insertExpressInfo(ExpressInfo expressInfo);

    /**
     * 修改快递信息
     * 
     * @param expressInfo 快递信息
     * @return 结果
     */
    public int updateExpressInfo(ExpressInfo expressInfo);

    /**
     * 批量删除快递信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpressInfoByIds(String ids);

    /**
     * 删除快递信息信息
     * 
     * @param message 快递信息ID
     * @return 结果
     */
    public int deleteExpressInfoById(String message);
}
