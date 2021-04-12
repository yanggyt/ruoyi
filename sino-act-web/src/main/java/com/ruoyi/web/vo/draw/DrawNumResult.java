package com.ruoyi.web.vo.draw;

import com.ruoyi.web.vo.Result;

import java.util.ArrayList;
import java.util.List;

public class DrawNumResult extends Result {
    /**
     * 总抽奖次数
     */
    private String total;
    /**
     * 剩余抽奖次数
     */
    private String num;

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

}
