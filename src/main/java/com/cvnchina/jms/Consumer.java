package com.cvnchina.jms;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;


public class Consumer {
    ConnectionFactory factory;
    Connection connection;
    String brokerURL = "";
    Session session;
    MessageProducer producer;
    Destination[] destinations;

    public Consumer() throws JMSException {
        factory = new ActiveMQConnectionFactory(brokerURL);
        connection = factory.createConnection();
        connection.start();
        session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    public static void main(String[] args) throws JMSException {
        Consumer consumer = new Consumer();

        for (String stock : args) {
            Destination destination = consumer.getSession().createTopic("STOCKS." + stock);
            MessageConsumer messageConsumer = consumer.getSession().createConsumer(destination);
            messageConsumer.setMessageListener(new Listener("job"));
        }
    }

    public Session getSession() {
        return session;
    }
}
