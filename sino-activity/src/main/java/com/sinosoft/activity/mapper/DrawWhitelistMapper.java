package com.sinosoft.activity.mapper;

import java.util.List;
import com.sinosoft.activity.domain.DrawWhitelist;

/**
 * 白名单信息Mapper接口
 * 
 * @author dy
 * @date 2021-04-19
 */
public interface DrawWhitelistMapper 
{
    /**
     * 查询白名单信息
     * 
     * @param WHITELISTID 白名单信息ID
     * @return 白名单信息
     */
    public DrawWhitelist selectDrawWhitelistById(String WHITELISTID);

    /**
     * 查询白名单信息列表
     * 
     * @param drawWhitelist 白名单信息
     * @return 白名单信息集合
     */
    public List<DrawWhitelist> selectDrawWhitelistList(DrawWhitelist drawWhitelist);

    /**
     * 新增白名单信息
     * 
     * @param drawWhitelist 白名单信息
     * @return 结果
     */
    public int insertDrawWhitelist(DrawWhitelist drawWhitelist);

    /**
     * 修改白名单信息
     * 
     * @param drawWhitelist 白名单信息
     * @return 结果
     */
    public int updateDrawWhitelist(DrawWhitelist drawWhitelist);

    /**
     * 删除白名单信息
     * 
     * @param WHITELISTID 白名单信息ID
     * @return 结果
     */
    public int deleteDrawWhitelistById(String WHITELISTID);

    /**
     * 批量删除白名单信息
     * 
     * @param WHITELISTIDs 需要删除的数据ID
     * @return 结果
     */
    public int deleteDrawWhitelistByIds(String[] WHITELISTIDs);
}
