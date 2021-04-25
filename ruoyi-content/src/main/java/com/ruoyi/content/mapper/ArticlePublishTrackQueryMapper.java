package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.ArticlePublishTrack;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticlePublishTrackQueryMapper {

    /**
     * @return
     */
    ArticlePublishTrack queryEditUrlByArticleId(String articleId, String puhlishId);

    /**
     * 根据文章id查询发布该文章的所有业务员信息
     *
     * @param articleId
     * @return
     */
    List<ArticlePublishTrack> queryPublishUserInfo(@Param(value = "articleId") String articleId,
                                                   @Param(value = "startRow") int startRow, @Param(value = "rows") int rows);

    List<ArticlePublishTrack> queryArticleIdByUserId(@Param(value = "userId") List<String> userId,
                                                     @Param(value = "startRow") int startRow, @Param(value = "rows") int rows);

    int countArticleIdByUserId(@Param(value = "userId") List<String> userId);

    /**
     * 根据publishid查询发布表记录
     *
     * @param publishId
     * @return
     */
    ArticlePublishTrack queryByPublishId(@Param(value = "publishId") String publishId);

    /**
     * 查询文章列表的文章信息
     *
     * @param articleId
     * @return
     */
    List<ArticlePublishTrack> queryArticleinfo(@Param(value = "articleId") String articleId);
}
