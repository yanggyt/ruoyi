package com.ruoyi.template.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.List;

/**
 * 交换机模板表 tmpl_switch
 *
 * @author TP
 * @date 2019-06-12
 */
public class TmplSwitch extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 交换机模板编号
     */
    private Integer switchId;
    /**
     * 交换机品牌
     */
    private String switchBrand;
    /**
     * 交换机型号
     */
    private String switchType;
    /**
     * 交换机电源数量
     */
    private Integer powerNum;
    /**
     * 交换机端口类型
     */
    private List<TmplSwitchPort> switchPorts;

    public Integer getSwitchId() {
        return switchId;
    }

    public void setSwitchId(Integer switchId) {
        this.switchId = switchId;
    }

    public String getSwitchBrand() {
        return switchBrand;
    }

    public void setSwitchBrand(String switchBrand) {
        this.switchBrand = switchBrand;
    }

    public String getSwitchType() {
        return switchType;
    }

    public void setSwitchType(String switchType) {
        this.switchType = switchType;
    }

    public Integer getPowerNum() {
        return powerNum;
    }

    public void setPowerNum(Integer powerNum) {
        this.powerNum = powerNum;
    }

    public List<TmplSwitchPort> getSwitchPorts() {
        return switchPorts;
    }

    public void setSwitchPorts(List<TmplSwitchPort> switchPorts) {
        this.switchPorts = switchPorts;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("switchId", getSwitchId())
                .append("switchBrand", getSwitchBrand())
                .append("switchType", getSwitchType())
                .append("powerNum", getPowerNum())
                .append("switchPorts", getSwitchPorts())
                .toString();
    }
}


