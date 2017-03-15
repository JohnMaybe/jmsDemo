package com.cvnchina.jms;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ztianyu(ztianyu@cvnchina.com) on 2017/3/9.
 * project : jmsTest
 * package : com.cvnchina.jms
 * <p>
 * <p>
 * COPYRIGHT BY CVNCHINA 2017.
 * <a href = "http://www.cvnchina.com">www.cvnchina.com</a>
 */
public class Test {
    List<String> stringList;
    List<String> stringLists = stringList;

    public Test(){

    }
    public Test(List<String> stringLists){
        this.stringList = stringLists;
    }
    public List<String> getStringList() {
        return stringList;
    }

    public void setStringList(List<String> stringList) {
        this.stringList = stringList;
    }

    public List<String> getStringLists() {
        return stringLists;
    }

    public void setStringLists(List<String> stringLists) {
        this.stringLists = stringLists;
    }

    public static void main(String[] args) {
       Test test = new Test(new ArrayList<>(10));
//        test.setStringList(new ArrayList<>(10));
        System.out.println(test.getStringList());
        System.out.println(test.getStringLists());

    }
}
