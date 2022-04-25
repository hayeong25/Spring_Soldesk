package com.study.myapp;

public class AppleSpeaker implements Speaker {
	public AppleSpeaker() {
		System.out.println("AppleSpeaker 객체 생성");
	}
	
	public void volumeUp() {
		System.out.println("AppleSpeaker volume UP");
	}
	
	public void volumeDown() {
		System.out.println("AppleSpeaker volume DOWN");
	}
}