package com.sinosoft.activity.mapper;

import java.util.List;
import com.sinosoft.activity.domain.DrawPrizeInfo;

/**
 * 存储奖品的基础信息Mapper接口
 * 
 * @author dy
 * @date 2021-03-25
 */
public interface DrawPrizeInfoMapper 
{
    /**
     * 查询存储奖品的基础信息
     * 
     * @param PRIZEID 存储奖品的基础信息ID
     * @return 存储奖品的基础信息
     */
    public DrawPrizeInfo selectDrawPrizeInfoById(String PRIZEID);

    /**
     * 获取最大的奖品代码
     * @return
     */
    String findMaxPrizeCode();

    /**
     * 查询存储奖品的基础信息列表
     * 
     * @param drawPrizeInfo 存储奖品的基础信息
     * @return 存储奖品的基础信息集合
     */
    public List<DrawPrizeInfo> selectDrawPrizeInfoList(DrawPrizeInfo drawPrizeInfo);

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
     * 删除存储奖品的基础信息
     * 
     * @param PRIZEID 存储奖品的基础信息ID
     * @return 结果
     */
    public int deleteDrawPrizeInfoById(String PRIZEID);

    /**
     * 批量删除存储奖品的基础信息
     * 
     * @param PRIZEIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawPrizeInfoByIds(String[] PRIZEIDs);
}
