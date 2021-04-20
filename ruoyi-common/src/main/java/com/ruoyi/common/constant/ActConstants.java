package com.ruoyi.common.constant;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO
 *
 * @author dy
 * @version 1.0
 * @date 2021/4/19 10:58
 */
public class ActConstants {
    public static final String DATE_FORMAT1 = "yyyyMMddHHmmssSSS";
    public static final String DATE_FORMAT2 = "yyyy-MM-dd-HH:mm:ss";
    public static final String DATE_FORMAT3 = "yyyyMMdd";
    public static final String DATE_FORMAT4 = "HHmmssSSS";
    /** 任务完成通知记录状态 **/
    /** 有效 **/
    public static final String TASK_NOTIFY_RECORD_STATUS_EFFECTIVE = "1";
    /** 失效 **/
    public static final String TASK_NOTIFY_RECORD_STATUS_EXPIRE = "0";
    /** 活动状态 **/
    /** 有效 **/
    public static final String DRAW_STATUS_EFFECTIVE = "1";
    /** 失效 **/
    public static final String DRAW_STATUS_EXPIRE = "0";
    /** 奖项配置状态 **/
    /** 有效 */
    public static final String DRAW_CONFIG_STATUS_EFFECTIVE = "1";
    /** 失效 **/
    public static final String DRAW_CONFIG_STATUS_EXPIRE = "0";
    /** 抽奖类型 **/
    /** 积分抽奖 **/
    public static final String DRAW_EXPE_INTEGRAL = "integral";
    /** 次数抽奖 **/
    public static final String DRAW_EXPE_TASK = "task";
    /** 抽奖记录状态 **/
    /** 没中奖 **/
    public static final String DRAW_RECORD_RESULT_NONE = "0";
    /** 中奖待发奖---发奖失败 **/
    public static final String DRAW_RECORD_RESULT_PRIZE = "1";
    /** 发奖成功 **/
    public static final String DRAW_RECORD_RESULT_AWARDS = "2";
    /** 奖品级别--空奖 1654**/
    public static final String PRIZE_LEVEL_BLANK = "blank";
    /** 奖品类型 ***/
    public static final String PRIZE_TYPE_BLANK = "empty";
    /**一账通**/
    public static final String PRIZE_TYPE_VOCHER = "vocher";
    /**实物**/
    public static final String PRIZE_TYPE_MATERIALOBJECT = "materialObject";
    /**积分**/
    public static final String PRIZE_TYPE_INTEGRAL = "integral";
    /**i购券**/
    public static final String PRIZE_TYPE_IEMALL = "iemall";
    /**公开码电子券**/
    public static final String PRIZE_TYPE_PCOUPON = "pcoupon";
    /**隐藏码电子券**/
    public static final String PRIZE_TYPE_SCOUPON = "scoupon";
    /**1656 积分平台虚拟电子券**/
    public static final String PRIZE_TYPE_VCOUPON = "vcoupon";
    /**服务奖品**/
    public static final String PRIZE_TYPE_SERVICE = "service";
    /**保险奖品**/
    public static final String PRIZE_TYPE_INSURANCE = "insurance";
    /**随机红包**/
    public static final String PRIZE_TYPE_WELFARE = "welfare";
    /**一账通现金红包 1654**/
    public static final String PRIZE_TYPE_CASH = "cash";
    /**发奖结果**/
    public static final String AWARD_RESULT_SUCCESS="1";
    public static final String AWARD_RESULT_FAIL="0";
    /**活动类型-转盘抽奖**/
    public static final String DRAW_TYPE_TURN_TABLE="turnTable";
    /**奖品发放通知类型**/
    public static final List<String> AWARD_TYPE=new ArrayList<String>();
    static{
        AWARD_TYPE.add("001");
        AWARD_TYPE.add("002");
        AWARD_TYPE.add("003");
        AWARD_TYPE.add("004");
        AWARD_TYPE.add("005");
        AWARD_TYPE.add("006");
    }
    /**异步发列表**/
    /**请求类型**/
    public static final String TASK_TYPE_VALIDATE="1";
    public static final String TASK_TYPE_SHARE="2";
    public static final String TASK_TYPE_OTHER="3";
    /**
     * 1654
     * 一账通支付
     */
    public static final String TASK_TYPE_PAY="9";
    /**一账通验证状态**/
    public static final String TASK_NOTIFY_VSTATE_TRUE="1";
    public static final String TASK_NOTIFY_VSTATE_FALSE="0";
    /**发将限制规则**/
    public static final String AWARD_TYPE_NONE="none";
    /**单个活动限制**/
    public static final String AWARD_TYPE_DISTINCTION="distinction";
    /**不区分活动限制**/
    public static final String AWARD_TYPE_NODISTINCTION="nodistinction";
    /**用户等级**/
    /**一般用户**/
    public static final String USER_LEVEL_ORDINARY="1";
    /**高价值客户**/
    public static final String USER_LEVEL_SENIOR="2";

    /**领奖方式**/
    /**自动领取**/
    public static final String AWARD_METHOD_AUTO="1";
    /**手动领取**/
    public static final String AWARD_METHOD_MANAL="0";
}
