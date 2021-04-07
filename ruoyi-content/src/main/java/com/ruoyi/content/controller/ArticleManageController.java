package com.ruoyi.content.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.content.domain.ArticleInfo;
import com.ruoyi.content.domain.PageDTO;
import com.ruoyi.content.domain.PublishedArticleInfo;
import com.ruoyi.content.exception.BusinessException;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.ArticleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * 说明：文章管理
 *
 * @author Ma.C
 * @date 2018年5月2日
 */
@Controller
@RequestMapping("/article")
public class ArticleManageController extends BaseController {

    private final static Logger logger = LoggerFactory.getLogger(ArticleManageController.class);
    private String prefix = "content/article";

    @Autowired
    private ArticleService articleService;

    @GetMapping("list")
    public String articleList() {
        return prefix + "/articleList";
    }

    /**
     * 查询所有公司发布的文章信息
     *
     * @param request
     * @return
     */
    @RequestMapping("/articleArry")
    @ResponseBody
    public TableDataInfo articleArry(HttpServletRequest request) throws Exception {
        logger.info("查询文章列表的控制层方法开始！");
        String articelName = request.getParameter("articleName");
        String articelAuthor = request.getParameter("articelAuthor");
        String channelId = request.getParameter("channelId");
        String special = request.getParameter("special");
        String articleState = request.getParameter("articleState");
        startPage();
        List<PublishedArticleInfo> list = articleService.queryArticle(articelName, articelAuthor,
                special, channelId, articleState);
        logger.info("查询文章列表的控制层方法结束！");
        return getDataTable(list);
    }

    /**
     * 更新文章基本信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/articleUp")
    @ResponseBody
    public Message articleUp(HttpServletRequest request, HttpServletResponse response) {
        logger.info("修改文章信息的控制层方法开始！");
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        logger.info("获取修改文章信息页面参数！");

        String articleId = request.getParameter("articleId");
        String articleName = request.getParameter("articleName");
        String articleAuthor = request.getParameter("articleAuthor");
        String special = request.getParameter("special");
        String channelId = request.getParameter("channelId");
        /* String articleState = request.getParameter("articleState"); */
        String shareTitle = request.getParameter("shareTitle");
        String shareDes = request.getParameter("shareDes");
        String originalUrl = request.getParameter("originalUrl");
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo = articleService.queryArticleByArticleId(articleId);
        articleInfo.setArticleAuthor(articleAuthor);
        articleInfo.setArticleName(articleName);
        articleInfo.setSpecial(special);
        articleInfo.setChannelId(channelId);
        /* articleInfo.setArticleState(articleState); */
        articleInfo.setShareTitle(shareTitle);
        articleInfo.setShareDes(shareDes);
        articleInfo.setOriginalUrl(originalUrl);
        articleInfo.setUpdateDate(DateUtils.getDate());
        articleInfo.setUpdateTime(DateUtils.getTimeNow());
        try {
            msg = articleService.articleUpByArticleId(articleInfo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("修改文章信息失败【{}】", e.getMessage());
        }
        logger.info("修改文章信息的控制层方法结束！");
        return msg;
    }

    /**
     * 文章列表的删除功能
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delArticleInfo")
    @ResponseBody
    public Message delArticleInfo(HttpServletRequest request, HttpServletResponse response) {
        logger.info("删除文章信息的控制层方法开始！");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        String id = request.getParameter("ids");
        try {
            msg = articleService.delArticleInfo(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除文章信息失败【{}】", e.getMessage());
        }
        logger.info("删除文章信息的控制层方法结束！");
        return msg;
    }

    /**
     * 通过文章链接获取文章内容
     *
     * @return
     */
    @RequestMapping("/getArticleContentByUrl")
    @ResponseBody
    public Message getArticleContentByUrl(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        logger.info("通过文章链接获取文章内容控制层方法开始");
//        String createUser = request.getParameter("createUser");
        String createUser = "13152783264";
        if (StringUtils.isBlank(createUser)) {
            logger.info("用户已离线");
            throw new BusinessException("通过文章链接获取文章内容,用户已离线");
        }
        String originalUrl = request.getParameter("originalUrl"); // 文章连接,如果是通过连接导入文章,则必传
//        String author = request.getParameter("author"); // 作者
        String author = "13152783264";
        String ids = request.getParameter("ids"); // 广告ids
        String isAuthorization = request.getParameter("isAuthorization"); // 是否授权
        String isReserve = request.getParameter("isReserve"); // 是否预约
        String automaticName = request.getParameter("automaticName");// 自定义名
        String introduction = request.getParameter("introduction"); // 介绍
        String isJoinActive = request.getParameter("isJoinActive");// 是否添加活动
        String labelIds = request.getParameter("labelIds");
        logger.info(
                "创建文章的控制层方法开始!传入参数originalUrl[{}], author[{}], createUser[{}],ids[{}],isAuthorization[{}],isReserve[{}],automaticName[{}],introduction[{}],labelIds[{}],isJoinActive[{}]",
                new Object[]{originalUrl, author, createUser, ids, isAuthorization, isReserve, automaticName,
                        introduction, labelIds, isJoinActive});
        if (StringUtils.isBlank(originalUrl)) {
            logger.info("创建文章内容参数为空!");
            msg.setInfo("请粘微信文章地址");
            msg.setResult(false);
            logger.info("通过文章链接获取文章内容控制层方法结束");
            return msg;
        }
        try {
            msg = this.articleService.getArticleContentByUrl(originalUrl, createUser, author, ids, isAuthorization,
                    isReserve, automaticName, introduction, labelIds, isJoinActive);
        } catch (Exception e) {
            logger.info("创建文章失败【{}】", e.getMessage());
            msg.setInfo("创建文章异常");
            msg.setResult(false);
            e.printStackTrace();
        }
        logger.info("通过文章链接获取文章内容控制层方法结束");
        return msg;
    }

    /**
     * 通过文章链接获取文章内容
     *
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public Message getArticleContent(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        logger.info("通过文章链接获取文章内容控制层方法开始");
        String createUser = request.getParameter("createUser");
        if (StringUtils.isBlank(createUser)) {
            logger.info("用户已离线");
            throw new BusinessException("通过文章链接获取文章内容,用户已离线");
        }
        String author = request.getParameter("author"); // 作者
        String ids = request.getParameter("ids"); // 广告ids
        String isAuthorization = request.getParameter("isAuthorization"); // 是否授权
        String isReserve = request.getParameter("isReserve"); // 是否预约
        String automaticName = request.getParameter("automaticName");// 自定义名
        String introduction = request.getParameter("introduction"); // 介绍
        String isJoinActive = request.getParameter("isJoinActive");// 是否添加活动
        String labelIds = request.getParameter("labelIds");
        String articleName = request.getParameter("articleName");
        String shareImgUrl = request.getParameter("shareImgUrl");
        String shareDes = request.getParameter("shareDes");
        String articleContent = request.getParameter("articleContent");
        logger.info(
                "创建文章的控制层方法开始!传入参数author[{}], createUser[{}],ids[{}],isAuthorization[{}],isReserve[{}],automaticName[{}],introduction[{}],labelIds[{}],isJoinActive[{}]",
                new Object[]{author, createUser, ids, isAuthorization, isReserve, automaticName, introduction,
                        labelIds, isJoinActive});
        try {
            msg = this.articleService.getArticleContent(createUser, author, ids, isAuthorization, isReserve,
                    automaticName, introduction, labelIds, isJoinActive, articleName, shareImgUrl, shareDes,
                    articleContent);
        } catch (Exception e) {
            logger.info("创建文章失败【{}】", e.getMessage());
            msg.setInfo("创建文章异常");
            msg.setResult(false);
            e.printStackTrace();
        }
        logger.info("通过文章链接获取文章内容控制层方法结束");
        return msg;
    }

//	/**
//	 * 发布文章(第一次发布)
//	 * @return
//	 */
//	@RequestMapping("/publishArticle")
//	@ResponseBody
//	public Message publishArticle(HttpServletRequest request,HttpServletResponse response){
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		Thread.currentThread().setName(UUID.randomUUID().toString());
//		logger.info("第一次发布文章的控制层方法开始");
//		CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
//		String author = userInfoDTO.getName();
//		String createUser = userInfoDTO.getEmail();		//文章创建者（后台管理员）
//		String companyId = userInfoDTO.getDepartmentId();//管理员公司id
//		
//		if (StringUtils.isBlank(createUser)) {
//			throw new BusinessException("您已离线,请重新登陆");
//		}
//		Message msg = new Message();
//		String articleContent = request.getParameter("articleContent");		//文章内容
//		String articleName = request.getParameter("articleName");			//文章标题
//		String originalUrl = request.getParameter("originalUrl");			//文章链接
//		String shareImgUrl = request.getParameter("shareImgUrl");			//分享展示图片
//		String shareTitle = request.getParameter("shareTitle");				//分享标题
//		String shareDes = request.getParameter("shareDes");              	//分享描述
//		String listPicUrl = request.getParameter("listPicUrl");				//文章列表显示图片url
////		String cardId = request.getParameter("cardId");						//名片id
//		String adId = request.getParameter("adId");							//广告id
//		
//		//广告id集合
//		ArticleInfo article = new ArticleInfo();
//		article.setArticleAuthor(author);
//		article.setArticleName(articleName);
//		article.setOriginalUrl(originalUrl);
//		article.setShareImgUrl(shareImgUrl);
//		article.setShareTitle(shareTitle);
//		article.setShareDes(shareDes);
//		article.setListPicUrl(listPicUrl);
//		article.setCreateUser(createUser);
//		article.setCompanyId(companyId);
//		
//		logger.info("第一次发布文章请求参数,articleName[{}],originalUrl[{}],shareImgUrl[{}],shareTitle[{}],shareDes[{}],listPicUrl[{}],createUser[{}],companyId[{}]",
//				articleName,originalUrl,shareImgUrl,shareTitle,shareDes,listPicUrl,createUser,companyId);
//		Map<String, Object> requestMap = new HashMap<String ,Object>();
//		requestMap.put("article", article);
//		requestMap.put("articleContent", articleContent);
//		requestMap.put("adId", adId);
//		if (StringUtils.isBlank(articleContent) || StringUtils.isBlank(articleName) || StringUtils.isBlank(originalUrl) || 
//				StringUtils.isBlank(shareImgUrl) || StringUtils.isBlank(shareTitle) || StringUtils.isBlank(listPicUrl)|| StringUtils.isBlank(companyId)|| StringUtils.isBlank(createUser)) {
//			logger.info("保存文章失败,缺少参数");
//			msg.setInfo("第一次发布文章,缺少请求参数");
//			msg.setResult(false);
//			return msg;
//		}
//		//根据链接获取的文章
//		
//		msg = articleService.publishArticle(requestMap);
//		logger.info("发布文章的控制层方法结束");
//		return msg;
//	}

    /**
     * 保存(更新)文章静态页面 @Title: updateArticle @Description: TODO() @param @return
     * 参数 @return Message 返回类型 @throws
     */
    @CrossOrigin(allowCredentials = "true", allowedHeaders = "*", origins = "*", maxAge = 1800)
    @RequestMapping("/updateArticle")
    @ResponseBody
    public Message updateArticle(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        logger.info("更新已发布文章的控制层方法开始");
        Message msg = new Message();
        String articleId = request.getParameter("articleId"); // 文章id
        String articleContent = request.getParameter("articleContent"); // 文章内容
        String shareTitle = request.getParameter("shareTitle"); // 分享标题
        String shareImgUrl = request.getParameter("shareImgUrl");
        String shareDes = request.getParameter("shareDes");
        String adId = request.getParameter("adId"); // 广告id
        String companyId = request.getParameter("companyId"); // 公司id
        String publishId = request.getParameter("publishId"); // 发布id
        String isAuthorization = request.getParameter("isAuthorization");
        String isReserve = request.getParameter("isReserve");
        String isJoinActive = request.getParameter("isJoinActive");
        String automaticName = request.getParameter("automaticName");// 自定义名
        String introduction = request.getParameter("introduction"); // 介绍
        logger.info(
                "调用生成静态页面参数articleId[{}],shareTitle[{}],shareImgUrl[{}],shareDes[{}],publishId[{}],companyId[{}],isAuthorization[{}],isReserve[{}],automaticName[{}],introduction[{}],isJoinActive[{}]",
                articleId, shareTitle, shareImgUrl, shareDes, publishId, companyId, isAuthorization, isReserve,
                automaticName, introduction, isJoinActive);
        // 文章名称
        if (StringUtils.isBlank(articleId) && articleId == null) {
            logger.info("更新已发布的文章缺少参数,articleId[{}]", articleId);
            msg.setInfo("跟新已发布的文章缺少参数");
            msg.setResult(false);
            return msg;
        }
        logger.info("要更新的文章aritcleId[{}]", Integer.valueOf(articleId));
        ArticleInfo article = new ArticleInfo();
        article.setArticleId(Integer.valueOf(articleId));
        article.setShareImgUrl(shareImgUrl);
        article.setShareDes(shareDes);
        article.setShareTitle(shareTitle);

        Map<String, Object> requestMap = new HashMap<String, Object>();
        requestMap.put("article", article);
        requestMap.put("articleContent", articleContent);
        requestMap.put("adId", adId);
        requestMap.put("companyId", companyId);
        requestMap.put("publishId", publishId);
        requestMap.put("isAuthorization", isAuthorization);
        requestMap.put("isReserve", isReserve);
        requestMap.put("isJoinActive", isJoinActive);
        requestMap.put("automaticName", automaticName);
        requestMap.put("introduction", introduction);
        msg = articleService.savePulishedArticle(requestMap);
        logger.info("更新已发布文章的控制层方法结束");
        return msg;
    }

//	/**
//	 * 1.2 获取个人已经发布过的文章内容
//	* @Title: getArticleContentByPublish
//	* @Description: TODO()
//	* @param @return    参数
//	* @return Message    返回类型
//	* @throws
//	 */
//	@RequestMapping("/getArticleContentByPublish")
//	@ResponseBody
//	public Message getArticleContentByPublish(HttpServletRequest request,HttpServletResponse response) {
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		Thread.currentThread().setName(UUID.randomUUID().toString());
//		logger.info("获取个人已经发布过的文章内容控制层方法开始");
//		Message msg = new Message();
//		String userId = request.getParameter("userId");
//		if (StringUtils.isBlank(userId)) {
//			logger.info("用户已离线");
//			throw new BusinessException("通过文章链接获取文章内容,用户已离线");
//		}
////		String articleId = request.getParameter("articleId");		//文章id
//		String publishId = request.getParameter("publishId");		//发布id
//		msg = articleService.getArticleContentByPublish(publishId, userId);
//		logger.info("获取个人已经发布过的文章内容控制层方法结束");
//		return msg;
//	}

    /**
     * @Title: queryPublishedDetails
     * @Description: TODO(获取文章浏览详情)
     */
    @RequestMapping("/queryPublishedDetails")
    @ResponseBody
    public Message queryPublishedDetails(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        logger.info("文章浏览详情控制层开始!");
        // 获取文章浏览详情
        String userId = request.getParameter("userId");
        String articleId = request.getParameter("articleId");// 文章Id
        msg = articleService.queryPublishedDetails(userId, articleId);
        logger.info("文章浏览详情控制层结束!");
        return msg;
    }

//	/**
//	* @Title: changeSate
//	* @Description: TODO(文章发布)
//	 */
//	@RequestMapping("/changeSate")
//	@ResponseBody
//	public Message changeSate(HttpServletRequest request,HttpServletResponse response){
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		Thread.currentThread().setName(UUID.randomUUID().toString());
//		Message msg = new Message();
//		logger.info("文章发布控制层开始!");
//		//修改文章状态
//		String articleId = request.getParameter("ids");//文章Id
//		try {
//			msg = articleService.changeSate(articleId);
//		} catch (Exception e) {
//			logger.info("发布文章失败【{}】",e.getMessage());
//			e.printStackTrace();
//		}
//		logger.info("文章发布控制层结束!");
//		return msg;
//	}

    /**
     * 查询当前公司发布的文章信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/articleBranchIdArry")
    @ResponseBody
    public PageDTO articleBranchIdArry(HttpServletRequest request, HttpServletResponse response) {
        logger.info("查询文章列表的控制层方法开始！");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        PageDTO pageDTO = new PageDTO();
        try {
            String rowsVal = request.getParameter("rows");
            String page = request.getParameter("page");
            int rows = Integer.parseInt(rowsVal);
            int startRow = rows * (Integer.parseInt(page) - 1);
            // 1.根据登录信息查哪个公司登录
            String companyId = "1";
            String branchId = "86";
            pageDTO = articleService.queryBranchIdArticle(companyId, branchId, startRow, rows);
            pageDTO.setPage(Integer.parseInt(page));
            pageDTO.setPage(Integer.parseInt(page));
        } catch (Exception e) {
            logger.info("查询当前用户发布的文章失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("查询文章列表的控制层方法结束！");
        return pageDTO;
    }

    /**
     * 创建文章的删除功能
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delArticle")
    @ResponseBody
    public Message delArticle(HttpServletRequest request, HttpServletResponse response) {
        logger.info("创建文章的删除功能的控制层方法开始！");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        String id = request.getParameter("ids");
        try {
            msg = articleService.delArticle(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("创建文章的删除功能失败【{}】", e.getMessage());
        }
        logger.info("创建文章的删除功能的控制层方法结束！");
        return msg;
    }

    /**
     * @Title: articleUrlCopy
     * @Description: TODO(生成文章链接)
     */
    @RequestMapping("/articleUrlCopy")
    @ResponseBody
    public Message articleUrlCopy(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        logger.info("生成文章链接控制层开始!");
        try {
            String articleId = request.getParameter("articleId");// 文章Id
            String publishId = request.getParameter("publishId");
            String userId = "24";

            msg = this.articleService.articleUrlCopy(articleId, userId, publishId);
        } catch (Exception e) {
            logger.info("生成文章链接失败【{}】", e.getMessage());
        }
        logger.info("生成文章链接控制层结束!");
        return msg;
    }

    /**
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delHTML")
    @ResponseBody
    public Message delHTML(HttpServletRequest request, HttpServletResponse response) {
        logger.info("创建文章的删除HTML的控制层方法开始！");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        String id = request.getParameter("ids");
        try {
            msg = this.articleService.delHTML(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("创建文章的删除HTML功能失败【{}】", e.getMessage());
        }
        logger.info("创建文章的删除HTML功能的控制层方法结束！");
        return msg;
    }

}
