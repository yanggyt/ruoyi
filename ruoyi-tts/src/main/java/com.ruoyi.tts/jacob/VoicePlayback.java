package com.ruoyi.tts.jacob;


import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;

/**
 * @author GideonYeung
 * @date 2020/6/24 11:39
 */
public class VoicePlayback {
    /**
     * 文字转语音播放
     *
     * @param text 需要播放的文字
     */
    public static void textToSpeech(String text) {
        ActiveXComponent ax;
        try {
            ax = new ActiveXComponent("Sapi.SpVoice");

            // 运行时输出语音内容
            Dispatch spVoice = ax.getObject();
            // 音量 0-100
            ax.setProperty("Volume", new Variant(100));
            // 语音朗读速度 -10 到 +10
            ax.setProperty("Rate", new Variant(-2));
            // 执行朗读
            Dispatch.call(spVoice, "Speak", new Variant(text));

            // Dispatch.put(spVoice, "Volume", new Variant(100));
            // 设置朗读速度
            // Dispatch.put(spVoice, "Rate", new Variant(-2));
            // 构建文件流把生成语音文件
            // makefile(ax, spVoice, text);

            spVoice.safeRelease();
            ax.safeRelease();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // public static void makefile(Dispatch spVoice, String text) {
    // ActiveXComponent ax = new ActiveXComponent("Sapi.SpFileStream");
    // Dispatch spFileStream = ax.getObject();

    // ax = new ActiveXComponent("Sapi.SpAudioFormat");
    // Dispatch spAudioFormat = ax.getObject();

    // // 设置音频流格式
    // Dispatch.put(spAudioFormat, "Type", new Variant(22));
    // // 设置文件输出流格式
    // Dispatch.putRef(spFileStream, "Format", spAudioFormat);
    // // 调用输出 文件流打开方法，创建一个.wav文件
    // Dispatch.call(spFileStream, "Open", new Variant("./text.wav"), new
    // Variant(3), new Variant(true));
    // // 设置声音对象的音频输出流为输出文件对象
    // Dispatch.putRef(spVoice, "AudioOutputStream", spFileStream);
    // // 设置音量 0到100
    // Dispatch.put(spVoice, "Volume", new Variant(100));
    // // 设置朗读速度
    // Dispatch.put(spVoice, "Rate", new Variant(-2));
    // // 开始朗读
    // Dispatch.call(spVoice, "Speak", new Variant(text));

    // // 关闭输出文件
    // Dispatch.call(spFileStream, "Close");
    // Dispatch.putRef(spVoice, "AudioOutputStream", null);

    // spAudioFormat.safeRelease();
    // spFileStream.safeRelease();
    // }

    public static void main(String[] args) {
        textToSpeech("B54号机处于生产系统维护计划停机状态，使用模具B54试模模具生产产品31003522蓝色盖子T，耗时1827.5小时。处理方法为, 预计解决时长为0.5小时，负责人为林志峰");
    }
}
