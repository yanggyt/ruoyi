package com.sinosoft.activity.service;

import java.util.List;
import com.sinosoft.activity.domain.DrawRecord;

/**
 * 抽奖记录信息Service接口
 * 
 * @author ruoyi
 * @date 2021-03-25
 */
public interface IDrawRecordService 
{
    /**
     * 查询抽奖记录信息
     * 
     * @param DRAWRECORDID 抽奖记录信息ID
     * @return 抽奖记录信息
     */
    public DrawRecord selectDrawRecordById(String DRAWRECORDID);

    /**
     * 查询抽奖记录信息列表
     * 
     * @param drawRecord 抽奖记录信息
     * @return 抽奖记录信息集合
     */
    public List<DrawRecord> selectDrawRecordList(DrawRecord drawRecord);

    /**
     * 新增抽奖记录信息
     * 
     * @param drawRecord 抽奖记录信息
     * @return 结果
     */
    public int insertDrawRecord(DrawRecord drawRecord);

    /**
     * 修改抽奖记录信息
     * 
     * @param drawRecord 抽奖记录信息
     * @return 结果
     */
    public int updateDrawRecord(DrawRecord drawRecord);

    /**
     * 批量删除抽奖记录信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawRecordByIds(String ids);

    /**
     * 删除抽奖记录信息信息
     * 
     * @param DRAWRECORDID 抽奖记录信息ID
     * @return 结果
     */
    public int deleteDrawRecordById(String DRAWRECORDID);
}
