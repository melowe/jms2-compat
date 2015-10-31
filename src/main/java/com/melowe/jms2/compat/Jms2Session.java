package com.melowe.jms2.compat;

import java.io.Serializable;
import java.util.Objects;
import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.MessageProducer;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.TextMessage;
import javax.jms.Topic;
import javax.jms.TopicSubscriber;

public final class Jms2Session implements Session {

    private final Session session;

    protected Jms2Session(Session session) {
        this.session = Objects.requireNonNull(session);
    }

    @Override
    public BytesMessage createBytesMessage() throws JMSException {
        return Jms2MessageFactory.createBytesMessage(session);
    }

    @Override
    public MapMessage createMapMessage() throws JMSException {
        return Jms2MessageFactory.createMapMessage(session);
    }

    @Override
    public Message createMessage() throws JMSException {
        return Jms2MessageFactory.createMessage(session);
    }

    @Override
    public ObjectMessage createObjectMessage() throws JMSException {
        return Jms2MessageFactory.createObjectMessage(session);
    }

    @Override
    public ObjectMessage createObjectMessage(Serializable object) throws JMSException {
        return Jms2MessageFactory.createObjectMessage(session, object);
    }

    @Override
    public StreamMessage createStreamMessage() throws JMSException {
        return Jms2MessageFactory.createStreamMessage(session);
    }

    @Override
    public TextMessage createTextMessage() throws JMSException {
        return Jms2MessageFactory.createTextMessage(session);
    }

    @Override
    public TextMessage createTextMessage(String text) throws JMSException {
        return Jms2MessageFactory.createTextMessage(session, text);
    }

    @Override
    public boolean getTransacted() throws JMSException {
        return Jms2Util.getTransacted(session);
    }

    @Override
    public int getAcknowledgeMode() throws JMSException {
        return Jms2Util.getSessionMode(session);
    }

    @Override
    public void commit() throws JMSException {
        Jms2Util.commit(session);
    }

    @Override
    public void rollback() throws JMSException {
        Jms2Util.rollback(session);
    }

    @Override
    public void close() throws JMSException {
        Jms2Util.close(session);
    }

    @Override
    public void recover() throws JMSException {
        Jms2Util.recover(session);
    }

    @Override
    public MessageListener getMessageListener() throws JMSException {
        return Jms2Util.getMessageListener(session);
    }

    @Override
    public void setMessageListener(MessageListener listener) throws JMSException {
        Jms2Util.setMessageListener(session, listener);
    }

    @Override
    public void run() {

        session.run();
    }

    @Override
    public MessageProducer createProducer(Destination destination) throws JMSException {
        return session.createProducer(destination);
    }

    @Override
    public MessageConsumer createConsumer(Destination destination) throws JMSException {
        return session.createConsumer(destination);
    }

    @Override
    public MessageConsumer createConsumer(Destination arg0, String arg1) throws JMSException {
        return session.createConsumer(arg0, arg1);
    }

    @Override
    public MessageConsumer createConsumer(Destination arg0, String arg1, boolean arg2) throws JMSException {
        return session.createConsumer(arg0, arg1, arg2);
    }

    @Override
    public MessageConsumer createSharedConsumer(Topic arg0, String arg1) throws JMSException {
        return session.createSharedConsumer(arg0, arg1);
    }

    @Override
    public MessageConsumer createSharedConsumer(Topic arg0, String arg1, String arg2) throws JMSException {
        return session.createSharedConsumer(arg0, arg1, arg2);
    }

    @Override
    public Queue createQueue(String queueName) throws JMSException {
        return session.createQueue(queueName);
    }

    @Override
    public Topic createTopic(String topicName) throws JMSException {
        return session.createTopic(topicName);
    }

    @Override
    public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1) throws JMSException {
        return session.createDurableSubscriber(arg0, arg1);
    }

    @Override
    public TopicSubscriber createDurableSubscriber(Topic arg0, String arg1, String arg2, boolean arg3) throws JMSException {
        return session.createDurableSubscriber(arg0, arg1, arg2, arg3);
    }

    @Override
    public MessageConsumer createDurableConsumer(Topic arg0, String arg1) throws JMSException {
        return session.createDurableConsumer(arg0, arg1);
    }

    @Override
    public MessageConsumer createDurableConsumer(Topic arg0, String arg1, String arg2, boolean arg3) throws JMSException {
        return session.createDurableConsumer(arg0, arg1, arg2, arg3);
    }

    @Override
    public MessageConsumer createSharedDurableConsumer(Topic arg0, String arg1) throws JMSException {
        return session.createSharedDurableConsumer(arg0, arg1);
    }

    @Override
    public MessageConsumer createSharedDurableConsumer(Topic arg0, String arg1, String arg2) throws JMSException {
        return session.createSharedDurableConsumer(arg0, arg1, arg2);
    }

    @Override
    public QueueBrowser createBrowser(Queue queue) throws JMSException {
        return session.createBrowser(queue);
    }

    @Override
    public QueueBrowser createBrowser(Queue queue, String selector) throws JMSException {
        return session.createBrowser(queue, selector);
    }

    @Override
    public TemporaryQueue createTemporaryQueue() throws JMSException {
        return session.createTemporaryQueue();
    }

    @Override
    public TemporaryTopic createTemporaryTopic() throws JMSException {
        return session.createTemporaryTopic();
    }

    @Override
    public void unsubscribe(String name) throws JMSException {
        Jms2Util.unsubscribe(session, name);
    }

}
