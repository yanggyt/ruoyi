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
import com.ruoyi.province.platform.mapper.PurchUserTypeMapper;
import com.ruoyi.province.platform.domain.PurchUserType;
import com.ruoyi.province.platform.service.IPurchUserTypeService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;
import com.ruoyi.province.platform.utils.BussUtils;
import com.ruoyi.common.utils.ShiroUtils;

/**
 * 购货用户类型Service业务层处理
 * 
 * @author dalin
 * @date 2021-01-14
 */
@Service
public class PurchUserTypeServiceImpl implements IPurchUserTypeService 
{
    @Autowired
    private PurchUserTypeMapper purchUserTypeMapper;

    /**
     * 查询购货用户类型
     * 
     * @param purchTypeId 购货用户类型ID
     * @return 购货用户类型
     */
    @Override
    public PurchUserType selectPurchUserTypeById(Long purchTypeId)
    {
        return purchUserTypeMapper.selectPurchUserTypeById(purchTypeId);
    }

            /**
         * 查询购货用户类型
         *
         * @param purchTypeId 购货用户类型ID
         * @return 购货用户类型
         */
        @Override
        public String checkPurchUserTypeUnique(PurchUserType purchUserType)
        {
            Long docId = StringUtils.isNull( purchUserType.getPurchTypeId() ) ? -1L : purchUserType.getPurchTypeId();
            PurchUserType info = purchUserTypeMapper.checkPurchUserTypeUnique( purchUserType.getPurchTypeName() );
            if (StringUtils.isNotNull(info) && info.getPurchTypeId().longValue() != docId.longValue())
            {
                return BussiConstants.DOC_NAME_NOT_UNIQUE;
            }
            return BussiConstants.DOC_NAME_UNIQUE;
        }

        /**
     * 查询购货用户类型列表
     * 
     * @param purchUserType 购货用户类型
     * @return 购货用户类型
     */
    @Override
    public List<PurchUserType> selectPurchUserTypeList(PurchUserType purchUserType)
    {
        return purchUserTypeMapper.selectPurchUserTypeList(purchUserType);
    }

    /**
     * 新增购货用户类型
     * 
     * @param purchUserType 购货用户类型
     * @return 结果
     */
    @Override
    public int insertPurchUserType(PurchUserType purchUserType)
    {
        purchUserType.setDocNum("00001".concat( BussUtils.nextValue("purchUserType") ) );

        purchUserType.setCreateBy( ShiroUtils.getLoginName() );
        purchUserType.setCreateTime( DateUtils.getNowDate() );

    PurchUserType info = purchUserTypeMapper.selectPurchUserTypeById(purchUserType.getParentId());
    purchUserType.setAncestors(info.getAncestors() + "," + purchUserType.getParentId());

        return purchUserTypeMapper.insertPurchUserType(purchUserType);

    }

    /**
     * 修改购货用户类型
     * 
     * @param purchUserType 购货用户类型
     * @return 结果
     */
    @Override
    public int updatePurchUserType(PurchUserType purchUserType)
    {
        PurchUserType newParentPurchUserType = purchUserTypeMapper.selectPurchUserTypeById(purchUserType.getParentId());
        PurchUserType oldPurchUserType = selectPurchUserTypeById(purchUserType.getPurchTypeId());
        if (StringUtils.isNotNull(newParentPurchUserType) && StringUtils.isNotNull(oldPurchUserType))
        {
            String newAncestors = newParentPurchUserType.getAncestors() + "," + newParentPurchUserType.getPurchTypeId();
            String oldAncestors = oldPurchUserType.getAncestors();
            purchUserType.setAncestors(newAncestors);
            updatePurchUserTypeChildren(purchUserType.getPurchTypeId(), newAncestors, oldAncestors);
        }

        purchUserType.setUpdateTime(DateUtils.getNowDate());
        int result = purchUserTypeMapper.updatePurchUserType(purchUserType);

        if (UserConstants.DEPT_NORMAL.equals(purchUserType.getStatus()))
        {
            // 如果该购货用户类型是启用状态，则启用该购货用户类型的所有上级购货用户类型
            updateParentPurchUserTypeStatus(purchUserType);
        }
        return result ;
    }

    /**
     * 删除购货用户类型对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deletePurchUserTypeByIds(String ids)
    {
        return purchUserTypeMapper.deletePurchUserTypeByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除购货用户类型信息
     * 
     * @param purchTypeId 购货用户类型ID
     * @return 结果
     */
    @Override
    public int deletePurchUserTypeById(Long purchTypeId)
    {
        return purchUserTypeMapper.deletePurchUserTypeById(purchTypeId);
    }

    /**
     * 查询购货用户类型树列表
     * 
     * @return 所有购货用户类型信息
     */
    @Override
    public List<Ztree> selectPurchUserTypeTree()
    {
        List<PurchUserType> purchUserTypeList = purchUserTypeMapper.selectPurchUserTypeList(new PurchUserType());
        List<Ztree> ztrees = new ArrayList<Ztree>();
        for (PurchUserType purchUserType : purchUserTypeList)
        {
            Ztree ztree = new Ztree();
            ztree.setId(purchUserType.getPurchTypeId());
            ztree.setpId(purchUserType.getParentId());
            ztree.setName(purchUserType.getPurchTypeName());
            ztree.setTitle(purchUserType.getPurchTypeName());
            ztrees.add(ztree);
        }
        return ztrees;
    }


    /**
  * 修改该购货用户类型的父级购货用户类型状态
  *
  * @param purchUserType 当前部门
  */
    private void updateParentPurchUserTypeStatus(PurchUserType purchUserType)
    {
        String updateBy = purchUserType.getUpdateBy();
        purchUserType = purchUserTypeMapper.selectPurchUserTypeById(purchUserType.getPurchTypeId());
        purchUserType.setUpdateBy(updateBy);
            purchUserTypeMapper.updatePurchUserTypeStatus(purchUserType);
    }

    /**
     * 查询购货用户类型管理树（排除下级）
     *
     * @param purchTypeId 部门ID
     * @return 所有部门信息
     */
    @Override
    @DataScope(deptAlias = "d")
    public List<Ztree> selectPurchUserTypeTreeExcludeChild(PurchUserType purchUserType)
    {
        Long purchTypeId = purchUserType.getPurchTypeId();
        List<PurchUserType> purchUserTypeList = purchUserTypeMapper.selectPurchUserTypeList(purchUserType);
        Iterator<PurchUserType> it = purchUserTypeList.iterator();
        while (it.hasNext())
        {
            PurchUserType d = (PurchUserType) it.next();
            if (d.getPurchTypeId().intValue() == purchTypeId
                    || ArrayUtils.contains(StringUtils.split(d.getAncestors(), ","), purchTypeId + ""))
            {
                it.remove();
            }
        }
        List<Ztree> ztrees = initZtree(purchUserTypeList);
        return ztrees;
    }

    /**
     * 对象转购货用户类型树
     *
     * @param purchUserTypeList 购货用户类型列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<PurchUserType> purchUserTypeList)
    {
        return initZtree(purchUserTypeList, null);
    }

    /**
        * 修改子元素关系
        *
        * @param purchTypeId 被修改的购货用户类型ID
        * @param newAncestors 新的父ID集合
        * @param oldAncestors 旧的父ID集合
        */
    public void updatePurchUserTypeChildren(Long purchTypeId, String newAncestors, String oldAncestors)
    {
        List<PurchUserType> children = purchUserTypeMapper.selectChildrenPurchUserTypeById(purchTypeId);
        for (PurchUserType child : children)
        {
            child.setAncestors(child.getAncestors().replace(oldAncestors, newAncestors));
        }
        if (children.size() > 0)
        {
            purchUserTypeMapper.updatePurchUserTypeChildren(children);
        }
    }

    /**
     * 对象转购货用户类型树
     *
     * @param purchUserTypeList 购货用户类型列表
     * @param roleList 角色已存在菜单列表
     * @return 树结构列表
     */
    public List<Ztree> initZtree(List<PurchUserType> purchUserTypeList, List<String> rolePurchUserTypeList)
    {

        List<Ztree> ztrees = new ArrayList<Ztree>();
        boolean isCheck = StringUtils.isNotNull(rolePurchUserTypeList);
        for (PurchUserType purchUserType : purchUserTypeList)
        {
            if (UserConstants.DEPT_NORMAL.equals(purchUserType.getStatus()))
            {
                Ztree ztree = new Ztree();
                ztree.setId(purchUserType.getPurchTypeId());
                ztree.setpId(purchUserType.getParentId());
                ztree.setName(purchUserType.getPurchTypeName());
                ztree.setTitle(purchUserType.getPurchTypeName());
                if (isCheck)
                {
                    ztree.setChecked(rolePurchUserTypeList.contains(purchUserType.getPurchTypeId() + purchUserType.getPurchTypeName()));
                }
                ztrees.add(ztree);
            }
        }
        return ztrees;
    }

    /**
     * 根据ID查询所有子部门（正常状态）
     *
     * @param purchTypeId 部门ID
     * @return 子部门数
     */
    @Override
    public int selectNormalChildrenPurchUserTypeById(Long purchTypeId)
    {
        return purchUserTypeMapper.selectNormalChildrenPurchUserTypeById(purchTypeId);
    }


}
