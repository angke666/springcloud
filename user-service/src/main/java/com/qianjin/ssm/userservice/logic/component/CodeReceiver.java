package com.qianjin.ssm.userservice.logic.component;

import com.qianjin.ssm.sccommon.entity.User;
import com.qianjin.ssm.userservice.config.RabbitMQConfig;
import com.qianjin.ssm.userservice.logic.service.IUserService;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * 消息接收
 * @author 钱进
 */
@Slf4j
@Component
@RabbitListener(queues = RabbitMQConfig.TEST_QUEUE)
public class CodeReceiver {

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private IUserService userService;

    /**
     * 手动确认消息
     */
//    @RabbitHandler
//    public void process(String content, Channel channel, Message message) {
//        log.info("接收消息：" + content);
//        log.info("接收消息 message：" + message.toString());
//        String correlation = String.valueOf(message.getMessageProperties().getHeaders().get("spring_returned_message_correlation"));
//        log.info(correlation);
//
//        Object correlationId = redisTemplate.opsForValue().get(correlation);
//        if (correlationId != null) {
//            return;
//        } else {
//            redisTemplate.opsForValue().set(correlation, correlation, 1, TimeUnit.MINUTES);
//        }
//        try {
//            User user = new User();
//            user.setName(content);
//            user.setPassword(content);
//            userService.save(user);
//
//            // 告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
//            // false 表示只确认当前一个消息收到 true 表示确认所有消息收到
//            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
//        } catch (Exception e) {
//            // 丢弃这条消息
//            // channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false)
//            if (correlation != null) {
//                redisTemplate.delete(correlation);
//            }
//            log.error("接收消息失败：" + message.toString(), e);
//        }
//    }

    /**
     * 自动确认消息
     * @param message
     */
    @RabbitHandler
    public void pro(String message) {
        log.info(message);
    }

}
