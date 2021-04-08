package com.sinosoft.activity.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.sinosoft.activity.domain.*;

/**
 * 活动主类
 *
 * @author dy
 * @version 1.0
 * @date 2021/4/8 10:15
 */
public class ActVO extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
     *第一步：基本信息
     */
    DrawInfo drawInfo;
    /**
     *第三步：展示内容
     */
    ActPageConfigGuide actPageConfigGuide;
    /**
     * 第四步 选择玩法
     */
    DrawRule drawRule;
    /**
     * 第五步 收集信息
     */
    ActPageConfigUserinfo actPageConfigUserinfo;
    /**
     * 第六步：分享信息  (包括：页面风格（第二步包含在这里）；活动类型)
     */
    ActConfig actConfig;
    /**
     * 第七步：二维码
     */
    ActPageConfigSubscribe actPageConfigSubscribe;

    public DrawInfo getDrawInfo() {
        return drawInfo;
    }

    public void setDrawInfo(DrawInfo drawInfo) {
        this.drawInfo = drawInfo;
    }

    public ActPageConfigGuide getActPageConfigGuide() {
        return actPageConfigGuide;
    }

    public void setActPageConfigGuide(ActPageConfigGuide actPageConfigGuide) {
        this.actPageConfigGuide = actPageConfigGuide;
    }

    public DrawRule getDrawRule() {
        return drawRule;
    }

    public void setDrawRule(DrawRule drawRule) {
        this.drawRule = drawRule;
    }

    public ActPageConfigUserinfo getActPageConfigUserinfo() {
        return actPageConfigUserinfo;
    }

    public void setActPageConfigUserinfo(ActPageConfigUserinfo actPageConfigUserinfo) {
        this.actPageConfigUserinfo = actPageConfigUserinfo;
    }

    public ActConfig getActConfig() {
        return actConfig;
    }

    public void setActConfig(ActConfig actConfig) {
        this.actConfig = actConfig;
    }

    public ActPageConfigSubscribe getActPageConfigSubscribe() {
        return actPageConfigSubscribe;
    }

    public void setActPageConfigSubscribe(ActPageConfigSubscribe actPageConfigSubscribe) {
        this.actPageConfigSubscribe = actPageConfigSubscribe;
    }
}
