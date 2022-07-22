package com.ruoyi.news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.news.mapper.NewsImportantTuwenMapper;
import com.ruoyi.news.domain.NewsImportantTuwen;
import com.ruoyi.news.service.INewsImportantTuwenService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
@Service
public class NewsImportantTuwenServiceImpl implements INewsImportantTuwenService 
{
    @Autowired
    private NewsImportantTuwenMapper newsImportantTuwenMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public NewsImportantTuwen selectNewsImportantTuwenById(Long id)
    {
        return newsImportantTuwenMapper.selectNewsImportantTuwenById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsImportantTuwen 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<NewsImportantTuwen> selectNewsImportantTuwenList(NewsImportantTuwen newsImportantTuwen)
    {
        return newsImportantTuwenMapper.selectNewsImportantTuwenList(newsImportantTuwen);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsImportantTuwen 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNewsImportantTuwen(NewsImportantTuwen newsImportantTuwen)
    {
        return newsImportantTuwenMapper.insertNewsImportantTuwen(newsImportantTuwen);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsImportantTuwen 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNewsImportantTuwen(NewsImportantTuwen newsImportantTuwen)
    {
        return newsImportantTuwenMapper.updateNewsImportantTuwen(newsImportantTuwen);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsImportantTuwenByIds(String ids)
    {
        return newsImportantTuwenMapper.deleteNewsImportantTuwenByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsImportantTuwenById(Long id)
    {
        return newsImportantTuwenMapper.deleteNewsImportantTuwenById(id);
    }
}
