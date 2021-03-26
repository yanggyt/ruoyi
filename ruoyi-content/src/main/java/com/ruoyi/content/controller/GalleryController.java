package com.ruoyi.content.controller;

import com.ruoyi.common.core.controller.BaseController;
import com.ruoyi.common.core.domain.AjaxResult;
import com.ruoyi.common.core.page.TableDataInfo;
import com.ruoyi.content.domain.GalleryPicInfoEx;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.GalleryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
    public String gallery() {
        return prefix + "/gallery";
    }

    @GetMapping("page")
    public String galleryPage() {
        return prefix + "/list";
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

    /**
     * 根据栏目图库列表
     *
     * @param request
     * @return
     */
    @RequestMapping("/galleryImgList")
    @ResponseBody
    public TableDataInfo galleryImgList(HttpServletRequest request) {
        LOGGER.info("上传图库图片控制层方法开始");
        String channel = request.getParameter("channel");
        String picState = "0";
        String special = "GALLERY";
        startPage();
        List<GalleryPicInfoEx> galleryPicInfos = galleryService.galleryImgList(channel, picState, special);
        LOGGER.info("查询图库列表的控制层方法结束！");
        return getDataTable(galleryPicInfos);
    }

    /**
     * 图片删除
     *
     * @return
     */
    @RequestMapping("/remove")
    @ResponseBody
    public AjaxResult delGalleryPic(String ids) {
        LOGGER.info("删除图片信息控制层方法开始");
        try {
            return toAjax(galleryService.delGalleryPic(ids));
        } catch (Exception e) {
            LOGGER.info("删除文章信息失败【{}】", e.getMessage());
        }
        LOGGER.info("删除图片信息的控制层方法结束！");
        return toAjax(0);
    }

    /**
     * 图片图库栏目录入
     *
     * @return
     */
    @PostMapping("/addGallery")
    @ResponseBody
    public Message addGallery(String channelId, String picId) {
        LOGGER.info("图库图片分配栏目控制层方法开始");
        LOGGER.info(channelId + "------" + picId);
        Message msg = galleryService.addGallery(channelId, picId);
        LOGGER.info("图库图片分配栏目控制层方法结束");
        return msg;
    }

}
