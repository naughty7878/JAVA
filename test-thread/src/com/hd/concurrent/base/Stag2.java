package com.hd.concurrent.base;

/**
 * 隋唐演义大戏舞台
 * @ClassName Stag 
 * @Description TODO
 * @author H__D
 * @date 2018年7月18日
 *
 */
public class Stag2 extends Thread {
	
	@Override
	public void run() {
		
		System.out.println("～欢迎观看隋唐演义～");
		
		try {
			Thread.sleep(5000);
			System.out.println("大幕徐徐拉开");
			Thread.sleep(5000);
		} catch (InterruptedException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		System.out.println("话说隋朝末年，随军与农名起义军杀的昏天黑地。。。");
		
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
		
		
		System.out.println("正当双方激战正酣，半路杀出个程咬金～～");
		
		Thread mrCheng = new KeyPersonThread();
		mrCheng.setName("程咬金");
		
		System.out.println("程咬金的理想是结束战争，使百姓安居乐业！");
		
		// 军队停止作战
		// 停止线程的方法
		armyTaskOfSuiDynasy.keepRuning = false;
		armyTaskOfRevolt.keepRuning = false;
		
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// 关键人物上场
		mrCheng.start();
		try {
			// 等待关键人物完成使命
			mrCheng.join();
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("战争结束，人民安居乐业，程咬金走向人生颠覆。。。");
		System.out.println("谢谢观看隋唐演义，再见！");
	}
	
	public static void main(String[] args) {
		
		new Stag2().start();

	}

}
















