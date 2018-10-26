package com.restful.mq.springboot;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

/**
 * 消息发送者
 */

public class Tut1Sender {

    @Autowired
    private RabbitTemplate template;

    @Autowired
    private Queue messageQueue;

    @Autowired
    private TopicExchange coreExchange;

    @Autowired
    private TopicExchange paymentExchange;

    /**
     * 不使用交换机
     */
    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void send() {
        String message = "发送不匹配目录的消息";
        this.template.convertAndSend(messageQueue.getName(), message);
    }

    /**
     * 使用topicExchange 交换机  routingKey：api.core.*
     */
    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendCore() {
        String message = "发送只匹配一层目录的消息";
        this.template.convertAndSend(coreExchange.getName(), "api.core.message", message);
    }

    /**
     * 使用topicExchange 交换机  routingKey：api.payment.#  like api.payment.*.*.*..........
     */
    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendPayment() {
        String message = "发送可以匹配多层目录的消息";
        this.template.convertAndSend(paymentExchange.getName(), "api.payment.next.message", message);
    }
}
