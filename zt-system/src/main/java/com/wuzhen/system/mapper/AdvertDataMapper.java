package com.wuzhen.system.mapper;

import com.wuzhen.common.core.domain.entity.SysDictData;
import com.wuzhen.system.domain.AdvertInfo;
import java.util.List;

/**
 * 广告表 数据层
 * 
 * @author zhengzheng
 */
public interface AdvertDataMapper
{
    /**
     * 根据条件分页查询广告数据
     * 
     * @param advertInfo 广告数据信息
     * @return 广告数据集合信息
     */
    public List<AdvertInfo> selectAdvertDataList(AdvertInfo advertInfo);


    /**
     * 批量删除广告数据
     * 
     * @param ids 需要删除的数据
     * @return 结果
     */
    public int deleteAdvertDataByIds(Long[] ids);

    /**
     * 新增广告数据信息
     * 
     * @param advertInfo 广告数据信息
     * @return 结果
     */
    public int insertAdvertData(AdvertInfo advertInfo);

    /**
     * 修改广告数据信息
     * 
     * @param advertInfo 广告数据信息
     * @return 结果
     */
    public int updateAdvertData(AdvertInfo advertInfo);


    /**
     * 根据广告数据ID查询信息
     *
     * @param id 广告数据ID
     * @return 广告数据
     */
    public AdvertInfo selectAdvertDataById(Long id);



}
