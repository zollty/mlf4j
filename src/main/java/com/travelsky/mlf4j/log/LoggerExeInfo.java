/* 
 * Copyright (C) 2012-2014 TravelSky Technology Limited.
 * Mlf4j (Monitoring Logging Facade for Java) 
 * -- 为监控而生的通用日志工具库
 * 
 * Licensed under the Apache License, Version 2.0 (the "License").
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *     http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * Create by Zollty Tsou (zouty@travelsky.com, http://blog.zollty.com)
 */
package com.travelsky.mlf4j.log;

public class LoggerExeInfo {
    
    private String name;
    long traceCount;
    long debugCount;
    long infoCount;
    long warnCount;
    long errorCount;
    
    public LoggerExeInfo(){
    }
    
    public LoggerExeInfo(String name){
        this.name = name;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public long getTraceCount() {
        return traceCount;
    }
    public void setTraceCount(long traceCount) {
        this.traceCount = traceCount;
    }
    public long getDebugCount() {
        return debugCount;
    }
    public void setDebugCount(long debugCount) {
        this.debugCount = debugCount;
    }
    public long getInfoCount() {
        return infoCount;
    }
    public void setInfoCount(long infoCount) {
        this.infoCount = infoCount;
    }
    public long getWarnCount() {
        return warnCount;
    }
    public void setWarnCount(long warnCount) {
        this.warnCount = warnCount;
    }
    public long getErrorCount() {
        return errorCount;
    }
    public void setErrorCount(long errorCount) {
        this.errorCount = errorCount;
    }

}
