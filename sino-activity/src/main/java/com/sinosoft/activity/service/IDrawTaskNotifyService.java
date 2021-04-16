package com.sinosoft.activity.service;

import java.util.List;
import com.sinosoft.activity.domain.DrawTaskNotify;

/**
 * 活动次数记录信息Service接口
 * 
 * @author dy
 * @date 2021-03-26
 */
public interface IDrawTaskNotifyService 
{
    /**
     * 查询活动次数记录信息
     * 
     * @param USERID 活动次数记录信息ID
     * @return 活动次数记录信息
     */
    public DrawTaskNotify selectDrawTaskNotifyById(String USERID);
    int selectDrawNumByUserId(String userId, String drawCode);
    /**
     * 查询活动次数记录信息列表
     * 
     * @param drawTaskNotify 活动次数记录信息
     * @return 活动次数记录信息集合
     */
    public List<DrawTaskNotify> selectDrawTaskNotifyList(DrawTaskNotify drawTaskNotify);

    /**
     * 新增活动次数记录信息
     * 
     * @param drawTaskNotify 活动次数记录信息
     * @return 结果
     */
    public int insertDrawTaskNotify(DrawTaskNotify drawTaskNotify);

    /**
     * 修改活动次数记录信息
     * 
     * @param drawTaskNotify 活动次数记录信息
     * @return 结果
     */
    public int updateDrawTaskNotify(DrawTaskNotify drawTaskNotify);
    public String updateDrawTaskNotifyNum(DrawTaskNotify drawTaskNotify);

    /**
     * 批量删除活动次数记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawTaskNotifyByIds(String ids);

    /**
     * 删除活动次数记录信息信息
     * 
     * @param USERID 活动次数记录信息ID
     * @return 结果
     */
    public int deleteDrawTaskNotifyById(String USERID);
}
