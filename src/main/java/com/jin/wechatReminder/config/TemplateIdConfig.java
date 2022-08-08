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
 * @create 2022/8/8 01:41
 */
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Component("templateIdConfigBuilder")
public class TemplateIdConfig {

    private String morning;

    private String wedding;

    private String birthday;

    @Bean
    @Primary
    public TemplateIdConfig templateIdConfig() {
        final String morning = "4V_HIG-0S2wq72v_DZ7RCyZl7A7DS9EqeZVKStM7yYA";
        final String birthday = "CavQS_MCuaxo-wPwYwuJRI3QWd9rgeJBHSKo16qFIS4";
        final String wedding = "PKcpe9q4VcYOvCdtkwl4T9fxBKE9n5vqMXNgaLxSIX0";
        return new TemplateIdConfig(morning, wedding, birthday);
    }

}
