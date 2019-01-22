package com.ruoyi.wx.cp.controller;

import com.ruoyi.common.annotation.Log;
import com.ruoyi.common.base.AjaxResult;
import com.ruoyi.common.enums.BusinessType;
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

    @Log(title = "上传文件", businessType = BusinessType.INSERT)
    @GetMapping("/departAllList")
    public AjaxResult departAllList() {
        this.logger.info("\n获取组织机构");
        try {
            final WxCpService wxCpService = WxCpConfiguration.getCpService(999999);
            WxCpDepartmentService departmentService = wxCpService.getDepartmentService();
            List<WxCpDepart> list = departmentService.list(null);
            return AjaxResult.success(list,"获取组织机构成功");
        } catch (Exception e) {
            this.logger.info("\n获取组织机构出错" + e.getMessage());
            return AjaxResult.error("获取组织机构出错");
        }
    }

    @GetMapping("/insert")
    public AjaxResult insert(WxCpDepart wxCpDepart) {
        this.logger.info("新增组织机构");
        try {
            WxCpDepartmentService departmentService = WxCpConfiguration.getCpService(999999).getDepartmentService();
            Integer id = departmentService.create(wxCpDepart);
            return AjaxResult.success(id,"新增组织机构成功");
        } catch (Exception e) {
            this.logger.info("\n新增组织机构" + e.getMessage());
            return AjaxResult.error("新增组织机构出错");
        }
    }

    @GetMapping("/update")
    public AjaxResult update(WxCpDepart wxCpDepart) {
        this.logger.info("\n获取组织机构");
        try {
            WxCpDepartmentService departmentService = WxCpConfiguration.getCpService(999999).getDepartmentService();

            departmentService.update(wxCpDepart);
            return AjaxResult.success("更新组织机构成功");
        } catch (Exception e) {
            this.logger.info("\n获取组织机构出错" + e.getMessage());
        }
        return null;
    }

    @GetMapping("/delete")
    public AjaxResult delete(Long id) {
        this.logger.info("\n获取组织机构");
        try {
            WxCpDepartmentService departmentService = WxCpConfiguration.getCpService(999999).getDepartmentService();
            departmentService.delete(id);
            return AjaxResult.success("删除组织机构成功");
        } catch (Exception e) {
            this.logger.info("\n删除组织机构出错" + e.getMessage());
        }
        return null;
    }

}
