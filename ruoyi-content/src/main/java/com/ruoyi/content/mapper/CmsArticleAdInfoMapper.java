package com.ruoyi.content.mapper;


import com.ruoyi.content.domain.CmsArticleAdInfo;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 文章广告Mapper接口
 *
 * @author ruoyi
 * @date 2021-03-23
 */
@Repository
public interface CmsArticleAdInfoMapper {
    /**
     * 查询文章广告
     *
     * @param adId 文章广告ID
     * @return 文章广告
     */
    public CmsArticleAdInfo selectCmsArticleAdInfoById(Long adId);

    /**
     * 查询文章广告列表
     *
     * @param cmsArticleAdInfo 文章广告
     * @return 文章广告集合
     */
    public List<CmsArticleAdInfo> selectCmsArticleAdInfoList(CmsArticleAdInfo cmsArticleAdInfo);

    /**
     * 新增文章广告
     *
     * @param cmsArticleAdInfo 文章广告
     * @return 结果
     */
    public int insertCmsArticleAdInfo(CmsArticleAdInfo cmsArticleAdInfo);

    /**
     * 修改文章广告
     *
     * @param cmsArticleAdInfo 文章广告
     * @return 结果
     */
    public int updateCmsArticleAdInfo(CmsArticleAdInfo cmsArticleAdInfo);

    /**
     * 删除文章广告
     *
     * @param adId 文章广告ID
     * @return 结果
     */
    public int deleteCmsArticleAdInfoById(Long adId);

    /**
     * 批量删除文章广告
     *
     * @param adIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmsArticleAdInfoByIds(String[] adIds);
}
