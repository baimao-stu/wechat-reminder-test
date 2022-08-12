package com.jin.wechatReminder;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSON;
import com.jin.wechatReminder.builder.TemplateBuilder;
import com.jin.wechatReminder.config.WechatConfig;
import com.jin.wechatReminder.entity.WechatSendBody;
import com.jin.wechatReminder.service.WechatAccessTokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.stereotype.Service;

/**
 * @author admin
 */
@SpringBootApplication
@EnableScheduling
public class WechatReminderApplication {

    private static final Logger logger = LogManager.getLogger(WechatReminderApplication.class);


    @Autowired
    private TemplateBuilder templateBuilder;

    @Autowired
    private WechatConfig wechatConfig;

    @Autowired
    private WechatAccessTokenService accessTokenService;

    public static void main(String[] args) throws Exception {
        SpringApplication.run(WechatReminderApplication.class, args);
    }

    /**
     * 项目启动完后测试一下
     */
    @Service
    public class testSend implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            if (contextRefreshedEvent.getApplicationContext().getParent() == null) {// 保证只执行一次
                WechatSendBody wechatSendBody = templateBuilder.buildMorningTemplate();
                wechatSendBody.setTouser("oQoJI68eGRrYXIcZcY-0HKXsnfRM");
                String body = JSON.toJSONString(wechatSendBody);
                logger.info("send message data:" + body);
                String sendUrl = wechatConfig.getSendUrl() + accessTokenService.getAccessToken();
                logger.info("send message sendUrl:" + sendUrl);
                HttpResponse response = HttpRequest.post(sendUrl)
                        .body(body).execute();
                logger.info("send message response:" + response.toString());
            }
        }
    }
}
