package com.melowe.jms2.compat;

import com.mockrunner.mock.jms.MockBytesMessage;
import com.mockrunner.mock.jms.MockMapMessage;
import com.mockrunner.mock.jms.MockObjectMessage;
import com.mockrunner.mock.jms.MockStreamMessage;
import com.mockrunner.mock.jms.MockTextMessage;
import java.io.Serializable;
import java.util.Map;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageFormatException;
import javax.jms.ObjectMessage;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

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
        assertEquals(9989L, jms2Message.getJMSDeliveryTime());
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
        jms2Message.setBooleanProperty("myprop", true);
        verify(mockMessage).setBooleanProperty("myprop", true);

    }

    @Test
    public void testSetByteProperty() throws Exception {
        byte b = 1;
        jms2Message.setByteProperty("myprop", b);
        verify(mockMessage).setByteProperty("myprop", b);
    }

    @Test
    public void testSetShortProperty() throws Exception {
        short s = 1;
        jms2Message.setShortProperty("myprop", s);
        verify(mockMessage).setShortProperty("myprop", s);
    }

    @Test
    public void testSetIntProperty() throws Exception {
        int i = 1;
        jms2Message.setIntProperty("myprop", i);
        verify(mockMessage).setIntProperty("myprop", i);
    }

    @Test
    public void testSetLongProperty() throws Exception {
        long l = 1;
        jms2Message.setLongProperty("myprop", l);
        verify(mockMessage).setLongProperty("myprop", l);
    }

    @Test
    public void testSetFloatProperty() throws Exception {
        float f = 1.8f;
        jms2Message.setFloatProperty("myprop", f);
        verify(mockMessage).setFloatProperty("myprop", f);
    }

    @Test
    public void testSetDoubleProperty() throws Exception {
        double f = 1.8092d;
        jms2Message.setDoubleProperty("myprop", f);
        verify(mockMessage).setDoubleProperty("myprop", f);
    }

    @Test
    public void testSetStringProperty() throws Exception {
        String s = "SOMEVALUE&**£((£";
        jms2Message.setStringProperty("myprop", s);
        verify(mockMessage).setStringProperty("myprop", s);
    }

    @Test
    public void testSetObjectProperty() throws Exception {
        Object o = "SOMEVALUE&**£((£";
        jms2Message.setObjectProperty("myprop", o);
        verify(mockMessage).setObjectProperty("myprop", o);
        
    }

    @Test
    public void testAcknowledge() throws Exception {
        jms2Message.acknowledge();
        verify(mockMessage).acknowledge();
    }

    @Test
    public void testClearBody() throws Exception {
        jms2Message.clearBody();
        verify(mockMessage).clearBody();
    }

    @Test
    public void testGetStringBody() throws Exception {
       Message msg =  new Jms2Message(new MockTextMessage("HELLOW"));
       assertEquals("HELLOW", msg.getBody(String.class));
    }
    
    @Test
    public void testGetMapBody() throws Exception {
        MapMessage mapMessage = new MockMapMessage();
        mapMessage.setString("GREETING", "HELLOW");
        
       Message msg =  new Jms2Message(mapMessage);
       assertEquals("HELLOW", msg.getBody(Map.class).get("GREETING"));
    }

    static class ObjectBody implements Serializable {
        public String getValue() {
            return "HELLOW";
        }
    }
    
     @Test
    public void testGetObjectBody() throws Exception {
         ObjectMessage oMessage = new MockObjectMessage(new ObjectBody());
 
       Message msg =  new Jms2Message(oMessage);
       ObjectBody body = (ObjectBody) msg.getBody(Serializable.class);
       
       assertEquals("HELLOW", body.getValue());
    }
    

    @Ignore
    @Test
    public void testGetBytesBody() throws Exception {
        MockBytesMessage bmsg = new MockBytesMessage();
        bmsg.writeBytes("HELLOW".getBytes());
        bmsg.setReadOnly(true);
        
        Message msg =  new Jms2Message(bmsg);
        assertEquals("HELLOW", new String(msg.getBody(byte[].class)));
        
    }
    
    @Test(expected = MessageFormatException.class)
    public void testGetBodyFromStreamMessage() throws Exception {
        Message msg =  new Jms2Message(new MockStreamMessage());
        msg.getBody(String.class);
        
    }
    
    @Test
    public void testIsBodyAssignableTo() throws Exception {
        
        assertTrue(jms2Message.isBodyAssignableTo(String.class));
        assertTrue(jms2Message.isBodyAssignableTo(Serializable.class));
        assertTrue(jms2Message.isBodyAssignableTo(Map.class));
        assertTrue(jms2Message.isBodyAssignableTo(byte[].class));

        
        assertTrue(new Jms2Message(new MockTextMessage()).isBodyAssignableTo(String.class));
        assertTrue(new Jms2Message(new MockBytesMessage()).isBodyAssignableTo(byte[].class));
        assertTrue(new Jms2Message(new MockMapMessage()).isBodyAssignableTo(Map.class));
        assertTrue(new Jms2Message(new MockObjectMessage()).isBodyAssignableTo(Serializable.class));
        assertFalse(new Jms2Message(new MockStreamMessage()).isBodyAssignableTo(Object.class));
    }

    @Test
    public void testGetDelegate() {
        assertSame(mockMessage, jms2Message.getDelegate());

    }

}
