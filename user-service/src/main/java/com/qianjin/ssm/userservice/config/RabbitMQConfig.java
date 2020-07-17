package com.qianjin.ssm.userservice.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    public static final String TEST_QUEUE = "topic.testQueue";
    public static final String TOPIC_EXCHANGE = "topicExchange";

    /**
     * 创建队列
     * @return
     */
    @Bean
    public Queue testQueue() {
        return new Queue(RabbitMQConfig.TEST_QUEUE);
    }

    /**
     * 创建转换器
     * @return
     */
    @Bean
    public TopicExchange topicExchange() {
        return new TopicExchange(RabbitMQConfig.TOPIC_EXCHANGE);
    }

    /**
     * 绑定路由
     * @return
     */
    @Bean
    public Binding binding() {
        return BindingBuilder.bind(testQueue()).to(topicExchange()).with("topic.#");
    }

}
