package com.ruoyi.news.mapper;

import java.util.List;
import com.ruoyi.news.domain.NewsPhoto;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
public interface NewsPhotoMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public NewsPhoto selectNewsPhotoById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsPhoto 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<NewsPhoto> selectNewsPhotoList(NewsPhoto newsPhoto);

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsPhoto 【请填写功能名称】
     * @return 结果
     */
    public int insertNewsPhoto(NewsPhoto newsPhoto);

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsPhoto 【请填写功能名称】
     * @return 结果
     */
    public int updateNewsPhoto(NewsPhoto newsPhoto);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteNewsPhotoById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsPhotoByIds(String[] ids);
}
