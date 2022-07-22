package com.ruoyi.news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.news.mapper.NewsProjectLearningMapper;
import com.ruoyi.news.domain.NewsProjectLearning;
import com.ruoyi.news.service.INewsProjectLearningService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
@Service
public class NewsProjectLearningServiceImpl implements INewsProjectLearningService 
{
    @Autowired
    private NewsProjectLearningMapper newsProjectLearningMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public NewsProjectLearning selectNewsProjectLearningById(Long id)
    {
        return newsProjectLearningMapper.selectNewsProjectLearningById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsProjectLearning 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<NewsProjectLearning> selectNewsProjectLearningList(NewsProjectLearning newsProjectLearning)
    {
        return newsProjectLearningMapper.selectNewsProjectLearningList(newsProjectLearning);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsProjectLearning 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNewsProjectLearning(NewsProjectLearning newsProjectLearning)
    {
        return newsProjectLearningMapper.insertNewsProjectLearning(newsProjectLearning);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsProjectLearning 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNewsProjectLearning(NewsProjectLearning newsProjectLearning)
    {
        return newsProjectLearningMapper.updateNewsProjectLearning(newsProjectLearning);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsProjectLearningByIds(String ids)
    {
        return newsProjectLearningMapper.deleteNewsProjectLearningByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsProjectLearningById(Long id)
    {
        return newsProjectLearningMapper.deleteNewsProjectLearningById(id);
    }
}
