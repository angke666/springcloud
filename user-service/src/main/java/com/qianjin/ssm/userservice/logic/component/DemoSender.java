package com.qianjin.ssm.userservice.logic.component;

import com.qianjin.ssm.userservice.config.RabbitMQConfig;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * @ClassName DemoSender
 * @Description TODO
 * @Author 钱进
 * @Date 2020/5/29 8:49
 * @Version 1.0
 **/
@Slf4j
@Component
public class DemoSender implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public DemoSender(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
        rabbitTemplate.setReturnCallback(this);
    }

    public void sendMsg(String content) {
        CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
        rabbitTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE, "topic.a", content, correlationId);
    }

    /**
     * 确认是否发送成功
     * @param correlationData
     * @param ack
     * @param cause
     */
    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String cause) {
        log.info("回调id：" + correlationData);
        if (ack) {
            log.info("消息发送成功");
        } else {
            log.info("消息发送失败：" + cause);
        }
    }

    @Override
    public void returnedMessage(Message message, int replyCode, String replyText, String exchange, String routingKey) {
        log.info("消息主体：" + message);
        log.info("消息主体返回码：" + replyCode);
        log.info("消息返回信息：" + replyText);
        log.info("消息使用的交换器：" + exchange);
        log.info("消息使用的路由规则：" + routingKey);
    }

}
