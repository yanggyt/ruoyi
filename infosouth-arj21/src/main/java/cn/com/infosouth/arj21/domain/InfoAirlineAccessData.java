package cn.com.infosouth.arj21.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.com.infosouth.common.annotation.Excel;
import cn.com.infosouth.common.core.domain.BaseEntity;

/**
 * 航空公司数据接入对象 info_airline_access_data
 * 
 * @author kxnf
 * @date 2020-03-06
 */
public class InfoAirlineAccessData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 序号 */
    private String fileNo;

    /** 公司名 */
    @Excel(name = "公司名")
    private String company;

    /** 文件名 */
    @Excel(name = "文件名")
    private String fileName;

    /** 文件大小 */
    @Excel(name = "文件大小")
    private String fileSize;

    /** 文件类型 */
    @Excel(name = "文件类型")
    private String fileType;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setFileNo(String fileNo) 
    {
        this.fileNo = fileNo;
    }

    public String getFileNo() 
    {
        return fileNo;
    }
    public void setCompany(String company) 
    {
        this.company = company;
    }

    public String getCompany() 
    {
        return company;
    }
    public void setFileName(String fileName) 
    {
        this.fileName = fileName;
    }

    public String getFileName() 
    {
        return fileName;
    }
    public void setFileSize(String fileSize) 
    {
        this.fileSize = fileSize;
    }

    public String getFileSize() 
    {
        return fileSize;
    }
    public void setFileType(String fileType) 
    {
        this.fileType = fileType;
    }

    public String getFileType() 
    {
        return fileType;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("fileNo", getFileNo())
            .append("company", getCompany())
            .append("fileName", getFileName())
            .append("fileSize", getFileSize())
            .append("createTime", getCreateTime())
            .append("fileType", getFileType())
            .toString();
    }
}
