package com.ruoyi.web.vo.draw;

import com.ruoyi.web.vo.Result;
import com.sinosoft.activity.domain.ActPageConfigUserinfo;

import java.util.List;


public class ActPageConfigUserinfoResult extends Result
{

 private List<ActPageConfigUserinfo> actPageConfigUserinfo;

    public List<ActPageConfigUserinfo> getActPageConfigUserinfo() {
        return actPageConfigUserinfo;
    }

    public void setActPageConfigUserinfo(List<ActPageConfigUserinfo> actPageConfigUserinfo) {
        this.actPageConfigUserinfo = actPageConfigUserinfo;
    }
}
