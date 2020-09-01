package com.ruoyi.bend.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.bend.domain.ScrcuOnlineOrders;
import com.ruoyi.bend.domain.ScrcuOnlineOrderDetails;
import java.util.List;

/**
 * 收单列表Mapper接口
 * 
 * @author bend
 * @date 2020-09-01
 */
public interface ScrcuOnlineOrdersMapper extends RuoYiBaseMapper<ScrcuOnlineOrders>
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
     * 删除收单列表
     * 
     * @param id 收单列表ID
     * @return 结果
     */
    public int deleteScrcuOnlineOrdersById(Long id);

    /**
     * 批量删除收单列表
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteScrcuOnlineOrdersByIds(String[] ids);

    /**
     * 批量删除收单详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteScrcuOnlineOrderDetailsByOrderNumbers(String[] ids);
    
    /**
     * 批量新增收单详情
     * 
     * @param scrcuOnlineOrderDetailsList 收单详情列表
     * @return 结果
     */
    public int batchScrcuOnlineOrderDetails(List<ScrcuOnlineOrderDetails> scrcuOnlineOrderDetailsList);
    

    /**
     * 通过收单列表ID删除收单详情信息
     * 
     * @param id 收单列表ID
     * @return 结果
     */
    public int deleteScrcuOnlineOrderDetailsByOrderNumber(Long id);
}
