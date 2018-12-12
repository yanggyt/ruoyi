package com.ruoyi.exam.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.base.BaseEntity;
									    import javax.persistence.Id;
    import java.util.Date;
		
/**
 * 课程分类表 train_course_category
 *
 * @author zhujj
 * @date 2018-12-12
 */
public class TrainCourseCategory
{
private static final long serialVersionUID = 1L;

        /** 部门id */
		@Id
    private Integer id;
	        /** 部门ID */
	    private Integer deptId;
	        /** 父部门id */
	    private Integer parentId;
	        /** 祖级列表 */
	    private String parentIds;
	        /** 分类名称 */
	    private String name;
	        /** 显示顺序 */
	    private Integer orderNum;
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
	
	    /** 设置部门id */
    public void setId(Integer id)
            {
            this.id = id;
            }

    /** 获取部门id */
    public Integer getId()
            {
            return id;
            }
	    /** 设置部门ID */
    public void setDeptId(Integer deptId)
            {
            this.deptId = deptId;
            }

    /** 获取部门ID */
    public Integer getDeptId()
            {
            return deptId;
            }
	    /** 设置父部门id */
    public void setParentId(Integer parentId)
            {
            this.parentId = parentId;
            }

    /** 获取父部门id */
    public Integer getParentId()
            {
            return parentId;
            }
	    /** 设置祖级列表 */
    public void setParentIds(String parentIds)
            {
            this.parentIds = parentIds;
            }

    /** 获取祖级列表 */
    public String getParentIds()
            {
            return parentIds;
            }
	    /** 设置分类名称 */
    public void setName(String name)
            {
            this.name = name;
            }

    /** 获取分类名称 */
    public String getName()
            {
            return name;
            }
	    /** 设置显示顺序 */
    public void setOrderNum(Integer orderNum)
            {
            this.orderNum = orderNum;
            }

    /** 获取显示顺序 */
    public Integer getOrderNum()
            {
            return orderNum;
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
	            .append("deptId", getDeptId())
	            .append("parentId", getParentId())
	            .append("parentIds", getParentIds())
	            .append("name", getName())
	            .append("orderNum", getOrderNum())
	            .append("delFlag", getDelFlag())
	            .append("createBy", getCreateBy())
	            .append("createTime", getCreateTime())
	            .append("updateBy", getUpdateBy())
	            .append("updateTime", getUpdateTime())
	            .append("remark", getRemark())
	        .toString();
        }
        }
