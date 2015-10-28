package com.melowe.jms2.compat;

import javax.jms.Connection;
import javax.jms.ConnectionMetaData;
import javax.jms.ExceptionListener;
import javax.jms.Session;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class Jms2ConnectionTest {

    Connection mockConnection;

    Jms2Connection connection;

    Session mockSession;

    public Jms2ConnectionTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() throws Exception {
        mockConnection = mock(Connection.class);
        mockSession = mock(Session.class);
        when(mockConnection.createSession(anyBoolean(), anyInt())).thenReturn(mockSession);

        connection = new Jms2Connection(mockConnection);
    }

    @After
    public void tearDown() {
        verifyZeroInteractions(mockSession);
        verifyNoMoreInteractions(mockConnection);
    }

    @Test
    public void testCreateSessionTransactionAndSessionMode() throws Exception {
        Session session = connection.createSession(true, Session.AUTO_ACKNOWLEDGE);
        assertEquals(Jms2Session.class, session.getClass());
        verify(mockConnection).createSession(true, Session.AUTO_ACKNOWLEDGE);
    }

    @Test
    public void testCreateSessionWithSessionMode() throws Exception {
        Session session = connection.createSession(Session.CLIENT_ACKNOWLEDGE);
        assertEquals(Jms2Session.class, session.getClass());
        verify(mockConnection).createSession(false, Session.CLIENT_ACKNOWLEDGE);
    }

    @Test
    public void testCreateSessionNoArgs() throws Exception {
        Session session = connection.createSession();
        assertEquals(Jms2Session.class, session.getClass());
        verify(mockConnection).createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    @Test
    public void testGetClientID() throws Exception {
        when(mockConnection.getClientID()).thenReturn("CLIENTID");
        String result = connection.getClientID();
        assertEquals("CLIENTID", result);
        verify(mockConnection).getClientID();

    }

    @Test
    public void testSetClientID() throws Exception {
        connection.setClientID("CLIENTID");
        verify(mockConnection).setClientID("CLIENTID");

    }

    @Test
    public void testGetMetaData() throws Exception {
        ConnectionMetaData metaData = mock(ConnectionMetaData.class);
        when(mockConnection.getMetaData()).thenReturn(metaData);

        ConnectionMetaData result = connection.getMetaData();

        assertSame(result, metaData);

        verify(mockConnection).getMetaData();

    }

    @Test
    public void testGetExceptionListener() throws Exception {
        ExceptionListener listener = mock(ExceptionListener.class);
        when(mockConnection.getExceptionListener()).thenReturn(listener);
        ExceptionListener result = connection.getExceptionListener();
        assertSame(listener, result);
        verify(mockConnection).getExceptionListener();

    }

    @Test
    public void testSetExceptionListener() throws Exception {
        ExceptionListener listener = mock(ExceptionListener.class);
        connection.setExceptionListener(listener);
        verify(mockConnection).setExceptionListener(listener);
    }

    @Test
    public void testStart() throws Exception {
        connection.start();
        verify(mockConnection).start();
    }

    @Test
    public void testStop() throws Exception {
        connection.stop();
        verify(mockConnection).stop();
    }

    @Test
    public void testClose() throws Exception {
        connection.close();
        verify(mockConnection).close();
    }

    @Test
    public void testCreateConnectionConsumer() throws Exception {
    }

    @Test
    public void testCreateSharedConnectionConsumer() throws Exception {
    }

    @Test
    public void testCreateDurableConnectionConsumer() throws Exception {
    }

    @Test
    public void testCreateSharedDurableConnectionConsumer() throws Exception {
    }

}
