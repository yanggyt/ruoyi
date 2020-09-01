package com.ruoyi.quartz.task;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bending.core.his.bo.ExpensesBillBO;
import com.bending.core.his.bo.InspectionReportBO;
import com.bending.core.his.bo.InspectionReportItemBO;
import com.bending.core.his.common.HISResult;
import com.bending.core.his.constant.HisConstant;
import com.bending.core.his.entity.ExpenseBill;
import com.bending.core.his.entity.InspectionApplyForm;
import com.bending.core.his.entity.InspectionReport;
import com.bending.core.his.entity.OutpatientPaid;
import com.bending.core.his.vo.InspectionApplyFormVO;
import com.bending.core.his.vo.InspectionReportVO;
import com.bending.core.his.vo.OutpatientPaidVO;
import com.bending.core.utils.HISClientUtil;
import com.ruoyi.common.annotation.TraceId;
import com.ruoyi.common.func.Func;
import com.ruoyi.common.func.IConstant;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.his.domain.*;
import com.ruoyi.his.service.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

/**
 * HIS 同步定时任务
 */
@Slf4j
@Component("syncHisTask")
public class SyncHisTask {

    @Resource
    private HISClientUtil hisClientUtil;
    @Resource
    private IHisService hisService;
    @Resource
    private IHisRegistrationRecordService iHisRegistrationRecordService;
    @Resource
    private IHisHospitalInfoService iHisHospitalInfoService;
    @Resource
    private IHisDoctorScheduleService iHisDoctorScheduleService;
    @Resource
    private IHisDoctorService iHisDoctorService;
    @Resource
    private IHisOutpatientService iHisOutpatientService;
    @Resource
    private IHisInpatientService iHisInpatientService;
    @Resource
    private IHisOutpatientExpensesBillService iHisOutpatientExpensesBillService;
    @Resource
    private IHisInspectionApplyService iHisInspectionApplyService;
    @Resource
    private IHisInspectionReportService iHisInspectionReportService;

    /**
     * <p>查询挂号记录:有效的挂号记录,过滤身份证不为空的、挂号成功就有挂号ID</p>
     *
     * @param orgCode   机构ID
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 挂号记录列表
     */
    private List<HisRegistrationRecord> registrationRecordQuery(String orgCode, Date beginTime, Date endTime) {
        //有效的挂号记录,过滤身份证不为空的、挂号成功就有挂号ID
        HisRegistrationRecord hisRegistrationRecordQuery = new HisRegistrationRecord();
        hisRegistrationRecordQuery.setOrgCode(orgCode);
        Map<String, Object> paramsQuery = new HashMap<>();
        paramsQuery.put("beginCreateTime", Func.formatDate(beginTime));
        paramsQuery.put("endCreateTime", Func.formatDate(endTime));
        hisRegistrationRecordQuery.setParams(paramsQuery);
        List<HisRegistrationRecord> registrationRecordList = iHisRegistrationRecordService.selectHisRegistrationRecordList(hisRegistrationRecordQuery);
        //有效的挂号记录,过滤身份证不为空的、挂号成功就有挂号ID
        return registrationRecordList.stream().filter(registrationRecord -> null != registrationRecord.getRegisteredId() && null != registrationRecord.getIdCardNo()).collect(toList());
    }

    /**
     * <p>查询门诊病人记录:有效的门诊病人,过滤接诊状态不为零的、有挂号ID</p>
     * 门诊病人：接诊状态 -1已取消 0未接诊、1接诊中、2已接(结)诊
     * 过滤不为空的挂号ID，接诊状态为-1，1，2(!=0)
     *
     * @param orgCode   机构ID
     * @param beginTime 开始时间
     * @param endTime   结束时间
     * @return 挂号记录列表
     */
    private List<HisOutpatient> hisOutpatientQuery(String orgCode, Date beginTime, Date endTime) {
        //门诊病人
        HisOutpatient hisOutpatientQuery = new HisOutpatient();
        hisOutpatientQuery.setOrgCode(orgCode);
        Map<String, Object> paramsQuery = new HashMap<>();
        paramsQuery.put("beginCreateTime", Func.formatDate(beginTime));
        paramsQuery.put("endCreateTime", Func.formatDate(endTime));
        hisOutpatientQuery.setParams(paramsQuery);
        List<HisOutpatient> outpatientList = iHisOutpatientService.selectHisOutpatientList(hisOutpatientQuery);
        //过滤不为空的挂号ID，接诊状态为-1，1，2(!=0)
        return outpatientList.stream().filter(hisOutpatient -> null != hisOutpatient.getRegisteredId() && 0 != hisOutpatient.getReceptionStatus()).collect(toList());
    }


    /**
     * 主题：同步机构所有门诊挂号记录
     * 业务：同步机构的所有线上和线下的挂号记录，需要指定医院ID,日期等参数（多参数使用JSON数据）
     * 1：数据同步的挂号（默认状态）,后面根据门诊接诊来更新该状态；
     * 2：后续还需要绑定用户ID和就诊人ID，方便信息推送
     */
    @TraceId
    public void registrationRecord(String params) {
        if (Func.isNotBlank(params)) {
            List<TaskParam> taskParams = JSON.parseArray(params, TaskParam.class);
            if (Func.isNotEmpty(taskParams)) {
                //计算同步时间-->
                long startTime = System.currentTimeMillis();
                taskParams.forEach(taskParam -> {
                    String orgCode = taskParam.getOrgCode();
                    Integer days = taskParam.getDays();
                    Date endTime = DateUtils.getNowDate();
                    Date beginTime = Func.minusDays(endTime, days);
                    hisService.syncRegistrationRecord(orgCode, "", Func.formatDate(beginTime), Func.formatDate(endTime));
                });
                long entTime = System.currentTimeMillis();
                log.info("同步挂号记录:共耗时 {} 秒", (float) (entTime - startTime) / 1000);
            }
        }
    }

    /**
     * 同步门诊病人
     */
    @TraceId
    public void outpatientTask(String params) {
        if (Func.isNotBlank(params)) {
            List<TaskParam> taskParams = JSON.parseArray(params, TaskParam.class);
            if (Func.isNotEmpty(taskParams)) {
                //计算同步时间-->
                long startTime = System.currentTimeMillis();
                taskParams.forEach(taskParam -> {
                    String orgCode = taskParam.getOrgCode();
                    Integer days = taskParam.getDays();
                    Date nowDate = DateUtils.getNowDate();
                    Date beginTime = Func.minusDays(nowDate, days);
                    Date endTime = Func.plusDays(nowDate, 1);
                    //有效的挂号记录,过滤身份证不为空的、挂号成功就有挂号ID
                    List<HisRegistrationRecord> registrationRecordList = this.registrationRecordQuery(orgCode, beginTime, endTime);
                    if (Func.isNotEmpty(registrationRecordList)) {
                        hisService.syncOutpatient(orgCode, registrationRecordList, Func.formatDateTime(beginTime), Func.formatDateTime(endTime));
                    }
                    long entTime = System.currentTimeMillis();
                    log.info("同步门诊病人:共耗时 {} 秒", (float) (entTime - startTime) / 1000);
                });
            }
        }
    }


    /**
     * 住院病人同步
     */
    @TraceId
    public void inpatientTask(String params) {
        if (Func.isNotBlank(params)) {
            //计算同步时间-->
            long startTime = System.currentTimeMillis();
            List<TaskParam> taskParams = JSON.parseArray(params, TaskParam.class);
            if (Func.isNotEmpty(taskParams)) {
                taskParams.forEach(taskParam -> {
                    String orgCode = taskParam.getOrgCode();
                    Integer days = taskParam.getDays();
                    Date nowDate = DateUtils.getNowDate();
                    Date beginTime = Func.minusDays(nowDate, days);
                    Date endTime = Func.plusDays(nowDate, 1);
                    List<String> idCardNoList = new ArrayList<>();//取出身份证信息
                    //有效的挂号记录,过滤身份证不为空的、挂号成功就有挂号ID
                    List<HisRegistrationRecord> registrationRecordList = this.registrationRecordQuery(orgCode, beginTime, endTime);
                    if (Func.isNotEmpty(registrationRecordList)) {
                        List<String> list = registrationRecordList.stream().map(HisRegistrationRecord::getIdCardNo).distinct().collect(toList());
                        idCardNoList.addAll(list);
                    }
                    //过滤不为空的挂号ID，接诊状态为-1，1，2(!=0)
                    List<HisOutpatient> hisOutpatientList = this.hisOutpatientQuery(orgCode, beginTime, endTime);
                    if (Func.isNotEmpty(hisOutpatientList)) {
                        List<String> list = hisOutpatientList.stream().map(HisOutpatient::getIdCardNo).distinct().collect(toList());
                        idCardNoList.addAll(list);
                    }
                    // 去重并集
                    List<String> idCardNoListAll = idCardNoList.stream().distinct().collect(toList());
                    hisService.syncInpatient(orgCode, idCardNoListAll, Func.formatDateTime(beginTime), Func.formatDateTime(endTime));
                });
            }
            long entTime = System.currentTimeMillis();
            log.info("同步住院病人:共耗时 {} 秒", (float) (entTime - startTime) / 1000);
        }
    }

    /**
     * 门诊待缴费清单
     * ***** 注意：待缴费清单存在变化！就需要同步更新远程数据和本地数据
     */
    @TraceId
    public void expensesBillTask(String params) {
        //计算同步时间-->
        long startTime = System.currentTimeMillis();
        if (Func.isNotBlank(params)) {
            List<TaskParam> taskParams = JSON.parseArray(params, TaskParam.class);
            if (Func.isNotEmpty(taskParams)) {
                taskParams.forEach(taskParam -> {
                    Integer days = taskParam.getDays();
                    Date nowDate = DateUtils.getNowDate();
                    Date beginTime = Func.minusDays(nowDate, days);
                    Date endTime = Func.plusDays(nowDate, 1);
                    List<HisOutpatient> hisOutpatientList = this.hisOutpatientQuery(taskParam.getOrgCode(), beginTime, endTime);
                    if (Func.isNotEmpty(hisOutpatientList)) {
                        hisService.syncPrePayOutpatientExpensesBill(hisOutpatientList);
                    }
                });
            }
        }
        long entTime = System.currentTimeMillis();
        log.info("同步待缴费清单:共耗时 {} 秒", (float) (entTime - startTime) / 1000);
    }

    /**
     * 主题:同步门诊待交费清单的所有状态
     * 调用
     * 30-81接口 已缴费列表获取
     * #30-82接口 获取缴费清单明细
     */
    @TraceId
    public void outpatientFeeTask(String params) {
        if (Func.isNotBlank(params)) {
            List<TaskParam> taskParams = JSON.parseArray(params, TaskParam.class);
            if (Func.isNotEmpty(taskParams)) {
                taskParams.forEach(taskParam -> {
                    Date nowDate = DateUtils.getNowDate();
                    Date beginTime = Func.minusDays(nowDate, taskParam.getDays());
                    Date endTime = Func.plusDays(nowDate, 1);
                    List<HisOutpatient> hisOutpatientList = this.hisOutpatientQuery(taskParam.getOrgCode(), beginTime, endTime);
                    hisOutpatientList.forEach(hisOutpatient -> {
                        String businessId = hisOutpatient.getBusinessId();
                        OutpatientPaid outpatientPaid = new OutpatientPaid();
                        outpatientPaid.setBusinessId(businessId);
                        HISResult hisResult = hisClientUtil.getOutpatientPaidList(outpatientPaid);
                        if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
                            TypeReference<List<OutpatientPaidVO>> typeRef = new TypeReference<List<OutpatientPaidVO>>() {
                            };
                            List<OutpatientPaidVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
                            if (Func.isNotEmpty(list)) {
                                for (OutpatientPaidVO vo : list) {
                                    ExpenseBill expenseBill = new ExpenseBill(); //30-82 获取缴费清单明细
                                    String recordId = vo.getRecordId();  // 根据收费记录ID判断 --> 获取缴费清单明细
                                    String payTime = vo.getOperateDate(); //操作时间 缴费时间
                                    expenseBill.setChargeRecordId(recordId);
                                    HISResult result = hisClientUtil.getOutpatientBillDetail(expenseBill);
                                    if (HisConstant.RESULT_SUCCESS_CODE.equals(result.getResult())) {
                                        TypeReference<List<ExpensesBillBO>> typeRef1 = new TypeReference<List<ExpensesBillBO>>() {
                                        };
                                        List<ExpensesBillBO> billBOList = JSON.parseObject(result.getMsg(), typeRef1);
                                        if (Func.isNotEmpty(billBOList)) {
                                            billBOList.forEach(expensesBillBO -> {
                                                String recipeId = expensesBillBO.getRecipeId();
                                                HisOutpatientExpensesBill hisOutpatientExpensesBillQuery = new HisOutpatientExpensesBill();
                                                hisOutpatientExpensesBillQuery.setRecipeId(recipeId);
                                                HisOutpatientExpensesBill hisOutpatientExpensesBill = iHisOutpatientExpensesBillService.selectHisOutpatientExpensesBill(hisOutpatientExpensesBillQuery);
                                                if (null != hisOutpatientExpensesBill) {
                                                    hisOutpatientExpensesBill.setRecipeStatus(IConstant.ONE.getValue());
                                                    hisOutpatientExpensesBill.setChargeRecordId(recordId);
                                                    hisOutpatientExpensesBill.setPayTime(payTime);
                                                    iHisOutpatientExpensesBillService.updateHisOutpatientExpensesBillStatus(hisOutpatientExpensesBill);
                                                }
                                            });
                                        }
                                    }
                                }
                            }
                        }
                    });
                });
            }
        }
    }

    /**
     * 门诊病人费用记录
     */
    @TraceId
    public void outPatientExpensesTask(String params) {
        //计算同步时间-->
        long startTime = System.currentTimeMillis();
        if (Func.isNotBlank(params)) {
            List<TaskParam> taskParams = JSON.parseArray(params, TaskParam.class);
            if (Func.isNotEmpty(taskParams)) {
                taskParams.forEach(taskParam -> {
                    String orgCode = taskParam.getOrgCode();
                    Integer days = taskParam.getDays();
                    Date nowDate = DateUtils.getNowDate();
                    Date beginTime = Func.minusDays(nowDate, days);
                    Date endTime = Func.plusDays(nowDate, 1);
                    List<HisOutpatient> hisOutpatientList = this.hisOutpatientQuery(orgCode, beginTime, endTime);
                    if (Func.isNotEmpty(hisOutpatientList)) {
                        hisService.syncOutpatientExpenses(hisOutpatientList);
                    }
                });
            }
        }
        long entTime = System.currentTimeMillis();
        log.info("同步门诊病人费用:共耗时 {} 秒", (float) (entTime - startTime) / 1000);
    }

    /**
     * 门诊病人 - 检查检验申请获取
     */
    @TraceId
    public void inspectionApplyTask(String params) {
        if (Func.isNotBlank(params)) {
            List<TaskParam> taskParams = JSON.parseArray(params, TaskParam.class);
            if (Func.isNotEmpty(taskParams)) {
                taskParams.forEach(taskParam -> {
                    String orgCode = taskParam.getOrgCode();
                    Integer days = taskParam.getDays();
                    Date nowDate = DateUtils.getNowDate();
                    Date beginTime = Func.minusDays(nowDate, days);
                    Date endTime = Func.plusDays(nowDate, 1);
                    //机构信息-虚拟操作员
                    HisHospitalInfo hospitalInfo = iHisHospitalInfoService.selectHisHospitalInfoByOrgCode(orgCode);
                    String userId = hospitalInfo.getVmUserId();
                    //门诊病人：接诊状态 -1已取消 0未接诊、1接诊中、2已接(结)诊
                    List<HisOutpatient> outpatientList = this.hisOutpatientQuery(orgCode, beginTime, endTime);
                    //过滤不为空的挂号ID，接诊状态为-1，1，2(!=0)
                    Map<String, String> map = outpatientList.stream().collect(Collectors.toMap(HisOutpatient::getBusinessId, HisOutpatient::getIdCardNo, (k1, k2) -> k2));
                    applyList(orgCode, userId, map);
                });
            }
        }
    }


    /**
     * 住院病人 - 检查检验申请获取
     */
    @TraceId
    public void inspectionApplyForInpatientTask(String params) {
        if (Func.isNotBlank(params)) {
            List<TaskParam> taskParams = JSON.parseArray(params, TaskParam.class);
            if (Func.isNotEmpty(taskParams)) {
                taskParams.forEach(taskParam -> {
                    String orgCode = taskParam.getOrgCode();
                    Integer days = taskParam.getDays();
                    Date nowDate = DateUtils.getNowDate();
                    Date beginTime = Func.minusDays(nowDate, days);
                    Date endTime = Func.plusDays(nowDate, 1);
                    //机构信息-虚拟操作员
                    HisHospitalInfo hospitalInfo = iHisHospitalInfoService.selectHisHospitalInfoByOrgCode(orgCode);
                    String userId = hospitalInfo.getVmUserId();

                    HisInpatient hisInpatientQuery = new HisInpatient();
                    hisInpatientQuery.setOrgCode(orgCode);
                    Map<String, Object> paramsQuery = new HashMap<>();
                    paramsQuery.put("beginCreateTime", Func.formatDate(beginTime));
                    paramsQuery.put("endCreateTime", Func.formatDate(endTime));
                    hisInpatientQuery.setParams(paramsQuery);
                    List<HisInpatient> inpatientList = iHisInpatientService.selectHisInpatientList(hisInpatientQuery);
                    //过滤不为空的挂号ID，接诊状态为-1，1，2(!=0)
                    Map<String, String> map = inpatientList.stream().filter(hisInpatient -> null != hisInpatient.getBusinessId()).collect(Collectors.toMap(HisInpatient::getBusinessId, HisInpatient::getIdCardNo, (k1, k2) -> k2));
                    applyList(orgCode, userId, map);
                });
            }
        }
    }

    private void applyList(String orgCode, String userId, Map<String, String> map) {
        List<HisInspectionApply> hisInspectionApplyList = new ArrayList<>();
        map.forEach((businessId, idCardNo) -> {
            //检查检验 查询申请表
            InspectionApplyForm applyForm = new InspectionApplyForm();
            applyForm.setBusinessId(businessId);
            HISResult hisResult = hisClientUtil.getInspectionApplyFormList(applyForm);
            if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
                TypeReference<List<InspectionApplyFormVO>> typeRef = new TypeReference<List<InspectionApplyFormVO>>() {
                };
                List<InspectionApplyFormVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
                if (Func.isNotEmpty(list)) {
                    for (InspectionApplyFormVO vo : list) {
                        String applyId = vo.getApplyId();
                        HisInspectionApply hisInspectionApplyQuery = new HisInspectionApply();
                        hisInspectionApplyQuery.setApplyId(applyId);
                        hisInspectionApplyQuery.setBusinessId(businessId);
                        HisInspectionApply hisInspectionApply = iHisInspectionApplyService.selectHisInspectionApply(hisInspectionApplyQuery);
                        if (null == hisInspectionApply) {
                            hisInspectionApply = new HisInspectionApply();
                            hisInspectionApply.setApplyId(applyId);
                            hisInspectionApply.setApplyItemName(vo.getApplyItemName());
                            hisInspectionApply.setOperator(vo.getOperator());
                            hisInspectionApply.setUserId(userId);
                            hisInspectionApply.setOrgCode(orgCode);
                            hisInspectionApply.setBusinessId(businessId);
                            hisInspectionApply.setIdCardNo(idCardNo);
                            Date billTime = Func.parse(vo.getBillTime(), "yyyy-MM-dd HH:mm");
                            hisInspectionApply.setBillTime(Func.formatDateTime(billTime)); //格式化
                            hisInspectionApply.setCreateTime(billTime);
                            hisInspectionApplyList.add(hisInspectionApply);
                        }
                    }
                } else {
                    log.info("-----idCardNo:{} ,businessId :{} 暂无申请单 ------", idCardNo, businessId);
                }
            }
        });
        if (Func.isNotEmpty(hisInspectionApplyList)) {
            iHisInspectionApplyService.insertHisInspectionApplyBatch(hisInspectionApplyList);
        }
    }


    /**
     * 同步检验检查报告结果
     */
    @TraceId
    public void inspectionReportTask(String params) {
        if (Func.isNotBlank(params)) {
            List<TaskParam> taskParams = JSON.parseArray(params, TaskParam.class);
            if (Func.isNotEmpty(taskParams)) {
                taskParams.forEach(taskParam -> {
                    String orgCode = taskParam.getOrgCode();
                    Integer days = taskParam.getDays();
                    Date nowDate = DateUtils.getNowDate();
                    Date beginTime = Func.minusDays(nowDate, days);
                    Date endTime = Func.plusDays(nowDate, 1);

                    HisInspectionApply hisInspectionApplyQuery = new HisInspectionApply();
                    hisInspectionApplyQuery.setOrgCode(orgCode);
                    Map<String, Object> paramsQuery = new HashMap<>();
                    paramsQuery.put("beginCreateTime", Func.formatDate(beginTime));
                    paramsQuery.put("endCreateTime", Func.formatDate(endTime));
                    hisInspectionApplyQuery.setParams(paramsQuery);

                    List<HisInspectionReport> hisInspectionReportList = new ArrayList<>();
                    //申请单
                    List<HisInspectionApply> inspectionApplyList = iHisInspectionApplyService.selectHisInspectionApplyList(hisInspectionApplyQuery);
                    if (Func.isNotEmpty(inspectionApplyList)) {
                        inspectionApplyList.forEach(inspectionApply -> {
                            //请求入参
                            InspectionReport inspectionReport = new InspectionReport();
                            inspectionReport.setApplyId(inspectionApply.getApplyId());
                            inspectionReport.setUserId(inspectionApply.getUserId()); //虚拟操作人员ID
                            //检查检验报告/结果
                            HISResult hisResult = hisClientUtil.getInspectionReport(inspectionReport);
                            if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
                                TypeReference<InspectionReportVO> typeRef = new TypeReference<InspectionReportVO>() {
                                };
                                InspectionReportVO inspectionReportVO = JSON.parseObject(hisResult.getMsg(), typeRef);
                                if (Func.isNotEmpty(inspectionReportVO)) {
                                    //患者信息、标本号 结论 结果等
                                    String perInfo = inspectionReportVO.getPerInfo();
                                    //指标值  参考值等
                                    String reportIdx = inspectionReportVO.getIdx();
                                    //检查检验报告
                                    InspectionReportBO inspectionReportBO = JSON.parseObject(perInfo, InspectionReportBO.class);
                                    inspectionReportVO.setInspectionReportBO(inspectionReportBO);

                                    InspectionReportItemBO inspectionReportItemBO = JSON.parseObject(reportIdx, InspectionReportItemBO.class);

                                    if (Func.isNotEmpty(inspectionReportItemBO)) {
                                        String idx = inspectionReportItemBO.getIdx();
                                        List<InspectionReportItemBO> inspectionReportItemList = JSON.parseArray(idx, InspectionReportItemBO.class);
                                        inspectionReportBO.setInspectionReportItemList(inspectionReportItemList);
                                    }
                                    if (Func.isNotEmpty(inspectionReportBO)) {
                                        //每次更新时,查询是否已经存在
                                        HisInspectionReport hisInspectionReportQuery = new HisInspectionReport();
                                        hisInspectionReportQuery.setApplyId(inspectionReportBO.getApplyId());
                                        HisInspectionReport hisInspectionReport = iHisInspectionReportService.selectHisInspectionReport(hisInspectionReportQuery);
                                        //检查检验报告
                                        if (null == hisInspectionReport) {
                                            hisInspectionReport = new HisInspectionReport();
                                            Func.copy(inspectionReportBO, hisInspectionReport);
                                            hisInspectionReport.setOrgCode(inspectionApply.getOrgCode());//机构编码
                                            hisInspectionReport.setOperators(inspectionReportBO.getOperator());
                                            hisInspectionReport.setIdCardNo(inspectionApply.getIdCardNo());//需要保存患者的身份证号
                                            //开单时间--> createDate  时间格式转换
                                            Date billTime = Func.parse(inspectionReportBO.getBillTime(), "yyyy/MM/dd HH:mm");
                                            hisInspectionReport.setBillTime(Func.formatDateTime(billTime));
                                            hisInspectionReport.setCreateTime(billTime);
                                            Date execTime = Func.parse(inspectionReportBO.getExecTime(), "yyyy/MM/dd HH:mm");
                                            hisInspectionReport.setExecTime(Func.formatDateTime(execTime));
                                            //检查检验项目明细
                                            List<InspectionReportItemBO> inspectionReportItemList = inspectionReportBO.getInspectionReportItemList();
                                            List<HisInspectionReportItem> itemList = new ArrayList<>();
                                            //保存数据到数据库
                                            if (Func.isNotEmpty(inspectionReportItemList)) {
                                                inspectionReportItemList.forEach(item -> {
                                                    HisInspectionReportItem inspectionReportItem = new HisInspectionReportItem();
                                                    //inspectionReportItem.setReportId(reportId);
                                                    Func.copy(item, inspectionReportItem);
                                                    if (Func.isNotEmpty(item.getFee())) {
                                                        inspectionReportItem.setFee(String.valueOf(item.getFee()));
                                                    }
                                                    inspectionReportItem.setCreateTime(billTime);
                                                    itemList.add(inspectionReportItem);
                                                });
                                                hisInspectionReport.setHisInspectionReportItemList(itemList);
                                            }
                                            hisInspectionReportList.add(hisInspectionReport);
                                        }
                                    }
                                }
                            }
                        });
                    }

                    if (Func.isNotEmpty(hisInspectionReportList)) {
                        iHisInspectionReportService.insertHisInspectionReportBatch(hisInspectionReportList);
                    }
                });
            }
        }
    }

    private void doctorScheduleQuery(String orgCode, Integer days) {
        //班次判断是否存在
        HisDoctorSchedule doctorScheduleQuery = new HisDoctorSchedule();
        doctorScheduleQuery.setOrgCode(orgCode);
        Map<String, Object> paramsQuery = new HashMap<>();
        //删除昨天以前的排班老数据
        Date beginScheduleDate = Func.minusDays(DateUtils.getNowDate(), days);  //当前日期过去的days
        Date endScheduleDate = Func.minusDays(DateUtils.getNowDate(), 1); //昨天
        paramsQuery.put("beginScheduleDate", Func.formatDate(beginScheduleDate));
        paramsQuery.put("endScheduleDate", Func.formatDate(endScheduleDate));
        doctorScheduleQuery.setParams(paramsQuery);
        List<HisDoctorSchedule> oldScheduleList = iHisDoctorScheduleService.selectHisDoctorScheduleList(doctorScheduleQuery);
        if (Func.isNotEmpty(oldScheduleList)) {
            List<Long> idsList = oldScheduleList.stream().map(HisDoctorSchedule::getId).collect(toList());
            String ids = Func.join(idsList, ",");
            iHisDoctorScheduleService.deleteHisDoctorScheduleByIds(ids);
        }
    }

    /**
     * 机构、人员的排班信息
     *
     * @param params JSON数据
     */
    @TraceId
    public void doctorSchedule(String params) {
        if (Func.isNotBlank(params)) {
            List<TaskParam> taskParams = JSON.parseArray(params, TaskParam.class);
            if (Func.isNotEmpty(taskParams)) {
                taskParams.forEach(taskParam -> {
                    String orgCode = taskParam.getOrgCode();
                    Integer days = taskParam.getDays();
                    Date nowDate = DateUtils.getNowDate();
                    Date beginTime = Func.minusDays(nowDate, 1);//昨天
                    Date endTime = Func.plusDays(nowDate, days);
                    this.doctorScheduleQuery(orgCode, days);
                    //医生列表(正常在线)
                    HisDoctor hisDoctor = new HisDoctor();
                    hisDoctor.setOrgCode(orgCode);
                    hisDoctor.setIsShow(IConstant.ONE.getValue());
                    List<HisDoctor> doctorList = iHisDoctorService.selectHisDoctorList(hisDoctor);
                    if (Func.isNotEmpty(doctorList)) {
                        hisService.syncDoctorSchedule(doctorList, Func.formatDate(beginTime), Func.formatDate(endTime));
                    }
                });
            }
        }
    }
}
