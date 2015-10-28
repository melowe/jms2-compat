
package com.melowe.jms2.compat;

import java.util.Enumeration;
import javax.jms.JMSException;
import javax.jms.MapMessage;


public final class Jms2MapMessage extends Jms2Message implements MapMessage {
    
    private final MapMessage delegate;

    public Jms2MapMessage(MapMessage delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public boolean getBoolean(String name) throws JMSException {
        return delegate.getBoolean(name);
    }

    @Override
    public byte getByte(String name) throws JMSException {
        return delegate.getByte(name);
    }

    @Override
    public short getShort(String name) throws JMSException {
        return delegate.getShort(name);
    }

    @Override
    public char getChar(String name) throws JMSException {
        return delegate.getChar(name);
    }

    @Override
    public int getInt(String name) throws JMSException {
        return delegate.getInt(name);
    }

    @Override
    public long getLong(String name) throws JMSException {
        return delegate.getLong(name);
    }

    @Override
    public float getFloat(String name) throws JMSException {
        return delegate.getFloat(name);
    }

    @Override
    public double getDouble(String name) throws JMSException {
        return delegate.getDouble(name);
    }

    @Override
    public String getString(String name) throws JMSException {
        return delegate.getString(name);
    }

    @Override
    public byte[] getBytes(String name) throws JMSException {
        return delegate.getBytes(name);
    }

    @Override
    public Object getObject(String name) throws JMSException {
        return delegate.getObject(name);
    }

    @Override
    public Enumeration getMapNames() throws JMSException {
        return delegate.getMapNames();
    }

    @Override
    public void setBoolean(String arg0, boolean arg1) throws JMSException {
        delegate.setBoolean(arg0, arg1);
    }

    @Override
    public void setByte(String arg0, byte arg1) throws JMSException {
        delegate.setByte(arg0, arg1);
    }

    @Override
    public void setShort(String arg0, short arg1) throws JMSException {
        delegate.setShort(arg0, arg1);
    }

    @Override
    public void setChar(String arg0, char arg1) throws JMSException {
        delegate.setChar(arg0, arg1);
    }

    @Override
    public void setInt(String arg0, int arg1) throws JMSException {
        delegate.setInt(arg0, arg1);
    }

    @Override
    public void setLong(String arg0, long arg1) throws JMSException {
        delegate.setLong(arg0, arg1);
    }

    @Override
    public void setFloat(String arg0, float arg1) throws JMSException {
        delegate.setFloat(arg0, arg1);
    }

    @Override
    public void setDouble(String arg0, double arg1) throws JMSException {
        delegate.setDouble(arg0, arg1);
    }

    @Override
    public void setString(String arg0, String arg1) throws JMSException {
        delegate.setString(arg0, arg1);
    }

    @Override
    public void setBytes(String arg0, byte[] arg1) throws JMSException {
        delegate.setBytes(arg0, arg1);
    }

    @Override
    public void setBytes(String arg0, byte[] arg1, int arg2, int arg3) throws JMSException {
        delegate.setBytes(arg0, arg1, arg2, arg3);
    }

    @Override
    public void setObject(String arg0, Object arg1) throws JMSException {
        delegate.setObject(arg0, arg1);
    }

    @Override
    public boolean itemExists(String name) throws JMSException {
        return delegate.itemExists(name);
    }

  
    
}
