package com.ruoyi.content.service;

import com.ruoyi.content.domain.ArticleLabel;
import com.ruoyi.content.message.Message;

import java.util.List;

/**
 * 标签业务层
 */
public interface ArticleLabelService {
    /**
     * 查询出所有标签
     *
     * @return
     */
    public List<ArticleLabel> queryLabel();

    /**
     * 根据公司id查询标签
     *
     * @return
     */
    public List<ArticleLabel> queryLabelOss(String companyId);

    /**
     * 添加文章标签关系
     *
     * @return
     */
    public Message addlabel(String articleId, String labelIds);

    /**
     * 新增标签
     *
     * @param labelName
     * @return
     */
    public Message saveLabel(String labelName);

    /**
     * 修改标签
     *
     * @param labelId
     * @param labelName
     * @return
     */
    public Message updateLabel(Integer labelId, String labelName);

    /**
     * 删除标签信息
     *
     * @param labelId
     * @return
     */
    public Message deleteLabel(Integer labelId);

}
