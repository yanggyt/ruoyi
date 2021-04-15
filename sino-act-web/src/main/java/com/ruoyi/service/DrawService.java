package com.ruoyi.service;

import com.ruoyi.cache.Cache;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.dto.DrawActivityRequest;
import com.ruoyi.dto.DrawActivityResult;
import com.ruoyi.dto.GtPrizeConfigTemp;
import com.ruoyi.thread.DrawActivityThread;
import com.ruoyi.thread.ThreadPool;
import com.ruoyi.web.vo.Const;
import com.ruoyi.web.vo.draw.DrawResult;
import com.sinosoft.activity.domain.DrawConfig;
import com.sinosoft.activity.domain.DrawInfo;
import com.sinosoft.activity.domain.DrawPrizeInfo;
import com.sinosoft.activity.domain.DrawRule;
import com.sinosoft.activity.service.IDrawConfigService;
import com.sinosoft.activity.service.IDrawInfoService;
import com.sinosoft.activity.service.IDrawPrizeInfoService;
import com.sinosoft.activity.service.IDrawRuleService;
import com.sinosoft.activity.vo.PrizeInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class DrawService {

    private static final Logger logger = LoggerFactory.getLogger(DrawService.class);
    @Autowired
    private IDrawRuleService drawRuleService;
    @Autowired
    private IDrawConfigService drawConfigService;
    @Autowired
    private IDrawPrizeInfoService drawPrizeInfoService;
    @Autowired
    private IDrawInfoService drawInfoService;

    /**
     * 抽奖实现主方法
     */
    public DrawResult drawActivity(DrawActivityRequest request) {
        long start = System.currentTimeMillis();
        long end = 0;
        long proct = 0;
        String exception = "";
        DrawResult drawResult = new DrawResult();
        long point = System.currentTimeMillis();
        logger.info("抽奖开始时间:" + request.getSerialNo() + ":" + point);
        try {
            // 校验体信息
            String drawCode = request.getDrawCode();
            if(StringUtils.isBlank(drawCode)){
                return processResp(request, DrawActivityResult.EXCEPTION_000001,
                        DrawActivityResult.getMsg(DrawActivityResult.EXCEPTION_000001, "drawCode参数为空"));
            }
            long point1 = System.currentTimeMillis();
            logger.info("抽奖检查点1:" + request.getSerialNo() + ":" + point1 + "与前一个检查点时间差" + (point1 - point));
            // 查询活动信息
            DrawInfo queryInfo = new DrawInfo();
            queryInfo.setDRAWCODE(drawCode);
            queryInfo.setSTATUS(Const.DRAW_STATUS_EFFECTIVE);
            List<DrawInfo> drawInfos = drawInfoService.selectDrawInfoList(queryInfo);
            if (drawInfos == null || drawInfos.size() == 0) {
                drawResult.setRespCode("-3");
                drawResult.setRespMsg("活动未开启");
                return processResp(request, DrawActivityResult.EXCEPTION_000011,
                        DrawActivityResult.getMsg(DrawActivityResult.EXCEPTION_000011));
            }
            DrawInfo drawInfo = drawInfos.get(0);
            Date starttime = drawInfo.getSTARTTIME();
            Date endtime = drawInfo.getENDTIME();
            Date currDate = new Date();
            if (currDate.before(starttime) || currDate.after(endtime)) {
                return processResp(request, DrawActivityResult.EXCEPTION_000011,
                        DrawActivityResult.getMsg(DrawActivityResult.EXCEPTION_000011));
            }
            long point2 = System.currentTimeMillis();
            logger.info("抽奖检查点2:" + request.getSerialNo() + ":" + point2 + "与前一个检查点时间差" + (point2 - point1));
            String currentDateStr = DateUtils.dateTime();
            DrawInfo gtDrawInfo = null;
            if ("1".equals(gtDrawInfo.getUPDATEFLAG()) || Cache.get("_" + drawCode + "_currentDateStr_") == null
                || Cache.get("_" + drawCode + "_" + currentDateStr + "_rule_") == null
                || Cache.get("_" + drawCode + "_" + currentDateStr + "_config_") == null
                || Cache.get("_" + drawCode + "_blank_") == null || Cache.get("_" + drawCode + "_blankConfig_") == null
                || Cache.get("_" + drawCode + "_" + currentDateStr + "_gtDrawConfigList_") == null
                || Cache.get("_" + drawCode + "_" + currentDateStr + "_baseNumber_") == null) {
                // 刷新缓存
                loadDrawRule(drawCode);
                gtDrawInfo.setUPDATEFLAG("0");
                drawInfoService.updateDrawInfo(gtDrawInfo);
            }
            long point3 = System.currentTimeMillis();
            logger.info("抽奖检查点3:" + request.getSerialNo() + ":" + point3 + "与前一个检查点时间差" + (point3 - point2));
            DrawRule gtDrawRule = (DrawRule) Cache.get("_" + drawCode + "_" + currentDateStr + "_rule_");
            Future<DrawResult> resFuture = ThreadPool.drawActivityExecutorService.submit(new DrawActivityThread(request, gtDrawInfo, gtDrawRule, drawResult));
            drawResult = resFuture.get(15 * 1000, TimeUnit.SECONDS);
            end = System.currentTimeMillis();
            logger.info("抽奖接口返回:" + request.getSerialNo() + "&返回时间" + end + "总时间" + (end - point));
            return drawResult;
        } catch (Exception e) {
            drawResult = processResp(request, DrawActivityResult.EXCEPTION, DrawActivityResult.getMsg(DrawActivityResult.EXCEPTION));
            logger.error("抽奖接口异常，异常原因：", e);
            return drawResult;
        }
    }

    private DrawResult processResp(DrawActivityRequest request, String resultCode, String resultInfo) {
        DrawResult response = new DrawResult();
        response.setRespCode(resultCode);
        response.setRespMsg(resultInfo);
        return response;
    }

    /**
     * 刷新抽奖需要的缓存信息
     *
     * @param drawCode
     * @throws Exception
     */
    private void loadDrawRule(String drawCode) throws Exception {
        logger.info("加载缓存：" + drawCode);
        String yesterdayDateStr = DateUtils.parseDateToStr("yyyyMMdd", DateUtils.addDays(DateUtils.getNowDate(), -1));
        // String yesterdayTimeStr = yesterdayDateStr + "0000000";
        // Date tomorrowTime = DateUtil.toFormatDate(yesterdayTimeStr,
        // "yyyyMMddHHmmss");
        String currentDateStr = DateUtils.dateTime();
        // long l1 = tomorrowTime.getTime();
        // long l2 = new Date().getTime();
        // long timeOut = l1 - l2;
        long timeOut = 1000 * 60 * 60;
        // 抽奖缓存日期加入缓存
        Cache.remove("_" + drawCode + "_currentDateStr_");
        Cache.add("_" + drawCode + "_currentDateStr_", currentDateStr);
        // 抽奖规则加入缓存
        DrawRule drawRuleParams = new DrawRule();
        drawRuleParams.setDRAWCODE(drawCode);
        DrawRule drawRule = drawRuleService.selectDrawRuleList(drawRuleParams).get(0);
        cacheAdd("_" + drawCode + "_" + currentDateStr + "_rule_", drawRule, "_" + drawCode + "_" + yesterdayDateStr + "_rule_", timeOut);
        // 奖项配置加入缓存
//        gtDrawConfigQueryRule.addAscOrder("prizeLevel");
        DrawConfig drawConfigParams = new DrawConfig();
        drawConfigParams.setDRAWCODE(drawCode);
        drawConfigParams.setSTATUS(Const.DRAW_CONFIG_STATUS_EFFECTIVE);
        List<DrawConfig> drawConfigs = drawConfigService.selectDrawConfigList(drawConfigParams);
        cacheAdd("_" + drawCode + "_" + currentDateStr + "_config_", drawConfigs, "_" + drawCode + "_" + yesterdayDateStr + "_config_", timeOut);
        // 空奖奖项配置
        DrawConfig drawConfigBlank = null;
        // 非空奖项配置
        List<DrawConfig> gtDrawConfigList = new ArrayList<>();
        for (DrawConfig drawConfig : drawConfigs) {
            String prizelevel = drawConfig.getPRIZELEVEL();
            if (Const.PRIZE_LEVEL_BLANK.equals(prizelevel)) {
                drawConfigBlank = drawConfig;
            } else {
                gtDrawConfigList.add(drawConfig);
            }
        }
        if (drawConfigBlank == null) {
            throw new Exception("空奖品配置错误");
        }
        // 非空奖奖项配置加入缓存
        cacheAdd("_" + drawCode + "_" + currentDateStr + "_gtDrawConfigList_", gtDrawConfigList, "_" + drawCode + "_" + yesterdayDateStr + "_gtDrawConfigList_", timeOut);
        // 空奖奖项配置加入缓存
        Cache.remove("_" + drawCode + "_blankConfig_");
        Cache.add("_" + drawCode + "_blankConfig_", drawConfigBlank);
        //空奖奖品加入缓存

        String prizeCodeBlank = drawConfigBlank.getPRIZECODE();
        PrizeInfo prizeInfoParams =  new PrizeInfo();
        prizeInfoParams.setDRAWCODE(drawCode);
        prizeInfoParams.setSTATUS(Const.STATUS_VALID);
        List<DrawPrizeInfo> drawPrizeInfos = drawPrizeInfoService.selectDrawPrizeInfoByDrawCode(prizeInfoParams);
        Map<String, DrawPrizeInfo> prizeMap = new HashMap<>();
        for (DrawPrizeInfo prizeInfo : drawPrizeInfos) {
            String prizeCode = prizeInfo.getPRIZECODE();
            prizeMap.put(prizeCode, prizeInfo);
        }
        DrawPrizeInfo blankPrize = prizeMap.get(prizeCodeBlank);
        Cache.remove("_" + drawCode + "_blank_");
        Cache.add("_" + drawCode + "_blank_", blankPrize);

        // 计算总权重
        BigDecimal totalProbability = BigDecimal.ZERO;
        // 最小概率
        BigDecimal minProbability = BigDecimal.ZERO;
        if (gtDrawConfigList != null && gtDrawConfigList.size() > 0) {
            for (int i = 0; i < gtDrawConfigList.size(); i++) {
                DrawConfig gtDrawConfig = gtDrawConfigList.get(i);
                String prizeWigth = new BigDecimal(gtDrawConfig.getPROBABILITY()).divide(new BigDecimal(100)).toString();
                totalProbability = totalProbability.add(new BigDecimal(prizeWigth));
                String n = prizeWigth;
                if (i == 0) {
                    minProbability = new BigDecimal(n);
                } else {
                    if (new BigDecimal(n).compareTo(minProbability) == -1) {
                        minProbability = new BigDecimal(n);
                    }
                    logger.info("最小权重：" + n + "最小概率" + minProbability);
                }
            }
        }
        // 计算基数
        BigDecimal bd = new BigDecimal(String.valueOf(minProbability));
        logger.info("权重长度 = " + bd.scale());

        BigDecimal baseNumer = new BigDecimal(Math.pow(10, bd.scale())).add(new BigDecimal(gtDrawConfigList.size()));
        cacheAdd("_" + drawCode + "_" + currentDateStr + "_baseNumber_", baseNumer, "_" + drawCode + "_" + yesterdayDateStr + "_baseNumber_", timeOut);
        // 计算起始区间
        long tmp = 0;
        if (gtDrawConfigList != null && gtDrawConfigList.size() > 0) {
            for (int i = 0; i < gtDrawConfigList.size(); i++) {
                DrawConfig gtDrawConfig = gtDrawConfigList.get(i);
                String probability = gtDrawConfig.getPROBABILITY();
                GtPrizeConfigTemp gtPrizeConfigTemp = new GtPrizeConfigTemp();
                gtPrizeConfigTemp.setBaseNumer(baseNumer.longValue());
                gtPrizeConfigTemp.setConfig(gtDrawConfig);
                gtPrizeConfigTemp.setPrizeInfo(prizeMap.get(gtDrawConfig.getPRIZECODE()));
                // 区间1从0开始
                if (i == 0) {
                    // 区间数从1开始
                    long start = new BigDecimal(1).longValue();
                    long end = new BigDecimal(Math.floor(baseNumer.multiply(new BigDecimal(probability).divide(new BigDecimal(100))).doubleValue())).longValue();
                    gtPrizeConfigTemp.setStartNumer(start);
                    gtPrizeConfigTemp.setEndNumber(end);
                    gtPrizeConfigTemp.setWeightLength(end);
                    tmp = end;
                } else {
                    long start = new BigDecimal(tmp).add(new BigDecimal(1)).longValue();
                    long end = new BigDecimal(start).add(new BigDecimal(new BigDecimal(Math.floor(baseNumer.multiply(new BigDecimal(probability).divide(new BigDecimal(100))).doubleValue())).longValue())).longValue();
                    gtPrizeConfigTemp.setStartNumer(start);
                    gtPrizeConfigTemp.setEndNumber(end);
                    gtPrizeConfigTemp.setWeightLength(baseNumer.multiply(new BigDecimal(probability).divide(new BigDecimal(100))).longValue());
                    tmp = end;
                }
                // 奖项开始结束区间加入缓存
                cacheAdd("_cache_" + drawCode + "_" + currentDateStr + "_" + gtDrawConfig.getPRIZELEVEL() + "_", gtPrizeConfigTemp, "_cache_" + drawCode + "_" + yesterdayDateStr + "_" + gtDrawConfig.getPRIZELEVEL() + "_", timeOut);
            }
        }
    }

    /**
     * HashTable缓存元素
     *
     * @param name
     * @param value
     * @param yesterdayName
     * @param timeOut
     */
    private void cacheAdd(String name, Object value, String yesterdayName, long timeOut) {
        logger.info("缓存加入键:" + name);
        if (Cache.get(name) == null) {
            Cache.add(name, value, timeOut);
        } else {
            Cache.remove(name);
            Cache.add(name, value, timeOut);
            if (Cache.get(yesterdayName) != null) {
                Cache.remove(yesterdayName);
            }
        }
    }
}
