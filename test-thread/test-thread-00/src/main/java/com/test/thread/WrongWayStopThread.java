package com.test.thread;

/**
 * 错误停止线程
 * @ClassName WrongWayStopThread 
 * @Description TODO
 * @author H__D
 * @date 2018年7月18日
 *
 */
public class WrongWayStopThread extends Thread {

	public static void main(String[] args) {
	
		WrongWayStopThread thread = new WrongWayStopThread();
		// 开始线程
		System.out.println("Starting thread ...");
		thread.start();
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 中断线程
		System.out.println("Interrupting thread ...");
		thread.interrupt();
		
		
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 结束线程
		System.out.println("Stopping application ...");
	}
	
	
	@Override
	public void run() {
		while (!this.isInterrupted()) {
			System.out.println("Thread is running ....");
//			long time = System.currentTimeMillis();
//			while ((System.currentTimeMillis() - time ) < 1000) {
//				// 减少屏幕输出的空循环
//			}
			
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}











