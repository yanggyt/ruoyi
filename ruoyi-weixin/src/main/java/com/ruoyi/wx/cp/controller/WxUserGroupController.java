package com.ruoyi.wx.cp.controller;

import com.ruoyi.wx.cp.config.WxCpConfiguration;
import com.ruoyi.wx.cp.config.WxCpProperties;
import com.ruoyi.wx.cp.utils.JsonUtils;
import me.chanjar.weixin.cp.api.WxCpDepartmentService;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.bean.WxCpDepart;
import me.chanjar.weixin.cp.bean.WxCpXmlMessage;
import me.chanjar.weixin.cp.bean.WxCpXmlOutMessage;
import me.chanjar.weixin.cp.config.WxCpInMemoryConfigStorage;
import me.chanjar.weixin.cp.util.crypto.WxCpCryptUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @author Binary Wang(https://github.com/binarywang)
 */
@RestController
@RequestMapping("/wx/cp/user/group")
public class WxUserGroupController {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired

    private WxCpProperties properties;

    @GetMapping("/departAllList")
    public List<WxCpDepart> departAllList() {
        this.logger.info("\n获取组织机构");
        try {
            final WxCpService wxCpService = WxCpConfiguration.getCpService(999999);
            WxCpDepartmentService departmentService = wxCpService.getDepartmentService();
            List<WxCpDepart> list = departmentService.list(null);
            return list;
        } catch (Exception e) {
            this.logger.info("\n获取组织机构出错" + e.getMessage());
        }
        return null;
    }

    @GetMapping("/insert")
    public Object insert(WxCpDepart wxCpDepart) {
        this.logger.info("\n获取组织机构");
        try {
            WxCpDepartmentService departmentService = WxCpConfiguration.getCpService(999999).getDepartmentService();
            Integer integer = departmentService.create(wxCpDepart);
            return integer;
        } catch (Exception e) {
            this.logger.info("\n获取组织机构出错" + e.getMessage());
        }
        return null;
    }

    @GetMapping("/update")
    public List<WxCpDepart> update() {
        this.logger.info("\n获取组织机构");
        try {
            final WxCpService wxCpService = WxCpConfiguration.getCpService(999999);
            WxCpDepartmentService departmentService = wxCpService.getDepartmentService();
            List<WxCpDepart> list = departmentService.list(null);
            return list;
        } catch (Exception e) {
            this.logger.info("\n获取组织机构出错" + e.getMessage());
        }
        return null;
    }

    @GetMapping("/delete")
    public List<WxCpDepart> delete() {
        this.logger.info("\n获取组织机构");
        try {
            final WxCpService wxCpService = WxCpConfiguration.getCpService(999999);
            WxCpDepartmentService departmentService = wxCpService.getDepartmentService();
            List<WxCpDepart> list = departmentService.list(null);
            return list;
        } catch (Exception e) {
            this.logger.info("\n获取组织机构出错" + e.getMessage());
        }
        return null;
    }

}
