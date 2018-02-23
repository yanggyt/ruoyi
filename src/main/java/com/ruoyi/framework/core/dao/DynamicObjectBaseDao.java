package com.ruoyi.framework.core.dao;

import java.util.List;
import javax.annotation.Resource;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.ruoyi.project.shiro.exception.base.DaoException;

/**
 * 数据DAO层通用数据处理
 * 
 * @author yangzz
 */
public class DynamicObjectBaseDao
{
    private static final Logger log = LoggerFactory.getLogger(DynamicObjectBaseDao.class);

    @Resource(name = "sqlSessionTemplate")
    private SqlSessionTemplate sqlSessionTemplate;

    /**
     * 保存对象
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public int save(String str, Object obj)
    {
        int rows;
        try
        {
            rows = sqlSessionTemplate.insert(str, obj);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new DaoException("插入失败");
        }
        return rows;
    }

    /**
     * 批量更新
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public int batchSave(String str, List<?> objs)
    {
        int rows;
        try
        {
            rows = sqlSessionTemplate.insert(str, objs);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new DaoException("批量更新失败");
        }
        return rows;
    }

    /**
     * 修改对象
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public int update(String str, Object obj)
    {
        int rows;
        try
        {
            rows = sqlSessionTemplate.update(str, obj);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new DaoException("修改失败");
        }
        return rows;
    }

    /**
     * 批量更新
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public void batchUpdate(String str, List<?> objs) throws Exception
    {
        SqlSessionFactory sqlSessionFactory = sqlSessionTemplate.getSqlSessionFactory();
        // 批量执行器
        SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH, false);
        try
        {
            if (objs != null)
            {
                for (int i = 0, size = objs.size(); i < size; i++)
                {
                    sqlSession.update(str, objs.get(i));
                }
                sqlSession.flushStatements();
                sqlSession.commit();
                sqlSession.clearCache();
            }
        }
        finally
        {
            sqlSession.close();
        }
    }

    /**
     * 批量删除
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public int batchDelete(String str, List<?> objs) throws Exception
    {
        int rows;
        try
        {
            rows = sqlSessionTemplate.update(str, objs);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new DaoException("批量删除失败");
        }
        return rows;
    }

    /**
     * 删除对象
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public int delete(String str, Object obj)
    {
        int rows;
        try
        {
            rows = sqlSessionTemplate.delete(str, obj);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new DaoException("删除失败");
        }
        return rows;
    }

    /**
     * 查找单条对象
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public <T> T findForObject(String str, Object obj)
    {
        T t;
        try
        {
            t = sqlSessionTemplate.selectOne(str, obj);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new DaoException("查找单条对象失败");
        }
        return t;
    }

    /**
     * 查找对象 - 无条件
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public <E> List<E> findForList(String str) throws Exception
    {
        List<E> list;
        try
        {
            list = sqlSessionTemplate.selectList(str);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new DaoException("查找所有对象-无条件。失败");
        }
        return list;
    }

    /**
     * 查找对象 - 有条件
     * 
     * @param str
     * @param obj
     * @return
     * @throws Exception
     */
    public <E> List<E> findForList(String str, Object obj)
    {
        List<E> list;
        try
        {
            list = sqlSessionTemplate.selectList(str, obj);
        }
        catch (Exception e)
        {
            log.error(e.getMessage());
            throw new DaoException("查找所有对象-有条件。失败");
        }
        return list;
    }

    public Object findForMap(String str, Object obj, String key, String value) throws Exception
    {
        return sqlSessionTemplate.selectMap(str, obj, key);
    }

}
