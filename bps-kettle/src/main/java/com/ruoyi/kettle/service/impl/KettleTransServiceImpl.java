package com.ruoyi.kettle.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kettle.mapper.KettleTransMapper;
import com.ruoyi.kettle.domain.KettleTrans;
import com.ruoyi.kettle.service.IKettleTransService;
import com.ruoyi.common.core.text.Convert;

/**
 * 转换Service业务层处理
 * 
 * @author kone
 * @date 2021-07-14
 */
@Service
public class KettleTransServiceImpl implements IKettleTransService 
{
    @Autowired
    private KettleTransMapper kettleTransMapper;

    /**
     * 查询转换
     * 
     * @param id 转换ID
     * @return 转换
     */
    @Override
    public KettleTrans selectKettleTransById(Long id)
    {
        return kettleTransMapper.selectKettleTransById(id);
    }

    /**
     * 查询转换列表
     * 
     * @param kettleTrans 转换
     * @return 转换
     */
    @Override
    public List<KettleTrans> selectKettleTransList(KettleTrans kettleTrans)
    {
       List<SysRole> roleList = (List<SysRole>) PermissionUtils.getPrincipalProperty("roles");
       //当前用户的roleKey
       List<String> roleKeys=roleList.stream().map(SysRole::getRoleKey).collect(Collectors.toList());

        return kettleTransMapper.selectKettleTransList(kettleTrans,roleKeys);
    }

    /**
     * 新增转换
     * 
     * @param kettleTrans 转换
     * @return 结果
     */
    @Override
    public int insertKettleTrans(KettleTrans kettleTrans)
    {
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        if(kettleTrans.getRoleKey()==null){
            kettleTrans.setRoleKey("admin");
        }else{
            if(!kettleTrans.getRoleKey().contains("admin")){
                kettleTrans.setRoleKey(kettleTrans.getRoleKey().concat(",admin"));
            }
        }
        kettleTrans.setCreatedBy(userName);
        kettleTrans.setUpdateBy(userName);
        kettleTrans.setTransType("File");
        return kettleTransMapper.insertKettleTrans(kettleTrans);
    }

    /**
     * 修改转换
     * 
     * @param kettleTrans 转换
     * @return 结果
     */
    @Override
    public int updateKettleTrans(KettleTrans kettleTrans)
    {
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        kettleTrans.setUpdateBy(userName);
        kettleTrans.setUpdateTime(DateUtils.getNowDate());
        kettleTrans.setTransType("File");
        if(kettleTrans.getRoleKey()==null){
            kettleTrans.setRoleKey("admin");
        }else{
            if(!kettleTrans.getRoleKey().contains("admin")){
                kettleTrans.setRoleKey(kettleTrans.getRoleKey().concat(",admin"));
            }
        }        return kettleTransMapper.updateKettleTrans(kettleTrans);
    }

    /**
     * 删除转换对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKettleTransByIds(String ids)
    {
        return kettleTransMapper.deleteKettleTransByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除转换信息
     * 
     * @param id 转换ID
     * @return 结果
     */
    @Override
    public int deleteKettleTransById(Long id)
    {
        return kettleTransMapper.deleteKettleTransById(id);
    }
}
