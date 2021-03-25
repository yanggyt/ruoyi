package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.ArticeClickInfoExDTO;
import com.ruoyi.content.domain.ArticleinfoExDTO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticeClickInfoExMapper {
    //用户浏览分享信息（List）
    public List<ArticeClickInfoExDTO> queryClerkArticleInfo(@Param(value = "companyId") String companyId, @Param(value = "articleId") String articleId,
                                                            @Param(value = "startRow") int startRow, @Param(value = "rows") int rows);

    ///用户浏览分享信息总数
    public int clerkArticleCount(@Param(value = "companyId") String companyId, @Param(value = "articleId") String articleId);


    public List<ArticleinfoExDTO> queryArticleInfoList(@Param(value = "companyId") String companyId,
                                                       @Param(value = "articelName") String articelName, @Param(value = "articelAuthor") String articelAuthor,
                                                       @Param(value = "channelId") String channelId, @Param(value = "special") String special,
                                                       @Param(value = "startRow") int startRow, @Param(value = "rows") int rows);

    public int articleInfoCount(@Param(value = "companyId") String companyId,
                                @Param(value = "articelName") String articelName, @Param(value = "articelAuthor") String articelAuthor,
                                @Param(value = "channelId") String channelId, @Param(value = "special") String special);

    //查询业务员文章信息
    public ArticleinfoExDTO selClerkArticleContent(@Param(value = "companyId") String companyId,
                                                   @Param(value = "articleId") String articleId);
}
