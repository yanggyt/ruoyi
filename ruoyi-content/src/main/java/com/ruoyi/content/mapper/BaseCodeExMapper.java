package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.BaseCode;
import com.ruoyi.content.domain.BaseCodeTree;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface BaseCodeExMapper {
    public List<BaseCode> selectAllWithLimit(@Param(value = "companyId") String companyId, @Param(value = "branchId") String branchId, @Param(value = "codeType") String codeType,
                                             @Param(value = "codeCname") String codeCname, @Param(value = "orderNo") String orderNo, @Param(value = "startRow") int startRow,
                                             @Param(value = "rows") int rows);

    /**
     * 查询栏目树
     *
     * @param parMap
     * @return
     */
    public List<BaseCodeTree> columnTree(HashMap<String, String> parMap);

}