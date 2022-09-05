package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 编码申请数据对象 sys_app_data
 * 
 * @author ruoyi
 * @date 2021-09-29
 */
public class SysAppData extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 申请单明细主键seq_sys_app_data.nextval */
    private Long appDataId;

    /** 申请单ID */
    @Excel(name = "申请单ID")
    private Long appId;

    /** CC或者ID（0:CC,1:ID） */
    @Excel(name = "CC或者ID", readConverterExp = "0=:CC,1:ID")
    private String type;

    /** CC代码或ID代码 */
    @Excel(name = "CC代码或ID代码")
    private String code;

    /** 设计描述 */
    @Excel(name = "设计描述")
    private String designDesc;

    /** 中文公制短描述 */
    @Excel(name = "中文公制短描述")
    private String zhCnShortDesc;

    /** 中文公制长描述 */
    @Excel(name = "中文公制长描述")
    private String zhCnLongDesc;

    /** 中文英制短描述 */
    @Excel(name = "中文英制短描述")
    private String zhEnShortDesc;

    /** 中文英制长描述 */
    @Excel(name = "中文英制长描述")
    private String zhEnLongDesc;

    /** 俄文公制短描述 */
    @Excel(name = "俄文公制短描述")
    private String ruCnShortDesc;

    /** 俄文公制长描述 */
    @Excel(name = "俄文公制长描述")
    private String ruCnLongDesc;

    /** 俄文英制短描述 */
    @Excel(name = "俄文英制短描述")
    private String ruEnShortDesc;

    /** 俄文英制长描述 */
    @Excel(name = "俄文英制长描述")
    private String ruEnLongDesc;

    /** 英文公制短描述 */
    @Excel(name = "英文公制短描述")
    private String enCnShortDesc;

    /** 英文公制长描述 */
    @Excel(name = "英文公制长描述")
    private String enCnLongDesc;

    /** 英文英制短描述 */
    @Excel(name = "英文英制短描述")
    private String enEnShortDesc;

    /** 英文英制长描述 */
    @Excel(name = "英文英制长描述")
    private String enEnLongDesc;

    /** 状态（0正常 1停用） */
    @Excel(name = "状态", readConverterExp = "0=正常,1=停用")
    private String status;

    public void setAppDataId(Long appDataId) 
    {
        this.appDataId = appDataId;
    }

    public Long getAppDataId() 
    {
        return appDataId;
    }
    public void setAppId(Long appId) 
    {
        this.appId = appId;
    }

    public Long getAppId() 
    {
        return appId;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setCode(String code) 
    {
        this.code = code;
    }

    public String getCode() 
    {
        return code;
    }
    public void setDesignDesc(String designDesc) 
    {
        this.designDesc = designDesc;
    }

    public String getDesignDesc() 
    {
        return designDesc;
    }
    public void setZhCnShortDesc(String zhCnShortDesc) 
    {
        this.zhCnShortDesc = zhCnShortDesc;
    }

    public String getZhCnShortDesc() 
    {
        return zhCnShortDesc;
    }
    public void setZhCnLongDesc(String zhCnLongDesc) 
    {
        this.zhCnLongDesc = zhCnLongDesc;
    }

    public String getZhCnLongDesc() 
    {
        return zhCnLongDesc;
    }
    public void setZhEnShortDesc(String zhEnShortDesc) 
    {
        this.zhEnShortDesc = zhEnShortDesc;
    }

    public String getZhEnShortDesc() 
    {
        return zhEnShortDesc;
    }
    public void setZhEnLongDesc(String zhEnLongDesc) 
    {
        this.zhEnLongDesc = zhEnLongDesc;
    }

    public String getZhEnLongDesc() 
    {
        return zhEnLongDesc;
    }
    public void setRuCnShortDesc(String ruCnShortDesc) 
    {
        this.ruCnShortDesc = ruCnShortDesc;
    }

    public String getRuCnShortDesc() 
    {
        return ruCnShortDesc;
    }
    public void setRuCnLongDesc(String ruCnLongDesc) 
    {
        this.ruCnLongDesc = ruCnLongDesc;
    }

    public String getRuCnLongDesc() 
    {
        return ruCnLongDesc;
    }
    public void setRuEnShortDesc(String ruEnShortDesc) 
    {
        this.ruEnShortDesc = ruEnShortDesc;
    }

    public String getRuEnShortDesc() 
    {
        return ruEnShortDesc;
    }
    public void setRuEnLongDesc(String ruEnLongDesc) 
    {
        this.ruEnLongDesc = ruEnLongDesc;
    }

    public String getRuEnLongDesc() 
    {
        return ruEnLongDesc;
    }
    public void setEnCnShortDesc(String enCnShortDesc) 
    {
        this.enCnShortDesc = enCnShortDesc;
    }

    public String getEnCnShortDesc() 
    {
        return enCnShortDesc;
    }
    public void setEnCnLongDesc(String enCnLongDesc) 
    {
        this.enCnLongDesc = enCnLongDesc;
    }

    public String getEnCnLongDesc() 
    {
        return enCnLongDesc;
    }
    public void setEnEnShortDesc(String enEnShortDesc) 
    {
        this.enEnShortDesc = enEnShortDesc;
    }

    public String getEnEnShortDesc() 
    {
        return enEnShortDesc;
    }
    public void setEnEnLongDesc(String enEnLongDesc) 
    {
        this.enEnLongDesc = enEnLongDesc;
    }

    public String getEnEnLongDesc() 
    {
        return enEnLongDesc;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("appDataId", getAppDataId())
            .append("appId", getAppId())
            .append("type", getType())
            .append("code", getCode())
            .append("designDesc", getDesignDesc())
            .append("zhCnShortDesc", getZhCnShortDesc())
            .append("zhCnLongDesc", getZhCnLongDesc())
            .append("zhEnShortDesc", getZhEnShortDesc())
            .append("zhEnLongDesc", getZhEnLongDesc())
            .append("ruCnShortDesc", getRuCnShortDesc())
            .append("ruCnLongDesc", getRuCnLongDesc())
            .append("ruEnShortDesc", getRuEnShortDesc())
            .append("ruEnLongDesc", getRuEnLongDesc())
            .append("enCnShortDesc", getEnCnShortDesc())
            .append("enCnLongDesc", getEnCnLongDesc())
            .append("enEnShortDesc", getEnEnShortDesc())
            .append("enEnLongDesc", getEnEnLongDesc())
            .append("status", getStatus())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
