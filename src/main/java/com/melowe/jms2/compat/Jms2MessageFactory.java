package com.melowe.jms2.compat;

import static com.melowe.jms2.compat.Jms2Util.execute;
import java.io.Serializable;
import java.util.Map;
import java.util.Objects;
import javax.jms.BytesMessage;
import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.StreamMessage;
import javax.jms.TextMessage;

public class Jms2MessageFactory {

    static Jms2MapMessage createMapMessage(final Session session, final Map<String, Object> map) {
        return execute(new Callback<Jms2MapMessage>() {

            @Override
            public Jms2MapMessage execute() throws JMSException {
                MapMessage m = session.createMapMessage();
                for (Map.Entry<String, Object> entry : map.entrySet()) {
                    m.setObject(entry.getKey(), entry.getValue());
                }
                return new Jms2MapMessage(m);
            }
        });

    }

    private Jms2MessageFactory() {
    }

    static Jms2BytesMessage createBytesMessage(final Session session) {
        return createBytesMessage(session, null);
    }

    static Jms2BytesMessage createBytesMessage(final Session session, final byte[] data) {
        return Jms2Util.execute(new Callback<Jms2BytesMessage>() {

            @Override
            public Jms2BytesMessage execute() throws JMSException {
                BytesMessage message = session.createBytesMessage();
                if (Objects.nonNull(data) && data.length > 0) {
                    message.writeBytes(data);
                }

                return new Jms2BytesMessage(message);
            }
        });
    }

    static Jms2TextMessage createTextMessage(Session session) {
        return createTextMessage(session, null);
    }

    static Jms2TextMessage createTextMessage(final Session session, final String data) {
        return Jms2Util.execute(new Callback<Jms2TextMessage>() {

            @Override
            public Jms2TextMessage execute() throws JMSException {
                TextMessage message = session.createTextMessage();
                if (Objects.nonNull(data)) {
                    message.setText(data);
                }
                return new Jms2TextMessage(message);
            }
        });
    }

    static Jms2MapMessage createMapMessage(final Session session) {
        return Jms2Util.execute(new Callback<Jms2MapMessage>() {

            @Override
            public Jms2MapMessage execute() throws JMSException {
                return new Jms2MapMessage(session.createMapMessage());
            }

        });
    }

    static Jms2Message createMessage(final Session session) {
        return Jms2Util.execute(new Callback<Jms2Message>() {

            @Override
            public Jms2Message execute() throws JMSException {
                return new Jms2Message(session.createMessage());
            }

        });
    }

    static Jms2ObjectMessage createObjectMessage(final Session session) {
        return createObjectMessage(session, null);
    }

    static Jms2ObjectMessage createObjectMessage(final Session session, final Serializable object) {

        return Jms2Util.execute(new Callback<Jms2ObjectMessage>() {

            @Override
            public Jms2ObjectMessage execute() throws JMSException {
                ObjectMessage message = session.createObjectMessage();
                if (Objects.nonNull(object)) {
                    message.setObject(object);
                }

                return new Jms2ObjectMessage(message);
            }

        });
    }

    static Jms2StreamMessage createStreamMessage(final Session session) {
        return Jms2Util.execute(new Callback<Jms2StreamMessage>() {

            @Override
            public Jms2StreamMessage execute() throws JMSException {
                return new Jms2StreamMessage(session.createStreamMessage());
            }

        });
    }

    static Jms2Message convert(Message message) {
        Objects.requireNonNull(message);

        if (Jms2Message.class.isInstance(message)) {
            throw new IllegalArgumentException("Attempting to convert Jms2Message implemention. ");
        }

        if (TextMessage.class.isInstance(message)) {
            return new Jms2TextMessage(TextMessage.class.cast(message));
        } else if (StreamMessage.class.isInstance(message)) {
            return new Jms2StreamMessage(StreamMessage.class.cast(message));
        } else if (BytesMessage.class.isInstance(message)) {
            return new Jms2BytesMessage(BytesMessage.class.cast(message));
        } else if (ObjectMessage.class.isInstance(message)) {
            return new Jms2ObjectMessage(ObjectMessage.class.cast(message));
        } else if (MapMessage.class.isInstance(message)) {
            return new Jms2MapMessage(MapMessage.class.cast(message));
        } else {
            return new Jms2Message(message);
        }
    }



}
