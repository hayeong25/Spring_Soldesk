package com.study.myapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class ProductMain {
	public static void main(String[] args) throws Exception {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		Product product = (Product)context.getBean("product");
		product.setCompany("현대");
		product.setPname("제네시스");
		product.setPrice("15000");
		product.getInfo();
	}
}