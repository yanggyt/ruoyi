package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.BusNews;
import java.util.List;	

/**
 * 新闻 数据层
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
public interface BusNewsMapper 
{
	/**
     * 查询新闻信息
     * 
     * @param newsId 新闻ID
     * @return 新闻信息
     */
	public BusNews selectBusNewsById(Long newsId);
	
	/**
     * 查询新闻列表
     * 
     * @param busNews 新闻信息
     * @return 新闻集合
     */
	public List<BusNews> selectBusNewsList(BusNews busNews);
	
	/**
     * 新增新闻
     * 
     * @param busNews 新闻信息
     * @return 结果
     */
	public int insertBusNews(BusNews busNews);
	
	/**
     * 修改新闻
     * 
     * @param busNews 新闻信息
     * @return 结果
     */
	public int updateBusNews(BusNews busNews);
	
	/**
     * 删除新闻
     * 
     * @param newsId 新闻ID
     * @return 结果
     */
	public int deleteBusNewsById(Long newsId);
	
	/**
     * 批量删除新闻
     * 
     * @param newsIds 需要删除的数据ID
     * @return 结果
     */
	public int deleteBusNewsByIds(String[] newsIds);
	
}