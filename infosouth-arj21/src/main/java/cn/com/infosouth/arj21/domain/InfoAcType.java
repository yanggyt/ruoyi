package cn.com.infosouth.arj21.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.com.infosouth.common.annotation.Excel;
import cn.com.infosouth.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 机型对象 info_ac_type
 * 
 * @author kxnf
 * @date 2020-03-05
 */
public class InfoAcType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 机型 */
    @Excel(name = "机型")
    private String acTpye;

    /** csv列头对应index(弃用，改用info_version表中对应字段) */
    @Excel(name = "csv列头对应index(弃用，改用info_version表中对应字段)")
    private Long csvHeaderIndex;

    /** csv内容开始行对应index(弃用，改用info_version表中对应字段) */
    @Excel(name = "csv内容开始行对应index(弃用，改用info_version表中对应字段)")
    private Long csvContentIndex;

    /** 参数版本id */
    @Excel(name = "参数版本id")
    private Long versionId;

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

    /** 机型的编号，自定义。用于查询后方便识记机型 */
    @Excel(name = "机型的编号，自定义。用于查询后方便识记机型")
    private String acTypeNo;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setAcTpye(String acTpye) 
    {
        this.acTpye = acTpye;
    }

    public String getAcTpye() 
    {
        return acTpye;
    }
    public void setCsvHeaderIndex(Long csvHeaderIndex) 
    {
        this.csvHeaderIndex = csvHeaderIndex;
    }

    public Long getCsvHeaderIndex() 
    {
        return csvHeaderIndex;
    }
    public void setCsvContentIndex(Long csvContentIndex) 
    {
        this.csvContentIndex = csvContentIndex;
    }

    public Long getCsvContentIndex() 
    {
        return csvContentIndex;
    }
    public void setVersionId(Long versionId) 
    {
        this.versionId = versionId;
    }

    public Long getVersionId() 
    {
        return versionId;
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
    public void setAcTypeNo(String acTypeNo) 
    {
        this.acTypeNo = acTypeNo;
    }

    public String getAcTypeNo() 
    {
        return acTypeNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("acTpye", getAcTpye())
            .append("csvHeaderIndex", getCsvHeaderIndex())
            .append("csvContentIndex", getCsvContentIndex())
            .append("versionId", getVersionId())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .append("acTypeNo", getAcTypeNo())
            .toString();
    }
}
