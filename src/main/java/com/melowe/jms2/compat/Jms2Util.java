package com.melowe.jms2.compat;

import java.util.Objects;
import javax.jms.Connection;
import javax.jms.ConnectionMetaData;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSConsumer;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageFormatException;
import javax.jms.MessageFormatRuntimeException;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.QueueBrowser;
import javax.jms.Session;
import javax.jms.TemporaryQueue;
import javax.jms.TemporaryTopic;
import javax.jms.Topic;

public final class Jms2Util {

    static TemporaryQueue createTemporaryQueue(final Session session) {
        return execute(new Callback<TemporaryQueue>() {

            @Override
            public TemporaryQueue execute() throws JMSException {
                return session.createTemporaryQueue();
            }
        });
     }

    static TemporaryTopic createTemporaryTopic(final Session session) {
        return execute(new Callback<TemporaryTopic>() {

            @Override
            public TemporaryTopic execute() throws JMSException {
                return session.createTemporaryTopic();
            }
        });
    }



    static QueueBrowser createBrowser(final Session session, final Queue queue) {
        return execute(new Callback<QueueBrowser>() {

            @Override
            public QueueBrowser execute() throws JMSException {
                return session.createBrowser(queue);
            }
        });
    }

    static QueueBrowser createBrowser(final Session session, final Queue queue, final String selector) {
        return execute(new Callback<QueueBrowser>() {

            @Override
            public QueueBrowser execute() throws JMSException {
                return session.createBrowser(queue,selector);
            }
        });
        
    }

    static JMSConsumer createDurableConsumer(Session session, Topic topic, String name) {
        
        
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static JMSConsumer createDurableConsumer(Session session, Topic topic, String name, String selector, boolean noLocal) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static JMSConsumer createSharedDurableConsumer(Session session, Topic topic, String name, String selector) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static JMSConsumer createSharedConsumer(Session session, Topic topic, String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static JMSConsumer createSharedConsumer(Session session, Topic topic, String name, String selector) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    static JMSRuntimeException uncheck(MessageFormatException ex) {
        return new MessageFormatRuntimeException(ex.getMessage(), ex.getErrorCode(), ex);
    }
    
    static JMSRuntimeException uncheck(JMSException ex) {
        return new JMSRuntimeException(ex.getMessage(), ex.getErrorCode(), ex);
    }

    private Jms2Util() {}
    
    static void commit(final Session session) {
        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                session.commit();
                return null;
            }
        });
    }

    static <T> T execute(Callback<T> callback) {
        try {
            return callback.execute();
        } catch (JMSException ex) {
            throw uncheck(ex);
        }
    }



    static void rollback(final Session session) {
        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                session.rollback();
                return null;
            }
        });
    }

    static void recover(final Session session) {
        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                session.recover();
                return null;
            }
        });
    }

    static int getSessionMode(final Session session) {
        return execute(new Callback<Integer>() {

            @Override
            public Integer execute() throws JMSException {
                return session.getAcknowledgeMode();
            }
        });
    }

    static boolean getTransacted(final Session session) {
        return execute(new Callback<Boolean>() {

            @Override
            public Boolean execute() throws JMSException {
                return session.getTransacted();
            }
        });
    }

    static void unsubscribe(final Session session, final String name) {
        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                session.unsubscribe(name);
                return null;
            }
        });
    }

    static String getClientID(final Connection connection) {
        return execute(new Callback<String>() {

            @Override
            public String execute() throws JMSException {
                return connection.getClientID();
            }
        });
    }

    static void setClientID(final Connection connection, final String clientID) {
        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                connection.setClientID(clientID);
                return null;
            }
        });
    }

    static ConnectionMetaData getMetaData(final Connection connection) {
        return execute(new Callback<ConnectionMetaData>() {

            @Override
            public ConnectionMetaData execute() throws JMSException {
                return connection.getMetaData();
            }
        });
    }

    static ExceptionListener getExceptionListener(final Connection connection) {
        return execute(new Callback<ExceptionListener>() {

            @Override
            public ExceptionListener execute() throws JMSException {
                return connection.getExceptionListener();
            }
        });
    }

    static void setExceptionListener(final Connection connection, final ExceptionListener listener) {
        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                connection.setExceptionListener(listener);
                return null;
            }
        });
    }

    static void start(final Connection connection) {

        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                connection.start();
                return null;
            }
        });
    }

    static void stop(final Connection connection) {
        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                connection.stop();
                return null;
            }
        });
    }

    static void close(final Connection connection, final Session session) {
        try {
            Jms2Util.close(session);
        } finally {
            Jms2Util.close(connection);
        }
    }

    static String getMessageSelector(final MessageConsumer consumer) {
        return execute(new Callback<String>() {

            @Override
            public String execute() throws JMSException {
                return consumer.getMessageSelector();
            }
        });    
    }

    static Jms2Message receive(final MessageConsumer consumer) {
        return execute(new Callback<Jms2Message>() {

            @Override
            public Jms2Message execute() throws JMSException {
                Message m = consumer.receive();
                return Jms2MessageFactory.convert(m);
            }
        });
    }

    static Jms2Message receive(final MessageConsumer consumer, final long timeout) {
        return execute(new Callback<Jms2Message>() {

            @Override
            public Jms2Message execute() throws JMSException {
                Message m = consumer.receive(timeout);
                if(Objects.isNull(m)) {
                    return null;
                }
                return Jms2MessageFactory.convert(m);
            }
        });
    }

    static Message receiveNoWait(final MessageConsumer consumer) {
        return execute(new Callback<Jms2Message>() {

            @Override
            public Jms2Message execute() throws JMSException {
                Message m = consumer.receiveNoWait();
                return Jms2MessageFactory.convert(m);
            }
        });
    }

    static void close(final MessageConsumer consumer) {
        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                consumer.close();
                return null;
            }
        });
    }

    static MessageListener getMessageListener(final MessageConsumer consumer) {
        return execute(new Callback<MessageListener>() {

            @Override
            public MessageListener execute() throws JMSException {
                return consumer.getMessageListener();
            }
        });
    }

    static void setMessageListener(final MessageConsumer consumer, final MessageListener listener) {
        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                consumer.setMessageListener(new Jms2MessageListener(listener));
                return null;
            }
        });
    }

    static MessageConsumer createMessageConsumer(final Session session,final Destination destination,final String selector,final Boolean noLocal) {
        return execute(new Callback<MessageConsumer>() {

            @Override
            public MessageConsumer execute() throws JMSException {
                //TODO: Deal with noLocal

                if(Objects.isNull(selector)) {
                    return session.createConsumer(destination);
                } else {
                    return session.createConsumer(destination, selector);
                }
            }
        });
    }
    
    static JMSConsumer createConsumer(Session session, Destination destination) {
        return createConsumer(session, destination,null,null);
    }

    static JMSConsumer createConsumer(Session session, Destination destination, String selector) {
        return createConsumer(session, destination,selector,null);
    }

    static JMSConsumer createConsumer(Session session, Destination destination, String selector, Boolean arg) { 
        return new Jms2Consumer(createMessageConsumer(session, destination, selector,null));
    }

    static Queue createQueue(final Session session,final String queueName) {
        return execute(new Callback<Queue>() {

            @Override
            public Queue execute() throws JMSException {
                return session.createQueue(queueName);
            }
        });
    }

    static Topic createTopic(final Session session,final String topicName) {
        return execute(new Callback<Topic>() {

            @Override
            public Topic execute() throws JMSException {
                return session.createTopic(topicName);
            }
        });
    }

    static void close(final Session session) {
        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                session.close();
                return null;
            }
        });
    }

    static void close(final Connection connection) {
        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                connection.close();
                return null;
            }
        });
    }

    static MessageListener getMessageListener(final Session session) {
        return execute(new Callback<MessageListener>() {

            @Override
            public MessageListener execute() throws JMSException {
                return session.getMessageListener();
            }
        });
    }

    static void setMessageListener(final Session session, final MessageListener listener) {
        execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                session.setMessageListener(new Jms2MessageListener(listener));
                return null;
            }
        });
    }

}
