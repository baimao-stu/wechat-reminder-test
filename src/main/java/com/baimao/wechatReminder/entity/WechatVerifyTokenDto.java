package com.baimao.wechatReminder.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author admin
 * @create 2022/8/7 04:11
 */
@Getter
@Setter
@ToString
public class WechatVerifyTokenDto {
    /**
     * 微信验证token合法性的随机数
     */
    private String nonce;
    /**
     * 微信验证token合法性的随机字符串
     */
    private String echostr;
    /**
     * 微信验证token合法性的随机签名
     * 根据时间戳 token 随机数nonce 时间戳timestamp 生成的签名信息
     */
    private String signature;
    /**
     * 微信验证token合法性的时间戳
     */
    private String timestamp;
}
