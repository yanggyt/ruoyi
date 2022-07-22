package com.ruoyi.news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.news.mapper.NewsPhotoMapper;
import com.ruoyi.news.domain.NewsPhoto;
import com.ruoyi.news.service.INewsPhotoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
@Service
public class NewsPhotoServiceImpl implements INewsPhotoService 
{
    @Autowired
    private NewsPhotoMapper newsPhotoMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public NewsPhoto selectNewsPhotoById(Long id)
    {
        return newsPhotoMapper.selectNewsPhotoById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsPhoto 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<NewsPhoto> selectNewsPhotoList(NewsPhoto newsPhoto)
    {
        return newsPhotoMapper.selectNewsPhotoList(newsPhoto);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsPhoto 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNewsPhoto(NewsPhoto newsPhoto)
    {
        return newsPhotoMapper.insertNewsPhoto(newsPhoto);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsPhoto 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNewsPhoto(NewsPhoto newsPhoto)
    {
        return newsPhotoMapper.updateNewsPhoto(newsPhoto);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsPhotoByIds(String ids)
    {
        return newsPhotoMapper.deleteNewsPhotoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsPhotoById(Long id)
    {
        return newsPhotoMapper.deleteNewsPhotoById(id);
    }
}
