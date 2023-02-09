package com.ruoyi.system.component;

import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.CacheUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysConfig;
import com.ruoyi.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;

/**
 * 用户 component
 *
 * @author SKaiL
 * @date 2022/10/9
 */
@Component
public class UserComponent
{

    private static final Logger log = LoggerFactory.getLogger(UserComponent.class);

    @Autowired
    private ISysUserService userService;

    public SysUser buildData(SysUser user){
        String zgSid = user.getZgsid();
        String purchasesid = user.getPurchasesid();
        if (StringUtils.isNotEmpty(zgSid)){
            //查询上级主管信息
            SysUser zgUser = userService.selectUserByUserCode(zgSid);
            //将主管姓名临时保存在searchValue字段
            if (StringUtils.isNotNull(zgUser)) {
                user.setSearchValue(zgUser.getUserName());
            }
        }
        if (StringUtils.isNotEmpty(purchasesid)){
            //查询代签主管
            SysUser agentUser = userService.selectUserByUserCode(purchasesid);
            //将代签姓名临时保存在remark字段
            if (StringUtils.isNotNull(agentUser)) {
                user.setRemark(agentUser.getUserName());
            }
        }
        return user;
    }

}
