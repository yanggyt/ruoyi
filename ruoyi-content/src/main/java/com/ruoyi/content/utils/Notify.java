package com.ruoyi.content.utils;

import org.apache.commons.codec.digest.DigestUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.*;
import java.util.Map.Entry;

public class Notify {
    private static final String SIGN = "signature";

    public static String sendMessage(String url, String uid, String key, String reqData)
            throws Exception {
        return toNotifySend(url, reqData, uid, key);
    }

    public static String toNotifySend(String url, String data, String uid, String key) {
        Map pMap = new HashMap();
        pMap.put("data", data);
        String reStr = post2NotifyByTest(url, pMap, uid, key);
        return reStr;
    }

    private static String post2NotifyByTest(String url, Map<String, String> map, String uid, String md5Key) {
        CloseableHttpClient httpclient = null;
        CloseableHttpResponse response = null;
        String result = "";
        try {
            map.put("uid", uid);

            String timestamp = String.valueOf(System.currentTimeMillis());
            map.put("timestamp", timestamp);

            String nonce = "";
            map.put("nonce", nonce);

            String params = getLinkString(map);

            String signature = sign(params, md5Key, "UTF-8");
            map.put("signature", signature);

            List nvps = new ArrayList();
            for (Entry entry : map.entrySet()) {
                nvps.add(new BasicNameValuePair((String) entry.getKey(), (String) entry.getValue()));
            }

            HttpEntity requestEntity = new UrlEncodedFormEntity(nvps, "UTF-8");

            HttpPost httpPost = new HttpPost(url);
            httpPost.setEntity(requestEntity);

            int connectTimeout = 200000;

            int socketTimeout = 200000;
            RequestConfig requestConfig = RequestConfig.custom().setConnectTimeout(connectTimeout).setSocketTimeout(socketTimeout).build();
            httpPost.setConfig(requestConfig);

            httpclient = HttpClients.createDefault();
            response = httpclient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            if (200 == response.getStatusLine().getStatusCode()) {
                result = EntityUtils.toString(responseEntity, "UTF-8").trim();
            }

            String str1 = result;
            return str1;
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    response.close();
                } catch (IOException localIOException4) {
                }
            }
            if (httpclient != null) {
                try {
                    httpclient.close();
                } catch (IOException localIOException5) {
                }
            }
        }
        return result;
    }

    private static String getLinkString(Map<String, String> sArray) {
        Map sPara = paraFilter(sArray);

        String prestr = createLinkString(sPara);
        return prestr;
    }

    private static Map<String, String> paraFilter(Map<String, String> sArray) {
        Map result = new HashMap();

        if ((sArray == null) || (sArray.size() <= 0)) {
            return result;
        }
        for (String key : sArray.keySet()) {
            String value = (String) sArray.get(key);
            if ((value == null) || (value.equals("")) || ("signature".equalsIgnoreCase(key))) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }

    private static String createLinkString(Map<String, String> params) {
        List keys = new ArrayList(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = (String) keys.get(i);
            String value = (String) params.get(key);

            if (i == keys.size() - 1) {
                prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    public static String sign(String text, String key, String input_charset) {
        text = text + key;
        return DigestUtils.md5Hex(getContentBytes(text, input_charset));
    }

    private static byte[] getContentBytes(String content, String charset) {
        if ((charset == null) || ("".equals(charset))) {
            return content.getBytes();
        }
        try {
            return content.getBytes(charset);
        } catch (UnsupportedEncodingException e) {
        }
        throw new RuntimeException("MD5签名过程中出现错误,指定的编码集不对,您目前指定的编码集是:" + charset);
    }
}