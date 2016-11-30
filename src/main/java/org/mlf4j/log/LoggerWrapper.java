/* 
 * Copyright (C) 2012-2014 the original author or authors.
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
 * 
 */
package org.mlf4j.log;


/**
 * @author zollty 
 * @since 2013-6-21
 */
public class LoggerWrapper implements Logger {

	private transient Logger logger = null;
	
	private static Level threshold = Level.ALL;
	
	private Level localLevel; // 如果设置了locallevel，则以它为准，否则以全局threshold为准。
	
    private boolean isEffectiveLevel(Level level) {
        return localLevel == null ? level.isGreaterOrEqual(threshold) : level.isGreaterOrEqual(localLevel);
    }
	
	public LoggerWrapper(Logger logger) {
		this.logger = logger;
	}

	@Override
	public void info(Object message) {
		if( isEffectiveLevel(Level.INFO) )
		this.logger.info(message);
	}


	@Override
	public void info(Throwable e) {
		if( isEffectiveLevel(Level.INFO) )
		this.logger.info(e);
	}

	
	@Override
	public void info(Throwable e, Object message) {
		if( isEffectiveLevel(Level.INFO) )
		this.logger.info(e, message);
	}


	@Override
	public void info(Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.INFO) )
		this.logger.info(message, msgParams);
	}


	@Override
	public void info(Throwable e, Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.INFO) )
		this.logger.info(e, message, msgParams);
	}


	@Override
	public void warn(Object message) {
		if( isEffectiveLevel(Level.WARN) )
		this.logger.warn(message);
	}


	@Override
	public void warn(Throwable e) {
		if( isEffectiveLevel(Level.WARN) )
		this.logger.warn(e);
	}


	@Override
	public void warn(Throwable e, Object message) {
		if( isEffectiveLevel(Level.WARN) )
		this.logger.warn(e, message);
	}


	@Override
	public void warn(Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.WARN) )
		this.logger.warn(message, msgParams);
	}


	@Override
	public void warn(Throwable e, Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.WARN) )
		this.logger.warn(e, message, msgParams);
	}


	@Override
	public void error(Object message) {
		if( isEffectiveLevel(Level.ERROR) )
		    this.logger.error(message);
	}


	@Override
	public void error(Throwable e) {
		if( isEffectiveLevel(Level.ERROR) )
		this.logger.error(e);
	}


	@Override
	public void error(Throwable e, Object message) {
		if( isEffectiveLevel(Level.ERROR) )
		this.logger.error(e, message);
	}
	

	@Override
	public void error(Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.ERROR) )
		this.logger.error(message, msgParams);
	}

	
	@Override
	public void error(Throwable e, Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.ERROR) )
		this.logger.error(e, message, msgParams);
	}


	@Override
	public void debug(Object message) {
		if( isEffectiveLevel(Level.DEBUG) )
		this.logger.debug(message);
	}


	@Override
	public void debug(Throwable e) {
		if( isEffectiveLevel(Level.DEBUG) )
		this.logger.debug(e);
	}


	@Override
	public void debug(Throwable e, Object message) {
		if( isEffectiveLevel(Level.DEBUG) )
		this.logger.debug(e, message);
	}


	@Override
	public void debug(Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.DEBUG) )
		this.logger.debug(message, msgParams);
	}


	@Override
	public void debug(Throwable e, Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.DEBUG) )
		this.logger.debug(e, message, msgParams);
	}


	@Override
	public void trace(Object message) {
		if( isEffectiveLevel(Level.TRACE) )
		this.logger.trace(message);
	}


	@Override
	public void trace(Throwable e) {
		if( isEffectiveLevel(Level.TRACE) )
		this.logger.trace(e);
	}


	@Override
	public void trace(Throwable e, Object message) {
		if( isEffectiveLevel(Level.TRACE) )
		this.logger.trace(e, message);
	}


	@Override
	public void trace(Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.TRACE) )
		this.logger.trace(message, msgParams);
	}


	@Override
	public void trace(Throwable e, Object message, Object... msgParams) {
		if( isEffectiveLevel(Level.TRACE) )
		this.logger.trace(e, message, msgParams);
	}
	
	
	@Override
    public void log(String callerFQCN, org.mlf4j.log.Level lev, Throwable t, Object msg, Object... msgParams) {
        this.logger.log(callerFQCN, lev, t, msg, msgParams);
    }
	

	public static Level getThreshold() {
		return threshold;
	}

	public static void setThreshold(Level threshold) {
		LoggerWrapper.threshold = threshold;
	}

	public Level getLocalLevel() {
		return localLevel;
	}

	public void setLocalLevel(Level localLevel) {
		this.localLevel = localLevel;
	}


	@Override
	public boolean isTraceEnabled() {
	    return isEffectiveLevel(Level.TRACE)?this.logger.isTraceEnabled():false;
	}


	@Override
	public boolean isDebugEnabled() {
	    return isEffectiveLevel(Level.DEBUG)?this.logger.isDebugEnabled():false;
	}


	@Override
	public boolean isInfoEnabled() {
	    return isEffectiveLevel(Level.INFO)?this.logger.isInfoEnabled():false;
	}

}
