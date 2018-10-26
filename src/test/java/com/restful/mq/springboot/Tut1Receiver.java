package com.restful.mq.springboot;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;


public class Tut1Receiver {
    @RabbitHandler
    @RabbitListener(queues = "messageQueue")
    public void receive(String message) {
        System.out.println("不用交换机接收消息，直接使用队列 -->" + message + "'");
    }

    @RabbitHandler
    @RabbitListener(queues = "api.core")
    public void receiveCore(String message) {
        System.out.println("接收到的消息 --> receiverCore：" + message);
    }

    @RabbitHandler
    @RabbitListener(queues = "api.payment")
    public void receivePayment(String message) {
        System.out.println("接收消息 --> receivePayment: " + message);
    }
}
