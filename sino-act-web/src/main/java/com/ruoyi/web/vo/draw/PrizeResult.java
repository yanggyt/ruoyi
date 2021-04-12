package com.ruoyi.web.vo.draw;

import com.ruoyi.web.vo.Result;

import java.util.List;

public class PrizeResult extends Result {
    private List<Prize> prizes;

    public List<Prize> getPrizes() {
        return prizes;
    }

    public void setPrizes(List<Prize> prizes) {
        this.prizes = prizes;
    }
}
