package com.ruoyi.news.mapper;

import java.util.List;
import com.ruoyi.news.domain.NewsImportantFalv;

/**
 * 【请填写功能名称】Mapper接口
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
public interface NewsImportantFalvMapper 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public NewsImportantFalv selectNewsImportantFalvById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsImportantFalv 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<NewsImportantFalv> selectNewsImportantFalvList(NewsImportantFalv newsImportantFalv);

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsImportantFalv 【请填写功能名称】
     * @return 结果
     */
    public int insertNewsImportantFalv(NewsImportantFalv newsImportantFalv);

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsImportantFalv 【请填写功能名称】
     * @return 结果
     */
    public int updateNewsImportantFalv(NewsImportantFalv newsImportantFalv);

    /**
     * 删除【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteNewsImportantFalvById(Long id);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteNewsImportantFalvByIds(String[] ids);
}
