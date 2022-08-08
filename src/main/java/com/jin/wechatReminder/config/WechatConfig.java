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
        final String appId = "wxef6401cd07caa58a";
        final String appSecret = "cdb4900ab4132a9b00497e5f545db7ba";
        final String accessUrl = "https://api.weixin.qq.com/cgi-bin/token";
        final String sendUrl = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token=";
        return new WechatConfig(appId, appSecret, accessUrl, sendUrl);
    }
}
