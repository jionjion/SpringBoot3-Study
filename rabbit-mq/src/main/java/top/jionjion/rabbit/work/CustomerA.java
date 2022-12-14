package top.jionjion.rabbit.work;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 *  工作队列模式
 *  消费者A
 * @author Jion
 */
@SuppressWarnings("ALL")
public class CustomerA {

    /** 接受队列消息 */
    public static void main(String[] args) throws IOException, TimeoutException {
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123456");

        Connection connection = connectionFactory.newConnection();
        Channel channel = connection.createChannel();

        channel.queueDeclare("work", false, false, false, null);

        channel.basicConsume("work", true, new DefaultConsumer(channel){
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) {
                System.out.println("消费者A获得消息... " + new String(body));
            }
        });
    }
}
