package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.PicAdInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PicAdQueryMapper {

    List<PicAdInfo> selectAllWithLimit(@Param(value = "companyId") String companyId, @Param(value = "picAdTitle") String picAdTitle,
                                       @Param(value = "startRow") int startRow, @Param(value = "rows") int rows);
}