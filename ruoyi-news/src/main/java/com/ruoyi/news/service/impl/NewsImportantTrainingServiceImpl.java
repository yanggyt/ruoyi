package com.ruoyi.news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.news.mapper.NewsImportantTrainingMapper;
import com.ruoyi.news.domain.NewsImportantTraining;
import com.ruoyi.news.service.INewsImportantTrainingService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
@Service
public class NewsImportantTrainingServiceImpl implements INewsImportantTrainingService 
{
    @Autowired
    private NewsImportantTrainingMapper newsImportantTrainingMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public NewsImportantTraining selectNewsImportantTrainingById(Long id)
    {
        return newsImportantTrainingMapper.selectNewsImportantTrainingById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsImportantTraining 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<NewsImportantTraining> selectNewsImportantTrainingList(NewsImportantTraining newsImportantTraining)
    {
        return newsImportantTrainingMapper.selectNewsImportantTrainingList(newsImportantTraining);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsImportantTraining 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNewsImportantTraining(NewsImportantTraining newsImportantTraining)
    {
        return newsImportantTrainingMapper.insertNewsImportantTraining(newsImportantTraining);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsImportantTraining 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNewsImportantTraining(NewsImportantTraining newsImportantTraining)
    {
        return newsImportantTrainingMapper.updateNewsImportantTraining(newsImportantTraining);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsImportantTrainingByIds(String ids)
    {
        return newsImportantTrainingMapper.deleteNewsImportantTrainingByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsImportantTrainingById(Long id)
    {
        return newsImportantTrainingMapper.deleteNewsImportantTrainingById(id);
    }
}
