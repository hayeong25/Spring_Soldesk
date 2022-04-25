package com.study.myapp;

/* 
	값이 NULL이라고 전부 NullPointerException이 발생하는 게 아님
	Null 값에 . 찍고 메소드 호출할 때만 발생
	
	String str = null;
	System.out.println(str); >> null
	System.out.println(str.toString()); >> NullPointerException
 */

public class TVmain {
	public static void main(String[] args) {
		// TV tv = new SamsungTV(new SonySpeaker());
		SamsungTV tv = new SamsungTV();
		
		// tv.setSpeaker(new SonySpeaker());
		tv.setSpeaker(new AppleSpeaker());
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.poweroff();
	}
}