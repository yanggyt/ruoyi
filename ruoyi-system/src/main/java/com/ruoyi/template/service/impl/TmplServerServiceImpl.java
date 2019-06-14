package com.ruoyi.template.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.template.domain.TmplServer;
import com.ruoyi.template.domain.TmplServerNetcard;
import com.ruoyi.template.mapper.TmplServerMapper;
import com.ruoyi.template.mapper.TmplServerNetcardMapper;
import com.ruoyi.template.service.ITmplServerService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
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
    @Autowired
    private TmplServerNetcardMapper tmplServerNetcardMapper;
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

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
        return saveOrUpdate(tmplServer);
    }

    private int saveOrUpdate(TmplServer tmplServer) {
        JSONArray jsonArray = JSONArray.parseArray(tmplServer.getForeignKeyInfo());
        int affectRow = tmplServerMapper.selectTmplServerById(tmplServer.getServerId()) != null
                ? tmplServerMapper.updateTmplServer(tmplServer)
                : tmplServerMapper.insertTmplServer(tmplServer);
        if (affectRow > 0) {
            List<TmplServerNetcard> list = new ArrayList<>();
            jsonArray.forEach((i) -> {
                JSONObject jsonObject = (JSONObject) i;
                String valueStr = jsonObject.getString("value");
                int num = 0;
                if (StringUtils.isNotBlank(valueStr) && (num = Convert.toInt(valueStr)) > 0) {
                    SysDictData dictData = sysDictDataMapper
                            .selectDictDataById(Convert.toLong(jsonObject.getString("id")));
                    TmplServerNetcard tmplServerNetcard = new TmplServerNetcard();
                    tmplServerNetcard.setServerId(tmplServer.getServerId());
                    tmplServerNetcard.setServerNetcardType(Convert.toLong(dictData.getDictCode()));
                    tmplServerNetcard.setServerNetcardNum(num);
                    list.add(tmplServerNetcard);
                }
            });
            int serverNetcardNums = list.size() > 0 ? tmplServerNetcardMapper.batchTmplServerNetcard(list) : 0;
            if (serverNetcardNums == 0) {
                throw new BusinessException("至少输入一个网卡数量");
            }
        }
        return affectRow;
    }

    /**
     * 修改交换机模板
     *
     * @param tmplServer 服务器模板信息
     * @return 更新后数据库中存在的值个数
     */
    @Override
    public int updateTmplServer(TmplServer tmplServer) {
        //1.清空数据库中已存的值
        tmplServerNetcardMapper.deleteTmplServerNetcardByServerId(tmplServer.getServerId());
        //2.更新主表
        return insertTmplServer(tmplServer);
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
