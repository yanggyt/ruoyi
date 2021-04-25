package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.LdCom;
import com.ruoyi.content.domain.LdComExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LdComMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldcom
     *
     * @mbggenerated Tue Jul 24 14:14:03 CST 2018
     */
    int countByExample(LdComExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldcom
     *
     * @mbggenerated Tue Jul 24 14:14:03 CST 2018
     */
    int deleteByExample(LdComExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldcom
     *
     * @mbggenerated Tue Jul 24 14:14:03 CST 2018
     */
    int deleteByPrimaryKey(String comcode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldcom
     *
     * @mbggenerated Tue Jul 24 14:14:03 CST 2018
     */
    int insert(LdCom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldcom
     *
     * @mbggenerated Tue Jul 24 14:14:03 CST 2018
     */
    int insertSelective(LdCom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldcom
     *
     * @mbggenerated Tue Jul 24 14:14:03 CST 2018
     */
    List<LdCom> selectByExample(LdComExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldcom
     *
     * @mbggenerated Tue Jul 24 14:14:03 CST 2018
     */
    LdCom selectByPrimaryKey(String comcode);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldcom
     *
     * @mbggenerated Tue Jul 24 14:14:03 CST 2018
     */
    int updateByExampleSelective(@Param("record") LdCom record, @Param("example") LdComExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldcom
     *
     * @mbggenerated Tue Jul 24 14:14:03 CST 2018
     */
    int updateByExample(@Param("record") LdCom record, @Param("example") LdComExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldcom
     *
     * @mbggenerated Tue Jul 24 14:14:03 CST 2018
     */
    int updateByPrimaryKeySelective(LdCom record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ldcom
     *
     * @mbggenerated Tue Jul 24 14:14:03 CST 2018
     */
    int updateByPrimaryKey(LdCom record);
}