package com.ruoyi.web.controller.system.base;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;

public class WebController extends BaseController {

    protected SysUser getUser(){
        return ShiroUtils.getSysUser();
    }
}
