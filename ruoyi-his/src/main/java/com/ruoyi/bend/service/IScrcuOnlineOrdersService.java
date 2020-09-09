package com.ruoyi.bend.service;

import java.util.List;
import com.ruoyi.bend.domain.ScrcuOnlineOrders;

/**
 * 收单列表Service接口
 * 
 * @author bend
 * @date 2020-09-01
 */
public interface IScrcuOnlineOrdersService 
{
    /**
     * 查询收单列表
     * 
     * @param id 收单列表ID
     * @return 收单列表
     */
    public ScrcuOnlineOrders selectScrcuOnlineOrdersById(Long id);

    /**
     * 查询收单列表列表
     * 
     * @param scrcuOnlineOrders 收单列表
     * @return 收单列表集合
     */
    public List<ScrcuOnlineOrders> selectScrcuOnlineOrdersList(ScrcuOnlineOrders scrcuOnlineOrders);

    /**
     * 新增收单列表
     * 
     * @param scrcuOnlineOrders 收单列表
     * @return 结果
     */
    public int insertScrcuOnlineOrders(ScrcuOnlineOrders scrcuOnlineOrders);

    /**
     * 修改收单列表
     * 
     * @param scrcuOnlineOrders 收单列表
     * @return 结果
     */
    public int updateScrcuOnlineOrders(ScrcuOnlineOrders scrcuOnlineOrders);

    /**
     * 批量删除收单列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteScrcuOnlineOrdersByIds(String ids);

    /**
     * 删除收单列表信息
     * 
     * @param id 收单列表ID
     * @return 结果
     */
    public int deleteScrcuOnlineOrdersById(Long id);

    /**
     * 查询在线收单
     *
     * @param scrcuOnlineOrders 在线收单
     * @return 在线收单
     */
    public ScrcuOnlineOrders selectScrcuOnlineOrders(ScrcuOnlineOrders scrcuOnlineOrders);
}
