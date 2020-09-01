package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.mapper.HisInspectionApplyMapper;
import com.ruoyi.his.domain.HisInspectionApply;
import com.ruoyi.his.service.IHisInspectionApplyService;

import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 申请单Service业务层处理
 * 
 * @author bend
 * @date 2020-07-10
 */
@Service
public class HisInspectionApplyServiceImpl implements IHisInspectionApplyService
{
    @Resource
    private HisInspectionApplyMapper hisInspectionApplyMapper;

    /**
     * 查询申请单
     * 
     * @param id 申请单ID
     * @return 申请单
     */
    @Override
    public HisInspectionApply selectHisInspectionApplyById(Long id)
    {
        return hisInspectionApplyMapper.selectHisInspectionApplyById(id);
    }

    /**
     * 查询申请单
     *
     * @param hisInspectionApply 申请单ID
     * @return 申请单
     */
    @Override
    public HisInspectionApply selectHisInspectionApply(HisInspectionApply hisInspectionApply)
    {
        return hisInspectionApplyMapper.selectOne(hisInspectionApply);
    }

    /**
     * 查询申请单列表
     * 
     * @param hisInspectionApply 申请单
     * @return 申请单
     */
    @Override
    public List<HisInspectionApply> selectHisInspectionApplyList(HisInspectionApply hisInspectionApply)
    {
        return hisInspectionApplyMapper.selectHisInspectionApplyList(hisInspectionApply);
    }

    /**
     * 新增申请单
     * 
     * @param hisInspectionApply 申请单
     * @return 结果
     */
    @Override
    public int insertHisInspectionApply(HisInspectionApply hisInspectionApply)
    {
        return hisInspectionApplyMapper.insertSelective(hisInspectionApply);
    }

    /**
     * 批量新增申请单
     *
     * @param hisInspectionApplyList 申请单列表
     * @return 结果
     */
    @Override
    public int insertHisInspectionApplyBatch(List<HisInspectionApply> hisInspectionApplyList)
    {
        return hisInspectionApplyMapper.insertList(hisInspectionApplyList);
    }

    /**
     * 修改申请单
     * 
     * @param hisInspectionApply 申请单
     * @return 结果
     */
    @Override
    public int updateHisInspectionApply(HisInspectionApply hisInspectionApply)
    {
        return hisInspectionApplyMapper.updateByPrimaryKeySelective(hisInspectionApply);
    }

    /**
     * 删除申请单对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisInspectionApplyByIds(String ids)
    {
        return hisInspectionApplyMapper.deleteByIds(ids);
    }

    /**
     * 删除申请单信息
     * 
     * @param id 申请单ID
     * @return 结果
     */
    @Override
    public int deleteHisInspectionApplyById(Long id)
    {
        return hisInspectionApplyMapper.deleteByPrimaryKey(id);
    }
}
