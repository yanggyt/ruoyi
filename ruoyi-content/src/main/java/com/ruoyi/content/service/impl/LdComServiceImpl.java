package com.ruoyi.content.service.impl;

import com.ruoyi.content.domain.LdComParty;
import com.ruoyi.content.domain.LdComPartyExample;
import com.ruoyi.content.mapper.LdComPartyMapper;
import com.ruoyi.content.service.LdComService;
import com.ruoyi.content.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LdComServiceImpl implements LdComService {

    private static final Logger LOGGER = LoggerFactory.getLogger(LdComServiceImpl.class);


    @Autowired
    private LdComPartyMapper ldComPartyMapper;

    @Override
    public List<LdComParty> queryldcomList() {
        LOGGER.info("获取公司机构编码及企业号部门编码信息业务层方法开始");
        String companyId = "1";// 公司id
        String branchId = "86";//机构编码
        LdComPartyExample example = new LdComPartyExample();
        example.setOrderByClause("COMCODE ASC");
        example.createCriteria().andCompanyIdEqualTo(companyId).andComcodeLike("%" + branchId + "%");
        List<LdComParty> ldComPartylist = ldComPartyMapper.selectByExample(example);
        LOGGER.info("机构编码与企业号部门编码信息ldComPartylist【{}】", JsonUtil.objectToJackson(ldComPartylist));
        LOGGER.info("获取公司机构编码及企业号部门编码信息业务层方法结束");
        return ldComPartylist;
    }

}
