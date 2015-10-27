
package com.melowe.jms2.compat;

import java.util.Enumeration;
import java.util.Objects;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;


public class Jms2Message implements Message {
    
    private final Message delegate;

    private Long jms2DeliveryTime;
    

    public Jms2Message(Message delegate) {
        this.delegate = Objects.requireNonNull(delegate);
    }

    @Override
    public String getJMSMessageID() throws JMSException {
        return delegate.getJMSMessageID();
    }

    @Override
    public void setJMSMessageID(String id) throws JMSException {
        delegate.setJMSMessageID(id);
    }

    @Override
    public long getJMSTimestamp() throws JMSException {
        return delegate.getJMSTimestamp();
    }

    @Override
    public void setJMSTimestamp(long timestamp) throws JMSException {
        delegate.setJMSTimestamp(timestamp);
    }

    @Override
    public byte[] getJMSCorrelationIDAsBytes() throws JMSException {
        return delegate.getJMSCorrelationIDAsBytes();
    }

    @Override
    public void setJMSCorrelationIDAsBytes(byte[] correlationID) throws JMSException {
        delegate.setJMSCorrelationIDAsBytes(correlationID);
    }

    @Override
    public void setJMSCorrelationID(String correlationID) throws JMSException {
        delegate.setJMSCorrelationID(correlationID);
    }

    @Override
    public String getJMSCorrelationID() throws JMSException {
        return delegate.getJMSCorrelationID();
    }

    @Override
    public Destination getJMSReplyTo() throws JMSException {
        return delegate.getJMSReplyTo();
    }

    @Override
    public void setJMSReplyTo(Destination replyTo) throws JMSException {
        delegate.setJMSReplyTo(replyTo);
    }

    @Override
    public Destination getJMSDestination() throws JMSException {
        return delegate.getJMSDestination();
    }

    @Override
    public void setJMSDestination(Destination destination) throws JMSException {
        delegate.setJMSDestination(destination);
    }

    @Override
    public int getJMSDeliveryMode() throws JMSException {
        return delegate.getJMSDeliveryMode();
    }

    @Override
    public void setJMSDeliveryMode(int deliveryMode) throws JMSException {
        delegate.setJMSDeliveryMode(deliveryMode);
    }

    @Override
    public boolean getJMSRedelivered() throws JMSException {
        return delegate.getJMSRedelivered();
    }

    @Override
    public void setJMSRedelivered(boolean redelivered) throws JMSException {
        delegate.setJMSRedelivered(redelivered);
    }

    @Override
    public String getJMSType() throws JMSException {
        return delegate.getJMSType();
    }

    @Override
    public void setJMSType(String type) throws JMSException {
        delegate.setJMSType(type);
    }

    @Override
    public long getJMSExpiration() throws JMSException {
        return delegate.getJMSExpiration();
    }

    @Override
    public void setJMSExpiration(long expiration) throws JMSException {
        delegate.setJMSExpiration(expiration);
    }

    @Override
    public long getJMSDeliveryTime() throws JMSException {
        return jms2DeliveryTime;
    }

    @Override
    public void setJMSDeliveryTime(long deliveryTime) throws JMSException {
        this.jms2DeliveryTime = deliveryTime;
    }

    @Override
    public int getJMSPriority() throws JMSException {
        return delegate.getJMSPriority();
    }

    @Override
    public void setJMSPriority(int priority) throws JMSException {
        delegate.setJMSPriority(priority);
    }

    @Override
    public void clearProperties() throws JMSException {
        delegate.clearProperties();
    }

    @Override
    public boolean propertyExists(String name) throws JMSException {
        return delegate.propertyExists(name);
    }

    @Override
    public boolean getBooleanProperty(String name) throws JMSException {
        return delegate.getBooleanProperty(name);
    }

    @Override
    public byte getByteProperty(String name) throws JMSException {
        return delegate.getByteProperty(name);
    }

    @Override
    public short getShortProperty(String name) throws JMSException {
        return delegate.getShortProperty(name);
    }

    @Override
    public int getIntProperty(String name) throws JMSException {
        return delegate.getIntProperty(name);
    }

    @Override
    public long getLongProperty(String name) throws JMSException {
        return delegate.getLongProperty(name);
    }

    @Override
    public float getFloatProperty(String name) throws JMSException {
        return delegate.getFloatProperty(name);
    }

    @Override
    public double getDoubleProperty(String name) throws JMSException {
        return delegate.getDoubleProperty(name);
    }

    @Override
    public String getStringProperty(String name) throws JMSException {
        return delegate.getStringProperty(name);
    }

    @Override
    public Object getObjectProperty(String name) throws JMSException {
        return delegate.getObjectProperty(name);
    }

    @Override
    public Enumeration getPropertyNames() throws JMSException {
        return delegate.getPropertyNames();
    }

    @Override
    public void setBooleanProperty(String arg0, boolean arg1) throws JMSException {
        delegate.setBooleanProperty(arg0, arg1);
    }

    @Override
    public void setByteProperty(String arg0, byte arg1) throws JMSException {
        delegate.setByteProperty(arg0, arg1);
    }

    @Override
    public void setShortProperty(String arg0, short arg1) throws JMSException {
        delegate.setShortProperty(arg0, arg1);
    }

    @Override
    public void setIntProperty(String arg0, int arg1) throws JMSException {
        delegate.setIntProperty(arg0, arg1);
    }

    @Override
    public void setLongProperty(String arg0, long arg1) throws JMSException {
        delegate.setLongProperty(arg0, arg1);
    }

    @Override
    public void setFloatProperty(String arg0, float arg1) throws JMSException {
        delegate.setFloatProperty(arg0, arg1);
    }

    @Override
    public void setDoubleProperty(String arg0, double arg1) throws JMSException {
        delegate.setDoubleProperty(arg0, arg1);
    }

    @Override
    public void setStringProperty(String arg0, String arg1) throws JMSException {
        delegate.setStringProperty(arg0, arg1);
    }

    @Override
    public void setObjectProperty(String arg0, Object arg1) throws JMSException {
        delegate.setObjectProperty(arg0, arg1);
    }

    @Override
    public void acknowledge() throws JMSException {
        delegate.acknowledge();
    }

    @Override
    public void clearBody() throws JMSException {
        delegate.clearBody();
    }

    @Override
    public <T> T getBody(Class<T> c) throws JMSException {
        return Jms2MessageUtil.getBody(delegate, c);
    }

    @Override
    public boolean isBodyAssignableTo(Class c) throws JMSException {
        return Jms2MessageUtil.isBodyAssignableTo(delegate, c);
    }
    
    protected Message getDelegate() {
        return delegate;
    }
    
}
