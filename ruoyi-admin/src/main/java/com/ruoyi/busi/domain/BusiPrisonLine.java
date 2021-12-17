package com.ruoyi.busi.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.TreeEntity;

/**
 * 监区产线对象 busi_prison_line
 * 
 * @author WangCL
 * @date 2021-12-17
 */
public class BusiPrisonLine extends TreeEntity
{
    private static final long serialVersionUID = 1L;

    /** ID主键 */
    private Long id;

    /** 所属监区 */
    private Long pid;

    /** 所属监区名称 */
    @Excel(name = "所属监区")
    private String pname;

    /** 名称 */
    @Excel(name = "名称")
    private String disname;

    /** 负责人 */
    @Excel(name = "负责人")
    private String leader;

    /** 人员数量 */
    @Excel(name = "人员数量")
    private Integer personNumber;

    /** 状态 */
    @Excel(name = "状态")
    private String status;

    /** 类型 */
    @Excel(name = "类型")
    private String classify;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setPid(Long pid) 
    {
        this.pid = pid;
    }

    public Long getPid() 
    {
        return pid;
    }
    public void setDisname(String disname) 
    {
        this.disname = disname;
    }

    public String getDisname() 
    {
        return disname;
    }
    public void setLeader(String leader) 
    {
        this.leader = leader;
    }

    public String getLeader() 
    {
        return leader;
    }
    public void setPersonNumber(Integer personNumber) 
    {
        this.personNumber = personNumber;
    }

    public Integer getPersonNumber() 
    {
        return personNumber;
    }
    public void setStatus(String status) 
    {
        this.status = status;
    }

    public String getStatus() 
    {
        return status;
    }
    public void setClassify(String classify) 
    {
        this.classify = classify;
    }

    public String getClassify() 
    {
        return classify;
    }

    public String getPname() {
        return pname;
    }

    public void setPname(String pname) {
        this.pname = pname;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("pid", getPid())
            .append("pname", getPname())
            .append("disname", getDisname())
            .append("leader", getLeader())
            .append("personNumber", getPersonNumber())
            .append("status", getStatus())
            .append("classify", getClassify())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
