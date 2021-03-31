package com.sinosoft.activity.service.impl;

import java.util.List;

import com.sinosoft.activity.domain.DrawInfo;
import com.sinosoft.activity.mapper.DrawInfoMapper;
import com.sinosoft.activity.service.IDrawInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;

/**
 * 抽奖活动管理对象Service业务层处理
 * 
 * @author xlh
 * @date 2021-03-25
 */
@Service
public class DrawInfoServiceImpl implements IDrawInfoService
{
    @Autowired
    private DrawInfoMapper drawInfoMapper;

    /**
     * 查询抽奖活动管理对象
     * 
     * @param DRAWID 抽奖活动管理对象ID
     * @return 抽奖活动管理对象
     */
    @Override
    public DrawInfo selectDrawInfoById(String DRAWID)
    {
        return drawInfoMapper.selectDrawInfoById(DRAWID);
    }

    /**
     * 查询抽奖活动管理对象列表
     * 
     * @param drawInfo 抽奖活动管理对象
     * @return 抽奖活动管理对象
     */
    @Override
    public List<DrawInfo> selectDrawInfoList(DrawInfo drawInfo)
    {
        return drawInfoMapper.selectDrawInfoList(drawInfo);
    }

    /**
     * 新增抽奖活动管理对象
     * 
     * @param drawInfo 抽奖活动管理对象
     * @return 结果
     */
    @Override
    public int insertDrawInfo(DrawInfo drawInfo)
    {
        return drawInfoMapper.insertDrawInfo(drawInfo);
    }

    /**
     * 修改抽奖活动管理对象
     * 
     * @param drawInfo 抽奖活动管理对象
     * @return 结果
     */
    @Override
    public int updateDrawInfo(DrawInfo drawInfo)
    {
        return drawInfoMapper.updateDrawInfo(drawInfo);
    }

    /**
     * 删除抽奖活动管理对象对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDrawInfoByIds(String ids)
    {
        return drawInfoMapper.deleteDrawInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除抽奖活动管理对象信息
     * 
     * @param DRAWID 抽奖活动管理对象ID
     * @return 结果
     */
    @Override
    public int deleteDrawInfoById(String DRAWID)
    {
        return drawInfoMapper.deleteDrawInfoById(DRAWID);
    }
}
