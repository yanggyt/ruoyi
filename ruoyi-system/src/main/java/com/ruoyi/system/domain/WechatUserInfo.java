package com.ruoyi.system.domain;

import java.util.List;

public class WechatUserInfo {
    String errocode;  //返回码
    String errmsg;  //返回码描述
    String Userid;  //成员UserID
    String name;    //成员名称
    String depatrment;    //成员所属部门id列表
    String order; //部门内的排序值
    String position;    //职务信息
    String mobile;  //手机号码
    String gender;  //性别，0：未定义，1：男，2：女
    String email;   //邮箱
    String is_leader_in_dept; //在所在的部门内是否为上级
    String avatar;  //头像Url
    String thumb_avatar;    //头像缩略图Url
    String telephone;   //座机
    String alias;   //别名
    String address;     //地址
    String open_userid; //全局唯一id
    String main_department; //主部门
    String extattr;  //扩展属性
    String Status;  //激活状态: 1=已激活，2=已禁用，4=未激活，5=退出企业。
    String qr_code;  //员工个人二维码
    String external_position;   //	对外职务
    String external_profile;  //成员对外属性

    public String getErrocode() {
        return errocode;
    }

    public void setErrocode(String errocode) {
        this.errocode = errocode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public String getUserid() {
        return Userid;
    }

    public void setUserid(String userid) {
        Userid = userid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDepatrment() {
        return depatrment;
    }

    public void setDepatrment(String depatrment) {
        this.depatrment = depatrment;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getIs_leader_in_dept() {
        return is_leader_in_dept;
    }

    public void setIs_leader_in_dept(String is_leader_in_dept) {
        this.is_leader_in_dept = is_leader_in_dept;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getThumb_avatar() {
        return thumb_avatar;
    }

    public void setThumb_avatar(String thumb_avatar) {
        this.thumb_avatar = thumb_avatar;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getOpen_userid() {
        return open_userid;
    }

    public void setOpen_userid(String open_userid) {
        this.open_userid = open_userid;
    }

    public String getMain_department() {
        return main_department;
    }

    public void setMain_department(String main_department) {
        this.main_department = main_department;
    }

    public String getExtattr() {
        return extattr;
    }

    public void setExtattr(String extattr) {
        this.extattr = extattr;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    public String getQr_code() {
        return qr_code;
    }

    public void setQr_code(String qr_code) {
        this.qr_code = qr_code;
    }

    public String getExternal_position() {
        return external_position;
    }

    public void setExternal_position(String external_position) {
        this.external_position = external_position;
    }

    public String getExternal_profile() {
        return external_profile;
    }

    public void setExternal_profile(String external_profile) {
        this.external_profile = external_profile;
    }


}
