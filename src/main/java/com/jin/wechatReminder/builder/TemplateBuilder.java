package com.jin.wechatReminder.builder;

import com.jin.wechatReminder.config.TemplateIdConfig;
import com.jin.wechatReminder.config.ToUserInfoConfig;
import com.jin.wechatReminder.entity.ParamDto;
import com.jin.wechatReminder.entity.Weather;
import com.jin.wechatReminder.entity.WechatSendBody;
import com.jin.wechatReminder.service.SweetWordService;
import com.jin.wechatReminder.service.WeatherService;
import com.jin.wechatReminder.utils.DataUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * @author admin
 * @create 2022/8/7 14:55
 */

@Component
public class TemplateBuilder {

    @Autowired
    private ToUserInfoConfig userInfoConfig;

    @Autowired
    private TemplateIdConfig templateIdConfig;

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private SweetWordService sweetWordService;

    /**
     * 构建一个每日提醒的推送内容
     *
     * @return 调用微信消息推送的body
     */
    public WechatSendBody buildMorningTemplate() {
        Map data = new HashMap();
        WechatSendBody wechatSendBody = new WechatSendBody(userInfoConfig.getOpenId(), templateIdConfig.getMorning(), data);
        data.put("today", new ParamDto(DataUtil.getNowDayAndWeekGregorian(), "#cc33cc"));
        data.put("love", new ParamDto(DataUtil.getDiffDays(userInfoConfig.getLoveDay()), "#ff3399"));
        data.put("birthday", new ParamDto(DataUtil.getBirthdayGregorian(userInfoConfig.getBirthday()), "#ff0033"));
        data.put("memorial", new ParamDto(DataUtil.getDiffDaysYears(userInfoConfig.getLoveDay()), "#66ff00"));
        Weather weather = weatherService.getWeather();
        data.put("weather", new ParamDto(weather.getWea(), "#33ff33"));
        data.put("temp", new ParamDto(weather.getTem_night() + "~" + weather.getTem_day(), "#0066ff"));
        data.put("humidity", new ParamDto(weather.getHumidity(), "#ff0033"));
        data.put("wind", new ParamDto(weather.getWin() + weather.getWin_speed() + " " + weather.getWin_meter(), "#3399ff"));
        data.put("word", new ParamDto(sweetWordService.getSweetWord(), "#8C8C8C"));
        if (DataUtil.isWorkDay() && (weather.getWea().contains("雨"))) {
            data.put("notice", new ParamDto(System.lineSeparator() + "小马虎请注意！今天有雨，记得带伞，注意安全，走路不要玩手机，到了公司报平安！", "#1cbbb4"));
        }
        return wechatSendBody;
    }

    /**
     * 构建一个生日提醒的推送内容
     *
     * @return 调用微信消息推送的body
     */
    public WechatSendBody buildBirthdayTemplate() {
        Map data = new HashMap();
        WechatSendBody wechatSendBody = new WechatSendBody(userInfoConfig.getOpenId(), templateIdConfig.getBirthday(), data);
        data.put("today", new ParamDto(DataUtil.getNowDayAndWeekGregorian(), "#cc33cc"));
        data.put("individual", new ParamDto(DataUtil.getTwoDayYears(userInfoConfig.getBirthday()), "#0066ff"));
        return wechatSendBody;
    }


    /**
     * 构建一个结婚纪念日提醒的推送内容
     *
     * @return 调用微信消息推送的body
     */
    public WechatSendBody buildWeddingAnniversaryTemplate() {
        Map data = new HashMap();
        WechatSendBody wechatSendBody = new WechatSendBody(userInfoConfig.getOpenId(), templateIdConfig.getWedding(), data);
        data.put("today", new ParamDto(DataUtil.getNowDayAndWeekGregorian(), "#cc33cc"));
        data.put("anniversary", new ParamDto(DataUtil.getTwoDayYears(userInfoConfig.getLoveDay()), "#0066ff"));
        return wechatSendBody;
    }
}
