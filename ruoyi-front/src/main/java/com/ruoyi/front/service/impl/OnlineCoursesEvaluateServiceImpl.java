package com.ruoyi.front.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.OnlineCoursesEvaluateMapper;
import com.ruoyi.front.domain.OnlineCoursesEvaluate;
import com.ruoyi.front.service.IOnlineCoursesEvaluateService;
import com.ruoyi.common.core.text.Convert;

/**
 * 线上课程评价Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class OnlineCoursesEvaluateServiceImpl implements IOnlineCoursesEvaluateService 
{
    @Autowired
    private OnlineCoursesEvaluateMapper onlineCoursesEvaluateMapper;

    /**
     * 查询线上课程评价
     * 
     * @param id 线上课程评价ID
     * @return 线上课程评价
     */
    @Override
    public OnlineCoursesEvaluate selectOnlineCoursesEvaluateById(Long id)
    {
        return onlineCoursesEvaluateMapper.selectOnlineCoursesEvaluateById(id);
    }

    /**
     * 查询线上课程评价列表
     * 
     * @param onlineCoursesEvaluate 线上课程评价
     * @return 线上课程评价
     */
    @Override
    public List<OnlineCoursesEvaluate> selectOnlineCoursesEvaluateList(OnlineCoursesEvaluate onlineCoursesEvaluate)
    {
        return onlineCoursesEvaluateMapper.selectOnlineCoursesEvaluateList(onlineCoursesEvaluate);
    }

    /**
     * 新增线上课程评价
     * 
     * @param onlineCoursesEvaluate 线上课程评价
     * @return 结果
     */
    @Override
    public int insertOnlineCoursesEvaluate(OnlineCoursesEvaluate onlineCoursesEvaluate)
    {
        onlineCoursesEvaluate.setCreateTime(DateUtils.getNowDate());
        return onlineCoursesEvaluateMapper.insertOnlineCoursesEvaluate(onlineCoursesEvaluate);
    }

    /**
     * 修改线上课程评价
     * 
     * @param onlineCoursesEvaluate 线上课程评价
     * @return 结果
     */
    @Override
    public int updateOnlineCoursesEvaluate(OnlineCoursesEvaluate onlineCoursesEvaluate)
    {
        onlineCoursesEvaluate.setUpdateTime(DateUtils.getNowDate());
        return onlineCoursesEvaluateMapper.updateOnlineCoursesEvaluate(onlineCoursesEvaluate);
    }

    /**
     * 删除线上课程评价对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteOnlineCoursesEvaluateByIds(String ids)
    {
        return onlineCoursesEvaluateMapper.deleteOnlineCoursesEvaluateByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除线上课程评价信息
     * 
     * @param id 线上课程评价ID
     * @return 结果
     */
    @Override
    public int deleteOnlineCoursesEvaluateById(Long id)
    {
        return onlineCoursesEvaluateMapper.deleteOnlineCoursesEvaluateById(id);
    }
}
