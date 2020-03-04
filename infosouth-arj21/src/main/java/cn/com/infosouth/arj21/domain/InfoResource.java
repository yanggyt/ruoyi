package cn.com.infosouth.arj21.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import cn.com.infosouth.common.annotation.Excel;
import cn.com.infosouth.common.core.domain.TreeEntity;

import java.util.Date;

/**
 * 资源目录对象 info_resource
 * 
 * @author kxnf
 * @date 2020-03-04
 */
public class InfoResource extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** 编号 */
    private String id;

    /** 所有父级编号 */
    @Excel(name = "所有父级编号")
    private String parentIds;

    /** 名称 */
    @Excel(name = "名称")
    private String name;

    /** 排序 */
    @Excel(name = "排序")
    private Integer sort;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /** 备注信息 */
    @Excel(name = "备注信息")
    private String remarks;

    /** 删除标记 */
    private String delFlag;

    /** 是否为目录 */
    @Excel(name = "是否为目录")
    private String isDir;

    /** 英文名 */
    @Excel(name = "英文名")
    private String enName;

    /** 所属用户ID */
    @Excel(name = "所属用户ID")
    private String ownerId;

    /** 用户组ID */
    @Excel(name = "用户组ID")
    private String ownerGrpId;

    /** 6可读可写 */
    @Excel(name = "6可读可写")
    private Long authLevelOwner;

    /** 同组者权限(默认有可读权限) */
    @Excel(name = "同组者权限(默认有可读权限)")
    private Long authLevelSameGrp;

    /** 其他用户权限 */
    @Excel(name = "其他用户权限")
    private Long authLevelOther;

    /** 免密公钥列表(user_id1@auth_level1;user_id2@auth_level2) */
    @Excel(name = "免密公钥列表(user_id1@auth_level1;user_id2@auth_level2)")
    private String authKey;

    /** hdfs映射路径 */
    @Excel(name = "hdfs映射路径")
    private String mappingPath;

    /** 检查是否同步的时间 */
    @Excel(name = "检查是否同步的时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date checkSyncTime;

    /** 资源路径是否有效(-1, 0, 1),0代表未知,-1代表无效，1代表有效 */
    @Excel(name = "资源路径是否有效(-1, 0, 1),0代表未知,-1代表无效，1代表有效")
    private Long checkValid;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setParentIds(String parentIds) 
    {
        this.parentIds = parentIds;
    }

    public String getParentIds() 
    {
        return parentIds;
    }
    public void setName(String name) 
    {
        this.name = name;
    }

    public String getName() 
    {
        return name;
    }
    public void setSort(Integer sort) 
    {
        this.sort = sort;
    }

    public Integer getSort() 
    {
        return sort;
    }
    public void setCreateDate(Date createDate) 
    {
        this.createDate = createDate;
    }

    public Date getCreateDate() 
    {
        return createDate;
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
    public void setIsDir(String isDir) 
    {
        this.isDir = isDir;
    }

    public String getIsDir() 
    {
        return isDir;
    }
    public void setEnName(String enName) 
    {
        this.enName = enName;
    }

    public String getEnName() 
    {
        return enName;
    }
    public void setOwnerId(String ownerId) 
    {
        this.ownerId = ownerId;
    }

    public String getOwnerId() 
    {
        return ownerId;
    }
    public void setOwnerGrpId(String ownerGrpId) 
    {
        this.ownerGrpId = ownerGrpId;
    }

    public String getOwnerGrpId() 
    {
        return ownerGrpId;
    }
    public void setAuthLevelOwner(Long authLevelOwner) 
    {
        this.authLevelOwner = authLevelOwner;
    }

    public Long getAuthLevelOwner() 
    {
        return authLevelOwner;
    }
    public void setAuthLevelSameGrp(Long authLevelSameGrp) 
    {
        this.authLevelSameGrp = authLevelSameGrp;
    }

    public Long getAuthLevelSameGrp() 
    {
        return authLevelSameGrp;
    }
    public void setAuthLevelOther(Long authLevelOther) 
    {
        this.authLevelOther = authLevelOther;
    }

    public Long getAuthLevelOther() 
    {
        return authLevelOther;
    }
    public void setAuthKey(String authKey) 
    {
        this.authKey = authKey;
    }

    public String getAuthKey() 
    {
        return authKey;
    }
    public void setMappingPath(String mappingPath) 
    {
        this.mappingPath = mappingPath;
    }

    public String getMappingPath() 
    {
        return mappingPath;
    }
    public void setCheckSyncTime(Date checkSyncTime) 
    {
        this.checkSyncTime = checkSyncTime;
    }

    public Date getCheckSyncTime() 
    {
        return checkSyncTime;
    }
    public void setCheckValid(Long checkValid) 
    {
        this.checkValid = checkValid;
    }

    public Long getCheckValid() 
    {
        return checkValid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("parentId", getParentId())
            .append("parentIds", getParentIds())
            .append("name", getName())
            .append("sort", getSort())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .append("isDir", getIsDir())
            .append("enName", getEnName())
            .append("ownerId", getOwnerId())
            .append("ownerGrpId", getOwnerGrpId())
            .append("authLevelOwner", getAuthLevelOwner())
            .append("authLevelSameGrp", getAuthLevelSameGrp())
            .append("authLevelOther", getAuthLevelOther())
            .append("authKey", getAuthKey())
            .append("mappingPath", getMappingPath())
            .append("checkSyncTime", getCheckSyncTime())
            .append("checkValid", getCheckValid())
            .toString();
    }
}
