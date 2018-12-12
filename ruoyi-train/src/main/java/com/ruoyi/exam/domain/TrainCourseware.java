package com.ruoyi.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
											    import javax.persistence.Id;
    import java.util.Date;
		
/**
 * 课件表 train_courseware
 *
 * @author zhujj
 * @date 2018-12-12
 */
public class TrainCourseware
{
private static final long serialVersionUID = 1L;

        /**  */
		@Id
    private Integer id;
	        /** 课件分类ID */
	    private Integer trainCoursewareCategoryId;
	        /** 部门名称 */
	    private String name;
	        /** 课件类型（1-文档，2-图文，3-视频，4-音频，5-图片，6-外部链接） */
	    private String type;
	        /** 学习时常（分钟） */
	    private Integer learnTime;
	        /** 是否公开（1-是，0-不是） */
	    private String state;
	        /** 地址 */
	    private String url;
	        /** 正文 */
	    private String content;
	        /** 删除标志（0代表存在 2代表删除） */
	    private String delFlag;
	        /** 创建者 */
	    private String createBy;
	        /** 创建时间 */
	    private Date createTime;
	        /** 更新者 */
	    private String updateBy;
	        /** 更新时间 */
	    private Date updateTime;
	        /** 备注 */
	    private String remark;
	
	    /** 设置 */
    public void setId(Integer id)
            {
            this.id = id;
            }

    /** 获取 */
    public Integer getId()
            {
            return id;
            }
	    /** 设置课件分类ID */
    public void setTrainCoursewareCategoryId(Integer trainCoursewareCategoryId)
            {
            this.trainCoursewareCategoryId = trainCoursewareCategoryId;
            }

    /** 获取课件分类ID */
    public Integer getTrainCoursewareCategoryId()
            {
            return trainCoursewareCategoryId;
            }
	    /** 设置部门名称 */
    public void setName(String name)
            {
            this.name = name;
            }

    /** 获取部门名称 */
    public String getName()
            {
            return name;
            }
	    /** 设置课件类型（1-文档，2-图文，3-视频，4-音频，5-图片，6-外部链接） */
    public void setType(String type)
            {
            this.type = type;
            }

    /** 获取课件类型（1-文档，2-图文，3-视频，4-音频，5-图片，6-外部链接） */
    public String getType()
            {
            return type;
            }
	    /** 设置学习时常（分钟） */
    public void setLearnTime(Integer learnTime)
            {
            this.learnTime = learnTime;
            }

    /** 获取学习时常（分钟） */
    public Integer getLearnTime()
            {
            return learnTime;
            }
	    /** 设置是否公开（1-是，0-不是） */
    public void setState(String state)
            {
            this.state = state;
            }

    /** 获取是否公开（1-是，0-不是） */
    public String getState()
            {
            return state;
            }
	    /** 设置地址 */
    public void setUrl(String url)
            {
            this.url = url;
            }

    /** 获取地址 */
    public String getUrl()
            {
            return url;
            }
	    /** 设置正文 */
    public void setContent(String content)
            {
            this.content = content;
            }

    /** 获取正文 */
    public String getContent()
            {
            return content;
            }
	    /** 设置删除标志（0代表存在 2代表删除） */
    public void setDelFlag(String delFlag)
            {
            this.delFlag = delFlag;
            }

    /** 获取删除标志（0代表存在 2代表删除） */
    public String getDelFlag()
            {
            return delFlag;
            }
	    /** 设置创建者 */
    public void setCreateBy(String createBy)
            {
            this.createBy = createBy;
            }

    /** 获取创建者 */
    public String getCreateBy()
            {
            return createBy;
            }
	    /** 设置创建时间 */
    public void setCreateTime(Date createTime)
            {
            this.createTime = createTime;
            }

    /** 获取创建时间 */
    public Date getCreateTime()
            {
            return createTime;
            }
	    /** 设置更新者 */
    public void setUpdateBy(String updateBy)
            {
            this.updateBy = updateBy;
            }

    /** 获取更新者 */
    public String getUpdateBy()
            {
            return updateBy;
            }
	    /** 设置更新时间 */
    public void setUpdateTime(Date updateTime)
            {
            this.updateTime = updateTime;
            }

    /** 获取更新时间 */
    public Date getUpdateTime()
            {
            return updateTime;
            }
	    /** 设置备注 */
    public void setRemark(String remark)
            {
            this.remark = remark;
            }

    /** 获取备注 */
    public String getRemark()
            {
            return remark;
            }
	
public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
	            .append("id", getId())
	            .append("trainCoursewareCategoryId", getTrainCoursewareCategoryId())
	            .append("name", getName())
	            .append("type", getType())
	            .append("learnTime", getLearnTime())
	            .append("state", getState())
	            .append("url", getUrl())
	            .append("content", getContent())
	            .append("delFlag", getDelFlag())
	            .append("createBy", getCreateBy())
	            .append("createTime", getCreateTime())
	            .append("updateBy", getUpdateBy())
	            .append("updateTime", getUpdateTime())
	            .append("remark", getRemark())
	        .toString();
        }
        }
