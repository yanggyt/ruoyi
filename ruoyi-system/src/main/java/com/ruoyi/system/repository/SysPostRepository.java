package com.ruoyi.system.repository;

import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.repository.base.BaseRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SysPostRepository extends BaseRepository<SysPost, Long> {

    int countByPostId(Long postId);

    SysPost findFirstByPostName(String postName);

    SysPost findFirstByPostCode(String postCode);
}
