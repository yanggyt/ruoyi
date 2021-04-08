package com.sinosoft.activity.vo;

import com.ruoyi.common.core.domain.BaseEntity;
import com.sinosoft.activity.domain.ActPageConfigGuide;
import com.sinosoft.activity.domain.DrawInfo;

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
     * 第三步--活动展示内容配置
     */
    ActPageConfigGuide actPageConfigGuide;
    /**
     * 第一步 第四步
     */
    DrawInfo drawInfo;



}
