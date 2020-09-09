package com.ruoyi.his.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.func.Func;
import com.ruoyi.his.domain.HisRegistrationRecord;
import com.ruoyi.his.mapper.HisRegistrationRecordMapper;
import com.ruoyi.his.service.IHisRegistrationRecordService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 挂号记录Service业务层处理
 * 
 * @author bend
 * @date 2020-06-28
 */
@Service
public class HisRegistrationRecordServiceImpl implements IHisRegistrationRecordService 
{
    @Resource
    private HisRegistrationRecordMapper hisRegistrationRecordMapper;

    /**
     * 查询挂号记录
     * 
     * @param id 挂号记录ID
     * @return 挂号记录
     */
    @Override
    public HisRegistrationRecord selectHisRegistrationRecordById(Long id)
    {
        return hisRegistrationRecordMapper.selectHisRegistrationRecordById(id);
    }
    /**
     * 查询挂号记录
     * @param hisRegistrationRecord 挂号记录
     * @return 查询挂号记录
     */
    @Override
    public HisRegistrationRecord selectHisRegistrationRecord(HisRegistrationRecord hisRegistrationRecord)
    {
        return hisRegistrationRecordMapper.selectHisRegistrationRecord(hisRegistrationRecord);
    }


    /**
     * 查询挂号记录列表
     * 
     * @param hisRegistrationRecord 挂号记录
     * @return 挂号记录
     */
    @Override
    public List<HisRegistrationRecord> selectHisRegistrationRecordList(HisRegistrationRecord hisRegistrationRecord)
    {
        return hisRegistrationRecordMapper.selectHisRegistrationRecordList(hisRegistrationRecord);
    }

    /**
     * 新增挂号记录
     * 
     * @param hisRegistrationRecord 挂号记录
     * @return 结果
     */
    @Override
    public int insertHisRegistrationRecord(HisRegistrationRecord hisRegistrationRecord)
    {
        return hisRegistrationRecordMapper.insertHisRegistrationRecord(hisRegistrationRecord);
    }

    @Override
    public int insertHisRegistrationRecordBatch(List<HisRegistrationRecord> hisRegistrationRecordList)
    {
        return hisRegistrationRecordMapper.insertList(hisRegistrationRecordList);
    }

    @Override
    public void updateHisRegistrationRecordBatch(List<HisRegistrationRecord> hisRegistrationRecordList)
    {
        if (Func.isNotEmpty(hisRegistrationRecordList)){
            hisRegistrationRecordList.forEach(hisRegistrationRecord -> {
                hisRegistrationRecordMapper.updateByPrimaryKeySelective(hisRegistrationRecord);
            });
        }
    }

    /**
     * 修改挂号记录
     * 
     * @param hisRegistrationRecord 挂号记录
     * @return 结果
     */
    @Override
    public int updateHisRegistrationRecord(HisRegistrationRecord hisRegistrationRecord)
    {
        return hisRegistrationRecordMapper.updateHisRegistrationRecord(hisRegistrationRecord);
    }

    /**
     * 删除挂号记录对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHisRegistrationRecordByIds(String ids)
    {
        return hisRegistrationRecordMapper.deleteHisRegistrationRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除挂号记录信息
     * 
     * @param id 挂号记录ID
     * @return 结果
     */
    @Override
    public int deleteHisRegistrationRecordById(Long id)
    {
        return hisRegistrationRecordMapper.deleteHisRegistrationRecordById(id);
    }
}
