package com.ruoyi.kettle.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.PermissionUtils;
import com.ruoyi.kettle.domain.XRepository;
import com.ruoyi.kettle.mapper.XRepositoryMapper;
import com.ruoyi.kettle.service.IKettleTransService;
import com.ruoyi.kettle.tools.KettleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kettle.mapper.KettleTransMapper;
import com.ruoyi.kettle.domain.KettleTrans;
import com.ruoyi.common.core.text.Convert;

/**
 * 转换Service业务层处理
 * 
 * @author kone
 * @date 2021-07-14
 */
@Service("kettleTransServiceImpl")
public class KettleTransServiceImpl implements IKettleTransService
{
    @Autowired
    private KettleTransMapper kettleTransMapper;
    @Autowired
    private XRepositoryMapper repositoryMapper;

    @Autowired
    private KettleUtil kettleUtil;

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
        Object o=PermissionUtils.getPrincipalProperty("roles");
        List<SysRole> roleList=new ArrayList<>();
       // roleList= (List<SysRole>) PermissionUtils.getPrincipalProperty("roles");
        if(o != null && o instanceof List<?>){
            for(Object r:(List<?>)o){
                roleList.add(SysRole.class.cast(r));
            }
        }

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
    public AjaxResult insertKettleTrans(KettleTrans kettleTrans)
    {
        String transName=kettleTrans.getTransName();
        if(kettleTransMapper.selectKettleTransByTransName(transName)>0){
           return AjaxResult.error("已存在同名转换");
        }
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
        return  AjaxResult.success(kettleTransMapper.insertKettleTrans(kettleTrans));
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


    /**
     * @Description:立即执行一次转换
     * @Author: Kone.wang
     * @Date: 2021/7/15 14:31
     * @param trans :
     * @return: void
     **/
    @Override
    public AjaxResult run(KettleTrans trans) {
        Long id = trans.getId();
        KettleTrans kettleTrans = kettleTransMapper.selectKettleTransById(id);
        if(kettleTrans ==null){
            return AjaxResult.error("转换不存在!");
        }
        XRepository repository=repositoryMapper.selectXRepositoryById(kettleTrans.getTransRepositoryId());
        if(repository==null){
            return AjaxResult.error("资源库不存在!");
        }
        String path = kettleTrans.getTransPath();
        try {
            kettleUtil.KETTLE_LOG_LEVEL=kettleTrans.getTransLogLevel();
            kettleUtil.KETTLE_REPO_ID=String.valueOf(kettleTrans.getTransRepositoryId());
            kettleUtil.KETTLE_REPO_NAME=repository.getRepoName();
            kettleUtil.KETTLE_REPO_PATH=repository.getBaseDir();
            kettleUtil.callTrans(path,kettleTrans.getTransName(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return AjaxResult.success("执行成功!");
    }
    /**
     * @Description:查询抓换执行日志
     * @Author: Kone.wang
     * @Date: 2021/7/28 16:24
     * @param kettleTrans:
     * @return: java.util.List<java.lang.String>
     **/
    @Override
    public List<String> queryTransLog(KettleTrans kettleTrans)  {
        List<String> transLogs=kettleTransMapper.queryTransLog(kettleTrans.getTransName());
        return transLogs;
    }
    /**
     * @Description:设置定时执行转换
     * @Author: Kone.wang
     * @Date: 2021/7/21 14:59
     * @param id:
     * @param transName:
     * @return: com.ruoyi.common.core.domain.AjaxResult
     **/
    @Override
    public AjaxResult runTransQuartz(String id, String transName) {
        KettleTrans kettleTrans = kettleTransMapper.selectKettleTransById(Long.valueOf(id));
        return run(kettleTrans);
    }
    /**
     * @Description:检查该转换是否设置了定时任务
     * @Author: Kone.wang
     * @Date: 2021/7/21 16:37
     * @param checkStr:
     * @return: int
     **/
    @Override
    public Long checkQuartzExist(String checkStr) {

        return kettleTransMapper.checkQuartzExist(checkStr);
    }
}
