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

import java.util.HashMap;
import java.util.Map;

/**
 * @author zollty
 * @since 2014-6-4
 */
public class LoggerManager {
    
    public static Map<String, LoggerForSetting> cacheLoggerMap = new HashMap<String, LoggerForSetting>();

}
