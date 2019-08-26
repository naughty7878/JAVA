package com.test.freemarker;

import java.io.File;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateExceptionHandler;

public class Test {

	private static final String TEMPLATE_PATH = "src/main/resources/templates";

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
			Map root = new HashMap();
	        root.put("user", "Big Joe");
	        Map latest = new HashMap();
	        root.put("latestProduct", latest);
	        latest.put("url", "products/greenmouse.html");
	        latest.put("name", "green mouse");

	    	// step3  加载模版文件
	        Template temp = cfg.getTemplate("test.ftl");

	        // step4  生成数据
	        out = new OutputStreamWriter(System.out);
	        temp.process(root, out);
			
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
