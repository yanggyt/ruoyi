package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.BusNewsMapper;
import com.ruoyi.system.domain.BusNews;
import com.ruoyi.system.service.IBusNewsService;
import com.ruoyi.common.core.text.Convert;

/**
 * 新闻 服务层实现
 * 
 * @author ruoyi
 * @date 2021-01-07
 */
@Service
public class BusNewsServiceImpl implements IBusNewsService 
{
	@Autowired
	private BusNewsMapper busNewsMapper;

	/**
     * 查询新闻信息
     * 
     * @param newsId 新闻ID
     * @return 新闻信息
     */
    @Override
	public BusNews selectBusNewsById(Long newsId)
	{
	    return busNewsMapper.selectBusNewsById(newsId);
	}
	
	/**
     * 查询新闻列表
     * 
     * @param busNews 新闻信息
     * @return 新闻集合
     */
	@Override
	public List<BusNews> selectBusNewsList(BusNews busNews)
	{
	    return busNewsMapper.selectBusNewsList(busNews);
	}
	
    /**
     * 新增新闻
     * 
     * @param busNews 新闻信息
     * @return 结果
     */
	@Override
	public int insertBusNews(BusNews busNews)
	{
	    return busNewsMapper.insertBusNews(busNews);
	}
	
	/**
     * 修改新闻
     * 
     * @param busNews 新闻信息
     * @return 结果
     */
	@Override
	public int updateBusNews(BusNews busNews)
	{
	    return busNewsMapper.updateBusNews(busNews);
	}

	/**
     * 删除新闻对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
	@Override
	public int deleteBusNewsByIds(String ids)
	{
		return busNewsMapper.deleteBusNewsByIds(Convert.toStrArray(ids));
	}
	
}
