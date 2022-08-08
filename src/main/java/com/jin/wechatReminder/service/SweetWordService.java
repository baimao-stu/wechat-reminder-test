package com.jin.wechatReminder.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.Objects;

/**
 * @author admin
 * @create 2022/8/7 21:30
 */

@Service
public class SweetWordService {

    private static final String BASE_URL = "https://api.shadiao.pro/chp";

    private static final String BASE_WORD = "今天没有骚话，只爱你！";


    public String getSweetWord() {
        HttpResponse response = HttpRequest.get(BASE_URL).execute();
        if (Objects.isNull(response) || !response.isOk()) {
            return BASE_WORD;
        }
        return (String) ((Map) JSONObject.parseObject(response.body(), Map.class).get("data")).get("text");
    }
}
