package cn.com.infosouth.arj21.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.com.infosouth.common.annotation.Excel;
import cn.com.infosouth.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 计算参数对象 info_parameter_computed
 * 
 * @author kxnf
 * @date 2020-03-05
 */
public class InfoParameterComputed extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 参数主表id */
    private String prmId;

    /** 版本id */
    @Excel(name = "版本id")
    private String infoVersion;

    /** 类型：1:参数,2:平滑处理,(通用方法)3:模型 */
    private Integer infoType;

    /** 参数名 */
    @Excel(name = "参数名")
    private String prmName;

    /** 参数描述 */
    @Excel(name = "参数描述")
    private String prmDesc;

    /** null */
    private String prmLanguage;

    /** 脚本代码 */
    private String scriptCode;

    /** 脚本代码(python) */
    private String pythonCode;

    /** 编译顺序 */
    @Excel(name = "编译顺序")
    private Integer order;

    /** 引用的参数 */
    private String preferenceParams;

    /** 模型需要导出的参数 */
    private String exportParams;

    /** 导出数据起始点 */
    private Integer exportFrom;

    /** 导出数据结束点 */
    private Integer exportTo;

    /** 创建时间 */
    private Date createDate;

    /** 更新时间 */
    @Excel(name = "更新时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /** 备注 */
    private String remarks;

    /** 删除标志 */
    private String delFlag;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setPrmId(String prmId) 
    {
        this.prmId = prmId;
    }

    public String getPrmId() 
    {
        return prmId;
    }
    public void setInfoVersion(String infoVersion) 
    {
        this.infoVersion = infoVersion;
    }

    public String getInfoVersion() 
    {
        return infoVersion;
    }
    public void setInfoType(Integer infoType) 
    {
        this.infoType = infoType;
    }

    public Integer getInfoType() 
    {
        return infoType;
    }
    public void setPrmName(String prmName) 
    {
        this.prmName = prmName;
    }

    public String getPrmName() 
    {
        return prmName;
    }
    public void setPrmDesc(String prmDesc) 
    {
        this.prmDesc = prmDesc;
    }

    public String getPrmDesc() 
    {
        return prmDesc;
    }
    public void setPrmLanguage(String prmLanguage) 
    {
        this.prmLanguage = prmLanguage;
    }

    public String getPrmLanguage() 
    {
        return prmLanguage;
    }
    public void setScriptCode(String scriptCode) 
    {
        this.scriptCode = scriptCode;
    }

    public String getScriptCode() 
    {
        return scriptCode;
    }
    public void setPythonCode(String pythonCode) 
    {
        this.pythonCode = pythonCode;
    }

    public String getPythonCode() 
    {
        return pythonCode;
    }
    public void setOrder(Integer order) 
    {
        this.order = order;
    }

    public Integer getOrder() 
    {
        return order;
    }
    public void setPreferenceParams(String preferenceParams) 
    {
        this.preferenceParams = preferenceParams;
    }

    public String getPreferenceParams() 
    {
        return preferenceParams;
    }
    public void setExportParams(String exportParams) 
    {
        this.exportParams = exportParams;
    }

    public String getExportParams() 
    {
        return exportParams;
    }
    public void setExportFrom(Integer exportFrom) 
    {
        this.exportFrom = exportFrom;
    }

    public Integer getExportFrom() 
    {
        return exportFrom;
    }
    public void setExportTo(Integer exportTo) 
    {
        this.exportTo = exportTo;
    }

    public Integer getExportTo() 
    {
        return exportTo;
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
            .append("prmId", getPrmId())
            .append("infoVersion", getInfoVersion())
            .append("infoType", getInfoType())
            .append("prmName", getPrmName())
            .append("prmDesc", getPrmDesc())
            .append("prmLanguage", getPrmLanguage())
            .append("scriptCode", getScriptCode())
            .append("pythonCode", getPythonCode())
            .append("order", getOrder())
            .append("preferenceParams", getPreferenceParams())
            .append("exportParams", getExportParams())
            .append("exportFrom", getExportFrom())
            .append("exportTo", getExportTo())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .toString();
    }
}
