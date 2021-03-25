package com.ruoyi.content.service.impl;

import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.content.constants.PropertiesConstants;
import com.ruoyi.content.domain.*;
import com.ruoyi.content.exception.BusinessException;
import com.ruoyi.content.exception.ParameterException;
import com.ruoyi.content.mapper.BaseCodeMapper;
import com.ruoyi.content.mapper.GalleryChannelMapper;
import com.ruoyi.content.mapper.GalleryPicInfoMapper;
import com.ruoyi.content.mapper.GalleryQueryMapper;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.redis.RedisManager;
import com.ruoyi.content.service.GalleryService;
import com.ruoyi.content.utils.ImgCompress;
import com.ruoyi.content.utils.JsonUtil;
import com.ruoyi.content.utils.OSSUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class GalleryServiceImpl implements GalleryService {

    private final static Logger LOGGER = LoggerFactory.getLogger(GalleryServiceImpl.class);

    @Autowired
    private GalleryPicInfoMapper galleryInfoMapper;
    @Autowired
    private GalleryQueryMapper galleryQueryMapper;
    @Autowired
    private GalleryChannelMapper galleryChannelMapper;
    @Autowired
    private RedisManager redisManager;
    @Autowired
    private BaseCodeMapper baseCodeMapper;

    /**
     * 将图库图片保存到OSS
     */
    @Override
    public Message addGalleryImg(MultipartFile file, String picAdId) {
        LOGGER.info("上传图片业务层开始");
        Message msg = new Message(true, "上传图片成功");
        // 公司id
        String companyId = "1";
        String branchId = "86";
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
            byte[] bytes = null;
            InputStream inStream = file.getInputStream();
            BufferedImage read = ImageIO.read(inStream);
            int width = read.getWidth();
            int height = read.getHeight();
            LOGGER.info("图片高度为height【{}】", height);
            if (height > 2000) {
                LOGGER.info("图片为长图进行剪切");
                BufferedImage sub = read.getSubimage(0, 0, width, 2000);
                ByteArrayOutputStream bStream = new ByteArrayOutputStream();
                ImageOutputStream imgOut = ImageIO.createImageOutputStream(bStream);
                String formatName = fileName.substring(fileName.lastIndexOf(".") + 1);
                LOGGER.info("图片格式为formatName【{}】", formatName);
                boolean writeFlag = ImageIO.write(sub, formatName, imgOut);
                if (writeFlag) {
                    inStream = new ByteArrayInputStream(bStream.toByteArray());
                } else {
                    LOGGER.info("图片剪切失败");
                }
                ImgCompress compress = new ImgCompress(inStream);
                bytes = compress.resize(320, 800);
            } else {
                ImgCompress compress = new ImgCompress(file.getInputStream());
                bytes = compress.resize(320, 516);
            }

            InputStream ins = new ByteArrayInputStream(bytes);
            String minImgUrl = PropertiesConstants.GALLERY_MINIMG_PATH + fileTime + ext;// OSS保存路径
            String flag2 = OSSUtil.uploadFileByInputStream(PropertiesConstants.OSSENDPOINT, PropertiesConstants.OSSID, PropertiesConstants.OSSKEY,
                    PropertiesConstants.BUCKETNAME, ins, PropertiesConstants.OSSPATH + minImgUrl);
            if (null == flag || flag.equals("false") || flag2 == null || flag2.equals("false")) {
                LOGGER.info("新增图库信息,图片上传OSS发生异常");
                msg.setInfo("新增图库信息,图片上传异常");
                msg.setResult(false);
            } else {
                GalleryPicInfo galleryInfo = new GalleryPicInfo();
                String createDate = DateUtils.getDate();
                String createTime = DateUtils.getTimeNow();
                galleryInfo.setPicId(null);
                galleryInfo.setImgUrl(PropertiesConstants.OSS_URL + PropertiesConstants.OSSPATH + minImgUrl);
                if (StringUtils.isNotBlank(picAdId)) {
                    galleryInfo.setPicAdId(picAdId);
                }
                galleryInfo.setPicState("0");
                galleryInfo.setCompanyId(companyId);
                galleryInfo.setCreateDate(createDate);
                galleryInfo.setCreateTime(createTime);
                galleryInfo.setBranchId(branchId);
                LOGGER.info("数据库新增图库图片");
                galleryInfoMapper.insertSelective(galleryInfo);
            }
        } catch (IOException e) {
            LOGGER.info("上传图片失败【{}】", e.getMessage());
            msg.setInfo("上传图片上传异常");
            msg.setResult(false);
            e.printStackTrace();
        }
        LOGGER.info("上传图片业务层结束");
        return msg;
    }

    @Override
    public List<GalleryDTO> galleryImgList(int startRow, int rows, String channelId, String picState, String special) {
        LOGGER.info("拿到的参数startRow【{}】，rows【{}】，所属栏目【{}】，图片状态【{}】", startRow, rows, channelId, picState);
        String companyId = "1";// 公司id
        String branchId = "86";
        List<String> list = new ArrayList<String>();
        List<Integer> picIds = new ArrayList<>();
        if (StringUtils.isBlank(picState)) {
            list.add("0");
            list.add("1");
        } else {
            list.add(picState);
        }

        if (StringUtils.isBlank(channelId)) {
            channelId = "";
        } else {
            LOGGER.info("所属分公司机构【{}】", branchId);
            GalleryChannelExample example = new GalleryChannelExample();
            example.isDistinct();
//			example.createCriteria().andChannelIdEqualTo(channelId).andStateEqualTo("0").andBranchIdLike("%"+branchId+"%");
            example.createCriteria().andSpecialEqualTo(special).andChannelIdEqualTo(channelId).andStateEqualTo("0").andBranchIdLike("%" + branchId + "%");
            List<GalleryChannel> galleryChannels = galleryChannelMapper.selectByExample(example);
            if (galleryChannels == null || galleryChannels.size() < 1) {
                LOGGER.info("未查询到栏目【{}】数据", channelId);
                throw new BusinessException("图库该栏目暂没有海报！");
            }
            for (GalleryChannel galleryChannel : galleryChannels) {
                picIds.add(galleryChannel.getPicId());
            }
        }

        List<GalleryPicInfo> galleryList = galleryQueryMapper.selectAllWithLimit(companyId, branchId, list, picIds, startRow, rows);
        if (galleryList == null || galleryList.size() < 1) {
            LOGGER.info("未查询到数据");
            throw new BusinessException("图库暂没有海报！");
        }

        //所属二级栏目
        List<GalleryDTO> galleryDTOs = new ArrayList<>();
        for (GalleryPicInfo galleryPicInfo : galleryList) {
            GalleryDTO galleryDTO = new GalleryDTO();
            galleryDTO.setPicId(galleryPicInfo.getPicId());
            galleryDTO.setImgUrl(galleryPicInfo.getImgUrl());
            galleryDTO.setPicAdId(galleryPicInfo.getPicAdId());
            galleryDTO.setPicState(galleryPicInfo.getPicState());
            galleryDTO.setCompanyId(galleryPicInfo.getCompanyId());
            galleryDTO.setCreateDate(galleryPicInfo.getCreateDate());
            galleryDTO.setCreateTime(galleryPicInfo.getCreateTime());
            LOGGER.info("图库数据【{}】", JsonUtil.objectToJackson(galleryDTO));
            List<String> codeName = new ArrayList<>();
//			List<String> channelIds = galleryQueryMapper.selectChannelId(galleryPicInfo.getPicId());
//			for (String string : channelIds) {
//				LOGGER.info("栏目id【{}】", string);
//				BaseCodeExample example = new BaseCodeExample();
//				BaseCodeExample.Criteria criteria = example.createCriteria();
//				criteria.andCodeCodeEqualTo(string).andStateEqualTo("0").andCodeTypeEqualTo("GALLERY");

            GalleryChannelExample channelexample = new GalleryChannelExample();
            channelexample.createCriteria().andPicIdEqualTo(galleryPicInfo.getPicId()).andStateEqualTo("0");
            List<GalleryChannel> galleryChannels = this.galleryChannelMapper.selectByExample(channelexample);
            for (GalleryChannel galleryChannel : galleryChannels) {
                LOGGER.info("栏目id【{}】", JsonUtil.objectToJackson(galleryChannel));
                BaseCodeExample example = new BaseCodeExample();
                BaseCodeExample.Criteria criteria = example.createCriteria();
                criteria.andCodeCodeEqualTo(galleryChannel.getChannelId()).andStateEqualTo("0")
                        .andCodeTypeEqualTo(galleryChannel.getSpecial());
                List<BaseCode> baseCodes = this.baseCodeMapper.selectByExample(example);
                LOGGER.info("栏目数据【{}】", JsonUtil.objectToJackson(baseCodes));
                if ((baseCodes != null) && (baseCodes.size() > 0)) {
                    codeName.add(((BaseCode) baseCodes.get(0)).getCodeCname());
                }
            }
            galleryDTO.setCodeName(codeName);
            galleryDTOs.add(galleryDTO);
        }

        return galleryDTOs;
    }

    @Override
    public Message addGallery(String channelId, String picId) {
        LOGGER.info("增加图库栏目关系的业务层方法开始！");
        Message msg = new Message();
        String companyId = "1";
        String branchId = "86";

        BaseCode baseCode = this.baseCodeMapper.selectByPrimaryKey(Integer.valueOf(channelId));
        GalleryChannelExample example = new GalleryChannelExample();
        example.createCriteria().andSpecialEqualTo(baseCode.getCodeType()).andChannelIdEqualTo(baseCode.getCodeCode())
                .andPicIdEqualTo(Integer.valueOf(picId)).andStateEqualTo("0").andCompanyIdEqualTo(companyId);
        List selectByExample = this.galleryChannelMapper.selectByExample(example);
        if ((selectByExample != null) && (selectByExample.size() > 0)) {
            LOGGER.info("添加图库关系失败,此海报已在栏目【{}】下展示", baseCode.getCodeCname());
            msg.setInfo("此海报在该栏目下已存在！");
            msg.setResult(false);
            return msg;
        }

        GalleryChannel galleryChannel = new GalleryChannel();
        String createDate = DateUtils.getDate();
        String createTime = DateUtils.getTimeNow();
        galleryChannel.setId(null);
        galleryChannel.setPicId(Integer.valueOf(picId));
//		galleryChannel.setChannelId(channelId);
        galleryChannel.setChannelId(baseCode.getCodeCode());
        galleryChannel.setCompanyId(companyId);
        galleryChannel.setBranchId(branchId);
        galleryChannel.setCreateDate(createDate);
        galleryChannel.setCreateTime(createTime);
//		galleryChannel.setSpecial("GALLERY");
        galleryChannel.setSpecial(baseCode.getCodeType());
        galleryChannel.setState("0");
        if (galleryChannelMapper.insert(galleryChannel) > 0) {
            msg.setInfo("添加成功！");
            msg.setResult(true);
            LOGGER.info("添加成功！");
//			redisManager.delete("gallery_channel_"+galleryChannel.getChannelId());
            this.redisManager.delete("gallery_channel_" + galleryChannel.getSpecial() + "_" + galleryChannel.getChannelId());
            LOGGER.info("删除redis图库原有栏目关系成功！");
        } else {
            LOGGER.info("添加图库关系失败");
//			msg.setInfo("添加关系失败！");
            msg.setInfo("分配失败！");
            msg.setResult(false);
        }
        LOGGER.info("增加文章栏目关系的业务层方法结束！");
        return msg;
    }

    @Override
    public Message delGallery(String channelId, String id) {
        LOGGER.info("删除图库栏目关系的业务层方法开始！");
        LOGGER.info("删除图库栏目关系的业务层方法中图片的id【{}】", id);
        Message msg = new Message();
        if (StringUtils.isBlank(id)) {
            LOGGER.info("删除图库信息失败，缺少参数");
            throw new ParameterException("未查询到图库信息");
        }
        msg.setInfo("删除失败！");
        msg.setResult(false);
        try {
            String[] arrId = id.split(",");
            for (String picId : arrId) {
                if (StringUtils.isNotBlank(picId)) {
                    GalleryChannelExample example = new GalleryChannelExample();
                    example.createCriteria().andChannelIdEqualTo(channelId).andPicIdEqualTo(Integer.valueOf(picId)).andStateEqualTo("0");
                    List<GalleryChannel> galleryChannels = galleryChannelMapper.selectByExample(example);
                    GalleryChannel galleryChannel = galleryChannels.get(0);
                    if (galleryChannel != null) {
                        galleryChannel.setState("1");
                        if (galleryChannelMapper.updateByPrimaryKey(galleryChannel) > 0) {
                            msg.setInfo("删除成功！");
                            msg.setResult(true);
                            LOGGER.info("删除图库栏目关系成功！");
//							redisManager.delete("gallery_channel_"+galleryChannel.getChannelId());
                            this.redisManager.delete("gallery_channel_" + galleryChannel.getSpecial() + "_" + galleryChannel.getChannelId());
                            LOGGER.info("删除redis图库栏目关系成功！");
                        }
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.info("删除图库栏目关系失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("删除图库栏目关系失败，请稍候再试！");
            msg.setResult(true);
        }
        LOGGER.info("删除图库栏目关系的业务层方法结束！");
        return msg;
    }

    @Override
    public int countGalleryPicInfoByState(String channelId, String picState) {
        LOGGER.info("统计图片数量的业务层方法开始！");
        String companyId = "1";// 管理员公司id
        String branchId = "86";
        LOGGER.info("统计图片数量的业务层方法中拿到的 所属栏目【{}】、文章状态【{}】", channelId, picState);
        List<String> list = new ArrayList<String>();
        List<Integer> picIds = new ArrayList<>();
        if (StringUtils.isBlank(picState)) {
            list.add("0");
            list.add("1");
        } else {
            list.add(picState);
        }
        if (StringUtils.isBlank(channelId)) {
            channelId = "";
        } else {
            GalleryChannelExample example = new GalleryChannelExample();
            example.isDistinct();
            example.createCriteria().andChannelIdEqualTo(channelId).andStateEqualTo("0").andBranchIdLike("%" + branchId + "%");
            List<GalleryChannel> galleryChannels = galleryChannelMapper.selectByExample(example);
            if (galleryChannels == null || galleryChannels.size() < 1) {
                LOGGER.info("未查询到栏目【{}】数据", channelId);
                throw new BusinessException("图库该栏目暂没有海报！");
            }
            for (GalleryChannel galleryChannel : galleryChannels) {
                picIds.add(galleryChannel.getPicId());
            }
        }

        List<GalleryPicInfo> galleryList = null;
        try {
            galleryList = galleryQueryMapper.selectAllPicInfo(companyId, branchId, list, picIds);
            if (galleryList == null || galleryList.size() < 1) {
                LOGGER.info("根据公司查询图片，未查询到数据companyId【{}】,branchId【{}】", companyId, branchId);
                return 0;
            }
        } catch (Exception e) {
            LOGGER.info("统计图片数量的业务层方法产生异常【{}】", e.getMessage());
            e.printStackTrace();
        }
        LOGGER.info("统计图片数量的业务层方法结束！");
        return galleryList.size();
    }

    @Override
    public Message delGalleryPic(String ids) {
        LOGGER.info("删除图片信息的业务层方法开始！");
        LOGGER.info("删除图片信息的业务层方法中拿到的图片的id【{}】", ids);
        Message msg = new Message();
        if (StringUtils.isBlank(ids)) {
            LOGGER.info("删除图片信息失败，缺少参数");
            throw new ParameterException("未查询到图片信息");
        }
        msg.setInfo("删除失败！");
        msg.setResult(false);
        try {
            String[] arrId = ids.split(",");
            for (String picId : arrId) {
                if (StringUtils.isNotBlank(picId)) {
                    GalleryPicInfo galleryPicInfo = galleryInfoMapper.selectByPrimaryKey(Integer.valueOf(picId));
                    GalleryChannelExample example = new GalleryChannelExample();
                    example.createCriteria().andPicIdEqualTo(Integer.valueOf(picId)).andStateEqualTo("0");
                    List<GalleryChannel> galleryChannels = galleryChannelMapper.selectByExample(example);
                    for (GalleryChannel galleryChannel : galleryChannels) {
                        galleryChannel.setState("1");
                        if (galleryChannelMapper.updateByPrimaryKey(galleryChannel) > 0) {
                            msg.setInfo("删除成功！");
                            msg.setResult(true);
                            LOGGER.info("删除此图片图库栏目关系成功！");
                            //redisManager.deleteZSet("gallery_channel_"+galleryChannel.getChannelId(),picId);
//							redisManager.delete("gallery_channel_"+galleryChannel.getChannelId());
                            this.redisManager.delete("gallery_channel_" + galleryChannel.getSpecial() + "_" + galleryChannel.getChannelId());
                            LOGGER.info("删除redis图库栏目关系成功！");
                        }
                    }
                    if (galleryPicInfo != null) {
                        galleryPicInfo.setPicState("1");
                        if (galleryInfoMapper.updateByPrimaryKey(galleryPicInfo) > 0) {
                            msg.setInfo("删除成功！");
                            msg.setResult(true);
                            LOGGER.info("删除图片信息成功！");
                            redisManager.delete("gallery_pic_Id" + galleryPicInfo.getPicId());
                        }
                    }
                }
            }

        } catch (Exception e) {
            LOGGER.info("删除图片信息失败【{}】", e.getMessage());
            e.printStackTrace();
            msg.setInfo("删除失败，请稍候再试！");
            msg.setResult(true);
        }
        LOGGER.info("删除图片信息的业务层方法结束！");
        return msg;
    }

}
