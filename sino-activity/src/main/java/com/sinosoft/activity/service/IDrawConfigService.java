package com.sinosoft.activity.service;

import java.util.List;

import com.sinosoft.activity.domain.DrawConfig;

/**
 * 存储奖项配置信息列表Service接口
 * 
 * @author xlh
 * @date 2021-03-25
 */
public interface IDrawConfigService 
{
    /**
     * 查询存储奖项配置信息列表
     * 
     * @param DRAWCONFIGID 存储奖项配置信息列表ID
     * @return 存储奖项配置信息列表
     */
    public DrawConfig selectDrawConfigById(String DRAWCONFIGID);

    /**
     * 查询存储奖项配置信息列表列表
     * 
     * @param drawConfig 存储奖项配置信息列表
     * @return 存储奖项配置信息列表集合
     */
    public List<DrawConfig> selectDrawConfigList(DrawConfig drawConfig);

    /**
     * 新增存储奖项配置信息列表
     * 
     * @param drawConfig 存储奖项配置信息列表
     * @return 结果
     */
    public int insertDrawConfig(DrawConfig drawConfig);

    /**
     * 修改存储奖项配置信息列表
     * 
     * @param drawConfig 存储奖项配置信息列表
     * @return 结果
     */
    public int updateDrawConfig(DrawConfig drawConfig);

    /**
     * 批量删除存储奖项配置信息列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawConfigByIds(String ids);

    /**
     * 删除存储奖项配置信息列表信息
     * 
     * @param DRAWCONFIGID 存储奖项配置信息列表ID
     * @return 结果
     */
    public int deleteDrawConfigById(String DRAWCONFIGID);
}
