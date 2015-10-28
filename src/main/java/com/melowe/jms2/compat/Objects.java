package com.melowe.jms2.compat;

public class Objects {

    static boolean nonNull(Object obj) {
        return obj != null;
    }

    static boolean isNull(Object obj) {
        return obj == null;
    }

    static <T> T requireNonNull(T obj) {
        if (isNull(obj)) {
            throw new NullPointerException();
        }
        return obj;
    }

}
