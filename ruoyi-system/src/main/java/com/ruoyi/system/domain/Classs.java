package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * Table containing Class Hierarchy对象 class
 * 
 * @author zbj
 * @date 2021-08-31
 */
public class Classs extends TreeEntity
{
    private static final long serialVersionUID = 1L;
    /**
     *  1 = Working, 2 = Approved
     * */
    public static final long APPROVAL_APPROVED = 2L;

    /** Unique internal identifier for class in class hierarchy. */
    private Long classNo;

    /** Name of class. */
    @Excel(name = "Name of class.")
    private String classId;

    /** System id of catalogue. */
    @Excel(name = "System id of catalogue.")
    private Long catalogNo;

    /** Sequence number of the class within parent class, or the top level if there is no parent. */
    @Excel(name = "Sequence number of the class within parent class, or the top level if there is no parent.")
    private Long seqNo;

    /** Description of class. */
    @Excel(name = "Description of class.")
    private String descr;

    /** Indicates valid entries (1 = Active, 0 = Obsolete). */
    @Excel(name = "Indicates valid entries (1 = Active, 0 = Obsolete).")
    private String stat;

    /** Class approval status. (1 = Working, 2 = Approved) */
    @Excel(name = "Class approval status. (1 = Working, 2 = Approved)")
    private Long approvalStatusNo;

    /** Specifies the parent (if any) for a class. A null value indicates a top level class. */
    @Excel(name = "Specifies the parent (if any) for a class. A null value indicates a top level class.")
    private Long parentClassNo;

    /** System id of discipline. */
    @Excel(name = "System id of discipline.")
    private Long drawDisciplineNo;

    /** The ultimate type of catalogue entity generated from this class. (1 = Commodity, 2 = Size Reference Format, 3 = Basic Component Type, 4 = Commodity Code Format, 5 = Part Number Format, 6 = CATREF Format, 7 = SPREF Format, 8 = STYP Format, 9 = CMPREF Format, 10 = BLTREF Format, 11 = Compound Component Type, 12 = Sub Commodity code). */
    @Excel(name = "The ultimate type of catalogue entity generated from this class. (1 = Commodity, 2 = Size Reference Format, 3 = Basic Component Type, 4 = Commodity Code Format, 5 = Part Number Format, 6 = CATREF Format, 7 = SPREF Format, 8 = STYP Format, 9 = CMPREF Format, 10 = BLTREF Format, 11 = Compound Component Type, 12 = Sub Commodity code).")
    private Long catEntityTypeNo;

    /** Whether this class can be instantiated. (1 = Can instantiate, 0 = Cannot instantiate). */
    @Excel(name = "Whether this class can be instantiated. (1 = Can instantiate, 0 = Cannot instantiate).")
    private String canInstantiate;

    /** Default branch code to be used for this type of component in branch tables. */
    @Excel(name = "Default branch code to be used for this type of component in branch tables.")
    private String branchCode;

    /** Whether this class is internal or external (0 = Internal, 1 = External). */
    @Excel(name = "Whether this class is internal or external (0 = Internal, 1 = External).")
    private Long defScopeNo;

    /** Whether bolts required. (1 - Required, 0 = Not required). */
    @Excel(name = "Whether bolts required. (1 - Required, 0 = Not required).")
    private String boltsRequired;

    /** Which size to use for the BLTREF FirstSize = 1 SecondSize = 4 ThirdSize = 5. Null is assumed to be the FirstSize */
    @Excel(name = "Which size to use for the BLTREF FirstSize = 1 SecondSize = 4 ThirdSize = 5. Null is assumed to be the FirstSize")
    private Long boltAtSizeNo;

    /** System id of the spec short code. */
    @Excel(name = "System id of the spec short code.")
    private Long specShortCodeNo;

    /** The category of component (1 = PIPE, 2 = BOLTS, 3 = BOLTITEMS, 4 = INSUL). */
    @Excel(name = "The category of component (1 = PIPE, 2 = BOLTS, 3 = BOLTITEMS, 4 = INSUL).")
    private Long compCategoryNo;

    /** Default spec component group for this Component Type. */
    @Excel(name = "Default spec component group for this Component Type.")
    private Long specCompGroupNo;

    /** Unit of class. */
    @Excel(name = "Unit of class.")
    private String unitId;

    /** The commodity code prefix for this component type */
    @Excel(name = "The commodity code prefix for this component type")
    private String commodityCodePref;

    /** Defines Sub-Code uniqueness.(1 = Sequence, 2 = Set) */
    @Excel(name = "Defines Sub-Code uniqueness.(1 = Sequence, 2 = Set)")
    private Long uniqueness;

    /** Whether this class can have size less parts in standard catalogue. */
    @Excel(name = "Whether this class can have size less parts in standard catalogue.")
    private String allowSizeLess;

    /** Identifies defining application for this class. */
    @Excel(name = "Identifies defining application for this class.")
    private Long defApplNo;

    /** Initials for person defining the class */
    @Excel(name = "Initials for person defining the class")
    private String defUsrId;

    /** Definition date */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Definition date", width = 30, dateFormat = "yyyy-MM-dd")
    private Date defDate;

    /** Identifies updating application for this class. */
    @Excel(name = "Identifies updating application for this class.")
    private Long updApplNo;

    /** Initials for person latest updating the class */
    @Excel(name = "Initials for person latest updating the class")
    private String updUsrId;

    /** Latest update date */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Latest update date", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updDate;

    public void setClassNo(Long classNo) 
    {
        this.classNo = classNo;
    }

    public Long getClassNo() 
    {
        return classNo;
    }
    public void setClassId(String classId) 
    {
        this.classId = classId;
    }

    public String getClassId() 
    {
        return classId;
    }
    public void setCatalogNo(Long catalogNo) 
    {
        this.catalogNo = catalogNo;
    }

    public Long getCatalogNo() 
    {
        return catalogNo;
    }
    public void setSeqNo(Long seqNo) 
    {
        this.seqNo = seqNo;
    }

    public Long getSeqNo() 
    {
        return seqNo;
    }
    public void setDescr(String descr) 
    {
        this.descr = descr;
    }

    public String getDescr() 
    {
        return descr;
    }
    public void setStat(String stat) 
    {
        this.stat = stat;
    }

    public String getStat() 
    {
        return stat;
    }
    public void setApprovalStatusNo(Long approvalStatusNo) 
    {
        this.approvalStatusNo = approvalStatusNo;
    }

    public Long getApprovalStatusNo() 
    {
        return approvalStatusNo;
    }
    public void setParentClassNo(Long parentClassNo) 
    {
        this.parentClassNo = parentClassNo;
    }

    public Long getParentClassNo() 
    {
        return parentClassNo;
    }
    public void setDrawDisciplineNo(Long drawDisciplineNo) 
    {
        this.drawDisciplineNo = drawDisciplineNo;
    }

    public Long getDrawDisciplineNo() 
    {
        return drawDisciplineNo;
    }
    public void setCatEntityTypeNo(Long catEntityTypeNo) 
    {
        this.catEntityTypeNo = catEntityTypeNo;
    }

    public Long getCatEntityTypeNo() 
    {
        return catEntityTypeNo;
    }
    public void setCanInstantiate(String canInstantiate) 
    {
        this.canInstantiate = canInstantiate;
    }

    public String getCanInstantiate() 
    {
        return canInstantiate;
    }
    public void setBranchCode(String branchCode) 
    {
        this.branchCode = branchCode;
    }

    public String getBranchCode() 
    {
        return branchCode;
    }
    public void setDefScopeNo(Long defScopeNo) 
    {
        this.defScopeNo = defScopeNo;
    }

    public Long getDefScopeNo() 
    {
        return defScopeNo;
    }
    public void setBoltsRequired(String boltsRequired) 
    {
        this.boltsRequired = boltsRequired;
    }

    public String getBoltsRequired() 
    {
        return boltsRequired;
    }
    public void setBoltAtSizeNo(Long boltAtSizeNo) 
    {
        this.boltAtSizeNo = boltAtSizeNo;
    }

    public Long getBoltAtSizeNo() 
    {
        return boltAtSizeNo;
    }
    public void setSpecShortCodeNo(Long specShortCodeNo) 
    {
        this.specShortCodeNo = specShortCodeNo;
    }

    public Long getSpecShortCodeNo() 
    {
        return specShortCodeNo;
    }
    public void setCompCategoryNo(Long compCategoryNo) 
    {
        this.compCategoryNo = compCategoryNo;
    }

    public Long getCompCategoryNo() 
    {
        return compCategoryNo;
    }
    public void setSpecCompGroupNo(Long specCompGroupNo) 
    {
        this.specCompGroupNo = specCompGroupNo;
    }

    public Long getSpecCompGroupNo() 
    {
        return specCompGroupNo;
    }
    public void setUnitId(String unitId) 
    {
        this.unitId = unitId;
    }

    public String getUnitId() 
    {
        return unitId;
    }
    public void setCommodityCodePref(String commodityCodePref) 
    {
        this.commodityCodePref = commodityCodePref;
    }

    public String getCommodityCodePref() 
    {
        return commodityCodePref;
    }
    public void setUniqueness(Long uniqueness) 
    {
        this.uniqueness = uniqueness;
    }

    public Long getUniqueness() 
    {
        return uniqueness;
    }
    public void setAllowSizeLess(String allowSizeLess) 
    {
        this.allowSizeLess = allowSizeLess;
    }

    public String getAllowSizeLess() 
    {
        return allowSizeLess;
    }
    public void setDefApplNo(Long defApplNo) 
    {
        this.defApplNo = defApplNo;
    }

    public Long getDefApplNo() 
    {
        return defApplNo;
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
    public void setUpdApplNo(Long updApplNo) 
    {
        this.updApplNo = updApplNo;
    }

    public Long getUpdApplNo() 
    {
        return updApplNo;
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
            .append("classNo", getClassNo())
            .append("classId", getClassId())
            .append("catalogNo", getCatalogNo())
            .append("seqNo", getSeqNo())
            .append("descr", getDescr())
            .append("stat", getStat())
            .append("approvalStatusNo", getApprovalStatusNo())
            .append("parentClassNo", getParentClassNo())
            .append("drawDisciplineNo", getDrawDisciplineNo())
            .append("catEntityTypeNo", getCatEntityTypeNo())
            .append("canInstantiate", getCanInstantiate())
            .append("branchCode", getBranchCode())
            .append("defScopeNo", getDefScopeNo())
            .append("boltsRequired", getBoltsRequired())
            .append("boltAtSizeNo", getBoltAtSizeNo())
            .append("specShortCodeNo", getSpecShortCodeNo())
            .append("compCategoryNo", getCompCategoryNo())
            .append("specCompGroupNo", getSpecCompGroupNo())
            .append("unitId", getUnitId())
            .append("commodityCodePref", getCommodityCodePref())
            .append("uniqueness", getUniqueness())
            .append("allowSizeLess", getAllowSizeLess())
            .append("defApplNo", getDefApplNo())
            .append("defUsrId", getDefUsrId())
            .append("defDate", getDefDate())
            .append("updApplNo", getUpdApplNo())
            .append("updUsrId", getUpdUsrId())
            .append("updDate", getUpdDate())
            .toString();
    }
}
