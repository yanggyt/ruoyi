package com.ruoyi.app.service.impl;

import com.ruoyi.app.service.AppCommonService;
import com.ruoyi.common.annotation.DataScope;
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
import com.ruoyi.system.domain.SysDept;
import com.ruoyi.system.domain.SysUserRole;
import com.ruoyi.system.mapper.SysDeptMapper;
import com.ruoyi.system.mapper.SysUserRoleMapper;
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
public class AppCommonServiceImpl implements AppCommonService
{
    private static final Logger log = LoggerFactory.getLogger(AppCommonServiceImpl.class);

    @Autowired
    private SysDeptMapper sysDeptMapper;

    /**
     * 查询部门管理数据
     *
     * @param dept 部门信息
     * @return 部门信息集合
     */
    @Override
    public List<SysDept> selectDeptList(SysDept dept)
    {
        return sysDeptMapper.selectDeptList(dept);
    }
}
