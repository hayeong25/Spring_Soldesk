package com.study.myapp.di.annotation;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/*
	UnsatisfiedDependencyException
	NoUniqueBeanDefinitionException : speaker 객체로 AppleSpeaker를 연결하는지 SonySpeaker를 연결하는지 모르겠다는 의미 >> 우리가 정확히 알려줘야 함
*/

public class TVmain {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config2.xml");
		
		// TV tv = (TV)context.getBean("samsung");
		TV tv = (TV)context.getBean("lg");
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.poweroff();
	}
}