package com.ruoyi.province.platform.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
/**
 * 经济类型对象 platf_econ_type
 *
 * @author dalin
 * @date 2020-12-24
 */
public class EconType extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 主键 */
    private Long econId;

    /** 名称 */
    @Excel(name = "名称")
    private String econName;

    /** 单据号 */
    @Excel(name = "单据号")
    private String docNum;

    /** 资料状态 */
    @Excel(name = "资料状态")
    private String status;

    /** 删除标志 删除标志 */
    private String delFlag;

    /** 制单人 */
    @Excel(name = "制单人")
    private String createByuserName;

    /** 修改人 */
    @Excel(name = "修改人")
    private String updateByuserName;

    public void setEconId(Long econId)
    {
        this.econId = econId;
    }

    public Long getEconId()
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
    public void setCreateByuserName(String createByuserName)
    {
        this.createByuserName = createByuserName;
    }

    public String getCreateByuserName()
    {
        return createByuserName;
    }
    public void setUpdateByuserName(String updateByuserName)
    {
        this.updateByuserName = updateByuserName;
    }

    public String getUpdateByuserName()
    {
        return updateByuserName;
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
                .append("createByuserName", getCreateByuserName())
                .append("createBy", getCreateBy())
                .append("createTime", getCreateTime())
                .append("updateByuserName", getUpdateByuserName())
                .append("updateBy", getUpdateBy())
                .append("updateTime", getUpdateTime())
                .toString();
    }
}
