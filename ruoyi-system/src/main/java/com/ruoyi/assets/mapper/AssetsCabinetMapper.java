package com.ruoyi.assets.mapper;

import com.ruoyi.assets.domain.AssetsCabinet;

import java.util.List;

/**
 * 机柜类型 数据层
 *
 * @author TP
 * @date 2019-06-17
 */
public interface AssetsCabinetMapper {
    /**
     * 查询机柜类型信息
     *
     * @param cabinetId 机柜类型ID
     * @return 机柜类型信息
     */
    public AssetsCabinet selectAssetsCabinetById(Integer cabinetId);

    /**
     * 查询机柜类型列表
     *
     * @param assetsCabinet 机柜类型信息
     * @return 机柜类型集合
     */
    public List<AssetsCabinet> selectAssetsCabinetList(AssetsCabinet assetsCabinet);

    /**
     * 新增机柜类型
     *
     * @param assetsCabinet 机柜类型信息
     * @return 结果
     */
    public int insertAssetsCabinet(AssetsCabinet assetsCabinet);

    /**
     * 修改机柜类型
     *
     * @param assetsCabinet 机柜类型信息
     * @return 结果
     */
    public int updateAssetsCabinet(AssetsCabinet assetsCabinet);

    /**
     * 删除机柜类型
     *
     * @param cabinetId 机柜类型ID
     * @return 结果
     */
    public int deleteAssetsCabinetById(Integer cabinetId);

    /**
     * 批量删除机柜类型
     *
     * @param cabinetIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetsCabinetByIds(String[] cabinetIds);

}