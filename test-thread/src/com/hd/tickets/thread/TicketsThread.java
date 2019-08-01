package com.hd.tickets.thread;

public class TicketsThread {

	public static void main(String[] args) {
		
		// 创建三个线程，模拟三个窗口卖票
		MyThread mt1 = new MyThread("窗口1");
		MyThread mt2 = new MyThread("窗口2");
		MyThread mt3 = new MyThread("窗口3");
		
		// 启动三个线程，开始卖票
		mt1.start();
		mt2.start();
		mt3.start();
	}

}



class MyThread extends Thread {
	
	// 一共由5张火车票
	private int ticketsCont = 5;
	
	// 窗口，即是线程的名字
	private String name;
	
	public MyThread(String name) {
		this.name = name;
	}
	
	@Override
	public void run() {
		while (ticketsCont > 0) {
			ticketsCont --; // 如果还有票，就卖掉一张
			System.out.println(name + "卖了1张票，剩余票数为：" + ticketsCont);
		}
	}
	
}



