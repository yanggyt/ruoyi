package com.ruoyi.content.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.content.constants.PropertiesConstants;
import com.ruoyi.content.domain.*;
import com.ruoyi.content.exception.BusinessException;
import com.ruoyi.content.exception.ParameterException;
import com.ruoyi.content.mapper.*;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.redis.RedisManager;
import com.ruoyi.content.service.ArticleService;
import com.ruoyi.content.utils.*;
import freemarker.template.Configuration;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.UnsupportedEncodingException;
import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ArticleServiceImpl.class);

    @Autowired
    private ArticleInfoMapper articleMapper;
    @Autowired
    private ArticleQueryMapper articleQueryMapper;
    @Autowired
    private ArticlePublishTrackMapper articlePublishTrackMapper;
    @Autowired
    private ArticleChannelQueryMapper articleChannelQueryMapper;
    @Autowired
    private CmsSysUserExMapper cmsSysUserExMapper;
    //	@Autowired
//	private ContentRedisManager redisManager;
    @Autowired
    private RedisManager rManager;
    @Autowired
    private CmsSysUserMapper cmsSysUserMapper;
    @Autowired
    private ArticlePublishTrackQueryMapper articlePublishTrackQueryMapper;
    @Autowired
    private ArticleLabelServiceImpl articleLabelServiceImpl;
    @Autowired
    private ArticleChannelMapper articleChannelMapper;

    /*
     * 根据文章url爬文章,创建文章
     */
    @SuppressWarnings("unchecked")
    @Override
    public Message getArticleContentByUrl(String originalUrl, String createUser, String author, String ids,
                                          String isAuthorization, String isReserve, String automaticName, String introduction, String labelIds,
                                          String isJoinActive) {
        Message msg = new Message();
        Map<String, String> resultMap = new HashMap<String, String>();
        /*************** 根据链接爬文章***********start ****************************/
        LOGGER.info(
                "根据文章链接获取文章内容,参数originalUrl[{}],createUser[{}],author[{}],ids[{}],isAuthorization[{}],isReserve[{}],automaticName[{}],introduction[{}],isJoinActive[{}]",
                originalUrl, createUser, author, ids, isAuthorization, isReserve, automaticName, introduction,
                isJoinActive);
        String companyId = "1";// 渠道id
        String email = "13152783264";
        // 根据登录人查询登录信息（shiro里有缓存数据不是最新）
        CmsSysUser cmsSysUser = cmsSysUserExMapper.queryLoginInfoByEmail(email);
        // 此时后台管理员
        String userId = cmsSysUser.getUserId();
        // 判断该url是否被爬取过
        ArticleInfo articleInfo = articleQueryMapper.queryArticleInfoByCompanyId(companyId, originalUrl);
        LOGGER.info("查询该文章是否存在[{}]", JsonUtil.objectToJackson(articleInfo));

        if (articleInfo != null) {
            // 数据库里有这篇文章
            String aritcleEditUrl = articleInfo.getArticleEditUrl();
            String modifiedEditUrl = articleInfo.getModifiedEditUrl();
            Integer articleId = articleInfo.getArticleId();
            // 存文章和标签的关系
            articleLabelServiceImpl.addlabel(String.valueOf(articleId), labelIds);
            LOGGER.info("aritcleEditUrl[{}],modifiedEditUrl[{}],articleId[{}]", aritcleEditUrl, modifiedEditUrl,
                    articleId);
            String publishId = userId + "a" + articleId;
            // modifiedEditUrl有值说明是后台导入过该文章返回已经发布过信息
            if (modifiedEditUrl != null && !"".equals(modifiedEditUrl)) {

                ArticlePublishTrackExample example = new ArticlePublishTrackExample();
                ArticlePublishTrackExample.Criteria criteria = example.createCriteria();
                criteria.andArticleIdEqualTo(String.valueOf(articleId));
                criteria.andPublishIdEqualTo(publishId);
                List<ArticlePublishTrack> list = articlePublishTrackMapper.selectByExample(example);
                if (list.size() > 0) {
                    // 该管理员发布过这篇文章
                    LOGGER.info("该文章后台已经发布过");
                    msg.setInfo("亲，该文章您已经发布过,请到文章列表中进行编辑~");
                    msg.setResult(false);
                } else {

                    // 该管理员没有导入过该文章，重新爬取文章;是否存在工号，有的存publish表
                    if (StringUtils.isNotBlank(cmsSysUser.getJobNumber())) {
                        msg = this.getArticleContent(originalUrl, author, "");
                        resultMap = (Map<String, String>) msg.getObject();
                        String listPicUrl = resultMap.get("listPicUrl");
                        String articleName = resultMap.get("articleName");
                        String shareTitle = resultMap.get("shareTitle");
                        String shareImgUrl = resultMap.get("shareImgUrl");
                        String shareDes = resultMap.get("shareDes");
                        String articleContent = resultMap.get("articleContent");
                        msg = this.getHtmlUrl(articleId, shareTitle, shareImgUrl, shareDes, articleContent, "",
                                publishId, companyId, isAuthorization, isReserve, automaticName, introduction,
                                isJoinActive);
                        resultMap = (Map<String, String>) msg.getObject();
                        String editUrl = resultMap.get("articleEditUrl");
                        String viewUrl = resultMap.get("articleViewUrl");

                        String versionNumber = (String) resultMap.get("versionNumber");
                        LOGGER.info("获取到的版本号3为{}", versionNumber);

                        String jobNumber = cmsSysUser.getJobNumber();
                        this.checkJobNumber(articleId, articleName, listPicUrl, ids, publishId, companyId, jobNumber,
                                userId, cmsSysUser.getName(), editUrl, viewUrl, versionNumber);
                        if (articleInfo.getArticleState().equals("1")) {
                            // 如果文章已被删除重新导入 更新article_info表文章状态
                            String updateDate = DateUtils.getDate();
                            String updateTime = DateUtils.getTimeNow();
                            articleInfo.setArticleState("0");
                            articleInfo.setUpdateDate(updateDate);
                            articleInfo.setUpdateTime(updateTime);
                            int cont = articleMapper.updateByPrimaryKeySelective(articleInfo);
                            if (cont == 0) {
                                LOGGER.info("更新文章失败");
                                throw new BusinessException("系统繁忙,请稍后再试");
                            }
                        }
                        resultMap.put("articleEditUrl", editUrl);
                        msg.setResult(true);
                        msg.setObject(resultMap);
                    }
                }
                return msg;
            }
            // aritcleEditUrl有值说明是手机端导入 ，此时重新导入更新modifiedEditUrl
            if (aritcleEditUrl != null && !"".equals(aritcleEditUrl)) {
                msg = this.getArticleContent(originalUrl, author, ids);
                resultMap = (Map<String, String>) msg.getObject();
                String listPicUrl = resultMap.get("listPicUrl");
                String articleName = resultMap.get("articleName");
                String shareTitle = resultMap.get("shareTitle");
                String shareImgUrl = resultMap.get("shareImgUrl");
                String shareDes = resultMap.get("shareDes");
                String articleContent = resultMap.get("articleContent");
                msg = this.getHtmlUrl(articleId, shareTitle, shareImgUrl, shareDes, articleContent, ids, publishId,
                        companyId, isAuthorization, isReserve, automaticName, introduction, isJoinActive);
                resultMap = (Map<String, String>) msg.getObject();
                String EditUrl = resultMap.get("articleEditUrl");
                String ViewUrl = resultMap.get("articleViewUrl");
                String versionNumber = (String) resultMap.get("versionNumber");

                articleInfo.setModifiedEditUrl(EditUrl);
                articleInfo.setModifiedViewUrl(ViewUrl);
                int count = articleMapper.updateByPrimaryKey(articleInfo);
                if (count == 0) {
                    LOGGER.info("更新modifiedEditUrl失败");
                    msg.setInfo("系统繁忙,请稍后再试");
                    msg.setResult(false);
                }
                resultMap.put("articleEditUrl", EditUrl);
                String jobNumber = cmsSysUser.getJobNumber();
                LOGGER.info("获取到的versionNumber1为{}", versionNumber);
                LOGGER.info("当前用户工号为jobNumber【{}】", jobNumber);
                this.checkJobNumber(articleId, articleName, listPicUrl, ids, publishId, companyId, jobNumber, userId,
                        cmsSysUser.getName() + "", EditUrl, ViewUrl, versionNumber);
                msg.setInfo("成功导入文章!");
                msg.setResult(true);
                msg.setObject(resultMap);
                return msg;
            }

        } else {
            // 如果文章没有导入过重新导入 存article_info表，如果这个管理员有工号同时存publish表
            msg = this.getArticleContent(originalUrl, author, ids);
            resultMap = (Map<String, String>) msg.getObject();
            String listPicUrl = resultMap.get("listPicUrl");
            String articleName = resultMap.get("articleName");
            String shareTitle = resultMap.get("shareTitle");
            String shareImgUrl = resultMap.get("shareImgUrl");
            String shareDes = resultMap.get("shareDes");
            String articleContent = resultMap.get("articleContent");
            String createDate = DateUtils.getDate();
            String createTime = DateUtils.getTimeNow();
            LOGGER.info(
                    "导入文章相关信息：【listPicUrl:{},articleName:{},shareTitle:{},shareImgUrl:{},shareDes:{},articleContent:{},createDate:{},createTime:{}】",
                    new Object[]{listPicUrl, articleName, shareTitle, shareImgUrl, shareDes, articleContent,
                            createDate, createTime});

            ArticleInfo article = new ArticleInfo();
            article.setArticleAuthor(cmsSysUser.getName() + "");
            article.setArticleName(articleName);
            article.setOriginalUrl(originalUrl);
            article.setShareImgUrl(shareImgUrl);
            article.setShareTitle(shareTitle);
            article.setShareDes(shareDes);
            article.setListPicUrl(listPicUrl);
            article.setCreateUser(createUser);
            article.setCompanyId(companyId);
            article.setCreateDate(createDate);
            article.setCreateTime(createTime);

            int cont = articleMapper.insertSelective(article);
            if (cont == 0) {
                LOGGER.info("插入新文章失败");
                throw new BusinessException("系统繁忙,请稍后再试");
            }

            // 根据文章原始url和创建公司查该文章
            ArticleInfo arInfo = articleQueryMapper.queryArticleInfoByCompanyId(companyId, originalUrl);
            // 文章id
            Integer articleId = arInfo.getArticleId();
            // 文章的发布id
            String publishId = userId + "a" + articleId;

            msg = this.getHtmlUrl(articleId, shareTitle, shareImgUrl, shareDes, articleContent, ids, publishId,
                    companyId, isAuthorization, isReserve, automaticName, introduction, isJoinActive);
            Map<String, String> resultMap1 = new HashMap<String, String>();
            resultMap1 = (Map<String, String>) msg.getObject();
            String modifiedEditUrl = resultMap1.get("articleEditUrl");
            String modifiedViewUrl = resultMap1.get("articleViewUrl");
            String versionNumber = (String) resultMap1.get("versionNumber");

            resultMap.put("articleEditUrl", modifiedEditUrl);
            resultMap.put("articleViewUrl", modifiedViewUrl);
            // article_info 中modified两个字段自判断是否后台导入过，第一次存值后不再更新
            arInfo.setModifiedEditUrl(modifiedEditUrl);
            arInfo.setModifiedViewUrl(modifiedViewUrl);
            LOGGER.info("存放静态页面路径modifiedEditUrl[{}],modifiedViewUrl[{}]", modifiedEditUrl, modifiedViewUrl);
            int count1 = articleMapper.updateByPrimaryKeySelective(arInfo);
            if (count1 == 0) {
                LOGGER.info("原始静态页面路径入库失败");
                throw new BusinessException("系统繁忙,请稍后再试");
            }
            // 后台管理员工号
            String jobNumber = cmsSysUser.getJobNumber();
            LOGGER.info("当前用户工号为jobNumber【{}】", jobNumber);
            LOGGER.info("获取到的versionNumber2为{}", versionNumber);
            // 存文章和标签的关系
            articleLabelServiceImpl.addlabel(String.valueOf(articleId), labelIds);
            this.checkJobNumber(articleId, articleName, listPicUrl, ids, publishId, companyId, jobNumber, userId,
                    cmsSysUser.getName() + "", modifiedEditUrl, modifiedViewUrl, versionNumber);
            msg.setInfo("成功导入文章!");
            msg.setResult(true);
            msg.setObject(resultMap);
        }
        return msg;
    }

    /**
     * 创建，二次编辑并发表
     */
    @SuppressWarnings("unchecked")
    @Override
    @Transactional
    public Message savePulishedArticle(Map<String, Object> requestMap) {
        // 更新已经发布过的文章
        Message msg = new Message();
        Map<String, String> resultMap = new HashMap<String, String>();
        LOGGER.info("准备更新文章数据");
        ArticleInfo articleInfo = (ArticleInfo) requestMap.get("article");
        String articleContent = (String) requestMap.get("articleContent");
        Integer articleId = articleInfo.getArticleId();
        String shareTitle = articleInfo.getShareTitle();
        String shareImgUrl = articleInfo.getShareImgUrl();
        String shareDes = articleInfo.getShareDes();
        String adId = (String) requestMap.get("adId");
        String companyId = (String) requestMap.get("companyId");
        String publishId = (String) requestMap.get("publishId");
        String isAuthorization = (String) requestMap.get("isAuthorization");
        String isReserve = (String) requestMap.get("isReserve");
        String isJoinActive = (String) requestMap.get("isJoinActive");
        String automaticName = (String) requestMap.get("automaticName");
        String introduction = (String) requestMap.get("introduction");

        LOGGER.info(
                "获取静态页面参数adId【{}】,companyId【{}】,publishId【{}】,isAuthorization【{}】,isReserve【{}】,automaticName【{}】,introduction【{}】,isJoinActive【{}】",
                adId, companyId, publishId, isAuthorization, isReserve, automaticName, introduction, isJoinActive);
        String updateDate = DateUtils.getDate();
        String updateTime = DateUtils.getTimeNow();
        articleInfo.setUpdateDate(updateDate);
        articleInfo.setUpdateTime(updateTime);

        // msg = this.getHtmlUrl(articleId, shareTitle, shareImgUrl, shareDes,
        // articleContent, adId, publishId, companyId,
        // isAuthorization, isReserve,automaticName,introduction, isJoinActive);
        msg = this.getHtmlUrl2(articleId, shareTitle, shareImgUrl, shareDes, articleContent, adId, publishId, companyId,
                isAuthorization, isReserve, automaticName, introduction, isJoinActive);
        resultMap = (Map<String, String>) msg.getObject();
        String modifiedEditUrl = resultMap.get("articleEditUrl");
        String modifiedViewUrl = resultMap.get("articleViewUrl");
        String versionNumber = (String) resultMap.get("versionNumber");
        // articleInfo.setModifiedEditUrl(modifiedEditUrl);
        // articleInfo.setModifiedViewUrl(modifiedViewUrl);
        // // 更新数据
        int count = articleMapper.updateByPrimaryKeySelective(articleInfo);
        if (count == 0) {
            LOGGER.info("根据链接发布文章,文章更新失败");
            throw new BusinessException("系统繁忙,请稍后再试");
        }

        // 根据publishId 去表里查 如果没数据不用更新
        ArticlePublishTrack apt = articlePublishTrackMapper.selectByPrimaryKey(publishId);
        LOGGER.info("根据publishId查询到信息apt[{}]", JsonUtil.objectToJackson(apt));
        if (apt != null) {
            ArticlePublishTrack articlePublishTrack = new ArticlePublishTrack();
            articlePublishTrack.setPublishId(publishId);
            articlePublishTrack.setUpdateDate(updateDate);
            articlePublishTrack.setUpdateTime(updateTime);
            articlePublishTrack.setAdId(adId);
            // articlePublishTrack.setPublishEditUrl(modifiedEditUrl);
            articlePublishTrack.setPublishViewUrl(modifiedViewUrl);
            articlePublishTrack.setBackgroundEditUrl(modifiedEditUrl);
            articlePublishTrack.setArticleVersion(versionNumber);
            int count1 = articlePublishTrackMapper.updateByPrimaryKeySelective(articlePublishTrack);
            LOGGER.info("更新publish表:" + JsonUtil.objectToJackson(articlePublishTrack) + "---返回标识：" + count1);
            if (count1 == 0) {
                LOGGER.info("根据链接发布文章,文章更新失败");
                throw new BusinessException("系统繁忙,请稍后再试");
            }
            Long flag = rManager.delete("company_articleInfo_Id" + articleInfo.getArticleId());
            Long flag1 = rManager.delete("company_articleInfo_" + publishId);
            // 清除文章列表缓存 使更新的文章置顶
            ArticleChannelExample ex = new ArticleChannelExample();
            ex.createCriteria().andArticleIdEqualTo(Integer.parseInt(apt.getArticleId()));
            List<ArticleChannel> channelList = articleChannelMapper.selectByExample(ex);
            for (ArticleChannel channel : channelList) {
                String key = "article_channel_" + channel.getSpecial() + "_" + channel.getChannel();
                LOGGER.info("清除redis中key：" + key);
                rManager.delete(key);
            }
            if (flag != 0 && flag1 != 0) {
                LOGGER.info("再次编辑删除更新文章redis信息成功！");
            }
            // 添加文库成功后，redis更新
            CompanyArticleInfo companyArticleInfo = articleQueryMapper.queryCompanyArticleById(publishId,
                    String.valueOf(articleId));
            String value = JsonUtil.objectToJackson(companyArticleInfo);
            Map<String, String> hMap = JsonUtil.jackson2Map(value);
            rManager.hMSet("company_articleInfo_" + publishId, hMap, 0L);
            LOGGER.info("再次编辑redis存放key[{}]的文库信息成功", "company_articleInfo_" + publishId);

            rManager.hSet("company_articleInfo_Id" + companyArticleInfo.getArticleId(), "visitorCount",
                    companyArticleInfo.getVisitorCount(), 0L);
            rManager.hSet("company_articleInfo_Id" + companyArticleInfo.getArticleId(), "sharedCount",
                    companyArticleInfo.getSharedCount(), 0L);

        }

        msg.setResult(true);
        msg.setInfo("文章修改成功");
        msg.setObject(resultMap);
        LOGGER.info("文章更新完成");
        return msg;
    }

    /**
     * 获取文章浏览详情
     */
    @Override
    public Message queryPublishedDetails(String userId, String articleId) {
        Message msg = new Message();
        Map<String, Object> resultMap = new HashMap<String, Object>();
        if (StringUtils.isBlank(userId)) {
            LOGGER.info("获取浏览详情,缺少参数userId=[{}]", userId);
            msg.setInfo("获取浏览详情,缺少参数");
            msg.setResult(false);
            return msg;
        }
        if (StringUtils.isBlank(articleId)) {
            LOGGER.info("获取浏览详情,缺少参数articleId=[{}]", articleId);
            msg.setInfo("获取浏览详情,缺少参数");
            msg.setResult(false);
            return msg;
        }
        // 查询标题
        PublishedArticleInfo articleInfo = articleQueryMapper.queryPublishedById(userId, articleId);
        // 获取浏览详情
        List<ArticeClickInfo> detailDTOs = articleQueryMapper.queryPublishedDetails(userId, articleId);
        for (ArticeClickInfo articeClickInfo : detailDTOs) {

            String clickUserNickname = articeClickInfo.getClickUserNickname();
            if (StringUtils.isNotBlank(clickUserNickname)) {
                try {
                    clickUserNickname = new String(Base64.getDecoder().decode(clickUserNickname), "UTF-8");
                } catch (UnsupportedEncodingException e) {
                    throw new BusinessException("用户昵称解码异常[{" + e.getMessage() + "}]");
                }
                articeClickInfo.setClickUserNickname(clickUserNickname);
            }

        }
        msg.setInfo("获取浏览详情查询成功");
        msg.setResult(true);
        resultMap.put("articleInfo", articleInfo);
        resultMap.put("detaislList", detailDTOs);
        msg.setObject(resultMap);
        return msg;
    }

    /**
     * 根据文章id删除文章
     */
    @Override
    public Message delArticle(String id) {
        LOGGER.info("删除文章信息的业务层方法开始！");

//		String updateDate = DateUtil.currentDate();
//		String updateTime = DateUtil.currentTime();
        CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        String updateUser = userInfoDTO.getUserId();
        LOGGER.info("管理员updateUser【{}】删除文章信息的业务层方法中拿到的文章的publishId【{}】", updateUser, id);
        Message msg = new Message();
        if (StringUtils.isBlank(id)) {
            LOGGER.info("删除文章信息失败，缺少参数");
            throw new ParameterException("未查询到文章信息");
        }
        msg.setInfo("删除失败！");
        msg.setResult(false);
        Boolean deleteState = true;
        try {
            String[] arrId = id.split(",");
            for (String publishId : arrId) {
                if (StringUtils.isNotBlank(publishId)) {
                    // 先去查文库是否存在
                    List<ArticleChannel> articleChannelList = articleChannelQueryMapper.queryByPublishId(publishId);
                    LOGGER.info("在文库中查询到的结果【{}】", articleChannelList.size());
                    if (articleChannelList.size() > 0) {
                        for (ArticleChannel articleChannel : articleChannelList) {
                            // 查看在文库中的状态 0发布 1删除 2未发布 也就是说只有等于一的才能删除;文库中没有可以直接删除
                            if (!"1".equals(articleChannel.getArticleState())) {
                                deleteState = false;
                                msg.setInfo("文章【" + articleChannel.getArticleName() + "】在文库中有发布请先删除文库中版本！");
                                msg.setResult(false);
                                return msg;
                            }
                        }
                    }
                    if (deleteState) {
                        if (articlePublishTrackMapper.deleteByPrimaryKey(publishId) > 0) {
                            msg.setInfo("文章删除成功！");
                            msg.setResult(true);
                            LOGGER.info("管理员updateUser【{}】文章publishId【{}】删除成功！", updateUser, publishId);
                        }
                    }

                }
            }

        } catch (Exception e) {
            LOGGER.info("删除文章信息失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("删除失败，请稍候再试！");
            msg.setResult(true);
        }
        LOGGER.info("删除文章信息的业务层方法结束！");
        return msg;
    }

    /**
     * 查询用户当前发布的文章，分页展示
     */
    @Override
    public List<PublishedArticleInfo> queryArticle(String articelName, String articelAuthor,
                                                   String special, String channelId, String articleState) {
        LOGGER.info("查询文章列表的业务层方法开始！");
        LOGGER.info("拿到的参数 ，文章名称【{}】，文章作者【{}】，一级分类【{}】，二级分类【{}】，文章状态【{}】",
                articelName, articelAuthor, special, channelId, articleState);
        String companyId = "1";// 公司id

        List<String> list = new ArrayList<String>();
        if (StringUtils.isBlank(articleState)) {
            list.add("0");
            list.add("2");
        } else {
            list.add(articleState);
        }
        if (StringUtils.isBlank(articelName)) {
            articelName = "";
        }
        if (StringUtils.isBlank(articelAuthor)) {
            articelAuthor = "";
        }
        if (StringUtils.isBlank(special)) {
            special = "";
        }
        if (StringUtils.isBlank(channelId)) {
            channelId = "";
        }
        List<PublishedArticleInfo> articleList = articleQueryMapper.selectAllWithLimit(companyId, list,
                articelName, articelAuthor, special, channelId);
        if (articleList == null || articleList.size() < 1) {
            LOGGER.info("根据用户查询已发布的文章，未查询到数据");
            throw new BusinessException("您还没有发布过文章！");
        }
        for (PublishedArticleInfo publishedArticleInfo : articleList) {
            String articleId = publishedArticleInfo.getArticleId();
            Map<String, String> countJsonMap = rManager.hGetAll("company_articleInfo_Id" + articleId);
            String clickTotal = rManager.query("company_clickTotal_articleId" + articleId);
            if (StringUtils.isBlank(clickTotal)) {
                if (countJsonMap != null && countJsonMap.size() > 0) {
                    rManager.save("company_clickTotal_articleId" + articleId, countJsonMap.get("visitorCount"));
                    publishedArticleInfo.setVisitorCount(countJsonMap.get("visitorCount"));
                } else {
                    publishedArticleInfo.setVisitorCount("0");
                }
            } else {
                publishedArticleInfo.setVisitorCount(clickTotal);
            }
            if (countJsonMap != null && countJsonMap.size() > 0) {
                publishedArticleInfo.setShareCount(countJsonMap.get("sharedCount"));
            } else {
                publishedArticleInfo.setShareCount("0");
            }
        }
        LOGGER.info("查询文章列表的业务层方法结束！");
        return articleList;
    }

    /**
     * 查询当前用户发布过的文章数量
     */
    @Override
    public int countArticleInfoByState(String articelName, String articelAuthor, String special, String channelId,
                                       String articleState) {
        LOGGER.info("统计文章数量的业务层方法开始！");
        CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        String companyId = userInfoDTO.getCompanyId();// 管理员公司id
        ArticleInfoExample example = new ArticleInfoExample();
        ArticleInfoExample.Criteria criteria = example.createCriteria();
        LOGGER.info("统计文章数量的业务层方法中拿到的文章名称【{}】、文章作者【{}】、一级分类【{}】、二级分类【{}】、文章状态【{}】", articelName, articelAuthor, special,
                channelId, articleState);
        if (StringUtils.isBlank(articleState)) {
            criteria.andArticleStateNotEqualTo("1");
        } else {
            criteria.andArticleStateEqualTo(articleState);
        }
        if (StringUtils.isNotBlank(special)) {
            criteria.andSpecialEqualTo(special);
        }
        if (StringUtils.isNotBlank(channelId)) {
            criteria.andChannelIdEqualTo(channelId);
        }
        if (StringUtils.isNotBlank(articelAuthor)) {
            criteria.andArticleAuthorLike(articelAuthor);
        }
        if (StringUtils.isNotBlank(articelName)) {
            criteria.andArticleNameLike(articelName);
        }
        if (StringUtils.isNotBlank(companyId)) {
            criteria.andCompanyIdEqualTo(companyId);
        }
        List<ArticleInfo> articleList = null;
        try {
            articleList = articleMapper.selectByExample(example);
            if (articleList == null || articleList.size() < 1) {
                LOGGER.info("根据用户查询已发布的文章，未查询到数据【{}】", JsonUtil.objectToJackson(userInfoDTO));
                return 0;
            }
        } catch (Exception e) {
            LOGGER.info("统计文章数量的业务层方法产生异常【{}】", e.getMessage());
            e.printStackTrace();
        }
        LOGGER.info("统计文章数量的业务层方法结束！");
        return articleList.size();
    }

    /**
     * 根据id查询文章信息
     */
    @Override
    public ArticleInfo queryArticleByArticleId(String articleId) {
        LOGGER.info("查询单个文章信息的业务层方法开始！");
        LOGGER.info("查询单个文章信息的业务层方法中拿到的文章articleId【{}】", articleId);
        if (StringUtils.isBlank(articleId)) {
            LOGGER.info("查询文章信息失败，缺少参数");
            throw new ParameterException("未查询到文章信息");
        }
        ArticleInfo articleInfo = articleMapper.selectByPrimaryKey(Integer.valueOf(articleId));
        LOGGER.info("查询到的文章信息【{}】", JsonUtil.objectToJackson(articleInfo));
        LOGGER.info("查询单个文章信息的业务层方法结束！");
        return articleInfo;
    }

    /**
     * 更新文章通过id
     */
    @Override
    public Message articleUpByArticleId(ArticleInfo articleInfo) throws Exception {
        LOGGER.info("修改文章信息的业务层方法开始！");
        LOGGER.info("修改文章业务方法中拿到的文章信息【{}】", JsonUtil.objectToJackson(articleInfo));
        Message msg = new Message();
        try {
            if (articleMapper.updateByPrimaryKeySelective(articleInfo) > 0) {
                msg.setInfo("修改成功！");
                msg.setResult(true);
                LOGGER.info("修改成功！");

                Long flag = rManager.delete("company_articleInfo_Id" + articleInfo.getArticleId());
                if (flag != 0) {
                    LOGGER.info("删除redis该条文章信息成功！");
                }
            }
        } catch (Exception e) {
            LOGGER.info("更新文章失败【{}】", e.getMessage());
            msg.setInfo("修改失败！");
            msg.setResult(false);
            e.printStackTrace();
        }
        LOGGER.info("修改文章信息的业务层方法结束！");
        return msg;
    }

    /**
     * 根据原始url爬取文章
     */
    @Override
    public Message getArticleContent(String originalUrl, String author, String ids) {
        Message msg = new Message();
        LOGGER.info("根据文章链接获取文章内容,参数originalUrl[{}],author[{}],ids[{}]", originalUrl, author, ids);
        OssDTO ossDTO = new OssDTO();
        ossDTO.setBucketName(PropertiesConstants.BUCKETNAME);
        ossDTO.setOssEndPoint(PropertiesConstants.OSSENDPOINT);
        ossDTO.setOssId(PropertiesConstants.OSSID);
        ossDTO.setOssKey(PropertiesConstants.OSSKEY);
        ossDTO.setOssPath(PropertiesConstants.OSSPATH);
        msg = GetMessageFromArticle.getArticleListFromUrl(originalUrl, ossDTO, author, ids);
        return msg;
    }

    /**
     * 生成静态页面路径
     */
    @Override
    public Message getHtmlUrl2(Integer articleId, String shareTitle, String shareImgUrl, String shareDes,
                               String articleContent, String ids, String publishId, String companyId, String isAuthorization,
                               String isReserve, String automaticName, String introduction, String isJoinActive) {
        Message msg = new Message();
        Map<String, String> resultMap = new HashMap<String, String>();
        /*************** 根据链接爬文章***********start ****************************/
        LOGGER.info(
                "调用生成静态页面参数articleId[{}],shareTitle[{}],shareImgUrl[{}],shareDes[{}],ids[{}],publishId[{}],companyId[{}],isAuthorization[{}],isReserve[{}],automaticName[{}],introduction[{}],isJoinActive[{}]",
                articleId, shareTitle, shareImgUrl, shareDes, ids, publishId, companyId, isAuthorization, isReserve,
                automaticName, introduction, isJoinActive);
        try {
            /**************************** 生成静态页开始 ************************************/
            Configuration conf = new Configuration();
            Map<String, Object> pageData = new HashMap<String, Object>();
            Map<String, String> articleMap = new HashMap<String, String>();
            ArticlePublishTrack articlePublishTrack = articlePublishTrackMapper.selectByPrimaryKey(publishId);
            // 时间戳
            // String versionNumber = DateUtil.currentDate(DateUtil.yyyyMMddHHmmssSSS) +
            // RandomUtils.randomNumber(3);
            String viewPath = articlePublishTrack.getPublishViewUrl().replace(PropertiesConstants.OSS_URL, "");
            String editPath = articlePublishTrack.getBackgroundEditUrl().replace(PropertiesConstants.OSS_URL, "");
            viewPath = viewPath.replace(PropertiesConstants.OSSPATH, "");
            editPath = editPath.replace(PropertiesConstants.OSSPATH, "");
            LOGGER.info("oss上删除的文章编辑的路径：" + articlePublishTrack.getBackgroundEditUrl());
            LOGGER.info("oss上删除的文章的视图路径：" + articlePublishTrack.getPublishViewUrl());
            String[] viewHtmlName = viewPath.split("/");
            String[] editHtmlName = editPath.split("/");
            String versionNumber = viewHtmlName[viewHtmlName.length - 1].split("b")[1].replace(".html", "");
            // 封装数据
            articleMap.put("shareTitle", shareTitle);
            articleMap.put("shareImgUrl", shareImgUrl);
            articleMap.put("shareDes", shareDes);
            articleMap.put("articleId", String.valueOf(articleId));
            articleMap.put("articleContent", articleContent);
            articleMap.put("companyId", companyId);
            articleMap.put("versionNumber", versionNumber);
            articleMap.put("publishId", publishId);
            articleMap.put("isAuthorization", isAuthorization);// 是否授权
            articleMap.put("isReserve", isReserve);
            articleMap.put("isJoinActive", isJoinActive);
            articleMap.put("automaticName", automaticName);
            articleMap.put("introduction", introduction);
            if (ids == null || "".equals(ids)) {
                articleMap.put("adId", "");
            } else {
                articleMap.put("adId", ids);
            }
            pageData.put("articleInfo", articleMap);

            int indexOf = articlePublishTrack.getPublishViewUrl().indexOf(".com");
            String ossViewUrl = articlePublishTrack.getPublishViewUrl().substring(indexOf + 5,
                    articlePublishTrack.getPublishViewUrl().length());
            indexOf = articlePublishTrack.getBackgroundEditUrl().indexOf(".com");
            String ossEditUrl = articlePublishTrack.getBackgroundEditUrl().substring(indexOf + 5,
                    articlePublishTrack.getBackgroundEditUrl().length());
            LOGGER.info(ossViewUrl + "-------------" + ossEditUrl);
            OSSUtil.deleteFile(PropertiesConstants.OSSENDPOINT, PropertiesConstants.OSSID, PropertiesConstants.OSSKEY,
                    PropertiesConstants.BUCKETNAME, ossViewUrl);
            OSSUtil.deleteFile(PropertiesConstants.OSSENDPOINT, PropertiesConstants.OSSID, PropertiesConstants.OSSKEY,
                    PropertiesConstants.BUCKETNAME, ossEditUrl);

            String delViewPath = viewPath.replace(viewHtmlName[viewHtmlName.length - 1], "");
            String delEditPath = editPath.replace(editHtmlName[editHtmlName.length - 1], "");
            FreemakerUtil.deleteFiles(PropertiesConstants.FILE_PATH + delViewPath,
                    viewHtmlName[viewHtmlName.length - 1]);
            FreemakerUtil.deleteFiles(PropertiesConstants.FILE_PATH + delEditPath,
                    editHtmlName[editHtmlName.length - 1]);
            LOGGER.info("本地后台删除的文章的路径：[{}]，名称：[{}]，本地后台删除的文章的编辑路径：[{}]]，名称：[{}]",
                    PropertiesConstants.FILE_PATH + delViewPath, viewHtmlName[viewHtmlName.length - 1],
                    PropertiesConstants.FILE_PATH + delEditPath, editHtmlName[editHtmlName.length - 1]);

            FreemakerUtil.generateStaticPage(conf, PropertiesConstants.TEMPLATE_VIEW_PATH,
                    PropertiesConstants.FILE_PATH + viewPath, pageData);
            FreemakerUtil.generateStaticPage(conf, PropertiesConstants.TEMPLATE_EDIT_PATH,
                    PropertiesConstants.FILE_PATH + editPath, pageData);
            // 把静态页上传到oss
            OSSUtil.uploadFileHtml(PropertiesConstants.OSSENDPOINT, PropertiesConstants.OSSID,
                    PropertiesConstants.OSSKEY, PropertiesConstants.BUCKETNAME,
                    PropertiesConstants.FILE_PATH + viewPath, PropertiesConstants.OSSPATH + viewPath);
            OSSUtil.uploadFileHtml(PropertiesConstants.OSSENDPOINT, PropertiesConstants.OSSID,
                    PropertiesConstants.OSSKEY, PropertiesConstants.BUCKETNAME,
                    PropertiesConstants.FILE_PATH + editPath, PropertiesConstants.OSSPATH + editPath);

            String articleViewUrl = PropertiesConstants.OSS_URL + PropertiesConstants.OSSPATH + viewPath; // cdn路径
            // 生产
//			String articleEditUrl = PropertiesConstants.OSS_URL_HTTPS +
//		    PropertiesConstants.OSSPATH + editPath; // cdn路径
            // 测试
            String articleEditUrl = PropertiesConstants.OSS_URL + PropertiesConstants.OSSPATH + editPath;
            LOGGER.info("articleEditUrl[{}]===============", articleEditUrl);

            /**************************** 生成静态页结束 ************************************/
            // 保存文章的原始静态页面路径
            resultMap.put("articleEditUrl", articleEditUrl);
            resultMap.put("articleViewUrl", articleViewUrl);
            resultMap.put("versionNumber", versionNumber);
            LOGGER.info("生成静态页面的versionNumber为{}", versionNumber);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("系统繁忙,请稍后再试");
        }
        msg.setObject(resultMap);
        return msg;
    }

    /**
     * 生成静态页面路径
     */
    @Override
    public Message getHtmlUrl(Integer articleId, String shareTitle, String shareImgUrl, String shareDes,
                              String articleContent, String ids, String publishId, String companyId, String isAuthorization,
                              String isReserve, String automaticName, String introduction, String isJoinActive) {
        Message msg = new Message();
        Map<String, String> resultMap = new HashMap<String, String>();
        /*************** 根据链接爬文章***********start ****************************/
        LOGGER.info(
                "调用生成静态页面参数articleId[{}],shareTitle[{}],shareImgUrl[{}],shareDes[{}],ids[{}],publishId[{}],companyId[{}],isAuthorization[{}],isReserve[{}],automaticName[{}],introduction[{}],isJoinActive[{}]",
                articleId, shareTitle, shareImgUrl, shareDes, ids, publishId, companyId, isAuthorization, isReserve,
                automaticName, introduction, isJoinActive);
        try {
            /**************************** 生成静态页开始 ************************************/
            Configuration conf = new Configuration();
            Map<String, Object> pageData = new HashMap<String, Object>();
            Map<String, String> articleMap = new HashMap<String, String>();
            // 时间戳
            String versionNumber = DateUtil.currentDate(DateUtil.yyyyMMddHHmmssSSS) + RandomUtils.randomNumber(3);
            // 封装数据
            articleMap.put("shareTitle", shareTitle);
            articleMap.put("shareImgUrl", shareImgUrl);
            articleMap.put("shareDes", shareDes);
            articleMap.put("articleId", String.valueOf(articleId));
            articleMap.put("articleContent", articleContent);
            articleMap.put("companyId", companyId);
            articleMap.put("versionNumber", versionNumber);
            articleMap.put("publishId", publishId);
            articleMap.put("isAuthorization", isAuthorization);// 是否授权
            articleMap.put("isReserve", isReserve);
            articleMap.put("isJoinActive", isJoinActive);
            articleMap.put("automaticName", automaticName);
            articleMap.put("introduction", introduction);
            if (ids == null || "".equals(ids)) {
                articleMap.put("adId", "");
            } else {
                articleMap.put("adId", ids);
            }

            pageData.put("articleInfo", articleMap);
            // 静态页生成路径
            String viewPath = PathUtil.getArticleViewUrl(versionNumber, String.valueOf(articleId));
            String editPath = PathUtil.getArticleEditUrl(versionNumber, articleId + versionNumber.substring(8, 14));
            FreemakerUtil.generateStaticPage(conf, PropertiesConstants.TEMPLATE_VIEW_PATH,
                    PropertiesConstants.FILE_PATH + viewPath, pageData);
            FreemakerUtil.generateStaticPage(conf, PropertiesConstants.TEMPLATE_EDIT_PATH,
                    PropertiesConstants.FILE_PATH + editPath, pageData);
            // 把静态页上传到oss
            OSSUtil.uploadFileHtml(PropertiesConstants.OSSENDPOINT, PropertiesConstants.OSSID,
                    PropertiesConstants.OSSKEY, PropertiesConstants.BUCKETNAME,
                    PropertiesConstants.FILE_PATH + viewPath, PropertiesConstants.OSSPATH + viewPath);
            OSSUtil.uploadFileHtml(PropertiesConstants.OSSENDPOINT, PropertiesConstants.OSSID,
                    PropertiesConstants.OSSKEY, PropertiesConstants.BUCKETNAME,
                    PropertiesConstants.FILE_PATH + editPath, PropertiesConstants.OSSPATH + editPath);

            String articleViewUrl = PropertiesConstants.OSS_URL + PropertiesConstants.OSSPATH + viewPath; // cdn路径
            // 生产
//			String articleEditUrl = PropertiesConstants.OSS_URL_HTTPS +
//		    PropertiesConstants.OSSPATH + editPath; // cdn路径
            // 测试
            String articleEditUrl = PropertiesConstants.OSS_URL + PropertiesConstants.OSSPATH + editPath;
            LOGGER.info("articleEditUrl[{}]===============", articleEditUrl);

            /**************************** 生成静态页结束 ************************************/
            // 保存文章的原始静态页面路径
            resultMap.put("articleEditUrl", articleEditUrl);
            resultMap.put("articleViewUrl", articleViewUrl);
            resultMap.put("versionNumber", versionNumber);
            LOGGER.info("生成静态页面的versionNumber为{}", versionNumber);
        } catch (Exception e) {
            e.printStackTrace();
            throw new BusinessException("系统繁忙,请稍后再试");
        }
        msg.setObject(resultMap);
        return msg;
    }

    /**
     * 检查是否有工号
     */
    @Override
    public void checkJobNumber(Integer articleId, String articleName, String listPicUrl, String ids, String publishId,
                               String companyId, String jobNumber, String userId, String createUser, String editUrl, String viewUrl,
                               String versionNumber) {
        String createDate = DateUtil.currentDate();
        String createTime = DateUtil.currentTime();

        if (jobNumber != null && !"".equals(jobNumber)) {
            LOGGER.info("导入文章开始存入redis");
            // 后台管理有userId 文章信息才存入发布表
            ArticlePublishTrack articlePublishTrack = new ArticlePublishTrack();
            articlePublishTrack.setPublishId(publishId);
            articlePublishTrack.setUserId(userId);
            articlePublishTrack.setArticleId(String.valueOf(articleId));
            articlePublishTrack.setAdId(ids);// 广告id
            articlePublishTrack.setPublishState("0");
            articlePublishTrack.setBackgroundEditUrl(editUrl);
            articlePublishTrack.setPublishViewUrl(viewUrl);
            articlePublishTrack.setCompanyId(companyId);
            articlePublishTrack.setCreateDate(createDate);
            articlePublishTrack.setCreateTime(createTime);
            articlePublishTrack.setCreateUser(createUser);
            articlePublishTrack.setArticleVersion(versionNumber);
            articlePublishTrack.setUpdateDate(createDate);
            articlePublishTrack.setUpdateTime(createTime);
            articlePublishTrack.setUpdateUser(createUser);
            LOGGER.info("存入publishTrack表中的versionNumber为{}", articlePublishTrack.getArticleVersion());
            int count2 = articlePublishTrackMapper.insertSelective(articlePublishTrack);
            LOGGER.info("存入publishTrack表之后返回的数据为{},publishId为{}", Integer.valueOf(count2), publishId);
            if (count2 == 0) {
                LOGGER.info("根据链接发布文章,文章存入发布表失败");
                throw new BusinessException("系统繁忙,请稍后再试");
            }
            /*
             * // 存入redis RedisPublishedArticleInfo redisArticleInfo = new
             * RedisPublishedArticleInfo(); redisArticleInfo.setPublishId(publishId);
             * redisArticleInfo.setArticleId(String.valueOf(articleId));
             * redisArticleInfo.setArticleName(articleName);
             * redisArticleInfo.setListPicUrl(listPicUrl); //
             * redisArticleInfo.setPublishEditUrl(editUrl);
             * redisArticleInfo.setPublishViewUrl(viewUrl);
             * redisArticleInfo.setCreateDate(createDate);
             * redisArticleInfo.setVisitorCount("0"); redisArticleInfo.setSharedCount("0");
             * redisArticleInfo.setAdId(ids); redisArticleInfo.setCardId(""); boolean
             * saveAPT = false; saveAPT = redisManager.saveAPT(userId,
             * String.valueOf(articleId), JsonUtil.objectToJackson(redisArticleInfo)); if
             * (!saveAPT) { LOGGER.info("发布文章时，存入文章的展示记录到redis失败！"); }
             * LOGGER.info("导入文章结束存入redis");
             */
        }
    }

    @SuppressWarnings({"unchecked", "rawtypes"})
    @Override
    public PageDTO queryBranchIdArticle(String companyId, String branchId, Integer startRow, Integer rows) {
        List userlist = new ArrayList<>();
        List<BranchIdArticle> balist = new ArrayList<>();
        CmsSysUserExample example = new CmsSysUserExample();
        CmsSysUserExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andBranchIdLike("%" + branchId + "%");
        // 查询该渠道该公司下的后台管理用户
        List<CmsSysUser> CmsSysUserList = cmsSysUserMapper.selectByExample(example);
        for (CmsSysUser c : CmsSysUserList) {
            String userId = c.getUserId();
            LOGGER.info("分公司文章列表后台管理员userId[{}]", userId);
            userlist.add(userId);
        }
        // 查该公司下所有文章版本
        List<ArticlePublishTrack> articleIdList = articlePublishTrackQueryMapper.queryArticleIdByUserId(userlist,
                startRow, rows);
        for (ArticlePublishTrack ap : articleIdList) {
            ArticleInfo articleInfo = articleMapper.selectByPrimaryKey(Integer.valueOf(ap.getArticleId()));
            BranchIdArticle branchIdArticle = new BranchIdArticle();
            branchIdArticle.setArticleId(ap.getArticleId());
            branchIdArticle.setArticleName(articleInfo.getArticleName());
            branchIdArticle.setCreateDate(articleInfo.getCreateDate());
            branchIdArticle.setLibraryState(ap.getLibraryState());
            branchIdArticle.setUpdateDate(articleInfo.getUpdateDate());
            branchIdArticle.setPublishId(ap.getPublishId());
            branchIdArticle.setName(ap.getCreateUser());
            LOGGER.info("getArticleVersion为{}", ap.getArticleVersion());
            branchIdArticle.setArticleVersion(ap.getArticleVersion());
            balist.add(branchIdArticle);
        }
        PageDTO pageDTO = new PageDTO();
        pageDTO.setStartRow(startRow);
        pageDTO.setDataRows(balist);
        int count = articlePublishTrackQueryMapper.countArticleIdByUserId(userlist);
        pageDTO.setTotal(count % rows == 0 ? count / rows : (count / rows + 1));
        pageDTO.setRecords(count);

        return pageDTO;
    }

    @Override
    public Message delArticleInfo(String id) {
        LOGGER.info("文章列表的删除功能的业务层方法开始！");
        String updateDate = DateUtil.currentDate();
        String updateTime = DateUtil.currentTime();
        String updateUser = "1";
        LOGGER.info("管理员updateUser【{}】文章列表的删除功能的业务层方法中拿到的文章的id【{}】", updateUser, id);
        Message msg = new Message();
        if (StringUtils.isBlank(id)) {
            LOGGER.info("文章列表的删除功能的删除文章信息失败，缺少参数");
            throw new ParameterException("未查询到文章信息");
        }
        msg.setInfo("删除失败！");
        msg.setResult(false);
        Boolean deleteState = true;
        try {
            String[] arrId = id.split(",");
            for (String articleId : arrId) {
                if (StringUtils.isNotBlank(articleId)) {
                    // 先查该articleid在publish表中是否有数据
                    List<ArticlePublishTrack> list = articlePublishTrackQueryMapper.queryArticleinfo(articleId);
                    LOGGER.info("在发布表中查询到的结果【{}】", list.size());
                    if (list.size() > 0) {
                        for (ArticlePublishTrack articlePublishTrack : list) {
                            if ("0".equals(articlePublishTrack.getPublishState())) {
                                deleteState = false;
                                msg.setInfo("文章在创建文章中有收录,请先删除创建文章中版本！");
                                msg.setResult(false);
                                return msg;
                            }
                        }
                    }
                    if (deleteState) {
                        ArticleInfo articleinfo = articleMapper.selectByPrimaryKey(Integer.valueOf(articleId));
                        // 删除的是publish表中的publish_state
                        articleinfo.setUpdateDate(updateDate);
                        articleinfo.setUpdateTime(updateTime);
                        articleinfo.setArticleState("1");
                        if (articleMapper.updateByPrimaryKey(articleinfo) > 0) {
                            msg.setInfo("文章删除成功！");
                            msg.setResult(true);
                            LOGGER.info("管理员【{}】文章articleId【{}】删除成功！", updateUser, articleId);
                        }
                    }
                }
            }
        } catch (Exception e) {
            LOGGER.info("删除文章信息失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("删除失败，请稍候再试！");
            msg.setResult(true);
        }
        LOGGER.info("删除文章信息的业务层方法结束！");
        return msg;
    }

    @Override
    public Message articleUrlCopy(String articleId, String userId, String publishId1) {
        LOGGER.info("生成文章链接的业务层方法开始！");
        LOGGER.info("生成文章链接的业务层方法中拿到的文章的articleId[{}],userId[{}]", articleId, userId);
        Message msg = new Message(true, "生成文章链接成功");

        String publishId = userId + "a" + articleId;
        ArticlePublishTrack articlePublishTrack = this.articlePublishTrackMapper.selectByPrimaryKey(publishId1);
        String viewUrl = articlePublishTrack.getPublishViewUrl();

        String companyId = "1";

        String parentClickId = "0";
        String parentPublishId = publishId;
        String parentUserId = userId;
        String isAuthorization = "0";
        String from = "singlemessage";
        String[] viewUrls = viewUrl.split("/");
        String url = viewUrls[(viewUrls.length - 1)];
        String[] urls = url.split("b");
        String url1 = urls[(urls.length - 1)];
        String versionNumber = url1.substring(0, 20);
        String forwardTimestamp = String.valueOf(System.currentTimeMillis());

        String shareViewUrl = viewUrl + "?publishId=" + publishId + "&parentClickId=" + parentClickId
                + "&versionNumber=" + versionNumber + "&parentPublishId=" + parentPublishId + "&parentUserId="
                + parentUserId + "&companyId=" + companyId + "&isAuthorization=" + isAuthorization + "&from=" + from
                + "&forwardTimestamp=" + forwardTimestamp + "&articleId=" + articleId;
        msg.setObject(shareViewUrl);
        LOGGER.info("生成文章链接的业务层方法结束！");
        return msg;
    }

    public String registeQyhUrl(String articleUrl) {
        Map result = new HashMap();
        String state = "";
        String qyhArticleUrl = "";
        String getUrl = PropertiesConstants.GVQYH_ARTICLE_WRITE + articleUrl;
        try {
            String json = HttpUtil.get(getUrl);
            LOGGER.info("企业号注册链接接口返回报文为【{}】", json);
            if (StringUtils.isNotBlank(json)) {
                result = JsonUtil.jackson2Map(json);
                if ("suc".equals(result.get("result_code"))) {
                    String resultMsg = (String) result.get("result_msg");
                    state = resultMsg.split("_")[1].substring(0, 5);
                    LOGGER.info("链接标识state[{}]", state);
                    qyhArticleUrl = PropertiesConstants.GVQYH_INDEX + state;
                } else {
                    state = (String) result.get("result_msg");
                    LOGGER.info("文章企业号链接注册失败，返回信息为：【{}】", state);
                }
            }
        } catch (Exception e) {
            LOGGER.info("执行http请求，发生异常。异常信息如下：", e.getMessage());
            throw new BusinessException("系统正在维护中，请稍后再试");
        }
        String json;
        return qyhArticleUrl;
    }

    @Override
    public Message delHTML(String ids) {
        Message message = new Message();
        try {
            CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
            String updateUser = userInfoDTO.getUserId();
            LOGGER.info("管理员updateUser【{}】删除文章信息的业务层方法中拿到的文章的publishId【{}】", updateUser, ids);

            CmsSysUserExample example = new CmsSysUserExample();
            CmsSysUserExample.Criteria criteria = example.createCriteria();
            criteria.andUserIdLike("%" + updateUser + "%");

            List<CmsSysUser> CmsSysUserList = cmsSysUserMapper.selectByExample(example);

            String branchId = "";
            for (CmsSysUser cmsSysUser : CmsSysUserList) {
                branchId = cmsSysUser.getBranchId();
                LOGGER.info("删除HTML的branchId为【{}】", cmsSysUser.getBranchId());
                LOGGER.info("删除HTML的userId为【{}】", cmsSysUser.getUserId());
            }
            Boolean deleteState = Boolean.valueOf(true);
            String[] split = ids.split(",");
            for (int i = 0; i < split.length; i++) {
                String publishId = split[i];

                if (StringUtils.isNotBlank(publishId)) {
                    List<ArticleChannel> articleChannelList = this.articleChannelQueryMapper
                            .queryByPublishId(publishId);
                    LOGGER.info("在文库中查询到的结果【{}】", Integer.valueOf(articleChannelList.size()));
                    if (articleChannelList.size() > 0) {
                        for (ArticleChannel articleChannel : articleChannelList) {
                            if (!"1".equals(articleChannel.getArticleState())) {
                                deleteState = Boolean.valueOf(false);
                                message.setInfo("文章【" + articleChannel.getArticleName() + "】在文库中有发布请先删除文库中版本！");
                                message.setResult(false);
                                return message;
                            }
                        }
                    }
                    if (deleteState.booleanValue()) {
                        ArticlePublishTrack selectByPrimaryKey = this.articlePublishTrackMapper
                                .selectByPrimaryKey(publishId);
                        String publishViewUrl = selectByPrimaryKey.getPublishViewUrl();
                        int indexOf = publishViewUrl.indexOf(".com");
                        String ossUrl = publishViewUrl.substring(indexOf + 5, publishViewUrl.length());
                        LOGGER.info("删除HTML的ossUrl【{}】", ossUrl);
                        String publishUserId2 = selectByPrimaryKey.getUserId();
                        CmsSysUserExample example1 = new CmsSysUserExample();
                        CmsSysUserExample.Criteria criteria1 = example1.createCriteria();
                        CmsSysUserExample.Criteria publishAndUserIdEqualTo = criteria1.andUserIdEqualTo(publishUserId2);
                        List<CmsSysUser> selectByExample1 = this.cmsSysUserMapper.selectByExample(example);
                        for (CmsSysUser cmsSysUser1 : selectByExample1) {
                            String publishBranchId = cmsSysUser1.getBranchId();
                            if ((branchId.equals(publishBranchId)) || (publishBranchId.equals("86"))) {
                                int deleteByPrimaryKey = this.articlePublishTrackMapper.deleteByPrimaryKey(publishId);
                                if (deleteByPrimaryKey > 0) {
                                    LOGGER.info("删除发布表数据成功");
                                    OSSUtil.deleteFile(PropertiesConstants.OSSENDPOINT, PropertiesConstants.OSSID,
                                            PropertiesConstants.OSSKEY, PropertiesConstants.BUCKETNAME, ossUrl);
                                } else {
                                    LOGGER.info("删除发布表数据失败");
                                    message.setInfo("删除数据失败");
                                    message.setResult(false);
                                    return message;
                                }
                            } else {
                                message.setInfo("当前用户没有权限");
                                message.setResult(false);
                                message.setObject("");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            message.setInfo("删除数据失败");
            message.setResult(false);
            message.setObject(e.getMessage());
            return message;
        }
        message.setInfo("删除数据成功");
        message.setResult(true);
        return message;
    }

    @Override
    public Message getArticleContent(String createUser, String author, String ids, String isAuthorization,
                                     String isReserve, String automaticName, String introduction, String labelIds, String isJoinActive,
                                     String articleName, String shareImgUrl, String shareDes, String articleContent) {
        Message msg = new Message();
        Map<String, String> resultMap = new HashMap<String, String>();
        /*************** 根据链接爬文章***********start ****************************/
        LOGGER.info(
                "根据文章链接获取文章内容,参数createUser[{}],author[{}],ids[{}],isAuthorization[{}],isReserve[{}],automaticName[{}],introduction[{}],isJoinActive[{}]",
                createUser, author, ids, isAuthorization, isReserve, automaticName, introduction, isJoinActive);
        CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        String companyId = userInfoDTO.getCompanyId();// 渠道id
        String email = userInfoDTO.getEmail();
        // 根据登录人查询登录信息（shiro里有缓存数据不是最新）
        CmsSysUser cmsSysUser = cmsSysUserExMapper.queryLoginInfoByEmail(email);
        // 此时后台管理员
        String userId = cmsSysUser.getUserId();

        String createDate = DateUtil.currentDate();
        String createTime = DateUtil.currentTime();
        LOGGER.info(
                "导入文章相关信息：【listPicUrl:{},articleName:{},shareTitle:{},shareImgUrl:{},shareDes:{},articleContent:{},createDate:{},createTime:{}】",
                new Object[]{shareImgUrl, articleName, articleName, shareImgUrl, shareDes, articleContent, createDate,
                        createTime});

        ArticleInfo article = new ArticleInfo();
        article.setArticleAuthor(userInfoDTO.getName() + "");
        article.setArticleName(articleName);
        article.setOriginalUrl("0");
        article.setShareImgUrl(shareImgUrl);
        article.setShareTitle(articleName);
        article.setShareDes(shareDes);
        article.setListPicUrl(shareImgUrl);
        article.setCreateUser(createUser);
        article.setCompanyId(companyId);
        article.setCreateDate(createDate);
        article.setCreateTime(createTime);

        int cont = articleMapper.insertSelective(article);
        if (cont == 0) {
            LOGGER.info("插入新文章失败");
            throw new BusinessException("系统繁忙,请稍后再试");
        }

        // 文章id
        Integer articleId = article.getArticleId();
        // 文章的发布id
        String publishId = userId + "a" + articleId;

        msg = this.getHtmlUrl(articleId, articleName, shareImgUrl, shareDes, articleContent, ids, publishId, companyId,
                isAuthorization, isReserve, automaticName, introduction, isJoinActive);
        Map<String, String> resultMap1 = new HashMap<String, String>();
        resultMap1 = (Map<String, String>) msg.getObject();
        String modifiedEditUrl = resultMap1.get("articleEditUrl");
        String modifiedViewUrl = resultMap1.get("articleViewUrl");
        String versionNumber = (String) resultMap1.get("versionNumber");

        resultMap.put("articleEditUrl", modifiedEditUrl);
        resultMap.put("articleViewUrl", modifiedViewUrl);
        // article_info 中modified两个字段自判断是否后台导入过，第一次存值后不再更新
        article.setModifiedEditUrl(modifiedEditUrl);
        article.setModifiedViewUrl(modifiedViewUrl);
        LOGGER.info("存放静态页面路径modifiedEditUrl[{}],modifiedViewUrl[{}]", modifiedEditUrl, modifiedViewUrl);
        int count1 = articleMapper.updateByPrimaryKeySelective(article);
        if (count1 == 0) {
            LOGGER.info("原始静态页面路径入库失败");
            throw new BusinessException("系统繁忙,请稍后再试");
        }
        // 后台管理员工号
        String jobNumber = cmsSysUser.getJobNumber();
        LOGGER.info("当前用户工号为jobNumber【{}】", jobNumber);
        LOGGER.info("获取到的versionNumber2为{}", versionNumber);
        // 存文章和标签的关系
        articleLabelServiceImpl.addlabel(String.valueOf(articleId), labelIds);
        this.checkJobNumber(articleId, articleName, shareImgUrl, ids, publishId, companyId, jobNumber, userId,
                userInfoDTO.getName() + "", modifiedEditUrl, modifiedViewUrl, versionNumber);
        msg.setInfo("成功导入文章!");
        msg.setResult(true);
        msg.setObject(resultMap);
        return msg;
    }

}
