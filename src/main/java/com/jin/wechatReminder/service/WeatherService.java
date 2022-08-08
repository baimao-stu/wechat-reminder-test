package com.jin.wechatReminder.service;

import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSONObject;
import com.jin.wechatReminder.config.ToUserInfoConfig;
import com.jin.wechatReminder.entity.Weather;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * @author admin
 * @create 2022/8/7 21:04
 */
@Service
public class WeatherService {

    private static final String APP_ID = "12917378";
    private static final String APP_SECRET = "5Vc0YjTj";
    private static final String REQ_URL = "https://v0.yiketianqi.com/free/day";


    @Autowired
    private ToUserInfoConfig userInfo;

    public Weather getWeather() {
        return getWeather(userInfo);
    }

    /**
     * 根据用户的地区获取天气信息
     *
     * @param userInfo 用户
     * @return 天气信息
     */
    public Weather getWeather(ToUserInfoConfig userInfo) {
        int times = 0;
        HttpRequest request = HttpRequest.get(REQ_URL + "?appid=" + APP_ID + "&appsecret=" + APP_SECRET + "&city=" + userInfo.getCity() +
                "&cityId=" + userInfo.getCityId() + "&unescape=1");
        HttpResponse response = request.execute();
        while ((Objects.isNull(response) || !response.isOk()) && times <= 3) {
            ThreadUtil.safeSleep(1000);
            response = request.execute();
            times++;
        }
        return Objects.nonNull(response) && response.isOk() ? JSONObject.parseObject(response.body(), Weather.class) : null;
    }
}
