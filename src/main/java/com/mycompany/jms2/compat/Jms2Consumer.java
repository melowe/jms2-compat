package com.mycompany.jms2.compat;

import java.util.Objects;
import javax.jms.JMSConsumer;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
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
    public <T> T receiveBody(Class<T> c) {
        Message msg = receive();
        if (Objects.isNull(msg)) {
            return null;
        }
        return Jms2MessageFactory.getBody(msg, c);
    }

    @Override
    public <T> T receiveBody(Class<T> type, long timeout) {
        Message msg = receive(timeout);
        if (Objects.isNull(msg)) {
            return null;
        }
        return Jms2MessageFactory.getBody(msg, type);
    }

    @Override
    public <T> T receiveBodyNoWait(Class<T> type) {
        Message msg = receiveNoWait();
        if (Objects.isNull(msg)) {
            return null;
        }
        return Jms2MessageFactory.getBody(msg, type);
    }

}
