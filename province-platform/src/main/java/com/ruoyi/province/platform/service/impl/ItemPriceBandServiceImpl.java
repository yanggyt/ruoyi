package com.ruoyi.province.platform.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.province.platform.mapper.ItemPriceBandMapper;
import com.ruoyi.province.platform.domain.ItemPriceBand;
import com.ruoyi.province.platform.service.IItemPriceBandService;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.province.platform.Constants.BussiConstants;
import com.ruoyi.province.platform.utils.BussUtils;
import com.ruoyi.common.utils.ShiroUtils;

/**
 * 商品价格带Service业务层处理
 * 
 * @author dalin
 * @date 2020-12-24
 */
@Service
public class ItemPriceBandServiceImpl implements IItemPriceBandService 
{
    @Autowired
    private ItemPriceBandMapper itemPriceBandMapper;

    /**
     * 查询商品价格带
     * 
     * @param priceBandId 商品价格带ID
     * @return 商品价格带
     */
    @Override
    public ItemPriceBand selectItemPriceBandById(Long priceBandId)
    {
        return itemPriceBandMapper.selectItemPriceBandById(priceBandId);
    }

            /**
         * 查询商品价格带
         *
         * @param priceBandId 商品价格带ID
         * @return 商品价格带
         */
        @Override
        public String checkItemPriceBandUnique(ItemPriceBand itemPriceBand)
        {
            Long docId = StringUtils.isNull( itemPriceBand.getPriceBandId() ) ? -1L : itemPriceBand.getPriceBandId();
            ItemPriceBand info = itemPriceBandMapper.checkItemPriceBandUnique( itemPriceBand.getPriceBandName() );
            if (StringUtils.isNotNull(info) && info.getPriceBandId().longValue() != docId.longValue())
            {
                return BussiConstants.DOC_NAME_NOT_UNIQUE;
            }
            return BussiConstants.DOC_NAME_UNIQUE;
        }

        /**
     * 查询商品价格带列表
     * 
     * @param itemPriceBand 商品价格带
     * @return 商品价格带
     */
    @Override
    public List<ItemPriceBand> selectItemPriceBandList(ItemPriceBand itemPriceBand)
    {
        return itemPriceBandMapper.selectItemPriceBandList(itemPriceBand);
    }

    /**
     * 新增商品价格带
     * 
     * @param itemPriceBand 商品价格带
     * @return 结果
     */
    @Override
    public int insertItemPriceBand(ItemPriceBand itemPriceBand)
    {
        itemPriceBand.setDocNum("00001".concat( BussUtils.nextValue("itemPriceBand") ) );

        itemPriceBand.setCreateBy( ShiroUtils.getLoginName() );
        itemPriceBand.setCreateTime( DateUtils.getNowDate() );
        return itemPriceBandMapper.insertItemPriceBand(itemPriceBand);
    }

    /**
     * 修改商品价格带
     * 
     * @param itemPriceBand 商品价格带
     * @return 结果
     */
    @Override
    public int updateItemPriceBand(ItemPriceBand itemPriceBand)
    {
        itemPriceBand.setUpdateTime(DateUtils.getNowDate());
        return itemPriceBandMapper.updateItemPriceBand(itemPriceBand);
    }

    /**
     * 删除商品价格带对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteItemPriceBandByIds(String ids)
    {
        return itemPriceBandMapper.deleteItemPriceBandByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除商品价格带信息
     * 
     * @param priceBandId 商品价格带ID
     * @return 结果
     */
    @Override
    public int deleteItemPriceBandById(Long priceBandId)
    {
        return itemPriceBandMapper.deleteItemPriceBandById(priceBandId);
    }
}
