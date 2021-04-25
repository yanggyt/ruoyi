package com.ruoyi.content.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.common.utils.StringUtils;
import com.ruoyi.content.constants.PropertiesConstants;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.CommonService;
import com.ruoyi.content.utils.ImgCompress;
import com.ruoyi.content.utils.OSSUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @program: ruoyi->CommonServiceImpl
 * @description:
 * @author: LiuShenlu
 * @create: 2021-04-09 17:29
 **/
@Service
public class CommonServiceImpl implements CommonService {

    @Override
    public Message upload(MultipartFile file) {
        Message msg = new Message(true, "上传图片成功");
        try {
            // 文件名
            String fileName = file.getOriginalFilename();
            // 文件后缀
            String ext = fileName.substring(fileName.lastIndexOf("."));
            String fileTime = DateUtils.getMillisecond();
            // OSS保存路径
            fileName = PropertiesConstants.GALLERY_IMG_PATH + fileTime + ext;
            String flag = OSSUtil.uploadFileByInputStream(PropertiesConstants.OSSENDPOINT, PropertiesConstants.OSSID, PropertiesConstants.OSSKEY,
                    PropertiesConstants.BUCKETNAME, file.getInputStream(), PropertiesConstants.OSSPATH + fileName);
            if (StringUtils.isBlank(flag) || StringUtils.equals(flag, "false")) {
                msg.setInfo("上传图片上传异常");
                msg.setResult(false);
            } else {
                msg.setObject(PropertiesConstants.OSS_URL + PropertiesConstants.OSSPATH + fileName);
            }
        } catch (IOException e) {
            msg.setInfo("上传图片上传异常");
            msg.setResult(false);
            e.printStackTrace();
        }
        return msg;
    }

}
