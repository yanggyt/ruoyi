package com.ruoyi.news.service;

import java.util.List;
import com.ruoyi.news.domain.NewsDutyTable;

/**
 * 【请填写功能名称】Service接口
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
public interface INewsDutyTableService 
{
    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    public NewsDutyTable selectNewsDutyTableById(Long id);

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsDutyTable 【请填写功能名称】
     * @return 【请填写功能名称】集合
     */
    public List<NewsDutyTable> selectNewsDutyTableList(NewsDutyTable newsDutyTable);

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsDutyTable 【请填写功能名称】
     * @return 结果
     */
    public int insertNewsDutyTable(NewsDutyTable newsDutyTable);

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsDutyTable 【请填写功能名称】
     * @return 结果
     */
    public int updateNewsDutyTable(NewsDutyTable newsDutyTable);

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键集合
     * @return 结果
     */
    public int deleteNewsDutyTableByIds(String ids);

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    public int deleteNewsDutyTableById(Long id);
}
