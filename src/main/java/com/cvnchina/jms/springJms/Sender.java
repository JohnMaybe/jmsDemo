package com.cvnchina.jms.springJms;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.Session;
import java.util.Date;

/**
 * Created by ztianyu(ztianyu@cvnchina.com) on 2017/3/15.
 * project : jmsTest
 * package : com.cvnchina.jms.springJms
 * <p>
 * <p>
 * COPYRIGHT BY CVNCHINA 2017.
 * <a href = "http://www.cvnchina.com">www.cvnchina.com</a>
 */
public class Sender {
    public static void main(String[] args) {
        ApplicationContext ctx = new FileSystemXmlApplicationContext("classpath:applicationContext-*.xml");
        JmsTemplate jmsTemplate = (JmsTemplate) ctx.getBean("jmsTemplate");

        jmsTemplate.send(new MessageCreator() {
            public Message createMessage(Session session) throws JMSException {
                MapMessage message = session.createMapMessage();
                message.setString("message", "current system time: " + new Date().getTime());

                return message;
            }
        });
    }
}
