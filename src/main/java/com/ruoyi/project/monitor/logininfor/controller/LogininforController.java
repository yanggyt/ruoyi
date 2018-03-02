package com.ruoyi.project.monitor.logininfor.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import com.ruoyi.framework.web.controller.BaseController;
import com.ruoyi.framework.web.page.PageUtilEntity;
import com.ruoyi.framework.web.page.TableDataInfo;
import com.ruoyi.project.monitor.logininfor.domain.Logininfor;
import com.ruoyi.project.monitor.logininfor.service.ILogininforService;

/**
 * 系统访问记录
 * 
 * @author ruoyi
 */
@Controller
@RequestMapping("/monitor/logininfor")
public class LogininforController extends BaseController
{
    private String prefix = "monitor/logininfor";

    @Autowired
    private ILogininforService logininforService;

    @GetMapping()
    public String logininfor()
    {
        return prefix + "/logininfor";
    }

    @GetMapping("/list")
    @ResponseBody
    public TableDataInfo list()
    {
        PageUtilEntity pageUtilEntity = this.getPageUtilEntity();
        List<Logininfor> list = logininforService.pageInfoQueryLogininfor(pageUtilEntity);
        TableDataInfo tableDataInfo = new TableDataInfo(list, pageUtilEntity.getTotalResult());
        return tableDataInfo;
    }
}
