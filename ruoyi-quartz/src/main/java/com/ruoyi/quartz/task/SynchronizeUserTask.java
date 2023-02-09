package com.ruoyi.quartz.task;


import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.quartz.vo.ResultUser;
import com.ruoyi.system.service.ISysUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Component("synchronizeUserTask")
public class SynchronizeUserTask
{

    protected final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private ISysUserService userService;

    @Transactional(rollbackFor = Exception.class)
    public void synchronizeUser()
    {
        String url = "https://hr.silergycorp.com:8084/index.php?m=api&f=getSessionID&t=json";
        String s = HttpUtils.sendPost(url, null).replaceAll("&", "");
        Map<String, Object> map = JSONObject.parseObject(s);
        Map<String, Object> data = JSONObject.parseObject(map.get("data").toString());
        String sessionID = (String) (data.get("sessionID"));
        String url1 = "https://hr.silergycorp.com:8084/index.php?sid=" + sessionID + "&m=user&f=login&account=silergyapi&password=silergyq1w2e3&t=json";
        String s1 = HttpUtils.sendPost(url1, null).replaceAll("&", "");
        Map mmap = JSONObject.parseObject(s1, HashMap.class);
        if (mmap.containsKey("status")) {
            if (map.get("status").equals("success")) {
                String url2 = "https://hr.silergycorp.com:8084/index.php?&sid=" + sessionID + "&m=api&f=getModel&module=api&methodName=getalluserapp&t=json";
                String s2 = HttpUtils.sendPost(url2, null);
                Map m = JSONObject.parseObject(s2, HashMap.class);
                List<ResultUser> resultUsers = JSONObject.parseArray(m.get("data").toString(), ResultUser.class);
                List<SysUser> userList = new ArrayList<>();
                for (ResultUser resultUser : resultUsers) {
                    SysUser user = userService.selectUserByUserCode(resultUser.getStaffcode());
                    //转换在职状态
                    if (StringUtils.isNotEmpty(resultUser.getStatus()) &&
                            Objects.equals(resultUser.getStatus(), "在职")) {
                        resultUser.setStatus("0");
                    } else if (StringUtils.isNotEmpty(resultUser.getStatus()) &&
                            Objects.equals(resultUser.getStatus(), "离职")) {
                        resultUser.setStatus("1");
                    }
                    //转换性别
                    if (Objects.equals(resultUser.getSex(), "男")) {
                        resultUser.setSex("0");
                    } else if (Objects.equals(resultUser.getSex(), "女")) {
                        resultUser.setSex("1");
                    } else {
                        resultUser.setSex("2");
                    }
                    if (StringUtils.isNull(user)) {
                        if (Objects.equals(resultUser.getStatus(), "0") && (
                                Objects.equals(resultUser.getEcompany(), "NJ") ||
                                        Objects.equals(resultUser.getEcompany(), "NJHK") ||
                                        Objects.equals(resultUser.getEcompany(), "PX"))) {
                            SysUser userInsert = new SysUser();
                            userInsert.setUserCode(resultUser.getStaffcode());
                            userInsert.setUserCardNum(resultUser.getZjnumber());
                            userInsert.setSex(resultUser.getSex());
                            userInsert.setZgsid(resultUser.getZgsid());
                            userInsert.setOffices(resultUser.getOffices());
                            userInsert.setDeptname(resultUser.getCompanyname());
                            userInsert.setLoginName(resultUser.getStaffcode());
                            userInsert.setUserName(resultUser.getAccount());
//                        userInsert.setEmail("kailun.shi@silergycorp.com");
                            userInsert.setEmail(resultUser.getEmail());
                            userInsert.setPhonenumber(resultUser.getMobile());
                            userInsert.setDepartmentCode(resultUser.getDepartmentcode());
                            userInsert.setStatus("0");
                            userInsert.setJobCode(resultUser.getJobcode());
                            userInsert.setBankName(resultUser.getBank());
                            userInsert.setBankNumber(resultUser.getBanknumber());
                            userList.add(userInsert);
                        }
                    } else {
                        //更新信息
                        if (StringUtils.isEmpty(user.getZgsid()) || StringUtils.isEmpty(resultUser.getZgsid())) {
                            continue;
                        }
                        //展示去掉代签主管的判断,影响请购单查找审核人员!Objects.equals(user.getAgentsid(), resultUser.getAgentsid()) ||
                        if (!Objects.equals(user.getZgsid(), resultUser.getZgsid()) ||
                                !Objects.equals(user.getStatus(), resultUser.getStatus()) ||
                                !Objects.equals(user.getEmail(), resultUser.getEmail()) ||
                                !Objects.equals(user.getJobCode(), resultUser.getJobcode()) ||
                                !Objects.equals(user.getSex(), resultUser.getSex()) ||
                                !Objects.equals(user.getDepartmentCode(), resultUser.getDepartmentcode()) ||
                                !Objects.equals(user.getDept().getDeptName(), resultUser.getCompanyname()) ||
                                (StringUtils.isNotEmpty(resultUser.getBank()) && !Objects.equals(user.getBankName(), resultUser.getBank())) ||
                                (StringUtils.isNotEmpty(resultUser.getBanknumber()) && !Objects.equals(user.getBankNumber(), resultUser.getBanknumber()))) {
                            SysUser userUpdate = new SysUser();
                            userUpdate.setUserId(user.getUserId());
                            userUpdate.setSex(resultUser.getSex());
                            userUpdate.setZgsid(resultUser.getZgsid());
                            userUpdate.setDepartmentCode(resultUser.getDepartmentcode());
                            userUpdate.setDeptname(resultUser.getCompanyname());
                            userUpdate.setOffices(resultUser.getOffices());
                            userUpdate.setStatus(resultUser.getStatus());
                            userUpdate.setEmail(resultUser.getEmail());
                            userUpdate.setJobCode(resultUser.getJobcode());
                            userUpdate.setBankName(resultUser.getBank());
                            userUpdate.setBankNumber(resultUser.getBanknumber());
                            userUpdate.setLoginName(user.getLoginName());
                            userUpdate.setUserCardNum(user.getUserCardNum());
                            userList.add(userUpdate);
                        }
                    }
                }
                SysUser sysUser = new SysUser();
                sysUser.setLoginName("admin");
                if (!userList.isEmpty()) {
                    String insertRes = userService.importUser(userList, true, sysUser.getLoginName());
                    log.info("insertRes - {}", insertRes + "同步用户");
                }
            }
        }
    }
}
