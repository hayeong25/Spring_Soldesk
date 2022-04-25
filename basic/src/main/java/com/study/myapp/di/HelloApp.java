package com.study.myapp.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class HelloApp {
	public static void main(String[] args) {
		System.out.println("----- 컨테이너 구동 전 -----");
		
		// Spring 컨테이너 구동
		ApplicationContext ctx = new ClassPathXmlApplicationContext("config.xml");
		
		System.out.println("----- 컨테이너 구동 후 -----");
		
		// Spring 컨테이너가 관리하는 객체 중에서 message 요청
		Message message = (Message)ctx.getBean("ko");
		message.sayHello("곰탱이");
		
		message = (Message)ctx.getBean("en");
		message.sayHello("ciro");
	}
}