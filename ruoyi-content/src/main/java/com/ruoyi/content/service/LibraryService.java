package com.ruoyi.content.service;


import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.content.domain.BaseCode;
import com.ruoyi.content.domain.PageDTO;
import com.ruoyi.content.message.Message;

import java.util.HashMap;
import java.util.List;


/**
 * 文库处理业务层
 *
 * @author Ma.C
 */
public interface LibraryService {


    /**
     * 查询文库全部文章
     *
     * @param articleState
     * @param special
     * @param articelName
     * @return
     */
    public List<HashMap<String, Object>> queryLibrary(int startRow, int rows, String articelName, String special, String channel, String articleState) throws Exception;

    /**
     * 根据状态查询当前用户所有发布过的文章
     *
     * @param articelName
     * @param special
     * @param articleState
     * @return
     */
    public int countArticleInfoByState(String articelName, String special, String channel,
                                       String articleState);

    /**
     * 添加文章栏目关系
     *
     * @return
     */
    public Message addLibrary(String companyId, String branchId, String channelId, String articleInfoList);

    /**
     * 移除文章栏目关系
     *
     * @return
     */
    public Message removeArticleItem(String companyId, String branchId, String channelId, String articleInfoList);

    /**
     * 发布文章
     *
     * @param ids
     * @return
     */
    public Message changeSate(String ids, String eState, String sState, String publishId);

    /**
     * 删除文章
     *
     * @param id
     * @return
     */
    public Message delArticle(String id);

    /**
     * 文章排序
     *
     * @param id
     * @return
     */
    public Message updateLibrary(String id, String orderNo);


    /**
     * 根据栏目id查一二级栏目名称
     *
     * @param channelId
     * @return
     */
    public BaseCode queryBaseCodeByChannelId(String channelId);

    /**
     * 根据文章id查后台修改路径
     *
     * @return
     */
    public Message queryEditUrlByPublishId(String publishId);

    /**
     * 根据文章id查询文章是否有原始静态页面路径
     *
     * @param articleId
     * @return
     */
    public int checkCreateUser(String articleId, String originalUrl, String createUser);

    /**
     * 推送文章
     *
     * @return
     */
    public Message articleSend(String articleId, String publishId, String agentCode, String sendType, String partyId, Integer sendId);

    /**
     * 定时推送文章信息
     *
     * @param articleId
     * @param publishId
     * @param agentCode
     * @param sendType
     * @param partyId
     * @param sendTime
     * @return
     */
    public Message onTimeSend(String articleId, String publishId, String agentCode, String sendType, String partyId, String sendTime);


    public Message delLibraryHTML(String id);

    public int countArticleByParam(String articelName, String special, String channel, String articleState);

    public Message onkeyExChange(String from, String to, String companyId, String branchId);

    public TableDataInfo noSendPublishArticle(String articleId, int startRow, int rows, String publishId);

    public Message delOnTimeTask(String delId);


}
