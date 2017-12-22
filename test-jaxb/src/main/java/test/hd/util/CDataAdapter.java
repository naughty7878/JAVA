package test.hd.util;

import javax.xml.bind.annotation.adapters.XmlAdapter;

/**
 * 有时候 Java 类不能自然映射到自己所需的 XML 形式，
 * 这时需要编写自己的适配器类，通过注解绑定到javabean的成员变量上，
 * 在运行的时候jaxb框架自动会适配你所编写的适配器类的方法，
 * CDataAdapter.marshal(String str)，将javabean的成员变量的value值
 * 转变成你想要的形式。
 * @author H__D
 * @date 2017年6月19日 下午4:32:00
 *
 */
public class CDataAdapter extends XmlAdapter<String, String> {

	// 从javabean到xml的适配方法
	@Override
	public String marshal(String str) throws Exception {
		return "<![CDATA[" + str + "]]>";
	}

	// 从xml到javabean的适配方法
	@Override
	public String unmarshal(String str) throws Exception {
		return str;
	}

}
