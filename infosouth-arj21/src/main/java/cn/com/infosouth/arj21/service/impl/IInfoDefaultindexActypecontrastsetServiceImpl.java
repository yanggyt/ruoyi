package cn.com.infosouth.arj21.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.com.infosouth.arj21.domain.InfoDefaultindexActypecontrastset;
import cn.com.infosouth.arj21.mapper.InfoDefaultindexActypecontrastsetMapper;
import cn.com.infosouth.arj21.service.IInfoDefaultindexActypecontrastsetService;

@Service
public class IInfoDefaultindexActypecontrastsetServiceImpl implements IInfoDefaultindexActypecontrastsetService{

	@Autowired
	private InfoDefaultindexActypecontrastsetMapper actypecontrastsetMapper;
	
	@Override
	public List<Map<String, String>> findActypecontrastSetData() {
		return actypecontrastsetMapper.findActypecontrastSetData();
	}

	@Override
	public List<InfoDefaultindexActypecontrastset> findList(
			InfoDefaultindexActypecontrastset infoDefaultindexActypecontrastset) {
		return actypecontrastsetMapper.findList();
	}

	
	
}
