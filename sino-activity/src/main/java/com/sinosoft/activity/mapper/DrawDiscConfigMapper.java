package com.sinosoft.activity.mapper;

import java.util.List;
import com.sinosoft.activity.domain.DrawDiscConfig;

/**
 * 用户路由信息Mapper接口
 * 
 * @author dy
 * @date 2021-04-19
 */
public interface DrawDiscConfigMapper 
{
    /**
     * 查询用户路由信息
     * 
     * @param DISCDRAWCONFIGID 用户路由信息ID
     * @return 用户路由信息
     */
    public DrawDiscConfig selectDrawDiscConfigById(String DISCDRAWCONFIGID);

    /**
     * 查询用户路由信息列表
     * 
     * @param drawDiscConfig 用户路由信息
     * @return 用户路由信息集合
     */
    public List<DrawDiscConfig> selectDrawDiscConfigList(DrawDiscConfig drawDiscConfig);

    /**
     * 新增用户路由信息
     * 
     * @param drawDiscConfig 用户路由信息
     * @return 结果
     */
    public int insertDrawDiscConfig(DrawDiscConfig drawDiscConfig);

    /**
     * 修改用户路由信息
     * 
     * @param drawDiscConfig 用户路由信息
     * @return 结果
     */
    public int updateDrawDiscConfig(DrawDiscConfig drawDiscConfig);

    /**
     * 删除用户路由信息
     * 
     * @param DISCDRAWCONFIGID 用户路由信息ID
     * @return 结果
     */
    public int deleteDrawDiscConfigById(String DISCDRAWCONFIGID);

    /**
     * 批量删除用户路由信息
     * 
     * @param DISCDRAWCONFIGIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawDiscConfigByIds(String[] DISCDRAWCONFIGIDs);
}
