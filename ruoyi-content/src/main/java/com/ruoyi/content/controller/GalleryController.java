package com.ruoyi.content.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @program: ruoyi->GalleryController
 * @description:
 * @author: LiuShenlu
 * @create: 2021-03-25 11:20
 **/
@Controller
@RequestMapping("/content/gallery")
public class GalleryController extends BaseController {

    private final static Logger LOGGER = LoggerFactory.getLogger(GalleryController.class);

    private String prefix = "content/gallery";

    @Autowired
    private GalleryService galleryService;

    @GetMapping()
    public String adverts() {
        return prefix + "/gallery";
    }

    /**
     * 上传图库图片
     *
     * @param files
     * @return
     */
    @PostMapping(value = "/addGalleryImg")
    @ResponseBody
    public Message addGalleryImg(@RequestParam("imgUrl") MultipartFile[] files, @RequestParam("picAdId") String picAdId) {
        LOGGER.info("上传图库图片控制层方法开始");
        MultipartFile file = files[0];
        Message msg = galleryService.addGalleryImg(file, picAdId);
        LOGGER.info("上传图库图片控制层方法结束");
        return msg;
    }

}
