package cn.com.infosouth.arj21.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.com.infosouth.arj21.domain.InfoChoosedMulticsvParamsTemplate;
import cn.com.infosouth.arj21.mapper.InfoChoosedMulticsvParamsTemplateMapper;
import cn.com.infosouth.arj21.service.IInfoChoosedMulticsvParamsTemplateService;

@Service
public class IInfoChoosedMulticsvParamsTemplateServiceImpl implements IInfoChoosedMulticsvParamsTemplateService{

	@Resource
	private InfoChoosedMulticsvParamsTemplateMapper chooseParamTemplateMapper;
	
	@Override
	public List<InfoChoosedMulticsvParamsTemplate> findList(
			InfoChoosedMulticsvParamsTemplate choosedParamtemplate) {
		return chooseParamTemplateMapper.selectInfoChoosedMulticsvParamsTemplateList(choosedParamtemplate);
	}

	@Override
	public List<Map<String, String>> findChoosedTemplateMapListByCondition(
			InfoChoosedMulticsvParamsTemplate infoChoosedParamsTemplate) {
		return chooseParamTemplateMapper.findChoosedTemplateMapListByCondition(infoChoosedParamsTemplate);
	}

	@Override
	public InfoChoosedMulticsvParamsTemplate selectById(String choosed_templateId) {
		return chooseParamTemplateMapper.selectById(choosed_templateId);
	}

	@Override
	public boolean save(InfoChoosedMulticsvParamsTemplate entity) {
		return chooseParamTemplateMapper.insert(entity) > 0 ? true : false;
	}

}
