package com.ruoyi.system.service.paymentRequest;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.system.domain.FormFile;
import com.ruoyi.system.domain.paymentRequest.PaymentAuditResults;
import com.ruoyi.system.domain.paymentRequest.PaymentRequest;
import com.ruoyi.system.domain.paymentRequest.PaymentRequestFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 请款单Service接口
 * 
 * @author SKaiL
 * @date 2022-09-21
 */
public interface IPaymentRequestService 
{
    /**
     * 查询请款单
     * 
     * @param id 请款单主键
     * @return 请款单
     */
    public PaymentRequest selectPaymentRequestById(Long id);

    /**
     * 查询请款单列表
     * 
     * @param paymentRequest 请款单
     * @return 请款单集合
     */
    public List<PaymentRequest> selectPaymentRequestList(PaymentRequest paymentRequest);


    /**
     * 查询我的草稿(待提交和撤回)
     * @param paymentRequest
     * @return
     */
    public List<PaymentRequest> selectPaymentRequestListDraft(PaymentRequest paymentRequest);

    /**
     * 新增请款单
     * 
     * @param paymentRequest 请款单
     * @return 结果
     */
    public AjaxResult insertPaymentRequest(PaymentRequest paymentRequest,SysUser user);

    /**
     * 修改请款单
     * 
     * @param paymentRequest 请款单
     * @return 结果
     */
    public AjaxResult updatePaymentRequest(PaymentRequest paymentRequest);

    /**
     * 批量删除请款单
     * 
     * @param ids 需要删除的请款单主键集合
     * @return 结果
     */
    public int deletePaymentRequestByIds(String ids);

    /**
     * 删除请款单信息
     * 
     * @param id 请款单主键
     * @return 结果
     */
    public int deletePaymentRequestById(Long id);


    /**
     * 用户提交流程
     */
    public AjaxResult submitProcess(Long requisitionId, SysUser sysUser);

    /**
     * 通用审核提交
     * @param paymentAuditResults 审核结果信息
     * @param sysUser 操作人信息
     * @return
     */
    public int auditSubmit(PaymentAuditResults paymentAuditResults, SysUser sysUser, PaymentRequest paymentRequest);


    /**
     * 请款撤回
     * @param id 需要撤回的id
     * @return
     */
    public AjaxResult WithdrawalOfInitiation(Long id);

    /**
     * 上传关闭文件
     * @param file 截图凭证
     * @param paymentRequestIds id集合
     * @param sysUser 当前用户
     * @return
     */
    public AjaxResult closePaymentRequestFile(MultipartFile file, String paymentRequestIds,Integer type, SysUser sysUser);


    /**
     * 批量审核
     * @param auditResults 审核信息
     * @param user 操作人
     * @return
     */
    public String paymentRequestBatchReview(PaymentAuditResults auditResults, SysUser user);

    /**
     * 新增时的附件
     */
    public AjaxResult insertPaymentRequestFile(MultipartFile[] files,MultipartFile summaryFile,Long id);

    /**
     * 查询文件列表
     * @param parentId 父id
     * @param type 类型
     * @return 文件列表
     */
    public List<FormFile> selectFormFile(Long parentId, Integer type);



}
