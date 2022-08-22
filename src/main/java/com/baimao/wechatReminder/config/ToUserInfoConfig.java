package com.baimao.wechatReminder.config;

import lombok.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

/**
 * @author admin
 * @create 2022/8/7 14:27
 */

@Data
@Getter
@Setter
@NoArgsConstructor
@Component("toUserInfoConfigBuilder")
public class ToUserInfoConfig {

    private String openId;

    private LocalDate schoolDay;

    private LocalDate loveDay;

    private LocalDate birthday;

    private String city;

    private String cityId;

    @Value("#{'${openids}'.split(',')}")
    String[] openids;

    public ToUserInfoConfig(String openId, LocalDate schoolDay, LocalDate loveDay, LocalDate birthday, String city, String cityId) {
        this.openId = openId;
        this.schoolDay = schoolDay;
        this.loveDay = loveDay;
        this.birthday = birthday;
        this.city = city;
        this.cityId = cityId;
    }

    public ToUserInfoConfig(String[] openids, LocalDate schoolDay, LocalDate loveDay, LocalDate birthday, String city, String cityId) {
        this.schoolDay = schoolDay;
        this.loveDay = loveDay;
        this.birthday = birthday;
        this.city = city;
        this.cityId = cityId;
        this.openids = openids;
    }

    /**
     * 推送多个对象
     * @return
     */
    @Bean
    @Primary
    public ToUserInfoConfig toUserInfoConfig() {
        /**推送的的对象 */
        final LocalDate loveDay = LocalDate.of(2022, 8, 22);
        final LocalDate birthday = LocalDate.of(1997, 10, 15);

        final LocalDate schoolDay = LocalDate.of(2022, 8, 29);
        final String city = "汕头";
        final String cityId = "101280501";
//        return new ToUserInfoConfig(openids[0], schoolDay,loveDay, birthday, city, cityId);
        return new ToUserInfoConfig(openids, schoolDay,loveDay, birthday, city, cityId);
    }

    /**
     * 单个对象
     * @return
     */
//    @Bean
//    @Primary
//    public ToUserInfoConfig toUserInfoConfig() {
//        /**推送的的对象 */
//        final String openId = "oYS9T6Ht2poRgl4I9MYnu0QjakWc";
//        final LocalDate loveDay = LocalDate.of(2022, 8, 22);
//        final LocalDate birthday = LocalDate.of(1997, 10, 15);
//
//        final LocalDate schoolDay = LocalDate.of(2022, 8, 29);
//        final String city = "汕头";
//        final String cityId = "101280501";
//        return new ToUserInfoConfig(openId, schoolDay,loveDay, birthday, city, cityId);
//    }
}
