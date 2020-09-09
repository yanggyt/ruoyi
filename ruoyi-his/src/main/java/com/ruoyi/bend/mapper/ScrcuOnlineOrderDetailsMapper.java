package com.ruoyi.bend.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.bend.domain.ScrcuOnlineOrderDetails;
import java.util.List;

/**
 * 收单详情Mapper接口
 * 
 * @author bend
 * @date 2020-09-01
 */
public interface ScrcuOnlineOrderDetailsMapper extends RuoYiBaseMapper<ScrcuOnlineOrderDetails>
{
    /**
     * 查询收单详情
     * 
     * @param id 收单详情ID
     * @return 收单详情
     */
    public ScrcuOnlineOrderDetails selectScrcuOnlineOrderDetailsById(Long id);

    /**
     * 查询收单详情列表
     * 
     * @param scrcuOnlineOrderDetails 收单详情
     * @return 收单详情集合
     */
    public List<ScrcuOnlineOrderDetails> selectScrcuOnlineOrderDetailsList(ScrcuOnlineOrderDetails scrcuOnlineOrderDetails);

    /**
     * 新增收单详情
     * 
     * @param scrcuOnlineOrderDetails 收单详情
     * @return 结果
     */
    public int insertScrcuOnlineOrderDetails(ScrcuOnlineOrderDetails scrcuOnlineOrderDetails);

    /**
     * 修改收单详情
     * 
     * @param scrcuOnlineOrderDetails 收单详情
     * @return 结果
     */
    public int updateScrcuOnlineOrderDetails(ScrcuOnlineOrderDetails scrcuOnlineOrderDetails);

    /**
     * 删除收单详情
     * 
     * @param id 收单详情ID
     * @return 结果
     */
    public int deleteScrcuOnlineOrderDetailsById(Long id);

    /**
     * 批量删除收单详情
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteScrcuOnlineOrderDetailsByIds(String[] ids);
}
