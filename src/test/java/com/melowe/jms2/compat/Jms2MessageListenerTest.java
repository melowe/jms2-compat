
package com.melowe.jms2.compat;

import com.mockrunner.mock.jms.MockBytesMessage;
import com.mockrunner.mock.jms.MockMapMessage;
import com.mockrunner.mock.jms.MockMessage;
import com.mockrunner.mock.jms.MockObjectMessage;
import com.mockrunner.mock.jms.MockStreamMessage;
import com.mockrunner.mock.jms.MockTextMessage;
import javax.jms.MessageListener;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;


public class Jms2MessageListenerTest {
    
    MessageListener mockListener;
    
    Jms2MessageListener listener;
    
    
    
    public Jms2MessageListenerTest() {
    }
    
    
    @Before
    public void setUp() {
        mockListener = mock(MessageListener.class);
        listener = new Jms2MessageListener(mockListener);
    }
    
    @After
    public void tearDown() {
       verifyNoMoreInteractions(mockListener);
    }

    @Test
    public void testOnTextMessage() {
        listener.onMessage(new MockTextMessage("HELLOW"));
        verify(mockListener).onMessage(any(Jms2Message.class));
    }
    
    @Test
    public void testOnObjectMessage() {
        listener.onMessage(new MockObjectMessage("HELLOW"));
        verify(mockListener).onMessage(any(Jms2Message.class));
    }
    
    @Test
    public void testOnMapMessage() {
        MockMapMessage mapMessage = new MockMapMessage();
       
        listener.onMessage(mapMessage);
        verify(mockListener).onMessage(any(Jms2Message.class));
    }
 
    @Test
    public void testOnBytesMessage() {
        MockBytesMessage m = new MockBytesMessage();
        listener.onMessage(m);
        verify(mockListener).onMessage(any(Jms2Message.class));
    }
 
    @Test
    public void testOnStreamMessage() {
        MockStreamMessage m = new MockStreamMessage();
        listener.onMessage(m);
        verify(mockListener).onMessage(any(Jms2Message.class));
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void testOnJms2Message() throws Exception {
        listener.onMessage(new Jms2Message(new MockMessage()));
    
    }
    
}
