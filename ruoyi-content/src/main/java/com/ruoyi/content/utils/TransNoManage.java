package com.ruoyi.content.utils;

import com.ruoyi.content.redis.RedisManager;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TransNoManage {
    @Autowired
    private RedisManager redisManager;
    //@Autowired
    //private BaseConstantService baseConstantService;
    private final static Logger logger = LoggerFactory.getLogger(TransNoManage.class);

//	public String queryTransNo(String source) {
//		if (StringUtils.isBlank(source)) {
//			source = "WKTTEM";// 如果没有数据，则临时使用
//		}
//		String transNo = queryPrefixBysource(source) + queryNumBySource(source);
//		logger.info("根据【{}】生成流水号【{}】", source, transNo);
//		return transNo;
//	}

//	/**
//	 * 获取数据库配置信息生成前缀如果系统异常则生成时间戳
//	 * 
//	 * @param source
//	 * @return
//	 */
//	public String queryPrefixBysource(String source) {
//		String transNo = "";
//		try {
//			transNo = baseConstantService.queryConstant(source);
//			if (StringUtils.isBlank(transNo)) {
//				logger.info("数据库未配置【{}】相关信息，使用日期+随机数生成12位流水号", source);
//				// 查询不到配置信息已时间为前缀
//				transNo = DateUtil.convertDate(new Date(), DateUtil.yyyyMMddHHmmssSSS);
//			}
//		} catch (Exception e) {
//			logger.info("数据库查询【{}】配置信息异常，生成时间戳:【{}】", source, e.getMessage());
//			transNo = DateUtil.convertDate(new Date(), DateUtil.yyyyMMddHHmmssSSS);
//		}
//		return transNo;
//	}

    /**
     * 根据key去redis取值 如果redis异常则生成随机6位数字
     *
     * @param source
     * @return
     */
    public String queryNumBySource(String source) {
        String num = "1";
        // 获取当前日期
        String nowDate = DateUtil.convertDate(new Date(), DateUtil.YYYYMMDD);
        try {
            String val = redisManager.query(source + "_TRANSNO");
            logger.info("根据code【{}】,获取redis数据【{}】", source, val);
            if (StringUtils.isNotBlank(val)) {// redis存在数据则进行规则计算
                if (val.indexOf("d") > -1) {// 判断是否存在分隔符
                    String orderNum = val.split("d")[0];
                    String orderDate = val.split("d")[1];
                    if (orderDate.equals(nowDate)) {// 同一天则继续序号
                        logger.info("数据为同一天orderDate【{}】,nowDate【{}】", orderNum, nowDate);
                        int rel = Integer.parseInt(orderNum) + 1;
                        num = rel + "";
                    } else {// 新的一天重新计算
                        logger.info("这是新的一天，重新计算【{}】", num);
                    }
                    // 将数据保存到redis
                    boolean saveRel = redisManager.save(source + "_TRANSNO", num + "d" + nowDate);
                    if (!saveRel) {
                        logger.info("保存redis【{}】，【{}】失败，直接获取6位随机数", (source + "_TRANSNO"), num);
                        num = RandomUtils.randomNumber(6) + "E";
                    }
                } else {// 不存在分隔符直接转生成随机数
                    logger.info("取出来的数据不存在分隔符【{}】", val);
                    num = RandomUtils.randomNumber(6) + "E";
                }
            } else {// 不存在值时，则重新开始计算
                logger.info("redis不存在数据，重新计算【{}】", num);
                // 将数据保存到redis
                boolean saveRel = redisManager.save(source + "_TRANSNO", num + "d" + nowDate);
                if (!saveRel) {
                    logger.info("保存redis【{}】，【{}】失败，直接获取6位随机数", (source + "_TRANSNO"), num);
                    num = RandomUtils.randomNumber(6) + "E";
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            logger.info("redis服务器【{}】异常，生成随机6位数【{}】", source, e.getMessage());
            // 当redies出错时，生成随机6位数字
            num = RandomUtils.randomNumber(6) + "E";
        }
        logger.info("获取流水号，最终得到数据【{}】", num);
        return nowDate + num;
    }

}
