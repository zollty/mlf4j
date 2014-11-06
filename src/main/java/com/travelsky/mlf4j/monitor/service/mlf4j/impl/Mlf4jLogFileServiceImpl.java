/**
 * 
 */
package com.travelsky.mlf4j.monitor.service.mlf4j.impl;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

import org.zollty.framework.core.annotation.Component;
import org.zollty.framework.util.MvcUtils;

import com.travelsky.mlf4j.base.exception.MlfI18nException;
import com.travelsky.mlf4j.monitor.service.mlf4j.IMlf4jLogFileService;
import com.travelsky.mlf4j.monitor.service.mlf4j.bean.FileContentQueryConditionBean;

/**
 * 
 * @author yinranchao
 * @since 2014年10月20日
 */
@Component("mlf4jLogFileService")
public class Mlf4jLogFileServiceImpl implements IMlf4jLogFileService {
    private static final String SPE = "\n";
    private static final String DEFAULT_ENCODE = "ISO-8859-1";
    private String encode = "GBK";
    RandomAccessFile raf;
   

    @Override
    public String getFileContent(FileContentQueryConditionBean queryCondition) throws MlfI18nException {
        String content = "";
        String keyWord = "";
        String level = "";
        int lineBegin = 1;
        int lineEnd = 500;
        try {
            raf = new RandomAccessFile(queryCondition.getLogFilePath().replaceAll("\\\\", "/"), "r");
            if(MvcUtils.StringUtil.isNotBlank(queryCondition.getKeyWord())){
                keyWord = queryCondition.getKeyWord();
            }
            if(MvcUtils.StringUtil.isNotBlank(queryCondition.getLevelStr())){
                level = queryCondition.getLevelStr();
            }
            if(MvcUtils.StringUtil.isNotBlank(queryCondition.getCode())){
                encode = queryCondition.getCode();
            }
            if(queryCondition.getLineBegin() != 0){
                lineBegin = queryCondition.getLineBegin();
            }
            if(queryCondition.getLineEnd() != 0){
                lineEnd = queryCondition.getLineEnd();
            }
            List<String> tempList = new ArrayList<String>();
            List<String> ttempList = new ArrayList<String>();
            String tempStr = "";
            String[] levelArray = level.split(",");
            
            int start = lineBegin - 10;
            int end = lineEnd + 10;
            if (start < 1) {
                start = 1;
            }
            int i = 1;
            //对日志文件进行按行读取，因为要显示关键字前10和后10行数据，所以要多读20条数据
            while (raf.getFilePointer() < raf.length()) {
                if (i > end) {
                    break;
                }
                if (i >= start && i < lineBegin) {
                    tempStr = readContentByLine(raf);
                    tempList = updateTempList(tempList,tempStr);
                }
                else if (i >= lineBegin && i <= lineEnd) {
                    tempStr = readContentByLine(raf);
                    tempList = updateTempList(tempList,tempStr);
                    //关键字不为空
                    if (MvcUtils.StringUtil.isNotBlank(keyWord)) {
                        //循环level级别
                        for (int y = 0; y < levelArray.length; y++) {
                            //关键字不为空，level为空
                            if(isLevelNeed(tempStr, levelArray[y])) {
                                int isNull = -1;
                                if(isKeyWordNeed(tempStr,keyWord)){
                                    content = makeContent(content,tempList,tempStr,ttempList,isNull);
                                }
                            }
                            //关键字不为空，level不为空
                            else {
                                if(isKeyWordNeed(tempStr,keyWord)){
                                    int isNull = -1;
                                    if(isLevelNeed(tempStr, levelArray[y])) {
                                        content = makeContent(content,tempList,tempStr,ttempList,isNull);
                                    }
                                }
                            }
                        }

                    }
                    //关键字为空
                    else {
                        for (int z = 0; z < levelArray.length; z++) {
                            if (MvcUtils.StringUtil.isBlank(levelArray[z])) {
                                content += tempStr + SPE;
                            }
                            else {
                                if(isLevelNeed(tempStr, levelArray[z])) {
                                    content += tempStr + SPE;
                                }
                            }
                        }

                    }
                }
                else if (i > lineEnd && i <= end) {
                    tempStr = readContentByLine(raf);
                    tempList = updateTempList(tempList,tempStr);
                }
                else {
                    tempStr = readContentByLine(raf);
                }
                i++;
                raf.seek(raf.getFilePointer());
            }
        }
        catch (FileNotFoundException e) {
            return "error";
        }catch(IOException e){
            return "fail";
        }finally{
            MvcUtils.IOUtil.close(raf);
        }
        content = content.replace('[', ' ').replace(']', ' ').trim();
        return content;
    }
    //解决raf的乱码问题，raf默认用ISO-8859-1读取，我们要将他改为用用户选择的编码格式我们默认用gbk
    private String readContentByLine(RandomAccessFile raf) throws IOException{
        return new String(raf.readLine().getBytes(DEFAULT_ENCODE),encode);
    }
    
    private boolean isLevelNeed(String tempStr, String level){
        String use = tempStr;
        if(tempStr.length()>100){
            use = tempStr.substring(0, 100);
        }
        if(use.indexOf(level) != -1){
            return true;
        }
        return false;
    }
    
    private List<String> updateTempList(List<String> tempList,String tempStr){
        tempList.add(tempStr + SPE);
        if (tempList.size() > 11) {
            tempList.remove(0);
        }
        return tempList;
    }
    
    private boolean isKeyWordNeed(final String tempStr, String keyWord){
        if(tempStr.indexOf(keyWord) != -1){
            return true;
        }
        return false;
    }
    
    private String makeTempListToContent(String content,List<String> tempList){
        for(int i = 0;i < tempList.size();i++){
            content += tempList.get(i);
        }
        return content;
    }
    
    private String makeContent(String content,List<String> tempList,String tempStr,List<String> ttempList,int isNull) throws IOException{
        content = makeTempListToContent(content,tempList);
        for (int j = 0; j < 11; j++) {
            tempStr = readContentByLine(raf);
            if (tempList.size() >= 11) {
                tempList.remove(0);
            }
            if (MvcUtils.StringUtil.isBlank(tempStr)) {
                if(j != 0 ){
                    for(int i = 0;i < tempList.size();i++){
                        ttempList.add(tempList.get(i));
                    }
                    tempList = ttempList;  
                }
                isNull = j;
                break;
            }
            tempList.add(tempStr  + SPE);
        }
        if(isNull != 0){
            content = makeTempListToContent(content,tempList);
        }
        tempList.clear();
        return content;
    }
}
