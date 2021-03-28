package com.test.concurrent.unsafe;

import com.test.concurrent.util.UnsafeInstance;
import sun.misc.Unsafe;

/**
 * @author ：图灵-杨过
 * @date：2019/8/2
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description :
 */
public class ObjectMonitorRunner {
    static Object object = new Object();
    static Unsafe unsafe = UnsafeInstance.reflectGetUnsafe();
    public void method1(){
        unsafe.monitorEnter(object);
    }

    public void method2(){
        unsafe.monitorExit(object);
    }

    public static void main(String[] args) {
        //jvm内置锁
        synchronized (object){

            //写逻辑
        }
    }

}
