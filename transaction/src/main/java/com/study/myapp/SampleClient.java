package com.study.myapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import com.study.service.SampleService;

public class SampleClient {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		SampleService service = (SampleService)context.getBean("service");
		
		String data = "Starry\r\n";
		data += "Starry night\r\n";
		data += "Paint your paletter blue and gray\r\n";
		data += "Look out on a summer day";
		
		service.addData(data);
	}
}