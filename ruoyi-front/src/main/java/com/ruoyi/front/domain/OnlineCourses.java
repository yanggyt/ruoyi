package com.ruoyi.front.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 线上课程对象 online_courses
 * 
 * @author ruoyi
 * @date 2020-10-21
 */
public class OnlineCourses extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** ID */
    private Long id;

    /** 标题 */
    @Excel(name = "标题")
    private String title;

    /** 简介 */
    @Excel(name = "简介")
    private String introduction;

    /** 点击量 */
    @Excel(name = "点击量")
    private Integer hits;

    /** 针对人群 */
    @Excel(name = "针对人群")
    private String targetPeople;

    /** 视频课程时长 */
    @Excel(name = "视频课程时长")
    private String coursesDuration;

    /** 课程难度等级 */
    @Excel(name = "课程难度等级")
    private Integer coursesLevel;

    /** 图片地址（多个地址用，分隔） */
    @Excel(name = "图片地址", readConverterExp = "多=个地址用，分隔")
    private String pictureUrl;

    /** 视频地址（多个地址用，分隔） */
    @Excel(name = "视频地址", readConverterExp = "多=个地址用，分隔")
    private String videoUrl;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    /** 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setTitle(String title) 
    {
        this.title = title;
    }

    public String getTitle() 
    {
        return title;
    }
    public void setIntroduction(String introduction) 
    {
        this.introduction = introduction;
    }

    public String getIntroduction() 
    {
        return introduction;
    }
    public void setHits(Integer hits) 
    {
        this.hits = hits;
    }

    public Integer getHits() 
    {
        return hits;
    }
    public void setTargetPeople(String targetPeople) 
    {
        this.targetPeople = targetPeople;
    }

    public String getTargetPeople() 
    {
        return targetPeople;
    }
    public void setCoursesDuration(String coursesDuration) 
    {
        this.coursesDuration = coursesDuration;
    }

    public String getCoursesDuration() 
    {
        return coursesDuration;
    }
    public void setCoursesLevel(Integer coursesLevel) 
    {
        this.coursesLevel = coursesLevel;
    }

    public Integer getCoursesLevel() 
    {
        return coursesLevel;
    }
    public void setPictureUrl(String pictureUrl) 
    {
        this.pictureUrl = pictureUrl;
    }

    public String getPictureUrl() 
    {
        return pictureUrl;
    }
    public void setVideoUrl(String videoUrl) 
    {
        this.videoUrl = videoUrl;
    }

    public String getVideoUrl() 
    {
        return videoUrl;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setDelFlag(String delFlag) 
    {
        this.delFlag = delFlag;
    }

    public String getDelFlag() 
    {
        return delFlag;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("title", getTitle())
            .append("introduction", getIntroduction())
            .append("hits", getHits())
            .append("targetPeople", getTargetPeople())
            .append("coursesDuration", getCoursesDuration())
            .append("coursesLevel", getCoursesLevel())
            .append("pictureUrl", getPictureUrl())
            .append("videoUrl", getVideoUrl())
            .append("status", getStatus())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
