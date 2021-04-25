package com.ruoyi.content.controller;

import com.ruoyi.content.domain.ArticleLabel;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.ArticleLabelService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Controller
@RequestMapping("/label")
public class ArticleLabelController {

    private final static Logger logger = LoggerFactory.getLogger(ArticleManageController.class);

    @Autowired
    private ArticleLabelService articleLabelService;

    /**
     * 查询所有标签信息(本程序调用)
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/queryLabel")
    @ResponseBody
    public Message queryLabel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        logger.info("查询所有标签信息控制层方法开始");
        Message msg = new Message();
        Map<String, Object> labelMap = new HashMap<String, Object>();
        // 查询标签表
        List<ArticleLabel> labelList = articleLabelService.queryLabel();
        if (null != labelList && labelList.size() > 0) {
            labelMap.put("labelList", labelList);
            msg.setInfo("查询标签成功");
            msg.setObject(labelMap);
            msg.setResult(true);
        } else {
            labelMap.put("labelList", labelList);
            msg.setInfo("查询标签失败");
            msg.setObject(labelMap);
            msg.setResult(false);
        }

        logger.info("查询所有标签信息控制层方法结束");
        return msg;
    }

//	/**
//	 * 根据公司id查询查询标签信息(远端OSS调用)
//	 * @param request
//	 * @param response
//	 * @param companyId
//	 * @return
//	 */
//	@RequestMapping("/queryLabelOss")
//	@ResponseBody
//	public Message queryLabelOss(HttpServletRequest request,HttpServletResponse response,String companyId) {
//		response.setHeader("Access-Control-Allow-Origin", "*");
//		Thread.currentThread().setName(UUID.randomUUID().toString());
//		logger.info("查询所有标签信息控制层方法开始,公司companyId【{}】",companyId);
//		Message msg = new Message();
//		Map<String, Object> labelMap = new HashMap<String, Object>();
//		// 标签列表信息
//		List<ArticleLabel> labelList = new ArrayList<ArticleLabel>();
//		labelList = articleLabelService.queryLabelOss(companyId);
//		if(null != labelList && labelList.size() > 0 ) {
//			labelMap.put("labelList", labelList);
//			msg.setInfo("查询标签成功");
//			msg.setObject(labelMap);
//			msg.setResult(true);
//		} else {
//			labelMap.put("labelList", labelList);
//			msg.setInfo("查询标签失败");
//			msg.setObject(labelMap);
//			msg.setResult(false);
//		}
//		logger.info("查询标签信息控制层方法结束");
//		return msg;
//		
//	}

    /**
     * 保存文章的标签信息
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("/addlabel")
    @ResponseBody
    public Message saveLabel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        logger.info("保存文章标签信息控制层方法开始");
        String articleId = request.getParameter("articleId"); // 文章id
        String labelIds = request.getParameter("labelIds"); // 标签id
        logger.info("要添加标签的文章articleId【{}】,标签labelId【{}】", articleId, labelIds);
        // 文章ID和标签ID
        if (StringUtils.isBlank(articleId) || StringUtils.isBlank(labelIds)) {
            logger.info("给文章添加标签缺少参数,articleId【{}】,labelIds【{}】", articleId, labelIds);
        }
        msg = articleLabelService.addlabel(articleId, labelIds);
        logger.info("保存文章标签信息控制层方法结束");
        return msg;
    }

    /**
     * 新增标签信息
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Message addLabel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        logger.info("新增标签信息控制层方法开始");
        String labelName = request.getParameter("name"); // 标签名称
        logger.info("标签labelName【{}】", labelName);
        // 文章ID和标签ID
        if (StringUtils.isBlank(labelName)) {
            logger.info("新增标签缺少参数,labelName【{}】", labelName);
            msg.setResult(false);
            msg.setInfo("labelName不能为空");
            return msg;
        }
        msg = articleLabelService.saveLabel(labelName);
        logger.info("新增标签信息控制层方法结束");
        return msg;
    }

    /**
     * 修改标签信息
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Message updateLabel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        logger.info("修改标签信息控制层方法开始");
        String labelId = request.getParameter("id"); // 标签id
        String labelName = request.getParameter("name"); // 标签名称
        logger.info("标签labelName【{}】", labelName);
        // 文章ID和标签ID
        if (StringUtils.isBlank(labelId)) {
            logger.info("修改标签缺少参数,labelId【{}】", labelId);
            msg.setResult(false);
            msg.setInfo("labelId不能为空");
            return msg;
        }
        if (StringUtils.isBlank(labelName)) {
            logger.info("修改标签缺少参数,labelName【{}】", labelName);
            msg.setResult(false);
            msg.setInfo("labelName不能为空");
            return msg;
        }
        msg = articleLabelService.updateLabel(Integer.valueOf(labelId), labelName);
        logger.info("修改标签信息控制层方法结束");
        return msg;
    }

    /**
     * 删除标签信息
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(method = RequestMethod.DELETE)
    @ResponseBody
    public Message deleteLabel(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setHeader("Access-Control-Allow-Origin", "*");
        Thread.currentThread().setName(UUID.randomUUID().toString());
        Message msg = new Message();
        logger.info("删除标签信息控制层方法开始");
        String labelId = request.getParameter("id"); // 标签名称
        logger.info("标签labelId【{}】", labelId);
        // 文章ID和标签ID
        if (StringUtils.isBlank(labelId)) {
            logger.info("删除标签缺少参数,labelId【{}】", labelId);
            msg.setResult(false);
            msg.setInfo("labelId不能为空");
            return msg;
        }
        msg = articleLabelService.deleteLabel(Integer.valueOf(labelId));
        logger.info("删除标签信息控制层方法结束");
        return msg;
    }

}
