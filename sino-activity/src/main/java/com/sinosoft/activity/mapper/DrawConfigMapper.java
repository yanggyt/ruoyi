package com.sinosoft.activity.mapper;

import com.sinosoft.activity.domain.DrawConfig;

import java.util.List;

/**
 * 存储奖项配置信息对象Mapper接口
 * 
 * @author ruoyi
 * @date 2021-03-25
 */
public interface DrawConfigMapper 
{
    /**
     * 查询存储奖项配置信息对象
     * 
     * @param DRAWCONFIGID 存储奖项配置信息对象ID
     * @return 存储奖项配置信息对象
     */
    public DrawConfig selectDrawConfigById(String DRAWCONFIGID);

    /**
     * 查询存储奖项配置信息对象列表
     * 
     * @param drawConfig 存储奖项配置信息对象
     * @return 存储奖项配置信息对象集合
     */
    public List<DrawConfig> selectDrawConfigList(DrawConfig drawConfig);

    /**
     * 新增存储奖项配置信息对象
     * 
     * @param drawConfig 存储奖项配置信息对象
     * @return 结果
     */
    public int insertDrawConfig(DrawConfig drawConfig);

    /**
     * 修改存储奖项配置信息对象
     * 
     * @param drawConfig 存储奖项配置信息对象
     * @return 结果
     */
    public int updateDrawConfig(DrawConfig drawConfig);

    /**
     * 删除存储奖项配置信息对象
     * 
     * @param DRAWCONFIGID 存储奖项配置信息对象ID
     * @return 结果
     */
    public int deleteDrawConfigById(String DRAWCONFIGID);

    /**
     * 批量删除存储奖项配置信息对象
     * 
     * @param DRAWCONFIGIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawConfigByIds(String[] DRAWCONFIGIDs);
}
