package com.ruoyi.courseware.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.exceptions.ServerException;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthRequest;
import com.aliyuncs.vod.model.v20170321.GetVideoPlayAuthResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/train/courseware/video")
public class VideoController {

    @GetMapping("")
    public GetVideoPlayAuthResponse authority(){
        DefaultProfile profile = DefaultProfile.getProfile("cn-shanghai", "LTAIo1i5PQB4pFme", "NyiVe3pqbVOMnwMQxnOkV39KrTx2jR");
        DefaultAcsClient client = new DefaultAcsClient(profile);
        GetVideoPlayAuthResponse response = getVideoPlayAuth(client);
        System.out.println(response.getPlayAuth());
        System.out.println(response.getVideoMeta());
        return response;
    }


    private GetVideoPlayAuthResponse getVideoPlayAuth(DefaultAcsClient client) {
        GetVideoPlayAuthRequest request = new GetVideoPlayAuthRequest();
        request.setVideoId("ID3dbb151b73c34b678efff61d3d50d999");
        GetVideoPlayAuthResponse response = null;
        try {
            response = client.getAcsResponse(request);
        } catch (ServerException e) {
            throw new RuntimeException("GetVideoPlayAuthRequest Server failed");
        } catch (ClientException e) {
            throw new RuntimeException("GetVideoPlayAuthRequest Client failed");
        }
        response.getPlayAuth();              // 播放凭证
        response.getVideoMeta();             // 视频Meta信息
        return response;
    }
}
