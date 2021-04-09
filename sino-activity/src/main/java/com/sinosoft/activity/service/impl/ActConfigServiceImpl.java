package com.sinosoft.activity.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinosoft.activity.mapper.ActConfigMapper;
import com.sinosoft.activity.domain.ActConfig;
import com.sinosoft.activity.service.IActConfigService;
import com.ruoyi.common.core.text.Convert;

/**
 * 活动配置Service业务层处理
 * 
 * @author dy
 * @date 2021-04-08
 */
@Service
public class ActConfigServiceImpl implements IActConfigService 
{
    @Autowired
    private ActConfigMapper actConfigMapper;

    /**
     * 查询活动配置
     * 
     * @param id 活动配置ID
     * @return 活动配置
     */
    @Override
    public ActConfig selectActConfigById(Integer id)
    {
        return actConfigMapper.selectActConfigById(id);
    }

    /**
     * 查询活动配置列表
     * 
     * @param actConfig 活动配置
     * @return 活动配置
     */
    @Override
    public List<ActConfig> selectActConfigList(ActConfig actConfig)
    {
        return actConfigMapper.selectActConfigList(actConfig);
    }

    /**
     * 新增活动配置
     * 
     * @param actConfig 活动配置
     * @return 结果
     */
    @Override
    public int insertActConfig(ActConfig actConfig)
    {
        return actConfigMapper.insertActConfig(actConfig);
    }

    /**
     * 修改活动配置
     * 
     * @param actConfig 活动配置
     * @return 结果
     */
    @Override
    public int updateActConfig(ActConfig actConfig)
    {
        actConfig.setUpdateTime(DateUtils.getNowDate());
        return actConfigMapper.updateActConfig(actConfig);
    }

    /**
     * 删除活动配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteActConfigByIds(String ids)
    {
        return actConfigMapper.deleteActConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除活动配置信息
     * 
     * @param id 活动配置ID
     * @return 结果
     */
    @Override
    public int deleteActConfigById(Integer id)
    {
        return actConfigMapper.deleteActConfigById(id);
    }
}
