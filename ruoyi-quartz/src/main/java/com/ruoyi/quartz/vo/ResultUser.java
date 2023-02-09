package com.ruoyi.quartz.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * 同步用户VO
 */
@Data
public class ResultUser implements Serializable
{
    private String staffcode;
    private String account;
    private String email;
    private String birthday;
    private String realname;
    private String password;
    private String departmentcode;
    private String officest;
    private String status;
    private String zhuanzhengdate;
    private String zhiwei;
    private String position;
    private String agentsid;
    private String higherup;
    private String ruzhidate;
    private String zgsid;
    private String companyname;
    private String ecompany;
    private String zjnumber;
    private String mobile;
    private String relativename;
    private String relativephone;
    private String offices;
    private String jobcode;
    private String bank;
    private String banknumber;
    private String sex;
}
