/**
 * 
 */
package com.travelsky.mlf4j.monitor.service.mlf4j;

import com.travelsky.mlf4j.base.exception.MlfI18nException;
import com.travelsky.mlf4j.monitor.service.mlf4j.bean.FileContentQueryConditionBean;

/**
 * 
 * @author yinranchao
 * @since 2014年10月20日
 */
public interface IMlf4jLogFileService {
    
    
    String getFileContent(FileContentQueryConditionBean queryCondition) throws MlfI18nException;

}
