package com.melowe.jms2.compat;

import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageFormatRuntimeException;
import javax.jms.MessageListener;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;
import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;
import static org.mockito.Mockito.when;

public class Jms2ConsumerTest {

    MessageConsumer mockMessageConsumer;

    Jms2Consumer consumer;

    public Jms2ConsumerTest() {
    }

    @Before
    public void setUp() {
        mockMessageConsumer = mock(MessageConsumer.class);
        consumer = new Jms2Consumer(mockMessageConsumer);
    }

    @After
    public void tearDown() {
        verifyNoMoreInteractions(mockMessageConsumer);
        mockMessageConsumer = null;
        consumer = null;
    }

    @Test
    public void testGetMessageSelector() throws Exception {
        consumer.getMessageSelector();

        verify(mockMessageConsumer, times(1)).getMessageSelector();

    }

    @Test
    public void testGetMessageListener() throws Exception {
        consumer.getMessageListener();

        verify(mockMessageConsumer, times(1)).getMessageListener();
    }

    @Test
    public void testSetMessageListener() throws Exception {
        final MessageListener mockMessageListener = mock(MessageListener.class);

        consumer.setMessageListener(mockMessageListener);
        verify(mockMessageConsumer, times(1)).setMessageListener(any(Jms2MessageListener.class));

    }

    @Test
    public void testReceive() throws Exception {
        Message mockMessage = mock(Message.class);
        when(mockMessageConsumer.receive()).thenReturn(mockMessage);
        Jms2Message result = (Jms2Message) consumer.receive();
        assertSame(mockMessage, result.getDelegate());
        verify(mockMessageConsumer, times(1)).receive();
        verifyZeroInteractions(mockMessage);

    }

    @Test
    public void testReceiveWithTimeout() throws Exception {
        Message mockMessage = mock(Message.class);
        when(mockMessageConsumer.receive(999L)).thenReturn(mockMessage);
        Jms2Message result = (Jms2Message) consumer.receive(999L);
        assertSame(mockMessage, result.getDelegate());
        verify(mockMessageConsumer, times(1)).receive(999L);
        verifyZeroInteractions(mockMessage);
    }

    @Test
    public void testReceiveNoWait() throws Exception {
        Message mockMessage = mock(Message.class);
        when(mockMessageConsumer.receiveNoWait()).thenReturn(mockMessage);
        Jms2Message result = (Jms2Message) consumer.receiveNoWait();
        assertSame(mockMessage, result.getDelegate());
        verify(mockMessageConsumer, times(1)).receiveNoWait();
        verifyZeroInteractions(mockMessage);
    }

    @Test
    public void testClose() throws Exception {
        consumer.close();
        verify(mockMessageConsumer, times(1)).close();
    }

    @Test
    public void testReceiveStringBody() throws Exception {
        TextMessage mockMessage = mock(TextMessage.class);
        when(mockMessage.getText()).thenReturn("RESULT");
        when(mockMessageConsumer.receive()).thenReturn(mockMessage);
        String result = consumer.receiveBody(String.class);
        assertEquals("RESULT", result);

        verify(mockMessageConsumer, times(1)).receive();
        verify(mockMessage, times(1)).getText();
    }

    @Test
    public void testReceiveStringBodyWithTimout() throws Exception {
        TextMessage mockMessage = mock(TextMessage.class);
        when(mockMessage.getText()).thenReturn("RESULT");
        when(mockMessageConsumer.receive(99L)).thenReturn(mockMessage);
        String result = consumer.receiveBody(String.class, 99L);
        assertEquals("RESULT", result);

        verify(mockMessageConsumer, times(1)).receive(99L);
        verify(mockMessage, times(1)).getText();
    }

    @Test
    public void testReceiveStringBodyNoWait() throws Exception {
        TextMessage mockMessage = mock(TextMessage.class);
        when(mockMessage.getText()).thenReturn("RESULT");
        when(mockMessageConsumer.receiveNoWait()).thenReturn(mockMessage);
        String result = consumer.receiveBodyNoWait(String.class);
        assertEquals("RESULT", result);

        verify(mockMessageConsumer, times(1)).receiveNoWait();
        verify(mockMessage, times(1)).getText();

    }

    @Test
    public void testReceiveNoWaitBodyFromStreamMessage() throws Exception {
        StreamMessage mockMessage = mock(StreamMessage.class);
        when(mockMessageConsumer.receiveNoWait()).thenReturn(mockMessage);
        try {
            consumer.receiveBodyNoWait(String.class);
            fail();
        } catch(MessageFormatRuntimeException ex) {
            assertNotNull(ex);
        } finally {
            verify(mockMessageConsumer).receiveNoWait();
        }
    }

}
