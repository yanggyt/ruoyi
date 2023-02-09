package com.ruoyi.system.service.requisition.impl;

import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.constant.FormFileConstants;
import com.ruoyi.common.constant.StatusFlagConstants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.OrderTypes;
import com.ruoyi.common.enums.PaymentRequestStatus;
import com.ruoyi.common.enums.RequisitionStatus;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.file.FileUploadUtils;
import com.ruoyi.common.utils.file.FileUtils;
import com.ruoyi.common.utils.file.MimeTypeUtils;
import com.ruoyi.system.component.requisition.RequisitionComponent;
import com.ruoyi.system.domain.FormFile;
import com.ruoyi.system.domain.ProcessFlow;
import com.ruoyi.system.domain.requisition.*;
import com.ruoyi.system.mapper.FormFileMapper;
import com.ruoyi.system.mapper.ProcessFlowMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.requisition.RequisitionMapper;
import com.ruoyi.system.service.requisition.IRequisitionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.common.core.text.Convert;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 请购单Service业务层处理
 * 
 * @author SKaiL
 * @date 2022-09-26
 */
@Service
public class RequisitionServiceImpl implements IRequisitionService
{

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RequisitionMapper requisitionMapper;

    @Autowired
    private RequisitionComponent requisitionComponent;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private ProcessFlowMapper processFlowMapper;

    @Autowired
    private FormFileMapper formFileMapper;

    /**
     * 查询请购单
     * 
     * @param id 请购单主键
     * @return 请购单
     */
    @Override
    public Requisition selectRequisitionById(Long id)
    {
        return requisitionMapper.selectRequisitionById(id);
    }

    /**
     * 查询请购单列表
     * 
     * @param requisition 请购单
     * @return 请购单
     */
    @Override
    public List<Requisition> selectRequisitionList(Requisition requisition)
    {
        return requisitionMapper.selectRequisitionList(requisition);
    }

    /**
     * 我的草稿
     *
     * @param requisition 请购单
     * @return 请购单
     */
    @Override
    public List<Requisition> selectRequisitionListDraft(Requisition requisition) {
        return requisitionMapper.selectRequisitionListByStatus(requisition);
    }

    /**
     * 新增请购单
     * 
     * @param requisition 请购单
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long insertRequisition(Requisition requisition, SysUser user)
    {
        requisition.setSendToCode(user.getUserCode());
        requisition.setSendToName(user.getUserName());
        requisition.setStatus(RequisitionStatus.SAVE.getCode());
        requisition.setCreateBy(user.getUserCode());
        requisition.setCreateTime(DateUtils.getNowDate());
        requisition.setUpdateBy(user.getUserCode());
        requisition.setUpdateTime(DateUtils.getNowDate());
        requisition.setDelFlag(StatusFlagConstants.SHOW_FLAG);
        requisition.setDelFlag(PaymentRequestStatus.SHOW_FLAG.getCode());
        requisitionComponent.insertCheck(requisition);
        requisitionMapper.insertRequisition(requisition);
        insertRequisitionDt1(requisition);
        insertRequisitionDt2(requisition);
        return requisition.getId();
    }

    /**
     * 修改请购单
     * 
     * @param requisition 请购单
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long updateRequisition(Requisition requisition, SysUser user)
    {
        requisition.setUpdateBy(user.getUserCode());
        requisition.setUpdateTime(DateUtils.getNowDate());
        requisitionComponent.insertCheck(requisition);
        requisitionMapper.deleteRequisitionDt1ByRequisitionId(requisition.getId());
        requisitionMapper.deleteRequisitionDt2ByRequisitionId(requisition.getId());
        insertRequisitionDt1(requisition);
        insertRequisitionDt2(requisition);
        requisitionMapper.updateRequisition(requisition);
        return requisition.getId();
    }

    /**
     * 批量删除请购单
     * 
     * @param ids 需要删除的请购单主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteRequisitionByIds(String ids)
    {
//        requisitionMapper.deleteRequisitionDt1ByRequisitionIds(Convert.toStrArray(ids));
//        requisitionMapper.deleteRequisitionDt2ByRequisitionIds(Convert.toStrArray(ids));
        return requisitionMapper.deleteRequisitionByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除请购单信息
     * 
     * @param id 请购单主键
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int deleteRequisitionById(Long id)
    {
        requisitionMapper.deleteRequisitionDt1ByRequisitionId(id);
        return requisitionMapper.deleteRequisitionById(id);
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

    /**
     * 请购单撤回
     * @param id 请购单id
     * @return 结果
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int withdraw(Long id)
    {
        //将原单子查出来
        Requisition requisitionRes = requisitionMapper.selectRequisitionById(id);
        Requisition requisitionUpdate = new Requisition();
        requisitionUpdate.setId(id);
        requisitionUpdate.setStatus(RequisitionStatus.WITHDRAWN.getCode());
        requisitionUpdate.setSendToCode(requisitionRes.getEmployeeNo());
        requisitionUpdate.setSendToName(requisitionRes.getUserName());
        int rows = requisitionMapper.updateRequisition(requisitionUpdate);
        //单子审核人员信息
        List<ProcessFlow> processFlows = processFlowMapper.selectProcessFlowListByOrderId(id, OrderTypes.REQUISITION.getCode());
        //查找当前审核状态对应的记录
        List<ProcessFlow> collect = processFlows.stream().filter(pf -> StringUtils.isNull(pf.getAuditResult()))
                .sorted(Comparator.comparing(ProcessFlow::getReviewOrder)).collect(Collectors.toList());
        ProcessFlow currentStaff = collect.get(0);
        //将后面的待审核信息删除
        List<String> ids = new ArrayList<>();
        for (int i = processFlows.indexOf(currentStaff); i < processFlows.size(); i++) {
            ids.add(Long.toString(processFlows.get(i).getId()));
        }
        processFlowMapper.deleteProcessFlowByIds(ids.toArray(new String[ids.size()]));
        return rows;
    }

    /**
     * 上传审批截图
     *
     * @param file 审批截图
     * @param id 单子ID
     * @param sysUser 操作用户
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int closeRequisitionFile(MultipartFile file, Long id, SysUser sysUser)
    {
        try {
            Requisition requisitionRes = requisitionMapper.selectRequisitionById(id);
            if (StringUtils.isNull(requisitionRes)) {
                logger.error("上传审批截图失败,错误原因---> 单子不存在,id有误");
                throw new ServiceException("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
            }
            String filePath = RuoYiConfig.getUploadPath() + FormFileConstants.REQUISITION_PATH;
            String fileName = file.getOriginalFilename().replaceAll(" ", "").replaceAll("&", "");
            String filePath1 = FormFileConstants.REQUISITION_PATH + FileUploadUtils.uploads(filePath, file, MimeTypeUtils.DEFAULT_ALLOWED_EXTENSION);
            Requisition requisition = new Requisition();
            requisition.setId(id);
            requisition.setCloseFileName(fileName);
            requisition.setCloseFile(filePath1);
            //保存文件信息
            requisitionMapper.updateRequisition(requisition);
            //模拟审核
            RequisitionAuditResults results = new RequisitionAuditResults();
            results.setRequisitionId(id);
            results.setResult(1);
            results.setRemark(sysUser.getUserName() + "上传文件关闭");
            String sendToCode = requisitionRes.getSendToCode();
            SysUser user = userMapper.selectUserByUserCode(sendToCode);
            return auditSubmit(results, user);
        } catch (ServiceException e) {
            throw new ServiceException(e.getMessage());
        } catch (Exception e) {
            logger.error("上传审批截图失败,发生未知异常---> ", e);
            throw new ServiceException("操作失败，发生未知异常，请保存截图，在系统工单模块发起“工作流审批系统”工单反馈。");
        }
    }

    /**
     * 提交
     * @param requisitionId 请购单
     * @param user 用户
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Long submit(Long requisitionId, SysUser user)
    {
        List<ProcessFlow> processFlows = new ArrayList<>();
        Requisition requisitionUpdate = new Requisition();
        Requisition requisitionRes = requisitionMapper.selectRequisitionById(requisitionId);
        if (StringUtils.isNull(requisitionRes)) {
            throw new ServiceException("提交失败，查询请购单信息出错！问题编号：" + requisitionId + "。请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
        }
        if (!user.isAdmin() && !Objects.equals(requisitionRes.getSendToCode(), user.getLoginName())) {
            throw new ServiceException("提交失败！只允许申请人操作。");
        }
        if (user.isAdmin()) {
            user = userMapper.selectUserByUserCode(requisitionRes.getEmployeeNo());
        }
        //封装提交记录
        ProcessFlow processFlow = new ProcessFlow();
        processFlow.setOrderId(requisitionId);
        processFlow.setStatus(requisitionRes.getStatus());
        processFlow.setAuditResult(0);
        processFlow.setCreateTime(DateUtils.getNowDate());
        processFlow.setCreateByCode(user.getUserCode());
        processFlow.setCreateBy(user.getUserName());

        //审核顺序
        int reviewOrder = 1;
        //判断是不是撤回的
        if (Objects.equals(requisitionRes.getStatus(), RequisitionStatus.WITHDRAWN.getCode())) {
            //查询之前的审核记录
            List<ProcessFlow> processFlowList = processFlowMapper.selectProcessFlowListByOrderId(requisitionId, OrderTypes.REQUISITION.getCode());
            if (StringUtils.isNotEmpty(processFlowList)) {
                reviewOrder += processFlowList.get(processFlowList.size() - 1).getReviewOrder();
                //撤回状态改成保存未提交的状态
                processFlow.setStatus(RequisitionStatus.SAVE.getCode());
            }
        }
        processFlows.add(processFlow);
        //获取多级主管
        processFlows = requisitionComponent.getRequestAuditors(user, requisitionComponent.getTotalAmount(requisitionRes), processFlows);
        //获取会计审核阶段对应的负责人
        processFlows = requisitionComponent.judgmentAccountingStaff(requisitionRes, processFlows);
        //为空生成新编号
        if (StringUtils.isEmpty(requisitionRes.getRequisitionNo())) {
            requisitionUpdate.setRequisitionNo(requisitionComponent.primaryKeyGenerationStrategy(requisitionRes));
        }
        //审核顺序
        for (ProcessFlow p : processFlows) {
            p.setOrderType(OrderTypes.REQUISITION.getCode());
            p.setOrderId(requisitionId);
            p.setReviewOrder(reviewOrder++);
        }
        //待审核人信息
        List<ProcessFlow> collect = processFlows.stream().filter(pf -> StringUtils.isNull(pf.getAuditResult()))
                .sorted(Comparator.comparing(ProcessFlow::getReviewOrder)).collect(Collectors.toList());
        ProcessFlow to = collect.get(0);
        //更新单子信息
        requisitionUpdate.setId(requisitionId);
        requisitionUpdate.setSendToCode(to.getCreateByCode());
        requisitionUpdate.setSendToName(to.getCreateBy());
        requisitionUpdate.setStatus(to.getStatus());
        requisitionUpdate.setUpdateTime(DateUtils.getNowDate());
        requisitionUpdate.setSubmitTime(DateUtils.getNowDate());
        //进度
        requisitionUpdate.setProgressRate("1/" + processFlows.size());
        requisitionMapper.updateRequisition(requisitionUpdate);
        //更新审核信息
        processFlowMapper.batchInsertProcessFlow(processFlows);
        if (reviewOrder > 0) {
            if (StringUtils.isEmpty(requisitionRes.getRequisitionNo())) {
                requisitionRes.setRequisitionNo(requisitionUpdate.getRequisitionNo());
            }
            requisitionComponent.sendEmailAudit(requisitionRes, requisitionUpdate.getSendToCode());
            return requisitionId;
        } else {
            throw new ServiceException("提交失败，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
        }
    }

    /**
     * 通用审核提交
     * @param auditResults 审核结果信息
     * @param sysUser 当前登录用户
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public int auditSubmit(RequisitionAuditResults auditResults, SysUser sysUser) {
        //请购单id
        Long id = auditResults.getRequisitionId();
        //审核结果
        Integer auditResult = auditResults.getResult();
        //查询原请购单信息
        Requisition requisition = requisitionMapper.selectRequisitionById(id);
        if (StringUtils.isNull(requisition)){
            throw new ServiceException("查询原请购单信息失败，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
        }
        if (sysUser.isAdmin()) {
            sysUser = userMapper.selectUserByUserCode(requisition.getSendToCode());
        }
        //创建一个新的请购单对象,用户保存将要修改的信息
        Requisition requisitionUpdate = new Requisition();
        requisitionUpdate.setUpdateTime(DateUtils.getNowDate());
        requisitionUpdate.setId(id);
        //单子相关联的审核人员信息
        List<ProcessFlow> processFlows = processFlowMapper.selectProcessFlowListByOrderId(id, OrderTypes.REQUISITION.getCode());
        //查找当前审核人所在的位置
        SysUser u = sysUser;
        List<ProcessFlow> collect = processFlows.stream().filter(pf -> Objects.equals(pf.getStatus(), requisition.getStatus()) &&
                StringUtils.isNull(pf.getAuditResult()) && Objects.equals(pf.getCreateByCode(), u.getUserCode()))
                .sorted(Comparator.comparing(ProcessFlow::getReviewOrder)).collect(Collectors.toList());
        ProcessFlow currentStaff = collect.get(0);
        //将当前用户放入已处理完的id,说明已经处理过
        if (StringUtils.isNotEmpty(requisition.getHandlesCode())){
            requisitionUpdate.setHandlesCode(requisition.getHandlesCode() + "," + sysUser.getUserCode());
        }else {
            requisitionUpdate.setHandlesCode(sysUser.getUserCode());
        }
        //进度
        String progressRate = requisition.getProgressRate();
        List<String> progress = Arrays.asList(progressRate.split("/"));
        //判断审核结果
        switch(auditResult){
            //同意
            case 1:
                approve(requisition, requisitionUpdate, auditResults, currentStaff, processFlows);
                break;
            //否决
            case 2:
                //否决不需要再审核，流程结束
                requisitionUpdate.setStatus(RequisitionStatus.VETO.getCode());
                requisitionUpdate.setSendToCode("");
                requisitionUpdate.setSendToName("");
                requisitionUpdate.setProgressRate("0/1");
                break;
            //退回重送
            case 3:
                requisitionUpdate.setStatus(RequisitionStatus.WITHDRAWN.getCode());
                //退回给发起人
                requisitionUpdate.setSendToCode(requisition.getEmployeeNo());
                requisitionUpdate.setSendToName(requisition.getUserName());
                //将后面的待审核信息删除
                List<String> ids = new ArrayList<>();
                for (int i = processFlows.indexOf(currentStaff) + 1; i < processFlows.size(); i++) {
                    ids.add(Long.toString(processFlows.get(i).getId()));
                }
                processFlowMapper.deleteProcessFlowByIds(ids.toArray(new String[ids.size()]));
                requisitionUpdate.setProgressRate("0/1");
                break;
            //保留
            case 4:
                //如果是保留需要向发起人发邮件进行通知
                requisitionUpdate.setStatus(requisition.getStatus());
                requisitionUpdate.setSendToCode(requisition.getSendToCode());
                requisitionUpdate.setSendToName(requisition.getSendToName());
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
                requisitionUpdate.setProgressRate((Integer.valueOf(progress.get(0)) + 1) + "/" + (Integer.valueOf(progress.get(1)) + 1));
                break;
            //加签
            case 5:
                Integer endorsementType = auditResults.getEndorsementType();
                String endorsement = auditResults.getEndorsement();
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
                signatureReview(requisition, requisitionUpdate, auditResults, user, currentStaff, processFlows);
                //保存加签人员
                currentStaff.setEndorsementName(sendToName);
                if (Objects.equals(auditResults.getEndorsementType(), 1)){
                    requisitionUpdate.setProgressRate((Integer.valueOf(progress.get(0)) + 1) + "/" + (Integer.valueOf(progress.get(1)) + 2));
                } else {
                    requisitionUpdate.setProgressRate((Integer.valueOf(progress.get(0)) + 1) + "/" + (Integer.valueOf(progress.get(1)) + 1));
                }
                break;
            default:
                throw new ServiceException("判断审核结果出错，请检查是否填写完整或保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
        }
        //更新请购单
        int rows = requisitionMapper.updateRequisition(requisitionUpdate);
        //备注
        currentStaff.setRemark(auditResults.getRemark());
        //结果
        currentStaff.setAuditResult(auditResults.getResult());
        //创建时间
        currentStaff.setCreateTime(DateUtils.getNowDate());
        //更新审核信息
        processFlowMapper.updateProcessFlow(currentStaff);
        //发邮件
        if (auditResult == 2){
            requisitionComponent.sendEmailReject(requisition, requisition.getEmployeeNo());
        } else if (auditResult == 3){
            requisitionComponent.sendEmailRecall(requisition, requisition.getEmployeeNo());
        } else if (auditResult == 4){
            requisitionComponent.sendEmailRetain(requisition, requisition.getEmployeeNo());
        } else if (Objects.equals(requisitionUpdate.getStatus(), RequisitionStatus.FINISH.getCode())) {
            requisitionComponent.sendEmailComplete(requisition, requisition.getEmployeeNo());
        } else {
            //发邮件有用到
            if (StringUtils.isNotNull(auditResults.getRequisition()) && StringUtils.isNotEmpty(auditResults.getRequisition().getRequisitionDt1List())) {
                requisition.setRequisitionDt1List(auditResults.getRequisition().getRequisitionDt1List());
            }
            requisitionComponent.sendEmailAudit(requisition, requisitionUpdate.getSendToCode());
        }
        return rows;
    }

    /**
     * 审核通过
     * @param requisition 原单信息
     * @param requisitionUpdate 更新信息
     * @param auditResults 用户提交的审核信息
     * @param currentStaff 当前用户审核信息
     * @param processFlows 审核人员信息
     * @return
     */
    private void approve(Requisition requisition, Requisition requisitionUpdate, RequisitionAuditResults auditResults,
                         ProcessFlow currentStaff, List<ProcessFlow> processFlows)
    {
        //将要处理人信息
        String sendToCode = "";
        String sendToName = "";
        //下一个阶段状态 默认完成状态
        Integer nextStatus = RequisitionStatus.FINISH.getCode();
        Long id = requisition.getId();
        //下一个审核顺序
        Integer reviewOrder = currentStaff.getReviewOrder() + 1;
        //采购代表审核特殊
        int newQty = 0;
        if (Objects.equals(requisition.getStatus(), RequisitionStatus.DB_OFFER.getCode())) {
            if (StringUtils.isNotNull(auditResults.getRequisition().getRequisitionDt1List())) {
                requisitionUpdate.setRequisitionDt1List(auditResults.getRequisition().getRequisitionDt1List());
                requisitionMapper.deleteRequisitionDt1ByRequisitionId(requisition.getId());
                insertRequisitionDt1(requisitionUpdate);
            }
            if (StringUtils.isNotNull(auditResults.getRequisition().getRequisitionDt2List())) {
                requisitionUpdate.setRequisitionDt2List(auditResults.getRequisition().getRequisitionDt2List());
                requisitionMapper.deleteRequisitionDt2ByRequisitionId(requisition.getId());
                insertRequisitionDt2(requisitionUpdate);
            }
            //再查找一次主管比较差异
            SysUser user = userMapper.selectUserByUserCode(requisition.getEmployeeNo());
            List<ProcessFlow> processFlows1 = requisitionComponent.getRequestAuditors(user, requisitionComponent.getTotalAmount(requisition), new ArrayList<>());
            List<ProcessFlow> processFlows2 = requisitionComponent.getRequestAuditors(user, requisitionComponent.getTotalAmount(requisitionUpdate), new ArrayList<>());
            processFlows2.removeAll(processFlows1);
            //不相同在采购代表后面新增
            if (StringUtils.isNotEmpty(processFlows2)){
                newQty = processFlows2.size();
                //修改采购代表后的审核顺序
                int size = processFlows2.size();
                for (int i = processFlows.indexOf(currentStaff) + 1; i < processFlows.size(); i++) {
                    ProcessFlow p = processFlows.get(i);
                    p.setReviewOrder(p.getReviewOrder() + size);
                    processFlowMapper.updateProcessFlow(p);
                }
                //新增主管审核
                int newOrder = reviewOrder;
                for (ProcessFlow pf2 : processFlows2) {
                    pf2.setReviewOrder(newOrder++);
                    pf2.setOrderType(OrderTypes.REQUISITION.getCode());
                    pf2.setOrderId(id);
                    processFlowMapper.insertProcessFlow(pf2);
                }
                processFlows.addAll(processFlows2);
            }
        }
        List<ProcessFlow> collect1 = processFlows.stream().filter(p ->
                Objects.equals(p.getReviewOrder(), reviewOrder)).collect(Collectors.toList());
        if (StringUtils.isNotEmpty(collect1)){
            ProcessFlow processFlow1 = collect1.get(0);
            sendToCode = processFlow1.getCreateByCode();
            sendToName = processFlow1.getCreateBy();
            nextStatus = processFlow1.getStatus();
        }
        requisitionUpdate.setSendToCode(sendToCode);
        requisitionUpdate.setSendToName(sendToName);
        requisitionUpdate.setStatus(nextStatus);
        //进度
        List<String> progress = Arrays.asList(requisition.getProgressRate().split("/"));
        requisitionUpdate.setProgressRate((Integer.valueOf(progress.get(0)) + 1) + "/" + (Integer.valueOf(progress.get(1)) + newQty));
    }

    /**
     * 加签审核
     * @param requisition 原单信息
     * @param requisitionUpdate 更新信息
     * @param auditResults 用户提交的审核信息
     * @param user 加签人员
     * @param currentStaff 当前用户审核信息
     * @param processFlows 审核人员信息
     */
    public void signatureReview(Requisition requisition, Requisition requisitionUpdate, RequisitionAuditResults auditResults,
                                SysUser user, ProcessFlow currentStaff, List<ProcessFlow> processFlows)
    {
        //将要处理人信息
        String sendToCode = "";
        String sendToName = "";
        //下一个阶段状态 默认完成状态
        Integer nextStatus = RequisitionStatus.FINISH.getCode();
        Integer endorsementType = auditResults.getEndorsementType();
        //新建加签人员审核记录
        ProcessFlow processFlow = new ProcessFlow();
        processFlow.setOrderType(OrderTypes.REQUISITION.getCode());
        processFlow.setOrderId(requisition.getId());
        processFlow.setCreateBy(user.getUserName());
        processFlow.setCreateByCode(user.getUserCode());
        //下一个审核顺序
        Integer reviewOrder = currentStaff.getReviewOrder() + 1;
        if (endorsementType == 1) {
            processFlow.setStatus(RequisitionStatus.SIGN_BEFORE.getCode());
            //更改签核顺序
            for (int i = processFlows.indexOf(currentStaff) + 1; i < processFlows.size(); i++) {
                ProcessFlow processFlow2 = processFlows.get(i);
                processFlow2.setReviewOrder(processFlow2.getReviewOrder() + 2);
                processFlowMapper.updateProcessFlow(processFlow2);
            }
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
            processFlow.setStatus(RequisitionStatus.SIGN_AFTER.getCode());
            //后加签默认同意
            auditResults.setResult(1);
            //更改签核顺序
            for (int i = processFlows.indexOf(currentStaff) + 1; i < processFlows.size(); i++) {
                ProcessFlow processFlow2 = processFlows.get(i);
                processFlow2.setReviewOrder(processFlow2.getReviewOrder() + 1);
                processFlowMapper.updateProcessFlow(processFlow2);
            }
        }
        processFlow.setReviewOrder(reviewOrder);
        processFlowMapper.insertProcessFlow(processFlow);
        processFlows.add(processFlow);
        List<ProcessFlow> collect1 = processFlows.stream().filter(pf ->
                Objects.equals(pf.getReviewOrder(), reviewOrder)).collect(Collectors.toList());
        if (StringUtils.isNotEmpty(collect1)){
            ProcessFlow processFlow1 = collect1.get(0);
            sendToCode = processFlow1.getCreateByCode();
            sendToName = processFlow1.getCreateBy();
            nextStatus = processFlow1.getStatus();
        }
        requisitionUpdate.setSendToCode(sendToCode);
        requisitionUpdate.setSendToName(sendToName);
        requisitionUpdate.setStatus(nextStatus);
    }

    /**
     * 新增请购单产品明细dt1信息
     * 
     * @param requisition 请购单对象
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertRequisitionDt1(Requisition requisition)
    {
        List<RequisitionDt1> requisitionDt1List = requisition.getRequisitionDt1List();
        Long id = requisition.getId();
        if (StringUtils.isNotNull(requisitionDt1List))
        {
            List<RequisitionDt1> list = new ArrayList<RequisitionDt1>();
            for (RequisitionDt1 requisitionDt1 : requisitionDt1List)
            {
                requisitionDt1.setRequisitionId(id);
                list.add(requisitionDt1);
            }
            if (list.size() > 0)
            {
                requisitionMapper.batchRequisitionDt1(list);
            }
        }
    }

    /**
     * 新增请购单供应商明显dt2信息
     *
     * @param requisition 请购单对象
     */
    @Transactional(rollbackFor = Exception.class)
    public void insertRequisitionDt2(Requisition requisition)
    {
        List<RequisitionDt2> requisitionDt2List = requisition.getRequisitionDt2List();
        Long id = requisition.getId();
        if (StringUtils.isNotNull(requisitionDt2List))
        {
            List<RequisitionDt2> list = new ArrayList<RequisitionDt2>();
            for (RequisitionDt2 requisitionDt2 : requisitionDt2List)
            {
                requisitionDt2.setRequisitionId(id);
                list.add(requisitionDt2);
            }
            if (list.size() > 0)
            {
                requisitionMapper.batchRequisitionDt2(list);
            }
        }
    }

    /**
     * 批量审核
     *
     * @param requisitionAuditResults 审核结果
     * @param sysUser 操作人
     * @return
     */
    @Transactional(propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
    @Override
    public String requisitionBatchReview(RequisitionAuditResults requisitionAuditResults, SysUser sysUser) {
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        StringBuilder msg = new StringBuilder();
        Long[] longs = Convert.toLongArray(requisitionAuditResults.getIds());
        //批量执行关闭操作
        for (Long id : longs) {
            //将原单子查出来
            Requisition requisition = requisitionMapper.selectRequisitionById(id);
            try {
            if (StringUtils.isNull(requisition)) {
                throw new GlobalException("批量审批过程出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
            }
            //构造处理结果
            RequisitionAuditResults auditResults = new RequisitionAuditResults();
            auditResults.setRequisitionId(id);
            auditResults.setResult(requisitionAuditResults.getResult());
            auditResults.setRemark(requisitionAuditResults.getRemark());

                Integer status = requisition.getStatus();
                if (status == 1 || status == 13 || status ==14 || status ==20 || status ==7){
                    throw new GlobalException("此状态不允许批量审核");
                }
                //模拟待办人处理流程
                Integer submit = auditSubmit(auditResults, sysUser);
                if (StringUtils.isNull(submit)) {
                    throw new GlobalException("批量审批过程出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
                }
                successNum++;
                successMsg.append("<br/>" + successNum + "、编号： " + requisition.getRequisitionNo() + " 审核成功");
            }catch (GlobalException e) {
                throw new GlobalException(e.getMessage());
            } catch (Exception e) {
                failureNum++;
                failureMsg.append("<br/>" + failureNum + "、编号： " + requisition.getRequisitionNo() + " 审核失败");
                logger.error("请购单批量审批过程出错, 原因------>",e);
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

    /**
     * 使用说明相关文档下载
     *
     * @param type 文档类型(1.使用说明 2.文档模版)
     */
    @Override
    public void downloadFileDescription(Integer type, HttpServletResponse response)
    {
        String fileName = "";
        String filePath = "";
        try {
            FormFile formFile = new FormFile();
            formFile.setFileType(FormFileConstants.REQUISTION_TEMPLATE);
            List<FormFile> formFiles = formFileMapper.selectFormFileList(formFile);
            if (StringUtils.isEmpty(formFiles)) {
                logger.error("获取说明文档失败, 原因--->未查询到相关文件");
                throw new ServiceException("获取说明文档失败, 未查询到相关文件");
            }
            Stream<FormFile> stream = formFiles.stream();
            FormFile formFile1 = new FormFile();
            if (Objects.equals(type, 1)) {
                formFile1 = stream.filter(ff -> ff.getFileName().contains(".pdf"))
                        .sorted(Comparator.comparing(FormFile::getCreateTime).reversed()).collect(Collectors.toList()).get(0);
            } else if (Objects.equals(type, 2)) {
                formFile1 = stream.filter(ff -> ff.getFileName().contains(".xlsx"))
                        .sorted(Comparator.comparing(FormFile::getCreateTime).reversed()).collect(Collectors.toList()).get(0);
            }
            fileName = formFile1.getFileName();
            filePath = formFile1.getFilePath();
            if (!FileUtils.checkAllowDownload(filePath)) {
                throw new ServiceException(StringUtils.format("资源文件({})非法，不允许下载。 ", filePath));
            }
            // 本地资源路径
            String localPath = RuoYiConfig.getUploadPath();
            // 数据库资源地址
            String downloadPath = localPath + StringUtils.substringAfter(filePath, Constants.RESOURCE_PREFIX) + filePath;
            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, fileName);
            FileUtils.writeBytes(downloadPath, response.getOutputStream());
        } catch (Exception e) {
            logger.error("获取说明文档失败, 发生未知异常, 原因--->", e);
        }
    }
}
