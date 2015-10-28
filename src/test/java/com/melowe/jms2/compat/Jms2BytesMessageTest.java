package com.melowe.jms2.compat;

import java.util.Collections;
import javax.jms.BytesMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class Jms2BytesMessageTest {

    BytesMessage mockMessage;

    Jms2BytesMessage jms2BytesMessage;

    public Jms2BytesMessageTest() {
    }

    @Before
    public void setUp() {
        mockMessage = mock(BytesMessage.class);
        jms2BytesMessage = new Jms2BytesMessage(mockMessage);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mockMessage);
    }

    @Test
    public void testGetBodyLength() throws Exception {
        jms2BytesMessage.getBodyLength();
        verify(mockMessage, times(1)).getBodyLength();

    }

    @Test
    public void testReadBoolean() throws Exception {
        jms2BytesMessage.readBoolean();
        verify(mockMessage, times(1)).readBoolean();
    }

    @Test
    public void testReadByte() throws Exception {
        jms2BytesMessage.readByte();
        verify(mockMessage, times(1)).readByte();
    }

    @Test
    public void testReadUnsignedByte() throws Exception {
        jms2BytesMessage.readUnsignedByte();
        verify(mockMessage, times(1)).readUnsignedByte();
    }

    @Test
    public void testReadShort() throws Exception {
        jms2BytesMessage.readShort();
        verify(mockMessage, times(1)).readShort();
    }

    @Test
    public void testReadUnsignedShort() throws Exception {
        jms2BytesMessage.readUnsignedShort();
        verify(mockMessage, times(1)).readUnsignedShort();
    }

    @Test
    public void testReadChar() throws Exception {
        jms2BytesMessage.readChar();
        verify(mockMessage, times(1)).readChar();
    }

    @Test
    public void testReadInt() throws Exception {
        jms2BytesMessage.readInt();
        verify(mockMessage, times(1)).readInt();
    }

    @Test
    public void testReadLong() throws Exception {
        jms2BytesMessage.readLong();
        verify(mockMessage, times(1)).readLong();
    }

    @Test
    public void testReadFloat() throws Exception {
        jms2BytesMessage.readFloat();
        verify(mockMessage, times(1)).readFloat();
    }

    @Test
    public void testReadDouble() throws Exception {
        jms2BytesMessage.readDouble();
        verify(mockMessage, times(1)).readDouble();
    }

    @Test
    public void testReadUTF() throws Exception {
        jms2BytesMessage.readUTF();
        verify(mockMessage, times(1)).readUTF();
    }

    @Test
    public void testReadBytes_byteArr() throws Exception {
        byte[] data = new byte[10];
        jms2BytesMessage.readBytes(data);
        verify(mockMessage, times(1)).readBytes(data);
    }

    @Test
    public void testReadBytes_byteArr_int() throws Exception {
        byte[] data = new byte[10];
        jms2BytesMessage.readBytes(data, 10);
        verify(mockMessage, times(1)).readBytes(data, 10);
    }

    @Test
    public void testWriteBoolean() throws Exception {
        jms2BytesMessage.writeBoolean(true);
        jms2BytesMessage.writeBoolean(false);
        verify(mockMessage, times(1)).writeBoolean(true);
        verify(mockMessage, times(1)).writeBoolean(false);

    }

    @Test
    public void testWriteByte() throws Exception {
        byte b = 1;
        jms2BytesMessage.writeByte(b);
        verify(mockMessage, times(1)).writeByte(b);
    }

    @Test
    public void testWriteShort() throws Exception {
        short s = 10;
        jms2BytesMessage.writeShort(s);
        verify(mockMessage, times(1)).writeShort(s);
    }

    @Test
    public void testWriteChar() throws Exception {
        char c = 'c';
        jms2BytesMessage.writeChar(c);
        verify(mockMessage, times(1)).writeChar(c);
    }

    @Test
    public void testWriteInt() throws Exception {
        int i = 998;
        jms2BytesMessage.writeInt(i);
        verify(mockMessage, times(1)).writeInt(i);

    }

    @Test
    public void testWriteLong() throws Exception {
        long i = Long.MAX_VALUE;
        jms2BytesMessage.writeLong(i);
        verify(mockMessage, times(1)).writeLong(i);
    }

    @Test
    public void testWriteFloat() throws Exception {
        float i = Float.MAX_VALUE;
        jms2BytesMessage.writeFloat(i);
        verify(mockMessage, times(1)).writeFloat(i);
    }

    @Test
    public void testWriteDouble() throws Exception {
        double i = Double.MAX_VALUE;
        jms2BytesMessage.writeDouble(i);
        verify(mockMessage, times(1)).writeDouble(i);
    }

    @Test
    public void testWriteUTF() throws Exception {
        String utf = "HELLOW";
        jms2BytesMessage.writeUTF(utf);
        verify(mockMessage, times(1)).writeUTF(utf);
    }

    @Test
    public void testWriteBytes_byteArr() throws Exception {
        byte[] data = "DATA".getBytes();
        jms2BytesMessage.writeBytes(data);
        verify(mockMessage, times(1)).writeBytes(data);
    }

    @Test
    public void testWriteBytes_3args() throws Exception {
        byte[] data = "DATA".getBytes();
        jms2BytesMessage.writeBytes(data, 0, 10);
        verify(mockMessage, times(1)).writeBytes(data, 0, 10);
    }

    @Test
    public void testWriteObject() throws Exception {
        Object obj = Collections.EMPTY_LIST;
        jms2BytesMessage.writeObject(obj);
        verify(mockMessage, times(1)).writeObject(obj);

    }

    @Test
    public void testReset() throws Exception {
        jms2BytesMessage.reset();
        verify(mockMessage, times(1)).reset();
    }

}
