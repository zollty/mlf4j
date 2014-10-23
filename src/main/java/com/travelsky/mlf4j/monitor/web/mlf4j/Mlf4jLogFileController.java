package com.travelsky.mlf4j.monitor.web.mlf4j;

import org.zollty.framework.core.annotation.Inject;
import org.zollty.framework.mvc.View;
import org.zollty.framework.mvc.annotation.Controller;
import org.zollty.framework.mvc.annotation.HttpParam;
import org.zollty.framework.mvc.annotation.RequestMapping;
import org.zollty.framework.mvc.view.TextView;
import org.zollty.framework.util.MvcUtils;

import com.travelsky.mlf4j.base.exception.MlfI18nException;
import com.travelsky.mlf4j.monitor.service.mlf4j.IMlf4jLogFileService;
import com.travelsky.mlf4j.monitor.service.mlf4j.bean.FileContentQueryConditionBean;
import com.travelsky.mlf4j.monitor.web.WebTools;
import com.travelsky.mlf4j.monitor.web.WebTools.Service2;

@Controller
public class Mlf4jLogFileController {
    
    
    @Inject("mlf4jLogFileService")
    private IMlf4jLogFileService mlf4jLogFileService;
    
    
    @RequestMapping("/mlf4j/showLogFileContent")
    public View getFileContent(@HttpParam("logFilePath") final String logFilePath,@HttpParam("lineBegin") final String lineBegin,
            @HttpParam("lineEnd") final String lineEnd,@HttpParam("levelStr") final String levelStr,@HttpParam("keyWord") final String keyWord) throws MlfI18nException {
        final FileContentQueryConditionBean queryCondition = new FileContentQueryConditionBean();
        if(MvcUtils.StringUtil.isNotBlank(logFilePath)){
            queryCondition.setLogFilePath(logFilePath);
        }
        if(MvcUtils.StringUtil.isNotBlank(lineBegin)){
            queryCondition.setLineBegin(Integer.parseInt(lineBegin));
        }
        if(MvcUtils.StringUtil.isNotBlank(lineEnd)){
            queryCondition.setLineEnd(Integer.parseInt(lineEnd));
        }
        if(MvcUtils.StringUtil.isNotBlank(levelStr)){
            queryCondition.setLevelStr(levelStr);
        }
        if(MvcUtils.StringUtil.isNotBlank(keyWord)){
            queryCondition.setKeyWord(keyWord);
        }
        return new TextView(WebTools.doService(new Service2() {
            @Override
            public String service() throws MlfI18nException {
                return mlf4jLogFileService.getFileContent(queryCondition);
            }
        }));
    }

}
