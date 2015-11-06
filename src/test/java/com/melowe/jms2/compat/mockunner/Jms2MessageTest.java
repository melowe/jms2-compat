package com.melowe.jms2.compat.mockunner;

import com.melowe.jms2.compat.Jms2BytesMessage;
import com.melowe.jms2.compat.Jms2Message;
import com.mockrunner.mock.jms.MockBytesMessage;
import com.mockrunner.mock.jms.MockMessage;
import com.mockrunner.mock.jms.MockQueue;
import com.mockrunner.mock.jms.MockTextMessage;
import java.util.Arrays;
import java.util.Enumeration;
import java.util.List;
import javax.jms.Destination;
import javax.jms.JMSException;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

public class Jms2MessageTest {

    MockMessage mockMessage;

    Jms2Message message;

    @Before
    public void setUp() throws Exception {
        mockMessage = new MockMessage();
        message = new Jms2Message(mockMessage);
    }

    @After
    public void tearDown() throws Exception {
        mockMessage = null;
        message = null;
    }

    @Test
    public void setGetJMSMessageID() throws Exception {
        String correlationId = "JUNITCID";
        message.setJMSCorrelationID(correlationId);
        assertEquals(correlationId, mockMessage.getJMSCorrelationID());
        assertEquals(correlationId, message.getJMSCorrelationID());
    }

    @Test
    public void setGetJMSTimestamp() throws Exception {
        long timestamp = 999L;
        message.setJMSTimestamp(timestamp);
        assertEquals(timestamp, mockMessage.getJMSTimestamp());
        assertEquals(timestamp, message.getJMSTimestamp());

    }

    @Test
    public void setGetJMSCorrelationIDAsBytes() throws Exception {
        byte[] correlationIDBytes = "CORRID".getBytes();
        message.setJMSCorrelationIDAsBytes(correlationIDBytes);
        assertArrayEquals(correlationIDBytes, mockMessage.getJMSCorrelationIDAsBytes());
        assertArrayEquals(correlationIDBytes, message.getJMSCorrelationIDAsBytes());
    }

    @Test
    public void setGetJMSCorrelationID() throws Exception {
        String correlationID = "CORRID";
        message.setJMSCorrelationID(correlationID);
        assertEquals(correlationID, mockMessage.getJMSCorrelationID());
        assertEquals(correlationID, message.getJMSCorrelationID());
    }

    @Test
    public void setGetJMSReplyTo() throws Exception {
        Destination replyTo = new MockQueue("JUNITQ");
        message.setJMSReplyTo(replyTo);
        assertEquals(replyTo, mockMessage.getJMSReplyTo());
        assertEquals(replyTo, message.getJMSReplyTo());

    }

    @Test
    public void setGetJMSDestination() throws Exception {
        Destination destination = new MockQueue("JUNITQ");
        message.setJMSDestination(destination);
        assertEquals(destination, mockMessage.getJMSDestination());
        assertEquals(destination, message.getJMSDestination());

    }

    @Test
    public void setGetJMSDeliveryMode() throws Exception {
        int deliveryMode = 99;
        message.setJMSDeliveryMode(deliveryMode);
        assertEquals(deliveryMode, mockMessage.getJMSDeliveryMode());
        assertEquals(deliveryMode, message.getJMSDeliveryMode());
    }

    @Test
    public void setGetJMSRedelivered() throws Exception {
        boolean redelivered = true;
        message.setJMSRedelivered(redelivered);
        assertEquals(redelivered, mockMessage.getJMSRedelivered());
        assertEquals(redelivered, message.getJMSRedelivered());

    }

    @Test
    public void setGetJMSType() throws Exception {
        String type = "SOMETYPE";
        message.setJMSType(type);
        assertEquals(type, mockMessage.getJMSType());
        assertEquals(type, message.getJMSType());
    }

    @Test
    public void setGetJMSExpiration() throws Exception {
        long expiration = 98272L;
        message.setJMSExpiration(expiration);
        assertEquals(expiration, mockMessage.getJMSExpiration());
        assertEquals(expiration, message.getJMSExpiration());
    }

    /**
     * @throws Exception
     */
    @Test
    public void setGetJMSDeliveryTime() throws Exception {
        long deliveryTime = 2982929L;
        message.setJMSDeliveryTime(deliveryTime);
        assertEquals(deliveryTime, message.getJMSDeliveryTime());
    }

    @Test
    public void setGetJMSPriority() throws Exception {
        int priority = 9;
        message.setJMSPriority(priority);
        assertEquals(priority, mockMessage.getJMSPriority());
    }

    @Ignore
    @Test
    public void clearProperties() throws Exception {

        long deliveryTime = 999L;
        String propertyName = "MYPROPERTY";
        String propertyValue = "HELLOW";
        mockMessage.setJMSCorrelationID("CORRID");
        message.setJMSDeliveryTime(deliveryTime);

        mockMessage.setStringProperty(propertyName, propertyValue);
        assertEquals(propertyValue, mockMessage.getStringProperty(propertyName));

        message.clearProperties();

        assertNull(mockMessage.getStringProperty(propertyName));

        assertEquals("CORRID", mockMessage.getJMSCorrelationID());

    }

    @Test
    public void propertyExists() throws Exception {
        mockMessage.setStringProperty("GREETING", "HELLOW");
        assertTrue(message.propertyExists("GREETING"));
        assertFalse(message.propertyExists("BOGUS"));

    }

    @Test
    public void setGetBooleanProperty() throws Exception {
        String name = "SOMEPROP";
        message.setBooleanProperty(name, true);

        assertTrue(mockMessage.getBooleanProperty(name));
    }

    @Test
    public void getPropertyNames() throws Exception {
        List<String> names = Arrays.asList("ONE", "TWO", "THREE");
        for (String name : names) {
            message.setStringProperty(name, name);
        }

        Enumeration<String> enu = message.getPropertyNames();

        int counter = 0;
        for (; enu.hasMoreElements(); counter++) {
            assertTrue(names.contains(enu.nextElement()));
        }
        assertEquals(names.size(), counter);

    }

    @Test
    public void setGetByteProperty() throws Exception {
        byte b = Byte.parseByte("1");
        message.setByteProperty("FOO", b);
        assertEquals(b, mockMessage.getByteProperty("FOO"));
        assertEquals(b, message.getByteProperty("FOO"));
    }
    
    
    public void setShortProperty(String arg0, short arg1) throws JMSException {
        message.setShortProperty(arg0, arg1);
    }

    public void setIntProperty(String arg0, int arg1) throws JMSException {
        message.setIntProperty(arg0, arg1);
    }

    public void setLongProperty(String arg0, long arg1) throws JMSException {
        message.setLongProperty(arg0, arg1);
    }

    public void setFloatProperty(String arg0, float arg1) throws JMSException {
        message.setFloatProperty(arg0, arg1);
    }

    public void setDoubleProperty(String arg0, double arg1) throws JMSException {
        message.setDoubleProperty(arg0, arg1);
    }

    public void setStringProperty(String arg0, String arg1) throws JMSException {
        message.setStringProperty(arg0, arg1);
    }

    public void setObjectProperty(String arg0, Object arg1) throws JMSException {
        message.setObjectProperty(arg0, arg1);
    }

    public void acknowledge() throws JMSException {
        message.acknowledge();
    }

    public void clearBody() throws JMSException {
        message.clearBody();
    }

    @Test
    public void getStringBody() throws Exception {
        MockTextMessage textMessage = new MockTextMessage("HELLOW");
        Jms2Message msg = new Jms2Message(textMessage);
        assertEquals("HELLOW", msg.getBody(String.class));
    }

    @Ignore
    @Test
    public void getBytesBody() throws Exception {
        MockBytesMessage bytesMessage = new MockBytesMessage();
        byte[] data = "HELLOW".getBytes();
        bytesMessage.writeUTF("HELLOW");
        bytesMessage.setReadOnly(true);
        Jms2BytesMessage msg = new Jms2BytesMessage(bytesMessage);

        byte[] result = msg.getBody(byte[].class);
        assertArrayEquals(data, result);
    }

    public boolean isBodyAssignableTo(Class c) throws JMSException {
        return message.isBodyAssignableTo(c);
    }


    
}
