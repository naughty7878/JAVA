package com.test.freemarker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class TestUser {

	private static final String TEMPLATE_PATH = "src/main/resources/templates";
	private static final String OUTPUT_PATH = "src/main/resources/output";

	public static void main(String[] args) throws IOException {
		// step1 创建freeMarker配置实例
		// 创建freeMarker配置实例
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_28);
		// 获取模版路径
		cfg.setDirectoryForTemplateLoading(new File(TEMPLATE_PATH));
		// 设置默认编码格式
		cfg.setDefaultEncoding("UTF-8");
		// 设置异常处理器
		cfg.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
		
		Writer out = null;
		try {
			// step2  创建数据模型
			Map<String, Object> dataMap = new HashMap<String, Object>();
			dataMap.put("classPath", "com.test.hello");
			dataMap.put("className", "User");
			dataMap.put("Id", "Id");
			dataMap.put("userName", "userName");
			dataMap.put("password", "password");
			// step3  加载模版文件
			Template template = cfg.getTemplate("user.ftl");
			// step4  生成数据
			File docFile = new File(OUTPUT_PATH + "\\" + "User.java");
			out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(docFile)));
			// step5  输出文件
			template.process(dataMap, out);
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^User.java 文件创建成功 !");
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (null != out) {
					out.flush();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
