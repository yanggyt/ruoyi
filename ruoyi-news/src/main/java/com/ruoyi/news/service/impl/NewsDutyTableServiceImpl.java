package com.ruoyi.news.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.news.mapper.NewsDutyTableMapper;
import com.ruoyi.news.domain.NewsDutyTable;
import com.ruoyi.news.service.INewsDutyTableService;
import com.ruoyi.common.core.text.Convert;

/**
 * 【请填写功能名称】Service业务层处理
 * 
 * @author admin@abdu.net.cn
 * @date 2022-07-19
 */
@Service
public class NewsDutyTableServiceImpl implements INewsDutyTableService 
{
    @Autowired
    private NewsDutyTableMapper newsDutyTableMapper;

    /**
     * 查询【请填写功能名称】
     * 
     * @param id 【请填写功能名称】主键
     * @return 【请填写功能名称】
     */
    @Override
    public NewsDutyTable selectNewsDutyTableById(Long id)
    {
        return newsDutyTableMapper.selectNewsDutyTableById(id);
    }

    /**
     * 查询【请填写功能名称】列表
     * 
     * @param newsDutyTable 【请填写功能名称】
     * @return 【请填写功能名称】
     */
    @Override
    public List<NewsDutyTable> selectNewsDutyTableList(NewsDutyTable newsDutyTable)
    {
        return newsDutyTableMapper.selectNewsDutyTableList(newsDutyTable);
    }

    /**
     * 新增【请填写功能名称】
     * 
     * @param newsDutyTable 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int insertNewsDutyTable(NewsDutyTable newsDutyTable)
    {
        return newsDutyTableMapper.insertNewsDutyTable(newsDutyTable);
    }

    /**
     * 修改【请填写功能名称】
     * 
     * @param newsDutyTable 【请填写功能名称】
     * @return 结果
     */
    @Override
    public int updateNewsDutyTable(NewsDutyTable newsDutyTable)
    {
        return newsDutyTableMapper.updateNewsDutyTable(newsDutyTable);
    }

    /**
     * 批量删除【请填写功能名称】
     * 
     * @param ids 需要删除的【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsDutyTableByIds(String ids)
    {
        return newsDutyTableMapper.deleteNewsDutyTableByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除【请填写功能名称】信息
     * 
     * @param id 【请填写功能名称】主键
     * @return 结果
     */
    @Override
    public int deleteNewsDutyTableById(Long id)
    {
        return newsDutyTableMapper.deleteNewsDutyTableById(id);
    }
}
