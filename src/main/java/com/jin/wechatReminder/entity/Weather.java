package com.jin.wechatReminder.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author admin
 * @create 2022/8/7 20:58
 */

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Weather {
    /**
     * 今日实时请求次数
     */
    private Long nums;

    /**
     * 空气质量
     */
    private String air;
    /**
     * 城市名称
     */
    private String city;
    /**
     * 城市ID
     */
    private String cityid;
    /**
     * 日期
     */
    private String date;
    /**
     * 湿度
     */
    private String humidity;
    /**
     * 气压
     */
    private String pressure;
    /**
     * 实况温度
     */
    private String tem;
    /**
     * 白天温度(高温)
     */
    private String tem_day;
    /**
     * 夜间温度(低温)
     */
    private String tem_night;
    /**
     * 更新时间
     */
    private String update_time;
    /**
     * 天气情况
     */
    private String wea;
    /**
     * 天气标识
     */
    private String wea_img;
    /**
     * 今日实时请求次数
     */
    private String week;
    /**
     * 风向
     */
    private String win;
    /**
     * 风速
     */
    private String win_meter;
    /**
     * 风力
     */
    private String win_speed;
}
