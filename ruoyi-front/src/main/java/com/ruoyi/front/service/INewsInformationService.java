package com.ruoyi.front.service;

import java.util.List;
import com.ruoyi.front.domain.NewsInformation;

/**
 * 新闻动态Service接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface INewsInformationService 
{
    /**
     * 查询新闻动态
     * 
     * @param id 新闻动态ID
     * @return 新闻动态
     */
    public NewsInformation selectNewsInformationById(Long id);

    /**
     * 查询新闻动态列表
     * 
     * @param newsInformation 新闻动态
     * @return 新闻动态集合
     */
    public List<NewsInformation> selectNewsInformationList(NewsInformation newsInformation);

    /**
     * 新增新闻动态
     * 
     * @param newsInformation 新闻动态
     * @return 结果
     */
    public int insertNewsInformation(NewsInformation newsInformation);

    /**
     * 修改新闻动态
     * 
     * @param newsInformation 新闻动态
     * @return 结果
     */
    public int updateNewsInformation(NewsInformation newsInformation);

    /**
     * 批量删除新闻动态
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteNewsInformationByIds(String ids);

    /**
     * 删除新闻动态信息
     * 
     * @param id 新闻动态ID
     * @return 结果
     */
    public int deleteNewsInformationById(Long id);
}
