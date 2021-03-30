package com.sinosoft.activity.service.impl;

import java.util.List;

import com.sinosoft.activity.domain.DrawConfig;
import com.sinosoft.activity.mapper.DrawConfigMapper;
import com.sinosoft.activity.service.IDrawConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;

/**
 * 存储奖项配置信息列表Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-25
 */
@Service
public class DrawConfigServiceImpl implements IDrawConfigService
{
    @Autowired
    private DrawConfigMapper drawConfigMapper;

    /**
     * 查询存储奖项配置信息列表
     * 
     * @param DRAWCONFIGID 存储奖项配置信息列表ID
     * @return 存储奖项配置信息列表
     */
    @Override
    public DrawConfig selectDrawConfigById(String DRAWCONFIGID)
    {
        return drawConfigMapper.selectDrawConfigById(DRAWCONFIGID);
    }

    /**
     * 查询存储奖项配置信息列表列表
     * 
     * @param drawConfig 存储奖项配置信息列表
     * @return 存储奖项配置信息列表
     */
    @Override
    public List<DrawConfig> selectDrawConfigList(DrawConfig drawConfig)
    {
        return drawConfigMapper.selectDrawConfigList(drawConfig);
    }

    /**
     * 新增存储奖项配置信息列表
     * 
     * @param drawConfig 存储奖项配置信息列表
     * @return 结果
     */
    @Override
    public int insertDrawConfig(DrawConfig drawConfig)
    {
        return drawConfigMapper.insertDrawConfig(drawConfig);
    }

    /**
     * 修改存储奖项配置信息列表
     * 
     * @param drawConfig 存储奖项配置信息列表
     * @return 结果
     */
    @Override
    public int updateDrawConfig(DrawConfig drawConfig)
    {
        return drawConfigMapper.updateDrawConfig(drawConfig);
    }

    /**
     * 删除存储奖项配置信息列表对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDrawConfigByIds(String ids)
    {
        return drawConfigMapper.deleteDrawConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除存储奖项配置信息列表信息
     * 
     * @param DRAWCONFIGID 存储奖项配置信息列表ID
     * @return 结果
     */
    @Override
    public int deleteDrawConfigById(String DRAWCONFIGID)
    {
        return drawConfigMapper.deleteDrawConfigById(DRAWCONFIGID);
    }
}
