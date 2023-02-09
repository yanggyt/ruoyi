package com.ruoyi.common.enums;

/**
 * 请款单状态信息
 * @author SKaiL
 * @date 2022/9/27
 * @version 1.0
 */
public enum PaymentRequestStatus
{
    /** ------删除标志------ */
    SHOW_FLAG(0, "正常"),
    HIDE_FLAG(1, "删除"),

    /** ------单子状态------ */
    /** 用户保存未提交 */
    SAVE(1,"用户保存未提交"),

    /** 待部门主管审批 */
    SUPERVISOR(2, "待部门主管审批"),

    /** 待部门经理审批 */
    MANAGER(3,"待部门经理审批"),

    /** 待部门总监审批 */
    DIRECTOR(4,"待部门总监审批"),

    /** 待部门执行总监审批 */
    EXECUTIVE_DIRECTOR(6,"待部门执行总监审批"),

    /** 待总经理审批 */
    GENERAL_MANAGER(8,"待总经理审批"),

    /** 财务初核 */
    FINANCIAL_AUDIT(5,"财务初核"),

    /** 总账确认 */
    FINANCIAL_LEDGER_AUDIT(7,"总账确认"),

    /** 前加签状态 */
    BEFORE_COUNTERSIGN_STATUS(18,"前加签状态"),

    /** 后加签状态 */
    AFTER_COUNTERSIGN_STATUS(23,"后加签状态"),

    /** 平行加签状态 */
    PARALLEL_COUNTERSIGN_STATUS(24,"平行加签状态"),

    /** 否决 */
    VETO(19,"否决"),

    /** 撤回 */
    WITHDRAWN(20,"撤回"),

    /** 完成 */
    COMPLETE(21,"完成"),

    /** 保留 */
    RESERVATION(22,"保留");


    private final Integer code;
    private final String info;

    PaymentRequestStatus(Integer code, String info)
    {
        this.code = code;
        this.info = info;
    }

    public Integer getCode()
    {
        return code;
    }

    public String getInfo()
    {
        return info;
    }
}
