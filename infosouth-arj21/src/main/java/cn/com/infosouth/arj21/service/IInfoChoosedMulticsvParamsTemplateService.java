package cn.com.infosouth.arj21.service;

import java.util.List;
import java.util.Map;

import cn.com.infosouth.arj21.domain.InfoChoosedMulticsvParamsTemplate;

public interface IInfoChoosedMulticsvParamsTemplateService {

	/**
	 * 查询已选择参数模板的信息
	 * @param infoChoosedMulticsvParamsTemplate
	 * @return
	 */
	List<InfoChoosedMulticsvParamsTemplate> findList(InfoChoosedMulticsvParamsTemplate infoChoosedMulticsvParamsTemplate);

	/**
	 * 	根据管理员查询设置过的选中参数模板
	 * @param infoChoosedParamsTemplate
	 * @return
	 */
	List<Map<String, String>> findChoosedTemplateMapListByCondition(
			InfoChoosedMulticsvParamsTemplate infoChoosedParamsTemplate);

	/**
	 * 查找单条记录
	 * @param choosed_templateId
	 * @return
	 */
	InfoChoosedMulticsvParamsTemplate selectById(String choosed_templateId);

	/**
	 * 保存
	 * @param entity
	 */
	boolean save(InfoChoosedMulticsvParamsTemplate entity);

	
}
