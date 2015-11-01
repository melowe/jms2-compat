
package com.melowe.jms2.compat;

import javax.jms.Connection;
import javax.jms.Session;

public class Jms2TestObjectFactory {
    public static Jms2Session create(Session session) {
        return new Jms2Session(session);
    }
    
    public static Jms2Connection create(Connection conn) {
        return new Jms2Connection(conn);
    }
    
}
