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

import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * @author zollty
 * @since 2014-5-7
 */
public class SimpleJSON {

    private Map<String, Object> map;
    
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public static SimpleJSON getInstance() {
        return new SimpleJSON();
    }

    public SimpleJSON() {
        map = new HashMap<String, Object>();
    }

    public SimpleJSON addItem(String key, Object value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if (value != null) {
            map.put(key, value);
        }
        else {
            map.put(key, "");
        }
        return this;
    }
    
    
    public static SimpleJSONArray toSimpleJSONArray(Collection<SimpleJSON> list) {
        StringBuilder sbr = new StringBuilder();
        if (list == null) {
            return new SimpleJSONArray("\"null\"");
        }
        int entryIndex = 0;
        sbr.append('[');
        for (SimpleJSON entry : list) {
            if (entryIndex != 0) {
                sbr.append(',');
            }
            sbr.append(entry.toString());
            entryIndex++;
        }
        sbr.append(']');
        return new SimpleJSONArray(sbr.toString());
    }

    public String toJsonString(){
        StringBuilder strb = new StringBuilder();
        strb.append("{");
        Iterator<Map.Entry<String,Object>> entryIter = map.entrySet().iterator();
        Map.Entry<String,Object> entry;
        Object value;
        boolean first=true;
        while(entryIter.hasNext()){
            if(!first) {
                strb.append(", ");
            }else{
                first = false;
            }
            entry = entryIter.next();
            strb.append("\"").append(entry.getKey()).append("\": ");
            value = entry.getValue();
            if( value==null ){
                strb.append("null");
            }else if(value instanceof Number ){
                strb.append(value.toString());
            }else if(value instanceof Boolean){
                strb.append(value.toString());
            }else if(SimpleJSON.class == value.getClass() ){
                strb.append(value.toString());
            }else if(SimpleJSONArray.class == value.getClass() ){
                strb.append(value.toString());
            }else if(value instanceof Date){
                strb.append("\"").append(dateFormat.format(value)).append("\"");
            }else{
                strb.append("\"").append(value.toString()).append("\"");
            }
        }
        strb.append("}");
        return strb.toString();
    }
    
    @Override
    public String toString() {
        return JSONUtils.toJSONString(map);
    }
}
