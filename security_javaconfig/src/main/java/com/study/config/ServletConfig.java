package com.study.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

// servlet-context.xml

@Configuration // 환경설정 클래스임을 알림
@EnableWebMvc // WebMVC 환경설정 관련 annotation 활성화 <annotation-driven />
@ComponentScan(basePackages = {"com.study.controller"}) // <context:component-scan base-package="com.study.controller" />
public class ServletConfig implements WebMvcConfigurer {
	
	// <resources mapping="/resources/**" location="/resources/" />
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		registry.addResourceHandler("/resources/**").addResourceLocations("/resources/");
	}
	
	// <beans:bean class="org.springframework.web.servlet.view.InternalResourceViewResolver">
	@Override
	public void configureViewResolvers(ViewResolverRegistry registry) {
		InternalResourceViewResolver bean = new InternalResourceViewResolver();
		bean.setViewClass(JstlView.class);
		bean.setPrefix("/WEB-INF/views/");
		bean.setSuffix(".jsp");
		registry.viewResolver(bean);
	}
	
	@Bean
	public MultipartResolver multipartResolver() {
		// <beans:bean id="multipartResolver" class="org.springframework.web.multipart.commons.CommonsMultipartResolver">
		CommonsMultipartResolver resolver = new CommonsMultipartResolver();
		return resolver;
	}
}