package com.test._03_reference;

import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.List;

/**
 * 软引用的测试：内存不足即回收
 *
 * @author shkstart  shkstart@126.com
 * @create 2020  16:06
 */
public class SoftReferenceTest2 {

    public static void main(String[] args) {
        //创建对象，建立软引用
        //上面的一行代码，等价于如下的三行代码
        byte[] a = new byte[1024 * 1024 * 10];
        SoftReference<byte[]> arrSoftRef = new SoftReference(a);
        a = null;//取消强引用

        //从软引用中重新获得强引用对象
        System.out.println(arrSoftRef.get());

        System.gc();
        System.out.println("After GC:");
//        //垃圾回收之后获得软引用中的对象
        System.out.println(arrSoftRef.get());//由于堆空间内存足够，所有不会回收软引用的可达对象。
//
        List<byte[]> list = new ArrayList<>();
        try {

            while (true) {
                //让系统认为内存资源紧张、不够
                byte[] b = new byte[1024*1024];
                list.add(b);
                if(arrSoftRef.get() == null) {

                    System.out.println(list.size());
                    break;
                }
                Thread.sleep(1);
            }

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            System.out.println(list.size());
            //再次从软引用中获取数据
            System.out.println(arrSoftRef.get());//在报OOM之前，垃圾回收器会回收软引用的可达对象。
        }
    }
}


