package com.restful.mq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * mq配置类
 *
 * @author lw
 * @date 2018.11.05
 */
@Configuration
public class MqConfig {

    @Bean
    public MqSender mqSender() {
        return new MqSender();
    }

    @Bean
    public MqReceiver mqReceiver() {
        return new MqReceiver();
    }

    @Bean
    public Queue coreQueue() {
        return new Queue("api.core");
    }

    @Bean
    public Queue paymentQueue() {
        return new Queue("api.payment");
    }

    @Bean
    public TopicExchange coreExchange() {
        return new TopicExchange("coreExchange");
    }

    @Bean
    public TopicExchange paymentExchange() {
        return new TopicExchange("paymentExchange");
    }

    /**
     *
     * 只能匹配一层目录 routingKey：api.core.*
     *
     * @param coreQueue
     * @param coreExchange
     * @return
     */
    @Bean
    public Binding bindingCoreExchange(Queue coreQueue, TopicExchange coreExchange) {
        return BindingBuilder.bind(coreQueue).to(coreExchange).with("api.core.*");
    }

    /**
     *
     * 可以匹配多层目录  routingKey：api.payment.#
     *
     * @param paymentQueue
     * @param paymentExchange
     * @return
     */
    @Bean
    public Binding bindingPaymentExchange(Queue paymentQueue, TopicExchange paymentExchange) {
        return BindingBuilder.bind(paymentQueue).to(paymentExchange).with("api.payment.#");
    }

}
