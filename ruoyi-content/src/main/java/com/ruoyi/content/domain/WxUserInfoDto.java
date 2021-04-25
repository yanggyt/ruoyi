package com.ruoyi.content.domain;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 用户微信基本信息
*
* @author liu.hx  
* @date 2018年4月3日  
*
 */
public class WxUserInfoDto implements Serializable{
	
	private static final long serialVersionUID = -2402610715101110825L;
	private String subscribe;     	//关注公众号标识  用户是否订阅该公众号标识，值为0时，代表此用户没有关注该公众号，拉取不到其余信息。
	private String openid;     		//用户openid
	@JsonProperty("nickname")
	private String nickName;     	//用户的昵称	  
	private String sex;     		//用户的性别	  值为1时是男性，值为2时是女性，值为0时是未知
	private String city;     		//用户所在城市	  
	private String country;     	//用户所在国家	  
	private String province;     	//用户所在省份	  
	private String language;     	//用户的语言	 
	@JsonProperty("headimgurl")
	private String headImgUrl;     	//用户头像		 
	@JsonProperty("groupid")
	private String groupId; 		//用户所在的分组  
	
	
	@Override
	public String toString() {
		return "WxUserInfoDto [subscribe=" + subscribe + ", openid=" + openid + ", nickName=" + nickName + ", sex="
				+ sex + ", city=" + city + ", country=" + country + ", province=" + province + ", language=" + language
				+ ", headImgUrl=" + headImgUrl + ", groupId=" + groupId + "]";
	}
	public String getSubscribe() {
		return subscribe;
	}
	public void setSubscribe(String subscribe) {
		this.subscribe = subscribe;
	}
	public String getOpenid() {
		return openid;
	}
	public void setOpenid(String openid) {
		this.openid = openid;
	}
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getHeadImgUrl() {
		return headImgUrl;
	}
	public void setHeadImgUrl(String headImgUrl) {
		this.headImgUrl = headImgUrl;
	}
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
}
