package com.ruoyi.front.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.NewsInformationMapper;
import com.ruoyi.front.domain.NewsInformation;
import com.ruoyi.front.service.INewsInformationService;
import com.ruoyi.common.core.text.Convert;

/**
 * 新闻动态Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class NewsInformationServiceImpl implements INewsInformationService 
{
    @Autowired
    private NewsInformationMapper newsInformationMapper;

    /**
     * 查询新闻动态
     * 
     * @param id 新闻动态ID
     * @return 新闻动态
     */
    @Override
    public NewsInformation selectNewsInformationById(Long id)
    {
        return newsInformationMapper.selectNewsInformationById(id);
    }

    /**
     * 查询新闻动态列表
     * 
     * @param newsInformation 新闻动态
     * @return 新闻动态
     */
    @Override
    public List<NewsInformation> selectNewsInformationList(NewsInformation newsInformation)
    {
        return newsInformationMapper.selectNewsInformationList(newsInformation);
    }

    /**
     * 新增新闻动态
     * 
     * @param newsInformation 新闻动态
     * @return 结果
     */
    @Override
    public int insertNewsInformation(NewsInformation newsInformation)
    {
        newsInformation.setCreateTime(DateUtils.getNowDate());
        return newsInformationMapper.insertNewsInformation(newsInformation);
    }

    /**
     * 修改新闻动态
     * 
     * @param newsInformation 新闻动态
     * @return 结果
     */
    @Override
    public int updateNewsInformation(NewsInformation newsInformation)
    {
        newsInformation.setUpdateTime(DateUtils.getNowDate());
        return newsInformationMapper.updateNewsInformation(newsInformation);
    }

    /**
     * 删除新闻动态对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteNewsInformationByIds(String ids)
    {
        return newsInformationMapper.deleteNewsInformationByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除新闻动态信息
     * 
     * @param id 新闻动态ID
     * @return 结果
     */
    @Override
    public int deleteNewsInformationById(Long id)
    {
        return newsInformationMapper.deleteNewsInformationById(id);
    }
}
