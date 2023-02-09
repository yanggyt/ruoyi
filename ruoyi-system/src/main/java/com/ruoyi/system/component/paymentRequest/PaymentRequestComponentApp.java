package com.ruoyi.system.component.paymentRequest;

import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.component.requisition.RequisitionComponent;
import com.ruoyi.system.domain.PaymentAccount;
import com.ruoyi.system.domain.paymentRequest.PaymentRequest;
import com.ruoyi.system.domain.paymentRequest.PaymentRequestDt1;
import com.ruoyi.system.dto.paymentRequest.PaymentRequestDto;
import com.ruoyi.system.mapper.SysUserMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.misc.BASE64Encoder;

import java.math.BigDecimal;
import java.util.*;
import java.util.stream.Collectors;


@Component
public class PaymentRequestComponentApp {
    private static final Logger log = LoggerFactory.getLogger(PaymentRequestComponentApp.class);

    @Autowired
    private SysUserMapper userMapper;

    /**
     * 详情页审核时可以编辑的区域
     * @param paymentRequest 原单信息
     * @param user 操作用户
     * @param type 列表类型
     * @return
     */
    public Map<String,Object> editableArea(PaymentRequest paymentRequest, SysUser user, Integer type){
        //返回结果
        Map<String,Object> resultMap = new HashMap<>();
        //是否可以编辑\删除 true:可以 false:不可以
        Boolean editable = false;
        //是否可以撤回 true:可以 false:不可以
        Boolean revocation = false;
        //是否可以删除 true:可以 false:不可以
        Boolean remove = false;
        //是否可以签核 true:可以 false:不可以
        Boolean review = false;
        //是否要到电脑签核 true:是 false:不是
        Boolean mobileApp = false;
        //是否可以加签 true:可以 false:不可以
        Boolean endorsementFlag = true;
        //单子状态
        Integer status = paymentRequest.getStatus();
        if (Objects.equals(status,18) || Objects.equals(status,23) || Objects.equals(status,22)){
            endorsementFlag = false;
        }
        //审核信息
        Map<String,Object> auditResults = new HashMap<>();
        auditResults.put("paymentRequestId",null);
        auditResults.put("result",null);
        auditResults.put("remark",null);
        auditResults.put("endorsementType",null);
        auditResults.put("afterStatus",null);
        auditResults.put("endorsement",null);
        auditResults.put("endorsementFlag",endorsementFlag);
        //判断列表类型
        if (Objects.equals(type, 1)){
            //所有
            if (Objects.equals(paymentRequest.getEmployeeNo(),user.getUserCode()) || user.isAdmin()) {
                if (status != 1 && status != 19 && status != 20 && status != 21) {
                    //可以撤回
                    revocation = true;
                }
                if (status == 1 || status == 20){
                    //保存未提交 撤回 可以删除
                    remove = true;
                }
            }
        } else if (Objects.equals(type, 2)){
            //待办
            if (Objects.equals(paymentRequest.getSendToCode(),user.getUserCode()) ||
                    user.isAdmin() || paymentRequest.getSendToCode().contains(user.getUserCode())) {
                if (status == 1 || status == 20) {
                    //保存未提交 撤回
                    editable = true;
                } else {
                    review = true;
                }
            }
        }
        resultMap.put("editable",editable);
        resultMap.put("review",review);
        resultMap.put("mobileApp",mobileApp);
        resultMap.put("field",auditResults);
        resultMap.put("revocation",revocation);
        resultMap.put("remove",remove);
        return resultMap;
    }


    /**
     * 请款单app新增请款单内容过滤
     * @param paymentRequest
     * @return
     */
    public String insertCheck(PaymentRequest paymentRequest) {
        StringBuilder errorMsg = new StringBuilder();
        StringBuilder msg = new StringBuilder();
        List<PaymentRequestDt1> paymentRequestDt1s = paymentRequest.getPaymentRequestDt1List();
        if (StringUtils.isNull(paymentRequest)){
            errorMsg.append("检查基本信息是否填写正确\n");
        }else {
            //判断请款信息是否完整
            if (StringUtils.isNull(paymentRequest.getCompany())){
                errorMsg.append("检查公司是否填写正确\n");
            }
            if (StringUtils.isEmpty(paymentRequest.getEmployeeNo())){
                errorMsg.append("检查员工编号是否填写正确\n");
            }
            if (StringUtils.isEmpty(paymentRequest.getApplicant())){
                errorMsg.append("检查请款人是否填写正确\n");
            }
            if (StringUtils.isEmpty(paymentRequest.getDept())){
                errorMsg.append("检查使用部门是否填写正确\n");
            }
            if (StringUtils.isEmpty(paymentRequest.getDeptCode())){
                errorMsg.append("检查部门代码是否填写正确\n");
            }
            if (StringUtils.isNull(paymentRequest.getApplicationDate())){
                errorMsg.append("检查申请日期是否填写正确\n");
            }
            if (StringUtils.isNull(paymentRequest.getPaymentDate())){
                errorMsg.append("检查付款日期是否填写正确\n");
            }
            if (StringUtils.isNull(paymentRequest.getPaymentMethod())){
                errorMsg.append("检查付款方式是否填写正确\n");
            }
            if (StringUtils.isEmpty(paymentRequest.getPaymentType())){
                errorMsg.append("检查请款类别是否填写正确\n");
            }
            //判断选择是代请款后的四个内容
            if (StringUtils.isEmpty(errorMsg)){
                if (StringUtils.isNotNull(paymentRequest.getInsteadPayment())){
                    if (paymentRequest.getInsteadPayment() == 1) {
                        if (StringUtils.isNull(paymentRequest.getActualUserNo())) {
                            errorMsg.append("实际使用人编号不能为空。\n");
                        }else {
                            SysUser user = userMapper.selectUserByUserCode(paymentRequest.getActualUserNo());
                            if (StringUtils.isNull(user)){
                                errorMsg.append("未查询到实际使用人，请查看实际使用人编号是否填写正确。\n");
                            }
                        }
                    }
                }else{
                    errorMsg.append("是否代请款不能为空\n");
                }
            }
        }

        Boolean projectCodeFlag = false;
        String departmentCode = null;
        if (paymentRequest.getInsteadPayment() == 1) {
            departmentCode = paymentRequest.getActualDeptCode();
        } else {
            departmentCode = paymentRequest.getDeptCode();
        }
        if ((Objects.equals(departmentCode.substring(2, 3), "2") && !Objects.equals(departmentCode.substring(2, 5), "250")) ||
                Objects.equals(departmentCode.substring(2, 5), "400") ||
                Objects.equals(departmentCode.substring(2, 5), "420") ||
                Objects.equals(departmentCode.substring(2, 5), "430")){
            projectCodeFlag = true;
        }

        if (StringUtils.isEmpty(errorMsg)){
            if (StringUtils.isNull(paymentRequestDt1s)){
                errorMsg.append("检查摘要说明，数量，单价，总金额，币别是否填写正确\n");
            }else{
                //判断请款明细信息是否完整
                for (PaymentRequestDt1 paymentRequestDt1 : paymentRequestDt1s) {
                    //过滤不完整的数据
                    if (projectCodeFlag && StringUtils.isEmpty(paymentRequestDt1.getProjectCode())){
                        errorMsg.append("检查项目代码是否填写正确\n");
                    }

                    if (StringUtils.isEmpty(paymentRequestDt1.getContent())){
                        errorMsg.append("检查摘要说明是否填写正确\n");
                    }
                    if (StringUtils.isEmpty(paymentRequestDt1.getQuantity())){
                        errorMsg.append("检查数量是否填写正确\n");
                    }
                    if (StringUtils.isNull(paymentRequestDt1.getUnitPrice())){
                        errorMsg.append("检查单价是否填写正确\n");
                    }
                    if (StringUtils.isNull(paymentRequestDt1.getTotalAmount())){
                        errorMsg.append("检查总金额是否填写正确\n");
                    }
                    if ( StringUtils.isNull(paymentRequestDt1.getCurrency())){
                        errorMsg.append("检查币别是否填写正确\n");
                    }
                }
            }
        }
        if (StringUtils.isEmpty(errorMsg)){
                //判断付款信息是否完整
                if (paymentRequest.getPaymentMethod() == 1){
                    if (StringUtils.isEmpty(paymentRequest.getPayee())){
                        errorMsg.append("检查收款人是否填写正确\n");
                    }
                    if (StringUtils.isEmpty(paymentRequest.getBankName())){
                        errorMsg.append("检查收款银行是否填写正确\n");
                    }
                    if (StringUtils.isEmpty(paymentRequest.getAccountNumber())){
                        errorMsg.append("检查收款账号是否填写正确\n");
                    }
                }else {
                    if (StringUtils.isEmpty(paymentRequest.getPayee())){
                        errorMsg.append("检查收款人是否填写正确\n");
                    }
                }

        }
        if (StringUtils.isNotEmpty(errorMsg)) {
            msg.append("数据未填写完整，具体如下：");
            msg.append("\n");
            msg.append(errorMsg);
        }
        return msg.toString();
    }
    
    

    /**
     * Base64加密
     * @param b
     * @return
     */
    public String byteToBase64(byte[] b) {
        String str = "";
        if (null != b) {
            BASE64Encoder encoder = new BASE64Encoder();
            str = encoder.encode(b);
        }
        return str;
    }

    /**
     * 表单下拉框信息
     *
     * @return 结果
     */
    public Map<Object, Object> selectInformation() {
        Map<Object, Object> paymentRequest = new HashMap<>();

        //------------表单下拉框信息------------
        Map<Object, Object> mainData = new HashMap<>();
        //公司
        Map<Object, Object> company = new HashMap<>();
        company.put("6", "南京矽力微电子技术有限公司");
        company.put("14", "上海矽力杰微电子技术有限公司");
        company.put("20", "南京矽力微電子(香港)有限公司");
        mainData.put("company", company);
        //请款类别
        Map<Object, Object> paymentType = new HashMap<>();
        paymentType.put("X", "个人报销");
//        paymentType.put("Y", "生产性支出");
//        paymentType.put("A", "生产性支出-汇总请款");
        paymentType.put("B", "非生产性支出-持续性");
        paymentType.put("C", "非生产性支出-非持续性");
        paymentType.put("E", "交际费");
        mainData.put("paymentType", paymentType);
        //是否预付
        Map<Object, Object> prepaid = new HashMap<>();
        prepaid.put("1", "否");
        prepaid.put("2", "是");
        mainData.put("prepaid", prepaid);
        //是否代请款
        Map<Object, Object> insteadPayment = new HashMap<>();
        insteadPayment.put("0", "否");
        insteadPayment.put("1", "是");
        mainData.put("insteadPayment", insteadPayment);
        //付款方式
        Map<Object, Object> paymentMethod = new HashMap<>();
        paymentMethod.put("1", "转账汇款");
        paymentMethod.put("2", "其他");
        mainData.put("paymentMethod", paymentMethod);
        //状态
        Map<Object, Object> status = new HashMap<>();
        status.put("1", "已保存待提交");
        status.put("2", "待部门主管审批");
        status.put("3", "待部门经理审批");
        status.put("4", "待部门总监审批");
        status.put("6", "待部门执行总监审批");
        status.put("8", "待总经理审批");
        status.put("5", "财务初核");
        status.put("7", "总账确认");
        status.put("18", "待前加签审批");
        status.put("23", "待后加签审批");
        status.put("22", "保留");
        status.put("21", "完成");
        status.put("19", "否决");
        status.put("20", "撤回");
        mainData.put("status", status);



        //------------表单下拉框信息2------------
        Map<Object, Object> mainData2 = new HashMap<>();
        //付款方式
        Map<Object, Object> paymentMethod2 = new HashMap<>();
        paymentMethod2.put("1", "转账汇款");
        paymentMethod2.put("2", "其他");
        mainData2.put("paymentMethod", paymentMethod2);



        //------------付款项目信息------------
        Map<Object, Object> projectInformation = new HashMap<>();
        //交易币别
        Map<Object, Object> currency = new HashMap<>();
        currency.put("1", "RMB");
        currency.put("2", "USD");
        currency.put("3", "TWD");
        currency.put("4", "HKD");
        currency.put("5", "KRW");
        currency.put("6", "MOP");
        currency.put("7", "EUR");
        projectInformation.put("currency", currency);
        //项目编号
        List<String> projectCode = (List<String>) CacheUtils.get(Constants.ZT_WGCPRORELEASE_CACHE, Constants.ZT_WGCPRORELEASE_KEY + "proCode");
        Map<String, Object> map = projectCode.stream().collect(
                Collectors.toMap(s -> s, s -> s));
        projectInformation.put("projectCode", map);


        //------------已审核人员列表下拉框信息------------
        Map<Object, Object> signConfig = new HashMap<>();
        //审核结果
        Map<Object, Object> auditResult = new HashMap<>();
        auditResult.put("0", "用户提交");
        auditResult.put("1", "同意");
        auditResult.put("2", "否决");
        auditResult.put("3", "退回重送");
        auditResult.put("4", "保留");
        auditResult.put("5", "加签");
        signConfig.put("auditResult", auditResult);
        //角色
        Map<Object, Object> orderNodeType = new HashMap<>();
        orderNodeType.put("1", "创建者");
        orderNodeType.put("2", "主管");
        orderNodeType.put("3", "经理");
        orderNodeType.put("4", "总监");
        orderNodeType.put("6", "执行总监");
        orderNodeType.put("8", "总经理");
        orderNodeType.put("5", "会计");
        orderNodeType.put("7", "总账");
        orderNodeType.put("18", "前加签");
        orderNodeType.put("23", "后加签");
        signConfig.put("orderNodeType", orderNodeType);


        //------------审核下拉框信息------------
        Map<Object, Object> signConfig2 = new HashMap<>();
        //审核结果
        Map<Object, Object> result = new HashMap<>();
        result.put("1", "同意");
        result.put("2", "否决");
        result.put("3", "退回重送");
        result.put("4", "保留");
        result.put("5", "加签");
        signConfig2.put("result", result);
        //加签类型
        Map<Object, Object> endorsementType = new HashMap<>();
        endorsementType.put("1", "前加签");
        endorsementType.put("2", "后加签");
        signConfig2.put("endorsementType", endorsementType);
        //后加签位置
//        Map<Object, Object> afterStatus = new HashMap<>();
//        afterStatus.put("2", "主管");
//        afterStatus.put("3", "经理");
//        afterStatus.put("4", "总监");
//        afterStatus.put("6", "执行总监");
//        afterStatus.put("8", "总经理");
//        afterStatus.put("5", "会计");
//        afterStatus.put("7", "总账");
//        signConfig2.put("afterStatus", afterStatus);


        paymentRequest.put("mainData", mainData);
        paymentRequest.put("mainData2", mainData2);
        paymentRequest.put("projectInformation", projectInformation);
        paymentRequest.put("signConfig", signConfig);
        paymentRequest.put("signConfig2", signConfig2);
        return paymentRequest;
    }
}
