package com.ruoyi.content.service.impl;

import com.ruoyi.content.domain.CmsSysUser;
import com.ruoyi.content.mapper.CmsSysUserMapper;
import com.ruoyi.content.service.WmsOperatorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WmsUserOperatorImpl implements WmsOperatorService {
    @Autowired
    private CmsSysUserMapper cmsSysUserMapper;
    private final static Logger logger = LoggerFactory.getLogger(WmsUserOperatorImpl.class);

    @Override
    public CmsSysUser findTblWmsOperatorByEmail(String email) {
        return cmsSysUserMapper.selectByPrimaryKey(email);
    }

}
