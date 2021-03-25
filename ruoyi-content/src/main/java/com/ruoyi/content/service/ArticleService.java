package com.ruoyi.content.service;

import com.ruoyi.content.domain.ArticleInfo;
import com.ruoyi.content.domain.PageDTO;
import com.ruoyi.content.domain.PublishedArticleInfo;
import com.ruoyi.content.message.Message;

import java.util.List;
import java.util.Map;

/**
 * 文章处理业务层
 *
 * @author root
 */
public interface ArticleService {

    /**
     * 根据文章连接获取文章内容
     *
     * @return
     */
    Message getArticleContentByUrl(String originalUrl, String createUser, String author, String ids,
                                   String isAuthorization, String isReserve, String automaticName, String introduction, String labelIds,
                                   String isJoinActive);

    /**
     * 个人编辑文章
     *
     * @param createUser
     * @param author
     * @param ids
     * @param isAuthorization
     * @param isReserve
     * @param automaticName
     * @param introduction
     * @param labelIds
     * @param isJoinActive
     * @param articleName
     * @param shareImgUrl
     * @param shareDes
     * @param articleContent
     * @return
     */
    public Message getArticleContent(String createUser, String author, String ids, String isAuthorization,
                                     String isReserve, String automaticName, String introduction, String labelIds, String isJoinActive,
                                     String articleName, String shareImgUrl, String shareDes, String articleContent);

    /**
     * 保存个人已经发布过的文章
     */
    Message savePulishedArticle(Map<String, Object> requestMap);

    /**
     * 更新文章信息
     *
     * @param articleInfo
     * @return
     * @throws Exception
     */
    Message articleUpByArticleId(ArticleInfo articleInfo) throws Exception;

    /**
     * 根据id查询文章信息
     *
     * @return
     */
    public ArticleInfo queryArticleByArticleId(String articleId);

    /**
     * 浏览详情查询
     */
    Message queryPublishedDetails(String userId, String articleId);

    /**
     * 根据id删除文章
     *
     * @param id
     * @return
     */
    Message delArticle(String id);

    /**
     * 查询当前用户发布的文章
     *
     * @return
     */
    public List<PublishedArticleInfo> queryArticle(int startRow, int rows, String articelName, String articelAuthor,
                                                   String special, String articelType, String articleState) throws Exception;

    /**
     * 根据状态查询当前用户所有发布过的文章
     *
     * @return
     */
    public int countArticleInfoByState(String articelName, String articelAuthor, String special, String channelId,
                                       String articleState);

    /**
     * 根据原始url爬取文章
     *
     * @param originalUrl
     * @param author
     * @param ids
     * @return
     */
    Message getArticleContent(String originalUrl, String author, String ids);

    /**
     * 生成静态页面路径
     *
     * @param articleId
     * @param shareTitle
     * @param shareImgUrl
     * @param shareDes
     * @param articleContent
     * @param ids
     * @return
     */
    Message getHtmlUrl(Integer articleId, String shareTitle, String shareImgUrl, String shareDes, String articleContent,
                       String ids, String publishId, String companyId, String isAuthorization, String isReserve,
                       String automaticName, String introduction, String isJoinActives);

    /**
     * 检查是否有工号
     *
     * @param articleId
     * @param articleName
     * @param listPicUrl
     * @param ids
     * @param publishId
     * @param companyId
     * @param jobNumber
     * @param userId
     * @param createUser
     * @return
     */
    void checkJobNumber(Integer articleId, String articleName, String listPicUrl, String ids, String publishId,
                        String companyId, String jobNumber, String userId, String createUser, String editUrl, String viewUrl,
                        String versionNumber);

    /**
     * 查询分公司文章列表
     *
     * @param companyId
     * @param branchId
     * @param startRow
     * @param rows
     * @return
     */
    PageDTO queryBranchIdArticle(String companyId, String branchId, Integer startRow, Integer rows);

    /**
     * 文章列表的删除功能
     *
     * @param id
     * @return
     */
    Message delArticleInfo(String id);

    Message articleUrlCopy(String articleId, String userId, String publishId1);

    Message delHTML(String ids);

    /**
     * 2020-04-16 新需求 编辑按钮 逻辑变为替换原HTML
     *
     * @param articleId
     * @param shareTitle
     * @param shareImgUrl
     * @param shareDes
     * @param articleContent
     * @param ids
     * @param publishId
     * @param companyId
     * @param isAuthorization
     * @param isReserve
     * @param automaticName
     * @param introduction
     * @param isJoinActive
     * @return
     */
    Message getHtmlUrl2(Integer articleId, String shareTitle, String shareImgUrl, String shareDes,
                        String articleContent, String ids, String publishId, String companyId, String isAuthorization,
                        String isReserve, String automaticName, String introduction, String isJoinActive);

}
