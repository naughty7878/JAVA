/**

*/

class Single {
	private static Single s = new Single();

	private Single () {}

	public static Single getInstance () {
		return s;
	}
}

class Single2 {
	private static Single2 s = null;

	private Single2 () {}

	public static Single2 getInstance () {
		if (s == null)
			s = new Single2();

		return s;
	}
}

class SingleDemo {

	public static void main(String[] args){
		Single s1 = Single.getInstance();
		Single s2 = Single.getInstance();

		System.out.println(s1 == s2);
	}
	
}