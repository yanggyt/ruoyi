package com.ruoyi.his.service;

import com.ruoyi.his.domain.HisRegistrationRecord;

import java.util.List;

/**
 * 挂号记录Service接口
 *
 * @author bend
 * @date 2020-06-28
 */
public interface IHisRegistrationRecordService
{
    /**
     * 查询挂号记录
     *
     * @param id 挂号记录ID
     * @return 挂号记录
     */
    public HisRegistrationRecord selectHisRegistrationRecordById(Long id);

    /**
     * 查询挂号记录
     * @param hisRegistrationRecord 挂号记录
     * @return 查询挂号记录
     */
    public HisRegistrationRecord selectHisRegistrationRecord(HisRegistrationRecord hisRegistrationRecord);

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
     * 批量新增挂号记录
     * @param hisRegistrationRecordList 挂号记录列表
     * @return 结果
     */
    public int insertHisRegistrationRecordBatch(List<HisRegistrationRecord> hisRegistrationRecordList);

    /**
     * 修改挂号记录
     *
     * @param hisRegistrationRecord 挂号记录
     * @return 结果
     */
    public int updateHisRegistrationRecord(HisRegistrationRecord hisRegistrationRecord);

    /**
     * 批量修改挂号记录
     *
     * @param hisRegistrationRecordList 挂号记录
     * @return 结果
     */
    public void updateHisRegistrationRecordBatch(List<HisRegistrationRecord> hisRegistrationRecordList);

    /**
     * 批量删除挂号记录
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHisRegistrationRecordByIds(String ids);

    /**
     * 删除挂号记录信息
     *
     * @param id 挂号记录ID
     * @return 结果
     */
    public int deleteHisRegistrationRecordById(Long id);
}
