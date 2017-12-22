package test.hd.pojo;

import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

@XmlRootElement
public class StudentList extends Person{

	@XmlElementWrapper(name="C")
	@XmlElement(name="s")
	private List<Student> students;

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
	
	
	public static void main(String[] args) throws JAXBException {
		
		Student student = new Student();
		student.setName( "H__D");
		student.setNumber(1);
		
		Student student2 = new Student();
		student2.setName( "H__D2");
		student2.setNumber(2);
		
		StudentList studentList = new StudentList();
		
		List<Student> list = new ArrayList<Student>();
		list.add(student);
		list.add(student2);
		
		studentList.setStudents(list);
		
		JAXBContext context = JAXBContext.newInstance(StudentList.class);
		Marshaller marshaller = context.createMarshaller();
		marshaller.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");
		// xml格式
		marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
		// 去掉生成xml的默认报文头
		// marshaller.setProperty(Marshaller.JAXB_FRAGMENT, true);
		// 不进行转义字符的处理
		marshaller.setProperty(CharacterEscapeHandler.class.getName(), new CharacterEscapeHandler() {
			public void escape(char[] ch, int start, int length, boolean isAttVal, Writer writer) throws IOException {
				writer.write(ch, start, length);
			}
		});
		// 将XML打印到控制台
		marshaller.marshal(studentList, System.out);
		
	}
	
}
