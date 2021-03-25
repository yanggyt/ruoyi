package com.sinosoft.activity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinosoft.activity.mapper.DrawPrizeInfoMapper;
import com.sinosoft.activity.domain.DrawPrizeInfo;
import com.sinosoft.activity.service.IDrawPrizeInfoService;
import com.ruoyi.common.core.text.Convert;

/**
 * 存储奖品的基础信息Service业务层处理
 * 
 * @author dy
 * @date 2021-03-25
 */
@Service
public class DrawPrizeInfoServiceImpl implements IDrawPrizeInfoService 
{
    @Autowired
    private DrawPrizeInfoMapper drawPrizeInfoMapper;

    /**
     * 查询存储奖品的基础信息
     * 
     * @param PRIZEID 存储奖品的基础信息ID
     * @return 存储奖品的基础信息
     */
    @Override
    public DrawPrizeInfo selectDrawPrizeInfoById(String PRIZEID)
    {
        return drawPrizeInfoMapper.selectDrawPrizeInfoById(PRIZEID);
    }

    /**
     * 查询存储奖品的基础信息列表
     * 
     * @param drawPrizeInfo 存储奖品的基础信息
     * @return 存储奖品的基础信息
     */
    @Override
    public List<DrawPrizeInfo> selectDrawPrizeInfoList(DrawPrizeInfo drawPrizeInfo)
    {
        return drawPrizeInfoMapper.selectDrawPrizeInfoList(drawPrizeInfo);
    }

    /**
     * 新增存储奖品的基础信息
     * 
     * @param drawPrizeInfo 存储奖品的基础信息
     * @return 结果
     */
    @Override
    public int insertDrawPrizeInfo(DrawPrizeInfo drawPrizeInfo)
    {
        return drawPrizeInfoMapper.insertDrawPrizeInfo(drawPrizeInfo);
    }

    /**
     * 修改存储奖品的基础信息
     * 
     * @param drawPrizeInfo 存储奖品的基础信息
     * @return 结果
     */
    @Override
    public int updateDrawPrizeInfo(DrawPrizeInfo drawPrizeInfo)
    {
        return drawPrizeInfoMapper.updateDrawPrizeInfo(drawPrizeInfo);
    }

    /**
     * 删除存储奖品的基础信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDrawPrizeInfoByIds(String ids)
    {
        return drawPrizeInfoMapper.deleteDrawPrizeInfoByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除存储奖品的基础信息信息
     * 
     * @param PRIZEID 存储奖品的基础信息ID
     * @return 结果
     */
    @Override
    public int deleteDrawPrizeInfoById(String PRIZEID)
    {
        return drawPrizeInfoMapper.deleteDrawPrizeInfoById(PRIZEID);
    }
}
