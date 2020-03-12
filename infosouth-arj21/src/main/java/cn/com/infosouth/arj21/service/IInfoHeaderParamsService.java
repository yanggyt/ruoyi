package cn.com.infosouth.arj21.service;

import cn.com.infosouth.arj21.domain.InfoHeaderParams;

public interface IInfoHeaderParamsService {

	InfoHeaderParams getByVersionId(int version);

	void save(InfoHeaderParams infoHeaderParams);

}
