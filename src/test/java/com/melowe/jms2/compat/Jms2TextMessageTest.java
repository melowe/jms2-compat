
package com.melowe.jms2.compat;

import com.melowe.jms2.compat.Jms2TextMessage;
import javax.jms.TextMessage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

public class Jms2TextMessageTest {
    
    TextMessage mockMessage;
    
    Jms2TextMessage textMessage;
    
    public Jms2TextMessageTest() {
    }

    @Before
    public void setUp() {
        mockMessage = mock(TextMessage.class);
        textMessage = new Jms2TextMessage(mockMessage);
        
    }
    
    @After
    public void tearDown() {
        textMessage = null;
        verifyNoMoreInteractions(mockMessage);
    }

    @Test
    public void testSetText() throws Exception {
        textMessage.setText("HELLOW");
        verify(mockMessage,times(1)).setText("HELLOW");
        
    }

    @Test
    public void testGetText() throws Exception {
        textMessage.getText();
        verify(mockMessage,times(1)).getText();
    }
    
}
