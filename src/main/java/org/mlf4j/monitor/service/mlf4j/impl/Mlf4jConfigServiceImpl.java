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
package org.mlf4j.monitor.service.mlf4j.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.Properties;

import org.mlf4j.base.exception.MlfI18nException;
import org.mlf4j.base.util.PropertiesTools;
import org.mlf4j.log.LogFactory;
import org.mlf4j.log.LogUtils;
import org.mlf4j.monitor.service.mlf4j.IMlf4jConfigService;
import org.zollty.framework.core.annotation.Component;
import org.zollty.framework.util.MvcUtils;
import org.zollty.util.NestedRuntimeException;

/**
 * @author zollty
 * @since 2014-6-4
 */
@Component("mlf4jConfigService")
public class Mlf4jConfigServiceImpl implements IMlf4jConfigService {
    
    @Override
    public void refreshMlf4jConfig(String configStr) throws MlfI18nException {
        InputStream in = MvcUtils.IOUtil.getInputStreamFromString(configStr, "UTF-8");
        Properties props = new Properties();
        try {
            props.load(in);
        } catch (IOException e) {
            throw new NestedRuntimeException(e);
        }
        Map<String, String> pmap = LogUtils.covertProperties2Map(props);
        LogFactory.refreshZolltyLogConfig(pmap);
    }

    @Override
    public String getMlf4jConfig() throws MlfI18nException {
        
        return PropertiesTools.getResoureFileContent("mlf4j-config.properties");
    }
    

}
