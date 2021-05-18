package com.test.tickets;

public class TicketsRunnable {

	public static void main(String[] args) {

		MyThreadR mtr = new MyThreadR();
		// 创建三个线程，模拟三个窗口
		Thread th1 = new Thread(mtr, "窗口1");
		Thread th2 = new Thread(mtr, "窗口2");
		Thread th3 = new Thread(mtr, "窗口3");
		
		th1.start();
		th2.start();
		th3.start();
		
	}
}

class MyThreadR implements Runnable {

	// 一共由5张火车票
	private int ticketsCont = 5;

	@Override
	public void run() {
		while (ticketsCont > 0) {
			ticketsCont--; // 如果还有票，就卖掉一张
			System.out.println(Thread.currentThread().getName() + "卖了1张票，剩余票数为：" + ticketsCont);
		}
	}
}