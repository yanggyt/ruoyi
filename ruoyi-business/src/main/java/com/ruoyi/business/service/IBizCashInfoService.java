package com.ruoyi.business.service;

import java.util.List;
import com.ruoyi.business.domain.BizCashInfo;

/**
 * 兑现申请记录Service接口
 * 
 * @author ruoyi
 * @date 2020-09-23
 */
public interface IBizCashInfoService 
{
    /**
     * 查询兑现申请记录
     * 
     * @param id 兑现申请记录ID
     * @return 兑现申请记录
     */
    public BizCashInfo selectBizCashInfoById(Long id);

    /**
     * 查询兑现申请记录列表
     * 
     * @param bizCashInfo 兑现申请记录
     * @return 兑现申请记录集合
     */
    public List<BizCashInfo> selectBizCashInfoList(BizCashInfo bizCashInfo);

    /**
     * 新增兑现申请记录
     * 
     * @param bizCashInfo 兑现申请记录
     * @return 结果
     */
    public int insertBizCashInfo(BizCashInfo bizCashInfo);

    /**
     * 修改兑现申请记录
     * 
     * @param bizCashInfo 兑现申请记录
     * @return 结果
     */
    public int updateBizCashInfo(BizCashInfo bizCashInfo);

    /**
     * 批量删除兑现申请记录
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBizCashInfoByIds(String ids);

    /**
     * 删除兑现申请记录信息
     * 
     * @param id 兑现申请记录ID
     * @return 结果
     */
    public int deleteBizCashInfoById(Long id);
}
