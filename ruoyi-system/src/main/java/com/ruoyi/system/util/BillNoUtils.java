package com.ruoyi.system.util;

import com.ruoyi.common.exception.BusinessException;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.common.utils.spring.SpringUtils;
import com.ruoyi.system.service.ISysBillNoService;
import com.ruoyi.system.service.impl.SysBillNoServiceImpl;

/**
 * 单据号管理工具类
 * @author pandong
 *
 */
public class BillNoUtils {

	
	private static ISysBillNoService billNoService = SpringUtils.getBean(SysBillNoServiceImpl.class);
	
	/***
	 * 获取单据标识下一个单据号
	 * @param billNoType
	 * @return
	 */
	public static String nextValue( String billNoType ){
		
		if ( StringUtils.isNull(billNoType) ){
			throw new BusinessException("缺少必须参数：单据标识");
		}
		String value = "";
		synchronized (BillNoUtils.class) {
			value = billNoService.selectNextBillNoById(billNoType.name());
		}
		if ( StringUtils.isEmpty(value) ){
			throw new BusinessException(String.format("未查询到%s相关单据信息,请确认！", billNoType.name()));
		}
		return value;
		
	}
	
	
	
}
