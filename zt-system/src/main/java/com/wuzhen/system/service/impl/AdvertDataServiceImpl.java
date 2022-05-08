package com.wuzhen.system.service.impl;

import com.wuzhen.common.core.domain.entity.SysDictData;
import com.wuzhen.common.core.text.Convert;
import com.wuzhen.system.domain.AdvertInfo;
import com.wuzhen.system.mapper.AdvertDataMapper;
import com.wuzhen.system.service.AdvertDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * 广告 业务层处理
 * 
 * @author zhengzheng
 */
@Service
public class AdvertDataServiceImpl implements AdvertDataService
{
    @Autowired
    private AdvertDataMapper advertDataMapper;

    /**
     * 根据条件分页查询广告数据
     * 
     * @param advertInfo 广告数据信息
     * @return 广告数据集合信息
     */
    @Override
    public List<AdvertInfo> selectAdvertDataList(AdvertInfo advertInfo)
    {
        return advertDataMapper.selectAdvertDataList(advertInfo);
    }



    /**
     * 批量删除广告数据
     * 
     * @param ids 需要删除的数据
     */
    @Override
    public void deleteAdvertDataByIds(String ids)
    {
        Long[] ids_arr = Convert.toLongArray(ids);
        advertDataMapper.deleteAdvertDataByIds(ids_arr);
    }

    /**
     * 新增保存广告数据信息
     * 
     * @param data 广告数据信息
     * @return 结果
     */
    @Override
    public int insertAdvertData(AdvertInfo data)
    {
        int row = advertDataMapper.insertAdvertData(data);
        return row;
    }

    /**
     * 修改保存广告数据信息
     * 
     * @param data 广告数据信息
     * @return 结果
     */
    @Override
    public int updateAdvertData(AdvertInfo data)
    {
        int row = advertDataMapper.updateAdvertData(data);
        return row;
    }

    @Override
    public AdvertInfo selectAdvertDataById(Long dictCode) {
        AdvertInfo advertInfo = advertDataMapper.selectAdvertDataById(dictCode);
        return  advertInfo;
    }
}
