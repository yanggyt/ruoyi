package com.ruoyi.template.domain;

import com.ruoyi.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 交换机端口类型表 tmpl_switch_port
 *
 * @author TP
 * @date 2019-06-12
 */
public class TmplSwitchPort extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     * 交换机端口编号
     */
    private Integer switchPortId;
    /**
     * 交换机模板编号
     */
    private Integer switchId;
    /**
     * 交换机端口类型
     */
    private Integer switchPortType;
    /**
     * 交换机端口数量
     */
    private Integer switchPortNum;

    public Integer getSwitchPortId() {
        return switchPortId;
    }

    public void setSwitchPortId(Integer switchPortId) {
        this.switchPortId = switchPortId;
    }

    public Integer getSwitchId() {
        return switchId;
    }

    public void setSwitchId(Integer switchId) {
        this.switchId = switchId;
    }

    public Integer getSwitchPortType() {
        return switchPortType;
    }

    public void setSwitchPortType(Integer switchPortType) {
        this.switchPortType = switchPortType;
    }

    public Integer getSwitchPortNum() {
        return switchPortNum;
    }

    public void setSwitchPortNum(Integer switchPortNum) {
        this.switchPortNum = switchPortNum;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.MULTI_LINE_STYLE)
                .append("switchPortId", getSwitchPortId())
                .append("switchId", getSwitchId())
                .append("switchPortType", getSwitchPortType())
                .append("switchPortNum", getSwitchPortNum())
                .toString();
    }
}
