package com.wuzhen.web.controller.busi;

import com.alibaba.fastjson.JSONObject;
import com.wuzhen.common.annotation.Log;
import com.wuzhen.common.core.controller.BaseController;
import com.wuzhen.common.core.domain.AjaxResult;
import com.wuzhen.common.core.page.TableDataInfo;
import com.wuzhen.common.enums.BusinessType;
import com.wuzhen.common.utils.StringUtils;
import com.wuzhen.common.utils.poi.ExcelUtil;
import com.wuzhen.framework.shiro.util.AuthorizationUtils;
import com.wuzhen.system.domain.ActiveInfo;
import com.wuzhen.system.service.IActiveInfoService;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 角色信息
 *
 * @author zhengzheng
 */
@Controller
@RequestMapping("/active/info")
public class ActiveInfoController extends BaseController
{

    private static final Logger logger = LoggerFactory.getLogger(ActiveInfoController.class);

    private String prefix = "active/info";

    @Autowired
    private IActiveInfoService activeInfoService;

    @Value("${wuzhen.fp}")
    private String fp ;
    @Value("${wuzhen.lp}")
    private String lp ;

    @Value("${spring.profiles.active}")
    private String active ;




    @RequiresPermissions("active:info:view")
    @GetMapping()
    public String role()
    {
        return prefix + "/index";
    }

    @RequiresPermissions("active:info:list")
    @PostMapping("/list")
    @ResponseBody
    public TableDataInfo list(ActiveInfo activeInfo)
    {
        startPage();

        List<ActiveInfo> list = activeInfoService.selectActiveList(activeInfo);
        list.forEach(item->{
            if (item!=null&&!"".equals(item.getActiveDesc())&&item.getActiveDesc().length()>6){
                item.setActiveDesc(item.getActiveDesc().substring(0,6)+"...");
            }
            String startdate = item.getActiveStartDate();
            String enddate = item.getActiveEndDate();
            item.setActStatus(this.judgeDate(startdate,enddate));
        });
        return getDataTable(list);
    }

    @Log(title = "活动信息导出", businessType = BusinessType.EXPORT)
    @RequiresPermissions("active:info:export")
    @PostMapping("/export")
    @ResponseBody
    public AjaxResult export(ActiveInfo activeInfo)
    {
        List<ActiveInfo> list = activeInfoService.selectActiveList(activeInfo);
        ExcelUtil<ActiveInfo> util = new ExcelUtil<ActiveInfo>(ActiveInfo.class);
        return util.exportExcel(list, "活动信息");
    }

    /**
     * 新增活动
     */
    @GetMapping("/add")
    public String add()
    {
        return prefix + "/add";
    }

    /**
     * 新增保存活动
     */
    @RequiresPermissions("active:info:add")
    @Log(title = "活动管理", businessType = BusinessType.INSERT)
    @PostMapping("/add")
    @ResponseBody
    public AjaxResult addSave(@Validated ActiveInfo activeInfo)
    {
        logger.info(activeInfo.toString());
        activeInfo.setCreateBy(getLoginName());
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(activeInfoService.insertActive(activeInfo));
    }

    /**
     * 修改活动
     */
    @RequiresPermissions("active:info:edit")
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id") Long id, ModelMap mmap)
    {
//        roleService.checkRoleDataScope(roleId);
        ActiveInfo activeInfo =   activeInfoService.selectActiveById(id);
        String fileNames = activeInfo.getLpFilesName();
        String[] fileNamesArray = null;
        List list =new ArrayList();
        if (fileNames !=null && !"".equals(fileNames)){
            fileNamesArray =  fileNames.split(",");
            for (int i = 0; i < fileNamesArray.length; i++) {
                HashMap map = new HashMap();
                map.put("id",i);
                map.put("filename",fileNamesArray[i]);
                map.put("filepath","http://"+this.getAdress()+":18000/profile/upload/lp/"+fileNamesArray[i]);
                list.add(map);
            }
        }
        String json = JSONObject.toJSONString(list);
        activeInfo.setListLpNames(json);
        if(activeInfo.getLsFilesName()!=null && !"".equals(activeInfo.getLsFilesName())){
            activeInfo.setListLsNames("http://"+this.getAdress()+":18000/profile/upload/ls/"+activeInfo.getLsFilesName());
        }

        if(activeInfo.getFpFilesName()!=null && !"".equals(activeInfo.getFpFilesName())){
            activeInfo.setListFpNames("http://"+this.getAdress()+":18000/profile/upload/fp/"+activeInfo.getFpFilesName());
        }

        mmap.put("active", activeInfo);
        return prefix + "/edit";
    }

    /**
     * 修改首页
     */
    @RequiresPermissions("active:info:edit")
    @GetMapping("/first/{id}")
    public String first(@PathVariable("id") Long id, ModelMap mmap)

    {
        ActiveInfo activeInfo = activeInfoService.selectActiveById(id);
        if(activeInfo.getFpFilesName()!=null && !"".equals(activeInfo.getFpFilesName())){
            activeInfo.setListFpNames("http://"+this.getAdress()+":18000/profile/upload/fp/"+activeInfo.getFpFilesName());
        }
        mmap.put("active", activeInfo);
        return prefix + "/first";
    }


    /**
     *保存首页
     */
    @RequiresPermissions("active:info:edit")
    @Log(title = "保存首页", businessType = BusinessType.UPDATE)
    @PostMapping("/firstSave")
    @ResponseBody
    public AjaxResult firstSave(@Validated ActiveInfo activeInfo)
    {
        activeInfo.setUpdateBy(getLoginName());
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(activeInfoService.saveFistPage(activeInfo));
    }



    /**
     * 修改保存活动
     */
    @RequiresPermissions("active:info:edit")
    @Log(title = "角色管理", businessType = BusinessType.UPDATE)
    @PostMapping("/edit")
    @ResponseBody
    public AjaxResult editSave(@Validated ActiveInfo activeInfo)
    {

        activeInfo.setUpdateBy(getLoginName());
        AuthorizationUtils.clearAllCachedAuthorizationInfo();
        return toAjax(activeInfoService.updateActive(activeInfo));
    }



    @RequiresPermissions("active:info:remove")
    @Log(title = "活动管理", businessType = BusinessType.DELETE)
    @PostMapping("/remove")
    @ResponseBody
    public AjaxResult remove(String ids)
    {
        return toAjax(activeInfoService.deleteActiveByIds(ids));
    }



    /**
     * 状态修改
     */
    @Log(title = "活动上下线状态管理", businessType = BusinessType.UPDATE)
    @RequiresPermissions("active:info:edit")
    @PostMapping("/changeStatus")
    @ResponseBody
    public AjaxResult changeStatus(@Validated ActiveInfo activeInfo)
    {
        return toAjax(activeInfoService.updateActive(activeInfo));
    }



    private String getAdress(){
        String ipaddr ="";
        if (active.equals("druid")){
            ipaddr = "localhost";
        }else if(active.equals("prd")){
            ipaddr="47.94.96.229";
        }
      return ipaddr;
    }
//	2022-05-04	2022-05-18
    public static void main(String[] args) {
        judgeDate("2022-05-04","2022-05-08");
    }

    private static String  judgeDate(String startdate,String enddate) {
        String retStauts = "3";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        if (StringUtils.isNotEmpty(startdate) && StringUtils.isNotEmpty(enddate)) {
            try {
                Date dateStart = simpleDateFormat.parse(startdate);
                Date dateEnd = simpleDateFormat.parse(enddate);
                dateEnd = dateAddOne(dateEnd);
                Date dateNow = new Date();

                if (dateStart.after(dateNow)) {
                    //未开始
                    retStauts = "0";
                } else if ((dateStart.before(dateNow) && dateEnd.after(dateNow)) || (dateStart.equals(dateNow)) || (dateEnd.equals(dateNow))) {
                   //进行中
                    retStauts = "1";
                } else if (dateNow.after(dateEnd)) {
                    //已结束
                    retStauts = "2";
                }
            } catch (ParseException e) {
                e.printStackTrace();

            }
        }
        return retStauts;
    }


    /*日期加+1天*/
    public static Date dateAddOne(Date date) {
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        //把日期往后增加一天,整数  往后推,负数往前移动
        calendar.add(calendar.DATE,1);
        //这个时间就是日期往后推一天的结果
        date=calendar.getTime();
        return date;

    }

}