package com.kai.utils;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

public class RabbitMQUtil {

    private static Connection connection;
    private static ConnectionFactory connectionFactory;
    private RabbitMQUtil(){

    }

    public static Connection getConnection(){
        if (connection!=null){
            return connection;
        }
        if (connectionFactory!=null){
            connectionFactory.setHost("192.168.153.128");
            connectionFactory.setPort(5672);
            connectionFactory.setVirtualHost("/ems");
            connectionFactory.setUsername("ems");
            connectionFactory.setPassword("123456");
            try {
                connection = connectionFactory.newConnection();
            } catch (Exception e) {
                e.printStackTrace();
            }
            return connection;
        }
        connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("192.168.153.128");
        connectionFactory.setPort(5672);
        connectionFactory.setVirtualHost("/ems");
        connectionFactory.setUsername("ems");
        connectionFactory.setPassword("123456");
        try {
            connection = connectionFactory.newConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return connection;
    }

    public static void closeConnection(Channel channel,Connection connection){
        try {
            channel.close();
            connection.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
