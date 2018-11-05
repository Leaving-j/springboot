package com.restful.mq;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

public class MqSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private TopicExchange paymentExchange;

    @Scheduled(fixedDelay = 1000, initialDelay = 500)
    public void sendStu(String stuJson) {
        rabbitTemplate.convertAndSend(paymentExchange.getName(),"api.payment.add.stu",stuJson);
    }
}
