package com.ruoyi.train.courseware.service.impl;

import java.util.List;

import com.ruoyi.train.courseware.domain.TrainCourseware;
import com.ruoyi.train.courseware.mapper.TrainCoursewareMapper;
import com.ruoyi.train.courseware.service.ITrainCoursewareService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 课件 服务层实现
 *
 * @author zhujj
 * @date 2018-12-23
 */
@Service
public class TrainCoursewareServiceImpl extends AbstractBaseServiceImpl<TrainCoursewareMapper,TrainCourseware> implements ITrainCoursewareService
{
    @Autowired
    private TrainCoursewareMapper trainCoursewareMapper;


    /**
     * 查询课件列表
     *
     * @param trainCourseware 课件信息
     * @return 课件集合
     */
    @Override
    public List<TrainCourseware> selectTrainCoursewareList(TrainCourseware trainCourseware)
    {
        return trainCoursewareMapper.selectTrainCoursewareList(trainCourseware);
    }
    /**
     * 查询课件分页列表
     *
     * @param trainCourseware 课件信息
     * @return 课件集合
     */
    @Override
    public List<TrainCourseware> selectTrainCoursewarePage(TrainCourseware trainCourseware)
    {
        startPage();
        return trainCoursewareMapper.selectTrainCoursewareList(trainCourseware);
    }

}
