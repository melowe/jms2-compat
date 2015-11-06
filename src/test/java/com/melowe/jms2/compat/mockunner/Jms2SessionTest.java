package com.melowe.jms2.compat.mockunner;

import com.melowe.jms2.compat.Jms2BytesMessage;
import com.melowe.jms2.compat.Jms2MapMessage;
import com.melowe.jms2.compat.Jms2Message;
import com.melowe.jms2.compat.Jms2MessageListener;
import com.melowe.jms2.compat.Jms2ObjectMessage;
import com.melowe.jms2.compat.Jms2Session;
import com.melowe.jms2.compat.Jms2StreamMessage;
import com.melowe.jms2.compat.Jms2TestObjectFactory;
import com.melowe.jms2.compat.Jms2TextMessage;
import com.melowe.jms2.compat.TopicSubscriberAdaptor;
import com.mockrunner.mock.jms.JMSMockObjectFactory;
import com.mockrunner.mock.jms.MockBytesMessage;
import com.mockrunner.mock.jms.MockMapMessage;
import com.mockrunner.mock.jms.MockMessage;
import com.mockrunner.mock.jms.MockObjectMessage;
import com.mockrunner.mock.jms.MockSession;
import com.mockrunner.mock.jms.MockStreamMessage;
import com.mockrunner.mock.jms.MockTextMessage;
import com.mockrunner.mock.jms.MockTopic;
import java.io.Serializable;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
import javax.jms.Topic;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class Jms2SessionTest {

    private MockSession mockSession;

    private Jms2Session session;

    public Jms2SessionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        mockSession = (MockSession) new JMSMockObjectFactory()
                .getMockConnectionFactory()
                .createConnection()
                .createSession(false, Session.AUTO_ACKNOWLEDGE);
        session = Jms2TestObjectFactory.create(mockSession);
    }

    @After
    public void tearDown() {
        mockSession = null;
    }

    @Test
    public void createBytesMessage() throws Exception {
        BytesMessage result = session.createBytesMessage();
        assertNotNull(result);
        assertEquals(Jms2BytesMessage.class, result.getClass());
        assertEquals(MockBytesMessage.class, Jms2BytesMessage.class.cast(result).getDelegate().getClass());
    }

    @Test
    public void createMapMessage() throws Exception {
        MapMessage result = session.createMapMessage();
        assertNotNull(result);
        assertEquals(Jms2MapMessage.class, result.getClass());
        assertEquals(MockMapMessage.class, Jms2MapMessage.class.cast(result).getDelegate().getClass());
    }

    @Test
    public void createMessage() throws Exception {
        Message result = session.createMessage();
        assertNotNull(result);
        assertEquals(Jms2Message.class, result.getClass());
        assertEquals(MockMessage.class, Jms2Message.class.cast(result).getDelegate().getClass());
    }

    @Test
    public void createObjectMessage() throws Exception {
        ObjectMessage result = session.createObjectMessage();
        assertNotNull(result);
        assertEquals(Jms2ObjectMessage.class, result.getClass());
        assertEquals(MockObjectMessage.class, Jms2ObjectMessage.class.cast(result).getDelegate().getClass());
    }

    static class Payload implements Serializable {
    };

    @Test
    public void createObjectMessageWithPayload() throws Exception {
        Payload payload = new Payload();
        ObjectMessage result = session.createObjectMessage(payload);
        assertNotNull(result);
        assertEquals(Jms2ObjectMessage.class, result.getClass());
        assertEquals(MockObjectMessage.class, Jms2ObjectMessage.class.cast(result).getDelegate().getClass());
        MockObjectMessage ob = ((MockObjectMessage) (Jms2ObjectMessage.class.cast(result).getDelegate()));
        assertSame(payload, ob.getObject());
    }

    @Test
    public void createStreamMessage() throws Exception {
        StreamMessage result = session.createStreamMessage();
        assertNotNull(result);
        assertEquals(Jms2StreamMessage.class, result.getClass());
        assertEquals(MockStreamMessage.class, Jms2StreamMessage.class.cast(result).getDelegate().getClass());
    }

    @Test
    public void createTextMessage() throws Exception {
        TextMessage result = session.createTextMessage();
        assertNotNull(result);
        assertEquals(Jms2TextMessage.class, result.getClass());
        assertEquals(MockTextMessage.class, Jms2TextMessage.class.cast(result).getDelegate().getClass());
    }

    @Test
    public void createTextMessageWithBody() throws Exception {
        TextMessage result = session.createTextMessage("BODY");
        assertNotNull(result);
        assertEquals(Jms2TextMessage.class, result.getClass());
        assertEquals(MockTextMessage.class, Jms2TextMessage.class.cast(result).getDelegate().getClass());
        MockTextMessage m = (MockTextMessage) Jms2TextMessage.class.cast(result).getDelegate();
        assertEquals("BODY", m.getText());
    }

    @Test
    public void setGetMessageListener() throws Exception {
        MessageListener l = new MessageListener() {

            @Override
            public void onMessage(Message message) {
                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        };
        session.setMessageListener(l);

        assertEquals(Jms2MessageListener.class, session.getMessageListener().getClass());

    }

    @Test
    public void createSharedConsumer() throws JMSException {
        Topic topic = new MockTopic("MOCKT");

        MessageConsumer messageConsumer = session.createSharedConsumer(topic, "FOO");
        assertNotNull(messageConsumer);
        assertEquals(TopicSubscriberAdaptor.class, messageConsumer.getClass());
    }

    @Test
    public void createSharedConsumerWithSelector() throws JMSException {
        Topic topic = new MockTopic("MOCKT");

        MessageConsumer messageConsumer = session.createSharedConsumer(topic, "FOO", "SELECTOR");
        assertNotNull(messageConsumer);
        assertEquals(TopicSubscriberAdaptor.class, messageConsumer.getClass());
        assertEquals("SELECTOR", messageConsumer.getMessageSelector());
    }

    @Test
    public void createDurableConsumer() throws Exception {
        Topic topic = new MockTopic("MOCKT");

        MessageConsumer messageConsumer = session.createDurableConsumer(topic, "FOO");
        assertNotNull(messageConsumer);
        assertEquals(TopicSubscriberAdaptor.class, messageConsumer.getClass());
    }

    @Test
    public void createDurableConsumerWithSelector() throws Exception {
        Topic topic = new MockTopic("MOCKT");
        
        MessageConsumer messageConsumer = session.createDurableConsumer(topic, "FOO", "SELECTOR", true);
        assertNotNull(messageConsumer);
        assertEquals(TopicSubscriberAdaptor.class, messageConsumer.getClass());
        assertEquals("SELECTOR", messageConsumer.getMessageSelector());
        
        
    }
    
    
    @Test
    public void createSharedDurableConsumer() throws JMSException {
        Topic topic = new MockTopic("MOCKT");

        MessageConsumer messageConsumer = session.createSharedDurableConsumer(topic, "FOO");
        assertNotNull(messageConsumer);
        assertEquals(TopicSubscriberAdaptor.class, messageConsumer.getClass());
    }
    
    
    @Test
    public void createSharedDurableConsumerWithSelector() throws JMSException {
        Topic topic = new MockTopic("MOCKT");

        MessageConsumer messageConsumer = session.createSharedDurableConsumer(topic, "FOO", "SELECTOR");
        assertNotNull(messageConsumer);
        assertEquals(TopicSubscriberAdaptor.class, messageConsumer.getClass());
        assertEquals("SELECTOR", messageConsumer.getMessageSelector());
    }


}
