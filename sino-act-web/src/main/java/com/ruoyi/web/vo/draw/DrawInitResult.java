package com.ruoyi.web.vo.draw;

import com.ruoyi.web.vo.Result;

import java.util.ArrayList;
import java.util.List;

/**
 * 抽奖页面初始化vo
 * @author Huayue
 * @version 1664
 */
public class DrawInitResult extends Result {
    private List<Prize> prizes = new ArrayList<Prize>();
    /**
     * 剩余抽奖次数
     */
    private String num;
    /**
     * 消耗积分
     */
    private String integral;
    /**
     * integral
     */
    private String drawType;
    /**
     * 活动规则内容
     */
    private String drawRule;

    public String getIntegral() {
        return integral;
    }

    public void setIntegral(String integral) {
        this.integral = integral;
    }

    public String getDrawType() {
        return drawType;
    }

    public void setDrawType(String drawType) {
        this.drawType = drawType;
    }

    public String getDrawRule() {
        return drawRule;
    }

    public void setDrawRule(String drawRule) {
        this.drawRule = drawRule;
    }

    public List<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}
