package com.study.myapp.di.annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component("lg")
public class LGTV implements TV {
	
	@Autowired
	@Qualifier("sony")
	private Speaker speaker;

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
		// System.out.println("LG TV Volume UP");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		// System.out.println("LG TV Volume DOWN");
		speaker.volumeDown();
	}

}
