package com.ruoyi.web.controller.draw;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.dto.DrawActivityRequest;
import com.ruoyi.web.vo.Result;
import com.ruoyi.web.vo.draw.*;
import com.sinosoft.activity.domain.DrawConfig;
import com.sinosoft.activity.service.IDrawConfigService;
import com.sinosoft.activity.service.IDrawInfoService;
import com.sinosoft.activity.service.IDrawTaskNotifyService;
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
import java.util.List;

/**
 * 抽奖Controller
 * @author huayue
 * @since 2020-08-13
 */
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

    @RequestMapping(value="/prizes.action", method = RequestMethod.POST)
    @ResponseBody
    public PrizeResult prizes(HttpServletRequest request, String drawCode, String isAll) {
        PrizeResult result = new PrizeResult();
        List<Prize> prizes = new ArrayList<Prize>();
        try {
            HttpSession session = request.getSession();
            WxOAuth2UserInfo userInfo = getUserInfo(request, null);
            if (userInfo == null && !"1".equals(isAll)) {
                result.setPrizes(prizes);
                return result;
            }
            String userId = null;
            if (!"1".equals(isAll)) {
                userId = userInfo.getOpenid();
            }
//            AwardPrizeListResponse awardPrizeListResponse = activityService.awardPrizeList(drawCode, userId).get_return();
//            AwardPrizeListResponseHeader header = awardPrizeListResponse.getHeader();
//            result.setRespCode(header.getResultCode());
//            result.setRespMsg(header.getResultInfo());
//            AwardPrizeListResponseBody responseBody = awardPrizeListResponse.getResponseBody();
//            AwardPrizeList[] awardPrizes = responseBody.getAwardPrizeLists();
//            if (awardPrizes != null) {
//                for (int i = 0; i < awardPrizes.length; i++) {
//                    AwardPrizeList awardPrize = awardPrizes[i];
//                    Prize prize = new Prize();
//                    prize.setPrizeCode(awardPrize.getPrizeCode());
//                    prize.setPrizeName(awardPrize.getPrizeName());
//                    prize.setPrizeType(awardPrize.getPrizeType());
//                    prize.setDrawTime(DateUtil.convertDate(DateUtil.convertStringToDate(awardPrize.getDrawTime(), DateUtil.YYYYMMDDHHMMSSS), "yyyy/MM/dd HH:mm"));
//                    prize.setStatus(awardPrize.getStatus());
//                    prize.setExtId(awardPrize.getExtId());
//                    prize.setGatewayFlow(awardPrize.getGatewayFolw());
//                    String userName = awardPrize.getUserName();
//                    if (StringUtils.isNotBlank(userName)) {
//                        int end = 1;
//                        if (userName.length()==2) {
//                            end = 0;
//                        }
//                        prize.setUserName(StringUtil.getStarString2(userName, 1, end));
//                    }
//                    String mobile = awardPrize.getMobile();
//                    if (StringUtils.isNotBlank(mobile)) {
//                        prize.setMobile(StringUtil.getStarString2(mobile, 3, 4));
//                    }
//                    prizes.add(prize);
//                }
//            }
            result.setPrizes(prizes);
        } catch (Exception e) {
            result.setRespCode("-1");
            result.setRespMsg("系统异常，请稍后再试");
            logger.error("DrawController.prizes ex: ", e);
        }
        return result;
    }
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
    @RequestMapping(value="/saveAddress.action", method = RequestMethod.POST)
    @ResponseBody
    public Result saveAddress(HttpServletRequest request, String drawCode, String uname, String phone, String addr, String flow) {
        Result result = new Result();
        try {
            HttpSession session = request.getSession();
            WxOAuth2UserInfo userInfo = getUserInfo(request, null);
            if (userInfo == null) {
                result.setRespCode("-2");
                result.setRespMsg("会话已失效，请重新登录");
                logger.info("DrawController.saveAddress userId is null");
                return result;
            }
            if (StringUtils.isBlank(uname)) {
                result.setRespCode("-4");
                result.setRespMsg("请输入姓名");
                return result;
            }
//            String validateMobile = CommonValidate.validateMobile(phone, "1");
//            if (StringUtils.isNotBlank(validateMobile)) {
//                result.setRespCode("-4");
//                result.setRespMsg(validateMobile);
//                return result;
//            }
            String userId = userInfo.getOpenid();
//            SaveUserAddressRequestBody requestBody = new SaveUserAddressRequestBody();
//            requestBody.setDrawCode(drawCode);
//            requestBody.setGatewayFlow(flow);
//            requestBody.setUserId(userId);
//            requestBody.setUserName(uname);
//            requestBody.setPhone(phone);
//            requestBody.setAddress(addr);
//            //实物留资
//            requestBody.setNotifyType("007");
//            requestBody.setCity("无");
//            activityService.saveUserAddress(requestBody);
        } catch (Exception e) {
            result.setRespCode("-1");
            result.setRespMsg("系统异常，请稍后再试");
            logger.error("DrawController.saveAddress ex: ", e);
        }
        return result;
    }

}
