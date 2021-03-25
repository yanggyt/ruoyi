package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.LdCom;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LdComQueryMapper {

    List<LdCom> queryAll();


}