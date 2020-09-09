package com.ruoyi.his.service.impl;

import com.ruoyi.common.func.Func;
import com.ruoyi.his.domain.HisOutpatient;
import com.ruoyi.his.mapper.HisOutpatientMapper;
import com.ruoyi.his.service.IHisOutpatientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 门诊病人Service业务层处理
 * 
 * @author bend
 * @date 2020-07-08
 */
@Service
public class HisOutpatientServiceImpl implements IHisOutpatientService
{
    @Resource
    private HisOutpatientMapper hisOutpatientMapper;

    /**
     * 查询门诊病人
     * 
     * @param id 门诊病人ID
     * @return 门诊病人
     */
    @Override
    public HisOutpatient selectHisOutpatientById(Long id)
    {
        return hisOutpatientMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询门诊病人
     *
     * @param hisOutpatient 门诊病人
     * @return 门诊病人
     */
    @Override
    public HisOutpatient selectHisOutpatient(HisOutpatient hisOutpatient)
    {
        return hisOutpatientMapper.selectOne(hisOutpatient);
    }

    /**
     * 查询门诊病人列表
     * 
     * @param hisOutpatient 门诊病人
     * @return 门诊病人
     */
    @Override
    public List<HisOutpatient> selectHisOutpatientList(HisOutpatient hisOutpatient)
    {
        return hisOutpatientMapper.selectHisOutpatientList(hisOutpatient);
    }

    /**
     * 新增门诊病人
     * 
     * @param hisOutpatient 门诊病人
     * @return 结果
     */
    @Override
    public int insertHisOutpatient(HisOutpatient hisOutpatient)
    {
        return hisOutpatientMapper.insertSelective(hisOutpatient);
    }

    /**
     * 批量新增门诊病人
     *
     * @param hisOutpatientList 门诊病人列表
     * @return 结果
     */
    @Override
    public int insertHisOutpatientBatch(List<HisOutpatient> hisOutpatientList) {
        return hisOutpatientMapper.insertList(hisOutpatientList);
    }

    /**
     * 修改门诊病人
     * 
     * @param hisOutpatient 门诊病人
     * @return 结果
     */
    @Override
    public int updateHisOutpatient(HisOutpatient hisOutpatient)
    {
        return hisOutpatientMapper.updateByPrimaryKeySelective(hisOutpatient);
    }

    /**
     * 删除门诊病人对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisOutpatientByIds(String ids)
    {
        return hisOutpatientMapper.deleteByIds(ids);
    }

    /**
     * 删除门诊病人信息
     * 
     * @param id 门诊病人ID
     * @return 结果
     */
    @Override
    public int deleteHisOutpatientById(Long id)
    {
        return hisOutpatientMapper.deleteByPrimaryKey(id);
    }

    /**
     *
     * @param hisOutpatientList 病人列表
     */
    @Override
    public void updateHisOutpatientBatch(List<HisOutpatient> hisOutpatientList)
    {
        if (Func.isNotEmpty(hisOutpatientList)){
            hisOutpatientList.forEach(hisOutpatient -> {
                hisOutpatientMapper.updateByPrimaryKeySelective(hisOutpatient);
            });
        }
    }
}
