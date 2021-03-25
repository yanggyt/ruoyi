package com.ruoyi.content.service.impl;

import com.ruoyi.content.domain.CmsUserRole;
import com.ruoyi.content.domain.CmsUserRoleExample;
import com.ruoyi.content.exception.ParameterException;
import com.ruoyi.content.mapper.CmsUserRoleMapper;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.CmsUserRoleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class CmsUserRoleServiceImpl implements CmsUserRoleService {

    private static Logger logger = LoggerFactory.getLogger(CmsUserRoleService.class);

    @Autowired
    private CmsUserRoleMapper cmsUserRoleMapper;

    /**
     * 添加对应关系
     */
    @Override
    public Message insertUserRole(String names, String email) {
        logger.info("添加对应关系的业务层方法开始！");
        logger.info("添加对应关系的业务层方法拿到参数names【{}】,email【{}】", names, email);
        Message msg = new Message();
        //获取登录人信息
        String roleLog = "o43yZt9cXHtRwvNF8ZdpGw9r0RGI";
        if (StringUtils.isBlank(names) || StringUtils.isBlank(email)) {
            msg.setInfo("添加对应关系失败！");
            msg.setResult(false);
            logger.info("添加对应关系失败，缺少参数");
            return msg;
        }
        try {
            //循环插入对应关系
            String[] arrId = names.split(",");
            List<?> list = queryUserRole("", email);
            if (list.size() > 0) {// 关系存在，修改
                CmsUserRoleExample example = new CmsUserRoleExample();
                CmsUserRoleExample.Criteria criteria = example.createCriteria();
                criteria.andAuthorityEmailEqualTo(email);
                cmsUserRoleMapper.deleteByExample(example);
            }
            for (String aId : arrId) {
                if (StringUtils.isNotBlank(aId)) {
                    CmsUserRole record = new CmsUserRole();
                    SimpleDateFormat data = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
                    SimpleDateFormat time = new SimpleDateFormat("HH:mm:ss");// 设置时间格式
                    // 添加对应关系之前先判断对应关系是否已经存在，不存在插入，存在更新
                    record.setAuthorityEmail(email);
                    record.setAuthorityName(aId);
                    record.setCreateDate(data.format(new Date()));
                    record.setCreateTime(time.format(new Date()));
                    record.setCreateUser(roleLog);
                    cmsUserRoleMapper.insert(record);
                    msg.setInfo("添加成功！");
                    msg.setResult(true);
                    logger.info("添加成功！");
                }
            }
        } catch (Exception e) {
            logger.info("对应关系创建失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("对应关系创建失败！");
            msg.setResult(false);
        }
        logger.info("添加对应关系的业务层方法结束！");
        return msg;
    }

    /**
     * 根据主键删除对应关系
     */
    @Override
    public Message delUserRole(String id) {
        logger.info("删除对应关系的业务层方法开始！");
        logger.info("删除对应关系的业务层方法拿到参数id【{}】", id);
        Message msg = new Message();
        if (StringUtils.isBlank(id)) {
            msg.setInfo("删除失败！");
            msg.setResult(false);
            logger.info("删除对应关系失败，缺少参数");
            throw new ParameterException("删除对应关系失败，缺少参数");
        }
        try {
            cmsUserRoleMapper.deleteByPrimaryKey(Integer.parseInt(id));
            msg.setInfo("删除成功！");
            msg.setResult(true);
            logger.info("删除对应关系成功！");
        } catch (Exception e) {
            logger.info("删除对应关系失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("删除对应关系失败！");
            msg.setResult(false);
        }
        logger.info("删除对应关系的业务层方法结束！");
        return msg;
    }

    /**
     * 查询对应关系，添加或者删除对应关系之前先确定此关系是否存在
     */
    @Override
    public List<?> queryUserRole(String name, String email) {
        logger.info("查询对应关系的业务层方法开始！");
        logger.info("查询对应关系的业务层方法拿到参数name【{}】，email【{}】", name, email);
        if (StringUtils.isBlank(name) && StringUtils.isBlank(email)) {
            logger.info("查询对应关系失败，缺少参数");
            throw new ParameterException("查询对应关系失败，缺少参数");
        }
        List<CmsUserRole> list = null;
        try {
            CmsUserRoleExample example = new CmsUserRoleExample();
            CmsUserRoleExample.Criteria criteria = example.createCriteria();
            if (name != "") {
                criteria.andAuthorityNameEqualTo(name);
            }
            if (email != "") {
                criteria.andAuthorityEmailEqualTo(email);
            }
            list = cmsUserRoleMapper.selectByExample(example);
        } catch (Exception e) {
            logger.info("查询对应关系失败【{}】", e.getMessage());
            e.printStackTrace();
        }
        logger.info("查询对应关系的业务层方法结束！");
        return list;
    }

}
