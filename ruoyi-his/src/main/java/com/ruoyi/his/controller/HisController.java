package com.ruoyi.his.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.bending.core.his.bo.PayAccountBO;
import com.bending.core.his.common.HISResult;
import com.bending.core.his.constant.HisConstant;
import com.bending.core.his.entity.*;
import com.bending.core.his.vo.*;
import com.bending.core.utils.HISClientUtil;
import com.ruoyi.common.func.IConstant;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.func.Func;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.bean.BeanUtils;
import com.ruoyi.his.domain.*;
import com.ruoyi.his.service.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@RestController
@RequestMapping("his/v1/sync")
@Api(value = "HIS同步数据接口", tags = "HIS同步数据接口")
public class HisController {
    @Resource
    private HISClientUtil hisClientUtil;
    @Resource
    private IHisHospitalInfoService iHisHospitalInfoService;
    @Resource
    private IHisDepartmentService iHisDepartmentService;
    @Resource
    private IHisDoctorDepartmentService iHisDoctorDepartmentService;
    @Resource
    private IHisDoctorService iHisDoctorService;
    @Resource
    private IHisRegistrationTemplateService iHisRegistrationTemplateService;
    @Resource
    private IHisPayAccountService iHisPayAccountService;
    @Resource
    private IHisFeeItemService hisFeeItemService;
    @Resource
    private IHisDoctorScheduleService iHisDoctorScheduleService;

    @ApiOperation("获取挂号模板")
    @GetMapping("/template/list")
    public AjaxResult templateList(String orgCode) {
        HisHospitalInfo hisHospitalInfo = iHisHospitalInfoService.selectHisHospitalInfoByOrgCode(orgCode);
        if (null == hisHospitalInfo) {
            return AjaxResult.error("指定机构不存在！");
        }
        RegistrationTemplate registrationTemplate = new RegistrationTemplate();
        registrationTemplate.setOrgCode(orgCode);
        registrationTemplate.setTemplateId("");
        HISResult hisResult = hisClientUtil.getRegistrationTemplateList(registrationTemplate);
        if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
            TypeReference<List<RegistrationTemplateVO>> typeRef = new TypeReference<List<RegistrationTemplateVO>>() {
            };
            List<RegistrationTemplateVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
            //保存数据到本地数据库
            for (RegistrationTemplateVO vo : list) {
                //每次更新时,查询是否已经存在
                String s = vo.getTemplateName().replaceAll("\\s*", "");
                vo.setTemplateName(Func.cleanChars(s));
                HisRegistrationTemplate hisRegistrationTemplate = Func.copy(vo, HisRegistrationTemplate.class);
                hisRegistrationTemplate.setIsShow(0);
                hisRegistrationTemplate.setFee(vo.getRegistrationAmount());
                hisRegistrationTemplate.setOrgCode(orgCode);
                hisRegistrationTemplate.setOrgName(hisHospitalInfo.getOrgName());
                iHisRegistrationTemplateService.insertHisRegistrationTemplate(hisRegistrationTemplate);
            }
            return AjaxResult.success(list);
        }
        return AjaxResult.error(hisResult.getMsg());
    }

    @ApiOperation("获取机构列表")
    @GetMapping("/hospital/list")
    public AjaxResult hospitalList(String orgName) {
        HospitalInfo hospitalInfo = new HospitalInfo();
        hospitalInfo.setOrgName(orgName);
        HISResult hisResult = hisClientUtil.getHospitalInfoByList(hospitalInfo);
        if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
            TypeReference<List<HospitalInfoVO>> typeRef = new TypeReference<List<HospitalInfoVO>>() {
            };
            List<HospitalInfoVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
            if (CollectionUtils.isNotEmpty(list)) {
                for (HospitalInfoVO hospitalInfoVO : list) {
                    HisHospitalInfo hisHospitalInfo = iHisHospitalInfoService.selectHisHospitalInfoByOrgCode(hospitalInfoVO.getOrgCode());
                    if (null == hisHospitalInfo) {
                        hisHospitalInfo = new HisHospitalInfo();
                        BeanUtils.copyProperties(hospitalInfoVO, hisHospitalInfo);
                        iHisHospitalInfoService.insertHisHospitalInfo(hisHospitalInfo);
                    } else {
                        iHisHospitalInfoService.updateHisHospitalInfo(hisHospitalInfo);
                    }
                }
            }
            return AjaxResult.success(list);
        }
        return AjaxResult.error(hisResult.getMsg());
    }

    @ApiOperation("获取科室列表")
    @GetMapping("/dept/list")
    public AjaxResult deptList(String orgCode) {
        //查询本地数据库
        HisHospitalInfo hisHospitalInfo = iHisHospitalInfoService.selectHisHospitalInfoByOrgCode(orgCode);
        if (null == hisHospitalInfo) {
            return AjaxResult.error("指定机构不存在！");
        }
        //HIS医院综合目录
        ComprehensiveCatalogue comprehensiveCatalogue = new ComprehensiveCatalogue();
        comprehensiveCatalogue.setDirectoryType(HisConstant.CATALOG_TYPE_1);
        comprehensiveCatalogue.setOrgCode(orgCode);
        HISResult hisResult = hisClientUtil.getComprehensiveCatalogueList(comprehensiveCatalogue);
        if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
            TypeReference<List<ComprehensiveCatalogueVO>> typeRef = new TypeReference<List<ComprehensiveCatalogueVO>>() {
            };
            List<ComprehensiveCatalogueVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
            for (ComprehensiveCatalogueVO vo : list) {
                String deptId = vo.getDirectoryCode();
                HisDepartment hisDepartment = new HisDepartment();
                hisDepartment.setDeptId(deptId);
                hisDepartment.setDeptName(vo.getDirectoryName());
                hisDepartment.setOrgCode(orgCode);
                hisDepartment.setOrgName(hisHospitalInfo.getOrgName());
                hisDepartment.setIsShow(0);
                hisDepartment.setCreateTime(DateUtils.getNowDate());
                int res = iHisDepartmentService.insertHisDepartment(hisDepartment);
                //每次更新时,查询是否已经存在
            }
            return AjaxResult.success(list);
        }
        return AjaxResult.error(hisResult.getMsg());
    }

    @ApiOperation("获取医生列表")
    @GetMapping("/doctor/list")
    public AjaxResult doctorList(String orgCode) {
        //查询本地数据库
        HisHospitalInfo hisHospitalInfo = iHisHospitalInfoService.selectHisHospitalInfoByOrgCode(orgCode);
        if (null == hisHospitalInfo) {
            return AjaxResult.error("指定机构不存在！");
        }
        //HIS医院综合目录
        ComprehensiveCatalogue comprehensiveCatalogue = new ComprehensiveCatalogue();
        comprehensiveCatalogue.setDirectoryType(HisConstant.CATALOG_TYPE_2);
        comprehensiveCatalogue.setOrgCode(orgCode);
        HISResult hisResult = hisClientUtil.getComprehensiveCatalogueList(comprehensiveCatalogue);
        if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
            TypeReference<List<ComprehensiveCatalogueVO>> typeRef = new TypeReference<List<ComprehensiveCatalogueVO>>() {
            };
            List<ComprehensiveCatalogueVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);

            //医生分组,但是数据没有去重。
            Map<String, List<ComprehensiveCatalogueVO>> map = list.stream().collect(Collectors.groupingBy(ComprehensiveCatalogueVO::getDirectoryCode));
            // 医生与科室对应关系
            //  Map<doctorId,deptIds> --> Map<String,List<String>>
            Map<String, List<String>> doctorMapR = new HashMap<>();
            map.forEach((doctorId, voList) -> {
                //拿到科室ID,就是科室去重
                List<ComprehensiveCatalogueVO> tempList = voList.stream().filter(distinctByKey(ComprehensiveCatalogueVO::getRemark)).collect(Collectors.toList());
                List<String> deptIds = tempList.stream().map(ComprehensiveCatalogueVO::getRemark).collect(Collectors.toList());
                doctorMapR.put(doctorId, deptIds);
            });
            //保存医生与科室的对应关系
            doctorMapR.forEach((doctorId, deptIds) -> {
                deptIds.forEach(deptId -> {
                    //每次更新时,查询是否已经存在对应关系
                    HisDoctorDepartment doctorDepartment = new HisDoctorDepartment();
                    //一个医生理论上对应一个科室，实际上是对应多个科室的.
                    doctorDepartment.setDeptId(deptId);
                    HisDepartment hisDepartment = iHisDepartmentService.selectHisDepartmentByDeptId(deptId);
                    doctorDepartment.setDeptName(null != hisDepartment ? hisDepartment.getDeptName() : "");
                    doctorDepartment.setDoctorId(doctorId);
                    doctorDepartment.setOrgCode(orgCode);
                    doctorDepartment.setIsShow(0);
                    iHisDoctorDepartmentService.insertHisDoctorDepartment(doctorDepartment);
                });
            });

            //根据医生ID去重
            List<ComprehensiveCatalogueVO> voList = list.stream().filter(distinctByKey(ComprehensiveCatalogueVO::getDirectoryCode)).collect(Collectors.toList());
            for (ComprehensiveCatalogueVO v : voList) {
                HisDoctor hisDoctor = new HisDoctor();
                hisDoctor.setDoctorId(v.getDirectoryCode());
                hisDoctor.setDoctorName(v.getDirectoryName());
                //备注[目录类型1： 返回医生所在科室的编码;目录类型3： 返回床位所在的病区编码.]
                hisDoctor.setDeptId(v.getRemark()); //随机设置一个默认科室
                HisDepartment hisDepartment = iHisDepartmentService.selectHisDepartmentByDeptId(v.getRemark());
                hisDoctor.setDeptName(null != hisDepartment ? hisDepartment.getDeptName() : "");
                hisDoctor.setOrgCode(orgCode);
                hisDoctor.setOrgName(hisHospitalInfo.getOrgName());
                hisDoctor.setIsShow(0);
                iHisDoctorService.insertHisDoctor(hisDoctor);
            }
            //每次更新时,查询是否已经存在
            return AjaxResult.success(list);
        }
        return AjaxResult.error(hisResult.getMsg());
    }

    private static <T> Predicate<T> distinctByKey(Function<? super T, ?> keyExtractor) {
        Map<Object, Boolean> seen = new ConcurrentHashMap<>();
        return t -> seen.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }


    @GetMapping("/payment/list")
    @ApiOperation(value = "HIS同步数据接口:机构支付方式列表", notes = "传入查询参数")
    public AjaxResult queryHospitalPayment(@ApiParam(name = "orgCode", value = "机构ID", required = true) @RequestParam String orgCode) {
        HisHospitalInfo hisHospitalInfo = iHisHospitalInfoService.selectHisHospitalInfoByOrgCode(orgCode);
        if (null == hisHospitalInfo) {
            return AjaxResult.error("指定机构不存在！");
        }
        HospitalPayment hospitalPayment = new HospitalPayment();
        hospitalPayment.setOrgCode(orgCode);
        HISResult hisResult = hisClientUtil.getHospitalPaymentList(hospitalPayment);

        List<HisPayAccount> hisPayAccountList = new ArrayList<>();

        if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
            TypeReference<List<HospitalPaymentVO>> typeRef = new TypeReference<List<HospitalPaymentVO>>() {
            };
            List<HospitalPaymentVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
            //保存数据到本地数据库
            for (HospitalPaymentVO vo : list) {
                List<PayAccountBO> payAccountBOList = vo.getOrgAcc();
                payAccountBOList.forEach(bo -> {
                    HisPayAccount payAccount = new HisPayAccount();
                    Func.copy(bo, payAccount);
                    payAccount.setAccId(bo.getId());
                    hisPayAccountList.add(payAccount);
                });
            }
            if (Func.isNotEmpty(hisPayAccountList)) {
                iHisPayAccountService.insertHisPayAccountBatch(hisPayAccountList);
            }
            return AjaxResult.success(hisPayAccountList);
        }
        return AjaxResult.error(hisResult.getMsg());
    }


    @GetMapping("/fee/item/list")
    @ApiOperation(value = "HIS同步数据接口:挂号费用类型")
    public AjaxResult queryHisFeeItem() {
        List<HisFeeItem> hisFeeItemList = new ArrayList<>();
        RegistrationFeeType registrationFeeType = new RegistrationFeeType();
        HISResult hisResult = hisClientUtil.getFeeItemList(registrationFeeType);
        if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
            TypeReference<List<RegistrationFeeTypeVO>> typeRef = new TypeReference<List<RegistrationFeeTypeVO>>() {
            };
            List<RegistrationFeeTypeVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
            if (Func.isNotEmpty(list)) {
                for (RegistrationFeeTypeVO vo : list) {
                    HisFeeItem hisFeeItem = new HisFeeItem();
                    Func.copy(vo, hisFeeItem);
                    hisFeeItem.setIsShow(IConstant.ZERO.getValue());
                    hisFeeItem.setCreateTime(DateUtils.getNowDate());
                    hisFeeItemList.add(hisFeeItem);
                }
            }
            if (Func.isNotEmpty(hisFeeItemList)) {
                hisFeeItemService.insertHisFeeItemBatch(hisFeeItemList);
            }
            return AjaxResult.success(hisFeeItemList);
        } else {
            return AjaxResult.error(hisResult.getMsg());
        }
    }

    /**
     * /his/v1/sync/doctor/schedule
     * 同步医生排班信息
     *
     * @param orgCode 机构ID
     * @return
     */
    @GetMapping("/doctor/schedule")
    @ApiOperation(value = "HIS同步数据接口:医生排班信息", notes = "传入查询参数")
    public AjaxResult queryDoctorSchedule(@ApiParam(name = "orgCode", value = "机构ID") @RequestParam(required = false) String orgCode) {
        Date nowDate = DateUtils.getNowDate();
        Date beginTime = Func.minusDays(nowDate, 1);//-days天
        Date endTime = Func.plusDays(nowDate, 1);//+days天
        if (Func.isNotEmpty(orgCode)) {
            HisHospitalInfo hospitalInfo = iHisHospitalInfoService.selectHisHospitalInfoByOrgCode(orgCode); //机构信息
            if (null == hospitalInfo) return AjaxResult.error("指定机构不存在！");
            syncDoctorSchedule(hospitalInfo, beginTime, endTime);
        } else {
            HisHospitalInfo hospitalInfoQuery = new HisHospitalInfo();
            hospitalInfoQuery.setIsShow(IConstant.ONE.getValue());
            List<HisHospitalInfo> hospitalInfoList = iHisHospitalInfoService.selectHisHospitalInfoList(hospitalInfoQuery);
            if (Func.isNotEmpty(hospitalInfoList)) {
                hospitalInfoList.forEach(hospitalInfo -> {
                    syncDoctorSchedule(hospitalInfo, beginTime, endTime);
                });
            }
        }
        return AjaxResult.success("医生排班同步完成!");
    }

    private void syncDoctorSchedule(HisHospitalInfo hospitalInfo, Date beginTime, Date endTime) {
        if (null == hospitalInfo) return;
        String orgName = Func.isNotEmpty(hospitalInfo) ? hospitalInfo.getOrgName() : "";
        String orgCode = hospitalInfo.getOrgCode();
        //班次判断是否存在
        HisDoctorSchedule doctorScheduleQuery = new HisDoctorSchedule();
        doctorScheduleQuery.setOrgCode(orgCode);
        Map<String, Object> paramsQuery = new HashMap<>();
        //删除过期排班老数据
        Date beginScheduleDate = Func.minusDays(beginTime, 7);  //当前日期过去的days
        Date endScheduleDate = Func.minusDays(beginTime, 1); //昨天
        paramsQuery.put("beginScheduleDate", Func.formatDate(beginScheduleDate, DateUtils.YYYY_MM_DD));
        paramsQuery.put("endScheduleDate", Func.formatDate(endScheduleDate, DateUtils.YYYY_MM_DD));
        doctorScheduleQuery.setParams(paramsQuery);
        List<HisDoctorSchedule> oldScheduleList = iHisDoctorScheduleService.selectHisDoctorScheduleList(doctorScheduleQuery);
        if (Func.isNotEmpty(oldScheduleList)) {
            List<Long> idsList = oldScheduleList.stream().map(HisDoctorSchedule::getId).collect(toList());
            String ids = Func.join(idsList, ",");
            iHisDoctorScheduleService.deleteHisDoctorScheduleByIds(ids);
        }
        //医生列表(正常在线)
        HisDoctor hisDoctor = new HisDoctor();
        hisDoctor.setOrgCode(orgCode);
        hisDoctor.setIsShow(IConstant.ONE.getValue());
        List<HisDoctor> doctorList = iHisDoctorService.selectHisDoctorList(hisDoctor);
        if (Func.isNotEmpty(doctorList)) {
            // 新数据
            List<HisDoctorSchedule> newDoctorScheduleList = new ArrayList<>();
            List<HisDoctorSchedule> oldDoctorScheduleList = new ArrayList<>();

            doctorList.forEach(doctor -> {
                DoctorSchedule doctorSchedule = new DoctorSchedule();
                doctorSchedule.setOrgCode(orgCode);
                doctorSchedule.setDoctorId(doctor.getDoctorId());
                //查询日期限制在7天内，yyyy-MM-dd
                doctorSchedule.setBeginTime(Func.formatDate(beginTime));
                doctorSchedule.setEndTime(Func.formatDate(endTime));
                HISResult hisResult = hisClientUtil.getDoctorScheduleList(doctorSchedule);
                if (HisConstant.RESULT_SUCCESS_CODE.equals(hisResult.getResult())) {
                    TypeReference<List<DoctorScheduleVO>> typeRef = new TypeReference<List<DoctorScheduleVO>>() {
                    };
                    List<DoctorScheduleVO> list = JSON.parseObject(hisResult.getMsg(), typeRef);
                    if (Func.isNotEmpty(list)) {
                        for (DoctorScheduleVO vo : list) {
                            String shift = Func.trimWhitespace(vo.getScheduleShift());
                            Date scheduleDate = Func.parse(vo.getScheduleDate(), "yyyy-MM-dd");
                            //班次判断是否存在 --老数据
                            HisDoctorSchedule hisDoctorScheduleQuery = new HisDoctorSchedule();
                            hisDoctorScheduleQuery.setOrgCode(orgCode);
                            hisDoctorScheduleQuery.setDoctorId(vo.getDoctorId());
                            hisDoctorScheduleQuery.setScheduleDate(scheduleDate);
                            hisDoctorScheduleQuery.setScheduleShift(shift);
                            HisDoctorSchedule hisDoctorSchedule = iHisDoctorScheduleService.selectHisDoctorSchedule(hisDoctorScheduleQuery);
                            if (null == hisDoctorSchedule) {
                                hisDoctorSchedule = new HisDoctorSchedule();
                                hisDoctorSchedule.setScheduleDate(scheduleDate);
                                hisDoctorSchedule.setOrgCode(orgCode);
                                hisDoctorSchedule.setOrgName(orgName);
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
                            } else {
                                Func.copy(vo, hisDoctorSchedule);
                                if (HisConstant.MORNING.equals(shift)) {
                                    hisDoctorSchedule.setScheduleTag(IConstant.ONE.getValue());
                                }
                                if (HisConstant.AFTERNOON.equals(shift)) {
                                    hisDoctorSchedule.setScheduleTag(IConstant.TWO.getValue());
                                }
                                oldDoctorScheduleList.add(hisDoctorSchedule);
                            }
                        }
                    }
                }
            });

            if (Func.isNotEmpty(newDoctorScheduleList)) {
                iHisDoctorScheduleService.insertHisDoctorScheduleBatch(newDoctorScheduleList);
            }
            if (Func.isNotEmpty(oldDoctorScheduleList)) {
                iHisDoctorScheduleService.updateHisDoctorScheduleBatch(oldDoctorScheduleList);
            }
        }
    }
}
