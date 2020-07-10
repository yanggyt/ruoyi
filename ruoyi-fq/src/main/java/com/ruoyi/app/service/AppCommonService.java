package com.ruoyi.app.service;

import com.ruoyi.fq.domain.FqPackage;
import com.ruoyi.system.domain.SysDept;

import java.util.List;

/**
 * 封铅袋Service接口
 * 
 * @author mario
 * @date 2020-07-02
 */
public interface AppCommonService
{
    /**
     * 获取部门列表
     * @param dept
     * @return
     */
    public List<SysDept> selectDeptList(SysDept dept);
}
