package com.ruoyi.template.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.template.domain.TmplServer;
import com.ruoyi.template.domain.TmplServerDisk;
import com.ruoyi.template.domain.TmplServerMemory;
import com.ruoyi.template.domain.TmplServerNetcard;
import com.ruoyi.template.mapper.TmplServerDiskMapper;
import com.ruoyi.template.mapper.TmplServerMapper;
import com.ruoyi.template.mapper.TmplServerMemoryMapper;
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
    private TmplServerDiskMapper tmplServerDiskMapper;
    @Autowired
    private TmplServerMemoryMapper tmplServerMemoryMapper;
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
        int affectRow = tmplServerMapper.selectTmplServerById(tmplServer.getServerId()) != null
                ? tmplServerMapper.updateTmplServer(tmplServer)
                : tmplServerMapper.insertTmplServer(tmplServer);
        if (affectRow > 0) {
            JSONObject jsonObject = JSONObject.parseObject(tmplServer.getForeignKeyInfo());
            if (jsonObject != null) {
                //保存网卡信息
                if (jsonObject.containsKey("serverNetcards")) {
                    saveServerNetcard(tmplServer.getServerId(), jsonObject.getJSONArray("serverNetcards"));
                }
                //保存硬盘信息
                if (jsonObject.containsKey("serverDisks")) {
                    saveServerDisk(tmplServer.getServerId(), jsonObject.getJSONArray("serverDisks"));
                }
                //保存内存信息
                if (jsonObject.containsKey("serverMemorys")) {
                    saveServerMemory(tmplServer.getServerId(), jsonObject.getJSONArray("serverMemorys"));
                }
            }
        }
        return affectRow;
    }

    /**
     * 新增网卡
     *
     * @param id
     * @param jsonArray
     */
    private void saveServerNetcard(Integer id, JSONArray jsonArray) {
        List<TmplServerNetcard> list = new ArrayList<>();
        jsonArray.forEach((i) -> {
            JSONObject jsonObject = (JSONObject) i;
            String valueStr = jsonObject.getString("value");
            int num = 0;
            if (StringUtils.isNotBlank(valueStr) && (num = Convert.toInt(valueStr)) > 0) {
                SysDictData dictData = sysDictDataMapper.selectDictDataById(Convert.toLong(jsonObject.getString("id")));
                TmplServerNetcard tmplServerNetcard = new TmplServerNetcard();
                tmplServerNetcard.setServerId(id);
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

    /**
     * 新增硬盘
     *
     * @param id
     * @param jsonArray
     */
    private void saveServerDisk(Integer id, JSONArray jsonArray) {
        List<TmplServerDisk> list = new ArrayList<>();
        jsonArray.forEach((i) -> {
            JSONObject jsonObject = (JSONObject) i;
            String valueStr = jsonObject.getString("value");
            int num = 0;
            if (StringUtils.isNotBlank(valueStr) && (num = Convert.toInt(valueStr)) > 0) {
                SysDictData dictData = sysDictDataMapper.selectDictDataById(Convert.toLong(jsonObject.getString("id")));
                TmplServerDisk tmplServerDisk = new TmplServerDisk();
                tmplServerDisk.setServerId(id);
                tmplServerDisk.setServerDiskType(Convert.toLong(dictData.getDictCode()));
                tmplServerDisk.setServerDiskNum(num);
                list.add(tmplServerDisk);
            }
        });
        int serverDiskNums = list.size() > 0 ? tmplServerDiskMapper.batchTmplServerDisk(list) : 0;
        if (serverDiskNums == 0) {
            throw new BusinessException("至少输入一个硬盘数量");
        }
    }

    /**
     * 新增内存
     *
     * @param id
     * @param jsonArray
     */
    private void saveServerMemory(Integer id, JSONArray jsonArray) {
        List<TmplServerMemory> list = new ArrayList<>();
        jsonArray.forEach((i) -> {
            JSONObject jsonObject = (JSONObject) i;
            String valueStr = jsonObject.getString("value");
            int num = 0;
            if (StringUtils.isNotBlank(valueStr) && (num = Convert.toInt(valueStr)) > 0) {
                SysDictData dictData = sysDictDataMapper.selectDictDataById(Convert.toLong(jsonObject.getString("id")));
                TmplServerMemory tmplServerMemory = new TmplServerMemory();
                tmplServerMemory.setServerId(id);
                tmplServerMemory.setServerMemoryType(Convert.toLong(dictData.getDictCode()));
                tmplServerMemory.setServerMemoryNum(num);
                list.add(tmplServerMemory);
            }
        });
        int serverDiskNums = list.size() > 0 ? tmplServerMemoryMapper.batchTmplServerMemory(list) : 0;
        if (serverDiskNums == 0) {
            throw new BusinessException("至少输入一个内存数量");
        }
    }

    /**
     * 修改交换机模板
     *
     * @param tmplServer 服务器模板信息
     * @return 更新后数据库中存在的值个数
     */
    @Override
    public int updateTmplServer(TmplServer tmplServer) {
        // 1.清空数据库中已存的值
        tmplServerNetcardMapper.deleteTmplServerNetcardByServerId(tmplServer.getServerId());
        // 2.更新主表
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
