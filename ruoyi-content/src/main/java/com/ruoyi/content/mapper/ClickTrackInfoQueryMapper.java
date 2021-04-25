package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.ClickTrackInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClickTrackInfoQueryMapper {

    List<ClickTrackInfo> queryClickInfoByUserId(@Param(value = "userId") String userId, @Param(value = "articleId") String articleId, @Param(value = "startRow") int startRow,
                                                @Param(value = "rows") int rows);

}