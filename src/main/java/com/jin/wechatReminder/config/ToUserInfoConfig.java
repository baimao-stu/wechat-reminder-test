package com.jin.wechatReminder.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author admin
 * @create 2022/8/7 14:27
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component("toUserInfoConfigBuilder")
public class ToUserInfoConfig {

    private String openId;

    private LocalDate loveDay;

    private LocalDate birthday;

    private String city;

    private String cityId;


    @Bean
    @Primary
    public ToUserInfoConfig toUserInfoConfig() {
        final String openId = "待推送的userId";
        final LocalDate loveDay = LocalDate.of(2021, 7, 28);
        final LocalDate birthday = LocalDate.of(1997, 10, 15);
        final String city = "合肥";
        final String cityId = "101220101";
        return new ToUserInfoConfig(openId, loveDay, birthday, city, cityId);
    }
}
