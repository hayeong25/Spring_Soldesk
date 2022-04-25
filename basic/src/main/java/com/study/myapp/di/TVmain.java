package com.study.myapp.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TVmain {
	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("config.xml");
		
		// TV tv = (TV)context.getBean("Samsung");
		TV tv = (TV)context.getBean("Lg");
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.poweroff();
	}
}