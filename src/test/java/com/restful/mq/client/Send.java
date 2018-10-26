package com.restful.mq.client;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 消息发送者
 */

public class Send {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws IOException, TimeoutException {
        // 连接工厂
        ConnectionFactory factory = new ConnectionFactory();
        // IP
        factory.setHost("localhost");
        // 工厂创建一个连接
        Connection connection = factory.newConnection();
        // 获取通道
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        String message = "send message";
        // 发布消息
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println(" [S] Sent '" + message + "'");
        channel.close();
        connection.close();
    }
}
