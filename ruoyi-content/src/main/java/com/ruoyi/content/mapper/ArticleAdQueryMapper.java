package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.ArticleAdInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleAdQueryMapper {

    List<ArticleAdInfo> queryAdByCompanyId(@Param("companyId") String companyId);

    List<ArticleAdInfo> selectAllWithLimit(@Param(value = "companyId") String companyId, @Param(value = "adTitle") String adTitle,
                                           @Param(value = "startRow") int startRow, @Param(value = "rows") int rows);
}