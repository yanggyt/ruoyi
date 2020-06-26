package com.ruoyi.business.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.business.mapper.BBannerMapper;
import com.ruoyi.business.domain.BBanner;
import com.ruoyi.business.service.IBBannerService;
import com.ruoyi.common.core.text.Convert;

/**
 * 轮播图Service业务层处理
 * 
 * @author anjie
 * @date 2020-06-21
 */
@Service
public class BBannerServiceImpl implements IBBannerService 
{
    @Autowired
    private BBannerMapper bBannerMapper;

    /**
     * 查询轮播图
     * 
     * @param id 轮播图ID
     * @return 轮播图
     */
    @Override
    public BBanner selectBBannerById(Long id)
    {
        return bBannerMapper.selectBBannerById(id);
    }

    /**
     * 查询轮播图列表
     * 
     * @param bBanner 轮播图
     * @return 轮播图
     */
    @Override
    public List<BBanner> selectBBannerList(BBanner bBanner)
    {
        return bBannerMapper.selectBBannerList(bBanner);
    }

    /**
     * 新增轮播图
     * 
     * @param bBanner 轮播图
     * @return 结果
     */
    @Override
    public int insertBBanner(BBanner bBanner)
    {
        bBanner.setCreateTime(DateUtils.getNowDate());
        return bBannerMapper.insertBBanner(bBanner);
    }

    /**
     * 修改轮播图
     * 
     * @param bBanner 轮播图
     * @return 结果
     */
    @Override
    public int updateBBanner(BBanner bBanner)
    {
        bBanner.setUpdateTime(DateUtils.getNowDate());
        return bBannerMapper.updateBBanner(bBanner);
    }

    /**
     * 删除轮播图对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteBBannerByIds(String ids)
    {
        return bBannerMapper.deleteBBannerByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除轮播图信息
     * 
     * @param id 轮播图ID
     * @return 结果
     */
    @Override
    public int deleteBBannerById(Long id)
    {
        return bBannerMapper.deleteBBannerById(id);
    }
}
