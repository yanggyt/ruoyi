package com.ruoyi.test.mapper;

import com.ruoyi.test.domain.TcUser;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component("TCUserMapper")
public interface TcUserMapper {
    //查询所有
    List<TcUser> selectAll();

    //查询 by id
    TcUser selectById(int id);

    //增加
    int insert(TcUser tcUser);

    //删除 by id
    int deleteById(int id);

    //更新 by id
    int updateById(TcUser tcUser);
}
