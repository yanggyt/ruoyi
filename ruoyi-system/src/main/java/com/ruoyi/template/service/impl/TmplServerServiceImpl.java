package com.ruoyi.template.service.impl;

import com.ruoyi.common.core.text.Convert;
import com.ruoyi.template.domain.TmplServer;
import com.ruoyi.template.mapper.TmplServerMapper;
import com.ruoyi.template.service.ITmplServerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 服务器模板 服务层实现
 *
 * @author TP
 * @date 2019-06-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TmplServerServiceImpl implements ITmplServerService {
    @Autowired
    private TmplServerMapper tmplServerMapper;

    /**
     * 查询服务器模板信息
     *
     * @param serverId 服务器模板ID
     * @return 服务器模板信息
     */
    @Override
    public TmplServer selectTmplServerById(Integer serverId) {
        return tmplServerMapper.selectTmplServerById(serverId);
    }

    /**
     * 查询服务器模板列表
     *
     * @param tmplServer 服务器模板信息
     * @return 服务器模板集合
     */
    @Override
    public List<TmplServer> selectTmplServerList(TmplServer tmplServer) {
        return tmplServerMapper.selectTmplServerList(tmplServer);
    }

    /**
     * 新增服务器模板
     *
     * @param tmplServer 服务器模板信息
     * @return 结果
     */
    @Override
    public int insertTmplServer(TmplServer tmplServer) {
        return tmplServerMapper.insertTmplServer(tmplServer);
    }

    /**
     * 修改服务器模板
     *
     * @param tmplServer 服务器模板信息
     * @return 结果
     */
    @Override
    public int updateTmplServer(TmplServer tmplServer) {
        return tmplServerMapper.updateTmplServer(tmplServer);
    }

    /**
     * 删除服务器模板对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTmplServerByIds(String ids) {
        return tmplServerMapper.deleteTmplServerByIds(Convert.toStrArray(ids));
    }

}
