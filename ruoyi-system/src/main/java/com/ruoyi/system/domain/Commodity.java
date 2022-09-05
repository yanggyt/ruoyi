package com.ruoyi.system.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

/**
 * CC码对象 commodity
 * 
 * @author ruoyi
 * @date 2021-09-02
 */
public class Commodity extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** System id of commodity.. */
    private Long commodityNo;

    /** Commodity code. */
    @Excel(name = "Commodity code.")
    private String commodityId;

    /** Set to COMMODITY_NO to fake uniqueness of the Commodity Code. Set to 0 for approved Commodities enforce uniqueness. */
    @Excel(name = "Set to COMMODITY_NO to fake uniqueness of the Commodity Code. Set to 0 for approved Commodities enforce uniqueness.")
    private Long fakeUnique;

    /** System id of catalogue. */
    @Excel(name = "System id of catalogue.")
    private Long catalogNo;

    /** System id of class for this commodity (and its parts). */
    @Excel(name = "System id of class for this commodity (and its parts).")
    private Long commodityClassNo;

    /** Method used to define this Commodity (1 = Generated, 2 = UserDefined, 3 = UserDefinedPartNumber, 4 = SubCode). */
    @Excel(name = "Method used to define this Commodity (1 = Generated, 2 = UserDefined, 3 = UserDefinedPartNumber, 4 = SubCode).")
    private Long defMethodNo;

    /** Whether datasheet required (1 = True, 0 = False). */
    @Excel(name = "Whether datasheet required (1 = True, 0 = False).")
    private String datasheetReqd;

    /** Whether part numbers for this Commodity may appear on requisitions (and other formal project reports) .(1 = True, 0 - False). */
    @Excel(name = "Whether part numbers for this Commodity may appear on requisitions (and other formal project reports) .(1 = True, 0 - False).")
    private String prntPartNo;

    /** The unit of measure for the quantity of this commodity (e.g. whether it is purchased by length or by number off). */
    @Excel(name = "The unit of measure for the quantity of this commodity (e.g. whether it is purchased by length or by number off).")
    private String unitId;

    /** Legacy commodity code. */
    @Excel(name = "Legacy commodity code.")
    private String commodityCodeOrig;

    /** Approval status of Commodity (1 =  Working, 2 = Approved). */
    @Excel(name = "Approval status of Commodity (1 =  Working, 2 = Approved).")
    private Long approvalStatusNo;

    /** Whether this part is obsolete. (1 = Active, 0 = Obsolete). */
    @Excel(name = "Whether this part is obsolete. (1 = Active, 0 = Obsolete).")
    private String stat;

    /** Initials for person defining the COMMODITY. */
    @Excel(name = "Initials for person defining the COMMODITY.")
    private String defUsrId;

    /** Definition date. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Definition date.", width = 30, dateFormat = "yyyy-MM-dd")
    private Date defDate;

    /** Initials for person latest updating the COMMODITY. */
    @Excel(name = "Initials for person latest updating the COMMODITY.")
    private String updUsrId;

    /** Latest update date. */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "Latest update date.", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updDate;

    /** Unique id of the spec entry */
    @Excel(name = "Unique id of the spec entry")
    private Long specEntryNo;

    public void setCommodityNo(Long commodityNo) 
    {
        this.commodityNo = commodityNo;
    }

    public Long getCommodityNo() 
    {
        return commodityNo;
    }
    public void setCommodityId(String commodityId) 
    {
        this.commodityId = commodityId;
    }

    public String getCommodityId() 
    {
        return commodityId;
    }
    public void setFakeUnique(Long fakeUnique) 
    {
        this.fakeUnique = fakeUnique;
    }

    public Long getFakeUnique() 
    {
        return fakeUnique;
    }
    public void setCatalogNo(Long catalogNo) 
    {
        this.catalogNo = catalogNo;
    }

    public Long getCatalogNo() 
    {
        return catalogNo;
    }
    public void setCommodityClassNo(Long commodityClassNo) 
    {
        this.commodityClassNo = commodityClassNo;
    }

    public Long getCommodityClassNo() 
    {
        return commodityClassNo;
    }
    public void setDefMethodNo(Long defMethodNo) 
    {
        this.defMethodNo = defMethodNo;
    }

    public Long getDefMethodNo() 
    {
        return defMethodNo;
    }
    public void setDatasheetReqd(String datasheetReqd) 
    {
        this.datasheetReqd = datasheetReqd;
    }

    public String getDatasheetReqd() 
    {
        return datasheetReqd;
    }
    public void setPrntPartNo(String prntPartNo) 
    {
        this.prntPartNo = prntPartNo;
    }

    public String getPrntPartNo() 
    {
        return prntPartNo;
    }
    public void setUnitId(String unitId) 
    {
        this.unitId = unitId;
    }

    public String getUnitId() 
    {
        return unitId;
    }
    public void setCommodityCodeOrig(String commodityCodeOrig) 
    {
        this.commodityCodeOrig = commodityCodeOrig;
    }

    public String getCommodityCodeOrig() 
    {
        return commodityCodeOrig;
    }
    public void setApprovalStatusNo(Long approvalStatusNo) 
    {
        this.approvalStatusNo = approvalStatusNo;
    }

    public Long getApprovalStatusNo() 
    {
        return approvalStatusNo;
    }
    public void setStat(String stat) 
    {
        this.stat = stat;
    }

    public String getStat() 
    {
        return stat;
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
    public void setSpecEntryNo(Long specEntryNo) 
    {
        this.specEntryNo = specEntryNo;
    }

    public Long getSpecEntryNo() 
    {
        return specEntryNo;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("commodityNo", getCommodityNo())
            .append("commodityId", getCommodityId())
            .append("fakeUnique", getFakeUnique())
            .append("catalogNo", getCatalogNo())
            .append("commodityClassNo", getCommodityClassNo())
            .append("defMethodNo", getDefMethodNo())
            .append("datasheetReqd", getDatasheetReqd())
            .append("prntPartNo", getPrntPartNo())
            .append("unitId", getUnitId())
            .append("commodityCodeOrig", getCommodityCodeOrig())
            .append("approvalStatusNo", getApprovalStatusNo())
            .append("stat", getStat())
            .append("defUsrId", getDefUsrId())
            .append("defDate", getDefDate())
            .append("updUsrId", getUpdUsrId())
            .append("updDate", getUpdDate())
            .append("specEntryNo", getSpecEntryNo())
            .toString();
    }
}
