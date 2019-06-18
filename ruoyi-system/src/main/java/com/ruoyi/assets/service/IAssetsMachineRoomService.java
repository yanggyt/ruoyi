package com.ruoyi.assets.service;

import com.ruoyi.assets.domain.AssetsMachineRoom;

import java.util.List;

/**
 * 机房 服务层
 *
 * @author TP
 * @date 2019-06-17
 */
public interface IAssetsMachineRoomService {
    /**
     * 查询机房信息
     *
     * @param machineRoomId 机房ID
     * @return 机房信息
     */
    public AssetsMachineRoom selectAssetsMachineRoomById(Integer machineRoomId);

    /**
     * 查询机房列表
     *
     * @param assetsMachineRoom 机房信息
     * @return 机房集合
     */
    public List<AssetsMachineRoom> selectAssetsMachineRoomList(AssetsMachineRoom assetsMachineRoom);

    /**
     * 新增机房
     *
     * @param assetsMachineRoom 机房信息
     * @return 结果
     */
    public int insertAssetsMachineRoom(AssetsMachineRoom assetsMachineRoom);

    /**
     * 修改机房
     *
     * @param assetsMachineRoom 机房信息
     * @return 结果
     */
    public int updateAssetsMachineRoom(AssetsMachineRoom assetsMachineRoom);

    /**
     * 删除机房信息
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteAssetsMachineRoomByIds(String ids);

}
