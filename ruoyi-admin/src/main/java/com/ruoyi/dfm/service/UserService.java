package com.ruoyi.dfm.service;

import com.ruoyi.dfm.dao.UserDAO;
import com.ruoyi.dfm.pojo.Page;
import com.ruoyi.dfm.pojo.Project;
import com.ruoyi.dfm.pojo.UserInfo;
import com.ruoyi.dfm.pojo.UserQueryBean;
import com.ruoyi.dfm.util.Md5Util;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

@Component
public class UserService
{
  private static final Logger logger = LoggerFactory.getLogger(UserService.class);
  @Autowired
  private UserDAO userDAO;
  @Autowired
  private ProjectService projectService;

  public void addUser(UserInfo user)
  {
    this.userDAO.add(user);
  }

  public List<UserInfo> getAllUser(Page page)
  {
    return this.userDAO.getAll(page);
  }

  public List<UserInfo> getAllUser()
  {
    return this.userDAO.getAll();
  }

  public void deleteUser(int uid, String delProject)
  {
    if ("yes".equals(delProject))
    {
      List list = this.projectService.getProjectByUser(uid);

      if ((list != null) && (!(list.isEmpty())))
      {
        String[] pids = new String[list.size()];
        for (int i = 0; i < list.size(); ++i)
        {
          pids[i] = ((Project)list.get(i)).getId() + "";
        }
        this.projectService.deleteProject(pids);

        UserInfo user = this.userDAO.getById(uid);
        String userDirPath = FileService.rootPath + user.getUsername();
        File userDir = new File(userDirPath);

        if (userDir.exists())
        {
          userDir.delete();
        }

      }

    }

    this.userDAO.delete(uid);
  }

  public UserInfo getUserById(int uid)
  {
    return this.userDAO.getById(uid);
  }

  public void updateUser(UserInfo user)
  {
    UserInfo userInfo = this.userDAO.getById(user.getId());
    userInfo.setDepartment(user.getDepartment());
    userInfo.setEmail(user.getEmail());
    userInfo.setCcEmail(user.getCcEmail());
    //如果密码没有发生变更，则不修改；如果发生变更，则使用md5加密
    if(!StringUtils.equals(userInfo.getPassword(), user.getPassword())) {
      userInfo.setPassword(Md5Util.md5(user.getPassword()));
    }
    userInfo.setProjectGroup(user.getProjectGroup());
    userInfo.setGroupId(user.getGroupId());
    this.userDAO.update(userInfo);
  }

  public List<UserInfo> getByQueryBean(UserQueryBean queryBean, Page page) {
    return this.userDAO.getByQueryBean(queryBean, page);
  }

  public List<UserInfo> getByQueryBean(UserQueryBean queryBean) {
    return this.userDAO.getByQueryBean(queryBean);
  }

  public List<UserInfo> getByDepartment(String department) {
    return this.userDAO.getByDepartment(department);
  }


  public void changeUserState(int uid, int state) {
    UserInfo user = getUserById(uid);
    user.setStatus(state);
    this.userDAO.update(user);
  }

  /**
   * 检查当前需要分析的projectName是否属于当前登陆用户的可见范围
   * @param projectName
   * @param submitUsers
   */
    public boolean checkProjectAndUsers(String projectName, List<UserInfo> submitUsers) {
      return userDAO.checkProjectAndUsers(projectName, submitUsers);
    }
}