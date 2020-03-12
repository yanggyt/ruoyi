package cn.com.infosouth.arj21.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;


import cn.com.infosouth.arj21.domain.ShowMultiCsv;
import cn.com.infosouth.arj21.mapper.ShowMultiCsvMapper;
import cn.com.infosouth.arj21.service.IShowMultiCsvService;
import cn.com.infosouth.arj21.utils_oneselef.Page;

@Service
public class IShowMultiCsvServiceImpl implements IShowMultiCsvService{

	@Resource
	private ShowMultiCsvMapper showMultiCsvMapper;
	 

	@Override
	public Page<ShowMultiCsv> findPage(int pageSize, int pageNum, ShowMultiCsv showMultiCsv) {
		Page<ShowMultiCsv> page = new Page<ShowMultiCsv>();
		int total = showMultiCsvMapper.selectCount(showMultiCsv);
		pageNum = (pageNum - 1) * pageSize;
		List<ShowMultiCsv> list = showMultiCsvMapper.findList(pageSize, pageNum, showMultiCsv);
		page.setData(list);
		page.setTotal(total);
		return page;
	}
	
	
	

}
