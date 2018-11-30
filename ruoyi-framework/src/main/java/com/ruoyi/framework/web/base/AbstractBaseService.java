package com.ruoyi.framework.web.base;

import java.util.List;

/**
 * @Author agile
 * Date: 17/1/13
 * Time: 15:13
 * Version 1.0.0
 */
public abstract interface AbstractBaseService<T> {

    public T selectOne(T entity);


    public T selectById(Object id);


    public List<T> selectList(T entity) ;


    public List<T> selectListAll();


    public Long selectCount(T entity);


    public void insert(T entity);


    public void insertSelective(T entity);


    public void delete(T entity) ;


    public void deleteById(Object id);


    public void updateById(T entity) ;


    public void updateSelectiveById(T entity);

    public List<T> selectByExample(Object example);

    public int selectCountByExample(Object example);



    public List<T> selectByQuery(T entity) ;
    /**
     * 设置请求分页数据
     */
    public void startPage();
}
