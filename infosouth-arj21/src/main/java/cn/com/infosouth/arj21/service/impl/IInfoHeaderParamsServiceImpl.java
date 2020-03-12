package cn.com.infosouth.arj21.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.infosouth.arj21.domain.InfoHeaderParams;
import cn.com.infosouth.arj21.mapper.InfoHeaderParamsMapper;
import cn.com.infosouth.arj21.service.IInfoHeaderParamsService;

@Service
public class IInfoHeaderParamsServiceImpl implements IInfoHeaderParamsService{
	
	@Resource
	private InfoHeaderParamsMapper infoHeaderParamsMapper;
	
	@Override
	public InfoHeaderParams getByVersionId(int versionId) {
		return infoHeaderParamsMapper.getByVersionId(versionId);
	}

	@Override
	public void save(InfoHeaderParams infoHeaderParams) {
		infoHeaderParamsMapper.insert();
	}

}
