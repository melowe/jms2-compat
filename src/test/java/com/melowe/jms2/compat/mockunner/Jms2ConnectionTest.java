
package com.melowe.jms2.compat.mockunner;

import com.melowe.jms2.compat.Jms2Connection;
import com.melowe.jms2.compat.Jms2Session;
import com.melowe.jms2.compat.Jms2TestObjectFactory;
import com.mockrunner.mock.jms.JMSMockObjectFactory;
import com.mockrunner.mock.jms.MockConnection;
import com.mockrunner.mock.jms.MockServerSessionPool;
import com.mockrunner.mock.jms.MockSession;
import com.mockrunner.mock.jms.MockTopic;
import javax.jms.ConnectionConsumer;
import javax.jms.ServerSessionPool;
import javax.jms.Session;
import javax.jms.Topic;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;


public class Jms2ConnectionTest {
    
    Jms2Connection connection;
    
    MockConnection mockConnection;
    
    JMSMockObjectFactory mockObjectFactory = new JMSMockObjectFactory();
    
    public Jms2ConnectionTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() throws Exception  {

        mockConnection = (MockConnection) mockObjectFactory.getMockConnectionFactory().createConnection();
        
        connection = Jms2TestObjectFactory.create(mockConnection);
    }
    
    @After
    public void tearDown() {
        mockConnection = null;
        connection = null;
    }


    @Test
    public void createSessionWithSessionMode() throws Exception {
         connection.createSession(Session.DUPS_OK_ACKNOWLEDGE);
         MockSession session = mockConnection.getSession(0);
         assertEquals(Session.DUPS_OK_ACKNOWLEDGE, session.getAcknowledgeMode());
         assertFalse(session.getTransacted());
         
    }

    @Test
    public void createSessionWithTransactedSessionMode() throws Exception {
         Jms2Session jms2session = (Jms2Session) connection.createSession(Session.SESSION_TRANSACTED);
         MockSession session = mockConnection.getSession(0);
         assertEquals(Session.SESSION_TRANSACTED, session.getAcknowledgeMode());
         assertTrue(session.getTransacted());
         
    }

    @Test
    public void createSession() throws Exception {
         Jms2Session jms2session = (Jms2Session) connection.createSession();
         MockSession session = mockConnection.getSession(0);
         assertEquals(Session.AUTO_ACKNOWLEDGE, session.getAcknowledgeMode());
         assertFalse(session.getTransacted());
         
    }

    @Test
    public void createSharedConnectionConsumer() throws Exception {
       Topic topic = new MockTopic("MOCK_T");
        
       ServerSessionPool sessionPool = new MockServerSessionPool(mockConnection);
       ConnectionConsumer result = connection.createSharedConnectionConsumer(topic, "MYSUB", "SELECTOR", sessionPool, 100);
       assertNotNull(result);
    }

    @Test
    public void createSharedDurableConnectionConsumer() throws Exception {
       MockTopic topic = new MockTopic("MOCK_T");
        
       ServerSessionPool sessionPool = new MockServerSessionPool(mockConnection);
       ConnectionConsumer result = connection.createSharedDurableConnectionConsumer(topic, "MYSUB", "SELECTOR", sessionPool, 100);
       assertNotNull(result);
       
        assertSame(sessionPool, result.getServerSessionPool());

       
    }
    

    
}
