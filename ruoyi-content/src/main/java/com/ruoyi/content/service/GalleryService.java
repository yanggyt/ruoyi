package com.ruoyi.content.service;

import com.ruoyi.content.domain.GalleryDTO;
import com.ruoyi.content.message.Message;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface GalleryService {


    /**
     * 图片上传
     *
     * @param file
     * @param picAdId
     * @return
     */
    public Message addGalleryImg(MultipartFile file, String picAdId);

    /**
     * 图库列表
     *
     * @param channelId
     * @param picState
     * @return
     */
    public List<GalleryDTO> galleryImgList(int startRow, int rows, String channelId, String picState, String special);

    /**
     * 图片个数
     *
     * @param channelId
     * @param picState
     * @return
     */
    public int countGalleryPicInfoByState(String channelId, String picState);

    /**
     * 图片图库录入
     *
     * @param channelId
     * @param picId
     * @return
     */
    public Message addGallery(String channelId, String picId);

    /**
     * 图库栏目下图片删除
     *
     * @param ids
     * @param channelId
     * @return
     */
    public Message delGallery(String ids, String channelId);

    /**
     * 图片删除
     *
     * @param ids
     * @return
     */
    public Message delGalleryPic(String ids);
}
