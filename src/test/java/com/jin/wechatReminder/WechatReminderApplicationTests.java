package com.jin.wechatReminder;

import com.jin.wechatReminder.service.WechatAccessTokenService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class WechatReminderApplicationTests {
    @Autowired
    private WechatAccessTokenService service;

    @Test
    void contextLoads() {
    }


    @Test
    void testAccessToken(){
        service.getAccessToken();
    }

}
