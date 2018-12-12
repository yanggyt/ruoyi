package com.ruoyi.exam.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.exam.mapper.TrainCoursewareMapper;
import com.ruoyi.exam.domain.TrainCourseware;
import com.ruoyi.exam.service.ITrainCoursewareService;
import com.ruoyi.common.support.Convert;
import com.ruoyi.framework.web.base.AbstractBaseServiceImpl;
/**
 * 课件 服务层实现
 * 
 * @author zhujj
 * @date 2018-12-12
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
