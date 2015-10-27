package com.melowe.jms2.compat;

import java.util.Objects;
import javax.jms.JMSConsumer;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageFormatException;
import javax.jms.MessageListener;

public class Jms2Consumer implements JMSConsumer {

    private final MessageConsumer consumer;

    public Jms2Consumer(MessageConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public String getMessageSelector() {
        return Jms2Util.getMessageSelector(consumer);
    }

    @Override
    public MessageListener getMessageListener() throws JMSRuntimeException {
        return Jms2Util.getMessageListener(consumer);
    }

    @Override
    public void setMessageListener(MessageListener listener) throws JMSRuntimeException {

        Jms2Util.setMessageListener(consumer, listener);
    }

    @Override
    public Message receive() {
        return Jms2Util.receive(consumer);
    }

    @Override
    public Message receive(long timeout) {
        return Jms2Util.receive(consumer, timeout);
    }

    @Override
    public Message receiveNoWait() {
        return Jms2Util.receiveNoWait(consumer);
    }

    @Override
    public void close() {
        Jms2Util.close(consumer);
    }

    @Override
    public <T> T receiveBody(final Class<T> c) {
        final Message msg = receive();
        if (Objects.isNull(msg)) {
            return null;
        }
        
        return getBody(msg, c);

    }

    private <T> T getBody(final Message msg,final Class<T> type) {
            
        try {
            return Jms2MessageUtil.getBody(msg, type);
        } catch(MessageFormatException ex) {
            throw Jms2Util.uncheck(ex);
        } catch (JMSException ex) {
            throw Jms2Util.uncheck(ex);
        }
        
        
    }
    
    @Override
    public <T> T receiveBody(Class<T> type, long timeout) {
        Message msg = receive(timeout);
        if (Objects.isNull(msg)) {
            return null;
        }
        return getBody(msg, type);
    }

    @Override
    public <T> T receiveBodyNoWait(Class<T> type) {
        Message msg = receiveNoWait();
        if (Objects.isNull(msg)) {
            return null;
        }
        return getBody(msg, type);
    }

}
