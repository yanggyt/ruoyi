package com.ruoyi.fq.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.fq.domain.FqPLogs;
import com.ruoyi.fq.domain.FqPackage;
import com.ruoyi.fq.domain.FqTable;
import com.ruoyi.fq.mapper.FqPLogsMapper;
import com.ruoyi.fq.mapper.FqPackageMapper;
import com.ruoyi.fq.mapper.FqTableMapper;
import com.ruoyi.fq.service.IFqPackageService;
import com.ruoyi.framework.util.ShiroUtils;
import com.ruoyi.system.domain.SysUser;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysUserRoleMapper;
import com.ruoyi.system.service.ISysConfigService;
import com.ruoyi.system.service.impl.SysUserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 封铅袋Service业务层处理
 *
 * @author mario
 * @date 2020-07-07
 */
@Service
public class FqPackageServiceImpl implements IFqPackageService
{
    private static final Logger log = LoggerFactory.getLogger(FqPackageServiceImpl.class);

    @Autowired
    private FqPackageMapper fqPackageMapper;

    @Autowired
    private FqPLogsMapper fqPLogsMapper;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

    @Autowired
    private FqTableMapper fqTableMapper;

    /**
     * 查询封铅袋
     *
     * @param id 封铅袋ID
     * @return 封铅袋
     */
    @Override
    public FqPackage selectFqPackageById(Long id)
    {
        return fqPackageMapper.selectFqPackageById(id);
    }

    /**
     * 查询封铅袋列表
     *
     * @param fqPackage 封铅袋
     * @return 封铅袋
     */
    @Override
    public List<FqPackage> selectFqPackageList(FqPackage fqPackage)
    {
        return fqPackageMapper.selectFqPackageList(fqPackage);
    }

    /**
     * 新增封铅袋
     *
     * @param fqPackage 封铅袋
     * @return 结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int insertFqPackage(FqPackage fqPackage)
    {
        fqPackage.setCreateTime(DateUtils.getNowDate());
        fqPackageMapper.insertFqPackage(fqPackage);
        //新增袋内数量封铅
        String header = getHeader(fqPackage.getStartNo());
        Long start = getNo(fqPackage.getStartNo());
        Long num = fqPackage.getNum();
        FqTable fqTable;
        List<FqTable> list = new ArrayList<>();
        for (int i=0; i<num; i++){
            fqTable = new FqTable();
            long order = start+i;
            fqTable.setfNo(header+order);
            fqTable.setpId(fqPackage.getId());
            list.add(fqTable);
        }

        return fqTableMapper.batchInsertFqTable(list);
    }

    private Long getNo(String code){
        String regEx="[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(code);
        return Long.valueOf(m.replaceAll("").trim());
    }

    private String getHeader(String code){
        String regEx="[0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(code);
        return m.replaceAll("").trim();
    }

    /**
     * 修改封铅袋
     *
     * @param fqPackage 封铅袋
     * @return 结果
     */
    @Override
    public int updateFqPackage(FqPackage fqPackage)
    {
        fqPackage.setUpdateTime(DateUtils.getNowDate());
        return fqPackageMapper.updateFqPackage(fqPackage);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateFqPackageByIds(FqPackage fqPackage,String ids){
        if(StringUtils.isNotEmpty(ids)){
            String[] idArray = ids.split(",");
            fqPackage.setUpdateTime(DateUtils.getNowDate());
            fqPackage.setUpdateBy(ShiroUtils.getLoginName());
            for (String id:
                 idArray) {
                fqPackage.setId(Long.valueOf(id));
                fqPackageMapper.updateFqPackage(fqPackage);
            }
            return ids.length();
        }else{
            return 0;
        }
    }


    /**
     * 删除封铅袋对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteFqPackageByIds(String ids)
    {
        return fqPackageMapper.deleteFqPackageByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除封铅袋信息
     *
     * @param id 封铅袋ID
     * @return 结果
     */
    @Override
    public int deleteFqPackageById(Long id)
    {
        return fqPackageMapper.deleteFqPackageById(id);
    }

    @Override
    public String importData(List<FqPackage> list, Boolean isUpdateSupport, String operName) {
        if (StringUtils.isNull(list) || list.size() == 0)
        {
            throw new BusinessException("导入数据不能为空！");
        }
        int successNum = 0;
        int failureNum = 0;
        StringBuilder successMsg = new StringBuilder();
        StringBuilder failureMsg = new StringBuilder();
        for (FqPackage data : list)
        {
            try
            {
                // 验证是否存在
                FqPackage parms = new FqPackage();
                parms.setStartNo(data.getStartNo());
                parms.setEndNo(data.getEndNo());
                parms.setColor(data.getColor());
                parms.setbName(data.getbName());
                List<FqPackage> u = fqPackageMapper.selectFqPackageList(parms);
                if (u == null || u.size() == 0)
                {
                    data.setCreateBy(operName);
                    this.insertFqPackage(data);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、起始编号: " + data.getStartNo()+ " 导入成功");
                }
                else if (isUpdateSupport)
                {
                    data.setUpdateBy(operName);
                    this.updateFqPackage(data);
                    successNum++;
                    successMsg.append("<br/>" + successNum + "、起始编号: " + data.getStartNo() + " 更新成功");
                }
                else
                {
                    failureNum++;
                    failureMsg.append("<br/>" + failureNum + "、起始编号: " + data.getStartNo() + " 已存在");
                }
            }
            catch (Exception e)
            {
                failureNum++;
                String msg = "<br/>" + failureNum + "、起始编号: " + data.getStartNo() + " 导入失败：";
                failureMsg.append(msg + e.getMessage());
                log.error(msg, e);
            }
        }
        if (failureNum > 0)
        {
            failureMsg.insert(0, "很抱歉，导入失败！共 " + failureNum + " 条数据格式不正确，错误如下：");
            throw new BusinessException(failureMsg.toString());
        }
        else
        {
            successMsg.insert(0, "恭喜您，数据已全部导入成功！共 " + successNum + " 条，数据如下：");
        }
        return successMsg.toString();
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateCkFqPackageByIds(FqPackage fqPackage, String ids) {
        //已出库
        fqPackage.setStatus("1");
        Long user_id = fqPackage.getkUserid() == 0 ? fqPackage.getaUserid() : fqPackage.getkUserid();
        fqPackage.setUserId(user_id);
        if(StringUtils.isNotEmpty(ids)){
            String[] idArray = ids.split(",");
//            fqPackage.setUpdateTime(DateUtils.getNowDate());
//            fqPackage.setUpdateBy(ShiroUtils.getLoginName());
            for (String id:
                    idArray) {
                fqPackage.setId(Long.valueOf(id));
                fqPackageMapper.updateFqPackage(fqPackage);
                //日志
                //领取日志
                FqPLogs fqPLogs = new FqPLogs();
                fqPLogs.setDeptId(fqPackage.getDeptId());
                fqPLogs.setpId(fqPackage.getId());
                fqPLogs.setNum(fqPackage.getNum());
                fqPLogs.setNumUse(fqPackage.getNumUse());
                fqPLogs.setUserId(fqPackage.getkUserid() == null ? fqPackage.getaUserid() : fqPackage.getkUserid());
                fqPLogs.setGetTime(fqPackage.getaGetTime() == null ? fqPackage.getkGetTime() : fqPackage.getaGetTime());
                fqPLogs.setOptType(fqPackage.getkUserid() == null ? "0" : "3");
                fqPLogsMapper.insertFqPLogs(fqPLogs);
            }
            return ids.length();
        }else{
            return 0;
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int updateGhFqPackageByIds(FqPackage fqPackage, String ids) {
        //已归还
        fqPackage.setStatus("2");
        Long user_id = fqPackage.getkUserid() == 0 ? fqPackage.getaUserid() : fqPackage.getkUserid();
        fqPackage.setUserId(user_id);
        if(StringUtils.isNotEmpty(ids)){
            String[] idArray = ids.split(",");
//            fqPackage.setUpdateTime(DateUtils.getNowDate());
//            fqPackage.setUpdateBy(ShiroUtils.getLoginName());
            for (String id:
                    idArray) {
                fqPackage.setId(Long.valueOf(id));
                fqPackageMapper.updateFqPackage(fqPackage);
                //日志
                //归还日志
                FqPLogs fqPLogs = new FqPLogs();
                fqPLogs.setDeptId(fqPackage.getDeptId());
                fqPLogs.setpId(fqPackage.getId());
                fqPLogs.setNum(fqPackage.getNum());
                fqPLogs.setNumUse(fqPackage.getNumUse());
                fqPLogs.setgUserid(fqPackage.getgUserid());
                fqPLogs.setGetTime(fqPackage.getgReTime());
                //查看用户角色
                List<SysUserRole> sysUserRoles = sysUserRoleMapper.selectUserRoleByUserId(fqPackage.getgUserid());
                String type = "1";
                for (SysUserRole s:
                     sysUserRoles) {
                    if(s.getRoleId() == 102){
                        type = "4";
                        break;
                    }
                }
                fqPLogs.setOptType(type);
                fqPLogsMapper.insertFqPLogs(fqPLogs);
            }
            return ids.length();
        }else{
            return 0;
        }
    }

    public static void main(String[] args) {
            String a="RC20190009801";
            String regEx="[0-9]";
            Pattern p = Pattern.compile(regEx);
            Matcher m = p.matcher(a);
            System.out.println( m.replaceAll("").trim());
    }
}
