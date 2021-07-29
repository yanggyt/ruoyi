package com.ruoyi.bps.mapper;

import com.ruoyi.bps.domain.ExpressInfo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
/**
 * 快递信息Mapper接口
 * 
 * @author box
 * @date 2021-05-06
 */
public interface ExpressInfoMapper 
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
     * 删除快递信息
     *
     * @param message 快递信息ID
     * @return 结果
     */
    public int deleteExpressInfoById(String message);

    /**
     * 批量删除快递信息
     * 
     * @param messages 需要删除的数据ID
     * @return 结果
     */
    public int deleteExpressInfoByIds(String[] messages);

    /**
     * 批量新增快递信息
     *
     * @param expressInfoList 角色菜单列表
     * @return 结果
     */
    public int batchInsertExpressInfo(List<ExpressInfo> expressInfoList);


    /**
     * 删除快递信息
     *
     * @param queryId 快递信息queryId
     * @return 结果
     */
    public int deleteExpressInfoByQueryId(String queryId);

}
