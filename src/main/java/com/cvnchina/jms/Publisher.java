package com.cvnchina.jms;

import org.apache.activemq.ActiveMQConnectionFactory;
import org.apache.activemq.command.ActiveMQMapMessage;

import javax.jms.*;

/**
 * Created by ztianyu(ztianyu@cvnchina.com) on 2017/3/7.
 * project : jmsTest
 * package : com.cvnchina.jms
 * <p>
 * <p>
 * COPYRIGHT BY CVNCHINA 2017.
 * <a href = "http://www.cvnchina.com">www.cvnchina.com</a>
 */
public class Publisher{
    ConnectionFactory factory;
    Connection connection;
    String brokerURL = "";
    Session session;
    MessageProducer producer;
    Destination[] destinations;
    public Publisher() throws JMSException {
        factory = new ActiveMQConnectionFactory(brokerURL);
        connection = factory.createConnection();
        try {
            connection.start();
        } catch (JMSException jmse) {
            connection.close();
            throw jmse;
        }
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        producer = session.createProducer(null);
    }
    protected void setTopics(String[] stocks) throws JMSException {
        destinations = new Destination[stocks.length];
        for(int i = 0; i < stocks.length; i++) {
            destinations[i] = session.createTopic("STOCKS." + stocks[i]);
        }
    }
    protected void sendMessage(String[] stocks) throws JMSException {
        for(int i = 0; i < stocks.length; i++) {
            Message message = createStockMessage(stocks[i], session);
            System.out.println("Sending: " + ((ActiveMQMapMessage)message).getContentMap() + " on destination: " + destinations[i]);
            producer.send(destinations[i], message);
        }
    }

    protected Message createStockMessage(String stock, Session session) throws JMSException {
        MapMessage message = session.createMapMessage();
        message.setString("stock", stock);
        message.setDouble("price", 1.00);
        message.setDouble("offer", 0.01);
        message.setBoolean("up", true);

        return message;
    }
    public static void main(String[] args) throws JMSException {
        if(args.length < 1)
            throw new IllegalArgumentException();

        // Create publisher
        Publisher publisher = new Publisher();

        // Set topics
        publisher.setTopics(args);

        for(int i = 0; i < 10; i++) {
            publisher.sendMessage(args);
            System.out.println("Publisher '" + i + " price messages");
            try {
                Thread.sleep(1000);
            } catch(InterruptedException e) {
                e.printStackTrace();
            }
        }
        // Close all resources
        publisher.close();
    }
    public void close() throws JMSException {
        if (connection != null) {
            connection.close();
        }
    }

}
