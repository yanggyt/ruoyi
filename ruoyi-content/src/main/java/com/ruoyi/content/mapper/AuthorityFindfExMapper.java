package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.AuthorityExDto;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorityFindfExMapper {

    int batchDdeleteOperatorInfo(@Param("array") String[] array);

    /**
     * 根据用户查询菜单
     *
     * @param userLog
     * @return
     */
    public List<AuthorityExDto> findAuthorityEx(@Param(value = "userLog") String userLog);

    public int insertOPeratorPath(@Param("array") String[] array, @Param(value = "email") String email,
                                  @Param(value = "createUser") String createUser, @Param(value = "creaetDate") String createDate,
                                  @Param(value = "createTime") String createTime);
}
