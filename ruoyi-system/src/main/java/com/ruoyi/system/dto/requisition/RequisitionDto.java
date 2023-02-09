package com.ruoyi.system.dto.requisition;

import com.ruoyi.system.domain.ProcessFlow;
import com.ruoyi.system.domain.requisition.Requisition;
import com.ruoyi.system.domain.requisition.RequisitionDt1;
import com.ruoyi.system.domain.requisition.RequisitionDt2;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 请购单Dto
 *
 * @author SKaiL
 * @date 2022/11/1
 */
@Data
public class RequisitionDto implements Serializable
{

    /** 请购单主信息 */
    private Requisition requisition;

    /** 审批记录 */
    private List<ProcessFlow> processFlows;

    /** 购买产品说明 */
    private List<RequisitionDt1> requisitionDt1s;

    /** 厂商比议价说明 */
    private List<RequisitionDt2> requisitionDt2s;
}
