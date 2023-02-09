package com.ruoyi.system.domain.paymentRequest;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Date;

public class PaymentRequestFile {
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;
    /** 相关联主键id */
    private Long associationId;
    /**类别 */
    /**1为流程相关附件，2为相关模板 **/
    private Integer type;
    /** 文件路径 */
    private String file;
    /** 文件名 */
    private String fileName;
    /** 创建时间 */
    private Date createTime;

    public void setId(Long id)
    {
        this.id = id;
    }

    public Long getId()
    {
        return id;
    }
    public void setAssociationId(Long associationId)
    {
        this.associationId = associationId;
    }

    public Long getAssociationId()
    {
        return associationId;
    }
    public void setType(Integer type)
    {
        this.type = type;
    }

    public Integer getType()
    {
        return type;
    }
    public void setFile(String file)
    {
        this.file = file;
    }

    public String getFile()
    {
        return file;
    }
    public void setFileName(String fileName)
    {
        this.fileName = fileName;
    }

    public String getFileName()
    {
        return fileName;
    }
    public void setCreateTime(Date createTime)
    {
        this.createTime = createTime;
    }

    public Date getCreateTime()
    {
        return createTime;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("id", getId())
                .append("associationId", getAssociationId())
                .append("type", getType())
                .append("file", getFile())
                .append("fileName", getFileName())
                .append("createTime", getCreateTime())
                .toString();
    }
}
