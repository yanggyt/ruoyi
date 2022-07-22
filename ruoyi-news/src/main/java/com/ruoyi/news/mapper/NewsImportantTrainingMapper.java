package com.ruoyi.news.mapper;

import java.util.List;
import com.ruoyi.news.domain.NewsImportantTraining;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
public interface NewsImportantTrainingMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public NewsImportantTraining selectNewsImportantTrainingById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsImportantTraining 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<NewsImportantTraining> selectNewsImportantTrainingList(NewsImportantTraining newsImportantTraining);

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsImportantTraining 【请填写功能名称】
     * @return 结果
     */
    public int insertNewsImportantTraining(NewsImportantTraining newsImportantTraining);

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsImportantTraining 【请填写功能名称】
     * @return 结果
     */
    public int updateNewsImportantTraining(NewsImportantTraining newsImportantTraining);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteNewsImportantTrainingById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsImportantTrainingByIds(String[] ids);
}
