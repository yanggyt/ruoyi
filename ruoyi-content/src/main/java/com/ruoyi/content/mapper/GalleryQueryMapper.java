package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.GalleryPicInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GalleryQueryMapper {

    public List<GalleryPicInfo> selectAllWithLimit(@Param(value = "companyId") String companyId, @Param(value = "branchId") String branchId,
                                                   @Param(value = "picState") List<String> picState, @Param(value = "picId") List<Integer> picId,
                                                   @Param(value = "startRow") int startRow, @Param(value = "rows") int rows);

    public List<String> selectChannelId(@Param(value = "picId") Integer picId);

    public List<GalleryPicInfo> selectAllPicInfo(@Param(value = "companyId") String companyId, @Param(value = "branchId") String branchId,
                                                 @Param(value = "picState") List<String> picState, @Param(value = "picId") List<Integer> picId);
}
