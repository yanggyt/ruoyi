package com.ruoyi.kettle.service.impl;

import java.util.List;
import java.util.stream.Collectors;

import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.domain.entity.SysRole;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.PermissionUtils;
import com.ruoyi.kettle.domain.KettleTrans;
import com.ruoyi.kettle.domain.XRepository;
import com.ruoyi.kettle.mapper.XRepositoryMapper;
import com.ruoyi.kettle.tools.KettleUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kettle.mapper.KettleJobMapper;
import com.ruoyi.kettle.domain.KettleJob;
import com.ruoyi.kettle.service.IKettleJobService;
import com.ruoyi.common.core.text.Convert;

/**
 * 作业调度Service业务层处理
 * 
 * @author kone
 * @date 2021-07-22
 */
@Service("kettleJobServiceImpl")
public class KettleJobServiceImpl implements IKettleJobService
{
    @Autowired
    private KettleJobMapper kettleJobMapper;
    @Autowired
    private XRepositoryMapper repositoryMapper;


    @Autowired
    private KettleUtil kettleUtil;
    /**
     * 查询作业调度
     * 
     * @param id 作业调度ID
     * @return 作业调度
     */
    @Override
    public KettleJob selectKettleJobById(Long id)
    {
        return kettleJobMapper.selectKettleJobById(id);
    }

    /**
     * 查询作业调度列表
     * 
     * @param kettleJob 作业调度
     * @return 作业调度
     */
    @Override
    public List<KettleJob> selectKettleJobList(KettleJob kettleJob)
    {
        List<SysRole> roleList = (List<SysRole>) PermissionUtils.getPrincipalProperty("roles");
        //当前用户的roleKey
        List<String> roleKeys=roleList.stream().map(SysRole::getRoleKey).collect(Collectors.toList());
        return kettleJobMapper.selectKettleJobList(kettleJob,roleKeys);
    }

    /**
     * 新增作业调度
     * 
     * @param kettleJob 作业调度
     * @return 结果
     */
    @Override
    public AjaxResult insertKettleJob(KettleJob kettleJob)
    {
        String jobName=kettleJob.getJobName();
        if(kettleJobMapper.selectJobByNameAndRepoId(jobName,kettleJob.getJobRepositoryId())>0){
            return AjaxResult.error("已存在同名作业");
        }
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        if(kettleJob.getRoleKey()==null){
            kettleJob.setRoleKey("admin");
        }else{
            if(!kettleJob.getRoleKey().contains("admin")){
                kettleJob.setRoleKey(kettleJob.getRoleKey().concat(",admin"));
            }
        }
        kettleJob.setCreatedBy(userName);
        kettleJob.setUpdateBy(userName);
        kettleJob.setJobType("File");
        return AjaxResult.success(kettleJobMapper.insertKettleJob(kettleJob));
    }

    /**
     * 修改作业调度
     * 
     * @param kettleJob 作业调度
     * @return 结果
     */
    @Override
    public int updateKettleJob(KettleJob kettleJob)
    {
        kettleJob.setUpdateTime(DateUtils.getNowDate());
        return kettleJobMapper.updateKettleJob(kettleJob);
    }

    /**
     * 删除作业调度对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteKettleJobByIds(String ids)
    {
        return kettleJobMapper.deleteKettleJobByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除作业调度信息
     * 
     * @param id 作业调度ID
     * @return 结果
     */
    @Override
    public int deleteKettleJobById(Long id)
    {
        return kettleJobMapper.deleteKettleJobById(id);
    }

    @Override
    public AjaxResult run(KettleJob job) {
        Long id = job.getId();
        KettleJob kettleJob = kettleJobMapper.selectKettleJobById(id);
        if(kettleJob ==null){
            return AjaxResult.error("作业不存在!");
        }
        XRepository repository=repositoryMapper.selectXRepositoryById(kettleJob.getJobRepositoryId());
        if(repository==null){
            return AjaxResult.error("资源库不存在!");
        }
        String path = kettleJob.getJobPath();
        try {
            kettleUtil.KETTLE_LOG_LEVEL=kettleJob.getJobLogLevel();
            kettleUtil.KETTLE_REPO_ID=String.valueOf(kettleJob.getJobRepositoryId());
            kettleUtil.KETTLE_REPO_NAME=repository.getRepoName();
            kettleUtil.KETTLE_REPO_PATH=repository.getBaseDir();
            kettleUtil.callJob(path,kettleJob.getJobName(),null,null);
        } catch (Exception e) {
            e.printStackTrace();
        }


        return AjaxResult.success("执行成功!");    }

    @Override
    public List<String> queryJobLog(KettleJob kettleJob) {
        List<String> logs=kettleJobMapper.queryJobLog(kettleJob.getJobName());
        return logs;
    }

    @Override
    public Long checkQuartzExist(String checkStr) {
        return kettleJobMapper.checkQuartzExist(checkStr);
    }
    @Override
    public AjaxResult runJobQuartz(String id, String jobName) {
        KettleJob kettleJob = kettleJobMapper.selectKettleJobById(Long.valueOf(id));
        return run(kettleJob);
    }
}
