package com.mycompany.jms2.compat;

import java.io.Serializable;
import javax.jms.BytesMessage;
import javax.jms.Destination;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class Jms2SessionTest {

    Session mockSession;

    Jms2Session jms2Session;

    public Jms2SessionTest() {
    }

    @Before
    public void setUp() {
        mockSession = mock(Session.class);
        jms2Session = new Jms2Session(mockSession);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mockSession);
    }

    @Test
    public void testCreateBytesMessage() throws Exception {
        BytesMessage mockMessage = mock(BytesMessage.class);
        when(mockSession.createBytesMessage()).thenReturn(mockMessage);
        Jms2BytesMessage result = (Jms2BytesMessage) jms2Session.createBytesMessage();
        assertSame(mockMessage, result.getDelegate());

        verify(mockSession, times(1)).createBytesMessage();
        verifyZeroInteractions(mockMessage);
    }

    @Test
    public void testCreateMapMessage() throws Exception {
        MapMessage mockMessage = mock(MapMessage.class);
        when(mockSession.createMapMessage()).thenReturn(mockMessage);
        Jms2MapMessage result = (Jms2MapMessage) jms2Session.createMapMessage();
        assertSame(mockMessage, result.getDelegate());

        verify(mockSession, times(1)).createMapMessage();
        verifyZeroInteractions(mockMessage);
    }

    @Test
    public void testCreateMessage() throws Exception {
        Message mockMessage = mock(Message.class);
        when(mockSession.createMessage()).thenReturn(mockMessage);
        Jms2Message result = (Jms2Message) jms2Session.createMessage();
        assertSame(mockMessage, result.getDelegate());

        verify(mockSession, times(1)).createMessage();
        verifyZeroInteractions(mockMessage);
    }

    @Test
    public void testCreateObjectMessage() throws Exception {
        ObjectMessage mockMessage = mock(ObjectMessage.class);
        when(mockSession.createObjectMessage()).thenReturn(mockMessage);
        Jms2ObjectMessage result = (Jms2ObjectMessage) jms2Session.createObjectMessage();
        assertSame(mockMessage, result.getDelegate());

        verify(mockSession, times(1)).createObjectMessage();
        verifyZeroInteractions(mockMessage);
    }

    @Test
    public void testCreateObjectMessageWithPayload() throws Exception {
        Serializable payload = "HELLOW";
        ObjectMessage mockMessage = mock(ObjectMessage.class);
        when(mockSession.createObjectMessage()).thenReturn(mockMessage);

        Jms2ObjectMessage result = (Jms2ObjectMessage) jms2Session.createObjectMessage(payload);
        assertSame(mockMessage, result.getDelegate());

        verify(mockSession, times(1)).createObjectMessage();
        verify(mockMessage, times(1)).setObject(payload);
        verifyZeroInteractions(mockMessage);
    }

    @Test
    public void testCreateStreamMessage() throws Exception {
        StreamMessage mockMessage = mock(StreamMessage.class);
        when(mockSession.createStreamMessage()).thenReturn(mockMessage);
        Jms2StreamMessage result = (Jms2StreamMessage) jms2Session.createStreamMessage();
        assertSame(mockMessage, result.getDelegate());

        verify(mockSession, times(1)).createStreamMessage();
        verifyZeroInteractions(mockMessage);
    }

    @Test
    public void testCreateTextMessage() throws Exception {
        TextMessage mockMessage = mock(TextMessage.class);
        when(mockSession.createTextMessage()).thenReturn(mockMessage);

        Jms2TextMessage result = (Jms2TextMessage) jms2Session.createTextMessage();
        assertSame(mockMessage, result.getDelegate());
        verify(mockSession, times(1)).createTextMessage();
        verifyZeroInteractions(mockMessage);
    }

    @Test
    public void testCreateTextMessageWithPayload() throws Exception {

        String payload = "PAYLOAD";
        TextMessage mockMessage = mock(TextMessage.class);
        when(mockSession.createTextMessage()).thenReturn(mockMessage);

        Jms2TextMessage result = (Jms2TextMessage) jms2Session.createTextMessage(payload);
        assertSame(mockMessage, result.getDelegate());
        verify(mockSession, times(1)).createTextMessage();
        verify(mockMessage, times(1)).setText(payload);
        verifyNoMoreInteractions(mockMessage);

    }

    @Test
    public void testGetTransacted() throws Exception {
        when(mockSession.getTransacted()).thenReturn(Boolean.TRUE);
        assertTrue(jms2Session.getTransacted());
        verify(mockSession).getTransacted();
        verifyNoMoreInteractions(mockSession);
    }

    @Test
    public void testGetAcknowledgeMode() throws Exception {
        when(mockSession.getAcknowledgeMode()).thenReturn(Session.SESSION_TRANSACTED);
        assertEquals(Session.SESSION_TRANSACTED, jms2Session.getAcknowledgeMode());
        verify(mockSession).getAcknowledgeMode();
        verifyNoMoreInteractions(mockSession);
    }

    @Test
    public void testCommit() throws Exception {
        jms2Session.commit();
        verify(mockSession).commit();
        verifyNoMoreInteractions(mockSession);
    }

    @Test
    public void testRollback() throws Exception {
        jms2Session.rollback();
        verify(mockSession).rollback();
        verifyNoMoreInteractions(mockSession);
    }

    @Test
    public void testClose() throws Exception {
        jms2Session.close();
        verify(mockSession).close();
        verifyNoMoreInteractions(mockSession);
    }

    @Test
    public void testRecover() throws Exception {
        jms2Session.recover();
        verify(mockSession).recover();
        verifyNoMoreInteractions(mockSession);
    }

    @Test
    public void testGetMessageListener() throws Exception {
        jms2Session.getMessageListener();
        verify(mockSession).getMessageListener();
        verifyNoMoreInteractions(mockSession);
    }

    @Test
    public void testSetMessageListener() throws Exception {
    }

    @Test
    public void testRun() throws Exception {
        jms2Session.run();
        verify(mockSession).run();
        verifyNoMoreInteractions(mockSession);
    }

    @Test
    public void testCreateProducer() throws Exception {
        Destination mockDestination = mock(Destination.class);
        jms2Session.createProducer(mockDestination);
        verify(mockSession).createProducer(mockDestination);
        verifyNoMoreInteractions(mockSession);
    }

    @Test
    public void testCreateConsumer_Destination() throws Exception {
    }

    @Test
    public void testCreateConsumer_Destination_String() throws Exception {
    }

    @Test
    public void testCreateConsumer_3args() throws Exception {
    }

    @Test
    public void testCreateSharedConsumer_Topic_String() throws Exception {
    }

    @Test
    public void testCreateSharedConsumer_3args() throws Exception {
    }

    @Test
    public void testCreateQueue() throws Exception {
    }

    @Test
    public void testCreateTopic() throws Exception {
    }

    @Test
    public void testCreateDurableSubscriber_Topic_String() throws Exception {
    }

    @Test
    public void testCreateDurableSubscriber_4args() throws Exception {
    }

    @Test
    public void testCreateDurableConsumer_Topic_String() throws Exception {
    }

    @Test
    public void testCreateDurableConsumer_4args() throws Exception {
    }

    @Test
    public void testCreateSharedDurableConsumer_Topic_String() throws Exception {
    }

    @Test
    public void testCreateSharedDurableConsumer_3args() throws Exception {
    }

    @Test
    public void testCreateBrowser_Queue() throws Exception {
    }

    @Test
    public void testCreateBrowser_Queue_String() throws Exception {
    }

    @Test
    public void testCreateTemporaryQueue() throws Exception {
        jms2Session.createTemporaryQueue();
        verify(mockSession).createTemporaryQueue();
    }

    @Test
    public void testCreateTemporaryTopic() throws Exception {
        jms2Session.createTemporaryTopic();
        verify(mockSession).createTemporaryTopic();
    }

    @Test
    public void testUnsubscribe() throws Exception {
        jms2Session.unsubscribe("MYSUB");
        verify(mockSession).unsubscribe("MYSUB");
        
    }

}
