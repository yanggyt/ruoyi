package com.ruoyi.system.domain.paymentRequest;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 请款单对象 sys_payment_request
 * 
 * @author SKaiL
 * @date 2022-09-21
 */
@Data
public class PaymentRequest extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 单据编号 */
    @Excel(name = "单据编号")
    private String paymentRequestNo;

    /** 1：用户保存未提交 2：待部门主管审批 3：主管加签状态 5：财务部门财务审核 6：财务部门审核加签 7：财务总账确认 8：财务总账审核加签 9：游总核决 10：核决加签 11：出纳确认 12：出纳加签 13：财务经理审核 14：财务经理加签 15：财务总监审核 16：财务总监加签 18：前加签状态 19：否决 20：撤回 21：完成 */
    @Excel(name = "1：用户保存未提交 2：待部门主管审批 3：主管加签状态 5：财务部门财务审核 6：财务部门审核加签 7：财务总账确认 8：财务总账审核加签 9：游总核决 10：核决加签 11：出纳确认 12：出纳加签 13：财务经理审核 14：财务经理加签 15：财务总监审核 16：财务总监加签 18：前加签状态 19：否决 20：撤回 21：完成")
    private Integer status;

    /** 公司 */
    @Excel(name = "公司")
    private Integer company;

    /** 项目代码 */
    @Excel(name = "项目代码")
    private String projectCode;

    /** 请款人 */
    @Excel(name = "请款人")
    private String applicant;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String employeeNo;

    /** 使用部门 */
    @Excel(name = "使用部门")
    private String dept;

    /** 部门代码 */
    @Excel(name = "部门代码")
    private String deptCode;

    /** 办公室 */
    @Excel(name = "办公室")
    private String offices;

    /** 申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applicationDate;

    /** 付款日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "付款日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date paymentDate;

    /** 是否代请款(0:否 1:是) */
    @Excel(name = "是否代请款(0:否 1:是)")
    private Integer insteadPayment;

    /** 实际使用人编号 */
    @Excel(name = "实际使用人编号")
    private String actualUserNo;

    /** 实际使用人 */
    @Excel(name = "实际使用人")
    private String actualUser;

    /** 实际使用部门 */
    @Excel(name = "实际使用部门")
    private String actualDept;

    /** 实际使用部门代码 */
    @Excel(name = "实际使用部门代码")
    private String actualDeptCode;

    /** 请款类别(A:生产性支出-汇总请款 B:非生产性支出-持续性 C:非生产性支出-非持续性 E:交际费 X:个人报销 Y:生产性支出 */
    @Excel(name = "请款类别(A:生产性支出-汇总请款 B:非生产性支出-持续性 C:非生产性支出-非持续性 E:交际费 X:个人报销 Y:生产性支出")
    private String paymentType;

    /** 生产性支出汇总文件地址 */
    @Excel(name = "生产性支出汇总文件地址")
    private String summaryFile;

    /** 生产性支出汇总文件名 */
    @Excel(name = "生产性支出汇总文件名")
    private String summaryFileName;

    /** 是否预付 */
    @Excel(name = "是否预付")
    private Integer prepaid;

    /** 是否交际费(0:否 1:是) */
    @Excel(name = "是否交际费(0:否 1:是)")
    private Long entertainmentExpense;

    /** 合计应付金额 */
    @Excel(name = "合计应付金额")
    private String paymentAmount;

    /** 付款方式(1:转账汇款 2:其他) */
    @Excel(name = "付款方式(1:转账汇款 2:其他)")
    private Long paymentMethod;

    /** 付款方式为其他时需要填写的备注 */
    @Excel(name = "付款方式为其他时需要填写的备注")
    private String others;

    /** 收款人 */
    @Excel(name = "收款人")
    private String payee;

    /** 开户行 */
    @Excel(name = "开户行")
    private String bankName;

    /** 账号 */
    @Excel(name = "账号")
    private String accountNumber;

    /** 收款人地址 */
    @Excel(name = "收款人地址")
    private String address;

    /** 开户行代码 */
    @Excel(name = "开户行代码")
    private String swiftCode;

    /** 开户行地址 */
    @Excel(name = "开户行地址")
    private String bankAddress;

    /** 财务主管 */
    @Excel(name = "财务主管")
    private String acctSupervisor;

    /** 出纳 */
    @Excel(name = "出纳")
    private String cashier;

    /** 核准 */
    @Excel(name = "核准")
    private String approvedBy;

    /** 复核 */
    @Excel(name = "复核")
    private String recheckedBy;

    /** 初核 */
    @Excel(name = "初核")
    private String checkedBy;

    /** 创建人code */
    @Excel(name = "创建人code")
    private String createByCode;

    /** 将要处理者的code，如果是发起人提交，里面将保存自己 */
    @Excel(name = "将要处理者的code，如果是发起人提交，里面将保存自己")
    private String sendToCode;

    /** 已经处理完的code，存储处理历史相关人员的code */
    @Excel(name = "已经处理完的code，存储处理历史相关人员的code")
    private String handlesCode;

    /** 提交时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 用来前加签保存上一个的send code */
    @Excel(name = "用来前加签保存上一个的send code")
    private String oldSendToCode;

    /** 用来前加签保存上一个状态 */
    @Excel(name = "用来前加签保存上一个状态")
    private Long oldStatus;

    /** 上传文件导致关闭文件地址 */
    @Excel(name = "上传文件导致关闭文件地址")
    private String closeFile;

    /** 上传文件导致关闭文件名 */
    @Excel(name = "上传文件导致关闭文件名")
    private String closeFileName;

    /** 删除标志（0代表删除 1代表存在） */
    private Integer delFlag;

    /** 用来前加签保存上一个的send code */
    @Excel(name = "用来前加签保存上一个的send code")
    private String beforeSendToCode;

    /** 用来前加签保存上一个状态 */
    @Excel(name = "用来前加签保存上一个状态")
    private Integer beforeStatus;

    /** 用来后加签保存下一个的send code */
    @Excel(name = "用来后加签保存下一个的send code")
    private String afterSendToCode;

    /** 用来后加签保存上一个状态 */
    @Excel(name = "用来后加签保存上一个状态")
    private Integer afterStatus;

    /** 用来保留保存下一个的send code */
    @Excel(name = "用来保留保存下一个的send code")
    private String reserveSendToCode;

    /** 用来保留保存上一个状态 */
    @Excel(name = "用来保留保存上一个状态")
    private Integer reserveStatus;

    /** 将要处理者的code，如果是发起人提交，里面将保存自己 */
    @Excel(name = "将要处理者的name，如果是发起人提交，里面将保存自己")
    private String sendToName;

    /** 请款单dt1信息 */
    private List<PaymentRequestDt1> paymentRequestDt1List;

    private  Integer[] statusList;

    /** 进度值(用于前端展示进度) */
    private String progressRate;

}
