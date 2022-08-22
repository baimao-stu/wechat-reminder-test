package com.baimao.wechatReminder.controller;


import com.baimao.wechatReminder.entity.WechatVerifyTokenDto;
import com.baimao.wechatReminder.utils.WechatVerifyUtil;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/wechat")
@Controller
public class WechatController {
    private static final Logger logger = LogManager.getLogger(WechatController.class);

    /**
     * 用户申请测试号，填报token时，微信会回调该接口，用户校验该信息后再返回校验的随机数完成服务器的验证
     *
     * @param tokenDto 微信回调时的接口入参
     * @return 检验通过后返回的随机字符串信息
     * @throws Exception
     */
    @RequestMapping(value = "/token")
    public String get(WechatVerifyTokenDto tokenDto) throws Exception {
        logger.info("start verify token from wechat, params:[" + tokenDto + "]");
        // 通过检验signature对请求进行校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败
        if (WechatVerifyUtil.verifySignature(tokenDto)) {
            logger.info("verify success, return " + tokenDto.getEchostr());
            return tokenDto.getEchostr();
        }
        logger.info("verify error, return null");
        return null;
    }
}

 



