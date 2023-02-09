package com.ruoyi.system.service.paymentRequest.impl;

import java.util.*;
import java.util.stream.Collectors;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.FormFileConstants;
import com.ruoyi.common.constant.StatusFlagConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.OrderTypes;
import com.ruoyi.common.enums.PaymentRequestStatus;
import com.ruoyi.common.enums.RequisitionStatus;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.system.domain.FormFile;
import com.ruoyi.system.domain.PaymentAccount;
import com.ruoyi.system.domain.ProcessFlow;
import com.ruoyi.system.domain.paymentRequest.PaymentAuditResults;
import com.ruoyi.system.domain.paymentRequest.PaymentRequest;
import com.ruoyi.system.domain.paymentRequest.PaymentRequestDt1;
import com.ruoyi.system.mapper.FormFileMapper;
import com.ruoyi.system.mapper.PaymentAccountMapper;
import com.ruoyi.system.mapper.ProcessFlowMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.paymentRequest.PaymentRequestMapper;
import com.ruoyi.system.service.paymentRequest.IPaymentRequestService;
import com.ruoyi.system.component.paymentRequest.PaymentRequestComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import static com.ruoyi.common.core.domain.AjaxResult.error;
import static com.ruoyi.common.core.domain.AjaxResult.success;
import static com.ruoyi.common.utils.ShiroUtils.getSysUser;

/**
 * 请款单Service业务层处理
 * 
 * @author SKaiL
 * @date 2022-09-21
 */
@Service
public class PaymentRequestServiceImpl implements IPaymentRequestService
{
    private static final Logger logger = LoggerFactory.getLogger(PaymentRequestServiceImpl.class);
    @Autowired
    private PaymentRequestMapper paymentRequestMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private PaymentRequestComponent paymentRequestComponent;

    @Autowired
    private ProcessFlowMapper processFlowMapper;

    @Autowired
    private FormFileMapper formFileMapper;

    @Autowired
    private PaymentAccountMapper paymentAccountMapper;


    /** 删除标志 1 删除 0 存在 */
    private static final Integer FLAG_HIDE = 1;

    private static final Integer FLAG_SHOW = 0;

    /**
     * 查询请款单
     * 
     * @param id 请款单主键
     * @return 请款单
     */
    @Override
    public PaymentRequest selectPaymentRequestById(Long id)
    {
        return paymentRequestMapper.selectPaymentRequestById(id);
    }

    /**
     * 查询请款单列表
     * 
     * @param paymentRequest 请款单
     * @return 请款单
     */
    @Override
    public List<PaymentRequest> selectPaymentRequestList(PaymentRequest paymentRequest)
    {
        return paymentRequestMapper.selectPaymentRequestList(paymentRequest);
    }

    /**
     * 获取cache name
     *
     * @return 缓存名
     */
    private static String getCacheName()
    {
        return Constants.ZT_WGCPRORELEASE_CACHE;
    }

    /**
     * 设置cache key
     *
     * @param configKey 参数键
     * @return 缓存键key
     */
    private static String getCacheKey(String configKey)
    {
        return Constants.ZT_WGCPRORELEASE_KEY + configKey;
    }

    /**
     * 查询我的草稿(待提交和撤回)
     * @param paymentRequest
     * @return
     */
    @Override
    public List<PaymentRequest> selectPaymentRequestListDraft(PaymentRequest paymentRequest){
        Integer[] str = {StatusFlagConstants.HIDE_FLAG, 20};
        paymentRequest.setStatusList(str);
        return paymentRequestMapper.selectPaymentRequestListDraft(paymentRequest);
    }

    /**
     * 新增请款单
     * 
     * @param paymentRequest 请款单
     * @return 结果
     */
    @Transactional
    @Override
    public AjaxResult insertPaymentRequest(PaymentRequest paymentRequest,SysUser sysUser)
    {
        try {
            insertPaymentAccount(paymentRequest);
            paymentRequest.setCreateTime(DateUtils.getNowDate());
            paymentRequest.setCreateBy(sysUser.getUserName());
            paymentRequest.setCreateByCode(sysUser.getUserCode());
            paymentRequest.setSendToCode(sysUser.getUserCode());
            paymentRequest.setStatus(PaymentRequestStatus.SAVE.getCode());
            paymentRequest.setUpdateTime(DateUtils.getNowDate());
            paymentRequest.setDelFlag(FLAG_SHOW);
            paymentRequestMapper.insertPaymentRequest(paymentRequest);
            insertPaymentRequestDt1(paymentRequest);
            return success(paymentRequest.getId());
        } catch (Exception e){
            logger.error("保存请款单出错,错误原因--->", e);
            return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
        }
    }

    /**
     * 修改请款单
     * 
     * @param paymentRequest 请款单
     * @return 结果
     */
    @Transactional
    @Override
    public AjaxResult updatePaymentRequest(PaymentRequest paymentRequest)
    {
        try{
            insertPaymentAccount(paymentRequest);
            paymentRequest.setUpdateTime(DateUtils.getNowDate());
            paymentRequestMapper.deletePaymentRequestDt1ByRequestId(paymentRequest.getId());
            insertPaymentRequestDt1(paymentRequest);
            paymentRequestMapper.updatePaymentRequest(paymentRequest);
            return success(paymentRequest.getId());
        } catch (Exception e){
            logger.error("修改请款单出错,错误原因--->", e);
            return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
        }

    }

    /**
     * 批量删除请款单
     *
     * 
     * @param ids 需要删除的请款单主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePaymentRequestByIds(String ids)
    {
        paymentRequestMapper.deletePaymentRequestDt1ByRequestIds(Convert.toStrArray(ids));
        return paymentRequestMapper.deletePaymentRequestByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除请款单信息
     * 
     * @param id 请款单主键
     * @return 结果
     */
    @Transactional
    @Override
    public int deletePaymentRequestById(Long id)
    {
        paymentRequestMapper.deletePaymentRequestDt1ByRequestId(id);
        return paymentRequestMapper.deletePaymentRequestById(id);
    }

    /**
     * 新增请款单dt1信息
     * 
     * @param paymentRequest 请款单对象
     */
    public void insertPaymentRequestDt1(PaymentRequest paymentRequest)
    {
        List<PaymentRequestDt1> paymentRequestDt1List = paymentRequest.getPaymentRequestDt1List();
        Long id = paymentRequest.getId();
        if (StringUtils.isNotNull(paymentRequestDt1List))
        {
            List<PaymentRequestDt1> list = new ArrayList<PaymentRequestDt1>();
            for (PaymentRequestDt1 paymentRequestDt1 : paymentRequestDt1List)
            {
                paymentRequestDt1.setRequestId(id);
                paymentRequestDt1.setDelFlag(String.valueOf(PaymentRequestStatus.SHOW_FLAG.getCode()));
                paymentRequestDt1.setUpdateTime(DateUtils.getNowDate());
                paymentRequestDt1.setCreateTime(DateUtils.getNowDate());
                list.add(paymentRequestDt1);
            }
            if (list.size() > 0)
            {
                try {
                    paymentRequestMapper.batchPaymentRequestDt1(list);
                }catch (Exception e){
                    logger.error("新增请款单信息出错,错误原因--->", e);
                }

            }
        }
    }

    /**
     * 提交
     * @param paymentRequestId 请款单ID
     * @param user 操作用户
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult submitProcess(Long paymentRequestId, SysUser user)
    {
        try {
            //存放审核人员信息
            List<ProcessFlow> processFlows = new ArrayList<>();
            PaymentRequest paymentRequestUpdate = new PaymentRequest();
            PaymentRequest paymentRequestRes = paymentRequestMapper.selectPaymentRequestById(paymentRequestId);
            if (StringUtils.isNull(paymentRequestRes)) {
                throw new ServiceException("提交失败，查询请款单信息出错！问题编号：" + paymentRequestId + "。请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
            }
            if (!user.isAdmin() && !Objects.equals(paymentRequestRes.getSendToCode(), user.getLoginName())) {
                throw new ServiceException("提交失败！只允许申请人操作。");
            }
            if (user.isAdmin()) {
                user = userMapper.selectUserByUserCode(paymentRequestRes.getEmployeeNo());
            }
            //封装提交记录
            ProcessFlow processFlow = new ProcessFlow();
            processFlow.setOrderId(paymentRequestId);
            processFlow.setStatus(paymentRequestRes.getStatus());
            processFlow.setAuditResult(0);
            processFlow.setCreateTime(DateUtils.getNowDate());
            processFlow.setCreateByCode(user.getUserCode());
            processFlow.setCreateBy(user.getUserName());
            //提交说明
            processFlow.setRemark(paymentRequestRes.getRemark());

            //审核顺序
            int reviewOrder = 1;
            //判断是不是撤回的
            if (Objects.equals(paymentRequestRes.getStatus(), PaymentRequestStatus.WITHDRAWN.getCode())) {
                //查询之前的审核记录
                List<ProcessFlow> processFlowList = processFlowMapper.selectProcessFlowListByOrderId(paymentRequestId, OrderTypes.PAYMENT_REQUEST.getCode());
                if (StringUtils.isNotEmpty(processFlowList)) {
                    reviewOrder += processFlowList.get(processFlowList.size() - 1).getReviewOrder();
                    //撤回状态改成保存未提交的状态
                    processFlow.setStatus(PaymentRequestStatus.SAVE.getCode());
                }
            }
            processFlows.add(processFlow);

            //判断是否代请款
            if (paymentRequestRes.getInsteadPayment() == 1){
                user = userMapper.selectUserByUserCode(paymentRequestRes.getActualUserNo());
            }
            //获取多级主管
            processFlows = paymentRequestComponent.getPaymentAuditors(paymentRequestRes.getPaymentType(), user, paymentRequestComponent.exchangeRateConversion(paymentRequestRes), processFlows);
            //获取会计审核阶段对应的负责人
            processFlows = paymentRequestComponent.judgmentAccountingStaff(paymentRequestRes, processFlows);
            //为空生成新编号
            if (StringUtils.isEmpty(paymentRequestRes.getPaymentRequestNo())) {
                paymentRequestUpdate.setPaymentRequestNo(paymentRequestComponent.primaryKeyGenerationStrategy(paymentRequestRes));
            }
            //审核顺序
            for (ProcessFlow p : processFlows) {
                p.setOrderType(OrderTypes.PAYMENT_REQUEST.getCode());
                p.setOrderId(paymentRequestId);
                p.setReviewOrder(reviewOrder++);
            }
            //待审核人信息
            List<ProcessFlow> collect = processFlows.stream().filter(pf -> StringUtils.isNull(pf.getAuditResult()))
                    .sorted(Comparator.comparing(ProcessFlow::getReviewOrder)).collect(Collectors.toList());
            ProcessFlow to = collect.get(0);
            //更新单子信息
            paymentRequestUpdate.setId(paymentRequestId);
            paymentRequestUpdate.setSendToCode(to.getCreateByCode());
            paymentRequestUpdate.setSendToName(to.getCreateBy());
            paymentRequestUpdate.setStatus(to.getStatus());
            paymentRequestUpdate.setSubmitTime(DateUtils.getNowDate());
            paymentRequestUpdate.setUpdateTime(DateUtils.getNowDate());
            paymentRequestUpdate.setRemark("");
            //进度
            paymentRequestUpdate.setProgressRate("1/" + processFlows.size());
            paymentRequestMapper.updatePaymentRequest(paymentRequestUpdate);
            //更新审核信息
            processFlowMapper.batchInsertProcessFlow(processFlows);
            if (reviewOrder > 0) {
                //发邮件
                if (StringUtils.isEmpty(paymentRequestRes.getPaymentRequestNo())) {
                    paymentRequestRes.setPaymentRequestNo(paymentRequestUpdate.getPaymentRequestNo());
                }
                paymentRequestComponent.sendEmailAudit(paymentRequestRes, paymentRequestUpdate.getSendToCode());
                return success();
            } else {
                throw new GlobalException("提交失败，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
            }
        } catch (GlobalException businessException) {
            return error(businessException.getMessage());
        } catch (Exception e) {
            logger.error("提交请款单出错,错误原因--->", e);
            return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
        }

    }


    /**
     * 通用审核提交
     * @param paymentAuditResults 审核结果信息
     * @param sysUser 当前登录用户
     * @return
     */
    @Transactional
    @Override
    public int auditSubmit(PaymentAuditResults paymentAuditResults, SysUser sysUser, PaymentRequest paymentRequest)
    {
        //请款单id
        Long id = paymentAuditResults.getPaymentRequestId();
        //审核结果
        Integer auditResult = paymentAuditResults.getResult();
        Boolean flag = true;
        //查询原请款单信息
        if (StringUtils.isNull(paymentRequest)) {
            paymentRequest = paymentRequestMapper.selectPaymentRequestById(id);
        }
        if (StringUtils.isNull(paymentRequest)) {
            throw new GlobalException("查询原请款单信息失败，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
        }

        if (sysUser.isAdmin()) {
            sysUser = userMapper.selectUserByUserCode(paymentRequest.getSendToCode());
        }
        //创建一个新的请款单对象,用于保存将要修改的信息
        PaymentRequest paymentRequestUpdate = new PaymentRequest();
        paymentRequestUpdate.setId(id);
        paymentRequestUpdate.setUpdateTime(DateUtils.getNowDate());
        //单子相关联的审核人员信息
        List<ProcessFlow> processFlows = processFlowMapper.selectProcessFlowListByOrderId(id, OrderTypes.PAYMENT_REQUEST.getCode());
        //查找当前审核人所在的位置
        SysUser u = sysUser;
        Integer status = paymentRequest.getStatus();
        List<ProcessFlow> collect = processFlows.stream().filter(pf -> Objects.equals(pf.getStatus(), status) &&
                StringUtils.isNull(pf.getAuditResult()) && Objects.equals(pf.getCreateByCode(), u.getUserCode()))
                .sorted(Comparator.comparing(ProcessFlow::getReviewOrder)).collect(Collectors.toList());
        ProcessFlow currentStaff = collect.get(0);
        //将当前用户放入已经处理完的id,说明已经处理过
        if (StringUtils.isNotEmpty(paymentRequest.getHandlesCode())) {
            paymentRequestUpdate.setHandlesCode(paymentRequest.getHandlesCode() + "," + sysUser.getUserCode());
        } else {
            paymentRequestUpdate.setHandlesCode(sysUser.getUserCode());
        }
        //进度
        String progressRate = paymentRequest.getProgressRate();
        List<String> progress = Arrays.asList(progressRate.split("/"));
        switch (auditResult) {
            //同意
            case 1:
                approve(paymentRequestUpdate, currentStaff, processFlows,progressRate);
                flag = false;
                break;
            //否决
            case 2:
                //否决不需要再审核，流程结束
                paymentRequestUpdate.setStatus(PaymentRequestStatus.VETO.getCode());
                paymentRequestUpdate.setSendToCode("");
                paymentRequestUpdate.setSendToName("");
                paymentRequestUpdate.setProgressRate("0/1");
                flag = false;
                break;
            //退回重送
            case 3:
                paymentRequestUpdate.setStatus(PaymentRequestStatus.WITHDRAWN.getCode());
                //退回给发起人
                paymentRequestUpdate.setSendToCode(paymentRequest.getEmployeeNo());
                paymentRequestUpdate.setSendToName(paymentRequest.getApplicant());
                //将后面的待审核信息删除
                List<String> ids = new ArrayList<>();
                for (int i = processFlows.indexOf(currentStaff) + 1; i < processFlows.size(); i++) {
                    ids.add(Long.toString(processFlows.get(i).getId()));
                }
                processFlowMapper.deleteProcessFlowByIds(ids.toArray(new String[ids.size()]));
                paymentRequestUpdate.setProgressRate("0/1");
                flag = false;
                break;
            //保留
            case 4:
                //如果是保留需要向发起人发邮件进行通知
                paymentRequestUpdate.setStatus(paymentRequest.getStatus());
                paymentRequestUpdate.setSendToName(paymentRequest.getSendToName());
                paymentRequestUpdate.setSendToCode(paymentRequest.getSendToCode());
                //更改顺序
                for (int i = processFlows.indexOf(currentStaff) + 1; i < processFlows.size(); i++) {
                    ProcessFlow p = processFlows.get(i);
                    p.setReviewOrder(p.getReviewOrder() + 1);
                    processFlowMapper.updateProcessFlow(p);
                }
                //新增一条
                ProcessFlow processFlow = new ProcessFlow();
                processFlow.setOrderId(currentStaff.getOrderId());
                processFlow.setOrderType(currentStaff.getOrderType());
                processFlow.setStatus(currentStaff.getStatus());
                processFlow.setCreateBy(currentStaff.getCreateBy());
                processFlow.setCreateByCode(currentStaff.getCreateByCode());
                processFlow.setReviewOrder(currentStaff.getReviewOrder() + 1);
                processFlowMapper.insertProcessFlow(processFlow);
                paymentRequestUpdate.setProgressRate((Integer.valueOf(progress.get(0)) + 1) + "/" + (Integer.valueOf(progress.get(1)) + 1));
                flag = false;
                break;
            //加签
            case 5:
                Integer endorsementType = paymentAuditResults.getEndorsementType();
                String endorsement = paymentAuditResults.getEndorsement();
                if (StringUtils.isNull(endorsementType)) {
                    throw new ServiceException("获取加签类型失败，请检查是否填写完整或保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
                }
                if (StringUtils.isEmpty(endorsement)) {
                    throw new ServiceException("获取加签人员失败，请检查是否填写完整或保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
                }
                String sendToName = "";
                //获取加签人员的信息
                SysUser user = userMapper.selectUserByUserCode(endorsement);
                if (StringUtils.isNotNull(user)) {
                    sendToName = user.getUserName();
                }
                signatureReview(paymentRequest, paymentRequestUpdate, paymentAuditResults, user, currentStaff, processFlows);
                //保存加签人员
                currentStaff.setEndorsementName(sendToName);
                flag = false;
                if (Objects.equals(paymentAuditResults.getEndorsementType(), 1)){
                    paymentRequestUpdate.setProgressRate((Integer.valueOf(progress.get(0)) + 1) + "/" + (Integer.valueOf(progress.get(1)) + 2));
                } else {
                    paymentRequestUpdate.setProgressRate((Integer.valueOf(progress.get(0)) + 1) + "/" + (Integer.valueOf(progress.get(1)) + 1));
                }
                break;
            default:
        }
        //更新请款单
        int rows = paymentRequestMapper.updatePaymentRequest(paymentRequestUpdate);
        //备注
        currentStaff.setRemark(paymentAuditResults.getRemark());
        //结果
        currentStaff.setAuditResult(paymentAuditResults.getResult());
        //创建时间
        currentStaff.setCreateTime(DateUtils.getNowDate());
        //更新审核信息
        processFlowMapper.updateProcessFlow(currentStaff);
        //发邮件
        if (auditResult == 2){
            paymentRequestComponent.sendEmailReject(paymentRequest, paymentRequest.getEmployeeNo());
        } else if (auditResult == 3){
            paymentRequestComponent.sendEmailRecall(paymentRequest, paymentRequest.getEmployeeNo());
        } else if (auditResult == 4){
            paymentRequestComponent.sendEmailRetain(paymentRequest, paymentRequest.getEmployeeNo());
        } else if (Objects.equals(paymentRequestUpdate.getStatus(), PaymentRequestStatus.COMPLETE.getCode())) {
            paymentRequestComponent.sendEmailComplete(paymentRequest, paymentRequest.getEmployeeNo());
        } else {
            paymentRequestComponent.sendEmailAudit(paymentRequest, paymentRequestUpdate.getSendToCode());
        }
        return rows;
    }

    /**
     * 审核通过
     * @param paymentRequestUpdate 更新信息
     * @param currentStaff 当前用户审核信息
     * @param processFlows 审核人员信息
     * @return
     */
    private void approve(PaymentRequest paymentRequestUpdate,
                                              ProcessFlow currentStaff, List<ProcessFlow> processFlows,String progressRate) {
        //将要处理人信息
        String sendToCode = "";
        String sendToName = "";
        //下一个阶段状态
        Integer nextStatus = PaymentRequestStatus.COMPLETE.getCode();
        //下一个审核顺序
        Integer reviewOrder = currentStaff.getReviewOrder() + 1;
        List<ProcessFlow> collect1 = processFlows.stream().filter(p ->
                Objects.equals(p.getReviewOrder(), reviewOrder)).collect(Collectors.toList());
        if (StringUtils.isNotEmpty(collect1)){
            ProcessFlow processFlow1 = collect1.get(0);
            sendToCode = processFlow1.getCreateByCode();
            sendToName = processFlow1.getCreateBy();
            nextStatus = processFlow1.getStatus();
        }
        paymentRequestUpdate.setSendToCode(sendToCode);
        paymentRequestUpdate.setSendToName(sendToName);
        paymentRequestUpdate.setStatus(nextStatus);
        //进度
        List<String> progress = Arrays.asList(progressRate.split("/"));
        paymentRequestUpdate.setProgressRate((Integer.valueOf(progress.get(0)) + 1) + "/" + (Integer.valueOf(progress.get(1)) ));
    }

    /**
     * 加签审核
     * @param paymentRequest 原单信息
     * @param paymentRequestUpdate 更新信息
     * @param auditResults 用户提交的审核信息
     * @param user 加签人员
     * @param currentStaff 当前用户审核信息
     * @param processFlows 审核人员信息
     */
    public void signatureReview(PaymentRequest paymentRequest, PaymentRequest paymentRequestUpdate,
                                PaymentAuditResults auditResults, SysUser user, ProcessFlow currentStaff,
                                List<ProcessFlow> processFlows)
    {
        //将要处理人信息
        String sendToCode = "";
        String sendToName = "";
        //下一个阶段状态 默认完成状态
        Integer nextStatus = RequisitionStatus.FINISH.getCode();
        Integer endorsementType = auditResults.getEndorsementType();
        //新建加签人员审核记录
        ProcessFlow processFlow = new ProcessFlow();
        processFlow.setOrderType(OrderTypes.PAYMENT_REQUEST.getCode());
        processFlow.setOrderId(paymentRequest.getId());
        processFlow.setCreateBy(user.getUserName());
        processFlow.setCreateByCode(user.getUserCode());
        //下一个审核顺序
        Integer reviewOrder = currentStaff.getReviewOrder() + 1;
        if (endorsementType == 1) {
            //更改签核顺序
            insertProcessFlow(endorsementType,processFlows,currentStaff,processFlow,reviewOrder);
//            for (int i = processFlows.indexOf(currentStaff) + 1; i < processFlows.size(); i++) {
//                ProcessFlow processFlow2 = processFlows.get(i);
//                processFlow2.setReviewOrder(processFlow2.getReviewOrder() + 2);
//                processFlowMapper.updateProcessFlow(processFlow2);
//            }
//            processFlow.setStatus(RequisitionStatus.SIGN_BEFORE.getCode());
//            processFlow.setReviewOrder(reviewOrder);
//            processFlowMapper.insertProcessFlow(processFlow);

            //前加签还需要新增一条当前人员审核信息
            ProcessFlow processFlow1 = new ProcessFlow();
            processFlow1.setOrderId(currentStaff.getOrderId());
            processFlow1.setOrderType(currentStaff.getOrderType());
            processFlow1.setStatus(currentStaff.getStatus());
            processFlow1.setCreateBy(currentStaff.getCreateBy());
            processFlow1.setCreateByCode(currentStaff.getCreateByCode());
            processFlow1.setReviewOrder(reviewOrder + 1);
            processFlowMapper.insertProcessFlow(processFlow1);
            processFlows.add(processFlow1);
        } else if (endorsementType == 2) {
            //后加签默认同意
            auditResults.setResult(1);
            //更改签核顺序
            insertProcessFlow(endorsementType,processFlows,currentStaff,processFlow,reviewOrder);
//            for (int i = processFlows.indexOf(currentStaff) + 1; i < processFlows.size(); i++) {
//                ProcessFlow processFlow2 = processFlows.get(i);
//                processFlow2.setReviewOrder(processFlow2.getReviewOrder() + 1);
//                processFlowMapper.updateProcessFlow(processFlow2);
//            }
//            processFlow.setStatus(RequisitionStatus.SIGN_AFTER.getCode());
//            processFlow.setReviewOrder(currentStaff.getReviewOrder() + 1);
//            processFlowMapper.insertProcessFlow(processFlow);
        }
        processFlows.add(processFlow);
        List<ProcessFlow> collect1 = processFlows.stream().filter(pf ->
                Objects.equals(pf.getReviewOrder(), reviewOrder)).collect(Collectors.toList());
        if (StringUtils.isNotEmpty(collect1)){
            ProcessFlow processFlow1 = collect1.get(0);
            sendToCode = processFlow1.getCreateByCode();
            sendToName = processFlow1.getCreateBy();
            nextStatus = processFlow1.getStatus();
        }
        paymentRequestUpdate.setSendToCode(sendToCode);
        paymentRequestUpdate.setSendToName(sendToName);
        paymentRequestUpdate.setStatus(nextStatus);
    }

    private void insertProcessFlow(Integer endorsementType, List<ProcessFlow> processFlows, ProcessFlow currentStaff,ProcessFlow processFlow,Integer reviewOrder){
        //更改签核顺序
        for (int i = processFlows.indexOf(currentStaff) + 1; i < processFlows.size(); i++) {
            ProcessFlow processFlow2 = processFlows.get(i);
            if (endorsementType == 1){
                processFlow2.setReviewOrder(processFlow2.getReviewOrder() + 2);
            }else{
                processFlow2.setReviewOrder(processFlow2.getReviewOrder() + 1);
            }
            processFlowMapper.updateProcessFlow(processFlow2);
        }
        processFlow.setStatus(PaymentRequestStatus.AFTER_COUNTERSIGN_STATUS.getCode());
        if (endorsementType == 1){
            processFlow.setStatus(PaymentRequestStatus.BEFORE_COUNTERSIGN_STATUS.getCode());
            processFlow.setReviewOrder(reviewOrder);
        }else{
            processFlow.setReviewOrder(currentStaff.getReviewOrder() + 1);
        }
        processFlowMapper.insertProcessFlow(processFlow);

    }
    /**
     * 请款单撤回
     *
     * @param id 需要撤回的id
     * @return
     */
    @Override
    public AjaxResult WithdrawalOfInitiation(Long id) {
        try {
            //将原单子查出来
            PaymentRequest paymentRequestRes = paymentRequestMapper.selectPaymentRequestById(id);
            PaymentRequest paymentRequestUpdate = new PaymentRequest();
            paymentRequestUpdate.setId(id);
            paymentRequestUpdate.setSendToCode(paymentRequestRes.getEmployeeNo());
            paymentRequestUpdate.setSendToName(paymentRequestRes.getApplicant());
            paymentRequestUpdate.setStatus(PaymentRequestStatus.WITHDRAWN.getCode());
            paymentRequestMapper.updatePaymentRequest(paymentRequestUpdate);
            //单子审核人员信息
            List<ProcessFlow> processFlows = processFlowMapper.selectProcessFlowListByOrderId(id, OrderTypes.PAYMENT_REQUEST.getCode());
            //查找当前审核状态对应的记录
            List<ProcessFlow> collect = processFlows.stream().filter(pf -> Objects.equals(pf.getStatus(), paymentRequestRes.getStatus())
                    && StringUtils.isNull(pf.getAuditResult()))
                    .sorted(Comparator.comparing(ProcessFlow::getReviewOrder)).collect(Collectors.toList());
            ProcessFlow currentStaff = collect.get(0);
            //将后面的待审核信息删除
            List<String> ids = new ArrayList<>();
            for (int i = processFlows.indexOf(currentStaff); i < processFlows.size(); i++) {
                ids.add(Long.toString(processFlows.get(i).getId()));
            }
            processFlowMapper.deleteProcessFlowByIds(ids.toArray(new String[ids.size()]));
            return success(id);
        }catch (Exception e) {
            logger.error("撤回请款单出错,错误原因--->", e);
                return error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
            }
    }

    /**
     * 传输文件提交，导致完成
     * @param file 审批截图
     * @param paymentRequestIds
     * @param sysUser
     * @return
     */
    @Transactional
    @Override
    public AjaxResult closePaymentRequestFile(MultipartFile file, String paymentRequestIds,Integer type, SysUser sysUser) {
        String path  = "";
        try {
        String uploadPath = "";
        if (Objects.equals(type, FormFileConstants.PAYMENTREQUEST_TEMPLATE) || Objects.equals(type, FormFileConstants.PAYMENTREQUEST)){
            uploadPath = FormFileConstants.PAYMENTREQUEST_PATH;
        } else if (Objects.equals(type, FormFileConstants.REQUISTION_TEMPLATE) || Objects.equals(type, FormFileConstants.REQUISITION)){
            uploadPath = FormFileConstants.REQUISITION_PATH;
        } else if (Objects.equals(type, FormFileConstants.PETITION_TEMPLATE) || Objects.equals(type, FormFileConstants.PETITION)){
            uploadPath = FormFileConstants.PETITION_PATH;
        }
        // 上传文件路径
        String filePath = RuoYiConfig.getUploadPath() + uploadPath;
        path = FileUploadUtils.uploads(filePath, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
        if (StringUtils.isEmpty(path)){
            throw new GlobalException("上传审批截图出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
        }

        Long[] longs = Convert.toLongArray(paymentRequestIds);
        //批量执行关闭操作
        for (Long id : longs) {
            //将原单子查出来
            PaymentRequest paymentRequest = paymentRequestMapper.selectPaymentRequestById(id);
            if(StringUtils.isNull(paymentRequest)){
                throw new GlobalException("上传审批截图过程出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
            }
            String sendToCode = paymentRequest.getSendToCode();
            SysUser user = userMapper.selectUserByUserCode(sendToCode);

            //构造处理结果
            PaymentAuditResults auditResults = new PaymentAuditResults();
            auditResults.setPaymentRequestId(id);
            auditResults.setResult(1);
            auditResults.setRemark(sysUser.getUserName() + "已上传审批截图");
            //模拟待办人处理流程
            int rows = auditSubmit(auditResults, user, paymentRequest);
            if(rows < 1){
                throw new GlobalException("上传审批截图过程出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
            }
            //保存截图文件信息
            PaymentRequest paymentRequestUpdate = new PaymentRequest();
            paymentRequestUpdate.setId(id);
            //保存文件名
            paymentRequestUpdate.setCloseFileName(file.getOriginalFilename().replaceAll(" ","").replaceAll("&",""));
            //保存文件路径
            paymentRequestUpdate.setCloseFile(uploadPath+path);

            int i = paymentRequestMapper.updatePaymentRequest(paymentRequestUpdate);
            if (i != 1){
                throw new GlobalException("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
            }
        }
        }catch (GlobalException ee){
            logger.error(ee.getMessage(),ee);
            return  AjaxResult.error(ee.getMessage());
        }catch (Exception e){
            return AjaxResult.error("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
        }
        return AjaxResult.success();
    }


    /**
     * 批量审核
     * @param paymentAuditResults 审核信息
     * @param user 操作人
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED,rollbackFor = Exception.class)
    @Override
    public String paymentRequestBatchReview(PaymentAuditResults paymentAuditResults, SysUser user){
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        StringBuilder msg = new StringBuilder();


        Long[] longs = Convert.toLongArray(paymentAuditResults.getIds());
        //批量执行关闭操作
        for (Long id : longs) {
            //将原单子查出来
            PaymentRequest paymentRequest = paymentRequestMapper.selectPaymentRequestById(id);
            try {
            if (StringUtils.isNull(paymentRequest)) {
                throw new GlobalException("批量审批过程出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
            }
            //构造处理结果
            PaymentAuditResults auditResults = new PaymentAuditResults();
            auditResults.setPaymentRequestId(id);
            auditResults.setResult(paymentAuditResults.getResult());
            auditResults.setRemark(paymentAuditResults.getRemark());

                Integer status = paymentRequest.getStatus();
                if (status == 1 || status == 20){
                    throw new GlobalException("此状态不允许批量审核");
                }
                //模拟待办人处理流程
                int rows = auditSubmit(auditResults, user, paymentRequest);
                if (rows < 1) {
                    throw new GlobalException("批量审批过程出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
                }
                successNum++;
                successMsg.append("<br/>" + successNum + "、编号： " + paymentRequest.getPaymentRequestNo() + " 审核成功");
            } catch (Exception e) {
                failureNum++;
                failureMsg.append("<br/>" + failureNum + "、编号： " + paymentRequest.getPaymentRequestNo() + " 审核失败");
                logger.error("请款单批量审批过程出错, 原因------>", e);
                TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            }
        }
        if (failureNum > 0) {
            failureMsg.insert(0, "很抱歉，共 " + failureNum + " 条数据审核失败，数据如下：");
        } else {
            successMsg.insert(0, "批量审核成功 " + successNum + " 条，数据如下：");
        }
        msg.append(successMsg);
        msg.append("<br/>");
        msg.append(failureMsg);
        return msg.toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public AjaxResult insertPaymentRequestFile(MultipartFile[] files,MultipartFile summaryFile,Long id) {


        return null;
    }


    /**
     * 查询文件列表
     * @param parentId 父id
     * @param type 类型
     * @return 文件列表
     */
    @Override
    public List<FormFile> selectFormFile(Long parentId, Integer type){
        return formFileMapper.selectFormFileList(new FormFile(parentId, type));
    }


    private void insertPaymentAccount(PaymentRequest paymentRequest){
        PaymentAccount paymentAccount = new PaymentAccount();
        paymentAccount.setPayee(paymentRequest.getPayee());
        List<PaymentAccount> paymentAccountList = paymentAccountMapper.selectPaymentAccountList(paymentAccount);
        if (StringUtils.isEmpty(paymentAccountList)){
//                paymentAccount.setParentId(paymentRequest.getId());
            paymentAccount.setBankName(paymentRequest.getBankName());
            paymentAccount.setAccountNumber(paymentRequest.getAccountNumber());
            paymentAccount.setAddress(paymentRequest.getAddress());
            paymentAccount.setSwiftCode(paymentRequest.getSwiftCode());
            paymentAccount.setBankAddress(paymentRequest.getBankAddress());
            paymentAccount.setDelFlag(Long.valueOf(PaymentRequestStatus.SHOW_FLAG.getCode()));
            paymentAccount.setCreateTime(DateUtils.getNowDate());
            paymentAccountMapper.insertPaymentAccount(paymentAccount);
    }


}






}
