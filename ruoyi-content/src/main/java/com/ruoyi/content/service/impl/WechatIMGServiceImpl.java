package com.ruoyi.content.service.impl;

import com.ruoyi.content.constants.PropertiesConstants;
import com.ruoyi.content.message.Message;
import com.ruoyi.content.service.WechatIMGService;
import com.ruoyi.content.utils.JsonUtil;
import com.ruoyi.content.utils.OSSUtil;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;

@Service
public class WechatIMGServiceImpl implements WechatIMGService {
//	@Autowired
//	private WeChatGatewayInterface chatGatewayInterface;
//	@Autowired
//	private BaseConstantService baseConstantService;
//	@Autowired
//	private WxImageRecordService imagerecordService;
//	@Autowired
//	private WeChatPlatInterface weChatPlatInterface;

    private final static Logger logger = LoggerFactory.getLogger(WechatIMGService.class);

    /**
     * 根据serverId从微信获取图片并返回流信息
     */
    @Override
    public InputStream getInputStream(String mediaId, InputStream ins) {
        // TODO Auto-generated method stub
        try {
            String access_token = "";

            // 从redis里查access_token
//			Map<String, String> map = chatGatewayInterface.getToken();
            Map<String, String> map = null;
            if (null == map || map.size() == 0) {
                logger.info("获取微信图片失败,access_token获取失败map【{}】", JsonUtil.objectToJackson(map));// 错误信息
                return null;
            }
            String result = map.get("result");
            if (result.equals(PropertiesConstants.I_SUCC)) {
                access_token = map.get("access_token");
            }
            logger.info("对外接口获取微信用户基本信息,从接口里查access_token【{}】", access_token);
            if (StringUtils.isBlank(access_token)) {
                logger.info("获取微信图片失败,access_token获取失败access_token【{}】", access_token);// 错误信息
                return null;
            }
            String url = "http://file.api.weixin.qq.com/cgi-bin/media/get?access_token=" + access_token + "&media_id=" + mediaId;
            logger.info("执行获取微信图片请求地址【{}】", url);
            URL urlGet = new URL(url);
            HttpURLConnection http = (HttpURLConnection) urlGet.openConnection();
            http.setRequestMethod("GET"); // 必须是get方式请求
            http.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            http.setDoOutput(true);
            http.setDoInput(true);
            System.setProperty("sun.net.client.defaultConnectTimeout", "30000");// 连接超时30秒
            System.setProperty("sun.net.client.defaultReadTimeout", "30000"); // 读取超时30秒
            http.connect();
            // 获取文件转化为byte流
            ins = http.getInputStream();
            String contentType = http.getHeaderField("Content-Type");
            logger.info("获取返回的contentType【{}】", contentType);
            if (contentType.indexOf("image") < 0 && contentType.indexOf("jpeg") < 0 && contentType.indexOf("audio") < 0 && contentType.indexOf("amr") < 0) {
                String data = readStream(ins);
                logger.info("获取微信图片失败返回data【{}】", JsonUtil.objectToJackson(data));
                return null;
            }
            return ins;
        } catch (Exception e) {
            // TODO: handle exception
            logger.info("从微信下载指定图片异常：【{}】", e.getMessage());
            e.printStackTrace();
            return null;
        }

    }

    private String readStream(InputStream in) {
        char[] buf = new char[1024];
        int size = 0;
        String data = new String();
        InputStreamReader reader = new InputStreamReader(in);
        try {
            while ((size = reader.read(buf, 0, 1024)) > 0) {
                data = data + String.copyValueOf(buf, 0, size);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return data;
    }

	/*@Override
	@LogServiceAnnotation(description = "图片上传")
	public Message IdIdentify(IdentityImageUploadDto imgUploadReq,String propertyCode) throws IOException {
		Message result = new Message();
		result.setResult(false);
		if (StringUtils.isBlank(imgUploadReq.getPolicyNo()) || StringUtils.isBlank(imgUploadReq.getFaceIdImgUrl()) || StringUtils.isBlank(imgUploadReq.getWholeIdImgUrl()) || StringUtils.isBlank(imgUploadReq.getEdorType()) || StringUtils.isBlank(imgUploadReq.getChannel())
				|| StringUtils.isBlank(imgUploadReq.getOrderNo())) {
			logger.info("影像缺少数据【{}】,propertyCode【{}】",JsonUtil.objectToJackson(imgUploadReq),propertyCode);
			throw new ParameterException("影像上传失败，缺少参数");
		}
		InputStream in = null;
		WxImageRecord imagerecord = new WxImageRecord();
		imagerecord.setPolicyNo(imgUploadReq.getPolicyNo());
		imagerecord.setSource(imgUploadReq.getChannel());// 这个是接口需要的
		imagerecord.setBussiType(imgUploadReq.getEdorType());
		imagerecord.setNoticeState("0");// 默认状态
		imagerecord.setCreateDate(DateUtil.currentDate());
		imagerecord.setCreateTime(DateUtil.currentDate() + " " + DateUtil.currentDate(DateUtil.HHMMSS));
		imagerecord.setRemark("数据存储");
		imagerecord.setNoticeNum(0);
		imagerecord.setServiceId(imgUploadReq.getOrderNo()); // 工单号
		imagerecord.setFileUrl(imgUploadReq.getPolicyNo() + imgUploadReq.getEdorType() + "/");// 保单号加业务类型作为文件夹名称防止重复
		String fileName = "";
		try {
			if (StringUtils.isNotBlank(imgUploadReq.getFaceIdImgUrl())) {
				in = getInputStream(imgUploadReq.getFaceIdImgUrl(), in);
				fileName = imgUploadReq.getPolicyNo() + "f" + ".jpg";// 正面例如：888888f.jpg
				result = ossFileUpload(imagerecord.getFileUrl() + fileName, in);// 图片名称，流
				if (!result.getResult()) {
					return new Message(false, "请拍照上传指定照片！");
				}
				imagerecord.setImgurl1(fileName);
			}
			if (StringUtils.isNotBlank(imgUploadReq.getWholeIdImgUrl())) {
				fileName = imgUploadReq.getPolicyNo() + "w" + ".jpg";// 手持
				in = getInputStream(imgUploadReq.getWholeIdImgUrl(), in);
				result = ossFileUpload(imagerecord.getFileUrl() + fileName, in);
				if (!result.getResult()) {
					return new Message(false, "请拍照上传指定照片！");
				}
				imagerecord.setImgurl3(fileName);// 手持
			}
			if (StringUtils.isNotBlank(imgUploadReq.getBackIdImgUrl())) {
				fileName = imgUploadReq.getPolicyNo() + "b" + ".jpg";// 反面
				in = getInputStream(imgUploadReq.getBackIdImgUrl(), in);
				result = ossFileUpload(imagerecord.getFileUrl() + fileName, in);
				if (!result.getResult()) {
					return new Message(false, "请拍照上传指定照片！");
				}
				imagerecord.setImgurl2(fileName);
			}
			// 存储
			String recordid = imagerecordService.insertRecord(imagerecord);
			if (StringUtils.isBlank(recordid)) {
				return new Message(false, "系统异常，请稍后再试");
			} else {
				// 通知中转站
				Map<String, String> noticeResult = weChatPlatInterface.ftpUploadSynchro(recordid, "/" + imagerecord.getFileUrl(), propertyCode);
				if (!CodeTable.I_SUCCESS.equals(noticeResult.get("code"))) {
					return new Message(false, "系统发生异常，请稍后再试");
				} else {
					// 更新状态
					imagerecord.setNoticeState("1");// 通知中转站成功
					imagerecord.setUpdateDate(DateUtil.currentDate());
					imagerecord.setUpdateTime(DateUtil.currentDate() + " " + DateUtil.currentDate(DateUtil.HHMMSS));
					imagerecord.setUpdateRemark("通知中转站成功");
					if (!imagerecordService.updateRecord(imagerecord).getResult()) {
						return new Message(false, "系统发生异常，请稍后再试");
					}
					return new Message(true, "图片处理成功");
				}
			}
		} catch (Exception e) {
			logger.info("上传图片出现异常【{}】", e.getMessage());
			e.printStackTrace();
			result.setResult(false);
			result.setInfo("系统繁忙");
		} finally {
			if (in != null) {
				in.close();
			}
		}
		return result;
	}
*/

    /**
     * 上传图片到阿里云服务器
     *
     * @param fileName
     * @param in
     * @return
     */
    public Message ossFileUpload(String fileName, InputStream in) {
        try {
            if (in == null) {
                logger.info("从微信端下载图片失败");
                return new Message(false, "图片上传失败，请重新上传图片信息");
            }
            String flag = OSSUtil.uploadFileByInputStream(PropertiesConstants.OSSENDPOINT, PropertiesConstants.OSSID, PropertiesConstants.OSSKEY, PropertiesConstants.BUCKETNAME, in, PropertiesConstants.OSSPATH + fileName);
            if ("success".equals(flag)) {
                return new Message(true, "阿里云上传成功");
            }
            return new Message(false, "阿里云上传失败");
        } catch (Exception ex) {
            logger.info("系统异常【{}】", ex);
            return new Message(false, "系统发生异常");
        }
    }
}
