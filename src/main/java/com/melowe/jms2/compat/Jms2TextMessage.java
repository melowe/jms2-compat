package com.melowe.jms2.compat;

import javax.jms.JMSException;
import javax.jms.TextMessage;

public final class Jms2TextMessage extends Jms2Message implements TextMessage {

    private final TextMessage delegate;

    public Jms2TextMessage(TextMessage delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public void setText(String string) throws JMSException {
        delegate.setText(string);
    }

    @Override
    public String getText() throws JMSException {
        return delegate.getText();
    }

}
