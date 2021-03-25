package com.ruoyi.content.service;

import com.ruoyi.content.domain.ArticleInfo;
import com.ruoyi.content.domain.ClickTrackInfo;
import com.ruoyi.content.domain.PageDTO;
import com.ruoyi.content.domain.UserInfo;

public interface StaffArticleManageService {


    public ClickTrackInfo articleSharingTrackInfo(String clickId);

    /**
     * 根据userId 查询对应user信息
     *
     * @param publishUserId
     * @return
     */
    public UserInfo queryPublishUserInfo(String publishUserId);

    /**
     * 根据文章id查view路径
     *
     * @param articleId
     * @return
     */
    public ArticleInfo queryModifiedViewUrl(String articleId);

    /**
     * 根据文章id去查publish表，找出该文章对应所有业务员
     *
     * @param articleId
     * @param startRow
     * @param rows
     * @return
     */
    public PageDTO querySalesmanByArticleId(String articleId, int startRow, int rows);

    /**
     * 根据用户id查询浏览表
     *
     * @param userId
     * @param startRow
     * @param rows
     * @return
     */
    public PageDTO queryClickInfoByUserId(String userId, String articleId, int startRow, int rows);

}
