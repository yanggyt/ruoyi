package com.ruoyi.template.mapper;

import com.ruoyi.template.domain.TmplSwitchPort;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 交换机端口类型 数据层
 *
 * @author TP
 * @date 2019-06-12
 */
public interface TmplSwitchPortMapper {
    /**
     * 查询交换机端口类型信息
     *
     * @param switchPortId 交换机端口类型ID
     * @return 交换机端口类型信息
     */
    public TmplSwitchPort selectTmplSwitchPortById(Integer switchPortId);

    /**
     * * 查询交换机端口类型信息
     *
     * @param switchId       交换机ID
     * @param switchPortType 交换机端口类型
     * @return 交换机端口类型信息
     */
    public TmplSwitchPort selectBySwitchIdAndPortType(Integer switchId, Integer switchPortType);

    public TmplSwitchPort selectBySwitchId(@Param("switchId") Integer switchId);

    /**
     * 查询交换机端口类型列表
     *
     * @param tmplSwitchPort 交换机端口类型信息
     * @return 交换机端口类型集合
     */
    public List<TmplSwitchPort> selectTmplSwitchPortList(TmplSwitchPort tmplSwitchPort);

    /**
     * 新增交换机端口类型
     *
     * @param tmplSwitchPort 交换机端口类型信息
     * @return 结果
     */
    public int insertTmplSwitchPort(TmplSwitchPort tmplSwitchPort);

    /**
     * 修改交换机端口类型
     *
     * @param tmplSwitchPort 交换机端口类型信息
     * @return 结果
     */
    public int updateTmplSwitchPort(TmplSwitchPort tmplSwitchPort);

    /**
     * 删除交换机端口类型
     *
     * @param switchPortId 交换机端口类型ID
     * @return 结果
     */
    public int deleteTmplSwitchPortById(Integer switchPortId);


    /**
     * 通过交换机ID批量删除交换机端口类型
     *
     * @param switchId 交换机ID
     * @return 结果
     */
    public int deleteTmplSwitchPortBySwitchId(Integer switchId);

    /**
     * 批量删除交换机端口类型
     *
     * @param switchPortIds 需要删除的数据ID
     * @return 结果
     */
    public int deleteTmplSwitchPortByIds(String[] switchPortIds);

    /**
     * 批量新增交换机端口信息
     *
     * @param tmplSwitchPortList 交换机端口列表
     * @return 结果
     */
    public int batchTmplSwitchPort(List<TmplSwitchPort> tmplSwitchPortList);

}