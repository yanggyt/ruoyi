package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.CmsSysAuthority;
import com.ruoyi.content.domain.CmsSysRoleAuthorDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CmsSysAuthorityExMapper {

    /**
     * 分页查询
     */
    public List<CmsSysAuthority> selectAllMenuWithLimit(@Param(value = "startRow") int startRow,
                                                        @Param(value = "rows") int rows, @Param(value = "createUser") String createUser,
                                                        @Param(value = "id") String id, @Param(value = "level") String level,
                                                        @Param(value = "type") String type, @Param(value = "operatorRoleName") String operatorRoleName);

    public List<CmsSysRoleAuthorDto> selectAllRoleMenuWithLimit(@Param(value = "startRow") int startRow,
                                                                @Param(value = "rows") int rows, @Param(value = "createUser") String createUser,
                                                                @Param(value = "id") String id, @Param(value = "level") String level, @Param(value = "type") String type,
                                                                @Param(value = "operatorRoleName") String operatorRoleName, @Param(value = "role") String role);
}