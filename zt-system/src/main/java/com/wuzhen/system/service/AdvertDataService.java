package com.wuzhen.system.service;

import com.wuzhen.common.core.domain.entity.SysDictData;
import com.wuzhen.system.domain.AdvertInfo;
import java.util.List;

/**
 * 广告位 业务层
 * 
 * @author zhengzheng
 */
public interface AdvertDataService
{
    /**
     * 根据条件分页查询广告位
     * 
     * @param advertInfo 广告位信息
     * @return 广告位集合信息
     */
    public List<AdvertInfo> selectAdvertDataList(AdvertInfo advertInfo);



    /**
     * 批量删除广告位数据
     * 
     * @param ids 需要删除的数据
     */
    public void deleteAdvertDataByIds(String ids);

    /**
     * 新增保存广告位数据信息
     * 
     * @param advertInfo 广告位数据信息
     * @return 结果
     */
    public int insertAdvertData(AdvertInfo advertInfo);

    /**
     * 修改保存广告位数据信息
     * 
     * @param advertInfo 广告位数据信息
     * @return 结果
     */
    public int updateAdvertData(AdvertInfo advertInfo);


    /**
     * 根据广告数据ID查询信息
     *
     * @param dictCode 广告数据ID
     * @return 广告数据
     */
    public AdvertInfo selectAdvertDataById(Long dictCode);
}
