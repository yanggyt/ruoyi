package com.ruoyi.web.controller.draw;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.dto.*;
import com.ruoyi.web.vo.Result;
import com.ruoyi.web.vo.draw.*;
import com.sinosoft.activity.domain.DrawConfig;
import com.sinosoft.activity.domain.*;
import com.sinosoft.activity.service.IActPageConfigUserinfoService;
import com.sinosoft.activity.service.IDrawConfigService;
import com.sinosoft.activity.service.IDrawInfoService;
import com.sinosoft.activity.service.IDrawTaskNotifyService;
import com.sinosoft.activity.service.*;
import com.sinosoft.activity.service.impl.ActPageConfigUserinfoServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.bean.WxOAuth2UserInfo;
import me.chanjar.weixin.common.bean.oauth2.WxOAuth2AccessToken;
import me.chanjar.weixin.mp.api.WxMpService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 抽奖Controller
 * @author huayue
 * @since 2020-08-13
 */
@Api("活动管理")
@Controller
@RequestMapping("/draw")
public class DrawController {

    private static final Logger logger = LoggerFactory.getLogger(DrawController.class);
    @Autowired
    private IDrawConfigService drawConfigService;
    @Autowired
    private IDrawTaskNotifyService drawTaskNotifyService;
    @Autowired
    private IDrawInfoService drawInfoService;
    @Autowired
    private WxMpService wxService;
    @Autowired
    private IActPageConfigGuideService actPageConfigGuideService;
    @Autowired
    private IActConfigService actConfigService;
    @Autowired
    private ActPageConfigUserinfoServiceImpl actPageConfigUserinfoServiceImpl;
    @Autowired
    private IActPageConfigSubscribeService iActPageConfigSubscribeService;

    @Autowired
    private IActPageConfigUserinfoService  iActPageConfigUserinfoService;
    @Autowired
    private IDrawRecordService iDrawRecordService;

    @Autowired
    private IDrawUserInfoService iDrawUserInfoService;
    @Autowired
    private IDrawTaskNotifyService taskNotifyService;
    @Autowired
    private IDrawRuleService drawRuleService;

    private WxOAuth2UserInfo getUserInfo(HttpServletRequest request, String code) throws Exception {
//        if (!this.wxService.switchover(appid)) {
//
//        }
        HttpSession session = request.getSession();
        WxOAuth2UserInfo wxOAuth2UserInfo = (WxOAuth2UserInfo) session.getAttribute("WxOAuth2UserInfo");
        if (wxOAuth2UserInfo != null) {
            return wxOAuth2UserInfo;
        }
        if (StringUtils.isBlank(code)) {
            return null;
        }
        WxOAuth2AccessToken accessToken = wxService.getOAuth2Service().getAccessToken(code);
        wxOAuth2UserInfo = wxService.getOAuth2Service().getUserInfo(accessToken, null);
        session.setAttribute("WxOAuth2UserInfo", wxOAuth2UserInfo);
        return wxOAuth2UserInfo;
    }
    @RequestMapping(value="/init.action", method = RequestMethod.POST)
    @ResponseBody
    public DrawInitResult init(HttpServletRequest request, String drawCode, String code) {
        DrawInitResult result = new DrawInitResult();
        try {
            getUserInfo(request, code);

            DrawConfig drawConfig = new DrawConfig();
            drawConfig.setDRAWCODE(drawCode);
            List<DrawConfig> drawConfigs = drawConfigService.selectDrawConfigList(drawConfig);

            List<Prize> prizes = result.getPrizes();
            for (DrawConfig config : drawConfigs) {
                Prize prize = new Prize();
                String prizeCode = config.getPRIZECODE();
                String prizeName = config.getPRIZENAME();
                String prizeImg = null;
                prize.setPrizeCode(prizeCode);
                prize.setPrizeName(prizeName);
                prize.setPrizeImg(prizeImg);
                prizes.add(prize);
            }
        } catch (Exception e) {
            result.setRespCode("-1");
            result.setRespMsg("系统异常，请稍后再试");
            logger.error("DrawController.init ex: ", e);
        }
        return result;
    }
    @RequestMapping(value="/num.action", method = RequestMethod.POST)
    @ResponseBody
    public DrawNumResult num(HttpServletRequest request, String drawCode) {
        DrawNumResult result = new DrawNumResult();
        try {
            WxOAuth2UserInfo userInfo = this.getUserInfo(request, null);
            if (userInfo == null) {
                result.setRespCode("-2");
                result.setRespMsg("会话已失效，请重新登录");
                logger.info("DrawController.init userId is null");
                return result;
            }
            String userId = userInfo.getOpenid();
            int num = drawTaskNotifyService.selectDrawNumByUserId(userId, drawCode);
            //查询抽奖次数
            result.setTotal(null);
            result.setNum(num+"");
        } catch (Exception e) {
            result.setRespCode("-1");
            result.setRespMsg("系统异常，请稍后再试");
            logger.error("DrawController.num ex: ", e);
        }
        return result;
    }

    @RequestMapping(value="/start.action", method = RequestMethod.POST)
    @ResponseBody
    public DrawResult start(HttpServletRequest request, String drawCode) {
        DrawResult result = new DrawResult();
        try {
            HttpSession session = request.getSession();
            WxOAuth2UserInfo userInfo = this.getUserInfo(request, null);
            if (userInfo == null) {
                result.setRespCode("-2");
                result.setRespMsg("会话已失效，请重新登录");
                logger.info("DrawController.start openid is null");
                return result;
            }
            String openid = userInfo.getOpenid();
            String userName = userInfo.getNickname();
            DrawActivityRequest body = new DrawActivityRequest();
            body.setDrawCode(drawCode);
            body.setUserId(openid);
            body.setUserType("01");
            body.setUserName(userName);
            body.setDrawTime(DateUtils.dateTimeNow(DateUtils.YYYYMMDDHHMMSSS));
            body.setMerchantCode("MerchantCode");
            body.setMerchantSysCode("MerchantSysCode");
            body.setBusinessArea("6");
            body.setChannel("WEIXIN");
            body.setSource("24");
        } catch (Exception e) {
            result.setRespCode("-1");
            result.setRespMsg("系统异常，请稍后再试");
            logger.error("DrawController.start ex: ", e);
        }
        return result;
    }

    @RequestMapping(value="/info.action", method = RequestMethod.POST)
    @ResponseBody
    public ActPageConfigUserinfoResult info(HttpServletRequest request, String actCode) {
        ActPageConfigUserinfoResult result = new ActPageConfigUserinfoResult();
        logger.info("活动编码"+actCode);
        try{
            List<ActPageConfigUserinfo> prizes = new ArrayList<ActPageConfigUserinfo>();
            ActPageConfigUserinfo actPageConfigUserinfo = iActPageConfigUserinfoService.selectActPageConfigUserinfoByCode(actCode);
            prizes.add(actPageConfigUserinfo);
            result.setActPageConfigUserinfo(prizes);
        }catch (Exception e){
            result.setRespCode("-1");
            result.setRespMsg("系统异常，请稍后再试");
            logger.error("DrawController.prizes ex: ", e);
        }
        return  result;
    }
    @RequestMapping(value="/prizes.action", method = RequestMethod.POST)
    @ResponseBody
    public PrizeResult prizes(HttpServletRequest request, String drawCode, String isAll) {
        PrizeResult result = new PrizeResult();
        List<DrawRecord> prizes = new ArrayList<DrawRecord>();
        try {

            HttpSession session = request.getSession();
            WxOAuth2UserInfo userInfo = getUserInfo(request, null);
         /*   if (userInfo == null && !"1".equals(isAll)) {
                result.setRecord(prizes);
                return result;
            }*/
            String userId ="1328495419744";
         /*   if (!"1".equals(isAll)) {
                 Long userId1 = ShiroUtils.getUserId();
                userId = userInfo.getOpenid();
            }*/
            List<DrawRecord> drawRecords = iDrawRecordService.selectDrawRecordCodeList(drawCode, userId);
            for (DrawRecord draw: drawRecords) {
                String mobile = draw.getPHONE();
                if (StringUtils.isNotBlank(mobile)) {
                    draw.setPHONE(mobile.substring(0, 3) + "****" + mobile.substring(7, mobile.length()));
                }
            }
            result.setRecord(drawRecords);
        } catch (Exception e) {
            result.setRespCode("-1");
            result.setRespMsg("系统异常，请稍后再试");
            logger.error("DrawController.prizes ex: ", e);
        }
        return result;
    }

    /**
     * 增加抽奖次数
     * @param request
     * @param drawCode
     * @param taskType
     * @return
     */
    @ApiOperation("增加抽奖次数")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "drawCode", value = "活动编码", required = true, dataType = "string", paramType = "path"),
            @ApiImplicitParam(name = "taskType", value = "第三方活动编码", required = true, dataType = "string", paramType = "path")
    })
    @RequestMapping(value="/addDrawNum", method = RequestMethod.POST)
    @ResponseBody
    public Result addDrawNum(HttpServletRequest request, String drawCode, String taskType) {
        Result result = new Result();
        try {
            HttpSession session = request.getSession();

            WxOAuth2UserInfo userInfo = getUserInfo(request, null);
            if (userInfo == null) {
                result.setRespCode("-2");
                result.setRespMsg("未授权登录");
                logger.error("DrawController.addDrawNum openId is null");
                return result;
            }
            String openId = userInfo.getOpenid();
            DrawTaskNotify drawTaskNotify = new DrawTaskNotify();
            drawTaskNotify.setISSPECIALFLAG("0");
            drawTaskNotify.setUSERID(openId);
            drawTaskNotify.setTASKTYPE(taskType);
            drawTaskNotify.setADDNUMBER(1);
            drawTaskNotify.setDRAWCODE(drawCode);
            taskNotifyService.addDrawNum(drawTaskNotify);
            //赠送抽奖机会
//            TaskNotifyRequestBody requestBody =  new TaskNotifyRequestBody();
//            requestBody.setTaskId("");
//            requestBody.setIsLimited("0");
//            requestBody.setUserId(openId);
//            requestBody.setTaskType(taskType);
//            requestBody.setGetNumber("1");
//            requestBody.setDrawCode(drawCode);
//            TaskNotifyResponse taskNotifyResponse = null;//activityService.taskNotify(requestBody).get_return();
//            TaskNotifyResponseHeader header = taskNotifyResponse.getHeader();
//            String resultCode = header.getResultCode();
//            if ("GT0000602".equals(resultCode)) {
//                result.setRespCode("04");
//                result.setRespMsg("积分不足哦");
//                return result;
//            }
//            String number = taskNotifyResponse.getResponseBody().getGetNumber();
//            Integer num = Integer.valueOf(number);
//            if (num == null || num < 1) {
//                result.setRespCode("03");
//                result.setRespMsg("已经赠送过抽奖机会");
//            }
        } catch (Exception e) {
            result.setRespCode("-1");
            result.setRespMsg("系统异常，请稍后再试");
            logger.error("DrawController.addDrawNum ex: ", e);
        }
        return result;
    }
    @RequestMapping(value="/saveAddress", method = RequestMethod.POST)
    @ResponseBody
    public Result saveAddress(HttpServletRequest request, DrawUserInfo userInfo) {
        Result result = new Result();
        try {
            userInfo.setCreateTime(new Date());
            iDrawUserInfoService.insertDrawUserInfo(userInfo);
        } catch (Exception e) {
            result.setRespCode("-1");
            result.setRespMsg("系统异常，请稍后再试");
            logger.error("DrawController.saveAddress ex: ", e);
        }
        return result;
    }

    /**
     *  获取活动配置展示信息，根据活动编码
     *  todo 判断活动状态
     * @param request
     * @param actCode
     * @return
     */
    @ApiOperation("获取活动配置展示信息")
    @ApiImplicitParam(name = "actCode", value = "活动编码", required = true, dataType = "string", paramType = "path")
    @RequestMapping(value="/guide", method = RequestMethod.POST)
    @ResponseBody
    public Result getActGuide(HttpServletRequest request, String actCode) {
        ActGuideResult result = new ActGuideResult();
        try {
            //查询活动内容
            DrawInfo drawInfo = new DrawInfo();
            drawInfo.setDRAWCODE(actCode);
            drawInfo.setSTATUS("1");
            List<DrawInfo> drawInfos = drawInfoService.selectDrawInfoList(drawInfo);
            result.setDrawInfo(drawInfos.get(0));
            //查询获取风格
            ActConfig actConfig = actConfigService.selectActConfigByCode(actCode);
            result.setPageStyle(actConfig.getPageStyle());
            result.setActType(actConfig.getActType());
            //获取页面展示内容配置
            ActPageConfigGuide actPageConfigGuide = actPageConfigGuideService.selectActPageConfigGuideByCode(actCode);
            result.setActPageConfigGuide(actPageConfigGuide);
        }catch (Exception e){
            result.setRespCode("-1");
            result.setRespMsg("系统异常，请稍后再试");
            logger.error("DrawController.saveAddress ex: ", e);
        }
        return result;
    }

    /**
     *  获取活动配置展示信息，根据活动编码
     *
     * @param request
     * @param actCode
     * @return
     */
    @ApiOperation("获取二维码信息")
    @ApiImplicitParam(name = "actCode", value = "活动编码", required = true, dataType = "string", paramType = "path")
    @RequestMapping(value="/qrCode", method = RequestMethod.POST)
    @ResponseBody
    public ActPageConfigSubscribeResult qrcode(HttpServletRequest request, String actCode) {
        ActPageConfigSubscribeResult result = new  ActPageConfigSubscribeResult ();
        try {
            List<ActPageConfigSubscribe> list = new ArrayList<>();

            ActPageConfigSubscribe subscribe = iActPageConfigSubscribeService.selectActPageConfigSubscribeByCode(actCode);
            list.add(subscribe);
            result.setActPageConfigSubscribe(list);
        }catch (Exception e){
            result.setRespCode("-1");
            result.setRespMsg("系统异常，请稍后再试");
            logger.error("DrawController.qrCode ex: ", e);
        }
        return result;
    }
    @ApiOperation("获取活动规则描述")
    @ApiImplicitParam(name = "actCode", value = "活动编码", required = true, dataType = "string", paramType = "path")
    @RequestMapping(value="/describe", method = RequestMethod.POST)
    @ResponseBody
    public Result findDescribe(HttpServletRequest request, String actCode){
        RuleResult result = new RuleResult();
        try {
            DrawRule drawRule = drawRuleService.selectDrawRuleByCode(actCode);
            result.setDrawRule(drawRule);
        }catch (Exception e){
            result.setRespCode("-1");
            result.setRespMsg("系统异常，请稍后再试");
            logger.error("DrawController.saveAddress ex: ", e);
        }
        return result;
    }

}
