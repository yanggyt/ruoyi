package com.ruoyi.bend.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.bend.mapper.BannerMapper;
import com.ruoyi.bend.domain.Banner;
import com.ruoyi.bend.service.IBannerService;
import com.ruoyi.common.core.text.Convert;

/**
 * 首页管理Service业务层处理
 * 
 * @author bend
 * @date 2020-08-30
 */
@Service
public class BannerServiceImpl implements IBannerService 
{
    @Autowired
    private BannerMapper bannerMapper;

    /**
     * 查询首页管理
     * 
     * @param id 首页管理ID
     * @return 首页管理
     */
    @Override
    public Banner selectBannerById(Long id)
    {
        return bannerMapper.selectBannerById(id);
    }

    /**
     * 查询首页管理列表
     * 
     * @param banner 首页管理
     * @return 首页管理
     */
    @Override
    public List<Banner> selectBannerList(Banner banner)
    {
        return bannerMapper.selectBannerList(banner);
    }

    /**
     * 新增首页管理
     * 
     * @param banner 首页管理
     * @return 结果
     */
    @Override
    public int insertBanner(Banner banner)
    {
        banner.setCreateTime(DateUtils.getNowDate());
        return bannerMapper.insertBanner(banner);
    }

    /**
     * 修改首页管理
     * 
     * @param banner 首页管理
     * @return 结果
     */
    @Override
    public int updateBanner(Banner banner)
    {
        banner.setUpdateTime(DateUtils.getNowDate());
        return bannerMapper.updateBanner(banner);
    }

    /**
     * 删除首页管理对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBannerByIds(String ids)
    {
        return bannerMapper.deleteBannerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除首页管理信息
     * 
     * @param id 首页管理ID
     * @return 结果
     */
    @Override
    public int deleteBannerById(Long id)
    {
        return bannerMapper.deleteBannerById(id);
    }
}
