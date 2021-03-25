package com.ruoyi.content.service;

import java.io.InputStream;

public interface WechatIMGService {

	InputStream getInputStream(String mediaId, InputStream ins);

}
