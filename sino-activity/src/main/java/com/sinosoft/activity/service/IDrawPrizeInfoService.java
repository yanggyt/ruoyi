package com.sinosoft.activity.service;

import java.util.List;
import com.sinosoft.activity.domain.DrawPrizeInfo;

/**
 * 存储奖品的基础信息Service接口
 * 
 * @author dy
 * @date 2021-03-25
 */
public interface IDrawPrizeInfoService 
{
    /**
     * 查询存储奖品的基础信息
     * 
     * @param PRIZEID 存储奖品的基础信息ID
     * @return 存储奖品的基础信息
     */
    public DrawPrizeInfo selectDrawPrizeInfoById(String PRIZEID);

    /**
     * 查询存储奖品的基础信息列表
     * 
     * @param drawPrizeInfo 存储奖品的基础信息
     * @return 存储奖品的基础信息集合
     */
    public List<DrawPrizeInfo> selectDrawPrizeInfoList(DrawPrizeInfo drawPrizeInfo);

    /**
     * 奖品列表-下拉框
     * @return
     */
    List<DrawPrizeInfo> findDrawPrizeInfoList();

    /**
     * 新增存储奖品的基础信息
     * 
     * @param drawPrizeInfo 存储奖品的基础信息
     * @return 结果
     */
    public int insertDrawPrizeInfo(DrawPrizeInfo drawPrizeInfo);

    /**
     * 修改存储奖品的基础信息
     * 
     * @param drawPrizeInfo 存储奖品的基础信息
     * @return 结果
     */
    public int updateDrawPrizeInfo(DrawPrizeInfo drawPrizeInfo);

    /**
     * 批量删除存储奖品的基础信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawPrizeInfoByIds(String ids);

    /**
     * 删除存储奖品的基础信息信息
     * 
     * @param PRIZEID 存储奖品的基础信息ID
     * @return 结果
     */
    public int deleteDrawPrizeInfoById(String PRIZEID);
}
