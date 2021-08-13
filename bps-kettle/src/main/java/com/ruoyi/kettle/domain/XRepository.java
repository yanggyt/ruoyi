package com.ruoyi.kettle.domain;

import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 资源库对象 kettle_repository
 * 
 * @author kone
 * @date 2021-07-12
 */
public class XRepository extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /**  */
    @Excel(name = "")
    private String repoId;

    /** 资源库名称 */
    @Excel(name = "资源库名称")
    private String repoName;

    /** 当资源库类型是db时候的用户名 */
    private String repoUsername;

    /** 当资源库类型是db时候的密码 */
    private String repoPassword;

    /** db类型 */
    private String repoType;

    /** 当资源库类型是db时候的连接类型 */
    private String dbAccess;

    /** 当资源库类型是db时候的ip */
    private String dbHost;

    /** 当资源库类型是db时候的端口 */
    private String dbPort;

    /** 当资源库类型是db时候的db库名 */
    private String dbName;

    /** 当资源库类型是db时候的db用户名 */
    private String dbUsername;

    /** 当资源库类型是db时候的db用户密码 */
    private String dbPassword;

    /** 软删除 */
    private int isDel;

    /**  */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createdTime;

    /**  */
    @Excel(name = "")
    private String createdBy;

    /** 资源库类型 */
    @Excel(name = "资源库类型")
    private String type;

    /** 基础路径 */
    @Excel(name = "基础路径")
    private String baseDir;

    public void setId(Long id) 
    {
        this.id = id;
    }

    public Long getId() 
    {
        return id;
    }
    public void setRepoId(String repoId) 
    {
        this.repoId = repoId;
    }

    public String getRepoId() 
    {
        return repoId;
    }
    public void setRepoName(String repoName) 
    {
        this.repoName = repoName;
    }

    public String getRepoName() 
    {
        return repoName;
    }
    public void setRepoUsername(String repoUsername) 
    {
        this.repoUsername = repoUsername;
    }

    public String getRepoUsername() 
    {
        return repoUsername;
    }
    public void setRepoPassword(String repoPassword) 
    {
        this.repoPassword = repoPassword;
    }

    public String getRepoPassword() 
    {
        return repoPassword;
    }
    public void setRepoType(String repoType) 
    {
        this.repoType = repoType;
    }

    public String getRepoType() 
    {
        return repoType;
    }
    public void setDbAccess(String dbAccess) 
    {
        this.dbAccess = dbAccess;
    }

    public String getDbAccess() 
    {
        return dbAccess;
    }
    public void setDbHost(String dbHost) 
    {
        this.dbHost = dbHost;
    }

    public String getDbHost() 
    {
        return dbHost;
    }
    public void setDbPort(String dbPort) 
    {
        this.dbPort = dbPort;
    }

    public String getDbPort() 
    {
        return dbPort;
    }
    public void setDbName(String dbName) 
    {
        this.dbName = dbName;
    }

    public String getDbName() 
    {
        return dbName;
    }
    public void setDbUsername(String dbUsername) 
    {
        this.dbUsername = dbUsername;
    }

    public String getDbUsername() 
    {
        return dbUsername;
    }
    public void setDbPassword(String dbPassword) 
    {
        this.dbPassword = dbPassword;
    }

    public String getDbPassword() 
    {
        return dbPassword;
    }
    public void setIsDel(int isDel)
    {
        this.isDel = isDel;
    }

    public int getIsDel()
    {
        return isDel;
    }
    public void setCreatedTime(Date createdTime) 
    {
        this.createdTime = createdTime;
    }

    public Date getCreatedTime() 
    {
        return createdTime;
    }
    public void setCreatedBy(String createdBy) 
    {
        this.createdBy = createdBy;
    }

    public String getCreatedBy() 
    {
        return createdBy;
    }
    public void setType(String type) 
    {
        this.type = type;
    }

    public String getType() 
    {
        return type;
    }
    public void setBaseDir(String baseDir) 
    {
        this.baseDir = baseDir;
    }

    public String getBaseDir() 
    {
        return baseDir;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("repoId", getRepoId())
            .append("repoName", getRepoName())
            .append("repoUsername", getRepoUsername())
            .append("repoPassword", getRepoPassword())
            .append("repoType", getRepoType())
            .append("dbAccess", getDbAccess())
            .append("dbHost", getDbHost())
            .append("dbPort", getDbPort())
            .append("dbName", getDbName())
            .append("dbUsername", getDbUsername())
            .append("dbPassword", getDbPassword())
            .append("isDel", getIsDel())
            .append("createdTime", getCreatedTime())
            .append("updateTime", getUpdateTime())
            .append("createdBy", getCreatedBy())
            .append("updateBy", getUpdateBy())
            .append("type", getType())
            .append("baseDir", getBaseDir())
            .toString();
    }
}
