package com.ruoyi.framework.web.base;

import com.github.pagehelper.PageHelper;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.framework.util.EntityUtils;
import com.ruoyi.framework.web.page.PageDomain;
import com.ruoyi.framework.web.page.TableSupport;
import org.springframework.beans.factory.annotation.Autowired;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

/**
 * @Author agile
 * Date: 17/1/13
 * Time: 15:13
 * Version 1.0.0
 */
public abstract class AbstractBaseServiceImpl<M extends Mapper<T>, T> implements AbstractBaseService<T>{
    @Autowired
    protected M mapper;
    public void setMapper(M mapper) {
        this.mapper = mapper;
    }
    @Override
    public T selectOne(T entity) {
        return mapper.selectOne(entity);
    }


    @Override
    public T selectById(Object id) {
        return mapper.selectByPrimaryKey(id);
    }


    @Override
    public List<T> selectList(T entity) {
        return mapper.select(entity);
    }


    @Override
    public List<T> selectListAll() {
        return mapper.selectAll();
    }


    @Override
    public Long selectCount(T entity) {
        return new Long(mapper.selectCount(entity));
    }


    @Override
    public void insert(T entity) {
        EntityUtils.setCreatAndUpdatInfo(entity);
        mapper.insert(entity);
    }


    @Override
    public void insertSelective(T entity) {
        EntityUtils.setCreatAndUpdatInfo(entity);
        mapper.insertSelective(entity);
    }


    @Override
    public void delete(T entity) {
        mapper.delete(entity);
    }


    @Override
    public void deleteById(Object id) {
        mapper.deleteByPrimaryKey(id);
    }


    @Override
    public void updateById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKey(entity);
    }


    @Override
    public void updateSelectiveById(T entity) {
        EntityUtils.setUpdatedInfo(entity);
        mapper.updateByPrimaryKeySelective(entity);

    }

    @Override
    public List<T> selectByExample(Object example) {
        return mapper.selectByExample(example);
    }

    @Override
    public int selectCountByExample(Object example) {
        return mapper.selectCountByExample(example);
    }



    @Override
    public List<T> selectByQuery(T entity) {
        startPage();
        return  mapper.select(entity);
    }
    /**
     * 设置请求分页数据
     */
    @Override
    public void startPage()
    {
        PageDomain pageDomain = TableSupport.buildPageRequest();
        Integer pageNum = pageDomain.getPageNum();
        Integer pageSize = pageDomain.getPageSize();
        if (StringUtils.isNotNull(pageNum) && StringUtils.isNotNull(pageSize))
        {
            String orderBy = pageDomain.getOrderBy();
            PageHelper.startPage(pageNum, pageSize, orderBy);
        }
    }
}
