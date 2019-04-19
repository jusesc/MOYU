package com.moyu.sms.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * 连接阿里云发送手机验证码配置
 * @author juses
 */
@Data
@ConfigurationProperties(prefix = "moyu")
public class SmsProperties {

    String accessKeyId;

    String accessKeySecret;

    String signName;

    String verifyCodeTemplate;
}
