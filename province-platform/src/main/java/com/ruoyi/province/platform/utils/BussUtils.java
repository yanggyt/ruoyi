package com.ruoyi.province.platform.utils;

import com.github.pagehelper.util.StringUtil;
import com.ruoyi.common.core.domain.entity.SysMenu;
import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.province.platform.domain.SysBillNo;
import com.ruoyi.province.platform.mapper.SysBillNoMapper;
import com.ruoyi.system.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * 单据号管理工具类
 * @author pandong
 *
 */
@Component
public class BussUtils {

	@Autowired
	private SysMenuMapper menuMapper;

	@Autowired
	private SysBillNoMapper sysBillNoMapper;

	private  static  BussUtils bussUtils ;


	@PostConstruct //通过@PostConstruct实现初始化bean之前进行的操作
	public void init() {
		bussUtils = this;
		bussUtils.sysBillNoMapper = this.sysBillNoMapper;
	}
	/***
	 * 获取单据标识下一个单据号
	 * @param billNoType
	 * @return
	 */
	public static String nextValue( String billNoType ){
		
		if ( StringUtils.isNull(bussUtils) ){
			throw new BusinessException("缺少必须参数：单据标识");
		}
		String value = ""; //
		synchronized (BussUtils.class) {

			// 取得单据前缀
			SysMenu menu = bussUtils.menuMapper.selectMenusByEntityId(billNoType) ;


			// 更新迭加序号
			SysBillNo newbillNo = new SysBillNo();
			newbillNo.setFperiod(DateUtils.getPeriod());
			newbillNo.setBillName(billNoType);
			newbillNo.setIterationValue("1");
			newbillNo.setNextValue("1");

			int resi = bussUtils.sysBillNoMapper.insertDuplicateByPeriod( newbillNo );

			newbillNo = bussUtils.sysBillNoMapper.selectSysBillNoByPeriod( newbillNo );

			value = newbillNo.getIterationValue() ;
			if (StringUtil.isEmpty(value) || value.equals(""))
				value = "0" ;
			value = addZeroForNum(value,5);
				//value = String.format("%05d", value);

			//00001+表单编号BA02+六位数的日期编号+四位数的单据序号
			//String.format("%05d", 77);  -->结果为00077
			if ( menu != null ) {
				value = menu.getBillPrefix()
					.concat(newbillNo.getFperiod())
					.concat(value) ;
			}

		}
//		if ( StringUtils.isEmpty(value) ){
//			throw new BusinessException(String.format("未查询到%s相关单据信息,请确认！", billNoType ));
//		}
		return value;
		
	}

	public static String addZeroForNum(String str, int strLength) {
		int strLen = str.length();
		if (strLen < strLength) {
			while (strLen < strLength) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(str);// 左补0
				// sb.append(str).append("0");//右补0
				str = sb.toString();
				strLen = str.length();
			}
		}

		return str;
	}

}
