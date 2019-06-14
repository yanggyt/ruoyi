package com.ruoyi.wish.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 记录微心愿的表 wish_info
 * 
 * @author jyking
 * @date 2019-06-14
 */
public class WishInfo extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 微心愿ID */
	private Integer wishId;
	/** 微心愿名称 */
	private String wishName;
	/** 微心愿归属地区 */
	private String wishOrg;
	/** 微心愿状态 01.未认领02.已认领03.已完成 */
	private String wishStatus;
	/** 微心愿对象 01党组织团体02党员个体 */
	private String wishTarget;
	/** 微心愿类型 01.组织建设类02.党员需求类03.初心教育类04.业务合作类 */
	private String wishType;
	/** 申请认领次数 */
	private Integer wishCount;
	/** 是不是推荐 0.是 1.不是 */
	private String isRecommend;
	/** 微心愿项目进展 */
	private String wishProgress;
	/** 微心愿详细描述 */
	private String wishDesc;
	/** 申请人姓名 */
	private String applyerName;
	/** 申请人联系方式 */
	private String applyerPhone;
	/** 申请人所在党组织 */
	private String applyerComp;
	/** 失效日期 */
	private Date expireDate;
	/** 生效日期 */
	private Date effectDate;
	/** 认领日期 */
	private Date claimDate;
	/** 完成日期 */
	private Date completeDate;
	/** 图片存储路径 */
	private String savePath;
	/** 创建日期(YYYY-MM-DD hh:mm:ss UUUUUU) */
	private Date gmtCreate;
	/** 创建操作员 */
	private String createOperId;
	/** 修改日期(YYYY-MM-DD hh:mm:ss UUUUUU) */
	private Date gmtModified;
	/** 修改操作员 */
	private String modifiedOperId;
	/** 审核时间(YYYY-MM-DD hh:mm:ss UUUUUU) */
	private Date gmtAudit;
	/** 审核操作员 */
	private String auditOperId;

	public void setWishId(Integer wishId) 
	{
		this.wishId = wishId;
	}

	public Integer getWishId() 
	{
		return wishId;
	}
	public void setWishName(String wishName) 
	{
		this.wishName = wishName;
	}

	public String getWishName() 
	{
		return wishName;
	}
	public void setWishOrg(String wishOrg) 
	{
		this.wishOrg = wishOrg;
	}

	public String getWishOrg() 
	{
		return wishOrg;
	}
	public void setWishStatus(String wishStatus) 
	{
		this.wishStatus = wishStatus;
	}

	public String getWishStatus() 
	{
		return wishStatus;
	}
	public void setWishTarget(String wishTarget) 
	{
		this.wishTarget = wishTarget;
	}

	public String getWishTarget() 
	{
		return wishTarget;
	}
	public void setWishType(String wishType) 
	{
		this.wishType = wishType;
	}

	public String getWishType() 
	{
		return wishType;
	}
	public void setWishCount(Integer wishCount) 
	{
		this.wishCount = wishCount;
	}

	public Integer getWishCount() 
	{
		return wishCount;
	}
	public void setIsRecommend(String isRecommend) 
	{
		this.isRecommend = isRecommend;
	}

	public String getIsRecommend() 
	{
		return isRecommend;
	}
	public void setWishProgress(String wishProgress) 
	{
		this.wishProgress = wishProgress;
	}

	public String getWishProgress() 
	{
		return wishProgress;
	}
	public void setWishDesc(String wishDesc) 
	{
		this.wishDesc = wishDesc;
	}

	public String getWishDesc() 
	{
		return wishDesc;
	}
	public void setApplyerName(String applyerName) 
	{
		this.applyerName = applyerName;
	}

	public String getApplyerName() 
	{
		return applyerName;
	}
	public void setApplyerPhone(String applyerPhone) 
	{
		this.applyerPhone = applyerPhone;
	}

	public String getApplyerPhone() 
	{
		return applyerPhone;
	}
	public void setApplyerComp(String applyerComp) 
	{
		this.applyerComp = applyerComp;
	}

	public String getApplyerComp() 
	{
		return applyerComp;
	}
	public void setExpireDate(Date expireDate) 
	{
		this.expireDate = expireDate;
	}

	public Date getExpireDate() 
	{
		return expireDate;
	}
	public void setEffectDate(Date effectDate) 
	{
		this.effectDate = effectDate;
	}

	public Date getEffectDate() 
	{
		return effectDate;
	}
	public void setClaimDate(Date claimDate) 
	{
		this.claimDate = claimDate;
	}

	public Date getClaimDate() 
	{
		return claimDate;
	}
	public void setCompleteDate(Date completeDate) 
	{
		this.completeDate = completeDate;
	}

	public Date getCompleteDate() 
	{
		return completeDate;
	}
	public void setSavePath(String savePath) 
	{
		this.savePath = savePath;
	}

	public String getSavePath() 
	{
		return savePath;
	}
	public void setGmtCreate(Date gmtCreate) 
	{
		this.gmtCreate = gmtCreate;
	}

	public Date getGmtCreate() 
	{
		return gmtCreate;
	}
	public void setCreateOperId(String createOperId) 
	{
		this.createOperId = createOperId;
	}

	public String getCreateOperId() 
	{
		return createOperId;
	}
	public void setGmtModified(Date gmtModified) 
	{
		this.gmtModified = gmtModified;
	}

	public Date getGmtModified() 
	{
		return gmtModified;
	}
	public void setModifiedOperId(String modifiedOperId) 
	{
		this.modifiedOperId = modifiedOperId;
	}

	public String getModifiedOperId() 
	{
		return modifiedOperId;
	}
	public void setGmtAudit(Date gmtAudit) 
	{
		this.gmtAudit = gmtAudit;
	}

	public Date getGmtAudit() 
	{
		return gmtAudit;
	}
	public void setAuditOperId(String auditOperId) 
	{
		this.auditOperId = auditOperId;
	}

	public String getAuditOperId() 
	{
		return auditOperId;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("wishId", getWishId())
            .append("wishName", getWishName())
            .append("wishOrg", getWishOrg())
            .append("wishStatus", getWishStatus())
            .append("wishTarget", getWishTarget())
            .append("wishType", getWishType())
            .append("wishCount", getWishCount())
            .append("isRecommend", getIsRecommend())
            .append("wishProgress", getWishProgress())
            .append("wishDesc", getWishDesc())
            .append("applyerName", getApplyerName())
            .append("applyerPhone", getApplyerPhone())
            .append("applyerComp", getApplyerComp())
            .append("expireDate", getExpireDate())
            .append("effectDate", getEffectDate())
            .append("claimDate", getClaimDate())
            .append("completeDate", getCompleteDate())
            .append("savePath", getSavePath())
            .append("gmtCreate", getGmtCreate())
            .append("createOperId", getCreateOperId())
            .append("gmtModified", getGmtModified())
            .append("modifiedOperId", getModifiedOperId())
            .append("gmtAudit", getGmtAudit())
            .append("auditOperId", getAuditOperId())
            .toString();
    }
}
