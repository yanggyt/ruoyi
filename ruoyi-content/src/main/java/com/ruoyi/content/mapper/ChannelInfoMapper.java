package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.ChannelInfo;
import com.ruoyi.content.domain.ChannelInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChannelInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_info
     *
     * @mbggenerated Mon Mar 26 15:46:22 CST 2018
     */
    int countByExample(ChannelInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_info
     *
     * @mbggenerated Mon Mar 26 15:46:22 CST 2018
     */
    int deleteByExample(ChannelInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_info
     *
     * @mbggenerated Mon Mar 26 15:46:22 CST 2018
     */
    int deleteByPrimaryKey(Integer channelId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_info
     *
     * @mbggenerated Mon Mar 26 15:46:22 CST 2018
     */
    int insert(ChannelInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_info
     *
     * @mbggenerated Mon Mar 26 15:46:22 CST 2018
     */
    int insertSelective(ChannelInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_info
     *
     * @mbggenerated Mon Mar 26 15:46:22 CST 2018
     */
    List<ChannelInfo> selectByExample(ChannelInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_info
     *
     * @mbggenerated Mon Mar 26 15:46:22 CST 2018
     */
    ChannelInfo selectByPrimaryKey(Integer channelId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_info
     *
     * @mbggenerated Mon Mar 26 15:46:22 CST 2018
     */
    int updateByExampleSelective(@Param("record") ChannelInfo record, @Param("example") ChannelInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_info
     *
     * @mbggenerated Mon Mar 26 15:46:22 CST 2018
     */
    int updateByExample(@Param("record") ChannelInfo record, @Param("example") ChannelInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_info
     *
     * @mbggenerated Mon Mar 26 15:46:22 CST 2018
     */
    int updateByPrimaryKeySelective(ChannelInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table channel_info
     *
     * @mbggenerated Mon Mar 26 15:46:22 CST 2018
     */
    int updateByPrimaryKey(ChannelInfo record);
}