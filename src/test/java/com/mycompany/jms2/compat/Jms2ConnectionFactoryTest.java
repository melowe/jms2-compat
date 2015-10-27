package com.mycompany.jms2.compat;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.Session;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class Jms2ConnectionFactoryTest {

    ConnectionFactory mockConnectionFactory;

    Jms2ConnectionFactory jms2ConnectionFactory;

    public Jms2ConnectionFactoryTest() {
    }


    @Before
    public void setUp() {
        mockConnectionFactory = mock(ConnectionFactory.class);
        
        jms2ConnectionFactory = new Jms2ConnectionFactory(mockConnectionFactory);
    }

    @After
    public void tearDown() {
        jms2ConnectionFactory = null;
        mockConnectionFactory = null;

    }

    @Test
    public void testCreateConnection() throws Exception {
        Connection mockConnection = mock(Connection.class);
        when(mockConnectionFactory.createConnection()).thenReturn(mockConnection);
        
        Connection conn = jms2ConnectionFactory.createConnection();
        assertNotNull(conn);
        assertEquals(Jms2Connection.class, conn.getClass());

        verify(mockConnectionFactory, times(1)).createConnection();
        verifyNoMoreInteractions(mockConnectionFactory);
        verifyZeroInteractions(mockConnection);
    }

    @Test
    public void testCreateConnectionwithCredentials() throws Exception {
        final String username = "top";
        final String password = "secret";
        
        Connection mockConnection = mock(Connection.class);
 
        when(mockConnectionFactory.createConnection(username, password)).thenReturn(mockConnection);
        
        Connection conn = jms2ConnectionFactory.createConnection(username, password);
        assertNotNull(conn);

        assertEquals(Jms2Connection.class, conn.getClass());

        verify(mockConnectionFactory, times(1)).createConnection(username, password);
        verifyZeroInteractions(mockConnection);
        verifyNoMoreInteractions(mockConnectionFactory);

    }

    @Test
    public void testCreateContext() throws Exception {

        Connection mockConnection = mock(Connection.class);
 
        when(mockConnectionFactory.createConnection()).thenReturn(mockConnection);
        
        Connection conn = jms2ConnectionFactory.createConnection();
        assertNotNull(conn);

        assertEquals(Jms2Connection.class, conn.getClass());

        verify(mockConnectionFactory, times(1)).createConnection();
        verifyZeroInteractions(mockConnection);
        verifyNoMoreInteractions(mockConnectionFactory);

    }

    @Test
    public void testCreateContextWithCredentials() throws Exception {

        final String username = "top";
        final String password = "secret";
        
        Connection mockConnection = mock(Connection.class);
        Session mockSession = mock(Session.class);
        when(mockConnectionFactory.createConnection(username, password)).thenReturn(mockConnection);
        when(mockConnection.createSession(false, Session.AUTO_ACKNOWLEDGE)).thenReturn(mockSession);

        JMSContext context = jms2ConnectionFactory.createContext(username, password);
        assertNotNull(context);
        assertEquals(Jms2Context.class, context.getClass());

        verify(mockConnectionFactory, times(1)).createConnection(username, password);
        verify(mockConnection, times(1)).createSession(false, Session.AUTO_ACKNOWLEDGE);
        verifyNoMoreInteractions(mockConnectionFactory, mockConnection);
        verifyZeroInteractions(mockSession);
    }

    @Test
    public void testCreateContextCredentialsAndTransactedSession() throws Exception {
        final String username = "top";
        final String password = "secret";
        
        Connection mockConnection = mock(Connection.class);
        Session mockSession = mock(Session.class);
        when(mockConnectionFactory.createConnection(username, password)).thenReturn(mockConnection);
        when(mockConnection.createSession(true, Session.SESSION_TRANSACTED)).thenReturn(mockSession);
        
        
        JMSContext context = jms2ConnectionFactory.createContext(username, password,JMSContext.SESSION_TRANSACTED);
        assertNotNull(context);
        assertEquals(Jms2Context.class, context.getClass());

        verify(mockConnectionFactory, times(1)).createConnection(username, password);
        verify(mockConnection, times(1)).createSession(true, Session.SESSION_TRANSACTED);
        verifyNoMoreInteractions(mockConnectionFactory, mockConnection);
        verifyZeroInteractions(mockSession);
    }

    @Test
    public void testCreateContextClientAckSession() throws Exception {

        
        Connection mockConnection = mock(Connection.class);
        Session mockSession = mock(Session.class);
        when(mockConnectionFactory.createConnection()).thenReturn(mockConnection);
        when(mockConnection.createSession(false, Session.CLIENT_ACKNOWLEDGE)).thenReturn(mockSession); 
        
        JMSContext context = jms2ConnectionFactory.createContext(JMSContext.CLIENT_ACKNOWLEDGE);
        assertNotNull(context);
        assertEquals(Jms2Context.class, context.getClass());

        verify(mockConnectionFactory, times(1)).createConnection();
        verify(mockConnection, times(1)).createSession(false, Session.CLIENT_ACKNOWLEDGE);
        verifyNoMoreInteractions(mockConnectionFactory, mockConnection);
        verifyZeroInteractions(mockSession);
    }
    
     @Test
    public void testCreateTransactedSession() throws Exception {
        Connection mockConnection = mock(Connection.class);
        Session mockSession = mock(Session.class);
        when(mockConnectionFactory.createConnection()).thenReturn(mockConnection);
        when(mockConnection.createSession(true, Session.SESSION_TRANSACTED)).thenReturn(mockSession); 
        
        JMSContext context = jms2ConnectionFactory.createContext(JMSContext.SESSION_TRANSACTED);
        assertNotNull(context);
        assertEquals(Jms2Context.class, context.getClass());

        verify(mockConnectionFactory, times(1)).createConnection();
        verify(mockConnection, times(1)).createSession(true, Session.SESSION_TRANSACTED);
        verifyNoMoreInteractions(mockConnectionFactory, mockConnection);
        verifyZeroInteractions(mockSession);
    }

    @Test
    public void testCreateContextCredentialsAndClientAckSession() throws Exception {
        final String username = "top";
        final String password = "secret";
        
        Connection mockConnection = mock(Connection.class);
        Session mockSession = mock(Session.class);
        when(mockConnectionFactory.createConnection(username, password)).thenReturn(mockConnection);
        when(mockConnection.createSession(false, Session.CLIENT_ACKNOWLEDGE)).thenReturn(mockSession);
        
        
        JMSContext context = jms2ConnectionFactory.createContext(username, password,JMSContext.CLIENT_ACKNOWLEDGE);
        assertNotNull(context);
        assertEquals(Jms2Context.class, context.getClass());

        verify(mockConnectionFactory, times(1)).createConnection(username, password);
        verify(mockConnection, times(1)).createSession(false, Session.CLIENT_ACKNOWLEDGE);
        verifyNoMoreInteractions(mockConnectionFactory, mockConnection);
        verifyZeroInteractions(mockSession);
    }

    @Test
    public void createContext() throws Exception {

        Connection mockConnection = mock(Connection.class);
        Session mockSession = mock(Session.class);
        when(mockConnectionFactory.createConnection()).thenReturn(mockConnection);
        when(mockConnection.createSession(false, Session.AUTO_ACKNOWLEDGE)).thenReturn(mockSession);
        
        
        JMSContext context = jms2ConnectionFactory.createContext();
        assertNotNull(context);
        assertEquals(Jms2Context.class, context.getClass());

        verify(mockConnectionFactory, times(1)).createConnection();
        verify(mockConnection, times(1)).createSession(false, Session.AUTO_ACKNOWLEDGE);
        verifyNoMoreInteractions(mockConnectionFactory, mockConnection);
        verifyZeroInteractions(mockSession);
        
    }
    
}
