package com.ruoyi.system.component.requisition;

import com.alibaba.fastjson.JSON;
import com.ruoyi.common.config.ServerConfig;
import com.ruoyi.common.constant.RequisitionConstant;
import com.ruoyi.common.core.domain.CxSelect;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.enums.OrderTypes;
import com.ruoyi.common.enums.RequisitionStatus;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.common.exception.ServiceException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.email.BootEmail;
import com.ruoyi.system.domain.ProcessFlow;
import com.ruoyi.system.domain.requisition.Requisition;
import com.ruoyi.system.domain.requisition.RequisitionDt1;
import com.ruoyi.system.domain.requisition.RequisitionDt2;
import com.ruoyi.system.mapper.PaymentAccountMapper;
import com.ruoyi.system.mapper.ProcessFlowMapper;
import com.ruoyi.system.mapper.SysUserMapper;
import com.ruoyi.system.mapper.requisition.RequisitionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

/**
 * 请购单 Component
 *
 * @author SKaiL
 * @version 1.0
 * @date 2022/9/27 9:40
 */
@Component
public class RequisitionComponent
{
    private static final Logger log = LoggerFactory.getLogger(RequisitionComponent.class);

    private static final String ROLE_SUPERVISOR = "主管";
    private static final String ROLE_MANAGER = "经理";
    private static final String ROLE_DIRECTOR = "总监";
    private static final String ROLE_EXECUTIVE_DIRECTOR = "执行总监";
    private static final String ROLE_GENERAL_MANAGER = "总经理";

    @Autowired
    private RequisitionMapper requisitionMapper;

    @Autowired
    private SysUserMapper userMapper;

    @Autowired
    private PaymentAccountMapper paymentAccountMapper;

    @Autowired
    private ProcessFlowMapper processFlowMapper;

    @Autowired
    private ServerConfig config;
    
    @Autowired
    private RequisitionConstant requisitionConstant;

    public Requisition insertCheck(Requisition requisition)
    {
        //是否必填厂商比议价标识
        Boolean requiredFlag = false;
        Boolean dt1Flag = false;
        Boolean dt2Flag = false;
        List<RequisitionDt1> requisitionDt1List = new ArrayList<>();
        List<RequisitionDt2> requisitionDt2List = new ArrayList<>();
        List<RequisitionDt1> requisitionDt1s = requisition.getRequisitionDt1List();
        List<RequisitionDt2> requisitionDt2s = requisition.getRequisitionDt2List();
        Integer company = requisition.getCompany();
        Integer assetManagement = requisition.getAssetManagement();
        if (Objects.equals(company, 20)) {
            requiredFlag = true;
        } else if (Objects.equals(company, 12)) {
            if (!Objects.equals(assetManagement, 2) && !Objects.equals(assetManagement, 12)) {
                requiredFlag = true;
            }
        } else if (Objects.equals(company, 6)) {
            if (Objects.equals(assetManagement, 5) || Objects.equals(assetManagement, 11)) {
                requiredFlag = true;
            }
        } else if (Objects.equals(company, 14)) {
            if (Objects.equals(assetManagement, 5) || Objects.equals(assetManagement, 11) || Objects.equals(assetManagement, 6) ||
                    Objects.equals(assetManagement, 1) || Objects.equals(assetManagement, 4)) {
                requiredFlag = true;
            }
        }
        //合计金额计算
        for (RequisitionDt1 requisitionDt1 : requisitionDt1s) {
            //过滤不完整的数据
            if (StringUtils.isNotEmpty(requisitionDt1.getProjectName()) &&
                    StringUtils.isNotEmpty(requisitionDt1.getSpecifications()) &&
                    StringUtils.isNotNull(requisitionDt1.getQuantity()) &&
                    StringUtils.isNotNull(requisitionDt1.getUnitPrice()) &&
                    StringUtils.isNotNull(requisitionDt1.getAmount())) {
                dt1Flag = true;
                requisitionDt1List.add(requisitionDt1);
            }
        }
        for (RequisitionDt2 requisitionDt2 : requisitionDt2s) {
            //过滤不完整的数据
            if (StringUtils.isNotEmpty(requisitionDt2.getSuppliersName()) &&
                    StringUtils.isNotNull(requisitionDt2.getCoin()) &&
                    StringUtils.isNotEmpty(requisitionDt2.getUntaxedUnitPrice()) &&
                    StringUtils.isNotNull(requisitionDt2.getTaxRate()) &&
                    StringUtils.isNotEmpty(requisitionDt2.getTotalAmount())) {
                dt2Flag = true;
                requisitionDt2List.add(requisitionDt2);
            }
        }
        if (requiredFlag && !dt2Flag) {
            throw new ServiceException("当前选择的资产管理类别需要填写厂商比议价说明并选中一个厂商，请补充完整后重试。");
        }
        requisition.setRequisitionDt1List(requisitionDt1List);
        requisition.setRequisitionDt2List(requisitionDt2List);
        return requisition;
    }

    public synchronized String primaryKeyGenerationStrategy(Requisition requisition){
        Integer company = requisition.getCompany();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String nowdayTime = dateFormat.format(DateUtils.getNowDate());
        String[] splitRes = nowdayTime.split("-");
        String head = "PRNJ";
        switch (company){
            case 6:
                //南京
                head = "PRNJ";
                break;
            case 14:
                //上海矽力杰微电子
                head = "PRPX";
                break;
            case 20:
                //南京香港
                head = "PRNJHK";
                break;
            default:
        }
        String year = splitRes[0];
        String month = splitRes[1];
        List<Requisition> requisitionList = requisitionMapper.selectTheLastLine(head + year + month);
        if(requisitionList.isEmpty()){
            return head + year + month + "001";
        }else{
            List<Long> longList = new ArrayList<>();
            for(Requisition r:requisitionList){
                String requisitionNo = r.getRequisitionNo();
                Long faNumberLong = Long.valueOf(requisitionNo.substring(requisitionNo.length() - 3, requisitionNo.length()));
                longList.add(faNumberLong);
            }
            //排序
            Collections.sort(longList);
            String result = "";
            //如果和当前年月相同就获取最后面3个值
            //获取number加1
            Long aLong = Long.valueOf(longList.size() - 1) + 1;
            if(aLong < 10L){
                result = head + year + month + "00" + aLong.toString();
            }else if(aLong < 100L){
                result = head + year + month + "0" + aLong.toString();
            }else {
                result = head + year + month + aLong.toString();
            }
            return result;
        }
    }

    /**
     * 计算总金额
     * @param requisition 请购单
     * @return
     */
    public String getTotalAmount(Requisition requisition) {
        //总金额
        BigDecimal totalAmount = new BigDecimal("0");
        for (RequisitionDt2 requisitionDt2 : requisition.getRequisitionDt2List()) {
            if (StringUtils.isNotNull(requisitionDt2.getSuppliers()) &&
                    requisitionDt2.getSuppliers().equals(1) &&
                    requisitionDt2.getTotalAmount() != null) {
                BigDecimal a = new BigDecimal(requisitionDt2.getTotalAmount());
                totalAmount = currencyConverter(a, requisitionDt2.getCoin());
            }
        }
        return totalAmount.toString();
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
        } else {
            return amount;
        }
        //currencyStr转美元汇率
        String currency = paymentAccountMapper.selectCurrency(currencyStr);
        BigDecimal rate = new BigDecimal(currency);
        BigDecimal totalAmount = null;
        if (Objects.equals(currencyType, 2)){
            //美元特殊处理
            totalAmount = amount.multiply(rate).setScale(2, BigDecimal.ROUND_HALF_UP);
        } else {
            //美元转人民币汇率
            String currencys = paymentAccountMapper.selectCurrency("USD");
            BigDecimal rates = new BigDecimal(currencys);
            totalAmount = amount.multiply(rate).multiply(rates).setScale(2, BigDecimal.ROUND_HALF_UP);
        }
        return totalAmount;
    }

    /**
     * 查找审核主管
     * @param sysUser 当前用户
     * @param totalAmount 单子金额
     * @param list 保存审核人员信息List
     * @return 保存审核人员信息List
     */
    public List<ProcessFlow> getRequestAuditors(SysUser sysUser, String totalAmount , List<ProcessFlow> list)
    {
        String sendToCode = "";
        //查找上级主管
        if (StringUtils.isNotEmpty(sysUser.getPurchasesid())) {
            //先用请款通用代签主管
            sendToCode = sysUser.getPurchasesid();
        } else {
            sendToCode = sysUser.getZgsid();
        }
        if (Objects.equals(sysUser.getZgsid(), "/") || Objects.equals(sysUser.getZgsid(), sysUser.getUserCode())
                || StringUtils.isEmpty(sendToCode)) {
            throw new ServiceException("查找上级主管出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
        }
        //主管信息
        SysUser user = userMapper.selectUserByUserCode(sendToCode);
        //主管职级
        if (Objects.isNull(user)) {
            log.error("获取员工主管有误，问题员工编号：{}", sendToCode);
            throw new GlobalException("查找主管出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");
        }
        //主管职级
        String jobCode = user.getJobCode();
        //主管职称
        String jobName = ROLE_SUPERVISOR;
        //主管金额
        String amount = null;
        //是否有审核权限
        Boolean flag = true;
        if (Objects.equals(jobCode, "S7") || Objects.equals(jobCode, "S8")) {
            jobName = ROLE_MANAGER;
            amount = requisitionConstant.MAX_4;
        } else if (Objects.equals(jobCode, "S9") || Objects.equals(jobCode, "S10")) {
            jobName = ROLE_DIRECTOR;
            amount = requisitionConstant.MAX_3;
        } else if (Objects.equals(jobCode, "S11") || Objects.equals(jobCode, "S12") || Objects.equals(jobCode, "S13")) {
            jobName = ROLE_EXECUTIVE_DIRECTOR;
            amount = requisitionConstant.MAX_2;
        } else if (Objects.equals(jobCode, "S14")) {
            jobName = ROLE_GENERAL_MANAGER;
            amount = requisitionConstant.MAX_1;
        } else {
            flag = false;
        }

        ProcessFlow processFlow = new ProcessFlow();
        processFlow.setStatus(judgingStatusByRole(jobName));
        processFlow.setCreateByCode(user.getUserCode());
        processFlow.setCreateBy(user.getUserName());
        list.add(processFlow);

        //判断是否有权限
        if (flag) {
            //判断金额是否满足
            if (new BigDecimal(amount).compareTo(new BigDecimal(totalAmount)) < 0) {
                //金额不满足继续查找上级
                list = getRequestAuditors(user, totalAmount, list);
            }
        } else {
            //不在名单继续查找上级
            list = getRequestAuditors(user, totalAmount, list);
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
                status = RequisitionStatus.SUPERVISOR.getCode();
                break;
            case "经理":
                status = RequisitionStatus.MANAGER.getCode();
                break;
            case "总监":
                status = RequisitionStatus.DIRECTOR.getCode();
                break;
            case "执行总监":
                status = RequisitionStatus.EXECUTIVE_DIRECTOR.getCode();
                break;
            case "资产管理员":
                status = RequisitionStatus.ASSET_MANAGER.getCode();
                break;
            case "采购代表":
                status = RequisitionStatus.DB_OFFER.getCode();
                break;
            case "新员工入职HR":
                status = RequisitionStatus.NEW_STAFF_ENDORSEMENT.getCode();
                break;
            case "HR":
                status = RequisitionStatus.HR_PRINCIPAL.getCode();
                break;
            case "预算管理员":
                status = RequisitionStatus.FINANCE_ASSETS.getCode();
                break;
            case "预算经理":
                status = RequisitionStatus.BUDGET_MANAGER.getCode();
                break;
            case "总账":
                status = RequisitionStatus.ASSISTANT_ACCOUNTING.getCode();
                break;
            case "财务经理":
                status = RequisitionStatus.ACCOUNTING.getCode();
                break;
            case "总经理":
                status = RequisitionStatus.GENERAL_MANAGER.getCode();
                break;
            case "前加签":
                status = RequisitionStatus.SIGN_BEFORE.getCode();
                break;
            case "后加签":
                status = RequisitionStatus.SIGN_AFTER.getCode();
                break;
            default:

        }
        return status;
    }

    /**
     * 查找会计审核人员
     * @param requisition 请购单
     * @param list 审核人员
     * @return 审核人员
     */
    public List<ProcessFlow> judgmentAccountingStaff(Requisition requisition, List<ProcessFlow> list){
        //资产管理员
        String assetManagerCode = "";
        String assetManagerName = "";
        //采购代表
        String procurementRepresentativeCode = "";
        String procurementRepresentativeName = "";
        //新员工入职HR
        String newEmployeeOnboardingHRCode = "";
        String newEmployeeOnboardingHRName = "";
        //HR老大
        String hrBossCode = "";
        String hrBossName = "";
        //预算管理员
        String budgetStaffCode = "";
        String budgetStaffName = "";
        //预算经理
        String budgetManagerCode = "";
        String budgetManagerName = "";
        //总账
        String generalLedgerCode = "";
        String generalLedgerName = "";
        //财务经理
        String financialManagerCode = "";
        String financialManagerName = "";
        Integer assetManagement = requisition.getAssetManagement();
        Integer company = requisition.getCompany();
        switch (assetManagement) {
            case 1: //测试设备类
                //预算管理员
                budgetStaffCode = requisitionConstant.financeAssets;
                budgetStaffName = requisitionConstant.financeAssetsName;
                //预算经理
                budgetManagerName = requisitionConstant.budgetManagerName;
                budgetManagerCode = requisitionConstant.budgetManager;

                if (Objects.equals(company, 14) || Objects.equals(company, 20)) {
                    //上海矽力杰微电子/南京香港 未设置资产管理员 采购代表
                }else {
                    //南京 采购代表
                    procurementRepresentativeName = requisitionConstant.testNJDeviceName;
                    procurementRepresentativeCode = requisitionConstant.testNJDevice;
                }
                break;
            case 2: //IT桌面类
            case 12: //个人电脑
                //预算管理员
                budgetStaffCode = requisitionConstant.financeAssets;
                budgetStaffName = requisitionConstant.financeAssetsName;
                //预算经理
                budgetManagerName = requisitionConstant.budgetManagerName;
                budgetManagerCode = requisitionConstant.budgetManager;

               if (Objects.equals(company, 6)) {
                    //南京赵晶
                    //采购代表
                   procurementRepresentativeName = requisitionConstant.itNjDeviceName;
                   procurementRepresentativeCode = requisitionConstant.itNjDevice;
                } else if (Objects.equals(company, 14)) {
                    //上海矽力杰微电子
                    //采购代表
//                   procurementRepresentativeName = requisitionConstant.engineeringNJName;
//                   procurementRepresentativeCode = requisitionConstant.engineeringNJ;
                } else if (Objects.equals(company, 20)) {
                    //南京香港
                    //资产管理员 无
                    //采购代表 无
                }
                break;
            case 3: //办公家具类
                //预算管理员
                budgetStaffCode = requisitionConstant.financeAssets;
                budgetStaffName = requisitionConstant.financeAssetsName;
                //预算经理
                budgetManagerName = requisitionConstant.budgetManagerName;
                budgetManagerCode = requisitionConstant.budgetManager;

                if (Objects.equals(company, 20)) {
                    //南京香港 未设置资产管理员采购代表
                }else if (Objects.equals(company, 6)) {
                    //南京
                    //采购代表
                    procurementRepresentativeName = requisitionConstant.deptNJDeviceName;
                    procurementRepresentativeCode = requisitionConstant.deptNJDevice;
                }else if (Objects.equals(company, 14)) {
                    //上海矽力杰微电子
                    //采购代表
//                    procurementRepresentativeName = requisitionConstant.engineeringNJName;
//                    procurementRepresentativeCode = requisitionConstant.engineeringNJ;
                }
                break;
            case 4: //实验室固资类
                //预算管理员
                budgetStaffCode = requisitionConstant.financeAssets;
                budgetStaffName = requisitionConstant.financeAssetsName;
                //预算经理
                budgetManagerName = requisitionConstant.budgetManagerName;
                budgetManagerCode = requisitionConstant.budgetManager;

                if (Objects.equals(company,14) || Objects.equals(company, 20)) {
                    //台湾\上海矽力杰微电子\南京香港 未设置资产管理员采购代表
                } else if (Objects.equals(company, 6)) {
                    //南京
                    //采购代表
                    procurementRepresentativeName = requisitionConstant.deptNJDeviceName;
                    procurementRepresentativeCode = requisitionConstant.deptNJDevice;
                }
                break;
            case 5: //非销售请购 无
                //采购代表
                procurementRepresentativeName = requisitionConstant.testNJDeviceName;
                procurementRepresentativeCode = requisitionConstant.testNJDevice;
                break;
            case 11:// 固定资产 无
                //预算管理员
                budgetStaffCode = requisitionConstant.financeAssets;
                budgetStaffName = requisitionConstant.financeAssetsName;
                //预算经理
                budgetManagerName = requisitionConstant.budgetManagerName;
                budgetManagerCode = requisitionConstant.budgetManager;
                //采购代表
                procurementRepresentativeName = requisitionConstant.testNJDeviceName;
                procurementRepresentativeCode = requisitionConstant.testNJDevice;
                break;
            case 6: //元器件
                if (Objects.equals(company,14) || Objects.equals(company, 20)) {
                    //上海矽力杰微电子/南京香港 未设置资产管理员采购代表
                } else if (Objects.equals(company, 6)) {
                    //南京
                    //采购代表
                    procurementRepresentativeName = requisitionConstant.testNJDeviceName;
                    procurementRepresentativeCode = requisitionConstant.testNJDevice;
                }
                break;
            case 7://办公家具
            case 8://耗材类
                if (Objects.equals(company, 20)) {
                    //南京香港未设置资产管理员 采购代表
                }else if (Objects.equals(company,14)) {
                    //上海矽力杰微电子
//                    procurementRepresentativeName = requisitionConstant.engineeringNJName;
//                    procurementRepresentativeCode = requisitionConstant.engineeringNJ;
                }else if (Objects.equals(company, 6)) {
                    //南京
                    //采购代表
                    procurementRepresentativeName = requisitionConstant.deptNJDeviceName;
                    procurementRepresentativeCode = requisitionConstant.deptNJDevice;
                }
                break;
            case 9://工程类
                if (Objects.equals(company, 20) || Objects.equals(company,14)) {
                    //南京香港 上海矽力杰微电子未设置资产管理员 采购代表
                } else if (Objects.equals(company, 6)) {
                    //南京
                    //采购代表
                    procurementRepresentativeName = requisitionConstant.engineeringNJName;
                    procurementRepresentativeCode = requisitionConstant.engineeringNJ;
                }
                break;
            case 10://工程类
                //预算管理员
                budgetStaffCode = requisitionConstant.financeAssets;
                budgetStaffName = requisitionConstant.financeAssetsName;
                //预算经理
                budgetManagerName = requisitionConstant.budgetManagerName;
                budgetManagerCode = requisitionConstant.budgetManager;

                if (Objects.equals(company,20) || Objects.equals(company,14)) {
                    //南京香港 上海矽力杰微电子未设置资产管理员 采购代表
                } else if (Objects.equals(company, 6)) {
                    //南京 采购代表
                    procurementRepresentativeName = requisitionConstant.engineeringNJName;
                    procurementRepresentativeCode = requisitionConstant.engineeringNJ;
                }
                break;
            default:
                throw new ServiceException("查找采购代表出错，请保存截图，在系统工单模块发起“请购/款表单”工单反馈。");

        }

        //查找总账
        if (Objects.equals(company, 14) || Objects.equals(company, 20)){
            //上海矽力杰微电子/南京香港
            generalLedgerCode = requisitionConstant.njXgZg;
            generalLedgerName = requisitionConstant.njXgZgName;
        } else {//南京
            generalLedgerCode = requisitionConstant.njZg;
            generalLedgerName = requisitionConstant.njZgName;
        }
        //财务经理
        financialManagerCode = requisitionConstant.njJl;
        financialManagerName = requisitionConstant.njJlName;
        //资产管理员
        if (StringUtils.isNotEmpty(assetManagerCode)){
            ProcessFlow processFlow = new ProcessFlow();
            processFlow.setStatus(RequisitionStatus.ASSET_MANAGER.getCode());
            processFlow.setCreateByCode(assetManagerCode);
            processFlow.setCreateBy(assetManagerName);
            list.add(processFlow);
        }

        //采购代表
        if (StringUtils.isNotEmpty(procurementRepresentativeCode)){
            ProcessFlow processFlow = new ProcessFlow();
            processFlow.setStatus(RequisitionStatus.DB_OFFER.getCode());
            processFlow.setCreateByCode(procurementRepresentativeCode);
            processFlow.setCreateBy(procurementRepresentativeName);
            list.add(processFlow);
        }

        //预算管理员
        if (StringUtils.isNotEmpty(budgetStaffCode)){
            ProcessFlow processFlow = new ProcessFlow();
            processFlow.setStatus(RequisitionStatus.FINANCE_ASSETS.getCode());
            processFlow.setCreateByCode(budgetStaffCode);
            processFlow.setCreateBy(budgetStaffName);
            list.add(processFlow);
        }

        //预算经理
        if (StringUtils.isNotEmpty(budgetManagerCode)){
            ProcessFlow processFlow = new ProcessFlow();
            processFlow.setStatus(RequisitionStatus.BUDGET_MANAGER.getCode());
            processFlow.setCreateByCode(budgetManagerCode);
            processFlow.setCreateBy(budgetManagerName);
            list.add(processFlow);
        }

        //新员工入职HR
        if (StringUtils.isNotEmpty(newEmployeeOnboardingHRCode)){
            ProcessFlow processFlow = new ProcessFlow();
            processFlow.setStatus(RequisitionStatus.NEW_STAFF.getCode());
            processFlow.setCreateByCode(newEmployeeOnboardingHRCode);
            processFlow.setCreateBy(newEmployeeOnboardingHRName);
            list.add(processFlow);
        }

        //HR
        if (StringUtils.isNotEmpty(hrBossCode)){
            ProcessFlow processFlow = new ProcessFlow();
            processFlow.setStatus(RequisitionStatus.HR_PRINCIPAL.getCode());
            processFlow.setCreateByCode(hrBossCode);
            processFlow.setCreateBy(hrBossName);
            list.add(processFlow);
        }

        //总账
        if (StringUtils.isNotEmpty(generalLedgerCode)){
            ProcessFlow processFlow = new ProcessFlow();
            processFlow.setStatus(RequisitionStatus.ASSISTANT_ACCOUNTING.getCode());
            processFlow.setCreateByCode(generalLedgerCode);
            processFlow.setCreateBy(generalLedgerName);
            list.add(processFlow);
        }

        //财务经理
        if (StringUtils.isNotEmpty(financialManagerCode)){
            ProcessFlow processFlow = new ProcessFlow();
            processFlow.setStatus(RequisitionStatus.ACCOUNTING.getCode());
            processFlow.setCreateByCode(financialManagerCode);
            processFlow.setCreateBy(financialManagerName);
            list.add(processFlow);
        }
        return list;
    }

    /**
     * 发送驳回邮件
     * @param requisition 请购单id
     * @param userCode 收件人
     * @return
     */
    public void sendEmailReject(Requisition requisition,String userCode)
    {
        Long id = requisition.getId();
        SysUser user = userMapper.selectUserByUserCode(userCode);
        String sendToUser = user.getEmail();
        String titleString = requisition.getUserName() + "请购单驳回";
        String text = "编号为：" + requisition.getRequisitionNo() + "的请购单，请购项目为" + getProjectName(requisition) + "的请购单已经被驳回，请重新申请。";
        try {
            // 给处理者发邮件
            log.info("发送请购单驳回邮件----------收件人：" + sendToUser);
            BootEmail.sendSimpleMail(sendToUser, titleString, text);
        } catch (Exception e) {
            log.error("发送请购单撤回邮件失败--------------" + e.getMessage() + "--------------------请购单ID:" + id);
        }
    }

    /**
     * 发送撤回邮件
     * @param requisition 请购单ID
     * @param userCode 收件人
     * @return
     */
    public void sendEmailRecall(Requisition requisition,String userCode)
    {
        Long id = requisition.getId();
        SysUser user = userMapper.selectUserByUserCode(userCode);
        String sendToUser = user.getEmail();
        String titleString = requisition.getUserName() + "请购单撤回";
        String text = "编号为：" + requisition.getRequisitionNo() + "的请购单,请购项目为" + getProjectName(requisition) + ",已经被撤回，请重新编辑。";
        try {
            log.info("发送请购单撤回邮件----------收件人：" + sendToUser);
            // 给处理者发邮件
            BootEmail.sendSimpleMail(sendToUser, titleString, text);
        } catch (Exception e) {
            log.error("发送请购单撤回邮件失败--------------" + e.getMessage() + "--------------------请购单ID:" + id);
        }
    }

    /**
     * 发送保留邮件
     * @param requisition 请购单
     * @param userCode 收件人
     * @return
     */
    public void sendEmailRetain(Requisition requisition,String userCode){
        Long id = requisition.getId();
        SysUser user = userMapper.selectUserByUserCode(userCode);
        String sendToUser = user.getEmail();
        String titleString = requisition.getUserName() +"请购单保留";
        String text = "编号为："+requisition.getRequisitionNo()+"的请购单，请购项目为"+ getProjectName(requisition) +"的请购单已经被保留。";
        try {
            // 给处理者发邮件
            log.info("发送请购单保留邮件----------收件人：" + sendToUser);
            BootEmail.sendSimpleMail(sendToUser,titleString,text);
        } catch (Exception e) {
            log.error("发送请购单保留邮件失败--------------"+e.getMessage()+"--------------------请购单ID:" + id);
        }
    }

    /**
     * 发送处理邮件
     * @param requisition 请购单
     * @param userCode 收件人
     * @return
     */
    public void sendEmailAudit(Requisition requisition,String userCode)
    {
        Long id = requisition.getId();
        SysUser user = userMapper.selectUserByUserCode(userCode);
        String sendToUser = user.getEmail();
        String titleString = requisition.getUserName() + "的请购单";
        String url = config.getUrl() + "/redirect/system/requisition/review/" + id;
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为：" + requisition.getRequisitionNo() + "的请购单,请购项目为").append(getProjectName(requisition));
        html.append("的请购单请去审核。<a href='").append(url).append("'>点击链接</a>");
        html.append("</body></html>");
        try {
            // 给处理者发邮件
            log.info("发送请购单处理邮件----------收件人：" + sendToUser);
            BootEmail.testSendHtml(sendToUser, titleString, html.toString());
        } catch (Exception e) {
            log.error("发送请购单处理邮件失败----------------" + e.getMessage() + "------------------请购单ID:" + id);
        }
    }

    /**
     * 发送完成邮件
     * @param requisition 请购单
     * @param userCode 收件人
     * @return
     */
    public void sendEmailComplete(Requisition requisition,String userCode)
    {
        Long id = requisition.getId();
        //查询已审核人员列表
        ProcessFlow processFlow = new ProcessFlow();
        processFlow.setOrderId(id);
        processFlow.setOrderType(OrderTypes.REQUISITION.getCode());
        List<ProcessFlow> list = processFlowMapper.selectProcessFlowList(processFlow);
        boolean flag = false;
        String c = null;
        Iterator<ProcessFlow> iterator = list.iterator();
        while (iterator.hasNext()) {
            ProcessFlow processFlow1 = iterator.next();
            //判断是否有资产管理员、采购代表和预算管理员
            if (processFlow1.getStatus() == 13 || processFlow1.getStatus() == 20 || processFlow1.getStatus() == 14) {
                //临时判断,之前的单子没有保存createById字段
                if (processFlow1.getCreateById() != null) {
                    flag = true;
                    Long createById = processFlow1.getCreateById();
                    if (StringUtils.isEmpty(c)) {
                        c = userMapper.selectUserById(createById).getEmail();
                    } else {
                        c += "," + userMapper.selectUserById(createById).getEmail();
                    }
                }
            }
        }
        SysUser user = userMapper.selectUserByUserCode(userCode);
        String sendToUser = user.getEmail();
        String url = config.getUrl() + "/redirect/system/requisition/detail/" + id + "/1";
        String titleString = requisition.getUserName() + "请购单已经完成";
        StringBuffer html = new StringBuffer("<!DOCTYPE html><html lang=\"en\"><head><meta charset=\"UTF-8\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">");
        html.append("<title>Document</title></head><body>编号为：" + requisition.getRequisitionNo() + "的请购单已完成。<a href='").append(url).append("'>查看详情</a>");
        html.append("</body></html>");
        try {
            log.info("发送请购单完成邮件----------收件人：" + sendToUser);
            //给处理者发邮件
            if (flag) {
                //抄送给采购代表和预算管理员
                BootEmail.sendMailWithCC(sendToUser, titleString, html.toString(), c.split(","));
            } else {
                BootEmail.testSendHtml(sendToUser, titleString, html.toString());
            }
        } catch (Exception e) {
            log.error("发送请购单完成邮件失败--------------" + e.getMessage() + "--------------------请购单ID:" + id);
        }
    }

    public String getProjectName(Requisition Requisition){
        String projectNames = Requisition.getRequisitionDt1List().stream().map(RequisitionDt1::getProjectName).collect(Collectors.joining(","));
        return projectNames;
    }

    /**
     * 计算审批进度
     *
     * @param requisition 原单信息
     */
    public void approvalProgress (Requisition requisition)
    {
        if (requisition.getStatus() == 1 || requisition.getStatus() == 7) {
            requisition.setProgressRate("0");
        } else {
            //进度
            String progressRate = requisition.getProgressRate();
            List<String> progress = Arrays.asList(progressRate.split("/"));
            BigDecimal a = new BigDecimal(progress.get(0));
            BigDecimal b = new BigDecimal(progress.get(1));
            String progressbar = a.divide(b, 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("100"))
                    .setScale(0, BigDecimal.ROUND_HALF_UP).toString();
            requisition.setProgressRate(progressbar);
        }
    }

    //todo 临时过渡,后期删除
    //二级菜单下拉
    public String buildData(){
        CxSelect cxSelectBattery = new CxSelect();
        cxSelectBattery.setN("battery");
        cxSelectBattery.setV("battery");
        CxSelect cxSelectBatteryBat = new CxSelect();
        cxSelectBatteryBat.setN("battery-bat");
        cxSelectBatteryBat.setV("battery-bat");
        CxSelect cxSelectBatteryBms = new CxSelect();
        cxSelectBatteryBms.setN("battery-bms");
        cxSelectBatteryBms.setV("battery-bms");
        CxSelect cxSelectBatteryGauge = new CxSelect();
        cxSelectBatteryGauge.setN("battery-gauge");
        cxSelectBatteryGauge.setV("battery-gauge");
        CxSelect cxSelectBatteryWireless = new CxSelect();
        cxSelectBatteryWireless.setN("battery-wireless");
        cxSelectBatteryWireless.setV("battery-wireless");
        List<CxSelect> batteryList = new ArrayList<CxSelect>();
        batteryList.add(cxSelectBatteryBat);
        batteryList.add(cxSelectBatteryBms);
        batteryList.add(cxSelectBatteryGauge);
        batteryList.add(cxSelectBatteryWireless);
        cxSelectBattery.setS(batteryList);

        CxSelect cxSelectPlatform = new CxSelect();
        cxSelectPlatform.setN("platform");
        cxSelectPlatform.setV("platform");
        CxSelect cxSelectPlatformOthers = new CxSelect();
        cxSelectPlatformOthers.setN("Platform-others");
        cxSelectPlatformOthers.setV("Platform-others");
        CxSelect cxSelectPlatformDigitalPower = new CxSelect();
        cxSelectPlatformDigitalPower.setN("Platform-digital power");
        cxSelectPlatformDigitalPower.setV("Platform-digital power");
        List<CxSelect> platformList = new ArrayList<CxSelect>();
        platformList.add(cxSelectPlatformOthers);
        platformList.add(cxSelectPlatformDigitalPower);
        cxSelectPlatform.setS(platformList);


        CxSelect cxSelectSignalChain = new CxSelect();
        cxSelectSignalChain.setN("Signal Chain");
        cxSelectSignalChain.setV("Signal Chain");
        CxSelect cxSelectAdda = new CxSelect();
        cxSelectAdda.setN("Signal Chain-adda");
        cxSelectAdda.setV("Signal Chain-adda");
        CxSelect cxSelectAmplifier = new CxSelect();
        cxSelectAmplifier.setN("Signal Chain-amplifier");
        cxSelectAmplifier.setV("Signal Chain-amplifier");
        CxSelect cxSelectAudio = new CxSelect();
        cxSelectAudio.setN("Signal Chain-audio");
        cxSelectAudio.setV("Signal Chain-audio");
        CxSelect cxSelectBlerf = new CxSelect();
        cxSelectBlerf.setN("Signal Chain-ble/rf");
        cxSelectBlerf.setV("Signal Chain-ble/rf");
        CxSelect cxSelectEsd = new CxSelect();
        cxSelectEsd.setN("Signal Chain-esd");
        cxSelectEsd.setV("Signal Chain-esd");
        CxSelect cxSelectChainISP = new CxSelect();
        cxSelectChainISP.setN("Signal chain-ISP");
        cxSelectChainISP.setV("Signal chain-ISP");
        CxSelect cxSelectChainMagneticSensor = new CxSelect();
        cxSelectChainMagneticSensor.setN("Signal Chain-magnetic sensor");
        cxSelectChainMagneticSensor.setV("Signal Chain-magnetic sensor");
        CxSelect cxSelectMcu = new CxSelect();
        cxSelectMcu.setN("Signal Chain-mcu");
        cxSelectMcu.setV("Signal Chain-mcu");
        CxSelect cxSelectOe = new CxSelect();
        cxSelectOe.setN("Signal Chain-oe");
        cxSelectOe.setV("Signal Chain-oe");
        CxSelect cxSelectSar = new CxSelect();
        cxSelectSar.setN("Signal Chain-sar");
        cxSelectSar.setV("Signal Chain-sar");
        CxSelect cxSelectTiming = new CxSelect();
        cxSelectTiming.setN("Signal Chain-timing");
        cxSelectTiming.setV("Signal Chain-timing");
        CxSelect cxSelectTpms = new CxSelect();
        cxSelectTpms.setN("Signal Chain-tpms");
        cxSelectTpms.setV("Signal Chain-tpms");
        CxSelect cxSelectVideoCodec = new CxSelect();
        cxSelectVideoCodec.setN("Signal chain-Video Codec");
        cxSelectVideoCodec.setV("Signal chain-Video Codec");
        CxSelect cxSelectOthers = new CxSelect();
        cxSelectOthers.setN("Signal chain-others");
        cxSelectOthers.setV("Signal chain-others");
        CxSelect cxSelectSerdes = new CxSelect();
        cxSelectSerdes.setN("Signal chain-serdes");
        cxSelectSerdes.setV("Signal chain-serdes");
        List<CxSelect> signalChainList = new ArrayList<CxSelect>();
        signalChainList.add(cxSelectAdda);
        signalChainList.add(cxSelectAmplifier);
        signalChainList.add(cxSelectAudio);
        signalChainList.add(cxSelectBlerf);
        signalChainList.add(cxSelectEsd);
        signalChainList.add(cxSelectChainISP);
        signalChainList.add(cxSelectChainMagneticSensor);
        signalChainList.add(cxSelectMcu);
        signalChainList.add(cxSelectOe);
        signalChainList.add(cxSelectSar);
        signalChainList.add(cxSelectTiming);
        signalChainList.add(cxSelectTpms);
        signalChainList.add(cxSelectVideoCodec);
        signalChainList.add(cxSelectOthers);
        signalChainList.add(cxSelectSerdes);
        cxSelectSignalChain.setS(signalChainList);

        CxSelect cxSelectpmic = new CxSelect();
        cxSelectpmic.setN("pmic");
        cxSelectpmic.setV("pmic");
        CxSelect cxSelectPmicGazelle = new CxSelect();
        cxSelectPmicGazelle.setN("pmic-gazelle");
        cxSelectPmicGazelle.setV("pmic-gazelle");
        CxSelect cxSelecTpmicHvPmic = new CxSelect();
        cxSelecTpmicHvPmic.setN("pmic-hv pmic");
        cxSelecTpmicHvPmic.setV("pmic-hv pmic");
        CxSelect cxSelectPmicLvPmic = new CxSelect();
        cxSelectPmicLvPmic.setN("pmic-lv pmic");
        cxSelectPmicLvPmic.setV("pmic-lv pmic");
        CxSelect cxSelectPmicPanelPmic = new CxSelect();
        cxSelectPmicPanelPmic.setN("pmic-panel pmic");
        cxSelectPmicPanelPmic.setV("pmic-panel pmic");
        CxSelect cxSelectPmicPmicKs = new CxSelect();
        cxSelectPmicPmicKs.setN("pmic-pmic-ks");
        cxSelectPmicPmicKs.setV("pmic-pmic-ks");
        List<CxSelect> pmicList = new ArrayList<CxSelect>();
        pmicList.add(cxSelectPmicGazelle);
        pmicList.add(cxSelecTpmicHvPmic);
        pmicList.add(cxSelectPmicLvPmic);
        pmicList.add(cxSelectPmicPanelPmic);
        pmicList.add(cxSelectPmicPmicKs);
        cxSelectpmic.setS(pmicList);

        CxSelect cxSelectPowerA = new CxSelect();
        cxSelectPowerA.setN("power a");
        cxSelectPowerA.setV("power a");
        CxSelect cxSelectPoweraBoostMulti = new CxSelect();
        cxSelectPoweraBoostMulti.setN("power a-boost/multi-phase");
        cxSelectPoweraBoostMulti.setV("power a-boost/multi-phase");
        CxSelect cxSelectPowerAbuck = new CxSelect();
        cxSelectPowerAbuck.setN("power a-buck");
        cxSelectPowerAbuck.setV("power a-buck");
        CxSelect cxSelectPoweraSwitch = new CxSelect();
        cxSelectPoweraSwitch.setN("power a-switch/ldo");
        cxSelectPoweraSwitch.setV("power a-switch/ldo");
        List<CxSelect> powerAList = new ArrayList<CxSelect>();
        powerAList.add(cxSelectPoweraBoostMulti);
        powerAList.add(cxSelectPowerAbuck);
        powerAList.add(cxSelectPoweraSwitch);
        cxSelectPowerA.setS(powerAList);

        CxSelect cxSelectPowerB = new CxSelect();
        cxSelectPowerB.setN("power b");
        cxSelectPowerB.setV("power b");
        CxSelect cxSelectPowerBAcdc = new CxSelect();
        cxSelectPowerBAcdc.setN("power b-acdc");
        cxSelectPowerBAcdc.setV("power b-acdc");
        CxSelect cxSelectpowerBAchp = new CxSelect();
        cxSelectpowerBAchp.setN("power b-achp");
        cxSelectpowerBAchp.setV("power b-achp");
        CxSelect cxSelectPowerBAcLed = new CxSelect();
        cxSelectPowerBAcLed.setN("power b-ac led");
        cxSelectPowerBAcLed.setV("power b-ac led");
        CxSelect cxSelectPowerBDcLed = new CxSelect();
        cxSelectPowerBDcLed.setN("power b-dc led");
        cxSelectPowerBDcLed.setV("power b-dc led");
        List<CxSelect> powerBList = new ArrayList<CxSelect>();
        powerBList.add(cxSelectPowerBAcdc);
        powerBList.add(cxSelectpowerBAchp);
        powerBList.add(cxSelectPowerBAcLed);
        powerBList.add(cxSelectPowerBDcLed);
        cxSelectPowerB.setS(powerBList);

        CxSelect cxSelectModule = new CxSelect();
        cxSelectModule.setN("module");
        cxSelectModule.setV("module");
        CxSelect cxSelectPowerModule = new CxSelect();
        cxSelectPowerModule.setN("power module");
        cxSelectPowerModule.setV("power module");
        List<CxSelect> modleList = new ArrayList<CxSelect>();
        modleList.add(cxSelectPowerModule);
        cxSelectModule.setS(modleList);

        CxSelect cxSelectSoc = new CxSelect();
        cxSelectSoc.setN("soc");
        cxSelectSoc.setV("soc");
        CxSelect cxSelectSocMetering = new CxSelect();
        cxSelectSocMetering.setN("soc-metering");
        cxSelectSocMetering.setV("soc-metering");
        List<CxSelect> socList = new ArrayList<CxSelect>();
        socList.add(cxSelectSocMetering);
        cxSelectSoc.setS(socList);

        CxSelect cxSelectShare = new CxSelect();
        cxSelectShare.setN("share");
        cxSelectShare.setV("share");
        List<CxSelect> shareList = new ArrayList<CxSelect>();
        cxSelectShare.setS(shareList);

        CxSelect cxSelectOther = new CxSelect();
        cxSelectOther.setN("other");
        cxSelectOther.setV("other");
        List<CxSelect> otherList = new ArrayList<CxSelect>();
        cxSelectOther.setS(otherList);

        CxSelect cxSelectAuto = new CxSelect();
        cxSelectAuto.setN("auto");
        cxSelectAuto.setV("auto");
        CxSelect cxSelectAutoDcdc = new CxSelect();
        cxSelectAutoDcdc.setN("auto-dcdc");
        cxSelectAutoDcdc.setV("auto-dcdc");
        CxSelect cxSelectAutoDriver = new CxSelect();
        cxSelectAutoDriver.setN("auto-driver");
        cxSelectAutoDriver.setV("auto-driver");
        List<CxSelect> autoList = new ArrayList<CxSelect>();
        autoList.add(cxSelectAutoDcdc);
        autoList.add(cxSelectAutoDriver);
        cxSelectAuto.setS(autoList);

        List<CxSelect> cxList = new ArrayList<CxSelect>();
        cxList.add(cxSelectBattery);
        cxList.add(cxSelectPlatform);
        cxList.add(cxSelectpmic);
        cxList.add(cxSelectPowerA);
        cxList.add(cxSelectPowerB);
        cxList.add(cxSelectModule);
        cxList.add(cxSelectSoc);
        cxList.add(cxSelectShare);
        cxList.add(cxSelectAuto);
        cxList.add(cxSelectSignalChain);
        cxList.add(cxSelectOther);
        Object o = JSON.toJSON(cxList);
        return o.toString();
    }
}
