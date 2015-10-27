package com.melowe.jms2.compat;

import com.melowe.jms2.compat.Jms2MapMessage;
import javax.jms.MapMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

public class Jms2MapMessageTest {

    MapMessage mockMessage;

    Jms2MapMessage mapMessage;

    public Jms2MapMessageTest() {
    }

    @Before
    public void setUp() {
        mockMessage = mock(MapMessage.class);
        mapMessage = new Jms2MapMessage(mockMessage);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mockMessage);
    }

    @Test
    public void testGetBoolean() throws Exception {
        mapMessage.getBoolean("myprop");
        verify(mockMessage).getBoolean("myprop");
    }

    @Test
    public void testGetByte() throws Exception {
        mapMessage.getByte("myprop");
        verify(mockMessage).getByte("myprop");
    }

    @Test
    public void testGetShort() throws Exception {
        mapMessage.getShort("myprop");
        verify(mockMessage).getShort("myprop");
    }

    @Test
    public void testGetChar() throws Exception {
        mapMessage.getChar("myprop");
        verify(mockMessage).getChar("myprop");
    }

    @Test
    public void testGetInt() throws Exception {
        mapMessage.getInt("myprop");
        verify(mockMessage).getInt("myprop");
    }

    @Test
    public void testGetLong() throws Exception {
        mapMessage.getLong("myprop");
        verify(mockMessage).getLong("myprop");
    }

    @Test
    public void testGetFloat() throws Exception {
        mapMessage.getFloat("myprop");
        verify(mockMessage).getFloat("myprop");
    }

    @Test
    public void testGetDouble() throws Exception {
        mapMessage.getDouble("myprop");
        verify(mockMessage).getDouble("myprop");
    }

    @Test
    public void testGetString() throws Exception {
        mapMessage.getString("myprop");
        verify(mockMessage).getString("myprop");
    }

    @Test
    public void testGetBytes() throws Exception {
        mapMessage.getBytes("myprop");
        verify(mockMessage).getBytes("myprop");
    }

    @Test
    public void testGetObject() throws Exception {
        mapMessage.getObject("myprop");
        verify(mockMessage).getObject("myprop");
    }

    @Test
    public void testGetMapNames() throws Exception {
        mapMessage.getMapNames();
        verify(mockMessage).getMapNames();
    }

    @Test
    public void testSetBoolean() throws Exception {
        mapMessage.setBoolean("myprop", true);
        verify(mockMessage).setBoolean("myprop", true);
    }

    @Test
    public void testSetByte() throws Exception {
        mapMessage.setByte("myprop", Byte.MAX_VALUE);
        verify(mockMessage).setByte("myprop", Byte.MAX_VALUE);
    }

    @Test
    public void testSetShort() throws Exception {
        mapMessage.setShort("myprop", Short.MAX_VALUE);
        verify(mockMessage).setShort("myprop", Short.MAX_VALUE);
    }

    @Test
    public void testSetChar() throws Exception {
        mapMessage.setChar("myprop", 'c');
        verify(mockMessage).setChar("myprop", 'c');
    }

    @Test
    public void testSetInt() throws Exception {
        mapMessage.setInt("myprop", 99);
        verify(mockMessage).setInt("myprop", 99);
    }

    @Test
    public void testSetLong() throws Exception {
        mapMessage.setLong("myprop", 999L);
        verify(mockMessage).setLong("myprop", 999L);
    }

    @Test
    public void testSetFloat() throws Exception {
        mapMessage.setFloat("myprop", Float.MAX_VALUE);
        verify(mockMessage).setFloat("myprop", Float.MAX_VALUE);
    }

    @Test
    public void testSetDouble() throws Exception {
        mapMessage.setDouble("myprop", Double.MAX_VALUE);
        verify(mockMessage).setDouble("myprop", Double.MAX_VALUE);
    }

    @Test
    public void testSetString() throws Exception {
        mapMessage.setString("myprop", "somevalue");
        verify(mockMessage).setString("myprop", "somevalue");
    }

    @Test
    public void testSetBytes() throws Exception {
        mapMessage.setBytes("myprop", "somevalue".getBytes());
        verify(mockMessage).setBytes("myprop", "somevalue".getBytes());
    }

    @Test
    public void testSetBytes_4args() throws Exception {
        mapMessage.setBytes("myprop", "somevalue".getBytes(), 10, 90);
        verify(mockMessage).setBytes("myprop", "somevalue".getBytes(), 10, 90);
    }

    static class SomeObj {
    }

    @Test
    public void testSetObject() throws Exception {
        Object obj = new SomeObj();
        mapMessage.setObject("myprop", obj);
        verify(mockMessage).setObject("myprop", obj);
    }

    @Test
    public void testItemExists() throws Exception {
        mapMessage.itemExists("myprop");
        verify(mockMessage).itemExists("myprop");

    }

}
