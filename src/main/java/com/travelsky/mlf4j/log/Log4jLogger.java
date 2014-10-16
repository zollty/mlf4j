/* 
 * Copyright (C) 2012-2014 TravelSky Technology Limited.
 * 
 * == Mlf4j (Monitoring Logging Facade for Java) ==
 * ============为监控而生的通用日志工具库===========
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by Zollty Tsou (zouty@travelsky.com, http://blog.csdn.net/zollty)
 */
package com.travelsky.mlf4j.log;

import java.io.Serializable;
import java.net.URL;

import org.apache.log4j.Level;
import org.apache.log4j.Priority;
import org.apache.log4j.xml.DOMConfigurator;

/**
 * @author zollty
 * @since 2013-6-22
 */
public class Log4jLogger implements com.travelsky.mlf4j.log.Logger, LoggerSupport, Serializable {

    private static final long serialVersionUID = -226607492227272649L;

    public static String configConfigLocation = "log4j-config.xml";
    
    private static final String FQCN = LoggerWrapper.class.getName();
    
    /** Logger name */
    private final String name;

    /**
     * The underlying Logger implementation we are using.
     */
    protected transient org.apache.log4j.Logger logger = null;
    
    static {
        if (!Priority.class.isAssignableFrom(Level.class)) {
            // only support 1.2.x
            // nope, this is log4j 1.3, so force an ExceptionInInitializerError
            throw new InstantiationError("Log4J 1.2 not available");
        }
    }
    
    /**
     * Return the native Logger instance we are using.
     */
    public org.apache.log4j.Logger getLogger() {
        org.apache.log4j.Logger result = logger;
        if (result == null) {
            synchronized(this) {
                result = logger;
                if (result == null) {
                    logger = result = org.apache.log4j.Logger.getLogger(name);
                }
            }
        }
        return result;
    }
    

    public Log4jLogger() {
        this.name = null;
    }
   

    public Log4jLogger(String name) {
        this.name = name;
        this.logger = getLogger();
    }

    @Override
    public com.travelsky.mlf4j.log.Logger newInstance(String name) {
        return new Log4jLogger(name);
    }

    public static void refreshLog4jConfig() {
        URL url;
        url = Thread.currentThread().getContextClassLoader().getResource(configConfigLocation);
        DOMConfigurator.configure(url);
    }

    @Override
    public void info(Object message) {
        getLogger().log(FQCN, Level.INFO, message, null);
    }

    @Override
    public void info(Throwable e) {
        getLogger().log(FQCN, Level.INFO, null, e);
    }

    @Override
    public void info(Throwable e, Object message) {
        getLogger().log(FQCN, Level.INFO, message, e);
    }

    @Override
    public void info(Object message, Object... msgParams) {
        if (getLogger().isInfoEnabled()) {
            getLogger().log(FQCN, Level.INFO, LogUtils.replace(message.toString(), msgParams), null);
        }
    }

    @Override
    public void info(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isInfoEnabled()) {
            getLogger().log(FQCN, Level.INFO, LogUtils.replace(message.toString(), msgParams), e);
        }
    }

    @Override
    public void warn(Object message) {
        getLogger().log(FQCN, Level.WARN, message, null);
    }

    @Override
    public void warn(Throwable e) {
        getLogger().log(FQCN, Level.WARN, null, e);
    }

    @Override
    public void warn(Throwable e, Object message) {
        getLogger().log(FQCN, Level.WARN, message, e);
    }

    @Override
    public void warn(Object message, Object... msgParams) {
        if (getLogger().isEnabledFor(Level.WARN)) {
            getLogger().log(FQCN, Level.WARN, LogUtils.replace(message.toString(), msgParams), null);
        }
    }

    @Override
    public void warn(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isEnabledFor(Level.WARN)) {
            getLogger().log(FQCN, Level.WARN, LogUtils.replace(message.toString(), msgParams), e);
        }
    }

    @Override
    public void error(Object message) {
        getLogger().log(FQCN, Level.ERROR, message, null);
    }

    @Override
    public void error(Throwable e) {
        getLogger().log(FQCN, Level.ERROR, null, e);
    }

    @Override
    public void error(Throwable e, Object message) {
        getLogger().log(FQCN, Level.ERROR, message, e);
    }

    @Override
    public void error(Object message, Object... msgParams) {
        getLogger().log(FQCN, Level.ERROR, LogUtils.replace(message.toString(), msgParams), null);
    }

    @Override
    public void error(Throwable e, Object message, Object... msgParams) {
        getLogger().log(FQCN, Level.ERROR, LogUtils.replace(message.toString(), msgParams), e);
    }

    @Override
    public void debug(Object message) {
        getLogger().log(FQCN, Level.DEBUG, message, null);
    }

    @Override
    public void debug(Throwable e) {
        getLogger().log(FQCN, Level.DEBUG, null, e);
    }

    @Override
    public void debug(Throwable e, Object message) {
        getLogger().log(FQCN, Level.DEBUG, message, e);
    }

    @Override
    public void debug(Object message, Object... msgParams) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, LogUtils.replace(message.toString(), msgParams), null);
        }
    }

    @Override
    public void debug(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isDebugEnabled()) {
            getLogger().log(FQCN, Level.DEBUG, LogUtils.replace(message.toString(), msgParams), e);
        }
    }

    @Override
    public void trace(Object message) {
        getLogger().log(FQCN, Level.TRACE, message, null);
    }

    @Override
    public void trace(Throwable e) {
        getLogger().log(FQCN, Level.TRACE, null, e);
    }

    @Override
    public void trace(Throwable e, Object message) {
        getLogger().log(FQCN, Level.TRACE, message, e);
    }

    @Override
    public void trace(Object message, Object... msgParams) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, Level.TRACE, LogUtils.replace(message.toString(), msgParams), null);
        }
    }

    @Override
    public void trace(Throwable e, Object message, Object... msgParams) {
        if (getLogger().isTraceEnabled()) {
            getLogger().log(FQCN, Level.TRACE, LogUtils.replace(message.toString(), msgParams), e);
        }
    }
    
    
    @Override
    public void log(String callerFQCN, com.travelsky.mlf4j.log.Level lev, Throwable t, Object msg, Object... msgParams) {
        Level level;
        if (com.travelsky.mlf4j.log.Level.TRACE.isGreaterOrEqual(lev)) {
            level = Level.TRACE;
        }
        else if (com.travelsky.mlf4j.log.Level.DEBUG.isGreaterOrEqual(lev)) {
            level = Level.DEBUG;
        }
        else if (com.travelsky.mlf4j.log.Level.INFO.isGreaterOrEqual(lev)) {
            level = Level.INFO;
        }
        else if (com.travelsky.mlf4j.log.Level.WARN.isGreaterOrEqual(lev)) {
            level = Level.WARN;
        }
        else if (com.travelsky.mlf4j.log.Level.ERROR.isGreaterOrEqual(lev)) {
            level = Level.ERROR;
        }
        else {
            throw new IllegalStateException("Level " + lev + " is not recognized.");
        }
        logger.log(callerFQCN, level, msg, t);
    }
    

    @Override
    public boolean isTraceEnabled() {
        return getLogger().isTraceEnabled();
    }


    @Override
    public boolean isDebugEnabled() {
        return getLogger().isDebugEnabled();
    }


    @Override
    public boolean isInfoEnabled() {
        return getLogger().isInfoEnabled();
    }


    @Override
    public String getName() {
        return name;
    }

}
