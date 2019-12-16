package com.ruoyi.system.service.impl;

import com.ruoyi.common.constant.UserConstants;
import com.ruoyi.common.core.domain.BaseEntity;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.system.domain.SysPost;
import com.ruoyi.system.repository.SysPostRepository;
import com.ruoyi.system.repository.SysUserRepository;
import com.ruoyi.system.service.ISysPostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;

/**
 * 岗位信息 服务层处理
 *
 * @author ruoyi
 */
@Service
public class SysPostServiceImpl implements ISysPostService {

    @Autowired
    private SysPostRepository sysPostRepository;
    @Autowired
    private SysUserRepository sysUserRepository;

    /**
     * 查询岗位信息集合
     *
     * @param post 岗位信息
     * @param pageRequest
     * @return 岗位信息集合
     */
    @Override
    public Page<SysPost> selectPostList(SysPost post, Pageable pageRequest) {
        return sysPostRepository.findAll(getSpecification(post), pageRequest);
    }

    private Specification<SysPost> getSpecification(SysPost sysPost){
        return new Specification<SysPost>() {
            @Override
            public Predicate toPredicate(Root<SysPost> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
                List<Predicate> predicates = new ArrayList<>();
                if(StringUtils.isNotEmpty(sysPost.getPostName())){
                    predicates.add(criteriaBuilder.like(root.get("postName").as(String.class), "%" + sysPost.getPostName() + "%"));
                }
                if(StringUtils.isNotEmpty(sysPost.getPostCode())){
                    predicates.add(criteriaBuilder.like(root.get("postCode").as(String.class), "%" + sysPost.getPostName() + "%"));
                }
                if(StringUtils.isNotEmpty(sysPost.getStatus())){
                    predicates.add(criteriaBuilder.equal(root.get("status").as(String.class), sysPost.getStatus()));
                }
                return criteriaBuilder.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }

    /**
     * 查询所有岗位
     *
     * @return 岗位列表
     */
    @Override
    public List<SysPost> selectPostAll() {
        return sysPostRepository.findAll();
    }

    /**
     * 通过岗位ID查询岗位信息
     *
     * @param postId 岗位ID
     * @return 角色对象信息
     */
    @Override
    public SysPost selectPostById(Long postId) {
        return sysPostRepository.findById(postId).orElseThrow(() -> new IllegalArgumentException("未知的数据id:"+postId));
    }

    /**
     * 批量删除岗位信息
     *
     * @param ids 需要删除的数据ID
     * @throws Exception
     */
    @Override
    @Transactional
    public int deletePostByIds(String ids) throws BusinessException {
        Long[] postIds = Convert.toLongArray(ids);
        for (Long postId : postIds) {
            SysPost post = selectPostById(postId);
            if (countUserPostById(postId) > 0) {
                throw new BusinessException(String.format("%1$s已分配,不能删除", post.getPostName()));
            }
            sysPostRepository.deleteById(postId);
        }
        return postIds.length;
    }

    /**
     * 新增保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Transactional
    @Override
    public SysPost insertPost(SysPost post) {
        return sysPostRepository.save(post);
    }

    /**
     * 修改保存岗位信息
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Transactional
    @Override
    public SysPost updatePost(SysPost post) {
        return sysPostRepository.save(post);
    }

    /**
     * 通过岗位ID查询岗位使用数量
     *
     * @param postId 岗位ID
     * @return 结果
     */
    @Override
    public int countUserPostById(Long postId) {
        return sysUserRepository.countByDelFlagAndPostsContaining(BaseEntity.NOT_DELETED, new SysPost(postId));
    }

    /**
     * 校验岗位名称是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public String checkPostNameUnique(SysPost post) {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = sysPostRepository.findFirstByPostName(post.getPostName());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue()) {
            return UserConstants.POST_NAME_NOT_UNIQUE;
        }
        return UserConstants.POST_NAME_UNIQUE;
    }

    /**
     * 校验岗位编码是否唯一
     *
     * @param post 岗位信息
     * @return 结果
     */
    @Override
    public String checkPostCodeUnique(SysPost post) {
        Long postId = StringUtils.isNull(post.getPostId()) ? -1L : post.getPostId();
        SysPost info = sysPostRepository.findFirstByPostCode(post.getPostCode());
        if (StringUtils.isNotNull(info) && info.getPostId().longValue() != postId.longValue()) {
            return UserConstants.POST_CODE_NOT_UNIQUE;
        }
        return UserConstants.POST_CODE_UNIQUE;
    }
}
