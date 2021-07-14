package com.ruoyi.system.domain;

public class EcologyUser {
    String subcompanyid1;
    String password;
    String id;
    String loginid;
    String lastname;
    String departmentid;
    String mobile;
    String email;
    String sex;
    String Status;

    public String getSubcompanyid1() {
        return subcompanyid1;
    }

    public void setSubcompanyid1(String subcompanyid1) {
        this.subcompanyid1 = subcompanyid1;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLoginid() {
        return loginid;
    }

    public void setLoginid(String loginid) {
        this.loginid = loginid;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getDepartmentid() {
        return departmentid;
    }

    public void setDepartmentid(String departmentid) {
        this.departmentid = departmentid;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }

    @Override
    public String toString() {
        return "EcologyUser{" +
                "subcompanyid1='" + subcompanyid1 + '\'' +
                ", password='" + password + '\'' +
                ", id='" + id + '\'' +
                ", loginid='" + loginid + '\'' +
                ", lastname='" + lastname + '\'' +
                ", departmentid='" + departmentid + '\'' +
                ", mobile='" + mobile + '\'' +
                ", email='" + email + '\'' +
                ", sex='" + sex + '\'' +
                ", Status='" + Status + '\'' +
                '}';
    }
}
