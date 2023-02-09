package com.ruoyi.common.enums;


/**
 * 请购单状态信息
 *
 * @author SKaiL
 * @date 2022/9/27
 */
public enum RequisitionStatus {

    /** 用户保存未提交 */
    SAVE(1,"用户保存未提交"),

    /** 待部门主管审批 */
    SUPERVISOR(2, "待部门主管审批"),

    /** 待部门经理审批 */
    MANAGER(23,"待部门经理审批"),

    /** 待部门总监审批 */
    DIRECTOR(24,"待部门总监审批"),

    /** 待部门执行总监审批 */
    EXECUTIVE_DIRECTOR(25,"待部门执行总监审批"),

    /** 待资产管理员审批 */
    ASSET_MANAGER(13,"待资产管理员审批"),

    /** 待采购代表审批 */
    DB_OFFER(20,"采购代表填写报价"),

    /** 待新员工入职审批 */
    NEW_STAFF(16,"新员工入职的叶丽丽签核"),

    /** 待hr部门老大审批 */
    HR_PRINCIPAL(19,"hr部门老大审核"),

    /** 待预算管理员审批 */
    FINANCE_ASSETS(14,"预算管理员"),

    /** 待财务预算经理审批 */
    BUDGET_MANAGER(27,"财务预算经理"),

    /** 待总账审批 */
    ASSISTANT_ACCOUNTING(3, "总账"),

    /** 待财务经理审批 */
    ACCOUNTING(11, "财务经理审核"),

    /** 待总经理审批 */
    GENERAL_MANAGER(4,"待总经理审批"),

    /** 待前加签审批 */
    SIGN_BEFORE(18,"前加签状态"),

    /** 待后加签审批 */
    SIGN_AFTER(26,"后加签状态"),

    /** 完成 */
    FINISH(5, "完成"),

    /** 否决 */
    VETO(6,"否决"),

    /** 撤回 */
    WITHDRAWN(7,"撤回"),

    /** 保留 */
    RETAIN(22,"保留"),

    /** 主管加签状态 */
    SUPERVISOR_ENDORSEMENT(8,"主管加签状态"),

    /** 财务经理加签状态 */
    ACCOUNTING_ENDORSEMENT(9,"财务经理加签状态"),

    /** 总账加签状态 */
    ASSISTANT_ACCOUNTING_ENDORSEMENT(12,"总账加签状态"),

    /** 裁决加签状态 */
    RULING_ENDORSEMENT(10,"裁决加签状态"),

    /**  */
    NEW_STAFF_ENDORSEMENT(17,"新员工入职的叶丽丽签核加签"),

    /** 预算管理员加签 */
    FINANCE_ASSETS_ENDORSEMENT(15,"预算管理员加签"),

    /**  */
    DB_OFFER_ENDORSEMENT(21,"采购代表填写报价加签");

    private final Integer code;
    private final String info;

    RequisitionStatus(Integer code, String info) {
        this.code = code;
        this.info = info;
    }

    public Integer getCode() {
        return code;
    }

    public String getInfo() {
        return info;
    }
}
