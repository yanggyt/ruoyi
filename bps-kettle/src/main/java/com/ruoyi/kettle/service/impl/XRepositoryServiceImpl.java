package com.ruoyi.kettle.service.impl;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.PermissionUtils;
import com.ruoyi.kettle.repo.RepoTree;
import com.ruoyi.kettle.repo.RepositoryTree;
import com.ruoyi.kettle.repo.XRepoManager;
import com.ruoyi.kettle.tools.KettleUtil_2;
import org.pentaho.di.core.exception.KettleException;
import org.pentaho.di.repository.RepositoryDirectoryInterface;
import org.pentaho.di.repository.filerep.KettleFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kettle.mapper.XRepositoryMapper;
import com.ruoyi.kettle.domain.XRepository;
import com.ruoyi.kettle.service.IXRepositoryService;
import com.ruoyi.common.core.text.Convert;
import org.springframework.util.CollectionUtils;

/**
 * 资源库Service业务层处理
 * 
 * @author kone
 * @date 2021-07-12
 */
@Service
public class XRepositoryServiceImpl implements IXRepositoryService 
{
    @Autowired
    private XRepositoryMapper xRepositoryMapper;

    /**
     * 查询资源库
     * 
     * @param id 资源库ID
     * @return 资源库
     */
    @Override
    public XRepository selectXRepositoryById(Long id)
    {
        return xRepositoryMapper.selectXRepositoryById(id);
    }

    /**
     * 查询资源库列表
     * 
     * @param xRepository 资源库
     * @return 资源库
     */
    @Override
    public List<XRepository> selectXRepositoryList(XRepository xRepository)
    {
        return xRepositoryMapper.selectXRepositoryList(xRepository);
    }

    /**
     * 新增资源库
     * 
     * @param xRepository 资源库
     * @return 结果
     */
    @Override
    public int insertXRepository(XRepository xRepository)
    {
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        xRepository.setCreatedBy(userName);
        xRepository.setUpdateBy(userName);
        xRepository.setType("File");
        return xRepositoryMapper.insertXRepository(xRepository);
    }

    /**
     * 修改资源库
     * 
     * @param xRepository 资源库
     * @return 结果
     */
    @Override
    public int updateXRepository(XRepository xRepository)
    {
        String userName = (String) PermissionUtils.getPrincipalProperty("userName");
        xRepository.setUpdateTime(DateUtils.getNowDate());
        xRepository.setUpdateBy(userName);

        return xRepositoryMapper.updateXRepository(xRepository);
    }

    /**
     * 删除资源库对象
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteXRepositoryByIds(String ids)
    {
        return xRepositoryMapper.updateIsDelBatch(Convert.toStrArray(ids));
       // return xRepositoryMapper.deleteXRepositoryByIds(Convert.toStrArray(ids));
    }

    /**
     * 删除资源库信息
     * 
     * @param id 资源库ID
     * @return 结果
     */
    @Override
    public int deleteXRepositoryById(Long id)
    {
        return xRepositoryMapper.updateIsDel(id);
        //return xRepositoryMapper.deleteXRepositoryById(id);
    }

    @Override
    public List<RepoTree> selectRepoRoot(XRepository repository) {
        List<XRepository> repositoryList = xRepositoryMapper.selectXRepositoryList(repository);
        List<RepoTree> ztrees = initZtree2(repositoryList);
        return ztrees;
    }

    @Override
    public List<RepoTree> selectRepoTree(Long id) {
        XRepository xrs = xRepositoryMapper.selectXRepositoryById(id);
        List<RepositoryTree> repositoryTrees = getRepoTress(xrs);
        List<RepositoryTree> subTrees = new ArrayList<>();
        String type=null;
        String pId=String.valueOf(xrs.getId());
//        try
//        {

//            repositoryTrees.forEach(item -> {
//                if (item.getParent().equals(pId)) {
//                    if (item.isLasted()) {
//                        if (!StringUtils.isEmpty(type)) {
//                            if (item.getType().indexOf(type) != -1) {
//                                subTrees.add(item);
//                            }
//                        } else {
//                            subTrees.add(item);
//                        }
//
//                    } else {
//                        subTrees.add(item);
//                    }
//                }});
//        }catch (Exception e)
//        {
//            StringWriter sw = new StringWriter();
//            e.printStackTrace(new PrintWriter(sw));
//            //throw new UserDefinedException(BaseResultConstant.UNKNOW_EXCEPTION, sw.toString().substring(0, 800));
//        }

        List<RepoTree> ztrees = initZtree(repositoryTrees,String.valueOf(id));
        return ztrees;
    }


    public List<RepoTree> initZtree(List<RepositoryTree> repositoryList ,String parentId)
    {

        List<RepoTree> ztrees = new ArrayList<RepoTree>();
        for (RepositoryTree rt : repositoryList) {
            if(rt.getId().equals(parentId) || rt.getText().equals("/")){
                continue;
            }
            RepoTree ztree = new RepoTree();
                ztree.setId(rt.getId());
                ztree.setpId(rt.getParent());
                ztree.setName(rt.getText());
                ztree.setTitle(rt.getPath());
                ztrees.add(ztree);
        }
        return ztrees;
    }
    public List<RepoTree> initZtree2(List<XRepository> repositoryList )
    {

        List<RepoTree> ztrees = new ArrayList<RepoTree>();
        for (XRepository rt : repositoryList)
        {
            RepoTree ztree = new RepoTree();
            ztree.setId(String.valueOf(rt.getId()));
            ztree.setpId(" ");
            ztree.setName(rt.getRepoName());
            ztree.setTitle(rt.getBaseDir());
            ztrees.add(ztree);
        }
        return ztrees;
    }
    private List<RepositoryTree> getRepoTress(XRepository xr) {
        List<RepositoryTree> repositoryTrees = new ArrayList<>();
        List<XRepository> xRepositoryList =xRepositoryMapper.selectXRepositoryList(xr);

        if (!CollectionUtils.isEmpty(xRepositoryList)) {
            xRepositoryList.forEach(item -> {
                List<RepositoryTree> tmpRepositoryList = new ArrayList<>();
                String type = item.getType();

                if (type.equalsIgnoreCase("File")) {
                    // 文件库
                    String baseDir = item.getBaseDir();

                    try {
                        KettleFileRepository repository = (KettleFileRepository) KettleUtil_2.
                                conFileRep(String.valueOf(item.getId()), item.getRepoName(), baseDir);
                        XRepoManager.getAllDirectoryTreeList(String.valueOf(item.getId()), repository, "/", tmpRepositoryList);
                        if (tmpRepositoryList.size() > 0) {
                            RepositoryDirectoryInterface rDirectory = repository.loadRepositoryDirectoryTree().findDirectory("/");
                            RepositoryTree repositoryTree = new RepositoryTree();
                            repositoryTree.setParent(String.valueOf(item.getId()));
                            repositoryTree.setId(item.getRepoId() + "@" + rDirectory.getObjectId().toString());
                            //repositoryTree.setId(String.valueOf(item.getId()));

                            repositoryTree.setText(rDirectory.getName().equals("\\/") ? "基础路径" : rDirectory.getName());
                            repositoryTree.setLasted(false);
                            repositoryTree.setType("tree");
                            repositoryTree.setPath("file");
                            tmpRepositoryList.add(repositoryTree);
                        }

                    } catch (KettleException e) {
                        StringWriter sw = new StringWriter();
                        e.printStackTrace(new PrintWriter(sw));
                       // throw new UserDefinedException(BaseResultConstant.UNKNOW_EXCEPTION, sw.toString().substring(0, 800));
                    }
                }


//                RepositoryTree repositoryTree;
//                repositoryTree = new RepositoryTree();
//                repositoryTree.setParent("99");
//                repositoryTree.setId(String.valueOf(item.getId()));
//                repositoryTree.setText(item.getRepoName());
//                repositoryTree.setLasted(false);
//                repositoryTree.setType(type);
//                repositoryTree.setPath("repo");
//                tmpRepositoryList.add(repositoryTree);
                repositoryTrees.addAll(tmpRepositoryList);
            });
        }

        return repositoryTrees;
    }

}
