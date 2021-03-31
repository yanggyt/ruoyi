package com.sinosoft.activity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinosoft.activity.mapper.DrawAwardRecordMapper;
import com.sinosoft.activity.domain.DrawAwardRecord;
import com.sinosoft.activity.service.IDrawAwardRecordService;
import com.ruoyi.common.core.text.Convert;

/**
 * 记录发奖信息Service业务层处理
 * 
 * @author dy
 * @date 2021-03-26
 */
@Service
public class DrawAwardRecordServiceImpl implements IDrawAwardRecordService 
{
    @Autowired
    private DrawAwardRecordMapper drawAwardRecordMapper;

    /**
     * 查询记录发奖信息
     * 
     * @param AWARDRECORDID 记录发奖信息ID
     * @return 记录发奖信息
     */
    @Override
    public DrawAwardRecord selectDrawAwardRecordById(String AWARDRECORDID)
    {
        return drawAwardRecordMapper.selectDrawAwardRecordById(AWARDRECORDID);
    }

    /**
     * 查询记录发奖信息列表
     * 
     * @param drawAwardRecord 记录发奖信息
     * @return 记录发奖信息
     */
    @Override
    public List<DrawAwardRecord> selectDrawAwardRecordList(DrawAwardRecord drawAwardRecord)
    {
        return drawAwardRecordMapper.selectDrawAwardRecordList(drawAwardRecord);
    }

    /**
     * 新增记录发奖信息
     * 
     * @param drawAwardRecord 记录发奖信息
     * @return 结果
     */
    @Override
    public int insertDrawAwardRecord(DrawAwardRecord drawAwardRecord)
    {
        return drawAwardRecordMapper.insertDrawAwardRecord(drawAwardRecord);
    }

    /**
     * 修改记录发奖信息
     * 
     * @param drawAwardRecord 记录发奖信息
     * @return 结果
     */
    @Override
    public int updateDrawAwardRecord(DrawAwardRecord drawAwardRecord)
    {
        return drawAwardRecordMapper.updateDrawAwardRecord(drawAwardRecord);
    }

    /**
     * 删除记录发奖信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDrawAwardRecordByIds(String ids)
    {
        return drawAwardRecordMapper.deleteDrawAwardRecordByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除记录发奖信息信息
     * 
     * @param AWARDRECORDID 记录发奖信息ID
     * @return 结果
     */
    @Override
    public int deleteDrawAwardRecordById(String AWARDRECORDID)
    {
        return drawAwardRecordMapper.deleteDrawAwardRecordById(AWARDRECORDID);
    }

    /**
     * 获取已发放奖品数量
     * @param DRAWCODE
     * @param prizecode
     */
    @Override
    public DrawAwardRecord selectDrawAwardRecordCount(String DRAWCODE, String prizecode) {
        DrawAwardRecord drawAwardRecord = new DrawAwardRecord();
        drawAwardRecord.setDRAWCODE(DRAWCODE);
        drawAwardRecord.setPRIZECODE(prizecode);
        return   drawAwardRecordMapper.selectDrawAwardRecordCount(drawAwardRecord);
    }
}
