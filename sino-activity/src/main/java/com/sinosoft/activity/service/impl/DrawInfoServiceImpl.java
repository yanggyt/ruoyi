package com.sinosoft.activity.service.impl;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.sinosoft.activity.domain.DrawInfo;
import com.sinosoft.activity.mapper.*;
import com.sinosoft.activity.service.IDrawInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ruoyi.common.core.text.Convert;

/**
 * 抽奖活动管理对象Service业务层处理
 * 
 * @author xlh
 * @date 2021-03-25
 */
@Service("drawInfo")
public class DrawInfoServiceImpl implements IDrawInfoService
{
    @Autowired
    private DrawInfoMapper drawInfoMapper;
    @Autowired
    private ActConfigMapper actConfigMapper;
    @Autowired
    private ActPageConfigGuideMapper actPageConfigGuideMapper;
    @Autowired
    private ActPageConfigSubscribeMapper actPageConfigSubscribeMapper;
    @Autowired
    private ActPageConfigUserinfoMapper actPageConfigUserinfoMapper;
    @Autowired
    private DrawRuleMapper drawRuleMapper;


    /**
     * 查询抽奖活动管理对象
     * 
     * @param DRAWID 抽奖活动管理对象ID
     * @return 抽奖活动管理对象
     */
    @Override
    public DrawInfo selectDrawInfoById(String DRAWID)
    {
        return drawInfoMapper.selectDrawInfoById(DRAWID);
    }

    /**
     * 查询抽奖活动管理对象列表
     * 
     * @param drawInfo 抽奖活动管理对象
     * @return 抽奖活动管理对象
     */
    @Override
    public List<DrawInfo> selectDrawInfoList(DrawInfo drawInfo)
    {
        return drawInfoMapper.selectDrawInfoList(drawInfo);
    }

    @Override
    public List<DrawInfo> findDrawInfoList() {
        return drawInfoMapper.findDrawInfoList();
    }

    /**
     * 新增抽奖活动管理对象
     * 
     * @param drawInfo 抽奖活动管理对象
     * @return 结果
     */
    @Override
    public int insertDrawInfo(DrawInfo drawInfo)
    {
        return drawInfoMapper.insertDrawInfo(drawInfo);
    }

    /**
     * 修改抽奖活动管理对象
     * 
     * @param drawInfo 抽奖活动管理对象
     * @return 结果
     */
    @Override
    public int updateDrawInfo(DrawInfo drawInfo)
    {
        return drawInfoMapper.updateDrawInfo(drawInfo);
    }

    /**
     * 删除抽奖活动管理及其相关活动配置信息
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    @Override
    public int deleteDrawInfoByIds(String ids)
    {
        DrawInfo drawInfo = new DrawInfo();
        String[] string = ids.split(",");
        List<String> code = Arrays.asList(string);
        drawInfo.setDrawId(code);
        //根据ID查询抽奖活动信息
        List<DrawInfo> drawInfos = drawInfoMapper.selectDrawInfoList(drawInfo);
        //删除活动管理信息
        int i = drawInfoMapper.deleteDrawInfoByIds(Convert.toStrArray(ids));
        List<String> collect = drawInfos.stream().map(DrawInfo::getDRAWCODE).collect(Collectors.toList());
        String policyEndorseNos = String.join(",",collect);

        //根据活动代码删除活动配置信息
        actConfigMapper.deleteActConfigByCode(Convert.toStrArray(policyEndorseNos));
        //根据活动代码删除活动展示内容配置信息
        actPageConfigGuideMapper.deleteActPageConfigGuideByCode(Convert.toStrArray(policyEndorseNos));
        //根据活动代码删除活动配置信息
        actPageConfigSubscribeMapper.deleteActPageConfigSubscribeByCode(Convert.toStrArray(policyEndorseNos));
        //根据活动代码删除活动收集配置信息
        actPageConfigUserinfoMapper.deleteActPageConfigUserinfoByCode(Convert.toStrArray(policyEndorseNos));
        //根据活动代码删除抽奖活动管理信息
        drawRuleMapper.deleteDrawRuleByIdCode(Convert.toStrArray(policyEndorseNos));
        return i;
    }

    /**
     * 删除抽奖活动管理对象信息
     * 
     * @param DRAWID 抽奖活动管理对象ID
     * @return 结果
     */
    @Override
    public int deleteDrawInfoById(String DRAWID)
    {
        return drawInfoMapper.deleteDrawInfoById(DRAWID);
    }
}
