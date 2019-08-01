package com.hd.concurrent.base;

/**
 * 关键人物
 * @ClassName KeyPersonThread 
 * @Description TODO
 * @author H__D
 * @date 2018年7月18日
 *
 */
public class KeyPersonThread extends Thread {

	@Override
	public void run() {
		
		System.out.println(Thread.currentThread().getName() + " 开始了战斗！");
		
		// 10连击
		for (int i = 0; i < 10; i++) {
			System.out.println(Thread.currentThread().getName() + " 左突右冲，攻击随军。。。");
		}
		
		System.out.println(Thread.currentThread().getName() + " 结束了战斗！");
		
	}
}




















