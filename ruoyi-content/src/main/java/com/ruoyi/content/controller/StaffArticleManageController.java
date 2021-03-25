package com.ruoyi.content.controller;

import com.ruoyi.content.domain.ClickTrackInfo;
import com.ruoyi.content.domain.ClickUserInfo;
import com.ruoyi.content.domain.PageDTO;
import com.ruoyi.content.domain.UserInfo;
import com.ruoyi.content.exception.BusinessException;
import com.ruoyi.content.mapper.ArticlePublishTrackMapper;
import com.ruoyi.content.mapper.CmsSysUserExMapper;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.StaffArticleManageService;
import com.ruoyi.content.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.UnsupportedEncodingException;
import java.util.*;

@Controller
@RequestMapping("/article")
public class StaffArticleManageController {
    private final static Logger logger = LoggerFactory.getLogger(ArticleManageController.class);

    @Autowired
    private StaffArticleManageService staffArticleManageService;
    @Autowired
    private CmsSysUserExMapper cmsSysUserExMapper;
    @Autowired
    private ArticlePublishTrackMapper articlePublishTrackMapper;

    /**
     * 查询该文章有多少业务员发布过
     *
     * @param request
     * @return
     */
    @RequestMapping("/articleSharingTrackList")
    @ResponseBody
    public PageDTO articleSharingTrack(HttpServletRequest request) {

        logger.info("查询文章分享阅读轨迹的控制层方法开始！");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        PageDTO pageDTO = new PageDTO();
        try {
            String rowsVal = request.getParameter("rows");
            String page = request.getParameter("page");
            int rows = Integer.parseInt(rowsVal);
            int startRow = rows * (Integer.parseInt(page) - 1);
            String articleId = request.getParameter("articleId");
            // 1.根据文章id去查publish表，找出该文章对应所有业务员
            pageDTO = staffArticleManageService.querySalesmanByArticleId(articleId, startRow, rows);
            pageDTO.setPage(Integer.parseInt(page));

            return pageDTO;
        } catch (Exception e) {
            logger.info("系统异常！", e);
            return pageDTO;
        }

    }

    /**
     * 查询该文章
     *
     * @param request
     * @return
     */
    @RequestMapping("/publishInfoList")
    @ResponseBody
    public PageDTO publishInfoList(HttpServletRequest request) {


        Thread.currentThread().setName(UUID.randomUUID().toString());
        PageDTO pageDTO = new PageDTO();
        try {
            String rowsVal = request.getParameter("rows");
            String page = request.getParameter("page");
            int rows = Integer.parseInt(rowsVal);
            int startRow = rows * (Integer.parseInt(page) - 1);
            String userId = request.getParameter("userId");
            String articleId = request.getParameter("articleId");

            logger.info("查询该业务员userId[{}]的文章articleId[{}]发布情况开始！", userId, articleId);


            // 1.根据文章id去查publish表，找出该文章对应所有业务员
            pageDTO = staffArticleManageService.queryClickInfoByUserId(userId, articleId, startRow, rows);
            pageDTO.setPage(Integer.parseInt(page));

            return pageDTO;
        } catch (Exception e) {
            logger.info("系统异常！", e);
            return pageDTO;
        }

    }

    /**
     * 文章分享轨迹信息查询
     *
     * @param request
     * @return
     */
    @RequestMapping("/articleSharingTrackInfo")
    @ResponseBody
    public Message articleSharingTrackInfo(HttpServletRequest request) {
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        Map<String, Object> returnMap = new HashMap<String, Object>();
        List<ClickUserInfo> clickUserInfos = new ArrayList<ClickUserInfo>();
        ClickTrackInfo clickTrackInfo = new ClickTrackInfo();
        logger.info("查询用户查看分享文章轨迹信息的控制层方法开始！");
        String clickId = request.getParameter("clickId");
        if (StringUtils.isBlank(clickId)) {
            returnMap.put("ClickTrackInfo", clickTrackInfo);
        } else {
            clickTrackInfo = staffArticleManageService.articleSharingTrackInfo(clickId);
            //发布人id
            UserInfo userInfo = new UserInfo();
            String publishUserId = clickTrackInfo.getPublishUserId();
            if (!publishUserId.contains("cofms")) {
                // UserInfoMapper
                userInfo = staffArticleManageService.queryPublishUserInfo(publishUserId);
                try {
                    userInfo.setNickName(new String(Base64.getDecoder().decode(userInfo.getNickName()), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new BusinessException("用户昵称解码异常[{" + e.getMessage() + "}]");
                }
                logger.info("转换后的用户昵称为:" + userInfo.getNickName());
            } else {
                userInfo.setNickName("后台管理员");
                userInfo.setHeadImgUrl("/static/common/image/logo.png");
                userInfo.setOpenid("");
            }
            returnMap.put("userInfo", userInfo);
            String clickUserInfo = clickTrackInfo.getClickUserInfo();
            if (StringUtils.isNotBlank(clickUserInfo)) {
                clickUserInfos = JsonUtil.JsonToCollectionType(clickUserInfo, List.class, ClickUserInfo.class);

                for (ClickUserInfo clickUserInfo2 : clickUserInfos) {
                    try {
                        clickUserInfo2.setNickName(
                                new String(Base64.getDecoder().decode(clickUserInfo2.getNickName()), "UTF-8"));
                    } catch (UnsupportedEncodingException e) {
                        throw new BusinessException("用户昵称解码异常[{" + e.getMessage() + "}]");
                    }
                }
            }
            returnMap.put("ClickUserInfoList", clickUserInfos);

            String clickUserNickname = clickTrackInfo.getClickUserNickname();
            if (StringUtils.isNotBlank(clickUserNickname)) {
                try {
                    clickTrackInfo.setClickUserNickname(
                            new String(Base64.getDecoder().decode(clickUserNickname), "UTF-8"));
                } catch (UnsupportedEncodingException e) {
                    throw new BusinessException("用户昵称解码异常[{" + e.getMessage() + "}]");
                }
            }
            returnMap.put("ClickTrackInfo", clickTrackInfo);
            msg.setInfo("获取浏览详情查询成功");
            msg.setResult(true);
            msg.setObject(returnMap);
        }
        logger.info("查询用户查看分享文章轨迹信息的控制层方法结束！");
        return msg;
    }

//    // 复制链路
//    @RequestMapping("/copyShareUrl")
//    @ResponseBody
//    public ModelAndView copyShareUrl(ModelAndView modelAndView, String articleId, String publishId) {
//        CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
//        String email = userInfoDTO.getEmail();
//        String companyId = userInfoDTO.getCompanyId();
//        CmsSysUser cmsSysUser = cmsSysUserExMapper.queryLoginInfoByEmail(email);
//        String userId = cmsSysUser.getUserId();
////		ArticleInfo articleInfo = staffArticleManageService.queryModifiedViewUrl(articleId);
//        ArticlePublishTrackExample example = new ArticlePublishTrackExample();
//        ArticlePublishTrackExample.Criteria criteria = example.createCriteria();
//        criteria.andPublishIdEqualTo(publishId);
//        criteria.andArticleIdEqualTo(String.valueOf(articleId));
//        List<ArticlePublishTrack> aplist = articlePublishTrackMapper.selectByExample(example);
//        // http://hx-cdn.oss-cn-szfinance-a.aliyuncs.com/contentMKT/console/template/html/view/20180525/272b20180525074506941010.html
////		String viewUrl = articleInfo.getModifiedViewUrl();
//        String viewUrl = aplist.get(0).getPublishViewUrl();
//        String[] viewUrls = viewUrl.split("/");
//        // 272b20180525074506941010.html
//        String url = viewUrls[viewUrls.length - 1];
//        String[] urls = url.split("b");
//        // 20180525074506941010.html
//        String url1 = urls[urls.length - 1];
//        String versionNumber = url1.substring(0, 20);
//        logger.info("版本号versionNumber[{}]", versionNumber);
//
//        modelAndView.setViewName("/micro/article/copyShareUrl");
//        modelAndView.addObject("articleId", articleId);
//        modelAndView.addObject("versionNumber", versionNumber);
//        modelAndView.addObject("userId", userId);
//        modelAndView.addObject("companyId", companyId);
//        return modelAndView;
//
//    }


}
