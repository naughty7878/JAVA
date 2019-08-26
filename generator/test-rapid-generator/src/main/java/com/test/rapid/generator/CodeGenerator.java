package com.test.rapid.generator;

import cn.org.rapid_framework.generator.GeneratorFacade;

/**
 * 
 * @author H__D
 * @date 2019-07-31 22:27:05
 *
 */
public class CodeGenerator {

	public static void main(String[] args) throws Exception {

		// 模板地址
		String templatePath = "classpath:template";
		GeneratorFacade g = new GeneratorFacade();
		g.getGenerator().addTemplateRootDir(templatePath);
		// 删除生成器的输出目录//
		g.deleteOutRootDir();
		// 通过数据库表生成文件
		g.generateByTable("god_user");
		
		// 自动搜索数据库中的所有表并生成文件,template为模板的根目录
		// g.generateByAllTable();
		// 按table名字删除文件
		// g.deleteByTable("table_name", "template");
		//打开文件夹
		//Runtime.getRuntime().exec("cmd.exe /c start "+GeneratorProperties.getRequiredProperty("outRoot"));
	}

}
