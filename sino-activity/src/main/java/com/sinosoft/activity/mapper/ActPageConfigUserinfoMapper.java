package com.sinosoft.activity.mapper;

import java.util.List;
import com.sinosoft.activity.domain.ActPageConfigUserinfo;

/**
 * 活动用户信息Mapper接口
 * 
 * @author dy
 * @date 2021-04-08
 */
public interface ActPageConfigUserinfoMapper 
{
    /**
     * 查询活动用户信息
     * 
     * @param id 活动用户信息ID
     * @return 活动用户信息
     */
    public ActPageConfigUserinfo selectActPageConfigUserinfoById(Integer id);

    /**
     * 查询活动用户信息列表
     * 
     * @param actPageConfigUserinfo 活动用户信息
     * @return 活动用户信息集合
     */
    public List<ActPageConfigUserinfo> selectActPageConfigUserinfoList(ActPageConfigUserinfo actPageConfigUserinfo);

    /**
     * 新增活动用户信息
     * 
     * @param actPageConfigUserinfo 活动用户信息
     * @return 结果
     */
    public int insertActPageConfigUserinfo(ActPageConfigUserinfo actPageConfigUserinfo);

    /**
     * 修改活动用户信息
     * 
     * @param actPageConfigUserinfo 活动用户信息
     * @return 结果
     */
    public int updateActPageConfigUserinfo(ActPageConfigUserinfo actPageConfigUserinfo);

    /**
     * 删除活动用户信息
     * 
     * @param id 活动用户信息ID
     * @return 结果
     */
    public int deleteActPageConfigUserinfoById(Integer id);

    /**
     * 批量删除活动用户信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteActPageConfigUserinfoByIds(String[] ids);
}
