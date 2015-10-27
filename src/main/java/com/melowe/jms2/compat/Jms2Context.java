package com.melowe.jms2.compat;

import static com.melowe.jms2.compat.Jms2Util.*;
import java.io.Serializable;
import java.util.Objects;
import javax.jms.BytesMessage;
import javax.jms.Connection;
import javax.jms.ConnectionMetaData;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSConsumer;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.JMSProducer;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.TextMessage;
import javax.jms.Topic;

public final class Jms2Context implements JMSContext {

    private boolean autoStart;

    private final Session session;

    private final Connection connection;

    public Jms2Context(Connection connection, Session session) {
        this.connection = Objects.requireNonNull(connection);
        this.session = Objects.requireNonNull(session);
    }

    @Override
    public JMSContext createContext(final int sessionMode) {
        return execute(new Callback<JMSContext>() {

            @Override
            public JMSContext execute() throws JMSException {
                Session newSession = connection.createSession(sessionMode == Session.SESSION_TRANSACTED, sessionMode);
                return new Jms2Context(connection, newSession);
            }
        });

    }

    @Override
    public JMSProducer createProducer() {
        return new Jms2Producer(session);
    }

    @Override
    public String getClientID() {
        return Jms2Util.getClientID(connection);
    }

    @Override
    public void setClientID(final String clientID) {
        Jms2Util.setClientID(connection, clientID);
    }

    @Override
    public ConnectionMetaData getMetaData() {
        return Jms2Util.getMetaData(connection);
    }

    @Override
    public ExceptionListener getExceptionListener() {
        return Jms2Util.getExceptionListener(connection);
    }

    @Override
    public void setExceptionListener(ExceptionListener listener) {
        Jms2Util.setExceptionListener(connection, listener);
    }

    @Override
    public void start() {
        Jms2Util.start(connection);
    }

    @Override
    public void stop() {
        Jms2Util.stop(connection);
    }

    @Override
    public void setAutoStart(boolean autoStart) {
        this.autoStart = autoStart;
    }

    @Override
    public boolean getAutoStart() {
        return autoStart;
    }

    @Override
    public void close() {
        Jms2Util.close(connection, session);
    }

    @Override
    public BytesMessage createBytesMessage() {
        return Jms2MessageFactory.createBytesMessage(session);
    }

    @Override
    public MapMessage createMapMessage() {
        return Jms2MessageFactory.createMapMessage(session);
    }

    @Override
    public Message createMessage() {
        return Jms2MessageFactory.createMessage(session);
    }

    @Override
    public ObjectMessage createObjectMessage() {
        return Jms2MessageFactory.createObjectMessage(session);
    }

    @Override
    public ObjectMessage createObjectMessage(Serializable object) {
        return Jms2MessageFactory.createObjectMessage(session, object);
    }

    @Override
    public StreamMessage createStreamMessage() {
        return Jms2MessageFactory.createStreamMessage(session);
    }

    @Override
    public TextMessage createTextMessage() {

        return Jms2MessageFactory.createTextMessage(session);
    }

    @Override
    public TextMessage createTextMessage(String text) {
        return Jms2MessageFactory.createTextMessage(session, text);
    }

    @Override
    public boolean getTransacted() {
        return Jms2Util.getTransacted(session);
    }

    @Override
    public int getSessionMode() {
        return Jms2Util.getSessionMode(session);
    }

    @Override
    public void commit() {
        Jms2Util.commit(session);
    }

    @Override
    public void rollback() {
        Jms2Util.rollback(session);
    }

    @Override
    public void recover() {
        Jms2Util.recover(session);
    }

    @Override
    public JMSConsumer createConsumer(Destination destination) {
        return Jms2Util.createConsumer(session, destination);
    }

    @Override
    public JMSConsumer createConsumer(Destination destination, String selector) {
        return Jms2Util.createConsumer(session, destination, selector);
    }

    @Override
    public JMSConsumer createConsumer(Destination destination, String selector, boolean arg2) {

        return Jms2Util.createConsumer(session, destination, selector, arg2);
    }

    @Override
    public Queue createQueue(String queueName) {
        return Jms2Util.createQueue(session, queueName);
    }

    @Override
    public Topic createTopic(String topicName) {
        return Jms2Util.createTopic(session, topicName);
    }

    @Override
    public JMSConsumer createDurableConsumer(Topic topic, String name) {
        return Jms2Util.createDurableConsumer(session, topic, name);
    }

    @Override
    public JMSConsumer createDurableConsumer(Topic topic, String name, String selector, boolean noLocal) {
        return Jms2Util.createDurableConsumer(session, topic, name,selector,noLocal);
    }

    @Override
    public JMSConsumer createSharedDurableConsumer(Topic topic, String name) {
        return Jms2Util.createDurableConsumer(session, topic, name);
    }

    @Override
    public JMSConsumer createSharedDurableConsumer(Topic topic, String name, String selector) {
        return Jms2Util.createSharedDurableConsumer(session, topic, name,selector);
    }

    @Override
    public JMSConsumer createSharedConsumer(Topic topic, String name) {
        return Jms2Util.createSharedConsumer(session, topic, name);
    }

    @Override
    public JMSConsumer createSharedConsumer(Topic topic, String name, String selector) {
        return Jms2Util.createSharedConsumer(session, topic, name,selector);
    }

    @Override
    public QueueBrowser createBrowser(Queue queue) {
        return Jms2Util.createBrowser(session,queue);
    }

    @Override
    public QueueBrowser createBrowser(Queue queue, String selector) {
        return Jms2Util.createBrowser(session, queue,selector);
    }

    @Override
    public TemporaryQueue createTemporaryQueue() {
        return Jms2Util.createTemporaryQueue(session);
    }

    @Override
    public TemporaryTopic createTemporaryTopic() {
        return Jms2Util.createTemporaryTopic(session);
    }

    @Override
    public void unsubscribe(String name) {
        Jms2Util.unsubscribe(session, name);
    }

    @Override
    public void acknowledge() {
        //TODO: 
    }

    
    
}
