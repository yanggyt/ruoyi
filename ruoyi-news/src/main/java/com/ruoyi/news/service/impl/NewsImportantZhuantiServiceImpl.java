package com.ruoyi.news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.news.mapper.NewsImportantZhuantiMapper;
import com.ruoyi.news.domain.NewsImportantZhuanti;
import com.ruoyi.news.service.INewsImportantZhuantiService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
@Service
public class NewsImportantZhuantiServiceImpl implements INewsImportantZhuantiService 
{
    @Autowired
    private NewsImportantZhuantiMapper newsImportantZhuantiMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public NewsImportantZhuanti selectNewsImportantZhuantiById(Long id)
    {
        return newsImportantZhuantiMapper.selectNewsImportantZhuantiById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsImportantZhuanti 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<NewsImportantZhuanti> selectNewsImportantZhuantiList(NewsImportantZhuanti newsImportantZhuanti)
    {
        return newsImportantZhuantiMapper.selectNewsImportantZhuantiList(newsImportantZhuanti);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsImportantZhuanti 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNewsImportantZhuanti(NewsImportantZhuanti newsImportantZhuanti)
    {
        return newsImportantZhuantiMapper.insertNewsImportantZhuanti(newsImportantZhuanti);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsImportantZhuanti 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNewsImportantZhuanti(NewsImportantZhuanti newsImportantZhuanti)
    {
        return newsImportantZhuantiMapper.updateNewsImportantZhuanti(newsImportantZhuanti);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsImportantZhuantiByIds(String ids)
    {
        return newsImportantZhuantiMapper.deleteNewsImportantZhuantiByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsImportantZhuantiById(Long id)
    {
        return newsImportantZhuantiMapper.deleteNewsImportantZhuantiById(id);
    }
}
