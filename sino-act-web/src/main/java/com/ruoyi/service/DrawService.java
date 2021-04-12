package com.ruoyi.service;

import com.ruoyi.cache.Cache;
import com.ruoyi.common.utils.DateUtils;
import com.sinosoft.activity.domain.DrawRule;
import com.sinosoft.activity.service.IDrawRuleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.math.BigDecimal;

public class DrawService {

    private static final Logger logger = LoggerFactory.getLogger(DrawService.class);
    @Autowired
    private IDrawRuleService drawRuleService;
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
        QueryRule gtDrawConfigQueryRule = QueryRule.getInstance();
        gtDrawConfigQueryRule.addEqual("drawCode", drawCode);
        gtDrawConfigQueryRule.addEqual("state", Constant.DRAW_CONFIG_STATUS_EFFECTIVE);
        gtDrawConfigQueryRule.addAscOrder("prizeLevel");
        List<GtDrawConfig> gtDrawConfigs = gtDrawConfigService.queryByQueryRule(gtDrawConfigQueryRule);
        cacheAdd("_" + drawCode + "_" + currentDateStr + "_config_", gtDrawConfigs, "_" + drawCode + "_" + yesterdayDateStr + "_config_", timeOut);
        // 空奖品加入缓存
        QueryRule gtDrawConfigBlankCondition = QueryRule.getInstance();
        gtDrawConfigBlankCondition.addEqual("drawCode", drawCode);
        gtDrawConfigBlankCondition.addEqual("state", Constant.DRAW_CONFIG_STATUS_EFFECTIVE);
        gtDrawConfigBlankCondition.addEqual("prizeLevel", "blank");
        List<GtDrawConfig> gtDrawConfigBlankList = gtDrawConfigService.queryByQueryRule(gtDrawConfigBlankCondition);
        if (gtDrawConfigBlankList == null) {
            throw new Exception("空奖品配置错误");
        }
        QueryRule blankQueryRule = QueryRule.getInstance();
        blankQueryRule.addEqual("prizeCode", gtDrawConfigBlankList.get(0).getPrizeCode());
        blankQueryRule.addEqual("status", "1");
        GtPrizeInfo blankPrize = gtPrizeInfoService.queryUniqueGtPrizeInfo(blankQueryRule);
        Cache.remove("_" + drawCode + "_blank_");
        Cache.add("_" + drawCode + "_blank_", blankPrize);
        // 空奖奖项配置加入缓存
        QueryRule gtBlankDrawConfigCondition = QueryRule.getInstance();
        gtBlankDrawConfigCondition.addEqual("drawCode", drawCode);
        gtBlankDrawConfigCondition.addEqual("prizeLevel", "blank");
        List<GtDrawConfig> gtBlankDrawConfigList = gtDrawConfigService.queryByQueryRule(gtBlankDrawConfigCondition);
        if (gtBlankDrawConfigList != null && gtBlankDrawConfigList.size() > 0) {
            GtDrawConfig gtDrawConfig = gtBlankDrawConfigList.get(0);
            Cache.remove("_" + drawCode + "_blankConfig_");
            Cache.add("_" + drawCode + "_blankConfig_", gtDrawConfig);
        }
        // 非空奖奖项配置加入缓存
        QueryRule gtDrawConfigCondition = QueryRule.getInstance();
        gtDrawConfigCondition.addEqual("drawCode", drawCode);
        gtDrawConfigCondition.addEqual("state", Constant.DRAW_CONFIG_STATUS_EFFECTIVE);
        gtDrawConfigCondition.addNotEqual("prizeLevel", "blank");
        gtDrawConfigCondition.addAscOrder("prizeLevel");
        List<GtDrawConfig> gtDrawConfigList = gtDrawConfigService.queryByQueryRule(gtDrawConfigCondition);
        cacheAdd("_" + drawCode + "_" + currentDateStr + "_gtDrawConfigList_", gtDrawConfigList, "_" + drawCode + "_" + yesterdayDateStr + "_gtDrawConfigList_", timeOut);
        // 计算总权重
        BigDecimal totalProbability = BigDecimal.ZERO;
        // 最小概率
        BigDecimal minProbability = BigDecimal.ZERO;
        if (gtDrawConfigList != null && gtDrawConfigList.size() > 0) {
            for (int i = 0; i < gtDrawConfigList.size(); i++) {
                GtDrawConfig gtDrawConfig = gtDrawConfigList.get(i);
                String prizeWigth = new BigDecimal(gtDrawConfig.getProbability()).divide(new BigDecimal(100)).toString();
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
                GtDrawConfig gtDrawConfig = gtDrawConfigList.get(i);
                String probability = gtDrawConfig.getProbability();
                GtPrizeConfigTemp gtPrizeConfigTemp = new GtPrizeConfigTemp();
                gtPrizeConfigTemp.setBaseNumer(baseNumer.longValue());
                gtPrizeConfigTemp.setConfig(gtDrawConfig);
                QueryRule prizeQueryRule = QueryRule.getInstance();
                prizeQueryRule.addEqual("prizeCode", gtDrawConfig.getPrizeCode());
                List<GtPrizeInfo> prizeInfo = gtPrizeInfoService.queryByQueryRule(prizeQueryRule);
                gtPrizeConfigTemp.setPrizeInfo(prizeInfo.get(0));
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
                cacheAdd("_cache_" + drawCode + "_" + currentDateStr + "_" + gtDrawConfig.getPrizeLevel() + "_", gtPrizeConfigTemp, "_cache_" + drawCode + "_" + yesterdayDateStr + "_" + gtDrawConfig.getPrizeLevel() + "_", timeOut);
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
