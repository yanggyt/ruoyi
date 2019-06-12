package com.ruoyi.template.domain;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 交换机端口类型(不用代码生成工具)表 tmpl_switch_port
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

    public void setSwitchPortId(Integer switchPortId) {
        this.switchPortId = switchPortId;
    }

    public Integer getSwitchPortId() {
        return switchPortId;
    }

    public void setSwitchId(Integer switchId) {
        this.switchId = switchId;
    }

    public Integer getSwitchId() {
        return switchId;
    }

    public void setSwitchPortType(Integer switchPortType) {
        this.switchPortType = switchPortType;
    }

    public Integer getSwitchPortType() {
        return switchPortType;
    }

    public void setSwitchPortNum(Integer switchPortNum) {
        this.switchPortNum = switchPortNum;
    }

    public Integer getSwitchPortNum() {
        return switchPortNum;
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
