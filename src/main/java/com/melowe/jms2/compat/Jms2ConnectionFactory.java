package com.melowe.jms2.compat;

import static com.melowe.jms2.compat.Jms2Util.execute;
import java.util.Objects;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.JMSContext;
import javax.jms.JMSException;
import javax.jms.Session;

public class Jms2ConnectionFactory implements ConnectionFactory {

    private final ConnectionFactory connectionFactory;

    public Jms2ConnectionFactory(ConnectionFactory connectionFactory) {
        this.connectionFactory = Objects.requireNonNull(connectionFactory);
    }

    @Override
    public Connection createConnection() throws JMSException {
        return new Jms2Connection(connectionFactory.createConnection());
    }

    @Override
    public Connection createConnection(String arg0, String arg1) throws JMSException {
        return new Jms2Connection(connectionFactory.createConnection(arg0, arg1));
    }

    @Override
    public JMSContext createContext() {
        return execute(new Callback<JMSContext>() {

            @Override
            public JMSContext execute() throws JMSException {
                Connection connection = createConnection();
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                return new Jms2Context(connection, session);
            }
        });

    }

    @Override
    public JMSContext createContext(final String arg0, final String arg1) {
        return execute(new Callback<Jms2Context>() {

            @Override
            public Jms2Context execute() throws JMSException {
                Connection connection = createConnection(arg0, arg1);
                Session session = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
                return new Jms2Context(connection, session);
            }
        });
    }

    @Override
    public JMSContext createContext(final String arg0, final String arg1, final int arg2) {
        return execute(new Callback<Jms2Context>() {

            @Override
            public Jms2Context execute() throws JMSException {
                Connection connection = createConnection(arg0, arg1);
                Session session = connection.createSession(arg2 == Session.SESSION_TRANSACTED, arg2);
                return new Jms2Context(connection, session);
            }
        });

    }

    @Override
    public JMSContext createContext(final int sessionMode) {

        return execute(new Callback<Jms2Context>() {

            @Override
            public Jms2Context execute() throws JMSException {
                Connection connection = createConnection();
                Session session = connection.createSession(Session.SESSION_TRANSACTED == sessionMode, sessionMode);
                return new Jms2Context(connection, session);
            }
        });

    }

}
