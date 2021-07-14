package com.ruoyi.test.conrtroller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.domain.EcologyDept;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@RestController
public class GetEcologyInfoTestController extends BaseController {
    @Autowired
    private SysDeptMapper deptMapper;
    @Autowired
    private ISysUserService userService;

    @RequestMapping("/anon/getEcologyDept")
    public String getEcologyDept() throws Exception {
        String url="http://192.168.2.85:90/api/hrm/resful/getHrmdepartmentWithPage";
        String params="{\"params\":{\"pagesize\":1000}}";
        //return  sendPost(url,params);
        return deptSync(HttpUtils.sendPostWithRest(url,params));

    }

    @RequestMapping("/anon/getEcologyUser")
    public String getEcologyUser(){
        String url="http://192.168.2.85:90/api/hrm/resful/getHrmUserInfoWithPage";
        String params="{\"params\":{\"pagesize\":999999}}";
        int result = userService.syncEcologyUser(url,params);
        if(result==200){
            return "同步成功";
        }
        return "同步失败";
    }

    /*public Map<String,String> sendPostWithRest(String url,String params){
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> result=null;
        int statusCode=0;
        try{
            result=restTemplate.postForEntity(url,params,String.class);
            statusCode=result.getStatusCode().value();
        }catch (RestClientException e){
            System.out.println("POST Request uri: "+url+", params:"+params+" error:"+e.getMessage());
        }
        Map<String,String> map=new HashMap<>();
        map.put("statusCode",String.valueOf(statusCode));
        if(statusCode== 200){
            map.put("result",result.getBody());
        } else{
            map.put("result",String.valueOf(statusCode));
        }

        return map;
    }*/


    public String deptSync(Map<String,String> mapResult){
        //如果接口返回状态码不为200，则不做同步处理
        String statusCode=mapResult.get("statusCode");
        String result= mapResult.get("result");
        if(!statusCode.equals("200"))
        {
            return mapResult.get("result");
        }
        //取Ecology返回信息中的部门信息
        Map<String,Object> map = (Map) JSON.parse(result);
        Map<String,Object> o= (Map<String, Object>) map.get("data");
        JSONArray json = (JSONArray) o.get("dataList");
        List<EcologyDept> depts = JSONArray.parseArray(json.toJSONString(), EcologyDept.class);
        //清空部门表
        deptMapper.truncateDept();
        //插入最顶层部门
        SysDept dept  =new SysDept();
        dept.setDeptId(Long.parseLong("999999"));
        dept.setParentId(Long.parseLong("0"));
        dept.setDeptName("BPS");
        dept.setOrderNum("0");
        dept.setStatus("0");
        dept.setCreateBy("Admin");
        deptMapper.insertDept(dept);
        //同步Ecology部门信息
        for(EcologyDept ecologyDept:depts){
            //System.out.println(ecologyDept.getDepartmentname());
            //String subCompanyid=ecologyDept.getSubcompanyid1();
            if(ecologyDept.getSubcompanyid1().equals("1")) { //只取分部ID为“1”的部门，排除代理商
                dept.setDeptId(Long.parseLong(ecologyDept.getId()));
                dept.setParentId(Long.parseLong(ecologyDept.getSupdepid()) == 0 ? 999999 : Long.parseLong(ecologyDept.getSupdepid()));
                dept.setDeptName(ecologyDept.getDepartmentname());
                dept.setOrderNum("0");
                dept.setStatus("0");
                dept.setCreateBy("Admin");
                deptMapper.insertDept(dept);
            }
        }
        return result;
    }

   /* public String sendPost(String url,String params) throws Exception {
        RestTemplate restTemplate=new RestTemplate();
        ResponseEntity<String> result = null;
        int statusCode = 0;
        try {
            result = restTemplate.postForEntity(url, params, String.class);
            statusCode = result.getStatusCode().value();
        } catch (RestClientException e) {
            //logger.error("POST Request uri: {}, params: {}, error: {}", url, params, e.getMessage());
        }
        if (statusCode == 200) {

            return result.getBody();
        }
        if (statusCode >= 300 || statusCode < 200) {
            throw new Exception("Response code is " + statusCode);
        }
        //return ResponseCreator.writeJsonErr(result.getStatusCode().toString());

        return  result.getStatusCode().toString();
    }
*/



}
