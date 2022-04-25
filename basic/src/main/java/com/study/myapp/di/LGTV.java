package com.study.myapp.di;

public class LGTV implements TV {
	private Speaker speaker;
	
	public LGTV(Speaker speaker) {
		this.speaker = speaker;
	}

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
