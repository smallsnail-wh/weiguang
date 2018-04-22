package com.wh.weiguang.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {
	@Bean
	public Docket buildDocket() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(buildApiInfo()).select()
				// 要扫描的API(Controller)基础包
				.apis(RequestHandlerSelectors.basePackage("com.wh.weiguang")).paths(PathSelectors.any())
				.build();
	}

	private ApiInfo buildApiInfo() {
		return new ApiInfoBuilder().title("API文档").description("微广接口文档")
				.version("1.0").build();
	}
}
