package com.melowe.jms2.compat.mockunner;

import com.melowe.jms2.compat.*;
import com.mockrunner.mock.jms.JMSMockObjectFactory;
import com.mockrunner.mock.jms.MockConnectionFactory;
import javax.jms.Connection;
import javax.jms.JMSContext;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Before;
import org.junit.Test;

public class Jms2ConnectionFactoryTest {

    MockConnectionFactory mockConnectionFactory;

    Jms2ConnectionFactory jms2ConnectionFactory;

    JMSMockObjectFactory mockObjectFactory = new JMSMockObjectFactory();

    public Jms2ConnectionFactoryTest() {
    }

    @Before
    public void setUp() {
        mockConnectionFactory = mockObjectFactory.createMockConnectionFactory();

        jms2ConnectionFactory = new Jms2ConnectionFactory(mockConnectionFactory);
    }

    @After
    public void tearDown() {
        jms2ConnectionFactory = null;
        mockConnectionFactory = null;

    }

    @Test
    public void testCreateConnection() throws Exception {
        Connection conn = jms2ConnectionFactory.createConnection();
        assertNotNull(conn);
        assertEquals(Jms2Connection.class, conn.getClass());
    }

    @Test
    public void testCreateConnectionwithCredentials() throws Exception {
        final String username = "top";
        final String password = "secret";


        Connection conn = jms2ConnectionFactory.createConnection(username, password);
        assertNotNull(conn);
        assertEquals(Jms2Connection.class, conn.getClass());


    }

    @Test
    public void testCreateContext() throws Exception {

        JMSContext context = jms2ConnectionFactory.createContext();
        assertNotNull(context);


    }

    @Test
    public void testCreateContextWithCredentials() throws Exception {

        final String username = "top";
        final String password = "secret";

        JMSContext context = jms2ConnectionFactory.createContext(username,password);
        assertNotNull(context);
    }

    @Test
    public void testCreateContextCredentialsAndTransactedSession() throws Exception {
        final String username = "top";
        final String password = "secret";

        JMSContext context = jms2ConnectionFactory.createContext(username, password, JMSContext.SESSION_TRANSACTED);
        assertNotNull(context);

    }

    @Test
    public void testCreateContextClientAckSession() throws Exception {


        JMSContext context = jms2ConnectionFactory.createContext(JMSContext.CLIENT_ACKNOWLEDGE);
        assertNotNull(context);
        assertEquals(Jms2Context.class, context.getClass());

    }

    @Test
    public void testCreateTransactedSession() throws Exception {

        JMSContext context = jms2ConnectionFactory.createContext(JMSContext.SESSION_TRANSACTED);
        assertNotNull(context);
        assertEquals(Jms2Context.class, context.getClass());

    }

    @Test
    public void testCreateContextCredentialsAndClientAckSession() throws Exception {
        final String username = "top";
        final String password = "secret";

        JMSContext context = jms2ConnectionFactory.createContext(username, password, JMSContext.CLIENT_ACKNOWLEDGE);
        assertNotNull(context);
        assertEquals(Jms2Context.class, context.getClass());

    }

    @Test
    public void createContext() throws Exception {


        JMSContext context = jms2ConnectionFactory.createContext();
        assertNotNull(context);
        assertEquals(Jms2Context.class, context.getClass());


    }

}
