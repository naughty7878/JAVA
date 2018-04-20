/**
 * 
 * @author H__D
 * @date 2018年4月19日 下午2:41:36
 * 
 * 一个对象实例化过程：
 * Person p = new Person();
 * 1、JVM会读取指定的路径下的Person.class文件，并加载进内存中，
 * 		并且先加载Person的父类（如果有直接父类的情况下）
 * 2、在堆内存中的开辟空间，分配地址。
 * 3、并在对象空间中，对对象中的属性进行默认初始化。
 * 4、调用对应的构造函数进行初始化。
 * 5、在构造函数中，第一行会先调用父类中构造函数进行初始化。
 * 6、父类初始化完毕后，在对子类的属性进行显示初始化
 * 7、在进行子类的构造函数的特定初始化
 * 8、初始化完毕后，降低至赋值给引用变量。
 * 
 * 
 */
public class ExtendsDemo2 {

	public static void main(String[] args) {
		Zi z = new Zi(1);
		

	}

}

class Fu {
	int num;

	Fu() {
		System.out.println("A fu run");
		System.out.println("fu num = " + num);
	}

	Fu(int n) {
		System.out.println("B fu run" + n);
	}
}

class Zi extends Fu{
	int num = 2;

	Zi() {
		//super();
		System.out.println("C Zi run");
		System.out.println("Zi num = " + num);
	}
	
	Zi(int x) {
		this();
		System.out.println("D Zi run" + x);
	}
}