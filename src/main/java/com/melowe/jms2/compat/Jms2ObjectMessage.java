package com.melowe.jms2.compat;

import java.io.Serializable;
import javax.jms.JMSException;
import javax.jms.ObjectMessage;

public class Jms2ObjectMessage extends Jms2Message implements ObjectMessage {

    private final ObjectMessage delegate;

    public Jms2ObjectMessage(ObjectMessage delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public void setObject(Serializable object) throws JMSException {
        delegate.setObject(object);
    }

    @Override
    public Serializable getObject() throws JMSException {
        return delegate.getObject();
    }

}
