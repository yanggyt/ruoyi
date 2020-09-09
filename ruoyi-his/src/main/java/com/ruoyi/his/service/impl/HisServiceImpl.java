package com.ruoyi.his.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bending.core.his.bo.ExpensesBillBO;
import com.bending.core.his.common.HISResult;
import com.bending.core.his.constant.HisConstant;
import com.bending.core.his.entity.*;
import com.bending.core.his.vo.*;
import com.bending.core.utils.HISClientUtil;
import com.ruoyi.common.func.IConstant;
import com.ruoyi.common.func.Func;
import com.ruoyi.common.func.IdCardUtil;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.*;
import com.ruoyi.his.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class HisServiceImpl implements IHisService {

    private static final Logger log = LoggerFactory.getLogger(HisServiceImpl.class);

    @Resource
    private HISClientUtil hisClientUtil;
    @Resource
    private IHisOutpatientService outpatientService;
    @Resource
    private IHisInpatientService inpatientService;
    @Resource
    private IHisRegistrationRecordService registrationRecordService;
    @Resource
    private IHisHospitalInfoService hospitalInfoService;
    @Resource
    private IHisOutpatientExpensesBillService outpatientExpensesBillService;
    @Resource
    private IHisPatientExpensesService patientExpensesService;
    @Resource
    private IHisDoctorScheduleService doctorScheduleService;

    /**
     * 同步机构挂号记录
     *
     * @param orgCode   机构编码
     * @param idCardNo  身份证号码,非必须参数！
     * @param beginTime 开始日期,格式:yyyy-MM-dd
     * @param endTime   结束日期,格式:yyyy-MM-dd
     * @return 如果提供idCardNo，则返回与之对应的列表，否则就返回该机构下所有挂号记录！
     */
    @Override
    public List<HisRegistrationRecord> syncRegistrationRecord(String orgCode, String idCardNo, String beginTime, String endTime) {
        //存放每次同步最新的数据
        List<HisRegistrationRecord> newRegistrationRecordList = new ArrayList<>();

        RegistrationRecord registrationRecord = new RegistrationRecord();
        registrationRecord.setOrgCode(orgCode);
        registrationRecord.setIdCardNo(idCardNo);
        registrationRecord.setBeginTime(beginTime);
        registrationRecord.setEndTime(endTime);
        //HIS拿到当日的挂号记录(线上和线下全部的)所有的
        HISResult hisResult = hisClientUtil.getRegistrationRecordList(registrationRecord);
        if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
            TypeReference<List<RegistrationRecordVO>> typeRef = new TypeReference<List<RegistrationRecordVO>>() {
            };
            List<RegistrationRecordVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
            if (Func.isNotEmpty(list)) {
                for (RegistrationRecordVO vo : list) {
                    String _idCardNo = vo.getIdCardNo();
                    if (Func.isNotEmpty(_idCardNo) && IdCardUtil.validate(_idCardNo)) {
                        //身份证验证,过滤无效的身份证数据
                        HisRegistrationRecord hisRegistrationRecord = new HisRegistrationRecord();
                        hisRegistrationRecord.setPatientSex(IdCardUtil.verifyGender(_idCardNo));//获取性别
                        hisRegistrationRecord.setOrgCode(orgCode);
                        String registeredTime = vo.getRegisteredDate();//挂号时间
                        //挂号日期 处理
                        if (Func.isNotEmpty(registeredTime)) {
                            Date date = DateUtils.parseDate(registeredTime);
                            Date registeredDate = Func.parse(registeredTime, DateUtils.YYYY_MM_DD);
                            hisRegistrationRecord.setRegisteredDate(registeredDate);         //挂号日期[YYYY-MM-DD]
                            hisRegistrationRecord.setVisitDate(Func.formatDate(date, DateUtils.YYYY_MM_DD));  //就诊日期[YYYY-MM-DD]
                            hisRegistrationRecord.setVisitTime(Func.getDuringDay(date));//就诊时段 上午或下午
                            hisRegistrationRecord.setCreateTime(date);                       //挂号时间[yyyy-MM-dd HH:mm:ss]
                            //0=预约挂号,1=当日挂号,2=分诊挂号
                            if (registeredDate.compareTo(DateUtils.getNowDate()) == 0) {
                                hisRegistrationRecord.setRegisteredType(IConstant.ONE.getValue());
                            } else {
                                hisRegistrationRecord.setRegisteredType(IConstant.ZERO.getValue());
                            }
                        }
                        //线下同步过来的挂号记录状态默认“1:已支付/待接诊”|需要同步病人门诊信息来更新(挂号状态)**
                        hisRegistrationRecord.setRegisteredStatus(1);
                        hisRegistrationRecord.setRegisteredSource(0);//挂号来源[0:线下,1:线上,2:自助分诊终端]
                        Func.copy(vo, hisRegistrationRecord);
                        hisRegistrationRecord.setRefundAble(vo.getRefundAble()); //挂号是否可退状态

                        newRegistrationRecordList.add(hisRegistrationRecord);
                    }
                }
            }
        }

        HisRegistrationRecord hisRegistrationRecordQuery = new HisRegistrationRecord();
        hisRegistrationRecordQuery.setIdCardNo(idCardNo);
        hisRegistrationRecordQuery.setOrgCode(orgCode);
        Map<String, Object> paramsQuery = new HashMap<>();
        paramsQuery.put("beginCreateTime", beginTime);
        paramsQuery.put("endCreateTime", endTime);
        hisRegistrationRecordQuery.setParams(paramsQuery);
        List<HisRegistrationRecord> existRegistrationRecordList = registrationRecordService.selectHisRegistrationRecordList(hisRegistrationRecordQuery);
        existRegistrationRecordList.forEach(this::refundRegistration);//微信挂号-->过期作废处理！
        //数据处理
        List<HisRegistrationRecord> updateRegList = sameRegList(existRegistrationRecordList, newRegistrationRecordList);
        List<HisRegistrationRecord> delRegList = diffRegList(existRegistrationRecordList, newRegistrationRecordList);
        List<HisRegistrationRecord> addRegList = diffRegList(newRegistrationRecordList, existRegistrationRecordList);
        if (Func.isNotEmpty(updateRegList)) {
            registrationRecordService.updateHisRegistrationRecordBatch(updateRegList);
        }
        if (Func.isNotEmpty(delRegList)) {
            List<Long> ids = delRegList.stream().map(HisRegistrationRecord::getId).distinct().collect(Collectors.toList());
            registrationRecordService.deleteHisRegistrationRecordByIds(Func.join(ids, ","));
        }
        if (Func.isNotEmpty(addRegList)) {
            registrationRecordService.insertHisRegistrationRecordBatch(addRegList);
        }
        //返回所有数据！
        updateRegList.addAll(addRegList);
        //根据身份证过滤数据！
        if (Func.isNotEmpty(idCardNo)) {
            return updateRegList.stream().filter(record -> record.getIdCardNo().equals(idCardNo)).collect(Collectors.toList());
        }
        return updateRegList;
    }

    @Override
    public void syncPrePayOutpatientExpensesBill(List<HisOutpatient> hisOutpatientList) {
        hisOutpatientList.forEach(outpatient -> {
            log.info("=========开始获取 {} 的待缴费清单==========", outpatient.getPatientName());
            String businessId = outpatient.getBusinessId();
            String orgCode = outpatient.getOrgCode();
            String orgName = outpatient.getOrgName();
            //每次更新时,查询是否已经存在
            HisOutpatientExpensesBill hisOutpatientExpensesBillQuery = new HisOutpatientExpensesBill();
            hisOutpatientExpensesBillQuery.setBusinessId(businessId);
            hisOutpatientExpensesBillQuery.setRecipeStatus(IConstant.ZERO.getValue());
            List<HisOutpatientExpensesBill> existBillList = outpatientExpensesBillService.selectHisOutpatientExpensesBillList(hisOutpatientExpensesBillQuery);
            List<HisOutpatientExpensesBill> newBillList = new ArrayList<>();
            String registeredId = outpatient.getRegisteredId();//与门诊业务ID相同
            OutpatientExpensesBill bill = new OutpatientExpensesBill();//HIS待缴费清单查询
            bill.setRegisteredId(registeredId);
            HISResult hisResult = hisClientUtil.getPrePayOutpatientExpensesBillList(bill);
            if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
                TypeReference<List<OutpatientExpensesBillVO>> typeRef = new TypeReference<List<OutpatientExpensesBillVO>>() {
                };
                List<OutpatientExpensesBillVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
                if (Func.isNotEmpty(list)) {
                    for (OutpatientExpensesBillVO vo : list) {
                        String prescriptionDetail = vo.getPrescriptionDetail();//门诊费用清单详情
                        List<ExpensesBillBO> expensesBillBOList = JSON.parseArray(prescriptionDetail, ExpensesBillBO.class);
                        vo.setExpensesBillList(expensesBillBOList);
                        //清单
                        HisOutpatientExpensesBill hisOutpatientExpensesBill = new HisOutpatientExpensesBill();
                        //处方缴费状态[0:待缴费,1:已缴费]  清单缴费之后会返回门诊ID，收费记录ID
                        hisOutpatientExpensesBill.setRecipeStatus(IConstant.ZERO.getValue());
                        hisOutpatientExpensesBill.setRegisteredId(registeredId);
                        hisOutpatientExpensesBill.setBusinessId(registeredId);
                        hisOutpatientExpensesBill.setIdCardNo(outpatient.getIdCardNo());
                        hisOutpatientExpensesBill.setPatientName(outpatient.getPatientName());
                        hisOutpatientExpensesBill.setOrgCode(orgCode);
                        hisOutpatientExpensesBill.setOrgName(orgName);
                        Func.copy(vo, hisOutpatientExpensesBill);
                        hisOutpatientExpensesBill.setBusinessId(outpatient.getBusinessId());
                        //门诊待缴费清单-详情
                        List<ExpensesBillBO> expensesBillList = vo.getExpensesBillList();
                        if (Func.isNotEmpty(expensesBillList)) {
                            List<HisOutpatientExpensesBillDetail> detailList = new ArrayList<>();
                            for (ExpensesBillBO billBO : expensesBillList) {
                                //每次更新时,查询是否已经存在
                                HisOutpatientExpensesBillDetail billDetail = new HisOutpatientExpensesBillDetail();
                                Func.copy(billBO, billDetail);
                                String billTime = billBO.getBillTime();
                                Date billDateTime = DateUtils.parseDate(billTime);
                                billDetail.setBillDetailId(billBO.getId());
                                billDetail.setCreateTime(billDateTime);
                                //费用主项冗余字段
                                hisOutpatientExpensesBill.setFeeName(billDetail.getFeeName());//新增费用名称
                                hisOutpatientExpensesBill.setOperateDept(billDetail.getOperateDept());//执行科室名称
                                hisOutpatientExpensesBill.setBillTime(billDateTime);
                                hisOutpatientExpensesBill.setCreateTime(billDateTime);
                                detailList.add(billDetail);
                            }
                            //添加详情
                            hisOutpatientExpensesBill.setHisOutpatientExpensesBillDetailList(detailList);
                            //
                            newBillList.add(hisOutpatientExpensesBill);
                        }
                    }
                    //需要更新的数据,参数顺序注意
                    List<HisOutpatientExpensesBill> updateExpensesBillList = sameExpensesBillList(existBillList, newBillList);
                    //需要删除的数据
                    List<HisOutpatientExpensesBill> delExpensesBillList = diffExpensesBillList(existBillList, newBillList);
                    //需要新增的数据
                    List<HisOutpatientExpensesBill> addExpensesBillList = diffExpensesBillList(newBillList, existBillList);
                    if (Func.isNotEmpty(updateExpensesBillList)) {
                        outpatientExpensesBillService.updateHisOutpatientExpensesBillBatch(updateExpensesBillList);
                    }
                    if (Func.isNotEmpty(delExpensesBillList)) {
                        outpatientExpensesBillService.deleteHisOutpatientExpensesBillBatch(delExpensesBillList);
                    }
                    if (Func.isNotEmpty(addExpensesBillList)) {
                        outpatientExpensesBillService.insertHisOutpatientExpensesBillBatch(addExpensesBillList);
                    }
                }
            }
        });
    }

    @Override
    public void syncOutpatient(String orgCode, List<HisRegistrationRecord> registrationRecordList, String beginTime, String endTime) {
        if (Func.isNotEmpty(registrationRecordList)) {
            registrationRecordList.forEach(registrationRecord -> {
                String businessId = registrationRecord.getBusinessId();
                String idCardNo = registrationRecord.getIdCardNo();
                //每次更新时,查询是否已经存在
                HisOutpatient hisOutpatientQuery = new HisOutpatient();
                hisOutpatientQuery.setBusinessId(businessId);
                hisOutpatientQuery.setIdCardNo(idCardNo);
                hisOutpatientQuery.setOrgCode(orgCode);
                Map<String, Object> paramsQuery = new HashMap<>();
                paramsQuery.put("beginCreateTime", beginTime);
                paramsQuery.put("endCreateTime", endTime);
                hisOutpatientQuery.setParams(paramsQuery);
                //每次更新时,查询是否已经存在
                List<HisOutpatient> existOutpatientList = outpatientService.selectHisOutpatientList(hisOutpatientQuery);
                List<HisOutpatient> newOutpatientList = new ArrayList<>();
                Outpatient outpatient = new Outpatient();
                outpatient.setOrgCode(orgCode);
                outpatient.setBusinessId(businessId);//挂号ID等于门诊业务ID
                outpatient.setIdCardNo(idCardNo);
                outpatient.setBeginTime(beginTime);
                outpatient.setEndTime(endTime);
                HISResult hisResult = hisClientUtil.getOutpatientList(outpatient);
                if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
                    TypeReference<List<OutpatientVO>> typeRef = new TypeReference<List<OutpatientVO>>() {
                    };
                    List<OutpatientVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
                    if (Func.isNotEmpty(list)) {
                        for (OutpatientVO vo : list) {
                            String _idCardNo = vo.getIdCardNo();
                            boolean validate = IdCardUtil.validate(_idCardNo);
                            if (validate) {
                                HisOutpatient hisOutpatient = new HisOutpatient();
                                hisOutpatient.setOrgCode(orgCode);
                                Func.copy(vo, hisOutpatient);
                                hisOutpatient.setRegisteredId(vo.getBusinessId());//设置门诊病人的挂号ID 其实就是门诊病人的业务ID
                                hisOutpatient.setCreateTime(DateUtils.parseDate(vo.getVisitDate()));//创建时间 <-- 门诊病人的就诊时间(日期)
                                hisOutpatient.setReceptionStatus(vo.getReceptionStatus()); //接诊状态 (-1 表示挂号已取消)
                                newOutpatientList.add(hisOutpatient);
                            } else {
                                log.info("实时获取门诊病人信息:非法身份证号码 | {} |", _idCardNo);
                            }
                        }
                    }
                }
                //需要更新的数据,参数顺序注意
                List<HisOutpatient> sameOutpatientList = sameOutpatientList(existOutpatientList, newOutpatientList);
                //需要删除的数据
                List<HisOutpatient> delOutpatientList = diffOutpatientList(existOutpatientList, newOutpatientList);
                //需要新增的数据
                List<HisOutpatient> addOutpatientList = diffOutpatientList(newOutpatientList, existOutpatientList);

                if (Func.isNotEmpty(sameOutpatientList)) {
                    outpatientService.updateHisOutpatientBatch(sameOutpatientList);
                }
                if (Func.isNotEmpty(delOutpatientList)) {
                    List<Long> ids = delOutpatientList.stream().map(HisOutpatient::getId).distinct().collect(Collectors.toList());
                    outpatientService.deleteHisOutpatientByIds(Func.join(ids, ","));
                }
                if (Func.isNotEmpty(addOutpatientList)) {
                    outpatientService.insertHisOutpatientBatch(addOutpatientList);
                }
                //过滤列表
                if (Func.isNotBlank(businessId)) {
                    Optional<HisOutpatient> outpatientOptional = newOutpatientList.stream().filter(item -> item.getBusinessId().equals(businessId)).findFirst();
                    if (outpatientOptional.isPresent()) {
                        HisOutpatient hisOutpatient = outpatientOptional.get();
                        this.updateHisRegistrationRecordStatus(hisOutpatient, registrationRecord);
                    }
                }
            });
        }
    }

    /**
     * 通过门诊病人来更新挂号状态
     *
     * @param hisOutpatient         门诊病人
     * @param hisRegistrationRecord 挂号记录
     */
    private void updateHisRegistrationRecordStatus(HisOutpatient hisOutpatient, HisRegistrationRecord hisRegistrationRecord) {
        //  门诊：接诊状态 -1已取消 0未接诊、1接诊中、2已接诊 ;
        //  挂号：状态[-1:已取消,0:新建,1:已支付/待接诊,2:已就诊,3:已退费]
        if (hisOutpatient.getReceptionStatus() == -1 || hisOutpatient.getReceptionStatus() == 2) {
            hisRegistrationRecord.setBusinessId(hisOutpatient.getBusinessId());
            hisRegistrationRecord.setRegisteredStatus(hisOutpatient.getReceptionStatus());
            int res = registrationRecordService.updateHisRegistrationRecord(hisRegistrationRecord);
            log.info("-----|门诊挂号状态|更新是否成功 :{} ------", res);
        }
        if (hisOutpatient.getReceptionStatus() == 1) {
            hisRegistrationRecord.setRegisteredStatus(2);
            hisRegistrationRecord.setBusinessId(hisOutpatient.getBusinessId());
            int res = registrationRecordService.updateHisRegistrationRecord(hisRegistrationRecord);
            log.info("-----|门诊挂号状态|更新是否成功 :{} ------", res);
        }
    }

    /**
     * 两list并集
     */
    private List<HisRegistrationRecord> sameRegList(List<HisRegistrationRecord> oldArrayList, List<HisRegistrationRecord> newArrayList) {
        return newArrayList.stream().filter(item -> oldArrayList.stream().map(HisRegistrationRecord::getRegisteredId).collect(Collectors.toList()).contains(item.getRegisteredId())).collect(Collectors.toList());
    }

    /**
     * 两list差集
     */
    private List<HisRegistrationRecord> diffRegList(List<HisRegistrationRecord> firstArrayList, List<HisRegistrationRecord> secondArrayList) {
        return firstArrayList.stream().filter(item -> !secondArrayList.stream().map(HisRegistrationRecord::getRegisteredId).collect(Collectors.toList()).contains(item.getRegisteredId())).collect(Collectors.toList());
    }

    private List<HisOutpatient> sameOutpatientList(List<HisOutpatient> oldArrayList, List<HisOutpatient> newArrayList) {
        return newArrayList.stream().filter(item -> oldArrayList.stream().map(HisOutpatient::getBusinessId).collect(Collectors.toList()).contains(item.getBusinessId())).collect(Collectors.toList());
    }

    private List<HisOutpatient> diffOutpatientList(List<HisOutpatient> firstArrayList, List<HisOutpatient> secondArrayList) {
        return firstArrayList.stream().filter(item -> !secondArrayList.stream().map(HisOutpatient::getBusinessId).collect(Collectors.toList()).contains(item.getBusinessId())).collect(Collectors.toList());
    }

    private List<HisInpatient> sameInpatientList(List<HisInpatient> oldArrayList, List<HisInpatient> newArrayList) {
        return newArrayList.stream().filter(item -> oldArrayList.stream().map(HisInpatient::getBusinessId).collect(Collectors.toList()).contains(item.getBusinessId())).collect(Collectors.toList());
    }

    private List<HisInpatient> diffInpatientList(List<HisInpatient> firstArrayList, List<HisInpatient> secondArrayList) {
        return firstArrayList.stream().filter(item -> !secondArrayList.stream().map(HisInpatient::getBusinessId).collect(Collectors.toList()).contains(item.getBusinessId())).collect(Collectors.toList());
    }

    private List<HisOutpatientExpensesBill> sameExpensesBillList(List<HisOutpatientExpensesBill> oldArrayList, List<HisOutpatientExpensesBill> newArrayList) {
        return newArrayList.stream().filter(item -> oldArrayList.stream().map(HisOutpatientExpensesBill::getRecipeId).collect(Collectors.toList()).contains(item.getRecipeId())).collect(Collectors.toList());
    }

    private List<HisOutpatientExpensesBill> diffExpensesBillList(List<HisOutpatientExpensesBill> firstArrayList, List<HisOutpatientExpensesBill> secondArrayList) {
        return firstArrayList.stream().filter(item -> !secondArrayList.stream().map(HisOutpatientExpensesBill::getRecipeId).collect(Collectors.toList()).contains(item.getRecipeId())).collect(Collectors.toList());
    }

    private List<HisPatientExpenses> samePatientExpensesList(List<HisPatientExpenses> oldArrayList, List<HisPatientExpenses> newArrayList) {
        return newArrayList.stream().filter(item -> oldArrayList.stream().map(HisPatientExpenses::getBusinessId).collect(Collectors.toList()).contains(item.getBusinessId())).collect(Collectors.toList());
    }

    private List<HisPatientExpenses> diffPatientExpensesList(List<HisPatientExpenses> firstArrayList, List<HisPatientExpenses> secondArrayList) {
        return firstArrayList.stream().filter(item -> !secondArrayList.stream().map(HisPatientExpenses::getBusinessId).collect(Collectors.toList()).contains(item.getBusinessId())).collect(Collectors.toList());
    }


    /**
     * 调用HIS退号接口
     *
     * @param hisRegistrationRecord 挂号记录
     * @return 退挂号是否成功
     */
    private void refundRegistration(HisRegistrationRecord hisRegistrationRecord) {
        String userId = hisRegistrationRecord.getUserId();
        if (Func.isNotEmpty(userId)) {
            Date registeredDate = hisRegistrationRecord.getRegisteredDate();
            Integer registeredStatus = hisRegistrationRecord.getRegisteredStatus();
            //挂号日期与当前日期比较，待就诊的挂号记录
            if (Func.isNotEmpty(registeredDate) && Func.isNotEmpty(registeredStatus) && DateUtils.getNowDate().compareTo(registeredDate) > 0 && registeredStatus == IConstant.ONE.getValue()) {
                log.info("====处理过期未就诊的挂号记录====");
                //HIS退挂号参数
                RegistrationCancel registrationCancel = new RegistrationCancel();
                registrationCancel.setRegisteredId(hisRegistrationRecord.getRegisteredId());
                registrationCancel.setUserId(hisRegistrationRecord.getUserId());
                registrationCancel.setVendorId(hisRegistrationRecord.getVendorId());
                HISResult hisResult = hisClientUtil.cancelRegistration(registrationCancel);
                if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
                    log.info("退挂号成功 : {}", hisResult.getMsg());
                    hisRegistrationRecord.setRefundAble(IConstant.ZERO.getValue());//[0:否,1:是]
                    hisRegistrationRecord.setRegisteredStatus(-1);//状态[-1:已取消,0:新建,1:已支付/待接诊,2:已就诊,3:已退费]
                    hisRegistrationRecord.setCancelTime(Func.formatDateTime(new Date()));
                    log.info("自动退号成功");
                }
            }
        }
    }

    /**
     * 同步住院病人信息
     *
     * @param orgCode      机构编码
     * @param idCardNoList 身份证列表
     * @param beginTime    开始时间,格式:yyyy-MM-dd HH:mm:ss
     * @param endTime      结束时间,格式:yyyy-MM-dd HH:mm:ss
     */
    @Override
    public void syncInpatient(String orgCode, List<String> idCardNoList, String beginTime, String endTime) {
        //机构信息
        HisHospitalInfo hospitalInfo = hospitalInfoService.selectHisHospitalInfoByOrgCode(orgCode);
        String orgName = Func.isNotEmpty(hospitalInfo) ? hospitalInfo.getOrgName() : "";
        //每次更新时,查询是否已经存在
        HisInpatient hisInpatientQuery = new HisInpatient();
        hisInpatientQuery.setOrgCode(orgCode);
        Map<String, Object> paramsQuery = new HashMap<>();
        paramsQuery.put("beginAdmissionDate", beginTime);
        paramsQuery.put("endAdmissionDate", endTime);
        hisInpatientQuery.setParams(paramsQuery);
        //每次更新时,查询是否已经存在
        List<HisInpatient> existInpatientList = inpatientService.selectHisInpatientList(hisInpatientQuery);
        List<HisInpatient> newInpatientList = new ArrayList<>();
        if (Func.isNotEmpty(idCardNoList)) {
            idCardNoList.forEach(idCardNo -> {
                Inpatient inpatient = new Inpatient();
                inpatient.setOrgCode(orgCode);
                inpatient.setIdCardNo(idCardNo);
                //inpatient.setBusinessId(businessId);//业务ID是门诊，不能用来查询住院.
                inpatient.setBeginTime(beginTime);
                inpatient.setEndTime(endTime);
                inpatient.setState(IConstant.ZERO.getValue()); //默认
                HISResult hisResult = hisClientUtil.getInpatientList(inpatient);
                if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
                    TypeReference<List<InpatientVO>> typeRef = new TypeReference<List<InpatientVO>>() {
                    };
                    List<InpatientVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
                    if (Func.isNotEmpty(list)) {
                        for (InpatientVO vo : list) {
                            HisInpatient hisInpatient = new HisInpatient();
                            //创建时间 -> 为患者入院时间
                            hisInpatient.setCreateTime(DateUtils.parseDate(vo.getAdmissionDate()));
                            hisInpatient.setOrgCode(orgCode);
                            hisInpatient.setOrgName(orgName);
                            Func.copy(vo, hisInpatient);
                            //在院状态 [0:在院无床;1:在院有床;2:出院未结算;3:出院已结算;-1:撤销入院]
                            hisInpatient.setInpatientStatus(Integer.valueOf(vo.getInpatientStatus()));
                            newInpatientList.add(hisInpatient);
                        }
                    }
                }
            });

            //需要更新的数据,参数顺序注意
            List<HisInpatient> updateInpatientList = this.sameInpatientList(existInpatientList, newInpatientList);
            //需要删除的数据
            List<HisInpatient> delInpatientList = diffInpatientList(existInpatientList, newInpatientList);
            //需要新增的数据
            List<HisInpatient> addInpatientList = diffInpatientList(newInpatientList, existInpatientList);
            if (Func.isNotEmpty(updateInpatientList)) {
                inpatientService.updateHisInpatientBatch(updateInpatientList);
            }
            if (Func.isNotEmpty(delInpatientList)) {
                List<Long> ids = delInpatientList.stream().map(HisInpatient::getId).distinct().collect(Collectors.toList());
                inpatientService.deleteHisInpatientByIds(Func.join(ids, ","));
            }
            if (Func.isNotEmpty(addInpatientList)) {
                inpatientService.insertHisInpatientBatch(addInpatientList);
            }
        }
    }


    /**
     * 同步门诊病人费用记录
     *
     * @param hisOutpatientList 门诊病人
     */
    @Override
    public void syncOutpatientExpenses(List<HisOutpatient> hisOutpatientList) {
        hisOutpatientList.forEach(hisOutpatient -> {
            //每次更新时,查询是否已经存在
            HisPatientExpenses hisPatientExpensesQuery = new HisPatientExpenses();
            hisPatientExpensesQuery.setBusinessId(hisOutpatient.getBusinessId());//门诊业务ID
            hisPatientExpensesQuery.setExpenseBusinessNo(hisOutpatient.getOutpatientNumber());
            List<HisPatientExpenses> existPatientExpensesList = patientExpensesService.selectHisPatientExpensesList(hisPatientExpensesQuery);
            List<HisPatientExpenses> newPatientExpensesList = new ArrayList<>();
            //
            OutpatientFee outpatientFee = new OutpatientFee();
            outpatientFee.setBusinessId(hisOutpatient.getBusinessId());
            outpatientFee.setOutpatientNumber(hisOutpatient.getOutpatientNumber());
            //HIS所有门诊费用
            HISResult hisResult = hisClientUtil.getOutpatientFeeList(outpatientFee);
            if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
                TypeReference<List<OutpatientFeeVO>> typeRef = new TypeReference<List<OutpatientFeeVO>>() {
                };
                List<OutpatientFeeVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
                if (Func.isNotEmpty(list)) {
                    list.forEach(vo -> vo.setBusinessId(hisOutpatient.getBusinessId()));
                    // 提取费用明细字段,需要注意的是：(List -> Map ) toMap 如果集合对象有重复的key，会报错Duplicate key .... apple1,apple12的id都为1；可以用 (k1,k2)->k1 来设置，如果有重复的key,则保留key1,舍弃key2
                    Map<String, String> outpatientFeeVOmap = list.stream().collect(Collectors.toMap(OutpatientFeeVO::getBusinessId, OutpatientFeeVO::getOutpatientNumber, (k1, k2) -> k1));
                    //只有一个费用主项
                    outpatientFeeVOmap.forEach((businessIdN, outpatientNumberN) -> {
                        HisPatientExpenses hisPatientExpenses = new HisPatientExpenses();
                        hisPatientExpenses.setBusinessId(businessIdN);
                        hisPatientExpenses.setExpenseBusinessNo(outpatientNumberN);
                        hisPatientExpenses.setExpenseType(IConstant.ONE.getValue());
                        hisPatientExpenses.setOrgCode(hisOutpatient.getOrgCode());
                        hisPatientExpenses.setOrgName(hisOutpatient.getOrgName());
                        hisPatientExpenses.setIdCardNo(hisOutpatient.getIdCardNo());
                        hisPatientExpenses.setPatientName(hisOutpatient.getPatientName());
                        //开单科室和医生 暂时取 门诊记录里面的
                        hisPatientExpenses.setBillDept(hisOutpatient.getDeptName());
                        hisPatientExpenses.setBillDoctor(hisOutpatient.getDoctorName());
                        //费用明细详情
                        List<HisPatientExpensesDetail> expensesDetailList = new ArrayList<>();
                        final Date[] tempTime = {DateUtils.getNowDate()};
                        list.forEach(outpatientFeeVO -> {
                            Date billDateTime = DateUtils.parseDate(outpatientFeeVO.getBillTime());
                            HisPatientExpensesDetail patientExpensesDetail = new HisPatientExpensesDetail();
                            patientExpensesDetail.setCreateTime(billDateTime);
                            Func.copy(outpatientFeeVO, patientExpensesDetail);
                            expensesDetailList.add(patientExpensesDetail);
                            tempTime[0] = billDateTime;
                        });
                        hisPatientExpenses.setCreateTime(tempTime[0]);//设置主项的开单时间
                        hisPatientExpenses.setHisPatientExpensesDetailList(expensesDetailList);

                        newPatientExpensesList.add(hisPatientExpenses);
                    });
                }
            }

            //需要更新的数据,参数顺序注意
            List<HisPatientExpenses> updatePatientExpensesList = samePatientExpensesList(existPatientExpensesList, newPatientExpensesList);
            //需要删除的数据
            List<HisPatientExpenses> delPatientExpensesList = diffPatientExpensesList(existPatientExpensesList, newPatientExpensesList);
            //需要新增的数据
            List<HisPatientExpenses> addPatientExpensesList = diffPatientExpensesList(newPatientExpensesList, existPatientExpensesList);

            if (Func.isNotEmpty(updatePatientExpensesList)) {
                patientExpensesService.updateHisPatientExpensesBatch(updatePatientExpensesList);
            }
            if (Func.isNotEmpty(delPatientExpensesList)) {
                patientExpensesService.deleteHisPatientExpensesBatch(delPatientExpensesList);
            }
            if (Func.isNotEmpty(addPatientExpensesList)) {
                patientExpensesService.insertHisPatientExpensesBatch(addPatientExpensesList);
            }
        });
    }

    @Override
    public void syncDoctorSchedule(List<HisDoctor> doctorList, String beginTime, String endTime) {
        doctorList.forEach(doctor -> {
            List<HisDoctorSchedule> existDoctorScheduleList = new ArrayList<>();
            List<HisDoctorSchedule> newDoctorScheduleList = new ArrayList<>();
            DoctorSchedule doctorSchedule = new DoctorSchedule();
            doctorSchedule.setOrgCode(doctor.getOrgCode());
            doctorSchedule.setDoctorId(doctor.getDoctorId());
            //查询日期限制在7天内，yyyy-MM-dd
            doctorSchedule.setBeginTime(beginTime);
            doctorSchedule.setEndTime(endTime);
            HISResult hisResult = hisClientUtil.getDoctorScheduleList(doctorSchedule);
            if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
                TypeReference<List<DoctorScheduleVO>> typeRef = new TypeReference<List<DoctorScheduleVO>>() {
                };
                List<DoctorScheduleVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
                if (Func.isNotEmpty(list)) {
                    for (DoctorScheduleVO vo : list) {
                        String shift = Func.trimWhitespace(vo.getScheduleShift());
                        Date scheduleDate = Func.parse(vo.getScheduleDate(), DateUtils.YYYY_MM_DD);
                        //班次判断是否存在 --老数据
                        HisDoctorSchedule hisDoctorScheduleQuery = new HisDoctorSchedule();
                        hisDoctorScheduleQuery.setOrgCode(doctor.getOrgCode());
                        hisDoctorScheduleQuery.setDoctorId(vo.getDoctorId());
                        hisDoctorScheduleQuery.setScheduleDate(scheduleDate);
                        hisDoctorScheduleQuery.setScheduleShift(shift);
                        HisDoctorSchedule hisDoctorSchedule = doctorScheduleService.selectHisDoctorSchedule(hisDoctorScheduleQuery);
                        if (Func.isNotEmpty(hisDoctorSchedule)) {
                            existDoctorScheduleList.add(hisDoctorSchedule);
                        } else {
                            hisDoctorSchedule = new HisDoctorSchedule();
                            hisDoctorSchedule.setScheduleDate(scheduleDate);
                            hisDoctorSchedule.setOrgCode(doctor.getOrgCode());
                            hisDoctorSchedule.setOrgName(doctor.getOrgName());
                            hisDoctorSchedule.setCreateTime(DateUtils.getNowDate());
                            hisDoctorSchedule.setIsShow(IConstant.ONE.getValue());
                            Func.copy(vo, hisDoctorSchedule);
                            if (HisConstant.MORNING.equals(shift)) {
                                hisDoctorSchedule.setScheduleTag(IConstant.ONE.getValue());
                            }
                            if (HisConstant.AFTERNOON.equals(shift)) {
                                hisDoctorSchedule.setScheduleTag(IConstant.TWO.getValue());
                            }
                            newDoctorScheduleList.add(hisDoctorSchedule);
                        }
                    }
                }
            }

            //需要更新的数据,参数顺序注意
            List<HisDoctorSchedule> sameDoctorScheduleList = sameDoctorScheduleList(existDoctorScheduleList, newDoctorScheduleList);
            //需要删除的数据
            List<HisDoctorSchedule> delDoctorScheduleList = diffDoctorScheduleList(existDoctorScheduleList, newDoctorScheduleList);
            //需要新增的数据
            List<HisDoctorSchedule> addDoctorScheduleList = diffDoctorScheduleList(newDoctorScheduleList, existDoctorScheduleList);

            if (Func.isNotEmpty(sameDoctorScheduleList)) {
                doctorScheduleService.updateHisDoctorScheduleBatch(sameDoctorScheduleList);
            }
            if (Func.isNotEmpty(delDoctorScheduleList)) {
                List<Long> ids = delDoctorScheduleList.stream().map(HisDoctorSchedule::getId).distinct().collect(Collectors.toList());
                doctorScheduleService.deleteHisDoctorScheduleByIds(Func.join(ids, ","));
            }
            if (Func.isNotEmpty(addDoctorScheduleList)) {
                doctorScheduleService.insertHisDoctorScheduleBatch(addDoctorScheduleList);
            }

        });
    }

    private List<HisDoctorSchedule> sameDoctorScheduleList(List<HisDoctorSchedule> oldArrayList, List<HisDoctorSchedule> newArrayList) {
        return newArrayList.stream().filter(item -> oldArrayList.stream().map(
                e -> e.getOrgCode() + "&" + e.getDoctorId() + "&" + e.getScheduleDate() + "&" + e.getScheduleShift()
        ).collect(Collectors.toList())
                .contains(
                        item.getOrgCode() + "&" + item.getDoctorId() + "&" + item.getScheduleDate() + "&" + item.getScheduleShift()
                )).collect(Collectors.toList());
    }

    private List<HisDoctorSchedule> diffDoctorScheduleList(List<HisDoctorSchedule> firstArrayList, List<HisDoctorSchedule> secondArrayList) {
        return firstArrayList.stream().filter(item -> !secondArrayList.stream().map(
                e -> e.getOrgCode() + "&" + e.getDoctorId() + "&" + e.getScheduleDate() + "&" + e.getScheduleShift()
        ).collect(Collectors.toList()).contains(
                item.getOrgCode() + "&" + item.getDoctorId() + "&" + item.getScheduleDate() + "&" + item.getScheduleShift()
        )).collect(Collectors.toList());
    }

}
