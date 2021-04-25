package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.ArticleChannel;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface ArticleChannelQueryMapper {

    /**
     * 查询文库表全部信息
     *
     * @param companyId
     * @param startRow
     * @param rows
     * @return
     */
    public List<ArticleChannel> selectAllWithLimit(@Param(value = "companyId") String companyId, @Param(value = "branchId") String branchId,
                                                   @Param(value = "articleState") List<String> articleState, @Param(value = "startRow") int startRow,
                                                   @Param(value = "rows") int rows, @Param(value = "articelName") String articelName,
                                                   @Param(value = "special") String special, @Param(value = "channel") String channel);

    /**
     * 根据文章id查文章修改路径
     *
     * @param articleId
     * @return
     */
    String queryModifiedEditUrlByArticleId(String articleId);

    /**
     * 根据栏目查文章ID
     *
     * @param channel
     * @return
     */
    List<Integer> queryArticleIdByChannel(@Param(value = "channel") String channel);

    /**
     * 根据文章id查文库关系
     *
     * @return
     */
    public List<ArticleChannel> queryByPublishId(String publishId);

    /*
     * 分页查询文库表信息
     */
    public List<HashMap<String, Object>> selectByLimit(HashMap<String, Object> map);

    public List<HashMap<String, Object>> selectCountByParam(HashMap<String, Object> parMap);

    /**
     * 批量收录文章
     *
     * @param batchInsertArticle
     * @return
     */
    public int batchInsertArticeChannel(@Param(value = "batchInsertArticle") List<ArticleChannel> batchInsertArticle);

    public List<HashMap<String, Object>> selectByPublishList(@Param(value = "publishList") List<String> publishList);


}