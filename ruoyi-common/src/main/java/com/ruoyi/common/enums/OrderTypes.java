package com.ruoyi.common.enums;

/**
 * 功能类型
 *
 * @author SKaiL
 * @version 1.0
 * @date 2022/9/27
 */
public enum OrderTypes
{
    // 用户账号权限申请单
    IT_USER_AUTHORIZATION(1, "入职申请表"),

    //权限更改
    PERMISSION_CHANGE(2, "权限更改表"),

    //资产设备申购表
    ASSET_PURCHASING(3, "资产设备申购表"),

    //请购单
    REQUISITION(4, "请购单"),

    //系统功能需求单
    FUNCTION(5, "系统功能需求单"),

    //电子签呈单
    PETITION(6, "电子签呈"),

    //原材料异常申请单
    MRPFROM(7, "原材料异常申请单"),

    //请款单
    PAYMENT_REQUEST(8, "请款单"),

    //出差报销单
    TRAVEL_EXPENSE_REIMBURSEMENT(9, "出差报销单");

    private final Integer code;
    private final String info;

    OrderTypes(Integer code, String info) {
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
