package com.ruoyi.content.service.impl;


import com.github.pagehelper.PageInfo;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.content.constants.PropertiesConstants;
import com.ruoyi.content.domain.*;
import com.ruoyi.content.exception.BusinessException;
import com.ruoyi.content.exception.ParameterException;
import com.ruoyi.content.mapper.*;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.redis.RedisManager;
import com.ruoyi.content.service.ArticleService;
import com.ruoyi.content.service.LibraryService;
import com.ruoyi.content.service.TemplateSendService;
import com.ruoyi.content.utils.DateUtil;
import com.ruoyi.content.utils.HttpUtil;
import com.ruoyi.content.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class LibraryServiceImpl implements LibraryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LibraryServiceImpl.class);

    @Autowired
    private ArticleChannelMapper articleChannelMapper;
    @Autowired
    private BaseCodeMapper baseCodeMapper;
    @Autowired
    private ArticleChannelQueryMapper articleChannelQueryMapper;
    @Autowired
    private ArticleInfoMapper articleInfoMapper;
    @Autowired
    private ArticleService articleService;
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private CmsSysUserExMapper cmsSysUserExMapper;
    @Autowired
    private TemplateSendService templateSendService;
    @Autowired
    private ArticlePublishTrackMapper articlePublishTrackMapper;
    @Autowired
    private ArticleSendUrlMapper articleSendUrlMapper;
    @Autowired
    private CmsSysUserMapper cmsSysUserMapper;
    @Autowired
    private ArticlePublishSendMapper articlePublishSendMapper;
    @Autowired
    private ArticleQueryMapper articleQueryMapper;


    @Override
    public List<HashMap<String, Object>> queryLibrary(int startRow, int rows, String articelName, String special, String channel,
                                                      String articleState) throws Exception {

        LOGGER.info("查询文章列表的业务层方法开始！");
        LOGGER.info("拿到的参数 startRow【{}】，rows【{}】，文章名称【{}】，一级分类【{}】，二级分类【{}】，文章状态【{}】", startRow, rows, articelName,
                special, channel, articleState);
        String companyId = "1";// 公司id
        String branchId = "86";
        CmsSysUserExample example = new CmsSysUserExample();
        CmsSysUserExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andBranchIdLike("%" + branchId + "%");
        // 查询该渠道该公司下的后台管理用户
        List<CmsSysUser> CmsSysUserList = cmsSysUserMapper.selectByExample(example);
        List<String> userlist = new ArrayList<>();
        for (CmsSysUser c : CmsSysUserList) {
            String userId = c.getUserId();
            LOGGER.info("分公司文章列表后台管理员userId[{}]", userId);
            userlist.add(userId);
        }
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");

        if (StringUtils.isBlank(articelName)) {
            articelName = "";
        }

        if (StringUtils.isBlank(special)) {
            special = "";
        }
        if (StringUtils.isBlank(channel)) {
            channel = "";
        }
        HashMap<String, Object> parMap = new HashMap<String, Object>();
        parMap.put("list", list);
        parMap.put("startRow", startRow);
        parMap.put("rows", rows);
        parMap.put("articelName", articelName);
        parMap.put("special", special);
        parMap.put("channel", channel);
        parMap.put("userlist", userlist);
        List<HashMap<String, Object>> resultMap = articleChannelQueryMapper.selectByLimit(parMap);
        List<String> publishList = new ArrayList<>();
        if (channel != null && !channel.trim().equals("")) {
            for (HashMap<String, Object> hashMap : resultMap) {
                publishList.add((String) hashMap.get("publishId"));
            }
            if (publishList.size() > 0) {
                LOGGER.info("所有的publishId：++++++++++++" + publishList);
                resultMap = articleChannelQueryMapper.selectByPublishList(publishList);
            }
        }
        if (resultMap == null || resultMap.size() < 1) {
            LOGGER.info("文库暂没有文章！");
            return resultMap;
        }
        LOGGER.info("查询文库列表的业务层方法结束！");
        return resultMap;

    }

    /**
     * 查询文库文章数量
     */
    @Override
    public int countArticleInfoByState(String articelName, String special, String channel, String articleState) {
        LOGGER.info("统计文章数量的业务层方法开始！");
        CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        String companyId = userInfoDTO.getCompanyId();// 管理员公司id
        ArticleChannelExample example = new ArticleChannelExample();

        ArticleChannelExample.Criteria criteria = example.createCriteria();
        example.createCriteria();
        LOGGER.info("统计文章数量的业务层方法中拿到的文章名称【{}】、一级分类【{}】、二级分类【{}】、文章状态【{}】", articelName, special, channel, articleState);
        if (StringUtils.isBlank(articleState)) {
            criteria.andArticleStateNotEqualTo("1");
        } else {
            criteria.andArticleStateEqualTo(articleState);
        }
        if (StringUtils.isNotBlank(special)) {
            criteria.andSpecialEqualTo(special);
        }
        if (StringUtils.isNotBlank(channel)) {
            criteria.andChannelEqualTo(channel);
        }

        if (StringUtils.isNotBlank(articelName)) {
            criteria.andArticleNameLike(articelName);
        }
        if (StringUtils.isNotBlank(companyId)) {
            criteria.andCompanyIdEqualTo(companyId);
        }
        List<ArticleChannel> libraryList = null;
        try {
            libraryList = articleChannelMapper.selectByExample(example);
            if (libraryList == null || libraryList.size() < 1) {
                LOGGER.info("根据用户查询已发布的文章，未查询到数据【{}】", JsonUtil.objectToJackson(userInfoDTO));
                return 0;
            }
        } catch (Exception e) {
            LOGGER.info("统计文章数量的业务层方法产生异常【{}】", e.getMessage());
            e.printStackTrace();
        }
        LOGGER.info("统计文章数量的业务层方法结束！");
        return libraryList.size();
    }
/*
	@Override
	public Message addLibrary(ArticleChannel articleChannel) {
		LOGGER.info("增加文章栏目关系的业务层方法开始！");
		LOGGER.info("增加文章栏目关系中拿到的文章信息【{}】", JsonUtil.objectToJackson(articleChannel));
		Message msg = new Message();
//		Map<String, String> resultMap = new HashMap<String,String>();
//		CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
		try {
			// 修改文章表收录状态
//			ArticleInfo articleInfo = articleInfoMapper.selectByPrimaryKey(articleChannel.getArticleId());
			
			ArticlePublishTrack apt = articlePublishTrackMapper.selectByPrimaryKey(articleChannel.getPublishId());
			//收录成功后 将
			apt.setLibraryState("1");
			LOGGER.info("收录得到的文章版本号为{}",apt.getArticleVersion());
			articleChannel.setArticleVersion(apt.getArticleVersion());
			//手机端收录暂时注释 20180730
//			LOGGER.info("articleInfo【{}】", JsonUtil.objectToJackson(articleInfo));
//			String modifiedEditUrl = articleInfo.getModifiedEditUrl();
//			String modifiedViewUrl = articleInfo.getModifiedViewUrl();
//			String originalUrl = articleInfo.getOriginalUrl();
//			String author = articleInfo.getArticleAuthor();
//			Integer articleId = articleInfo.getArticleId();
//			if(modifiedEditUrl==null || modifiedEditUrl=="") {
//				//手机端导入文章
//				String companyId = userInfoDTO.getCompanyId();//公司id
//				String email = userInfoDTO.getEmail();
//				CmsSysUser cmsSysUser = cmsSysUserExMapper.queryLoginInfoByEmail(email);
//				String userId = cmsSysUser.getUserId();
//				msg = articleService.getArticleContent(originalUrl, author, "");
//				resultMap  = (Map<String, String>) msg.getObject();
//				String listPicUrl = resultMap.get("listPicUrl");
//				String articleName = resultMap.get("articleName");
//				String shareTitle = resultMap.get("shareTitle");
//				String shareImgUrl = resultMap.get("shareImgUrl");
//				String shareDes = resultMap.get("shareDes");
//				String articleContent = resultMap.get("articleContent");
//				String publishId = userId+"a"+articleId;
//				msg = articleService.getHtmlUrl(articleId, shareTitle, shareImgUrl, shareDes, articleContent, "",publishId,companyId);
//				resultMap  = (Map<String, String>) msg.getObject();
//				modifiedEditUrl = resultMap.get("articleEditUrl");
//				modifiedViewUrl = resultMap.get("articleViewUrl");
//				//将文章原始静态页路径添加到收录路径
//				articleInfo.setModifiedEditUrl(modifiedEditUrl);
//				articleInfo.setModifiedViewUrl(modifiedViewUrl);
//				
//				if (articleInfoMapper.updateByPrimaryKey(articleInfo)> 0) {
//					LOGGER.info("修改文章表收录状态成功");
//				}
//				
//				String jobNumber = cmsSysUser.getJobNumber();
//				LOGGER.info("当前用户工号为jobNumber【{}】",jobNumber);
//				articleService.checkJobNumber(articleId, articleName, listPicUrl, "", publishId, 
//						companyId, jobNumber, userId, email,modifiedEditUrl,modifiedViewUrl);
//			}

			if (articlePublishTrackMapper.updateByPrimaryKeySelective(apt)> 0) {
				LOGGER.info("修改文章发布表成功");
			}
			// 文库表添加数据
			ArticleChannelExample example = new ArticleChannelExample();
			example.createCriteria().andSpecialEqualTo(articleChannel.getSpecial()).andChannelEqualTo(articleChannel.getChannel()).andArticleIdEqualTo(articleChannel.getArticleId()).andArticleStateNotEqualTo("1");
			List<ArticleChannel> ArticleChannels = articleChannelMapper.selectByExample(example);
			
			if (ArticleChannels.size()>0) {
				LOGGER.info("添加文章关系失败，栏目channel【{}】下已存在文章article【{}】",articleChannel.getChannel(),articleChannel.getArticleId());
				msg.setInfo("收录失败！已有其他版本的文章被收录到同一栏目下！");
				msg.setResult(false);
			}else {
				if (articleChannelMapper.insertSelective(articleChannel) > 0) {
					msg.setInfo("添加成功！");
					msg.setResult(true);
					LOGGER.info("添加成功！");
					String codeType = articleChannel.getSpecial();
					redisManager.delete("article_channel_"+codeType+"_"+articleChannel.getChannel());
					LOGGER.info("删除原有redis文库信息成功！");
				}
			}
			
		} catch (Exception e) {
			LOGGER.info("添加文章关系失败【{}】", e.getMessage());
			msg.setInfo("收录失败！");
			msg.setResult(false);
			e.printStackTrace();
		}
		LOGGER.info("增加文章栏目关系的业务层方法结束！");
		return msg;
	}
	*/

    @Override
    //@Transactional
    public Message addLibrary(String companyId, String branchId, String channelId, String articleInfoList) {
        LOGGER.info("批量添加文章到相应栏目业务层开始！");
        Message msg = new Message();
        List<String> articleState = new ArrayList<>();
        articleState.add("1");
        articleState.add("2");
        JsonArray jsonArray = new JsonParser().parse(articleInfoList).getAsJsonArray();
        List<ArticleChannel> batchInsertArticle = new ArrayList<>();
        if (channelId != null && !"".equals(channelId.trim())) {
            channelId = channelId + ",ARTICLE-2";
        }
        try {
            String[] channelIdArr = channelId.split(",");
            for (String id : channelIdArr) {
                BaseCode baseCode = this.queryBaseCodeByChannelId(id.split("-")[1]);
                if (baseCode == null) {
                    continue;
                }
                for (JsonElement ele : jsonArray) {
                    JsonObject article = ele.getAsJsonObject();
                    LOGGER.info("遍历的单个文章信息：articleId：【{}】，publishId：【{}】，articleName：【{}】", article.get("articleId").getAsString(),
                            article.get("publishId").getAsString(), article.get("articleName").getAsString());
                    //变更文章收录状态
                    ArticlePublishTrack apt = articlePublishTrackMapper.selectByPrimaryKey(article.get("publishId").getAsString());
                    if (!"1".equals(apt.getLibraryState())) {
                        apt.setLibraryState("1");
                        int sucCount = articlePublishTrackMapper.updateByPrimaryKeySelective(apt);
                        LOGGER.info("根据文章publishId：【{}】,更新文章发布状态成功sucCount：", article.get("publishId").getAsString(), sucCount);
                    }
                    ArticleChannelExample example = new ArticleChannelExample();
                    example.createCriteria().andSpecialEqualTo(baseCode.getCodeType())
                            .andChannelEqualTo(baseCode.getCodeCode())
                            .andArticleIdEqualTo(article.get("articleId").getAsInt())
                            .andArticleStateNotIn(articleState);
                    List<ArticleChannel> ArticleChannels = articleChannelMapper.selectByExample(example);
                    if (!(ArticleChannels.size() > 0)) {
                        ArticleChannel articleChannel = new ArticleChannel();
                        articleChannel.setArticleId(Integer.valueOf(article.get("articleId").getAsInt()));
                        articleChannel.setPublishId(article.get("publishId").getAsString());
                        articleChannel.setArticleName(article.get("articleName").getAsString());
                        articleChannel.setSpecial(baseCode.getCodeType());
                        articleChannel.setChannel(baseCode.getCodeCode());
                        articleChannel.setCompanyId(companyId);
                        articleChannel.setBranchId(branchId);
                        articleChannel.setPublishDate(DateUtil.currentDate());
                        articleChannel.setPublishTime(DateUtil.currentTime());
                        articleChannel.setUpdateDate(DateUtil.currentDate());
                        articleChannel.setUpdateTime(DateUtil.currentTime());
                        articleChannel.setArticleState("0");
                        articleChannel.setArticleVersion(apt.getArticleVersion());
                        batchInsertArticle.add(articleChannel);
                    }
                }
            }
            int successCount = 0;
            if (batchInsertArticle.size() > 0) {
                LOGGER.info("数据库保存数据：" + batchInsertArticle);
                successCount = articleChannelQueryMapper.batchInsertArticeChannel(batchInsertArticle);
                LOGGER.info("批量收录【{}】篇文章成功！", successCount);
                for (ArticleChannel ac : batchInsertArticle) {
                    String codeType = ac.getSpecial();
                    String channel = ac.getChannel();
                    redisManager.delete("article_channel_" + codeType + "_" + channel);
                    LOGGER.info("先清除相应栏目下的redis：" + "article_channel_" + codeType + "_" + channel);
                }
            }
            msg.setInfo("批量收录文章成功");
            msg.setResult(true);
            HashMap<Object, Object> result = new HashMap<Object, Object>();
            result.put("successNum", successCount);
            msg.setObject(result);
        } catch (Exception e) {
            LOGGER.info("添加文章关系失败【{}】", e.getMessage());
            msg.setInfo("收录失败！");
            msg.setResult(false);
            e.printStackTrace();
        }
        LOGGER.info("批量收录文章到指定栏目的业务层方法结束！");
        return msg;
    }

    @Override
    public Message removeArticleItem(String companyId, String branchId, String channelId, String articleInfoList) {
        LOGGER.info("批量删除相应栏目下文章业务层开始！");
        Message msg = new Message();
        List<Integer> batchList = new ArrayList<>();
        JsonArray jsonArray = new JsonParser().parse(articleInfoList).getAsJsonArray();
        List<String> articleState = new ArrayList<>();
        articleState.add("1");
        articleState.add("2");
        //三级栏目的文章和二级栏目的文章分开清理
        String[] channelIdArray = channelId.split(",");
        try {
            for (String id : channelIdArray) {
                BaseCode baseCode = this.queryBaseCodeByChannelId(id.split("-")[1]);
                if (baseCode == null) { //baseCode不存在  跳过相应的栏目
                    continue;
                }
                for (JsonElement ele : jsonArray) {
                    JsonObject article = ele.getAsJsonObject();
                    ArticleChannelExample example = new ArticleChannelExample();
                    example.createCriteria().andSpecialEqualTo(baseCode.getCodeType())
                            .andChannelEqualTo(baseCode.getCodeCode())
                            .andArticleIdEqualTo(article.get("articleId").getAsInt());
                    List<ArticleChannel> ArticleChannels = articleChannelMapper.selectByExample(example);
                    for (ArticleChannel articleChannel : ArticleChannels) {
                        //如果要移除的栏目是二级栏目即文库下的直接栏目  需要判断文库相应二级栏目下
                        //是否有三级栏目还保存着这片文章，如果有的话移除无意义  不进行移除  没有的话就移除
                        if ("ARTICLE".equals(baseCode.getCodeType())) {
                            ArticleChannelExample exampleSec = new ArticleChannelExample();
                            exampleSec.createCriteria().andSpecialEqualTo(baseCode.getCodeCode())
                                    .andArticleStateNotIn(articleState)
                                    .andArticleIdEqualTo(articleChannel.getArticleId());
                            List<ArticleChannel> thirdArticle = articleChannelMapper.selectByExample(exampleSec);
                            List<Boolean> flagList = new ArrayList<>();
                            for (ArticleChannel ac : thirdArticle) {
                                LOGGER.info("查询的文章列表：" + ac.getArticleName());
                                for (int i = 0; i < channelIdArray.length; i++) {
                                    BaseCode bc = this.queryBaseCodeByChannelId(channelIdArray[i].split("-")[1]);
                                    LOGGER.info("比较详情文章的channel：" + ac.getChannel() + "  数组的查询channel" + bc.getCodeCode());
                                    if (ac.getChannel().equals(bc.getCodeCode())) {
                                        flagList.add(true);
                                        break;
                                    }
                                }
                            }
                            if (flagList.size() == thirdArticle.size()) {
                                batchList.add(articleChannel.getId());
                            }
                        } else {
                            batchList.add(articleChannel.getId());
                        }
                    }
                }
                String codeType = baseCode.getCodeType();
                redisManager.delete("article_channel_" + codeType + "_" + baseCode.getCodeCode());
                LOGGER.info("并清除相应栏目redis：【{}】", "article_channel_" + codeType + "_" + baseCode.getCodeCode());
            }
            int delCount = 0;
            if (batchList.size() > 0) {
                ArticleChannelExample delExample = new ArticleChannelExample();
                delExample.createCriteria().andIdIn(batchList);
                delCount = articleChannelMapper.deleteByExample(delExample);
                LOGGER.info("批量移除【{}】篇文章成功！", delCount);
            }
            msg.setInfo("批量移除成功！");
            msg.setResult(true);
            HashMap<Object, Object> resultMap = new HashMap<Object, Object>();
            resultMap.put("delCount", delCount);
            msg.setObject(resultMap);
        } catch (Exception e) {
            LOGGER.info("移除文章关系失败【{}】", e.getMessage());
            msg.setInfo("移除失败！");
            msg.setResult(false);
            e.printStackTrace();
        }
        LOGGER.info("批量移除指定栏目下文章的业务层方法结束！");
        return msg;
    }

    @Override
    public Message changeSate(String ids, String eState, String sState, String publishId) {
        LOGGER.info("发布文章的业务层方法开始！");
        LOGGER.info("发布文章的业务层方法中拿到的文章的articleId【{}】,eState[{}],sState[{}],publishId[{}]", ids, eState, sState, publishId);
        Message msg = new Message();
        if (StringUtils.isBlank(ids)) {
            LOGGER.info("发布文章失败，缺少参数");
            msg.setInfo("发布文章失败！");
            msg.setResult(false);
            throw new ParameterException("发布文章失败，缺少参数");
        }
        try {
            String[] arrId = ids.split(",");
            for (String aId : arrId) {
                if (StringUtils.isNotBlank(aId)) {
                    ArticleChannel articleChannel = articleChannelMapper.selectByPrimaryKey(Integer.valueOf(aId));

                    if (articleChannel != null) {
                        LOGGER.info("文章存在【{}】，进行状态修改！", JsonUtil.objectToJackson(articleChannel));
                        if (articleChannel.getArticleState() == "0") {// 文章已经发布
                            LOGGER.info("文章已经发布，不进行操作！");
                        } else {
                            articleChannel.setArticleState("0");
                            articleChannel.setPublishDate(DateUtil.currentDate());
                            articleChannel.setPublishTime(DateUtil.currentTime());
                            articleChannel.setUpdateDate(DateUtil.currentDate());
                            articleChannel.setUpdateTime(DateUtil.currentTime());
                            if (articleChannelMapper.updateByPrimaryKey(articleChannel) > 0) {
                                msg.setInfo("发布文章成功。");
                                msg.setResult(true);
                                LOGGER.info("发布文章成功！");
                                String codeType = articleChannel.getSpecial();
                                redisManager.delete("article_channel_" + codeType + "_" + articleChannel.getChannel());
                                LOGGER.info("删除原有redis文库信息成功！");
                            } else {
                                msg.setInfo("发布文章失败。");
                                msg.setResult(false);
                                LOGGER.info("发布文章失败！");
                            }
                        }
                    } else {
                        msg.setInfo("发布文章失败。");
                        msg.setResult(false);
                        LOGGER.info("文章不存在，发布失败！");
                    }
                }
            }
            LOGGER.info("状态修改结束！");
        } catch (NumberFormatException e) {
            msg.setInfo("发布文章失败，请重试！");
            msg.setResult(false);
            LOGGER.info("发布文章出现 异常【{}】", e.getMessage());
            e.printStackTrace();
        }
        LOGGER.info("发布文章的业务层方法结束！");
        return msg;
    }

    /**
     * 根据文章id删除文章
     */
    @Override
    public Message delArticle(String id) {
        LOGGER.info("删除文章栏目关系的业务层方法开始！");
        LOGGER.info("删除文章栏目关系的业务层方法中拿到的文章的id【{}】", id);
        Message msg = new Message();
        if (StringUtils.isBlank(id)) {
            LOGGER.info("删除文章信息失败，缺少参数");
            throw new ParameterException("未查询到文章信息");
        }
        msg.setInfo("删除失败！");
        msg.setResult(false);
        List<Integer> delChannel = new ArrayList<>();
        List<String> delPublishId = new ArrayList<>();
        try {
            String[] publishId = id.split(",");
            for (String pId : publishId) {
                if (StringUtils.isNotBlank(pId)) {
                    delPublishId.add(pId);
                    ArticleChannelExample example = new ArticleChannelExample();
                    ArticleChannelExample.Criteria criteria = example.createCriteria();
                    criteria.andPublishIdEqualTo(pId);
                    //ArticleChannel articleChannel = articleChannelMapper.selectByPrimaryKey(Integer.valueOf(aId));
                    List<ArticleChannel> list = articleChannelMapper.selectByExample(example);
                    for (ArticleChannel articleChannel : list) {
                        if (articleChannel != null) {
                            delChannel.add(articleChannel.getId());
                            redisManager.delete("article_channel_" + articleChannel.getSpecial() + "_" + articleChannel.getChannel());
                            LOGGER.info("删除redis文章栏目关系成功key:[{}]！", "article_channel_" + articleChannel.getSpecial() + "_" + articleChannel.getChannel());
                        }
                    }
                }
            }
            if (delChannel != null && delChannel.size() > 0) {
                ArticleChannelExample delChannelExample = new ArticleChannelExample();
                delChannelExample.createCriteria().andIdIn(delChannel);
                articleChannelMapper.deleteByExample(delChannelExample);
            }
            ArticlePublishTrackExample delPublishExample = new ArticlePublishTrackExample();
            delPublishExample.createCriteria().andPublishIdIn(delPublishId);
            articlePublishTrackMapper.deleteByExample(delPublishExample);
            LOGGER.info("删除channel表成功，id：【{}】，删除publish表成功，publishId：【{}】", delChannel, delPublishId);
            msg.setInfo("删除文章成功");
            msg.setResult(true);
        } catch (Exception e) {
            LOGGER.info("删除文章栏目关系失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("删除文章栏目关系失败，请稍候再试！");
            msg.setResult(true);
        }
        LOGGER.info("删除文章栏目关系的业务层方法结束！");
        return msg;
    }

    /**
     * 文章排序
     */
    @Override
    public Message updateLibrary(String id, String orderNo) {
        LOGGER.info("文库文章排序的业务层方法开始！");
        LOGGER.info("文章排序参数id【{}】，orderNo【{}】", id, orderNo);
        Message msg = new Message();
        if (StringUtils.isBlank(orderNo) || StringUtils.isBlank(id)) {
            LOGGER.info("文章排序失败，缺少参数");
            msg.setInfo("文章排序失败！");
            msg.setResult(false);
            throw new ParameterException("文章排序失败，缺少参数");
        }
        try {
            ArticleChannel articleChannel = articleChannelMapper.selectByPrimaryKey(Integer.valueOf(id));
            if (articleChannel != null) {
                String time = DateUtil.currentDate(DateUtil.YMDHMS);
                articleChannel.setOrderNo(Integer.valueOf(orderNo));
                if ("0".equals(orderNo)) {
                    articleChannel.setTopTime("0");
                } else {
                    articleChannel.setTopTime(time);
                }
                articleChannel.setUpdateDate(DateUtil.currentDate());
                articleChannel.setUpdateTime(DateUtil.currentTime());
                if (articleChannelMapper.updateByPrimaryKey(articleChannel) > 0) {
                    msg.setInfo("更新排序成功！");
                    msg.setResult(true);
                    LOGGER.info("更新排序成功！");
                    redisManager.delete("article_channel_" + articleChannel.getChannel());
                    LOGGER.info("删除redis文章栏目关系成功！");
                }

            }

        } catch (Exception e) {
            LOGGER.info("文章排序失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("文章排序失败，请稍候再试！");
            msg.setResult(true);
        }
        LOGGER.info("文库文章排序的业务层方法结束！");
        return msg;
    }

    @Override
    public BaseCode queryBaseCodeByChannelId(String channelId) {
        LOGGER.info("开始根据栏目id查询栏目名称！");
        BaseCode baseCode = baseCodeMapper.selectByPrimaryKey(Integer.valueOf(channelId));
        LOGGER.info("查询基础数据结束，查询到的结果为【{}】" + JsonUtil.objectToJackson(baseCode));
        LOGGER.info("结束根据栏目id查询栏目名称！");
        return baseCode;
    }

    @Override
    public Message queryEditUrlByPublishId(String publishId) {
        LOGGER.info("根据发布id查编辑路径开始！");
        Message msg = new Message();
        Map<String, String> resultMap = new HashMap<String, String>();
        ArticlePublishTrack ap = articlePublishTrackMapper.selectByPrimaryKey(publishId);
        LOGGER.info("接收到参数ap[{}]", JsonUtil.objectToJackson(ap));
        String editUrl = ap.getBackgroundEditUrl();
        if (editUrl == null || editUrl == "") {
            msg.setResult(false);
            msg.setInfo("预览失败！");
            LOGGER.info("预览失败！");

        } else {
            msg.setResult(true);
            resultMap.put("editUrl", editUrl);
            msg.setObject(resultMap);
        }
        LOGGER.info("结束根据栏目id查询栏目名称！");
        return msg;
    }

    @Override
    public int checkCreateUser(String articleId, String originalUrl, String createUser) {
        ArticleInfo articleInfo = articleService.queryArticleByArticleId(articleId);
        String modifiedEditUrl = articleInfo.getModifiedEditUrl();
        if (modifiedEditUrl == null || modifiedEditUrl == "") {
            //msg = articleService.getArticleContentByUrl(originalUrl,createUser,createUser,"");
            //resultMap  = (Map<String, String>) msg.getObject();
            String articleEditUrl = articleInfo.getArticleEditUrl();
            String articleViewUrl = articleInfo.getArticleViewUrl();
            articleInfo.setModifiedEditUrl(articleEditUrl);
            articleInfo.setModifiedViewUrl(articleViewUrl);
            articleInfoMapper.updateByPrimaryKey(articleInfo);
            return 0;//业务员创建
        }
        return 1;//
    }

    @SuppressWarnings({"unused", "deprecation"})
    @Override
    public Message articleSend(String articleId, String publishId, String agentCode, String sendType, String partyId, Integer sendId) {
        LOGGER.info("推送文章的业务层方法开始！");
        LOGGER.info("推送文章的业务层方法中拿到的文章的articleId【{}】,publishId[{}],agentCode[{}],sendType[{}],partyId[{}]", new Object[]{articleId, publishId, agentCode, sendType, partyId});
        Message msg = new Message(true, "推送文章成功");
//        CmsSysUser userInfoDTO = null;
        String companyId = null;
        String email = null;
        ArticlePublishSend aps = null;
        if (sendId != null) {
            aps = articlePublishSendMapper.selectByPrimaryKey(sendId);
//            String userId = aps.getOperateId();
//            CmsSysUserExample user = new CmsSysUserExample();
//            user.createCriteria().andUserIdEqualTo(userId);
//            userInfoDTO = cmsSysUserMapper.selectByExample(user).get(0);

        } else {
//            userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
        }
        companyId = "1";
        email = "13152783264";
        CmsSysUser cmsSysUser = this.cmsSysUserExMapper.queryLoginInfoByEmail(email);
        String userId = cmsSysUser.getUserId();
        String sysUserName = cmsSysUser.getName();
        if ((StringUtils.isBlank(articleId)) || (StringUtils.isBlank(publishId))) {
            LOGGER.info("推送文章失败，缺少参数");
            msg.setInfo("推送文章失败！");
            msg.setResult(false);
            throw new ParameterException("推送文章失败，缺少参数");
        }
        try {
            ArticlePublishTrackExample example = new ArticlePublishTrackExample();
            ArticlePublishTrackExample.Criteria criteria = example.createCriteria();
            criteria.andPublishIdEqualTo(publishId);
            criteria.andArticleIdEqualTo(articleId);
            List aplist = this.articlePublishTrackMapper.selectByExample(example);
            String viewUrl = ((ArticlePublishTrack) aplist.get(0)).getPublishViewUrl();
            ArticleInfo articleInfo = this.articleInfoMapper.selectByPrimaryKey(Integer.valueOf(articleId));
            String articleName = articleInfo.getArticleName();
            String picUrl = articleInfo.getListPicUrl();
            String shareDes = articleInfo.getShareDes();
            String[] viewUrls = viewUrl.split("/");
            String url = viewUrls[(viewUrls.length - 1)];
            String[] urls = url.split("b");
            String url1 = urls[(urls.length - 1)];
            String versionNumber = url1.substring(0, 20);
            LOGGER.info("管理员sysUserName【{}】推送文章articleId【{}】版本号versionNumber[{}]", new Object[]{sysUserName, articleId, versionNumber});

            String newClickId = "0";
            String origin = PropertiesConstants.REDIRECT_ARTICLE_VIEW;
            String link = "publishId=" + publishId + "&parentClickId=" + newClickId + "&versionNumber=" + versionNumber + "&parentPublishId=" + publishId + "&parentUserId=" + userId + "&companyId=" + companyId;

            String articleUrl = URLEncoder.encode(origin + link);
            LOGGER.info("请求企业号跳转文章url[{}]", articleUrl);
            Map result = new HashMap();
            String qyhArticleUrl = "";
            ArticleSendUrl articleSendUrl = this.articleSendUrlMapper.selectByPrimaryKey(publishId);

            String sendViewUrl = viewUrl + "?" + "companyId=" + companyId + "&parentClickId=" + newClickId + "&qyhState=1";
            if ("1".equals(sendType)) {
                LOGGER.info("复制文章推送链路qyhArticleUrl[{}]", sendViewUrl);
                msg.setObject(sendViewUrl);
                return msg;
            }
            LOGGER.info("开始调用消息推送方法！");
            try {
                msg = this.templateSendService.newArticleSend(articleName, sendViewUrl, picUrl, shareDes, agentCode, partyId);
                if (sendId != null) {
                    if (msg.getResult()) {
                        aps.setSendState("1");
                    } else {
                        aps.setSendState("0");
                    }
                    articlePublishSendMapper.updateByPrimaryKey(aps);
                    LOGGER.info("更新articlePublishSend成功");
                } else {
                    //发送消息成功  保存详细信息到  推送文章信息记录表
                    ArticlePublishSend articlePublishSend = new ArticlePublishSend();
                    articlePublishSend.setArticleId(Integer.parseInt(articleId));
                    articlePublishSend.setPublishId(publishId);
                    articlePublishSend.setOperateId(userId);
                    articlePublishSend.setOperateName(sysUserName);
                    articlePublishSend.setPublishDate(DateUtil.currentDate());
                    articlePublishSend.setPublishTime(DateUtil.currentTime());
                    articlePublishSend.setOperateDate(DateUtil.currentDate());
                    articlePublishSend.setOperateTime(DateUtil.currentTime());
                    if (msg.getResult()) {
                        articlePublishSend.setSendState("1");
                    } else {
                        articlePublishSend.setSendState("0");
                    }
                    if (StringUtils.isBlank(agentCode)) {  //推送企业
                        LOGGER.info("推送至企业");
                        articlePublishSend.setSendType("0");
                        articlePublishSend.setGroupId(partyId);
                    } else { //推送个人
                        LOGGER.info("推送至个人");
                        articlePublishSend.setSendType("1");
                        articlePublishSend.setGroupId(agentCode);
                    }
                    if (articlePublishSendMapper.insert(articlePublishSend) > 1) {
                        LOGGER.info("记录推送文章详情成功，记录信息：" + JsonUtil.objectToJackson(articlePublishSend));
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            LOGGER.info("结束调用消息推送!");
            //消息推送类型如果是企业号  结束更新publish表
            if (StringUtils.isBlank(agentCode)) {
                ArticlePublishTrack publishArticle = (ArticlePublishTrack) aplist.get(0);
                publishArticle.setUpdateDate(DateUtil.currentDate());
                publishArticle.setUpdateTime(DateUtil.currentTime());
                publishArticle.setUpdateUser(sysUserName);
                articlePublishTrackMapper.updateByPrimaryKey(publishArticle);
                //清楚相应栏目下的redis
                ArticleChannelExample channelExample = new ArticleChannelExample();
                channelExample.createCriteria()
                        .andPublishIdEqualTo(publishArticle.getPublishId())
                        .andArticleIdEqualTo(Integer.parseInt(publishArticle.getArticleId()))
                        .andArticleStateEqualTo("0");
                List<ArticleChannel> articleChannels = articleChannelMapper.selectByExample(channelExample);
                for (ArticleChannel articleChannel : articleChannels) {
                    LOGGER.info("清楚redis中key：" + "article_channel_" + articleChannel.getSpecial() + "_" + articleChannel.getChannel());
                    redisManager.delete("article_channel_" + articleChannel.getSpecial() + "_" + articleChannel.getChannel());
                }


            }
        } catch (NumberFormatException e) {
            msg.setInfo("文章推送失败，请重试！");
            msg.setResult(false);
            LOGGER.info("文章推送失败 异常【{}】", e.getMessage());
            e.printStackTrace();
        }
        LOGGER.info("文章推送的业务层方法结束！");
        return msg;
    }

    //注册企业号链接
    @SuppressWarnings("unchecked")
    public String registeQyhUrl(String articleUrl) {
        Map<String, String> result = new HashMap<>();
        String state = "";
        String qyhArticleUrl = "";
        String getUrl = PropertiesConstants.GVQYH_ARTICLE_WRITE + articleUrl;
        String json;
        String resultMsg;
        try {
            json = HttpUtil.get(getUrl);
            LOGGER.info("企业号注册链接接口返回报文为【{}】", json);
            if (StringUtils.isNotBlank(json)) {
                result = JsonUtil.jackson2Map(json);
                if ("suc".equals(result.get("result_code"))) {
                    resultMsg = result.get("result_msg");
                    state = resultMsg.split("_")[1].substring(0, 5);
                    LOGGER.info("链接标识state[{}]", state);
                    qyhArticleUrl = PropertiesConstants.GVQYH_INDEX + state;
                } else {
                    state = result.get("result_msg");
                    LOGGER.info("文章企业号链接注册失败，返回信息为：【{}】", state);
                }
            }
        } catch (Exception e) {
            LOGGER.info("执行http请求，发生异常。异常信息如下：", e.getMessage());
            throw new BusinessException("系统正在维护中，请稍后再试");
        }
        return qyhArticleUrl;
    }


    @Override
    public Message delLibraryHTML(String id) {
        LOGGER.info("删除HTML栏目关系的业务层方法开始！");

        LOGGER.info("删除HTML栏目关系的业务层方法中拿到的文章的主键id【{}】", id);
        Message msg = new Message();
        if (StringUtils.isBlank(id)) {
            LOGGER.info("删除HTML信息失败，缺少参数");
            throw new ParameterException("未查询到文章信息");
        }
        msg.setInfo("删除失败！");
        msg.setResult(false);
        try {
            String[] arrId = id.split(",");
            for (String aId : arrId) {
                if (StringUtils.isNotBlank(aId)) {
                    ArticleChannelExample example = new ArticleChannelExample();
                    ArticleChannelExample.Criteria criteria = example.createCriteria();
                    criteria.andArticleIdEqualTo(Integer.valueOf(aId));
                    //ArticleChannel articleChannel = articleChannelMapper.selectByPrimaryKey(Integer.valueOf(aId));
                    List<ArticleChannel> articleChannelList = articleChannelMapper.selectByExample(example);
                    for (ArticleChannel articleChannel : articleChannelList) {
                        if (articleChannel != null) {
                            articleChannel.setArticleState("1");
                            articleChannel.setUpdateDate(DateUtil.currentDate());
                            articleChannel.setUpdateTime(DateUtil.currentTime());
                            if (articleChannelMapper.updateByPrimaryKey(articleChannel) > 0) {
                                msg.setInfo("删除成功！");
                                msg.setResult(true);
                                LOGGER.info("删除文章栏目关系成功！");
                                redisManager.delete("article_channel_ARTICLE_" + articleChannel.getChannel());
                                LOGGER.info("删除redis文章栏目关系成功！");
                            }
                        }
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.info("删除文章栏目关系失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("删除文章栏目关系失败，请稍候再试！");
            msg.setResult(true);
            return msg;
        }
        LOGGER.info("删除文章栏目关系的业务层方法结束！");
        return msg;
    }

    @Override
    public int countArticleByParam(String articelName, String special, String channel, String articleState) {
        LOGGER.info("查询文章列表总数的业务层方法开始！");
        LOGGER.info("拿到的参数 文章名称【{}】，一级分类【{}】，二级分类【{}】，文章状态【{}】", articelName,
                special, channel, articleState);
        String companyId = "1";// 公司id
        String branchId = "86";
        CmsSysUserExample example = new CmsSysUserExample();
        CmsSysUserExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        criteria.andBranchIdLike("%" + branchId + "%");
        // 查询该渠道该公司下的后台管理用户
        List<CmsSysUser> CmsSysUserList = cmsSysUserMapper.selectByExample(example);
        List<String> userlist = new ArrayList<>();
        for (CmsSysUser c : CmsSysUserList) {
            String userId = c.getUserId();
            LOGGER.info("分公司文章列表后台管理员userId[{}]", userId);
            userlist.add(userId);
        }
        List<String> list = new ArrayList<String>();
        list.add("1");
        list.add("2");

        if (StringUtils.isBlank(articelName)) {
            articelName = "";
        }

        if (StringUtils.isBlank(special)) {
            special = "";
        }
        if (StringUtils.isBlank(channel)) {
            channel = "";
        }
        HashMap<String, Object> parMap = new HashMap<String, Object>();
        parMap.put("list", list);
        parMap.put("articelName", articelName);
        parMap.put("special", special);
        parMap.put("channel", channel);
        parMap.put("userlist", userlist);
        List<HashMap<String, Object>> resultMap = articleChannelQueryMapper.selectCountByParam(parMap);
        if (resultMap == null || resultMap.size() < 1) {
            LOGGER.info("文库暂没有文章！");
            return 0;
        }
        LOGGER.info("查询文库列表的业务层方法结束！");
        return resultMap.size();
    }

    @Override
    public Message onkeyExChange(String from, String to, String companyId, String branchId) {
        LOGGER.info("批量转换文章栏目关系的业务层方法开始！");
        String special = to.split("-")[0];
        String codeTypeTo = to.split("-")[1];
        Message msg = new Message();
        Integer fall = 0;
        Integer suc = 0;
        try {
            String[] codeType = from.split(",");
            for (int i = 0; i < codeType.length; i++) {
                ArticleChannelExample example = new ArticleChannelExample();
                example.createCriteria().andSpecialEqualTo("ARTICLE").andChannelEqualTo(codeType[i])
                        .andArticleStateEqualTo("0").andCompanyIdEqualTo(companyId).andBranchIdEqualTo(branchId);
                List<ArticleChannel> ArticleChannels = articleChannelMapper.selectByExample(example);
                for (ArticleChannel articleChannel : ArticleChannels) {
                    ArticleChannelExample example2 = new ArticleChannelExample();
                    example2.createCriteria().andArticleIdEqualTo(articleChannel.getArticleId()).andSpecialEqualTo(special).
                            andChannelEqualTo(codeTypeTo).andArticleStateEqualTo("0");
                    List<ArticleChannel> result = articleChannelMapper.selectByExample(example2);
                    if (result.size() > 0) {
                        LOGGER.info("文章：" + result.get(0).getId() + result.get(0).getArticleName() + "在" + codeTypeTo + "发布过");
                        fall++;
                    } else {
                        articleChannel.setId(null);
                        articleChannel.setUpdateDate(DateUtil.currentDate());
                        articleChannel.setUpdateTime(DateUtil.currentTime());
                        articleChannel.setSpecial(special);
                        articleChannel.setChannel(codeTypeTo);
                        if (articleChannelMapper.insertSelective(articleChannel) > 0) {
                            suc++;
                        }
                    }
                }
            }
            redisManager.delete("article_channel_" + special + "_" + codeTypeTo);
            LOGGER.info("删除原有redis文库信息成功！key:" + "article_channel_" + special + "_" + codeTypeTo);
            msg.setInfo("成功");
            msg.setResult(true);
            HashMap<String, Object> resultMap = new HashMap<>();
            resultMap.put("fall", fall);
            resultMap.put("suc", suc);
            msg.setObject(resultMap);
        } catch (Exception e) {
            LOGGER.info("添加文章关系失败【{}】", e.getMessage());
            msg.setInfo("收录失败！");
            msg.setResult(false);
            e.printStackTrace();
        }
        LOGGER.info("增加文章栏目关系的业务层方法结束！");
        return msg;
    }

    @Override
    public Message onTimeSend(String articleId, String publishId, String agentCode, String sendType, String partyId,
                              String sendTime) {
        LOGGER.info("定时推送文章的业务层方法开始！");
        LOGGER.info("接收到的参数：articleId【{}】,publishId[{}],agentCode[{}],sendType[{}],partyId[{}],sendTime[{}]",
                new Object[]{articleId, publishId, agentCode, sendType, partyId, sendTime});
        Message msg = new Message(true, "推送文章成功");
        String email = "13152783264";
        CmsSysUser cmsSysUser = this.cmsSysUserExMapper.queryLoginInfoByEmail(email);
        String userId = cmsSysUser.getUserId();
        String sysUserName = cmsSysUser.getName();
        if ((StringUtils.isBlank(articleId)) || (StringUtils.isBlank(publishId)) || StringUtils.isBlank(sendTime)) {
            LOGGER.info("设置定时文章推送失败，缺少参数");
            msg.setInfo("设置定时文章推送失败，缺少请求参数！");
            msg.setResult(false);
            throw new ParameterException("设置定时文章推送失败，缺少参数");
        }
        try {
            long currentTimeMillis = System.currentTimeMillis();
            long setTimeMillis = DateUtil.convertStringToDate(sendTime, DateUtil.YMDHMS).getTime();
            LOGGER.info("currentTimeMillis:[{}],setTimeMillis:[{}]", currentTimeMillis, setTimeMillis);
            if (currentTimeMillis >= setTimeMillis) {
                LOGGER.info("时间设置异常！");
                msg.setResult(false);
                msg.setInfo("设置时间异常");
                return msg;
            }
            ArticlePublishSend articlePublishSend = new ArticlePublishSend();
            articlePublishSend.setArticleId(Integer.parseInt(articleId));
            articlePublishSend.setPublishId(publishId);
            articlePublishSend.setOperateId(userId);
            articlePublishSend.setOperateName(sysUserName);
            articlePublishSend.setPublishDate(sendTime.split(" ")[0]);
            articlePublishSend.setPublishTime(sendTime.split(" ")[1]);
            articlePublishSend.setOperateDate(DateUtil.currentDate());
            articlePublishSend.setOperateTime(DateUtil.currentTime());
            articlePublishSend.setSendState("2");
            if ("0".equals(sendType)) {  //推送企业
                LOGGER.info("推送至企业");
                articlePublishSend.setSendType("0");
                articlePublishSend.setGroupId(partyId);
            } else { //推送个人
                LOGGER.info("推送至个人");
                articlePublishSend.setSendType("1");
                articlePublishSend.setGroupId(agentCode);
            }
            if (articlePublishSendMapper.insert(articlePublishSend) > 1) {
                LOGGER.info("记录定时推送文章信息成功，记录详情为：" + JsonUtil.objectToJackson(articlePublishSend));
                msg.setResult(true);
                msg.setInfo("文章设置定时推送成功！");
            }
        } catch (NumberFormatException e) {
            msg.setInfo("文章设置定时推送失败，请重试！");
            msg.setResult(false);
            LOGGER.info("定时推送设置失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        LOGGER.info("文章定时推送业务层方法结束！");
        return msg;
    }

    @Override
    public TableDataInfo noSendPublishArticle(String articleId, int startRow, int rows, String publishId) {
        LOGGER.info("查询未定时推送的文章列表，入参   articleId：【{}】，startRow：【{}】，rows：【{}】，publishId【{}】"
                , articleId, startRow, rows, publishId);
        HashMap<String, Object> paramMap = new HashMap<>();
        paramMap.put("articleId", articleId);
        paramMap.put("publishId", publishId);
        paramMap.put("startRow", startRow);
        paramMap.put("rows", rows);

        List<ArticlePublishSend> daoList = articleQueryMapper.noSendPublishArticle(paramMap);
        List<ArticlePublishSendDTO> resultDTO = new ArrayList<ArticlePublishSendDTO>();
        LOGGER.info("查询的结果集：" + JsonUtil.objectToJackson(daoList));
        for (ArticlePublishSend aps : daoList) {
            ArticlePublishSendDTO apsd = new ArticlePublishSendDTO();
            apsd.setId(aps.getId());
            if ("0".equals(aps.getSendType())) {
                apsd.setSendType("公司");
            } else {
                apsd.setSendType("个人");
            }
            apsd.setOperateName(aps.getOperateName());
            if (StringUtils.isBlank(aps.getOperateTime())) {
                apsd.setOperateTime(aps.getPublishDate() + " " + aps.getPublishTime());
            } else {
                apsd.setOperateTime(aps.getOperateDate() + " " + aps.getOperateTime());
            }
            apsd.setPublishTime(aps.getPublishDate() + " " + aps.getPublishTime());
            apsd.setGroupId(aps.getGroupId());


            switch (aps.getSendState()) {
                case "1":
                    apsd.setSendState("成功");
                    break;
                case "2":
                    apsd.setSendState("待推送");
                    break;
                default:
                    apsd.setSendState("失败");
                    break;
            }
            resultDTO.add(apsd);
        }
        LOGGER.info("返回列表拼接完成");
        ArticlePublishSendExample ex = new ArticlePublishSendExample();
        ex.createCriteria().andArticleIdEqualTo(Integer.parseInt(articleId)).andPublishIdEqualTo(publishId);
        int count = articlePublishSendMapper.countByExample(ex);
        TableDataInfo pageDTO = new TableDataInfo();
        pageDTO.setCode(0);
        pageDTO.setRows(resultDTO);
        pageDTO.setTotal(count);
        return pageDTO;
    }

    @Override
    public Message delOnTimeTask(String delId) {
        LOGGER.info("删除置顶定时任务业务层方法开始！入参delId：", delId);
        Message msg = new Message();
        if (StringUtils.isBlank(delId)) {
            LOGGER.info("删除失败，缺少参数");
            msg.setInfo("删除失败，缺少参数");
            msg.setResult(false);
            return msg;
        }
        try {
            int result = articlePublishSendMapper.deleteByPrimaryKey(Integer.parseInt(delId));
            msg.setInfo("删除成功");
            msg.setResult(true);
            LOGGER.info("删除定时任务返回结果：" + result);
        } catch (Exception e) {
            LOGGER.info("删除失败，业务层抛异常  【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("删除失败");
            msg.setResult(false);
            return msg;
        }
        LOGGER.info("删除文章栏目关系的业务层方法结束！");
        return msg;
    }
}
