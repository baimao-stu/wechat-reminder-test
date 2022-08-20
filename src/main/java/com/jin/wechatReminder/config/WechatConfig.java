package com.jin.wechatReminder.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author admin
 * @create 2022/8/7 14:26
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component("wechatConfigBuilder")
public class WechatConfig {

    private String appId;
    private String appSecret;
    private String accessUrl;
    private String sendUrl;

    @Bean
    @Primary
    public WechatConfig wechatConfig() {
        final String appId = "个人测试号的appId";
        final String appSecret = "个人测试号的appSecret";
        final String accessUrl = "https://api.weixin.qq.com/cgi-bin/token";
        final String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
        return new WechatConfig(appId, appSecret, accessUrl, sendUrl);
    }
}
