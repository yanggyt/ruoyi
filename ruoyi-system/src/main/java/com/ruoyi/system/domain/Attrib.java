package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.utils.security.CipherUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.apache.shiro.codec.Base64;

import java.util.Date;

/**
 * 属性对象 attrib
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
public class Attrib extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** Internal ATTRIB number */
    private Long attribNo;

    /** External ATTRIB number */
    @Excel(name = "External ATTRIB number")
    private String attribId;

    /** System id of catalogue */
    @Excel(name = "System id of catalogue")
    private Long catalogNo;

    private Long classNo;

    /** Optionally identifies a calculation parameter to be fed by this attribute */
    @Excel(name = "Optionally identifies a calculation parameter to be fed by this attribute")
    private String calcParam;

    /** The type (heading) for the physical value. E.g. "mm OD" */
    @Excel(name = "The type (heading) for the physical value. E.g. ")
    private String physicalValueType;

    /** The unit of measure for the physical values for this attribute type - e.g. for bolt diameters this would be length in mm */
    @Excel(name = "The unit of measure for the physical values for this attribute type - e.g. for bolt diameters this would be length in mm")
    private String physicalValueUnitId;

    /** Description of the attribute type */
    @Excel(name = "Description of the attribute type")
    private String descr;

    /** Whether the value of this attribute can be defined against a Commodity  (1 = can, 0 = cannot) */
    @Excel(name = "Whether the value of this attribute can be defined against a Commodity  (1 = can, 0 = cannot)")
    private String commodityLevel;

    /** Whether the value of this attribute can be defined against a Size Reference (1 = can, 0 = cannot). */
    @Excel(name = "Whether the value of this attribute can be defined against a Size Reference (1 = can, 0 = cannot).")
    private String sizeRefLevel;

    /** Whether the value of this attribute can be defined against a Part (component (1 = can, 0 = cannot). */
    @Excel(name = "Whether the value of this attribute can be defined against a Part (component (1 = can, 0 = cannot).")
    private String partLevel;

    /** Whether the value of this attribute can be defined against a Modeller (component (1 = can, 0 = cannot). */
    @Excel(name = "Whether the value of this attribute can be defined against a Modeller (component (1 = can, 0 = cannot).")
    private String modellerLevel;

    /** Defined scope of the attribute type (0 = Internal, 1 = External). */
    @Excel(name = "Defined scope of the attribute type (0 = Internal, 1 = External).")
    private Long defScope;

    /** Whether this attribute type is obsolete. (1 = Active, 0 = Obsolete) */
    @Excel(name = "Whether this attribute type is obsolete. (1 = Active, 0 = Obsolete)")
    private String stat;

    /** The category of attribute */
    @Excel(name = "The category of attribute")
    private Long attribCategoryNo;

    /** System id of discipline. */
    @Excel(name = "System id of discipline.")
    private Long drawDisciplineNo;

    /** Initials for person defining the position */
    @Excel(name = "Initials for person defining the position")
    private String defUsrId;

    /** Definition date */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Definition date", width = 30, dateFormat = "yyyy-MM-dd")
    private Date defDate;

    /** Defining application of row */
    @Excel(name = "Defining application of row")
    private Long defApplNo;

    /** Initials for person latest updating the position */
    @Excel(name = "Initials for person latest updating the position")
    private String updUsrId;

    /** Latest update date */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Latest update date", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updDate;

    /** Last updating application of row */
    @Excel(name = "Last updating application of row")
    private Long updApplNo;

    /** Weighting to be applied for this attribute when matching to alternative parts (10=LOW, 20=MEDIUM, 30=HIGH) */
    @Excel(name = "Weighting to be applied for this attribute when matching to alternative parts (10=LOW, 20=MEDIUM, 30=HIGH)")
    private Long matchWeightingNo;

    /** If attribute is system attribute. */
    @Excel(name = "If attribute is system attribute.")
    private String isSystem;
    private String class_attrib_id;

    public void setAttribNo(Long attribNo) 
    {
        this.attribNo = attribNo;
    }

    public Long getAttribNo() 
    {
        return attribNo;
    }
    public void setAttribId(String attribId) 
    {
        this.attribId = attribId;
    }

    public String getAttribId() 
    {
        return attribId;
    }
    public void setCatalogNo(Long catalogNo) 
    {
        this.catalogNo = catalogNo;
    }

    public Long getCatalogNo() 
    {
        return catalogNo;
    }
    public void setCalcParam(String calcParam) 
    {
        this.calcParam = calcParam;
    }

    public String getCalcParam() 
    {
        return calcParam;
    }
    public void setPhysicalValueType(String physicalValueType) 
    {
        this.physicalValueType = physicalValueType;
    }

    public String getPhysicalValueType() 
    {
        return physicalValueType;
    }
    public void setPhysicalValueUnitId(String physicalValueUnitId) 
    {
        this.physicalValueUnitId = physicalValueUnitId;
    }

    public String getPhysicalValueUnitId() 
    {
        return physicalValueUnitId;
    }
    public void setDescr(String descr) 
    {
        this.descr = descr;
    }

    public String getDescr() 
    {
        return descr;
    }
    public void setCommodityLevel(String commodityLevel) 
    {
        this.commodityLevel = commodityLevel;
    }

    public String getCommodityLevel() 
    {
        return commodityLevel;
    }
    public void setSizeRefLevel(String sizeRefLevel) 
    {
        this.sizeRefLevel = sizeRefLevel;
    }

    public String getClass_attrib_id() {
        return class_attrib_id;
    }

    public void setClass_attrib_id(String class_attrib_id) {
        this.class_attrib_id = class_attrib_id;
    }

    public String getSizeRefLevel()
    {
        return sizeRefLevel;
    }
    public void setPartLevel(String partLevel) 
    {
        this.partLevel = partLevel;
    }

    public String getPartLevel() 
    {
        return partLevel;
    }
    public void setModellerLevel(String modellerLevel) 
    {
        this.modellerLevel = modellerLevel;
    }

    public String getModellerLevel() 
    {
        return modellerLevel;
    }
    public void setDefScope(Long defScope) 
    {
        this.defScope = defScope;
    }

    public Long getDefScope() 
    {
        return defScope;
    }
    public void setStat(String stat) 
    {
        this.stat = stat;
    }

    public String getStat() 
    {
        return stat;
    }
    public Long getClassNo()
    {
        return classNo;
    }
    public void setAttribCategoryNo(Long attribCategoryNo)
    {
        this.attribCategoryNo = attribCategoryNo;
    }

    public Long getAttribCategoryNo() 
    {
        return attribCategoryNo;
    }
    public void setDrawDisciplineNo(Long drawDisciplineNo) 
    {
        this.drawDisciplineNo = drawDisciplineNo;
    }

    public Long getDrawDisciplineNo() 
    {
        return drawDisciplineNo;
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
    public void setDefApplNo(Long defApplNo) 
    {
        this.defApplNo = defApplNo;
    }

    public Long getDefApplNo() 
    {
        return defApplNo;
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
    public void setUpdApplNo(Long updApplNo) 
    {
        this.updApplNo = updApplNo;
    }

    public Long getUpdApplNo() 
    {
        return updApplNo;
    }
    public void setMatchWeightingNo(Long matchWeightingNo) 
    {
        this.matchWeightingNo = matchWeightingNo;
    }

    public void setClassNo(Long classNo) {
        this.classNo = classNo;
    }

    public Long getMatchWeightingNo()
    {
        return matchWeightingNo;
    }
    public void setIsSystem(String isSystem) 
    {
        this.isSystem = isSystem;
    }

    public String getIsSystem() 
    {
        return isSystem;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("attribNo", getAttribNo())
            .append("attribId", getAttribId())
            .append("catalogNo", getCatalogNo())
            .append("calcParam", getCalcParam())
            .append("physicalValueType", getPhysicalValueType())
            .append("physicalValueUnitId", getPhysicalValueUnitId())
            .append("descr", getDescr())
            .append("commodityLevel", getCommodityLevel())
            .append("sizeRefLevel", getSizeRefLevel())
            .append("partLevel", getPartLevel())
            .append("modellerLevel", getModellerLevel())
            .append("defScope", getDefScope())
            .append("stat", getStat())
            .append("classNo", getClassNo())
            .append("attribCategoryNo", getAttribCategoryNo())
            .append("drawDisciplineNo", getDrawDisciplineNo())
            .append("defUsrId", getDefUsrId())
            .append("defDate", getDefDate())
            .append("defApplNo", getDefApplNo())
            .append("updUsrId", getUpdUsrId())
            .append("updDate", getUpdDate())
            .append("updApplNo", getUpdApplNo())
            .append("matchWeightingNo", getMatchWeightingNo())
            .append("isSystem", getIsSystem())
            .append("class_attrib_id", getClass_attrib_id())
            .toString();
    }

    public static void main(String[] args) {
        String aes = Base64.encodeToString(CipherUtils.generateNewKey(128, "AES").getEncoded());
        System.out.println(aes);
    }
}
