package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.*;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface ArticleQueryMapper {

    //List<PublishedArticleInfo> queryAllPublishedArticles(@Param("userId")String userId);

    List<ArticeClickInfo> queryPublishedDetails(@Param("userId") String userId, @Param("articleId") String articleId);

    PublishedArticleInfo queryPublishedById(@Param("userId") String userId, @Param("articleId") String articleId);

    //	int queryArticleByUrl(@Param("companyId")String companyId,@Param("originalUrl") String originalUrl);
    int queryArticleByUrl(@Param("originalUrl") String originalUrl);

    int updatePublishedArticle(Map<String, String> articleMap);

    public List<PublishedArticleInfo> selectAllWithLimit(@Param(value = "companyId") String companyId,
                                                         @Param(value = "articleState") List<String> articleState, @Param(value = "articelName") String articelName,
                                                         @Param(value = "articelAuthor") String articelAuthor, @Param(value = "special") String special, @Param(value = "channelId") String channelId);

    ArticleInfo queryArticleInfoByCompanyId(@Param("companyId") String companyId, @Param("originalUrl") String originalUrl);

    List<AdvertisementInfo> queryAdByCompanyId(@Param(value = "companyId") String companyId);

    /**
     * @param valueOf
     * @return
     */
    ArticleInfo queryModifiedEditUrlByArticleId(Integer valueOf);

    CompanyArticleInfo queryCompanyArticleById(@Param("publishId") String publishId, @Param("articleId") String articleId);

    /**
     * 根据文章主键集合查询文章对应的所有标签集合
     *
     * @param articelId
     * @return
     */
    List<ArticleLabelRelDTO> findArticleLabelRelByArticleIds(@Param(value = "articelId") List<String> articelId);

    List<ArticlePublishSend> noSendPublishArticle(HashMap<String, Object> paramMap);


}
