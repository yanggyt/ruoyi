package cn.com.infosouth.arj21.domain;

import java.util.Date;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import cn.com.infosouth.common.annotation.Excel;
import cn.com.infosouth.common.core.domain.BaseEntity;

/**
 * InfoChoosedMulticsvParamsTemplate对象 info_choosed_multicsv_params_template
 * 
 * @author kxnf
 * @date 2020-03-08
 */
public class InfoChoosedMulticsvParamsTemplate extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** null */
    private String id;

    /** null */
    @Excel(name = "null")
    private String templatename;

    /** null */
    @Excel(name = "null")
    private String loginAdmin;

    /** null */
    @Excel(name = "null")
    private String arn;

    /** null */
    @Excel(name = "null")
    private String actype;

    /** null */
    @Excel(name = "null")
    private String choosedParamsstr;

    /** 创建时间 */
    @Excel(name = "创建时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date createTime;
    
    /** 构型 */
    @Excel(name = "构型")
    private String versionid;

    public void setId(String id) 
    {
        this.id = id;
    }

    public String getId() 
    {
        return id;
    }
    public void setTemplatename(String templatename) 
    {
        this.templatename = templatename;
    }

    public String getTemplatename() 
    {
        return templatename;
    }
    public void setLoginAdmin(String loginAdmin) 
    {
        this.loginAdmin = loginAdmin;
    }

    public String getLoginAdmin() 
    {
        return loginAdmin;
    }
    public void setArn(String arn) 
    {
        this.arn = arn;
    }

    public String getArn() 
    {
        return arn;
    }
    public void setActype(String actype) 
    {
        this.actype = actype;
    }

    public String getActype() 
    {
        return actype;
    }
    public void setChoosedParamsstr(String choosedParamsstr) 
    {
        this.choosedParamsstr = choosedParamsstr;
    }

    public String getChoosedParamsstr() 
    {
        return choosedParamsstr;
    }
    
    public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public void setVersionid(String versionid) 
    {
        this.versionid = versionid;
    }

    public String getVersionid() 
    {
        return versionid;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("templatename", getTemplatename())
            .append("loginAdmin", getLoginAdmin())
            .append("arn", getArn())
            .append("actype", getActype())
            .append("choosedParamsstr", getChoosedParamsstr())
            .append("createtime", getCreateTime())
            .append("versionid", getVersionid())
            .toString();
    }
}
