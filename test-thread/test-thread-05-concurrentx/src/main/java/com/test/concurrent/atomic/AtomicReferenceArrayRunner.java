package com.test.concurrent.atomic;

import java.util.concurrent.atomic.AtomicReferenceArray;

/**
 * @author ：杨过
 * @date ：Created in 2020/8/21
 * @version: V1.0
 * @slogan: 天下风云出我辈，一入代码岁月催
 * @description:
 **/
public class AtomicReferenceArrayRunner {

    static Tuling[] ovalue = new Tuling[]{new Tuling(1),new Tuling(2)};
    static AtomicReferenceArray<Tuling> objarray = new AtomicReferenceArray(ovalue);

    public static void main(String[] args) {
        System.out.println(objarray.get(0).getSequence());

        objarray.set(0,new Tuling(3));

        System.out.println(objarray.get(0).getSequence());

    }

}
