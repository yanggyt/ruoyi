package com.ruoyi.his.service.impl;

import com.ruoyi.common.func.Func;
import com.ruoyi.his.domain.HisInpatient;
import com.ruoyi.his.mapper.HisInpatientMapper;
import com.ruoyi.his.service.IHisInpatientService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * 住院病人Service业务层处理
 * 
 * @author bend
 * @date 2020-07-08
 */
@Service
public class HisInpatientServiceImpl implements IHisInpatientService
{

    @Resource
    private HisInpatientMapper hisInpatientMapper;


    /**
     * 查询住院病人
     * 
     * @param id 住院病人ID
     * @return 住院病人
     */
    @Override
    public HisInpatient selectHisInpatientById(Long id)
    {
        return hisInpatientMapper.selectByPrimaryKey(id);
    }

    /**
     * 查询住院病人
     *
     * @param hisInpatient 住院病人
     * @return 住院病人
     */
    @Override
    public HisInpatient selectHisInpatient(HisInpatient hisInpatient) {
        return hisInpatientMapper.selectOne(hisInpatient);
    }

    /**
     * 查询住院病人列表
     * 
     * @param hisInpatient 住院病人
     * @return 住院病人
     */
    @Override
    public List<HisInpatient> selectHisInpatientList(HisInpatient hisInpatient)
    {
        return hisInpatientMapper.selectHisInpatientList(hisInpatient);
    }

    /**
     * 新增住院病人
     * 
     * @param hisInpatient 住院病人
     * @return 结果
     */
    @Override
    public int insertHisInpatient(HisInpatient hisInpatient)
    {
        return hisInpatientMapper.insertSelective(hisInpatient);
    }

    /**
     * 批量新增住院病人
     *
     * @param hisInpatientList 住院病人
     * @return 结果
     */
    @Override
    public int insertHisInpatientBatch(List<HisInpatient> hisInpatientList) {
        return hisInpatientMapper.insertList(hisInpatientList);
    }

    /**
     * 修改住院病人
     * 
     * @param hisInpatient 住院病人
     * @return 结果
     */
    @Override
    public int updateHisInpatient(HisInpatient hisInpatient)
    {
        return hisInpatientMapper.updateByPrimaryKeySelective(hisInpatient);
    }

    /**
     * 删除住院病人对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisInpatientByIds(String ids)
    {
        return hisInpatientMapper.deleteByIds(ids);
    }

    /**
     * 删除住院病人信息
     * 
     * @param id 住院病人ID
     * @return 结果
     */
    @Override
    public int deleteHisInpatientById(Long id)
    {
        return hisInpatientMapper.deleteByPrimaryKey(id);
    }

    /**
     *
     * @param hisInpatientList 住院病人列表
     */
    @Override
    public void updateHisInpatientBatch(List<HisInpatient> hisInpatientList)
    {
        if (Func.isNotEmpty(hisInpatientList)) {
            hisInpatientList.forEach(hisInpatient -> {
                hisInpatientMapper.updateByPrimaryKeySelective(hisInpatient);
            });
        }
    }
}
