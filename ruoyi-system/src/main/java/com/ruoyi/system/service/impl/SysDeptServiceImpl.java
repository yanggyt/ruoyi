package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.domain.entity.SysDept;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.ShiroUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.domain.EcologyDept;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.ISysDeptService;
import com.ruoyi.system.service.ISysUserService;
import com.ruoyi.system.service.IWechatApiService;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * 部门管理 服务实现
 * 
 * @author ruoyi
 */
@Service("sysDeptServiceImpl")
public class SysDeptServiceImpl implements ISysDeptService
{
    @Autowired
    private SysDeptMapper deptMapper;

    @Autowired
    private ISysConfigService sysconfig;

    @Autowired
    private IWechatApiService wechatApiService;

    @Autowired
    ISysUserService userService;

    /**
     * 查询部门管理数据
     * 
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<SysDept> selectDeptList(SysDept dept)
    {
        return deptMapper.selectDeptList(dept);
    }

    /**
     * 查询部门管理树
     * 
     * @param dept 部门信息
     * @return 所有部门信息
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<Ztree> selectDeptTree(SysDept dept)
    {
        List<SysDept> deptList = deptMapper.selectDeptList(dept);
        List<Ztree> ztrees = initZtree(deptList);
        return ztrees;
    }

    /**
     * 查询部门管理树（排除下级）
     * 
     * @param dept 部门
     * @return 所有部门信息
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<Ztree> selectDeptTreeExcludeChild(SysDept dept)
    {
        Long deptId = dept.getDeptId();
        List<SysDept> deptList = deptMapper.selectDeptList(dept);
        Iterator<SysDept> it = deptList.iterator();
        while (it.hasNext())
        {
            SysDept d = (SysDept) it.next();
            if (d.getDeptId().intValue() == deptId
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), deptId + ""))
            {
                it.remove();
            }
        }
        List<Ztree> ztrees = initZtree(deptList);
        return ztrees;
    }

    /**
     * 根据角色ID查询部门（数据权限）
     *
     * @param role 角色对象
     * @return 部门列表（数据权限）
     */
    @Override
    public List<Ztree> roleDeptTreeData(SysRole role)
    {
        Long roleId = role.getRoleId();
        List<Ztree> ztrees = new ArrayList<Ztree>();
        List<SysDept> deptList = selectDeptList(new SysDept());
        if (StringUtils.isNotNull(roleId))
        {
            List<String> roleDeptList = deptMapper.selectRoleDeptTree(roleId);
            ztrees = initZtree(deptList, roleDeptList);
        }
        else
        {
            ztrees = initZtree(deptList);
        }
        return ztrees;
    }

    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysDept> deptList)
    {
        return initZtree(deptList, null);
    }

    /**
     * 对象转部门树
     *
     * @param deptList 部门列表
     * @param roleDeptList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SysDept> deptList, List<String> roleDeptList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleDeptList);
        for (SysDept dept : deptList)
        {
            if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()))
            {
                Ztree ztree = new Ztree();
                ztree.setId(dept.getDeptId());
                ztree.setpId(dept.getParentId());
                ztree.setName(dept.getDeptName());
                ztree.setTitle(dept.getDeptName());
                if (isCheck)
                {
                    ztree.setChecked(roleDeptList.contains(dept.getDeptId() + dept.getDeptName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 查询部门人数
     * 
     * @param parentId 部门ID
     * @return 结果
     */
    @Override
    public int selectDeptCount(Long parentId)
    {
        SysDept dept = new SysDept();
        dept.setParentId(parentId);
        return deptMapper.selectDeptCount(dept);
    }

    /**
     * 查询部门是否存在用户
     * 
     * @param deptId 部门ID
     * @return 结果 true 存在 false 不存在
     */
    @Override
    public boolean checkDeptExistUser(Long deptId)
    {
        int result = deptMapper.checkDeptExistUser(deptId);
        return result > 0 ? true : false;
    }

    /**
     * 删除部门管理信息
     * 
     * @param deptId 部门ID
     * @return 结果
     */
    @Override
    public int deleteDeptById(Long deptId)
    {
        return deptMapper.deleteDeptById(deptId);
    }

    /**
     * 新增保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public int insertDept(SysDept dept)
    {
        SysDept info = deptMapper.selectDeptById(dept.getParentId());
        // 如果父节点不为"正常"状态,则不允许新增子节点
        if (!UserConstants.DEPT_NORMAL.equals(info.getStatus()))
        {
            throw new BusinessException("部门停用，不允许新增");
        }
        dept.setAncestors(info.getAncestors() + "," + dept.getParentId());
        return deptMapper.insertDept(dept);
    }

    /**
     * 修改保存部门信息
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    @Transactional
    public int updateDept(SysDept dept)
    {
        SysDept newParentDept = deptMapper.selectDeptById(dept.getParentId());
        SysDept oldDept = selectDeptById(dept.getDeptId());
        if (StringUtils.isNotNull(newParentDept) && StringUtils.isNotNull(oldDept))
        {
            String newAncestors = newParentDept.getAncestors() + "," + newParentDept.getDeptId();
            String oldAncestors = oldDept.getAncestors();
            dept.setAncestors(newAncestors);
            updateDeptChildren(dept.getDeptId(), newAncestors, oldAncestors);
        }
        int result = deptMapper.updateDept(dept);
        if (UserConstants.DEPT_NORMAL.equals(dept.getStatus()) && StringUtils.isNotEmpty(dept.getAncestors())
                && !StringUtils.equals("0", dept.getAncestors()))
        {
            // 如果该部门是启用状态，则启用该部门的所有上级部门
            updateParentDeptStatusNormal(dept);
        }
        return result;
    }

    /**
     * 修改该部门的父级部门状态
     * 
     * @param dept 当前部门
     */
    private void updateParentDeptStatusNormal(SysDept dept)
    {
        String ancestors = dept.getAncestors();
        Long[] deptIds = Convert.toLongArray(ancestors);
        deptMapper.updateDeptStatusNormal(deptIds);
    }

    /**
     * 修改子元素关系
     * 
     * @param deptId 被修改的部门ID
     * @param newAncestors 新的父ID集合
     * @param oldAncestors 旧的父ID集合
     */
    public void updateDeptChildren(Long deptId, String newAncestors, String oldAncestors)
    {
        List<SysDept> children = deptMapper.selectChildrenDeptById(deptId);
        for (SysDept child : children)
        {
            child.setAncestors(child.getAncestors().replaceFirst(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            deptMapper.updateDeptChildren(children);
        }
    }

    /**
     * 根据部门ID查询信息
     * 
     * @param deptId 部门ID
     * @return 部门信息
     */
    @Override
    public SysDept selectDeptById(Long deptId)
    {
        return deptMapper.selectDeptById(deptId);
    }

    /**
     * 根据ID查询所有子部门（正常状态）
     * 
     * @param deptId 部门ID
     * @return 子部门数
     */
    @Override
    public int selectNormalChildrenDeptById(Long deptId)
    {
        return deptMapper.selectNormalChildrenDeptById(deptId);
    }

    /**
     * 校验部门名称是否唯一
     * 
     * @param dept 部门信息
     * @return 结果
     */
    @Override
    public String checkDeptNameUnique(SysDept dept)
    {
        Long deptId = StringUtils.isNull(dept.getDeptId()) ? -1L : dept.getDeptId();
        SysDept info = deptMapper.checkDeptNameUnique(dept.getDeptName(), dept.getParentId());
        if (StringUtils.isNotNull(info) && info.getDeptId().longValue() != deptId.longValue())
        {
            return UserConstants.DEPT_NAME_NOT_UNIQUE;
        }
        return UserConstants.DEPT_NAME_UNIQUE;
    }

    /**
     * Ecology部门信息同步
     */
    @Override
    public AjaxResult syncEcologyDept(String url, String params) {
        String msg="同步OA部门失败!";
        List<String> userList=new ArrayList<>();
        userList.add(ShiroUtils.getLoginName().equals("admin")?"359":String.valueOf(ShiroUtils.getUserId()));
        if( ! sysconfig.selectConfigByKey("sys.dept.sync").equals("1")){
               msg="OA部门同步失败！系统未开启OA部门同步!";
              wechatApiService.SendTextMessageToWechatUser(userList,msg);
              return AjaxResult.success(msg,"false");
        }
          int result =deptSync(HttpUtils.sendPostWithRest(url,params));
          if( result==200)
          {
              return AjaxResult.success("OA部门同步成功!",result);
          }

        wechatApiService.SendTextMessageToWechatUser(userList,msg+"result:"+result);
        return AjaxResult.error(msg,result);

    }

    @SuppressWarnings("unchecked")
    public int deptSync(Map<String,String> mapResult){
        //如果接口返回状态码不为200，则不做同步处理
        if(!mapResult.get("statusCode").equals("200"))
        {
            return 0;
        }

        //取Ecology返回信息中的部门信息
        Map<String,Object> map = (Map) JSON.parse(mapResult.get("result"));
        Map<String,Object> dataMap= (Map<String, Object>) map.get("data");
        JSONArray json = (JSONArray) dataMap.get("dataList");
        List<EcologyDept> deptList = JSONArray.parseArray(json.toJSONString(), EcologyDept.class);
        //清空部门表
        deptMapper.truncateDept();

        //插入顶级部门
        SysDept topDept= new SysDept();//deptMapper.selectDeptById(Long.parseLong("999999"));
        topDept.setDeptId(Long.parseLong("999999"));
        topDept.setParentId(Long.parseLong("0"));
        topDept.setDeptName("BPS");
        topDept.setAncestors("0");
        topDept.setOrderNum("0");
        topDept.setStatus("0");
        topDept.setCreateBy("Admin");
        deptMapper.insertDept(topDept);

        //同步Ecology部门信息
        List<SysDept> list=new ArrayList<>();
        for(EcologyDept ecologyDept:deptList){
            if(ecologyDept.getSubcompanyid1().equals("1")) { //只取分部ID为“1”的部门，排除代理商
                SysDept dept= insertEcologyDept(ecologyDept);
                list.add(dept);
            }
        }
        //更新祖级列表信息
        updateAncestors(list);

        return 200;
    }

    //将Ecology部门转化为系统部门，并更新到部门表sys_dept
    public SysDept insertEcologyDept(EcologyDept ecologyDept){
        SysDept dept=new SysDept();
        dept.setDeptId(Long.parseLong(ecologyDept.getId()));
        dept.setParentId(Long.parseLong(ecologyDept.getSupdepid()) == 0 ? 999999 : Long.parseLong(ecologyDept.getSupdepid()));
        dept.setDeptName(ecologyDept.getDepartmentname());
        dept.setOrderNum("0");
        dept.setStatus("0");
        dept.setCreateBy("Admin");
        deptMapper.insertDept(dept);
        return dept;
    }

    //更新祖级列表信息
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
}
