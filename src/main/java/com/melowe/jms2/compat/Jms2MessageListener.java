package com.melowe.jms2.compat;

import java.util.Objects;
import javax.jms.Message;
import javax.jms.MessageListener;

public class Jms2MessageListener implements MessageListener {

    private final MessageListener messageListener;

    public Jms2MessageListener(MessageListener messageListener) {
        this.messageListener = Objects.requireNonNull(messageListener);
    }

    @Override
    public void onMessage(Message message) {
        Jms2Message msg = Jms2MessageFactory.convert(message);
        this.messageListener.onMessage(msg);
    }

}
