package com.sinosoft.activity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinosoft.activity.mapper.DrawRecordMapper;
import com.sinosoft.activity.domain.DrawRecord;
import com.sinosoft.activity.service.IDrawRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 抽奖记录信息Service业务层处理
 * 
 * @author ruoyi
 * @date 2021-03-25
 */
@Service
public class DrawRecordServiceImpl implements IDrawRecordService 
{
    @Autowired
    private DrawRecordMapper drawRecordMapper;

    /**
     * 查询抽奖记录信息
     * 
     * @param DRAWRECORDID 抽奖记录信息ID
     * @return 抽奖记录信息
     */
    @Override
    public DrawRecord selectDrawRecordById(String DRAWRECORDID)
    {
        return drawRecordMapper.selectDrawRecordById(DRAWRECORDID);
    }

    /**
     * 查询抽奖记录信息列表
     * 
     * @param drawRecord 抽奖记录信息
     * @return 抽奖记录信息
     */
    @Override
    public List<DrawRecord> selectDrawRecordList(DrawRecord drawRecord)
    {
        return drawRecordMapper.selectDrawRecordList(drawRecord);
    }

    /**
     * 新增抽奖记录信息
     * 
     * @param drawRecord 抽奖记录信息
     * @return 结果
     */
    @Override
    public int insertDrawRecord(DrawRecord drawRecord)
    {
        return drawRecordMapper.insertDrawRecord(drawRecord);
    }

    /**
     * 修改抽奖记录信息
     * 
     * @param drawRecord 抽奖记录信息
     * @return 结果
     */
    @Override
    public int updateDrawRecord(DrawRecord drawRecord)
    {
        return drawRecordMapper.updateDrawRecord(drawRecord);
    }

    /**
     * 删除抽奖记录信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDrawRecordByIds(String ids)
    {
        return drawRecordMapper.deleteDrawRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除抽奖记录信息信息
     * 
     * @param DRAWRECORDID 抽奖记录信息ID
     * @return 结果
     */
    @Override
    public int deleteDrawRecordById(String DRAWRECORDID)
    {
        return drawRecordMapper.deleteDrawRecordById(DRAWRECORDID);
    }
}
