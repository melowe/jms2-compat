package com.melowe.jms2.compat;

import com.melowe.jms2.compat.Jms2Message;
import com.mockrunner.mock.jms.MockMessage;
import javax.jms.Destination;
import javax.jms.Message;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class Jms2MessageTest {

    Message mockMessage;

    Jms2Message jms2Message;

    public Jms2MessageTest() {
    }

    @Before
    public void setUp() {
        mockMessage = mock(Message.class);
        jms2Message = new Jms2Message(mockMessage);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mockMessage);
        mockMessage = null;
        jms2Message = null;
    }

    @Test
    public void testGetJMSMessageID() throws Exception {
        jms2Message.getJMSMessageID();
        verify(mockMessage, times(1)).getJMSMessageID();
    }

    @Test
    public void testSetJMSMessageID() throws Exception {
        jms2Message.setJMSMessageID("HELLO");
        verify(mockMessage, times(1)).setJMSMessageID("HELLO");
    }

    @Test
    public void testGetJMSTimestamp() throws Exception {
        jms2Message.getJMSTimestamp();
        verify(mockMessage, times(1)).getJMSTimestamp();
    }

    @Test
    public void testSetJMSTimestamp() throws Exception {
        jms2Message.setJMSTimestamp(999L);
        verify(mockMessage, times(1)).setJMSTimestamp(999L);
    }

    @Test
    public void testGetJMSCorrelationIDAsBytes() throws Exception {
        jms2Message.getJMSCorrelationIDAsBytes();
        verify(mockMessage, times(1)).getJMSCorrelationIDAsBytes();

    }

    @Test
    public void testSetJMSCorrelationIDAsBytes() throws Exception {
        byte[] data = "HELLO".getBytes();
        jms2Message.setJMSCorrelationIDAsBytes(data);
        verify(mockMessage, times(1)).setJMSCorrelationIDAsBytes(data);

    }

    @Test
    public void testSetJMSCorrelationID() throws Exception {
        String data = "HELLO";
        jms2Message.setJMSCorrelationID(data);
        verify(mockMessage, times(1)).setJMSCorrelationID(data);

    }

    @Test
    public void testGetJMSCorrelationID() throws Exception {
        jms2Message.getJMSCorrelationID();
        verify(mockMessage, times(1)).getJMSCorrelationID();
    }

    @Test
    public void testGetJMSReplyTo() throws Exception {
        jms2Message.getJMSReplyTo();
        verify(mockMessage, times(1)).getJMSReplyTo();
    }

    @Test
    public void testSetJMSReplyTo() throws Exception {
        Destination mockReplyTo = mock(Destination.class);
        jms2Message.setJMSReplyTo(mockReplyTo);
        verify(mockMessage, times(1)).setJMSReplyTo(mockReplyTo);

    }

    @Test
    public void testGetJMSDestination() throws Exception {
        jms2Message.getJMSDestination();
        verify(mockMessage, times(1)).getJMSDestination();
    }

    @Test
    public void testSetJMSDestination() throws Exception {
        Destination destination = mock(Destination.class);
        jms2Message.setJMSDestination(destination);
        verify(mockMessage, times(1)).setJMSDestination(destination);
    }

    @Test
    public void testGetJMSDeliveryMode() throws Exception {
        jms2Message.getJMSDeliveryMode();
        verify(mockMessage, times(1)).getJMSDeliveryMode();
    }

    @Test
    public void testSetJMSDeliveryMode() throws Exception {
        jms2Message.setJMSDeliveryMode(11);
        verify(mockMessage, times(1)).setJMSDeliveryMode(11);
    }

    @Test
    public void testGetJMSRedelivered() throws Exception {
        jms2Message.getJMSRedelivered();
        verify(mockMessage, times(1)).getJMSRedelivered();
    }

    @Test
    public void testSetJMSRedelivered() throws Exception {
        jms2Message.setJMSRedelivered(true);
        verify(mockMessage, times(1)).setJMSRedelivered(true);
    }

    @Test
    public void testGetJMSType() throws Exception {
        jms2Message.getJMSType();
        verify(mockMessage, times(1)).getJMSType();
    }

    @Test
    public void testSetJMSType() throws Exception {
    }

    @Test
    public void testGetJMSExpiration() throws Exception {
        jms2Message.getJMSExpiration();
        verify(mockMessage, times(1)).getJMSExpiration();
    }

    @Test
    public void testSetJMSExpiration() throws Exception {
        jms2Message.setJMSExpiration(999L);
        verify(mockMessage, times(1)).setJMSExpiration(999L);
    }

    @Test
    public void testSetGetJMSDeliveryTime() throws Exception {
        jms2Message.setJMSDeliveryTime(9989L);
        assertEquals(9989L,jms2Message.getJMSDeliveryTime());
        verifyZeroInteractions(mockMessage);

    }

    @Test
    public void testGetJMSPriority() throws Exception {
        jms2Message.getJMSPriority();
        verify(mockMessage, times(1)).getJMSPriority();
    }

    @Test
    public void testSetJMSPriority() throws Exception {
    }

    @Test
    public void testClearProperties() throws Exception {
        jms2Message.clearProperties();
        verify(mockMessage, times(1)).clearProperties();
    }

    @Test
    public void testPropertyExists() throws Exception {
        jms2Message.propertyExists("foo");
        verify(mockMessage, times(1)).propertyExists("foo");
        
    }

    @Test
    public void testGetBooleanProperty() throws Exception {
        jms2Message.getBooleanProperty("myprop");
        verify(mockMessage, times(1)).getBooleanProperty("myprop");
    }

    @Test
    public void testGetByteProperty() throws Exception {
        jms2Message.getByteProperty("myprop");
        verify(mockMessage, times(1)).getByteProperty("myprop");
    }

    @Test
    public void testGetShortProperty() throws Exception {
        jms2Message.getShortProperty("myprop");
        verify(mockMessage, times(1)).getShortProperty("myprop");
    }

    @Test
    public void testGetIntProperty() throws Exception {
        jms2Message.getIntProperty("myprop");
        verify(mockMessage, times(1)).getIntProperty("myprop");
    }

    @Test
    public void testGetLongProperty() throws Exception {
        jms2Message.getLongProperty("myprop");
        verify(mockMessage, times(1)).getLongProperty("myprop");
    }

    @Test
    public void testGetFloatProperty() throws Exception {
        jms2Message.getFloatProperty("myprop");
        verify(mockMessage, times(1)).getFloatProperty("myprop");
    }

    @Test
    public void testGetDoubleProperty() throws Exception {
        jms2Message.getDoubleProperty("myprop");
        verify(mockMessage, times(1)).getDoubleProperty("myprop");
    }

    @Test
    public void testGetStringProperty() throws Exception {
        jms2Message.getStringProperty("myprop");
        verify(mockMessage, times(1)).getStringProperty("myprop");
    }

    @Test
    public void testGetObjectProperty() throws Exception {
        jms2Message.getObjectProperty("myprop");
        verify(mockMessage, times(1)).getObjectProperty("myprop");
    }

    @Test
    public void testGetPropertyNames() throws Exception {
        jms2Message.getPropertyNames();
        verify(mockMessage, times(1)).getPropertyNames();
    }

    @Test
    public void testSetBooleanProperty() throws Exception {
    }

    @Test
    public void testSetByteProperty() throws Exception {
    }

    @Test
    public void testSetShortProperty() throws Exception {
    }

    @Test
    public void testSetIntProperty() throws Exception {
    }

    @Test
    public void testSetLongProperty() throws Exception {
    }

    @Test
    public void testSetFloatProperty() throws Exception {
    }

    @Test
    public void testSetDoubleProperty() throws Exception {
    }

    @Test
    public void testSetStringProperty() throws Exception {
    }

    @Test
    public void testSetObjectProperty() throws Exception {
    }

    @Test
    public void testAcknowledge() throws Exception {
    }

    @Test
    public void testClearBody() throws Exception {
        new MockMessage().clearBody();
    }

    @Test
    public void testGetBody() throws Exception {
           
    }

    @Test
    public void testIsBodyAssignableTo() throws Exception {
    }

    @Test
    public void testGetDelegate() {
        assertSame(mockMessage,jms2Message.getDelegate());
        
    }

    

}
