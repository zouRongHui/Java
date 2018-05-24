package org.rone.study.java.middleWare.rabbitMQ;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.MessageProperties;

/**
 * MQ发送方
 */
public class Sender {

    private final static String QUEUE_NAME = "hello";

    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost("localhost");
        Connection connection = factory.newConnection();
        Channel channel = connection.createChannel();

        //设置队列持久化
        boolean durable = true;
        channel.queueDeclare(QUEUE_NAME, durable, false, false, null);
        for (int i = 0; i < 99; i++) {
            String message = "Hello World!" + i;
            //通过 MessageProperties.PERSISTENT_TEXT_PLAIN 设置消息持久化
            channel.basicPublish("", QUEUE_NAME, MessageProperties.PERSISTENT_TEXT_PLAIN,
                    message.getBytes("UTF-8"));
            System.out.println("[x] Sent '" + message + "'");
        }

        channel.close();
        connection.close();
    }

}
