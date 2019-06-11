package com.ruoyi.template.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 交换机模板表 tmpl_switch
 * 
 * @author TP
 * @date 2019-06-12
 */
public class Switch extends BaseEntity
{
	private static final long serialVersionUID = 1L;
	
	/** 交换机模板编号 */
	private Integer switchId;
	/** 交换机品牌 */
	private String switchBrand;
	/** 交换机型号 */
	private String switchType;
	/** 交换机电源数量 */
	private Integer powerNum;

	public void setSwitchId(Integer switchId) 
	{
		this.switchId = switchId;
	}

	public Integer getSwitchId() 
	{
		return switchId;
	}
	public void setSwitchBrand(String switchBrand) 
	{
		this.switchBrand = switchBrand;
	}

	public String getSwitchBrand() 
	{
		return switchBrand;
	}
	public void setSwitchType(String switchType) 
	{
		this.switchType = switchType;
	}

	public String getSwitchType() 
	{
		return switchType;
	}
	public void setPowerNum(Integer powerNum) 
	{
		this.powerNum = powerNum;
	}

	public Integer getPowerNum() 
	{
		return powerNum;
	}

    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("switchId", getSwitchId())
            .append("switchBrand", getSwitchBrand())
            .append("switchType", getSwitchType())
            .append("powerNum", getPowerNum())
            .toString();
    }
}
