package com.ruoyi.template.service.impl;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ruoyi.common.core.text.Convert;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.system.domain.SysDictData;
import com.ruoyi.system.mapper.SysDictDataMapper;
import com.ruoyi.template.domain.TmplSwitch;
import com.ruoyi.template.domain.TmplSwitchPort;
import com.ruoyi.template.mapper.TmplSwitchMapper;
import com.ruoyi.template.mapper.TmplSwitchPortMapper;
import com.ruoyi.template.service.ITmplSwitchService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * 交换机模板 服务层实现
 *
 * @author TP
 * @date 2019-06-12
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TmplSwitchServiceImpl implements ITmplSwitchService {
    @Autowired
    private TmplSwitchMapper tmplSwitchMapper;
    @Autowired
    private TmplSwitchPortMapper tmplSwitchPortMapper;
    @Autowired
    private SysDictDataMapper sysDictDataMapper;

    /**
     * 查询交换机模板信息
     *
     * @param switchId 交换机模板ID
     * @return 交换机模板信息
     */
    @Override
    public TmplSwitch selectTmplSwitchById(Integer switchId) {
        return tmplSwitchMapper.selectTmplSwitchById(switchId);
    }

    /**
     * 查询交换机模板列表
     *
     * @param tmplSwitch 交换机模板信息
     * @return 交换机模板集合
     */
    @Override
    public List<TmplSwitch> selectTmplSwitchList(TmplSwitch tmplSwitch) {
        return tmplSwitchMapper.selectTmplSwitchList(tmplSwitch);
    }

    /**
     * 新增交换机模板
     *
     * @param tmplSwitch 交换机模板信息
     * @return 结果
     */
    @Override
    public int insertTmplSwitch(TmplSwitch tmplSwitch) {
        JSONArray jsonArray = JSONArray.parseArray(tmplSwitch.getForeignKeyInfo());
        int affectRow = tmplSwitchMapper.insertTmplSwitch(tmplSwitch);
        if (affectRow > 0) {
            List<TmplSwitchPort> list = new ArrayList<>();
            jsonArray.forEach((i) -> {
                JSONObject jsonObject = (JSONObject) i;
                String valueStr = jsonObject.getString("value");
                int num = 0;
                if (StringUtils.isNotBlank(valueStr) && (num = Convert.toInt(valueStr)) > 0) {
                    SysDictData dictData = sysDictDataMapper
                            .selectDictDataById(Convert.toLong(jsonObject.getString("id")));
                    TmplSwitchPort tmplSwitchPort = new TmplSwitchPort();
                    tmplSwitchPort.setSwitchId(tmplSwitch.getSwitchId());
                    tmplSwitchPort.setSwitchPortType(Convert.toInt(dictData.getDictCode()));
                    tmplSwitchPort.setSwitchPortNum(num);
                    list.add(tmplSwitchPort);
                }
            });
            int switchPorts = list.size() > 0 ? tmplSwitchPortMapper.batchTmplSwitchPort(list) : 0;
            if (switchPorts == 0) {
                throw new BusinessException("至少输入一个端口数量");
            }
        }
        return affectRow;
    }

    /**
     * 修改交换机模板
     *
     * @param tmplSwitch 交换机模板信息
     * @return 结果
     */
    @Override
    public int updateTmplSwitch(TmplSwitch tmplSwitch) {
        return tmplSwitchMapper.updateTmplSwitch(tmplSwitch);
    }

    /**
     * 删除交换机模板对象
     *
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteTmplSwitchByIds(String ids) {
        return tmplSwitchMapper.deleteTmplSwitchByIds(Convert.toStrArray(ids));
    }

}
