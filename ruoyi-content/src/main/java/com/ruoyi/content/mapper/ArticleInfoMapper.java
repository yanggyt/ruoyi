package com.ruoyi.content.mapper;

import com.ruoyi.content.domain.ArticleInfo;
import com.ruoyi.content.domain.ArticleInfoExample;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleInfoMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    int countByExample(ArticleInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    int deleteByExample(ArticleInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    int deleteByPrimaryKey(Integer articleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    int insert(ArticleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    int insertSelective(ArticleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    List<ArticleInfo> selectByExampleWithBLOBs(ArticleInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    List<ArticleInfo> selectByExample(ArticleInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    ArticleInfo selectByPrimaryKey(Integer articleId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    int updateByExampleSelective(@Param("record") ArticleInfo record, @Param("example") ArticleInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    int updateByExampleWithBLOBs(@Param("record") ArticleInfo record, @Param("example") ArticleInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    int updateByExample(@Param("record") ArticleInfo record, @Param("example") ArticleInfoExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    int updateByPrimaryKeySelective(ArticleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    int updateByPrimaryKeyWithBLOBs(ArticleInfo record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table article_info
     *
     * @mbggenerated Fri May 25 15:02:37 CST 2018
     */
    int updateByPrimaryKey(ArticleInfo record);
}