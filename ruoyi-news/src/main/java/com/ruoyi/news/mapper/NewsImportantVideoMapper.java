package com.ruoyi.news.mapper;

import java.util.List;
import com.ruoyi.news.domain.NewsImportantVideo;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
public interface NewsImportantVideoMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public NewsImportantVideo selectNewsImportantVideoById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsImportantVideo 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<NewsImportantVideo> selectNewsImportantVideoList(NewsImportantVideo newsImportantVideo);

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsImportantVideo 【请填写功能名称】
     * @return 结果
     */
    public int insertNewsImportantVideo(NewsImportantVideo newsImportantVideo);

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsImportantVideo 【请填写功能名称】
     * @return 结果
     */
    public int updateNewsImportantVideo(NewsImportantVideo newsImportantVideo);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteNewsImportantVideoById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsImportantVideoByIds(String[] ids);
}
