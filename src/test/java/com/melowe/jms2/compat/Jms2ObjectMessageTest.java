package com.melowe.jms2.compat;

import java.io.Serializable;
import javax.jms.ObjectMessage;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

public class Jms2ObjectMessageTest {

    ObjectMessage mockObjectMessage;

    Jms2ObjectMessage objectMessage;

    public Jms2ObjectMessageTest() {
    }

    @Before
    public void setUp() {
        mockObjectMessage = mock(ObjectMessage.class);
        objectMessage = new Jms2ObjectMessage(mockObjectMessage);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mockObjectMessage);
    }

    @Test
    public void testSetObject() throws Exception {
        Serializable obj = "DATA";
        objectMessage.setObject(obj);
        verify(mockObjectMessage).setObject(obj);
    }

    @Test
    public void testGetObject() throws Exception {
        when(mockObjectMessage.getObject()).thenReturn("VALUE");
        Serializable result = objectMessage.getObject();
        assertEquals("VALUE", result);
        verify(mockObjectMessage).getObject();
    }

}
