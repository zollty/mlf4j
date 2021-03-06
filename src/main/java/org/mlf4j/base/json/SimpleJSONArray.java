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
package org.mlf4j.base.json;

/**
 * @author zollty
 * @since 2014-5-7
 */
public class SimpleJSONArray {

    private String jsonArray;
    
    public SimpleJSONArray(String jsonArray){
        this.jsonArray = jsonArray;
    }
    
    @Override
    public String toString() {
        return jsonArray;
    }
}
