package com.jin.wechatReminder.service;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.jin.wechatReminder.config.WechatConfig;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author admin
 * @create 2022/8/7 04:37
 */

@Service
public class WechatAccessTokenService {
    private static final Logger logger = LogManager.getLogger(WechatAccessTokenService.class);
    /**
     * 获取accessToken的缓存前缀
     */
    private static final String CACHE_ACCESS_TOKEN = "cache:wechat:accessToken:";
    /**
     * AccessToken缓存
     */
    private static final ConcurrentHashMap<String, Map<String, Object>> TOKEN_CACHE = new ConcurrentHashMap<>();
    @Autowired
    private WechatConfig wechatConfig;

    /**
     * 获取接口的AccessToken，对响应数据进行缓存，避免重复请求
     *
     * @param appID 开发者Id
     * @return AccessToken
     */
    public String getAccessToken(String appID) {
        logger.info("getAccessToken by appId: " + appID);
        Map<String, Object> accessTokenInfo = TOKEN_CACHE.get(CACHE_ACCESS_TOKEN + appID);
        if (!CollectionUtils.isEmpty(accessTokenInfo) &&
                ((LocalDateTime) accessTokenInfo.get("expiration")).isAfter(LocalDateTime.now())) {
            String accessToken = (String) accessTokenInfo.get("accessToken");
            logger.info("getAccessToken from cache, accessToken: " + accessToken);
            return accessToken;
        }
        HttpResponse response = HttpRequest.get(getWechatAccessTokenUrl()).execute();
        logger.info("getAccessToken from wechat api, body: " + response.toString());
        if (response.isOk()) {
            JSONObject jsonObject = JSON.parseObject(response.body());
            Object errcode = jsonObject.get("errcode");
            if (Objects.isNull(errcode) || ((int) errcode) == 0) {
                String accessToken = (String) jsonObject.get("access_token");
                int expiresTime = (int) jsonObject.get("expires_in");
                accessTokenInfo = new HashMap<>();
                accessTokenInfo.put("accessToken", accessToken);
                expiresTime = expiresTime > 100 ? expiresTime - 100 : expiresTime;
                accessTokenInfo.put("expiration", LocalDateTime.now().plusSeconds(expiresTime));
                TOKEN_CACHE.put(CACHE_ACCESS_TOKEN + appID, accessTokenInfo);
                return accessToken;
            }
        }
        return null;
    }

    public String getAccessToken() {
        return this.getAccessToken(wechatConfig.getAppId());
    }

    private String getWechatAccessTokenUrl() {
        return wechatConfig.getAccessUrl() + "?grant_type=client_credential&appid=" +
                wechatConfig.getAppId() + "&secret=" + wechatConfig.getAppSecret();
    }


}
