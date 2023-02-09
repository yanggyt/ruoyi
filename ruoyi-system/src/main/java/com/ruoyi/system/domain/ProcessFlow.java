package com.ruoyi.system.domain;

import com.ruoyi.common.annotation.Excel;
import com.ruoyi.common.core.domain.BaseEntity;
import lombok.Data;

/**
 * 流程中间对象 sys_process_flow
 *
 * @author SKaiL
 * @date 2022-09-26
 */
@Data
public class ProcessFlow extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /**  */
    private Long id;

    /** 关联流程id */
    @Excel(name = "关联流程id")
    private Long orderId;

    /** 创建人id */
    @Excel(name = "创建人id")
    private Long createById;

    /** 创建人UserCode */
    @Excel(name = "创建人UserCode")
    private String createByCode;

    /** 发送到id */
    @Excel(name = "发送到id")
    private String sendToId;

    /** 流程当前的状态 */
    @Excel(name = "流程当前的状态")
    private Integer status;

    /** 加签人员的名字 */
    @Excel(name = "加签人员的名字")
    private String endorsementName;

    /** 流程类型 */
    @Excel(name = "流程类型")
    private Integer orderType;

    /** 审核结果，不同流程不一样 */
    @Excel(name = "审核结果，不同流程不一样")
    private Integer auditResult;

    /** 流程中间表新增某一个流程当前所处的节点 */
    @Excel(name = "流程中间表新增某一个流程当前所处的节点")
    private Integer orderNodeType;

    /** 审核顺序 */
    @Excel(name = "审核顺序")
    private Integer reviewOrder;

    /** 删除标志(0 隐藏 1 显示) */
    private Integer delFlag;

}
