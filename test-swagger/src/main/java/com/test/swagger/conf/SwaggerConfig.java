package com.test.swagger.conf;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2 // 作用是启用Swagger2相关功能。
public class SwaggerConfig {
//	@Bean
//	public Docket api() {
//		return new Docket(DocumentationType.SWAGGER_2).select() // 选择那些路径和api会生成document
//				.apis(RequestHandlerSelectors.any()) // 对所有api进行监控
//				.paths(PathSelectors.any()) // 对所有路径进行监控
//				.build();
//	}

	@Bean
	public Docket api() {
		List<ResponseMessage> responseMessageList = new ArrayList<>();
		responseMessageList.add( new ResponseMessageBuilder().code(500).message("服务器发生异常").responseModel(new ModelRef("Error")).build());
		responseMessageList.add( new ResponseMessageBuilder().code(403).message("资源不可用").build());
		
		return new Docket(DocumentationType.SWAGGER_2).select().apis(RequestHandlerSelectors.any())
				.paths(PathSelectors.any()).build().apiInfo(apiInfo()).useDefaultResponseMessages(false)
				.globalResponseMessage(RequestMethod.GET,responseMessageList);
	}

	private ApiInfo apiInfo() {
		return new ApiInfo("Spring Web 项目集成 Swagger 实例文档", "欢迎大家访问。", "API V1.0", "Terms of service",
				new Contact("OpenApi", "http://127.0.0.1", "123456@163.com"), "Apache", "http://www.apache.org/",
				Collections.emptyList());
	}
}
