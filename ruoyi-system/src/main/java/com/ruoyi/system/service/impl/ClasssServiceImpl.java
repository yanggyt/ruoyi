package com.ruoyi.system.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.annotation.DataSource;
import com.ruoyi.common.constant.Constants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.enums.DataSourceType;
import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.system.domain.Classs;
import com.ruoyi.system.mapper.ClasssMapper;
import com.ruoyi.system.service.IClasssService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Table containing Class HierarchyService业务层处理
 * 
 * @author zbj
 * @date 2021-08-31
 */
@Service("classs")
@DataSource(value = DataSourceType.SLAVE)
public class ClasssServiceImpl implements IClasssService 
{
    @Autowired
    private ClasssMapper classsMapper;

    /**
     * 查询Table containing Class Hierarchy
     * 
     * @param classNo Table containing Class Hierarchy主键
     * @return Table containing Class Hierarchy
     */
    @Override
    public Classs selectClasssByClassNo(Long classNo)
    {
        return classsMapper.selectClasssByClassNo(classNo);
    }

    /**
     * 查询Table containing Class Hierarchy列表
     * 
     * @param classs Table containing Class Hierarchy
     * @return Table containing Class Hierarchy
     */
    @Override
    public List<Classs> selectClasssList(Classs classs)
    {
        return classsMapper.selectClasssList(classs);
    }

    /**
     * 新增Table containing Class Hierarchy
     * 
     * @param classs Table containing Class Hierarchy
     * @return 结果
     */
    @Override
    public int insertClasss(Classs classs)
    {
        return classsMapper.insertClasss(classs);
    }

    /**
     * 修改Table containing Class Hierarchy
     * 
     * @param classs Table containing Class Hierarchy
     * @return 结果
     */
    @Override
    public int updateClasss(Classs classs)
    {
        return classsMapper.updateClasss(classs);
    }

    /**
     * 批量删除Table containing Class Hierarchy
     * 
     * @param classNos 需要删除的Table containing Class Hierarchy主键
     * @return 结果
     */
    @Override
    public int deleteClasssByClassNos(String classNos)
    {
        return classsMapper.deleteClasssByClassNos(Convert.toStrArray(classNos));
    }

    /**
     * 删除Table containing Class Hierarchy信息
     * 
     * @param classNo Table containing Class Hierarchy主键
     * @return 结果
     */
    @Override
    public int deleteClasssByClassNo(Long classNo)
    {
        return classsMapper.deleteClasssByClassNo(classNo);
    }

    /**
     * 查询Table containing Class Hierarchy树列表
     * 
     * @return 所有Table containing Class Hierarchy信息
     */
    @Override
    public List<Ztree> selectClasssTree()
    {
        String json= HttpUtils.sendGet(Constants.WLFL,"pagecount=1");
        JSONObject jsonObject = JSONObject.parseObject(json);
        String totals = jsonObject.getString("totals");
        Double num =Math.ceil(Double.parseDouble(totals)/100);
        List<HashMap> object=new ArrayList<>();
        for(int i=1;i<=num.intValue();i++){
             json= HttpUtils.sendGet(Constants.WLFL,"pagecount=100&pagenum="+i);
             jsonObject = JSONObject.parseObject(json);
            String r = jsonObject.getString("data");
            object.addAll (JSONArray.parseArray(r, HashMap.class)) ;
        }
        List<Ztree> ztrees = new ArrayList<Ztree>();
        System.out.println(object.size());
        for (HashMap map : object)
        {
            Ztree ztree = new Ztree();
            ztree.setId(Long.valueOf(map.get("class_no").toString()));
            ztree.setpId(Long.valueOf(map.get("parent_class_no")==null?"0":map.get("parent_class_no").toString()));
            ztree.setName((String) map.get("class_id"));
            ztree.setTitle((String) map.get("descr"));
            ztrees.add(ztree);
        }
        return ztrees;
    }

    /**
     * 查询已发布的分类
     *
     * @return 所有已发布的分类
     */
    @Override
    public List<Classs> selectApprovalClasss() {
        Classs classs=new Classs();
        classs.setApprovalStatusNo(Classs.APPROVAL_APPROVED);
        classs.setCatEntityTypeNo(3L);
        List<Classs> classList=selectClasssList( classs);
        return  classList;
    }
}
