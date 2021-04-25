package com.sinosoft.activity.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.DateUtils;
import com.sinosoft.activity.domain.DrawAwardRecord;
import com.sinosoft.activity.domain.DrawRecord;
import com.sinosoft.activity.domain.DrawUserInfo;
import com.sinosoft.activity.mapper.DrawAwardRecordMapper;
import com.sinosoft.activity.mapper.DrawRecordMapper;
import com.sinosoft.activity.mapper.DrawUserInfoMapper;
import com.sinosoft.activity.service.IDrawUserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 用户信息收集页面Service业务层处理
 *
 * @author ruoyi
 * @date 2021-04-20
 */
@Service
public class DrawUserInfoServiceImpl implements IDrawUserInfoService
{
    @Autowired
    private DrawUserInfoMapper drawUserInfoMapper;

    @Autowired
    private DrawRecordMapper  drawRecordMapper;
    @Autowired
    private DrawAwardRecordMapper  drawAwardRecordMapper;
    /**
     * 查询用户信息收集页面
     *
     * @param awarDrecordId 用户信息收集页面ID
     * @return 用户信息收集页面
     */
    @Override
    public DrawUserInfo selectDrawUserInfoById(String awarDrecordId)
    {
        return drawUserInfoMapper.selectDrawUserInfoById(awarDrecordId);
    }

    /**
     * 查询用户信息收集页面列表
     *
     * @param drawUserInfo 用户信息收集页面
     * @return 用户信息收集页面
     */
    @Override
    public List<DrawUserInfo> selectDrawUserInfoList(DrawUserInfo drawUserInfo)
    {
        return drawUserInfoMapper.selectDrawUserInfoList(drawUserInfo);
    }

    /**
     * 新增用户信息收集页面
     *
     * @param drawUserInfo 用户信息收集页面
     * @return 结果
     */
    @Override
    public int insertDrawUserInfo(DrawUserInfo drawUserInfo)
    {
        drawUserInfo.setCreateTime(DateUtils.getNowDate());
        DrawRecord drawRecord = new DrawRecord();

       /* drawRecord.setDRAWCODE("20200805223654039");
        drawRecord.setUSERID("7709828507406032");
        drawRecord.setDRAWTRANSEQNO("202010151647450600802021");*/
        drawRecord.setDRAWCODE(drawUserInfo.getDrawCode());
        drawRecord.setUSERID(drawUserInfo.getUserId());
        drawRecord.setDRAWTRANSEQNO(drawUserInfo.getFlow());
        DrawRecord gtDrawRecord = drawRecordMapper.selectDrawRecordUserIdList(drawRecord);

            DrawAwardRecord drawAwardRecord = new DrawAwardRecord();
            drawAwardRecord.setAWARDRESULT("1");
            drawAwardRecord.setAWARDTIME(DateUtils.getNowDate());
            drawAwardRecord.setDRAWCODE(gtDrawRecord.getDRAWCODE());
            drawAwardRecord.setDRAWRECORDID(gtDrawRecord.getDRAWRECORDID());
            drawAwardRecord.setDRAWTRANSEQNO(gtDrawRecord.getDRAWTRANSEQNO());
            drawAwardRecord.setCREATETIMESTAMP(DateUtils.getNowDate());
            drawAwardRecord.setLASTUPDATETIMESTAMP(DateUtils.getNowDate());
            drawAwardRecord.setPRIZECODE(gtDrawRecord.getPRIZECODE());
            drawAwardRecord.setRETURNMESSAGE("0000");
            drawAwardRecord.setRETURNMESSAGE("成功");
            drawAwardRecord.setUSERID("userId");
            drawAwardRecord.setUSERNAME(gtDrawRecord.getUSERNAME());
            drawAwardRecord.setPRIZELEVEL(gtDrawRecord.getPRIZELEVEL());
            drawAwardRecord.setPRIZETYPE(gtDrawRecord.getPRIZETYPE());
            drawAwardRecordMapper.insertDrawAwardRecord(drawAwardRecord);
            gtDrawRecord.setDRAWRESULT("2");
            drawRecordMapper.updateDrawRecord(gtDrawRecord);
            drawUserInfo.setAwarDrecordId(drawAwardRecord.getAWARDRECORDID());
        return drawUserInfoMapper.insertDrawUserInfo(drawUserInfo);
    }

    /**
     * 修改用户信息收集页面
     *
     * @param drawUserInfo 用户信息收集页面
     * @return 结果
     */
    @Override
    public int updateDrawUserInfo(DrawUserInfo drawUserInfo)
    {
        drawUserInfo.setUpdateTime(DateUtils.getNowDate());
        return drawUserInfoMapper.updateDrawUserInfo(drawUserInfo);
    }

    /**
     * 删除用户信息收集页面对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDrawUserInfoByIds(String ids)
    {
        return drawUserInfoMapper.deleteDrawUserInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除用户信息收集页面信息
     *
     * @param awarDrecordId 用户信息收集页面ID
     * @return 结果
     */
    @Override
    public int deleteDrawUserInfoById(Long awarDrecordId)
    {
        return drawUserInfoMapper.deleteDrawUserInfoById(awarDrecordId);
    }
}