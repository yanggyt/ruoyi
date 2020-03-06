package cn.com.infosouth.arj21.service;

import java.util.List;
import java.util.Map;

import cn.com.infosouth.arj21.domain.InfoDefaultindexActypecontrastset;

public interface IInfoDefaultindexActypecontrastsetService {

	List<Map<String, String>> findActypecontrastSetData();

	List<InfoDefaultindexActypecontrastset> findList(
			InfoDefaultindexActypecontrastset infoDefaultindexActypecontrastset);

	
	
}
