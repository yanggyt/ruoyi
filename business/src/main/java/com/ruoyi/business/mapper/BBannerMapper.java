package com.ruoyi.business.mapper;

import java.util.List;
import com.ruoyi.business.domain.BBanner;

/**
 * 轮播图Mapper接口
 * 
 * @author anjie
 * @date 2020-06-21
 */
public interface BBannerMapper 
{
    /**
     * 查询轮播图
     * 
     * @param id 轮播图ID
     * @return 轮播图
     */
    public BBanner selectBBannerById(Long id);

    /**
     * 查询轮播图列表
     * 
     * @param bBanner 轮播图
     * @return 轮播图集合
     */
    public List<BBanner> selectBBannerList(BBanner bBanner);

    /**
     * 新增轮播图
     * 
     * @param bBanner 轮播图
     * @return 结果
     */
    public int insertBBanner(BBanner bBanner);

    /**
     * 修改轮播图
     * 
     * @param bBanner 轮播图
     * @return 结果
     */
    public int updateBBanner(BBanner bBanner);

    /**
     * 删除轮播图
     * 
     * @param id 轮播图ID
     * @return 结果
     */
    public int deleteBBannerById(Long id);

    /**
     * 批量删除轮播图
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBBannerByIds(String[] ids);
}
