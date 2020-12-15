package com.ruoyi.system.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.system.mapper.SysBillNoMapper;
import com.ruoyi.system.domain.SysBillNo;
import com.ruoyi.system.service.ISysBillNoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 单据号迭代信息Service业务层处理
 * 
 * @author dalin
 * @date 2020-12-15
 */
@Service
public class SysBillNoServiceImpl implements ISysBillNoService 
{
    @Autowired
    private SysBillNoMapper sysBillNoMapper;

    /**
     * 查询单据号迭代信息
     * 
     * @param fperiod 单据号迭代信息ID
     * @return 单据号迭代信息
     */
    @Override
    public SysBillNo selectSysBillNoById(String fperiod)
    {
        return sysBillNoMapper.selectSysBillNoById(fperiod);
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
     * @param fperiod 单据号迭代信息ID
     * @return 结果
     */
    @Override
    public int deleteSysBillNoById(String fperiod)
    {
        return sysBillNoMapper.deleteSysBillNoById(fperiod);
    }


    @Override
    public SysBillNo selectSysBillNoByBillPrefix(String billPrefix) {
        return sysBillNoMapper.selectSysBillNoByBillPrefix(billPrefix);
    }

    @Override
    @Transactional
    public String selectNextBillNoById(String billName) {

        SysBillNo sysBillNo = sysBillNoMapper.selectSysBillNoById(billName);
        String value = sysBillNo.getNextValue();
        //	获取到值后累加
        long nextValue = Long.valueOf(sysBillNo.getIterationValue())+1;
        sysBillNo.setIterationValue(String.valueOf(nextValue));
        sysBillNo.setNextValue(sysBillNo.getIterationValue());
        sysBillNoMapper.updateSysBillNo(sysBillNo);
        return value;
    }

}
