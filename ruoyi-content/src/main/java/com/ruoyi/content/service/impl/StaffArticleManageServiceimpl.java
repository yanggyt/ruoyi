package com.ruoyi.content.service.impl;

import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.content.domain.*;
import com.ruoyi.content.exception.BusinessException;
import com.ruoyi.content.mapper.*;
import com.ruoyi.content.service.StaffArticleManageService;
import com.ruoyi.content.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

@Service
public class StaffArticleManageServiceimpl implements StaffArticleManageService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private UserInfoMapper userInfoMapper;
    @Autowired
    private ArticleInfoMapper articleInfoMapper;
    @Autowired
    private ArticlePublishTrackQueryMapper articlePublishTrackQueryMapper;
    @Autowired
    private ClickTrackInfoMapper clickTrackInfoMapper;
    @Autowired
    private ArticlePublishTrackMapper articlePublishTrackMapper;
    @Autowired
    private ClickTrackInfoQueryMapper clickTrackInfoQueryMapper;


    @Override
    public ClickTrackInfo articleSharingTrackInfo(String clickId) {
        return clickTrackInfoMapper.selectByPrimaryKey(clickId);
    }

    @Override
    public UserInfo queryPublishUserInfo(String publishUserId) {
        return userInfoMapper.selectByPrimaryKey(Integer.valueOf(publishUserId));
    }

    @Override
    public ArticleInfo queryModifiedViewUrl(String articleId) {
        return articleInfoMapper.selectByPrimaryKey(Integer.valueOf(articleId));
    }

    @Override
    public TableDataInfo querySalesmanByArticleId(String articleId, int startRow, int rows) {
        List<ArticlePublishTrack> list = articlePublishTrackQueryMapper.queryPublishUserInfo(articleId, startRow, rows);
        List<YwyForwardUserInfo> ywyList = new ArrayList<>();

        if (list.size() > 0) {
            for (ArticlePublishTrack apt : list) {
                YwyForwardUserInfo ywyForwardUserInfo = new YwyForwardUserInfo();
                String publishUserId = apt.getUserId();
                LOGGER.info("业务员publishUserId【{}】", publishUserId);
                //如果不包含后台默认管理员字段则查userInfo表
                if (!publishUserId.contains("cofms")) {
                    UserInfo userInfo = userInfoMapper.selectByPrimaryKey(Integer.valueOf(publishUserId));
                    ywyForwardUserInfo.setArticleId(articleId);
                    ywyForwardUserInfo.setCreateDate(apt.getCreateDate());
                    ywyForwardUserInfo.setHeadImgUrl(userInfo.getHeadImgUrl());
                    ywyForwardUserInfo.setUserId(publishUserId);
                    if (StringUtils.isBlank(apt.getCreateUser())) {
                        ywyForwardUserInfo.setCreateUser("");
                    } else {
                        ywyForwardUserInfo.setCreateUser(apt.getCreateUser());
                    }
                    String nickName = userInfo.getNickName();
                    try {
                        if (StringUtils.isBlank(nickName)) {
                            ywyForwardUserInfo.setNickName("匿名");
                        } else {
                            ywyForwardUserInfo.setNickName(new String(Base64.getDecoder().decode(nickName), "UTF-8"));
                        }

                    } catch (UnsupportedEncodingException e) {
                        throw new BusinessException("业务员昵称解码异常[{" + e.getMessage() + "}]");
                    }
                } else {
                    ywyForwardUserInfo.setNickName("后台管理员");
                    ywyForwardUserInfo.setArticleId(articleId);
                    ywyForwardUserInfo.setCreateDate(apt.getCreateDate());
                    ywyForwardUserInfo.setHeadImgUrl("/img/admin.png");
                    ywyForwardUserInfo.setUserId(publishUserId);
                }
                ywyList.add(ywyForwardUserInfo);
            }
        }

        LOGGER.info("业务员列表ywyList【{}】", ywyList.size());
        ArticlePublishTrackExample example = new ArticlePublishTrackExample();
        ArticlePublishTrackExample.Criteria criteria = example.createCriteria();
        criteria.andArticleIdEqualTo(articleId);
        int count = articlePublishTrackMapper.countByExample(example);
        TableDataInfo rspData = new TableDataInfo();
        rspData.setCode(0);
        rspData.setRows(ywyList);
        rspData.setTotal(count);
        return rspData;
    }

    @Override
    public TableDataInfo queryClickInfoByUserId(String userId, String articleId, int startRow, int rows) {

        List<ClickTrackInfo> list = clickTrackInfoQueryMapper.queryClickInfoByUserId(userId, articleId, startRow, rows);
        List<YwyPublishInfo> YwyPublishInfolList = new ArrayList<>();
        if (list.size() > 0) {
            for (ClickTrackInfo ct : list) {
                YwyPublishInfo ywyPublishInfo = new YwyPublishInfo();

                ywyPublishInfo.setClickDate(ct.getCreateDate());
                ywyPublishInfo.setClickId(ct.getClickId());
                ywyPublishInfo.setClickTime(ct.getCreateTime());
                ywyPublishInfo.setClientHeadImgurl(ct.getClickUserHeadimgurl());
                ywyPublishInfo.setShareState(ct.getShareState());
                ywyPublishInfo.setWatchTime(ct.getWatchTime());
                ywyPublishInfo.setOpenId(ct.getClickOpenId());
                try {
                    ywyPublishInfo.setClientNickName(new String(Base64.getDecoder().decode(ct.getClickUserNickname()), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new BusinessException("业务员昵称解码异常[{" + e.getMessage() + "}]");
                }
                YwyPublishInfolList.add(ywyPublishInfo);
            }
        }

        LOGGER.info("业务员【{}】,的客户列表YwyPublishInfolList【{}】", userId, JsonUtil.objectToJackson(YwyPublishInfolList));

        ClickTrackInfoExample example = new ClickTrackInfoExample();
        ClickTrackInfoExample.Criteria criteria = example.createCriteria();
        criteria.andPublishUserIdEqualTo(userId);
        criteria.andArticleIdEqualTo(articleId);
        int count = clickTrackInfoMapper.countByExample(example);
        TableDataInfo pageDTO = new TableDataInfo();
        pageDTO.setCode(0);
        pageDTO.setRows(YwyPublishInfolList);
        pageDTO.setTotal(count);
        return pageDTO;
    }
}
