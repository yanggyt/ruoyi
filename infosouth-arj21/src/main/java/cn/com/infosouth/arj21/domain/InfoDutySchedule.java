package cn.com.infosouth.arj21.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import cn.com.infosouth.common.annotation.Excel;
import cn.com.infosouth.common.core.domain.BaseEntity;
import java.util.Date;

/**
 * 任务调度对象 info_duty_schedule
 * 
 * @author kxnf
 * @date 2020-03-05
 */
public class InfoDutySchedule extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** 任务名称 */
    @Excel(name = "任务名称")
    private String jobName;

    /** 模型名称，多个用;分割 */
    @Excel(name = "模型名称，多个用;分割")
    private String modelName;

    /** 机型，多个用;分割，为空表示不设限制 */
    @Excel(name = "机型，多个用;分割，为空表示不设限制")
    private String acType;

    /** 飞机号，多个用;分割，为空表示不设限制 */
    @Excel(name = "飞机号，多个用;分割，为空表示不设限制")
    private String acReg;

    /** 对应版本 */
    @Excel(name = "对应版本")
    private Long versionId;

    /** 开始日期，为空表示不设限制 */
    @Excel(name = "开始日期，为空表示不设限制", width = 30, dateFormat = "yyyy-MM-dd")
    private Date startDate;

    /** 结束日期，为空表示不设限制 */
    @Excel(name = "结束日期，为空表示不设限制", width = 30, dateFormat = "yyyy-MM-dd")
    private Date endDate;

    /** 任务描述 */
    @Excel(name = "任务描述")
    private String jobDesc;

    /** 具体任务的执行状态 */
    @Excel(name = "具体任务的执行状态")
    private Integer isExcute;

    /** 任务状态，正常/异常/ */
    @Excel(name = "任务状态，正常/异常/")
    private String jobStatus;

    /** 执行周期 */
    @Excel(name = "执行周期")
    private String excuteFreq;

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

    /** job配置id */
    @Excel(name = "job配置id")
    private String jobConf;

    /** 程序执行状态,-2,定时器停止；-1，定时器执行； */
    @Excel(name = "程序执行状态,-2,定时器停止；-1，定时器执行；")
    private Integer executeStatus;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setJobName(String jobName) 
    {
        this.jobName = jobName;
    }

    public String getJobName() 
    {
        return jobName;
    }
    public void setModelName(String modelName) 
    {
        this.modelName = modelName;
    }

    public String getModelName() 
    {
        return modelName;
    }
    public void setAcType(String acType) 
    {
        this.acType = acType;
    }

    public String getAcType() 
    {
        return acType;
    }
    public void setAcReg(String acReg) 
    {
        this.acReg = acReg;
    }

    public String getAcReg() 
    {
        return acReg;
    }
    public void setVersionId(Long versionId) 
    {
        this.versionId = versionId;
    }

    public Long getVersionId() 
    {
        return versionId;
    }
    public void setStartDate(Date startDate) 
    {
        this.startDate = startDate;
    }

    public Date getStartDate() 
    {
        return startDate;
    }
    public void setEndDate(Date endDate) 
    {
        this.endDate = endDate;
    }

    public Date getEndDate() 
    {
        return endDate;
    }
    public void setJobDesc(String jobDesc) 
    {
        this.jobDesc = jobDesc;
    }

    public String getJobDesc() 
    {
        return jobDesc;
    }
    public void setIsExcute(Integer isExcute) 
    {
        this.isExcute = isExcute;
    }

    public Integer getIsExcute() 
    {
        return isExcute;
    }
    public void setJobStatus(String jobStatus) 
    {
        this.jobStatus = jobStatus;
    }

    public String getJobStatus() 
    {
        return jobStatus;
    }
    public void setExcuteFreq(String excuteFreq) 
    {
        this.excuteFreq = excuteFreq;
    }

    public String getExcuteFreq() 
    {
        return excuteFreq;
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
    public void setJobConf(String jobConf) 
    {
        this.jobConf = jobConf;
    }

    public String getJobConf() 
    {
        return jobConf;
    }
    public void setExecuteStatus(Integer executeStatus) 
    {
        this.executeStatus = executeStatus;
    }

    public Integer getExecuteStatus() 
    {
        return executeStatus;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("jobName", getJobName())
            .append("modelName", getModelName())
            .append("acType", getAcType())
            .append("acReg", getAcReg())
            .append("versionId", getVersionId())
            .append("startDate", getStartDate())
            .append("endDate", getEndDate())
            .append("jobDesc", getJobDesc())
            .append("isExcute", getIsExcute())
            .append("jobStatus", getJobStatus())
            .append("excuteFreq", getExcuteFreq())
            .append("createBy", getCreateBy())
            .append("createDate", getCreateDate())
            .append("updateBy", getUpdateBy())
            .append("updateDate", getUpdateDate())
            .append("remarks", getRemarks())
            .append("delFlag", getDelFlag())
            .append("jobConf", getJobConf())
            .append("executeStatus", getExecuteStatus())
            .toString();
    }
}
