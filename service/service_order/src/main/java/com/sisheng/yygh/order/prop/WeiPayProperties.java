package com.sisheng.yygh.order.prop;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@ConfigurationProperties(prefix = "weipay")
@PropertySource(value = "classpath:weipay.properties")
@Component
@Data
public class WeiPayProperties {
    private String partnerkey;
    private String partner;
    private String appid;
}
