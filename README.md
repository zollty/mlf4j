Mlf4j
==========
    
What is Mlf4j 
----------------------------

Mlf4j(Monitoring Logging Facade for Java), it's a facade or abstraction for various logging frameworks (e.g. java.util.logging, logback, log4j) allowing the end user to plug in the desired logging framework at deployment time. It is not only a facade, but also provides some useful decorations for common logging frameworks, such as log monitoring, online log viewer, configuration dynamic refreshing.
 

How to use it 
---------------------------------------

### Easy to use 
	For example:
```java
  public static final Logger LOG = LogFactory.getLogger(); // Concise

  LOG.error("Hello {}, welcome to {}", "GUYS", "ZolltyLog Demo"); // use placeholder
```
### Some advanced usages 
	See the manual and docs.  