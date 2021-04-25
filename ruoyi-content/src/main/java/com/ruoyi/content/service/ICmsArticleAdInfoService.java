package com.ruoyi.content.service;

import java.util.List;

import com.ruoyi.content.domain.ArticleAdInfo;
import com.ruoyi.content.domain.CmsArticleAdInfo;
import org.springframework.web.multipart.MultipartFile;

/**
 * 文章广告Service接口
 *
 * @author ruoyi
 * @date 2021-03-23
 */
public interface ICmsArticleAdInfoService {
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
     * @param file 广告图片
     * @param cmsArticleAdInfo 文章广告
     * @return
     */
    public int insertCmsArticleAdInfo(MultipartFile file, CmsArticleAdInfo cmsArticleAdInfo);

    /**
     * 修改文章广告
     *
     * @param file 广告图片
     * @param cmsArticleAdInfo 文章广告
     * @return 结果
     */
    public int updateCmsArticleAdInfo(MultipartFile file, CmsArticleAdInfo cmsArticleAdInfo);

    /**
     * 批量删除文章广告
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteCmsArticleAdInfoByIds(String ids);

    /**
     * 删除文章广告信息
     *
     * @param adId 文章广告ID
     * @return 结果
     */
    public int deleteCmsArticleAdInfoById(Long adId);

    List<ArticleAdInfo> queryAdByCompanyId(String companyId);
}
