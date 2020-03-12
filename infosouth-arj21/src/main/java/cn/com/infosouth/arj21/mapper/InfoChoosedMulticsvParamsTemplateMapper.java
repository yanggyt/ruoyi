package cn.com.infosouth.arj21.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import cn.com.infosouth.arj21.domain.InfoChoosedMulticsvParamsTemplate;

/**
 * InfoChoosedMulticsvParamsTemplateMapper接口
 * 
 * @author kxnf
 * @date 2020-03-08
 */
@Mapper
public interface InfoChoosedMulticsvParamsTemplateMapper 
{
    /**
     * 查询InfoChoosedMulticsvParamsTemplate
     * 
     * @param id InfoChoosedMulticsvParamsTemplateID
     * @return InfoChoosedMulticsvParamsTemplate
     */
    public InfoChoosedMulticsvParamsTemplate selectInfoChoosedMulticsvParamsTemplateById(String id);

    /**
     * 查询InfoChoosedMulticsvParamsTemplate列表
     * 
     * @param infoChoosedMulticsvParamsTemplate InfoChoosedMulticsvParamsTemplate
     * @return InfoChoosedMulticsvParamsTemplate集合
     */
    public List<InfoChoosedMulticsvParamsTemplate> selectInfoChoosedMulticsvParamsTemplateList(InfoChoosedMulticsvParamsTemplate infoChoosedMulticsvParamsTemplate);

    /**
     * 新增InfoChoosedMulticsvParamsTemplate
     * 
     * @param infoChoosedMulticsvParamsTemplate InfoChoosedMulticsvParamsTemplate
     * @return 结果
     */
    public int insertInfoChoosedMulticsvParamsTemplate(InfoChoosedMulticsvParamsTemplate infoChoosedMulticsvParamsTemplate);

    /**
     * 修改InfoChoosedMulticsvParamsTemplate
     * 
     * @param infoChoosedMulticsvParamsTemplate InfoChoosedMulticsvParamsTemplate
     * @return 结果
     */
    public int updateInfoChoosedMulticsvParamsTemplate(InfoChoosedMulticsvParamsTemplate infoChoosedMulticsvParamsTemplate);

    /**
     * 删除InfoChoosedMulticsvParamsTemplate
     * 
     * @param id InfoChoosedMulticsvParamsTemplateID
     * @return 结果
     */
    public int deleteInfoChoosedMulticsvParamsTemplateById(String id);

    /**
     * 批量删除InfoChoosedMulticsvParamsTemplate
     * 
     * @param ids 需要删除的数据ID
     * @return 结果
     */
    public int deleteInfoChoosedMulticsvParamsTemplateByIds(String[] ids);

    /**
     * 根据管理员查询设置过的选中参数模板
     * @param infoChoosedParamsTemplate
     * @return
     */
	public List<Map<String, String>> findChoosedTemplateMapListByCondition(
			InfoChoosedMulticsvParamsTemplate infoChoosedParamsTemplate);

	/**
	 * 查找单条记录
	 * @param choosed_templateId
	 * @return
	 */
	public InfoChoosedMulticsvParamsTemplate selectById(String choosed_templateId);

	/**
	 * 保存
	 * @param entity
	 * @return
	 */
	public int insert(InfoChoosedMulticsvParamsTemplate entity);
}
