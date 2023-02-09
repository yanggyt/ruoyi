package com.ruoyi.common.enums;

import java.util.Objects;

/**
 * 请款单流转前节点
 * @author
 * @date 2020/08/17 15:14
 * @version 1.0
 */
public enum PaymentRequestNode {
    //提交
    SUBMIT(1,"提交"),
    //主管审批
    SUPERVISOR(2, "主管"),
    //经理
    MANAGER(23, "经理"),
    //总监
    DIRECTOR(24, "总监"),
    //执行总监
    EXECUTIVE_DIRECTOR(25, "执行总监"),
    //总经理
    GENERAL_MANAGER(26, "总经理"),
    //会计初核
    FINANCIAL_AUDIT(3,"会计"),
    //总账确认
    FINANCIAL_LEDGER_AUDIT(4,"总账"),
    //前加签
    BEFORE_COUNTERSIGN(18,"前加签"),
    //后加签
    AFTER_COUNTERSIGN(19,"后加签"),
    //平行加签
    PARALLEL_COUNTERSIGN(21,"平行加签"),
    //保留
    RESERVATION(22,"保留");

    private Integer value;

    private String desc;

    PaymentRequestNode(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static PaymentRequestNode from(int value) {
        for (PaymentRequestNode range : PaymentRequestNode.values()) {
            if (Objects.equals(range.value, value)) {
                return range;
            }
        }
        return null;
    }
}
