package com.sinosoft.activity.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinosoft.activity.mapper.DrawWhitelistMapper;
import com.sinosoft.activity.domain.DrawWhitelist;
import com.sinosoft.activity.service.IDrawWhitelistService;
import com.ruoyi.common.core.text.Convert;

/**
 * 白名单信息Service业务层处理
 * 
 * @author dy
 * @date 2021-04-19
 */
@Service
public class DrawWhitelistServiceImpl implements IDrawWhitelistService 
{
    @Autowired
    private DrawWhitelistMapper drawWhitelistMapper;

    /**
     * 查询白名单信息
     * 
     * @param WHITELISTID 白名单信息ID
     * @return 白名单信息
     */
    @Override
    public DrawWhitelist selectDrawWhitelistById(String WHITELISTID)
    {
        return drawWhitelistMapper.selectDrawWhitelistById(WHITELISTID);
    }

    /**
     * 查询白名单信息列表
     * 
     * @param drawWhitelist 白名单信息
     * @return 白名单信息
     */
    @Override
    public List<DrawWhitelist> selectDrawWhitelistList(DrawWhitelist drawWhitelist)
    {
        return drawWhitelistMapper.selectDrawWhitelistList(drawWhitelist);
    }

    /**
     * 新增白名单信息
     * 
     * @param drawWhitelist 白名单信息
     * @return 结果
     */
    @Override
    public int insertDrawWhitelist(DrawWhitelist drawWhitelist)
    {
        return drawWhitelistMapper.insertDrawWhitelist(drawWhitelist);
    }

    /**
     * 修改白名单信息
     * 
     * @param drawWhitelist 白名单信息
     * @return 结果
     */
    @Override
    public int updateDrawWhitelist(DrawWhitelist drawWhitelist)
    {
        return drawWhitelistMapper.updateDrawWhitelist(drawWhitelist);
    }

    /**
     * 删除白名单信息对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDrawWhitelistByIds(String ids)
    {
        return drawWhitelistMapper.deleteDrawWhitelistByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除白名单信息信息
     * 
     * @param WHITELISTID 白名单信息ID
     * @return 结果
     */
    @Override
    public int deleteDrawWhitelistById(String WHITELISTID)
    {
        return drawWhitelistMapper.deleteDrawWhitelistById(WHITELISTID);
    }
}
