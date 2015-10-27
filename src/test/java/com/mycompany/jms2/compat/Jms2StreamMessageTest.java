package com.mycompany.jms2.compat;

import java.util.Collections;
import javax.jms.StreamMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class Jms2StreamMessageTest {

    StreamMessage mockMessage;

    Jms2StreamMessage jms2StreamMessage;

    public Jms2StreamMessageTest() {
    }

    @Before
    public void setUp() {
        mockMessage = mock(StreamMessage.class);
        jms2StreamMessage = new Jms2StreamMessage(mockMessage);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mockMessage);
    }



    @Test
    public void testReadBoolean() throws Exception {
        jms2StreamMessage.readBoolean();
        verify(mockMessage, times(1)).readBoolean();
    }

    @Test
    public void testReadByte() throws Exception {
        jms2StreamMessage.readByte();
        verify(mockMessage, times(1)).readByte();
    }



    @Test
    public void testReadShort() throws Exception {
        jms2StreamMessage.readShort();
        verify(mockMessage, times(1)).readShort();
    }



    @Test
    public void testReadChar() throws Exception {
        jms2StreamMessage.readChar();
        verify(mockMessage, times(1)).readChar();
    }

    @Test
    public void testReadInt() throws Exception {
        jms2StreamMessage.readInt();
        verify(mockMessage, times(1)).readInt();
    }

    @Test
    public void testReadLong() throws Exception {
        jms2StreamMessage.readLong();
        verify(mockMessage, times(1)).readLong();
    }

    @Test
    public void testReadFloat() throws Exception {
        jms2StreamMessage.readFloat();
        verify(mockMessage, times(1)).readFloat();
    }

    @Test
    public void testReadDouble() throws Exception {
        jms2StreamMessage.readDouble();
        verify(mockMessage, times(1)).readDouble();
    }



    @Test
    public void testReadBytes_byteArr() throws Exception {
        byte[] data = new byte[10];
        jms2StreamMessage.readBytes(data);
        verify(mockMessage, times(1)).readBytes(data);
    }


    @Test
    public void testWriteBoolean() throws Exception {
        jms2StreamMessage.writeBoolean(true);
        jms2StreamMessage.writeBoolean(false);
        verify(mockMessage, times(1)).writeBoolean(true);
        verify(mockMessage, times(1)).writeBoolean(false);

    }

    @Test
    public void testWriteByte() throws Exception {
        byte b = 1;
        jms2StreamMessage.writeByte(b);
        verify(mockMessage, times(1)).writeByte(b);
    }

    @Test
    public void testWriteShort() throws Exception {
        short s = 10;
        jms2StreamMessage.writeShort(s);
        verify(mockMessage, times(1)).writeShort(s);
    }

    @Test
    public void testWriteChar() throws Exception {
        char c = 'c';
        jms2StreamMessage.writeChar(c);
        verify(mockMessage, times(1)).writeChar(c);
    }

    @Test
    public void testWriteInt() throws Exception {
        int i = 998;
        jms2StreamMessage.writeInt(i);
        verify(mockMessage, times(1)).writeInt(i);

    }

    @Test
    public void testWriteLong() throws Exception {
        long i = Long.MAX_VALUE;
        jms2StreamMessage.writeLong(i);
        verify(mockMessage, times(1)).writeLong(i);
    }

    @Test
    public void testWriteFloat() throws Exception {
        float i = Float.MAX_VALUE;
        jms2StreamMessage.writeFloat(i);
        verify(mockMessage, times(1)).writeFloat(i);
    }

    @Test
    public void testWriteDouble() throws Exception {
        double i = Double.MAX_VALUE;
        jms2StreamMessage.writeDouble(i);
        verify(mockMessage, times(1)).writeDouble(i);
    }

    @Test
    public void testWriteBytes_byteArr() throws Exception {
        byte[] data = "DATA".getBytes();
        jms2StreamMessage.writeBytes(data);
        verify(mockMessage, times(1)).writeBytes(data);
    }

    @Test
    public void testWriteBytes_3args() throws Exception {
        byte[] data = "DATA".getBytes();
        jms2StreamMessage.writeBytes(data,0,10);
        verify(mockMessage, times(1)).writeBytes(data,0,10);
    }

    @Test
    public void testWriteObject() throws Exception {
        Object obj = Collections.EMPTY_LIST;
        jms2StreamMessage.writeObject(obj);
        verify(mockMessage, times(1)).writeObject(obj);
        
    }

    @Test
    public void testReset() throws Exception {
        jms2StreamMessage.reset();
        verify(mockMessage, times(1)).reset();
    }
    
    
    @Test
    public void testReadString() throws Exception {
        jms2StreamMessage.readString();
        verify(mockMessage, times(1)).readString();
        
    }
    
    @Test
    public void testReadObject() throws Exception {
        jms2StreamMessage.readObject();
        verify(mockMessage, times(1)).readObject();
        
    }
    
    @Test
    public void testWriteString() throws Exception {
        jms2StreamMessage.writeString("HELLOW");
        verify(mockMessage, times(1)).writeString("HELLOW");
        
    }
}
