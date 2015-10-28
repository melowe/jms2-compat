package com.melowe.jms2.compat;

import java.util.Objects;
import javax.jms.Connection;
import javax.jms.ConnectionConsumer;
import javax.jms.ConnectionMetaData;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.ServerSessionPool;
import javax.jms.Session;
import javax.jms.Topic;

public final class Jms2Connection implements Connection {

    private final Connection connection;

    protected Jms2Connection(Connection connection) {
        this.connection = Objects.requireNonNull(connection);
    }

    @Override
    public Session createSession(boolean transacted, int sessionMode) throws JMSException {
        return new Jms2Session(connection.createSession(transacted, sessionMode));
    }

    @Override
    public Session createSession(int sessionMode) throws JMSException {
        return createSession(sessionMode == Session.SESSION_TRANSACTED, sessionMode);
    }

    @Override
    public Session createSession() throws JMSException {
        return createSession(false, Session.AUTO_ACKNOWLEDGE);
    }

    @Override
    public String getClientID() throws JMSException {
        return connection.getClientID();
    }

    @Override
    public void setClientID(String clientID) throws JMSException {
        connection.setClientID(clientID);
    }

    @Override
    public ConnectionMetaData getMetaData() throws JMSException {
        return connection.getMetaData();
    }

    @Override
    public ExceptionListener getExceptionListener() throws JMSException {
        return connection.getExceptionListener();
    }

    @Override
    public void setExceptionListener(ExceptionListener listener) throws JMSException {
        connection.setExceptionListener(listener);
    }

    @Override
    public void start() throws JMSException {
        connection.start();
    }

    @Override
    public void stop() throws JMSException {
        connection.stop();
    }

    @Override
    public void close() throws JMSException {
        connection.close();
    }

    @Override
    public ConnectionConsumer createConnectionConsumer(Destination arg0, String arg1, ServerSessionPool arg2, int arg3) throws JMSException {
        return connection.createConnectionConsumer(arg0, arg1, arg2, arg3);
    }

    @Override
    public ConnectionConsumer createSharedConnectionConsumer(Topic arg0, String arg1, String arg2, ServerSessionPool arg3, int arg4) throws JMSException {
        return connection.createSharedConnectionConsumer(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public ConnectionConsumer createDurableConnectionConsumer(Topic arg0, String arg1, String arg2, ServerSessionPool arg3, int arg4) throws JMSException {
        return connection.createDurableConnectionConsumer(arg0, arg1, arg2, arg3, arg4);
    }

    @Override
    public ConnectionConsumer createSharedDurableConnectionConsumer(Topic arg0, String arg1, String arg2, ServerSessionPool arg3, int arg4) throws JMSException {
        return connection.createSharedDurableConnectionConsumer(arg0, arg1, arg2, arg3, arg4);
    }

}
