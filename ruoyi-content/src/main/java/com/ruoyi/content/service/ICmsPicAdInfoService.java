package com.ruoyi.content.service;

import java.util.List;

import com.ruoyi.content.domain.CmsPicAdInfo;

/**
 * 图片广告Service接口
 *
 * @author liushenlu
 * @date 2021-03-25
 */
public interface ICmsPicAdInfoService {

    /**
     * 查询图片广告
     *
     * @param picAdId 图片广告ID
     * @return 图片广告
     */
    public CmsPicAdInfo selectCmsPicAdInfoById(Long picAdId);

    /**
     * 查询图片广告列表
     *
     * @param cmsPicAdInfo 图片广告
     * @return 图片广告集合
     */
    public List<CmsPicAdInfo> selectCmsPicAdInfoList(CmsPicAdInfo cmsPicAdInfo);

    /**
     * 新增图片广告
     *
     * @param cmsPicAdInfo 图片广告
     * @return 结果
     */
    public int insertCmsPicAdInfo(CmsPicAdInfo cmsPicAdInfo);

    /**
     * 修改图片广告
     *
     * @param cmsPicAdInfo 图片广告
     * @return 结果
     */
    public int updateCmsPicAdInfo(CmsPicAdInfo cmsPicAdInfo);

    /**
     * 批量删除图片广告
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmsPicAdInfoByIds(String ids);

    /**
     * 删除图片广告信息
     *
     * @param picAdId 图片广告ID
     * @return 结果
     */
    public int deleteCmsPicAdInfoById(Long picAdId);
}
