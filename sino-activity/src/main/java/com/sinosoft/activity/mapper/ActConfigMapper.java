package com.sinosoft.activity.mapper;

import java.util.List;
import com.sinosoft.activity.domain.ActConfig;

/**
 * 活动配置Mapper接口
 * 
 * @author dy
 * @date 2021-04-08
 */
public interface ActConfigMapper 
{
    /**
     * 查询活动配置
     * 
     * @param id 活动配置ID
     * @return 活动配置
     */
    public ActConfig selectActConfigById(Integer id);

    /**
     * 查询活动配置列表
     * 
     * @param actConfig 活动配置
     * @return 活动配置集合
     */
    public List<ActConfig> selectActConfigList(ActConfig actConfig);

    /**
     * 新增活动配置
     * 
     * @param actConfig 活动配置
     * @return 结果
     */
    public int insertActConfig(ActConfig actConfig);

    /**
     * 修改活动配置
     * 
     * @param actConfig 活动配置
     * @return 结果
     */
    public int updateActConfig(ActConfig actConfig);

    /**
     * 删除活动配置
     * 
     * @param id 活动配置ID
     * @return 结果
     */
    public int deleteActConfigById(Integer id);

    /**
     * 批量删除活动配置
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteActConfigByIds(String[] ids);
}
