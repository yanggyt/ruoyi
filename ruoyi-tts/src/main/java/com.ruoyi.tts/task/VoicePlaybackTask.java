package com.ruoyi.tts.task;

import com.ruoyi.tts.exception.*;
import com.ruoyi.tts.jacob.VoicePlayback;
import com.ruoyi.tts.serial.SerialTool;
import com.ruoyi.tts.utils.ByteUtils;
import gnu.io.NoSuchPortException;
import gnu.io.PortInUseException;
import gnu.io.SerialPort;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.TooManyListenersException;

/**
 * @author GideonYeung
 * @date 2020/6/24 11:42
 */
@Slf4j
@Component
@Configuration
@EnableScheduling
public class VoicePlaybackTask {

    @Autowired
    private RestTemplate restTemplate;

    // 串口对象
    private SerialPort mSerialport;

    /**
     * 2秒播放一次语音
     */
    @Scheduled(fixedDelay = 3000)
    private void configureTasks() throws SerialPortParameterFailureException, SerialPortOutputStreamCloseFailureException, NotSerialPortException, PortInUseException, NoSuchPortException, TooManyListenersException {
        Map<String, Boolean> nodeValueMap = new HashMap<>();
        try {
            nodeValueMap = restTemplate.getForObject("http://127.0.0.1/icloud/nodeValue/getWmKey", Map.class);
        } catch (HttpClientErrorException e) {
            log.error("icloud暂无开启，无法获取数据。");
        }
        boolean contains = nodeValueMap.values().contains(true);
        byte[] bs;
        SerialPort port = null;
        if (contains) {
            port = SerialTool.openPort("COM1", 9600);
            //设定发送字符串
            bs = ByteUtils.hex2Bytes("5501110000000168");
            //打开串口
            SerialTool.sendToPort(port, bs);
        }
        nodeValueMap.forEach((key, value) -> {
            if (value) {
                String message = key + "号机按下广播通知，请及时处理。";
                log.info(message);
                VoicePlayback.textToSpeech(message);
            }
        });
        if (contains) {
            //设定发送字符串
            bs = ByteUtils.hex2Bytes("5501120000000169");
            //关闭串口
            SerialTool.sendToPort(port, bs);
            SerialTool.closePort(port);
        }
    }
}
