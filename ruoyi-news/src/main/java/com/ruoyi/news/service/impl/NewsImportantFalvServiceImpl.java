package com.ruoyi.news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.news.mapper.NewsImportantFalvMapper;
import com.ruoyi.news.domain.NewsImportantFalv;
import com.ruoyi.news.service.INewsImportantFalvService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
@Service
public class NewsImportantFalvServiceImpl implements INewsImportantFalvService 
{
    @Autowired
    private NewsImportantFalvMapper newsImportantFalvMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public NewsImportantFalv selectNewsImportantFalvById(Long id)
    {
        return newsImportantFalvMapper.selectNewsImportantFalvById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsImportantFalv 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<NewsImportantFalv> selectNewsImportantFalvList(NewsImportantFalv newsImportantFalv)
    {
        return newsImportantFalvMapper.selectNewsImportantFalvList(newsImportantFalv);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsImportantFalv 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNewsImportantFalv(NewsImportantFalv newsImportantFalv)
    {
        return newsImportantFalvMapper.insertNewsImportantFalv(newsImportantFalv);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsImportantFalv 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNewsImportantFalv(NewsImportantFalv newsImportantFalv)
    {
        return newsImportantFalvMapper.updateNewsImportantFalv(newsImportantFalv);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsImportantFalvByIds(String ids)
    {
        return newsImportantFalvMapper.deleteNewsImportantFalvByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsImportantFalvById(Long id)
    {
        return newsImportantFalvMapper.deleteNewsImportantFalvById(id);
    }
}
