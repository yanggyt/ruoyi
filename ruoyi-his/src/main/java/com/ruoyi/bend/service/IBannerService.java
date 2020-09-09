package com.ruoyi.bend.service;

import java.util.List;
import com.ruoyi.bend.domain.Banner;

/**
 * 首页管理Service接口
 * 
 * @author bend
 * @date 2020-08-30
 */
public interface IBannerService 
{
    /**
     * 查询首页管理
     * 
     * @param id 首页管理ID
     * @return 首页管理
     */
    public Banner selectBannerById(Long id);

    /**
     * 查询首页管理列表
     * 
     * @param banner 首页管理
     * @return 首页管理集合
     */
    public List<Banner> selectBannerList(Banner banner);

    /**
     * 新增首页管理
     * 
     * @param banner 首页管理
     * @return 结果
     */
    public int insertBanner(Banner banner);

    /**
     * 修改首页管理
     * 
     * @param banner 首页管理
     * @return 结果
     */
    public int updateBanner(Banner banner);

    /**
     * 批量删除首页管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBannerByIds(String ids);

    /**
     * 删除首页管理信息
     * 
     * @param id 首页管理ID
     * @return 结果
     */
    public int deleteBannerById(Long id);
}
