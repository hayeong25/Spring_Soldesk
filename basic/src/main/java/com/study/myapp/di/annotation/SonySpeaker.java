package com.study.myapp.di.annotation;

import org.springframework.stereotype.Component;

@Component("sony")
public class SonySpeaker implements Speaker{
	public SonySpeaker() {
		System.out.println("SonySpeaker -- 객체 생성");
	}
	
	public void volumeUp() {
		System.out.println("SonySpeaker -- volume Up");
	}
	
	public void volumeDown() {
		System.out.println("SonySpeaker -- volume Down");
	}
}