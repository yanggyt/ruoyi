package com.sinosoft.activity.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinosoft.activity.mapper.ActPageConfigGuideMapper;
import com.sinosoft.activity.domain.ActPageConfigGuide;
import com.sinosoft.activity.service.IActPageConfigGuideService;
import com.ruoyi.common.core.text.Convert;

/**
 * 活动展示内容配置Service业务层处理
 * 
 * @author dy
 * @date 2021-04-08
 */
@Service
public class ActPageConfigGuideServiceImpl implements IActPageConfigGuideService 
{
    @Autowired
    private ActPageConfigGuideMapper actPageConfigGuideMapper;

    /**
     * 查询活动展示内容配置
     * 
     * @param id 活动展示内容配置ID
     * @return 活动展示内容配置
     */
    @Override
    public ActPageConfigGuide selectActPageConfigGuideById(Integer id)
    {
        return actPageConfigGuideMapper.selectActPageConfigGuideById(id);
    }

    /**
     * 查询活动展示内容配置列表
     * 
     * @param actPageConfigGuide 活动展示内容配置
     * @return 活动展示内容配置
     */
    @Override
    public List<ActPageConfigGuide> selectActPageConfigGuideList(ActPageConfigGuide actPageConfigGuide)
    {
        return actPageConfigGuideMapper.selectActPageConfigGuideList(actPageConfigGuide);
    }

    /**
     * 新增活动展示内容配置
     * 
     * @param actPageConfigGuide 活动展示内容配置
     * @return 结果
     */
    @Override
    public int insertActPageConfigGuide(ActPageConfigGuide actPageConfigGuide)
    {
        return actPageConfigGuideMapper.insertActPageConfigGuide(actPageConfigGuide);
    }

    /**
     * 修改活动展示内容配置
     * 
     * @param actPageConfigGuide 活动展示内容配置
     * @return 结果
     */
    @Override
    public int updateActPageConfigGuide(ActPageConfigGuide actPageConfigGuide)
    {
        actPageConfigGuide.setUpdateTime(DateUtils.getNowDate());
        return actPageConfigGuideMapper.updateActPageConfigGuide(actPageConfigGuide);
    }

    /**
     * 删除活动展示内容配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteActPageConfigGuideByIds(String ids)
    {
        return actPageConfigGuideMapper.deleteActPageConfigGuideByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除活动展示内容配置信息
     * 
     * @param id 活动展示内容配置ID
     * @return 结果
     */
    @Override
    public int deleteActPageConfigGuideById(Integer id)
    {
        return actPageConfigGuideMapper.deleteActPageConfigGuideById(id);
    }
}
