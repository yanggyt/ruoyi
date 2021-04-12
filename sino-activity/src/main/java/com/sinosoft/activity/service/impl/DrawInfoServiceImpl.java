package com.sinosoft.activity.service.impl;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import com.sinosoft.activity.domain.*;
import com.sinosoft.activity.mapper.*;
import com.sinosoft.activity.service.IDrawInfoService;
import com.sinosoft.activity.vo.ActVO;
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
        drawInfoMapper.deleteDrawInfoByIds(Convert.toStrArray(ids));
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
        int i = drawRuleMapper.deleteDrawRuleByIdCode(Convert.toStrArray(policyEndorseNos));
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

    @Override
    public int updateActVO(ActVO vo) {
        Date date = new Date();

        DrawInfo drawInfo = vo.getDrawInfo();
        drawInfo.setLASTUPDATETIMESTAMP(date);

        //修改抽奖活动管理对象
        String drawcode = drawInfo.getDRAWCODE();
        drawInfoMapper.updateDrawInfo(drawInfo);

        //修改活动配置
        ActConfig actConfig = vo.getActConfig();
        actConfig.setUpdateTime(date);
        actConfig.setActCode(drawcode);
        actConfigMapper.updateActConfig(actConfig);

        //修改活动展示内容配置
        ActPageConfigGuide actPageConfigGuide = vo.getActPageConfigGuide();
        actPageConfigGuide.setUpdateTime(date);
        actPageConfigGuide.setActCode(drawcode);
        actPageConfigGuideMapper.updateActPageConfigGuide(actPageConfigGuide);


        //修改活动收集配置
        ActPageConfigSubscribe actPageConfigSubscribe = vo.getActPageConfigSubscribe();
        actPageConfigSubscribe.setUpdateTime(date);
        actPageConfigSubscribe.setActCode(drawcode);
        actPageConfigSubscribeMapper.updateActPageConfigSubscribe(actPageConfigSubscribe);

        //修改活动用户信息
        ActPageConfigUserinfo actPageConfigUserinfo = vo.getActPageConfigUserinfo();
        actPageConfigUserinfo.setUpdateTime(date);
        actPageConfigUserinfo.setActCode(drawcode);
        actPageConfigUserinfoMapper.updateActPageConfigUserinfo(actPageConfigUserinfo);

        //修改查询抽奖活动管理对象
        DrawRule drawRule = vo.getDrawRule();
        drawRule.setDRAWCODE(drawcode);
        drawRule.setLASTUPDATETIMESTAMP(date);
        int i = drawRuleMapper.updateDrawRule(drawRule);
        return i;
    }
}
