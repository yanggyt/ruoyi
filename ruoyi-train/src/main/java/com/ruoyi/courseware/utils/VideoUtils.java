package com.ruoyi.courseware.utils;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;

public class VideoUtils {

    // 设置AccessKey ID和AccessKey secret
    private static String access_key_id = "LTAILL4H4JcoUJdf";
    private static String access_key_secret = "6TtJ1MD7ueolOXWjx0VhaseX6nkPVe ";

    public static void main(String[] args) {
        // 点播服务所在的地域，中国大陆地域请填cn-shanghai
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", access_key_id, access_key_secret);
        DefaultAcsClient client = new DefaultAcsClient(profile);
        // 传入视频ID
        GetVideoPlayAuthResponse response = getVideoPlayAuth(client, "3dbb151b73c34b678efff61d3d50d999");
        System.out.println(response.getPlayAuth());
    }

    private static GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client, String videoId) {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId(videoId);
        GetVideoPlayAuthResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            throw new RuntimeException("GetVideoPlayAuthRequest Server failed");
        } catch (ClientException e) {
            throw new RuntimeException("GetVideoPlayAuthRequest Client failed");
        }
        return response;
    }

}
