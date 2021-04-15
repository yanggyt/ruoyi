package com.ruoyi.web.vo.draw;

import com.ruoyi.web.vo.Result;
import com.sinosoft.activity.domain.ActPageConfigGuide;
import com.sinosoft.activity.domain.DrawInfo;
import lombok.Data;

/**
 * TODO
 *
 * @author dy
 * @version 1.0
 * @date 2021/4/14 14:38
 */
@Data
public class ActGuideResult extends Result {
    private ActPageConfigGuide actPageConfigGuide;

    private DrawInfo drawInfo;

    private Integer pageStyle;

    private  Integer actType;

}
