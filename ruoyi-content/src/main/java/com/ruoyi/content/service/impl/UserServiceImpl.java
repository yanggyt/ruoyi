package com.ruoyi.content.service.impl;

import com.ruoyi.content.domain.UserInfo;
import com.ruoyi.content.domain.UserInfoExample;
import com.ruoyi.content.domain.WxUserInfoDto;
import com.ruoyi.content.exception.BusinessException;
import com.ruoyi.content.mapper.UserInfoMapper;
import com.ruoyi.content.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.UnsupportedEncodingException;
import java.util.Base64;
import java.util.Base64.Encoder;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoMapper userInfoMapper;

    private static final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public String getUserId(WxUserInfoDto wxUserInfoDto) {
        UserInfoExample example = new UserInfoExample();
        String userid = "";
        UserInfoExample.Criteria criteria = example.createCriteria();
        String openid = wxUserInfoDto.getOpenid();
        String subscribe = wxUserInfoDto.getSubscribe();
        if (StringUtils.isBlank(openid)) {
            LOGGER.info("获取用户userid时,从网关回掉回来的openid为空");
            throw new BusinessException("回掉回来的微信用户信息有误,openid为空");
        }
        if (StringUtils.isBlank(subscribe)) {
            subscribe = "0";
        }
        criteria.andOpenidEqualTo(openid);
        List<UserInfo> userInfos = userInfoMapper.selectByExample(example);
        if (null != userInfos && userInfos.size() > 0) {
            UserInfo userInfo = new UserInfo();
            Integer userId = userInfos.get(0).getUserId();
            userid = userId.toString();
            if (!"1".equals(subscribe)) {    //没有关注公众号
                LOGGER.info("该用户未关注公众号,已在本项目中注册,入库信息openid[{}],nickname[{}],headimgurl[{}]", openid, wxUserInfoDto.getNickName(), wxUserInfoDto.getHeadImgUrl());
                userInfo.setUserId(userId);
                userInfo.setNickName("");
                userInfo.setHeadImgUrl("");
                int cont = userInfoMapper.updateByPrimaryKeySelective(userInfo);
                if (cont != 1) {
                    LOGGER.info("根据用户微信信息失败");
                }
            } else {
                //已经关注公众号
                LOGGER.info("该用户已注公众号,已在本项目中注册,入库信息openid[{}],nickname[{}],headimgurl[{}]", openid, wxUserInfoDto.getNickName(), wxUserInfoDto.getHeadImgUrl());
                userInfo.setUserId(userId);
                Encoder encoder = Base64.getEncoder();
                try {
                    userInfo.setNickName(encoder.encodeToString(wxUserInfoDto.getNickName().getBytes("UTF-8")));
                } catch (UnsupportedEncodingException e) {
                    LOGGER.info("保存用户微信信息时,用户昵称转码异常");
                }
//				userInfo.setNickName(wxUserInfoDto.getNickName());
                userInfo.setHeadImgUrl(wxUserInfoDto.getHeadImgUrl());
                int cont = userInfoMapper.updateByPrimaryKeySelective(userInfo);
                if (cont != 1) {
                    LOGGER.info("更新用户微信信息失败");
                }
            }
            return userid;
        } else {
            UserInfo userInfo = new UserInfo();
            try {
                if (!"1".equals(subscribe)) { //没有关注公众号
                    LOGGER.info("该用户未关注公众号,还未在本项目中注册,入库信息openid[{}],nickname[{}],headimgurl[{}]", openid, wxUserInfoDto.getNickName(), wxUserInfoDto.getHeadImgUrl());
                    userInfo.setOpenid(openid);
                    int cont = userInfoMapper.insertSelective(userInfo);
                    if (cont != 1) {
                        throw new BusinessException("用户微信信息入库失败,openid===" + openid);
                    } else {
                        return userInfo.getUserId().toString();
                    }
                } else {
                    //已经关注公众号
                    LOGGER.info("该用户已经关注公众号,还未在本项目中注册,入库信息openid[{}],nickname[{}],headimgurl[{}]", openid, wxUserInfoDto.getNickName(), wxUserInfoDto.getHeadImgUrl());
                    userInfo.setOpenid(openid);
                    Encoder encoder = Base64.getEncoder();
                    userInfo.setNickName(encoder.encodeToString(wxUserInfoDto.getNickName().getBytes("UTF-8")));
//					userInfo.setNickName(wxUserInfoDto.getNickName());
                    userInfo.setHeadImgUrl(wxUserInfoDto.getHeadImgUrl());
                    int cont = userInfoMapper.insertSelective(userInfo);
                    if (cont != 1) {
                        throw new BusinessException("用户微信信息入库失败,openid===" + openid);
                    } else {
                        return userInfo.getUserId().toString();
                    }
                }
            } catch (Exception e) {
                throw new BusinessException("用户微信信息入库异常,异常信息[{" + e.getMessage() + "}]");
            }

        }
    }

}
