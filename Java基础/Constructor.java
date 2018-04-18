class Person {
	private String name;
	private int age;

	Person(){
		name = "baby";
		age = 1;
		System.out.printf("person run");
	}

	Person(String name) {
		this.name = name;
	}

	public void speak() {
		System.out.printf(name + ":" + age);
	}
}

class Constructor {
	public static void main(String[] args){
		Person p = new Person("旺财");
		p.speak();
	}
}