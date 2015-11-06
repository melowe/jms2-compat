package com.melowe.jms2.compat;

import java.util.ArrayList;
import java.util.List;
import javax.jms.JMSConsumer;
import javax.jms.JMSException;
import javax.jms.JMSRuntimeException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageFormatException;
import javax.jms.MessageListener;
import javax.jms.Session;

public class Jms2Consumer implements JMSConsumer {

    private final MessageConsumer consumer;

    private final List<Message> acknowledgeMessages = new ArrayList<>();


    private final Jms2Context context;
    
    protected Jms2Consumer(Jms2Context context, MessageConsumer consumer) {
        this.consumer = java.util.Objects.requireNonNull(consumer);
        this.context = java.util.Objects.requireNonNull(context);
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
        if(context.getAutoStart()) {
            context.start();
        }
        
    }

    @Override
    public Message receive() {
        return add( Jms2Util.receive(consumer) );
    }

    @Override
    public Message receive(long timeout) {
        
        return add( Jms2Util.receive(consumer, timeout) );
    }

    @Override
    public Message receiveNoWait() {
        return add( Jms2Util.receiveNoWait(consumer));
    }

    @Override
    public void close() {
        Jms2Util.close(consumer);
    }

    @Override
    public <T> T receiveBody(final Class<T> c) {
        final Message msg = receive();
        if (msg == null) {
            return null;
        }
        add(msg);
        return getBody(msg, c);

    }

    private <T> T getBody(final Message msg, final Class<T> type) {

        try {
            return Jms2MessageUtil.getBody(msg, type);
        } catch (MessageFormatException ex) {
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

    private <M extends Message> M add(M message) {
        if(message == null) {
            return null;
        }
        
        if (context.getSessionMode() == Session.CLIENT_ACKNOWLEDGE) {
            acknowledgeMessages.add(message);
        }
        return message;
    }

    protected void acknowledge() {
        
        for (final Message msg : acknowledgeMessages) {
            Jms2Util.execute(new Callback<Void>() {

                @Override
                public Void execute() throws JMSException {
                    msg.acknowledge();
                    return null;
                }
            });
        }
        acknowledgeMessages.clear();
        
    }
}
