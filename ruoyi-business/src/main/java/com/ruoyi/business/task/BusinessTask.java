package com.ruoyi.business.task;

import com.ruoyi.business.domain.BizAccountDetail;
import com.ruoyi.business.mapper.BizAccountMapper;
import com.ruoyi.business.service.IBizAccountService;
import com.ruoyi.business.service.IBizMemberService;
import com.ruoyi.framework.util.LogUtils;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.utils.DictUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.List;

/**
 * 定时任务调度
 *
 * @author ruoyi
 */
@Component("businessTask")
public class BusinessTask {

    @Autowired
    private IBizMemberService bizMemberService;

    @Autowired
    private IBizAccountService bizAccountService;

    /**
        执行每日定时任务
     */
    public void doTask()
    {
        //每日清空福豆田
        doClearField();
        //判断节假日
        if (isInHoliday()) {
            LogUtils.getAccessLog().info("======今日休息,不执行福豆相关任务======");
            return;
        }
        LogUtils.getAccessLog().info("======今日非休息日,开始执行福豆相关任务======");

        //专项划拨任务
        //doSpecialTask();

        //团队福豆分成任务
        doTeamTask();

        LogUtils.getAccessLog().info("======今日所有任务执行完成======");
    }

    //福豆田清零任务
    private void doClearField()
    {
        try {
            bizAccountService.clearAllDouField();
            LogUtils.getAccessLog().info("======执行福豆田清零任务完成======");
        } catch (Exception ex) {
            ex.printStackTrace();
            LogUtils.getAccessLog().error("======执行福豆田清零任务出错======" + ex.getMessage());
        }
    }

    //专项划拨任务
    private void doSpecialTask()
    {
        try {
            int accessCount = bizMemberService.doSpecialTask();

            LogUtils.getAccessLog().info("======执行专项划拨任务完成,处理会员数据" + accessCount + "条======");
        } catch (Exception ex) {
            ex.printStackTrace();
            LogUtils.getAccessLog().error("======执行专项划拨任务出错======" + ex.getMessage());
        }
    }

    //团队福豆分成任务
    private void doTeamTask()
    {
        try {
            int accessCount = bizMemberService.doTeamTask();

            LogUtils.getAccessLog().info("======执行团队福豆分成任务完成,处理会员数据" + accessCount + "条======");
        } catch (Exception ex) {
            ex.printStackTrace();
            LogUtils.getAccessLog().error("======执行团队福豆分成任务出错======" + ex.getMessage());
        }
    }

    //是否节假日(不结算)
    public static boolean isInHoliday(int ... monthDayWeek)
    {
        Calendar now = Calendar.getInstance();
        int month, day, weekday;
        if (monthDayWeek.length > 0) {
            month = monthDayWeek[0];
        } else {
            month = now.get(Calendar.MONTH) + 1;
        }
        if (monthDayWeek.length > 1) {
            day = monthDayWeek[1];
        } else {
            day = now.get(Calendar.DAY_OF_MONTH);
        }
        if (monthDayWeek.length > 2) {
            weekday = monthDayWeek[2];
        } else {
            weekday = now.get(Calendar.DAY_OF_WEEK);
        }
        //字典配置
        List<SysDictData> holidays = DictUtils.getDictCache("busi_holidays");
        List<SysDictData> workdays = DictUtils.getDictCache("busi_workdays");
        //节假日
        if (holidays != null && isInDictDays(holidays, month, day)) return true;
        //工作日
        if (workdays != null && isInDictDays(workdays, month, day)) return false;
        //普通的周六周日
        return weekday == Calendar.SUNDAY || weekday == Calendar.SATURDAY;
    }

    //是否在设定范围内
    public static boolean isInDictDays(List<SysDictData> days, int month, int day) {
        for (SysDictData data : days) {
            String label = data.getDictLabel();
            String[] split = label.split("-");
            String begin = split[0];
            String end = split.length > 1 ? split[1] : begin;
            String[] beginStr = begin.split("[.]");
            int beginMonth = Integer.parseInt(beginStr[0]);
            int beginDay = Integer.parseInt(beginStr[1]);
            String[] endStr = end.split("[.]");
            int endMonth = Integer.parseInt(endStr[0]);
            int endDay = Integer.parseInt(endStr[1]);
            if (month >= beginMonth && month <= endMonth && day >= beginDay && day <= endDay) {
                return true;
            }
        }
        return false;
    }

}

