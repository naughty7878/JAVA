package com.test.jmm.dcl;

public class Singleton {

    /**
     * 查看汇编指令
     * -XX:+UnlockDiagnosticVMOptions -XX:+PrintAssembly -Xcomp
     */
    private volatile static Singleton myinstance;

    /**
     * 双重锁机制保证单例安全
     * @return
     */
    public static Singleton getInstance() {
        if (myinstance == null) {
            synchronized (Singleton.class) {
                if (myinstance == null) {
                    myinstance = new Singleton();
                }
            }
        }
        return myinstance;
    }

    public static void main(String[] args) {
        Singleton.getInstance();
    }
}
