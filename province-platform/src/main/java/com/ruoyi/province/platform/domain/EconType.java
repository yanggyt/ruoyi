package com.ruoyi.province.platform.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 经济类型对象 platf_econ_type
 * 
 * @author dalin
 * @date 2020-12-08
 */
public class EconType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Integer econId;

    /** 名称 */
    @Excel(name = "名称")
    private String econName;

    /** 单据号 */
    @Excel(name = "单据号")
    private String docNum;

    /** 资料状态 0有效 1无效 */
    @Excel(name = "资料状态 0有效 1无效")
    private String status;

    /** 删除标志 删除标志（0代表存在 2代表删除） */
    private String delFlag;

    public void setEconId(Integer econId) 
    {
        this.econId = econId;
    }

    public Integer getEconId() 
    {
        return econId;
    }
    public void setEconName(String econName) 
    {
        this.econName = econName;
    }

    public String getEconName() 
    {
        return econName;
    }
    public void setDocNum(String docNum) 
    {
        this.docNum = docNum;
    }

    public String getDocNum() 
    {
        return docNum;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
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
            .append("econId", getEconId())
            .append("econName", getEconName())
            .append("docNum", getDocNum())
            .append("status", getStatus())
            .append("remark", getRemark())
            .append("delFlag", getDelFlag())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
