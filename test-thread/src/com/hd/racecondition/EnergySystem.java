package com.hd.racecondition;

import java.util.Iterator;

/**
 * 宇宙的能量系统
 * 遵循能量守恒定律
 * 能量不会凭空创生或消失，只会从一处转移到另一处
 * @ClassName EnergySystem 
 * @Description TODO
 * @author H__D
 * @date 2018年7月18日
 *
 */
public class EnergySystem {

	// 能量盒子，能量存储的地方
	private final double[] energyBoxes;
	
	// 锁的对象
	private final Object lockObj = new Object();

	/**
	 * 
	 * @param n 能量盒子的数量
	 * @param initialEnergy 每个能量盒子初始含有的能量值
	 */
	public EnergySystem(int n, double initialEnergy){
		energyBoxes = new double[n];
		for (int i = 0; i < energyBoxes.length; i++) {
			energyBoxes[i] = initialEnergy;
		}
	}
	
	
	public void transfer(int from, int to, double amount) {
		
		// 互斥
		synchronized (lockObj) {
			// 加锁占用资源
//			if (energyBoxes[from] < amount) {
//				return;
//			}
			
			// while循环，保证条件不满足时任务都会被条件阻挡
			// 而不是继续竞争CPU资源
			// lockobj中 的wait set 等待集合中
			while (energyBoxes[from] < amount) {
				try {
					// System.err.println(Thread.currentThread().getName() + "---wait---");
					lockObj.wait();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			System.out.print(Thread.currentThread().getName());
			energyBoxes[from] -= amount;
			System.out.printf(" 从 -%d- 转移%10.2f 单位能量到 -%d- ", from, amount, to);
			energyBoxes[to] += amount;
			System.out.printf("能量总和：%10.2f%n", getTotalEnergies());
			
			// 唤醒所有lockObj上所有等待线程
			lockObj.notifyAll(); 
		}
	
	}

	/**
	 * 获取能量世界的能量总合
	 * @Description TODO
	 * @author H__D
	 * @date 2018年7月18日
	 * @return
	 */
	public double getTotalEnergies() {
		double sum = 0;
		for (double amount : energyBoxes) {
			sum += amount;
		}
		return sum;
	}
	
	/**
	 * 返回能量盒子的长度
	 * @Description TODO
	 * @author H__D
	 * @date 2018年7月18日
	 * @return
	 */
	public int getBoxAmount(){
		return energyBoxes.length;
	}
}

















