package com.ruoyi.dfm.service;

import com.ruoyi.dfm.dao.ProjectDAO;
import com.ruoyi.dfm.dao.UserDAO;
import com.ruoyi.dfm.pojo.*;
import com.ruoyi.dfm.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class ProjectService
{
  private static final Logger logger = LoggerFactory.getLogger(ProjectService.class);
  @Autowired
  private ProjectDAO projectDAO;
  @Autowired
  private UserDAO userDAO;
  @Autowired
  private FileService fileService;

  public void addProject(UserInfo loginUser, MultipartFile pcbFile, MultipartFile bomFile, Project project) throws Exception
  {
    try
    {
//      String dir = ((UserInfo)req.getSession().getAttribute("user")).getUsername() + "/" +
//        project.getProjectName() + "/" + "Ver" + project.getVersion();
      String dir = loginUser.getUsername() + "/" +
              project.getProjectName() + "/" + "Ver" + project.getVersion();

      MultipartFile[] multipartFiles = new MultipartFile[]{pcbFile, bomFile};
      List fileList = new ArrayList();
      this.fileService.savePhysicFile(loginUser, multipartFiles, fileList, dir, false , null);
      for (int i = 0; i < fileList.size(); ++i)
      {
        FileInfo file = (FileInfo)fileList.get(i);
        if ("pcbFile".equals(file.getFieldName()))
        {
          project.setPcbFile(file.getId());
          project.setPcbFileName(file.getFileName());
        }
        else {
          if (!("bomFile".equals(file.getFieldName())))
            continue;
          project.setBomFile(file.getId());
          project.setBomFileName(file.getFileName());
        }

      }

      this.projectDAO.add(project);
      createParamFile(dir, project);

      reOrderPriByState("待查", project.getPri());
    } catch (IOException e) {
      logger.error("创建文件失败！", e);
      throw e;
    }
  }

  private void createParamFile(String dir, Project project)
    throws IOException
  {
    String fileName = project.getProjectName() + "_" + project.getVersion() + ".param";
    File file = this.fileService.createFile(dir + "/" + fileName);

    int[] ids = new int[15];
    ids[0] = project.getCheckType();
    ids[1] = project.getPcbType();
    ids[2] = project.getHdiModel();
    ids[3] = project.getPanelModel();
    ids[4] = project.getRailwayPosition();
    ids[5] = project.getViacapLayer();
    ids[6] = project.getAssemblyProcessTop();
    ids[7] = project.getHavePb();
    ids[8] = project.getAssemblyProcessBot();
    ids[9] = project.getSurfaceProcess();
    ids[10] = project.getDirectionTop();
    ids[11] = project.getPrimarySide();
    ids[12] = project.getDirectionBot();
    ids[13] = project.getDirectionBotFs();
    ids[14] = Integer.parseInt(project.getDensity());

    List<Map<String, Object>> attrValues = this.projectDAO.getAttrValueByIds(ids);
    
    UserInfo userInfo = this.userDAO.getById(project.getSubmitUser());
    

    StringBuilder sb = new StringBuilder();

    sb.append("User=" + project.getSubmitUserName());
    sb.append("\r\n");
    sb.append("Email=" + userInfo.getEmail());
    sb.append("\r\n");
    sb.append("CC_EMAIL=" + project.getCCtoOther());
//    sb.append("\r\n");
//    sb.append("CCtoOther=" + project.getCCtoOther());
    sb.append("\r\n");
    sb.append("ReportLanguage=" + project.getReportLanguage());
    sb.append("\r\n");
    sb.append("Version=" + project.getVersion());
    sb.append("\r\n");
    sb.append("LastVersion=" + project.getLastVersion());
    sb.append("\r\n");
    sb.append("Check_Type=" + getValueById(attrValues, project.getCheckType()));
    sb.append("\r\n");

    sb.append("NetlistCheck=" + ((project.getDfmCheck().contains("NetlistCheck")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("Fiducial=" + ((project.getDfmCheck().contains("Fiducial")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("Component=" + ((project.getDfmCheck().contains("Component")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("Padstack=" + ((project.getDfmCheck().contains("Padstack")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("Solderpaste=" + ((project.getDfmCheck().contains("Solderpaste")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("Pin2Pad=" + ((project.getDfmCheck().contains("Pin2Pad")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("Testpoint=" + ((project.getDfmCheck().contains("Testpoint")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("Drill=" + ((project.getDfmCheck().contains("Drill")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("Signal Layer=" + ((project.getDfmCheck().contains("Signal Layer")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("P/G Layer=" + ((project.getDfmCheck().contains("P/G Layer")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("Silk Screen=" + ((project.getDfmCheck().contains("Silk Screen")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("Solder Mask=" + ((project.getDfmCheck().contains("Solder Mask")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("extchk1=" + ((project.getDfmCheck().contains("extchk1")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("extchk2=" + ((project.getDfmCheck().contains("extchk2")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("extchk3=" + ((project.getDfmCheck().contains("extchk3")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("extchk4=" + ((project.getDfmCheck().contains("extchk4")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("extchk5=" + ((project.getDfmCheck().contains("extchk5")) ? 1 : 0));
    sb.append("\r\n");
    sb.append("extchk6=" + ((project.getDfmCheck().contains("extchk6")) ? 1 : 0));
    sb.append("\r\n");
    

    if ((project.getPcbFileName() == null) || ("".equals(project.getPcbFileName())))
    {
      sb.append("PCB_File=");
    }
    else
    {
      FileInfo pcbFile = this.fileService.getById(project.getPcbFile());
      sb.append("PCB_File=" + this.fileService.getRootPath() + pcbFile.getRelaPath() + "/" + project.getPcbFileName());
    }
    sb.append("\r\n");
    if ((project.getBomFileName() == null) || ("".equals(project.getBomFileName())))
    {
      sb.append("BOM_File=");
    }
    else
    {
      FileInfo bomFile = this.fileService.getById(project.getBomFile());
      sb.append("BOM_File=" + this.fileService.getRootPath() + bomFile.getRelaPath() + "/" + project.getBomFileName());
    }
    sb.append("\r\n");
    sb.append("PCB_Type=" + getValueById(attrValues, project.getPcbType()));
    sb.append("\r\n");
    sb.append("Board_Thickness=" + project.getBoardThickness());
    sb.append("\r\n");
    sb.append("Max_Heigh_Top=" + project.getMaxHeightTop());
    sb.append("\r\n");
    sb.append("Max_Heigh_Bot=" + project.getMaxHeightBot());

    sb.append("\r\n");
    sb.append("HDI_Model=" + getValueById(attrValues, project.getHdiModel()));
    sb.append("\r\n");
    sb.append("Panel_Model=" + getValueById(attrValues, project.getPanelModel()));
    sb.append("\r\n");
    sb.append("SubPCB_Num=" + project.getSubPcbNum());
    sb.append("\r\n");
    sb.append("Railway_Position=" + getValueById(attrValues, project.getRailwayPosition()));
    sb.append("\r\n");

    sb.append("Direction_Bot_Fs=" + getValueById(attrValues, project.getDirectionBotFs()));
    sb.append("\r\n");
    sb.append("Have_Pb=" + getValueById(attrValues, project.getHavePb()));
    sb.append("\r\n");
    sb.append("Surface_Process=" + getValueById(attrValues, project.getSurfaceProcess()));
    sb.append("\r\n");
    sb.append("Primary_Side=" + getValueById(attrValues, project.getPrimarySide()));
    sb.append("\r\n");
    sb.append("Assembly_Process_Top=" + getValueById(attrValues, project.getAssemblyProcessTop()));
    sb.append("\r\n");
    sb.append("Assembly_Process_Bot=" + getValueById(attrValues, project.getAssemblyProcessBot()));
    sb.append("\r\n");
    sb.append("Direction_Top=" + getValueById(attrValues, project.getDirectionTop()));
    sb.append("\r\n");
    sb.append("Direction_Bot=" + getValueById(attrValues, project.getDirectionBot()));
    sb.append("\r\n");
    sb.append("Density=" + getValueById(attrValues, Integer.parseInt(project.getDensity())));
    OutputStream os = new FileOutputStream(file);
    os.write(sb.toString().getBytes());
    os.close();
  }

  private String getValueById(List<Map<String, Object>> values, int id)
  {
    String rs = "";
    for (int i = 0; i < values.size(); ++i)
    {
      if (!(((Map)values.get(i)).get("F_ID").equals(Integer.valueOf(id))))
        continue;
      rs = ((Map)values.get(i)).get("F_ATTR_VALUE").toString();
      break;
    }

    return rs;
  }

  /**
   * 结果文件下载查询
   * @param states
   * @param page
   * @param queryBean
   * @return
   */
  public List<Project> getProjectByStates(String[] states, Page page, ProjectQueryBean queryBean)
  {
    return this.projectDAO.getByStates(states, page, queryBean);
  }

  public List<Project> getProjectByStatesOrderUser(String[] states, int uid, Page page, ProjectQueryBean queryBean)
  {
    return this.projectDAO.getByStatesOrderUser(states, uid, page, queryBean);
  }

  /**
   * 结果文件下载查询
   * @param states
   * @param uid
   * @param page
   * @param queryBean
   * @return
   */
  public List<Project> getProjectByStates(String[] states, int[] uid, Page page, ProjectQueryBean queryBean)
  {
    return this.projectDAO.getByStates(states, uid, page, queryBean);
  }

  public Project getLastVersion(int uid, String projectName)
  {
    return this.projectDAO.getLastVersion(uid, projectName);
  }

  public List<Map<String, Object>> getAttrValue(String attrName)
  {
    return this.projectDAO.getAttrValue(attrName);
  }

  public void pauseProject(String[] pids)
  {
    int pri = 10000;
    String state = "暂停";
    for (String pid : pids)
    {
      this.projectDAO.changePri(pid, pri, state);
    }
  }
  
  public void startProject(String[] pids)
  {
    int pri = this.projectDAO.getMaxPri();
    String state = "待查";
    for (String pid : pids)
    {
    	pri++;
      this.projectDAO.changePri(pid, pri, state);
    }
  }

  public void deleteProject(String[] pids)
  {
    try
    {
      for (String pid : pids)
      {
        Project project = this.projectDAO.getById(Integer.parseInt(pid));
        this.projectDAO.delete(pid);
        FileInfo fileInfo = null;

        if (project.getPcbFile() != 0)
        {
          fileInfo = this.fileService.getById(project.getPcbFile());
        }
        else if (project.getBomFile() != 0)
        {
          fileInfo = this.fileService.getById(project.getBomFile());
        }

        this.fileService.deleteFile(project.getPcbFile());
        this.fileService.deleteFile(project.getBomFile());
        String deleteDir = this.fileService.getRootPath() + "\\" + fileInfo.getRelaPath();
        File file = new File(deleteDir);
        if (!(file.exists()))
          continue;
        File[] files = file.listFiles();
        if ((files != null) && (files.length > 0))
        {
          for (File temp : files)
          {
            if (!(temp.exists()))
              continue;
            temp.delete();
          }
        }

        if (file.exists())
        {
          file.delete();
        }

        if ((project.getVersion() > 1) || 
          (!(file.getParentFile().exists())))
          continue;
        file.getParentFile().delete();
      }

    }
    catch (Exception e)
    {
      logger.error("删除项目失败", e);
    }
  }

  public Project getProjectById(int id)
  {
    return this.projectDAO.getById(id);
  }

  public void restoreProject(String pid) {
    int pri = this.projectDAO.getMaxPri();
    if (pri <= 0)
    {
      pri = 1;
    }
    else {
      ++pri;
    }
    String state = "待查";
    this.projectDAO.changePri(pid, pri, state);
  }

  public void setUserDAO(UserDAO userDAO) {
    this.userDAO = userDAO;
  }

  public void changePri(String pid, int[] uids, String change)
  {
    int id = Integer.parseInt(pid);
    Project p1 = this.projectDAO.getById(id);
    Project p2 = this.projectDAO.getByPriState(id, uids, change, "待查");
    this.projectDAO.changePri(p1.getId(), p2.getPri());
    this.projectDAO.changePri(p2.getId(), p1.getPri());
  }

  public Project getByPriState(String change, int pid, int[] uids, String state)
  {
    return this.projectDAO.getByPriState(pid, uids, change, state);
  }

  public void reOrderPriByState(String state, int pri)
  {
    this.projectDAO.reOrderPriByState(state, pri);
  }

  public List<Project> getProjectByUser(int uid) {
    return this.projectDAO.getByUser(uid);
  }

  public void recheck(String[] pids)
  {
    for (String pid : pids)
    {
      int id = Integer.parseInt(pid);

      this.projectDAO.recheck(id, TimeUtil.getNowChar14(), "待查");

      this.projectDAO.deleteStageByProject(id);
    }
  }

  public List<String> getAllServers() {
    return this.projectDAO.getAllServers();
  }

  public Project getProjectByServerState(String server, String state)
  {
    return this.projectDAO.getByServerState(server, state);
  }

  public List<ProjectStage> getStagesByProject(int pid) {
    return this.projectDAO.getStagesByProject(pid);
  }

  public void stopProject(String pid)
  {
    this.projectDAO.stop(pid);
  }
  
  /**
   * 修改项目PreDFMFile字段
   * @param pid
   */
  public void updateProjectPreDFMFile(Integer pid, Integer preDFMFileId, String preDFMFileName) {
	  projectDAO.updateProjectPreDFMFile(pid, preDFMFileId, preDFMFileName);
  }
  
  /**
   * 修改项目PostDFMFile字段
   * @param pid
   */
  public void updateProjectPostDFMFile(Integer pid, Integer postDFMFileId, String postDFMFileName) {
	  projectDAO.updateProjectPostDFMFile(pid, postDFMFileId, postDFMFileName);
  }
  
}