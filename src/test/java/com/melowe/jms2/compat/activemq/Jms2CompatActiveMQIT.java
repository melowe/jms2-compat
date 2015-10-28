package com.melowe.jms2.compat.activemq;

import com.melowe.jms2.compat.Jms2ConnectionFactory;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSContext;
import javax.jms.Message;
import javax.jms.MessageListener;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class Jms2CompatActiveMQIT {

    ConnectionFactory connectionFactory;

    public Jms2CompatActiveMQIT() {
    }

    @Before
    public void setUp() {
        connectionFactory = new Jms2ConnectionFactory(new ActiveMQConnectionFactory("vm://junit?marshal=false&broker.persistent=false"));
    }

    @After
    public void tearDown() {
        connectionFactory = null;
    }

    @Test
    public void testProduceAndConsumeMessage() throws Exception {

        try (JMSContext jmsContext = connectionFactory.createContext()) {

            Destination queue = jmsContext.createQueue("TESTQ");

            final CountDownLatch latch = new CountDownLatch(1);
            final List<String> results = new ArrayList<>();
            jmsContext.createConsumer(queue).setMessageListener(new MessageListener() {

                @Override
                public void onMessage(Message message) {
                    try {
                        results.add(message.getBody(String.class));
                        latch.countDown();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            jmsContext.start();

            jmsContext.createProducer()
                    .send(queue, "HELLOW");

            assertTrue(latch.await(3, TimeUnit.SECONDS));
            assertEquals("HELLOW", results.iterator().next());

        }

    }

}
