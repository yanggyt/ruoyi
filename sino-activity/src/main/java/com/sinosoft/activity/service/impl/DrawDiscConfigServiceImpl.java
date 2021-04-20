package com.sinosoft.activity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinosoft.activity.mapper.DrawDiscConfigMapper;
import com.sinosoft.activity.domain.DrawDiscConfig;
import com.sinosoft.activity.service.IDrawDiscConfigService;
import com.ruoyi.common.core.text.Convert;

/**
 * 用户路由信息Service业务层处理
 * 
 * @author dy
 * @date 2021-04-19
 */
@Service
public class DrawDiscConfigServiceImpl implements IDrawDiscConfigService 
{
    @Autowired
    private DrawDiscConfigMapper drawDiscConfigMapper;

    /**
     * 查询用户路由信息
     * 
     * @param DISCDRAWCONFIGID 用户路由信息ID
     * @return 用户路由信息
     */
    @Override
    public DrawDiscConfig selectDrawDiscConfigById(String DISCDRAWCONFIGID)
    {
        return drawDiscConfigMapper.selectDrawDiscConfigById(DISCDRAWCONFIGID);
    }

    /**
     * 查询用户路由信息列表
     * 
     * @param drawDiscConfig 用户路由信息
     * @return 用户路由信息
     */
    @Override
    public List<DrawDiscConfig> selectDrawDiscConfigList(DrawDiscConfig drawDiscConfig)
    {
        return drawDiscConfigMapper.selectDrawDiscConfigList(drawDiscConfig);
    }

    /**
     * 新增用户路由信息
     * 
     * @param drawDiscConfig 用户路由信息
     * @return 结果
     */
    @Override
    public int insertDrawDiscConfig(DrawDiscConfig drawDiscConfig)
    {
        return drawDiscConfigMapper.insertDrawDiscConfig(drawDiscConfig);
    }

    /**
     * 修改用户路由信息
     * 
     * @param drawDiscConfig 用户路由信息
     * @return 结果
     */
    @Override
    public int updateDrawDiscConfig(DrawDiscConfig drawDiscConfig)
    {
        return drawDiscConfigMapper.updateDrawDiscConfig(drawDiscConfig);
    }

    /**
     * 删除用户路由信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDrawDiscConfigByIds(String ids)
    {
        return drawDiscConfigMapper.deleteDrawDiscConfigByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户路由信息信息
     * 
     * @param DISCDRAWCONFIGID 用户路由信息ID
     * @return 结果
     */
    @Override
    public int deleteDrawDiscConfigById(String DISCDRAWCONFIGID)
    {
        return drawDiscConfigMapper.deleteDrawDiscConfigById(DISCDRAWCONFIGID);
    }
}
