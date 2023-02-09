package com.ruoyi.system.domain.petition;

import java.math.BigDecimal;
import java.util.List;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;

/**
 * 电子签呈对象 sys_petition
 * 
 * @author SKaiL
 * @date 2022-09-26
 */
@Data
public class Petition extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** id */
    private Long id;

    /** 类型 */
    @Excel(name = "类型")
    private Integer typeType;

    /** 表单编号 */
    @Excel(name = "表单编号")
    private String comNo;

    /** 主题 */
    @Excel(name = "主题")
    private String topicObj;

    /** 说明文本 */
    @Excel(name = "说明文本")
    private String explanation;

    /** 印章类型(公章 1 ,法人章2 ,财务章 3 ,合同章 4 ,法人章(经济部 小章)5 ,法人章(经济部 小章) 6 ,印鉴大章 7 印鉴小章8 ) */
    @Excel(name = "印章类型(公章 1 ,法人章2 ,财务章 3 ,合同章 4 ,法人章(经济部 小章)5 ,法人章(经济部 小章) 6 ,印鉴大章 7 印鉴小章8 )")
    private String sealType;

    /** 币别 */
    @Excel(name = "币别")
    private String curr;

    /** 金额 */
    @Excel(name = "金额")
    private BigDecimal moneyM;

    /** 文件id */
    @Excel(name = "文件id")
    private String fileId;

    /** 使用印监 */
    @Excel(name = "使用印监")
    private String seal;

    /** 用印使用说明 */
    @Excel(name = "用印使用说明")
    private String sealExplain;

    /** 用印借出日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "用印借出日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date sealDate;

    /** 补充内容 */
    @Excel(name = "补充内容")
    private String moreExplain;

    /** 公司代码 */
    @Excel(name = "公司代码")
    private String company;

    /** 部门 */
    @Excel(name = "部门")
    private String dept;

    /** 员工编号 */
    @Excel(name = "员工编号")
    private String sid;

    /** 申请人名字 */
    @Excel(name = "申请人名字")
    private String applyname;

    /** 申请日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "申请日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date applydate;

    /** 部门主管 */
    @Excel(name = "部门主管")
    private String deptmanager;

    /** 部门主管id */
    @Excel(name = "部门主管id")
    private String deptmanagerSid;

    /** 部门主管审核意见( 1 同意 2 不同意) */
    @Excel(name = "部门主管审核意见( 1 同意 2 不同意)")
    private Integer deptmanagerSta;

    /** 主管审核备注 */
    @Excel(name = "主管审核备注")
    private String deptmanagerIdea;

    /** 审核日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date deptmanageDate;

    /** HR选择 */
    @Excel(name = "HR选择")
    private String hr;

    /** hr意见(1 同意,2 不同意) */
    @Excel(name = "hr意见(1 同意,2 不同意)")
    private Integer hrSta;

    /** hr审核备注 */
    @Excel(name = "hr审核备注")
    private String hrIdea;

    /** 审核日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date hrStaDate;

    /** 法务 */
    @Excel(name = "法务")
    private String law;

    /** 法务意见 */
    @Excel(name = "法务意见")
    private Integer lawSta;

    /** 法务审核备注 */
    @Excel(name = "法务审核备注")
    private String lawIdea;

    /** 法务审核时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "法务审核时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date lawDate;

    /** 会计主管 */
    @Excel(name = "会计主管")
    private String acOfficer;

    /** 会计主管id */
    @Excel(name = "会计主管id")
    private String acOfficerSid;

    /** 审核日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "审核日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date acOfficerDate;

    /** 会计主管审核备注 */
    @Excel(name = "会计主管审核备注")
    private String acOfficerIdea;

    /** 会计主管审核意见 */
    @Excel(name = "会计主管审核意见")
    private Integer acOfficerSta;

    /** 财务总账 */
    @Excel(name = "财务总账")
    private String fcManager;

    /** 财务总账意见 */
    @Excel(name = "财务总账意见")
    private Integer fcManagerSta;

    /** 财务总账审核备注 */
    @Excel(name = "财务总账审核备注")
    private String fcManagerIdea;

    /** 财务总账审核日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "财务总账审核日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date fcManagerDate;

    /** 财务长 */
    @Excel(name = "财务长")
    private String cfcoManager;

    /** 财务长审核意见 */
    @Excel(name = "财务长审核意见")
    private Integer cfcoManagerSta;

    /** 财务长审核备注 */
    @Excel(name = "财务长审核备注")
    private String cfcoManagerIdea;

    /** 财务长审核日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "财务长审核日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date cfcoManagerDate;

    /** 总经理 */
    @Excel(name = "总经理")
    private String gm;

    /** 总经理id */
    @Excel(name = "总经理id")
    private String gmSid;

    /** 总经理审核意见 */
    @Excel(name = "总经理审核意见")
    private Integer gmSta;

    /** 总经理审核备注 */
    @Excel(name = "总经理审核备注")
    private String gmIdea;

    /** 总经理审核日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "总经理审核日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date gmDate;

    /** 创建人 */
    @Excel(name = "创建人")
    private String creatBy;

    /** 创建日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "创建日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date creatDate;

    /** 更新日期 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "更新日期", width = 30, dateFormat = "yyyy-MM-dd")
    private Date updateDate;

    /** 状态(.....) */
    @Excel(name = "状态(.....)")
    private Integer status;

    /** 发送接收者 */
    @Excel(name = "发送接收者")
    private String fromSendto;

    /** 已经处理完的code，存储处理历史相关人员的code */
    @Excel(name = "已经处理完的code，存储处理历史相关人员的code")
    private String handlesCode;

    /** 表单状态(10  未完成 8 完成) */
    @Excel(name = "表单状态(10  未完成 8 完成)")
    private Integer formSta;

    /** 加签状态( 1 加签  0 非加签) */
    @Excel(name = "加签状态( 1 加签  0 非加签)")
    private Integer oldSta;

    /** 1 是前加签 2 是后加签 */
    @Excel(name = "1 是前加签 2 是后加签")
    private Integer addauditSta;

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

    /** 0 隐藏  1  显示 */
    @Excel(name = "0 隐藏  1  显示")
    private Integer flag;

    /** 撤回时间 */
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Excel(name = "撤回时间", width = 30, dateFormat = "yyyy-MM-dd")
    private Date recallDate;

    /** 撤回原因 */
    @Excel(name = "撤回原因")
    private String recallReason;

    /** 上传文件导致关闭文件地址 */
    @Excel(name = "上传文件导致关闭文件地址")
    private String closeFile;

    /** 上传文件导致关闭文件名字 */
    @Excel(name = "上传文件导致关闭文件名字")
    private String closeFileName;

    /** 核准人员信息 */
    private List<PetitionSign> petitionSignList;

}
