package com.test.singleton;

import java.io.IOException;
import java.util.Properties;

public class Singleton03 {

    public static final Singleton03 INSTANCE;

    private String info;

    static {
        try {
            Properties properties = new Properties();
            properties.load(Singleton03.class.getClassLoader().getResourceAsStream("singleton.properties"));

            INSTANCE = new Singleton03(properties.getProperty("info"));
        }catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

    private Singleton03 (String info){
        this.info = info;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }
}
