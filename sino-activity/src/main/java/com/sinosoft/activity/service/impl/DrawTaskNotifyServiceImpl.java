package com.sinosoft.activity.service.impl;

import com.ruoyi.common.constant.ActConstants;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.sinosoft.activity.domain.*;
import com.sinosoft.activity.mapper.*;
import com.sinosoft.activity.service.IDrawTaskNotifyService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.poi.ss.usermodel.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 活动次数记录信息Service业务层处理
 *
 * @author dy
 * @date 2021-03-26
 */
@Service
public class DrawTaskNotifyServiceImpl implements IDrawTaskNotifyService
{
    private static final Logger log = LoggerFactory.getLogger(DrawTaskNotifyServiceImpl.class);

    @Autowired
    private DrawTaskNotifyMapper drawTaskNotifyMapper;
    @Autowired
    private DrawTaskConsumeMapper drawTaskConsumeMapper;
    @Autowired
    private DrawInfoMapper drawInfoMapper;
    @Autowired
    private DrawWhitelistMapper drawWhitelistMapper;
    @Autowired
    private DrawDiscConfigMapper drawDiscConfigMapper;

    /**
     * 查询活动次数记录信息
     *
     * @param USERID 活动次数记录信息ID
     * @return 活动次数记录信息
     */
    @Override
    public DrawTaskNotify selectDrawTaskNotifyById(String USERID)
    {
        return drawTaskNotifyMapper.selectDrawTaskNotifyById(USERID);
    }
    @Override
    public int selectDrawNumByUserId(String userId, String drawCode)
    {
        return drawTaskNotifyMapper.selectDrawNumByUserId(userId, drawCode);
    }

    /**
     * 查询活动次数记录信息列表
     *
     * @param drawTaskNotify 活动次数记录信息
     * @return 活动次数记录信息
     */
    @Override
    public List<DrawTaskNotify> selectDrawTaskNotifyList(DrawTaskNotify drawTaskNotify)
    {
        return drawTaskNotifyMapper.selectDrawTaskNotifyList(drawTaskNotify);
    }

    /**
     * 新增活动次数记录信息
     *
     * @param drawTaskNotify 活动次数记录信息
     * @return 结果
     */
    @Override
    public int insertDrawTaskNotify(DrawTaskNotify drawTaskNotify)
    {
        return drawTaskNotifyMapper.insertDrawTaskNotify(drawTaskNotify);
    }

    /**
     * 修改活动次数记录信息
     *
     * @param drawTaskNotify 活动次数记录信息
     * @return 结果
     */
    @Override
    public int updateDrawTaskNotify(DrawTaskNotify drawTaskNotify)
    {
        return drawTaskNotifyMapper.updateDrawTaskNotify(drawTaskNotify);
    }
    @Override
    public String updateDrawTaskNotifyNum(DrawTaskNotify drawTaskNotify)
    {
        DrawTaskNotify queryParams = new DrawTaskNotify();
        queryParams.setUSERID(drawTaskNotify.getUSERID());
        queryParams.setDRAWCODE(drawTaskNotify.getDRAWCODE());
        queryParams.setSTATE("1");
        List<DrawTaskNotify> drawTaskNotifies = drawTaskNotifyMapper.selectDrawTaskNotifyList(queryParams);
        if (drawTaskNotifies == null || drawTaskNotifies.size() == 0) {
            return null;
        }
        DrawTaskNotify taskNotify = drawTaskNotifies.get(drawTaskNotifies.size() - 1);
        String taskNotifyId = taskNotify.getTASKNOTIFYID();
        int result = drawTaskNotifyMapper.updateDrawTaskNotifyNum(taskNotifyId);
        if (result > 0) {
            DrawTaskConsume gtTaskConsume = new DrawTaskConsume();
            gtTaskConsume.setCONSUMENUMBER(1L);
            gtTaskConsume.setCreateTime(new Date());
            gtTaskConsume.setDRAWCODE(taskNotify.getDRAWCODE());
            gtTaskConsume.setLASTUPDATETIMESTAMP(new Date());
            gtTaskConsume.setSTATE("0");
            gtTaskConsume.setTASKID(taskNotify.getTASKID());
            gtTaskConsume.setTASKNOTIFYID(taskNotifyId);
            gtTaskConsume.setTYPE(taskNotify.getTYPE());
            gtTaskConsume.setUSERID(taskNotify.getUSERID());
            drawTaskConsumeMapper.insertDrawTaskConsume(gtTaskConsume);
            return gtTaskConsume.getTASKCONSUMEID();
        }
        return null;
    }

    /**
     * 删除活动次数记录信息对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDrawTaskNotifyByIds(String ids)
    {
        return drawTaskNotifyMapper.deleteDrawTaskNotifyByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除活动次数记录信息信息
     *
     * @param USERID 活动次数记录信息ID
     * @return 结果
     */
    @Override
    public int deleteDrawTaskNotifyById(String USERID)
    {
        return drawTaskNotifyMapper.deleteDrawTaskNotifyById(USERID);
    }

    /**
     * 1.查询活动状态
     * 2.查询记录表是否重复
     * orther一天只能调用一次设定number
     *
     * @param drawTaskNotify
     */
    @Override
    public Map<String, Object> addDrawNum(DrawTaskNotify drawTaskNotify) {
        HashMap<String, Object> response = new HashMap<>();
        try {
            //请求类型
            String taskType = drawTaskNotify.getTASKTYPE();
            int number = 0;
            //当前时间
            String currentDateString = new SimpleDateFormat("yyyyMMdd").format(new Date());
            String taskId = drawTaskNotify.getTASKID();
//            String getNumber = requestBody.getGetNumber();
            String userId = drawTaskNotify.getUSERID();
//            String isLimited = requestBody.getIsLimited();
            String isSpecialFlag = null;
            String drawCode = drawTaskNotify.getDRAWCODE();//活动编码
            String source = null;
            String uLevel = null;
            String vState = drawTaskNotify.getVSTATE();
            // 查询活动
            DrawInfo drawInfo = new DrawInfo();
            drawInfo.setDRAWCODE(drawCode);
            drawInfo.setSTATUS("1");
            List<DrawInfo> drawInfos = drawInfoMapper.selectDrawInfoList(drawInfo);
            if (CollectionUtils.isEmpty(drawInfos)) {
//                response = response(request, WebServiceResult.EXCEPTION_000001, WebServiceResult.getReturnMessage(WebServiceResult.EXCEPTION_000001, "drawCode参数错误"));
                response.put("msg", "drawCode参数错误");
                response.put("code", "-1");
            } else {
                // 完成一账通身份认证增加次数
                if (taskType.equals(ActConstants.TASK_TYPE_VALIDATE) || taskType.equals(ActConstants.TASK_TYPE_SHARE)) {
                    if (taskType.equals(ActConstants.TASK_TYPE_VALIDATE)) {
                        // 一账通只能认证一次，查询是否已经做过认证送次数
                        DrawTaskNotify QueryRule = new DrawTaskNotify();
                        QueryRule.setUSERID(drawTaskNotify.getUSERID());
                        QueryRule.setTASKTYPE(drawTaskNotify.getTASKTYPE());
                        List<DrawTaskNotify> drawTaskNotifies = drawTaskNotifyMapper.selectDrawTaskNotifyList(QueryRule);
                        // 已经做过身份认证，直接返回，不赠送次数
                        if (CollectionUtils.isNotEmpty(drawTaskNotifies)) {
//                        response = response(request, WebServiceResult.EXCEPTION_000006, WebServiceResult.getReturnMessage(WebServiceResult.EXCEPTION_000006, "身份认证送抽奖机会已经完成"));
                            response.put("msg", "身份认证送抽奖机会已经完成");
                            response.put("code", "-1");
                            return response;
                        }
                    }
                    // 发放次数
                    //todo 修改值
                    if ("isLimited".equals("1")) {
                        //查询赠送限制次数
                        Map<String, String> drawNumberMap = drawTaskNotifyMapper.findAllCodeAndName("drawNumberLimit");
                        int limitNumber = Integer.parseInt(drawNumberMap.get(taskId));
                        // 查询今天发放的次数
                        DrawTaskNotify QueryRule = new DrawTaskNotify();
                        QueryRule.setDRAWCODE(drawCode);
                        QueryRule.setTASKID(taskId);
                        QueryRule.setUSERID(userId);
                        QueryRule.setCHECKINGDATE(currentDateString);
                        List<DrawTaskNotify> drawTaskNotifies = drawTaskNotifyMapper.selectDrawTaskNotifyList(QueryRule);
                        if (CollectionUtils.isNotEmpty(drawTaskNotifies)) {
                            //今天进行过增加次数
                            Integer totalNumber = 0;//总次数
                            for (DrawTaskNotify drawTaskNotify1 : drawTaskNotifies) {
                                totalNumber += drawTaskNotify1.getADDNUMBER();
                            }
                            // 查看是否到达上限
                            int surplusNum = limitNumber - totalNumber;
                            if (drawTaskNotify.getADDNUMBER() > surplusNum) {
                                number = surplusNum > 0 ? surplusNum : 0;
                            }
                        } else {
                            //今天没赠送次数
                            number = drawTaskNotify.getADDNUMBER() > limitNumber ? limitNumber : 0;
                        }
                    }
                } else if (taskType.equals(ActConstants.TASK_TYPE_OTHER)) {
                    // 查询是不是在白名单
                    DrawWhitelist drawWhitelist = new DrawWhitelist();
                    drawWhitelist.setPHONE(drawTaskNotify.getPHONE());
                    List<DrawWhitelist> drawWhitelists = drawWhitelistMapper.selectDrawWhitelistList(drawWhitelist);
                    DrawDiscConfig drawDiscConfig = new DrawDiscConfig();
                    if (CollectionUtils.isEmpty(drawWhitelists)) {
                        // 不在白名单
                        isSpecialFlag = "0";
                        drawDiscConfig.setISSPECIALFLAG( isSpecialFlag);
                        // 区分来源
                        source = drawTaskNotify.getSOURCE();
                        drawDiscConfig.setSOURCE(source);
                        // 不区分是否验证身份
                        drawDiscConfig.setVSTATE(vState);
                        // 广发不区分区分等级，其他渠道区分等级
                        Map<String, String> noneLevelMap = drawTaskNotifyMapper.findAllCodeAndName("NONELEVEL");
                        if (noneLevelMap.containsKey(drawTaskNotify.getSOURCE())) {
                            uLevel = "none";
                            drawDiscConfig.setULEVEL(uLevel);
                        } else {
                            if (drawTaskNotify.getVSTATE().equals("1")) {
                                if (StringUtils.isEmpty(drawTaskNotify.getULEVEL())) {
//                                    response = response(request, WebServiceResult.EXCEPTION_000001, WebServiceResult.getReturnMessage(WebServiceResult.EXCEPTION_000001, "uLevel参数为空"));
                                    response.put("msg","uLevel参数为空");
                                    response.put("code","-1");
                                    return response;
                                }
                                uLevel = drawTaskNotify.getULEVEL();
                                Map<String, String> userLevelPointMap = drawTaskNotifyMapper.findAllCodeAndName("userLevel");
                                if (!userLevelPointMap.containsKey("point")) {
//                                    response = response(request, WebServiceResult.EXCEPTION, WebServiceResult.getReturnMessage(WebServiceResult.EXCEPTION, "用户等级配置错误"));
                                    response.put("msg","用户等级配置错误");
                                    response.put("code","-1");
                                    return response;
                                }
                                if (new BigDecimal(uLevel).compareTo(new BigDecimal(userLevelPointMap.get("point"))) == -1) {
                                    // 用户等级小于界限，判定为一般用户
                                    drawDiscConfig.setULEVEL(ActConstants.USER_LEVEL_ORDINARY);
                                    drawTaskNotify.setULEVEL(ActConstants.USER_LEVEL_ORDINARY);
                                } else {
                                    // 高价值客户
                                    drawDiscConfig.setULEVEL(ActConstants.USER_LEVEL_SENIOR);
                                    drawTaskNotify.setULEVEL(ActConstants.USER_LEVEL_SENIOR);
                                }
                            } else {
                                uLevel = "none";
                                drawDiscConfig.setULEVEL(uLevel);
                            }
                        }
                    } else {
                        // 在白名单
                        isSpecialFlag = "1";
                        // 不区分来源
                        source = "none";
                        drawDiscConfig.setSOURCE(source);
                        // 区分是否验证身份
                        drawDiscConfig.setVSTATE(vState);
                        // 不区分等级
                        uLevel = "none";
                        drawDiscConfig.setULEVEL(uLevel);
                    }

                    drawDiscConfig.setISSPECIALFLAG(isSpecialFlag);
                    List<DrawDiscConfig> drawDiscConfigs = drawDiscConfigMapper.selectDrawDiscConfigList(drawDiscConfig);
                    if (CollectionUtils.isEmpty(drawDiscConfigs)) {
                       // response = response(request, WebServiceResult.EXCEPTION_000006, WebServiceResult.getReturnMessage(WebServiceResult.EXCEPTION_000006, "配置错误"));
                        response.put("msg","配置错误");
                        response.put("code","-1");
                        return response;
                    }
                    // 一天只能调用一次设定number
                    // 查询今天是否做过
                    DrawTaskNotify QueryRule = new DrawTaskNotify();
                    QueryRule.setTASKTYPE(ActConstants.TASK_TYPE_OTHER);
                    QueryRule.setUSERID(userId);
                    QueryRule.setCHECKINGDATE(currentDateString);
                    List<DrawTaskNotify> drawTaskNotifies = drawTaskNotifyMapper.selectDrawTaskNotifyList(QueryRule);
                    if (CollectionUtils.isEmpty(drawTaskNotifies)) {
                        number = drawDiscConfig.getGETNUMBER().intValue();
                    } else {
                        number = 0;
                    }
                    drawCode = drawDiscConfig.getDRAWCODE();
                }
            }

            if (number > 0) {
                DrawTaskNotify gtTaskNotify = new DrawTaskNotify();
                gtTaskNotify.setPHONE(drawTaskNotify.getPHONE());
                gtTaskNotify.setSOURCE(source);
                gtTaskNotify.setTASKTYPE(drawTaskNotify.getTASKTYPE());
                gtTaskNotify.setTYPE(ActConstants.DRAW_TYPE_TURN_TABLE);
                gtTaskNotify.setULEVEL(uLevel);
                gtTaskNotify.setISSPECIALFLAG(isSpecialFlag);
                gtTaskNotify.setVSTATE(vState);
                gtTaskNotify.setCreateTime(new Date());
                gtTaskNotify.setDRAWCODE(drawCode);
                gtTaskNotify.setADDNUMBER(number);
                gtTaskNotify.setLASTUPDATETIMESTAMP(new Date());
                gtTaskNotify.setSTATE(ActConstants.TASK_NOTIFY_RECORD_STATUS_EFFECTIVE);
                gtTaskNotify.setAVAILABLENUMBER(number);
                gtTaskNotify.setTASKID(drawTaskNotify.getTASKID());
                gtTaskNotify.setUSERID(drawTaskNotify.getUSERID());
                gtTaskNotify.setCHECKINGDATE(currentDateString);
                if (taskType.equals(ActConstants.TASK_TYPE_OTHER)) {
                    gtTaskNotify.setREQUESTFLAG("AAAA");
                } else {
//                    String seq = new SimpleDateFormat("yyyyMMdd").format(new Date()) + sequenceService.getGtReqFlagSeq();
//                    gtTaskNotify.setREQUESTFLAG(seq);
                }
                drawTaskNotifyMapper.insertDrawTaskNotify(gtTaskNotify);
            }
//            responseHeader.setResultCode(WebServiceResult.SUCCESS);
//            responseHeader.setResultInfo(WebServiceResult.getReturnMessage(WebServiceResult.SUCCESS));
//            responseHeader.setTranDate(DateUtil.nowDateToString(Constant.DATE_FORMAT3));
//            responseHeader.setTranTime(DateUtil.nowDateToString(Constant.DATE_FORMAT4));
//            responseHeader.setSerialNo(requestHeader.getSerialNo());
//            response.setHeader(responseHeader);
            // 请求体
//            responseBody.setGetNumber(String.valueOf(number));
//            responseBody.setTaskId(taskId);
//            responseBody.setUserId(userId);
//            responseBody.setDrawCode(drawCode);
//            responseBody.setTaskType(requestBody.getTaskType());
//            response.setResponseBody(responseBody);
//            logger.info("【任务完成通知接口请求报文】" + jsonBinder.toJson(request) + "【任务完成通知接口响应报文】" + jsonBinder.toJson(response) + "【处理时间】" + proct);
            return response;
        } catch (NumberFormatException e) {
            e.printStackTrace();
//            exception = CommonUtil.stackTrace2String(e);
//            response = response(request, WebServiceResult.EXCEPTION_000006, WebServiceResult.getReturnMessage(WebServiceResult.EXCEPTION_000006, "参数格式错误"));
//            logger.error("增加抽奖次数接口异常，异常原因", e);
            return response;
        } finally {
//            InPortalTransmessage message = new InPortalTransmessage();
//            message.setException(exception);
//            message.setTransCode(transCode);
//            message.setTransRefGuid(request.getHeader().getSerialNo());
//            message.setRequestTime(new Date());
//            message.setResponseTime(new Date());
//            message.setRequestMessage(jsonBinder.toJson(request));
//            message.setResponseMessage(jsonBinder.toJson(response));
//            message.setProct(proct);
//            transRecordService.offer(message);
        }
    }

}
