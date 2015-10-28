package com.melowe.jms2.compat;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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

}
