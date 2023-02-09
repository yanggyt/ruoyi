package com.ruoyi.system.component.paymentRequest;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.RuoYiConfig;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.PaymentRequestConstant;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.OrderTypes;
import com.ruoyi.common.enums.PaymentRequestNode;
import com.ruoyi.common.enums.PaymentRequestStatus;
import com.ruoyi.common.enums.RequisitionStatus;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.ListUtil;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.email.BootEmail;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.poi.ExcelUtil;
import com.ruoyi.system.domain.NameRole;
import com.ruoyi.system.domain.PaymentAccount;
import com.ruoyi.system.domain.ProcessFlow;
import com.ruoyi.system.domain.paymentRequest.PaymentRequest;
import com.ruoyi.system.domain.paymentRequest.PaymentRequestDt1;
import com.ruoyi.system.domain.requisition.Requisition;
import com.ruoyi.system.dto.paymentRequest.Ocrd;
import com.ruoyi.system.dto.paymentRequest.PaymentRequestDt1Dto;
import com.ruoyi.system.dto.paymentRequest.PaymentRequestDto;
import com.ruoyi.system.dto.paymentRequest.PaymentSummaryDto;
import com.ruoyi.system.mapper.PaymentAccountMapper;
import com.ruoyi.system.mapper.ProcessFlowMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.paymentRequest.PaymentRequestDt1Mapper;
import com.ruoyi.system.mapper.paymentRequest.PaymentRequestMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileInputStream;
import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

import static com.ruoyi.common.utils.ProCodeUtils.getCacheKey;
import static com.ruoyi.common.utils.ProCodeUtils.getCacheName;

/**
 * @Filename: PaymentRequestComponent
 * @Author: lss
 * @Data:2022/9/27 9:28
 */
@Component
public class PaymentRequestComponent {
    private static final Logger log = LoggerFactory.getLogger(PaymentRequestComponent.class);

    @Autowired
    private PaymentRequestDt1Mapper paymentRequestDt1Mapper;

    @Autowired
    private PaymentRequestMapper paymentRequestMapper;

    @Autowired
    private ProcessFlowMapper processFlowMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private PaymentAccountMapper paymentAccountMapper;

    @Autowired
    private ServerConfig config;

    @Autowired
    private PaymentRequestConstant paymentRequestConstant;


    private static final String ROLE_SUPERVISOR = "主管";
    private static final String ROLE_MANAGER = "经理";
    private static final String ROLE_DIRECTOR = "总监";
    private static final String ROLE_EXECUTIVE_DIRECTOR = "执行总监";
    private static final String ROLE_GENERAL_MANAGER = "总经理";

    public void buildList(List<PaymentRequest> list){
        for(PaymentRequest paymentRequest: list){
            String userName = "";
            Long id = paymentRequest.getId();
            //展示币别
            PaymentRequestDt1 paymentRequestDt1 = new PaymentRequestDt1();
            paymentRequestDt1.setRequestId(id);
            PaymentRequestDt1 dt1 = paymentRequestDt1Mapper.selectPaymentRequestDt1List(paymentRequestDt1).get(0);
            paymentRequest.setRemark(dt1.getCurrency().toString());
        }
    }

    /**
     * 计算总金额, 汇率转换(转成人民币)
     * @param paymentRequest
     * @return
     */
    public String exchangeRateConversion(PaymentRequest paymentRequest) {
        PaymentRequestDt1 paymentRequestDt1 = new PaymentRequestDt1();
        paymentRequestDt1.setRequestId(paymentRequest.getId());
        List<PaymentRequestDt1> paymentRequestDt1s = paymentRequestDt1Mapper.selectPaymentRequestDt1List(paymentRequestDt1);
        //汇率转换，获取总金额
        String totalAmount = currencyConverter(new BigDecimal(paymentRequest.getPaymentAmount()),
                paymentRequestDt1s.get(0).getCurrency()).toString();
        return totalAmount;
    }

    /**
     * 汇率换算
     * @param amount 金额
     * @param currencyType 币别
     * @return 人民币
     */
    public BigDecimal currencyConverter(BigDecimal amount, Integer currencyType){
        String currencyStr = "";
        if (Objects.equals(currencyType, 2)){
            currencyStr = "USD";
        } else if (Objects.equals(currencyType, 3)){
            currencyStr = "TWD";
        } else if (Objects.equals(currencyType, 4)){
            currencyStr = "HKD";
        } else if (Objects.equals(currencyType, 5)){
            currencyStr = "KRW";
        } else if (Objects.equals(currencyType, 6)){
            currencyStr = "MOP";
        } else if (Objects.equals(currencyType, 7)){
            currencyStr = "EUR";
        } else {
            return amount;
        }
        //currencyStr转美元汇率
        BigDecimal totalAmount = null;
        BigDecimal multiple = new BigDecimal("100");
        BigDecimal a = amount.divide(multiple);
        String rmbCurrency = paymentAccountMapper.selectCurrency("RMB");
        BigDecimal RMBCurrency = new BigDecimal(rmbCurrency);
//        String currency = paymentAccountMapper.selectCurrency(currencyStr);
//        BigDecimal rate = new BigDecimal(currency);
//        BigDecimal totalAmount = null;
        if (Objects.equals(currencyType, 2)){
            //美元特殊处理
            totalAmount = a.divide(RMBCurrency, 2, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);
//            totalAmount = amount.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            //美元转人民币汇率
            String usdCurrency = paymentAccountMapper.selectCurrency(currencyStr);
            BigDecimal USDCurrency = new BigDecimal(usdCurrency);
            //美元转人民币汇率
            totalAmount = a.multiply(USDCurrency).divide(RMBCurrency, 2, BigDecimal.ROUND_HALF_UP).setScale(2, BigDecimal.ROUND_HALF_UP);

//            String currencys = paymentAccountMapper.selectCurrency("USD");
//            BigDecimal rates = new BigDecimal(currencys);
//            totalAmount = amount.multiply(rate).multiply(rates).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return totalAmount.multiply(multiple);
    }

    /**
     * 查找会计审核人员
     * @param paymentRequest 请款单信息
     * @param list 审核人员
     * @return 审核人员
     */
    public List<ProcessFlow> judgmentAccountingStaff(PaymentRequest paymentRequest, List<ProcessFlow> list) {
        //会计 默认南京审核人员
        String accountingCode = paymentRequestConstant.NJ_ACCOUNTING;
        String accountingName = paymentRequestConstant.NJ_ACCOUNTING_NAME;
        //总账 默认南京审核人员
        String generalLedgerCode = paymentRequestConstant.NJ_GENERAL_LEDGER;
        String generalLedgerName = paymentRequestConstant.NJ_GENERAL_LEDGER_NAME;
        //出纳
        switch (paymentRequest.getCompany()) {
            case 6:
                //南京
                accountingCode = paymentRequestConstant.NJ_ACCOUNTING;
                accountingName = paymentRequestConstant.NJ_ACCOUNTING_NAME;
                generalLedgerCode = paymentRequestConstant.NJ_GENERAL_LEDGER;
                generalLedgerName = paymentRequestConstant.NJ_GENERAL_LEDGER_NAME;
                break;
            case 14:
                //上海矽力杰微电子
                accountingCode = paymentRequestConstant.SH_ACCOUNTING;
                accountingName = paymentRequestConstant.SH_ACCOUNTING_NAME;
                generalLedgerCode = paymentRequestConstant.SHW_GENERAL_LEDGER;
                generalLedgerName = paymentRequestConstant.SHW_GENERAL_LEDGER_NAME;
                break;
            case 20:
                //南京香港
                accountingCode = paymentRequestConstant.NJXG_ACCOUNTING;
                accountingName = paymentRequestConstant.NJXG_ACCOUNTING_NAME;
                generalLedgerCode = paymentRequestConstant.NJXG_GENERAL_LEDGER;
                generalLedgerName = paymentRequestConstant.NJXG_GENERAL_LEDGER_NAME;
                break;
            default:
        }
        //会计
        ProcessFlow processFlow1 = new ProcessFlow();
        processFlow1.setStatus(PaymentRequestStatus.FINANCIAL_AUDIT.getCode());
        processFlow1.setCreateByCode(accountingCode);
        processFlow1.setCreateBy(accountingName);
        list.add(processFlow1);

        //总账
        ProcessFlow processFlow2 = new ProcessFlow();
        processFlow2.setStatus(PaymentRequestStatus.FINANCIAL_LEDGER_AUDIT.getCode());
        processFlow2.setCreateByCode(generalLedgerCode);
        processFlow2.setCreateBy(generalLedgerName);
        list.add(processFlow2);
        return list;
    }


    /**
     * 查找审核主管
     * @param paymentType 请款类型
     * @param userRes 当前用户
     * @param amount 请款金额
     * @param list 保存审核人员信息List
     * @return 保存审核人员信息List
     */
    public List<ProcessFlow> getPaymentAuditors(String paymentType, SysUser userRes, String amount, List<ProcessFlow> list)
    {
        String sendToCode = "";
        if (StringUtils.isNotEmpty(userRes.getPurchasesid())) {
            sendToCode = userRes.getPurchasesid();
        } else {
            sendToCode = userRes.getZgsid();
        }
        //查找上级主管
        if ("/".equals(userRes.getZgsid()) || Objects.equals(userRes.getZgsid(), userRes.getUserCode())
                || StringUtils.isEmpty(sendToCode)) {
            throw new GlobalException("查找上级主管出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
        }
        //主管信息
        SysUser user = userMapper.selectUserByUserCode(sendToCode);
        if (Objects.isNull(user)) {
            log.error("获取员工主管有误，问题员工编号：{}", sendToCode);
            throw new GlobalException("查找主管出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
        }
        //主管职级
        String jobCode = user.getJobCode();
        //主管职称
        String jobName = ROLE_SUPERVISOR;
        //主管金额
        String amountA = "0";
        String amountB = "0";
        String amountC = "0";
        String amountE = "0";
        //是否有审核权限
        Boolean flag = true;
        if (Objects.equals(jobCode, "S7") || Objects.equals(jobCode, "S8")) {
            jobName = ROLE_MANAGER;
            amountA = paymentRequestConstant.MAX_A4;
            amountB = paymentRequestConstant.MAX_B4;
            amountC = paymentRequestConstant.MAX_C4;
            amountE = paymentRequestConstant.MAX_E4;
        } else if (Objects.equals(jobCode, "S9") || Objects.equals(jobCode, "S10")) {
            jobName = ROLE_DIRECTOR;
            amountA = paymentRequestConstant.MAX_A3;
            amountB = paymentRequestConstant.MAX_B3;
            amountC = paymentRequestConstant.MAX_C3;
            amountE = paymentRequestConstant.MAX_E3;
        } else if (Objects.equals(jobCode, "S11") || Objects.equals(jobCode, "S12") || Objects.equals(jobCode, "S13")) {
            jobName = ROLE_EXECUTIVE_DIRECTOR;
            amountA = paymentRequestConstant.MAX_A2;
            amountB = paymentRequestConstant.MAX_B2;
            amountC = paymentRequestConstant.MAX_C2;
            amountE = paymentRequestConstant.MAX_E2;
        } else if (Objects.equals(jobCode, "S14")) {
            jobName = ROLE_GENERAL_MANAGER;
            amountA = paymentRequestConstant.MAX_A1;
            amountB = paymentRequestConstant.MAX_B1;
            amountC = paymentRequestConstant.MAX_C1;
            amountE = paymentRequestConstant.MAX_E1;
        } else {
            flag = false;
        }

        ProcessFlow processFlow = new ProcessFlow();
        processFlow.setStatus(judgingStatusByRole(jobName));
        processFlow.setCreateByCode(user.getUserCode());
        processFlow.setCreateBy(user.getUserName());
        list.add(processFlow);
        if (flag) {
            switch (paymentType) {
                case "A":
                case "Y":
                    if (new BigDecimal(amountA).compareTo(new BigDecimal(amount)) < 0) {
                        list = getPaymentAuditors(paymentType, user, amount, list);
                    }
                    break;
                case "B":
                    if (new BigDecimal(amountB).compareTo(new BigDecimal(amount)) < 0) {
                        list = getPaymentAuditors(paymentType, user, amount, list);
                    }
                    break;
                case "C":
                case "X":
                    if (new BigDecimal(amountC).compareTo(new BigDecimal(amount)) < 0) {
                        list = getPaymentAuditors(paymentType, user, amount, list);
                    }
                    break;
                case "E":
                    if (new BigDecimal(amountE).compareTo(new BigDecimal(amount)) < 0) {
                        list = getPaymentAuditors(paymentType, user, amount, list);
                    }
                    break;
                default:
            }
        } else {
            list = getPaymentAuditors(paymentType, user, amount, list);
        }

        return list;
    }

    /**
     * 根据角色判断状态
     * @param role 角色
     * @return 单子状态
     */
    public Integer judgingStatusByRole(String role)
    {
        Integer status = 1;
        switch (role) {
            case "主管":
                status = PaymentRequestStatus.SUPERVISOR.getCode();
                break;
            case "经理":
                status = PaymentRequestStatus.MANAGER.getCode();
                break;
            case "总监":
                status = PaymentRequestStatus.DIRECTOR.getCode();
                break;
            case "执行总监":
                status = PaymentRequestStatus.EXECUTIVE_DIRECTOR.getCode();
                break;
            case "会计":
                status = PaymentRequestStatus.FINANCIAL_AUDIT.getCode();
                break;
            case "总账":
                status = PaymentRequestStatus.FINANCIAL_LEDGER_AUDIT.getCode();
                break;
            case "总经理":
                status = PaymentRequestStatus.GENERAL_MANAGER.getCode();
                break;
            case "前加签":
                status = PaymentRequestStatus.BEFORE_COUNTERSIGN_STATUS.getCode();
                break;
            case "后加签":
                status = PaymentRequestStatus.AFTER_COUNTERSIGN_STATUS.getCode();
                break;
            default:

        }
        return status;
    }

    /**
     * 计算审批进度
     *
     * @param paymentRequest 原单信息
     * @return 进度
     */
    public void approvalProgress (PaymentRequest paymentRequest){
        if (paymentRequest.getStatus() == 1 || paymentRequest.getStatus() == 20) {
            paymentRequest.setProgressRate("0");
        }else {
            //进度
            String progressRate = paymentRequest.getProgressRate();
            List<String> progress = Arrays.asList(progressRate.split("/"));
            BigDecimal a = new BigDecimal(progress.get(0));
            BigDecimal b = new BigDecimal(progress.get(1));
            String progressbar = a.divide(b, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"))
                    .setScale(0, BigDecimal.ROUND_HALF_UP).toString();
            paymentRequest.setProgressRate(progressbar);

        }
    }

    /**
     * 发送处理邮件
     * @param paymentRequest
     * @param userCode
     * @return
     */
    public void sendEmailAudit(PaymentRequest paymentRequest, String userCode) {
        Long paymentRequestId = paymentRequest.getId();
        String sendToUser = "";
        String titleString = paymentRequest.getApplicant() + "的请款单";
        String url = config.getUrl() + "/redirect/system/paymentRequest/review/"+paymentRequestId;
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为:"+ paymentRequest.getPaymentRequestNo());
        html.append("的请款单请去审核。<a href='").append(url).append("'>点击链接</a>");
        html.append("</body></html>");
        try {
            //判断是否有多个员工编号
            if (userCode.contains(",")) {
                String[] split = userCode.split(",");
                for (int i = 0; i < split.length; i++) {
                    sendToUser = userMapper.selectUserByUserCode(split[i]).getEmail();
                    log.info("发送请款单处理邮件----------收件人：" + sendToUser);
                    // 给处理者发邮件
                    BootEmail.testSendHtml(sendToUser,titleString,html.toString());
                }
            } else {
                sendToUser = userMapper.selectUserByUserCode(userCode).getEmail();
                log.info("发送请款单处理邮件----------收件人：" + sendToUser);
                // 给处理者发邮件
                BootEmail.testSendHtml(sendToUser,titleString,html.toString());
            }
        } catch (Exception e) {
            log.error("发送请款单处理邮件失败--------------"+e.getMessage()+
                    "---------请款单编号---------------" + paymentRequest.getPaymentRequestNo());
        }
    }

    /**
     * 发送撤回邮件
     * @param paymentRequest 请款单
     * @param userCode 收件人
     * @return
     */
    public void sendEmailRecall(PaymentRequest paymentRequest, String userCode){
        Long paymentRequestId = paymentRequest.getId();
        SysUser user = userMapper.selectUserByUserCode(userCode);
        String sendToUser = user.getEmail();
        String titleString = paymentRequest.getApplicant() + "请款单撤回";
        String text = "编号为："+paymentRequest.getPaymentRequestNo()+"的请款单已经被撤回，请重新编辑。";
        try {
            // 给处理者发邮件
            log.info("发送请款单撤回邮件----------收件人：" + sendToUser);
            BootEmail.sendSimpleMail(sendToUser,titleString,text);
        } catch (Exception e) {
            log.error("---发送邮件失败--------------"+e.getMessage()+"------------------------------");
        }
    }


    /**
     * 发送保留邮件
     * @param paymentRequest
     * @param userCode
     * @return
     */
    public void sendEmailRetain(PaymentRequest paymentRequest, String userCode) {
        String handlesCode = paymentRequest.getHandlesCode();
        String[] c;
        String[] cc = new String[0];
        if (StringUtils.isNotEmpty(handlesCode)){
             c = handlesCode.split(",");
             cc = new String[c.length - 1];
            for (int i = 0; i < c.length - 1; i++) {
                cc[i] = userMapper.selectUserByUserCode(userCode).getEmail();
            }
        }
        SysUser user = userMapper.selectUserByUserCode(userCode);
        String sendToUser = user.getEmail();
        String titleString = paymentRequest.getApplicant() +"请款单保留";
        String text = "编号为："+paymentRequest.getPaymentRequestNo()+"的请款单已经被保留，请对相关人员做出说明。";
        try {
            // 给处理者发邮件
            log.info("发送请款单保留邮件----------收件人：" + sendToUser + "-------------");
            BootEmail.sendMailWithCC(sendToUser,titleString,text,cc);
        } catch (Exception e) {
            log.error("发送请款单保留邮件失败--------------"+e.getMessage()+
                    "---------请款单编号---------------" + paymentRequest.getPaymentRequestNo());
        }
    }
    /**
     * 发送驳回邮件
     * @param paymentRequest
     * @param userCode
     * @return
     */
    public void sendEmailReject(PaymentRequest paymentRequest,String userCode){
        Long paymentRequestId = paymentRequest.getId();
        SysUser user = userMapper.selectUserByUserCode(userCode);
        String sendToUser = user.getEmail();
        String titleString = paymentRequest.getApplicant() +"请款单驳回";
        String text = "编号为："+paymentRequest.getPaymentRequestNo()+"的请款单已经被驳回，请重新申请"+ "。链接："+config.getUrl()+"/redirect/system/paymentRequest/detail/" + paymentRequestId + "/1";
        try {
            // 给处理者发邮件
            log.info("发送请款单驳回邮件----------收件人：" + sendToUser);
            BootEmail.sendSimpleMail(sendToUser,titleString,text);
        } catch (Exception e) {
            log.error("发送请款单驳回邮件失败--------------"+e.getMessage()+
                    "---------请款单编号---------------" + paymentRequest.getPaymentRequestNo());
        }
    }

    /**
     * 发送打印PDF邮件
     * @param paymentRequest 单子ID
     * @param userCode 收件人
     * @return
     */
    public void sendEmailPrint(PaymentRequest paymentRequest,String userCode){
        Long paymentRequestId = paymentRequest.getId();
        SysUser user = userMapper.selectUserByUserCode(userCode);
        String sendToUser = user.getEmail();
        String url = config.getUrl() + "/redirect/system/paymentRequest/detail/" + paymentRequestId + "/1";
        String titleString = "请款单打印提醒!!!";
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\">" +
                "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为："+ paymentRequest.getPaymentRequestNo()+"的请款单已通过" +
                "主管审批，请将请款单页面打印，并附上发票或合同等相关资料交予财务部。<a href='").append(url).append("'>查看详情</a>");
        html.append("</body></html>");
        try {
            // 给处理者发邮件
            log.info("发送请款单打印邮件----------收件人：" + sendToUser);
            BootEmail.testSendHtml(sendToUser, titleString, html.toString());
        } catch (Exception e) {
            log.error("发送请款单打印邮件失败--------------"+e.getMessage()+
                    "---------请款单编号---------------" + paymentRequest.getPaymentRequestNo());
        }
    }

    /**
     * 发送完成邮件
     * @param paymentRequest
     * @param userCode
     * @return
     */
    public void sendEmailComplete(PaymentRequest paymentRequest,String userCode){
        Long paymentRequestId = paymentRequest.getId();
        SysUser user = userMapper.selectUserByUserCode(userCode);
        String sendToUser = user.getEmail();
        String url = config.getUrl() + "/redirect/system/paymentRequest/detail/" + paymentRequestId + "/1";
        String titleString = paymentRequest.getApplicant() + "请款单已经完成";
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为："+ paymentRequest.getPaymentRequestNo()+"的请款单已经完成。<a href='").append(url).append("'>查看详情</a>");
        html.append("</body></html>");
        try {
            // 给处理者发邮件
            log.info("发送请款单完成邮件----------收件人：" + sendToUser);
            BootEmail.testSendHtml(sendToUser, titleString, html.toString());
        } catch (Exception e) {
            log.error("发送请款单完成邮件失败--------------"+e.getMessage()+
                    "---------请款单编号---------------" + paymentRequest.getPaymentRequestNo());
        }
    }


    /**
     * 拆分请款单
     * @param paymentRequest
     */
    @Transactional
    public void splitPaymentRequest(PaymentRequest paymentRequest) {
        //获取汇总文件地址
        String filePath = RuoYiConfig.getUploadPath() + paymentRequest.getSummaryFile();
        //解析文件,生成list
        List<PaymentSummaryDto> list = null;
        try {
            ExcelUtil<PaymentSummaryDto> util = new ExcelUtil<PaymentSummaryDto>(PaymentSummaryDto.class);
            list = util.importExcel(new FileInputStream(new File(filePath)));
        }catch (Exception e){
            log.error("请款单生产性支出拆单失败,解析Excel出错,错误原因--->" + e.getMessage());
        }
        //审批记录
        ProcessFlow processFlow = new ProcessFlow();
        processFlow.setOrderId(paymentRequest.getId());
        processFlow.setOrderType(OrderTypes.PAYMENT_REQUEST.getCode());
        List<ProcessFlow> processFlowList = processFlowMapper.selectProcessFlowList(processFlow);
        //将list拆分成请款单
        List<Long> ids = new ArrayList<>();
        list.forEach(ps -> {
            PaymentRequestDto paymentRequestDto = new PaymentRequestDto();
            PaymentRequest pr = new PaymentRequest();
            PaymentRequestDt1 dt1 = new PaymentRequestDt1();
            PaymentAccount pa = new PaymentAccount();
            ArrayList<PaymentRequestDt1> dt1s = new ArrayList<>();

            //封装主数据
            SysUser user = userMapper.selectUserByUserCode(ps.getUserCode());
            pr.setCreateByCode(user.getUserCode());
            pr.setCreateTime(DateUtils.getNowDate());
            pr.setUpdateTime(DateUtils.getNowDate());
            pr.setPaymentRequestNo(paymentRequest.getPaymentRequestNo() + "-" + ps.getId());
            pr.setCompany(Integer.valueOf(ps.getCompany()));
            pr.setStatus(PaymentRequestStatus.FINANCIAL_AUDIT.getCode());
            List<ProcessFlow> processFlows = judgmentAccountingStaff(pr, new ArrayList<>());
            ProcessFlow processFlow1 = processFlows.stream().filter(pf -> Objects.equals(pf.getStatus(), PaymentRequestStatus.FINANCIAL_AUDIT.getCode()))
                    .collect(Collectors.toList()).get(0);
            pr.setSendToCode(processFlow1.getCreateByCode());
            pr.setSendToName(processFlow1.getCreateBy());
            pr.setEmployeeNo(user.getUserCode());
            pr.setApplicant(user.getUserName());
            pr.setDept(user.getDept().getDeptName());
//            pr.setDeptCode(user.getDepartmentCode());
            pr.setApplicationDate(paymentRequest.getApplicationDate());
            pr.setPaymentDate(ps.getPaymentDate());
            pr.setInsteadPayment(0);
            pr.setPaymentType("A");
            pr.setPrepaid(1);
            Long amount = new BigDecimal(ps.getAmount()).multiply(new BigDecimal("100")).longValue();
            pr.setPaymentAmount(String.valueOf(amount));
            pr.setPaymentMethod(1L);
            pr.setDelFlag(1);
//            pr.setSummaryFileName(paymentRequest.getSummaryFileName());
//            pr.setSummaryFile(paymentRequest.getSummaryFile());
            //付款信息
            Ocrd ocrd = selectOcrdByCardCode(ps.getSupplierCode(), ps.getCompany());
            pa.setPayee(ocrd.getCardName());
            pa.setBankName(ocrd.getUBankname());
            pa.setAccountNumber(ocrd.getUBankaccount());
            pa.setAddress(ocrd.getUBankreceaddress());
            pa.setSwiftCode(ocrd.getUBankswift());
            pa.setBankAddress(ocrd.getUBankaddress());
            //封装子数据
            dt1.setContent(ps.getType());
//            dt1.setUnit("1");
            dt1.setQuantity(ps.getQuantity());
            long totalAmount = new BigDecimal(ps.getAmount()).multiply(new BigDecimal("100")).longValue();
//            dt1.setUnitPrice(totalAmount);
            dt1.setTotalAmount(String.valueOf(totalAmount));
            dt1.setCurrency(Integer.valueOf(ps.getCurrency()));
            dt1.setRemark(ps.getRemark());
            dt1s.add(dt1);
            paymentRequestDto.setPaymentRequest(pr);
            paymentRequestDto.setPaymentRequestDt1List(dt1s);
            paymentRequestDto.setPaymentAccount(pa);
            ids.add(createPaymentRequest(paymentRequestDto));
        });

        //封装审批记录
        ids.forEach(id ->{
            processFlowList.forEach(pf ->{
                pf.setOrderId(id);
                pf.setId(null);
            });
            processFlowMapper.batchInsertProcessFlow(processFlowList);
        });

    }

    /**
     * 根据伙伴代码获取业务伙伴信息
     * @param cardCode 业务伙伴代码
     * @return 业务伙伴信息
     */
    public Ocrd selectOcrdByCardCode(String cardCode, String company){
        String url = "http://192.168.5.16:9002/api/codeName/selectOcrdByCardCode?cardCode=" + cardCode + "&company=" + company;
        String res = HttpUtils.sendGet(url,"");
        Ocrd ocrd = JSON.parseObject(res, Ocrd.class);
        return ocrd;
    }

    /**
     * 新增请款单
     * @param paymentRequestDto 请款单信息
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    public Long createPaymentRequest(PaymentRequestDto paymentRequestDto) {
        PaymentRequest paymentRequest = paymentRequestDto.getPaymentRequest();
//        calculateTheAmount(paymentRequestDto);
        handlePaymentAccountInfo(paymentRequestDto);
        paymentRequestMapper.insertPaymentRequest(paymentRequest);
        Long id = paymentRequest.getId();
        for (PaymentRequestDt1 paymentRequestDt1: paymentRequestDto.getPaymentRequestDt1List()) {
            paymentRequestDt1.setCreateTime(DateUtils.getNowDate());
            paymentRequestDt1.setUpdateTime(DateUtils.getNowDate());
            paymentRequestDt1.setRequestId(id);
//            paymentRequestDt1.setDelFlag("0");
            paymentRequestDt1.setDelFlag(String.valueOf(PaymentRequestStatus.SHOW_FLAG.getCode()));
            paymentRequestDt1Mapper.insertPaymentRequestDt1(paymentRequestDt1);
        }

        return id;
    }

    /**
     * 处理付款信息
     * @param paymentRequestDto 单子信息
     */
    public void handlePaymentAccountInfo(PaymentRequestDto paymentRequestDto){
        PaymentRequest paymentRequest = paymentRequestDto.getPaymentRequest();
        PaymentAccount paymentAccount = paymentRequestDto.getPaymentAccount();
        if (StringUtils.isNotNull(paymentAccount)){
            paymentRequest.setPayee(paymentAccount.getPayee());
            paymentRequest.setBankName(paymentAccount.getBankName());
            paymentRequest.setAccountNumber(paymentAccount.getAccountNumber());
            paymentRequest.setAddress(paymentAccount.getAddress());
            paymentRequest.setSwiftCode(paymentAccount.getSwiftCode());
            paymentRequest.setBankAddress(paymentAccount.getBankAddress());
            paymentAccount.setCreateBy(paymentRequest.getCreateByCode());
            List<PaymentAccount> paymentAccounts = paymentAccountMapper.selectPaymentAccountList(paymentAccount);
            if (StringUtils.isEmpty(paymentAccounts)){
                paymentAccount.setCreateTime(DateUtils.getNowDate());
                paymentAccount.setDelFlag(1L);
                paymentAccountMapper.insertPaymentAccount(paymentAccount);
            }
        }
    }


    /**
     * 生成请款单编号
     *
     * @param paymentRequest
     * @return
     */
    public synchronized String primaryKeyGenerationStrategy(PaymentRequest paymentRequest)
    {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowDayTime = dateFormat.format(DateUtils.getNowDate());
        String[] splitRes = nowDayTime.split("-");
        String head = "PR";
        Integer company = paymentRequest.getCompany();
        String com = "NJ";
        switch (company) {
            case 6:
                //南京
                com = "NJ";
                break;
            case 14:
                //上海矽力杰微电子
                com = "PX";
                break;
            case 20:
                //南京香港
                com = "NJHK";
                break;
            default:
        }
        String year = splitRes[0];
        String month = splitRes[1];
        String day = splitRes[2];
        List<PaymentRequest> paymentRequestList = paymentRequestMapper.selectTheLastLine(head + com + year + month + day);
        if (paymentRequestList.isEmpty()) {
            return head + com + year + month + day + "001";
        } else {
            List<Long> longList = new ArrayList<>();
            for (PaymentRequest pr : paymentRequestList) {
                String paymentRequestNo = pr.getPaymentRequestNo();
                Long faNumberLong = null;
                if (paymentRequestNo.contains("-")) {
                    faNumberLong = Long.valueOf(paymentRequestNo.substring(paymentRequestNo.indexOf("-") - 3, paymentRequestNo.indexOf("-")));
                } else {
                    faNumberLong = Long.valueOf(paymentRequestNo.substring(paymentRequestNo.length() - 3));

                }
                longList.add(faNumberLong);
            }
            //排序
            Collections.sort(longList);
            String result = "";
            //如果和当前年月相同就获取最后面3个值
            //获取number加1
            Long aLong = Long.valueOf(ListUtil.getLastElement(longList)) + 1;
            if (aLong < 10L) {
                result = head + com + year + month + day + "00" + aLong.toString();
            } else if (aLong < 100L) {
                result = head + com + year + month + day + "0" + aLong.toString();
            } else {
                result = head + com + year + month + day + aLong.toString();
            }
            return result;
        }
    }

    /**
     * 发送加急处理邮件
     * @param paymentRequestId
     * @return
     */
    public Long sendExpeditedEmail(Long paymentRequestId) {
        PaymentRequest paymentRequest = paymentRequestMapper.selectPaymentRequestById(paymentRequestId);
        String sendToUser = paymentRequest.getSendToCode();
        String titleString = "加急！" + paymentRequest.getApplicant() + "的请款单";
        String url = config.getUrl() + "/redirect/system/paymentRequest/review/"+paymentRequestId;
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为："+ paymentRequest.getPaymentRequestNo());
        html.append("的请款单申请加急审核。<a href='").append(url).append("'>点击链接</a>");
        html.append("</body></html>");
        try {
            //判断是否有多个员工编号
            if (sendToUser.contains(",")) {
                String[] split = sendToUser.split(",");
                for (int i = 0; i < split.length; i++) {
                    sendToUser = userMapper.selectUserByUserCode(split[i]).getEmail();
                    log.info("发送请款单加急处理邮件----------收件人：" + sendToUser);
                    // 给处理者发邮件
                    BootEmail.testSendHtml(sendToUser,titleString,html.toString());
                }
            } else {
                sendToUser = userMapper.selectUserByUserCode(sendToUser).getEmail();
                log.info("发送请款单加急处理邮件----------收件人：" + sendToUser);
                // 给处理者发邮件
                BootEmail.testSendHtml(sendToUser,titleString,html.toString());
            }
        } catch (Exception e) {
            log.error("发送请款单处理邮件失败--------------"+e.getMessage()+
                    "---------请款单编号---------------" + paymentRequest.getPaymentRequestNo());
        }
        return null;
    }
    /**
     * 生成汇总表
     * @param file 汇总表原文件
     * @param paymentDate 付款日期
     * @return 结果
     */
    public AjaxResult summaryTable(MultipartFile file, String paymentDate) {
        ExcelUtil<PaymentSummaryDto> util = new ExcelUtil<PaymentSummaryDto>(PaymentSummaryDto.class);
        List<PaymentSummaryDto> list = null;
        int corpQuantity = 0;
        BigDecimal corpAmount = new BigDecimal("0");

        int hzQuantity = 0;
        BigDecimal hzAmount = new BigDecimal("0");

        int njQuantity = 0;
        BigDecimal njAmount = new BigDecimal("0");

        int njhkQuantity = 0;
        BigDecimal njhkAmount = new BigDecimal("0");

        int njshQuantity = 0;
        BigDecimal njshAmount = new BigDecimal("0");

        int xaQuantity = 0;
        BigDecimal xaAmount = new BigDecimal("0");

        int cdQuantity = 0;
        BigDecimal cdAmount = new BigDecimal("0");

        int hfQuantity = 0;
        BigDecimal hfAmount = new BigDecimal("0");

        int shQuantity = 0;
        BigDecimal shAmount = new BigDecimal("0");

        int twQuantity = 0;
        BigDecimal twAmount = new BigDecimal("0");

        Boolean flag = true;
        try {
            list = util.importExcel(file.getInputStream());
            if (StringUtils.isEmpty(list)) {
                throw new GlobalException("解析失败，请检查数据格式是否正确。");
            }

            for (PaymentSummaryDto ps : list) {
                String company = ps.getCompany();
                String currency = ps.getCurrency();

                //以下是侦错逻辑
                if (StringUtils.isEmpty(ps.getUserCode()) || StringUtils.isNull(userMapper.selectUserByUserCode(ps.getUserCode()))){
                    flag = false;
                    ps.setUserCodeFlag(false);
                }

//                String date = DateUtils.getDate().substring(0, 7) + "-25";
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                if (StringUtils.isNull(ps.getPaymentDate()) || !Objects.equals(ps.getPaymentDate(), sdf.parse(paymentDate))){
                    flag = false;
                    ps.setPaymentDateFlag(false);
                }

                if (StringUtils.isEmpty(company) || ( !Objects.equals(company, "6") && !Objects.equals(company, "20") && !Objects.equals(company, "14"))) {
                    flag = false;
                    ps.setCompanyFlag(false);
                }

                if (StringUtils.isEmpty(ps.getSupplierCode())) {
                    flag = false;
                    ps.setSupplierCodeFlag(false);
                } else {
                    Ocrd ocrd = selectOcrdByCardCode(ps.getSupplierCode(), company);
                    if (StringUtils.isNull(ocrd)){
                        flag = false;
                        ps.setSupplierCodeFlag(false);
                        ps.setSupplierNameFlag(false);
                    } else if (!Objects.equals(ocrd.getCardName(), ps.getSupplierName())){
                        flag = false;
                        ps.setSupplierNameFlag(false);
                    }
                }

                if (StringUtils.isEmpty(currency) || (!Objects.equals(currency, "1") && !Objects.equals(currency, "2")
                        && !Objects.equals(currency, "3") && !Objects.equals(currency, "4") && !Objects.equals(currency, "5")
                        && !Objects.equals(currency, "6") && !Objects.equals(currency, "7") )) {
                    flag = false;
                    ps.setCurrencyFlag(false);
                }

                if (StringUtils.isEmpty(ps.getAmount()) || !ps.getAmount().matches("^[0-9]+(.[0-9]+)?$")) {
                    flag = false;
                    ps.setAmountFlag(false);
                }

                if (StringUtils.isEmpty(ps.getQuantity()) || !ps.getQuantity().matches("^[0-9]*$")){
                    flag = false;
                    ps.setQuantityFlag(false);
                }

                //整理金额
                if (flag){
                    if (Objects.equals(company, "6")) {
                        njQuantity++;
                        BigDecimal amount = new BigDecimal(ps.getAmount());
                        //判断币别
                        Integer currency1 = Integer.valueOf(currency);
                        if (!Objects.equals(currency, 1)) {
                            amount = currencyConverter(amount, currency1);
                        }
                        njAmount = njAmount.add(amount);
                    } else if (Objects.equals(company, "20")) {
                        njhkQuantity++;
                        BigDecimal amount = new BigDecimal(ps.getAmount());
                        //判断币别
                    /*Integer currency1 = Integer.valueOf(currency);
                    if (!Objects.equals(currency, 2)) {
                        amount = currencyConverter(amount, currency1);
                    }*/
                        njhkAmount = njhkAmount.add(amount);
                    } else if (Objects.equals(company, "14")) {
                        njshQuantity++;
                        BigDecimal amount = new BigDecimal(ps.getAmount());
                        //判断币别
                    /*Integer currency1 = Integer.valueOf(currency);
                    if (!Objects.equals(currency, 2)) {
                        amount = currencyConverter(amount, currency1);
                    }*/
                        njshAmount = njshAmount.add(amount);
                    }
                }
            }

            //生成汇总信息
            List<PaymentRequestDt1> dt1s = new ArrayList<>();
            BigDecimal totalAmount = new BigDecimal("0");
            if (flag){
                if (corpQuantity != 0) {
                    PaymentRequestDt1 dt1 = new PaymentRequestDt1();
                    dt1.setContent("Corp");
                    dt1.setQuantity("1");
                    String amount = String.valueOf(corpAmount.multiply(new BigDecimal("100")));
                    dt1.setUnitPrice(amount);
                    dt1.setTotalAmount(amount);
                    dt1.setCurrency(2);
                    dt1.setRemark("共计" + corpQuantity + "笔");
                    dt1s.add(dt1);
                    totalAmount = totalAmount.add(currencyConverter(new BigDecimal(amount), 2));
                }
                if (hzQuantity != 0) {
                    PaymentRequestDt1 dt1 = new PaymentRequestDt1();
                    dt1.setContent("HZ");
                    dt1.setQuantity("1");
                    String amount = String.valueOf(hzAmount.multiply(new BigDecimal("100")).longValue());
                    dt1.setUnitPrice(amount);
                    dt1.setTotalAmount(amount);
                    dt1.setCurrency(1);
                    dt1.setRemark("共计" + hzQuantity + "笔");
                    dt1s.add(dt1);
                    totalAmount = totalAmount.add(new BigDecimal(amount));
                }
                if (njQuantity != 0) {
                    PaymentRequestDt1 dt1 = new PaymentRequestDt1();
                    dt1.setContent("NJ");
                    dt1.setQuantity("1");
                    String amount = String.valueOf(njAmount.multiply(new BigDecimal("100")).longValue());
                    dt1.setUnitPrice(amount);
                    dt1.setTotalAmount(amount);
                    dt1.setCurrency(1);
                    dt1.setRemark("共计" + njQuantity + "笔");
                    dt1s.add(dt1);
                    totalAmount = totalAmount.add(new BigDecimal(amount));
                }
                if (njhkQuantity != 0) {
                    PaymentRequestDt1 dt1 = new PaymentRequestDt1();
                    dt1.setContent("NJHK");
                    dt1.setQuantity("1");
                    String amount = String.valueOf(njhkAmount.multiply(new BigDecimal("100")).longValue());
                    dt1.setUnitPrice(amount);
                    dt1.setTotalAmount(amount);
                    dt1.setCurrency(2);
                    dt1.setRemark("共计" + njhkQuantity + "笔");
                    dt1s.add(dt1);
                    totalAmount = totalAmount.add(currencyConverter(new BigDecimal(amount), 2));
                }
                if (njshQuantity != 0) {
                    PaymentRequestDt1 dt1 = new PaymentRequestDt1();
                    dt1.setContent("NJSH");
                    dt1.setQuantity("1");
                    String amount = String.valueOf(njshAmount.multiply(new BigDecimal("100")).longValue());
                    dt1.setUnitPrice(amount);
                    dt1.setTotalAmount(amount);
                    dt1.setCurrency(1);
                    dt1.setRemark("共计" + njshQuantity + "笔");
                    dt1s.add(dt1);
                    totalAmount = totalAmount.add(new BigDecimal(amount));
                }
                if (xaQuantity != 0) {
                    PaymentRequestDt1 dt1 = new PaymentRequestDt1();
                    dt1.setContent("XA");
                    dt1.setQuantity("1");
                    String amount = String.valueOf(xaAmount.multiply(new BigDecimal("100")).longValue());
                    dt1.setUnitPrice(amount);
                    dt1.setTotalAmount(amount);
                    dt1.setCurrency(1);
                    dt1.setRemark("共计" + xaQuantity + "笔");
                    dt1s.add(dt1);
                    totalAmount = totalAmount.add(new BigDecimal(amount));
                }
                if (cdQuantity != 0) {
                    PaymentRequestDt1 dt1 = new PaymentRequestDt1();
                    dt1.setContent("CD");
                    dt1.setQuantity("1");
                    String amount = String.valueOf(cdAmount.multiply(new BigDecimal("100")).longValue());
                    dt1.setUnitPrice(amount);
                    dt1.setTotalAmount(amount);
                    dt1.setCurrency(1);
                    dt1.setRemark("共计" + cdQuantity + "笔");
                    dt1s.add(dt1);
                    totalAmount = totalAmount.add(new BigDecimal(amount));
                }
                if (hfQuantity != 0) {
                    PaymentRequestDt1 dt1 = new PaymentRequestDt1();
                    dt1.setContent("HF");
                    dt1.setQuantity("1");
                    String amount = String.valueOf(hfAmount.multiply(new BigDecimal("100")).longValue());
                    dt1.setUnitPrice(amount);
                    dt1.setTotalAmount(amount);
                    dt1.setCurrency(1);
                    dt1.setRemark("共计" + hfQuantity + "笔");
                    dt1s.add(dt1);
                    totalAmount = totalAmount.add(new BigDecimal(amount));
                }
                if (shQuantity != 0) {
                    PaymentRequestDt1 dt1 = new PaymentRequestDt1();
                    dt1.setContent("SH");
                    dt1.setQuantity("1");
                    String amount = String.valueOf(shAmount.multiply(new BigDecimal("100")).longValue());
                    dt1.setUnitPrice(amount);
                    dt1.setTotalAmount(amount);
                    dt1.setCurrency(1);
                    dt1.setRemark("共计" + shQuantity + "笔");
                    dt1s.add(dt1);
                    totalAmount = totalAmount.add(new BigDecimal(amount));
                }
                if (twQuantity != 0) {
                    PaymentRequestDt1 dt1 = new PaymentRequestDt1();
                    dt1.setContent("TW");
                    dt1.setQuantity("1");
                    String amount = String.valueOf(twAmount.multiply(new BigDecimal("100")).longValue());
                    dt1.setUnitPrice(amount);
                    dt1.setTotalAmount(amount);
                    dt1.setCurrency(3);
                    dt1.setRemark("共计" + twQuantity + "笔");
                    dt1s.add(dt1);
                    totalAmount = totalAmount.add(currencyConverter(new BigDecimal(amount), 3));
                }
            }

            AjaxResult result = new AjaxResult();
            result.put("code", 0);
            result.put("data", list);
            if (StringUtils.isNotEmpty(dt1s)) {
                result.put("dt1s", dt1s);
                result.put("totalAmount", totalAmount.divide(new BigDecimal("100")).toString());
            }
            result.put("flag", flag);
            return result;
        } catch (GlobalException bs) {
            return AjaxResult.error(bs.getMessage());
        } catch (Exception e) {
            log.error("解析生成支出汇总表失败，错误原因：", e);
            return AjaxResult.error("解析失败，未知异常，请检查模版格式是否正确或在系统工单模块发起“请购/款表单”工单反馈。");
        }
    }

    /**
     * 生成明细表
     * @param file 明细表原文件
     * @return 结果
     */
    public AjaxResult uploadDetailTable(MultipartFile file,String deptCode) {
        ExcelUtil<PaymentRequestDt1Dto> util = new ExcelUtil<PaymentRequestDt1Dto>(PaymentRequestDt1Dto.class);
        List<PaymentRequestDt1Dto> list = null;
        //合计应付金额
        BigDecimal paymentAmount = new BigDecimal("0");
        System.out.println(CacheUtils.get(getCacheName(), getCacheKey("proCode")));
        List<String> projectCode = (List<String>) CacheUtils.get(getCacheName(), getCacheKey("proCode"));

        Boolean flag = true;
        try {
            list = util.importExcel(file.getInputStream());
            if (StringUtils.isEmpty(list)) {
                throw new GlobalException("解析失败，请检查数据格式是否正确。");
            }

            Boolean currencyFlag = false;
            //获取currencyFlag对象currency属性，distinct()去重，count()获取去重后的集合数
            long count = list.stream().map(PaymentRequestDt1Dto::getCurrency).distinct().count();
            //不等于1说明币别不一致
            if (count != 1){
                currencyFlag = true;
            }
            for (PaymentRequestDt1Dto dt1 : list) {
                String currency = dt1.getCurrency();

                //判断部门代码
                if ((deptCode.substring(2,3).equals("2") && !deptCode.substring(2,5).equals("250") &&
                        !deptCode.substring(2,5).equals("240") && !deptCode.substring(2,5).equals("292")) ||
                        deptCode.substring(2,5).equals("400") || deptCode.substring(2,5).equals("420") ||
                        deptCode.substring(2,5).equals("430")) {
                    if (StringUtils.isEmpty(dt1.getProjectCode()) || !projectCode.contains(dt1.getProjectCode())){
                        flag = false;
                        dt1.setProjectCodeFlag(false);
                    }
                }else{
                    if (StringUtils.isNotEmpty(dt1.getProjectCode())){
                        flag = false;
                        dt1.setProjectCodeFlag(false);
                    }
                }

                //判断币别
                if (StringUtils.isEmpty(currency) || currencyFlag ||
                        (!Objects.equals(currency, "1") && !Objects.equals(currency, "2")
                                && !Objects.equals(currency, "3") && !Objects.equals(currency, "4")
                                && !Objects.equals(currency, "5") && !Objects.equals(currency, "6") && !Objects.equals(currency, "7") )) {
                    flag = false;
                    dt1.setCurrencyFlag(false);
                }

                //判断数量
                if (StringUtils.isEmpty(dt1.getQuantity()) || !dt1.getQuantity().matches("^[0-9]*$")){
                    flag = false;
                    dt1.setQuantityFlag(false);
                }

                //判断单价
                if (StringUtils.isNull(dt1.getUnitPrice()) || !dt1.getUnitPrice().matches("^[0-9]+(.[0-9]+)?$")) {
                    flag = false;
                    dt1.setUnitPriceFlag(false);
                }

                //判断总金额
                String totalAmount = "0";
                if (StringUtils.isNull(dt1.getTotalAmount()) || !dt1.getTotalAmount().matches("^[0-9]+(.[0-9]+)?$")) {
                    flag = false;
                    dt1.setTotalAmountFlag(false);
                } else {
                    totalAmount = dt1.getTotalAmount();
                    paymentAmount = paymentAmount.add(new BigDecimal(totalAmount));
                }
                //判断总金额计算是否正确
                if (dt1.getQuantityFlag() && dt1.getUnitPriceFlag()){
                    BigDecimal multiply = new BigDecimal(dt1.getQuantity()).multiply(new BigDecimal(dt1.getUnitPrice()));
                    float v = multiply.subtract(new BigDecimal(totalAmount)).abs().floatValue();
                    //允许1差值
                    if (v > 1){
                        flag = false;
                        dt1.setTotalAmountFlag(false);
                    }
                }

            }

            AjaxResult result = new AjaxResult();
            result.put("code", 0);
            result.put("data", list);
            result.put("flag", flag);
            result.put("paymentAmount", paymentAmount);
            return result;
        } catch (GlobalException bs) {
            return AjaxResult.error(bs.getMessage());
        } catch (Exception e) {
            log.error("解析生成明细表失败，错误原因：", e);
            return AjaxResult.error("解析失败，未知异常，请检查模版格式是否正确或在系统工单模块发起“请购/款表单”工单反馈。");
        }
    }

    /**
     * 计算单子金额
     * @param  paymentRequest 单子信息
     * @return
     */
    public PaymentRequest calculateTheAmount(PaymentRequest paymentRequest){
        //总金额
        BigDecimal totalAmount = new BigDecimal("0");
        BigDecimal multiple = new BigDecimal("100");
        for (PaymentRequestDt1 dt1 : paymentRequest.getPaymentRequestDt1List()) {
            //数量
            BigDecimal quantity = new BigDecimal(dt1.getQuantity());
            //单价(先转成正常的金额, 最后再扩大100倍)
            BigDecimal unitPrice = new BigDecimal(dt1.getUnitPrice()).divide(multiple);
            //计算行金额
            BigDecimal amount = (quantity.multiply(unitPrice)).setScale(2, BigDecimal.ROUND_HALF_UP);
            dt1.setTotalAmount(String.valueOf(amount.multiply(multiple)));
            //计算总金额
            if (Objects.equals(paymentRequest.getPrepaid(), 2)){
                //预付比例
                BigDecimal ratio = new BigDecimal(dt1.getRatio()).divide(new BigDecimal("10000"));
                totalAmount = amount.multiply(ratio).add(totalAmount).setScale(2, BigDecimal.ROUND_HALF_UP);
            }else {
                totalAmount = amount.add(totalAmount);
            }
        }
        paymentRequest.setPaymentAmount(String.valueOf(totalAmount.multiply(multiple)));
        return paymentRequest;
    }


}
