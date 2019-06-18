package com.ruoyi.assets.service.impl;

import com.ruoyi.assets.domain.AssetsCabinet;
import com.ruoyi.assets.mapper.AssetsCabinetMapper;
import com.ruoyi.assets.service.IAssetsCabinetService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 机柜类型 服务层实现
 *
 * @author TP
 * @date 2019-06-17
 */
@Service
public class AssetsCabinetServiceImpl implements IAssetsCabinetService {
    @Autowired
    private AssetsCabinetMapper assetsCabinetMapper;

    /**
     * 查询机柜类型信息
     *
     * @param cabinetId 机柜类型ID
     * @return 机柜类型信息
     */
    @Override
    public AssetsCabinet selectAssetsCabinetById(Integer cabinetId) {
        return assetsCabinetMapper.selectAssetsCabinetById(cabinetId);
    }

    /**
     * 查询机柜类型列表
     *
     * @param assetsCabinet 机柜类型信息
     * @return 机柜类型集合
     */
    @Override
    public List<AssetsCabinet> selectAssetsCabinetList(AssetsCabinet assetsCabinet) {
        return assetsCabinetMapper.selectAssetsCabinetList(assetsCabinet);
    }

    /**
     * 新增机柜类型
     *
     * @param assetsCabinet 机柜类型信息
     * @return 结果
     */
    @Override
    public int insertAssetsCabinet(AssetsCabinet assetsCabinet) {
        return assetsCabinetMapper.insertAssetsCabinet(assetsCabinet);
    }

    /**
     * 修改机柜类型
     *
     * @param assetsCabinet 机柜类型信息
     * @return 结果
     */
    @Override
    public int updateAssetsCabinet(AssetsCabinet assetsCabinet) {
        return assetsCabinetMapper.updateAssetsCabinet(assetsCabinet);
    }

    /**
     * 删除机柜类型对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteAssetsCabinetByIds(String ids) {
        return assetsCabinetMapper.deleteAssetsCabinetByIds(Convert.toStrArray(ids));
    }

}
