package com.ruoyi.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
import java.util.Date;

/**
 * 问题点评表 exam_question_comment
 * 
 * @author zhujj
 * @date 2018-12-06
 */
public class ExamQuestionComment
{
	private static final long serialVersionUID = 1L;
	
	/**  */
	private String id;
	/** 点评内容 */
	private Integer content;
	/** 问题 */
	private String examQuestionId;
	/** 点赞数量 */
	private Integer praiseCount;
	/** 类型（0 学生点评 1 老师点评） */
	private String commentType;
	/** 创建者 */
	private String createBy;
	/** 创建时间 */
	private Date createDate;
	/** 更新者 */
	private String updateBy;
	/** 更新时间 */
	private Date updateDate;
	/** 备注信息 */
	private String remarks;
	/** 删除标记 */
	private String delFlag;

	public void setId(String id) 
	{
		this.id = id;
	}

	public String getId() 
	{
		return id;
	}
	public void setContent(Integer content) 
	{
		this.content = content;
	}

	public Integer getContent() 
	{
		return content;
	}
	public void setExamQuestionId(String examQuestionId) 
	{
		this.examQuestionId = examQuestionId;
	}

	public String getExamQuestionId() 
	{
		return examQuestionId;
	}
	public void setPraiseCount(Integer praiseCount) 
	{
		this.praiseCount = praiseCount;
	}

	public Integer getPraiseCount() 
	{
		return praiseCount;
	}
	public void setCommentType(String commentType) 
	{
		this.commentType = commentType;
	}

	public String getCommentType() 
	{
		return commentType;
	}
	public void setCreateBy(String createBy) 
	{
		this.createBy = createBy;
	}

	public String getCreateBy() 
	{
		return createBy;
	}
	public void setCreateDate(Date createDate) 
	{
		this.createDate = createDate;
	}

	public Date getCreateDate() 
	{
		return createDate;
	}
	public void setUpdateBy(String updateBy) 
	{
		this.updateBy = updateBy;
	}

	public String getUpdateBy() 
	{
		return updateBy;
	}
	public void setUpdateDate(Date updateDate) 
	{
		this.updateDate = updateDate;
	}

	public Date getUpdateDate() 
	{
		return updateDate;
	}
	public void setRemarks(String remarks) 
	{
		this.remarks = remarks;
	}

	public String getRemarks() 
	{
		return remarks;
	}
	public void setDelFlag(String delFlag) 
	{
		this.delFlag = delFlag;
	}

	public String getDelFlag() 
	{
		return delFlag;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("content", getContent())
            .append("examQuestionId", getExamQuestionId())
            .append("praiseCount", getPraiseCount())
            .append("commentType", getCommentType())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
