package cn.com.infosouth.arj21.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import cn.com.infosouth.common.annotation.Excel;
import cn.com.infosouth.common.core.domain.BaseEntity;

import java.util.Date;
import java.util.List;

/**
 * 航班信息对象 info_flight
 * 
 * @author kxnf
 * @date 2020-03-04
 */
public class InfoFlight extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 航班日期 */
    @Excel(name = "航班日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date flDate;

    /** 航班号 */
    @Excel(name = "航班号")
    private String flNo;

    /** 飞机号 */
    @Excel(name = "飞机号")
    private String arn;

    /** 机型 */
    @Excel(name = "机型")
    private String acType;

    /** 起飞机场 */
    @Excel(name = "起飞机场")
    private String pod;

    /** 到达机场 */
    @Excel(name = "到达机场")
    private String poa;

    /** 所属公司 */
    @Excel(name = "所属公司")
    private String airline;

    /** csv存储路径 */
    @Excel(name = "csv存储路径")
    private String csvPath;

    /** csv文件名 */
    @Excel(name = "csv文件名")
    private String csvName;

    /** 资源目录ID（info_resource.id） */
    @Excel(name = "资源目录ID", readConverterExp = "i=nfo_resource.id")
    private String infoResourceId;

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
    
	private List<String> idsList;
	
	private List<DataAuthCondition> conditionsList;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setFlDate(Date flDate) 
    {
        this.flDate = flDate;
    }

    public Date getFlDate() 
    {
        return flDate;
    }
    public void setFlNo(String flNo) 
    {
        this.flNo = flNo;
    }

    public String getFlNo() 
    {
        return flNo;
    }
    public void setArn(String arn) 
    {
        this.arn = arn;
    }

    public String getArn() 
    {
        return arn;
    }
    public void setAcType(String acType) 
    {
        this.acType = acType;
    }

    public String getAcType() 
    {
        return acType;
    }
    public void setPod(String pod) 
    {
        this.pod = pod;
    }

    public String getPod() 
    {
        return pod;
    }
    public void setPoa(String poa) 
    {
        this.poa = poa;
    }

    public String getPoa() 
    {
        return poa;
    }
    public void setAirline(String airline) 
    {
        this.airline = airline;
    }

    public String getAirline() 
    {
        return airline;
    }
    public void setCsvPath(String csvPath) 
    {
        this.csvPath = csvPath;
    }

    public String getCsvPath() 
    {
        return csvPath;
    }
    public void setCsvName(String csvName) 
    {
        this.csvName = csvName;
    }

    public String getCsvName() 
    {
        return csvName;
    }
    public void setInfoResourceId(String infoResourceId) 
    {
        this.infoResourceId = infoResourceId;
    }

    public String getInfoResourceId() 
    {
        return infoResourceId;
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

    public List<String> getIdsList() {
		return idsList;
	}

	public void setIdsList(List<String> idsList) {
		this.idsList = idsList;
	}

	public List<DataAuthCondition> getConditionsList() {
		return conditionsList;
	}

	public void setConditionsList(List<DataAuthCondition> conditionsList) {
		this.conditionsList = conditionsList;
	}

	@Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("flDate", getFlDate())
            .append("flNo", getFlNo())
            .append("arn", getArn())
            .append("acType", getAcType())
            .append("pod", getPod())
            .append("poa", getPoa())
            .append("airline", getAirline())
            .append("csvPath", getCsvPath())
            .append("csvName", getCsvName())
            .append("infoResourceId", getInfoResourceId())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
