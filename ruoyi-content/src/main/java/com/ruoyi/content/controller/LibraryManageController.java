package com.ruoyi.content.controller;


import com.ruoyi.common.core.page.PageDomain;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.common.core.page.TableSupport;
import com.ruoyi.content.domain.BaseCodeTree;
import com.ruoyi.content.domain.PageDTO;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.BaseCodeService;
import com.ruoyi.content.service.LibraryService;
import com.ruoyi.content.utils.CheckUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * 说明：文章管理
 *
 * @author Ma.C
 * @date 2018年5月2日
 */
@Controller
@RequestMapping("/article")
public class LibraryManageController {

    private String prefix = "content/article";

    private final static Logger logger = LoggerFactory.getLogger(LibraryManageController.class);

    @Autowired
    private LibraryService libraryService;
    @Autowired
    private CheckUtil checkUtil;
    @Autowired
    private BaseCodeService baseCodeService;

    @GetMapping("manage")
    public String articleList() {
        return prefix + "/articleManage";
    }

    /**
     * 获取栏目树
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/columnTree")
    @ResponseBody
    public Message columnTree(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Message msg = new Message();
        Map<String, Object> policyMap = new HashMap<String, Object>();
        String codeCode = request.getParameter("codeCode");
        // 查询栏目树
        List<BaseCodeTree> columnList = new ArrayList<BaseCodeTree>();
        columnList = baseCodeService.baseColumnTree(codeCode);
        policyMap.put("columnList", columnList);
        msg.setInfo("成功");
        msg.setObject(policyMap);
        msg.setResult(true);
        return msg;
    }

    /**
     * 查询文库信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/libraryArry")
    @ResponseBody
    public TableDataInfo libraryArry(HttpServletRequest request, HttpServletResponse response) {
        logger.info("查询文库列表的控制层方法开始！");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        TableDataInfo pageDTO = new TableDataInfo();
        try {
            PageDomain pageDomain = TableSupport.buildPageRequest();
            Integer pageSize = pageDomain.getPageSize();
            Integer pageNum = (pageDomain.getPageNum() - 1) * pageSize;
            String articelName = request.getParameter("articelName");
            String articleThirdTypeInit = request.getParameter("articleThirdTypeInit");
            String channel = request.getParameter("channel");
            String special = request.getParameter("special");
            String articleState = request.getParameter("articleState");
            if(articleThirdTypeInit != null && !"".equals(articleThirdTypeInit.trim())) {
                channel = articleThirdTypeInit;
            }else {
                special = channel;
            }
            List<HashMap<String, Object>> list = libraryService.queryLibrary(pageNum, pageSize, articelName,
                    special, channel, articleState);
            //int count = libraryService.countArticleInfoByState(articelName,special, channel, articleState);
            int count = libraryService.countArticleByParam(articelName, special, channel, articleState);
            pageDTO.setCode(0);
            pageDTO.setRows(list);
            pageDTO.setTotal(count);
        } catch (Exception e) {
            logger.info("查询当前用户发布的文章失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("查询文章列表的控制层方法结束！");
        return pageDTO;
    }

    /**
     * 更新文章栏目关系
     *
     * @param request
     * @param response
     * @return
     */
/*	@RequestMapping("/addLibrary")
	@ResponseBody
	public Message articleUp(HttpServletRequest request, HttpServletResponse response) {
		logger.info("给文章添加栏目的控制层方法开始！");
		response.setHeader("Access-Control-Allow-Origin", "*");
		Message resultMsg = new Message();
		Thread.currentThread().setName(UUID.randomUUID().toString());
		CmsSysUser userInfoDTO = (CmsSysUser) SecurityUtils.getSubject().getPrincipal();
		String companyId = userInfoDTO.getCompanyId();
		String branchId = userInfoDTO.getBranchId();
		logger.info("获取修改文章信息页面参数！");
		String channelId = request.getParameter("channelId");
		String articleInfo = request.getParameter("articleInfo");
		logger.info("保存的文章列表是："+articleInfo);
		JsonArray jsonArray = new JsonParser().parse(articleInfo).getAsJsonArray();
		logger.info("jsonArray:"+jsonArray);
		int successNum = 0;
		try {
			for (JsonElement ele : jsonArray) {
				JsonObject obj = ele.getAsJsonObject();
				logger.info("单个保存参数信息：articleId：【{}】，publishId：【{}】，articleName：【{}】",obj.get("articleId").toString(),
						obj.get("publishId").toString(),obj.get("articleName").toString());
				//判断是业务员还是管理员创建的文章
	//			int count = libraryService.checkCreateUser(articleId,originalUrl,createUser);
	//			if(count>0) {
	//				logger.info("爬取业务员文章url失败");
	//			}
				//根据栏目id去查一二级栏目
				BaseCode baseCode = new BaseCode();
				baseCode = libraryService.queryBaseCodeByChannelId(channelId);	
	//			String publishId = userInfoDTO.getUserId() + "a" + articleId;
				//保存在文库表
				ArticleChannel articleChannel = new ArticleChannel();
				articleChannel.setArticleId(Integer.valueOf(obj.get("articleId").getAsInt()));
				articleChannel.setPublishId(obj.get("publishId").getAsString());
				articleChannel.setArticleName(obj.get("articleName").getAsString());
				articleChannel.setSpecial(baseCode.getCodeType());
				articleChannel.setChannel(baseCode.getCodeCode());
				articleChannel.setCompanyId(companyId);
				articleChannel.setBranchId(branchId);
				articleChannel.setPublishDate(DateUtil.currentDate());
				articleChannel.setPublishTime(DateUtil.currentTime());
				articleChannel.setUpdateDate(DateUtil.currentDate());
				articleChannel.setUpdateTime(DateUtil.currentTime());
				articleChannel.setArticleState("0");
				Message msg = libraryService.addLibrary(articleChannel);
				if(msg.getResult()) {
					successNum++;
				}
			}
			if(successNum==0) {
				resultMsg.setResult(false);
				resultMsg.setInfo("收录失败！已有其他版本的文章被收录到同一栏目下！");
			}else {
				resultMsg.setInfo("添加成功！");
				resultMsg.setResult(true);
				HashMap<String, Object> resultMap = new HashMap<String, Object>();
				resultMap.put("successNum", successNum);
				resultMap.put("failed", jsonArray.size()-successNum);
				resultMsg.setObject(resultMap);
			}
		} catch (Exception e) {
			e.printStackTrace();
			logger.info("修改文章信息失败【{}】" , e.getMessage());
		}
		logger.info("修改文章信息的控制层方法结束！");
		return resultMsg;
	}*/


    /**
     * 更新文章栏目关系
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/addLibrary")
    @ResponseBody
    public Message articleUp(HttpServletRequest request, HttpServletResponse response) {
        logger.info("给文章添加栏目的控制层方法开始！");
        response.setHeader("Access-Control-Allow-Origin", "*");
        Message msg = new Message();
        Thread.currentThread().setName(UUID.randomUUID().toString());
        String companyId = "1";
        String branchId = "86";
        String channelId = request.getParameter("channelId");
        String articleInfoList = request.getParameter("articleInfo");
        logger.info("批量收录文章控制层获取接口入参参数：companyId：【{}】，branchId：【{}】，channelId：【{}】，articleInfoList：【{}】", companyId,
                branchId, channelId, articleInfoList);
        msg = libraryService.addLibrary(companyId, branchId, channelId, articleInfoList);
        logger.info("修改文章信息的控制层方法结束！");
        return msg;
    }

    @RequestMapping("/removeArticleItem")
    @ResponseBody
    public Message removeArticleItem(HttpServletRequest request, HttpServletResponse response) {
        logger.info("批量移除栏目下文章控制层方法开始！");
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        String companyId = "1";
        String branchId = "86";
        String channelId = request.getParameter("channelId");
        String articleInfoList = request.getParameter("articleInfo");
        logger.info("批量收录文章控制层获取接口入参参数：companyId：【{}】，branchId：【{}】，channelId：【{}】，articleInfoList：【{}】", companyId,
                branchId, channelId, articleInfoList);
        msg = libraryService.removeArticleItem(companyId, branchId, channelId, articleInfoList);
        logger.info("修改文章信息的控制层方法结束！");
        return msg;
    }

    /**
     * @Title: changeSate
     * @Description: TODO(文章发布)
     */
    @RequestMapping("/changeSate")
    @ResponseBody
    public Message changeSate(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        logger.info("文章发布控制层开始!");
        //修改文章状态
        String ids = request.getParameter("ids");//文章Id
        String eState = request.getParameter("eState");
        String sState = request.getParameter("sState");
        String publishId = request.getParameter("publishId");
        try {
            msg = libraryService.changeSate(ids, eState, sState, publishId);
//			logger.info("开始调用消息推送方法！");
//			templateSendService.newArticleSend(companyId);
//			logger.info("结束调用消息推送!");
        } catch (Exception e) {
            logger.info("发布文章失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("文章发布控制层结束!");
        return msg;
    }


    /**
     * 删除文章信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delLibrary")
    @ResponseBody
    public Message delLibrary(HttpServletRequest request, HttpServletResponse response) {
        logger.info("删除文章信息的控制层方法开始！");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        String id = request.getParameter("ids");
        try {
            msg = libraryService.delArticle(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除文章信息失败【{}】", e.getMessage());
        }
        logger.info("删除文章信息的控制层方法结束！");
        return msg;
    }


    @RequestMapping("/preview")
    @ResponseBody
    public Message preview(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        logger.info("文章预览控制层开始!");
        //获取文章浏览详情
        String publishId = request.getParameter("publishId");//文章Id
        logger.info("预览接收到的publishId[{}]", publishId);
        msg = libraryService.queryEditUrlByPublishId(publishId);
        logger.info("文章预览控制层结束【{}】!", msg);
        return msg;
    }


    /**
     * @Title: articleSend
     * @Description: TODO(文章推送)
     */
    @RequestMapping("/articleSend")
    @ResponseBody
    public Message articleSend(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        String openId = "o43yZt9cXHtRwvNF8ZdpGw9r0RGI";
        logger.info("文章推送控制层开始!");
        //修改文章状态
        String articleId = request.getParameter("articleId");//文章Id
        String publishId = request.getParameter("publishId");
        String agentCode = request.getParameter("agentCode");
        String sendType = request.getParameter("sendType"); //推送方式 0：推送企业号/个人，1：复制链路
        String partyId = request.getParameter("partyId");
        String data = publishId + "-" + partyId;
        if (StringUtils.isNotBlank(partyId) && checkUtil.isRepeat(openId, "articleSend", data)) {
            logger.info("openId【{}】文章在30秒内重复推送！data【{}】", openId, data);
            msg.setInfo("文章已推送");
            msg.setResult(false);
            return msg;
        }
        try {
            msg = libraryService.articleSend(articleId, publishId, agentCode, sendType, partyId, null);
        } catch (Exception e) {
            logger.info("发布文章失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("文章发布控制层结束!");
        return msg;
    }


    /**
     * @Title: onTimeSend
     * @Description: TODO(文章定时推送)
     */
    @RequestMapping("/onTimeSend")
    @ResponseBody
    public Message onTimeSend(HttpServletRequest request, HttpServletResponse response) {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        logger.info("文章定时推送控制层开始!");
        //修改文章状态
        String articleId = request.getParameter("articleId");
        String publishId = request.getParameter("publishId");
        String agentCode = request.getParameter("agentCode");
        String sendType = request.getParameter("sendType");
        String partyId = request.getParameter("partyId");
        String sendTime = request.getParameter("sendTime");
        try {
            msg = libraryService.onTimeSend(articleId, publishId, agentCode, sendType, partyId, sendTime);
        } catch (Exception e) {
            logger.info("定时发布文章失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("文章定时推送控制层结束!");
        return msg;
    }


    /**
     * 更新文库信息进行排序
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/updateLibrary")
    @ResponseBody
    public Message updateLibrary(HttpServletRequest request, HttpServletResponse response) {
        logger.info("更新文库信息的控制层方法开始！");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        String id = request.getParameter("id");
        String orderNo = request.getParameter("orderNo");
        try {
            msg = libraryService.updateLibrary(id, orderNo);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("更新文库信息失败【{}】", e.getMessage());
        }
        logger.info("更新文库信息的控制层方法结束！");
        return msg;
    }


    /**
     * 删除文章信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delLibraryHTML")
    @ResponseBody
    public Message delLibraryHTML(HttpServletRequest request, HttpServletResponse response) {
        logger.info("删除文章信息的控制层方法开始！");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        String id = request.getParameter("ids");
        try {
            msg = libraryService.delLibraryHTML(id);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除文章信息失败【{}】", e.getMessage());
        }
        logger.info("删除文章信息的控制层方法结束！");
        return msg;
    }


    /**
     * 批量转换文章和栏目的关系
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/onkeyExChange")
    @ResponseBody
    public Message onkeyExChange(HttpServletRequest request, HttpServletResponse response) {
        logger.info("给文章转换栏目的控制层方法开始！");
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());

        Message msg = new Message();
        String companyId = "1";
        String branchId = "86";
        String from = request.getParameter("from");
        String to = request.getParameter("to");
        logger.info("接收的请求参数：from:【{}】,to【{}】", from, to);
        try {
            msg = libraryService.onkeyExChange(from, to, companyId, branchId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("修改文章信息失败【{}】", e.getMessage());
        }
        logger.info("修改文章信息的控制层方法结束！");
        return msg;
    }

    /**
     * 查询待推送的文章信息
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/noSendPublishArticle")
    @ResponseBody
    public PageDTO noSendPublishArticle(HttpServletRequest request, HttpServletResponse response) {
        logger.info("查询未推送出去的文章列表！");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        PageDTO pageDTO = new PageDTO();
        try {
            String rowsVal = request.getParameter("rows");
            String page = request.getParameter("page");
            int rows = Integer.parseInt(rowsVal);
            int startRow = rows * (Integer.parseInt(page) - 1);
            String articleId = request.getParameter("articleId");
            String publishId = request.getParameter("publishId");
            // 1.根据文章id去查publish表，找出该文章对应所有业务员
            pageDTO = libraryService.noSendPublishArticle(articleId, startRow, rows, publishId);
            pageDTO.setPage(Integer.parseInt(page));

            return pageDTO;
        } catch (Exception e) {
            logger.info("系统异常！", e);
            return pageDTO;
        }
    }


    /**
     * 批量转换文章和栏目的关系
     *
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("/delOnTimeTask")
    @ResponseBody
    public Message delOnTimeTask(HttpServletRequest request, HttpServletResponse response) {
        logger.info("删除未执行的定时任务开始！");
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        String delId = request.getParameter("delId");
        logger.info("接收的请求参数：delId:【{}】", delId);
        try {
            msg = libraryService.delOnTimeTask(delId);
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("删除未执行的定时任务失败【{}】", e.getMessage());
        }
        logger.info("删除未执行的定时任务控制层方法结束！");
        return msg;
    }


}
