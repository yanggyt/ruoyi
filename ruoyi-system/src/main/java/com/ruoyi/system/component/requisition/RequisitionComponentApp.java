package com.ruoyi.system.component.requisition;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.exception.GlobalException;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.requisition.Requisition;
import com.ruoyi.system.domain.requisition.RequisitionDt1;
import com.ruoyi.system.domain.requisition.RequisitionDt2;
import com.ruoyi.system.dto.requisition.RequisitionDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import sun.misc.BASE64Encoder;

import java.util.*;


@Component
@Controller
public class RequisitionComponentApp
{
    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private RequisitionComponent requisitionComponent;


    /**
     * 详情页审核时可以编辑的区域
     *
     * @param requisition 原单信息
     * @param user        操作用户
     * @param type        列表类型
     * @return
     */
    public Map<String, Object> editableArea(Requisition requisition, SysUser user, Integer type)
    {
        //返回结果
        Map<String, Object> resultMap = new HashMap<>();
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
        if (Objects.equals(requisition.getStatus(), 18) || Objects.equals(requisition.getStatus(), 26)
                || Objects.equals(requisition.getStatus(), 22)) {
            endorsementFlag = false;
        }
        //审核信息
        Map<String, Object> auditResults = new HashMap<>();
        auditResults.put("requisitionId", null);
        auditResults.put("result", null);
        auditResults.put("remark", null);
        auditResults.put("endorsementType", null);
        auditResults.put("afterStatus", null);
        auditResults.put("endorsement", null);
        auditResults.put("endorsementFlag", endorsementFlag);
        //Dto
        Map<String, Object> requisitionDto = new HashMap<>();
        //主数据
        Map<String, Object> requisition1 = new HashMap<>();
        requisition1.put("category", true);
        requisition1.put("purchaseCode", true);
        requisition1.put("priceComparison", true);
        requisition1.put("specialReason", true);
        Integer status = requisition.getStatus();
        //判断列表类型
        switch (type) {
            case 1:
                //所有
                if (Objects.equals(requisition.getEmployeeNo(), user.getUserCode())) {
                    if (status == 1 || status == 7) {
                        //保存未提交
                        if (StringUtils.isEmpty(requisition.getPurchaseCode())) {
                            //存在预算编号的单子不能删除
                            remove = true;
                        }
                    }
                    if (status != 5 && status != 6 && status != 1 && status != 7) {
                        revocation = true;
                    }
                }
            case 3:
                editable = false;
                review = false;
                break;
            case 2:
                //待办
                if (Objects.equals(requisition.getSendToCode(), user.getUserCode())) {
                    if (status == 13 || status == 20) {
                        //采购代表
                        requisitionDto.put("requisition", requisition1);
                        requisitionDto.put("requisitionDt1s", true);
                        requisitionDto.put("requisitionDt2s", true);
                        //后面可能会开放
                        mobileApp = true;
                    } else if (status == 14) {
                        //预算管理员
                        requisitionDto.put("requisition", requisition1);
                        //后面可能会开放
                        mobileApp = true;
                    } else if (status == 1 || status == 7) {
                        //保存未提交
                        if (StringUtils.isEmpty(requisition.getPurchaseCode())) {
                            //存在预算编号的单子不能删除
                            editable = true;
                        }
                        review = false;
                    } else if (status == 5) {
                        review = false;
                    } else {
                        review = true;
                    }
                    auditResults.put("requisitionDto", requisitionDto);
                }
                break;
            default:
                log.error("获取请购单列表类型出错，异常数据:::{}", type);
                throw new GlobalException("获取请购单列表类型出错。");

        }
        resultMap.put("editable", editable);
        resultMap.put("review", review);
        resultMap.put("mobileApp", mobileApp);
        resultMap.put("field", auditResults);
        resultMap.put("revocation", revocation);
        resultMap.put("remove", remove);
        return resultMap;
    }

    /**
     * 校验数据完整性
     *
     * @param requisitionDto
     * @return
     */
    public String insertCheck(RequisitionDto requisitionDto)
    {
        //是否必填厂商比议价标识
        Boolean requiredFlag = false;
        //dt1是否有数据
        Boolean dt1Flag = false;
        //dt2是否有数据
        Boolean dt2Flag = false;
        //是否选中供应商
        Boolean checkedFlag = false;
        StringBuilder errorMsg = new StringBuilder();
        StringBuilder msg = new StringBuilder();
        List<RequisitionDt1> requisitionDt1List = new ArrayList<>();
        List<RequisitionDt2> requisitionDt2List = new ArrayList<>();
        Requisition requisition = requisitionDto.getRequisition();
        List<RequisitionDt1> requisitionDt1s = requisitionDto.getRequisitionDt1s();
        List<RequisitionDt2> requisitionDt2s = requisitionDto.getRequisitionDt2s();
        if (StringUtils.isNull(requisition) || StringUtils.isNull(requisition.getCompany()) ||
                StringUtils.isEmpty(requisition.getEmployeeNo()) ||
                StringUtils.isEmpty(requisition.getUserName()) ||
                StringUtils.isEmpty(requisition.getUserDepartment()) ||
                StringUtils.isEmpty(requisition.getOffice()) ||
                StringUtils.isNull(requisition.getUserDate()) ||
                StringUtils.isEmpty(requisition.getDepartmentCode()) ||
                StringUtils.isNull(requisition.getAssetManagement()) ||
                StringUtils.isNull(requisition.getReasonForPurchase())) {
            msg.append("提交失败，请检查数据是否填写完整。");
            return msg.toString();
        }
        String departmentCode = requisition.getDepartmentCode();
        Integer company = requisition.getCompany();
        Integer assetManagement = requisition.getAssetManagement();
        Integer priceComparison = requisition.getPriceComparison();
        //判断产品线
        if (Objects.equals(departmentCode.substring(2, 4), "21") || Objects.equals(departmentCode.substring(2, 4), "23")) {
            // || assetManagement == 2
            if (assetManagement == 1 || assetManagement == 3 ||
                    assetManagement == 4 || assetManagement == 6) {
                if (StringUtils.isEmpty(requisition.getProductLine())) {
                    errorMsg.append("产品线类别不能为空。\n");
//                    throw new BusinessException("产品线类别不能为空");
                }
            } else {
                requisition.setProductLine("");
                requisition.setProductLine1("");
            }
        } else {
            requisition.setProductLine("");
            requisition.setProductLine1("");
        }

        //判断项目说明,厂商比议价
        if (company == 20) {
            requiredFlag = true;
        } else if (company == 12) {
            if (assetManagement != 2 && assetManagement != 12) {
                requiredFlag = true;
            }
        } else if (company == 6) {
            if (assetManagement == 5 || assetManagement == 11) {
                requiredFlag = true;
            }
        } else if (company == 14) {
            if (assetManagement == 5 || assetManagement == 11 || assetManagement == 6 ||
                    assetManagement == 1 || assetManagement == 4) {
                requiredFlag = true;
            }
        }

        Boolean projectCodeFlag = false;
        if ((Objects.equals(departmentCode.substring(2, 3), "2") && !Objects.equals(departmentCode.substring(2, 5), "250")) ||
                Objects.equals(departmentCode.substring(2, 5), "400") ||
                Objects.equals(departmentCode.substring(2, 5), "420") ||
                Objects.equals(departmentCode.substring(2, 5), "430")) {
            projectCodeFlag = true;
        }

        if (StringUtils.isNotEmpty(requisitionDt1s)) {
            for (RequisitionDt1 requisitionDt1 : requisitionDt1s) {
                //过滤不完整的数据
                if (StringUtils.isNotEmpty(requisitionDt1.getProjectName()) &&
                        StringUtils.isNotEmpty(requisitionDt1.getSpecifications()) &&
                        StringUtils.isNotNull(requisitionDt1.getQuantity()) &&
                        StringUtils.isNotNull(requisitionDt1.getUnitPrice()) &&
                        StringUtils.isNotNull(requisitionDt1.getAmount()) &&
                        (projectCodeFlag ? StringUtils.isNotEmpty(requisitionDt1.getProjectCode()) : true)) {
                    dt1Flag = true;
                    requisitionDt1List.add(requisitionDt1);
                }
            }
        }
        if (!dt1Flag) {
            errorMsg.append("至少填写1个完整的项目说明。\n");
//            throw new BusinessException("至少填写1个完整的项目说明");
        }
        if (StringUtils.isNotEmpty(requisitionDt2s)) {
            for (RequisitionDt2 requisitionDt2 : requisitionDt2s) {
                //过滤不完整的数据
                if (StringUtils.isNotEmpty(requisitionDt2.getSuppliersName()) &&
                        StringUtils.isNotNull(requisitionDt2.getCoin()) &&
                        StringUtils.isNotEmpty(requisitionDt2.getUntaxedUnitPrice()) &&
                        StringUtils.isNotNull(requisitionDt2.getTaxRate()) &&
                        StringUtils.isNotEmpty(requisitionDt2.getTotalAmount())) {
                    dt2Flag = true;
                    requisitionDt2List.add(requisitionDt2);
                    if (StringUtils.isNotNull(requisitionDt2.getSuppliers()) && requisitionDt2.getSuppliers() == 1) {
                        checkedFlag = true;
                    }
                }
            }
        }
        if (requiredFlag) {
            if (StringUtils.isNotNull(priceComparison)) {
                if (priceComparison == 1) {
                    if (requisitionDt2List.size() < 2) {
                        errorMsg.append("您选择了比议价需要输入2个以上完整的供应商信息，并选中其中一个。\n");
//                    throw new BusinessException("您选择了比议价需要输入2个以上完整的供应商信息，并选中其中一个！");
                    }
                } else if (priceComparison == 2) {
                    if (requisitionDt2List.size() < 1) {
                        errorMsg.append("至少输入1个完整的供应商信息，并选中。\n");
//                        throw new BusinessException("至少输入1个完整的供应商信息，并选中。");
                    }
                }
                if (dt2Flag && !checkedFlag) {
                    errorMsg.append("至少选中1个供应商。\n");
//                throw new BusinessException("至少选中1个供应商");
                }
            } else {
                errorMsg.append("请选择是否比议价。\n");
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
     *
     * @param b
     * @return
     */
    public String byteToBase64(byte[] b)
    {
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
    public Map<Object, Object> selectInformation()
    {
        Map<Object, Object> requisition = new HashMap<>();

        //------------表单下拉框信息------------
        Map<Object, Object> mainData = new HashMap<>();
        //公司
        Map<Object, Object> company = new HashMap<>();
        company.put("5", "矽力杰半导体技术（杭州）有限公司");
        company.put("6", "南京矽力微电子技术有限公司");
        company.put("17", "成都矽力杰半导体技术有限公司");
        company.put("7", "西安矽力杰半导体技术有限公司");
        company.put("14", "上海矽力杰微电子技术有限公司");
        company.put("1", "Silergy Corp");
        company.put("19", "上海矽力杰半导体技术有限公司");
        company.put("3", "Silergy Korea Limited");
        company.put("4", "Silergy Technology");
        company.put("9", "Silergy Semiconductor (Samoa) Limited");
        company.put("16", "Silergy Technologies Private Limited");
        company.put("15", "Silergy Semiconductor Samoa Limited");
        company.put("13", "矽力杰半導體(香港)有限公司");
        company.put("12", "台湾矽力杰科技股份有限公司");
        company.put("20", "南京矽力微電子(香港)有限公司");
        company.put("21", "矽望投资有限公司");
        company.put("23", "合肥矽力杰半导体技术有限公司");
        company.put("24", "矽力杰半導體(澳門)一人有限公司");
        mainData.put("company", company);
        //申请类别
        Map<Object, Object> typeOfApplication = new HashMap<>();
        typeOfApplication.put("1", "非销售性请购");
        typeOfApplication.put("2", "固定资产");
        mainData.put("typeOfApplication", typeOfApplication);
        //是否新员工入职
        Map<Object, Object> newStaff = new HashMap<>();
        newStaff.put("1", "是");
        newStaff.put("2", "否");
        mainData.put("newStaff", newStaff);
        //产品线类别
        Map<Object, Object> productLine = new HashMap<>();
        productLine.put("battery", "battery");
        productLine.put("platform", "platform");
        productLine.put("pmic", "pmic");
        productLine.put("power a", "power a");
        productLine.put("power b", "power b");
        productLine.put("module", "module");
        productLine.put("soc", "soc");
        productLine.put("share", "share");
        productLine.put("auto", "auto");
        productLine.put("other", "other");
        mainData.put("productLine", productLine);
        //产品线详细
        Map<Object, Object> productLine1 = new HashMap<>();
        productLine1.put("battery-bat", "battery-bat");
        productLine1.put("battery-bms", "battery-bms");
        productLine1.put("battery-gauge", "battery-gauge");
        productLine1.put("battery-wireless", "battery-wireless");

        productLine1.put("platform-adda", "platform-adda");
        productLine1.put("platform-amplifier", "platform-amplifier");
        productLine1.put("platform-audio", "platform-audio");
        productLine1.put("platform-ble/rf", "platform-ble/rf");
        productLine1.put("platform-esd", "platform-esd");
        productLine1.put("platform-mcu", "platform-mcu");
        productLine1.put("platform-oe", "platform-oe");
        productLine1.put("platform-sar", "platform-sar");
        productLine1.put("platform-timing", "platform-timing");
        productLine1.put("platform-tpms", "platform-tpms");
        productLine1.put("platform-digital power", "platform-digital power");
        productLine1.put("platform-mot", "platform-mot");
        productLine1.put("platform-others", "platform-others");

        productLine1.put("pmic-gazelle", "pmic-gazelle");
        productLine1.put("pmic-hv pmic", "pmic-hv pmic");
        productLine1.put("pmic-lv pmic", "pmic-lv pmic");
        productLine1.put("pmic-panel pmic", "pmic-panel pmic");
        productLine1.put("pmic-pmic-ks", "pmic-pmic-ks");

        productLine1.put("power a-boost/multi-phase", "power a-boost/multi-phase");
        productLine1.put("power a-buck", "power a-buck");
        productLine1.put("power a-switch/ldo", "power a-switch/ldo");

        productLine1.put("power b-acdc", "power b-acdc");
        productLine1.put("power b-achp", "power b-achp");
        productLine1.put("power b-ac led", "power b-ac led");
        productLine1.put("power b-dc led", "power b-dc led");

        productLine1.put("power module", "power module");

        productLine1.put("soc-metering", "soc-metering");

        productLine1.put("auto-dcdc", "auto-dcdc");
        productLine1.put("auto-driver", "auto-driver");
        mainData.put("productLine1", productLine1);

        //产品线详细11
        Map<Object, Object> productLine11 = new HashMap<>();
        Map<Object, Object> battery = new HashMap<>();
        battery.put("battery-bat", "battery-bat");
        battery.put("battery-bms", "battery-bms");
        battery.put("battery-gauge", "battery-gauge");
        battery.put("battery-wireless", "battery-wireless");
        productLine11.put("battery", battery);

        Map<Object, Object> platform = new HashMap<>();
        platform.put("platform-adda", "platform-adda");
        platform.put("platform-amplifier", "platform-amplifier");
        platform.put("platform-audio", "platform-audio");
        platform.put("platform-ble/rf", "platform-ble/rf");
        platform.put("platform-esd", "platform-esd");
        platform.put("platform-mcu", "platform-mcu");
        platform.put("platform-oe", "platform-oe");
        platform.put("platform-sar", "platform-sar");
        platform.put("platform-timing", "platform-timing");
        platform.put("platform-tpms", "platform-tpms");
        platform.put("platform-digital power", "platform-digital power");
        platform.put("platform-mot", "platform-mot");
        platform.put("platform-others", "platform-others");
        productLine11.put("platform", platform);

        Map<Object, Object> pmic = new HashMap<>();
        pmic.put("pmic-gazelle", "pmic-gazelle");
        pmic.put("pmic-hv pmic", "pmic-hv pmic");
        pmic.put("pmic-lv pmic", "pmic-lv pmic");
        pmic.put("pmic-panel pmic", "pmic-panel pmic");
        pmic.put("pmic-pmic-ks", "pmic-pmic-ks");
        productLine11.put("pmic", pmic);

        Map<Object, Object> powerA = new HashMap<>();
        powerA.put("power a-boost/multi-phase", "power a-boost/multi-phase");
        powerA.put("power a-buck", "power a-buck");
        powerA.put("power a-switch/ldo", "power a-switch/ldo");
        productLine11.put("power a", powerA);

        Map<Object, Object> powerB = new HashMap<>();
        powerB.put("power b-acdc", "power b-acdc");
        powerB.put("power b-achp", "power b-achp");
        powerB.put("power b-ac led", "power b-ac led");
        powerB.put("power b-dc led", "power b-dc led");
        productLine11.put("power b", powerB);

        Map<Object, Object> module = new HashMap<>();
        module.put("power module", "power module");
        productLine11.put("module", module);

        Map<Object, Object> soc = new HashMap<>();
        soc.put("soc-metering", "soc-metering");
        productLine11.put("soc", soc);

        Map<Object, Object> share = new HashMap<>();
        productLine11.put("share", share);

        Map<Object, Object> auto = new HashMap<>();
        auto.put("auto-dcdc", "auto-dcdc");
        auto.put("auto-driver", "auto-driver");
        productLine11.put("auto", auto);

        Map<Object, Object> other = new HashMap<>();
        productLine11.put("other", other);

        mainData.put("productLine11", productLine11);
        //是否比议价
        Map<Object, Object> priceComparison = new HashMap<>();
        priceComparison.put("1", "是");
        priceComparison.put("2", "否");
        mainData.put("priceComparison", priceComparison);
        //状态
        Map<Object, Object> status = new HashMap<>();
        status.put("1", "已保存待提交");
        status.put("2", "待主管审批");
        status.put("23", "待经理审批");
        status.put("24", "待总监审批");
        status.put("25", "待执行总监审批");
        status.put("13", "待资产管理员审批");
        status.put("20", "待采购代表审批");
        status.put("16", "待新员工入职HR审批");
        status.put("19", "待HR审批");
        status.put("14", "待预算管理员审批");
        status.put("27", "待财务预算经理审批");
        status.put("3", "待总账审批");
        status.put("11", "待财务经理审批");
        status.put("4", "待总经理审批");
        status.put("18", "待前加签审批");
        status.put("26", "待后加签审批");
        status.put("22", "保留");
        status.put("5", "完成");
        status.put("6", "否决");
        status.put("7", "撤回");
        mainData.put("status", status);


        //------------表单下拉框信息->资产类别相关------------
        Map<Object, Object> mainData2 = new HashMap<>();
        //所属类别
        Map<Object, Object> category = new HashMap<>();
        Map<Object, Object> category1 = new HashMap<>();
        Map<Object, Object> category2 = new HashMap<>();
        category1.put("3", "新产品开发");
        category1.put("4", "其他");
        category2.put("1", "预算内");
        category2.put("2", "预算外");
        category.put("typeOfApplication_1", category1);
        category.put("typeOfApplication_2", category2);
        mainData2.put("category", category);
        //资产管理类别
        Map<Object, Object> assetManagement = new HashMap<>();
        Map<Object, Object> assetManagement1 = new HashMap<>();
        Map<Object, Object> assetManagement2 = new HashMap<>();
        assetManagement1.put("5", "无");
        assetManagement1.put("6", "元器件");
        assetManagement1.put("7", "办公家具");
        assetManagement1.put("8", "办公用品类");
        assetManagement1.put("9", "工程维修改造");
        assetManagement2.put("1", "测试类设备");
        assetManagement2.put("2", "IT桌面类");
        assetManagement2.put("3", "办公家具类");
        assetManagement2.put("4", "实验室桌椅");
        assetManagement2.put("10", "工程维修改造");
        assetManagement2.put("11", "无");
        assetManagement2.put("12", "个人电脑");
        assetManagement.put("typeOfApplication_1", assetManagement1);
        assetManagement.put("typeOfApplication_2", assetManagement2);
        mainData2.put("assetManagement", assetManagement);


        //------------表单下拉框信息->资产类别相关------------
        Map<Object, Object> productInformation = new HashMap<>();
        //项目编号
        productInformation.put("projectCode", CacheUtils.get(Constants.ZT_WGCPRORELEASE_CACHE, Constants.ZT_WGCPRORELEASE_KEY + "proCode"));


        //------------厂商比议价下拉框信息------------
        Map<Object, Object> factoryInfos = new HashMap<>();
        //交易币别
        Map<Object, Object> coin = new HashMap<>();
        coin.put("1", "RMB");
        coin.put("2", "USD");
        coin.put("3", "TWD");
        coin.put("4", "HKD");
        coin.put("5", "KRW");
        coin.put("6", "MOP");
        factoryInfos.put("coin", coin);
        //税率
        Map<Object, Object> taxRate = new HashMap<>();
        taxRate.put("0", "0%");
        taxRate.put("1", "1%");
        taxRate.put("3", "3%");
        taxRate.put("5", "5%");
        taxRate.put("6", "6%");
        taxRate.put("9", "9%");
        taxRate.put("13", "13%");
        factoryInfos.put("taxRate", taxRate);
        //厂商是否选中
        Map<Object, Object> suppliers = new HashMap<>();
        suppliers.put("1", " ");
        factoryInfos.put("suppliers", suppliers);


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
        orderNodeType.put("23", "经理");
        orderNodeType.put("24", "总监");
        orderNodeType.put("25", "执行总监");
        orderNodeType.put("13", "资产管理员");
        orderNodeType.put("22", "采购代表");
        orderNodeType.put("16", "新员工入职HR");
        orderNodeType.put("21", "HR审核");
        orderNodeType.put("14", "预算管理员");
        orderNodeType.put("27", "待财务预算经理审批");
        orderNodeType.put("3", "总账");
        orderNodeType.put("11", "财务经理");
        orderNodeType.put("4", "总经理");
        orderNodeType.put("19", "前加签");
        orderNodeType.put("20", "后加签");
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
        Map<Object, Object> afterStatus = new HashMap<>();
        afterStatus.put("2", "主管");
        afterStatus.put("23", "经理");
        afterStatus.put("24", "总监");
        afterStatus.put("25", "执行总监");
        afterStatus.put("13", "资产管理员");
        afterStatus.put("20", "采购代表");
        afterStatus.put("16", "新员工入职HR");
        afterStatus.put("19", "HR审核");
        afterStatus.put("14", "预算管理员");
        afterStatus.put("27", "待财务预算经理审批");
        afterStatus.put("3", "总账");
        afterStatus.put("11", "财务经理");
        afterStatus.put("4", "总经理");
        signConfig2.put("afterStatus", afterStatus);


        requisition.put("mainData", mainData);
        requisition.put("mainData2", mainData2);
        requisition.put("productInformation", productInformation);
        requisition.put("factory_infos", factoryInfos);
        requisition.put("signConfig", signConfig);
        requisition.put("signConfig2", signConfig2);
        return requisition;
    }
}
