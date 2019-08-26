package com.hd.concurrent.base;

/**
 * 隋唐演义大戏舞台
 * @ClassName Stag 
 * @Description TODO
 * @author H__D
 * @date 2018年7月18日
 *
 */
public class Stag1 extends Thread {
	
	@Override
	public void run() {
		// 隋唐军队
		ArmyRunnable armyTaskOfSuiDynasy = new ArmyRunnable();
		// 农名军队
		ArmyRunnable armyTaskOfRevolt = new ArmyRunnable();
		
		// 军队线程，使用Runnable接口创建线程
		Thread armyOfSuiDynasy = new Thread(armyTaskOfSuiDynasy, "隋军");
		Thread armyOfRevolt = new Thread(armyTaskOfRevolt, "起义军");
		
		// 启动线程，让军队开始作战
		armyOfSuiDynasy.start();
		armyOfRevolt.start();
		
		// 舞台线程休眠，大家专心观看军队厮杀
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		armyTaskOfSuiDynasy.keepRuning = false;
		armyTaskOfRevolt.keepRuning = false;
		
		
		try {
			//join() 等待该线程终止。
			armyOfRevolt.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		
		new Stag1().start();

	}

}
















