package com.ruoyi.province.platform.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.province.platform.mapper.SysBillNoMapper;
import com.ruoyi.province.platform.domain.SysBillNo;
import com.ruoyi.province.platform.service.ISysBillNoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 单据号迭代信息Service业务层处理
 * 
 * @author dalin
 * @date 2020-12-19
 */
@Service
public class SysBillNoServiceImpl implements ISysBillNoService 
{
    @Autowired
    private SysBillNoMapper sysBillNoMapper;

    /**
     * 查询单据号迭代信息
     * 
     * @param id 单据号迭代信息ID
     * @return 单据号迭代信息
     */
    @Override
    public SysBillNo selectSysBillNoById(Long id)
    {
        return sysBillNoMapper.selectSysBillNoById(id);
    }

        /**
     * 查询单据号迭代信息列表
     * 
     * @param sysBillNo 单据号迭代信息
     * @return 单据号迭代信息
     */
    @Override
    public List<SysBillNo> selectSysBillNoList(SysBillNo sysBillNo)
    {
        return sysBillNoMapper.selectSysBillNoList(sysBillNo);
    }

    @Override
    public SysBillNo selectSysBillNoByPeriod(SysBillNo sysBillNo)
    {
        return sysBillNoMapper.selectSysBillNoByPeriod(sysBillNo);
    }

    @Override
    public int insertDuplicateByPeriod(SysBillNo sysBillNo)
    {
        return sysBillNoMapper.insertDuplicateByPeriod(sysBillNo);
    }
    /**
     * 新增单据号迭代信息
     * 
     * @param sysBillNo 单据号迭代信息
     * @return 结果
     */
    @Override
    public int insertSysBillNo(SysBillNo sysBillNo)
    {
        return sysBillNoMapper.insertSysBillNo(sysBillNo);
    }

    /**
     * 修改单据号迭代信息
     * 
     * @param sysBillNo 单据号迭代信息
     * @return 结果
     */
    @Override
    public int updateSysBillNo(SysBillNo sysBillNo)
    {
        return sysBillNoMapper.updateSysBillNo(sysBillNo);
    }

    /**
     * 删除单据号迭代信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSysBillNoByIds(String ids)
    {
        return sysBillNoMapper.deleteSysBillNoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除单据号迭代信息信息
     * 
     * @param id 单据号迭代信息ID
     * @return 结果
     */
    @Override
    public int deleteSysBillNoById(Long id)
    {
        return sysBillNoMapper.deleteSysBillNoById(id);
    }
}
