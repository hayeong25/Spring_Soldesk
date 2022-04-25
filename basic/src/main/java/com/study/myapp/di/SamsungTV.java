package com.study.myapp.di;

public class SamsungTV implements TV {
	// Speaker를 이용한 Volume 조절
	
	// Null이므로 NullPointerException 발생 >> 초기화 필요 (생성자 or Setter 해서 초기화)
	// private SonySpeaker speaker;
	private Speaker speaker;
	
	public SamsungTV() {
		
	}
	
	public SamsungTV(Speaker speaker) {
		this.speaker = speaker;
	}
	
	// Setter로 초기화 및 변경
	public void setSpeaker(Speaker speaker) {
		this.speaker = speaker;
	}

	@Override
	public void powerOn() {
		System.out.println("Samsung TV 전원 ON");
	}

	@Override
	public void poweroff() {
		System.out.println("Samsung TV 전원 OFF");
	}

	@Override
	public void volumeUp() {
		// System.out.println("Samsung TV Volume UP");
		speaker.volumeUp();
	}

	@Override
	public void volumeDown() {
		// System.out.println("Samsung TV Volume DOWN");
		speaker.volumeDown();
	}

}