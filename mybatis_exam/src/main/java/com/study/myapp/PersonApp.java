package com.study.myapp;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.study.service.PersonService;

public class PersonApp {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		
		PersonService service = (PersonService)context.getBean("person");
		System.out.println(service.insertPerson("ciro", "곰탱이"));
		System.out.println(service.selectPerson("ciro"));
		System.out.println(service.updatePerson("ciro", "찬료리") ? "수정 성공" : "수정 실패");
	}
}