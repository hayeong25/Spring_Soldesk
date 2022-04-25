package com.study.myapp;

public class LGTV implements TV {

	@Override
	public void powerOn() {
		System.out.println("LG TV 전원 ON");
	}

	@Override
	public void poweroff() {
		System.out.println("LG TV 전원 OFF");
	}

	@Override
	public void volumeUp() {
		System.out.println("LG TV Volume UP");
	}

	@Override
	public void volumeDown() {
		System.out.println("LG TV Volume DOWN");
	}

}
