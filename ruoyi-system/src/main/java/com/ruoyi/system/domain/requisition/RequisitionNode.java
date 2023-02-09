package com.ruoyi.system.domain.requisition;

import java.util.Objects;

/**
 * @author
 */

/**
 *  相关节点
 */
public enum RequisitionNode
{
    //提交
    SUBMIT(1,"提交"),
    //主管审批
    SUPERVISOR(2, "主管审批"),
    //部门经理
    MANAGER(23,"待部门经理审批"),
    //总监
    DIRECTOR(24,"待部门总监审批"),
    //执行总监
    EXECUTIVE_DIRECTOR(25,"待部门执行总监审批"),
    //资产管理员的审批
    ASSET_MANAGER(13,"资产管理员"),
    //采购代表
    DB_OFFER(22,"采购代表"),
    //新员工入职的叶丽丽签核
    NEW_STAFF(16,"新员工入职的叶丽丽签核"),
    //hr部门老大审核
    HR_PRINCIPAL(21,"hr部门老大审核"),
    //财务预算管理员
    FINANCE_ASSETS(14,"预算管理员"),
    //财务预算经理
    BUDGET_MANAGER(27,"财务预算经理"),
    // 部门主管等提交完成后副经理审核
    ASSISTANT_ACCOUNTING(3, "总账"),
    // 副经理审核提交完成后经理审核
    ACCOUNTING(11, "财务经理审核"),
    //总经理
    GENERAL_MANAGER(4,"待总经理审批"),
    //前加签
    BEFORE_COUNTERSIGN(19,"前加签"),
    //后加签
    AFTER_COUNTERSIGN(20,"后加签");
    
    
    private Integer value;
    
    private String desc;
    
    RequisitionNode(Integer value, String desc) {
        this.value = value;
        this.desc = desc;
    }

    public Integer value() {
        return this.value;
    }

    public String getDesc() {
        return this.desc;
    }

    public static RequisitionNode from(int value) {
        for (RequisitionNode range : RequisitionNode.values()) {
            if (Objects.equals(range.value, value)) {
                return range;
            }
        }
        return null;
    }
}
