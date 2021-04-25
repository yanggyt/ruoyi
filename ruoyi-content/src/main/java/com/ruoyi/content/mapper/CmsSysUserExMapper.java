package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.CmsSysUser;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 系统用户的分页查询
 *
 * @author ZMN
 */
@Repository
public interface CmsSysUserExMapper {
    /**
     * 分页查所有
     */
//    List<CmsSysUser> selectByPage(@Param(value = "startRow") int startRow,@Param(value = "rows") int rows,
//    		@Param(value = "createUser") String createUser,@Param(value = "emial") String emial,
//    		@Param(value = "phone") String phone,@Param(value = "name") String name,
//    		@Param(value = "companyId") String companyId);
    List<CmsSysUser> selectByPage(@Param(value = "startRow") int startRow, @Param(value = "rows") int rows,
                                  @Param(value = "email") String email,
                                  @Param(value = "phone") String phone, @Param(value = "name") String name,
                                  @Param(value = "companyId") String companyId);

    /**
     * 根据登录账号查询登录信息
     *
     * @param email
     * @return
     */
    CmsSysUser queryLoginInfoByEmail(@Param(value = "email") String email);
}