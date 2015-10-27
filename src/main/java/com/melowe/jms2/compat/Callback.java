
package com.melowe.jms2.compat;

import javax.jms.JMSException;

//Functional interface for unchecking JMSExceptions
public interface Callback<T> {
    
    T execute() throws JMSException;
    
}
