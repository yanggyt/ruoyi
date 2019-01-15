package com.ruoyi.vip.service;

import com.ruoyi.vip.domain.VipUserCertificate;
import java.util.List;
import com.ruoyi.framework.web.base.AbstractBaseService;
/**
 * 我的订单 服务层
 * 
 * @author zhujj
 * @date 2019-01-15
 */
public interface IVipUserCertificateService extends AbstractBaseService<VipUserCertificate>
{
	/**
     * 查询我的订单分页列表
     *
     * @param vipUserCertificate 我的订单信息
     * @return 我的订单集合
     */
	public List<VipUserCertificate> selectVipUserCertificatePage(VipUserCertificate vipUserCertificate);
    /**
     * 查询我的订单列表
     *
     * @param vipUserCertificate 我的订单信息
     * @return 我的订单集合
     */
    public List<VipUserCertificate> selectVipUserCertificateList(VipUserCertificate vipUserCertificate);

	
}
