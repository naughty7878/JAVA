package test.hd.pojo;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Student extends Person{

	public int number;

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

}
