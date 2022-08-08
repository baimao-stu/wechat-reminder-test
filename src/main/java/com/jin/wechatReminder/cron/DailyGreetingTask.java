package com.jin.wechatReminder.cron;

import cn.hutool.core.date.ChineseDate;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSON;
import com.jin.wechatReminder.builder.TemplateBuilder;
import com.jin.wechatReminder.config.ToUserInfoConfig;
import com.jin.wechatReminder.config.WechatConfig;
import com.jin.wechatReminder.service.WechatAccessTokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.Objects;

/**
 * @author admin
 * @create 2022/8/7 05:16
 */

@Component
@EnableScheduling
public class DailyGreetingTask {

    private static final Logger logger = LogManager.getLogger(DailyGreetingTask.class);
    @Autowired
    private WechatConfig wechatConfig;

    @Autowired
    private ToUserInfoConfig userInfoConfig;
    @Autowired
    private WechatAccessTokenService accessTokenService;

    @Autowired
    private TemplateBuilder templateBuilder;

    @Scheduled(cron = "0 20 06 ? * *")
    private void morning() {
        String body = JSON.toJSONString(templateBuilder.buildMorningTemplate());
        logger.info("send message data:" + body);
        String sendUrl = wechatConfig.getSendUrl() + accessTokenService.getAccessToken();
        logger.info("send message sendUrl:" + sendUrl);
        HttpResponse response = HttpRequest.post(sendUrl)
                .body(body).execute();
        logger.info("send message response:" + response.toString());
    }

    /**
     * 特殊的纪念日处理
     */
    @Scheduled(cron = "0 00 08 ? * *")
    private void special() {
        this.birthday();
        this.weddingAnniversary();
    }


    /**
     * 判断是否需要发送生日提醒
     */
    private void birthday() {
        ChineseDate birthday = new ChineseDate(userInfoConfig.getBirthday());
        ChineseDate now = new ChineseDate(LocalDate.now());
        if (!Objects.equals(birthday.getChineseMonth(), now.getChineseMonth()) ||
                !Objects.equals(birthday.getChineseDay(), now.getChineseDay())) {
            return;
        }
        String body = JSON.toJSONString(templateBuilder.buildBirthdayTemplate());
        logger.info("send message data:" + body);
        String sendUrl = wechatConfig.getSendUrl() + accessTokenService.getAccessToken();
        logger.info("send message sendUrl:" + sendUrl);
        HttpResponse response = HttpRequest.post(sendUrl)
                .body(body).execute();
        logger.info("send message response:" + response.toString());
    }


    /**
     * 判断是否需要发送纪念日模板
     */
    private void weddingAnniversary() {
        LocalDate now = LocalDate.now();
        LocalDate loveDay = userInfoConfig.getLoveDay();
        if (now.getMonth() == loveDay.getMonth() && now.getDayOfMonth() == loveDay.getDayOfMonth()) {
            String body = JSON.toJSONString(templateBuilder.buildWeddingAnniversaryTemplate());
            logger.info("send message data:" + body);
            String sendUrl = wechatConfig.getSendUrl() + accessTokenService.getAccessToken();
            logger.info("send message sendUrl:" + sendUrl);
            HttpResponse response = HttpRequest.post(sendUrl)
                    .body(body).execute();
            logger.info("send message response:" + response.toString());
        }
    }
}
