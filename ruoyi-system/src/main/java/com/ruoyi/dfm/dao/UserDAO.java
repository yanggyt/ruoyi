package com.ruoyi.dfm.dao;

import com.ruoyi.dfm.pojo.Page;
import com.ruoyi.dfm.pojo.UserInfo;
import com.ruoyi.dfm.pojo.UserQueryBean;
import com.ruoyi.dfm.util.PageUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class UserDAO extends JdbcBaseDao
{
  private static final Logger logger = LoggerFactory.getLogger(UserDAO.class);
  public void add(UserInfo user)
  {
    String sql = "INSERT INTO T_USER (F_NAME ,F_USERNAME, F_PASSWORD,F_DEPARTMENT,F_PROJECT_GROUP,F_EMAIL,F_GROUP_ID,F_COPY_EMAIL)VALUES(?,?,?,?,?,?,?,?)";

    Object[] args = new Object[8];
    args[0] = user.getName();
    args[1] = user.getUsername();
    args[2] = user.getPassword();
    args[3] = user.getDepartment();
    args[4] = user.getProjectGroup();
    args[5] = user.getEmail();
    args[6] = Integer.valueOf(user.getGroupId());
    args[7] = user.getCcEmail();
    getJdbcTemplate().update(sql, args);
  }

  public UserInfo checkUser(String username, String pwd) {
    UserInfo user = null;
    String sql = "SELECT * FROM T_USER T WHERE T.F_USERNAME = ? AND T.F_PASSWORD = ?";
    List list = getJdbcTemplate().queryForList(sql, new Object[] { username, pwd });

    if ((list == null) || (list.size() <= 0)) {
      return user;
    }
    Map map = (Map)list.get(0);
    user = new UserInfo();
    user.setId(Integer.parseInt(map.get("F_ID").toString()));
    user.setGroupId(Integer.parseInt(map.get("F_GROUP_ID").toString()));
    user.setDepartment((map.get("F_DEPARTMENT") == null) ? "" : map.get("F_DEPARTMENT").toString());
    user.setCcEmail((map.get("F_COPY_EMAIL") == null) ? "" : map.get("F_COPY_EMAIL").toString());
    user.setEmail((map.get("F_EMAIL") == null) ? "" : map.get("F_EMAIL").toString());
    user.setName((map.get("F_NAME") == null) ? "" : map.get("F_NAME").toString());
    user.setPassword(map.get("F_PASSWORD").toString());
    user.setProjectGroup((map.get("F_PROJECT_GROUP") == null) ? "" : map.get("F_PROJECT_GROUP").toString());
    user.setUsername(map.get("F_USERNAME").toString());
    user.setStatus(Integer.parseInt(map.get("F_STATUS").toString()));

    return user;
  }

  public List<UserInfo> getAll()
  {
    String sql = "SELECT * FROM T_USER T ORDER BY T.F_ID ASC";
    List list = getJdbcTemplate().queryForList(sql);
    List rs = null;
    if ((list == null) || (list.size() <= 0)) {
      return rs;
    }
    rs = new ArrayList();
    for (int i = 0; i < list.size(); ++i)
    {
      Map map = (Map)list.get(i);
      UserInfo user = new UserInfo();
      user.setId(Integer.parseInt(map.get("F_ID").toString()));
      user.setGroupId(Integer.parseInt(map.get("F_GROUP_ID").toString()));
      user.setDepartment((map.get("F_DEPARTMENT") == null) ? "" : map.get("F_DEPARTMENT").toString());
      user.setEmail((map.get("F_EMAIL") == null) ? "" : map.get("F_EMAIL").toString());
      user.setCcEmail((map.get("F_COPY_EMAIL") == null) ? "" : map.get("F_COPY_EMAIL").toString());
      user.setName((map.get("F_NAME") == null) ? "" : map.get("F_NAME").toString());
      user.setPassword((String)map.get("F_PASSWORD"));
      user.setProjectGroup((map.get("F_PROJECT_GROUP") == null) ? "" : map.get("F_PROJECT_GROUP").toString());
      user.setUsername(map.get("F_USERNAME").toString());
      user.setStatus(Integer.parseInt(map.get("F_STATUS").toString()));
      rs.add(user);
    }

    return rs;
  }

  public List<UserInfo> getAll(Page page)
  {
    int totalCount = getAllCount();
    int start = (page.getCurrentPage() - 1) * page.getPageSize();
    String sql = "SELECT * FROM T_USER T ORDER BY T.F_ID ASC  limit ?,?";
    List list = getJdbcTemplate().queryForList(sql, new Object[] { Integer.valueOf(start), Integer.valueOf(page.getPageSize()) });
    List rs = null;
    if ((list == null) || (list.size() <= 0)) {
      return rs;
    }
    rs = new ArrayList();
    for (int i = 0; i < list.size(); ++i)
    {
      Map map = (Map)list.get(i);
      UserInfo user = new UserInfo();
      user.setId(Integer.parseInt(map.get("F_ID").toString()));
      user.setGroupId(Integer.parseInt(map.get("F_GROUP_ID").toString()));
      user.setDepartment((map.get("F_DEPARTMENT") == null) ? "" : map.get("F_DEPARTMENT").toString());
      user.setEmail((map.get("F_EMAIL") == null) ? "" : map.get("F_EMAIL").toString());
      user.setCcEmail((map.get("F_COPY_EMAIL") == null) ? "" : map.get("F_COPY_EMAIL").toString());
      user.setName((map.get("F_NAME") == null) ? "" : map.get("F_NAME").toString());
      user.setPassword(map.get("F_PASSWORD").toString());
      user.setProjectGroup((map.get("F_PROJECT_GROUP") == null) ? "" : map.get("F_PROJECT_GROUP").toString());
      user.setUsername(map.get("F_USERNAME").toString());
      user.setStatus(Integer.parseInt(map.get("F_STATUS").toString()));
      rs.add(user);
    }

    PageUtil.constructPage(page, totalCount);
    return rs;
  }

  private int getAllCount()
  {
    String sql = "SELECT COUNT(*) FROM T_USER T ";
    return getJdbcTemplate().queryForObject(sql, Integer.class);
  }

  public void delete(int uid) {
    String sql = "DELETE  FROM T_USER WHERE F_ID = ?";
    getJdbcTemplate().update(sql, new Object[] { Integer.valueOf(uid) });
  }

  public UserInfo getById(int uid)
  {
    UserInfo user = null;
    String sql = "SELECT * FROM T_USER T WHERE T.F_ID = ?";
    List list = getJdbcTemplate().queryForList(sql, new Object[] { Integer.valueOf(uid) });

    if ((list == null) || (list.size() <= 0)) {
      return user;
    }
    Map map = (Map)list.get(0);
    user = new UserInfo();
    user.setId(Integer.parseInt(map.get("F_ID").toString()));
    user.setGroupId(Integer.parseInt(map.get("F_GROUP_ID").toString()));
    user.setDepartment((map.get("F_DEPARTMENT") == null) ? "" : map.get("F_DEPARTMENT").toString());
    user.setEmail((map.get("F_EMAIL") == null) ? "" : map.get("F_EMAIL").toString());
    user.setCcEmail((map.get("F_COPY_EMAIL") == null) ? "" : map.get("F_COPY_EMAIL").toString());
    user.setName((map.get("F_NAME") == null) ? "" : map.get("F_NAME").toString());
    user.setPassword(map.get("F_PASSWORD").toString());
    user.setProjectGroup((map.get("F_PROJECT_GROUP") == null) ? "" : map.get("F_PROJECT_GROUP").toString());
    user.setUsername(map.get("F_USERNAME").toString());
    user.setStatus(Integer.parseInt(map.get("F_STATUS").toString()));

    return user;
  }

  public void update(UserInfo user) {
    String sql = "UPDATE T_USER  SET F_NAME = ?,F_USERNAME= ?, F_PASSWORD= ?,F_DEPARTMENT= ?,F_PROJECT_GROUP= ?,F_EMAIL= ?,F_GROUP_ID= ?,F_STATUS = ?,F_COPY_EMAIL=? WHERE F_ID = ?";

    Object[] args = new Object[10];
    args[0] = user.getName();
    args[1] = user.getUsername();
    args[2] = user.getPassword();
    args[3] = user.getDepartment();
    args[4] = user.getProjectGroup();
    args[5] = user.getEmail();
    args[6] = Integer.valueOf(user.getGroupId());
    args[7] = Integer.valueOf(user.getStatus());
    args[8] = user.getCcEmail();
    args[9] = Integer.valueOf(user.getId());
    getJdbcTemplate().update(sql, args);
  }

  public List<UserInfo> getByQueryBean(UserQueryBean queryBean, Page page)
  {
    int totalCount = getCountByQueryBean(queryBean);
    int start = (page.getCurrentPage() - 1) * page.getPageSize();
    String sql = "SELECT * FROM T_USER T WHERE 1=1 ";
    List params = new ArrayList();
    if (queryBean != null)
    {
      sql = constructConditionSql(queryBean, sql, params);
    }
    sql = sql + "  limit ?,?";
    params.add(Integer.valueOf(start));
    params.add(Integer.valueOf(page.getPageSize()));
    PageUtil.constructPage(page, totalCount);

    List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql, params.toArray());
    List rs = new ArrayList();
    for (Map map : list)
    {
      UserInfo user = new UserInfo();
      user.setId(Integer.parseInt(map.get("F_ID").toString()));
      user.setGroupId(Integer.parseInt(map.get("F_GROUP_ID").toString()));
      user.setDepartment((map.get("F_DEPARTMENT") == null) ? "" : map.get("F_DEPARTMENT").toString());
      user.setEmail((map.get("F_EMAIL") == null) ? "" : map.get("F_EMAIL").toString());
      user.setCcEmail((map.get("F_COPY_EMAIL") == null) ? "" : map.get("F_COPY_EMAIL").toString());
      user.setName((map.get("F_NAME") == null) ? "" : map.get("F_NAME").toString());
      user.setPassword(map.get("F_PASSWORD").toString());
      user.setProjectGroup((map.get("F_PROJECT_GROUP") == null) ? "" : map.get("F_PROJECT_GROUP").toString());
      user.setUsername(map.get("F_USERNAME").toString());
      user.setStatus(Integer.parseInt(map.get("F_STATUS").toString()));
      rs.add(user);
    }
    return rs;
  }


  public List<UserInfo> getByQueryBean(UserQueryBean queryBean)
  {
    String sql = "SELECT * FROM T_USER T WHERE 1=1 ";
    List params = new ArrayList();
    if (queryBean != null)
    {
      sql = constructConditionSql(queryBean, sql, params);
    }

    List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql, params.toArray());
    List rs = new ArrayList();
    for (Map map : list)
    {
      UserInfo user = new UserInfo();
      user.setId(Integer.parseInt(map.get("F_ID").toString()));
      user.setGroupId(Integer.parseInt(map.get("F_GROUP_ID").toString()));
      user.setDepartment((map.get("F_DEPARTMENT") == null) ? "" : map.get("F_DEPARTMENT").toString());
      user.setEmail((map.get("F_EMAIL") == null) ? "" : map.get("F_EMAIL").toString());
      user.setCcEmail((map.get("F_COPY_EMAIL") == null) ? "" : map.get("F_COPY_EMAIL").toString());
      user.setName((map.get("F_NAME") == null) ? "" : map.get("F_NAME").toString());
      user.setPassword(map.get("F_PASSWORD").toString());
      user.setProjectGroup((map.get("F_PROJECT_GROUP") == null) ? "" : map.get("F_PROJECT_GROUP").toString());
      user.setUsername(map.get("F_USERNAME").toString());
      user.setStatus(Integer.parseInt(map.get("F_STATUS").toString()));
      rs.add(user);
    }
    return rs;
  }

  private int getCountByQueryBean(UserQueryBean queryBean) {
    String sql = "SELECT COUNT(*) FROM T_USER T WHERE 1=1 ";
    List params = new ArrayList();
    if (queryBean != null)
    {
      sql = constructConditionSql(queryBean, sql, params);
    }
    return getJdbcTemplate().queryForObject(sql, params.toArray(), Integer.class);
  }

  private String constructConditionSql(UserQueryBean queryBean, String sql, List<Object> params)
  {
    if ((queryBean.getUsername() != null) && (!("".equals(queryBean.getUsername().trim()))))
    {
      sql = sql + " AND T.F_USERNAME like '%" + queryBean.getUsername() + "%'";
    }
    if ((queryBean.getState() != null) && (!("-1".equals(queryBean.getState().trim()))))
    {
      sql = sql + " AND T.F_STATUS=?";
      params.add(queryBean.getState());
    }
    if ((queryBean.getName() != null) && (!("".equals(queryBean.getName().trim()))))
    {
      sql = sql + " AND T.F_NAME like '%" + queryBean.getName() + "%'";
    }
    if ((queryBean.getProjectGroup() != null) && (!("".equals(queryBean.getProjectGroup().trim()))))
    {
      sql = sql + " AND T.F_PROJECT_GROUP like '%" + queryBean.getProjectGroup() + "%'";
    }
    if ((queryBean.getDepartment() != null) && (!("".equals(queryBean.getDepartment().trim()))))
    {
      sql = sql + " AND T.F_DEPARTMENT like '%" + queryBean.getDepartment() + "%'";
    }
    return sql;
  }

  /**
   * 根据部门ID获取部门所有员工
   * @param department
   * @return
   */
  public List<UserInfo> getByDepartment(String department)
  {
    String sql = "SELECT * FROM T_USER T WHERE T.F_DEPARTMENT = ? ";
    List<Map<String, Object>> list = getJdbcTemplate().queryForList(sql, new Object[]{department});
    List rs = new ArrayList();
    for (Map map : list)
    {
      UserInfo user = new UserInfo();
      user.setId(Integer.parseInt(map.get("F_ID").toString()));
      user.setGroupId(Integer.parseInt(map.get("F_GROUP_ID").toString()));
      user.setDepartment((map.get("F_DEPARTMENT") == null) ? "" : map.get("F_DEPARTMENT").toString());
      user.setEmail((map.get("F_EMAIL") == null) ? "" : map.get("F_EMAIL").toString());
      user.setCcEmail((map.get("F_COPY_EMAIL") == null) ? "" : map.get("F_COPY_EMAIL").toString());
      user.setName((map.get("F_NAME") == null) ? "" : map.get("F_NAME").toString());
      user.setPassword(map.get("F_PASSWORD").toString());
      user.setProjectGroup((map.get("F_PROJECT_GROUP") == null) ? "" : map.get("F_PROJECT_GROUP").toString());
      user.setUsername(map.get("F_USERNAME").toString());
      user.setStatus(Integer.parseInt(map.get("F_STATUS").toString()));
      rs.add(user);
    }
    return rs;
  }

  /**
   * 检查当前需要分析的projectName是否属于当前登陆用户的可见范围
   * @param projectName
   * @param submitUsers
   */
    public boolean checkProjectAndUsers(String projectName, List<UserInfo> submitUsers) {
        String sql = "SELECT count(1) FROM T_PROJECT T WHERE T.F_SUBMIT_USER IN (-1";
        for (int i = 0; i < submitUsers.size(); ++i)
        {
          sql += "," + submitUsers.get(i).getId();
        }
        sql += ") AND T.F_PROJECT_NAME = ?";
        log.info("检查项目和用户关系sql=" + sql);
        int result = getJdbcTemplate().queryForObject(sql, new Object[]{ projectName}, Integer.class);
        if(result > 0) {
          return true;
        }
        return false;
    }
}