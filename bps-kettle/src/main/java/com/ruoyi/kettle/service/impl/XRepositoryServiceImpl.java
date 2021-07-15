package com.ruoyi.kettle.service.impl;

import java.util.List;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.security.PermissionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ruoyi.kettle.mapper.XRepositoryMapper;
import com.ruoyi.kettle.domain.XRepository;
import com.ruoyi.kettle.service.IXRepositoryService;
import com.ruoyi.common.core.text.Convert;

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
        return xRepositoryMapper.deleteXRepositoryByIds(Convert.toStrArray(ids));
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
        return xRepositoryMapper.deleteXRepositoryById(id);
    }
}
