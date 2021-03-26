package com.sinosoft.activity.mapper;

import java.util.List;
import com.sinosoft.activity.domain.DrawAwardRecord;

/**
 * 记录发奖信息Mapper接口
 * 
 * @author dy
 * @date 2021-03-26
 */
public interface DrawAwardRecordMapper 
{
    /**
     * 查询记录发奖信息
     * 
     * @param AWARDRECORDID 记录发奖信息ID
     * @return 记录发奖信息
     */
    public DrawAwardRecord selectDrawAwardRecordById(String AWARDRECORDID);

    /**
     * 查询记录发奖信息列表
     * 
     * @param drawAwardRecord 记录发奖信息
     * @return 记录发奖信息集合
     */
    public List<DrawAwardRecord> selectDrawAwardRecordList(DrawAwardRecord drawAwardRecord);

    /**
     * 新增记录发奖信息
     * 
     * @param drawAwardRecord 记录发奖信息
     * @return 结果
     */
    public int insertDrawAwardRecord(DrawAwardRecord drawAwardRecord);

    /**
     * 修改记录发奖信息
     * 
     * @param drawAwardRecord 记录发奖信息
     * @return 结果
     */
    public int updateDrawAwardRecord(DrawAwardRecord drawAwardRecord);

    /**
     * 删除记录发奖信息
     * 
     * @param AWARDRECORDID 记录发奖信息ID
     * @return 结果
     */
    public int deleteDrawAwardRecordById(String AWARDRECORDID);

    /**
     * 批量删除记录发奖信息
     * 
     * @param AWARDRECORDIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawAwardRecordByIds(String[] AWARDRECORDIDs);
}
