package com.sinosoft.activity.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinosoft.activity.mapper.ActPageConfigSubscribeMapper;
import com.sinosoft.activity.domain.ActPageConfigSubscribe;
import com.sinosoft.activity.service.IActPageConfigSubscribeService;
import com.ruoyi.common.core.text.Convert;

/**
 * 活动收集配置Service业务层处理
 * 
 * @author dy
 * @date 2021-04-08
 */
@Service
public class ActPageConfigSubscribeServiceImpl implements IActPageConfigSubscribeService 
{
    @Autowired
    private ActPageConfigSubscribeMapper actPageConfigSubscribeMapper;

    /**
     * 查询活动收集配置
     * 
     * @param id 活动收集配置ID
     * @return 活动收集配置
     */
    @Override
    public ActPageConfigSubscribe selectActPageConfigSubscribeById(Integer id)
    {
        return actPageConfigSubscribeMapper.selectActPageConfigSubscribeById(id);
    }

    /**
     * 查询活动收集配置列表
     * 
     * @param actPageConfigSubscribe 活动收集配置
     * @return 活动收集配置
     */
    @Override
    public List<ActPageConfigSubscribe> selectActPageConfigSubscribeList(ActPageConfigSubscribe actPageConfigSubscribe)
    {
        return actPageConfigSubscribeMapper.selectActPageConfigSubscribeList(actPageConfigSubscribe);
    }

    /**
     * 新增活动收集配置
     * 
     * @param actPageConfigSubscribe 活动收集配置
     * @return 结果
     */
    @Override
    public int insertActPageConfigSubscribe(ActPageConfigSubscribe actPageConfigSubscribe)
    {
        return actPageConfigSubscribeMapper.insertActPageConfigSubscribe(actPageConfigSubscribe);
    }

    /**
     * 修改活动收集配置
     * 
     * @param actPageConfigSubscribe 活动收集配置
     * @return 结果
     */
    @Override
    public int updateActPageConfigSubscribe(ActPageConfigSubscribe actPageConfigSubscribe)
    {
        actPageConfigSubscribe.setUpdateTime(DateUtils.getNowDate());
        return actPageConfigSubscribeMapper.updateActPageConfigSubscribe(actPageConfigSubscribe);
    }

    /**
     * 删除活动收集配置对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteActPageConfigSubscribeByIds(String ids)
    {
        return actPageConfigSubscribeMapper.deleteActPageConfigSubscribeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除活动收集配置信息
     * 
     * @param id 活动收集配置ID
     * @return 结果
     */
    @Override
    public int deleteActPageConfigSubscribeById(Integer id)
    {
        return actPageConfigSubscribeMapper.deleteActPageConfigSubscribeById(id);
    }
    /**
     * 根据活动编码查询
     * @param drawCode
     * @return
     */
    @Override
    public ActPageConfigSubscribe selectActPageConfigSubscribeByCode(String drawCode) {
        return actPageConfigSubscribeMapper.selectActPageConfigSubscribeByCode(drawCode);
    }
}
