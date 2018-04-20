
public class InnerClassDemo {
	public static void main(String[] args) {
		Outer out = new Outer();
		out.method();
		
		// 直接访问外部类中的内部类中的成员
		Outer.Inner oin = new Outer().new Inner();
		oin.show();
		
		// 直接访问外部类中的静态内部类
		Outer.SInner sin = new Outer.SInner();
		sin.show();
	}
}

class Outer {
	private int num = 3;

	class Inner {// 内部类
		void show() {
			System.out.println("show inner run ..." + num);
		}
	}
	
	static class SInner {
		void show(){
			System.out.println("show SInner run ...");
		}
	}
	public void method(){
		Inner in = new Inner();
		in.show();
		
		
	}
}