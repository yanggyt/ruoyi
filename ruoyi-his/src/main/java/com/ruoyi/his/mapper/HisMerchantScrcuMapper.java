package com.ruoyi.his.mapper;

import com.ruoyi.common.mappers.RuoYiBaseMapper;
import com.ruoyi.his.domain.HisMerchantScrcu;

import java.util.List;

/**
 * 农信商户Mapper接口
 * 
 * @author bend
 * @date 2020-07-27
 */
public interface HisMerchantScrcuMapper extends RuoYiBaseMapper<HisMerchantScrcu>
{

    /**
     * 查询农信商户
     *
     * @param id 农信商户ID
     * @return 农信商户
     */
    public HisMerchantScrcu selectHisMerchantScrcuById(Long id);

    /**
     * 查询农信商户列表
     * 
     * @param hisMerchantScrcu 农信商户
     * @return 农信商户集合
     */
    public List<HisMerchantScrcu> selectHisMerchantScrcuList(HisMerchantScrcu hisMerchantScrcu);


}
