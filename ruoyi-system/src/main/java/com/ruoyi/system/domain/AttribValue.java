package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * Table containing ATTRIB_VALUE对象 attrib_value
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
public class AttribValue extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Internal ATTRIB_VALUE number */
    private Long attribValueNo;

    /** Actual attribute value */
    @Excel(name = "Actual attribute value")
    private String valueText;

    /** System id of catalogue */
    @Excel(name = "System id of catalogue")
    private Long catalogNo;

    /** System id for the set of equivalent values to which this value belongs */
    @Excel(name = "System id for the set of equivalent values to which this value belongs")
    private Long attribEquivSetNo;

    /** System id for the representation in which this value is being specified */
    @Excel(name = "System id for the representation in which this value is being specified")
    private Long attribRepresentNo;

    /** Initials for person defining the position */
    @Excel(name = "Initials for person defining the position")
    private String defUsrId;

    /** Definition date */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Definition date", width = 30, dateFormat = "yyyy-MM-dd")
    private Date defDate;

    /** Initials for person latest updating the position */
    @Excel(name = "Initials for person latest updating the position")
    private String updUsrId;

    /** Latest update date */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Latest update date", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updDate;

    public void setAttribValueNo(Long attribValueNo) 
    {
        this.attribValueNo = attribValueNo;
    }

    public Long getAttribValueNo() 
    {
        return attribValueNo;
    }
    public void setValueText(String valueText) 
    {
        this.valueText = valueText;
    }

    public String getValueText() 
    {
        return valueText;
    }
    public void setCatalogNo(Long catalogNo) 
    {
        this.catalogNo = catalogNo;
    }

    public Long getCatalogNo() 
    {
        return catalogNo;
    }
    public void setAttribEquivSetNo(Long attribEquivSetNo) 
    {
        this.attribEquivSetNo = attribEquivSetNo;
    }

    public Long getAttribEquivSetNo() 
    {
        return attribEquivSetNo;
    }
    public void setAttribRepresentNo(Long attribRepresentNo) 
    {
        this.attribRepresentNo = attribRepresentNo;
    }

    public Long getAttribRepresentNo() 
    {
        return attribRepresentNo;
    }
    public void setDefUsrId(String defUsrId) 
    {
        this.defUsrId = defUsrId;
    }

    public String getDefUsrId() 
    {
        return defUsrId;
    }
    public void setDefDate(Date defDate) 
    {
        this.defDate = defDate;
    }

    public Date getDefDate() 
    {
        return defDate;
    }
    public void setUpdUsrId(String updUsrId) 
    {
        this.updUsrId = updUsrId;
    }

    public String getUpdUsrId() 
    {
        return updUsrId;
    }
    public void setUpdDate(Date updDate) 
    {
        this.updDate = updDate;
    }

    public Date getUpdDate() 
    {
        return updDate;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attribValueNo", getAttribValueNo())
            .append("valueText", getValueText())
            .append("catalogNo", getCatalogNo())
            .append("attribEquivSetNo", getAttribEquivSetNo())
            .append("attribRepresentNo", getAttribRepresentNo())
            .append("defUsrId", getDefUsrId())
            .append("defDate", getDefDate())
            .append("updUsrId", getUpdUsrId())
            .append("updDate", getUpdDate())
            .toString();
    }
}
