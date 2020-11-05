package com.ruoyi.front.service;

import java.util.List;
import com.ruoyi.front.domain.OnlineCoursesEvaluate;
import org.apache.ibatis.annotations.Param;

/**
 * 线上课程评价Service接口
 * 
 * @author ruoyi
 * @date 2020-11-05
 */
public interface IOnlineCoursesEvaluateService 
{
    /**
     * 查询线上课程评价
     * 
     * @param id 线上课程评价ID
     * @return 线上课程评价
     */
    public OnlineCoursesEvaluate selectOnlineCoursesEvaluateById(Long id);

    /**
     * 查询线上课程评价列表
     * 
     * @param onlineCoursesEvaluate 线上课程评价
     * @return 线上课程评价集合
     */
    public List<OnlineCoursesEvaluate> selectOnlineCoursesEvaluateList(OnlineCoursesEvaluate onlineCoursesEvaluate);

    /**
     * 新增线上课程评价
     * 
     * @param onlineCoursesEvaluate 线上课程评价
     * @return 结果
     */
    public int insertOnlineCoursesEvaluate(OnlineCoursesEvaluate onlineCoursesEvaluate);

    /**
     * 修改线上课程评价
     * 
     * @param onlineCoursesEvaluate 线上课程评价
     * @return 结果
     */
    public int updateOnlineCoursesEvaluate(OnlineCoursesEvaluate onlineCoursesEvaluate);

    /**
     * 批量删除线上课程评价
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteOnlineCoursesEvaluateByIds(String ids);

    /**
     * 删除线上课程评价信息
     * 
     * @param id 线上课程评价ID
     * @return 结果
     */
    public int deleteOnlineCoursesEvaluateById(Long id);
    /**
     * 审核评论
     * @param ids
     * @param auditStatus
     * @return
     */
    public int audit(String ids, String auditStatus, String remark);
}
