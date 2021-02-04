package com.ruoyi.province.platform.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import com.ruoyi.common.utils.StringUtils;
import org.springframework.transaction.annotation.Transactional;
import com.ruoyi.province.platform.domain.ItemParamtersUnit;
import com.ruoyi.province.platform.mapper.ItemParamtersMapper;
import com.ruoyi.province.platform.domain.ItemParamters;
import com.ruoyi.province.platform.service.IItemParamtersService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;
import com.ruoyi.province.platform.utils.BussUtils;
import com.ruoyi.common.utils.ShiroUtils;

/**
 * 商品参数Service业务层处理
 * 
 * @author dalin
 * @date 2021-01-14
 */
@Service
public class ItemParamtersServiceImpl implements IItemParamtersService 
{
    @Autowired
    private ItemParamtersMapper itemParamtersMapper;

    /**
     * 查询商品参数
     * 
     * @param itemParamtersId 商品参数ID
     * @return 商品参数
     */
    @Override
    public ItemParamters selectItemParamtersById(Long itemParamtersId)
    {
        return itemParamtersMapper.selectItemParamtersById(itemParamtersId);
    }

            /**
         * 查询商品参数
         *
         * @param itemParamtersId 商品参数ID
         * @return 商品参数
         */
        @Override
        public String checkItemParamtersUnique(ItemParamters itemParamters)
        {
            Long docId = StringUtils.isNull( itemParamters.getItemParamtersId() ) ? -1L : itemParamters.getItemParamtersId();
            ItemParamters info = itemParamtersMapper.checkItemParamtersUnique( itemParamters.getItemParamtersName() );
            if (StringUtils.isNotNull(info) && info.getItemParamtersId().longValue() != docId.longValue())
            {
                return BussiConstants.DOC_NAME_NOT_UNIQUE;
            }
            return BussiConstants.DOC_NAME_UNIQUE;
        }

        /**
     * 查询商品参数列表
     * 
     * @param itemParamters 商品参数
     * @return 商品参数
     */
    @Override
    public List<ItemParamters> selectItemParamtersList(ItemParamters itemParamters)
    {
        return itemParamtersMapper.selectItemParamtersList(itemParamters);
    }

    /**
     * 新增商品参数
     * 
     * @param itemParamters 商品参数
     * @return 结果
     */
    @Transactional
    @Override
    public int insertItemParamters(ItemParamters itemParamters)
    {
        itemParamters.setDocNum("00001".concat( BussUtils.nextValue("itemParamters") ) );

        itemParamters.setCreateBy( ShiroUtils.getLoginName() );
        itemParamters.setCreateTime( DateUtils.getNowDate() );


        int rows = itemParamtersMapper.insertItemParamters(itemParamters);
        insertItemParamtersUnit(itemParamters);
        return rows;

    }

    /**
     * 修改商品参数
     * 
     * @param itemParamters 商品参数
     * @return 结果
     */
    @Transactional
    @Override
    public int updateItemParamters(ItemParamters itemParamters)
    {

        itemParamters.setUpdateTime(DateUtils.getNowDate());
        itemParamtersMapper.deleteItemParamtersUnitByItemParamtersId(itemParamters.getItemParamtersId());
        insertItemParamtersUnit(itemParamters);
        int result = itemParamtersMapper.updateItemParamters(itemParamters);

        return result ;
    }

    /**
     * 删除商品参数对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Transactional
    @Override
    public int deleteItemParamtersByIds(String ids)
    {
        itemParamtersMapper.deleteItemParamtersUnitByItemParamtersIds(Convert.toStrArray(ids));
        return itemParamtersMapper.deleteItemParamtersByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品参数信息
     * 
     * @param itemParamtersId 商品参数ID
     * @return 结果
     */
    @Override
    public int deleteItemParamtersById(Long itemParamtersId)
    {
        itemParamtersMapper.deleteItemParamtersUnitByItemParamtersId(itemParamtersId);
        return itemParamtersMapper.deleteItemParamtersById(itemParamtersId);
    }

    /**
     * 新增商品参数_计量单位信息
     * 
     * @param itemParamters 商品参数对象
     */
    public void insertItemParamtersUnit(ItemParamters itemParamters)
    {
        List<ItemParamtersUnit> itemParamtersUnitList = itemParamters.getItemParamtersUnitList();
        Long itemParamtersId = itemParamters.getItemParamtersId();
        if (StringUtils.isNotNull(itemParamtersUnitList))
        {
            List<ItemParamtersUnit> list = new ArrayList<ItemParamtersUnit>();
            for (ItemParamtersUnit itemParamtersUnit : itemParamtersUnitList)
            {
                itemParamtersUnit.setItemParamtersId(itemParamtersId);
                list.add(itemParamtersUnit);
            }
            if (list.size() > 0)
            {
                itemParamtersMapper.batchItemParamtersUnit(list);
            }
        }
    }


}
