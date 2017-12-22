package test.hd.test_jaxb;

import java.io.IOException;
import java.io.StringReader;
import java.io.Writer;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

import com.sun.xml.internal.bind.marshaller.CharacterEscapeHandler;

import test.hd.pojo.Person;
import test.hd.pojo.Student;

public class Test {

	public static void main(String[] args) throws JAXBException {

		marshall();//对象转xml
		unMarshal();//xml转对象
	}

	/**
	 * 对象转xml
	 * 
	 * @throws JAXBException
	 */
	public static void marshall() throws JAXBException {
		Student person = new Student();
		person.setName("H__D");
		person.setAge(11);

		JAXBContext context = JAXBContext.newInstance(Student.class);
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
		marshaller.marshal(person, System.out);

	}

	/**
	 * xml转对象
	 * 
	 * @throws JAXBException
	 */
	public static void unMarshal() throws JAXBException {

		JAXBContext context = JAXBContext.newInstance(Person.class);
		Unmarshaller unmarshaller = context.createUnmarshaller();

		Person p = (Person) unmarshaller.unmarshal(new StringReader("<person><name><![CDATA[H__D]]></name><age>11</age></person>"));

		System.out.println("person = " + p.getName() + "------" + p.getAge());
	}

}
