package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.AttachmentInfo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 说明：根据附件的对应id和type查询某个课程下的所有附件进行分页查询
 */
@Repository
public interface AttachmentInfoExMapper {
    public List<AttachmentInfo> selectAllWithLimit(@Param(value = "courseId") String courseId, @Param(value = "courseType") String courseType,
                                                   @Param(value = "startRow") int startRow, @Param(value = "rows") int rows);
}
