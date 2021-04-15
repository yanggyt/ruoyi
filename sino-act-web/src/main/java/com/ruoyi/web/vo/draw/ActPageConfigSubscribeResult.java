package com.ruoyi.web.vo.draw;

import com.ruoyi.web.vo.Result;
import com.sinosoft.activity.domain.ActPageConfigSubscribe;

import java.util.List;

public class ActPageConfigSubscribeResult extends Result {
    private List<ActPageConfigSubscribe> actPageConfigSubscribe;

    public List<ActPageConfigSubscribe> getActPageConfigSubscribe() {
        return actPageConfigSubscribe;
    }

    public void setActPageConfigSubscribe(List<ActPageConfigSubscribe> actPageConfigSubscribe) {
        this.actPageConfigSubscribe = actPageConfigSubscribe;
    }
}
