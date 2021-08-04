package com.ruoyi.system.domain;

public class EcologyDept {
    String Canceled;
    String supdepid;
    String departmentmark;
    String departmentname;
    String created;
    String departmentcode;
    String modified;
    String id;
    String subcompanyid1;
    String showorder;

    public String getCanceled() {
        return Canceled;
    }

    public void setCanceled(String canceled) {
        Canceled = canceled;
    }

    public String getSupdepid() {
        return supdepid;
    }

    public void setSupdepid(String supdepid) {
        this.supdepid = supdepid;
    }

    public String getDepartmentmark() {
        return departmentmark;
    }

    public void setDepartmentmark(String departmentmark) {
        this.departmentmark = departmentmark;
    }

    public String getDepartmentname() {
        return departmentname;
    }

    public void setDepartmentname(String departmentname) {
        this.departmentname = departmentname;
    }

    public String getCreated() {
        return created;
    }

    public void setCreated(String created) {
        this.created = created;
    }

    public String getDepartmentcode() {
        return departmentcode;
    }

    public void setDepartmentcode(String departmentcode) {
        this.departmentcode = departmentcode;
    }

    public String getModified() {
        return modified;
    }

    public void setModified(String modified) {
        this.modified = modified;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSubcompanyid1() {
        return subcompanyid1;
    }

    public void setSubcompanyid1(String subcompanyid1) {
        this.subcompanyid1 = subcompanyid1;
    }

    public String getShoworder() {
        return showorder;
    }

    public void setShoworder(String showorder) {
        this.showorder = showorder;
    }

    @Override
    public String toString() {
        return "EcologyDept{" +
                "Canceled='" + Canceled + '\'' +
                ", supdepid='" + supdepid + '\'' +
                ", departmentmark='" + departmentmark + '\'' +
                ", departmentname='" + departmentname + '\'' +
                ", created='" + created + '\'' +
                ", departmentcode='" + departmentcode + '\'' +
                ", modified='" + modified + '\'' +
                ", id='" + id + '\'' +
                ", subcompanyid1='" + subcompanyid1 + '\'' +
                ", showorder='" + showorder + '\'' +
                '}';
    }
}
