package com.melowe.jms2.compat;

import javax.jms.Connection;
import javax.jms.ConnectionMetaData;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
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
import javax.jms.TopicSubscriber;

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
                return session.createBrowser(queue, selector);
            }
        });

    }

    static Jms2Consumer createDurableConsumer(Jms2Context context, final Topic topic, final String name) {
        return new Jms2Consumer(context.getSession(),createDurableMessageConsumer(context.getSession(), topic, name));
    }

    static Jms2Consumer createDurableConsumer(Jms2Context context, final Topic topic, final String name, final String selector, final boolean noLocal) {
        return new Jms2Consumer(context.getSession(),createDurableMessageConsumer(context.getSession(), topic, name, selector, noLocal));
    }

    static Jms2Consumer createSharedDurableConsumer(Jms2Context context, Topic topic, String name, String selector) {
         return new Jms2Consumer(context.getSession(),createDurableMessageConsumer(context.getSession(), topic, name,selector,false));
    }

    static Jms2Consumer createSharedConsumer(Jms2Context context, Topic topic, String name) {
        return context.addConsumer(createSharedConsumer(context, topic, name, null));
    }

    static Jms2Consumer createSharedConsumer(Jms2Context context, Topic topic, String name, String selector) {
        return context.addConsumer(createDurableConsumer(context, topic, name, selector, false));
    }

    static JMSRuntimeException uncheck(MessageFormatException ex) {
        return new MessageFormatRuntimeException(ex.getMessage(), ex.getErrorCode(), ex);
    }

    static JMSRuntimeException uncheck(JMSException ex) {
        return new JMSRuntimeException(ex.getMessage(), ex.getErrorCode(), ex);
    }

    static boolean isClientAcknowledge(final Session session) {
        return getSessionMode(session) == Session.CLIENT_ACKNOWLEDGE;
     }

    private Jms2Util() {
    }

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
                if (m == null) {
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
                if(m == null) return null; 
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

    
    static MessageConsumer createDurableMessageConsumer(final Session session, final Topic topic,final String name, final String selector, final boolean noLocal) {
        return execute(new Callback<TopicSubscriberAdaptor>() {

            @Override
            public TopicSubscriberAdaptor execute() throws JMSException {
                TopicSubscriber subscriber;
                if(selector == null) {
                    subscriber = session.createDurableSubscriber(topic, name);
                } else {
                    subscriber = session.createDurableSubscriber(topic, name, selector, noLocal);
                }
                
                return new TopicSubscriberAdaptor(subscriber);                
            }
        });
    }
    
    static MessageConsumer createDurableMessageConsumer(final Session session, final Topic topic,final String name) {
        return execute(new Callback<TopicSubscriberAdaptor>() {
            @Override
            public TopicSubscriberAdaptor execute() throws JMSException {
                TopicSubscriber topicSubscriber = session.createDurableSubscriber(topic, name);
                return new TopicSubscriberAdaptor(topicSubscriber);                
            }
        });
    }
    
    
    static MessageConsumer createSharedMessageConsumer(final Session session, final Topic topic, final String sharedSubscriptionName, final String selector) {
        return createDurableMessageConsumer(session, topic, sharedSubscriptionName, selector, false);
    }

    static MessageConsumer createMessageConsumer(final Session session, final Destination destination, final String selector, final Boolean noLocal) {
        return execute(new Callback<MessageConsumer>() {

            @Override
            public MessageConsumer execute() throws JMSException {
                //TODO: Deal with noLocal

                if (selector == null) {
                    return session.createConsumer(destination);
                } else {
                    return session.createConsumer(destination, selector);
                }
            }
        });
    }

    static Jms2Consumer createConsumer(Jms2Context context, Destination destination) {
        return createConsumer(context, destination, null, null);
    }

    static Jms2Consumer createConsumer(Jms2Context context, Destination destination, String selector) {
        return createConsumer(context, destination, selector, null);
    }

    static Jms2Consumer createConsumer(Jms2Context context, Destination destination, String selector, Boolean arg) {

        Jms2Consumer consumer =  new Jms2Consumer(context.getSession(),createMessageConsumer(context.getSession(), destination, selector, null));
        return context.addConsumer(consumer);
        
    }

    static Queue createQueue(final Session session, final String queueName) {
        return execute(new Callback<Queue>() {

            @Override
            public Queue execute() throws JMSException {
                return session.createQueue(queueName);
            }
        });
    }

    static Topic createTopic(final Session session, final String topicName) {
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
