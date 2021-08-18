package com.ruoyi.system.mapper;

import com.ruoyi.system.domain.WechatAccessToken;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
/**
 * 企业微信获取Access Token 的Mapper接口
 * 
 * @author Bo
 * @date 2021-07-21
 */
public interface WechatAccessTokenMapper
{



    /**
     *  根据SID查询Access Token
     *
     * @param sid
     * @return 企业微信Access Token信息
     */
    public WechatAccessToken selectWechatAccessTokenById(Long sid);
    

    /**
     *  查询Access Token
     * @param  wechatAccessToken
     * @return 企业微信Access Token信息
     */
    public List<WechatAccessToken> selectWechatAccessTokenList(WechatAccessToken wechatAccessToken);

    /**
     * 新增Access Token
     *
     * @param wechatAccessToken Access Token
     * @return 结果
     */
    public int insertWechatAccessToken(WechatAccessToken wechatAccessToken);

    /**
     * 修改Access Token
     *
     * @param wechatAccessToken Access Token
     * @return 结果
     */
    public int updateWechatAccessToken(WechatAccessToken wechatAccessToken);

    /**
     * 删除Access Token
     *
     * @param sid Access TokenID
     * @return 结果
     */
    public int deleteWechatAccessTokenById(Long sid);

    /**
     * 批量删除Access Token
     *
     * @param sids 需要删除的数据ID
     * @return 结果
     */
    public int deleteWechatAccessTokenByIds(String[] sids);
}
