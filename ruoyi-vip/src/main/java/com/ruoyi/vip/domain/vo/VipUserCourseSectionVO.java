package com.ruoyi.vip.domain.vo;

import com.ruoyi.vip.domain.VipUserCourseSection;

/**
 * 自定义学习进度
 *
 * @author zhujj
 * @date 2019-01-15
 */
public class VipUserCourseSectionVO extends VipUserCourseSection {

    private String trainCourseName;
    private String trainCourseSectionName;
    private String userName;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTrainCourseName() {
        return trainCourseName;
    }

    public void setTrainCourseName(String trainCourseName) {
        this.trainCourseName = trainCourseName;
    }

    public String getTrainCourseSectionName() {
        return trainCourseSectionName;
    }

    public void setTrainCourseSectionName(String trainCourseSectionName) {
        this.trainCourseSectionName = trainCourseSectionName;
    }
}
