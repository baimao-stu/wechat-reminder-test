package com.baimao.wechatReminder;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSON;
import com.baimao.wechatReminder.builder.TemplateBuilder;
import com.baimao.wechatReminder.config.WechatConfig;
import com.baimao.wechatReminder.entity.WechatSendBody;
import com.baimao.wechatReminder.service.WechatAccessTokenService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
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

    @Value("#{'${openids}'.split(',')}")
    String[] openids;

    public static void main(String[] args){
        SpringApplication.run(WechatReminderApplication.class, args);
    }

    /**
     * 项目启动完后测试一下
     */
    @Service
    public class testSend implements ApplicationListener<ContextRefreshedEvent> {
        @Override
        public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
            // 保证只执行一次
            if (contextRefreshedEvent.getApplicationContext().getParent() == null) {

//                for(String openId:openids) {
                    /**指定测试用户的id*/
                    WechatSendBody wechatSendBody = templateBuilder.buildMorningTemplate(openids[0]);
                    String body = JSON.toJSONString(wechatSendBody);
                    logger.info("send message data:{}",body);
                    String sendUrl = wechatConfig.getSendUrl() + accessTokenService.getAccessToken();
                    logger.info("send message sendUrl:{}",sendUrl);
                    HttpResponse response = HttpRequest.post(sendUrl)
                            .body(body).execute();
                    logger.info("send message response:{}{}",System.lineSeparator(),response.toString());


                    /**宵夜测试*/
                    wechatSendBody = templateBuilder.buildSnackTemplate(openids[0]);
                    body = JSON.toJSONString(wechatSendBody);
                    response = HttpRequest.post(sendUrl)
                            .body(body).execute();
                    logger.info("send message response:{}{}",System.lineSeparator(),response.toString());
//                }
            }
        }
    }
}
