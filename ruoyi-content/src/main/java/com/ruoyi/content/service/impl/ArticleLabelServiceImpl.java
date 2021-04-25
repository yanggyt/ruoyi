package com.ruoyi.content.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.content.domain.ArticleLabel;
import com.ruoyi.content.domain.ArticleLabelExample;
import com.ruoyi.content.domain.ArticleLabelRelationship;
import com.ruoyi.content.domain.ArticleLabelRelationshipExample;
import com.ruoyi.content.mapper.ArticleLabelMapper;
import com.ruoyi.content.mapper.ArticleLabelRelationshipMapper;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.ArticleLabelService;
import com.ruoyi.content.utils.JsonUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ArticleLabelServiceImpl implements ArticleLabelService {
    private static final Logger logger = LoggerFactory.getLogger(ArticleLabelServiceImpl.class);
    @Autowired
    private ArticleLabelMapper articleLabelMapper;
    @Autowired
    ArticleLabelRelationshipMapper articleLabelRelationshipMapper;

    @Override
    public List<ArticleLabel> queryLabel() {
        logger.info("查询标签的方法开始");
        List<ArticleLabel> list = null;
        String companyId = "1";// 公司id
        String branchId = "86";
        ArticleLabelExample example = new ArticleLabelExample();
        ArticleLabelExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        if (!"86".equals(branchId)) {
            List<String> branchList = new ArrayList<>();
            branchList.add("86");
            branchList.add(branchId);
            criteria.andBranchIdIn(branchList);
        }
        list = articleLabelMapper.selectByExample(example);
        logger.info("查询标签信息结束，查询到的结果为【{}】" + JsonUtil.objectToJackson(list));
        logger.info("查询标签的方法结束！");
        return list;
    }

    @Override
    public Message addlabel(String articleId, String labelIds) {
        logger.info("添加文章标签方法开始");
        Message msg = new Message();
        try {
            String[] arrId = labelIds.split(",");
            for (String labelId : arrId) {
                if (StringUtils.isNotBlank(labelId)) {
                    ArticleLabelRelationshipExample example = new ArticleLabelRelationshipExample();
                    ArticleLabelRelationshipExample.Criteria criteria = example.createCriteria();
                    criteria.andArticleIdEqualTo(Integer.valueOf(articleId));
                    criteria.andLabelIdEqualTo(Integer.valueOf(labelId));
                    List<ArticleLabelRelationship> list = articleLabelRelationshipMapper.selectByExample(example);
                    logger.info("该文章是否存在该关系articleId[{}],labelId[{}],list[{}]", articleId, labelId,
                            JsonUtil.objectToJackson(list));
                    if (null == list || list.size() == 0) {
                        ArticleLabelRelationship articleLabelRelationship = new ArticleLabelRelationship();
                        articleLabelRelationship.setArticleId(Integer.valueOf(articleId));
                        articleLabelRelationship.setLabelId(Integer.valueOf(labelId));
                        if (articleLabelRelationshipMapper.insert(articleLabelRelationship) == 1) {
                            logger.info("给文章添加标签关系成功！");
                        } else {
                            logger.info("给文章添加标签关系失败！");
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("给文章添加标签失败");
            msg.setInfo("添加标签失败！");
            msg.setResult(false);
        }
        msg.setInfo("添加标签成功");
        msg.setResult(true);
        logger.info("添加文章标签方法结束！");
        return msg;
    }

    @Override
    public List<ArticleLabel> queryLabelOss(String companyId) {
        logger.info("根据公司id查询标签的方法开始,companyId【{}】", companyId);
        List<ArticleLabel> list = null;
        ArticleLabelExample example = new ArticleLabelExample();
        ArticleLabelExample.Criteria criteria = example.createCriteria();
        criteria.andCompanyIdEqualTo(companyId);
        list = articleLabelMapper.selectByExample(example);
        logger.info("根据公司id查询标签信息结束，查询到的结果为【{}】" + JsonUtil.objectToJackson(list));
        logger.info("查询标签的方法结束！");
        return list;
    }

    @Override
    public Message saveLabel(String labelName) {
        logger.info("新增标签的方法开始");
        String companyId = "1";// 公司id
        String branchId = "86";
        String date = DateUtils.getDate();
        String time = DateUtils.getTimeNow();
        ArticleLabel record = new ArticleLabel();
        record.setBranchId(branchId);
        record.setCompanyId(companyId);
        record.setLabelName(labelName);
        record.setCreateDate(date);
        record.setCreateTime(time);
        record.setUpdateDate(date);
        record.setUpdateTime(time);
        int i = articleLabelMapper.insertSelective(record);
        logger.info("新增标签信息结束，结果为【{}】", i > 0);
        return new Message(true, "新增成功");
    }

    @Override
    public Message updateLabel(Integer labelId, String labelName) {
        logger.info("修改标签的方法开始");
        ArticleLabel record = articleLabelMapper.selectByPrimaryKey(labelId);
        if (record == null) {
            return new Message(false, "标签不存在");
        }
        String date = DateUtils.getDate();
        String time = DateUtils.getTimeNow();
        record.setLabelName(labelName);
        record.setUpdateDate(date);
        record.setUpdateTime(time);
        int i = articleLabelMapper.insertSelective(record);
        logger.info("修改标签信息结束，结果为【{}】", i > 0);
        return new Message(true, "修改成功");
    }

    @Override
    public Message deleteLabel(Integer labelId) {
        logger.info("删除标签的方法开始");
        ArticleLabel record = articleLabelMapper.selectByPrimaryKey(labelId);
        if (record == null) {
            return new Message(false, "标签不存在");
        }
        int i = articleLabelMapper.deleteByPrimaryKey(labelId);
        logger.info("修改标签信息结束，结果为【{}】", i > 0);
        return new Message(true, "删除成功");
    }

}
