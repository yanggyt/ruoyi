package com.ruoyi.content.service;

import com.ruoyi.content.message.Message;
import org.springframework.web.multipart.MultipartFile;

/**
 *
 * @author liushenlu
 */
public interface CommonService {

    Message upload(MultipartFile file);

}
