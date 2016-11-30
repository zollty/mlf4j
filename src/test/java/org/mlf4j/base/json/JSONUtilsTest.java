/*
 * @(#)JSONUtilsTest.java
 */
package org.mlf4j.base.json;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mlf4j.base.json.SimpleJSON;

/**
 * @author zollty
 * @since 2014-5-7
 */
@RunWith(JUnit4.class)
public class JSONUtilsTest {
    
    @Test
    public void test(){
        //simpleJSON4();
    }
    
    public void toJSONString() {
    }

    public void parse() {
    }
    
    
    public void simpleJSON1() {
        SimpleJSON jsonObj = new SimpleJSON();
        jsonObj.addItem("Level", "\n");
        jsonObj.addItem("Name", "SLf4j");
        System.out.println(jsonObj.toString());
    }
    
    public void simpleJSON2() {
        System.out.println(SimpleJSON.getInstance().addItem("Level", "\n").addItem("Name", "SLf4j"));
    }
    
    public void simpleJSON3() {
        SimpleJSON ss1 = SimpleJSON.getInstance()
                .addItem("Level", "\n")
                .addItem("MM", false)
                .addItem("Name", "SLf4j");
        System.out.println(SimpleJSON.getInstance()
                .addItem("str1", ss1)
                .addItem("Name", "SLf4j")
                .addItem("LL", 125L)
                .addItem("now", new Date())
                .toJsonString());
    }

    public void simpleJSON4() {
        List<SimpleJSON> list = new ArrayList<SimpleJSON>();
        for (int i = 0; i < 5; i++) {
            list.add(SimpleJSON.getInstance().addItem("Level", "\n" + i).addItem("Name", "SLf4j" + i));
        }
        String ret = SimpleJSON.getInstance()
            .addItem("lsit", SimpleJSON.toSimpleJSONArray(list))
            .addItem("KKK", "asnsdds")
            .addItem("WFD", 106).toJsonString();
        System.out.println(ret);
    }

}
