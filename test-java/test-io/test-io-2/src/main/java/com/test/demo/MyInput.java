package com.test.demo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class MyInput {

    public String next(){
        BufferedReader br = null;
        try {
            br = new BufferedReader(new InputStreamReader(System.in));
        } finally {
            if(br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}
