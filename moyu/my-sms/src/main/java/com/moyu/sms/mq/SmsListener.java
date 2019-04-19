package com.moyu.sms.mq;





import com.aliyuncs.exceptions.ClientException;
import com.moyu.common.utils.JsonUtils;
import com.moyu.sms.config.SmsProperties;
import com.moyu.sms.utils.SmsUtils;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.Map;


/**
 * @author juses
 * @date 19-4-18 上午10:39
 */
@Component
@EnableConfigurationProperties(SmsProperties.class)
@Slf4j
public class SmsListener {

    @Autowired
    private SmsUtils smsUtils;

    @Autowired
    private SmsProperties prop;

    /**
     * 发送短信验证码
     *
     * @param msg
     * @throws Exception
     */
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "sms.verify.code.queue", durable = "true"),
            exchange = @Exchange(value = "moyu.sms.exchange", type = ExchangeTypes.TOPIC),
            key = {"sms.verify.code"}))
    public void listenSms(Map<String, String> msg) {
        if (CollectionUtils.isEmpty(msg)) {
            return;
        }
        String phone = msg.remove("phone");
        if (StringUtils.isBlank(phone)) {
            return;
        }
        //处理消息
        // TODO 短信验证
        try {
            smsUtils.sendSms(phone, prop.getSignName(), prop.getVerifyCodeTemplate(), JsonUtils.parse(msg.toString(), String.class));
        } catch (ClientException e) {
            System.err.println(e.getClass().getName());
            System.err.println(e.getMessage());
        }

        //发送短信日志
        log.info("[短信服务],发送短信验证码,手机号:{}",phone);
    }

}