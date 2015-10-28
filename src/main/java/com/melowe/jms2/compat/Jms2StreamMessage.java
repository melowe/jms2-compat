package com.melowe.jms2.compat;

import javax.jms.JMSException;
import javax.jms.StreamMessage;

public final class Jms2StreamMessage extends Jms2Message implements StreamMessage {

    private final StreamMessage delegate;

    public Jms2StreamMessage(StreamMessage delegate) {
        super(delegate);
        this.delegate = delegate;
    }

    @Override
    public boolean readBoolean() throws JMSException {
        return delegate.readBoolean();
    }

    @Override
    public byte readByte() throws JMSException {
        return delegate.readByte();
    }

    @Override
    public short readShort() throws JMSException {
        return delegate.readShort();
    }

    @Override
    public char readChar() throws JMSException {
        return delegate.readChar();
    }

    @Override
    public int readInt() throws JMSException {
        return delegate.readInt();
    }

    @Override
    public long readLong() throws JMSException {
        return delegate.readLong();
    }

    @Override
    public float readFloat() throws JMSException {
        return delegate.readFloat();
    }

    @Override
    public double readDouble() throws JMSException {
        return delegate.readDouble();
    }

    @Override
    public String readString() throws JMSException {
        return delegate.readString();
    }

    @Override
    public int readBytes(byte[] value) throws JMSException {
        return delegate.readBytes(value);
    }

    @Override
    public Object readObject() throws JMSException {
        return delegate.readObject();
    }

    @Override
    public void writeBoolean(boolean value) throws JMSException {
        delegate.writeBoolean(value);
    }

    @Override
    public void writeByte(byte value) throws JMSException {
        delegate.writeByte(value);
    }

    @Override
    public void writeShort(short value) throws JMSException {
        delegate.writeShort(value);
    }

    @Override
    public void writeChar(char value) throws JMSException {
        delegate.writeChar(value);
    }

    @Override
    public void writeInt(int value) throws JMSException {
        delegate.writeInt(value);
    }

    @Override
    public void writeLong(long value) throws JMSException {
        delegate.writeLong(value);
    }

    @Override
    public void writeFloat(float value) throws JMSException {
        delegate.writeFloat(value);
    }

    @Override
    public void writeDouble(double value) throws JMSException {
        delegate.writeDouble(value);
    }

    @Override
    public void writeString(String value) throws JMSException {
        delegate.writeString(value);
    }

    @Override
    public void writeBytes(byte[] value) throws JMSException {
        delegate.writeBytes(value);
    }

    @Override
    public void writeBytes(byte[] arg0, int arg1, int arg2) throws JMSException {
        delegate.writeBytes(arg0, arg1, arg2);
    }

    @Override
    public void writeObject(Object value) throws JMSException {
        delegate.writeObject(value);
    }

    @Override
    public void reset() throws JMSException {
        delegate.reset();
    }

}
