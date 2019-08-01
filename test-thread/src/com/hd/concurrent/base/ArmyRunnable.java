package com.hd.concurrent.base;

/**
 * 军队线程
 * 模拟作战双方的行为
 * @ClassName ArmyRunnable 
 * @Description TODO
 * @author H__D
 * @date 2018年7月18日
 *
 */
public class ArmyRunnable implements Runnable {

	// volatile 保证了线程可以正确的读取其他线程写入的值
	// 可见行
	volatile boolean keepRuning = true;
	
	@Override
	public void run() {
		
		while (keepRuning) {
			// 发动5连击
			for (int i = 0; i < 5; i++) {
				// getName()  返回该线程的名称。
				System.out.println(Thread.currentThread().getName() + " 攻击对方【" + i +"】");
			
				// 让出处理器时间，再一次谁进攻还不一定
				// yield() 暂停当前正在执行的线程对象，并执行其他线程
				Thread.yield();
			}
		}

		System.out.println(Thread.currentThread().getName() + "鸣金收兵");
	}

}
