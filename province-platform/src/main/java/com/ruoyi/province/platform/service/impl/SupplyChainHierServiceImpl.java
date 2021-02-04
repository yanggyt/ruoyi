package com.ruoyi.province.platform.service.impl;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import com.ruoyi.common.annotation.DataScope;
import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.Ztree;
import com.ruoyi.common.utils.DateUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.province.platform.mapper.SupplyChainHierMapper;
import com.ruoyi.province.platform.domain.SupplyChainHier;
import com.ruoyi.province.platform.service.ISupplyChainHierService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;
import com.ruoyi.province.platform.utils.BussUtils;
import com.ruoyi.common.utils.ShiroUtils;

/**
 * 供应链层级Service业务层处理
 * 
 * @author dalin
 * @date 2021-01-14
 */
@Service
public class SupplyChainHierServiceImpl implements ISupplyChainHierService 
{
    @Autowired
    private SupplyChainHierMapper supplyChainHierMapper;

    /**
     * 查询供应链层级
     * 
     * @param supplyChainId 供应链层级ID
     * @return 供应链层级
     */
    @Override
    public SupplyChainHier selectSupplyChainHierById(Long supplyChainId)
    {
        return supplyChainHierMapper.selectSupplyChainHierById(supplyChainId);
    }

            /**
         * 查询供应链层级
         *
         * @param supplyChainId 供应链层级ID
         * @return 供应链层级
         */
        @Override
        public String checkSupplyChainHierUnique(SupplyChainHier supplyChainHier)
        {
            Long docId = StringUtils.isNull( supplyChainHier.getSupplyChainId() ) ? -1L : supplyChainHier.getSupplyChainId();
            SupplyChainHier info = supplyChainHierMapper.checkSupplyChainHierUnique( supplyChainHier.getSupplyChainName() );
            if (StringUtils.isNotNull(info) && info.getSupplyChainId().longValue() != docId.longValue())
            {
                return BussiConstants.DOC_NAME_NOT_UNIQUE;
            }
            return BussiConstants.DOC_NAME_UNIQUE;
        }

        /**
     * 查询供应链层级列表
     * 
     * @param supplyChainHier 供应链层级
     * @return 供应链层级
     */
    @Override
    public List<SupplyChainHier> selectSupplyChainHierList(SupplyChainHier supplyChainHier)
    {
        return supplyChainHierMapper.selectSupplyChainHierList(supplyChainHier);
    }

    /**
     * 新增供应链层级
     * 
     * @param supplyChainHier 供应链层级
     * @return 结果
     */
    @Override
    public int insertSupplyChainHier(SupplyChainHier supplyChainHier)
    {
        supplyChainHier.setDocNum("00001".concat( BussUtils.nextValue("supplyChainHier") ) );

        supplyChainHier.setCreateBy( ShiroUtils.getLoginName() );
        supplyChainHier.setCreateTime( DateUtils.getNowDate() );

    SupplyChainHier info = supplyChainHierMapper.selectSupplyChainHierById(supplyChainHier.getParentId());
    supplyChainHier.setAncestors(info.getAncestors() + "," + supplyChainHier.getParentId());

        return supplyChainHierMapper.insertSupplyChainHier(supplyChainHier);

    }

    /**
     * 修改供应链层级
     * 
     * @param supplyChainHier 供应链层级
     * @return 结果
     */
    @Override
    public int updateSupplyChainHier(SupplyChainHier supplyChainHier)
    {
        SupplyChainHier newParentSupplyChainHier = supplyChainHierMapper.selectSupplyChainHierById(supplyChainHier.getParentId());
        SupplyChainHier oldSupplyChainHier = selectSupplyChainHierById(supplyChainHier.getSupplyChainId());
        if (StringUtils.isNotNull(newParentSupplyChainHier) && StringUtils.isNotNull(oldSupplyChainHier))
        {
            String newAncestors = newParentSupplyChainHier.getAncestors() + "," + newParentSupplyChainHier.getSupplyChainId();
            String oldAncestors = oldSupplyChainHier.getAncestors();
            supplyChainHier.setAncestors(newAncestors);
            updateSupplyChainHierChildren(supplyChainHier.getSupplyChainId(), newAncestors, oldAncestors);
        }

        supplyChainHier.setUpdateTime(DateUtils.getNowDate());
        int result = supplyChainHierMapper.updateSupplyChainHier(supplyChainHier);

        if (UserConstants.DEPT_NORMAL.equals(supplyChainHier.getStatus()))
        {
            // 如果该供应链层级是启用状态，则启用该供应链层级的所有上级供应链层级
            updateParentSupplyChainHierStatus(supplyChainHier);
        }
        return result ;
    }

    /**
     * 删除供应链层级对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteSupplyChainHierByIds(String ids)
    {
        return supplyChainHierMapper.deleteSupplyChainHierByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除供应链层级信息
     * 
     * @param supplyChainId 供应链层级ID
     * @return 结果
     */
    @Override
    public int deleteSupplyChainHierById(Long supplyChainId)
    {
        return supplyChainHierMapper.deleteSupplyChainHierById(supplyChainId);
    }

    /**
     * 查询供应链层级树列表
     * 
     * @return 所有供应链层级信息
     */
    @Override
    public List<Ztree> selectSupplyChainHierTree()
    {
        List<SupplyChainHier> supplyChainHierList = supplyChainHierMapper.selectSupplyChainHierList(new SupplyChainHier());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (SupplyChainHier supplyChainHier : supplyChainHierList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(supplyChainHier.getSupplyChainId());
            ztree.setpId(supplyChainHier.getParentId());
            ztree.setName(supplyChainHier.getSupplyChainName());
            ztree.setTitle(supplyChainHier.getSupplyChainName());
            ztrees.add(ztree);
        }
        return ztrees;
    }


    /**
  * 修改该供应链层级的父级供应链层级状态
  *
  * @param supplyChainHier 当前部门
  */
    private void updateParentSupplyChainHierStatus(SupplyChainHier supplyChainHier)
    {
        String updateBy = supplyChainHier.getUpdateBy();
        supplyChainHier = supplyChainHierMapper.selectSupplyChainHierById(supplyChainHier.getSupplyChainId());
        supplyChainHier.setUpdateBy(updateBy);
            supplyChainHierMapper.updateSupplyChainHierStatus(supplyChainHier);
    }

    /**
     * 查询供应链层级管理树（排除下级）
     *
     * @param supplyChainId 部门ID
     * @return 所有部门信息
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<Ztree> selectSupplyChainHierTreeExcludeChild(SupplyChainHier supplyChainHier)
    {
        Long supplyChainId = supplyChainHier.getSupplyChainId();
        List<SupplyChainHier> supplyChainHierList = supplyChainHierMapper.selectSupplyChainHierList(supplyChainHier);
        Iterator<SupplyChainHier> it = supplyChainHierList.iterator();
        while (it.hasNext())
        {
            SupplyChainHier d = (SupplyChainHier) it.next();
            if (d.getSupplyChainId().intValue() == supplyChainId
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), supplyChainId + ""))
            {
                it.remove();
            }
        }
        List<Ztree> ztrees = initZtree(supplyChainHierList);
        return ztrees;
    }

    /**
     * 对象转供应链层级树
     *
     * @param supplyChainHierList 供应链层级列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SupplyChainHier> supplyChainHierList)
    {
        return initZtree(supplyChainHierList, null);
    }

    /**
        * 修改子元素关系
        *
        * @param supplyChainId 被修改的供应链层级ID
        * @param newAncestors 新的父ID集合
        * @param oldAncestors 旧的父ID集合
        */
    public void updateSupplyChainHierChildren(Long supplyChainId, String newAncestors, String oldAncestors)
    {
        List<SupplyChainHier> children = supplyChainHierMapper.selectChildrenSupplyChainHierById(supplyChainId);
        for (SupplyChainHier child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            supplyChainHierMapper.updateSupplyChainHierChildren(children);
        }
    }

    /**
     * 对象转供应链层级树
     *
     * @param supplyChainHierList 供应链层级列表
     * @param roleList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<SupplyChainHier> supplyChainHierList, List<String> roleSupplyChainHierList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(roleSupplyChainHierList);
        for (SupplyChainHier supplyChainHier : supplyChainHierList)
        {
            if (UserConstants.DEPT_NORMAL.equals(supplyChainHier.getStatus()))
            {
                Ztree ztree = new Ztree();
                ztree.setId(supplyChainHier.getSupplyChainId());
                ztree.setpId(supplyChainHier.getParentId());
                ztree.setName(supplyChainHier.getSupplyChainName());
                ztree.setTitle(supplyChainHier.getSupplyChainName());
                if (isCheck)
                {
                    ztree.setChecked(roleSupplyChainHierList.contains(supplyChainHier.getSupplyChainId() + supplyChainHier.getSupplyChainName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param supplyChainId 部门ID
     * @return 子部门数
     */
    @Override
    public int selectNormalChildrenSupplyChainHierById(Long supplyChainId)
    {
        return supplyChainHierMapper.selectNormalChildrenSupplyChainHierById(supplyChainId);
    }


}
