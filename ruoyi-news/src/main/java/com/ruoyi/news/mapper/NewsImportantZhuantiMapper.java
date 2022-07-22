package com.ruoyi.news.mapper;

import java.util.List;
import com.ruoyi.news.domain.NewsImportantZhuanti;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
public interface NewsImportantZhuantiMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public NewsImportantZhuanti selectNewsImportantZhuantiById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsImportantZhuanti 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<NewsImportantZhuanti> selectNewsImportantZhuantiList(NewsImportantZhuanti newsImportantZhuanti);

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsImportantZhuanti 【请填写功能名称】
     * @return 结果
     */
    public int insertNewsImportantZhuanti(NewsImportantZhuanti newsImportantZhuanti);

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsImportantZhuanti 【请填写功能名称】
     * @return 结果
     */
    public int updateNewsImportantZhuanti(NewsImportantZhuanti newsImportantZhuanti);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteNewsImportantZhuantiById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsImportantZhuantiByIds(String[] ids);
}
