package com.ruoyi.front.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.front.mapper.HomePageCarouselMapper;
import com.ruoyi.front.domain.HomePageCarousel;
import com.ruoyi.front.service.IHomePageCarouselService;
import com.ruoyi.common.core.text.Convert;

/**
 * 首页轮播图Service业务层处理
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
@Service
public class HomePageCarouselServiceImpl implements IHomePageCarouselService 
{
    @Autowired
    private HomePageCarouselMapper homePageCarouselMapper;

    /**
     * 查询首页轮播图
     * 
     * @param id 首页轮播图ID
     * @return 首页轮播图
     */
    @Override
    public HomePageCarousel selectHomePageCarouselById(Long id)
    {
        return homePageCarouselMapper.selectHomePageCarouselById(id);
    }

    /**
     * 查询首页轮播图列表
     * 
     * @param homePageCarousel 首页轮播图
     * @return 首页轮播图
     */
    @Override
    public List<HomePageCarousel> selectHomePageCarouselList(HomePageCarousel homePageCarousel)
    {
        return homePageCarouselMapper.selectHomePageCarouselList(homePageCarousel);
    }

    /**
     * 新增首页轮播图
     * 
     * @param homePageCarousel 首页轮播图
     * @return 结果
     */
    @Override
    public int insertHomePageCarousel(HomePageCarousel homePageCarousel)
    {
        homePageCarousel.setCreateTime(DateUtils.getNowDate());
        return homePageCarouselMapper.insertHomePageCarousel(homePageCarousel);
    }

    /**
     * 修改首页轮播图
     * 
     * @param homePageCarousel 首页轮播图
     * @return 结果
     */
    @Override
    public int updateHomePageCarousel(HomePageCarousel homePageCarousel)
    {
        homePageCarousel.setUpdateTime(DateUtils.getNowDate());
        return homePageCarouselMapper.updateHomePageCarousel(homePageCarousel);
    }

    /**
     * 删除首页轮播图对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteHomePageCarouselByIds(String ids)
    {
        return homePageCarouselMapper.deleteHomePageCarouselByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除首页轮播图信息
     * 
     * @param id 首页轮播图ID
     * @return 结果
     */
    @Override
    public int deleteHomePageCarouselById(Long id)
    {
        return homePageCarouselMapper.deleteHomePageCarouselById(id);
    }
}
