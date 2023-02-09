package com.ruoyi.system.domain.requisition;

import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 请购单对象 sys_requisition
 * 
 * @author SKaiL
 * @date 2022-09-26
 */
@Data
public class Requisition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 唯一编号 */
    @Excel(name = "唯一编号")
    private String requisitionNo;

    /** 状态 */
    @Excel(name = "状态")
    private Integer status;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String employeeNo;

    /** 新员工入职hr签字 */
    @Excel(name = "新员工入职hr签字")
    private String hrSign;

    /** 是否新员工 */
    @Excel(name = "是否新员工")
    private Integer newStaff;

    /** 产品线 */
    @Excel(name = "产品线")
    private String productLine;

    /** 产品线详细 */
    @Excel(name = "产品线详细")
    private String productLine1;

    /** 产品线其他 */
    @Excel(name = "产品线其他")
    private String productLineOther;

    /** 部门编号 */
    @Excel(name = "部门编号")
    private String userDepartment;

    /** 公司 */
    @Excel(name = "公司")
    private Integer company;

    /** 申购单号 */
    @Excel(name = "申购单号")
    private String purchaseCode;

    /** 地区部门编号 */
    @Excel(name = "地区部门编号")
    private String departmentCode;

    /** 地区 */
    @Excel(name = "地区")
    private String office;

    /** 员工姓名 */
    @Excel(name = "员工姓名")
    private String userName;

    /** 需求日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "需求日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date userDate;

    /** 申请类别 */
    @Excel(name = "申请类别")
    private Integer typeOfApplication;

    /** 资产管理人员，可填可不填，如果填1，则是桌面，如果填2则是测试，需要转到相应的人员进行审核 */
    @Excel(name = "资产管理人员，可填可不填，如果填1，则是桌面，如果填2则是测试，需要转到相应的人员进行审核")
    private Integer assetManagement;

    /** 所属类别 */
    @Excel(name = "所属类别")
    private Integer category;

    /** 请购原因 */
    @Excel(name = "请购原因")
    private String reasonForPurchase;

    /** 是否须比议价 */
    @Excel(name = "是否须比议价")
    private Integer priceComparison;

    /** 特殊原因说明 */
    @Excel(name = "特殊原因说明")
    private String specialReason;

    /** dt1合计金额 */
    @Excel(name = "dt1合计金额")
    private String totalAmount;

    /** 提交时间，用来保存提交流程的时刻 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "提交时间，用来保存提交流程的时刻", width = 30, dateFormat = "yyyy-MM-dd")
    private Date submitTime;

    /** 创建人code */
    @Excel(name = "创建人code")
    private String createByCode;

    /** 将要处理者的code，如果是发起人提交，里面将保存自己 */
    @Excel(name = "将要处理者的code，如果是发起人提交，里面将保存自己")
    private String sendToCode;

    /** 将要处理者的名字，如果是发起人提交，里面将保存自己 */
    @Excel(name = "将要处理者的名字，如果是发起人提交，里面将保存自己")
    private String sendToName;

    /** 已经处理完的code，存储处理历史相关人员的code */
    @Excel(name = "已经处理完的code，存储处理历史相关人员的code")
    private String handlesCode;

    /** 上传文件导致关闭文件地址 */
    @Excel(name = "上传文件导致关闭文件地址")
    private String closeFile;

    /** 上传文件导致关闭文件名 */
    @Excel(name = "上传文件导致关闭文件名")
    private String closeFileName;

    /** 删除标志（0代表删除 1代表存在） */
    private Integer delFlag;

    /** 请购单产品明细dt1信息 */
    private List<RequisitionDt1> requisitionDt1List;

    /** 请购单供应商明显dt2信息 */
    private List<RequisitionDt2> requisitionDt2List;

    /** 进度值(用于前端展示进度) */
    private String progressRate;

}
