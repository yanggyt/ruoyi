package com.ruoyi.content.utils;

import com.gargoylesoftware.htmlunit.*;
import com.gargoylesoftware.htmlunit.html.HtmlPage;
import com.ruoyi.content.constants.PropertiesConstants;
import com.ruoyi.content.domain.OssDTO;
import com.ruoyi.content.message.Message;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 爬虫获取文章内容和图片
 *
 * @author ZMN
 */

public class GetMessageFromArticle {
    private final static Logger logger = LoggerFactory.getLogger(GetMessageFromArticle.class);

    public static Message getArticleListFromUrl(String listurl, OssDTO ossDTO, String author, String ids) {
        Message msg = new Message();
        Map<String, String> resultMap = new HashMap<String, String>();
        msg.setResult(true);
        msg.setInfo("抓取文章成功！");
        Document doc = null;
        try {
//			doc = Jsoup.connect(listurl).userAgent("Mozilla/5.0").timeout(3000).post();
            doc = Jsoup.connect(listurl).userAgent("Mozilla/5.0").timeout(3000).get();
            //doc= Jsoup.parse(httpMethod(listurl));
            //doc = getDocument(listurl);
            logger.info("-----------:" + doc);
            Thread.sleep(2000);
        } catch (Exception e) {
            logger.info("获取文章地址错误【{}】,url【{}】", e.getMessage(), listurl);
            e.printStackTrace();
            msg.setResult(false);
            msg.setInfo("获取文章信息失败！");
            return msg;
        }
        // 如果通过传过来的地址拿不到文章，直接返回
        if (doc == null) {
            logger.info("创建文章，没有拿到远程文章内容，文章url【{}】", listurl);
            msg.setResult(false);
            msg.setInfo("没有获取到相应的文章内容，请重试！");
            return msg;
        }
        //去掉临时链接标签
        doc.select("div.rich_media_global_msg").remove();
        doc.select("#js_article").attr("class", "rich_media");
        //去掉原文章中的 ‘阅读原文’
//		doc.getElementById("js_toobar3").remove();
//		Elements remove = doc.getElementsByClass("rich_media_tool").remove();
        //替换阅读原文跳转地址
        String docString = doc.getAllElements().get(0).toString();
        String msg_source_url = docString.split("var msg_source_url = '")[1].split("';")[0];
        doc.select("#js_view_source").attr("href", msg_source_url).attr("style", "font-size: 14px");
        //去掉原文章中的‘文章已于...修改’
        doc.select("div.article_modify_area.tips_global_primary.article_modify_area_primary").remove();
        //获取分享信息和标题信息
        resultMap = getArticleTitle(doc, ossDTO);
        Element element = doc.getElementById("js_article");//获取文章内容,包含标题
//		String title = doc.getElementById("activity-name").toString();
        Element contentElement = doc.getElementById("js_content");// 只要正文，不要标题
        System.out.println(contentElement);
        contentElement.attr("style", "");
        // Elements spans = element.getElementsByTag("span");// 找到所有xx标签 ，以便拿文字
        // 判断是否有正文内容
        if (element == null) {
            logger.info("创建文章，没有拿到文章内容，文章url【{}】", listurl);
            msg.setResult(false);
            msg.setInfo("没有获取到文章内容，请重试！");
            return msg;
        }
//		element.getElementById("post-user").textNodes().get(0).text(author);	//设置作者
//		element.getElementById("post-user").removeAttr("href");			//移除属性
//		element.getElementById("post-user").attr("href", "");			//设置属性
        Elements imgs = element.getElementsByTag("img");// 拿到所有的图片，以便保存
        Elements style = doc.getElementsByTag("style");// 拿到所有的图片，以便保存
        // 判断是否有视频
        Elements mpvoice = element.getElementsByTag("iframe");// 拿到所有的视频
        if (mpvoice != null) {
//		if (null != null) {
            logger.info("当前存在视频");
            for (Element ele : mpvoice) {
                try {
                    String videoUrls = "";
                    String dataSrc = ele.attr("data-src");// 拿到视频的链接
                    logger.info("拿到视频的链接" + dataSrc);
                    if (dataSrc.indexOf("vid=") > -1) {


//						String[] arr = dataSrc.split("&");
//						String src = arr[0];
//						String width = arr[0].split("=")[1];
//						String height = arr[1];


                        HashMap hashMap = new HashMap();
                        int indexOf = dataSrc.indexOf("?");
                        String dataSrcData = dataSrc.substring(indexOf + 1, dataSrc.length());
                        String[] dataSplit = dataSrcData.split("&");
                        for (int i = 0; i < dataSplit.length; i++) {
                            String string = dataSplit[i];
                            String[] split = string.split("=");
                            hashMap.put(split[0], split[1]);
                        }
                        String width = "" + hashMap.get("width");
                        String height = "" + hashMap.get("height");
//						if(StringUtils.isBlank(width) && StringUtils.isBlank(height) && width.equals("null") && height.equals("null")) {
//							width = "10000px";
//							height = "10000px";
//						}
                        //height= heightArr[1]
                        if (StringUtils.isNotBlank(dataSrcData)) {
//							String[] widthArr = width.split("=");
//							String[] heightArr = height.split("=");
//							String[] urlArr = src.split("vid=");
//							String[] urlArr = src.split("=");
                            String vid = "" + hashMap.get("vid");

                            String videoSrc = "https://v.qq.com/iframe/player.html?vid=";
                            //默认视频连接为.html ，存在mp4 的改为php的视频连接
                            if (vid.indexOf("wxv") > -1) {
//								videoSrc = "https://mp.weixin.qq.com/mp/readtemplate?t=pages/video_player_tmpl&action=mpvideo&vid=";
                                videoSrc = "http://video.kmphp.kuaizhanye.com/video.php?vid=";
                            }

                            logger.info("视频页面地址为：" + videoSrc);
                            String js = "<script type=\"text/javascript\"> var iframe = document.getElementsByTagName(\"iframe\");"
                                    + "for (var i = 0; i < iframe.length; i++) {"
                                    + " iframe[i].style.width = document.body.clientWidth + \"px\";"
                                    + " iframe[i].style.height = document.body.clientWidth * 0.5625 + \"px\";}</script>";
                            String videoUrl = "<iframe frameborder=\"0\" width=\"" + width + "\" height=\""
                                    + height + "\" src=\"" + videoSrc + vid
//									+ hashMap.get("height") + "\" src=\"https://v.qq.com/iframe/player.html?vid=" +  hashMap.get("vid")
                                    + "&tiny=0&auto=0\" allowfullscreen></iframe>";
                            logger.info("截取文章视频vid【{}】，生成后的视频地址【{}】", vid, videoUrl);
                            videoUrls = js + videoUrl;
                            // 替换原来的视频链接
                            ele.before(videoUrls);
                            ele.remove();
                        }
                    }
                } catch (Exception e) {
                    logger.info("文章获取视频异常【{}】", e.getMessage());
                    e.printStackTrace();
                }
            }
        } else {
            logger.info("不存在视频");
            msg.setResult(true);
            msg.setInfo("抓取文章成功！");
        }

        logger.info("音频处理中");
        Elements voiceEle = element.getElementsByTag("mpvoice");// 拿到所有的音频
        if (voiceEle != null) {
            // if (null != null) {
            logger.info("当前存在音频");
            for (Element ele : voiceEle) {
                String downloadSrc = "https://res.wx.qq.com/voice/getvoice?mediaid=";
                String ossUrl = "";
                String mediaid = ele.attr("voice_encode_fileid");
                //播放长度
                String playLength = ele.attr("play_length");
                if (StringUtils.isBlank(playLength)) {
                    playLength = "0";
                }
                //音频名称
                String name = ele.attr("name");
				/*if (!name.contains(".mp3")) {
					name = name + ".mp3";
				}*/
                if (StringUtils.isNotBlank(mediaid)) {
                    try {
                        logger.info("下载音频到oss:【{}】【{}】", ossDTO.getBucketName(), ossDTO.getOssPath());
                        ossUrl = download(downloadSrc + mediaid, name, "voice/", ossDTO);
                        logger.info("oss返回音频地址：" + ossUrl);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String tag = "<div class=\"audio_box_\">" +
                            "  <h3>" + name + "</h3>" +
                            "<audio id=\"audio_\" style=\"width: 0;height: 0\" src=\"" + ossUrl + "\" controls=\"controls\"></audio>" +
                            "  <div class=\"ProcessAllBox\">" +
                            "    <div class=\"Process\">" +
                            "      <div class=\"ProcessAll\">" +
                            "        <div class=\"ProcessNow\">" +
                            "        </div>" +
                            "        <div class=\"ProcessNow_circular\">" +
                            "        </div>" +
                            "      </div>" +
                            "      <div class=\"play_\" id=\"play_\">" +
                            "        <span aria-labelledby=\"播放开关\" class=\"audio_card_switch\"><em class=\"article_audio_btn\" role=\"button\"></em></span>" +
                            "      </div>" +
                            "    </div>" +
                            "    <div class=\"show_time_\">" +
                            "      <span class=\"real_time\">00:00</span>" +
                            "      <span class=\"all_time\">" + playLength + "</span>" +
                            "    </div>" +
                            "  </div>";
//					String tag = "<div class=\"audio_box_\"><h3>" + name + "</h3><audio src=\""+ ossUrl +"\" controls=\"controls\" ></audio></div>";
                    ele.before(tag);
                    ele.remove();
                }
            }
        }
        logger.info("音频处理结束");


        // 判断是否有图片
        String body = style + element.toString().replace("amp;", "");

        if (imgs != null) {
//		if (null != null) {
            // 循环存储拿到的图片
            for (Element img : imgs) {
                try {
                    // 原文中的data-src
                    String oldImg = "data-src=\"" + img.attr("data-src") + "\"";

                    // 判断图片的格式
                    int begin = img.attr("data-src").indexOf("_") + 1;
                    int end = img.attr("data-src").indexOf("_") + 4;
                    String styles = img.attr("data-src").substring(begin, end);

                    String imgType = ".jpg";
                    if (styles.equals("jpg")) {
                        imgType = ".jpg";
                    } else if (styles.equals("png")) {
                        imgType = ".png";
                    } else if (styles.equals("gif")) {
                        imgType = ".gif";
                    } else if (styles.equals("bmp")) {
                        imgType = ".bmp";
                    } else if (styles.equals("jpeg")) {
                        imgType = ".jpeg";
                    } else if (styles.equals("svg")) {
                        imgType = ".svg";
                    }

                    // 保持原图片名字不变
                    String str = "";
                    String strEnd = "";
                    String imageName = "";
                    if (img.toString().contains("?")) {

                        str = img.toString().substring(0, img.toString().indexOf("?"));
                        strEnd = str.toString().substring(0, str.toString().lastIndexOf("/"));
                        imageName = strEnd.substring(strEnd.lastIndexOf("/") + 1, strEnd.length());
                    } else {
                        str = img.toString().substring(0, img.toString().lastIndexOf("/"));
                        imageName = str.substring(str.lastIndexOf("/") + 1, str.length());
                    }
                    // 开始保存
                    String imgUrl = download(img.attr("data-src"), imageName + imgType, PropertiesConstants.ONLINE_IMG_PATH,
                            ossDTO);
                    if (StringUtils.isNotBlank(imgUrl)) {
                        // 最后生成可访问的src
                        String newImgUrl = " src=\"" + imgUrl + "\" ";
                        body = body.replace(oldImg, newImgUrl);

//						String[] split = body.split("<script");
//						for (String string : split) {
//							int endIndex = string.indexOf("/script>");
//							if (endIndex!=-1) {
//								endIndex+=8;
//								String substring = "<script"+string.substring(0,endIndex);
//								body.replace(substring, "");
//							}
//						}
                        if (body.indexOf(img.attr("data-src")) > -1) {
                            logger.info("当前还存在相同图片地址的数据，可能为图片放盗，再次替换");
                            body = body.replace(img.attr("data-src"), imgUrl);// 替换可能存在图片验证的地址
                        }
                    }
                } catch (Exception e) {
                    logger.info("保存图片失败！！！【{}】", e.getMessage());
                    e.printStackTrace();
                }
            }
        }

        // Elements mpvoice = element.getElementsByTag("embed");// 拿到所有的视频
        // 处理样式中存在的图片
        Elements section = element.getElementsByTag("section");// 拿到所有的图片，以便保存
        if (section != null) {
            for (Element ele : section) {
                String styles = ele.attr("style");
                String regEx = "http(s)?://(.)*(gif|png|jpg|bmp|jpeg)";
                Pattern p = Pattern.compile(regEx);
                Matcher m = p.matcher(styles);
                if (m.find()) {
                    String imgUrl = m.group();
                    logger.info("imgUrl的值【{}】", imgUrl);

                    String imgType = "";
                    if (imgUrl.indexOf("=") > -1) {
                        // 保持原图片名字不变
                        imgType = imgUrl.substring(imgUrl.lastIndexOf("="), imgUrl.length()).substring(1);
                        logger.info("imgType的值【{}】", imgType);
                    }
                    String imageName = "";
                    if (imgUrl.toString().indexOf("?") > -1) {
                        String str = imgUrl.toString().substring(0, imgUrl.toString().indexOf("?"));
                        String strEnd = str.toString().substring(0, str.toString().lastIndexOf("/"));
                        imageName = strEnd.substring(strEnd.lastIndexOf("/") + 1, strEnd.length());
                    }

                    // 开始保存
                    try {
                        if (StringUtils.isNotBlank(imgType) && StringUtils.isNotBlank(imageName)) {
                            String bgImgUrl = download(imgUrl, imageName + imgType,
                                    PropertiesConstants.ONLINE_IMG_PATH, ossDTO);
                            logger.info("bgImgUrl的值【{}】", bgImgUrl);
                            if (StringUtils.isNotBlank(bgImgUrl)) {
                                body = body.replace(imgUrl, bgImgUrl);
                            }
                        } else {
                            continue;
                        }
                    } catch (Exception e) {
                        logger.info("保存样式中的图片异常【{}】", e.getMessage());
                        e.printStackTrace();
                    }
                }
            }
        }
        String regEx = "http(s)?://(.)*(wx_fmt=png)";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(body);
        if (m.find()) {
            String imgUrl = m.group();
            // 保持原图片名字不变
            String imgType = imgUrl.substring(imgUrl.lastIndexOf("."), imgUrl.length());
            // 开始保存
            try {
                String bgImgUrl = download(imgUrl, RandomUtils.generateRandomCharAndNumber(8) + imgType,
                        PropertiesConstants.ONLINE_IMG_PATH, ossDTO);
                if (StringUtils.isNotBlank(bgImgUrl)) {
                    body = body.replace(imgUrl, bgImgUrl);
                }
            } catch (Exception e) {
                logger.info("保存样式中的图片异常【{}】", e.getMessage());
                e.printStackTrace();
            }
        }

//		File file = new File("D:\\a.html");
//		try {
//			FileOutputStream fileOutputStream = new FileOutputStream(file);
//			try {
//				fileOutputStream.write(body.getBytes());
//			} catch (IOException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
//		} catch (FileNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		body = title + body;


        try {
            body = HtmlCompressor.compress(body);
            resultMap.put("articleContent", body);
            resultMap.put("originalUrl", listurl);
            resultMap.put("adId", ids);
            msg.setObject(resultMap);
        } catch (Exception e) {
            logger.info("压缩html异常【{}】", e.getMessage());
            e.printStackTrace();
        }
        return msg;
    }

    // 保存图片到本地
    @SuppressWarnings("unchecked")
    public static String download(String urlString, String filename, String savePath, OssDTO ossDTO) throws Exception {
        String imgUrl = "";
        // 构造URL
        URL url = new URL(urlString);
        // 打开连接
        URLConnection conn = url.openConnection();
        // 设置请求超时为5s
        conn.setConnectTimeout(5 * 1000);
        // 通过输入流获取图片数据
        InputStream inStream = null;
        try {
            inStream = conn.getInputStream();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 文件上传OSS并生成可访问的链接
        Message msg = new Message();
        msg = FileUploadUtil.ossFileUpload(savePath + filename, inStream, ossDTO);
        // 关闭输入流
        inStream.close();
        if (msg.getResult()) {
            Map<String, Object> map = new HashMap<String, Object>();
            map = (Map<String, Object>) msg.getObject();
            imgUrl = map.get("url") + "";
        }
        logger.info("原始地址【{}】,保存图片后返回地址【{}】", urlString, imgUrl);
        return imgUrl;
    }

    public static Map<String, String> getArticleTitle(Document doc, OssDTO ossDTO) {
        Map<String, String> resultMap = new HashMap<String, String>();
        String element = doc.getAllElements().get(0).toString();
        String msg_title = element.split("var msg_title = '")[1].split("'.html")[0];
        String msg_desc = element.split("var msg_desc = \"")[1].split("\";")[0];
        if (msg_desc.contains("\\x0a")) {
            msg_desc = msg_desc.replace("\\x0a", "\n");
        }
        String msg_cdn_url = element.split("var msg_cdn_url = \"")[1].split("\";")[0];

        int begin = msg_cdn_url.indexOf("_") + 1;
        int end = msg_cdn_url.indexOf("_") + 4;
        String styles = msg_cdn_url.substring(begin, end);
        String imgType = ".jpg";
        if (styles.equals("jpg")) {
            imgType = ".jpg";
        } else if (styles.equals("png")) {
            imgType = ".png";
        } else if (styles.equals("gif")) {
            imgType = ".gif";
        } else if (styles.equals("bmp")) {
            imgType = ".bmp";
        } else if (styles.equals("jpeg")) {
            imgType = ".jpeg";
        }
        String str = msg_cdn_url.toString().substring(0, msg_cdn_url.toString().lastIndexOf("/"));
        String imageName = str.substring(str.lastIndexOf("/") + 1, str.length());
        try {
            String imgUrl = download(msg_cdn_url, imageName + imgType, PropertiesConstants.ONLINE_IMG_PATH,
                    ossDTO);
            if (StringUtils.isNotBlank(imgUrl)) {
                resultMap.put("shareImgUrl", imgUrl);
                resultMap.put("listPicUrl", imgUrl);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        resultMap.put("shareTitle", msg_title);
        resultMap.put("shareDes", msg_desc);
        resultMap.put("articleName", msg_title);
        return resultMap;
    }

    @SuppressWarnings({"unused", "unchecked"})
    public static void main(String[] args) {
        OssDTO ossDTO = new OssDTO();
        ossDTO.setBucketName("hx-cdn-int");
        ossDTO.setOssEndPoint("oss-cn-szfinance.aliyuncs.com");
        ossDTO.setOssEndPointOut("oss-cn-szfinance-internal.aliyuncs.com");
        ossDTO.setOssId("LTAIsVIQ8j9WueV2");
        ossDTO.setOssKey("Il5Y1tugBi9u8giGi9n4lQhAWwkcih");
        ossDTO.setOssPath("gv/YXPT");
//		msg = GetMessageFromArticle.getArticleListFromUrl(url,ossDTO);
//		String url = "https://mp.weixin.qq.com/s/ufsPXRlbmronWpAKnkSwNg";
//		String url = "http://mp.weixin.qq.com/s/neU3nXAj7EGVCLDyI8sZHA";
        String url = "https://mp.weixin.qq.com/s/kHx-OJ6BVHE7MXWFReUGGw";
        Message msg = getArticleListFromUrl(url, ossDTO, "", "");
        logger.info("返回的msg【{}】", JsonUtil.objectToJackson(msg));
//		JsonArray json = JsonUtil.objectToJackson(msg). 
        Map<String, String> resultMap = new HashMap<String, String>();
        resultMap = (Map<String, String>) msg.getObject();
    }


    public static Document getDocument(String url) throws IOException, InterruptedException {
		         /*String url="https://www.marklines.com/cn/vehicle_sales/search_country/search/?searchID=587200";
		         Connection connect = Jsoup.connect(url).userAgent("")
		                  .header("Cookie", "PLAY_LANG=cn; _plh=b9289d0a863a8fc9c79fb938f15372f7731d13fb; PLATFORM_SESSION=39034d07000717c664134556ad39869771aabc04-_ldi=520275&_lsh=8cf91cdbcbbb255adff5cba6061f561b642f5157&csrfToken=209f20c8473bc0518413c226f898ff79cd69c3ff-1539926671235-b853a6a63c77dd8fcc364a58&_lpt=%2Fcn%2Fvehicle_sales%2Fsearch&_lsi=1646321; _ga=GA1.2.2146952143.1539926675; _gid=GA1.2.1032787565.1539926675; _plh_notime=8cf91cdbcbbb255adff5cba6061f561b642f5157")
		                 .timeout(360000000);
		         Document document = connect.get();*/
        WebClient wc = new WebClient(BrowserVersion.CHROME);
        //启用JS解释器，默认为true
        wc.getOptions().setJavaScriptEnabled(true);
        //禁用CSS
        wc.getOptions().setCssEnabled(false);
        //js运行错误时，是否抛出异常
        wc.getOptions().setThrowExceptionOnScriptError(false);
        //状态码错误时，是否抛出异常
        wc.getOptions().setThrowExceptionOnFailingStatusCode(false);
        //是否允许使用ActiveX
        wc.getOptions().setActiveXNative(false);
        //等待js时间
        wc.waitForBackgroundJavaScript(6000 * 1000);
        //设置Ajax异步处理控制器即启用Ajax支持
        wc.setAjaxController(new NicelyResynchronizingAjaxController());
        //设置超时时间
        wc.getOptions().setTimeout(1000000);
        //不跟踪抓取
        wc.getOptions().setDoNotTrackEnabled(false);
        WebRequest request = new WebRequest(new URL(url));
        request.setAdditionalHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:62.0) Gecko/20100101 Firefox/62.0");
        try {
            //模拟浏览器打开一个目标网址
            HtmlPage htmlPage = wc.getPage(request);
            //为了获取js执行的数据 线程开始沉睡等待
            Thread.sleep(7000);//这个线程的等待 因为js加载需要时间的
            //以xml形式获取响应文本
            String xml = htmlPage.asXml();
            //并转为Document对象return
            return Jsoup.parse(xml);
            //System.out.println(xml.contains("结果.xls"));//false
        } catch (FailingHttpStatusCodeException e) {
            e.printStackTrace();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    public static String httpMethod(String url) {
        try {
            //创建client实例
            HttpClient client = HttpClients.createDefault();
            //创建httpget实例
            HttpGet httpGet = new HttpGet(url);
            Thread.sleep(3000);
            //执行 get请求
            HttpResponse response = client.execute(httpGet);
            Thread.sleep(3000);
            //返回获取实体
            HttpEntity entity = response.getEntity();
            Thread.sleep(3000);
            //获取网页内容，指定编码
            String web = EntityUtils.toString(entity, "UTF-8");
            //输出网页
            return web;


        } catch (Exception e) {

            e.printStackTrace();
            return null;
        }
    }


}
