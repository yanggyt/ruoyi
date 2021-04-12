package com.sinosoft.activity.mapper;

import java.util.List;
import com.sinosoft.activity.domain.DrawTaskNotify;

/**
 * 活动次数记录信息Mapper接口
 * 
 * @author dy
 * @date 2021-03-26
 */
public interface DrawTaskNotifyMapper 
{
    /**
     * 查询活动次数记录信息
     * 
     * @param USERID 活动次数记录信息ID
     * @return 活动次数记录信息
     */
    public DrawTaskNotify selectDrawTaskNotifyById(String USERID);
    public int selectDrawNumByUserId(String userId, String drawCode);

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

    /**
     * 删除活动次数记录信息
     * 
     * @param USERID 活动次数记录信息ID
     * @return 结果
     */
    public int deleteDrawTaskNotifyById(String USERID);

    /**
     * 批量删除活动次数记录信息
     * 
     * @param USERIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawTaskNotifyByIds(String[] USERIDs);
}
