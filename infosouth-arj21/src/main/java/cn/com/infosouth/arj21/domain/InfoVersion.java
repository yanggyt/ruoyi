package cn.com.infosouth.arj21.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.com.infosouth.common.annotation.Excel;
import cn.com.infosouth.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 参数版本对象 info_version
 * 
 * @author kxnf
 * @date 2020-03-05
 */
public class InfoVersion extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private Long id;

    /** 版本名 */
    @Excel(name = "版本名")
    private String verName;

    /** 版本描述 */
    @Excel(name = "版本描述")
    private String verDesc;

    /** csv列头对应index */
    @Excel(name = "csv列头对应index")
    private Long csvHeaderIndex;

    /** csv内容开始行对应index */
    @Excel(name = "csv内容开始行对应index")
    private Long csvContentIndex;

    /** 脚本语言 */
    @Excel(name = "脚本语言")
    private String scriptLanguage;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createDate;

    /** 备注 */
    @Excel(name = "备注")
    private String remarks;

    @Excel(name = "更新时间", width = 30)
    private Date updateDate;
    
    /** 删除标志 */
    private String delFlag;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setVerName(String verName) 
    {
        this.verName = verName;
    }

    public String getVerName() 
    {
        return verName;
    }
    public void setVerDesc(String verDesc) 
    {
        this.verDesc = verDesc;
    }

    public String getVerDesc() 
    {
        return verDesc;
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
    public void setScriptLanguage(String scriptLanguage) 
    {
        this.scriptLanguage = scriptLanguage;
    }

    public String getScriptLanguage() 
    {
        return scriptLanguage;
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

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("verName", getVerName())
            .append("verDesc", getVerDesc())
            .append("csvHeaderIndex", getCsvHeaderIndex())
            .append("csvContentIndex", getCsvContentIndex())
            .append("scriptLanguage", getScriptLanguage())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
