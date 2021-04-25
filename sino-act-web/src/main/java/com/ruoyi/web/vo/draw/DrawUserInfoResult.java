package com.ruoyi.web.vo.draw;

import com.ruoyi.web.vo.Result;
import com.sinosoft.activity.domain.DrawUserInfo;

/**
 * @author xlh
 * @date 2021/4/23
 */

public class DrawUserInfoResult extends Result {

  private  DrawUserInfo drawUserInfo;

    public DrawUserInfo getDrawUserInfo() {
        return drawUserInfo;
    }

    public void setDrawUserInfo(DrawUserInfo drawUserInfo) {
        this.drawUserInfo = drawUserInfo;
    }
}
