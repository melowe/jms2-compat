package com.melowe.jms2.compat;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import javax.jms.JMSException;
import javax.jms.Message;

public class Jms2Properties {

    private final Map<String, Object> map = new HashMap<>();

    public <T> T getProperty(String name) {
        return (T) map.get(name);
    }

    public <T> Jms2Properties setProperty(String name, T value) {
        this.map.put(name, value);
        return this;
    }

    public Jms2Properties clear() {
        map.clear();
        return this;
    }

    public boolean propertyExists(String name) {
        return map.containsKey(name);
    }

    public Set<String> getPropertyNames() {
        return map.keySet();
    }

    public void applyToMessage(final Message message) {
        Jms2Util.execute(new Callback<Void>() {

            @Override
            public Void execute() throws JMSException {
                for (String propertyName : getPropertyNames()) {
                    Object value = getProperty(propertyName);
                    java.util.Objects.requireNonNull(value);
                    if (String.class.isInstance(value)) {
                        message.setStringProperty(propertyName, String.class.cast(value));
                    } else if (Integer.class.isInstance(value)) {
                        message.setIntProperty(propertyName, Integer.class.cast(value));
                    } else if (Short.class.isInstance(value)) {
                        message.setShortProperty(propertyName, Short.class.cast(value));
                    } else if (Double.class.isInstance(value)) {
                        message.setDoubleProperty(propertyName, Double.class.cast(value));
                    } else if (Float.class.isInstance(value)) {
                        message.setFloatProperty(propertyName, Float.class.cast(value));
                    } else if (Boolean.class.isInstance(value)) {
                        message.setBooleanProperty(propertyName, Boolean.class.cast(value));
                    } else if (Byte.class.isInstance(value)) {
                        message.setByteProperty(propertyName, Byte.class.cast(value));
                    } else {
                        message.setObjectProperty(propertyName, value);
                    }

                }
                return null;

            }

        }
        );

    }
}
