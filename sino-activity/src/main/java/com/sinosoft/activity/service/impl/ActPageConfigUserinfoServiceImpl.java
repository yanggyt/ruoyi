package com.sinosoft.activity.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinosoft.activity.mapper.ActPageConfigUserinfoMapper;
import com.sinosoft.activity.domain.ActPageConfigUserinfo;
import com.sinosoft.activity.service.IActPageConfigUserinfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 活动用户信息Service业务层处理
 * 
 * @author dy
 * @date 2021-04-08
 */
@Service
public class ActPageConfigUserinfoServiceImpl implements IActPageConfigUserinfoService 
{
    @Autowired
    private ActPageConfigUserinfoMapper actPageConfigUserinfoMapper;

    /**
     * 查询活动用户信息
     * 
     * @param id 活动用户信息ID
     * @return 活动用户信息
     */
    @Override
    public ActPageConfigUserinfo selectActPageConfigUserinfoById(Integer id)
    {
        return actPageConfigUserinfoMapper.selectActPageConfigUserinfoById(id);
    }

    /**
     * 查询活动用户信息列表
     * 
     * @param actPageConfigUserinfo 活动用户信息
     * @return 活动用户信息
     */
    @Override
    public List<ActPageConfigUserinfo> selectActPageConfigUserinfoList(ActPageConfigUserinfo actPageConfigUserinfo)
    {
        return actPageConfigUserinfoMapper.selectActPageConfigUserinfoList(actPageConfigUserinfo);
    }

    /**
     * 新增活动用户信息
     * 
     * @param actPageConfigUserinfo 活动用户信息
     * @return 结果
     */
    @Override
    public int insertActPageConfigUserinfo(ActPageConfigUserinfo actPageConfigUserinfo)
    {
        return actPageConfigUserinfoMapper.insertActPageConfigUserinfo(actPageConfigUserinfo);
    }

    /**
     * 修改活动用户信息
     * 
     * @param actPageConfigUserinfo 活动用户信息
     * @return 结果
     */
    @Override
    public int updateActPageConfigUserinfo(ActPageConfigUserinfo actPageConfigUserinfo)
    {
        actPageConfigUserinfo.setUpdateTime(DateUtils.getNowDate());
        return actPageConfigUserinfoMapper.updateActPageConfigUserinfo(actPageConfigUserinfo);
    }

    /**
     * 删除活动用户信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteActPageConfigUserinfoByIds(String ids)
    {
        return actPageConfigUserinfoMapper.deleteActPageConfigUserinfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除活动用户信息信息
     * 
     * @param id 活动用户信息ID
     * @return 结果
     */
    @Override
    public int deleteActPageConfigUserinfoById(Integer id)
    {
        return actPageConfigUserinfoMapper.deleteActPageConfigUserinfoById(id);
    }
}
