package cn.com.infosouth.arj21.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.com.infosouth.common.annotation.Excel;
import cn.com.infosouth.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 飞机对象 info_aircraft
 * 
 * @author kxnf
 * @date 2020-03-05
 */
public class InfoAircraft extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    /** 删除标志 */
    private String delFlag;

    /** 飞机号 */
    @Excel(name = "飞机号")
    private String acReg;

    /** 机型编号,info_ac_type.id */
    @Excel(name = "机型编号,info_ac_type.id")
    private String infoAcTypeId;

    /** 默认上传目录, info_resource.id */
    @Excel(name = "默认上传目录, info_resource.id")
    private String uploadDirId;

    /** null */
    @Excel(name = "null")
    private String aircompanyid;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
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
    public void setAcReg(String acReg) 
    {
        this.acReg = acReg;
    }

    public String getAcReg() 
    {
        return acReg;
    }
    public void setInfoAcTypeId(String infoAcTypeId) 
    {
        this.infoAcTypeId = infoAcTypeId;
    }

    public String getInfoAcTypeId() 
    {
        return infoAcTypeId;
    }
    public void setUploadDirId(String uploadDirId) 
    {
        this.uploadDirId = uploadDirId;
    }

    public String getUploadDirId() 
    {
        return uploadDirId;
    }
    public void setAircompanyid(String aircompanyid) 
    {
        this.aircompanyid = aircompanyid;
    }

    public String getAircompanyid() 
    {
        return aircompanyid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .append("acReg", getAcReg())
            .append("infoAcTypeId", getInfoAcTypeId())
            .append("uploadDirId", getUploadDirId())
            .append("aircompanyid", getAircompanyid())
            .toString();
    }
}
