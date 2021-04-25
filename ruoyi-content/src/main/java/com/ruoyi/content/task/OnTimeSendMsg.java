package com.ruoyi.content.task;

import com.ruoyi.content.domain.ArticlePublishSend;
import com.ruoyi.content.domain.ArticlePublishSendExample;
import com.ruoyi.content.mapper.ArticlePublishSendMapper;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.LibraryService;
import com.ruoyi.content.utils.DateUtil;
import com.ruoyi.content.utils.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class OnTimeSendMsg {
    private static final Logger LOGGER = LoggerFactory.getLogger(OnTimeSendMsg.class);

    @Autowired
    private ArticlePublishSendMapper articlePublishSendMapper;
    @Autowired
    private LibraryService libraryService;

    public void onTimeSend() {
        try {
            LOGGER.info("批量处理定时推送消息开始！");
            String currentDate = DateUtil.currentDate();
            String currentTime = DateUtil.currentTime();
            String currentMin = currentTime.substring(0, currentTime.length() - 2);
            ArticlePublishSendExample example = new ArticlePublishSendExample();
            example.createCriteria().andPublishDateEqualTo(currentDate).
                    andSendStateEqualTo("2").
                    andPublishTimeLike(currentMin + "%");
            List<ArticlePublishSend> onTimeSendList = articlePublishSendMapper.selectByExample(example);
            LOGGER.info("数据库入参查询条件：currentDate【{}】,currentMin【{}】，查询的结果：【{}】", currentDate, currentMin, JsonUtil.objectToJackson(onTimeSendList));
            for (ArticlePublishSend articlePublishSend : onTimeSendList) {
                new Thread() {
                    @Override
                    public void run() {
                        Message resultMsg = null;
                        if ("1".equals(articlePublishSend.getSendType())) { //个人
                            resultMsg = libraryService.articleSend(articlePublishSend.getArticleId().toString(), articlePublishSend.getPublishId(),
                                    articlePublishSend.getGroupId(), "0", null, articlePublishSend.getId());
                        } else {
                            resultMsg = libraryService.articleSend(articlePublishSend.getArticleId().toString(), articlePublishSend.getPublishId(),
                                    null, "0", articlePublishSend.getGroupId(), articlePublishSend.getId());
                        }
                        LOGGER.info("返回的结果信息：【{}】，调取方法的入参信息：【{}】", JsonUtil.objectToJackson(resultMsg), JsonUtil.objectToJackson(articlePublishSend));
                    }
                }.start();
            }
        } catch (Exception e) {
            LOGGER.info("批量处理定时推送消息异常【{}】", e.getMessage());
        }
        LOGGER.info("批量处理定时推送消息结束！");
    }

}
