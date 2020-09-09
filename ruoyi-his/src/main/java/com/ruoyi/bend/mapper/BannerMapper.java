package com.ruoyi.bend.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.bend.domain.Banner;
import java.util.List;

/**
 * 首页管理Mapper接口
 * 
 * @author bend
 * @date 2020-08-30
 */
public interface BannerMapper extends RuoYiBaseMapper<Banner>
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
     * 删除首页管理
     * 
     * @param id 首页管理ID
     * @return 结果
     */
    public int deleteBannerById(Long id);

    /**
     * 批量删除首页管理
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteBannerByIds(String[] ids);
}
