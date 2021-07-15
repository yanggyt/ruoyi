package com.ruoyi.test.conrtroller;


import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GetEcologyInfoTestController extends BaseController {
    @Autowired
    private ISysDeptService deptService;
    @Autowired
    private ISysUserService userService;

    @RequestMapping("/anon/getEcologyDept")
    public String getEcologyDept() throws Exception {
        String url="http://192.168.2.85:90/api/hrm/resful/getHrmdepartmentWithPage";
        String params="{\"params\":{\"pagesize\":999999}}";
        //return  sendPost(url,params);
        int result = deptService.syncEcologyDept(url,params);
        if(result==200){
            return "同步成功";
        }
        return "同步失败";

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

   /* *//*public Map<String,String> sendPostWithRest(String url,String params){
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
    }*//*

    public SysDept insertEcologyDept(EcologyDept ecologyDept){
        SysDept dept=new SysDept();
        dept.setDeptId(Long.parseLong(ecologyDept.getId()));
        dept.setParentId(Long.parseLong(ecologyDept.getSupdepid()) == 0 ? 999999 : Long.parseLong(ecologyDept.getSupdepid()));
        dept.setDeptName(ecologyDept.getDepartmentname());
        //dept.setAncestors(pAncestors);
        dept.setOrderNum("0");
        dept.setStatus("0");
        dept.setCreateBy("Admin");
        deptMapper.insertDept(dept);
        return dept;
    }

    public void updateAncestors(List<SysDept> sysDeptList)
    {
        if(sysDeptList.isEmpty())
        {
            return;
        }
        List<SysDept> list =new ArrayList<>();
        for(SysDept dept:sysDeptList){
            SysDept info = deptMapper.selectDeptById(dept.getParentId());
            if(StringUtils.isNotEmpty(info.getAncestors())) {
                dept.setAncestors(info.getAncestors()+","+dept.getParentId());
                deptMapper.updateDept(dept);
            }else{
                list.add(dept);
            }
        }
        updateAncestors(list);
    }



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

        //清空部门表,并插入顶级部门
        SysDept sysDept=deptMapper.selectDeptById(Long.parseLong("999999"));
        deptMapper.truncateDept();
        deptMapper.insertDept(sysDept);
        List<SysDept> list=new ArrayList<>();
        //同步Ecology部门信息
       for(EcologyDept ecologyDept:depts){
            if(ecologyDept.getSubcompanyid1().equals("1")) { //只取分部ID为“1”的部门，排除代理商
       *//*         String pAncestors=null;
                if(ecologyDept.getSupdepid().equals("0")){ //如果Ecology部门为一级部门，则设定ancestors="0,999999"
                    pAncestors="0,999999";
                }*//*
               SysDept dept= insertEcologyDept(ecologyDept);
                list.add(dept);
            }
        }
       //更新祖级信息
        updateAncestors(list);

        return result;
    }

   *//* public String sendPost(String url,String params) throws Exception {
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
*//*

*/

}
