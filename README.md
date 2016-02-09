# jms2-compat
Compatibility library for wrapping JMS 1 connection factories in JMS2 compliant objects.

```java
ConnectionFactory jms1ConnectionFactory .. 
ConnectionFactory connectonFactory = new com.melowe.jms2.compat.Jms2ConnectionFactory(jms1ConnectionFactory);

try (JMSContext context = connectonFactory.createContext()) {
    // do JMS2 stuff
}
```
