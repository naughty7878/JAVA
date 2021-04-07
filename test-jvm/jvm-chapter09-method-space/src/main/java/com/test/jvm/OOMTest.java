package com.test.jvm;

import jdk.internal.org.objectweb.asm.ClassWriter;
import jdk.internal.org.objectweb.asm.Opcodes;

/**
 *  JDK7及以前
 *  设置参数：-XX:PermSize=100m -XX:MaxPermSize=100m
 *
 *  JDK8及以后
 *  设置参数：-XX:MetaspaceSize=100m -XX:MaxMetaspaceSize=100m
 *
 * 示例：前提设置方法区大小，不停的加载类，造成方法区OOM
 */
public class OOMTest extends ClassLoader{
    public static void main(String[] args) {
        int j = 0;
        try {
            OOMTest test = new OOMTest();
            for (int i = 0; i < 10000; i++) {
                //创建ClassWriter对象，用于生成类的二进制字节码
                ClassWriter classWriter = new ClassWriter(0);
                //指明版本号，修饰符，类名，包名，父类，接口
                classWriter.visit(Opcodes.V1_8, Opcodes.ACC_PUBLIC, "Class" + i, null, "java/lang/Object", null);
                //返回byte[]
                byte[] code = classWriter.toByteArray();
                //类的加载
                test.defineClass("Class" + i, code, 0, code.length);//Class对象
                j++;
            }
        } finally {
            System.out.println(j);
        }
    }
}
