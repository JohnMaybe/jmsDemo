package com.cvnchina.jms;

/**
 * Created by ztianyu(ztianyu@cvnchina.com) on 2017/3/7.
 * project : jmsTest
 * package : com.cvnchina.jms
 * <p>
 * <p>
 * COPYRIGHT BY CVNCHINA 2017.
 * <a href = "http://www.cvnchina.com">www.cvnchina.com</a>
 */
public class BrokerService {
    private boolean persistent;
    private boolean useJmx;

    public void setPersistent(boolean persistent) {
        this.persistent = persistent;
    }

    public void setUseJmx(boolean useJmx) {
        this.useJmx = useJmx;
    }

    public void start() {
    }
    
    public void addConnector(String s){
        
    }
}
