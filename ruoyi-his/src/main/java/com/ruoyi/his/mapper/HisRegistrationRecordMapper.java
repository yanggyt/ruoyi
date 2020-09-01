package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisRegistrationRecord;

import java.util.List;

/**
 * 挂号记录Mapper接口
 * 
 * @author bend
 * @date 2020-06-28
 */
public interface HisRegistrationRecordMapper extends RuoYiBaseMapper<HisRegistrationRecord>
{
    /**
     * 查询挂号记录
     * 
     * @param id 挂号记录ID
     * @return 挂号记录
     */
    public HisRegistrationRecord selectHisRegistrationRecordById(Long id);

    /**
     * 查询挂号记录列表
     * 
     * @param hisRegistrationRecord 挂号记录
     * @return 挂号记录集合
     */
    public List<HisRegistrationRecord> selectHisRegistrationRecordList(HisRegistrationRecord hisRegistrationRecord);

    /**
     * 新增挂号记录
     * 
     * @param hisRegistrationRecord 挂号记录
     * @return 结果
     */
    public int insertHisRegistrationRecord(HisRegistrationRecord hisRegistrationRecord);

    /**
     * 修改挂号记录
     * 
     * @param hisRegistrationRecord 挂号记录
     * @return 结果
     */
    public int updateHisRegistrationRecord(HisRegistrationRecord hisRegistrationRecord);

    /**
     * 删除挂号记录
     * 
     * @param id 挂号记录ID
     * @return 结果
     */
    public int deleteHisRegistrationRecordById(Long id);

    /**
     * 批量删除挂号记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisRegistrationRecordByIds(String[] ids);

    /**
     * 查询挂号记录
     * @param hisRegistrationRecord 挂号记录
     * @return 查询挂号记录
     */
    public HisRegistrationRecord selectHisRegistrationRecord(HisRegistrationRecord hisRegistrationRecord);

}
