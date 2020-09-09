package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.mapper.HisInpatientPrepaymentMapper;
import com.ruoyi.his.domain.HisInpatientPrepayment;
import com.ruoyi.his.service.IHisInpatientPrepaymentService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 住院预交Service业务层处理
 * 
 * @author bend
 * @date 2020-07-14
 */
@Service
public class HisInpatientPrepaymentServiceImpl implements IHisInpatientPrepaymentService
{
    @Resource
    private HisInpatientPrepaymentMapper hisInpatientPrepaymentMapper;

    /**
     * 查询住院预交
     * 
     * @param id 住院预交ID
     * @return 住院预交
     */
    @Override
    public HisInpatientPrepayment selectHisInpatientPrepaymentById(Long id)
    {
        return hisInpatientPrepaymentMapper.selectHisInpatientPrepaymentById(id);
    }

    /**
     * 查询住院预交
     *
     * @param hisInpatientPrepayment 住院预交ID
     * @return 住院预交
     */
    @Override
    public HisInpatientPrepayment selectHisInpatientPrepayment(HisInpatientPrepayment hisInpatientPrepayment)
    {
        return hisInpatientPrepaymentMapper.selectOne(hisInpatientPrepayment);
    }

    /**
     * 查询住院预交列表
     * 
     * @param hisInpatientPrepayment 住院预交
     * @return 住院预交
     */
    @Override
    public List<HisInpatientPrepayment> selectHisInpatientPrepaymentList(HisInpatientPrepayment hisInpatientPrepayment)
    {
        return hisInpatientPrepaymentMapper.selectHisInpatientPrepaymentList(hisInpatientPrepayment);
    }

    /**
     * 新增住院预交
     * 
     * @param hisInpatientPrepayment 住院预交
     * @return 结果
     */
    @Override
    public int insertHisInpatientPrepayment(HisInpatientPrepayment hisInpatientPrepayment)
    {
        return hisInpatientPrepaymentMapper.insertSelective(hisInpatientPrepayment);
    }

    /**
     * 批量新增住院预交
     *
     * @param hisInpatientPrepaymentList 住院预交列表
     * @return 结果
     */
    @Override
    public int insertHisInpatientPrepaymentBatch(List<HisInpatientPrepayment> hisInpatientPrepaymentList)
    {
        return hisInpatientPrepaymentMapper.insertList(hisInpatientPrepaymentList);
    }

    /**
     * 修改住院预交
     * 
     * @param hisInpatientPrepayment 住院预交
     * @return 结果
     */
    @Override
    public int updateHisInpatientPrepayment(HisInpatientPrepayment hisInpatientPrepayment)
    {
        return hisInpatientPrepaymentMapper.updateByPrimaryKeySelective(hisInpatientPrepayment);
    }

    /**
     * 删除住院预交对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisInpatientPrepaymentByIds(String ids)
    {
        return hisInpatientPrepaymentMapper.deleteByIds(ids);
    }

    /**
     * 删除住院预交信息
     * 
     * @param id 住院预交ID
     * @return 结果
     */
    @Override
    public int deleteHisInpatientPrepaymentById(Long id)
    {
        return hisInpatientPrepaymentMapper.deleteByPrimaryKey(id);
    }
}
