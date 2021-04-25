package com.ruoyi.content.mapper;


import com.ruoyi.content.domain.CmsSysRole;
import com.ruoyi.content.domain.CmsUSerSysRoleDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsSysRoleExMapper {
    /**
     * 分页查所有
     */
    List<CmsSysRole> selectByPage(@Param(value = "startRow") int startRow, @Param(value = "rows") int rows,
                                  @Param(value = "createUser") String createUser, @Param(value = "name") String name);

    List<CmsUSerSysRoleDto> selectuserRoleByPage(@Param(value = "startRow") int startRow, @Param(value = "rows") int rows,
                                                 @Param(value = "createUser") String createUser, @Param(value = "name") String name, @Param(value = "email") String email);
}