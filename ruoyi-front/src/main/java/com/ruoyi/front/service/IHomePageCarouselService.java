package com.ruoyi.front.service;

import java.util.List;
import com.ruoyi.front.domain.HomePageCarousel;

/**
 * 首页轮播图Service接口
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public interface IHomePageCarouselService 
{
    /**
     * 查询首页轮播图
     * 
     * @param id 首页轮播图ID
     * @return 首页轮播图
     */
    public HomePageCarousel selectHomePageCarouselById(Long id);

    /**
     * 查询首页轮播图列表
     * 
     * @param homePageCarousel 首页轮播图
     * @return 首页轮播图集合
     */
    public List<HomePageCarousel> selectHomePageCarouselList(HomePageCarousel homePageCarousel);

    /**
     * 新增首页轮播图
     * 
     * @param homePageCarousel 首页轮播图
     * @return 结果
     */
    public int insertHomePageCarousel(HomePageCarousel homePageCarousel);

    /**
     * 修改首页轮播图
     * 
     * @param homePageCarousel 首页轮播图
     * @return 结果
     */
    public int updateHomePageCarousel(HomePageCarousel homePageCarousel);

    /**
     * 批量删除首页轮播图
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteHomePageCarouselByIds(String ids);

    /**
     * 删除首页轮播图信息
     * 
     * @param id 首页轮播图ID
     * @return 结果
     */
    public int deleteHomePageCarouselById(Long id);
}
