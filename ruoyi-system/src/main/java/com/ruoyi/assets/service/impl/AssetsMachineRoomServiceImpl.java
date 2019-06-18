package com.ruoyi.assets.service.impl;

import com.ruoyi.assets.domain.AssetsMachineRoom;
import com.ruoyi.assets.mapper.AssetsMachineRoomMapper;
import com.ruoyi.assets.service.IAssetsMachineRoomService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机房 服务层实现
 *
 * @author TP
 * @date 2019-06-17
 */
@Service
public class AssetsMachineRoomServiceImpl implements IAssetsMachineRoomService {
    @Autowired
    private AssetsMachineRoomMapper assetsMachineRoomMapper;

    /**
     * 查询机房信息
     *
     * @param machineRoomId 机房ID
     * @return 机房信息
     */
    @Override
    public AssetsMachineRoom selectAssetsMachineRoomById(Integer machineRoomId) {
        return assetsMachineRoomMapper.selectAssetsMachineRoomById(machineRoomId);
    }

    /**
     * 查询机房列表
     *
     * @param assetsMachineRoom 机房信息
     * @return 机房集合
     */
    @Override
    public List<AssetsMachineRoom> selectAssetsMachineRoomList(AssetsMachineRoom assetsMachineRoom) {
        return assetsMachineRoomMapper.selectAssetsMachineRoomList(assetsMachineRoom);
    }

    /**
     * 新增机房
     *
     * @param assetsMachineRoom 机房信息
     * @return 结果
     */
    @Override
    public int insertAssetsMachineRoom(AssetsMachineRoom assetsMachineRoom) {
        return assetsMachineRoomMapper.insertAssetsMachineRoom(assetsMachineRoom);
    }

    /**
     * 修改机房
     *
     * @param assetsMachineRoom 机房信息
     * @return 结果
     */
    @Override
    public int updateAssetsMachineRoom(AssetsMachineRoom assetsMachineRoom) {
        return assetsMachineRoomMapper.updateAssetsMachineRoom(assetsMachineRoom);
    }

    /**
     * 删除机房对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAssetsMachineRoomByIds(String ids) {
        return assetsMachineRoomMapper.deleteAssetsMachineRoomByIds(Convert.toStrArray(ids));
    }

}
