package com.baimao.wechatReminder.config;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

/**
 * @author admin
 * @create 2022/8/8 01:41
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component("templateIdConfigBuilder")
public class TemplateIdConfig {

    private String morning;
    private String evening;

    private String nightSnackTime;

    private String wedding;

    private String birthday;

    @Bean
    @Primary
    public TemplateIdConfig templateIdConfig() {
        //每日提醒
        final String morning = "7I5hvjWy3syqcaCQnXYVPvGEpan06uzUtK3nKFClKC4";
        final String evening = "X-LQ7xDo9WG9ELV_RCOS6WsWSnFpiWK_c1XWnPKECs4";
        //宵夜提醒
        final String nightSnackTime = "mw9zrT8yvlPLtnIiGdDVNJcSUpfj_W49dHgRQFLMWfs";


        final String birthday = "CavQS_MCuaxo-wPwYwuJRI3QWd9rgeJBHSKo16qFIS4";
        final String wedding = "PKcpe9q4VcYOvCdtkwl4T9fxBKE9n5vqMXNgaLxSIX0";
        return new TemplateIdConfig(morning, evening, nightSnackTime, wedding, birthday);
    }

}
