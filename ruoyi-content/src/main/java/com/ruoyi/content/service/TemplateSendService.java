package com.ruoyi.content.service;


import com.ruoyi.content.message.Message;

public interface TemplateSendService {

    /**
     * 新文章发布通知
     *
     * @throws Exception
     */
    Message newArticleSend(String articleName, String url, String picurl, String shareDes, String agentCode, String partyId) throws Exception;
    /**
     * 已阅读通知
     * @throws Exception
     *//*
	void haveReadSend() throws Exception;
	*//**
     * 已转发通知
     * @throws Exception
     *//*
	void forwardedSend() throws Exception;*/


}
