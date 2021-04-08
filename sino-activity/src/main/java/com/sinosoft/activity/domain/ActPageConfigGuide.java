package com.sinosoft.activity.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 活动展示内容配置对象 act_page_config_guide
 * 
 * @author dy
 * @date 2021-04-08
 */
public class ActPageConfigGuide extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Integer id;

    /** 活动编码 */
    @Excel(name = "活动编码")
    private String actCode;

    /** 活动标题 */
    @Excel(name = "活动标题")
    private String actTitle;

    /** 活动描述 */
    @Excel(name = "活动描述")
    private String actDesc;

    /** 背景图片 */
    @Excel(name = "背景图片")
    private String bgImg;

    /** 按钮文本1 */
    @Excel(name = "按钮文本1")
    private String btnText;

    /** 按钮链接1 */
    @Excel(name = "按钮链接1")
    private String btnLink;

    /** 按钮链接2 */
    @Excel(name = "按钮链接2")
    private String btnLink2;

    /** 按钮文本2 */
    @Excel(name = "按钮文本2")
    private String btnText2;

    public void setId(Integer id) 
    {
        this.id = id;
    }

    public Integer getId() 
    {
        return id;
    }
    public void setActCode(String actCode) 
    {
        this.actCode = actCode;
    }

    public String getActCode() 
    {
        return actCode;
    }
    public void setActTitle(String actTitle) 
    {
        this.actTitle = actTitle;
    }

    public String getActTitle() 
    {
        return actTitle;
    }
    public void setActDesc(String actDesc) 
    {
        this.actDesc = actDesc;
    }

    public String getActDesc() 
    {
        return actDesc;
    }
    public void setBgImg(String bgImg) 
    {
        this.bgImg = bgImg;
    }

    public String getBgImg() 
    {
        return bgImg;
    }
    public void setBtnText(String btnText) 
    {
        this.btnText = btnText;
    }

    public String getBtnText() 
    {
        return btnText;
    }
    public void setBtnLink(String btnLink) 
    {
        this.btnLink = btnLink;
    }

    public String getBtnLink() 
    {
        return btnLink;
    }
    public void setBtnLink2(String btnLink2) 
    {
        this.btnLink2 = btnLink2;
    }

    public String getBtnLink2() 
    {
        return btnLink2;
    }
    public void setBtnText2(String btnText2) 
    {
        this.btnText2 = btnText2;
    }

    public String getBtnText2() 
    {
        return btnText2;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("id", getId())
            .append("actCode", getActCode())
            .append("actTitle", getActTitle())
            .append("actDesc", getActDesc())
            .append("bgImg", getBgImg())
            .append("btnText", getBtnText())
            .append("btnLink", getBtnLink())
            .append("btnLink2", getBtnLink2())
            .append("btnText2", getBtnText2())
            .append("createTime", getCreateTime())
            .append("updateTime", getUpdateTime())
            .toString();
    }
}
