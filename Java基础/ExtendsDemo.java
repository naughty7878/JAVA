
class ExtendsDemo {

	public static void main(String[] args) {
		Student s = new Student();
		s.name = "zhangsan";
		s.age = 22;
		s.study();
	}

}

class Person1 {
	String name;
	int age;
}


class Student extends Person1 {
	//String name;
	//int age;

	void study(){
		System.out.println(name + "....student study..." + age);
	}
}

class Teacher extends Person1 {
	//String name;
	//int age;
	void work(){
		System.out.println(name + "....teacher work..." + age);
	}
}

