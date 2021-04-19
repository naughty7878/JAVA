package com.test.decorator.doc;

public class Decorator extends Drink {
	private Drink obj;

	// 组合
	public Decorator(Drink obj) {
		this.obj = obj;
	}

	@Override
	public float cost() {
		// getPrice 自己价格
		return getPrice() + obj.cost();
	}

	@Override
	public String getDes() {
		// obj.getDes() 输出被装饰者的信息
		return des + " " + getPrice() + " && " + obj.getDes();
	}

}
