package com.melowe.jms2.compat;

import java.util.Arrays;
import java.util.Collections;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageFormatException;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

public final class Jms2MessageUtil {

    private static void acknowledge(final Message m) {
         Jms2Util.execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                m.acknowledge();
                return null;
            }
        });
    }
    
    static void acknowledge(Session session, List<Message> acknowledgeMessages) {
        if(!Jms2Util.isClientAcknowledge(session)) {
            return;
        }
        for(Message  m : acknowledgeMessages) {
            acknowledge(m);
        }
        
    }

    private Jms2MessageUtil() {
    }

    static boolean isTextMessage(Message message, Class type) {
        return TextMessage.class.isInstance(message) && String.class.isAssignableFrom(type);
    }

    static boolean isBytesMessage(Message message, Class type) {
        return BytesMessage.class.isInstance(message) && byte[].class.isAssignableFrom(type);
    }

    static boolean isObjectMessage(Message message, Class type) {
        return ObjectMessage.class.isInstance(message) && java.io.Serializable.class.isAssignableFrom(type);
    }

    static boolean isMapMessage(Message message, Class type) {
        return MapMessage.class.isInstance(message) && Map.class.isAssignableFrom(type);
    }

    static boolean isPlainMessage(Message message) {
        List<Class<? extends Message>> types
                = Arrays.asList(TextMessage.class, BytesMessage.class, MapMessage.class, ObjectMessage.class, StreamMessage.class);
        for (Class<? extends Message> c : types) {
            if (c.isInstance(message)) {
                return false;
            }
        }
        return true;
    }

    static boolean isBodyAssignableTo(Message message, Class type) {
        if (StreamMessage.class.isInstance(message)) {
            return false;
        }
        return isPlainMessage(message)
                || isTextMessage(message, type)
                || isBytesMessage(message, type)
                || isObjectMessage(message, type)
                || isMapMessage(message, type);
    }

    static <T> T getBody(final Message message, final Class<T> type) throws JMSException {
        if (StreamMessage.class.isInstance(message)) {
            throw new MessageFormatException("Body cannot be extracted from StreamMessage see JMS 2.0 Spec");
        }

        if (isTextMessage(message, type)) {
            return (T) TextMessage.class.cast(message).getText();
        }

        if (isObjectMessage(message, type)) {
            return (T) ObjectMessage.class.cast(message).getObject();
        }

        if (isBytesMessage(message, type)) {
            BytesMessage bmsg = BytesMessage.class.cast(message);
            int length = (int) bmsg.getBodyLength();
            byte[] data = new byte[length];
            bmsg.readBytes(data);
            return (T) data;
        }

        if (isMapMessage(message, type)) {
            MapMessage mapMessage = MapMessage.class.cast(message);
            Enumeration<String> enu = mapMessage.getMapNames();
            Map<String, Object> map = new HashMap<>();
            while (enu.hasMoreElements()) {
                String name = enu.nextElement();
                Object value = mapMessage.getObject(name);
                map.put(name, value);
            }
            return (T) Collections.unmodifiableMap(map);

        }

        if (Message.class.isInstance(message)) {
            return null;
        }

        throw new MessageFormatException("Uable to resolve message type or body");

    }
    
    
    
}
