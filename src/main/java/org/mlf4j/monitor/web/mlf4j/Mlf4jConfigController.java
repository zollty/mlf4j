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
package org.mlf4j.monitor.web.mlf4j;

import javax.servlet.http.HttpServletRequest;

import org.mlf4j.base.Const;
import org.mlf4j.base.exception.MlfI18nException;
import org.mlf4j.monitor.service.mlf4j.IMlf4jConfigService;
import org.mlf4j.monitor.web.WebTools;
import org.mlf4j.monitor.web.WebTools.Service1;
import org.mlf4j.monitor.web.WebTools.Service2;
import org.zollty.framework.core.annotation.Inject;
import org.zollty.framework.mvc.View;
import org.zollty.framework.mvc.annotation.Controller;
import org.zollty.framework.mvc.annotation.HttpParam;
import org.zollty.framework.mvc.annotation.RequestMapping;
import org.zollty.framework.mvc.view.TextView;
import org.zollty.framework.util.MvcUtils;

/**
 * @author zollty
 * @since 2014-6-5
 */
@Controller
public class Mlf4jConfigController {
    
    @Inject("mlf4jConfigService")
    private IMlf4jConfigService mlf4jConfigService;
    
    @RequestMapping("/mlf4j/show-config-file")
    public View getMlf4jConfig(HttpServletRequest request) {
        return new TextView(WebTools.doService(new Service2() {
            @Override
            public String service() throws MlfI18nException {
                return mlf4jConfigService.getMlf4jConfig();
            }
        }));
    }

    @RequestMapping("/mlf4j/refresh-config")
    public View getMlf4jConfig(@HttpParam("configStr") final String configStr) {
        String ret;
        if (MvcUtils.StringUtil.isNotBlank(configStr)) {
            ret = WebTools.doService(new Service1() {
                @Override
                public void service() throws MlfI18nException {
                    mlf4jConfigService.refreshMlf4jConfig(configStr);
                }
            });
        }
        else {
            ret = Const.ERROR + "configStr is blank!";
        }
        return new TextView(ret);
    }

}
