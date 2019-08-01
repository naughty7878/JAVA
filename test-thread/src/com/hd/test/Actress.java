package com.hd.test;

public class Actress implements Runnable {
	@Override
	public void run() {
		// 开始
		System.out.println(Thread.currentThread().getName() + " 是一个演员！");

		// 进行中
		int count = 0;// 登台次数
		boolean keepRunning = true;// 演出控制
		while (keepRunning) {
			System.out.println(Thread.currentThread().getName() + " 登台演出：" + (++count));
			if (count == 100) {
				keepRunning = false;
			}

			// 登台10场 等待一会
			if (count % 10 == 0) {
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

		// 结束
		System.out.println(Thread.currentThread().getName() + " 的演出结束了");
	}
}
