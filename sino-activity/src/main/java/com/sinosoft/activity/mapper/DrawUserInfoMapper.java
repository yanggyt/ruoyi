package com.sinosoft.activity.mapper;

import com.sinosoft.activity.domain.DrawUserInfo;

import java.util.List;

/**
 * 用户信息收集页面Mapper接口
 *
 * @author ruoyi
 * @date 2021-04-20
 */
public interface DrawUserInfoMapper
{
    /**
     * 查询用户信息收集页面
     *
     * @param awarDrecordId 用户信息收集页面ID
     * @return 用户信息收集页面
     */
    public DrawUserInfo selectDrawUserInfoById(Long awarDrecordId);

    /**
     * 查询用户信息收集页面列表
     *
     * @param drawUserInfo 用户信息收集页面
     * @return 用户信息收集页面集合
     */
    public List<DrawUserInfo> selectDrawUserInfoList(DrawUserInfo drawUserInfo);

    /**
     * 新增用户信息收集页面
     *
     * @param drawUserInfo 用户信息收集页面
     * @return 结果
     */
    public int insertDrawUserInfo(DrawUserInfo drawUserInfo);

    /**
     * 修改用户信息收集页面
     *
     * @param drawUserInfo 用户信息收集页面
     * @return 结果
     */
    public int updateDrawUserInfo(DrawUserInfo drawUserInfo);

    /**
     * 删除用户信息收集页面
     *
     * @param awarDrecordId 用户信息收集页面ID
     * @return 结果
     */
    public int deleteDrawUserInfoById(Long awarDrecordId);

    /**
     * 批量删除用户信息收集页面
     *
     * @param awarDrecordIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawUserInfoByIds(String[] awarDrecordIds);
}